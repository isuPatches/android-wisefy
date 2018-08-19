package com.isupatches.wisefy.main

import com.isupatches.wisefy.BaseAndroidJUnit4TestClass
import com.isupatches.wisefy.TEST_SSID
import com.isupatches.wisefy.VERIFICATION_SUCCESS_TIMEOUT
import com.isupatches.wisefy.callbacks.GetSavedNetworksCallbacks
import com.isupatches.wisefy.constants.MISSING_PARAMETER
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.timeout
import org.mockito.Mockito.verify

/**
 * Tests the ability to retrieve a list of saved networks on a device.
 *
 * @author Patches
 */
internal class GetSavedNetworksTests : BaseAndroidJUnit4TestClass() {

    @Test fun sync_failure_prechecks() {
        mockWiseFyPrechecksUtil.getSavedNetworks_failure()
        assertEquals(null, wiseFy.getSavedNetworks())
        verificationUtil.didNotTryToGetSavedNetworks()
    }

    @Test fun sync_success() {
        mockWiseFyPrechecksUtil.getSavedNetworks_success()
        val savedNetworks = mockNetworkUtil.savedNetworks()
        assertEquals(savedNetworks, wiseFy.getSavedNetworks())
        verificationUtil.triedToGetSavedNetworks()
    }

    @Test fun async_failure_prechecks() {
        mockWiseFyPrechecksUtil.getSavedNetworks_failure()
        val mockCallbacks = mock(GetSavedNetworksCallbacks::class.java)
        wiseFy.getSavedNetworks(mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).wisefyFailure(MISSING_PARAMETER)
        verificationUtil.didNotTryToGetSavedNetworks()
    }

    @Test fun async_failure_prechecks_nullCallback() {
        mockWiseFyPrechecksUtil.getSavedNetworks_failure()
        nullCallbackUtil.callGetSavedNetworks()
        verificationUtil.didNotTryToGetSavedNetworks()
    }

    @Test fun async_failure_nullSavedNetworks() {
        mockWiseFyPrechecksUtil.getSavedNetworks_success()
        mockNetworkUtil.getConfiguredNetworks_null()
        val mockCallbacks = mock(GetSavedNetworksCallbacks::class.java)
        wiseFy.getSavedNetworks(mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).noSavedNetworksFound()
        verificationUtil.triedToGetSavedNetworks()
    }

    @Test fun async_failure_nullSavedNetworks_nullCallback() {
        mockWiseFyPrechecksUtil.getSavedNetworks_success()
        mockNetworkUtil.getConfiguredNetworks_null()
        nullCallbackUtil.callGetSavedNetworks()
        verificationUtil.triedToGetSavedNetworks()
    }

    @Test fun async_failure_emptyConfiguredNetworks() {
        mockWiseFyPrechecksUtil.getSavedNetworks_success()
        mockNetworkUtil.getConfiguredNetworks_emptyList()
        val mockCallbacks = mock(GetSavedNetworksCallbacks::class.java)
        wiseFy.getSavedNetworks(mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).noSavedNetworksFound()
        verificationUtil.triedToGetSavedNetworks()
    }

    @Test fun async_failure_emptyConfiguredNetworks_nullCallback() {
        mockWiseFyPrechecksUtil.getSavedNetworks_success()
        mockNetworkUtil.getConfiguredNetworks_emptyList()
        nullCallbackUtil.callGetSavedNetworks()
        verificationUtil.triedToGetSavedNetworks()
    }

    @Test fun async_success() {
        mockWiseFyPrechecksUtil.getSavedNetworks_success()
        val savedNetworks = mockNetworkUtil.savedNetworks()
        val mockCallbacks = mock(GetSavedNetworksCallbacks::class.java)
        wiseFy.getSavedNetworks(mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).retrievedSavedNetworks(savedNetworks)
        verificationUtil.triedToGetSavedNetworks()
    }

    @Test fun async_success_nullCallback() {
        mockWiseFyPrechecksUtil.getSavedNetworks_success()
        mockNetworkUtil.savedNetworks()
        nullCallbackUtil.callGetSavedNetworks()
        verificationUtil.triedToGetSavedNetworks()
    }

    @Test fun sync_failure_prechecks_withRegex() {
        mockWiseFyPrechecksUtil.getSavedNetworks_failure()
        assertEquals(null, wiseFy.getSavedNetworks(TEST_SSID))
        verificationUtil.didNotTryToGetSavedNetworks()
    }

    @Test fun sync_success_withRegex() {
        mockWiseFyPrechecksUtil.getSavedNetworks_success()
        val savedNetwork = mockWiseFySearchUtil.findSavedNetworksMatchingRegex_success()
        assertEquals(savedNetwork, wiseFy.getSavedNetworks(TEST_SSID))
    }

    @Test fun async_failure_prechecks_withRegex() {
        mockWiseFyPrechecksUtil.getSavedNetworks_failure()
        val mockCallbacks = mock(GetSavedNetworksCallbacks::class.java)
        wiseFy.getSavedNetworks(TEST_SSID, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).wisefyFailure(MISSING_PARAMETER)
    }

    @Test fun async_failure_prechecks_withRegex_nullCallback() {
        mockWiseFyPrechecksUtil.getSavedNetworks_failure()
        nullCallbackUtil.callGetSavedNetworks_withRegex(TEST_SSID)
        wiseFy.getSavedNetworks(TEST_SSID, null)
    }

    @Test fun async_failure_withRegex_nullSavedNetworks() {
        mockWiseFyPrechecksUtil.getSavedNetworks_success()
        mockWiseFySearchUtil.findSavedNetworksByRegex_null()
        val mockCallbacks = mock(GetSavedNetworksCallbacks::class.java)
        wiseFy.getSavedNetworks(TEST_SSID, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).noSavedNetworksFound()
    }

    @Test fun async_failure_withRegex_nullSavedNetworks_nullCallback() {
        mockWiseFyPrechecksUtil.getSavedNetworks_success()
        mockWiseFySearchUtil.findSavedNetworksByRegex_null()
        nullCallbackUtil.callGetSavedNetworks_withRegex(TEST_SSID)
    }

    @Test fun async_failure_withRegex_emptyConfiguredNetworks() {
        mockWiseFyPrechecksUtil.getSavedNetworks_success()
        mockWiseFySearchUtil.findSavedNetworksByRegex_emptyList()
        val mockCallbacks = mock(GetSavedNetworksCallbacks::class.java)
        wiseFy.getSavedNetworks(TEST_SSID, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).noSavedNetworksFound()
    }

    @Test fun async_failure_withRegex_emptyConfiguredNetworks_nullCallback() {
        mockWiseFyPrechecksUtil.getSavedNetworks_success()
        mockWiseFySearchUtil.findSavedNetworksByRegex_emptyList()
        nullCallbackUtil.callGetSavedNetworks_withRegex(TEST_SSID)
    }

    @Test fun async_success_withRegex() {
        mockWiseFyPrechecksUtil.getSavedNetworks_success()
        val savedNetworks = mockWiseFySearchUtil.findSavedNetworksMatchingRegex_success()
        val mockCallbacks = mock(GetSavedNetworksCallbacks::class.java)
        wiseFy.getSavedNetworks(TEST_SSID, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).retrievedSavedNetworks(savedNetworks)
    }

    @Test fun async_success_withRegex_nullCallbacks() {
        mockWiseFyPrechecksUtil.getSavedNetworks_success()
        mockWiseFySearchUtil.findSavedNetworksMatchingRegex_success()
        nullCallbackUtil.callGetSavedNetworks_withRegex(TEST_SSID)
    }
}
