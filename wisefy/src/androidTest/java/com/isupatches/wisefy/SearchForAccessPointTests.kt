package com.isupatches.wisefy

import com.isupatches.wisefy.callbacks.SearchForAccessPointCallbacks
import com.isupatches.wisefy.constants.MISSING_PARAMETER
import com.isupatches.wisefy.internal.base.BaseInstrumentationTest

import org.junit.Assert.assertEquals
import org.junit.Test

import org.mockito.Mockito.mock
import org.mockito.Mockito.timeout
import org.mockito.Mockito.verify

/**
 * Used to test the functionality to search for a set of nearby access points.
 *
 * @author Patches
 * @since 3.0
 */
internal class SearchForAccessPointTests : BaseInstrumentationTest() {

    @Test fun sync_failure_prechecks_filterDuplicates_false() {
        mockWiseFyPrechecksUtil.searchForAccessPoint_failure()
        assertEquals(null, wisefy.searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, false))
    }

    @Test fun sync_failure_prechecks_filterDuplicates_true() {
        mockWiseFyPrechecksUtil.searchForAccessPoint_failure()
        assertEquals(null, wisefy.searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, true))
    }

    @Test fun sync_failure_filterDuplicates_false() {
        mockWiseFyPrechecksUtil.searchForAccessPoint_success()
        mockWiseFySearchUtil.findAccessPointByRegex_null()
        assertEquals(null, wisefy.searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, false))
    }

    @Test fun sync_failure_filterDuplicates_true() {
        mockWiseFyPrechecksUtil.searchForAccessPoint_success()
        mockWiseFySearchUtil.findAccessPointByRegex_null()
        assertEquals(null, wisefy.searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, true))
    }

    @Test fun sync_success_filterDuplicates_false() {
        mockWiseFyPrechecksUtil.searchForAccessPoint_success()
        val accessPoint = mockWiseFySearchUtil.findAccessPointByRegex_success()
        assertEquals(accessPoint, wisefy.searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, false))
    }

    @Test fun sync_success_filterDuplicates_true() {
        mockWiseFyPrechecksUtil.searchForAccessPoint_success()
        val accessPoint = mockWiseFySearchUtil.findAccessPointByRegex_success()
        assertEquals(accessPoint, wisefy.searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, true))
    }

    @Test fun async_failure_prechecks_filterDuplicates_false() {
        mockWiseFyPrechecksUtil.searchForAccessPoint_failure()
        val mockCallbacks = mock(SearchForAccessPointCallbacks::class.java)
        wisefy.searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, false, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).wisefyFailure(MISSING_PARAMETER)
    }

    @Test fun async_failure_prechecks_filterDuplicates_false_nullCallback() {
        mockWiseFyPrechecksUtil.searchForAccessPoint_failure()
        nullCallbackUtil.callSearchForAccessPoint(TEST_SSID, false)
    }

    @Test fun async_failure_prechecks_filterDuplicates_true() {
        mockWiseFyPrechecksUtil.searchForAccessPoint_failure()
        val mockCallbacks = mock(SearchForAccessPointCallbacks::class.java)
        wisefy.searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, true, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).wisefyFailure(MISSING_PARAMETER)
    }

    @Test fun async_failure_prechecks_filterDuplicates_true_nullCallback() {
        mockWiseFyPrechecksUtil.searchForAccessPoint_failure()
        nullCallbackUtil.callSearchForAccessPoint(TEST_SSID, true)
    }

    @Test fun async_failure_filterDuplicates_false() {
        mockWiseFyPrechecksUtil.searchForAccessPoint_success()
        mockWiseFySearchUtil.findAccessPointByRegex_null()
        val mockCallbacks = mock(SearchForAccessPointCallbacks::class.java)
        wisefy.searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, false, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).accessPointNotFound()
    }

    @Test fun async_failure_filterDuplicates_false_nullCallback() {
        mockWiseFyPrechecksUtil.searchForAccessPoint_success()
        mockWiseFySearchUtil.findAccessPointByRegex_null()
        nullCallbackUtil.callSearchForAccessPoint(TEST_SSID, false)
    }

    @Test fun async_failure_filterDuplicates_true() {
        mockWiseFyPrechecksUtil.searchForAccessPoint_success()
        mockWiseFySearchUtil.findAccessPointByRegex_null()
        val mockCallbacks = mock(SearchForAccessPointCallbacks::class.java)
        wisefy.searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, true, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).accessPointNotFound()
    }

    @Test fun async_failure_filterDuplicates_true_nullCallback() {
        mockWiseFyPrechecksUtil.searchForAccessPoint_success()
        mockWiseFySearchUtil.findAccessPointByRegex_null()
        nullCallbackUtil.callSearchForAccessPoint(TEST_SSID, true)
    }

    @Test fun async_success_filterDuplicates_false() {
        mockWiseFyPrechecksUtil.searchForAccessPoint_success()
        val accessPoint = mockWiseFySearchUtil.findAccessPointByRegex_success()
        val mockCallbacks = mock(SearchForAccessPointCallbacks::class.java)
        wisefy.searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, false, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).accessPointFound(accessPoint)
    }

    @Test fun async_success_filterDuplicates_false_nullCallback() {
        mockWiseFyPrechecksUtil.searchForAccessPoint_success()
        mockWiseFySearchUtil.findAccessPointByRegex_success()
        nullCallbackUtil.callSearchForAccessPoint(TEST_SSID, false)
    }

    @Test fun async_success_filterDuplicates_true() {
        mockWiseFyPrechecksUtil.searchForAccessPoint_success()
        val accessPoint = mockWiseFySearchUtil.findAccessPointByRegex_success()
        val mockCallbacks = mock(SearchForAccessPointCallbacks::class.java)
        wisefy.searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, true, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).accessPointFound(accessPoint)
    }

    @Test fun async_success_filterDuplicates_true_nullCallback() {
        mockWiseFyPrechecksUtil.searchForAccessPoint_success()
        mockWiseFySearchUtil.findAccessPointByRegex_success()
        nullCallbackUtil.callSearchForAccessPoint(TEST_SSID, true)
    }
}
