package com.isupatches.wisefy.search

import com.isupatches.wisefy.TEST_REGEX
import com.isupatches.wisefy.TEST_SSID
import com.isupatches.wisefy.internal.base.BaseInstrumentationTest
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Used to test the AbstractWiseFySearch class and common search functionality.
 *
 * @see AbstractWiseFySearch
 *
 * @author Patches
 * @since 4.0
 */
@Suppress("LargeClass", "TooManyFunctions")
internal class AbstractWiseFySearchTests : BaseInstrumentationTest() {

    private val wisefySearch: WiseFySearch = WiseFySearchSDK23.create(mockWifiManager)

    /*
     * findSavedNetworkByRegex tests
     */

    @Test fun findSavedNetworkByRegex_failure_nullSavedNetworkList() {
        mockNetworkUtil.savedNetworks_nullList()
        assertEquals(null, wisefySearch.findSavedNetworkByRegex(TEST_SSID))
    }

    @Test fun findSavedNetworkByRegex_failure_emptySavedNetworkList() {
        mockNetworkUtil.savedNetworks_emptyList()
        assertEquals(null, wisefySearch.findSavedNetworkByRegex(TEST_SSID))
    }

    @Test fun findSavedNetworkByRegex_failure_nullSavedNetworkInList() {
        mockNetworkUtil.savedNetworks_listWithNull()
        assertEquals(null, wisefySearch.findSavedNetworksMatchingRegex(TEST_SSID))
    }

    @Test fun findSavedNetworkByRegex_failure_nullSSID() {
        mockNetworkUtil.savedNetworks_nullSSID()
        assertEquals(null, wisefySearch.findSavedNetworkByRegex(TEST_SSID))
    }

    @Test fun findSavedNetworkByRegex_failure_nonMatchingSSID() {
        mockNetworkUtil.savedNetworks_nonMatchingSSID()
        assertEquals(null, wisefySearch.findSavedNetworkByRegex(TEST_SSID))
    }

    @Test fun findSavedNetworkByRegex_failure_multipleNonMatchingSSIDs() {
        mockNetworkUtil.savedNetworks_multipleNonMatchingSSIDs()
        assertEquals(null, wisefySearch.findSavedNetworkByRegex(TEST_SSID))
    }

    @Test fun findSavedNetworkByRegex_success() {
        mockNetworkUtil.savedNetworks_matchingSSID()
        assertEquals(mockNetworkUtil.getExpectedSavedNetwork(), wisefySearch.findSavedNetworkByRegex(TEST_SSID))
    }

    @Test fun findSavedNetworkByRegex_success_multipleMatchingSSIDs() {
        mockNetworkUtil.savedNetworks_multipleMatchingSSIDs()
        assertEquals(mockNetworkUtil.getExpectedSavedNetwork(), wisefySearch.findSavedNetworkByRegex(TEST_SSID))
    }

    @Test fun findSavedNetworkByRegex_success_multipleSSIDs_nonRegex() {
        mockNetworkUtil.savedNetworks_multipleSSIDs(false)
        assertEquals(mockNetworkUtil.getExpectedSavedNetwork(), wisefySearch.findSavedNetworkByRegex(TEST_SSID))
    }

    @Test fun findSavedNetworkByRegex_success_multipleSSIDs_regex() {
        mockNetworkUtil.savedNetworks_multipleSSIDs(true)
        assertEquals(mockNetworkUtil.getExpectedSavedNetwork(), wisefySearch.findSavedNetworkByRegex(TEST_REGEX))
    }

    /*
     * findSavedNetworksMatchingRegex tests
     */

    @Test fun findSavedNetworksMatchingRegex_failure_nullSavedNetworkList() {
        mockNetworkUtil.savedNetworks_nullList()
        assertEquals(null, wisefySearch.findSavedNetworksMatchingRegex(TEST_SSID))
    }

    @Test fun findSavedNetworksMatchingRegex_failure_emptySavedNetworkList() {
        mockNetworkUtil.savedNetworks_emptyList()
        assertEquals(null, wisefySearch.findSavedNetworksMatchingRegex(TEST_SSID))
    }

    @Test fun findSavedNetworksMatchingRegex_failure_nullSavedNetworkInList() {
        mockNetworkUtil.savedNetworks_listWithNull()
        assertEquals(null, wisefySearch.findSavedNetworksMatchingRegex(TEST_SSID))
    }

    @Test fun findSavedNetworksMatchingRegex_failure_nullSSID() {
        mockNetworkUtil.savedNetworks_nullSSID()
        assertEquals(null, wisefySearch.findSavedNetworksMatchingRegex(TEST_SSID))
    }

