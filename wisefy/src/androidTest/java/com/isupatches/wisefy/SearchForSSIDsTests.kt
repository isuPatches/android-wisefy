package com.isupatches.wisefy

import com.isupatches.wisefy.callbacks.SearchForSSIDsCallbacks
import com.isupatches.wisefy.constants.MISSING_PARAMETER
import com.isupatches.wisefy.internal.base.BaseInstrumentationTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.timeout
import org.mockito.Mockito.verify

/**
 * Used to test the functionality to search for SSIDs nearby.
 *
 * @author Patches
 * @since 3.0
 */
internal class SearchForSSIDsTests : BaseInstrumentationTest() {

    @Test
    fun sync_failure_precheck() {
        mockWiseFyPrechecksUtil.searchForSSIDs_failure()
        assertEquals(null, wisefy.searchForSSIDs(TEST_SSID))
    }

    @Test
    fun sync_failure() {
        mockWiseFyPrechecksUtil.searchForSSIDs_success()
        mockWiseFySearchUtil.findSSIDsMatchingRegex_null()
        assertEquals(null, wisefy.searchForSSIDs(TEST_SSID))
    }

    @Test
    fun sync_success() {
        mockWiseFyPrechecksUtil.searchForSSIDs_success()
        val ssids = mockWiseFySearchUtil.findSSIDsMatchingRegex_success()
        assertEquals(ssids, wisefy.searchForSSIDs(TEST_SSID))
    }

    @Test
    fun async_failure_prechecks() {
        mockWiseFyPrechecksUtil.searchForSSIDs_failure()
        val mockCallbacks = mock(SearchForSSIDsCallbacks::class.java)
        wisefy.searchForSSIDs(TEST_SSID, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).wisefyFailure(MISSING_PARAMETER)
    }

    @Test
    fun async_failure_prechecks_nullCallback() {
        mockWiseFyPrechecksUtil.searchForSSIDs_failure()
        nullCallbackUtil.callSearchForSSIDs(TEST_SSID)
    }

    @Test
    fun async_failure() {
        mockWiseFyPrechecksUtil.searchForSSIDs_success()
        mockWiseFySearchUtil.findSSIDsMatchingRegex_null()
        val mockCallbacks = mock(SearchForSSIDsCallbacks::class.java)
        wisefy.searchForSSIDs(TEST_SSID, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).noSSIDsFound()
    }

    @Test
    fun async_failure_nullCallback() {
        mockWiseFyPrechecksUtil.searchForSSIDs_success()
        mockWiseFySearchUtil.findSSIDsMatchingRegex_null()
        nullCallbackUtil.callSearchForSSIDs(TEST_SSID)
    }

    @Test
    fun async_success() {
        mockWiseFyPrechecksUtil.searchForSSIDs_success()
        val ssids = mockWiseFySearchUtil.findSSIDsMatchingRegex_success()
        val mockCallbacks = mock(SearchForSSIDsCallbacks::class.java)
        wisefy.searchForSSIDs(TEST_SSID, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).retrievedSSIDs(ssids)
    }

    @Test
    fun async_success_nullCallback() {
        mockWiseFyPrechecksUtil.searchForSSIDs_success()
        mockWiseFySearchUtil.findSSIDsMatchingRegex_success()
        nullCallbackUtil.callSearchForSSIDs(TEST_SSID)
    }
}
