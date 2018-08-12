package com.isupatches.wisefy.main

import org.junit.Assert.assertEquals

import org.mockito.Mockito.mock
import org.mockito.Mockito.timeout
import org.mockito.Mockito.verify

import com.isupatches.wisefy.BaseAndroidJUnit4TestClass
import com.isupatches.wisefy.TEST_SSID
import com.isupatches.wisefy.TEST_TIMEOUT
import com.isupatches.wisefy.VERIFICATION_SUCCESS_TIMEOUT
import com.isupatches.wisefy.callbacks.SearchForAccessPointCallbacks
import com.isupatches.wisefy.constants.MISSING_PARAMETER

import org.junit.Test

/**
 * Used to test the functionality to search for a set of nearby access points.
 *
 * @author Patches
 */
internal class SearchForAccessPointTests : BaseAndroidJUnit4TestClass() {

    @Test fun sync_failure_prechecks_filterDuplicates_false() {
        mockWiseFyPrechecksUtil.searchForAccessPoint_failure()
        assertEquals(null, wiseFy.searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, false))
    }

    @Test fun sync_failure_prechecks_filterDuplicates_true() {
        mockWiseFyPrechecksUtil.searchForAccessPoint_failure()
        assertEquals(null, wiseFy.searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, true))
    }

    @Test fun sync_failure_filterDuplicates_false() {
        mockWiseFyPrechecksUtil.searchForAccessPoint_success()
        mockWiseFySearchUtil.findAccessPointByRegex_null()
        assertEquals(null, wiseFy.searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, false))
    }

    @Test fun sync_failure_filterDuplicates_true() {
        mockWiseFyPrechecksUtil.searchForAccessPoint_success()
        mockWiseFySearchUtil.findAccessPointByRegex_null()
        assertEquals(null, wiseFy.searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, true))
    }

    @Test fun sync_success_filterDuplicates_false() {
        mockWiseFyPrechecksUtil.searchForAccessPoint_success()
        val accessPoint = mockWiseFySearchUtil.findAccessPointByRegex_success()
        assertEquals(accessPoint, wiseFy.searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, false))
    }

    @Test fun sync_success_filterDuplicates_true() {
        mockWiseFyPrechecksUtil.searchForAccessPoint_success()
        val accessPoint = mockWiseFySearchUtil.findAccessPointByRegex_success()
        assertEquals(accessPoint, wiseFy.searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, true))
    }

    @Test fun async_failure_prechecks_filterDuplicates_false() {
        mockWiseFyPrechecksUtil.searchForAccessPoint_failure()
        val mockCallbacks = mock(SearchForAccessPointCallbacks::class.java)
        wiseFy.searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, false, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).wisefyFailure(MISSING_PARAMETER)
    }

    @Test fun async_failure_prechecks_filterDuplicates_false_nullCallback() {
        mockWiseFyPrechecksUtil.searchForAccessPoint_failure()
        nullCallbackUtil.callSearchForAccessPoint(TEST_SSID, false)
    }

    @Test fun async_failure_prechecks_filterDuplicates_true() {
        mockWiseFyPrechecksUtil.searchForAccessPoint_failure()
        val mockCallbacks = mock(SearchForAccessPointCallbacks::class.java)
        wiseFy.searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, true, mockCallbacks)
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
        wiseFy.searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, false, mockCallbacks)
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
        wiseFy.searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, true, mockCallbacks)
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
        wiseFy.searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, false, mockCallbacks)
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
        wiseFy.searchForAccessPoint(TEST_SSID, TEST_TIMEOUT, true, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).accessPointFound(accessPoint)
    }

    @Test fun async_success_filterDuplicates_true_nullCallback() {
        mockWiseFyPrechecksUtil.searchForAccessPoint_success()
        mockWiseFySearchUtil.findAccessPointByRegex_success()
        nullCallbackUtil.callSearchForAccessPoint(TEST_SSID, true)
    }
}
