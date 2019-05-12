package com.isupatches.wisefy.search

import android.os.Build

import com.isupatches.wisefy.TEST_REGEX
import com.isupatches.wisefy.TEST_SSID
import com.isupatches.wisefy.TEST_TIMEOUT
import com.isupatches.wisefy.internal.base.BaseUnitTest

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith

import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Used to test the WiseFySearch class and search capabilities.
 *
 * @see WiseFySearch
 *
 * @author Patches
 */
@Suppress("LargeClass", "TooManyFunctions")
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.JELLY_BEAN])
internal class WiseFySearchLegacyTests : BaseUnitTest() {

    private val wisefySearch: WiseFySearch = WiseFySearchLegacy.create(mockWifiManager)

    /*
     * findAccessPointByRegex tests
     */

    @Test fun findAccessPointByRegex_failure_nullAccessPoints_takeHighest_false() {
        mockNetworkUtil.nearbyAccessPoints_nullList()
        assertEquals(null, wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, false))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findAccessPointByRegex_failure_nullAccessPoints_takeHighest_true() {
        mockNetworkUtil.nearbyAccessPoints_nullList()
        assertEquals(null, wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, true))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findAccessPointByRegex_failure_emptyAccessPointList_takeHighest_false() {
        mockNetworkUtil.nearbyAccessPoints_emptyList()
        assertEquals(null, wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, false))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findAccessPointByRegex_failure_emptyAccessPointList_takeHighest_true() {
        mockNetworkUtil.nearbyAccessPoints_emptyList()
        assertEquals(null, wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, true))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findAccessPointByRegex_failure_nullAccessPoint_takeHighest_false() {
        mockNetworkUtil.nearbyAccessPoints_nullAccessPoint()
        assertEquals(null, wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, false))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findAccessPointByRegex_failure_nullAccessPoint_takeHighest_true() {
        mockNetworkUtil.nearbyAccessPoints_nullAccessPoint()
        assertEquals(null, wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, true))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findAccessPointByRegex_failure_nullSSID_takeHighest_false() {
        mockNetworkUtil.nearbyAccessPoints_nullSSID()
        assertEquals(null, wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, false))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findAccessPointByRegex_failure_nullSSID_takeHighest_true() {
        mockNetworkUtil.nearbyAccessPoints_nullSSID()
        assertEquals(null, wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, true))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findAccessPointByRegex_failure_nonMatchingSSID_takeHighest_false() {
        mockNetworkUtil.nearbyAccessPoints_nonMatchingSSID()
        assertEquals(null, wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, false))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findAccessPointByRegex_failure_nonMatchingSSID_takeHighest_true() {
        mockNetworkUtil.nearbyAccessPoints_nonMatchingSSID()
        assertEquals(null, wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, true))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findAccessPointByRegex_failure_multipleNonMatchingSSIDs_takeHighest_false() {
        mockNetworkUtil.nearbyAccessPoints_multipleNonMatchingSSIDs()
        assertEquals(null, wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, false))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findAccessPointByRegex_failure_multipleNonMatchingSSIDs_takeHighest_true() {
        mockNetworkUtil.nearbyAccessPoints_multipleNonMatchingSSIDs()
        assertEquals(null, wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, true))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findAccessPointByRegex_success_takeHighest_false() {
        mockNetworkUtil.nearbyAccessPoints_matchingSSID()
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoint(), wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, false))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findAccessPointByRegex_success_takeHighest_true() {
        mockNetworkUtil.nearbyAccessPoints_matchingSSID()
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoint(), wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, true))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findAccessPointByRegex_success_multipleSSIDs_sameRSSI_nonRegex_takeHighest_false() {
        mockNetworkUtil.nearbyAccessPoints_multipleSSIDs_sameRSSI(false)
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoint(), wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, false))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findAccessPointByRegex_success_multipleSSIDs_sameRSSI_nonRegex_takeHighest_true() {
        mockNetworkUtil.nearbyAccessPoints_multipleSSIDs_sameRSSI(false)
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoint(), wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, true))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findAccessPointByRegex_success_multipleSSIDs_sameRSSI_regex_takeHighest_false() {
        mockNetworkUtil.nearbyAccessPoints_multipleSSIDs_sameRSSI(true)
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoint(), wisefySearch.findAccessPointByRegex(TEST_REGEX, TEST_TIMEOUT, false))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findAccessPointByRegex_success_multipleSSIDs_sameRSSI_regex_takeHighest_true() {
        mockNetworkUtil.nearbyAccessPoints_multipleSSIDs_sameRSSI(true)
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoint(), wisefySearch.findAccessPointByRegex(TEST_REGEX, TEST_TIMEOUT, true))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findAccessPointByRegex_success_multipleMatchingSSIDs_accessPoint1HasHigherRSSI_takeHighest_false() {
        mockNetworkUtil.nearbyAccessPoints_multipleMatchingSSIDs_accessPoint1HasHigherRSSI(false)
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoint(), wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, false))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findAccessPointByRegex_success_multipleMatchesSSIDs_accessPoint1HasHigherRSSI_takeHighest_true() {
        mockNetworkUtil.nearbyAccessPoints_multipleMatchingSSIDs_accessPoint1HasHigherRSSI(true)
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoint(), wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, true))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findAccessPointByRegex_success_multipleMatchingSSIDs_accessPoint2HasHigherRSSI_takeHighest_false() {
        val takeHighest = false
        mockNetworkUtil.nearbyAccessPoints_multipleMatchingSSIDs_accessPoint2HasHigherRSSI(takeHighest)
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoint(), wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, takeHighest))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findAccessPointByRegex_success_multipleMatchesSSIDs_accessPoint2HasHigherRSSI_takeHighest_true() {
        val takeHighest = true
        mockNetworkUtil.nearbyAccessPoints_multipleMatchingSSIDs_accessPoint2HasHigherRSSI(takeHighest)
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoint(), wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, takeHighest))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findAccessPointByRegex_success_multipleMatchingSSIDs_sameRSSI_takeHighest_false() {
        mockNetworkUtil.nearbyAccessPoints_multipleMatchingSSIDs_sameRSSI(false)
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoint(), wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, false))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findAccessPointByRegex_success_multipleMatchesSSIDs_sameRSSI_takeHighest_true() {
        mockNetworkUtil.nearbyAccessPoints_multipleMatchingSSIDs_sameRSSI(false)
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoint(), wisefySearch.findAccessPointByRegex(TEST_SSID, TEST_TIMEOUT, true))
        verificationUtil.triedToScanForAccessPoints()
    }

    /*
     * findAccessPointsMatchingRegex tests
     */

    @Test fun findAccessPointsMatchingRegex_failure_nullAccessPoints_takeHighest_false() {
        mockNetworkUtil.nearbyAccessPoints_nullList()
        assertEquals(null, wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, false))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findAccessPointsMatchingRegex_failure_nullAccessPoints_takeHighest_true() {
        mockNetworkUtil.nearbyAccessPoints_nullList()
        assertEquals(null, wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, true))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findAccessPointsMatchingRegex_failure_emptyAccessPointList_takeHighest_false() {
        mockNetworkUtil.nearbyAccessPoints_emptyList()
        assertEquals(null, wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, false))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findAccessPointsMatchingRegex_failure_emptyAccessPointList_takeHighest_true() {
        mockNetworkUtil.nearbyAccessPoints_emptyList()
        assertEquals(null, wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, true))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findAccessPointsMatchingRegex_failure_nullAccessPoint_takeHighest_false() {
        mockNetworkUtil.nearbyAccessPoints_nullAccessPoint()
        assertEquals(null, wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, false))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findAccessPointsMatchingRegex_failure_nullAccessPoint_takeHighest_true() {
        mockNetworkUtil.nearbyAccessPoints_nullAccessPoint()
        assertEquals(null, wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, true))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findAccessPointsMatchingRegex_failure_nullSSID_takeHighest_false() {
        mockNetworkUtil.nearbyAccessPoints_nullSSID()
        assertEquals(null, wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, false))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findAccessPointsMatchingRegex_failure_nullSSID_takeHighest_true() {
        mockNetworkUtil.nearbyAccessPoints_nullSSID()
        assertEquals(null, wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, true))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findAccessPointsMatchingRegex_failure_nonMatchingSSID_takeHighest_false() {
        mockNetworkUtil.nearbyAccessPoints_nonMatchingSSID()
        assertEquals(null, wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, false))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findAccessPointsMatchingRegex_failure_nonMatchingSSID_takeHighest_true() {
        mockNetworkUtil.nearbyAccessPoints_nonMatchingSSID()
        assertEquals(null, wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, true))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findAccessPointsMatchingRegex_failure_multipleNonMatchingSSIDs_takeHighest_false() {
        mockNetworkUtil.nearbyAccessPoints_multipleNonMatchingSSIDs()
        assertEquals(null, wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, false))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findAccessPointsMatchingRegex_failure_multipleNonMatchingSSIDs_takeHighest_true() {
        mockNetworkUtil.nearbyAccessPoints_multipleNonMatchingSSIDs()
        assertEquals(null, wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, true))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findAccessPointsMatchingRegex_success_takeHighest_false() {
        mockNetworkUtil.nearbyAccessPoints_matchingSSID()
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoints(), wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, false))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findAccessPointsMatchingRegex_success_takeHighest_true() {
        mockNetworkUtil.nearbyAccessPoints_matchingSSID()
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoints(), wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, true))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findAccessPointsMatchingRegex_success_multipleSSIDs_nonRegex_takeHighest_false() {
        mockNetworkUtil.nearbyAccessPoints_multipleSSIDs_sameRSSI(false)
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoints(), wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, false))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findAccessPointsMatchingRegex_success_multipleSSIDs_nonRegex_takeHighest_true() {
        mockNetworkUtil.nearbyAccessPoints_multipleSSIDs_sameRSSI(false)
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoints(), wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, true))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findAccessPointsMatchingRegex_success_multipleSSIDs_regex_takeHighest_false() {
        mockNetworkUtil.nearbyAccessPoints_multipleSSIDs_sameRSSI(true)
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoints(), wisefySearch.findAccessPointsMatchingRegex(TEST_REGEX, false))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findAccessPointsMatchingRegex_success_multipleSSIDs_regex_takeHighest_true() {
        mockNetworkUtil.nearbyAccessPoints_multipleSSIDs_sameRSSI(true)
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoints(), wisefySearch.findAccessPointsMatchingRegex(TEST_REGEX, true))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findAccessPointsMatchingRegex_success_multipleMatchingSSIDs_accessPoint1HasHigherRSSI_takeHighest_false() {
        val takeHighest = false
        mockNetworkUtil.nearbyAccessPoints_multipleMatchingSSIDs_accessPoint1HasHigherRSSI(takeHighest)
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoints(), wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, takeHighest))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findAccessPointsMatchingRegex_success_multipleMatchingSSIDs_accessPoint1HasHigherRSSI_takeHighest_true() {
        val takeHighest = true
        mockNetworkUtil.nearbyAccessPoints_multipleMatchingSSIDs_accessPoint1HasHigherRSSI(takeHighest)
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoints(), wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, takeHighest))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findAccessPointsMatchingRegex_success_multipleMatchingSSIDs_accessPoint2HasHigherRSSI_takeHighest_false() {
        mockNetworkUtil.nearbyAccessPoints_multipleMatchingSSIDs_accessPoint2HasHigherRSSI(false)
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoints(), wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, false))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findAccessPointsMatchingRegex_success_multipleMatchingSSIDs_accessPoint2HasHigherRSSI_takeHighest_true() {
        mockNetworkUtil.nearbyAccessPoints_multipleMatchingSSIDs_accessPoint2HasHigherRSSI(true)
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoints(), wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, true))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findAccessPointsMatchingRegex_success_multipleMatchingSSIDs_sameRSSI_takeHighest_false() {
        mockNetworkUtil.nearbyAccessPoints_multipleMatchingSSIDs_sameRSSI(true)
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoints(), wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, false))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findAccessPointsMatchingRegex_success_multipleMatchingSSIDs_sameRSSI_takeHighest_true() {
        mockNetworkUtil.nearbyAccessPoints_multipleMatchingSSIDs_sameRSSI(true)
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoints(), wisefySearch.findAccessPointsMatchingRegex(TEST_SSID, true))
        verificationUtil.triedToScanForAccessPoints()
    }

    /*
     * findSSIDsMatchingRegex tests
     */

    @Test fun findSSIDsMatchingRegex_failure_nullAccessPoints() {
        mockNetworkUtil.nearbyAccessPoints_nullList()
        assertEquals(null, wisefySearch.findSSIDsMatchingRegex(TEST_SSID))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findSSIDsMatchingRegex_failure_emptySavedNetworkList() {
        mockNetworkUtil.nearbyAccessPoints_emptyList()
        assertEquals(null, wisefySearch.findSSIDsMatchingRegex(TEST_SSID))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findSSIDsMatchingRegex_failure_nullAccessPoint() {
        mockNetworkUtil.nearbyAccessPoints_nullAccessPoint()
        assertEquals(null, wisefySearch.findSSIDsMatchingRegex(TEST_SSID))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findSSIDsMatchingRegex_failure_nullSSID() {
        mockNetworkUtil.nearbyAccessPoints_nullSSID()
        assertEquals(null, wisefySearch.findSSIDsMatchingRegex(TEST_SSID))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findSSIDsMatchingRegex_failure_nonMatchingSSID() {
        mockNetworkUtil.nearbyAccessPoints_nonMatchingSSID()
        assertEquals(null, wisefySearch.findSSIDsMatchingRegex(TEST_SSID))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findSSIDsMatchingRegex_failure_multipleNonMatchingSSIDs() {
        mockNetworkUtil.nearbyAccessPoints_multipleNonMatchingSSIDs()
        assertEquals(null, wisefySearch.findSSIDsMatchingRegex(TEST_SSID))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findSSIDsMatchingRegex_success() {
        mockNetworkUtil.nearbyAccessPoints_matchingSSID()
        assertEquals(mockNetworkUtil.getExpectedSSIDs(), wisefySearch.findSSIDsMatchingRegex(TEST_SSID))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findSSIDsMatchingRegex_success_multipleMatchingSSIDs_sameRSSI() {
        mockNetworkUtil.nearbyAccessPoints_multipleMatchingSSIDs_sameRSSI(true)
        assertEquals(mockNetworkUtil.getExpectedSSIDs(), wisefySearch.findSSIDsMatchingRegex(TEST_SSID))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findSSIDsMatchingRegex_success_multipleMatchingSSIDs_accessPoint1HasHigherRSSI() {
        mockNetworkUtil.nearbyAccessPoints_multipleMatchingSSIDs_accessPoint1HasHigherRSSI(true)
        assertEquals(mockNetworkUtil.getExpectedSSIDs(), wisefySearch.findSSIDsMatchingRegex(TEST_SSID))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findSSIDsMatchingRegex_success_multipleMatchingSSIDs_accessPoint2HasHigherRSSI() {
        mockNetworkUtil.nearbyAccessPoints_multipleMatchingSSIDs_accessPoint2HasHigherRSSI(true)
        assertEquals(mockNetworkUtil.getExpectedSSIDs(), wisefySearch.findSSIDsMatchingRegex(TEST_SSID))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findSSIDsMatchingRegex_success_multipleSSIDs_nonRegex() {
        mockNetworkUtil.nearbyAccessPoints_multipleSSIDs_sameRSSI(false)
        assertEquals(mockNetworkUtil.getExpectedSSIDs(), wisefySearch.findSSIDsMatchingRegex(TEST_SSID))
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun findSSIDsMatchingRegex_success_multipleSSIDs_regex() {
        mockNetworkUtil.nearbyAccessPoints_multipleSSIDs_sameRSSI(true)
        assertEquals(mockNetworkUtil.getExpectedSSIDs(), wisefySearch.findSSIDsMatchingRegex(TEST_REGEX))
        verificationUtil.triedToScanForAccessPoints()
    }

    /*
     *  getNearbyAccessPoints tests
     */

    @Test fun getNearbyAccessPoints_nullAccessPointList_filterDuplicates_true() {
        mockNetworkUtil.nearbyAccessPoints_nullList()
        val accessPoints = wisefySearch.getNearbyAccessPoints(true)
        assertNull(accessPoints)
        verificationUtil.triedToGetNearbyAccessPoints()
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun getNearbyAccessPoints_emptyAccessPointList_filterDuplicates_true() {
        mockNetworkUtil.nearbyAccessPoints_emptyList()
        val accessPoints = wisefySearch.getNearbyAccessPoints(true)
        assertNull(accessPoints)
        verificationUtil.triedToGetNearbyAccessPoints()
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun getNearbyAccessPoints_differentSSIDs_filterDuplicates_true() {
        mockNetworkUtil.nearbyAccessPoints_multipleSSIDs_sameRSSI(true)
        val accessPoints = wisefySearch.getNearbyAccessPoints(true)
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoints(), accessPoints)
        verificationUtil.triedToGetNearbyAccessPoints()
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun getNearbyAccessPoints_sameSignalLevels_filterDuplicates_true() {
        mockNetworkUtil.nearbyAccessPoints_multipleMatchingSSIDs_sameRSSI(false)
        val accessPoints = wisefySearch.getNearbyAccessPoints(true)
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoints(), accessPoints)
        verificationUtil.triedToGetNearbyAccessPoints()
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun getNearbyAccessPoints_accessPoint1Higher_filterDuplicates_true() {
        mockNetworkUtil.nearbyAccessPoints_multipleMatchingSSIDs_accessPoint1HasHigherRSSI(true)
        val accessPoints = wisefySearch.getNearbyAccessPoints(true)
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoints(), accessPoints)
        verificationUtil.triedToGetNearbyAccessPoints()
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun getNearbyAccessPoints_accessPoint2Higher_filterDuplicates_true() {
        mockNetworkUtil.nearbyAccessPoints_multipleMatchingSSIDs_accessPoint2HasHigherRSSI(true)
        val accessPoints = wisefySearch.getNearbyAccessPoints(true)
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoints(), accessPoints)
        verificationUtil.triedToGetNearbyAccessPoints()
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun getNearbyAccessPoints_nullAccessPointList_filterDuplicates_false() {
        mockNetworkUtil.nearbyAccessPoints_nullList()
        val accessPoints = wisefySearch.getNearbyAccessPoints(false)
        assertNull(accessPoints)
        verificationUtil.triedToGetNearbyAccessPoints()
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun getNearbyAccessPoints_emptyAccessPointList_filterDuplicates_false() {
        mockNetworkUtil.nearbyAccessPoints_emptyList()
        val accessPoints = wisefySearch.getNearbyAccessPoints(false)
        assertNull(accessPoints)
        verificationUtil.triedToGetNearbyAccessPoints()
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun getNearbyAccessPoints_differentSSIDs_filterDuplicates_false() {
        mockNetworkUtil.nearbyAccessPoints_multipleSSIDs_sameRSSI(true)
        val accessPoints = wisefySearch.getNearbyAccessPoints(false)
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoints(), accessPoints)
        verificationUtil.triedToGetNearbyAccessPoints()
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun getNearbyAccessPoints_sameSignalLevels_filterDuplicates_false() {
        mockNetworkUtil.nearbyAccessPoints_multipleMatchingSSIDs_sameRSSI(true)
        val accessPoints = wisefySearch.getNearbyAccessPoints(false)
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoints(), accessPoints)
        verificationUtil.triedToGetNearbyAccessPoints()
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun getNearbyAccessPoints_accessPoint1Higher_filterDuplicates_false() {
        mockNetworkUtil.nearbyAccessPoints_multipleMatchingSSIDs_accessPoint1HasHigherRSSI(false)
        val accessPoints = wisefySearch.getNearbyAccessPoints(false)
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoints(), accessPoints)
        verificationUtil.triedToGetNearbyAccessPoints()
        verificationUtil.triedToScanForAccessPoints()
    }

    @Test fun getNearbyAccessPoints_accessPoint2Higher_filterDuplicates_false() {
        mockNetworkUtil.nearbyAccessPoints_multipleMatchingSSIDs_accessPoint2HasHigherRSSI(false)
        val accessPoints = wisefySearch.getNearbyAccessPoints(false)
        assertEquals(mockNetworkUtil.getExpectedNearbyAccessPoints(), accessPoints)
        verificationUtil.triedToGetNearbyAccessPoints()
        verificationUtil.triedToScanForAccessPoints()
    }
}
