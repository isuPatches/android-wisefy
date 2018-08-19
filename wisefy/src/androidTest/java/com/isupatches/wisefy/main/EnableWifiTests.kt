package com.isupatches.wisefy.main

import com.isupatches.wisefy.BaseAndroidJUnit4TestClass
import com.isupatches.wisefy.VERIFICATION_SUCCESS_TIMEOUT
import com.isupatches.wisefy.callbacks.EnableWifiCallbacks
import com.isupatches.wisefy.constants.MISSING_PARAMETER

import org.junit.Assert.assertEquals
import org.junit.Test

import org.mockito.Mockito.mock
import org.mockito.Mockito.timeout
import org.mockito.Mockito.verify

/**
 * Tests the ability to enable a device's wifi.
 *
 * @author Patches
 */
internal class EnableWifiTests : BaseAndroidJUnit4TestClass() {

    @Test fun sync_failure_prechecks() {
        mockWiseFyPrechecksUtil.enableWifi_failure()
        assertEquals(false, wisefy.enableWifi())
        verificationUtil.didNotTryToEnableWifi()
    }

    @Test fun sync_failure() {
        mockWiseFyPrechecksUtil.enableWifi_success()
        mockNetworkUtil.enableWifi_failure()
        assertEquals(false, wisefy.enableWifi())
        verificationUtil.triedToEnableWifi()
    }

    @Test fun sync_success() {
        mockWiseFyPrechecksUtil.enableWifi_success()
        mockNetworkUtil.enableWifi_success()
        assertEquals(true, wisefy.enableWifi())
        verificationUtil.triedToEnableWifi()
    }

    @Test fun async_failure_prechecks() {
        mockWiseFyPrechecksUtil.enableWifi_failure()
        val mockCallbacks = mock(EnableWifiCallbacks::class.java)
        wisefy.enableWifi(mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).wisefyFailure(MISSING_PARAMETER)
        verificationUtil.didNotTryToEnableWifi()
    }

    @Test fun async_failure_prechecks_nullCallback() {
        mockWiseFyPrechecksUtil.enableWifi_failure()
        nullCallbackUtil.callEnableWifi()
        verificationUtil.didNotTryToEnableWifi()
    }

    @Test fun async_failure() {
        mockWiseFyPrechecksUtil.enableWifi_success()
        mockNetworkUtil.enableWifi_failure()
        val mockCallbacks = mock(EnableWifiCallbacks::class.java)
        wisefy.enableWifi(mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).failureEnablingWifi()
        verificationUtil.triedToEnableWifi()
    }

    @Test fun async_failure_nullCallback() {
        mockWiseFyPrechecksUtil.enableWifi_success()
        mockNetworkUtil.enableWifi_failure()
        nullCallbackUtil.callEnableWifi()
        verificationUtil.triedToEnableWifi()
    }

    @Test fun async_success() {
        mockWiseFyPrechecksUtil.enableWifi_success()
        mockNetworkUtil.enableWifi_success()
        val mockCallbacks = mock(EnableWifiCallbacks::class.java)
        wisefy.enableWifi(mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).wifiEnabled()
        verificationUtil.triedToEnableWifi()
    }

    @Test fun async_success_nullCallback() {
        mockWiseFyPrechecksUtil.enableWifi_success()
        mockNetworkUtil.enableWifi_success()
        nullCallbackUtil.callEnableWifi()
        verificationUtil.triedToEnableWifi()
    }
}
