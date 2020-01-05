/*
 * Copyright 2020 Patches Klinefelter
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
 * device when WiseFy is set to use the legacy connectivity class or is on a pre-SDK23 device.
 *
 * @see [ConnectivityManager]
 * @see [WifiManager]
 * @see [AbstractWiseFyConnection]
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

        /**
         * Used internally to create an instance of a legacy WiseFyConnection.
         *
         * @param connectivityManager The instance of ConnectivityManager to use
         * @param wifiManager The instance of WifiManager to use
         *
         * @return WiseFyConnectionLegacy
         *
         * @see [WiseFyConnection]
         *
         * Updates
         * - 01/04/2020: Formatting update
         *
         * @author Patches
         * @since 4.0
         */
        fun create(connectivityManager: ConnectivityManager, wifiManager: WifiManager): WiseFyConnection {
            return WiseFyConnectionLegacy(connectivityManager, wifiManager)
        }
    }

    /**
     * Used internally for any initialization of [WiseFyConnectionLegacy] class.
     *
     * @author Patches
     * @since 4.0
     */
    override fun init() {
        // No-op
    }

    /**
     * Used internally for any tear down of [WiseFyConnectionLegacy] class.
     *
     * @author Patches
     * @since 4.0
     */
    override fun destroy() {
        // No-op
    }

    /**
     * Used internally to check if a network is connected to a mobile network (i.e. non-Wifi)
     *
     * @return boolean - True if the device is using a mobile network, false otherwise
     *
     * @see [ConnectivityManager.getActiveNetworkInfo]
     * @see [isNetworkConnectedAndMatchesType]
     * @see [MOBILE]
     *
     * @author Patches
     * @since 4.0
     */
    override fun isDeviceConnectedToMobileNetwork(): Boolean {
        return isNetworkConnectedAndMatchesType(connectivityManager.activeNetworkInfo, MOBILE)
    }

    /**
     * Used internally to check if a network is connected to a wifi network (i.e. not using
     * mobile data)
     *
     * @return boolean - True if the device is using a wifi network, false otherwise
     *
     * @see [ConnectivityManager.getActiveNetworkInfo]
     * @see [isNetworkConnectedAndMatchesType]
     * @see [WIFI]
     *
     * Updates
     * - 01/04/2020: Formatting update
     *
     * @author Patches
     * @since 4.0
     */
    override fun isDeviceConnectedToWifiNetwork(): Boolean {
        return isNetworkConnectedAndMatchesType(connectivityManager.activeNetworkInfo, WIFI)
    }

    /**
     * Used internally to check if a network is in a roaming state.
     *
     * @return boolean - True if the device is roaming, false otherwise
     *
     * @see [ConnectivityManager.getActiveNetworkInfo]
     * @see [NetworkInfo.isRoaming]
     *
     * @author Patches
     * @since 4.0
     */
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
     * @see [isConnectedAndAvailable]
     *
     * Updates
     * - 05/12/2019: Switched to using [isConnectedAndAvailable]
     *
     * @author Patches
     * @since 3.0
     */
    override fun isNetworkConnected(): Boolean {
        val networkInfo = connectivityManager.activeNetworkInfo
        WiseFyLogger.debug(TAG, "networkInfo: %s", networkInfo ?: "")
        return networkInfo?.isConnectedAndAvailable() ?: false
    }

    /**
     * Used internally to check to see if a given network matches a specified type (i.error. Mobile or Wifi)
     *
     * *NOTE* Case insensitive
     *
     * @param networkInfo The network to check
     * @param type The type of network (f.e. Mobile or Wifi)
     *
     * @return boolean - True if the network matches the given type
     *
     * @see [NetworkInfo]
     * @see [NetworkType]
     *
     * Updates
     * - 05/12/2019: Made networkInfo expectation non-null
     * - 01/04/2020: Formatting update
     *
     * @author Patches
     * @since 3.0
     */
    private fun doesNetworkMatchType(networkInfo: NetworkInfo, @NetworkType type: String): Boolean {
        return type.equals(networkInfo.typeName, ignoreCase = true)
    }

    /**
     * Used internally to check if a given network matches a given type and is connected.
     *
     * @param networkInfo The network to check
     * @param type The type of network (f.e. Mobile or Wifi)
     *
     * @return boolean - True if the network is both connected and matches the given type of network
     *
     * @see [doesNetworkMatchType]
     * @see [isConnectedAndAvailable]
     * @see [NetworkInfo]
     *
     * Updates
     * - 05/12/2019: Switched to using [isConnectedAndAvailable] over [isNetworkConnected]
     * - 01/04/2020: Formatting update
     *
     * @author Patches
     * @since 3.0
     */
    private fun isNetworkConnectedAndMatchesType(networkInfo: NetworkInfo?, @NetworkType type: String): Boolean {
        return networkInfo?.let { doesNetworkMatchType(it, type) && it.isConnectedAndAvailable() } ?: false
    }

    /**
     * Used within legacy class to determine if a given network is in a connected state.
     *
     * @return boolean - Whether the given NetworkInfo is both connected and available.
     *
     * @see [NetworkInfo.isConnected]
     * @see [NetworkInfo.isAvailable]
     *
     * @author Patches
     * @since 4.0
     */
    private fun NetworkInfo.isConnectedAndAvailable() = isConnected && isAvailable
}
