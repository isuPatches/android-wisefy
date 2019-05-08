package com.isupatches.wisefy.search

import com.isupatches.wisefy.BaseAndroidJUnit4TestClass
import com.isupatches.wisefy.TEST_REGEX
import com.isupatches.wisefy.TEST_SSID
import com.isupatches.wisefy.TEST_TIMEOUT
import org.junit.Assert.assertEquals

import org.junit.Test

/**
 * Used to test the WiseFySearch class and search capabilities.
 *
 * @see WiseFySearch
 *
 * @author Patches
 */
@Suppress("LargeClass", "TooManyFunctions")
internal class WiseFySearchLegacyTests : BaseAndroidJUnit4TestClass() {

    private val wisefySearch: WiseFySearch = WiseFySearchLegacy.create(mockWifiManager)

    /*
     * findAccessPointByRegex tests
     */

    @Test fun findAccessPointByRegex_failure_nullAccessPoints_takeHighest_false() {
        mockNetworkUtil.nearbyAccessPoints_nullList()
        assertEquals(null, wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, false))
    }

    @Test fun findAccessPointByRegex_failure_nullAccessPoints_takeHighest_true() {
        mockNetworkUtil.nearbyAccessPoints_nullList()
        assertEquals(null, wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, true))
    }

    @Test fun findAccessPointByRegex_failure_emptyAccessPointList_takeHighest_false() {
        mockNetworkUtil.nearbyAccessPoints_emptyList()
        assertEquals(null, wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, false))
    }

    @Test fun findAccessPointByRegex_failure_emptyAccessPointList_takeHighest_true() {
        mockNetworkUtil.nearbyAccessPoints_emptyList()
        assertEquals(null, wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, true))
    }

    @Test fun findAccessPointByRegex_failure_nullAccessPoint_takeHighest_false() {
        mockNetworkUtil.nearbyAccessPoints_nullAccessPoint()
        assertEquals(null, wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, false))
    }

    @Test fun findAccessPointByRegex_failure_nullAccessPoint_takeHighest_true() {
        mockNetworkUtil.nearbyAccessPoints_nullAccessPoint()
        assertEquals(null, wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, true))
    }

    @Test fun findAccessPointByRegex_failure_nullSSID_takeHighest_false() {
        mockNetworkUtil.nearbyAccessPoints_nullSSID()
        assertEquals(null, wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, false))
    }

    @Test fun findAccessPointByRegex_failure_nullSSID_takeHighest_true() {
        mockNetworkUtil.nearbyAccessPoints_nullSSID()
        assertEquals(null, wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, true))
    }

    @Test fun findAccessPointByRegex_failure_nonMatchingSSID_takeHighest_false() {
        mockNetworkUtil.nearbyAccessPoints_nonMatchingSSID()
        assertEquals(null, wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, false))
    }

    @Test fun findAccessPointByRegex_failure_nonMatchingSSID_takeHighest_true() {
        mockNetworkUtil.nearbyAccessPoints_nonMatchingSSID()
        assertEquals(null, wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, true))
    }

    @Test fun findAccessPointByRegex_failure_multipleNonMatchingSSIDs_takeHighest_false() {
        mockNetworkUtil.nearbyAccessPoints_multipleNonMatchingSSIDs()
        assertEquals(null, wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, false))
    }

    @Test fun findAccessPointByRegex_failure_multipleNonMatchingSSIDs_takeHighest_true() {
        mockNetworkUtil.nearbyAccessPoints_multipleNonMatchingSSIDs()
        assertEquals(null, wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, true))
    }

    @Test fun findAccessPointByRegex_success_takeHighest_false() {
        mockNetworkUtil.nearbyAccessPoints_matchingSSID()
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoint(), wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, false))
    }

    @Test fun findAccessPointByRegex_success_takeHighest_true() {
        mockNetworkUtil.nearbyAccessPoints_matchingSSID()
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoint(), wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, true))
    }

    @Test fun findAccessPointByRegex_success_multipleSSIDs_sameRSSI_nonRegex_takeHighest_false() {
        mockNetworkUtil.nearbyAccessPoints_multipleSSIDs_sameRSSI(false)
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoint(), wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, false))
    }

    @Test fun findAccessPointByRegex_success_multipleSSIDs_sameRSSI_nonRegex_takeHighest_true() {
        mockNetworkUtil.nearbyAccessPoints_multipleSSIDs_sameRSSI(false)
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoint(), wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, true))
    }

    @Test fun findAccessPointByRegex_success_multipleSSIDs_sameRSSI_regex_takeHighest_false() {
        mockNetworkUtil.nearbyAccessPoints_multipleSSIDs_sameRSSI(true)
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoint(), wisefySearch.findAccessPointByRegex(TEST_REGEX, TEST_TIMEOUT, false))
    }

    @Test fun findAccessPointByRegex_success_multipleSSIDs_sameRSSI_regex_takeHighest_true() {
        mockNetworkUtil.nearbyAccessPoints_multipleSSIDs_sameRSSI(true)
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoint(), wisefySearch.findAccessPointByRegex(TEST_REGEX, TEST_TIMEOUT, true))
    }

    @Test fun findAccessPointByRegex_success_multipleMatchingSSIDs_accessPoint1HasHigherRSSI_takeHighest_false() {
        mockNetworkUtil.nearbyAccessPoints_multipleMatchingSSIDs_accessPoint1HasHigherRSSI(false)
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoint(), wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, false))
    }

    @Test fun findAccessPointByRegex_success_multipleMatchesSSIDs_accessPoint1HasHigherRSSI_takeHighest_true() {
        mockNetworkUtil.nearbyAccessPoints_multipleMatchingSSIDs_accessPoint1HasHigherRSSI(true)
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoint(), wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, true))
    }

    @Test fun findAccessPointByRegex_success_multipleMatchingSSIDs_accessPoint2HasHigherRSSI_takeHighest_false() {
        val takeHighest = false
        mockNetworkUtil.nearbyAccessPoints_multipleMatchingSSIDs_accessPoint2HasHigherRSSI(takeHighest)
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoint(), wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, takeHighest))
    }

    @Test fun findAccessPointByRegex_success_multipleMatchesSSIDs_accessPoint2HasHigherRSSI_takeHighest_true() {
        val takeHighest = true
        mockNetworkUtil.nearbyAccessPoints_multipleMatchingSSIDs_accessPoint2HasHigherRSSI(takeHighest)
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoint(), wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, takeHighest))
    }

    @Test fun findAccessPointByRegex_success_multipleMatchingSSIDs_sameRSSI_takeHighest_false() {
        mockNetworkUtil.nearbyAccessPoints_multipleMatchingSSIDs_sameRSSI(false)
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoint(), wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, false))
    }

    @Test fun findAccessPointByRegex_success_multipleMatchesSSIDs_sameRSSI_takeHighest_true() {
        mockNetworkUtil.nearbyAccessPoints_multipleMatchingSSIDs_sameRSSI(false)
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoint(), wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, true))
    }

    /*
   * findAccessPointsMatchingRegex tests
   */

    @Test fun findAccessPointsMatchingRegex_failure_nullAccessPoints_takeHighest_false() {
        mockNetworkUtil.nearbyAccessPoints_nullList()
        assertEquals(null, wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, false))
    }

    @Test fun findAccessPointsMatchingRegex_failure_nullAccessPoints_takeHighest_true() {
        mockNetworkUtil.nearbyAccessPoints_nullList()
        assertEquals(null, wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, true))
    }

    @Test fun findAccessPointsMatchingRegex_failure_emptyAccessPointList_takeHighest_false() {
        mockNetworkUtil.nearbyAccessPoints_emptyList()
        assertEquals(null, wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, false))
    }

    @Test fun findAccessPointsMatchingRegex_failure_emptyAccessPointList_takeHighest_true() {
        mockNetworkUtil.nearbyAccessPoints_emptyList()
        assertEquals(null, wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, true))
    }

    @Test fun findAccessPointsMatchingRegex_failure_nullAccessPoint_takeHighest_false() {
        mockNetworkUtil.nearbyAccessPoints_nullAccessPoint()
        assertEquals(null, wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, false))
    }

    @Test fun findAccessPointsMatchingRegex_failure_nullAccessPoint_takeHighest_true() {
        mockNetworkUtil.nearbyAccessPoints_nullAccessPoint()
        assertEquals(null, wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, true))
    }

    @Test fun findAccessPointsMatchingRegex_failure_nullSSID_takeHighest_false() {
        mockNetworkUtil.nearbyAccessPoints_nullSSID()
        assertEquals(null, wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, false))
    }

    @Test fun findAccessPointsMatchingRegex_failure_nullSSID_takeHighest_true() {
        mockNetworkUtil.nearbyAccessPoints_nullSSID()
        assertEquals(null, wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, true))
    }

    @Test fun findAccessPointsMatchingRegex_failure_nonMatchingSSID_takeHighest_false() {
        mockNetworkUtil.nearbyAccessPoints_nonMatchingSSID()
        assertEquals(null, wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, false))
    }

    @Test fun findAccessPointsMatchingRegex_failure_nonMatchingSSID_takeHighest_true() {
        mockNetworkUtil.nearbyAccessPoints_nonMatchingSSID()
        assertEquals(null, wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, true))
    }

    @Test fun findAccessPointsMatchingRegex_failure_multipleNonMatchingSSIDs_takeHighest_false() {
        mockNetworkUtil.nearbyAccessPoints_multipleNonMatchingSSIDs()
        assertEquals(null, wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, false))
    }

    @Test fun findAccessPointsMatchingRegex_failure_multipleNonMatchingSSIDs_takeHighest_true() {
        mockNetworkUtil.nearbyAccessPoints_multipleNonMatchingSSIDs()
        assertEquals(null, wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, true))
    }

    @Test fun findAccessPointsMatchingRegex_success_takeHighest_false() {
        mockNetworkUtil.nearbyAccessPoints_matchingSSID()
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoints(), wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, false))
    }

    @Test fun findAccessPointsMatchingRegex_success_takeHighest_true() {
        mockNetworkUtil.nearbyAccessPoints_matchingSSID()
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoints(), wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, true))
    }

    @Test fun findAccessPointsMatchingRegex_success_multipleSSIDs_nonRegex_takeHighest_false() {
        mockNetworkUtil.nearbyAccessPoints_multipleSSIDs_sameRSSI(false)
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoints(), wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, false))
    }

    @Test fun findAccessPointsMatchingRegex_success_multipleSSIDs_nonRegex_takeHighest_true() {
        mockNetworkUtil.nearbyAccessPoints_multipleSSIDs_sameRSSI(false)
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoints(), wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, true))
    }

    @Test fun findAccessPointsMatchingRegex_success_multipleSSIDs_regex_takeHighest_false() {
        mockNetworkUtil.nearbyAccessPoints_multipleSSIDs_sameRSSI(true)
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoints(), wisefySearch.findAccessPointsMatchingRegex(TEST_REGEX, false))
    }

    @Test fun findAccessPointsMatchingRegex_success_multipleSSIDs_regex_takeHighest_true() {
        mockNetworkUtil.nearbyAccessPoints_multipleSSIDs_sameRSSI(true)
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoints(), wisefySearch.findAccessPointsMatchingRegex(TEST_REGEX, true))
    }

    @Test fun findAccessPointsMatchingRegex_success_multipleMatchingSSIDs_accessPoint1HasHigherRSSI_takeHighest_false() {
        val takeHighest = false
        mockNetworkUtil.nearbyAccessPoints_multipleMatchingSSIDs_accessPoint1HasHigherRSSI(takeHighest)
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoints(), wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, takeHighest))
    }

    @Test fun findAccessPointsMatchingRegex_success_multipleMatchingSSIDs_accessPoint1HasHigherRSSI_takeHighest_true() {
        val takeHighest = true
        mockNetworkUtil.nearbyAccessPoints_multipleMatchingSSIDs_accessPoint1HasHigherRSSI(takeHighest)
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoints(), wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, takeHighest))
    }

    @Test fun findAccessPointsMatchingRegex_success_multipleMatchingSSIDs_accessPoint2HasHigherRSSI_takeHighest_false() {
        mockNetworkUtil.nearbyAccessPoints_multipleMatchingSSIDs_accessPoint2HasHigherRSSI(false)
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoints(), wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, false))
    }

    @Test fun findAccessPointsMatchingRegex_success_multipleMatchingSSIDs_accessPoint2HasHigherRSSI_takeHighest_true() {
        mockNetworkUtil.nearbyAccessPoints_multipleMatchingSSIDs_accessPoint2HasHigherRSSI(true)
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoints(), wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, true))
    }

    @Test fun findAccessPointsMatchingRegex_success_multipleMatchingSSIDs_sameRSSI_takeHighest_false() {
        mockNetworkUtil.nearbyAccessPoints_multipleMatchingSSIDs_sameRSSI(true)
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoints(), wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, false))
    }

    @Test fun findAccessPointsMatchingRegex_success_multipleMatchingSSIDs_sameRSSI_takeHighest_true() {
        mockNetworkUtil.nearbyAccessPoints_multipleMatchingSSIDs_sameRSSI(true)
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoints(), wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, true))
    }

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

    @Test fun findSavedNetworkByRegex_failure_nullWifiConfiguration() {
        mockNetworkUtil.savedNetworks_nullSavedNetwork()
        assertEquals(null, wisefySearch.findSavedNetworkByRegex(TEST_SSID))
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

    @Test fun findSavedNetworksMatchingRegex_failure_nullWifiConfiguration() {
        mockNetworkUtil.savedNetworks_nullSavedNetwork()
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
     * findSSIDsMatchingRegex tests
     */

    @Test fun findSSIDsMatchingRegex_failure_nullAccessPoints() {
        mockNetworkUtil.nearbyAccessPoints_nullList()
        assertEquals(null, wisefySearch.findSSIDsMatchingRegex(TEST_SSID))
    }

    @Test fun findSSIDsMatchingRegex_failure_emptySavedNetworkList() {
        mockNetworkUtil.nearbyAccessPoints_emptyList()
        assertEquals(null, wisefySearch.findSSIDsMatchingRegex(TEST_SSID))
    }

    @Test fun findSSIDsMatchingRegex_failure_nullAccessPoint() {
        mockNetworkUtil.nearbyAccessPoints_nullAccessPoint()
        assertEquals(null, wisefySearch.findSSIDsMatchingRegex(TEST_SSID))
    }

    @Test fun findSSIDsMatchingRegex_failure_nullSSID() {
        mockNetworkUtil.nearbyAccessPoints_nullSSID()
        assertEquals(null, wisefySearch.findSSIDsMatchingRegex(TEST_SSID))
    }

    @Test fun findSSIDsMatchingRegex_failure_nonMatchingSSID() {
        mockNetworkUtil.nearbyAccessPoints_nonMatchingSSID()
        assertEquals(null, wisefySearch.findSSIDsMatchingRegex(TEST_SSID))
    }

    @Test fun findSSIDsMatchingRegex_failure_multipleNonMatchingSSIDs() {
        mockNetworkUtil.nearbyAccessPoints_multipleNonMatchingSSIDs()
        assertEquals(null, wisefySearch.findSSIDsMatchingRegex(TEST_SSID))
    }

    @Test fun findSSIDsMatchingRegex_success() {
        mockNetworkUtil.nearbyAccessPoints_matchingSSID()
        assertEquals(mockNetworkUtil.getExpectedSSIDs(), wisefySearch.findSSIDsMatchingRegex(TEST_SSID))
    }

    @Test fun findSSIDsMatchingRegex_success_multipleMatchingSSIDs_sameRSSI() {
        mockNetworkUtil.nearbyAccessPoints_multipleMatchingSSIDs_sameRSSI(true)
        assertEquals(mockNetworkUtil.getExpectedSSIDs(), wisefySearch.findSSIDsMatchingRegex(TEST_SSID))
    }

    @Test fun findSSIDsMatchingRegex_success_multipleMatchingSSIDs_accessPoint1HasHigherRSSI() {
        mockNetworkUtil.nearbyAccessPoints_multipleMatchingSSIDs_accessPoint1HasHigherRSSI(true)
        assertEquals(mockNetworkUtil.getExpectedSSIDs(), wisefySearch.findSSIDsMatchingRegex(TEST_SSID))
    }

    @Test fun findSSIDsMatchingRegex_success_multipleMatchingSSIDs_accessPoint2HasHigherRSSI() {
        mockNetworkUtil.nearbyAccessPoints_multipleMatchingSSIDs_accessPoint2HasHigherRSSI(true)
        assertEquals(mockNetworkUtil.getExpectedSSIDs(), wisefySearch.findSSIDsMatchingRegex(TEST_SSID))
    }

    @Test fun findSSIDsMatchingRegex_success_multipleSSIDs_nonRegex() {
        mockNetworkUtil.nearbyAccessPoints_multipleSSIDs_sameRSSI(false)
        assertEquals(mockNetworkUtil.getExpectedSSIDs(), wisefySearch.findSSIDsMatchingRegex(TEST_SSID))
    }

    @Test fun findSSIDsMatchingRegex_success_multipleSSIDs_regex() {
        mockNetworkUtil.nearbyAccessPoints_multipleSSIDs_sameRSSI(true)
        assertEquals(mockNetworkUtil.getExpectedSSIDs(), wisefySearch.findSSIDsMatchingRegex(TEST_REGEX))
    }

    /*
     *  isNetworkASavedConfiguration tests
     */

    @Test fun isNetworkASavedConfiguration_failure_nullSSIDParam() {
        mockNetworkUtil.savedNetworks_nullList()
        assertEquals(false, wisefySearch.isNetworkASavedConfiguration(null))
    }

    @Test fun isNetworkASavedConfiguration_failure_nullSavedNetworkList() {
        mockNetworkUtil.savedNetworks_nullList()
        assertEquals(false, wisefySearch.isNetworkASavedConfiguration(TEST_SSID))
    }

    @Test fun isNetworkASavedConfiguration_failure_emptySavedNetworkList() {
        mockNetworkUtil.savedNetworks_emptyList()
        assertEquals(false, wisefySearch.isNetworkASavedConfiguration(TEST_SSID))
    }

    @Test fun isNetworkASavedConfiguration_failure_nullWifiConfiguration() {
        mockNetworkUtil.savedNetworks_nullSavedNetwork()
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

//    /*
//     *  removeEntriesWithLowerSignalStrength tests
//     */
//
//    @Test fun removeEntriesWithLowerSignalStrength_differentSSIDs() {
//        val accessPoints = mockNetworkUtil.nearbyAccessPoints_multipleSSIDs_sameRSSI(true)
//        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoints(), wisefySearch.removeEntriesWithLowerSignalStrength(accessPoints))
//    }
//
//    @Test fun removeEntriesWithLowerSignalStrength_sameSignalLevels() {
//        val accessPoints = mockNetworkUtil.nearbyAccessPoints_multipleMatchingSSIDs_sameRSSI(false)
//        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoints(), wisefySearch.removeEntriesWithLowerSignalStrength(accessPoints))
//    }
//
//    @Test fun removeEntriesWithLowerSignalStrength_accessPoint1Higher() {
//        val accessPoints = mockNetworkUtil.nearbyAccessPoints_multipleMatchingSSIDs_accessPoint1HasHigherRSSI(true)
//        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoints(), wisefySearch.removeEntriesWithLowerSignalStrength(accessPoints))
//    }
//
//    @Test fun removeEntriesWithLowerSignalStrength_accessPoint2Higher() {
//        val accessPoints = mockNetworkUtil.nearbyAccessPoints_multipleMatchingSSIDs_accessPoint2HasHigherRSSI(true)
//        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoints(), wisefySearch.removeEntriesWithLowerSignalStrength(accessPoints))
//    }
}
