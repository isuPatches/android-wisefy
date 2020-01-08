package com.isupatches.wisefy

import com.isupatches.wisefy.constants.DEFAULT_PRECHECK_RETURN_CODE
import com.isupatches.wisefy.constants.MISSING_PARAMETER
import com.isupatches.wisefy.constants.NETWORK_ALREADY_CONFIGURED
import com.isupatches.wisefy.internal.base.BaseInstrumentationTest
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Used to test the WiseFyPrechecks class and functionality pertaining to being able to continue
 * with processing logic.
 *
 * @author Patches
 * @since 3.0
 */
internal class WiseFyPrechecksTests : BaseInstrumentationTest() {

    private val wisefyPrechecks: WiseFyPrechecks = WiseFyPrechecksImpl.create(mockWiseFySearch)

    @Test
    fun addNetworkPrechecks_failure_nullSSIDParam() {
        assertEquals(MISSING_PARAMETER, wisefyPrechecks.addNetworkPrechecks(null).code)
    }

    @Test
    fun addNetworkPrechecks_failure_emptySSID() {
        assertEquals(MISSING_PARAMETER, wisefyPrechecks.addNetworkPrechecks("").code)
    }

    @Test
    fun addNetworkPrechecks_failure_networkAlreadySaved() {
        mockWiseFySearchUtil.isNetworkASavedConfiguration(true)
        assertEquals(NETWORK_ALREADY_CONFIGURED, wisefyPrechecks.addNetworkPrechecks(TEST_SSID).code)
    }

    @Test
    fun addNetworkPrechecks_success() {
        mockWiseFySearchUtil.isNetworkASavedConfiguration(false)
        assertEquals(DEFAULT_PRECHECK_RETURN_CODE, wisefyPrechecks.addNetworkPrechecks(TEST_SSID).code)
    }

    @Test
    fun addNetworkPrechecks_withPassword_failure_nullSSIDParam() {
        assertEquals(MISSING_PARAMETER, wisefyPrechecks.addNetworkPrechecks(null, WEP_NETWORK_PASSWORD).code)
    }

    @Test
    fun addNetworkPrechecks_withPassword_failure_emptySSIDParam() {
        assertEquals(MISSING_PARAMETER, wisefyPrechecks.addNetworkPrechecks("", WEP_NETWORK_PASSWORD).code)
    }

    @Test
    fun addNetworkPrechecks_withPassword_failure_nullPasswordParam() {
        assertEquals(MISSING_PARAMETER, wisefyPrechecks.addNetworkPrechecks(WEP_NETWORK_SSID, null).code)
    }

    @Test
    fun addNetworkPrechecks_withPassword_failure_emptyPasswordParam() {
        assertEquals(MISSING_PARAMETER, wisefyPrechecks.addNetworkPrechecks(WEP_NETWORK_SSID, "").code)
    }

    @Test
    fun addNetworkPrechecks_withPassword_failure_networkAlreadySaved() {
        mockWiseFySearchUtil.isNetworkASavedConfiguration(true)
        assertEquals(
            NETWORK_ALREADY_CONFIGURED,
            wisefyPrechecks.addNetworkPrechecks(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD).code
        )
    }

    @Test
    fun addNetworkPrechecks_withPassword_success() {
        mockWiseFySearchUtil.isNetworkASavedConfiguration(false)
        assertEquals(
            DEFAULT_PRECHECK_RETURN_CODE,
            wisefyPrechecks.addNetworkPrechecks(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD).code
        )
    }

    @Test
    fun connectToNetworkPrechecks_failure_nullParam() {
        assertEquals(MISSING_PARAMETER, wisefyPrechecks.connectToNetworkPrechecks(null).code)
    }

    @Test
    fun connectToNetworkPrechecks_failure_emptyParam() {
        assertEquals(MISSING_PARAMETER, wisefyPrechecks.connectToNetworkPrechecks("").code)
    }

    @Test
    fun connectToNetworkPrechecks_success() {
        assertEquals(DEFAULT_PRECHECK_RETURN_CODE, wisefyPrechecks.connectToNetworkPrechecks(TEST_SSID).code)
    }

    @Test
    fun disableWifiChecks() {
        assertEquals(DEFAULT_PRECHECK_RETURN_CODE, wisefyPrechecks.disableWifiChecks().code)
    }

    @Test
    fun disconnectFromCurrentNetworkChecks() {
        assertEquals(DEFAULT_PRECHECK_RETURN_CODE, wisefyPrechecks.disconnectFromCurrentNetworkChecks().code)
    }

    @Test
    fun enableWifiChecks() {
        assertEquals(DEFAULT_PRECHECK_RETURN_CODE, wisefyPrechecks.enableWifiChecks().code)
    }

    @Test
    fun getCurrentNetworkChecks() {
        assertEquals(DEFAULT_PRECHECK_RETURN_CODE, wisefyPrechecks.getCurrentNetworkChecks().code)
    }

