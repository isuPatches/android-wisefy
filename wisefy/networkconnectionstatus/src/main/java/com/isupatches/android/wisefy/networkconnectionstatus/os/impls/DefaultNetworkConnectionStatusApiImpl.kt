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
package com.isupatches.android.wisefy.networkconnectionstatus.os.impls

import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.core.constants.QUOTE
import com.isupatches.android.wisefy.core.logging.WisefyLogger
import com.isupatches.android.wisefy.core.util.SdkUtil
import com.isupatches.android.wisefy.networkconnectionstatus.entities.NetworkConnectionStatus
import com.isupatches.android.wisefy.networkconnectionstatus.os.apis.DefaultNetworkConnectionStatusApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.math.BigInteger
import java.net.InetAddress
import java.net.UnknownHostException

/**
 * A default implementation for checking the device's connection status and if it meets certain criteria.
 *
 * @param wifiManager The WifiManager instance to use
 * @param connectivityManager The ConnectivityManager instance to use
 * @param sdkUtil The SdkUtil instance to use
 * @param logger The WisefyLogger instance to use
 *
 * @see DefaultNetworkConnectionStatusApi
 * @see SdkUtil
 * @see WisefyLogger
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
internal class DefaultNetworkConnectionStatusApiImpl(
    private val wifiManager: WifiManager,
    private val connectivityManager: ConnectivityManager,
    private val sdkUtil: SdkUtil,
    private val logger: WisefyLogger,
    private val scope: CoroutineScope,
    private val networkConnectionMutex: Mutex
) : DefaultNetworkConnectionStatusApi {

    companion object {
        private const val LOG_TAG = "DefaultNetworkConnectionStatusApiImpl"
    }

    private val wisefyNetworkCallback = WisefyNetworkCallback()

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getSSIDOfTheNetworkTheDeviceIsConnectedTo(): String? {
        val connectionInfo = getNetworkTransportInfo()
        return connectionInfo?.ssid?.replace(QUOTE, "")
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getBSSIDOfTheNetworkTheDeviceIsConnectedTo(): String? {
        val connectionInfo = getNetworkTransportInfo()
        return connectionInfo?.bssid?.replace(QUOTE, "")
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

    override fun isDeviceConnected(): Boolean {
        return isNetworkConnected()
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun isDeviceConnectedToMobileNetwork(): Boolean {
        return doesNetworkHaveTransportTypeAndInternetCapability(
            transportType = NetworkCapabilities.TRANSPORT_CELLULAR
        ) && isNetworkConnected()
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun isDeviceConnectedToWifiNetwork(): Boolean {
        return doesNetworkHaveTransportTypeAndInternetCapability(
            transportType = NetworkCapabilities.TRANSPORT_WIFI
        ) && isNetworkConnected()
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

    /**
     * A function that will retrieve the network transport info for the device's current network .
     *
     * @return WifiInfo or null - The network from ConnectivityManager or null if cannot be retrieved
     *
     * @author Patches Klinefelter
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

    private fun isNetworkConnected(): Boolean {
        return connectionStatus == NetworkConnectionStatus.AVAILABLE
    }

    private var connectionStatus: NetworkConnectionStatus? = null

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun attachNetworkWatcher() {
        startListeningForNetworkChanges(connectivityManager)
    }

    override fun detachNetworkWatcher() {
        stopListeningForNetworkChanges(connectivityManager)
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    private fun startListeningForNetworkChanges(connectivityManager: ConnectivityManager) {
        val request = NetworkRequest.Builder().build()
        connectivityManager.registerNetworkCallback(request, wisefyNetworkCallback)
    }

    private fun stopListeningForNetworkChanges(connectivityManager: ConnectivityManager) {
        connectivityManager.unregisterNetworkCallback(wisefyNetworkCallback)
        connectionStatus = null
    }

    private inner class WisefyNetworkCallback : ConnectivityManager.NetworkCallback() {

        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            logger.d(LOG_TAG, "onAvailable, $network")
            updateConnectionStatus(NetworkConnectionStatus.AVAILABLE)
        }

        override fun onCapabilitiesChanged(network: Network, networkCapabilities: NetworkCapabilities) {
            super.onCapabilitiesChanged(network, networkCapabilities)
            logger.d(
                LOG_TAG,
                "onCapabilitiesChanged, network: $network, networkCapabilities: $networkCapabilities"
            )
        }

        override fun onLinkPropertiesChanged(network: Network, linkProperties: LinkProperties) {
            super.onLinkPropertiesChanged(network, linkProperties)
            logger.d(LOG_TAG, "onLinkPropertiesChanged, network: $network, linkProperties: $linkProperties")
        }

        override fun onLosing(network: Network, maxMsToLive: Int) {
            super.onLosing(network, maxMsToLive)
            logger.d(LOG_TAG, "onLosing, network: $network, maxMsToLive: $maxMsToLive")
            updateConnectionStatus(NetworkConnectionStatus.LOSING)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            logger.d(LOG_TAG, "onLost, network: $network")
            updateConnectionStatus(NetworkConnectionStatus.LOST)
        }

        override fun onUnavailable() {
            super.onUnavailable()
            logger.d(LOG_TAG, "onUnavailable")
            updateConnectionStatus(NetworkConnectionStatus.UNAVAILABLE)
        }

        private fun updateConnectionStatus(status: NetworkConnectionStatus) {
            scope.launch {
                networkConnectionMutex.withLock {
                    connectionStatus = status
                }
            }
        }
    }
}
