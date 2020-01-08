/*
 * Copyright 2019 Patches Klinefelter
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

import android.net.wifi.WifiManager
import com.isupatches.wisefy.annotations.WaitsForTimeout
import com.isupatches.wisefy.constants.QUOTE
import com.isupatches.wisefy.logging.WiseFyLogger
import com.isupatches.wisefy.utils.rest

/**
 * A class used internally to house shared connectivity logic between all SDK versions of Android.
 *
 * @see [WifiManager]
 * @see [WiseFyConnection]
 *
 * @author Patches
 * @since 4.0
 */
internal abstract class AbstractWiseFyConnection(
    private val wifiManager: WifiManager
) : WiseFyConnection {

    internal companion object {
        private val TAG = AbstractWiseFyConnection::class.java.simpleName
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
     * Updates
     * - 05/12/2019: Moved here from previous WiseFyConnectionImpl class
     *
     * @author Patches
     * @since 3.0
     */
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
     * Updates
     * - 05/12/2019: Moved here from previous WiseFyConnectionImpl class
     * - 01/04/2020: Formatting update
     *
     * @author Patches
     * @since 3.0
     */
    @WaitsForTimeout
    override fun waitToConnectToSSID(ssid: String?, timeoutInMillis: Int): Boolean {
        WiseFyLogger.debug(
            TAG,
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
            WiseFyLogger.debug(TAG, "Current time: %d, End time: %d (waitToConnectToSSID)", currentTime, endTime)
        } while (currentTime < endTime)
        return false
    }
}
