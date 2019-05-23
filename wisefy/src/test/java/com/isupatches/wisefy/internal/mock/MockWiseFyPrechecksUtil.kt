package com.isupatches.wisefy.internal.mock

import com.isupatches.wisefy.DEFAULT_PRECHECK_RESULT
import com.isupatches.wisefy.PrecheckResult
import com.isupatches.wisefy.WiseFyPrechecks
import com.isupatches.wisefy.constants.MISSING_PARAMETER

import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.`when`

/**
 * A class to mock returns from the WiseFyPrechecks class.
 *
 * @see WiseFyPrechecks
 *
 *
 * @author Patches
 */
internal class MockWiseFyPrechecksUtil internal constructor(private val mockWiseFyPrechecks: WiseFyPrechecks) {

    /**
     * Mocks a precheck failure adding a network.
     *
     * @see WiseFyPrechecks.addNetworkPrechecks
     * @see WiseFyPrechecks.addNetworkPrechecks
     */
    fun addNetwork_failure() {
        `when`(mockWiseFyPrechecks.addNetworkPrechecks(anyString())).thenReturn(PRECHECK_RESULT_FAILURE)
        `when`(mockWiseFyPrechecks.addNetworkPrechecks(anyString(), anyString())).thenReturn(PRECHECK_RESULT_FAILURE)
    }

    fun addNetwork_success() {
        `when`(mockWiseFyPrechecks.addNetworkPrechecks(anyString())).thenReturn(DEFAULT_PRECHECK_RESULT)
        `when`(mockWiseFyPrechecks.addNetworkPrechecks(anyString(), anyString())).thenReturn(DEFAULT_PRECHECK_RESULT)
    }

    /**
     * Mocks a precheck failure connecting to a network.
     *
     * @see WiseFyPrechecks.connectToNetworkPrechecks
     */
    fun connectToNetwork_failure() {
        `when`(mockWiseFyPrechecks.connectToNetworkPrechecks(anyString())).thenReturn(PRECHECK_RESULT_FAILURE)
    }

    fun connectToNetwork_success() {
        `when`(mockWiseFyPrechecks.connectToNetworkPrechecks(anyString())).thenReturn(DEFAULT_PRECHECK_RESULT)
    }

    /**
     * Mocks a precheck failure disabling wifi.
     *
     * @see WiseFyPrechecks.disableWifiChecks
     */
    fun disableWifi_failure() {
        `when`(mockWiseFyPrechecks.disableWifiChecks()).thenReturn(PRECHECK_RESULT_FAILURE)
    }

    fun disableWifi_success() {
        `when`(mockWiseFyPrechecks.disableWifiChecks()).thenReturn(DEFAULT_PRECHECK_RESULT)
    }

    /**
     * Mocks a precheck failure disconnecting from the current network.
     *
     * @see WiseFyPrechecks.disconnectFromCurrentNetworkChecks
     */
    fun disconnectFromCurrentNetwork_failure() {
        `when`(mockWiseFyPrechecks.disconnectFromCurrentNetworkChecks()).thenReturn(PRECHECK_RESULT_FAILURE)
    }

    fun disconnectFromCurrentNetwork_success() {
        `when`(mockWiseFyPrechecks.disconnectFromCurrentNetworkChecks()).thenReturn(DEFAULT_PRECHECK_RESULT)
    }

    /**
     * Mocks a precheck failure enabling wifi.
     *
     * @see WiseFyPrechecks.enableWifiChecks
     */
    fun enableWifi_failure() {
        `when`(mockWiseFyPrechecks.enableWifiChecks()).thenReturn(PRECHECK_RESULT_FAILURE)
    }

    fun enableWifi_success() {
        `when`(mockWiseFyPrechecks.enableWifiChecks()).thenReturn(DEFAULT_PRECHECK_RESULT)
    }

    /**
     * Mocks a precheck failure getting the current network.
     *
     * @see WiseFyPrechecks.getCurrentNetworkChecks
     */
    fun getCurrentNetwork_failure() {
        `when`(mockWiseFyPrechecks.getCurrentNetworkChecks()).thenReturn(PRECHECK_RESULT_FAILURE)
    }

    fun getCurrentNetwork_success() {
        `when`(mockWiseFyPrechecks.getCurrentNetworkChecks()).thenReturn(DEFAULT_PRECHECK_RESULT)
    }

    fun getCurrentNetworkInfo_failure() {
        `when`(mockWiseFyPrechecks.getCurrentNetworkInfoChecks()).thenReturn(PRECHECK_RESULT_FAILURE)
    }

    fun getCurrentNetworkInfo_success() {
        `when`(mockWiseFyPrechecks.getCurrentNetworkInfoChecks()).thenReturn(DEFAULT_PRECHECK_RESULT)
    }

    /**
     * Mocks a precheck failure getting the ip of the device.
     *
     * @see WiseFyPrechecks.getIPChecks
     */
    fun getIP_failure() {
        `when`(mockWiseFyPrechecks.getIPChecks()).thenReturn(PRECHECK_RESULT_FAILURE)
    }

