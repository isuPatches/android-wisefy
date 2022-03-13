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
package com.isupatches.android.wisefy.networkinfo.os.adapters

import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.shared.logging.WisefyLogger
import com.isupatches.android.wisefy.networkinfo.NetworkInfoApi
import com.isupatches.android.wisefy.networkinfo.entities.CurrentNetworkData
import com.isupatches.android.wisefy.networkinfo.entities.CurrentNetworkInfoData
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkInfoRequest
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkInfoResult
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkRequest
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkResult
import com.isupatches.android.wisefy.networkinfo.entities.GetIPRequest
import com.isupatches.android.wisefy.networkinfo.entities.IPData
import com.isupatches.android.wisefy.networkinfo.entities.GetIPResult
import com.isupatches.android.wisefy.networkinfo.os.apis.DefaultNetworkInfoApi
import com.isupatches.android.wisefy.networkinfo.os.impls.DefaultNetworkInfoApiImpl

internal class DefaultNetworkInfoAdapter(
    wifiManager: WifiManager,
    private val connectivityManager: ConnectivityManager,
    logger: WisefyLogger?,
    private val api: DefaultNetworkInfoApi = DefaultNetworkInfoApiImpl(
        wifiManager = wifiManager,
        connectivityManager = connectivityManager,
        logger = logger
    )
) : NetworkInfoApi {

    override fun getCurrentNetwork(request: GetCurrentNetworkRequest): GetCurrentNetworkResult {
        val result = api.getCurrentNetwork()
        return result?.let {
            GetCurrentNetworkResult.Network(data = CurrentNetworkData(it))
        } ?: GetCurrentNetworkResult.Empty
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getCurrentNetworkInfo(request: GetCurrentNetworkInfoRequest): GetCurrentNetworkInfoResult {
        val network = request.network ?: connectivityManager.activeNetwork
        return network?.let {
            GetCurrentNetworkInfoResult.NetworkInfo(
                data = CurrentNetworkInfoData(
                    capabilities = api.getNetworkCapabilities(network),
                    linkProperties = api.getLinkProperties(network)
                )
            )
        } ?: GetCurrentNetworkInfoResult.Empty
    }

    override fun getIP(request: GetIPRequest): GetIPResult {
        val ip = api.getIP()
        return ip?.let {
            GetIPResult.IPAddress(data = IPData(it))
        } ?: GetIPResult.Empty
    }
}
