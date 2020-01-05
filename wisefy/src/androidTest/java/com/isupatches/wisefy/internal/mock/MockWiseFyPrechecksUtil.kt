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
 * @author Patches
 * @since 3.0
 */
internal class MockWiseFyPrechecksUtil internal constructor(private val mockWiseFyPrechecks: WiseFyPrechecks) {

    /**
     * Mocks a precheck failure adding a network.
     *
     * @see WiseFyPrechecks.addNetworkPrechecks
     * @see WiseFyPrechecks.addNetworkPrechecks
     *
     * @author Patches
     * @since 3.0
     */
    fun addNetwork_failure() {
        `when`(mockWiseFyPrechecks.addNetworkPrechecks(anyString())).thenReturn(PRECHECK_RESULT_FAILURE)
        `when`(mockWiseFyPrechecks.addNetworkPrechecks(anyString(), anyString())).thenReturn(PRECHECK_RESULT_FAILURE)
    }

    /**
     * Mocks a precheck success adding a network.
     *
     * @see WiseFyPrechecks.addNetworkPrechecks
     * @see WiseFyPrechecks.addNetworkPrechecks
     * @see DEFAULT_PRECHECK_RESULT
     *
     * @author Patches
     * @since 3.0
     */
    fun addNetwork_success() {
        `when`(mockWiseFyPrechecks.addNetworkPrechecks(anyString())).thenReturn(DEFAULT_PRECHECK_RESULT)
        `when`(mockWiseFyPrechecks.addNetworkPrechecks(anyString(), anyString())).thenReturn(DEFAULT_PRECHECK_RESULT)
    }

    /**
     * Mocks a precheck failure connecting to a network.
     *
     * @see WiseFyPrechecks.connectToNetworkPrechecks
     *
     * @author Patches
     * @since 3.0
     */
    fun connectToNetwork_failure() {
        `when`(mockWiseFyPrechecks.connectToNetworkPrechecks(anyString())).thenReturn(PRECHECK_RESULT_FAILURE)
    }

    /**
     * Mocks a precheck success connecting to a network.
     *
     * @see WiseFyPrechecks.connectToNetworkPrechecks
     * @see DEFAULT_PRECHECK_RESULT
     *
     * @author Patches
     * @since 3.0
     */
    fun connectToNetwork_success() {
        `when`(mockWiseFyPrechecks.connectToNetworkPrechecks(anyString())).thenReturn(DEFAULT_PRECHECK_RESULT)
    }

    /**
     * Mocks a precheck failure disabling wifi.
     *
     * @see WiseFyPrechecks.disableWifiChecks
     *
     * @author Patches
     * @since 3.0
     */
    fun disableWifi_failure() {
        `when`(mockWiseFyPrechecks.disableWifiChecks()).thenReturn(PRECHECK_RESULT_FAILURE)
    }

    /**
     * Mocks a precheck success disabling wifi.
     *
     * @see WiseFyPrechecks.disableWifiChecks
     * @see DEFAULT_PRECHECK_RESULT
     *
     * @author Patches
     * @since 3.0
     */
    fun disableWifi_success() {
        `when`(mockWiseFyPrechecks.disableWifiChecks()).thenReturn(DEFAULT_PRECHECK_RESULT)
    }

    /**
     * Mocks a precheck failure disconnecting from the current network.
     *
     * @see WiseFyPrechecks.disconnectFromCurrentNetworkChecks
     *
     * @author Patches
     * @since 3.0
     */
    fun disconnectFromCurrentNetwork_failure() {
        `when`(mockWiseFyPrechecks.disconnectFromCurrentNetworkChecks()).thenReturn(PRECHECK_RESULT_FAILURE)
    }

    /**
     * Mocks a precheck success disconnecting from the current network.
     *
     * @see WiseFyPrechecks.disconnectFromCurrentNetworkChecks
     * @see DEFAULT_PRECHECK_RESULT
     *
     * @author Patches
     * @since 3.0
     */
    fun disconnectFromCurrentNetwork_success() {
        `when`(mockWiseFyPrechecks.disconnectFromCurrentNetworkChecks()).thenReturn(DEFAULT_PRECHECK_RESULT)
    }

