/*
 * Copyright (c) 2024. Patches Barrett
 *
 * Last modified: September 22, 2024
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

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.core.assertions.NoOpWisefyAssertions
import com.isupatches.android.wisefy.core.assertions.WisefyAssertions
import com.isupatches.android.wisefy.core.constants.DeprecationMessages
import com.isupatches.android.wisefy.core.entities.NetworkConnectionStatus
import com.isupatches.android.wisefy.core.exceptions.WisefyException
import com.isupatches.android.wisefy.core.logging.NoOpWisefyLogger
import com.isupatches.android.wisefy.core.logging.WisefyLogger
import com.isupatches.android.wisefy.core.util.AndroidUtil
import com.isupatches.android.wisefy.networkconnection.callbacks.ChangeNetworkCallbacks
import com.isupatches.android.wisefy.networkconnection.callbacks.ConnectToNetworkCallbacks
import com.isupatches.android.wisefy.networkconnection.callbacks.DisconnectFromCurrentNetworkCallbacks
import com.isupatches.android.wisefy.networkconnection.entities.ChangeNetworkRequest
import com.isupatches.android.wisefy.networkconnection.entities.ChangeNetworkResult
import com.isupatches.android.wisefy.networkconnection.entities.ConnectToNetworkRequest
import com.isupatches.android.wisefy.networkconnection.entities.ConnectToNetworkResult
import com.isupatches.android.wisefy.networkconnection.entities.DisconnectFromCurrentNetworkRequest
import com.isupatches.android.wisefy.networkconnection.entities.DisconnectFromCurrentNetworkResult
import com.isupatches.android.wisefy.networkconnection.os.adapters.Android29NetworkConnectionAdapter
import com.isupatches.android.wisefy.networkconnection.os.adapters.DefaultNetworkConnectionAdapter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlin.coroutines.cancellation.CancellationException

/**
 * An internal Wisefy delegate for getting and searching for nearby access points through the Android OS.
 *
 * @param connectivityManager The ConnectivityManager instance to use
 * @param wifiManager The WifiManager instance to use
 * @param networkConnectionStatusProvider The on-demand way to retrieve the current network connection status
 * @param scope The coroutine scope to use
 * @param networkConnectionMutex The mutex for all read/write operations involving connecting, disconnecting, and
 * getting the device's current network and connection status
 * @param assertions The [WisefyAssertions] instance to use (defaults to no-op)
 * @param logger The [WisefyLogger] instance to use (defaults to no-op)
 * @param mainDispatcher The main thread dispatcher
 * @param adapter The adapter instance to use for connecting, disconnecting, and changing networks
 * (determined based on the Android OS level)
 *
 * @see Android29NetworkConnectionAdapter
 * @see DefaultNetworkConnectionAdapter
 * @see NetworkConnectionDelegate
 * @see NetworkConnectionStatus
 * @see WisefyAssertions
 * @see WisefyLogger
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
class WisefyNetworkConnectionDelegate(
    connectivityManager: ConnectivityManager,
    wifiManager: WifiManager,
    networkConnectionStatusProvider: suspend () -> NetworkConnectionStatus?,
    private val scope: CoroutineScope,
    private val networkConnectionMutex: Mutex,
    assertions: WisefyAssertions = NoOpWisefyAssertions(),
    logger: WisefyLogger = NoOpWisefyLogger(),
    private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main,
    private val adapter: NetworkConnectionApi = if (AndroidUtil.isAtLeastQ()) {
        Android29NetworkConnectionAdapter(
            logger,
            assertions,
        )
    } else {
        DefaultNetworkConnectionAdapter(
            connectivityManager,
            wifiManager,
            networkConnectionStatusProvider,
            AndroidUtil.isAtLeastS(),
            logger,
            assertions,
        )
    },
) : NetworkConnectionDelegate {

    init {
        logger.d(LOG_TAG, "WisefyNetworkConnectionDelegate adapter is: ${adapter::class.java.simpleName}")
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun changeNetwork(request: ChangeNetworkRequest): ChangeNetworkResult {
        return adapter.changeNetwork(request)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    @Suppress("TooGenericExceptionCaught")
    override fun changeNetwork(
        request: ChangeNetworkRequest,
        callbacks: ChangeNetworkCallbacks?,
    ) {
        scope.launch {
            networkConnectionMutex.withLock {
                try {
                    val result = adapter.changeNetwork(request)
                    withContext(mainDispatcher) {
                        when (result) {
                            is ChangeNetworkResult.Success -> callbacks?.onSuccessChangingNetworks(result)
                            is ChangeNetworkResult.Failure -> callbacks?.onFailureChangingNetworks(result)
                        }
                    }
                } catch (ex: CancellationException) {
                    throw ex
                } catch (ex: Exception) {
                    withContext(mainDispatcher) {
                        callbacks?.onWisefyAsyncFailure(
                            WisefyException(message = "Internal Wisefy error with changeNetwork", throwable = ex),
                        )
                    }
                }
            }
        }
    }

    @Deprecated(DeprecationMessages.NetworkConnection.CONNECT_TO_NETWORK)
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE, ACCESS_NETWORK_STATE])
    override fun connectToNetwork(request: ConnectToNetworkRequest): ConnectToNetworkResult {
        @Suppress("Deprecation")
        return adapter.connectToNetwork(request)
    }

    @Deprecated(DeprecationMessages.NetworkConnection.CONNECT_TO_NETWORK)
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE, ACCESS_NETWORK_STATE])
    @Suppress("TooGenericExceptionCaught")
    override fun connectToNetwork(
        request: ConnectToNetworkRequest,
        callbacks: ConnectToNetworkCallbacks?,
    ) {
        scope.launch {
            networkConnectionMutex.withLock {
                try {
                    @Suppress("Deprecation")
                    val result = adapter.connectToNetwork(request)
                    withContext(mainDispatcher) {
                        when (result) {
                            is ConnectToNetworkResult.Success -> callbacks?.onSuccessConnectingToNetwork(result)
                            is ConnectToNetworkResult.Failure -> callbacks?.onFailureConnectingToNetwork(result)
                        }
                    }
                } catch (ex: CancellationException) {
                    throw ex
                } catch (ex: Exception) {
                    withContext(mainDispatcher) {
                        callbacks?.onWisefyAsyncFailure(
                            WisefyException(message = "Internal Wisefy error with connectToNetwork", throwable = ex),
                        )
                    }
                }
            }
        }
    }

    @Deprecated(DeprecationMessages.NetworkConnection.DISCONNECT_FROM_CURRENT_NETWORK)
    override fun disconnectFromCurrentNetwork(
        request: DisconnectFromCurrentNetworkRequest,
    ): DisconnectFromCurrentNetworkResult {
        @Suppress("Deprecation")
        return adapter.disconnectFromCurrentNetwork(request)
    }

    @Deprecated(DeprecationMessages.NetworkConnection.DISCONNECT_FROM_CURRENT_NETWORK)
    @Suppress("TooGenericExceptionCaught")
    override fun disconnectFromCurrentNetwork(
        request: DisconnectFromCurrentNetworkRequest,
        callbacks: DisconnectFromCurrentNetworkCallbacks?,
    ) {
        scope.launch {
            networkConnectionMutex.withLock {
                try {
                    @Suppress("Deprecation")
                    val result = adapter.disconnectFromCurrentNetwork(request)
                    withContext(mainDispatcher) {
                        when (result) {
                            is DisconnectFromCurrentNetworkResult.Success -> {
                                callbacks?.onSuccessDisconnectingFromCurrentNetwork(result)
                            }

                            is DisconnectFromCurrentNetworkResult.Failure -> {
                                callbacks?.onFailureDisconnectingFromCurrentNetwork(result)
                            }
                        }
                    }
                } catch (ex: CancellationException) {
                    throw ex
                } catch (ex: Exception) {
                    withContext(mainDispatcher) {
                        callbacks?.onWisefyAsyncFailure(
                            WisefyException(
                                message = "Internal Wisefy error with disconnectFromCurrentNetwork",
                                throwable = ex,
                            ),
                        )
                    }
                }
            }
        }
    }

    companion object {
        private const val LOG_TAG = "WisefyNetworkConnectionDelegate"
    }
}
