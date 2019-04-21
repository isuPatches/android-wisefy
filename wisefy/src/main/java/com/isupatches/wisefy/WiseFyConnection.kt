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
package com.isupatches.wisefy

import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission

import com.isupatches.wisefy.annotations.WaitsForTimeout
import com.isupatches.wisefy.constants.NetworkType
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
 * @since 3.0
 */
internal class WiseFyConnectionImpl private constructor(
    private val connectivityManager: ConnectivityManager,
    private val wifiManager: WifiManager
) : WiseFyConnection {

    internal companion object {
        private val TAG = WiseFyConnection::class.java.simpleName

        fun create(connectivityManager: ConnectivityManager, wifiManager: WifiManager): WiseFyConnection =
            WiseFyConnectionImpl(connectivityManager, wifiManager)
    }

    /**
     * Used internally to see if the current network is connected to and matches a given ssid.
     *
     * *NOTE* Case insensitive
     *
     * @param ssid The ssid to check if the device is connected to
     *
     * @return boolean - True if the device is connected to a network
     *
     * @see [isNetworkConnected]
     * @see [WifiManager.getConnectionInfo]
     *
     * @author Patches
     * @since 3.0
     */
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
                if (currentSSID.equals(ssid, ignoreCase = true) &&
                    isNetworkConnected(connectivityManager.activeNetworkInfo)
                ) {
                    WiseFyLogger.debug(TAG, "Network is connected")
                    return true
                }
            }
        }
        return false
    }

    /**
     * Used internally to check if a network is connected.
     *
     * @param networkInfo The network to check
     *
     * @return boolean - True if the network is both available and connected
     *
     * @see [NetworkInfo]
     *
     * @author Patches
     * @since 3.0
     */
    override fun isNetworkConnected(networkInfo: NetworkInfo?): Boolean {
        WiseFyLogger.debug(TAG, "networkInfo: %s", networkInfo ?: "")
        return networkInfo != null && networkInfo.isConnected && networkInfo.isAvailable
    }

    /**
     * Used internally to check if a given network matches a given type and is connected.
     *
     * @param networkInfo The network to check
     * @param type The type of network (i.error. Mobile or Wifi)
     *
     * @return boolean - True if the network is both connected and matches the given type of network
     *
     * @see [doesNetworkMatchType]
     * @see [isNetworkConnected]
     * @see [NetworkInfo]
     *
     * @author Patches
     * @since 3.0
     */
    override fun isNetworkConnectedAndMatchesType(networkInfo: NetworkInfo?, @NetworkType type: String): Boolean =
        isNetworkConnected(networkInfo) && doesNetworkMatchType(networkInfo, type)

    /**
     * Used internally to check if the device connects to a given SSID within a specified time.
     *
     * @param ssid The ssid to wait for the device to connect to
     * @param timeoutInMillis The number of milliseconds to wait
     *
     * @return boolean - True if the device is connected to the ssid within the given time
     *
     * @see [isCurrentNetworkConnectedToSSID]
     *
     * @author Patches
     * @since 3.0
     */
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

    /**
     * Used internally to check to see if a given network matches a specified type (i.error. Mobile or Wifi)
     *
     * *NOTE* Case insensitive
     *
     * @param networkInfo The network to check
     * @param type The type of network
     *
     * @return boolean - True if the network matches the given type
     *
     * @see [NetworkInfo]
     * @see [NetworkType]
     *
     * @author Patches
     * @since 3.0
     */
    private fun doesNetworkMatchType(networkInfo: NetworkInfo?, @NetworkType type: String): Boolean =
        type.equals(networkInfo?.typeName, ignoreCase = true)
}

/**
 * An interface with methods that relate to checking device connectivity.
 *
 * @see [WiseFyConnectionImpl]
 *
 * @author Patches
 * @since 3.0
 */
internal interface WiseFyConnection {

    fun isCurrentNetworkConnectedToSSID(ssid: String?): Boolean

    fun isNetworkConnected(networkInfo: NetworkInfo?): Boolean

    fun isNetworkConnectedAndMatchesType(networkInfo: NetworkInfo?, @NetworkType type: String): Boolean

    fun waitToConnectToSSID(ssid: String?, timeoutInMillis: Int): Boolean
}
