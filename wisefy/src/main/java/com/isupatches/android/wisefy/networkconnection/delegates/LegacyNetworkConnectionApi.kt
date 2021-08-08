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

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.constants.QUOTE
import com.isupatches.android.wisefy.logging.WisefyLogger
import com.isupatches.android.wisefy.networkconnection.entities.NetworkConnectionResult
import com.isupatches.android.wisefy.networkconnectionstatus.NetworkConnectionStatusUtil
import com.isupatches.android.wisefy.savednetworks.SavedNetworkUtil
import com.isupatches.android.wisefy.savednetworks.entities.SavedNetworkData
import com.isupatches.android.wisefy.util.rest

internal interface LegacyNetworkConnectionApi {
    fun connectToNetwork(ssidToConnectTo: String, timeoutInMillis: Int = 0): NetworkConnectionResult
    fun disconnectFromCurrentNetwork(): NetworkConnectionResult
}

private const val LOG_TAG = "LegacyNetworkConnectionApiImpl"

internal class LegacyNetworkConnectionApiImpl(
    private val wifiManager: WifiManager,
    private val networkConnectionStatusUtil: NetworkConnectionStatusUtil,
    private val savedNetworkUtil: SavedNetworkUtil,
    private val logger: WisefyLogger?
) : LegacyNetworkConnectionApi, ConnectivityManager.NetworkCallback() {

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun connectToNetwork(ssidToConnectTo: String, timeoutInMillis: Int): NetworkConnectionResult {
        when (val savedNetworkData = savedNetworkUtil.searchForSavedNetwork(ssidToConnectTo)) {
            is SavedNetworkData.Configuration -> {
                savedNetworkData.data?.let {
                    wifiManager.disconnect()
                    wifiManager.enableNetwork(it.networkId, true)
                    wifiManager.reconnect()
                    return NetworkConnectionResult.Succeeded(waitToConnectToSSID(ssidToConnectTo, timeoutInMillis))
                }
                return NetworkConnectionResult.Succeeded(false)
            }
        }
        return NetworkConnectionResult.Succeeded(false)
    }

    override fun disconnectFromCurrentNetwork(): NetworkConnectionResult {
        return NetworkConnectionResult.Succeeded(data = wifiManager.disconnect())
    }

    private fun waitToConnectToSSID(ssid: String?, timeoutInMillis: Int): Boolean {
        logger?.d(
            LOG_TAG,
            "Waiting %d milliseconds to connect to network with ssid %s",
            timeoutInMillis,
            ssid ?: ""
        )
        var currentTime: Long
        val endTime = System.currentTimeMillis() + timeoutInMillis
        do {
            if (isCurrentNetworkConnectedToSSID(ssid)) {
                return true
            }
            rest()
            currentTime = System.currentTimeMillis()
            logger?.d(LOG_TAG, "Current time: %d, End time: %d (waitToConnectToSSID)", currentTime, endTime)
        } while (currentTime < endTime)
        return false
    }

    private fun isCurrentNetworkConnectedToSSID(ssid: String?): Boolean {
        if (ssid.isNullOrEmpty()) {
            return false
        }

        val connectionInfo = wifiManager.connectionInfo
        connectionInfo?.let {
            if (!it.ssid.isNullOrEmpty()) {
                val currentSSID = it.ssid.replace(QUOTE, "")
                logger?.d(LOG_TAG, "Current SSID: %s, Desired SSID: %s", currentSSID, ssid)
                if (currentSSID.equals(ssid, ignoreCase = true) &&
                    networkConnectionStatusUtil.isDeviceConnectedToMobileOrWifiNetwork()
                ) {
                    logger?.d(LOG_TAG, "Network is connected")
                    return true
                }
            }
        }
        return false
    }
}
