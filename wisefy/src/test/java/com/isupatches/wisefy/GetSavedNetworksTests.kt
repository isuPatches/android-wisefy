package com.isupatches.wisefy

import android.os.Build

import com.isupatches.wisefy.callbacks.GetSavedNetworksCallbacks
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
 * Tests the ability to retrieve a list of saved networks on a device.
 *
 * @author Patches
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.M])
internal class GetSavedNetworksTests : BaseUnitTest() {

    @Test fun sync_failure_prechecks() {
        mockWiseFyPrechecksUtil.getSavedNetworks_failure()
        assertEquals(null, wisefy.getSavedNetworks())
        verificationUtil.didNotTryToGetSavedNetworks()
    }

    @Test fun sync_success() {
        mockWiseFyPrechecksUtil.getSavedNetworks_success()
        val savedNetworks = mockNetworkUtil.savedNetworks()
        assertEquals(savedNetworks, wisefy.getSavedNetworks())
        verificationUtil.triedToGetSavedNetworks()
    }

    @Test fun async_failure_prechecks() {
        mockWiseFyPrechecksUtil.getSavedNetworks_failure()
        val mockCallbacks = mock(GetSavedNetworksCallbacks::class.java)
        wisefy.getSavedNetworks(mockCallbacks)
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
        wisefy.getSavedNetworks(mockCallbacks)
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
        wisefy.getSavedNetworks(mockCallbacks)
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
        wisefy.getSavedNetworks(mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).retrievedSavedNetworks(savedNetworks)
        verificationUtil.triedToGetSavedNetworks()
    }

    @Test fun async_success_nullCallback() {
        mockWiseFyPrechecksUtil.getSavedNetworks_success()
        mockNetworkUtil.savedNetworks()
        nullCallbackUtil.callGetSavedNetworks()
        verificationUtil.triedToGetSavedNetworks()
    }
}
