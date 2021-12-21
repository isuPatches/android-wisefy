/*
 * Copyright 2021 Patches Klinefelter
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
import com.isupatches.android.wisefy.callbacks.ConnectToNetworkCallbacks
import com.isupatches.android.wisefy.callbacks.DisconnectFromCurrentNetworkCallbacks
import com.isupatches.android.wisefy.logging.WisefyLogger
import com.isupatches.android.wisefy.networkconnection.delegates.Android29NetworkConnectionDelegate
import com.isupatches.android.wisefy.networkconnection.delegates.LegacyNetworkConnectionDelegate
import com.isupatches.android.wisefy.networkconnection.entities.NetworkConnectionRequest
import com.isupatches.android.wisefy.networkconnection.entities.NetworkConnectionResult
import com.isupatches.android.wisefy.networkconnectionstatus.NetworkConnectionStatusUtil
import com.isupatches.android.wisefy.savednetworks.SavedNetworkUtil
import com.isupatches.android.wisefy.util.SdkUtil
import com.isupatches.android.wisefy.util.coroutines.CoroutineDispatcherProvider
import com.isupatches.android.wisefy.util.coroutines.createBaseCoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal interface NetworkConnectionUtil : NetworkConnectionApi, NetworkConnectionApiAsync

private const val LOG_TAG = "WisefyNetworkConnectionUtil"

internal class WisefyNetworkConnectionUtil(
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider,
    connectivityManager: ConnectivityManager,
    logger: WisefyLogger?,
    networkConnectionStatusUtil: NetworkConnectionStatusUtil,
    savedNetworkUtil: SavedNetworkUtil,
    sdkUtil: SdkUtil,
    wifiManager: WifiManager
) : NetworkConnectionUtil {

    private val networkConnectionScope = CoroutineScope(Job() + coroutineDispatcherProvider.io)

    private val delegate = when {
        sdkUtil.isAtLeastQ() -> Android29NetworkConnectionDelegate(connectivityManager, logger)
        else -> {
            LegacyNetworkConnectionDelegate(
                wifiManager,
                networkConnectionStatusUtil,
                savedNetworkUtil,
                logger
            )
        }
    }

    init {
        logger?.d(LOG_TAG, "WisefyNetworkConnectionUtil delegate is: ${delegate::class.java.simpleName}")
    }

    override fun connectToNetwork(request: NetworkConnectionRequest): NetworkConnectionResult {
        return delegate.connectToNetwork(request)
    }

    override fun connectToNetwork(request: NetworkConnectionRequest, callbacks: ConnectToNetworkCallbacks?) {
        networkConnectionScope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val result = delegate.connectToNetwork(request)
            withContext(coroutineDispatcherProvider.main) {
                when (result) {
                    is NetworkConnectionResult.Succeeded -> {
                        if (result.value) {
                            callbacks?.onConnectedToNetwork()
                        } else {
                            callbacks?.onFailureConnectingToNetwork()
                        }
                    }
                    is NetworkConnectionResult.ConnectionRequestPlaced -> {
                        callbacks?.onConnectionRequestPlaced()
                    }
                    is NetworkConnectionResult.NetworkNotFound -> {
                        callbacks?.onNetworkNotFoundToConnectTo()
                    }
                }
            }
        }
    }

    override fun disconnectFromCurrentNetwork(): NetworkConnectionResult {
        return delegate.disconnectFromCurrentNetwork()
    }

    override fun disconnectFromCurrentNetwork(callbacks: DisconnectFromCurrentNetworkCallbacks?) {
        networkConnectionScope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val result = delegate.disconnectFromCurrentNetwork()
            withContext(coroutineDispatcherProvider.main) {
                when (result) {
                    is NetworkConnectionResult.Succeeded -> {
                        if (result.value) {
                            callbacks?.onDisconnectedFromCurrentNetwork()
                        } else {
                            callbacks?.onFailureDisconnectingFromCurrentNetwork()
                        }
                    }
                    else -> {
                        // No-op
                    }
                }
            }
        }
    }
}
