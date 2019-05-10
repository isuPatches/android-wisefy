package com.isupatches.wisefy.connection

import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build

import com.isupatches.wisefy.BaseAndroidJUnit4TestClass
import com.isupatches.wisefy.TEST_SSID
import com.isupatches.wisefy.TEST_SSID2
import com.isupatches.wisefy.TEST_TIMEOUT
import com.isupatches.wisefy.getNetworkCapabilities

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Assume.assumeTrue
import org.junit.Before
import org.junit.Test

import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

internal class WisefyConnectionSDK23Tests : BaseAndroidJUnit4TestClass() {

    private lateinit var wisefyConnection: WiseFyConnection

    @Before fun setUp() {
        wisefyConnection = WiseFyConnectionSDK23.create(mockConnectivityManager, mockWifiManager)
        assumeTrue(
        "Can only run on API Level 23 or newer",
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
        )
    }

    /*
     * isCurrentNetworkConnectedToSSID tests
     */

    @Test fun isCurrentNetworkConnectedToSSID_failure_nullSSIDParam() {
        withAvailableNetwork()
        assertFalse(wisefyConnection.isCurrentNetworkConnectedToSSID(null))
    }

    @Test fun isCurrentNetworkConnectedToSSID_failure_nullConnectionInfo() {
        withAvailableNetwork()
        mockNetworkUtil.currentNetwork_null()
        assertFalse(wisefyConnection.isCurrentNetworkConnectedToSSID(TEST_SSID))
    }

    @Test fun isCurrentNetworkConnectedToSSID_failure_nullSSID() {
        withAvailableNetwork()
        mockNetworkUtil.currentNetwork(null)
        assertFalse(wisefyConnection.isCurrentNetworkConnectedToSSID(TEST_SSID))
    }

    @Test fun isCurrentNetworkConnectedToSSID_failure_differentSSID() {
        withAvailableNetwork()
        mockNetworkUtil.currentNetwork(TEST_SSID2)
        assertFalse(wisefyConnection.isCurrentNetworkConnectedToSSID(TEST_SSID))
    }

    @Test fun isCurrentNetworkConnectedToSSID_failure_unavailable() {
        mockNetworkUtil.currentNetwork(TEST_SSID)
        withUnavailableNetwork()
        assertFalse(wisefyConnection.isCurrentNetworkConnectedToSSID(TEST_SSID))
    }

    @Test fun isCurrentNetworkConnectedToSSID_failure_lost() {
        mockNetworkUtil.currentNetwork(TEST_SSID)
        withLostNetwork()
        assertFalse(wisefyConnection.isCurrentNetworkConnectedToSSID(TEST_SSID))
    }

    @Test fun isCurrentNetworkConnectedToSSID_failure_losing() {
        mockNetworkUtil.currentNetwork(TEST_SSID)
        withLosingNetwork()
        assertFalse(wisefyConnection.isCurrentNetworkConnectedToSSID(TEST_SSID))
    }

    @Test fun isCurrentNetworkConnectedToSSID_success() {
        mockNetworkUtil.currentNetwork(TEST_SSID)
        withAvailableNetwork()
        assertTrue(wisefyConnection.isCurrentNetworkConnectedToSSID(TEST_SSID))
    }

    /*
     * isNetworkConnected tests
     */

    @Test fun isNetworkConnected_failure_unavailable() {
        withUnavailableNetwork()
        assertFalse(wisefyConnection.isNetworkConnected())
    }

    @Test fun isNetworkConnected_failure_lost() {
        withLostNetwork()
        assertFalse(wisefyConnection.isNetworkConnected())
    }

    @Test fun isNetworkConnected_failure_losing() {
        withLosingNetwork()
        assertFalse(wisefyConnection.isNetworkConnected())
    }

    @Test fun isNetworkConnected_success() {
        withAvailableNetwork()
        assertTrue(wisefyConnection.isNetworkConnected())
    }

