package com.isupatches.wisefy.internal.mock

import android.net.wifi.ScanResult
import android.net.wifi.WifiConfiguration

import com.isupatches.wisefy.TEST_RSSI_LEVEL
import com.isupatches.wisefy.TEST_RSSI_LEVEL_HIGH
import com.isupatches.wisefy.TEST_RSSI_LEVEL_LOW
import com.isupatches.wisefy.TEST_SSID
import com.isupatches.wisefy.TEST_SSID2
import com.isupatches.wisefy.internal.createMockAccessPointList
import com.isupatches.wisefy.internal.createMockAccessPointWithSSIDAndRSSI
import com.isupatches.wisefy.internal.createSavedNetwork
import com.isupatches.wisefy.search.WiseFySearch

import org.mockito.ArgumentMatchers.anyBoolean
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.`when`

/**
 * A class to mock returns from the WiseFySearch class.
 *
 * @see WiseFySearch
 *
 * @author Patches
 * @since 3.0
 */
internal class MockWiseFySearchUtil internal constructor(private val mockWiseFySearch: WiseFySearch) {

    /**
     * Mocks no nearby access points.
     *
     * @see WiseFySearch.getNearbyAccessPoints
     *
     * @author Patches
     * @since 3.0
     */
    fun nearbyAccessPoints_null(filterDuplicates: Boolean) {
        `when`(mockWiseFySearch.getNearbyAccessPoints(filterDuplicates)).thenReturn(null)
    }

    /**
     * Mocks a list of nearby access point.
     *
     * @return List of ScanResult - The mocked list of nearby access points
     *
     * @see WiseFySearch.getNearbyAccessPoints
     *
     * @author Patches
     * @since 3.0
     */
    fun nearbyAccessPoints_success(filterDuplicates: Boolean): List<ScanResult> {
        val accessPoints = createMockAccessPointList(TEST_SSID, TEST_RSSI_LEVEL_HIGH, TEST_SSID2, TEST_RSSI_LEVEL_LOW)
        `when`(mockWiseFySearch.getNearbyAccessPoints(filterDuplicates)).thenReturn(accessPoints)
        return accessPoints
    }

    /**
     * Mocks no nearby access point.
     *
     * @see WiseFySearch.findAccessPointByRegex
     *
     * @author Patches
     * @since 3.0
     */
    fun findAccessPointByRegex_null() {
        `when`<ScanResult>(mockWiseFySearch.findAccessPointByRegex(anyString(), anyInt(), anyBoolean())).thenReturn(null)
    }

    /**
     * Mocks a nearby access point.
     *
     * @return ScanResult - The mocked nearby access point.
     *
     * @see WiseFySearch.findAccessPointByRegex
     *
     * @author Patches
     * @since 3.0
     */
    fun findAccessPointByRegex_success(): ScanResult {
        val accessPoint = createMockAccessPointWithSSIDAndRSSI(TEST_SSID, TEST_RSSI_LEVEL)
        `when`<ScanResult>(mockWiseFySearch.findAccessPointByRegex(anyString(), anyInt(), anyBoolean())).thenReturn(accessPoint)
        return accessPoint
    }

    /**
     * Mocks no nearby access points for search.
     *
     * @see WiseFySearch.findAccessPointsMatchingRegex
     *
     * @author Patches
     * @since 3.0
     */
    fun findAccessPointsMatchingRegex_null() {
        `when`<List<ScanResult>>(mockWiseFySearch.findAccessPointsMatchingRegex(anyString(), anyBoolean())).thenReturn(null)
    }

    /**
     * Mocks nearby access point.
     *
     * @return List of ScanResults - The mocked access points.
     *
     * @see WiseFySearch.findAccessPointsMatchingRegex
     *
     * @author Patches
     * @since 3.0
     */
    fun findAccessPointsMatchingRegex_success(): List<ScanResult> {
        val accessPoints = ArrayList<ScanResult>()
        val accessPoint = createMockAccessPointWithSSIDAndRSSI(TEST_SSID, TEST_RSSI_LEVEL)
        accessPoints.add(accessPoint)
        `when`<List<ScanResult>>(mockWiseFySearch.findAccessPointsMatchingRegex(anyString(), anyBoolean())).thenReturn(accessPoints)
        return accessPoints
    }

