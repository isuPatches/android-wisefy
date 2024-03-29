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
package com.isupatches.android.wisefy.networkinfo

import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.core.coroutines.CoroutineDispatcherProvider
import com.isupatches.android.wisefy.core.coroutines.createBaseCoroutineExceptionHandler
import com.isupatches.android.wisefy.core.entities.NetworkConnectionStatus
import com.isupatches.android.wisefy.core.logging.WisefyLogger
import com.isupatches.android.wisefy.core.util.SdkUtil
import com.isupatches.android.wisefy.networkinfo.callbacks.GetCurrentNetworkCallbacks
import com.isupatches.android.wisefy.networkinfo.callbacks.GetNetworkConnectionStatusCallbacks
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkQuery
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkResult
import com.isupatches.android.wisefy.networkinfo.entities.GetNetworkConnectionStatusQuery
import com.isupatches.android.wisefy.networkinfo.entities.GetNetworkConnectionStatusResult
import com.isupatches.android.wisefy.networkinfo.os.adapters.DefaultNetworkInfoAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

/**
 * An internal Wisefy delegate for getting information about a network, the device's current network,
 * and the device's IP through the Android OS.
 *
 * @param connectivityManager The ConnectivityManager instance to use
 * @param logger The [WisefyLogger] instance to use
 * @param sdkUtil The [SdkUtil] instance to use
 * @param wifiManager The WifiManager instance to use
 * @param networkConnectionStatusProvider The on-demand way to retrieve the current network connection status
 * @property coroutineDispatcherProvider The instance of the coroutine dispatcher provider to use
 * @property scope The coroutine scope to use
 * @property networkConnectionMutex The mutex for all read/write operations involving connecting, disconnecting, and
 * getting the device's current network and connection status
 * @property adapter The adapter instance to use for getting the device's current network and connection status
 * (determined based on the Android OS level)
 *
 * @see CoroutineDispatcherProvider
 * @see DefaultNetworkInfoAdapter
 * @see NetworkInfoApi
 * @see NetworkConnectionStatus
 * @see NetworkInfoDelegate
 * @see SdkUtil
 * @see WisefyLogger
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
class WisefyNetworkInfoDelegate(
    connectivityManager: ConnectivityManager,
    logger: WisefyLogger,
    sdkUtil: SdkUtil,
    wifiManager: WifiManager,
    networkConnectionStatusProvider: suspend () -> NetworkConnectionStatus?,
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider,
    private val scope: CoroutineScope,
    private val networkConnectionMutex: Mutex,
    private val adapter: NetworkInfoApi = DefaultNetworkInfoAdapter(
        connectivityManager = connectivityManager,
        wifiManager = wifiManager,
        sdkUtil = sdkUtil,
        logger = logger,
        networkConnectionStatusProvider = networkConnectionStatusProvider
    )
) : NetworkInfoDelegate {

    init {
        logger.d(LOG_TAG, "WisefyNetworkInfoDelegate adapter is: ${adapter::class.java.simpleName}")
    }

    override fun getCurrentNetwork(query: GetCurrentNetworkQuery): GetCurrentNetworkResult {
        return adapter.getCurrentNetwork(query)
    }

    override fun getCurrentNetwork(query: GetCurrentNetworkQuery, callbacks: GetCurrentNetworkCallbacks?) {
        scope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            networkConnectionMutex.withLock {
                val currentNetwork = adapter.getCurrentNetwork(query)
                withContext(coroutineDispatcherProvider.main) {
                    callbacks?.onCurrentNetworkRetrieved(currentNetwork.value)
                }
            }
        }
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getNetworkConnectionStatus(query: GetNetworkConnectionStatusQuery): GetNetworkConnectionStatusResult {
        return adapter.getNetworkConnectionStatus(query)
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getNetworkConnectionStatus(
        query: GetNetworkConnectionStatusQuery,
        callbacks: GetNetworkConnectionStatusCallbacks?
    ) {
        scope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val result = adapter.getNetworkConnectionStatus(query)
            withContext(coroutineDispatcherProvider.main) {
                callbacks?.onDeviceNetworkConnectionStatusRetrieved(result.value)
            }
        }
    }

    companion object {
        private const val LOG_TAG = "WisefyNetworkInfoDelegate"
    }
}
