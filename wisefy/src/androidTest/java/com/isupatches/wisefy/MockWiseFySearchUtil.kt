package com.isupatches.wisefy

import org.mockito.ArgumentMatchers.anyBoolean
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.`when`

import android.net.wifi.ScanResult
import android.net.wifi.WifiConfiguration

import java.util.ArrayList

import org.mockito.ArgumentMatchers

/**
 * A class to mock returns from the WiseFySearch class.
 *
 * @see WiseFySearch
 *
 * @author Patches
 */
internal class MockWiseFySearchUtil internal constructor(private val mockWiseFySearch: WiseFySearch) {

    /**
     * Mocks no nearby access point.
     *
     * @see WiseFySearch.findAccessPointByRegex
     */
    fun findAccessPointByRegex_null() {
        `when`<ScanResult>(mockWiseFySearch.findAccessPointByRegex(anyString(), anyInt(), anyBoolean())).thenReturn(null)
    }

    /**
     * Mocks a nearby access point.
     *
     * @return ScanResult - The mocked nearby access point.
     */
    fun findAccessPointByRegex_success(): ScanResult {
        val accessPoint = createMockAccessPointWithSSIDAndRSSI(TEST_SSID, TEST_RSSI_LEVEL)
        `when`<ScanResult>(mockWiseFySearch.findAccessPointByRegex(anyString(), anyInt(), anyBoolean())).thenReturn(accessPoint)
        return accessPoint
    }

    /**
     * Mocks no nearby access points.
     *
     * @see WiseFySearch.findAccessPointsMatchingRegex
     */
    fun findAccessPointsMatchingRegex_null() {
        `when`<List<ScanResult>>(mockWiseFySearch.findAccessPointsMatchingRegex(anyString(), anyBoolean())).thenReturn(null)
    }

    /**
     * Mocks nearby access point.
     *
     * @return List of ScanResults - The mocked access points.
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
     */
    fun findSavedNetworkByRegex_null() {
        `when`<WifiConfiguration>(mockWiseFySearch.findSavedNetworkByRegex(anyString())).thenReturn(null)
    }

    /**
     * Mocks a saved network.
     *
     * @return WifiConfiguration - The mocked saved network
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
     */
    fun findSavedNetworksByRegex_emptyList() {
        `when`<List<WifiConfiguration>>(mockWiseFySearch.findSavedNetworksMatchingRegex(anyString())).thenReturn(ArrayList())
    }

    /**
     * Mocks no saved networks.
     */
    fun findSavedNetworksByRegex_null() {
        `when`<List<WifiConfiguration>>(mockWiseFySearch.findSavedNetworksMatchingRegex(anyString())).thenReturn(null)
    }

    /**
     * Mocks a list of saved networks.
     *
     * @return List of WifiConfigurations - The mocked saved network list
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
     */
    fun findSSIDsMatchingRegex_success(): List<String> {
        val ssids = ArrayList<String>()
        ssids.add(TEST_SSID)
        `when`<List<String>>(mockWiseFySearch.findSSIDsMatchingRegex(anyString())).thenReturn(ssids)
        return ssids
    }

    /**
     * Mocks no matching SSIDs.
     */
    fun findSSIDsMatchingRegex_null() {
        `when`<List<String>>(mockWiseFySearch.findSSIDsMatchingRegex(anyString())).thenReturn(null)
    }

    /**
     * Mocks if a network is already saved.
     *
     * @param saved If the network is already saved
     */
    fun isNetworkASavedConfiguration(saved: Boolean) {
        `when`(mockWiseFySearch.isNetworkASavedConfiguration(anyString())).thenReturn(saved)
    }

    /**
     * Mocks a basic return of two networks from WiseFySearch#removeEntriesWithLowerSignalStrength.
     *
     * @see WiseFySearch.removeEntriesWithLowerSignalStrength
     * @return List of ScanResults - The mocked networks that will be returned
     */
    fun removeEntriesWithLowerSignalStrength(): List<ScanResult> {
        val accessPoints = createMockAccessPointList(TEST_SSID, TEST_RSSI_LEVEL_HIGH, TEST_SSID2, TEST_RSSI_LEVEL_LOW)
        `when`(mockWiseFySearch.removeEntriesWithLowerSignalStrength(ArgumentMatchers.anyList())).thenReturn(accessPoints)
        return accessPoints
    }
}
