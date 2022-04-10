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
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkRequest
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkResult
import com.isupatches.android.wisefy.networkinfo.entities.GetIPRequest
import com.isupatches.android.wisefy.networkinfo.entities.GetIPResult
import com.isupatches.android.wisefy.networkinfo.entities.GetNetworkInfoRequest
import com.isupatches.android.wisefy.networkinfo.entities.GetNetworkInfoResult
import com.isupatches.android.wisefy.networkinfo.os.adapters.DefaultNetworkInfoAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
    override fun getNetworkInfo(request: GetNetworkInfoRequest): GetNetworkInfoResult {
        return adapter.getNetworkInfo(request)
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getNetworkInfo(
        request: GetNetworkInfoRequest,
        callbacks: GetCurrentNetworkInfoCallbacks?
    ) {
        scope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val currentNetworkInfo = adapter.getNetworkInfo(request)
            withContext(coroutineDispatcherProvider.main) {
                when (currentNetworkInfo) {
                    is GetNetworkInfoResult.Empty -> callbacks?.onNoCurrentNetworkInfo()
                    is GetNetworkInfoResult.NetworkInfo -> callbacks?.onCurrentNetworkInfoRetrieved(
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
