package com.isupatches.wisefy

import com.isupatches.wisefy.callbacks.SearchForSavedNetworksCallbacks
import com.isupatches.wisefy.constants.MISSING_PARAMETER
import com.isupatches.wisefy.internal.base.BaseInstrumentationTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.timeout
import org.mockito.Mockito.verify

/**
 * Tests the ability to search for saved networks on a device.
 *
 * @author Patches
 * @since 4.0
 */
internal class SearchForSavedNetworksTests : BaseInstrumentationTest() {

    @Test
    fun sync_failure_prechecks() {
        mockWiseFyPrechecksUtil.searchForSavedNetworks_failure()
        assertEquals(null, wisefy.searchForSavedNetworks(TEST_SSID))
        verificationUtil.didNotTryToGetSavedNetworks()
    }

    @Test
    fun sync_success() {
        mockWiseFyPrechecksUtil.searchForSavedNetworks_success()
        val savedNetwork = mockWiseFySearchUtil.findSavedNetworksMatchingRegex_success()
        assertEquals(savedNetwork, wisefy.searchForSavedNetworks(TEST_SSID))
    }

    @Test
    fun async_failure_prechecks() {
        mockWiseFyPrechecksUtil.searchForSavedNetworks_failure()
        val mockCallbacks = mock(SearchForSavedNetworksCallbacks::class.java)
        wisefy.searchForSavedNetworks(TEST_SSID, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).wisefyFailure(MISSING_PARAMETER)
    }

    @Test
    fun async_failure_prechecks_nullCallback() {
        mockWiseFyPrechecksUtil.searchForSavedNetworks_failure()
        nullCallbackUtil.callSearchForSavedNetworks(TEST_SSID)
        wisefy.searchForSavedNetworks(TEST_SSID, null)
    }

    @Test
    fun async_failure_nullSavedNetworks() {
        mockWiseFyPrechecksUtil.searchForSavedNetworks_success()
        mockWiseFySearchUtil.findSavedNetworksByRegex_null()
        val mockCallbacks = mock(SearchForSavedNetworksCallbacks::class.java)
        wisefy.searchForSavedNetworks(TEST_SSID, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).noSavedNetworksFound()
    }

    @Test
    fun async_failure_nullSavedNetworks_nullCallback() {
        mockWiseFyPrechecksUtil.searchForSavedNetworks_success()
        mockWiseFySearchUtil.findSavedNetworksByRegex_null()
        nullCallbackUtil.callSearchForSavedNetworks(TEST_SSID)
    }

    @Test
    fun async_failure_emptyConfiguredNetworks() {
        mockWiseFyPrechecksUtil.searchForSavedNetworks_success()
        mockWiseFySearchUtil.findSavedNetworksByRegex_emptyList()
        val mockCallbacks = mock(SearchForSavedNetworksCallbacks::class.java)
        wisefy.searchForSavedNetworks(TEST_SSID, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).noSavedNetworksFound()
    }

    @Test
    fun async_failure_emptyConfiguredNetworks_nullCallback() {
        mockWiseFyPrechecksUtil.searchForSavedNetworks_success()
        mockWiseFySearchUtil.findSavedNetworksByRegex_emptyList()
        nullCallbackUtil.callSearchForSavedNetworks(TEST_SSID)
    }

    @Test
    fun async_success() {
        mockWiseFyPrechecksUtil.searchForSavedNetworks_success()
        val savedNetworks = mockWiseFySearchUtil.findSavedNetworksMatchingRegex_success()
        val mockCallbacks = mock(SearchForSavedNetworksCallbacks::class.java)
        wisefy.searchForSavedNetworks(TEST_SSID, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).retrievedSavedNetworks(savedNetworks)
    }

    @Test
    fun async_success_nullCallbacks() {
        mockWiseFyPrechecksUtil.searchForSavedNetworks_success()
        mockWiseFySearchUtil.findSavedNetworksMatchingRegex_success()
        nullCallbackUtil.callSearchForSavedNetworks(TEST_SSID)
    }
}
