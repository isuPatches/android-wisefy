package com.isupatches.wisefy

import com.isupatches.wisefy.callbacks.RemoveNetworkCallbacks
import com.isupatches.wisefy.constants.MISSING_PARAMETER
import com.isupatches.wisefy.internal.base.BaseInstrumentationTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.timeout
import org.mockito.Mockito.verify

/**
 * Tests the ability to remove a network from a device's list of saved networks.
 *
 * @author Patches
 * @since 3.0
 */
internal class RemoveNetworkTests : BaseInstrumentationTest() {

    @Test fun sync_failure_prechecks() {
        mockWiseFyPrechecksUtil.removeNetwork_failure()
        assertEquals(false, wisefy.removeNetwork(TEST_SSID))
        verificationUtil.didNotTryToRemoveNetwork()
    }

    @Test fun sync_failure_noSavedNetwork() {
        mockWiseFyPrechecksUtil.removeNetwork_success()
        mockWiseFySearchUtil.findSavedNetworkByRegex_null()
        assertEquals(false, wisefy.removeNetwork(TEST_SSID))
        verificationUtil.didNotTryToRemoveNetwork()
    }

    @Test fun sync_failure() {
        mockWiseFyPrechecksUtil.removeNetwork_success()
        mockWiseFySearchUtil.findSavedNetworkByRegex_success()
        mockNetworkUtil.removeNetwork(false)
        assertEquals(false, wisefy.removeNetwork(TEST_SSID))
        verificationUtil.triedToRemoveNetwork()
    }

    @Test fun sync_success() {
        mockWiseFyPrechecksUtil.removeNetwork_success()
        mockWiseFySearchUtil.findSavedNetworkByRegex_success()
        mockNetworkUtil.removeNetwork(true)
        assertEquals(true, wisefy.removeNetwork(TEST_SSID))
        verificationUtil.triedToRemoveNetwork()
    }

    @Test fun async_failure_prechecks() {
        mockWiseFyPrechecksUtil.removeNetwork_failure()
        val mockCallbacks = mock(RemoveNetworkCallbacks::class.java)
        wisefy.removeNetwork(TEST_SSID, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).wisefyFailure(MISSING_PARAMETER)
        verificationUtil.didNotTryToRemoveNetwork()
    }

    @Test fun async_failure_prechecks_nullCallback() {
        mockWiseFyPrechecksUtil.removeNetwork_failure()
        nullCallbackUtil.callRemoveNetwork(TEST_SSID)
        verificationUtil.didNotTryToRemoveNetwork()
    }

    @Test fun async_failure_noSavedNetwork() {
        mockWiseFyPrechecksUtil.removeNetwork_success()
        mockWiseFySearchUtil.findSavedNetworkByRegex_null()
        val mockCallbacks = mock(RemoveNetworkCallbacks::class.java)
        wisefy.removeNetwork(TEST_SSID, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).networkNotFoundToRemove()
        verificationUtil.didNotTryToRemoveNetwork()
    }

    @Test fun async_failure_noSavedNetwork_nullCallback() {
        mockWiseFyPrechecksUtil.removeNetwork_success()
        mockWiseFySearchUtil.findSavedNetworkByRegex_null()
        nullCallbackUtil.callRemoveNetwork(TEST_SSID)
        verificationUtil.didNotTryToRemoveNetwork()
    }

    @Test fun async_failure() {
        mockWiseFyPrechecksUtil.removeNetwork_success()
        mockWiseFySearchUtil.findSavedNetworkByRegex_success()
        mockNetworkUtil.removeNetwork(false)
        val mockCallbacks = mock(RemoveNetworkCallbacks::class.java)
        wisefy.removeNetwork(TEST_SSID, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).failureRemovingNetwork()
        verificationUtil.triedToRemoveNetwork()
    }

    @Test fun async_failure_nullCallback() {
        mockWiseFyPrechecksUtil.removeNetwork_success()
        mockWiseFySearchUtil.findSavedNetworkByRegex_success()
        mockNetworkUtil.removeNetwork(false)
        nullCallbackUtil.callRemoveNetwork(TEST_SSID)
        verificationUtil.triedToRemoveNetwork()
    }

    @Test fun async_success() {
        mockWiseFyPrechecksUtil.removeNetwork_success()
        mockWiseFySearchUtil.findSavedNetworkByRegex_success()
        mockNetworkUtil.removeNetwork(true)
        val mockCallbacks = mock(RemoveNetworkCallbacks::class.java)
        wisefy.removeNetwork(TEST_SSID, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).networkRemoved()
        verificationUtil.triedToRemoveNetwork()
    }

    @Test fun async_success_nullCallback() {
        mockWiseFyPrechecksUtil.removeNetwork_success()
        mockWiseFySearchUtil.findSavedNetworkByRegex_success()
        mockNetworkUtil.removeNetwork(true)
        nullCallbackUtil.callRemoveNetwork(TEST_SSID)
        verificationUtil.triedToRemoveNetwork()
    }
}