    /**
     * Mocks a precheck failure enabling wifi.
     *
     * @see WiseFyPrechecks.enableWifiChecks
     *
     * @author Patches
     * @since 3.0
     */
    fun enableWifi_failure() {
        `when`(mockWiseFyPrechecks.enableWifiChecks()).thenReturn(PRECHECK_RESULT_FAILURE)
    }

    /**
     * Mocks a precheck success enabling wifi.
     *
     * @see WiseFyPrechecks.enableWifiChecks
     * @see DEFAULT_PRECHECK_RESULT
     *
     * @author Patches
     * @since 3.0
     */
    fun enableWifi_success() {
        `when`(mockWiseFyPrechecks.enableWifiChecks()).thenReturn(DEFAULT_PRECHECK_RESULT)
    }

    /**
     * Mocks a precheck failure getting the current network.
     *
     * @see WiseFyPrechecks.getCurrentNetworkChecks
     *
     * @author Patches
     * @since 3.0
     */
    fun getCurrentNetwork_failure() {
        `when`(mockWiseFyPrechecks.getCurrentNetworkChecks()).thenReturn(PRECHECK_RESULT_FAILURE)
    }

    /**
     * Mocks a precheck success getting the current network.
     *
     * @see WiseFyPrechecks.getCurrentNetworkChecks
     * @see DEFAULT_PRECHECK_RESULT
     *
     * @author Patches
     * @since 3.0
     */
    fun getCurrentNetwork_success() {
        `when`(mockWiseFyPrechecks.getCurrentNetworkChecks()).thenReturn(DEFAULT_PRECHECK_RESULT)
    }

    /**
     * Mocks a precheck failure getting a device's current network info.
     *
     * @see WiseFyPrechecks.getCurrentNetworkInfoChecks
     *
     * @author Patches
     * @since 3.0
     */
    fun getCurrentNetworkInfo_failure() {
        `when`(mockWiseFyPrechecks.getCurrentNetworkInfoChecks()).thenReturn(PRECHECK_RESULT_FAILURE)
    }

    /**
     * Mocks a precheck success getting a device's current network info.
     *
     * @see WiseFyPrechecks.getCurrentNetworkInfoChecks
     * @see DEFAULT_PRECHECK_RESULT
     *
     * @author Patches
     * @since 3.0
     */
    fun getCurrentNetworkInfo_success() {
        `when`(mockWiseFyPrechecks.getCurrentNetworkInfoChecks()).thenReturn(DEFAULT_PRECHECK_RESULT)
    }

    /**
     * Mocks a precheck failure getting the current network's frequency.
     *
     * @see WiseFyPrechecks.getFrequencyChecks
     * @see DEFAULT_PRECHECK_RESULT
     *
     * @author Patches
     * @since 4.1
     */
    fun getFrequency_failure() {
        `when`(mockWiseFyPrechecks.getFrequencyChecks()).thenReturn(PRECHECK_RESULT_FAILURE)
    }

    /**
     * Mocks a precheck success getting the current network's frequency.
     *
     * @see WiseFyPrechecks.getFrequencyChecks
     * @see DEFAULT_PRECHECK_RESULT
     *
     * @author Patches
     * @since 4.1
     */
    fun getFrequency_success() {
        `when`(mockWiseFyPrechecks.getFrequencyChecks()).thenReturn(DEFAULT_PRECHECK_RESULT)
    }

    /**
     * Mocks a precheck failure getting the ip of the device.
     *
     * @see WiseFyPrechecks.getIPChecks
     *
     * @author Patches
     * @since 3.0
     */
    fun getIP_failure() {
        `when`(mockWiseFyPrechecks.getIPChecks()).thenReturn(PRECHECK_RESULT_FAILURE)
    }

