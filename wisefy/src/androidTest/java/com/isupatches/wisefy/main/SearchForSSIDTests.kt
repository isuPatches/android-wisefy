package com.isupatches.wisefy.main

import com.isupatches.wisefy.BaseAndroidJUnit4TestClass
import com.isupatches.wisefy.TEST_SSID
import com.isupatches.wisefy.TEST_TIMEOUT
import com.isupatches.wisefy.VERIFICATION_SUCCESS_TIMEOUT
import com.isupatches.wisefy.callbacks.SearchForSSIDCallbacks
import com.isupatches.wisefy.constants.MISSING_PARAMETER

import org.junit.Assert.assertEquals
import org.junit.Test

import org.mockito.Mockito.mock
import org.mockito.Mockito.timeout
import org.mockito.Mockito.verify

/**
 * Used to test the functionality to search for SSIDs nearby.
 *
 * @author Patches
 */
internal class SearchForSSIDTests : BaseAndroidJUnit4TestClass() {

    @Test fun sync_failure_missingPrerequisite() {
        mockWiseFyPrechecksUtil.searchForSSID_failure()
        assertEquals(null, wisefy.searchForSSID(TEST_SSID, TEST_TIMEOUT))
    }

    @Test fun sync_failure() {
        mockWiseFyPrechecksUtil.searchForSSID_success()
        mockWiseFySearchUtil.findAccessPointByRegex_null()
        assertEquals(null, wisefy.searchForSSID(TEST_SSID, TEST_TIMEOUT))
    }

    @Test fun sync_success() {
        mockWiseFyPrechecksUtil.searchForSSID_success()
        mockWiseFySearchUtil.findAccessPointByRegex_success()
        assertEquals(TEST_SSID, wisefy.searchForSSID(TEST_SSID, TEST_TIMEOUT))
    }

    @Test fun async_failure_missingPrerequisite() {
        mockWiseFyPrechecksUtil.searchForSSID_failure()
        val mockCallbacks = mock(SearchForSSIDCallbacks::class.java)
        wisefy.searchForSSID(TEST_SSID, TEST_TIMEOUT, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).wisefyFailure(MISSING_PARAMETER)
    }

    @Test fun async_failure_missingPrerequisite_nullCallback() {
        mockWiseFyPrechecksUtil.searchForSSID_failure()
        nullCallbackUtil.callSearchForSSID(TEST_SSID)
    }

    @Test fun async_failure() {
        mockWiseFyPrechecksUtil.searchForSSID_success()
        mockWiseFySearchUtil.findAccessPointByRegex_null()
        val mockCallbacks = mock(SearchForSSIDCallbacks::class.java)
        wisefy.searchForSSID(TEST_SSID, TEST_TIMEOUT, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).ssidNotFound()
    }

    @Test fun async_failure_nullCallback() {
        mockWiseFyPrechecksUtil.searchForSSID_success()
        mockWiseFySearchUtil.findAccessPointByRegex_null()
        nullCallbackUtil.callSearchForSSID(TEST_SSID)
    }

    @Test fun async_success() {
        mockWiseFyPrechecksUtil.searchForSSID_success()
        mockWiseFySearchUtil.findAccessPointByRegex_success()
        val mockCallbacks = mock(SearchForSSIDCallbacks::class.java)
        wisefy.searchForSSID(TEST_SSID, TEST_TIMEOUT, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).ssidFound(TEST_SSID)
    }

    @Test fun async_success_nullCallback() {
        mockWiseFyPrechecksUtil.searchForSSID_success()
        mockWiseFySearchUtil.findAccessPointByRegex_success()
        nullCallbackUtil.callSearchForSSID(TEST_SSID)
    }
}
