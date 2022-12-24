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
package com.isupatches.android.wisefy.networkinfo.os.impls

import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.core.bssidWithoutQuotes
import com.isupatches.android.wisefy.core.entities.NetworkConnectionStatus
import com.isupatches.android.wisefy.core.logging.WisefyLogger
import com.isupatches.android.wisefy.core.ssidWithoutQuotes
import com.isupatches.android.wisefy.core.util.SdkUtil
import com.isupatches.android.wisefy.networkinfo.os.apis.DefaultNetworkInfoApi
import java.math.BigInteger
import java.net.InetAddress
import java.net.UnknownHostException

/**
 * A default implementation for getting information about a network, the device's current network, and the device's IP.
 *
 * @param connectivityManager The ConnectivityManager instance to use
 *
 * @see DefaultNetworkInfoApi
 * @see WisefyLogger
 *
 * @author Patches Barrett
 * @since 03/2022
 */
internal class DefaultNetworkInfoApiImpl(
    private val wifiManager: WifiManager,
    private val connectivityManager: ConnectivityManager,
    private val sdkUtil: SdkUtil,
    private val logger: WisefyLogger,
    private val networkConnectionStatusProvider: suspend () -> NetworkConnectionStatus?
) : DefaultNetworkInfoApi {

    companion object {
        private const val LOG_TAG = "DefaultNetworkInfoApiImpl"
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getCurrentNetwork(): Network? {
        return connectivityManager.activeNetwork
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getNetworkCapabilities(network: Network): NetworkCapabilities? {
        return connectivityManager.getNetworkCapabilities(network)
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getLinkProperties(network: Network): LinkProperties? {
        return connectivityManager.getLinkProperties(network)
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getSSIDOfTheNetworkTheDeviceIsConnectedTo(): String? {
        return getNetworkTransportInfo()?.ssidWithoutQuotes
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getBSSIDOfTheNetworkTheDeviceIsConnectedTo(): String? {
        return getNetworkTransportInfo()?.bssidWithoutQuotes
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getIP(): String? {
        val inetAddress = if (sdkUtil.isAtLeastS()) {
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

    override suspend fun isDeviceConnected(): Boolean {
        return networkConnectionStatusProvider() == NetworkConnectionStatus.AVAILABLE
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun isDeviceRoaming(): Boolean {
        return if (sdkUtil.isAtLeastP()) {
            // NET_CAPABILITY_NOT_ROAMING only available for P and above devices :'(
            !(getActiveNetworkCapabilities()?.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_ROAMING) ?: false)
        } else {
            @Suppress("Deprecation")
            val networkInfo = connectivityManager.activeNetworkInfo
            @Suppress("Deprecation")
            networkInfo != null && networkInfo.isRoaming
        }
    }

    override fun isTransportTypeMobile(): Boolean {
        return doesNetworkHaveTransportTypeAndInternetCapability(
            transportType = NetworkCapabilities.TRANSPORT_CELLULAR
        )
    }

    override fun isTransportTypeWifi(): Boolean {
        return doesNetworkHaveTransportTypeAndInternetCapability(
            transportType = NetworkCapabilities.TRANSPORT_WIFI
        )
    }

    /**
     * A function that will retrieve the network transport info for the device's current network .
     *
     * @return WifiInfo or null - The network from ConnectivityManager or null if cannot be retrieved
     *
     * @author Patches Barrett
     * @since 03/2022
     */
    @RequiresPermission(ACCESS_NETWORK_STATE)
    private fun getNetworkTransportInfo(): WifiInfo? {
        return if (sdkUtil.isAtLeastS()) {
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.transportInfo as? WifiInfo
        } else {
            @Suppress("Deprecation")
            wifiManager.connectionInfo
        }
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    private fun doesNetworkHaveTransportTypeAndInternetCapability(transportType: Int): Boolean {
        return getActiveNetworkCapabilities()?.let {
            it.hasTransport(transportType) && it.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        } ?: false
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    private fun getActiveNetworkCapabilities(): NetworkCapabilities? {
        return connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
    }
}