    @Test
    fun getCurrentNetworkInfoChecks() {
        assertEquals(DEFAULT_PRECHECK_RETURN_CODE, wisefyPrechecks.getCurrentNetworkInfoChecks().code)
    }

    @Test
    fun getFrequencyChecks() {
        assertEquals(DEFAULT_PRECHECK_RETURN_CODE, wisefyPrechecks.getFrequencyChecks().code)
    }

    @Test
    fun getIPChecks() {
        assertEquals(DEFAULT_PRECHECK_RETURN_CODE, wisefyPrechecks.getIPChecks().code)
    }

    @Test
    fun getNearbyAccessPointsChecks_success() {
        assertEquals(DEFAULT_PRECHECK_RETURN_CODE, wisefyPrechecks.getNearbyAccessPointsChecks().code)
    }

    @Test
    fun getRSSIChecks_failure_nullRegexForSSIDParam() {
        assertEquals(MISSING_PARAMETER, wisefyPrechecks.getRSSIChecks(null).code)
    }

    @Test
    fun getRSSIChecks_failure_emptyRegexForSSIDParam() {
        assertEquals(MISSING_PARAMETER, wisefyPrechecks.getRSSIChecks("").code)
    }

    @Test
    fun getRSSIChecks_success() {
        assertEquals(DEFAULT_PRECHECK_RETURN_CODE, wisefyPrechecks.getRSSIChecks(TEST_SSID).code)
    }

    @Test
    fun getSavedNetworkChecks_failure_nullRegexForSSIDParam() {
        assertEquals(MISSING_PARAMETER, wisefyPrechecks.getSavedNetworkChecks(null).code)
    }

    @Test
    fun getSavedNetworkChecks_failure_emptyRegexForSSIDParam() {
        assertEquals(MISSING_PARAMETER, wisefyPrechecks.getSavedNetworkChecks("").code)
    }

    @Test
    fun getSavedNetworkChecks_success() {
        assertEquals(DEFAULT_PRECHECK_RETURN_CODE, wisefyPrechecks.getSavedNetworkChecks(TEST_SSID).code)
    }

    @Test
    fun getSavedNetworksChecks_withRegex_failure_nullRegexForSSIDParam() {
        assertEquals(MISSING_PARAMETER, wisefyPrechecks.getSavedNetworksChecks(null).code)
    }

    @Test
    fun getSavedNetworksChecks_withRegex_failure_emptyRegexForSSIDParam() {
        assertEquals(MISSING_PARAMETER, wisefyPrechecks.getSavedNetworksChecks("").code)
    }

    @Test
    fun getSavedNetworksChecks_success() {
        assertEquals(DEFAULT_PRECHECK_RETURN_CODE, wisefyPrechecks.getSavedNetworksChecks().code)
    }

    @Test
    fun getSavedNetworksChecks_with_regex_success() {
        assertEquals(DEFAULT_PRECHECK_RETURN_CODE, wisefyPrechecks.getSavedNetworksChecks(TEST_SSID).code)
    }

    @Test
    fun isDeviceConnectedToMobileNetworkChecks() {
        assertEquals(DEFAULT_PRECHECK_RETURN_CODE, wisefyPrechecks.isDeviceConnectedToMobileNetworkChecks().code)
    }

    @Test
    fun isDeviceConnectedToMobileOrWifiNetworkChecks_success() {
        assertEquals(DEFAULT_PRECHECK_RETURN_CODE, wisefyPrechecks.isDeviceConnectedToMobileOrWifiNetworkChecks().code)
    }

    @Test
    fun isDeviceConnectedToSSIDChecks_failure_nullSSIDParam() {
        assertEquals(MISSING_PARAMETER, wisefyPrechecks.isDeviceConnectedToSSIDChecks(null).code)
    }

    @Test
    fun isDeviceConnectedToSSIDChecks_failure_emptySSIDParam() {
        assertEquals(MISSING_PARAMETER, wisefyPrechecks.isDeviceConnectedToSSIDChecks("").code)
    }

    @Test
    fun isDeviceConnectedToSSIDChecks_success() {
        assertEquals(DEFAULT_PRECHECK_RETURN_CODE, wisefyPrechecks.isDeviceConnectedToSSIDChecks(TEST_SSID).code)
    }

    @Test
    fun isDeviceConnectedToWifiNetworkChecks() {
        assertEquals(DEFAULT_PRECHECK_RETURN_CODE, wisefyPrechecks.isDeviceConnectedToWifiNetworkChecks().code)
    }

    @Test
    fun isDeviceRoamingChecks() {
        assertEquals(DEFAULT_PRECHECK_RETURN_CODE, wisefyPrechecks.isDeviceRoamingChecks().code)
    }

    @Test
    fun isNetworkSavedChecks() {
        assertEquals(DEFAULT_PRECHECK_RETURN_CODE, wisefyPrechecks.isNetworkSavedChecks().code)
    }

