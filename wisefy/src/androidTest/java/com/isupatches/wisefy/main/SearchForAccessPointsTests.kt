package com.isupatches.wisefy.main

import com.isupatches.wisefy.BaseAndroidJUnit4TestClass
import com.isupatches.wisefy.TEST_SSID
import com.isupatches.wisefy.VERIFICATION_SUCCESS_TIMEOUT
import com.isupatches.wisefy.callbacks.SearchForAccessPointsCallbacks
import com.isupatches.wisefy.constants.MISSING_PARAMETER
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.timeout
import org.mockito.Mockito.verify

/**
 * Used to test the functionality to search for a nearby access point.
 *
 * @author Patches
 */
internal class SearchForAccessPointsTests : BaseAndroidJUnit4TestClass() {

    @Test fun sync_failure_prechecks_filterDuplicates_false() {
        mockWiseFyPrechecksUtil.searchForAccessPoints_failure()
        assertEquals(null, wisefy.searchForAccessPoints(TEST_SSID, false))
    }

    @Test fun sync_failure_prechecks_filterDuplicates_true() {
        mockWiseFyPrechecksUtil.searchForAccessPoints_failure()
        assertEquals(null, wisefy.searchForAccessPoints(TEST_SSID, true))
    }

    @Test fun sync_failure_filterDuplicates_false() {
        mockWiseFyPrechecksUtil.searchForAccessPoints_success()
        mockWiseFySearchUtil.findAccessPointsMatchingRegex_null()
        assertEquals(null, wisefy.searchForAccessPoints(TEST_SSID, false))
    }

    @Test fun sync_failure_filterDuplicates_true() {
        mockWiseFyPrechecksUtil.searchForAccessPoints_success()
        mockWiseFySearchUtil.findAccessPointsMatchingRegex_null()
        assertEquals(null, wisefy.searchForAccessPoints(TEST_SSID, true))
    }

    @Test fun sync_success_filterDuplicates_false() {
        mockWiseFyPrechecksUtil.searchForAccessPoints_success()
        val accessPoints = mockWiseFySearchUtil.findAccessPointsMatchingRegex_success()
        assertEquals(accessPoints, wisefy.searchForAccessPoints(TEST_SSID, false))
    }

    @Test fun sync_success_filterDuplicates_true() {
        mockWiseFyPrechecksUtil.searchForAccessPoints_success()
        val accessPoints = mockWiseFySearchUtil.findAccessPointsMatchingRegex_success()
        assertEquals(accessPoints, wisefy.searchForAccessPoints(TEST_SSID, true))
    }

    @Test fun async_failure_prechecks_filterDuplicates_false() {
        mockWiseFyPrechecksUtil.searchForAccessPoints_failure()
        val mockCallbacks = mock(SearchForAccessPointsCallbacks::class.java)
        wisefy.searchForAccessPoints(TEST_SSID, false, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).wisefyFailure(MISSING_PARAMETER)
    }

    @Test fun async_failure_prechecks_filterDuplicates_false_nullCallback() {
        mockWiseFyPrechecksUtil.searchForAccessPoints_failure()
        nullCallbackUtil.callSearchForAccessPoints(TEST_SSID, false)
    }

    @Test fun async_failure_prechecks_filterDuplicates_true() {
        mockWiseFyPrechecksUtil.searchForAccessPoints_failure()
        val mockCallbacks = mock(SearchForAccessPointsCallbacks::class.java)
        wisefy.searchForAccessPoints(TEST_SSID, true, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).wisefyFailure(MISSING_PARAMETER)
    }

    @Test fun async_failure_prechecks_filterDuplicates_true_nullCallback() {
        mockWiseFyPrechecksUtil.searchForAccessPoints_failure()
        nullCallbackUtil.callSearchForAccessPoints(TEST_SSID, true)
    }

    @Test fun async_failure_filterDuplicates_false() {
        mockWiseFyPrechecksUtil.searchForAccessPoints_success()
        mockWiseFySearchUtil.findAccessPointsMatchingRegex_null()
        val mockCallbacks = mock(SearchForAccessPointsCallbacks::class.java)
        wisefy.searchForAccessPoints(TEST_SSID, false, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).noAccessPointsFound()
    }

    @Test fun async_failure_filterDuplicates_false_nullCallback() {
        mockWiseFyPrechecksUtil.searchForAccessPoints_success()
        mockWiseFySearchUtil.findAccessPointsMatchingRegex_null()
        nullCallbackUtil.callSearchForAccessPoints(TEST_SSID, false)
    }

    @Test fun async_failure_filterDuplicates_true() {
        mockWiseFyPrechecksUtil.searchForAccessPoints_success()
        mockWiseFySearchUtil.findAccessPointsMatchingRegex_null()
        val mockCallbacks = mock(SearchForAccessPointsCallbacks::class.java)
        wisefy.searchForAccessPoints(TEST_SSID, true, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).noAccessPointsFound()
    }

    @Test fun async_failure_filterDuplicates_true_nullCallback() {
        mockWiseFyPrechecksUtil.searchForAccessPoints_success()
        mockWiseFySearchUtil.findAccessPointsMatchingRegex_null()
        nullCallbackUtil.callSearchForAccessPoints(TEST_SSID, true)
    }

    @Test fun async_success_filterDuplicates_false() {
        mockWiseFyPrechecksUtil.searchForAccessPoints_success()
        val accessPoints = mockWiseFySearchUtil.findAccessPointsMatchingRegex_success()
        val mockCallbacks = mock(SearchForAccessPointsCallbacks::class.java)
        wisefy.searchForAccessPoints(TEST_SSID, false, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).foundAccessPoints(accessPoints)
    }

    @Test fun async_success_filterDuplicates_false_nullCallback() {
        mockWiseFyPrechecksUtil.searchForAccessPoints_success()
        mockWiseFySearchUtil.findAccessPointsMatchingRegex_success()
        nullCallbackUtil.callSearchForAccessPoints(TEST_SSID, false)
    }

    @Test fun async_success_filterDuplicates_true() {
        mockWiseFyPrechecksUtil.searchForAccessPoints_success()
        val accessPoints = mockWiseFySearchUtil.findAccessPointsMatchingRegex_success()
        val mockCallbacks = mock(SearchForAccessPointsCallbacks::class.java)
        wisefy.searchForAccessPoints(TEST_SSID, true, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).foundAccessPoints(accessPoints)
    }

    @Test fun async_success_filterDuplicates_true_nullCallback() {
        mockWiseFyPrechecksUtil.searchForAccessPoints_success()
        mockWiseFySearchUtil.findAccessPointsMatchingRegex_success()
        nullCallbackUtil.callSearchForAccessPoints(TEST_SSID, true)
    }
}