    /**
     * Mocks a precheck success getting the ip of the device.
     *
     * @see WiseFyPrechecks.getIPChecks
     * @see DEFAULT_PRECHECK_RESULT
     *
     * @author Patches
     * @since 3.0
     */
    fun getIP_success() {
        `when`(mockWiseFyPrechecks.getIPChecks()).thenReturn(DEFAULT_PRECHECK_RESULT)
    }

    /**
     * Mocks a precheck failure getting nearby access points.
     *
     * @see WiseFyPrechecks.getNearbyAccessPointsChecks
     *
     * @author Patches
     * @since 3.0
     */
    fun getNearbyAccessPoints_failure() {
        `when`(mockWiseFyPrechecks.getNearbyAccessPointsChecks()).thenReturn(PRECHECK_RESULT_FAILURE)
    }

    /**
     * Mocks a precheck success getting nearby access points.
     *
     * @see WiseFyPrechecks.getNearbyAccessPointsChecks
     * @see DEFAULT_PRECHECK_RESULT
     *
     * @author Patches
     * @since 3.0
     */
    fun getNearbyAccessPoints_success() {
        `when`(mockWiseFyPrechecks.getNearbyAccessPointsChecks()).thenReturn(DEFAULT_PRECHECK_RESULT)
    }

    /**
     * Mocks a precheck failure getting the RSSI level of a network.
     *
     * @see WiseFyPrechecks.getRSSIChecks
     *
     * @author Patches
     * @since 3.0
     */
    fun getRSSI_failure() {
        `when`(mockWiseFyPrechecks.getRSSIChecks(anyString())).thenReturn(PRECHECK_RESULT_FAILURE)
    }

    /**
     * Mocks a precheck success getting the RSSI level of a network.
     *
     * @see WiseFyPrechecks.getRSSIChecks
     * @see DEFAULT_PRECHECK_RESULT
     *
     * @author Patches
     * @since 3.0
     */
    fun getRSSI_success() {
        `when`(mockWiseFyPrechecks.getRSSIChecks(anyString())).thenReturn(DEFAULT_PRECHECK_RESULT)
    }

    /**
     * Mocks a precheck failure getting saved networks.
     *
     * @see WiseFyPrechecks.getSavedNetworksChecks
     *
     * @author Patches
     * @since 3.0
     */
    fun getSavedNetworks_failure() {
        `when`(mockWiseFyPrechecks.getSavedNetworksChecks()).thenReturn(PRECHECK_RESULT_FAILURE)
    }

    /**
     * Mocks a precheck success getting saved networks.
     *
     * @see WiseFyPrechecks.getSavedNetworksChecks
     * @see DEFAULT_PRECHECK_RESULT
     *
     * @author Patches
     * @since 3.0
     */
    fun getSavedNetworks_success() {
        `when`(mockWiseFyPrechecks.getSavedNetworksChecks()).thenReturn(DEFAULT_PRECHECK_RESULT)
    }

    /**
     * Mocks a precheck failure checking if a device is connected to a mobile network.
     *
     * @see WiseFyPrechecks.isDeviceConnectedToMobileNetworkChecks
     *
     * @author Patches
     * @since 3.0
     */
    fun isDeviceConnectedToMobileNetwork_failure() {
        `when`(mockWiseFyPrechecks.isDeviceConnectedToMobileNetworkChecks()).thenReturn(PRECHECK_RESULT_FAILURE)
    }

    /**
     * Mocks a precheck success checking if a device is connected to a mobile network.
     *
     * @see WiseFyPrechecks.isDeviceConnectedToMobileNetworkChecks
     * @see DEFAULT_PRECHECK_RESULT
     *
     * @author Patches
     * @since 3.0
     */
    fun isDeviceConnectedToMobileNetwork_success() {
        `when`(mockWiseFyPrechecks.isDeviceConnectedToMobileNetworkChecks()).thenReturn(DEFAULT_PRECHECK_RESULT)
    }

