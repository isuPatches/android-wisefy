package com.isupatches.wisefy

import com.isupatches.wisefy.callbacks.SearchForSavedNetworkCallbacks
import com.isupatches.wisefy.constants.MISSING_PARAMETER
import com.isupatches.wisefy.internal.base.BaseInstrumentationTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.timeout
import org.mockito.Mockito.verify

/**
 * Tests the ability to search for a saved network on a device.
 *
 * @author Patches
 * @since 4.0
 */
internal class SearchForSavedNetworkTests : BaseInstrumentationTest() {

    @Test fun sync_failure_prechecks() {
        mockWiseFyPrechecksUtil.searchForSavedNetwork_failure()
        assertEquals(null, wisefy.searchForSavedNetwork(TEST_SSID))
    }

    @Test fun sync_failure() {
        mockWiseFyPrechecksUtil.searchForSavedNetwork_success()
        mockWiseFySearchUtil.findSavedNetworkByRegex_null()
        assertEquals(null, wisefy.searchForSavedNetwork(TEST_SSID))
    }

    @Test fun sync_success() {
        mockWiseFyPrechecksUtil.searchForSavedNetwork_success()
        val savedNetwork = mockWiseFySearchUtil.findSavedNetworkByRegex_success()
        assertEquals(savedNetwork, wisefy.searchForSavedNetwork(TEST_SSID))
    }

    @Test fun async_failure_prechecks() {
        mockWiseFyPrechecksUtil.searchForSavedNetwork_failure()
        val mockCallbacks = mock(SearchForSavedNetworkCallbacks::class.java)
        wisefy.searchForSavedNetwork(TEST_SSID, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).wisefyFailure(MISSING_PARAMETER)
    }

    @Test fun async_failure_prechecks_nullCallback() {
        mockWiseFyPrechecksUtil.searchForSavedNetwork_failure()
        nullCallbackUtil.callSearchForSavedNetwork(TEST_SSID)
    }

    @Test fun async_failure() {
        mockWiseFyPrechecksUtil.searchForSavedNetwork_success()
        mockWiseFySearchUtil.findSavedNetworkByRegex_null()
        val mockCallbacks = mock(SearchForSavedNetworkCallbacks::class.java)
        wisefy.searchForSavedNetwork(TEST_SSID, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).savedNetworkNotFound()
    }

    @Test fun async_failure_nullCallback() {
        mockWiseFyPrechecksUtil.searchForSavedNetwork_success()
        mockWiseFySearchUtil.findSavedNetworkByRegex_null()
        nullCallbackUtil.callSearchForSavedNetwork(TEST_SSID)
    }

    @Test fun async_success() {
        mockWiseFyPrechecksUtil.searchForSavedNetwork_success()
        val savedNetwork = mockWiseFySearchUtil.findSavedNetworkByRegex_success()
        val mockCallbacks = mock(SearchForSavedNetworkCallbacks::class.java)
        wisefy.searchForSavedNetwork(TEST_SSID, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).retrievedSavedNetwork(savedNetwork)
    }

    @Test fun async_success_nullCallback() {
        mockWiseFyPrechecksUtil.searchForSavedNetwork_success()
        mockWiseFySearchUtil.findSavedNetworkByRegex_success()
        nullCallbackUtil.callSearchForSavedNetwork(TEST_SSID)
    }
}
