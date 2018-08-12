package com.isupatches.wisefy.main

import com.isupatches.wisefy.BaseAndroidJUnit4TestClass
import org.junit.Assert.assertEquals

import org.mockito.Mockito.mock
import org.mockito.Mockito.timeout
import org.mockito.Mockito.verify

import com.isupatches.wisefy.TEST_SSID
import com.isupatches.wisefy.VERIFICATION_SUCCESS_TIMEOUT
import com.isupatches.wisefy.callbacks.SearchForSSIDsCallbacks
import com.isupatches.wisefy.constants.MISSING_PARAMETER

import org.junit.Test

/**
 * Used to test the functionality to search for SSIDs nearby.
 *
 * @author Patches
 */
internal class SearchForSSIDsTests : BaseAndroidJUnit4TestClass() {

    @Test fun sync_failure_precheck() {
        mockWiseFyPrechecksUtil.searchForSSIDs_failure()
        assertEquals(null, wiseFy.searchForSSIDs(TEST_SSID))
    }

    @Test fun sync_failure() {
        mockWiseFyPrechecksUtil.searchForSSIDs_success()
        mockWiseFySearchUtil.findSSIDsMatchingRegex_null()
        assertEquals(null, wiseFy.searchForSSIDs(TEST_SSID))
    }

    @Test fun sync_success() {
        mockWiseFyPrechecksUtil.searchForSSIDs_success()
        val ssids = mockWiseFySearchUtil.findSSIDsMatchingRegex_success()
        assertEquals(ssids, wiseFy.searchForSSIDs(TEST_SSID))
    }

    @Test fun async_failure_prechecks() {
        mockWiseFyPrechecksUtil.searchForSSIDs_failure()
        val mockCallbacks = mock(SearchForSSIDsCallbacks::class.java)
        wiseFy.searchForSSIDs(TEST_SSID, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).wisefyFailure(MISSING_PARAMETER)
    }

    @Test fun async_failure_prechecks_nullCallback() {
        mockWiseFyPrechecksUtil.searchForSSIDs_failure()
        nullCallbackUtil.callSearchForSSIDs(TEST_SSID)
    }

    @Test fun async_failure() {
        mockWiseFyPrechecksUtil.searchForSSIDs_success()
        mockWiseFySearchUtil.findSSIDsMatchingRegex_null()
        val mockCallbacks = mock(SearchForSSIDsCallbacks::class.java)
        wiseFy.searchForSSIDs(TEST_SSID, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).noSSIDsFound()
    }


    @Test fun async_failure_nullCallback() {
        mockWiseFyPrechecksUtil.searchForSSIDs_success()
        mockWiseFySearchUtil.findSSIDsMatchingRegex_null()
        nullCallbackUtil.callSearchForSSIDs(TEST_SSID)
    }

    @Test fun async_success() {
        mockWiseFyPrechecksUtil.searchForSSIDs_success()
        val ssids = mockWiseFySearchUtil.findSSIDsMatchingRegex_success()
        val mockCallbacks = mock(SearchForSSIDsCallbacks::class.java)
        wiseFy.searchForSSIDs(TEST_SSID, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).retrievedSSIDs(ssids)
    }

    @Test fun async_success_nullCallback() {
        mockWiseFyPrechecksUtil.searchForSSIDs_success()
        mockWiseFySearchUtil.findSSIDsMatchingRegex_success()
        nullCallbackUtil.callSearchForSSIDs(TEST_SSID)
    }
}
