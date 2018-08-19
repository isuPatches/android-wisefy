package com.isupatches.wisefy.main

import android.net.NetworkInfo

import com.isupatches.wisefy.BaseAndroidJUnit4TestClass
import com.isupatches.wisefy.VERIFICATION_SUCCESS_TIMEOUT
import com.isupatches.wisefy.callbacks.GetCurrentNetworkInfoCallbacks
import com.isupatches.wisefy.constants.MISSING_PARAMETER

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.fail
import org.junit.Test

import org.mockito.Mockito.mock
import org.mockito.Mockito.timeout
import org.mockito.Mockito.verify

/**
 * Tests the ability to retrieve a device's current network information.
 *
 * @author Patches
 */
internal class GetCurrentNetworkInfoTest : BaseAndroidJUnit4TestClass() {

    @Test fun sync_failure_prechecks() {
        mockWiseFyPrechecksUtil.getCurrentNetworkInfo_failure()
        assertEquals(null, wisefy.getCurrentNetworkInfo())
        verificationUtil.didNotTryToGetCurrentNetworkInfo()
    }

    @Test fun sync_success() {
        mockWiseFyPrechecksUtil.getCurrentNetworkInfo_success()
        mockNetworkUtil.activeNetwork()
        val currentNetworkInfo = wisefy.getCurrentNetworkInfo()
        if (currentNetworkInfo != null) {
            assertNotNull(currentNetworkInfo)
            verificationUtil.triedToGetCurrentNetworkInfo()
        } else {
            fail()
        }
    }

    @Test fun async_failure_prechecks() {
        mockWiseFyPrechecksUtil.getCurrentNetworkInfo_failure()
        val mockCallbacks = mock(GetCurrentNetworkInfoCallbacks::class.java)
        wisefy.getCurrentNetworkInfo(mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).wisefyFailure(MISSING_PARAMETER)
        verificationUtil.didNotTryToGetCurrentNetworkInfo()
    }

    @Test fun async_failure_prechecks_nullCallbacks() {
        mockWiseFyPrechecksUtil.getCurrentNetworkInfo_failure()
        nullCallbackUtil.callGetCurrentNetworkInfo()
        verificationUtil.didNotTryToGetCurrentNetworkInfo()
    }

    @Test fun async_success() {
        mockWiseFyPrechecksUtil.getCurrentNetworkInfo_success()
        mockNetworkUtil.activeNetwork()
        val mockCallbacks = mock(GetCurrentNetworkInfoCallbacks::class.java)
        wisefy.getCurrentNetworkInfo(mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).retrievedCurrentNetworkInfo(any(NetworkInfo::class.java))
        verificationUtil.triedToGetCurrentNetworkInfo()
    }

    @Test fun async_success_nullCallback() {
        mockWiseFyPrechecksUtil.getCurrentNetworkInfo_success()
        mockNetworkUtil.activeNetwork()
        nullCallbackUtil.callGetCurrentNetworkInfo()
        verificationUtil.didNotTryToGetCurrentNetworkInfo()
    }
}
