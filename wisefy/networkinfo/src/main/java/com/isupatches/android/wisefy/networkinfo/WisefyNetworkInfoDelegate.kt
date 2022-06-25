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
package com.isupatches.android.wisefy.networkinfo

import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.core.coroutines.CoroutineDispatcherProvider
import com.isupatches.android.wisefy.core.coroutines.createBaseCoroutineExceptionHandler
import com.isupatches.android.wisefy.core.logging.WisefyLogger
import com.isupatches.android.wisefy.networkinfo.callbacks.GetCurrentNetworkCallbacks
import com.isupatches.android.wisefy.networkinfo.callbacks.GetCurrentNetworkInfoCallbacks
import com.isupatches.android.wisefy.networkinfo.callbacks.GetIPCallbacks
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkInfoRequest
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkInfoResult
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkRequest
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkResult
import com.isupatches.android.wisefy.networkinfo.entities.GetIPRequest
import com.isupatches.android.wisefy.networkinfo.entities.GetIPResult
import com.isupatches.android.wisefy.networkinfo.os.adapters.DefaultNetworkInfoAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * An internal Wisefy delegate for getting information about a network, the device's current network,
 * and the device's IP through the Android OS.
 *
 * @param coroutineDispatcherProvider The instance of the coroutine dispatcher provider to use
 * @param scope The coroutine scope to use
 * @param connectivityManager The ConnectivityManager instance to use
 * @param logger The logger instance to use
 * @param wifiManager The WifiManager instance to use
 *
 * @see CoroutineDispatcherProvider
 * @see NetworkInfoDelegate
 * @see WisefyLogger
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
class WisefyNetworkInfoDelegate(
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider,
    private val scope: CoroutineScope,
    connectivityManager: ConnectivityManager,
    logger: WisefyLogger,
    wifiManager: WifiManager
) : NetworkInfoDelegate {

    companion object {
        private const val LOG_TAG = "WisefyNetworkInfoDelegate"
    }

    private val adapter = DefaultNetworkInfoAdapter(wifiManager, connectivityManager, logger)

    init {
        logger.d(LOG_TAG, "WisefyNetworkInfoDelegate adapter is: ${adapter::class.java.simpleName}")
    }

    override fun getCurrentNetwork(request: GetCurrentNetworkRequest): GetCurrentNetworkResult {
        return adapter.getCurrentNetwork()
    }

    override fun getCurrentNetwork(request: GetCurrentNetworkRequest, callbacks: GetCurrentNetworkCallbacks?) {
        scope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val currentNetwork = adapter.getCurrentNetwork()
            withContext(coroutineDispatcherProvider.main) {
                when (currentNetwork) {
                    is GetCurrentNetworkResult.Empty -> callbacks?.onNoCurrentNetwork()
                    is GetCurrentNetworkResult.Network -> callbacks?.onCurrentNetworkRetrieved(currentNetwork.data)
                }
            }
        }
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getCurrentNetworkInfo(request: GetCurrentNetworkInfoRequest): GetCurrentNetworkInfoResult {
        return adapter.getCurrentNetworkInfo(request)
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getNetworkInfo(
        request: GetCurrentNetworkInfoRequest,
        callbacks: GetCurrentNetworkInfoCallbacks?
    ) {
        scope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val currentNetworkInfo = adapter.getCurrentNetworkInfo(request)
            withContext(coroutineDispatcherProvider.main) {
                when (currentNetworkInfo) {
                    is GetCurrentNetworkInfoResult.Empty -> callbacks?.onNoCurrentNetworkToRetrieveInfo()
                    is GetCurrentNetworkInfoResult.NetworkInfo -> callbacks?.onCurrentNetworkInfoRetrieved(
                        currentNetworkInfo.data
                    )
                }
            }
        }
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getIP(request: GetIPRequest): GetIPResult {
        return adapter.getIP()
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getIP(request: GetIPRequest, callbacks: GetIPCallbacks?) {
        scope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val ip = adapter.getIP()
            withContext(coroutineDispatcherProvider.main) {
                when (ip) {
                    is GetIPResult.Empty -> callbacks?.onFailureRetrievingIP()
                    is GetIPResult.IPAddress -> callbacks?.onIPRetrieved(ip.data)
                }
            }
        }
    }
}