    /**
     * Mocks a precheck failure checking if a device is connected to a mobile or wifi network.
     *
     * @see WiseFyPrechecks.isDeviceConnectedToMobileOrWifiNetworkChecks
     *
     * @author Patches
     * @since 3.0
     */
    fun isDeviceConnectedToMobileOrWifiNetwork_failure() {
        `when`(mockWiseFyPrechecks.isDeviceConnectedToMobileOrWifiNetworkChecks()).thenReturn(PRECHECK_RESULT_FAILURE)
    }

    /**
     * Mocks a precheck success checking if a device is connected to a mobile or wifi network.
     *
     * @see WiseFyPrechecks.isDeviceConnectedToMobileOrWifiNetworkChecks
     * @see DEFAULT_PRECHECK_RESULT
     *
     * @author Patches
     * @since 3.0
     */
    fun isDeviceConnectedToMobileOrWifiNetwork_success() {
        `when`(mockWiseFyPrechecks.isDeviceConnectedToMobileOrWifiNetworkChecks()).thenReturn(DEFAULT_PRECHECK_RESULT)
    }

    /**
     * Mocks a precheck failure checking if a device is connected to a network with a specific SSID.
     *
     * @see WiseFyPrechecks.isDeviceConnectedToSSIDChecks
     *
     * @author Patches
     * @since 3.0
     */
    fun isDeviceConnectedToSSIDChecks_failure() {
        `when`(mockWiseFyPrechecks.isDeviceConnectedToSSIDChecks(anyString())).thenReturn(PRECHECK_RESULT_FAILURE)
    }

    /**
     * Mocks a precheck success checking if a device is connected to a network with a specific SSID.
     *
     * @see WiseFyPrechecks.isDeviceConnectedToSSIDChecks
     * @see DEFAULT_PRECHECK_RESULT
     *
     * @author Patches
     * @since 3.0
     */
    fun isDeviceConnectedToSSIDChecks_success() {
        `when`(mockWiseFyPrechecks.isDeviceConnectedToSSIDChecks(anyString())).thenReturn(DEFAULT_PRECHECK_RESULT)
    }

    /**
     * Mocks a precheck failure checking if a device is connected to a wifi network.
     *
     * @see WiseFyPrechecks.isDeviceConnectedToWifiNetworkChecks
     *
     * @author Patches
     * @since 3.0
     */
    fun isDeviceConnectedToWifiNetwork_failure() {
        `when`(mockWiseFyPrechecks.isDeviceConnectedToWifiNetworkChecks()).thenReturn(PRECHECK_RESULT_FAILURE)
    }

    /**
     * Mocks a precheck success checking if a device is connected to a wifi network.
     *
     * @see WiseFyPrechecks.isDeviceConnectedToWifiNetworkChecks
     * @see DEFAULT_PRECHECK_RESULT
     *
     * @author Patches
     * @since 3.0
     */
    fun isDeviceConnectedToWifiNetwork_success() {
        `when`(mockWiseFyPrechecks.isDeviceConnectedToWifiNetworkChecks()).thenReturn(DEFAULT_PRECHECK_RESULT)
    }

    /**
     * Mocks a precheck failure checking if a device is roaming.
     *
     * @see WiseFyPrechecks.isDeviceRoamingChecks
     *
     * @author Patches
     * @since 3.0
     */
    fun isDeviceRoaming_failure() {
        `when`(mockWiseFyPrechecks.isDeviceRoamingChecks()).thenReturn(PRECHECK_RESULT_FAILURE)
    }

    /**
     * Mocks a precheck success checking if a device is roaming.
     *
     * @see WiseFyPrechecks.isDeviceRoamingChecks
     * @see DEFAULT_PRECHECK_RESULT
     *
     * @author Patches
     * @since 3.0
     */
    fun isDeviceRoaming_success() {
        `when`(mockWiseFyPrechecks.isDeviceRoamingChecks()).thenReturn(DEFAULT_PRECHECK_RESULT)
    }