    @Test fun findSavedNetworksMatchingRegex_failure_nonMatchingSSID() {
        mockNetworkUtil.savedNetworks_nonMatchingSSID()
        assertEquals(null, wisefySearch.findSavedNetworksMatchingRegex(TEST_SSID))
    }

    @Test fun findSavedNetworksMatchingRegex_failure_multipleNonMatchingSSIDs() {
        mockNetworkUtil.savedNetworks_multipleNonMatchingSSIDs()
        assertEquals(null, wisefySearch.findSavedNetworksMatchingRegex(TEST_SSID))
    }

    @Test fun findSavedNetworksMatchingRegex_success() {
        mockNetworkUtil.savedNetworks_matchingSSID()
        assertEquals(mockNetworkUtil.getExpectedSavedNetworks(), wisefySearch.findSavedNetworksMatchingRegex(TEST_SSID))
    }

    @Test fun findSavedNetworksMatchingRegex_success_multipleMatchingSSIDs() {
        mockNetworkUtil.savedNetworks_multipleMatchingSSIDs()
        assertEquals(mockNetworkUtil.getExpectedSavedNetworks(), wisefySearch.findSavedNetworksMatchingRegex(TEST_SSID))
    }

    @Test fun findSavedNetworksMatchingRegex_success_multipleSSIDs_nonRegex() {
        mockNetworkUtil.savedNetworks_multipleSSIDs(false)
        assertEquals(mockNetworkUtil.getExpectedSavedNetworks(), wisefySearch.findSavedNetworksMatchingRegex(TEST_SSID))
    }

    @Test fun findSavedNetworksMatchingRegex_success_multipleSSIDs_regex() {
        mockNetworkUtil.savedNetworks_multipleSSIDs(true)
        assertEquals(mockNetworkUtil.getExpectedSavedNetworks(), wisefySearch.findSavedNetworksMatchingRegex(TEST_REGEX))
    }

    /*
     *  isNetworkASavedConfiguration tests
     */

    @Test fun isNetworkASavedConfiguration_failure_nullSSIDParam() {
        mockNetworkUtil.savedNetworks_nullList()
        assertEquals(false, wisefySearch.isNetworkASavedConfiguration(null))
    }

    @Test fun isNetworkASavedConfiguration_failure_emptySSIDParam() {
        mockNetworkUtil.savedNetworks_nullList()
        assertEquals(false, wisefySearch.isNetworkASavedConfiguration(""))
    }

    @Test fun isNetworkASavedConfiguration_failure_nullSavedNetworkList() {
        mockNetworkUtil.savedNetworks_nullList()
        assertEquals(false, wisefySearch.isNetworkASavedConfiguration(TEST_SSID))
    }

    @Test fun isNetworkASavedConfiguration_failure_emptySavedNetworkList() {
        mockNetworkUtil.savedNetworks_emptyList()
        assertEquals(false, wisefySearch.isNetworkASavedConfiguration(TEST_SSID))
    }

    @Test fun isNetworkASavedConfiguration_failure_nullSSID() {
        mockNetworkUtil.savedNetworks_nullSSID()
        assertEquals(false, wisefySearch.isNetworkASavedConfiguration(TEST_SSID))
    }

    @Test fun isNetworkASavedConfiguration_failure_nonMatchingSSID() {
        mockNetworkUtil.savedNetworks_nonMatchingSSID()
        assertEquals(false, wisefySearch.isNetworkASavedConfiguration(TEST_SSID))
    }

    @Test fun isNetworkASavedConfiguration_failure_multipleNonMatchingSSIDs() {
        mockNetworkUtil.savedNetworks_multipleNonMatchingSSIDs()
        assertEquals(false, wisefySearch.isNetworkASavedConfiguration(TEST_SSID))
    }

    @Test fun isNetworkASavedConfiguration_success() {
        mockNetworkUtil.savedNetworks_matchingSSID()
        assertEquals(true, wisefySearch.isNetworkASavedConfiguration(TEST_SSID))
    }

    @Test fun isNetworkASavedConfiguration_success_multipleMatchingSSIDs() {
        mockNetworkUtil.savedNetworks_multipleMatchingSSIDs()
        assertEquals(true, wisefySearch.isNetworkASavedConfiguration(TEST_SSID))
    }

    @Test fun isNetworkASavedConfiguration_success_multipleSSIDs_nonRegex() {
        mockNetworkUtil.savedNetworks_multipleSSIDs(false)
        assertEquals(true, wisefySearch.isNetworkASavedConfiguration(TEST_SSID))
    }

    @Test fun isNetworkASavedConfiguration_success_multipleSSIDs_regex() {
        mockNetworkUtil.savedNetworks_multipleSSIDs(true)
        assertEquals(true, wisefySearch.isNetworkASavedConfiguration(TEST_REGEX))
    }
}
