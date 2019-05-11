package com.isupatches.wisefy.internal

import android.net.wifi.ScanResult
import android.net.wifi.WifiConfiguration

import org.mockito.Mockito.mock

/**
 * Creates a mock of a nearby access point for tests with specified security capabilities.
 *
 * @param capabilities The security attributes you want the access point to have
 *
 * @return ScanResult - A mock access point with the given capabilities
 *
 * @see ScanResult
 */
internal fun createMockAccessPointWithCapabilities(capabilities: String?): ScanResult {
    val scanResult = mock(ScanResult::class.java)
    scanResult.capabilities = capabilities
    return scanResult
}

/**
 * Mocks two nearby access points given ssids and rssi levels.
 *
 * @param ssid1 - SSID of access points 1
 * @param rssi1 - RSSI level of access point 1
 * @param ssid2 - SSID of access point 2
 * @param rssi2 - RSSI level of access point 2
 *
 * @return List of ScanResults - The mocked access points
 *
 * @see ScanResult
 */
internal fun createMockAccessPointList(
    ssid1: String,
    rssi1: Int,
    ssid2: String,
    rssi2: Int
): List<ScanResult> {
    val accessPoints = ArrayList<ScanResult>()
    accessPoints.add(createMockAccessPointWithSSIDAndRSSI(ssid1, rssi1))
    accessPoints.add(createMockAccessPointWithSSIDAndRSSI(ssid2, rssi2))
    return accessPoints
}

/**
 * Creates a mock of a nearby access point for tests with a specified SSID.
 *
 * @param ssid Tne ssid for the access point
 *
 * @return ScanResult - A mock object representing a nearby access point
 *
 * @see ScanResult
 */
internal fun createMockAccessPointWithSSID(ssid: String?): ScanResult {
    val scanResult = mock(ScanResult::class.java)
    scanResult.SSID = ssid
    return scanResult
}

/**
 * Creates a mock of a nearby access point.
 *
 * @param ssid Tne ssid for the access point
 * @param rssi The rssi level for the access point
 *
 * @return ScanResult - A mock object representing a nearby access point
 *
 * @see ScanResult
 */
internal fun createMockAccessPointWithSSIDAndRSSI(ssid: String, rssi: Int): ScanResult {
    val scanResult = mock(ScanResult::class.java)
    scanResult.SSID = ssid
    scanResult.level = rssi
    return scanResult
}

/**
 * Creates a saved network configuration.
 *
 * @param ssid The ssid for the saved network
 *
 * @return WifiConfiguration - Saved network
 *
 * @see WifiConfiguration
 */
internal fun createSavedNetwork(ssid: String?): WifiConfiguration {
    val wiFiConfiguration = WifiConfiguration()
    wiFiConfiguration.SSID = ssid
    return wiFiConfiguration
}
