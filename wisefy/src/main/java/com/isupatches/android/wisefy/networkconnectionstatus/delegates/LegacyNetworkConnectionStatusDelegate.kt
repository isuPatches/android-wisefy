/*
 * Copyright 2021 Patches Klinefelter
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
package com.isupatches.android.wisefy.networkconnectionstatus.delegates

import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.networkconnectionstatus.NetworkConnectionStatusApi
import com.isupatches.android.wisefy.logging.WisefyLogger
import com.isupatches.android.wisefy.util.SdkUtil

internal class LegacyNetworkConnectionStatusDelegate(
    connectivityManager: ConnectivityManager,
    wifiManager: WifiManager,
    sdkUtil: SdkUtil,
    logger: WisefyLogger?,
    private val impl: LegacyNetworkConnectionStatusApi = LegacyNetworkConnectionStatusApiImpl(
        wifiManager,
        connectivityManager,
        sdkUtil,
        logger
    )
) : NetworkConnectionStatusApi {

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun attachNetworkWatcher() {
        impl.attachNetworkWatcher()
    }

    override fun detachNetworkWatcher() {
        impl.detachNetworkWatcher()
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun isDeviceConnectedToMobileNetwork(): Boolean {
        return impl.isDeviceConnectedToMobileNetwork()
    }

    override fun isDeviceConnectedToMobileOrWifiNetwork(): Boolean {
        return impl.isDeviceConnectedToMobileOrWifiNetwork()
    }

    override fun isDeviceConnectedToSSID(ssid: String): Boolean {
        return impl.isDeviceConnectedToSSID(ssid)
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun isDeviceConnectedToWifiNetwork(): Boolean {
        return impl.isDeviceConnectedToWifiNetwork()
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun isDeviceRoaming(): Boolean {
        return impl.isDeviceRoaming()
    }
}
