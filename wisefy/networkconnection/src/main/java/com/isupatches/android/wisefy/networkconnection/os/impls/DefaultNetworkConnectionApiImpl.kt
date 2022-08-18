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
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.core.entities.DeprecationMessages
import com.isupatches.android.wisefy.core.entities.QUOTE
import com.isupatches.android.wisefy.core.logging.WisefyLogger
import com.isupatches.android.wisefy.core.util.rest
import com.isupatches.android.wisefy.networkconnection.os.apis.DefaultNetworkConnectionApi
import com.isupatches.android.wisefy.networkconnectionstatus.NetworkConnectionStatusDelegate
import com.isupatches.android.wisefy.networkconnectionstatus.entities.IsDeviceConnectedResult
import com.isupatches.android.wisefy.savednetworks.SavedNetworkDelegate
import com.isupatches.android.wisefy.savednetworks.entities.SavedNetworkData
import com.isupatches.android.wisefy.savednetworks.entities.SearchForSavedNetworkRequest
import com.isupatches.android.wisefy.savednetworks.entities.SearchForSavedNetworkResult

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

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun connectToNetworkBySSID(ssid: String, timeoutInMillis: Int): Boolean? {
        return when (
            val savedNetworkSearchResult = savedNetworkUtil.searchForSavedNetwork(
                SearchForSavedNetworkRequest.SSID(regex = ssid)
            )
        ) {
            is SearchForSavedNetworkResult.Success.Empty -> null
            is SearchForSavedNetworkResult.Success.SavedNetworks -> {
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
            is SearchForSavedNetworkResult.Failure.Assertion -> {
                // todo@patches Figure out what to do here
                null
            }
        }
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun connectToNetworkByBSSID(bssid: String, timeoutInMillis: Int): Boolean? {
        return when (
            val savedNetworkSearchResult = savedNetworkUtil.searchForSavedNetwork(
                SearchForSavedNetworkRequest.BSSID(regex = bssid)
            )
        ) {
            is SearchForSavedNetworkResult.Success.Empty -> null
            is SearchForSavedNetworkResult.Success.SavedNetworks -> {
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
            is SearchForSavedNetworkResult.Failure.Assertion -> {
                // todo@patches Figure out what to do here
                null
            }
        }
    }

    @Deprecated(DeprecationMessages.NetworkConnection.DisconnectFromCurrentNetwork)
    override fun disconnectFromCurrentNetwork(): Boolean {
        return wifiManager.disconnect()
    }

    private fun isCurrentNetworkConnectedBySSID(ssid: String): Boolean {
        val connectionInfo = wifiManager.connectionInfo
        connectionInfo?.let {
            if (!it.ssid.isNullOrEmpty()) {
                val currentValue = it.ssid.replace(QUOTE, "")
                logger.d(LOG_TAG, "Current value: %s, Desired value: %s", currentValue, ssid)
                if (currentValue.equals(ssid, ignoreCase = true) &&
                    networkConnectionStatusDelegate.isDeviceConnectedToMobileOrWifiNetwork() is
                    IsDeviceConnectedResult.True
                ) {
                    logger.d(LOG_TAG, "Network is connected")
                    return true
                }
            }
        }
        return false
    }

    private fun isCurrentNetworkConnectedByBSSID(bssid: String): Boolean {
        val connectionInfo = wifiManager.connectionInfo
        connectionInfo?.let {
            if (!it.ssid.isNullOrEmpty()) {
                val currentValue = it.bssid.replace(QUOTE, "")
                logger.d(LOG_TAG, "Current value: %s, Desired value: %s", currentValue, bssid)
                if (currentValue.equals(bssid, ignoreCase = true) &&
                    networkConnectionStatusDelegate.isDeviceConnectedToMobileOrWifiNetwork() is
                    IsDeviceConnectedResult.True
                ) {
                    logger.d(LOG_TAG, "Network is connected")
                    return true
                }
            }
        }
        return false
    }

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
