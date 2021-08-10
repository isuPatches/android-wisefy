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
package com.isupatches.android.wisefy.networkconnectionstatus

import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.logging.WisefyLogger
import com.isupatches.android.wisefy.networkconnectionstatus.delegates.LegacyNetworkConnectionStatusDelegate
import com.isupatches.android.wisefy.util.SdkUtil

internal interface NetworkConnectionStatusUtil : NetworkConnectionStatusApi

private const val LOG_TAG = "WisefyNetworkConnectionStatusUtil"

internal class WisefyNetworkConnectionStatusUtil(
    connectivityManager: ConnectivityManager,
    logger: WisefyLogger?,
    sdkUtil: SdkUtil,
    wifiManager: WifiManager
) : NetworkConnectionStatusUtil {

    private val delegate = LegacyNetworkConnectionStatusDelegate(
        connectivityManager,
        wifiManager,
        sdkUtil,
        logger
    )

    init {
        logger?.d(LOG_TAG, "WisefyNetworkConnectionStatusUtil delegate is: ${delegate::class.java.simpleName}")
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun attachNetworkWatcher() {
        delegate.attachNetworkWatcher()
    }

    override fun detachNetworkWatcher() {
        delegate.detachNetworkWatcher()
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun isDeviceConnectedToMobileNetwork(): Boolean {
        return delegate.isDeviceConnectedToMobileNetwork()
    }

    override fun isDeviceConnectedToMobileOrWifiNetwork(): Boolean {
        return delegate.isDeviceConnectedToMobileOrWifiNetwork()
    }

    override fun isDeviceConnectedToSSID(ssid: String): Boolean {
        return delegate.isDeviceConnectedToSSID(ssid)
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun isDeviceConnectedToWifiNetwork(): Boolean {
        return delegate.isDeviceConnectedToWifiNetwork()
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun isDeviceRoaming(): Boolean {
        return delegate.isDeviceRoaming()
    }
}