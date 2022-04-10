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
import com.isupatches.android.wisefy.core.logging.WisefyLogger
import com.isupatches.android.wisefy.networkinfo.NetworkInfoApi
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkRequest
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkResult
import com.isupatches.android.wisefy.networkinfo.entities.GetIPRequest
import com.isupatches.android.wisefy.networkinfo.entities.GetIPResult
import com.isupatches.android.wisefy.networkinfo.entities.GetNetworkInfoRequest
import com.isupatches.android.wisefy.networkinfo.entities.GetNetworkInfoResult
import com.isupatches.android.wisefy.networkinfo.entities.IPData
import com.isupatches.android.wisefy.networkinfo.entities.NetworkData
import com.isupatches.android.wisefy.networkinfo.entities.NetworkInfoData
import com.isupatches.android.wisefy.networkinfo.os.apis.DefaultNetworkInfoApi
import com.isupatches.android.wisefy.networkinfo.os.impls.DefaultNetworkInfoApiImpl

/**
 * A default adapter for getting details about the device's network.
 *
 * @param wifiManager The WifiManager instance to use
 * @param connectivityManager The ConnectivityManager instance to use
 * @param logger The logger instance to use
 * @param api The OS level API instance to use
 *
 * @see DefaultNetworkInfoApi
 * @see DefaultNetworkInfoApiImpl
 * @see NetworkInfoApi
 * @see WisefyLogger
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
internal class DefaultNetworkInfoAdapter(
    wifiManager: WifiManager,
    private val connectivityManager: ConnectivityManager,
    logger: WisefyLogger,
    private val api: DefaultNetworkInfoApi = DefaultNetworkInfoApiImpl(
        wifiManager = wifiManager,
        connectivityManager = connectivityManager,
        logger = logger
    )
) : NetworkInfoApi {

    override fun getCurrentNetwork(request: GetCurrentNetworkRequest): GetCurrentNetworkResult {
        val result = api.getCurrentNetwork()
        return result?.let {
            GetCurrentNetworkResult.Network(data = NetworkData(it))
        } ?: GetCurrentNetworkResult.Empty
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getNetworkInfo(request: GetNetworkInfoRequest): GetNetworkInfoResult {
        val network = request.network ?: connectivityManager.activeNetwork
        return network?.let {
            GetNetworkInfoResult.NetworkInfo(
                data = NetworkInfoData(
                    capabilities = api.getNetworkCapabilities(network),
                    linkProperties = api.getLinkProperties(network)
                )
            )
        } ?: GetNetworkInfoResult.Empty
    }

    override fun getIP(request: GetIPRequest): GetIPResult {
        val ip = api.getIP()
        return ip?.let {
            GetIPResult.IPAddress(data = IPData(it))
        } ?: GetIPResult.Empty
    }
}
