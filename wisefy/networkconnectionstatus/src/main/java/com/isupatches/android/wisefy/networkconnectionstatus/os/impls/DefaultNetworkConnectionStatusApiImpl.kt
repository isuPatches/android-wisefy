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
import android.net.wifi.WifiManager
import android.os.Build
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.core.entities.QUOTE
import com.isupatches.android.wisefy.core.logging.WisefyLogger
import com.isupatches.android.wisefy.core.util.SdkUtil
import com.isupatches.android.wisefy.core.util.getNetwork
import com.isupatches.android.wisefy.networkconnectionstatus.entities.NetworkConnectionStatus
import com.isupatches.android.wisefy.networkconnectionstatus.os.apis.DefaultNetworkConnectionStatusApi

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
    private val logger: WisefyLogger
) : DefaultNetworkConnectionStatusApi, ConnectivityManager.NetworkCallback() {

    companion object {
        private const val LOG_TAG = "DefaultNetworkConnectionStatusApiImpl"
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getSSIDOfTheNetworkTheDeviceIsConnectedTo(): String? {
        val connectionInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            connectivityManager.getNetwork()
        } else {
            @Suppress("Deprecation")
            wifiManager.connectionInfo
        }
        return connectionInfo?.ssid?.replace(QUOTE, "")
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getBSSIDOfTheNetworkTheDeviceIsConnectedTo(): String? {
        val connectionInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            connectivityManager.getNetwork()
        } else {
            @Suppress("Deprecation")
            wifiManager.connectionInfo
        }
        return connectionInfo?.bssid?.replace(QUOTE, "")
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
            !doesNetworkHaveCapability(capability = NetworkCapabilities.NET_CAPABILITY_NOT_ROAMING)
        } else {
            @Suppress("Deprecation")
            val networkInfo = connectivityManager.activeNetworkInfo
            @Suppress("Deprecation")
            networkInfo != null && networkInfo.isRoaming
        }
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    private fun doesNetworkHaveCapability(capability: Int): Boolean {
        return getActiveNetworkCapabilities()?.hasCapability(capability) ?: false
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

    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        logger.d(LOG_TAG, "onAvailable, $network")
        this.connectionStatus = NetworkConnectionStatus.AVAILABLE
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
        this.connectionStatus = NetworkConnectionStatus.LOSING
    }

    override fun onLost(network: Network) {
        super.onLost(network)
        logger.d(LOG_TAG, "onLost, network: $network")
        this.connectionStatus = NetworkConnectionStatus.LOST
    }

    override fun onUnavailable() {
        super.onUnavailable()
        logger.d(LOG_TAG, "onUnavailable")
        this.connectionStatus = NetworkConnectionStatus.UNAVAILABLE
    }

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
        connectivityManager.registerNetworkCallback(request, this)
    }

    private fun stopListeningForNetworkChanges(connectivityManager: ConnectivityManager) {
        connectivityManager.unregisterNetworkCallback(this)
        connectionStatus = null
    }
}
