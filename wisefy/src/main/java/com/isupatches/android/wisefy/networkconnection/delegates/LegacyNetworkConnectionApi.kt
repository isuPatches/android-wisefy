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
package com.isupatches.android.wisefy.networkconnection.delegates

import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.net.wifi.WifiManager
import android.os.Build
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.constants.QUOTE
import com.isupatches.android.wisefy.logging.WisefyLogger
import com.isupatches.android.wisefy.networkconnection.entities.NetworkConnectionStatus

internal interface LegacyNetworkConnectionApi {
    @RequiresPermission(ACCESS_NETWORK_STATE)
    fun attachNetworkWatcher()
    fun detachNetworkWatcher()

    @RequiresPermission(ACCESS_NETWORK_STATE)
    fun isDeviceConnectedToMobileNetwork(): Boolean

    fun isDeviceConnectedToMobileOrWifiNetwork(): Boolean

    fun isDeviceConnectedToSSID(ssid: String): Boolean

    @RequiresPermission(ACCESS_NETWORK_STATE)
    fun isDeviceConnectedToWifiNetwork(): Boolean

    @RequiresPermission(ACCESS_NETWORK_STATE)
    fun isDeviceRoaming(): Boolean
}

private const val LOG_TAG = "LegacyNetworkConnectionApiImpl"

internal class LegacyNetworkConnectionApiImpl(
    private val wifiManager: WifiManager,
    private val connectivityManager: ConnectivityManager,
    private val logger: WisefyLogger?
) : LegacyNetworkConnectionApi, ConnectivityManager.NetworkCallback() {

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun isDeviceConnectedToMobileNetwork(): Boolean {
        return doesNetworkHaveTransportTypeAndInternetCapability(
            transportType = NetworkCapabilities.TRANSPORT_CELLULAR
        ) && isNetworkConnected()
    }

    override fun isDeviceConnectedToMobileOrWifiNetwork(): Boolean {
        return isNetworkConnected()
    }

    override fun isDeviceConnectedToSSID(ssid: String): Boolean {
        val connectionInfo = wifiManager.connectionInfo
        connectionInfo?.let {
            if (!it.ssid.isNullOrEmpty()) {
                val currentSSID = it.ssid.replace(QUOTE, "")
                logger?.d(LOG_TAG, "Current SSID: %s, Desired SSID: %s", currentSSID, ssid)
                if (currentSSID.equals(ssid, ignoreCase = true) && isNetworkConnected()) {
                    logger?.d(LOG_TAG, "Network is connected")
                    return true
                }
            }
        }
        return false
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun isDeviceConnectedToWifiNetwork(): Boolean {
        return doesNetworkHaveTransportTypeAndInternetCapability(
            transportType = NetworkCapabilities.TRANSPORT_WIFI
        ) && isNetworkConnected()
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun isDeviceRoaming(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            // NET_CAPABILITY_NOT_ROAMING only available for P and above devices :'(
            !doesNetworkHaveCapability(capability = NetworkCapabilities.NET_CAPABILITY_NOT_ROAMING)
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo
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
        logger?.d(LOG_TAG, "onAvailable, $network")
        this.connectionStatus = NetworkConnectionStatus.AVAILABLE
    }

    override fun onCapabilitiesChanged(network: Network, networkCapabilities: NetworkCapabilities) {
        super.onCapabilitiesChanged(network, networkCapabilities)
        logger?.d(
            LOG_TAG,
            "onCapabilitiesChanged, network: $network, networkCapabilities: $networkCapabilities"
        )
    }

    override fun onLinkPropertiesChanged(network: Network, linkProperties: LinkProperties) {
        super.onLinkPropertiesChanged(network, linkProperties)
        logger?.d(LOG_TAG, "onLinkPropertiesChanged, network: $network, linkProperties: $linkProperties")
    }

    override fun onLosing(network: Network, maxMsToLive: Int) {
        super.onLosing(network, maxMsToLive)
        logger?.d(LOG_TAG, "onLosing, network: $network, maxMsToLive: $maxMsToLive")
        this.connectionStatus = NetworkConnectionStatus.LOSING
    }

    override fun onLost(network: Network) {
        super.onLost(network)
        logger?.d(LOG_TAG, "onLost, network: $network")
        this.connectionStatus = NetworkConnectionStatus.LOST
    }

    override fun onUnavailable() {
        super.onUnavailable()
        logger?.d(LOG_TAG, "onUnavailable")
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