    /**
     * Mocks a precheck failure checking if a network is saved on a device.
     *
     * @see WiseFyPrechecks.isNetworkSavedChecks
     *
     * @author Patches
     * @since 3.0
     */
    fun isNetworkSaved_failure() {
        `when`(mockWiseFyPrechecks.isNetworkSavedChecks()).thenReturn(PRECHECK_RESULT_FAILURE)
    }

    /**
     * Mocks a precheck success checking if a network is saved on a device.
     *
     * @see WiseFyPrechecks.isNetworkSavedChecks
     * @see DEFAULT_PRECHECK_RESULT
     *
     * @author Patches
     * @since 3.0
     */
    fun isNetworkSaved_success() {
        `when`(mockWiseFyPrechecks.isNetworkSavedChecks()).thenReturn(DEFAULT_PRECHECK_RESULT)
    }

    /**
     * Mocks a precheck failure checking if Wifi is enabled on a device.
     *
     * @see WiseFyPrechecks.isWifiEnabledChecks
     *
     * @author Patches
     * @since 3.0
     */
    fun isWifiEnabled_failure() {
        `when`(mockWiseFyPrechecks.isWifiEnabledChecks()).thenReturn(PRECHECK_RESULT_FAILURE)
    }

    /**
     * Mocks a precheck success checking if Wifi is enabled on a device.
     *
     * @see WiseFyPrechecks.isWifiEnabledChecks
     * @see DEFAULT_PRECHECK_RESULT
     *
     * @author Patches
     * @since 3.0
     */
    fun isWifiEnabled_success() {
        `when`(mockWiseFyPrechecks.isWifiEnabledChecks()).thenReturn(DEFAULT_PRECHECK_RESULT)
    }

    /**
     * Mocks a precheck failure removing a network.
     *
     * @see WiseFyPrechecks.removeNetworkCheck
     *
     * @author Patches
     * @since 3.0
     */
    fun removeNetwork_failure() {
        `when`(mockWiseFyPrechecks.removeNetworkCheck(anyString())).thenReturn(PRECHECK_RESULT_FAILURE)
    }

    /**
     * Mocks a precheck success removing a network.
     *
     * @see WiseFyPrechecks.removeNetworkCheck
     * @see DEFAULT_PRECHECK_RESULT
     *
     * @author Patches
     * @since 3.0
     */
    fun removeNetwork_success() {
        `when`(mockWiseFyPrechecks.removeNetworkCheck(anyString())).thenReturn(DEFAULT_PRECHECK_RESULT)
    }

    /**
     * Mocks a precheck failure searching for a nearby access point.
     *
     * @see WiseFyPrechecks.searchForAccessPointsChecks
     *
     * @author Patches
     * @since 3.0
     */
    fun searchForAccessPoints_failure() {
        `when`(mockWiseFyPrechecks.searchForAccessPointsChecks(anyString())).thenReturn(PRECHECK_RESULT_FAILURE)
    }

    /**
     * Mocks a precheck success searching for a nearby access point.
     *
     * @see WiseFyPrechecks.searchForAccessPointsChecks
     * @see DEFAULT_PRECHECK_RESULT
     *
     * @author Patches
     * @since 3.0
     */
    fun searchForAccessPoints_success() {
        `when`(mockWiseFyPrechecks.searchForAccessPointsChecks(anyString())).thenReturn(DEFAULT_PRECHECK_RESULT)
    }

    /**
     * Mocks a precheck failure searching for nearby access points.
     *
     * @see WiseFyPrechecks.searchForAccessPointChecks
     *
     * @author Patches
     * @since 3.0
     */
    fun searchForAccessPoint_failure() {
        `when`(mockWiseFyPrechecks.searchForAccessPointChecks(anyString())).thenReturn(PRECHECK_RESULT_FAILURE)
    }

