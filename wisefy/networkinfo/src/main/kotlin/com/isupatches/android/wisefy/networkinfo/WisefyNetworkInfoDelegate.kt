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
package com.isupatches.android.wisefy.networkinfo

import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.core.entities.NetworkConnectionStatus
import com.isupatches.android.wisefy.core.exceptions.WisefyException
import com.isupatches.android.wisefy.core.logging.NoOpWisefyLogger
import com.isupatches.android.wisefy.core.logging.WisefyLogger
import com.isupatches.android.wisefy.core.util.AndroidUtil
import com.isupatches.android.wisefy.networkinfo.callbacks.GetCurrentNetworkCallbacks
import com.isupatches.android.wisefy.networkinfo.callbacks.GetNetworkConnectionStatusCallbacks
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkQuery
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkResult
import com.isupatches.android.wisefy.networkinfo.entities.GetNetworkConnectionStatusQuery
import com.isupatches.android.wisefy.networkinfo.entities.GetNetworkConnectionStatusResult
import com.isupatches.android.wisefy.networkinfo.os.adapters.DefaultNetworkInfoAdapter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlin.coroutines.cancellation.CancellationException

/**
 * An internal Wisefy delegate for getting information about a network, the device's current network,
 * and the device's IP through the Android OS.
 *
 * @param connectivityManager The ConnectivityManager instance to use
 * @param wifiManager The WifiManager instance to use
 * @param networkConnectionStatusProvider The on-demand way to retrieve the current network connection status
 * @param scope The coroutine scope to use
 * @param networkConnectionMutex The mutex for all read/write operations involving connecting, disconnecting, and
 * getting the device's current network and connection status
 * @param logger The [WisefyLogger] instance to use (defaults to no-op)
 * @param mainDispatcher The main thread dispatcher
 * @param adapter The adapter instance to use for getting the device's current network and connection status
 * (determined based on the Android OS level)
 *
 * @see DefaultNetworkInfoAdapter
 * @see NetworkInfoApi
 * @see NetworkConnectionStatus
 * @see NetworkInfoDelegate
 * @see WisefyLogger
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
@Suppress("LongParameterList")
class WisefyNetworkInfoDelegate(
    connectivityManager: ConnectivityManager,
    wifiManager: WifiManager,
    networkConnectionStatusProvider: suspend () -> NetworkConnectionStatus?,
    private val scope: CoroutineScope,
    private val networkConnectionMutex: Mutex,
    logger: WisefyLogger = NoOpWisefyLogger(),
    private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main,
    private val adapter: NetworkInfoApi = DefaultNetworkInfoAdapter(
        connectivityManager = connectivityManager,
        wifiManager = wifiManager,
        logger = logger,
        isAtLeastAndroidP = AndroidUtil.isAtLeastP(),
        isAtLeastAndroidS = AndroidUtil.isAtLeastS(),
        networkConnectionStatusProvider = networkConnectionStatusProvider,
    ),
) : NetworkInfoDelegate {

    init {
        logger.d(LOG_TAG, "WisefyNetworkInfoDelegate adapter is: ${adapter::class.java.simpleName}")
    }

    override fun getCurrentNetwork(query: GetCurrentNetworkQuery): GetCurrentNetworkResult {
        return adapter.getCurrentNetwork(query)
    }

    @Suppress("TooGenericExceptionCaught")
    override fun getCurrentNetwork(
        query: GetCurrentNetworkQuery,
        callbacks: GetCurrentNetworkCallbacks?,
    ) {
        scope.launch {
            networkConnectionMutex.withLock {
                try {
                    val currentNetwork = adapter.getCurrentNetwork(query)
                    withContext(mainDispatcher) {
                        callbacks?.onCurrentNetworkRetrieved(currentNetwork.value)
                    }
                } catch (ex: CancellationException) {
                    throw ex
                } catch (ex: Exception) {
                    withContext(mainDispatcher) {
                        callbacks?.onWisefyAsyncFailure(
                            WisefyException(message = "Internal Wisefy error with getCurrentNetwork", throwable = ex),
                        )
                    }
                }
            }
        }
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getNetworkConnectionStatus(query: GetNetworkConnectionStatusQuery): GetNetworkConnectionStatusResult {
        return adapter.getNetworkConnectionStatus(query)
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    @Suppress("TooGenericExceptionCaught")
    override fun getNetworkConnectionStatus(
        query: GetNetworkConnectionStatusQuery,
        callbacks: GetNetworkConnectionStatusCallbacks?,
    ) {
        scope.launch {
            try {
                val result = adapter.getNetworkConnectionStatus(query)
                withContext(mainDispatcher) {
                    callbacks?.onDeviceNetworkConnectionStatusRetrieved(result.value)
                }
            } catch (ex: CancellationException) {
                throw ex
            } catch (ex: Exception) {
                withContext(mainDispatcher) {
                    callbacks?.onWisefyAsyncFailure(
                        WisefyException(
                            message = "Internal Wisefy error with getNetworkConnectionStatus",
                            throwable = ex,
                        ),
                    )
                }
            }
        }
    }

    companion object {
        private const val LOG_TAG = "WisefyNetworkInfoDelegate"
    }
}