    @Test
    fun isWifiEnabledChecks() {
        assertEquals(DEFAULT_PRECHECK_RETURN_CODE, wisefyPrechecks.isWifiEnabledChecks().code)
    }

    @Test
    fun removeNetworkCheck_failure_nullSSIDToRemoveParam() {
        assertEquals(MISSING_PARAMETER, wisefyPrechecks.removeNetworkCheck(null).code)
    }

    @Test
    fun removeNetworkCheck_failure_emptySSIDToRemoveParam() {
        assertEquals(MISSING_PARAMETER, wisefyPrechecks.removeNetworkCheck("").code)
    }

    @Test
    fun removeNetworkCheck_success() {
        assertEquals(DEFAULT_PRECHECK_RETURN_CODE, wisefyPrechecks.removeNetworkCheck(TEST_SSID).code)
    }

    @Test
    fun searchForAccessPointChecks_failure_nullRegexForSSIDParam() {
        assertEquals(MISSING_PARAMETER, wisefyPrechecks.searchForAccessPointChecks(null).code)
    }

    @Test
    fun searchForAccessPointChecks_failure_emptyRegexForSSIDParam() {
        assertEquals(MISSING_PARAMETER, wisefyPrechecks.searchForAccessPointChecks("").code)
    }

    @Test
    fun searchForAccessPointChecks_success() {
        assertEquals(DEFAULT_PRECHECK_RETURN_CODE, wisefyPrechecks.searchForAccessPointChecks(TEST_SSID).code)
    }

    @Test
    fun searchForAccessPointsChecks_failure_nullRegexForSSIDParam() {
        assertEquals(MISSING_PARAMETER, wisefyPrechecks.searchForAccessPointsChecks(null).code)
    }

    @Test
    fun searchForAccessPointsChecks_failure_emptyRegexForSSIDParam() {
        assertEquals(MISSING_PARAMETER, wisefyPrechecks.searchForAccessPointsChecks("").code)
    }

    @Test
    fun searchForAccessPointsChecks_success() {
        assertEquals(DEFAULT_PRECHECK_RETURN_CODE, wisefyPrechecks.searchForAccessPointsChecks(TEST_SSID).code)
    }

    @Test
    fun searchForSavedNetwork_failure_nullRegexForSSIDParam() {
        assertEquals(MISSING_PARAMETER, wisefyPrechecks.searchForSavedNetworkChecks(null).code)
    }

    @Test
    fun searchForSavedNetwork_failure_emptyRegexForSSIDParam() {
        assertEquals(MISSING_PARAMETER, wisefyPrechecks.searchForSavedNetworkChecks("").code)
    }

    @Test
    fun searchForSavedNetworkChecks_success() {
        assertEquals(DEFAULT_PRECHECK_RETURN_CODE, wisefyPrechecks.searchForSavedNetworkChecks(TEST_SSID).code)
    }

    @Test
    fun searchForSavedNetworksChecks_failure_nullRegexForSSIDParam() {
        assertEquals(MISSING_PARAMETER, wisefyPrechecks.searchForSavedNetworksChecks(null).code)
    }

    @Test
    fun searchForSavedNetworksChecks_failure_emptyRegexForSSIDParam() {
        assertEquals(MISSING_PARAMETER, wisefyPrechecks.searchForSavedNetworksChecks("").code)
    }

    @Test
    fun searchForSavedNetworksChecks_success() {
        assertEquals(DEFAULT_PRECHECK_RETURN_CODE, wisefyPrechecks.searchForSavedNetworksChecks(TEST_SSID).code)
    }

    @Test
    fun searchForSSIDChecks_failure_nullRegexForSSIDParam() {
        assertEquals(MISSING_PARAMETER, wisefyPrechecks.searchForSSIDChecks(null).code)
    }

    @Test
    fun searchForSSIDChecks_failure_emptyRegexForSSIDParam() {
        assertEquals(MISSING_PARAMETER, wisefyPrechecks.searchForSSIDChecks("").code)
    }

    @Test
    fun searchForSSIDChecks_success() {
        assertEquals(DEFAULT_PRECHECK_RETURN_CODE, wisefyPrechecks.searchForSSIDChecks(TEST_SSID).code)
    }

    @Test
    fun searchForSSIDsChecks_failure_nullRegexForSSIDParam() {
        assertEquals(MISSING_PARAMETER, wisefyPrechecks.searchForSSIDsChecks(null).code)
    }

    @Test
    fun searchForSSIDsChecks_failure_emptyRegexForSSIDParam() {
        assertEquals(MISSING_PARAMETER, wisefyPrechecks.searchForSSIDsChecks("").code)
    }

    @Test
    fun searchForSSIDsChecks_success() {
        assertEquals(DEFAULT_PRECHECK_RETURN_CODE, wisefyPrechecks.searchForSSIDsChecks(TEST_SSID).code)
    }
}
