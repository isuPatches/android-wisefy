package com.isupatches.wisefy

import android.net.NetworkInfo
import com.isupatches.wisefy.connection.WiseFyConnection
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.`when`

/**
 * A class to mock returns from the WiseFyConnection class.
 *
 * @see WiseFyConnection
 *
 * @author Patches
 */
internal class MockWiseFyConnectionUtil internal constructor(
    private val mockWiseFyConnection: WiseFyConnection
) {

    /**
     * Mocks if the device is connected to a specific SSID.
     *
     * @param connected Whether the device is connected to the specific SSID or not
     *
     * @see WiseFyConnection.isCurrentNetworkConnectedToSSID
     */
    fun isCurrentNetworkConnectedToSSID(connected: Boolean) {
        `when`(mockWiseFyConnection.isCurrentNetworkConnectedToSSID(anyString())).thenReturn(connected)
    }

    /**
     * To mock if the device is connected to a mobile network.
     *
     * @param connected If the device is connected to a mobile network
     *
     * @see WiseFyConnection.isDeviceConnectedToMobileNetwork
     */
    fun isDeviceConnectedToMobileNetwork(connected: Boolean) {
        `when`(mockWiseFyConnection.isDeviceConnectedToMobileNetwork()).thenReturn(connected)
    }

    /**
     * To mock if the device is connected to a Wifi network.
     *
     * @param connected If the device is connected to a Wifi network
     *
     * @see WiseFyConnection.isDeviceConnectedToWifiNetwork
     */
    fun isDeviceConnectedToWifiNetwork(connected: Boolean) {
        `when`(mockWiseFyConnection.isDeviceConnectedToWifiNetwork()).thenReturn(connected)
    }

    /**
     * Mocks if the device is connected to a network.
     *
     * @param connected Whether the device is connected or not
     *
     * @see WiseFyConnection.isNetworkConnected
     */
    fun isNetworkConnected(connected: Boolean) {
        `when`(mockWiseFyConnection.isNetworkConnected()).thenReturn(connected)
    }

    /**
     * Mocks if waitToConnectToSSID is successful.
     *
     * @param success Whether waitToConnectToSSID succeeds or not
     *
     * @see WiseFyConnection.waitToConnectToSSID
     */
    fun waitToConnectToSSID(success: Boolean) {
        `when`(mockWiseFyConnection.waitToConnectToSSID(anyString(), anyInt())).thenReturn(success)
    }
}
