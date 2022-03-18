/*
 * Copyright 2022 Patches Klinefelter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.isupatches.android.wisefy.networkconnection

import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import com.isupatches.android.wisefy.networkconnection.callbacks.ConnectToNetworkCallbacks
import com.isupatches.android.wisefy.networkconnection.callbacks.DisconnectFromCurrentNetworkCallbacks
import com.isupatches.android.wisefy.networkconnection.entities.NetworkConnectionRequest
import com.isupatches.android.wisefy.networkconnection.entities.NetworkConnectionResult
import com.isupatches.android.wisefy.networkconnection.os.adapters.Android29NetworkConnectionAdapter
import com.isupatches.android.wisefy.networkconnection.os.adapters.DefaultNetworkConnectionAdapter
import com.isupatches.android.wisefy.networkconnectionstatus.NetworkConnectionStatusDelegate
import com.isupatches.android.wisefy.savednetworks.SavedNetworkDelegate
import com.isupatches.android.wisefy.shared.coroutines.CoroutineDispatcherProvider
import com.isupatches.android.wisefy.shared.coroutines.createBaseCoroutineExceptionHandler
import com.isupatches.android.wisefy.shared.logging.WisefyLogger
import com.isupatches.android.wisefy.shared.util.SdkUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WisefyNetworkConnectionDelegate(
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider,
    connectivityManager: ConnectivityManager,
    logger: WisefyLogger,
    networkConnectionStatusUtil: NetworkConnectionStatusDelegate,
    savedNetworkUtil: SavedNetworkDelegate,
    sdkUtil: SdkUtil,
    wifiManager: WifiManager
) : NetworkConnectionDelegate {

    companion object {
        private const val LOG_TAG = "WisefyNetworkConnectionUtil"
    }

    private val networkConnectionScope = CoroutineScope(Job() + coroutineDispatcherProvider.io)

    private val proxy = when {
        sdkUtil.isAtLeastQ() -> Android29NetworkConnectionAdapter(connectivityManager, logger)
        else -> {
            DefaultNetworkConnectionAdapter(
                wifiManager,
                networkConnectionStatusUtil,
                savedNetworkUtil,
                logger
            )
        }
    }

    init {
        logger.d(LOG_TAG, "WisefyNetworkConnectionUtil proxy is: ${proxy::class.java.simpleName}")
    }

    override fun connectToNetwork(request: NetworkConnectionRequest): NetworkConnectionResult {
        return proxy.connectToNetwork(request)
    }

    override fun connectToNetwork(request: NetworkConnectionRequest, callbacks: ConnectToNetworkCallbacks?) {
        networkConnectionScope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val result = proxy.connectToNetwork(request)
            withContext(coroutineDispatcherProvider.main) {
                when (result) {
                    is NetworkConnectionResult.Succeeded -> {
                        if (result.value) {
                            callbacks?.onConnectedToNetwork()
                        } else {
                            callbacks?.onFailureConnectingToNetwork()
                        }
                    }
                    is NetworkConnectionResult.ConnectionRequestSent -> {
                        callbacks?.onConnectionRequestPlaced()
                    }
                    is NetworkConnectionResult.NetworkNotFound -> {
                        callbacks?.onNetworkNotFoundToConnectTo()
                    }
                    is NetworkConnectionResult.UnregisterRequestSent -> {
                        // No-op
                    }
                }
            }
        }
    }

    override fun disconnectFromCurrentNetwork(): NetworkConnectionResult {
        return proxy.disconnectFromCurrentNetwork()
    }

    override fun disconnectFromCurrentNetwork(callbacks: DisconnectFromCurrentNetworkCallbacks?) {
        networkConnectionScope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val result = proxy.disconnectFromCurrentNetwork()
            withContext(coroutineDispatcherProvider.main) {
                when (result) {
                    is NetworkConnectionResult.Succeeded -> {
                        if (result.value) {
                            callbacks?.onDisconnectedFromCurrentNetwork()
                        } else {
                            callbacks?.onFailureDisconnectingFromCurrentNetwork()
                        }
                    }

                    is NetworkConnectionResult.UnregisterRequestSent -> {
                        callbacks?.onUnregisterRequestPlaced()
                    }
                    is NetworkConnectionResult.NetworkNotFound -> {
                        callbacks?.onNetworkNotFoundToDisconnectFrom()
                    }
                    is NetworkConnectionResult.ConnectionRequestSent -> {
                        // No-op
                    }
                }
            }
        }
    }
}