    /**
     * Mocks no saved network.
     *
     * @see WiseFySearch.findSavedNetworkByRegex
     *
     * @author Patches
     * @since 3.0
     */
    fun findSavedNetworkByRegex_null() {
        `when`<WifiConfiguration>(mockWiseFySearch.findSavedNetworkByRegex(anyString())).thenReturn(null)
    }

    /**
     * Mocks a saved network.
     *
     * @return WifiConfiguration - The mocked saved network
     *
     * @see WiseFySearch.findSavedNetworkByRegex
     *
     * @author Patches
     * @since 3.0
     */
    fun findSavedNetworkByRegex_success(): WifiConfiguration {
        val savedNetwork = createSavedNetwork(TEST_SSID)
        `when`<WifiConfiguration>(mockWiseFySearch.findSavedNetworkByRegex(anyString())).thenReturn(savedNetwork)
        return savedNetwork
    }

    /**
     * Mocks an empty saved network list.
     *
     * @see WiseFySearch.findSavedNetworksMatchingRegex
     *
     * @author Patches
     * @since 3.0
     */
    fun findSavedNetworksByRegex_emptyList() {
        `when`<List<WifiConfiguration>>(mockWiseFySearch.findSavedNetworksMatchingRegex(anyString())).thenReturn(ArrayList())
    }

    /**
     * Mocks no saved networks.
     *
     * @see WiseFySearch.findSavedNetworksMatchingRegex
     *
     * @author Patches
     * @since 3.0
     */
    fun findSavedNetworksByRegex_null() {
        `when`<List<WifiConfiguration>>(mockWiseFySearch.findSavedNetworksMatchingRegex(anyString())).thenReturn(null)
    }

    /**
     * Mocks a list of saved networks.
     *
     * @return List of WifiConfigurations - The mocked saved network list
     *
     * @see WiseFySearch.findSavedNetworksMatchingRegex
     *
     * @author Patches
     * @since 3.0
     */
    fun findSavedNetworksMatchingRegex_success(): List<WifiConfiguration> {
        val savedNetworks = ArrayList<WifiConfiguration>()
        val wiFiConfiguration = createSavedNetwork(TEST_SSID)
        savedNetworks.add(wiFiConfiguration)

        `when`<List<WifiConfiguration>>(mockWiseFySearch.findSavedNetworksMatchingRegex(anyString())).thenReturn(savedNetworks)
        return savedNetworks
    }

    /**
     * Mocks a list of nearby SSIDs.
     *
     * @return List of Strings - The mocked list of SSIDs.
     *
     * @see WiseFySearch.findSSIDsMatchingRegex
     *
     * @author Patches
     * @since 3.0
     */
    fun findSSIDsMatchingRegex_success(): List<String> {
        val ssids = ArrayList<String>()
        ssids.add(TEST_SSID)
        `when`<List<String>>(mockWiseFySearch.findSSIDsMatchingRegex(anyString())).thenReturn(ssids)
        return ssids
    }

    /**
     * Mocks no matching SSIDs.
     *
     * @see WiseFySearch.findSSIDsMatchingRegex
     *
     * @author Patches
     * @since 3.0
     */
    fun findSSIDsMatchingRegex_null() {
        `when`<List<String>>(mockWiseFySearch.findSSIDsMatchingRegex(anyString())).thenReturn(null)
    }

    /**
     * Mocks if a network is already saved.
     *
     * @param saved If the network is already saved
     *
     * @see WiseFySearch.isNetworkASavedConfiguration
     *
     * @author Patches
     * @since 3.0
     */
    fun isNetworkASavedConfiguration(saved: Boolean) {
        `when`(mockWiseFySearch.isNetworkASavedConfiguration(anyString())).thenReturn(saved)
    }
}
