package com.isupatches.wisefy

import android.net.NetworkInfo
import com.isupatches.wisefy.callbacks.GetCurrentNetworkInfoCallbacks
import com.isupatches.wisefy.constants.MISSING_PARAMETER
import com.isupatches.wisefy.internal.base.BaseInstrumentationTest
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
 * @since 3.0
 */
internal class GetCurrentNetworkInfoTest : BaseInstrumentationTest() {

    @Test fun sync_failure_prechecks() {
        mockWiseFyPrechecksUtil.getCurrentNetworkInfo_failure()
        assertEquals(null, wisefy.getCurrentNetworkInfo())
        verificationUtil.didNotTryToGetCurrentNetworkInfo()
    }

    @Test fun sync_failure_nullCurrentNetwork() {
        mockWiseFyPrechecksUtil.getCurrentNetworkInfo_success()
        assertEquals(null, wisefy.getCurrentNetworkInfo())
        verificationUtil.triedToGetCurrentNetworkInfo()
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

    @Test fun async_failure_nullCurrentNetwork() {
        mockWiseFyPrechecksUtil.getCurrentNetworkInfo_success()
        val mockCallbacks = mock(GetCurrentNetworkInfoCallbacks::class.java)
        wisefy.getCurrentNetworkInfo(mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).noCurrentNetworkInfo()
        verificationUtil.triedToGetCurrentNetworkInfo()
    }

    @Test fun async_failure_nullCurrentNetwork_nullCallbacks() {
        mockWiseFyPrechecksUtil.getCurrentNetworkInfo_success()
        nullCallbackUtil.callGetCurrentNetworkInfo()
        verificationUtil.triedToGetCurrentNetworkInfo()
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
        verificationUtil.triedToGetCurrentNetworkInfo()
    }
}
