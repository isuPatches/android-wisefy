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
package com.isupatches.android.wisefy.networkconnectionstatus.os.adapters

import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.core.logging.WisefyLogger
import com.isupatches.android.wisefy.core.util.SdkUtil
import com.isupatches.android.wisefy.networkconnectionstatus.NetworkConnectionStatusApiInternal
import com.isupatches.android.wisefy.networkconnectionstatus.NetworkConnectionStatusDelegate
import com.isupatches.android.wisefy.networkconnectionstatus.entities.IsDeviceConnectedResult
import com.isupatches.android.wisefy.networkconnectionstatus.entities.IsDeviceConnectedToSSIDRequest
import com.isupatches.android.wisefy.networkconnectionstatus.entities.IsDeviceRoamingResult
import com.isupatches.android.wisefy.networkconnectionstatus.os.apis.DefaultNetworkConnectionStatusApi
import com.isupatches.android.wisefy.networkconnectionstatus.os.impls.DefaultNetworkConnectionStatusApiImpl

/**
 * A default adapter for checking the device's connection status and if it meets certain criteria.
 *
 * @param connectivityManager The ConnectivityManager instance to use
 * @param wifiManager The WifiManager instance to use
 * @param sdkUtil The SdkUtil instance to use
 * @param logger The logger instance to use
 * @param api The OS level API instance to use
 *
 * @see DefaultNetworkConnectionStatusApi
 * @see DefaultNetworkConnectionStatusApiImpl
 * @see NetworkConnectionStatusApiInternal
 * @see NetworkConnectionStatusDelegate
 * @see SdkUtil
 * @see WisefyLogger
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
internal class DefaultNetworkConnectionStatusAdapter(
    connectivityManager: ConnectivityManager,
    wifiManager: WifiManager,
    sdkUtil: SdkUtil,
    logger: WisefyLogger,
    private val api: DefaultNetworkConnectionStatusApi = DefaultNetworkConnectionStatusApiImpl(
        wifiManager = wifiManager,
        connectivityManager = connectivityManager,
        sdkUtil = sdkUtil,
        logger = logger
    )
) : NetworkConnectionStatusApiInternal {

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun attachNetworkWatcher() {
        api.attachNetworkWatcher()
    }

    override fun detachNetworkWatcher() {
        api.detachNetworkWatcher()
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun isDeviceConnectedToMobileNetwork(): IsDeviceConnectedResult {
        val isConnected = api.isDeviceConnectedToMobileNetwork()
        return if (isConnected) {
            IsDeviceConnectedResult.True
        } else {
            IsDeviceConnectedResult.False
        }
    }

    override fun isDeviceConnectedToMobileOrWifiNetwork(): IsDeviceConnectedResult {
        val isConnected = api.isDeviceConnectedToMobileOrWifiNetwork()
        return if (isConnected) {
            IsDeviceConnectedResult.True
        } else {
            IsDeviceConnectedResult.False
        }
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun isDeviceConnectedToSSID(request: IsDeviceConnectedToSSIDRequest): IsDeviceConnectedResult {
        val isConnected = when (request) {
            is IsDeviceConnectedToSSIDRequest.SSID -> api.isDeviceConnectedToSSID(request.regex)
            is IsDeviceConnectedToSSIDRequest.BSSID -> api.isDeviceConnectedToBSSID(request.regex)
        }
        return if (isConnected) {
            IsDeviceConnectedResult.True
        } else {
            IsDeviceConnectedResult.False
        }
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun isDeviceConnectedToWifiNetwork(): IsDeviceConnectedResult {
        val isConnected = api.isDeviceConnectedToWifiNetwork()
        return if (isConnected) {
            IsDeviceConnectedResult.True
        } else {
            IsDeviceConnectedResult.False
        }
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun isDeviceRoaming(): IsDeviceRoamingResult {
        val isRoaming = api.isDeviceRoaming()
        return if (isRoaming) {
            IsDeviceRoamingResult.True
        } else {
            IsDeviceRoamingResult.False
        }
    }
}
