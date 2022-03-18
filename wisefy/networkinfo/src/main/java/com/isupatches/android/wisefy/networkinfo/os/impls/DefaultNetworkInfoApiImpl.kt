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
package com.isupatches.android.wisefy.networkinfo.os.impls

import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.os.Build
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.networkinfo.os.apis.DefaultNetworkInfoApi
import com.isupatches.android.wisefy.shared.logging.WisefyLogger
import com.isupatches.android.wisefy.shared.util.getNetwork
import java.math.BigInteger
import java.net.InetAddress
import java.net.UnknownHostException

internal class DefaultNetworkInfoApiImpl(
    private val wifiManager: WifiManager,
    private val connectivityManager: ConnectivityManager,
    private val logger: WisefyLogger
) : DefaultNetworkInfoApi {

    companion object {
        private const val LOG_TAG = "LegacyNetworkInfoApiImpl"
    }

    override fun getCurrentNetwork(): WifiInfo? {
        val currentNetwork = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            connectivityManager.getNetwork()
        } else {
            @Suppress("Deprecation")
            wifiManager.connectionInfo
        }
        return currentNetwork
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getNetworkCapabilities(network: Network): NetworkCapabilities? {
        return connectivityManager.getNetworkCapabilities(network)
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getLinkProperties(network: Network): LinkProperties? {
        return connectivityManager.getLinkProperties(network)
    }

    override fun getIP(): String? {
        val inetAddress = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            connectivityManager.getLinkProperties(connectivityManager.activeNetwork)?.dhcpServerAddress
        } else {
            @Suppress("Deprecation")
            InetAddress.getByAddress(
                BigInteger.valueOf(wifiManager.connectionInfo.ipAddress.toLong()).toByteArray()
            )
        }
        return try {
            inetAddress?.hostAddress
        } catch (uhe: UnknownHostException) {
            logger.e(LOG_TAG, uhe, "UnknownHostException while gathering IP (sync)")
            null
        }
    }
}
