package com.isupatches.wisefy

import com.isupatches.wisefy.callbacks.GetIPCallbacks
import com.isupatches.wisefy.constants.MISSING_PARAMETER
import com.isupatches.wisefy.internal.base.BaseInstrumentationTest

import org.junit.Assert.assertEquals
import org.junit.Test

import org.mockito.Mockito.mock
import org.mockito.Mockito.timeout
import org.mockito.Mockito.verify

/**
 * Tests the ability to get the IP of a device.
 *
 * @author Patches
 * @since 3.0
 */
internal class GetIPTests : BaseInstrumentationTest() {

    @Test fun sync_getIP_failure_missingPrerequisites() {
        mockWiseFyPrechecksUtil.getIP_failure()
        assertEquals(null, wisefy.getIP())
    }

    @Test fun sync_getIP_failure() {
        mockWiseFyPrechecksUtil.getIP_success()
        mockNetworkUtil.ip_failure()
        assertEquals(null, wisefy.getIP())
    }

    @Test fun sync_getIP_success() {
        mockWiseFyPrechecksUtil.getIP_success()
        mockNetworkUtil.ip_success()
        assertEquals(TEST_IP_ADDRESS_STRING, wisefy.getIP())
    }

    @Test fun async_getIP_failure_missingPrerequisites() {
        mockWiseFyPrechecksUtil.getIP_failure()
        val mockCallbacks = mock(GetIPCallbacks::class.java)
        wisefy.getIP(mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).wisefyFailure(MISSING_PARAMETER)
    }

    @Test fun async_getIP_failure_missingPrerequisites_nullCallbacks() {
        mockWiseFyPrechecksUtil.getIP_failure()
        nullCallbackUtil.callGetIP()
    }

    @Test fun async_getIP_failure() {
        mockWiseFyPrechecksUtil.getIP_success()
        mockNetworkUtil.ip_failure()
        val mockCallbacks = mock(GetIPCallbacks::class.java)
        wisefy.getIP(mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).failureRetrievingIP()
    }

    @Test fun async_getIP_failure_nullCallbacks() {
        mockWiseFyPrechecksUtil.getIP_success()
        mockNetworkUtil.ip_failure()
        nullCallbackUtil.callGetIP()
    }

    @Test fun async_getIP_success() {
        mockWiseFyPrechecksUtil.getIP_success()
        mockNetworkUtil.ip_success()
        val mockCallbacks = mock(GetIPCallbacks::class.java)
        wisefy.getIP(mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).retrievedIP(TEST_IP_ADDRESS_STRING)
    }

    @Test fun async_getIP_success_nullCallbacks() {
        mockWiseFyPrechecksUtil.getIP_success()
        mockNetworkUtil.ip_success()
        nullCallbackUtil.callGetIP()
    }
}