    fun getIP_success() {
        `when`(mockWiseFyPrechecks.getIPChecks()).thenReturn(DEFAULT_PRECHECK_RESULT)
    }

    /**
     * Mocks a precheck failure getting nearby access points.
     *
     * @see WiseFyPrechecks.getNearbyAccessPointsChecks
     */
    fun getNearbyAccessPoints_failure() {
        `when`(mockWiseFyPrechecks.getNearbyAccessPointsChecks()).thenReturn(PRECHECK_RESULT_FAILURE)
    }

    fun getNearbyAccessPoints_success() {
        `when`(mockWiseFyPrechecks.getNearbyAccessPointsChecks()).thenReturn(DEFAULT_PRECHECK_RESULT)
    }

    /**
     * Mocks a precheck failure getting the RSSI level of a network.
     *
     * @see WiseFyPrechecks.getRSSIChecks
     */
    fun getRSSI_failure() {
        `when`(mockWiseFyPrechecks.getRSSIChecks(anyString())).thenReturn(PRECHECK_RESULT_FAILURE)
    }

    fun getRSSI_success() {
        `when`(mockWiseFyPrechecks.getRSSIChecks(anyString())).thenReturn(DEFAULT_PRECHECK_RESULT)
    }

    /**
     * Mocks a precheck failure getting saved networks.
     *
     * @see WiseFyPrechecks.getSavedNetworksChecks
     */
    fun getSavedNetworks_failure() {
        `when`(mockWiseFyPrechecks.getSavedNetworksChecks()).thenReturn(PRECHECK_RESULT_FAILURE)
    }

    fun getSavedNetworks_success() {
        `when`(mockWiseFyPrechecks.getSavedNetworksChecks()).thenReturn(DEFAULT_PRECHECK_RESULT)
    }

    /**
     * Mocks a precheck failure checking if a device is connected to a mobile network.
     *
     * @see WiseFyPrechecks.isDeviceConnectedToMobileNetworkChecks
     */
    fun isDeviceConnectedToMobileNetwork_failure() {
        `when`(mockWiseFyPrechecks.isDeviceConnectedToMobileNetworkChecks()).thenReturn(PRECHECK_RESULT_FAILURE)
    }

    fun isDeviceConnectedToMobileNetwork_success() {
        `when`(mockWiseFyPrechecks.isDeviceConnectedToMobileNetworkChecks()).thenReturn(DEFAULT_PRECHECK_RESULT)
    }

    /**
     * Mocks a precheck failure checking if a device is connected to a mobile or wifi network.
     *
     * @see WiseFyPrechecks.isDeviceConnectedToMobileOrWifiNetworkChecks
     */
    fun isDeviceConnectedToMobileOrWifiNetwork_failure() {
        `when`(mockWiseFyPrechecks.isDeviceConnectedToMobileOrWifiNetworkChecks()).thenReturn(PRECHECK_RESULT_FAILURE)
    }

    fun isDeviceConnectedToMobileOrWifiNetwork_success() {
        `when`(mockWiseFyPrechecks.isDeviceConnectedToMobileOrWifiNetworkChecks()).thenReturn(DEFAULT_PRECHECK_RESULT)
    }

    /**
     * Mocks a precheck failure checking if a device is connected to a network with a specific SSID.
     *
     * @see WiseFyPrechecks.isDeviceConnectedToSSIDChecks
     */
    fun isDeviceConnectedToSSIDChecks_failure() {
        `when`(mockWiseFyPrechecks.isDeviceConnectedToSSIDChecks(anyString())).thenReturn(PRECHECK_RESULT_FAILURE)
    }

    fun isDeviceConnectedToSSIDChecks_success() {
        `when`(mockWiseFyPrechecks.isDeviceConnectedToSSIDChecks(anyString())).thenReturn(DEFAULT_PRECHECK_RESULT)
    }

    /**
     * Mocks a precheck failure checking if a device is connected to a wifi network.
     *
     * @see WiseFyPrechecks.isDeviceConnectedToWifiNetworkChecks
     */
    fun isDeviceConnectedToWifiNetwork_failure() {
        `when`(mockWiseFyPrechecks.isDeviceConnectedToWifiNetworkChecks()).thenReturn(PRECHECK_RESULT_FAILURE)
    }

    fun isDeviceConnectedToWifiNetwork_success() {
        `when`(mockWiseFyPrechecks.isDeviceConnectedToWifiNetworkChecks()).thenReturn(DEFAULT_PRECHECK_RESULT)
    }

    /**
     * Mocks a precheck failure checking if a device is roaming.
     *
     * @see WiseFyPrechecks.isDeviceRoamingChecks
     */
    fun isDeviceRoaming_failure() {
        `when`(mockWiseFyPrechecks.isDeviceRoamingChecks()).thenReturn(PRECHECK_RESULT_FAILURE)
    }

