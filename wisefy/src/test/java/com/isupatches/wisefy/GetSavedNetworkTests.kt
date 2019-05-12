package com.isupatches.wisefy

import android.os.Build

import com.isupatches.wisefy.callbacks.GetSavedNetworkCallbacks
import com.isupatches.wisefy.constants.MISSING_PARAMETER
import com.isupatches.wisefy.internal.base.BaseUnitTest

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

import org.mockito.Mockito.mock
import org.mockito.Mockito.timeout
import org.mockito.Mockito.verify

import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Tests the ability to retrieve a saved on a device.
 *
 * @author Patches
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.M])
internal class GetSavedNetworkTests : BaseUnitTest() {

    @Test fun sync_failure_prechecks() {
        mockWiseFyPrechecksUtil.getSavedNetwork_failure()
        assertEquals(null, wisefy.getSavedNetwork(TEST_SSID))
    }

    @Test fun sync_failure() {
        mockWiseFyPrechecksUtil.getSavedNetwork_success()
        mockWiseFySearchUtil.findSavedNetworkByRegex_null()
        assertEquals(null, wisefy.getSavedNetwork(TEST_SSID))
    }

    @Test fun sync_success() {
        mockWiseFyPrechecksUtil.getSavedNetwork_success()
        val savedNetwork = mockWiseFySearchUtil.findSavedNetworkByRegex_success()
        assertEquals(savedNetwork, wisefy.getSavedNetwork(TEST_SSID))
    }

    @Test fun async_failure_prechecks() {
        mockWiseFyPrechecksUtil.getSavedNetwork_failure()
        val mockCallbacks = mock(GetSavedNetworkCallbacks::class.java)
        wisefy.getSavedNetwork(TEST_SSID, mockCallbacks)
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
        wisefy.getSavedNetwork(TEST_SSID, mockCallbacks)
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
        wisefy.getSavedNetwork(TEST_SSID, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).retrievedSavedNetwork(savedNetwork)
    }

    @Test fun async_success_nullCallback() {
        mockWiseFyPrechecksUtil.getSavedNetwork_success()
        mockWiseFySearchUtil.findSavedNetworkByRegex_success()
        nullCallbackUtil.callGetSavedNetwork(TEST_SSID)
    }
}
