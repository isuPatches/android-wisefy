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
package com.isupatches.android.wisefy.networkconnection.os.impls

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.net.ConnectivityManager
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.core.bssidWithoutQuotes
import com.isupatches.android.wisefy.core.entities.NetworkConnectionStatus
import com.isupatches.android.wisefy.core.logging.WisefyLogger
import com.isupatches.android.wisefy.core.ssidWithoutQuotes
import com.isupatches.android.wisefy.core.util.SdkUtil
import com.isupatches.android.wisefy.core.util.rest
import com.isupatches.android.wisefy.networkconnection.os.apis.DefaultNetworkConnectionApi

/**
 * A default implementation for connecting to or disconnecting from a network through the Android OS.
 *
 * @param wifiManager The WifiManager instance to use
 * @param logger The WisefyLogger instance to use
 *
 * @see DefaultNetworkConnectionApi
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
internal class DefaultNetworkConnectionApiImpl(
    private val connectivityManager: ConnectivityManager,
    private val wifiManager: WifiManager,
    private val logger: WisefyLogger,
    private val sdkUtil: SdkUtil,
    private val networkConnectionStatusProvider: () -> NetworkConnectionStatus
) : DefaultNetworkConnectionApi, ConnectivityManager.NetworkCallback() {

    companion object {
        private const val LOG_TAG = "DefaultNetworkConnectionApiImpl"
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE, ACCESS_NETWORK_STATE])
    override fun connectToNetworkBySSID(ssid: String, timeoutInMillis: Int): Boolean? {
        val savedNetwork = wifiManager.configuredNetworks.firstOrNull { it.ssidWithoutQuotes == ssid }
        return savedNetwork?.let {
            connect(networkId = it.networkId)
            waitForConnectionToSSID(ssid = ssid, timeoutInMillis = timeoutInMillis)
        }
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE, ACCESS_NETWORK_STATE])
    override fun connectToNetworkByBSSID(bssid: String, timeoutInMillis: Int): Boolean? {
        val savedNetwork = wifiManager.configuredNetworks.firstOrNull { it.bssidWithoutQuotes == bssid }
        return savedNetwork?.let {
            connect(networkId = it.networkId)
            waitForConnectionToBSSID(bssid = bssid, timeoutInMillis = timeoutInMillis)
        }
    }

    override fun disconnectFromCurrentNetwork(): Boolean {
        val result = wifiManager.disconnect()
        logger.d(LOG_TAG, "Disconnecting from network. result: $result")
        return result
    }

    private fun connect(networkId: Int) {
        val disconnectResult = wifiManager.disconnect()
        val enableNetworkResult = wifiManager.enableNetwork(networkId, true)
        val reconnectResult = wifiManager.reconnect()
        logger.d(
            LOG_TAG,
            "Connecting to network with id: $networkId. disconnectResult: $disconnectResult, enableNetworkResult: " +
                "$enableNetworkResult, reconnectResult: $reconnectResult"
        )
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    private fun isCurrentNetworkConnectedBySSID(ssid: String): Boolean {
        return when (networkConnectionStatusProvider()) {
            NetworkConnectionStatus.AVAILABLE -> getNetworkTransportInfo()?.ssidWithoutQuotes == ssid
            NetworkConnectionStatus.LOSING,
            NetworkConnectionStatus.LOST,
            NetworkConnectionStatus.UNAVAILABLE -> false
        }
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
    private fun isCurrentNetworkConnectedByBSSID(bssid: String): Boolean {
        return when (networkConnectionStatusProvider()) {
            NetworkConnectionStatus.AVAILABLE -> getNetworkTransportInfo()?.bssidWithoutQuotes == bssid
            NetworkConnectionStatus.LOSING,
            NetworkConnectionStatus.LOST,
            NetworkConnectionStatus.UNAVAILABLE -> false
        }
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    private fun waitForConnectionToSSID(ssid: String, timeoutInMillis: Int): Boolean {
        logger.d(
            LOG_TAG,
            "Waiting %d milliseconds to connect to network with ssid %s",
            timeoutInMillis,
            ssid
        )
        var currentTime: Long
        val endTime = System.currentTimeMillis() + timeoutInMillis
        do {
            if (isCurrentNetworkConnectedBySSID(ssid)) {
                return true
            }
            rest()
            currentTime = System.currentTimeMillis()
            logger.d(LOG_TAG, "Current time: %d, End time: %d (waitToConnectToSSID)", currentTime, endTime)
        } while (currentTime < endTime)
        return false
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    private fun waitForConnectionToBSSID(bssid: String, timeoutInMillis: Int): Boolean {
        logger.d(
            LOG_TAG,
            "Waiting %d milliseconds to connect to network with bssid %s",
            timeoutInMillis,
            bssid
        )
        var currentTime: Long
        val endTime = System.currentTimeMillis() + timeoutInMillis
        do {
            if (isCurrentNetworkConnectedByBSSID(bssid)) {
                return true
            }
            rest()
            currentTime = System.currentTimeMillis()
            logger.d(LOG_TAG, "Current time: %d, End time: %d (waitToConnectToBSSID)", currentTime, endTime)
        } while (currentTime < endTime)
        return false
    }
}
