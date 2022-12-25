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
package com.isupatches.android.wisefy.networkinfo.os.adapters

import android.Manifest
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.core.entities.NetworkConnectionStatus
import com.isupatches.android.wisefy.core.logging.WisefyLogger
import com.isupatches.android.wisefy.core.util.SdkUtil
import com.isupatches.android.wisefy.networkinfo.NetworkInfoApi
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkQuery
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkResult
import com.isupatches.android.wisefy.networkinfo.entities.GetNetworkConnectionStatusQuery
import com.isupatches.android.wisefy.networkinfo.entities.GetNetworkConnectionStatusResult
import com.isupatches.android.wisefy.networkinfo.entities.NetworkConnectionStatusData
import com.isupatches.android.wisefy.networkinfo.entities.NetworkData
import com.isupatches.android.wisefy.networkinfo.os.apis.DefaultNetworkInfoApi
import com.isupatches.android.wisefy.networkinfo.os.impls.DefaultNetworkInfoApiImpl
import kotlinx.coroutines.runBlocking

/**
 * A default adapter for getting information about a network, the device's current network, and the device's IP.
 *
 * @param connectivityManager The ConnectivityManager instance to use
 * @property wifiManager The WifiManager instance to use
 * @param sdkUtil The [SdkUtil] instance to use
 * @param logger The [WisefyLogger] instance to use
 * @param networkConnectionStatusProvider The on-demand way to retrieve the current network connection status
 * @param api The OS level API instance to use
 *
 * @see DefaultNetworkInfoApi
 * @see DefaultNetworkInfoApiImpl
 * @see NetworkConnectionStatus
 * @see NetworkInfoApi
 * @see SdkUtil
 * @see WisefyLogger
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
internal class DefaultNetworkInfoAdapter(
    connectivityManager: ConnectivityManager,
    private val wifiManager: WifiManager,
    sdkUtil: SdkUtil,
    logger: WisefyLogger,
    networkConnectionStatusProvider: suspend () -> NetworkConnectionStatus?,
    private val api: DefaultNetworkInfoApi = DefaultNetworkInfoApiImpl(
        wifiManager = wifiManager,
        connectivityManager = connectivityManager,
        sdkUtil = sdkUtil,
        logger = logger,
        networkConnectionStatusProvider = networkConnectionStatusProvider
    )
) : NetworkInfoApi {

    override fun getCurrentNetwork(query: GetCurrentNetworkQuery): GetCurrentNetworkResult {
        val currentNetwork = api.getCurrentNetwork()
        return GetCurrentNetworkResult(
            value = NetworkData(
                network = currentNetwork,
                connectionInfo = wifiManager.connectionInfo,
                capabilities = currentNetwork?.let { api.getNetworkCapabilities(it) },
                linkProperties = currentNetwork?.let { api.getLinkProperties(it) }
            )
        )
    }

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    override fun getNetworkConnectionStatus(query: GetNetworkConnectionStatusQuery): GetNetworkConnectionStatusResult {
        val isDeviceConnected = runBlocking { api.isDeviceConnected() }
        return GetNetworkConnectionStatusResult(
            value = NetworkConnectionStatusData(
                isConnected = isDeviceConnected,
                isConnectedToMobileNetwork = isDeviceConnected && api.isTransportTypeMobile(),
                isConnectedToWifiNetwork = isDeviceConnected && api.isTransportTypeWifi(),
                isRoaming = api.isDeviceRoaming(),
                ssidOfNetworkConnectedTo = api.getSSIDOfTheNetworkTheDeviceIsConnectedTo(),
                bssidOfNetworkConnectedTo = api.getBSSIDOfTheNetworkTheDeviceIsConnectedTo(),
                ip = api.getIP()
            )
        )
    }
}