    /**
     * Mocks a precheck success searching for nearby access points.
     *
     * @see WiseFyPrechecks.searchForAccessPointChecks
     * @see DEFAULT_PRECHECK_RESULT
     *
     * @author Patches
     * @since 3.0
     */
    fun searchForAccessPoint_success() {
        `when`(mockWiseFyPrechecks.searchForAccessPointChecks(anyString())).thenReturn(DEFAULT_PRECHECK_RESULT)
    }

    /**
     * Mocks a precheck failure searching for a saved network.
     *
     * @see WiseFyPrechecks.searchForSavedNetworksChecks
     *
     * @author Patches
     * @since 3.0
     */
    fun searchForSavedNetwork_failure() {
        `when`(mockWiseFyPrechecks.searchForSavedNetworkChecks(anyString())).thenReturn(PRECHECK_RESULT_FAILURE)
    }

    /**
     * Mocks a precheck success searching for a saved network.
     *
     * @see WiseFyPrechecks.searchForSavedNetworksChecks
     * @see DEFAULT_PRECHECK_RESULT
     *
     * @author Patches
     * @since 3.0
     */
    fun searchForSavedNetwork_success() {
        `when`(mockWiseFyPrechecks.searchForSavedNetworkChecks(anyString())).thenReturn(DEFAULT_PRECHECK_RESULT)
    }

    /**
     * Mocks a precheck failure searching for saved networks.
     *
     * @see WiseFyPrechecks.searchForSavedNetworksChecks
     *
     * @author Patches
     * @since 3.0
     */
    fun searchForSavedNetworks_failure() {
        `when`(mockWiseFyPrechecks.searchForSavedNetworksChecks(anyString())).thenReturn(PRECHECK_RESULT_FAILURE)
    }

    /**
     * Mocks a precheck success searching for saved networks.
     *
     * @see WiseFyPrechecks.searchForSavedNetworksChecks
     * @see DEFAULT_PRECHECK_RESULT
     *
     * @author Patches
     * @since 3.0
     */
    fun searchForSavedNetworks_success() {
        `when`(mockWiseFyPrechecks.searchForSavedNetworksChecks(anyString())).thenReturn(DEFAULT_PRECHECK_RESULT)
    }

    /**
     * Mocks a precheck failure searching for an SSID.
     *
     * @see WiseFyPrechecks.searchForSSIDChecks
     *
     * @author Patches
     * @since 3.0
     */
    fun searchForSSID_failure() {
        `when`(mockWiseFyPrechecks.searchForSSIDChecks(anyString())).thenReturn(PRECHECK_RESULT_FAILURE)
    }

    /**
     * Mocks a precheck success searching for an SSID.
     *
     * @see WiseFyPrechecks.searchForSSIDChecks
     * @see DEFAULT_PRECHECK_RESULT
     *
     * @author Patches
     * @since 3.0
     */
    fun searchForSSID_success() {
        `when`(mockWiseFyPrechecks.searchForSSIDChecks(anyString())).thenReturn(DEFAULT_PRECHECK_RESULT)
    }

    /**
     * Mocks a precheck failure searching for SSIDs.
     *
     * @see WiseFyPrechecks.searchForSSIDsChecks
     *
     * @author Patches
     * @since 3.0
     */
    fun searchForSSIDs_failure() {
        `when`(mockWiseFyPrechecks.searchForSSIDsChecks(anyString())).thenReturn(PRECHECK_RESULT_FAILURE)
    }

    /**
     * Mocks a precheck success searching for SSIDs.
     *
     * @see WiseFyPrechecks.searchForSSIDsChecks
     * @see DEFAULT_PRECHECK_RESULT
     *
     * @author Patches
     * @since 3.0
     */
    fun searchForSSIDs_success() {
        `when`(mockWiseFyPrechecks.searchForSSIDsChecks(anyString())).thenReturn(DEFAULT_PRECHECK_RESULT)
    }

    companion object {
        private val PRECHECK_RESULT_FAILURE = PrecheckResult(MISSING_PARAMETER)
    }
}
