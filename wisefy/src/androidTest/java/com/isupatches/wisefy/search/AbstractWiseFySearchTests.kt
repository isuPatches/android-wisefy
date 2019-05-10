package com.isupatches.wisefy.search

import com.isupatches.wisefy.BaseAndroidJUnit4TestClass
import com.isupatches.wisefy.TEST_REGEX
import com.isupatches.wisefy.TEST_SSID

import org.junit.Assert
import org.junit.Test

@Suppress("LargeClass", "TooManyFunctions")
internal class AbstractWiseFySearchTests : BaseAndroidJUnit4TestClass() {

    private val wisefySearch: WiseFySearch = WiseFySearchSDK23.create(mockWifiManager)

    /*
     * findSavedNetworkByRegex tests
     */

    @Test fun findSavedNetworkByRegex_failure_nullSavedNetworkList() {
        mockNetworkUtil.savedNetworks_nullList()
        Assert.assertEquals(null, wisefySearch.findSavedNetworkByRegex(TEST_SSID))
    }

    @Test fun findSavedNetworkByRegex_failure_emptySavedNetworkList() {
        mockNetworkUtil.savedNetworks_emptyList()
        Assert.assertEquals(null, wisefySearch.findSavedNetworkByRegex(TEST_SSID))
    }

    @Test fun findSavedNetworkByRegex_failure_nullWifiConfiguration() {
        mockNetworkUtil.savedNetworks_nullSavedNetwork()
        Assert.assertEquals(null, wisefySearch.findSavedNetworkByRegex(TEST_SSID))
    }

    @Test fun findSavedNetworkByRegex_failure_nullSSID() {
        mockNetworkUtil.savedNetworks_nullSSID()
        Assert.assertEquals(null, wisefySearch.findSavedNetworkByRegex(TEST_SSID))
    }

    @Test fun findSavedNetworkByRegex_failure_nonMatchingSSID() {
        mockNetworkUtil.savedNetworks_nonMatchingSSID()
        Assert.assertEquals(null, wisefySearch.findSavedNetworkByRegex(TEST_SSID))
    }

    @Test fun findSavedNetworkByRegex_failure_multipleNonMatchingSSIDs() {
        mockNetworkUtil.savedNetworks_multipleNonMatchingSSIDs()
        Assert.assertEquals(null, wisefySearch.findSavedNetworkByRegex(TEST_SSID))
    }

    @Test fun findSavedNetworkByRegex_success() {
        mockNetworkUtil.savedNetworks_matchingSSID()
        Assert.assertEquals(mockNetworkUtil.getExpectedSavedNetwork(), wisefySearch.findSavedNetworkByRegex(TEST_SSID))
    }

    @Test fun findSavedNetworkByRegex_success_multipleMatchingSSIDs() {
        mockNetworkUtil.savedNetworks_multipleMatchingSSIDs()
        Assert.assertEquals(mockNetworkUtil.getExpectedSavedNetwork(), wisefySearch.findSavedNetworkByRegex(TEST_SSID))
    }

    @Test fun findSavedNetworkByRegex_success_multipleSSIDs_nonRegex() {
        mockNetworkUtil.savedNetworks_multipleSSIDs(false)
        Assert.assertEquals(mockNetworkUtil.getExpectedSavedNetwork(), wisefySearch.findSavedNetworkByRegex(TEST_SSID))
    }

    @Test fun findSavedNetworkByRegex_success_multipleSSIDs_regex() {
        mockNetworkUtil.savedNetworks_multipleSSIDs(true)
        Assert.assertEquals(mockNetworkUtil.getExpectedSavedNetwork(), wisefySearch.findSavedNetworkByRegex(TEST_REGEX))
    }

    /*
     * findSavedNetworksMatchingRegex tests
     */

    @Test fun findSavedNetworksMatchingRegex_failure_nullSavedNetworkList() {
        mockNetworkUtil.savedNetworks_nullList()
        Assert.assertEquals(null, wisefySearch.findSavedNetworksMatchingRegex(TEST_SSID))
    }

    @Test fun findSavedNetworksMatchingRegex_failure_emptySavedNetworkList() {
        mockNetworkUtil.savedNetworks_emptyList()
        Assert.assertEquals(null, wisefySearch.findSavedNetworksMatchingRegex(TEST_SSID))
    }

    @Test fun findSavedNetworksMatchingRegex_failure_nullWifiConfiguration() {
        mockNetworkUtil.savedNetworks_nullSavedNetwork()
        Assert.assertEquals(null, wisefySearch.findSavedNetworksMatchingRegex(TEST_SSID))
    }

    @Test fun findSavedNetworksMatchingRegex_failure_nullSSID() {
        mockNetworkUtil.savedNetworks_nullSSID()
        Assert.assertEquals(null, wisefySearch.findSavedNetworksMatchingRegex(TEST_SSID))
    }

