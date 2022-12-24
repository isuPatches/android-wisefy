/*
 * Copyright 2022 Patches Barrett
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
import android.Manifest.permission.CHANGE_NETWORK_STATE
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.core.assertions.WisefyAssertions
import com.isupatches.android.wisefy.core.coroutines.CoroutineDispatcherProvider
import com.isupatches.android.wisefy.core.coroutines.createBaseCoroutineExceptionHandler
import com.isupatches.android.wisefy.core.entities.NetworkConnectionStatus
import com.isupatches.android.wisefy.core.logging.WisefyLogger
import com.isupatches.android.wisefy.core.util.SdkUtil
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
 * @param networkConnectionMutex
 * @param connectivityManager The ConnectivityManager instance to use
 * @param logger The logger instance to use
 * @param sdkUtil The SdkUtil instance to use
 * @param wifiManager The WifiManager instance to use
 *
 * @see CoroutineDispatcherProvider
 * @see NetworkConnectionDelegate
 * @see SdkUtil
 * @see WisefyLogger
 *
 * @author Patches Barrett
 * @since 03/2022
 */
class WisefyNetworkConnectionDelegate(
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider,
    private val scope: CoroutineScope,
    private val networkConnectionMutex: Mutex,
    assertions: WisefyAssertions,
    connectivityManager: ConnectivityManager,
    logger: WisefyLogger,
    sdkUtil: SdkUtil,
    wifiManager: WifiManager,
    networkConnectionStatusProvider: suspend () -> NetworkConnectionStatus?,
    private val adapter: NetworkConnectionApi = when {
        sdkUtil.isAtLeastQ() -> Android29NetworkConnectionAdapter(
            logger,
            assertions
        )
        else -> DefaultNetworkConnectionAdapter(
            connectivityManager,
            wifiManager,
            logger,
            assertions,
            sdkUtil,
            networkConnectionStatusProvider
        )
    }
) : NetworkConnectionDelegate {

    companion object {
        private const val LOG_TAG = "WisefyNetworkConnectionDelegate"
    }

    init {
        logger.d(LOG_TAG, "WisefyNetworkConnectionDelegate adapter is: ${adapter::class.java.simpleName}")
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun changeNetwork(request: ChangeNetworkRequest): ChangeNetworkResult {
        return adapter.changeNetwork(request)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun changeNetwork(request: ChangeNetworkRequest, callbacks: ChangeNetworkCallbacks?) {
        scope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            networkConnectionMutex.withLock {
                val result = adapter.changeNetwork(request)
                withContext(coroutineDispatcherProvider.main) {
                    when (result) {
                        is ChangeNetworkResult.Success -> callbacks?.onSuccessChangingNetworks(result)
                        is ChangeNetworkResult.Failure -> callbacks?.onFailureChangingNetworks(result)
                    }
                }
            }
        }
    }

    @Deprecated("")
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE, ACCESS_NETWORK_STATE, CHANGE_NETWORK_STATE])
    override fun connectToNetwork(request: ConnectToNetworkRequest): ConnectToNetworkResult {
        return adapter.connectToNetwork(request)
    }

    @Deprecated("")
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE, ACCESS_NETWORK_STATE, CHANGE_NETWORK_STATE])
    override fun connectToNetwork(request: ConnectToNetworkRequest, callbacks: ConnectToNetworkCallbacks?) {
        scope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            networkConnectionMutex.withLock {
                val result = adapter.connectToNetwork(request)
                withContext(coroutineDispatcherProvider.main) {
                    when (result) {
                        is ConnectToNetworkResult.Success -> callbacks?.onSuccessConnectingToNetwork(result)
                        is ConnectToNetworkResult.Failure -> callbacks?.onFailureConnectingToNetwork(result)
                    }
                }
            }
        }
    }

    @Deprecated("")
    override fun disconnectFromCurrentNetwork(
        request: DisconnectFromCurrentNetworkRequest
    ): DisconnectFromCurrentNetworkResult {
        return adapter.disconnectFromCurrentNetwork(request)
    }

    @Deprecated("")
    override fun disconnectFromCurrentNetwork(
        request: DisconnectFromCurrentNetworkRequest,
        callbacks: DisconnectFromCurrentNetworkCallbacks?
    ) {
        scope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            networkConnectionMutex.withLock {
                val result = adapter.disconnectFromCurrentNetwork(request)
                withContext(coroutineDispatcherProvider.main) {
                    when (result) {
                        is DisconnectFromCurrentNetworkResult.Success -> {
                            callbacks?.onSuccessDisconnectingFromCurrentNetwork(result)
                        }
                        is DisconnectFromCurrentNetworkResult.Failure -> {
                            callbacks?.onFailureDisconnectingFromCurrentNetwork(result)
                        }
                    }
                }
            }
        }
    }
}
