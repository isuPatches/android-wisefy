package com.isupatches.wisefy

import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.`when`

import android.net.NetworkInfo

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
     * Mocks if the device is connected to a network.
     *
     * @param connected Whether the device is connected or not
     *
     * @see WiseFyConnection.isNetworkConnected
     */
    fun isNetworkConnected(connected: Boolean) {
        `when`(mockWiseFyConnection.isNetworkConnected(any(NetworkInfo::class.java))).thenReturn(connected)
    }

    /**
     * To mock if the device is connected to a mobile network.
     *
     * @param connectedAndMatchesType If the device is connected to a mobile network
     *
     * @see WiseFyConnection.isNetworkConnectedAndMatchesType
     */
    fun isNetworkConnectedAndMatchesType(connectedAndMatchesType: Boolean) {
        `when`(mockWiseFyConnection.isNetworkConnectedAndMatchesType(any(NetworkInfo::class.java), anyString())).thenReturn(connectedAndMatchesType)
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
