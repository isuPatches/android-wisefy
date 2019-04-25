package com.isupatches.wisefy.connection

import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission

import com.isupatches.wisefy.annotations.WaitsForTimeout
import com.isupatches.wisefy.constants.QUOTE
import com.isupatches.wisefy.logging.WiseFyLogger
import com.isupatches.wisefy.utils.rest

abstract class AbstractWiseFyConnection(private val wifiManager: WifiManager): WiseFyConnection {

    internal companion object {
        private val TAG = AbstractWiseFyConnection::class.java.simpleName
    }

    override fun init() {
        // No-op
    }

    override fun destroy() {
        // No-op
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
}
