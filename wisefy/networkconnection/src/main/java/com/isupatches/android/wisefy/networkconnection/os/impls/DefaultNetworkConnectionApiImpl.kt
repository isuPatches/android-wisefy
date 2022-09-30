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
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.core.logging.WisefyLogger
import com.isupatches.android.wisefy.core.util.rest
import com.isupatches.android.wisefy.networkconnection.os.apis.DefaultNetworkConnectionApi
import com.isupatches.android.wisefy.networkconnectionstatus.NetworkConnectionStatusDelegate
import com.isupatches.android.wisefy.savednetworks.SavedNetworkDelegate
import com.isupatches.android.wisefy.savednetworks.entities.SavedNetworkData
import com.isupatches.android.wisefy.savednetworks.entities.SearchForSavedNetworksRequest
import com.isupatches.android.wisefy.savednetworks.entities.SearchForSavedNetworksResult

/**
 * A default implementation for connecting to or disconnecting from a network through the Android OS.
 *
 * @param wifiManager The WifiManager instance to use
 * @param networkConnectionStatusDelegate The NetworkConnectionStatusDelegate instance to use
 * @param savedNetworkUtil The SavedNetworkDelegate instance to use
 * @param logger The WisefyLogger instance to use
 *
 * @see DefaultNetworkConnectionApi
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
internal class DefaultNetworkConnectionApiImpl(
    private val wifiManager: WifiManager,
    private val networkConnectionStatusDelegate: NetworkConnectionStatusDelegate,
    private val savedNetworkUtil: SavedNetworkDelegate,
    private val logger: WisefyLogger
) : DefaultNetworkConnectionApi, ConnectivityManager.NetworkCallback() {

    companion object {
        private const val LOG_TAG = "DefaultNetworkConnectionApiImpl"
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE, ACCESS_NETWORK_STATE])
    override fun connectToNetworkBySSID(ssid: String, timeoutInMillis: Int): Boolean? {
        return when (
            val savedNetworkSearchResult = savedNetworkUtil.searchForSavedNetwork(
                SearchForSavedNetworksRequest.SSID(regex = ssid)
            )
        ) {
            is SearchForSavedNetworksResult.Success.Empty -> null
            is SearchForSavedNetworksResult.Success.SavedNetworks -> {
                when (val saveNetwork = savedNetworkSearchResult.data.first()) {
                    is SavedNetworkData.Configuration -> {
                        saveNetwork.value.let {
                            wifiManager.disconnect()
                            wifiManager.enableNetwork(it.networkId, true)
                            wifiManager.reconnect()
                            waitToConnectToSSID(ssid, timeoutInMillis)
                        }
                    }
                    is SavedNetworkData.Suggestion -> false
                }
            }
            is SearchForSavedNetworksResult.Failure.Assertion -> null
        }
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE, ACCESS_NETWORK_STATE])
    override fun connectToNetworkByBSSID(bssid: String, timeoutInMillis: Int): Boolean? {
        return when (
            val savedNetworkSearchResult = savedNetworkUtil.searchForSavedNetwork(
                SearchForSavedNetworksRequest.BSSID(regex = bssid)
            )
        ) {
            is SearchForSavedNetworksResult.Success.Empty -> null
            is SearchForSavedNetworksResult.Success.SavedNetworks -> {
                when (val saveNetwork = savedNetworkSearchResult.data.first()) {
                    is SavedNetworkData.Configuration -> {
                        saveNetwork.value.let {
                            wifiManager.disconnect()
                            wifiManager.enableNetwork(it.networkId, true)
                            wifiManager.reconnect()
                            waitToConnectToBSSID(bssid, timeoutInMillis)
                        }
                    }
                    is SavedNetworkData.Suggestion -> false
                }
            }
            is SearchForSavedNetworksResult.Failure.Assertion -> {
                // todo@patches Figure out what to do here
                null
            }
        }
    }

    override fun disconnectFromCurrentNetwork(): Boolean {
        return wifiManager.disconnect()
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    private fun isCurrentNetworkConnectedBySSID(ssid: String): Boolean {
        val connectionStatus = networkConnectionStatusDelegate.getNetworkConnectionStatus()
        if (!connectionStatus.ssidOfNetworkConnectedTo.isNullOrEmpty()) {
            logger.d(
                LOG_TAG,
                "Current value: %s, Desired value: %s", connectionStatus.ssidOfNetworkConnectedTo ?: "", ssid
            )
            if (connectionStatus.ssidOfNetworkConnectedTo.equals(ssid, ignoreCase = true) &&
                networkConnectionStatusDelegate.getNetworkConnectionStatus().isConnected
            ) {
                logger.d(LOG_TAG, "Network is connected")
                return true
            }
        }
        return false
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    private fun isCurrentNetworkConnectedByBSSID(bssid: String): Boolean {
        val connectionStatus = networkConnectionStatusDelegate.getNetworkConnectionStatus()
        if (!connectionStatus.bssidOfNetworkConnectedTo.isNullOrEmpty()) {
            logger.d(
                LOG_TAG,
                "Current value: %s, Desired value: %s", connectionStatus.bssidOfNetworkConnectedTo ?: "", bssid
            )
            if (connectionStatus.bssidOfNetworkConnectedTo.equals(bssid, ignoreCase = true) &&
                networkConnectionStatusDelegate.getNetworkConnectionStatus().isConnected
            ) {
                logger.d(LOG_TAG, "Network is connected")
                return true
            }
        }
        return false
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    private fun waitToConnectToSSID(ssid: String, timeoutInMillis: Int): Boolean {
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
    private fun waitToConnectToBSSID(bssid: String, timeoutInMillis: Int): Boolean {
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
