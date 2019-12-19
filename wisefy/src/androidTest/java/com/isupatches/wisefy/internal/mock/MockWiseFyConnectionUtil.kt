package com.isupatches.wisefy.internal.mock

import com.isupatches.wisefy.connection.WiseFyConnection
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.`when`

/**
 * A class to mock returns from the WiseFyConnection class.
 *
 * @see WiseFyConnection
 *
 * @author Patches
 * @since 3.0
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
     *
     * @author Patches
     * @since 3.0
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
     *
     * @author Patches
     * @since 3.0
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
     *
     * @author Patches
     * @since 3.0
     */
    fun isDeviceConnectedToWifiNetwork(connected: Boolean) {
        `when`(mockWiseFyConnection.isDeviceConnectedToWifiNetwork()).thenReturn(connected)
    }

    /**
     * To mock whether the device is roaming.
     *
     * @param roaming If the device is roaming
     *
     * @see WiseFyConnection.isDeviceRoaming
     *
     * @author Patches
     * @since 4.0
     */
    fun isDeviceRoaming(roaming: Boolean) {
        `when`(mockWiseFyConnection.isDeviceRoaming()).thenReturn(roaming)
    }

    /**
     * Mocks if the device is connected to a network.
     *
     * @param connected Whether the device is connected or not
     *
     * @see WiseFyConnection.isNetworkConnected
     *
     * @author Patches
     * @since 3.0
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
     *
     * @author Patches
     * @since 3.0
     */
    fun waitToConnectToSSID(success: Boolean) {
        `when`(mockWiseFyConnection.waitToConnectToSSID(anyString(), anyInt())).thenReturn(success)
    }
}
