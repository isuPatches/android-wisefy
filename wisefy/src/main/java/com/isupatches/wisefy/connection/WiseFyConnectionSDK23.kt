/*
 * Copyright 2018 Patches Klinefelter
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
package com.isupatches.wisefy.connection

import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.net.wifi.WifiManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission

import com.isupatches.wisefy.annotations.WaitsForTimeout
import com.isupatches.wisefy.constants.QUOTE
import com.isupatches.wisefy.logging.WiseFyLogger
import com.isupatches.wisefy.utils.rest

/**
 * A class used internally to query and determine different parts of the connection state for a
 * device.
 *
 * @see [ConnectivityManager]
 * @see [WifiManager]
 * @see [WiseFyConnection]
 *
 * @author Patches
 * @since 4.0
 */
@RequiresApi(Build.VERSION_CODES.M)
internal class WiseFyConnectionSDK23 private constructor(
    private val connectivityManager: ConnectivityManager,
    private val wifiManager: WifiManager
) : WiseFyConnection {

    internal companion object {
        private val TAG = WiseFyConnectionSDK23::class.java.simpleName

        fun create(connectivityManager: ConnectivityManager, wifiManager: WifiManager): WiseFyConnection =
                WiseFyConnectionSDK23(connectivityManager, wifiManager)
    }

    private var network: Network? = null
    private var networkCapabilities: NetworkCapabilities? = null

    private var connectionStatus: WiseFyConnectionStatus? = null

    private val networkChangeCallback by lazy {
        object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network?) {
                super.onAvailable(network)
                WiseFyLogger.debug(TAG, "onAvailable, $network")
                this@WiseFyConnectionSDK23.network = network
                this@WiseFyConnectionSDK23.connectionStatus = WiseFyConnectionStatus.AVAILABLE
            }

            override fun onCapabilitiesChanged(network: Network?, networkCapabilities: NetworkCapabilities?) {
                super.onCapabilitiesChanged(network, networkCapabilities)
                WiseFyLogger.debug(TAG, "onCapabilitiesChanged, network: $network, networkCapabilities: $networkCapabilities")
                this@WiseFyConnectionSDK23.networkCapabilities = networkCapabilities
                this@WiseFyConnectionSDK23.network = network
            }

            override fun onLinkPropertiesChanged(network: Network?, linkProperties: LinkProperties?) {
                super.onLinkPropertiesChanged(network, linkProperties)
                WiseFyLogger.debug(TAG, "onCapabilitiesChanged, network: $network, linkProperties: $linkProperties")
                this@WiseFyConnectionSDK23.network = network
            }

            override fun onLosing(network: Network?, maxMsToLive: Int) {
                super.onLosing(network, maxMsToLive)
                WiseFyLogger.debug(TAG, "onLosing, network: $network, maxMsToLive: $maxMsToLive")
                this@WiseFyConnectionSDK23.network = network
                this@WiseFyConnectionSDK23.connectionStatus = WiseFyConnectionStatus.LOSING
            }

            override fun onLost(network: Network?) {
                super.onLost(network)
                WiseFyLogger.debug(TAG, "onLost, network: $network")
                this@WiseFyConnectionSDK23.network = network
                this@WiseFyConnectionSDK23.connectionStatus = WiseFyConnectionStatus.LOST
            }

            override fun onUnavailable() {
                super.onUnavailable()
                WiseFyLogger.debug(TAG, "onUnavailable")
                this@WiseFyConnectionSDK23.connectionStatus = WiseFyConnectionStatus.UNAVAILABLE
            }
        }
    }

    override fun init() {
        startListeningForNetworkChanges(connectivityManager)
    }

    override fun destroy() {
        stopListeningForNetworkChanges(connectivityManager)
    }

    @RequiresPermission(allOf = arrayOf(ACCESS_NETWORK_STATE, ACCESS_WIFI_STATE))
    override fun isCurrentNetworkConnectedToSSID(ssid: String?): Boolean {
        if (ssid.isNullOrEmpty()) {
            return false
        }

        val connectionInfo = wifiManager.connectionInfo
        connectionInfo?.let {
            if (!it.ssid.isNullOrEmpty()) {
                val currentSSID = it.ssid.replace(QUOTE, "")
                WiseFyLogger.debug(TAG, "Current SSID: %s, Desired SSID: %s", currentSSID, ssid)
                if (currentSSID.equals(ssid, ignoreCase = true) && isNetworkConnected()) {
                    WiseFyLogger.debug(TAG, "Network is connected")
                    return true
                }
            }
        }
        return false
    }

    override fun isDeviceConnectedToMobileNetwork(): Boolean = doesNetworkHaveTransportType(
        transportType = NetworkCapabilities.TRANSPORT_CELLULAR
    )

    override fun isDeviceConnectedToWifiNetwork(): Boolean = doesNetworkHaveTransportType(
        transportType = NetworkCapabilities.TRANSPORT_WIFI
    )

    override fun isNetworkConnected(): Boolean = connectionStatus == WiseFyConnectionStatus.AVAILABLE

    @WaitsForTimeout
    @RequiresPermission(allOf = arrayOf(ACCESS_NETWORK_STATE, ACCESS_WIFI_STATE))
    override fun waitToConnectToSSID(ssid: String?, timeoutInMillis: Int): Boolean {
        WiseFyLogger.debug(TAG, "Waiting %d milliseconds to connect to network with ssid %s", timeoutInMillis, ssid ?: "")
        var currentTime: Long
        val endTime = System.currentTimeMillis() + timeoutInMillis
        do {
            if (isCurrentNetworkConnectedToSSID(ssid)) {
                return true
            }
            rest()
            currentTime = System.currentTimeMillis()
            WiseFyLogger.debug(TAG, "Current time: %d, End time: %d (waitToConnectToSSID)", currentTime, endTime)
        } while (currentTime < endTime)
        return false
    }

    private fun doesNetworkHaveTransportType(transportType: Int): Boolean =
            networkCapabilities?.hasTransport(transportType) ?: false

    private fun startListeningForNetworkChanges(connectivityManager: ConnectivityManager) {
        val request = NetworkRequest.Builder().build()
        connectivityManager.registerNetworkCallback(request, networkChangeCallback)
    }

    private fun stopListeningForNetworkChanges(connectivityManager: ConnectivityManager) {
        connectivityManager.unregisterNetworkCallback(networkChangeCallback)
    }
}
