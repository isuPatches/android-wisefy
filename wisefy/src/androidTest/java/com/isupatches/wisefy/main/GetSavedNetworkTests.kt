package com.isupatches.wisefy.main

import org.junit.Assert.assertEquals
import org.mockito.Mockito.mock
import org.mockito.Mockito.timeout
import org.mockito.Mockito.verify

import com.isupatches.wisefy.BaseAndroidJUnit4TestClass
import com.isupatches.wisefy.TEST_SSID
import com.isupatches.wisefy.VERIFICATION_SUCCESS_TIMEOUT
import com.isupatches.wisefy.callbacks.GetSavedNetworkCallbacks
import com.isupatches.wisefy.constants.MISSING_PARAMETER

import org.junit.Test

/**
 * Tests the ability to retrieve a saved on a device.
 *
 * @author Patches
 */
internal class GetSavedNetworkTests : BaseAndroidJUnit4TestClass() {

    @Test fun sync_failure_prechecks() {
        mockWiseFyPrechecksUtil.getSavedNetwork_failure()
        assertEquals(null, wiseFy.getSavedNetwork(TEST_SSID))
    }

    @Test fun sync_failure() {
        mockWiseFyPrechecksUtil.getSavedNetwork_success()
        mockWiseFySearchUtil.findSavedNetworkByRegex_null()
        assertEquals(null, wiseFy.getSavedNetwork(TEST_SSID))
    }

    @Test fun sync_success() {
        mockWiseFyPrechecksUtil.getSavedNetwork_success()
        val savedNetwork = mockWiseFySearchUtil.findSavedNetworkByRegex_success()
        assertEquals(savedNetwork, wiseFy.getSavedNetwork(TEST_SSID))
    }

    @Test fun async_failure_prechecks() {
        mockWiseFyPrechecksUtil.getSavedNetwork_failure()
        val mockCallbacks = mock(GetSavedNetworkCallbacks::class.java)
        wiseFy.getSavedNetwork(TEST_SSID, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).wisefyFailure(MISSING_PARAMETER)
    }

    @Test fun async_failure_prechecks_nullCallback() {
        mockWiseFyPrechecksUtil.getSavedNetwork_failure()
        nullCallbackUtil.callGetSavedNetwork(TEST_SSID)
    }

    @Test fun async_failure() {
        mockWiseFyPrechecksUtil.getSavedNetwork_success()
        mockWiseFySearchUtil.findSavedNetworkByRegex_null()
        val mockCallbacks = mock(GetSavedNetworkCallbacks::class.java)
        wiseFy.getSavedNetwork(TEST_SSID, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).savedNetworkNotFound()
    }

    @Test fun async_failure_nullCallback() {
        mockWiseFyPrechecksUtil.getSavedNetwork_success()
        mockWiseFySearchUtil.findSavedNetworkByRegex_null()
        nullCallbackUtil.callGetSavedNetwork(TEST_SSID)
    }

    @Test fun async_success() {
        mockWiseFyPrechecksUtil.getSavedNetwork_success()
        val savedNetwork = mockWiseFySearchUtil.findSavedNetworkByRegex_success()
        val mockCallbacks = mock(GetSavedNetworkCallbacks::class.java)
        wiseFy.getSavedNetwork(TEST_SSID, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).retrievedSavedNetwork(savedNetwork)
    }

    @Test fun async_success_nullCallback() {
        mockWiseFyPrechecksUtil.getSavedNetwork_success()
        mockWiseFySearchUtil.findSavedNetworkByRegex_success()
        nullCallbackUtil.callGetSavedNetwork(TEST_SSID)
    }
}
