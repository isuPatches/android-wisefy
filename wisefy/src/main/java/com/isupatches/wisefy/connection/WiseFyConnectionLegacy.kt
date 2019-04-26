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

import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.wifi.WifiManager

import com.isupatches.wisefy.constants.MOBILE
import com.isupatches.wisefy.constants.NetworkType
import com.isupatches.wisefy.constants.WIFI
import com.isupatches.wisefy.logging.WiseFyLogger

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
@Suppress("deprecation")
internal class WiseFyConnectionLegacy private constructor(
    private val connectivityManager: ConnectivityManager,
    wifiManager: WifiManager
) : AbstractWiseFyConnection(wifiManager) {

    internal companion object {
        private val TAG = WiseFyConnectionLegacy::class.java.simpleName

        fun create(connectivityManager: ConnectivityManager, wifiManager: WifiManager): WiseFyConnection =
                WiseFyConnectionLegacy(connectivityManager, wifiManager)
    }

    override fun isDeviceConnectedToMobileNetwork(): Boolean =
        isNetworkConnectedAndMatchesType(connectivityManager.activeNetworkInfo, MOBILE)

    override fun isDeviceConnectedToWifiNetwork(): Boolean =
        isNetworkConnectedAndMatchesType(connectivityManager.activeNetworkInfo, WIFI)

    override fun isDeviceRoaming(): Boolean {
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isRoaming
    }

    /**
     * Used internally to check if a network is connected.
     *
     * @return boolean - True if the network is both available and connected
     *
     * @see [NetworkInfo]
     *
     * @author Patches
     * @since 3.0
     */
    override fun isNetworkConnected(): Boolean {
        val networkInfo = connectivityManager.activeNetworkInfo
        WiseFyLogger.debug(TAG, "networkInfo: %s", networkInfo ?: "")
        return networkInfo != null && networkInfo.isConnected && networkInfo.isAvailable
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
    private fun isNetworkConnectedAndMatchesType(networkInfo: NetworkInfo?, @NetworkType type: String): Boolean =
        isNetworkConnected() && doesNetworkMatchType(networkInfo, type)
}
