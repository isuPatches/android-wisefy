package com.isupatches.wisefy.internal.mock

import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.wifi.ScanResult
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import com.isupatches.wisefy.TEST_IP_ADDRESS_INT
import com.isupatches.wisefy.TEST_RSSI_LEVEL
import com.isupatches.wisefy.TEST_RSSI_LEVEL_HIGH
import com.isupatches.wisefy.TEST_RSSI_LEVEL_LOW
import com.isupatches.wisefy.TEST_SSID
import com.isupatches.wisefy.TEST_SSID2
import com.isupatches.wisefy.TEST_SSID3
import com.isupatches.wisefy.WiseFy.Companion.WIFI_MANAGER_FAILURE
import com.isupatches.wisefy.internal.createMockAccessPointWithSSID
import com.isupatches.wisefy.internal.createMockAccessPointWithSSIDAndRSSI
import com.isupatches.wisefy.internal.createSavedNetwork
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

/**
 * A class that mocks lower level returns from ConnectivityManager and WifiManager.
 *
 * @see ConnectivityManager
 * @see WifiManager
 *
 * @author Patches
 * @since 3.0
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

    /**
     * To mock a device having active network info.
     *
     * @see ConnectivityManager.getActiveNetworkInfo
     *
     * @author Patches
     * @since 3.0
     */
    internal fun activeNetwork() {
        `when`(mockConnectivityManager.activeNetworkInfo).thenReturn(mock(NetworkInfo::class.java))
    }

    /**
     * To mock a failure adding a network.
     *
     * @see WifiManager.addNetwork
     *
     * @author Patches
     * @since 3.0
     */
    internal fun addNetwork_failure() {
        `when`(mockWifiManager.addNetwork(any(WifiConfiguration::class.java))).thenReturn(WIFI_MANAGER_FAILURE)
    }

    /**
     * To mock a success adding a network.
     *
     * @see WifiManager.addNetwork
     *
     * @author Patches
     * @since 3.0
     */
    internal fun addNetwork_success() {
        `when`(mockWifiManager.addNetwork(any(WifiConfiguration::class.java))).thenReturn(0)
    }

    /**
     * To mock a device having an active network.
     *
     * @param ssid The SSID for the mocked network
     *
     * @return WifiInfo - The mock active network
     *
     * @see WifiInfo
     * @see WifiManager.getConnectionInfo
     *
     * @author Patches
     * @since 3.0
     */
    internal fun currentNetwork(ssid: String?): WifiInfo {
        val mockCurrentNetwork = mock(WifiInfo::class.java)
        `when`(mockCurrentNetwork.ssid).thenReturn(ssid)
        `when`(mockWifiManager.connectionInfo).thenReturn(mockCurrentNetwork)
        return mockCurrentNetwork
    }

    /**
     * To mock a current connection status.
     *
     * @param isAvailable Whether the network is available or not
     * @param isConnected Whether the network is connected or not
     * @param type The network type
     *
     * @see NetworkInfo.isAvailable
     * @see NetworkInfo.isConnected
     * @see NetworkInfo.getTypeName
     * @see ConnectivityManager.getActiveNetworkInfo
     *
     * @author Patches
     * @since 3.0
     */
    @Suppress("deprecation")
    internal fun currentNetworkConnectionStatus(isAvailable: Boolean, isConnected: Boolean, type: String?) {
        val networkInfo = mock(NetworkInfo::class.java)
        `when`(networkInfo.isAvailable).thenReturn(isAvailable)
        `when`(networkInfo.isConnected).thenReturn(isConnected)
        if (type != null) {
            `when`(networkInfo.typeName).thenReturn(type)
        }
        `when`(mockConnectivityManager.activeNetworkInfo).thenReturn(networkInfo)
    }

    /**
     * To mock no current network.
     *
     * @see WifiManager.getConnectionInfo
     *
     * @author Patches
     * @since 4.0
     */
    internal fun currentNetwork_null() {
        `when`(mockWifiManager.connectionInfo).thenReturn(null)
    }

    /**
     * To mock a failure disabling Wifi.
     *
     * @see WifiManager.setWifiEnabled
     *
     * @author Patches
     * @since 3.0
     */
    internal fun disableWifi_failure() {
        `when`(mockWifiManager.setWifiEnabled(false)).thenReturn(false)
    }

    /**
     * To mock a success disabling Wifi.
     *
     * @see WifiManager.setWifiEnabled
     *
     * @author Patches
     * @since 3.0
     */
    internal fun disableWifi_success() {
        `when`(mockWifiManager.setWifiEnabled(false)).thenReturn(true)
    }

    /**
     * To mock a failure disconnecting from current network.
     *
     * @see WifiManager.disconnect
     *
     * @author Patches
     * @since 3.0
     */
    internal fun disconnectFromCurrentNetwork_failure() {
        `when`(mockWifiManager.disconnect()).thenReturn(false)
    }

    /**
     * To mock a success disconnecting from current network.
     *
     * @see WifiManager.disconnect
     *
     * @author Patches
     * @since 3.0
     */
    internal fun disconnectFromCurrentNetwork_success() {
        `when`(mockWifiManager.disconnect()).thenReturn(true)
    }

    /**
     * To mock a failure enabling Wifi.
     *
     * @see WifiManager.setWifiEnabled
     *
     * @author Patches
     * @since 3.0
     */
    internal fun enableWifi_failure() {
        `when`(mockWifiManager.setWifiEnabled(true)).thenReturn(false)
    }

    /**
     * To mock a success enabling Wifi.
     *
     * @see WifiManager.setWifiEnabled
     *
     * @author Patches
     * @since 3.0
     */
    internal fun enableWifi_success() {
        `when`(mockWifiManager.setWifiEnabled(true)).thenReturn(true)
    }

    /**
     * To return the expected access point for testing.
     *
     * @return List of ScanResult - The expected access point for test
     *
     * @see ScanResult
     *
     * @author Patches
     * @since 3.0
     */
    internal fun getExpectedNearbyAccessPoint(): ScanResult? = expectedNearbyAccessPoint

    /**
     * To return the list of expected access points for testing.
     *
     * @return List of ScanResult - The expected access points for test
     *
     * @see ScanResult
     *
     * @author Patches
     * @since 3.0
     */
    internal fun getExpectedNearbyAccessPoints(): List<ScanResult>? = expectedNearbyAccessPoints

    /**
     * To return the expected saved network for testing.
     *
     * @return List of Strings - The expected saved network for test
     *
     * @see WifiConfiguration
     *
     * @author Patches
     * @since 3.0
     */
    internal fun getExpectedSavedNetwork(): WifiConfiguration? = expectedSavedNetwork

    /**
     * To return the expected list of saved networks for testing.
     *
     * @return List of WifiConfiguration - The expected saved networks for test
     *
     * @see WifiConfiguration
     *
     * @author Patches
     * @since 3.0
     */
    internal fun getExpectedSavedNetworks(): List<WifiConfiguration>? = expectedSavedNetworks

    /**
     * To return the expected list of SSIDs for testing.
     *
     * @return List of Strings - The expected SSIDS for test
     *
     * @author Patches
     * @since 3.0
     */
    internal fun getExpectedSSIDs(): List<String>? = expectedSSIDs

    /**
     * To mock if a device is roaming.
     *
     * @param roaming Whether the device is roaming or not
     *
     * @see NetworkInfo.isRoaming
     * @see ConnectivityManager.getActiveNetworkInfo
     *
     * @author Patches
     * @since 3.0
     */
    @Suppress("deprecation")
    internal fun isDeviceRoaming(roaming: Boolean) {
        val networkInfo = mock(NetworkInfo::class.java)
        `when`(networkInfo.isRoaming).thenReturn(roaming)
        `when`(mockConnectivityManager.activeNetworkInfo).thenReturn(networkInfo)
    }

    /**
     * To mock if Wifi is enabled or not.
     *
     * @param wifiEnabled Whether the device has Wifi enabled or not
     *
     * @see WifiManager.isWifiEnabled
     *
     * @author Patches
     * @since 3.0
     */
    internal fun isWifiEnabled(wifiEnabled: Boolean) {
        `when`(mockWifiManager.isWifiEnabled).thenReturn(wifiEnabled)
    }

    /**
     * To mock a network with a given frequency.
     *
     * @param frequency The frequency for the network
     *
     * @see WifiInfo.getFrequency
     * @see WifiManager.getConnectionInfo
     *
     * @author Patches
     * @since 3.0
     */
    internal fun networkWithFrequency(frequency: Int): WifiInfo {
        val mockWifiInfo = mock(WifiInfo::class.java)
        `when`(mockWifiInfo.frequency).thenReturn(frequency)
        `when`(mockWifiManager.connectionInfo).thenReturn(mockWifiInfo)
        return mockWifiInfo
    }

    /**
     * To mock a failure getting the IP of a device.
     *
     * @see WifiInfo.getIpAddress
     * @see WifiManager.getConnectionInfo
     *
     * @author Patches
     * @since 3.0
     */
    internal fun ip_failure() {
        val wifiInfo = mock(WifiInfo::class.java)
        `when`(wifiInfo.ipAddress).thenReturn(0)
        `when`(mockWifiManager.connectionInfo).thenReturn(wifiInfo)
    }

    /**
     * To mock a success getting the IP of a device.
     *
     * @see WifiInfo.getIpAddress
     * @see WifiManager.getConnectionInfo
     *
     * @author Patches
     * @since 3.0
     */
    internal fun ip_success() {
        val wifiInfo = mock(WifiInfo::class.java)
        `when`(wifiInfo.ipAddress).thenReturn(TEST_IP_ADDRESS_INT)
        `when`(mockWifiManager.connectionInfo).thenReturn(wifiInfo)
    }

    /**
     * To mock an empty list of access points.
     *
     * @see WifiManager.getScanResults
     *
     * @author Patches
     * @since 3.0
     */
    internal fun nearbyAccessPoints_emptyList() {
        `when`(mockWifiManager.scanResults).thenReturn(ArrayList())
    }

    /**
     * To mock a list of access points with one that has a matching SSID.
     *
     * @see addToExpectedNearbyAccessPoints
     * @see addToExpectedSSIDs
     * @see ScanResult
     * @see WifiManager.getScanResults
     *
     * @author Patches
     * @since 3.0
     */
    internal fun nearbyAccessPoints_matchingSSID() {
        val accessPoint = createMockAccessPointWithSSID(TEST_SSID)

        val accessPoints = ArrayList<ScanResult>()
        accessPoints.add(accessPoint)

        addToExpectedNearbyAccessPoints(accessPoint)
        addToExpectedSSIDs(accessPoint)

        `when`(mockWifiManager.scanResults).thenReturn(accessPoints)
    }

    /**
     * To mock a list of access points with multiple access points that have the same SSID.
     * Access point 1 will have the higher RSSI level.
     *
     * @param takeHighest If the search is going to take the access point with the highest RSSI
     *
     * @see createMockAccessPointWithSSIDAndRSSI
     * @see ScanResult
     * @see addToExpectedNearbyAccessPoints
     * @see addToExpectedSSIDs
     * @see WifiManager.getScanResults
     *
     * @author Patches
     * @since 3.0
     */
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

    /**
     * To mock a list of access points with multiple access points that have the same SSID.
     * Access point 2 will have the higher RSSI level.
     *
     * @param takeHighest If the search is going to take the access point with the highest RSSI
     *
     * @see createMockAccessPointWithSSIDAndRSSI
     * @see ScanResult
     * @see addToExpectedNearbyAccessPoints
     * @see addToExpectedSSIDs
     * @see WifiManager.getScanResults
     *
     * @author Patches
     * @since 3.0
     */
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

    /**
     * To mock a list of access points with one access point that has a matching SSID and
     * another one that does not a matching SSID.  Both will that have the same RSSI.
     *
     * @param addSecondNetwork If the second network should be added to the expected network
     * list for testing
     *
     * @see createMockAccessPointWithSSIDAndRSSI
     * @see ScanResult
     * @see addToExpectedNearbyAccessPoints
     * @see addToExpectedSSIDs
     * @see WifiManager.getScanResults
     *
     * @author Patches
     * @since 3.0
     */
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

    /**
     * To mock a list of access points with multiple access points that have the same SSID and
     * the same RSSI level.
     *
     * @param addSecondNetwork If the second network should be added to the expected network
     * list for testing
     *
     * @see createMockAccessPointWithSSIDAndRSSI
     * @see ScanResult
     * @see addToExpectedNearbyAccessPoints
     * @see addToExpectedSSIDs
     * @see WifiManager.getScanResults
     *
     * @author Patches
     * @since 3.0
     */
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

    /**
     * To mock a list of access points with multiple non-matching SSIDs.
     *
     * @see createMockAccessPointWithSSID
     * @see ScanResult
     * @see WifiManager.getScanResults
     *
     * @author Patches
     * @since 3.0
     */
    internal fun nearbyAccessPoints_multipleNonMatchingSSIDs() {
        val accessPoint1 = createMockAccessPointWithSSID(TEST_SSID2)
        val accessPoint2 = createMockAccessPointWithSSID(TEST_SSID3)

        val accessPoints = ArrayList<ScanResult>()
        accessPoints.add(accessPoint1)
        accessPoints.add(accessPoint2)

        `when`(mockWifiManager.scanResults).thenReturn(accessPoints)
    }

    /**
     * To mock a list of access points with a non-matching SSID.
     *
     * @see createMockAccessPointWithSSID
     * @see ScanResult
     * @see WifiManager.getScanResults
     *
     * @author Patches
     * @since 3.0
     */
    internal fun nearbyAccessPoints_nonMatchingSSID() {
        val accessPoint = createMockAccessPointWithSSID(TEST_SSID2)

        val accessPoints = ArrayList<ScanResult>()
        accessPoints.add(accessPoint)

        `when`(mockWifiManager.scanResults).thenReturn(accessPoints)
    }

    /**
     * To mock a list of access points with a null configuration.
     *
     * @see ScanResult
     * @see WifiManager.getScanResults
     *
     * @author Patches
     * @since 3.0
     */
    internal fun nearbyAccessPoints_nullAccessPoint() {
        val accessPoints = arrayListOf<ScanResult?>(null)
        `when`(mockWifiManager.scanResults).thenReturn(accessPoints)
    }

    /**
     * To mock a null list of access points.
     *
     * @see WifiManager.getScanResults
     *
     * @author Patches
     * @since 3.0
     */
    internal fun nearbyAccessPoints_nullList() {
        `when`(mockWifiManager.scanResults).thenReturn(null)
    }

    /**
     * To mock a list of access points with a configuration that has a null SSID.
     *
     * @see createMockAccessPointWithSSID
     * @see ScanResult
     * @see WifiManager.getScanResults
     *
     * @author Patches
     * @since 3.0
     */
    internal fun nearbyAccessPoints_nullSSID() {
        val accessPoint = createMockAccessPointWithSSID(null)

        val accessPoints = ArrayList<ScanResult>()
        accessPoints.add(accessPoint)

        `when`(mockWifiManager.scanResults).thenReturn(accessPoints)
    }

    /**
     * To mock if removing a network is successful or not.
     *
     * @param removed Whether or not the network was successfully removed
     *
     * @see WifiManager.removeNetwork
     *
     * @author Patches
     * @since 3.0
     */
    internal fun removeNetwork(removed: Boolean) {
        `when`(mockWifiManager.removeNetwork(anyInt())).thenReturn(removed)
    }

    /**
     * To mock a list of saved networks.
     *
     * @return The list of saved networks for testing
     *
     * @see createSavedNetwork
     * @see WifiConfiguration
     * @see WifiManager.getConfiguredNetworks
     *
     * @author Patches
     * @since 3.0
     */
    internal fun savedNetworks(): List<WifiConfiguration> {
        val savedNetworks = ArrayList<WifiConfiguration>()
        val wiFiConfiguration = createSavedNetwork(TEST_SSID)
        savedNetworks.add(wiFiConfiguration)

        `when`(mockWifiManager.configuredNetworks).thenReturn(savedNetworks)
        return savedNetworks
    }

    /**
     * To mock an empty list of saved networks.
     *
     * @see WifiManager.getConfiguredNetworks
     *
     * @author Patches
     * @since 3.0
     */
    internal fun savedNetworks_emptyList() {
        `when`(mockWifiManager.configuredNetworks).thenReturn(ArrayList())
    }

    /**
     * To mock a list of saved networks with a null configuration.
     *
     * @see WifiManager.getConfiguredNetworks
     *
     * @author Patches
     * @since 3.0
     */
    internal fun savedNetworks_listWithNull() {
        val savedNetworks = arrayListOf<WifiConfiguration?>(null)
        `when`(mockWifiManager.configuredNetworks).thenReturn(savedNetworks)
    }

    /**
     * To mock a list of saved networks with a configuration that has a matching SSID.
     *
     * @see addToExpectedSavedNetworks
     * @see createSavedNetwork
     * @see WifiConfiguration
     * @see WifiManager.getConfiguredNetworks
     *
     * @author Patches
     * @since 3.0
     */
    internal fun savedNetworks_matchingSSID() {
        val savedNetwork = createSavedNetwork(TEST_SSID)

        val savedNetworks = ArrayList<WifiConfiguration>()
        savedNetworks.add(savedNetwork)

        addToExpectedSavedNetworks(savedNetwork)

        `when`(mockWifiManager.configuredNetworks).thenReturn(savedNetworks)
    }

    /**
     * To mock a list of saved networks with multiple configurations that have a matching SSID.
     *
     * @see addToExpectedSavedNetworks
     * @see createSavedNetwork
     * @see WifiConfiguration
     * @see WifiManager.getConfiguredNetworks
     *
     * @author Patches
     * @since 3.0
     */
    internal fun savedNetworks_multipleMatchingSSIDs() {
        val savedNetwork1 = createSavedNetwork(TEST_SSID)
        val savedNetwork2 = createSavedNetwork(TEST_SSID)

        val savedNetworks = ArrayList<WifiConfiguration>()
        savedNetworks.add(savedNetwork1)
        savedNetworks.add(savedNetwork2)

        addToExpectedSavedNetworks(savedNetwork1, savedNetwork2)

        `when`(mockWifiManager.configuredNetworks).thenReturn(savedNetworks)
    }

    /**
     * To mock a list of saved networks with multiple configurations that have non-matching SSIDs.
     *
     * @see createSavedNetwork
     * @see WifiConfiguration
     * @see WifiManager.getConfiguredNetworks
     *
     * @author Patches
     * @since 3.0
     */
    internal fun savedNetworks_multipleNonMatchingSSIDs() {
        val savedNetwork1 = createSavedNetwork(TEST_SSID2)
        val savedNetwork2 = createSavedNetwork(TEST_SSID3)

        val savedNetworks = ArrayList<WifiConfiguration>()
        savedNetworks.add(savedNetwork1)
        savedNetworks.add(savedNetwork2)

        `when`(mockWifiManager.configuredNetworks).thenReturn(savedNetworks)
    }

    /**
     * To mock a list of saved networks with one matching and one non-matching SSID.
     *
     * @param addSecondNetwork If the second network should be added to the expected network
     * list for testing
     *
     * @see addToExpectedSavedNetworks
     * @see createSavedNetwork
     * @see WifiConfiguration
     * @see WifiManager.getConfiguredNetworks
     *
     * @author Patches
     * @since 3.0
     */
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

    /**
     * To mock a list of saved networks with a configuration that has a non-matching SSID.
     *
     * @see createSavedNetwork
     * @see WifiConfiguration
     * @see WifiManager.getConfiguredNetworks
     *
     * @author Patches
     * @since 3.0
     */
    internal fun savedNetworks_nonMatchingSSID() {
        val savedNetwork = createSavedNetwork(TEST_SSID2)

        val savedNetworks = ArrayList<WifiConfiguration>()
        savedNetworks.add(savedNetwork)

        `when`(mockWifiManager.configuredNetworks).thenReturn(savedNetworks)
    }

    /**
     * To mock a null list of saved networks.
     *
     * @see WifiManager.getConfiguredNetworks
     *
     * @author Patches
     * @since 3.0
     */
    internal fun savedNetworks_nullList() {
        `when`(mockWifiManager.configuredNetworks).thenReturn(null)
    }

    /**
     * To mock a list of saved networks with a configuration that has a null SSID.
     *
     * @see createSavedNetwork
     * @see WifiConfiguration
     * @see WifiManager.getConfiguredNetworks
     *
     * @author Patches
     * @since 3.0
     */
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

        val accessPoints = ArrayList<ScanResult>()
        accessPoints.add(accessPoint)
        expectedNearbyAccessPoints = accessPoints
    }

    private fun addToExpectedNearbyAccessPoints(accessPoint1: ScanResult, accessPoint2: ScanResult) {
        expectedNearbyAccessPoint = accessPoint1

        val accessPoints = ArrayList<ScanResult>()
        accessPoints.add(accessPoint1)
        accessPoints.add(accessPoint2)
        expectedNearbyAccessPoints = accessPoints
    }

    private fun addToExpectedSavedNetworks(savedNetwork: WifiConfiguration) {
        expectedSavedNetwork = savedNetwork

        val savedNetworks = ArrayList<WifiConfiguration>()
        savedNetworks.add(savedNetwork)
        expectedSavedNetworks = savedNetworks
    }

    private fun addToExpectedSavedNetworks(savedNetwork1: WifiConfiguration, savedNetwork2: WifiConfiguration) {
        expectedSavedNetwork = savedNetwork1

        expectedSavedNetworks = ArrayList()
        val savedNetworks = ArrayList<WifiConfiguration>()
        savedNetworks.add(savedNetwork1)
        savedNetworks.add(savedNetwork2)
        expectedSavedNetworks = savedNetworks
    }

    private fun addToExpectedSSIDs(accessPoint: ScanResult) {
        val ssids = ArrayList<String>()
        ssids.add(accessPoint.SSID)
        expectedSSIDs = ssids
    }

    private fun addToExpectedSSIDs(accessPoint1: ScanResult, accessPoint2: ScanResult) {
        val ssids = ArrayList<String>()
        ssids.add(accessPoint1.SSID)
        ssids.add(accessPoint2.SSID)
        expectedSSIDs = ssids
    }
}