    fun isDeviceRoaming_success() {
        `when`(mockWiseFyPrechecks.isDeviceRoamingChecks()).thenReturn(DEFAULT_PRECHECK_RESULT)
    }

    /**
     * Mocks a precheck failure checking if a network is saved on a device.
     *
     * @see WiseFyPrechecks.isNetworkSavedChecks
     */
    fun isNetworkSaved_failure() {
        `when`(mockWiseFyPrechecks.isNetworkSavedChecks()).thenReturn(PRECHECK_RESULT_FAILURE)
    }

    fun isNetworkSaved_success() {
        `when`(mockWiseFyPrechecks.isNetworkSavedChecks()).thenReturn(DEFAULT_PRECHECK_RESULT)
    }

    /**
     * Mocks a precheck failure checking if Wifi is enabled on a device.
     *
     * @see WiseFyPrechecks.isWifiEnabledChecks
     */
    fun isWifiEnabled_failure() {
        `when`(mockWiseFyPrechecks.isWifiEnabledChecks()).thenReturn(PRECHECK_RESULT_FAILURE)
    }

    fun isWifiEnabled_success() {
        `when`(mockWiseFyPrechecks.isWifiEnabledChecks()).thenReturn(DEFAULT_PRECHECK_RESULT)
    }

    /**
     * Mocks a precheck failure removing a network.
     *
     * @see WiseFyPrechecks.removeNetworkCheck
     */
    fun removeNetwork_failure() {
        `when`(mockWiseFyPrechecks.removeNetworkCheck(anyString())).thenReturn(PRECHECK_RESULT_FAILURE)
    }

    fun removeNetwork_success() {
        `when`(mockWiseFyPrechecks.removeNetworkCheck(anyString())).thenReturn(DEFAULT_PRECHECK_RESULT)
    }

    /**
     * Mocks a precheck failure searching for a nearby access point.
     *
     * @see WiseFyPrechecks.searchForAccessPointsChecks
     */
    fun searchForAccessPoints_failure() {
        `when`(mockWiseFyPrechecks.searchForAccessPointsChecks(anyString())).thenReturn(PRECHECK_RESULT_FAILURE)
    }

    fun searchForAccessPoints_success() {
        `when`(mockWiseFyPrechecks.searchForAccessPointsChecks(anyString())).thenReturn(DEFAULT_PRECHECK_RESULT)
    }

    /**
     * Mocks a precheck failure searching for nearby access points.
     *
     * @see WiseFyPrechecks.searchForAccessPointChecks
     */
    fun searchForAccessPoint_failure() {
        `when`(mockWiseFyPrechecks.searchForAccessPointChecks(anyString())).thenReturn(PRECHECK_RESULT_FAILURE)
    }

    fun searchForAccessPoint_success() {
        `when`(mockWiseFyPrechecks.searchForAccessPointChecks(anyString())).thenReturn(DEFAULT_PRECHECK_RESULT)
    }

    fun searchForSavedNetwork_failure() {
        `when`(mockWiseFyPrechecks.searchForSavedNetworkChecks(anyString())).thenReturn(PRECHECK_RESULT_FAILURE)
    }

    fun searchForSavedNetwork_success() {
        `when`(mockWiseFyPrechecks.searchForSavedNetworkChecks(anyString())).thenReturn(DEFAULT_PRECHECK_RESULT)
    }


    fun searchForSavedNetworks_failure() {
        `when`(mockWiseFyPrechecks.searchForSavedNetworksChecks(anyString())).thenReturn(PRECHECK_RESULT_FAILURE)
    }

    fun searchForSavedNetworks_success() {
        `when`(mockWiseFyPrechecks.searchForSavedNetworksChecks(anyString())).thenReturn(DEFAULT_PRECHECK_RESULT)
    }

    /**
     * Mocks a precheck failure searching for an SSID.
     *
     * @see WiseFyPrechecks.searchForSSIDChecks
     */
    fun searchForSSID_failure() {
        `when`(mockWiseFyPrechecks.searchForSSIDChecks(anyString())).thenReturn(PRECHECK_RESULT_FAILURE)
    }

    fun searchForSSID_success() {
        `when`(mockWiseFyPrechecks.searchForSSIDChecks(anyString())).thenReturn(DEFAULT_PRECHECK_RESULT)
    }

    /**
     * Mocks a precheck failure searching for SSIDs.
     *
     * @see WiseFyPrechecks.searchForSSIDsChecks
     */
    fun searchForSSIDs_failure() {
        `when`(mockWiseFyPrechecks.searchForSSIDsChecks(anyString())).thenReturn(PRECHECK_RESULT_FAILURE)
    }

    fun searchForSSIDs_success() {
        `when`(mockWiseFyPrechecks.searchForSSIDsChecks(anyString())).thenReturn(DEFAULT_PRECHECK_RESULT)
    }

    companion object {

        private val PRECHECK_RESULT_FAILURE = PrecheckResult(MISSING_PARAMETER)
    }
}
