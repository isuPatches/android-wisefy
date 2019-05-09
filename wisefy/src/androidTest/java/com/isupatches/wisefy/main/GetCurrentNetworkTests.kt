package com.isupatches.wisefy.main

import com.isupatches.wisefy.BaseAndroidJUnit4TestClass
import com.isupatches.wisefy.TEST_SSID
import com.isupatches.wisefy.VERIFICATION_SUCCESS_TIMEOUT
import com.isupatches.wisefy.callbacks.GetCurrentNetworkCallbacks
import com.isupatches.wisefy.constants.MISSING_PARAMETER
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.timeout
import org.mockito.Mockito.verify

/**
 * Tests the ability to retrieve a device's current network.
 *
 * @author Patches
 */
internal class GetCurrentNetworkTests : BaseAndroidJUnit4TestClass() {

    @Test fun sync_failure_prechecks() {
        mockWiseFyPrechecksUtil.getCurrentNetwork_failure()
        assertEquals(null, wisefy.getCurrentNetwork())
        verificationUtil.didNotTryToGetCurrentNetwork()
    }

    @Test fun sync_failure_nullCurrentNetwork() {
        mockWiseFyPrechecksUtil.getCurrentNetwork_success()
        assertEquals(null, wisefy.getCurrentNetwork())
        verificationUtil.triedToGetCurrentNetwork()
    }

    @Test fun sync_success() {
        mockWiseFyPrechecksUtil.getCurrentNetwork_success()
        mockNetworkUtil.currentNetwork(TEST_SSID)
        val currentNetwork = wisefy.getCurrentNetwork()
        if (currentNetwork != null) {
            assertEquals(TEST_SSID, currentNetwork.ssid)
            verificationUtil.triedToGetCurrentNetwork()
        } else {
            fail()
        }
    }

    @Test fun async_failure_prechecks() {
        mockWiseFyPrechecksUtil.getCurrentNetwork_failure()
        val mockCallbacks = mock(GetCurrentNetworkCallbacks::class.java)
        wisefy.getCurrentNetwork(mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).wisefyFailure(MISSING_PARAMETER)
        verificationUtil.didNotTryToGetCurrentNetwork()
    }

    @Test fun async_failure_prechecks_nullCallbacks() {
        mockWiseFyPrechecksUtil.getCurrentNetwork_failure()
        nullCallbackUtil.callGetCurrentNetwork()
        verificationUtil.didNotTryToGetCurrentNetwork()
    }

    @Test fun async_failure_nullCurrentNetwork() {
        mockWiseFyPrechecksUtil.getCurrentNetwork_success()
        val mockCallbacks = mock(GetCurrentNetworkCallbacks::class.java)
        wisefy.getCurrentNetwork(mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).noCurrentNetwork()
        verificationUtil.triedToGetCurrentNetwork()
    }

    @Test fun async_failure_nullCurrentNetwork_nullCallbacks() {
        mockWiseFyPrechecksUtil.getCurrentNetwork_success()
        nullCallbackUtil.callGetCurrentNetwork()
        verificationUtil.triedToGetCurrentNetwork()
    }

    @Test fun async_success() {
        mockWiseFyPrechecksUtil.getCurrentNetwork_success()
        val currentNetwork = mockNetworkUtil.currentNetwork(TEST_SSID)
        val mockCallbacks = mock(GetCurrentNetworkCallbacks::class.java)
        wisefy.getCurrentNetwork(mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).retrievedCurrentNetwork(currentNetwork)
        verificationUtil.triedToGetCurrentNetwork()
    }

    @Test fun async_success_nullCallback() {
        mockWiseFyPrechecksUtil.getCurrentNetwork_success()
        mockNetworkUtil.currentNetwork(TEST_SSID)
        nullCallbackUtil.callGetCurrentNetwork()
        verificationUtil.triedToGetCurrentNetwork()
    }
}
