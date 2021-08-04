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
package com.isupatches.android.wisefy.networkinfo

import android.Manifest
import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.logging.WisefyLogger
import com.isupatches.android.wisefy.networkinfo.delegates.LegacyNetworkInfoDelegate

internal interface NetworkInfoUtil : NetworkInfoApi

private const val LOG_TAG = "WisefyNetworkInfoUtil"

internal class WisefyNetworkInfoUtil(
    connectivityManager: ConnectivityManager,
    wifiManager: WifiManager,
    logger: WisefyLogger?
) : NetworkInfoUtil {

    private val delegate = LegacyNetworkInfoDelegate(connectivityManager, wifiManager, logger)

    init {
        logger?.d(LOG_TAG, "WisefyNetworkInfoUtil delegate is: ${delegate::class.java.simpleName}")
    }

    override fun getCurrentNetwork(): WifiInfo? {
        return delegate.getCurrentNetwork()
    }

    @Deprecated("")
    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getCurrentNetworkInfo(): NetworkInfo? {
        return delegate.getCurrentNetworkInfo()
    }

    override fun getIP(): String? {
        return delegate.getIP()
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getNetworkCapabilities(network: Network?): NetworkCapabilities? {
        return delegate.getNetworkCapabilities(network)
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getNetworkLinkProperties(network: Network?): LinkProperties? {
        return delegate.getNetworkLinkProperties(network)
    }
}
