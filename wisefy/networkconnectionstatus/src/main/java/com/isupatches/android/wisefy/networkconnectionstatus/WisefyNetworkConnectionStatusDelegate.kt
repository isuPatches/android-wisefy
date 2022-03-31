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
package com.isupatches.android.wisefy.networkconnectionstatus

import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.networkconnectionstatus.entities.IsDeviceConnectedResult
import com.isupatches.android.wisefy.networkconnectionstatus.entities.IsDeviceConnectedToSSIDRequest
import com.isupatches.android.wisefy.networkconnectionstatus.entities.IsDeviceRoamingResult
import com.isupatches.android.wisefy.networkconnectionstatus.os.adapters.DefaultNetworkConnectionStatusAdapter
import com.isupatches.android.wisefy.shared.coroutines.CoroutineDispatcherProvider
import com.isupatches.android.wisefy.shared.logging.WisefyLogger
import com.isupatches.android.wisefy.shared.util.SdkUtil

/**
 * A default internal implementation for getting and searching for nearby access points through the Android OS.
 *
 * @param connectivityManager The ConnectivityManager instance to use
 * @param logger The logger instance to use
 * @param sdkUtil The SdkUtil instance to use
 * @param wifiManager The WifiManager instance to use
 *
 * @see NetworkConnectionStatusDelegate
 * @see SdkUtil
 * @see WisefyLogger
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
class WisefyNetworkConnectionStatusDelegate(
    connectivityManager: ConnectivityManager,
    logger: WisefyLogger,
    sdkUtil: SdkUtil,
    wifiManager: WifiManager
) : NetworkConnectionStatusDelegate {

    companion object {
        private const val LOG_TAG = "WisefyNetworkConnectionStatusDelegate"
    }

    private val adapter = DefaultNetworkConnectionStatusAdapter(
        connectivityManager = connectivityManager,
        wifiManager = wifiManager,
        sdkUtil = sdkUtil,
        logger = logger
    )

    init {
        logger.d(LOG_TAG, "WisefyNetworkConnectionStatusDelegate adapter is: ${adapter::class.java.simpleName}")
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun attachNetworkWatcher() {
        adapter.attachNetworkWatcher()
    }

    override fun detachNetworkWatcher() {
        adapter.detachNetworkWatcher()
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun isDeviceConnectedToMobileNetwork(): IsDeviceConnectedResult {
        return adapter.isDeviceConnectedToMobileNetwork()
    }

    override fun isDeviceConnectedToMobileOrWifiNetwork(): IsDeviceConnectedResult {
        return adapter.isDeviceConnectedToMobileOrWifiNetwork()
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun isDeviceConnectedToSSID(request: IsDeviceConnectedToSSIDRequest): IsDeviceConnectedResult {
        return adapter.isDeviceConnectedToSSID(request)
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun isDeviceConnectedToWifiNetwork(): IsDeviceConnectedResult {
        return adapter.isDeviceConnectedToWifiNetwork()
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun isDeviceRoaming(): IsDeviceRoamingResult {
        return adapter.isDeviceRoaming()
    }
}