    @Test fun findSavedNetworksMatchingRegex_failure_nonMatchingSSID() {
        mockNetworkUtil.savedNetworks_nonMatchingSSID()
        Assert.assertEquals(null, wisefySearch.findSavedNetworksMatchingRegex(TEST_SSID))
    }

    @Test fun findSavedNetworksMatchingRegex_failure_multipleNonMatchingSSIDs() {
        mockNetworkUtil.savedNetworks_multipleNonMatchingSSIDs()
        Assert.assertEquals(null, wisefySearch.findSavedNetworksMatchingRegex(TEST_SSID))
    }

    @Test fun findSavedNetworksMatchingRegex_success() {
        mockNetworkUtil.savedNetworks_matchingSSID()
        Assert.assertEquals(mockNetworkUtil.getExpectedSavedNetworks(), wisefySearch.findSavedNetworksMatchingRegex(TEST_SSID))
    }

    @Test fun findSavedNetworksMatchingRegex_success_multipleMatchingSSIDs() {
        mockNetworkUtil.savedNetworks_multipleMatchingSSIDs()
        Assert.assertEquals(mockNetworkUtil.getExpectedSavedNetworks(), wisefySearch.findSavedNetworksMatchingRegex(TEST_SSID))
    }

    @Test fun findSavedNetworksMatchingRegex_success_multipleSSIDs_nonRegex() {
        mockNetworkUtil.savedNetworks_multipleSSIDs(false)
        Assert.assertEquals(mockNetworkUtil.getExpectedSavedNetworks(), wisefySearch.findSavedNetworksMatchingRegex(TEST_SSID))
    }

    @Test fun findSavedNetworksMatchingRegex_success_multipleSSIDs_regex() {
        mockNetworkUtil.savedNetworks_multipleSSIDs(true)
        Assert.assertEquals(mockNetworkUtil.getExpectedSavedNetworks(), wisefySearch.findSavedNetworksMatchingRegex(TEST_REGEX))
    }

    /*
     *  isNetworkASavedConfiguration tests
     */

    @Test fun isNetworkASavedConfiguration_failure_nullSSIDParam() {
        mockNetworkUtil.savedNetworks_nullList()
        Assert.assertEquals(false, wisefySearch.isNetworkASavedConfiguration(null))
    }

    @Test fun isNetworkASavedConfiguration_failure_nullSavedNetworkList() {
        mockNetworkUtil.savedNetworks_nullList()
        Assert.assertEquals(false, wisefySearch.isNetworkASavedConfiguration(TEST_SSID))
    }

    @Test fun isNetworkASavedConfiguration_failure_emptySavedNetworkList() {
        mockNetworkUtil.savedNetworks_emptyList()
        Assert.assertEquals(false, wisefySearch.isNetworkASavedConfiguration(TEST_SSID))
    }

    @Test fun isNetworkASavedConfiguration_failure_nullWifiConfiguration() {
        mockNetworkUtil.savedNetworks_nullSavedNetwork()
        Assert.assertEquals(false, wisefySearch.isNetworkASavedConfiguration(TEST_SSID))
    }

    @Test fun isNetworkASavedConfiguration_failure_nullSSID() {
        mockNetworkUtil.savedNetworks_nullSSID()
        Assert.assertEquals(false, wisefySearch.isNetworkASavedConfiguration(TEST_SSID))
    }

    @Test fun isNetworkASavedConfiguration_failure_nonMatchingSSID() {
        mockNetworkUtil.savedNetworks_nonMatchingSSID()
        Assert.assertEquals(false, wisefySearch.isNetworkASavedConfiguration(TEST_SSID))
    }

    @Test fun isNetworkASavedConfiguration_failure_multipleNonMatchingSSIDs() {
        mockNetworkUtil.savedNetworks_multipleNonMatchingSSIDs()
        Assert.assertEquals(false, wisefySearch.isNetworkASavedConfiguration(TEST_SSID))
    }

    @Test fun isNetworkASavedConfiguration_success() {
        mockNetworkUtil.savedNetworks_matchingSSID()
        Assert.assertEquals(true, wisefySearch.isNetworkASavedConfiguration(TEST_SSID))
    }

    @Test fun isNetworkASavedConfiguration_success_multipleMatchingSSIDs() {
        mockNetworkUtil.savedNetworks_multipleMatchingSSIDs()
        Assert.assertEquals(true, wisefySearch.isNetworkASavedConfiguration(TEST_SSID))
    }

    @Test fun isNetworkASavedConfiguration_success_multipleSSIDs_nonRegex() {
        mockNetworkUtil.savedNetworks_multipleSSIDs(false)
        Assert.assertEquals(true, wisefySearch.isNetworkASavedConfiguration(TEST_SSID))
    }

    @Test fun isNetworkASavedConfiguration_success_multipleSSIDs_regex() {
        mockNetworkUtil.savedNetworks_multipleSSIDs(true)
        Assert.assertEquals(true, wisefySearch.isNetworkASavedConfiguration(TEST_REGEX))
    }
}