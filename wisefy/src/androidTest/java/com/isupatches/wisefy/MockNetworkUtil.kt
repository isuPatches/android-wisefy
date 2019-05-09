package com.isupatches.wisefy

import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.wifi.ScanResult
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import com.isupatches.wisefy.WiseFy.Companion.WIFI_MANAGER_FAILURE
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.util.ArrayList

/**
 * A class that mocks lower level returns from ConnectivityManager and WifiManager.
 *
 * @see ConnectivityManager
 * @see WifiManager
 *
 * @author Patches
 */
@Suppress("LargeClass")
internal class MockNetworkUtil internal constructor(
    private val mockConnectivityManager: ConnectivityManager,
    private val mockWifiManager: WifiManager
) {

    private var expectedNearbyAccessPoint: ScanResult? = null
    private var expectedNearbyAccessPoints: MutableList<ScanResult>? = null

    private var expectedSavedNetwork: WifiConfiguration? = null
    private var expectedSavedNetworks: MutableList<WifiConfiguration>? = null

    private var expectedSSIDs: MutableList<String>? = null

    internal fun activeNetwork() {
        `when`(mockConnectivityManager.activeNetworkInfo).thenReturn(mock(NetworkInfo::class.java))
    }

    internal fun addNetwork_failure() {
        `when`(mockWifiManager.addNetwork(any(WifiConfiguration::class.java))).thenReturn(WIFI_MANAGER_FAILURE)
    }

    internal fun addNetwork_success() {
        `when`(mockWifiManager.addNetwork(any(WifiConfiguration::class.java))).thenReturn(0)
    }

    internal fun currentNetwork(ssid: String?): WifiInfo {
        val mockCurrentNetwork = mock(WifiInfo::class.java)
        `when`(mockCurrentNetwork.ssid).thenReturn(ssid)
        `when`(mockWifiManager.connectionInfo).thenReturn(mockCurrentNetwork)
        return mockCurrentNetwork
    }

    @Suppress("DEPRECATION")
    internal fun currentNetworkConnectionStatus(isAvailable: Boolean, isConnected: Boolean, type: String?) {
        val networkInfo = mock(NetworkInfo::class.java)
        `when`(networkInfo.isAvailable).thenReturn(isAvailable)
        `when`(networkInfo.isConnected).thenReturn(isConnected)
        if (type != null) {
            `when`(networkInfo.typeName).thenReturn(type)
        }
        `when`(mockConnectivityManager.activeNetworkInfo).thenReturn(networkInfo)
    }

    internal fun currentNetwork_null() {
        `when`(mockWifiManager.connectionInfo).thenReturn(null)
    }

    internal fun disableWifi_failure() {
        `when`(mockWifiManager.setWifiEnabled(false)).thenReturn(false)
    }

    internal fun disableWifi_success() {
        `when`(mockWifiManager.setWifiEnabled(false)).thenReturn(true)
    }

    internal fun disconnectFromCurrentNetwork_failure() {
        `when`(mockWifiManager.disconnect()).thenReturn(false)
    }

    internal fun disconnectFromCurrentNetwork_success() {
        `when`(mockWifiManager.disconnect()).thenReturn(true)
    }

    internal fun enableWifi_failure() {
        `when`(mockWifiManager.setWifiEnabled(true)).thenReturn(false)
    }

    internal fun enableWifi_success() {
        `when`(mockWifiManager.setWifiEnabled(true)).thenReturn(true)
    }

    internal fun getExpectedNearbyAccessPoint(): ScanResult? = expectedNearbyAccessPoint
    internal fun getExpectedNearbyAccessPoints(): List<ScanResult>? = expectedNearbyAccessPoints

    internal fun getExpectedSavedNetwork(): WifiConfiguration? = expectedSavedNetwork
    internal fun getExpectedSavedNetworks(): List<WifiConfiguration>? = expectedSavedNetworks

    internal fun getExpectedSSIDs(): List<String>? = expectedSSIDs

    @Suppress("DEPRECATION")
    internal fun isDeviceRoaming(roaming: Boolean) {
        val networkInfo = mock(NetworkInfo::class.java)
        `when`(networkInfo.isRoaming).thenReturn(roaming)
        `when`(mockConnectivityManager.activeNetworkInfo).thenReturn(networkInfo)
    }

    internal fun isWifiEnabled(wifiEnabled: Boolean) {
        `when`(mockWifiManager.isWifiEnabled).thenReturn(wifiEnabled)
    }

    internal fun networkWithFrequency(frequency: Int): WifiInfo {
        val mockWifiInfo = mock(WifiInfo::class.java)
        `when`(mockWifiInfo.frequency).thenReturn(frequency)
        `when`(mockWifiManager.connectionInfo).thenReturn(mockWifiInfo)
        return mockWifiInfo
    }

    internal fun removeNetwork(removed: Boolean) {
        `when`(mockWifiManager.removeNetwork(anyInt())).thenReturn(removed)
    }

    internal fun savedNetworks(): List<WifiConfiguration> {
        val savedNetworks = ArrayList<WifiConfiguration>()
        val wiFiConfiguration = createSavedNetwork(TEST_SSID)
        savedNetworks.add(wiFiConfiguration)

        `when`(mockWifiManager.configuredNetworks).thenReturn(savedNetworks)
        return savedNetworks
    }

    internal fun getConfiguredNetworks_null() {
        `when`(mockWifiManager.configuredNetworks).thenReturn(null)
    }

    internal fun getConfiguredNetworks_emptyList() {
        `when`(mockWifiManager.configuredNetworks).thenReturn(ArrayList())
    }

    internal fun ip_failure() {
        val wifiInfo = mock(WifiInfo::class.java)
        `when`(wifiInfo.ipAddress).thenReturn(0)
        `when`(mockWifiManager.connectionInfo).thenReturn(wifiInfo)
    }

    internal fun ip_success() {
        val wifiInfo = mock(WifiInfo::class.java)
        `when`(wifiInfo.ipAddress).thenReturn(TEST_IP_ADDRESS_INT)
        `when`(mockWifiManager.connectionInfo).thenReturn(wifiInfo)
    }

    internal fun nearbyAccessPoints_emptyList() {
        `when`(mockWifiManager.scanResults).thenReturn(ArrayList())
    }

    internal fun nearbyAccessPoints_matchingSSID() {
        val accessPoint = createMockAccessPointWithSSID(TEST_SSID)

        val accessPoints = ArrayList<ScanResult>()
        accessPoints.add(accessPoint)

        addToExpectedNearbyAccessPoints(accessPoint)
        addToExpectedSSIDs(accessPoint)

        `when`(mockWifiManager.scanResults).thenReturn(accessPoints)
    }

    internal fun nearbyAccessPoints_multipleMatchingSSIDs_accessPoint1HasHigherRSSI(takeHighest: Boolean) {
        val accessPoint1 = createMockAccessPointWithSSIDAndRSSI(TEST_SSID, TEST_RSSI_LEVEL_HIGH)
        val accessPoint2 = createMockAccessPointWithSSIDAndRSSI(TEST_SSID, TEST_RSSI_LEVEL_LOW)

        val accessPoints = ArrayList<ScanResult>()
        accessPoints.add(accessPoint1)
        accessPoints.add(accessPoint2)

        if (takeHighest) {
            addToExpectedNearbyAccessPoints(accessPoint1)
        } else {
            addToExpectedNearbyAccessPoints(accessPoint1, accessPoint2)
        }
        addToExpectedSSIDs(accessPoint1)

        `when`(mockWifiManager.scanResults).thenReturn(accessPoints)
    }

    internal fun nearbyAccessPoints_multipleMatchingSSIDs_accessPoint2HasHigherRSSI(takeHighest: Boolean) {
        val accessPoint1 = createMockAccessPointWithSSIDAndRSSI(TEST_SSID, TEST_RSSI_LEVEL_LOW)
        val accessPoint2 = createMockAccessPointWithSSIDAndRSSI(TEST_SSID, TEST_RSSI_LEVEL_HIGH)

        val accessPoints = ArrayList<ScanResult>()
        accessPoints.add(accessPoint1)
        accessPoints.add(accessPoint2)

        if (takeHighest) {
            addToExpectedNearbyAccessPoints(accessPoint2)
        } else {
            addToExpectedNearbyAccessPoints(accessPoint1, accessPoint2)
        }
        addToExpectedSSIDs(accessPoint1)

        `when`(mockWifiManager.scanResults).thenReturn(accessPoints)
    }

    internal fun nearbyAccessPoints_multipleSSIDs_sameRSSI(addSecondNetwork: Boolean) {
        val accessPoint1 = createMockAccessPointWithSSIDAndRSSI(TEST_SSID, TEST_RSSI_LEVEL)
        val accessPoint2 = createMockAccessPointWithSSIDAndRSSI(TEST_SSID2, TEST_RSSI_LEVEL)

        val accessPoints = ArrayList<ScanResult>()
        accessPoints.add(accessPoint1)
        accessPoints.add(accessPoint2)

        if (addSecondNetwork) {
            addToExpectedNearbyAccessPoints(accessPoint1, accessPoint2)
        } else {
            addToExpectedNearbyAccessPoints(accessPoint1)
        }

        if (addSecondNetwork) {
            addToExpectedSSIDs(accessPoint1, accessPoint2)
        } else {
            addToExpectedSSIDs(accessPoint1)
        }

        `when`(mockWifiManager.scanResults).thenReturn(accessPoints)
    }

    internal fun nearbyAccessPoints_multipleMatchingSSIDs_sameRSSI(addSecondNetwork: Boolean) {
        val accessPoint1 = createMockAccessPointWithSSIDAndRSSI(TEST_SSID, TEST_RSSI_LEVEL)
        val accessPoint2 = createMockAccessPointWithSSIDAndRSSI(TEST_SSID, TEST_RSSI_LEVEL)

        val accessPoints = ArrayList<ScanResult>()
        accessPoints.add(accessPoint1)
        accessPoints.add(accessPoint2)

        if (addSecondNetwork) {
            addToExpectedNearbyAccessPoints(accessPoint1, accessPoint2)
        } else {
            addToExpectedNearbyAccessPoints(accessPoint1)
        }
        addToExpectedSSIDs(accessPoint1)

        `when`(mockWifiManager.scanResults).thenReturn(accessPoints)
    }

    internal fun nearbyAccessPoints_multipleNonMatchingSSIDs() {
        val accessPoint1 = createMockAccessPointWithSSID(TEST_SSID2)
        val accessPoint2 = createMockAccessPointWithSSID(TEST_SSID3)

        val accessPoints = ArrayList<ScanResult>()
        accessPoints.add(accessPoint1)
        accessPoints.add(accessPoint2)

        `when`(mockWifiManager.scanResults).thenReturn(accessPoints)
    }

    internal fun nearbyAccessPoints_nonMatchingSSID() {
        val accessPoint = createMockAccessPointWithSSID(TEST_SSID2)

        val accessPoints = ArrayList<ScanResult>()
        accessPoints.add(accessPoint)

        `when`(mockWifiManager.scanResults).thenReturn(accessPoints)
    }

    internal fun nearbyAccessPoints_nullAccessPoint() {
        val accessPoints = emptyList<ScanResult>()
        `when`(mockWifiManager.scanResults).thenReturn(accessPoints)
    }

    internal fun nearbyAccessPoints_nullList() {
        `when`(mockWifiManager.scanResults).thenReturn(null)
    }

    internal fun nearbyAccessPoints_nullSSID() {
        val accessPoint = createMockAccessPointWithSSID(null)

        val accessPoints = ArrayList<ScanResult>()
        accessPoints.add(accessPoint)

        `when`(mockWifiManager.scanResults).thenReturn(accessPoints)
    }

    internal fun savedNetworks_emptyList() {
        `when`(mockWifiManager.configuredNetworks).thenReturn(ArrayList())
    }

    internal fun savedNetworks_matchingSSID() {
        val savedNetwork = createSavedNetwork(TEST_SSID)

        val savedNetworks = ArrayList<WifiConfiguration>()
        savedNetworks.add(savedNetwork)

        addToExpectedSavedNetworks(savedNetwork)

        `when`(mockWifiManager.configuredNetworks).thenReturn(savedNetworks)
    }

    internal fun savedNetworks_multipleMatchingSSIDs() {
        val savedNetwork1 = createSavedNetwork(TEST_SSID)
        val savedNetwork2 = createSavedNetwork(TEST_SSID)

        val savedNetworks = ArrayList<WifiConfiguration>()
        savedNetworks.add(savedNetwork1)
        savedNetworks.add(savedNetwork2)

        addToExpectedSavedNetworks(savedNetwork1, savedNetwork2)

        `when`(mockWifiManager.configuredNetworks).thenReturn(savedNetworks)
    }

    internal fun savedNetworks_multipleNonMatchingSSIDs() {
        val savedNetwork1 = createSavedNetwork(TEST_SSID2)
        val savedNetwork2 = createSavedNetwork(TEST_SSID3)

        val savedNetworks = ArrayList<WifiConfiguration>()
        savedNetworks.add(savedNetwork1)
        savedNetworks.add(savedNetwork2)

        `when`(mockWifiManager.configuredNetworks).thenReturn(savedNetworks)
    }

    internal fun savedNetworks_multipleSSIDs(addSecondNetwork: Boolean) {
        val savedNetwork1 = createSavedNetwork(TEST_SSID)
        val savedNetwork2 = createSavedNetwork(TEST_SSID2)

        val savedNetworks = ArrayList<WifiConfiguration>()
        savedNetworks.add(savedNetwork1)
        savedNetworks.add(savedNetwork2)

        if (addSecondNetwork) {
            addToExpectedSavedNetworks(savedNetwork1, savedNetwork2)
        } else {
            addToExpectedSavedNetworks(savedNetwork1)
        }

        `when`(mockWifiManager.configuredNetworks).thenReturn(savedNetworks)
    }

    internal fun savedNetworks_nonMatchingSSID() {
        val savedNetwork = createSavedNetwork(TEST_SSID2)

        val savedNetworks = ArrayList<WifiConfiguration>()
        savedNetworks.add(savedNetwork)

        `when`(mockWifiManager.configuredNetworks).thenReturn(savedNetworks)
    }

    internal fun savedNetworks_nullList() {
        `when`(mockWifiManager.configuredNetworks).thenReturn(null)
    }

    internal fun savedNetworks_nullSavedNetwork() {
        val wifiList = emptyList<WifiConfiguration>()
        `when`(mockWifiManager.configuredNetworks).thenReturn(wifiList)
    }

    internal fun savedNetworks_nullSSID() {
        val savedNetwork = createSavedNetwork(null)

        val savedNetworks = ArrayList<WifiConfiguration>()
        savedNetworks.add(savedNetwork)

        `when`(mockWifiManager.configuredNetworks).thenReturn(savedNetworks)
    }

    /*
     * HELPERS
     */

    private fun addToExpectedNearbyAccessPoints(accessPoint: ScanResult) {
        expectedNearbyAccessPoint = accessPoint

        expectedNearbyAccessPoints = ArrayList()
        expectedNearbyAccessPoints!!.add(accessPoint)
    }

    private fun addToExpectedNearbyAccessPoints(accessPoint1: ScanResult, accessPoint2: ScanResult) {
        expectedNearbyAccessPoint = accessPoint1

        expectedNearbyAccessPoints = ArrayList()
        expectedNearbyAccessPoints!!.add(accessPoint1)
        expectedNearbyAccessPoints!!.add(accessPoint2)
    }

    private fun addToExpectedSavedNetworks(savedNetwork: WifiConfiguration) {
        expectedSavedNetwork = savedNetwork

        expectedSavedNetworks = ArrayList()
        expectedSavedNetworks!!.add(savedNetwork)
    }

    private fun addToExpectedSavedNetworks(savedNetwork1: WifiConfiguration, savedNetwork2: WifiConfiguration) {
        expectedSavedNetwork = savedNetwork1

        expectedSavedNetworks = ArrayList()
        expectedSavedNetworks!!.add(savedNetwork1)
        expectedSavedNetworks!!.add(savedNetwork2)
    }

    private fun addToExpectedSSIDs(accessPoint: ScanResult) {
        expectedSSIDs = ArrayList()
        expectedSSIDs!!.add(accessPoint.SSID)
    }

    private fun addToExpectedSSIDs(accessPoint1: ScanResult, accessPoint2: ScanResult) {
        expectedSSIDs = ArrayList()
        expectedSSIDs!!.add(accessPoint1.SSID)
        expectedSSIDs!!.add(accessPoint2.SSID)
    }
}