    /*
     * isDeviceConnectedToMobileNetwork tests
     */

    @Test fun isDeviceConnectedToMobileNetwork_failure_noActiveNetwork() {
        assertFalse(wisefyConnection.isDeviceConnectedToMobileNetwork())
    }

    @Test fun isDeviceConnectedToMobileNetwork_failure_noCapabilities() {
        withAvailableNetwork()
        assertFalse(wisefyConnection.isDeviceConnectedToMobileNetwork())
    }

    @Test fun isDeviceConnectedToMobileNetwork_failure_differentTransport() {
        withAvailableNetwork()
        val networkCapabilities = getNetworkCapabilities(
            NetworkCapabilities.TRANSPORT_WIFI,
            arrayOf(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        )
        setUpNetworkCapabilities(networkCapabilities)
        assertFalse(wisefyConnection.isDeviceConnectedToMobileNetwork())
    }

    @Test fun isDeviceConnectedToMobileNetwork_failure_noInternetCapability() {
        withAvailableNetwork()
        val networkCapabilities = getNetworkCapabilities(
            NetworkCapabilities.TRANSPORT_CELLULAR
        )
        setUpNetworkCapabilities(networkCapabilities)
        assertFalse(wisefyConnection.isDeviceConnectedToMobileNetwork())
    }

    @Test fun isDeviceConnectedToMobileNetwork_failure_unavailable() {
        withUnavailableNetwork()
        val networkCapabilities = getNetworkCapabilities(
            NetworkCapabilities.TRANSPORT_CELLULAR,
            arrayOf(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        )
        setUpNetworkCapabilities(networkCapabilities)
        assertFalse(wisefyConnection.isDeviceConnectedToMobileNetwork())
    }

    @Test fun isDeviceConnectedToMobileNetwork_failure_lost() {
        withLostNetwork()
        val networkCapabilities = getNetworkCapabilities(
            NetworkCapabilities.TRANSPORT_CELLULAR,
            arrayOf(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        )
        setUpNetworkCapabilities(networkCapabilities)
        assertFalse(wisefyConnection.isDeviceConnectedToMobileNetwork())
    }

    @Test fun isDeviceConnectedToMobileNetwork_failure_losing() {
        withLosingNetwork()
        val networkCapabilities = getNetworkCapabilities(
            NetworkCapabilities.TRANSPORT_CELLULAR,
            arrayOf(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        )
        setUpNetworkCapabilities(networkCapabilities)
        assertFalse(wisefyConnection.isDeviceConnectedToMobileNetwork())
    }

    @Test fun isDeviceConnectedToMobileNetwork_success() {
        withAvailableNetwork()
        val networkCapabilities = getNetworkCapabilities(
            NetworkCapabilities.TRANSPORT_CELLULAR,
            arrayOf(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        )
        setUpNetworkCapabilities(networkCapabilities)
        assertTrue(wisefyConnection.isDeviceConnectedToMobileNetwork())
    }

    /*
     * isDeviceConnectedToWifiNetwork tests
     */

    @Test fun isDeviceConnectedToWifiNetwork_failure_noActiveNetwork() {
        assertFalse(wisefyConnection.isDeviceConnectedToWifiNetwork())
    }

    @Test fun isDeviceConnectedToWifiNetwork_failure_noCapabilities() {
        withAvailableNetwork()
        assertFalse(wisefyConnection.isDeviceConnectedToWifiNetwork())
    }

    @Test fun isDeviceConnectedToWifiNetwork_failure_differentTransport() {
        withAvailableNetwork()
        val networkCapabilities = getNetworkCapabilities(
            NetworkCapabilities.TRANSPORT_CELLULAR,
            arrayOf(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        )
        setUpNetworkCapabilities(networkCapabilities)
        assertFalse(wisefyConnection.isDeviceConnectedToWifiNetwork())
    }

    @Test fun isDeviceConnectedToWifiNetwork_failure_noInternetCapability() {
        withAvailableNetwork()
        val networkCapabilities = getNetworkCapabilities(
            NetworkCapabilities.TRANSPORT_WIFI
        )
        setUpNetworkCapabilities(networkCapabilities)
        assertFalse(wisefyConnection.isDeviceConnectedToWifiNetwork())
    }

    @Test fun isDeviceConnectedToWifiNetwork_failure_unavailable() {
        withUnavailableNetwork()
        val networkCapabilities = getNetworkCapabilities(
            NetworkCapabilities.TRANSPORT_WIFI,
            arrayOf(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        )
        setUpNetworkCapabilities(networkCapabilities)
        assertFalse(wisefyConnection.isDeviceConnectedToWifiNetwork())
    }

    @Test fun isDeviceConnectedToWifiNetwork_failure_lost() {
        withLostNetwork()
        val networkCapabilities = getNetworkCapabilities(
            NetworkCapabilities.TRANSPORT_WIFI,
            arrayOf(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        )
        setUpNetworkCapabilities(networkCapabilities)
        assertFalse(wisefyConnection.isDeviceConnectedToWifiNetwork())
    }

    @Test fun isDeviceConnectedToWifiNetwork_failure_losing() {
        withLosingNetwork()
        val networkCapabilities = getNetworkCapabilities(
            NetworkCapabilities.TRANSPORT_WIFI,
            arrayOf(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        )
        setUpNetworkCapabilities(networkCapabilities)
        assertFalse(wisefyConnection.isDeviceConnectedToWifiNetwork())
    }

    @Test fun isDeviceConnectedToWifiNetwork_success() {
        withAvailableNetwork()
        val networkCapabilities = getNetworkCapabilities(
            NetworkCapabilities.TRANSPORT_WIFI,
            arrayOf(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        )
        setUpNetworkCapabilities(networkCapabilities)
        assertTrue(wisefyConnection.isDeviceConnectedToWifiNetwork())
    }

    /*
     * isDeviceRoaming tests
     */

    @Test fun isDeviceRoaming_failure_nullNetworkInfo() {
        assumeTrue(
                "Can only run on API's before Level 28",
                Build.VERSION.SDK_INT < Build.VERSION_CODES.P
        )
        assertFalse(wisefyConnection.isDeviceRoaming())
    }

    @Test fun isDeviceRoaming_failure() {
        assumeTrue(
                "Can only run on API's before Level 28",
                Build.VERSION.SDK_INT < Build.VERSION_CODES.P
        )
        mockNetworkUtil.currentNetwork(TEST_SSID)
        mockNetworkUtil.isDeviceRoaming(false)
        assertFalse(wisefyConnection.isDeviceRoaming())
    }

    @Test fun isDeviceRoaming_success() {
        assumeTrue(
                "Can only run on API's before Level 28",
                Build.VERSION.SDK_INT < Build.VERSION_CODES.P
        )
        mockNetworkUtil.currentNetwork(TEST_SSID)
        mockNetworkUtil.isDeviceRoaming(true)
        assertTrue(wisefyConnection.isDeviceRoaming())
    }

    @Test fun isDeviceRoaming_sdk28_failure_nullNetwork() {
        assumeTrue(
        "Can only run on API Level 28 or newer",
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.P
        )
        mockNetworkUtil.isDeviceRoaming(false)
        assertTrue(wisefyConnection.isDeviceRoaming())
    }

    @Test fun isDeviceRoaming_sdk28_failure_missingNotRoamingCapability() {
        assumeTrue(
        "Can only run on API Level 28 or newer",
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.P
        )
        val networkCapabilities = NetworkCapabilities(null)
        setUpNetworkCapabilities(networkCapabilities)
        mockNetworkUtil.isDeviceRoaming(false)
        assertTrue(wisefyConnection.isDeviceRoaming())
    }

    @Test fun isDeviceRoaming_sdk28_success() {
        assumeTrue(
            "Can only run on API Level 28 or newer",
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.P
        )
        val networkCapabilities = getNetworkCapabilities(arrayOf(NetworkCapabilities.NET_CAPABILITY_NOT_ROAMING))
        setUpNetworkCapabilities(networkCapabilities)
        mockNetworkUtil.isDeviceRoaming(true)
        assertFalse(wisefyConnection.isDeviceRoaming())
    }

    /*
     * waitToConnectToSSID tests
     */

    @Test fun waitToConnectToSSID_failure_nullSSIDParam() {
        withAvailableNetwork()
        assertFalse(wisefyConnection.waitToConnectToSSID(null, TEST_TIMEOUT))
    }

    @Test fun waitToConnectToSSID_failure_nullConnectionInfo() {
        withAvailableNetwork()
        mockNetworkUtil.currentNetwork_null()
        assertFalse(wisefyConnection.waitToConnectToSSID(TEST_SSID, TEST_TIMEOUT))
    }

    @Test fun waitToConnectToSSID_failure_nullSSID() {
        withAvailableNetwork()
        mockNetworkUtil.currentNetwork(null)
        assertFalse(wisefyConnection.waitToConnectToSSID(null, TEST_TIMEOUT))
    }

    @Test fun waitToConnectToSSID_failure_differentSSID() {
        withAvailableNetwork()
        mockNetworkUtil.currentNetwork(TEST_SSID2)
        assertFalse(wisefyConnection.waitToConnectToSSID(TEST_SSID, TEST_TIMEOUT))
    }

    @Test fun waitToConnectToSSID_failure_unavailable() {
        withUnavailableNetwork()
        mockNetworkUtil.currentNetwork(TEST_SSID)
        assertFalse(wisefyConnection.waitToConnectToSSID(TEST_SSID, TEST_TIMEOUT))
    }

    @Test fun waitToConnectToSSID_failure_losing() {
        withLosingNetwork()
        mockNetworkUtil.currentNetwork(TEST_SSID)
        assertFalse(wisefyConnection.waitToConnectToSSID(TEST_SSID, TEST_TIMEOUT))
    }

    @Test fun waitToConnectToSSID_failure_lost() {
        withLostNetwork()
        mockNetworkUtil.currentNetwork(TEST_SSID)
        assertFalse(wisefyConnection.waitToConnectToSSID(TEST_SSID, TEST_TIMEOUT))
    }

    @Test fun waitToConnectToSSID_success() {
        withAvailableNetwork()
        mockNetworkUtil.currentNetwork(TEST_SSID)
        assertTrue(wisefyConnection.waitToConnectToSSID(TEST_SSID, TEST_TIMEOUT))
    }

    /*
     *
     */

    private fun setUpNetworkCapabilities(networkCapabilities: NetworkCapabilities) {
        val network = mock(Network::class.java)
        `when`(mockConnectivityManager.activeNetwork).thenReturn(network)
        `when`(mockConnectivityManager.getNetworkCapabilities(network)).thenReturn(networkCapabilities)
        (wisefyConnection as WiseFyConnectionSDK23).networkChangeCallback.onCapabilitiesChanged(
            network,
            networkCapabilities
        )
    }

    /*
     * Connection status helpers
     */

    private fun withAvailableNetwork() {
        (wisefyConnection as WiseFyConnectionSDK23).networkChangeCallback.onAvailable(
            mock(Network::class.java)
        )
    }

    private fun withLosingNetwork() {
        (wisefyConnection as WiseFyConnectionSDK23).networkChangeCallback.onLosing(
            mock(Network::class.java),
            1
        )
    }

    private fun withLostNetwork() {
        (wisefyConnection as WiseFyConnectionSDK23).networkChangeCallback.onLost(
            mock(Network::class.java)
        )
    }

    private fun withUnavailableNetwork() {
        (wisefyConnection as WiseFyConnectionSDK23).networkChangeCallback.onUnavailable()
    }
}
