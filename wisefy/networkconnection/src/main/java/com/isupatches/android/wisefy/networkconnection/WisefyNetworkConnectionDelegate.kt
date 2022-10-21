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
import com.isupatches.android.wisefy.core.assertions.WisefyAssertions
import com.isupatches.android.wisefy.core.constants.DeprecationMessages
import com.isupatches.android.wisefy.core.coroutines.CoroutineDispatcherProvider
import com.isupatches.android.wisefy.core.coroutines.createBaseCoroutineExceptionHandler
import com.isupatches.android.wisefy.core.exceptions.WisefyException
import com.isupatches.android.wisefy.core.logging.WisefyLogger
import com.isupatches.android.wisefy.core.util.SdkUtil
import com.isupatches.android.wisefy.networkconnection.callbacks.ConnectToNetworkCallbacks
import com.isupatches.android.wisefy.networkconnection.callbacks.DisconnectFromCurrentNetworkCallbacks
import com.isupatches.android.wisefy.networkconnection.entities.ConnectToNetworkRequest
import com.isupatches.android.wisefy.networkconnection.entities.ConnectToNetworkResult
import com.isupatches.android.wisefy.networkconnection.entities.DisconnectFromCurrentNetworkRequest
import com.isupatches.android.wisefy.networkconnection.entities.DisconnectFromCurrentNetworkResult
import com.isupatches.android.wisefy.networkconnection.os.adapters.Android29NetworkConnectionAdapter
import com.isupatches.android.wisefy.networkconnection.os.adapters.DefaultNetworkConnectionAdapter
import com.isupatches.android.wisefy.networkconnectionstatus.NetworkConnectionStatusDelegate
import com.isupatches.android.wisefy.savednetworks.SavedNetworkDelegate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

/**
 * An internal Wisefy delegate for getting and searching for nearby access points through the Android OS.
 *
 * @param coroutineDispatcherProvider The instance of the coroutine dispatcher provider to use
 * @param scope The coroutine scope to use
 * @param connectivityManager The ConnectivityManager instance to use
 * @param logger The logger instance to use
 * @param networkConnectionStatusDelegate The NetworkConnectionStatusDelegate instance to use
 * @param savedNetworkDelegate The SavedNetworkDelegate instance to use
 * @param sdkUtil The SdkUtil instance to use
 * @param wifiManager The WifiManager instance to use
 *
 * @see CoroutineDispatcherProvider
 * @see NetworkConnectionDelegate
 * @see NetworkConnectionStatusDelegate
 * @see SavedNetworkDelegate
 * @see SdkUtil
 * @see WisefyLogger
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
class WisefyNetworkConnectionDelegate(
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider,
    private val scope: CoroutineScope,
    private val networkConnectionMutex: Mutex,
    assertions: WisefyAssertions,
    connectivityManager: ConnectivityManager,
    logger: WisefyLogger,
    networkConnectionStatusDelegate: NetworkConnectionStatusDelegate,
    savedNetworkDelegate: SavedNetworkDelegate,
    sdkUtil: SdkUtil,
    wifiManager: WifiManager
) : NetworkConnectionDelegate {

    companion object {
        private const val LOG_TAG = "WisefyNetworkConnectionDelegate"
    }

    private val adapter = when {
        sdkUtil.isAtLeastQ() -> Android29NetworkConnectionAdapter(connectivityManager, logger, assertions)
        else -> {
            DefaultNetworkConnectionAdapter(
                wifiManager,
                networkConnectionStatusDelegate,
                savedNetworkDelegate,
                logger
            )
        }
    }

    init {
        logger.d(LOG_TAG, "WisefyNetworkConnectionDelegate adapter is: ${adapter::class.java.simpleName}")
    }

    override fun connectToNetwork(request: ConnectToNetworkRequest): ConnectToNetworkResult {
        return adapter.connectToNetwork(request)
    }

    override fun connectToNetwork(request: ConnectToNetworkRequest, callbacks: ConnectToNetworkCallbacks?) {
        scope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            networkConnectionMutex.withLock {
                val result = adapter.connectToNetwork(request)
                withContext(coroutineDispatcherProvider.main) {
                    when (result) {
                        is ConnectToNetworkResult.Success.True -> callbacks?.onConnectedToNetwork()
                        is ConnectToNetworkResult.Success.ConnectionRequestSent -> {
                            callbacks?.onConnectionRequestPlaced()
                        }
                        is ConnectToNetworkResult.Failure.False -> callbacks?.onFailureConnectingToNetwork()
                        is ConnectToNetworkResult.Failure.NetworkNotFound -> callbacks?.onNetworkNotFoundToConnectTo()
                    }
                }
            }
        }
    }

    @Deprecated(DeprecationMessages.NetworkConnection.DISCONNECT_FROM_CURRENT_NETWORK)
    override fun disconnectFromCurrentNetwork(
        request: DisconnectFromCurrentNetworkRequest
    ): DisconnectFromCurrentNetworkResult {
        return adapter.disconnectFromCurrentNetwork(request)
    }

    @Deprecated(DeprecationMessages.NetworkConnection.DISCONNECT_FROM_CURRENT_NETWORK)
    override fun disconnectFromCurrentNetwork(
        request: DisconnectFromCurrentNetworkRequest,
        callbacks: DisconnectFromCurrentNetworkCallbacks?
    ) {
        scope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            networkConnectionMutex.withLock {
                val result = adapter.disconnectFromCurrentNetwork(request)
                withContext(coroutineDispatcherProvider.main) {
                    when (result) {
                        is DisconnectFromCurrentNetworkResult.Success.True -> {
                            callbacks?.onDisconnectedFromCurrentNetwork()
                        }
                        is DisconnectFromCurrentNetworkResult.Success.DisconnectRequestSent -> {
                            callbacks?.onDisconnectRequestPlaced()
                        }
                        is DisconnectFromCurrentNetworkResult.Failure.False -> {
                            callbacks?.onFailureDisconnectingFromCurrentNetwork()
                        }
                        is DisconnectFromCurrentNetworkResult.Failure.NetworkNotFound -> {
                            callbacks?.onNetworkNotFoundToDisconnectFrom()
                        }
                        is DisconnectFromCurrentNetworkResult.Failure.Assertion -> {
                            callbacks?.onWisefyAsyncFailure(WisefyException(message = result.message, throwable = null))
                        }
                    }
                }
            }
        }
    }
}
