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
 * A default implementation for getting information about the device's current network and connection status.
 *
 * @property wifiManager The WifiManager instance to use
 * @property connectivityManager The ConnectivityManager instance to use
 * @property sdkUtil The [SdkUtil] instance to use
 * @property logger The [WisefyLogger] instance to use
 * @property networkConnectionStatusProvider The on-demand way to retrieve the current network connection status
 *
 * @see DefaultNetworkInfoApi
 * @see NetworkConnectionStatus
 * @see SdkUtil
 * @see WisefyLogger
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
internal class DefaultNetworkInfoApiImpl(
    private val wifiManager: WifiManager,
    private val connectivityManager: ConnectivityManager,
    private val sdkUtil: SdkUtil,
    private val logger: WisefyLogger,
    private val networkConnectionStatusProvider: suspend () -> NetworkConnectionStatus?
) : DefaultNetworkInfoApi {

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

    companion object {
        private const val LOG_TAG = "DefaultNetworkInfoApiImpl"
    }
}
