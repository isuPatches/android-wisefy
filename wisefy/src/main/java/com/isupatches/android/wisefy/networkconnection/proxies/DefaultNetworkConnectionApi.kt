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
package com.isupatches.android.wisefy.networkconnection.proxies

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.constants.QUOTE
import com.isupatches.android.wisefy.logging.WisefyLogger
import com.isupatches.android.wisefy.networkconnection.entities.NetworkConnectionRequest
import com.isupatches.android.wisefy.networkconnection.entities.NetworkConnectionResult
import com.isupatches.android.wisefy.networkconnection.entities.toSearchForNetworkRequest
import com.isupatches.android.wisefy.networkconnectionstatus.NetworkConnectionStatusDelegate
import com.isupatches.android.wisefy.savednetworks.SavedNetworkDelegate
import com.isupatches.android.wisefy.savednetworks.entities.SavedNetworkData
import com.isupatches.android.wisefy.util.rest

internal interface DefaultNetworkConnectionApi {
    fun connectToNetwork(request: NetworkConnectionRequest): NetworkConnectionResult
    fun disconnectFromCurrentNetwork(): NetworkConnectionResult
}

private const val LOG_TAG = "DefaultNetworkConnectionApiImpl"

internal class DefaultNetworkConnectionApiImpl(
    private val wifiManager: WifiManager,
    private val networkConnectionStatusUtil: NetworkConnectionStatusDelegate,
    private val savedNetworkUtil: SavedNetworkDelegate,
    private val logger: WisefyLogger?
) : DefaultNetworkConnectionApi, ConnectivityManager.NetworkCallback() {

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun connectToNetwork(request: NetworkConnectionRequest): NetworkConnectionResult {
        return when (
            val savedNetworkData = savedNetworkUtil.searchForSavedNetwork(request.toSearchForNetworkRequest())
        ) {
            null -> return NetworkConnectionResult.NetworkNotFound
            is SavedNetworkData.Configuration -> {
                savedNetworkData.value.let {
                    wifiManager.disconnect()
                    wifiManager.enableNetwork(it.networkId, true)
                    wifiManager.reconnect()
                    return NetworkConnectionResult.Succeeded(waitToConnectToSSID(request))
                }
            }
            else -> NetworkConnectionResult.Succeeded(false)
        }
    }

    override fun disconnectFromCurrentNetwork(): NetworkConnectionResult {
        return NetworkConnectionResult.Succeeded(value = wifiManager.disconnect())
    }

    private fun waitToConnectToSSID(request: NetworkConnectionRequest): Boolean {
        logger?.d(
            LOG_TAG,
            "Waiting %d milliseconds to connect to network with search request %s",
            request.timeoutInMillis,
            request
        )
        var currentTime: Long
        val endTime = System.currentTimeMillis() + request.timeoutInMillis
        do {
            if (isCurrentNetworkConnected(request)) {
                return true
            }
            rest()
            currentTime = System.currentTimeMillis()
            logger?.d(LOG_TAG, "Current time: %d, End time: %d (waitToConnectToSSID)", currentTime, endTime)
        } while (currentTime < endTime)
        return false
    }

    private fun isCurrentNetworkConnected(request: NetworkConnectionRequest): Boolean {
        val expectedValue = when (request) {
            is NetworkConnectionRequest.SSID -> request.ssid
            is NetworkConnectionRequest.BSSID -> request.bssid
        }
        if (expectedValue.isBlank()) {
            return false
        }

        val connectionInfo = wifiManager.connectionInfo
        connectionInfo?.let {
            if (!it.ssid.isNullOrEmpty()) {
                val currentValue = when (request) {
                    is NetworkConnectionRequest.SSID -> it.ssid.replace(QUOTE, "")
                    is NetworkConnectionRequest.BSSID -> it.bssid.replace(QUOTE, "")
                }
                logger?.d(LOG_TAG, "Current value: %s, Desired value: %s", currentValue, expectedValue)
                if (currentValue.equals(expectedValue, ignoreCase = true) &&
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
