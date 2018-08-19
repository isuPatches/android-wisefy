package com.isupatches.wisefy.main

import com.isupatches.wisefy.BaseAndroidJUnit4TestClass
import com.isupatches.wisefy.VERIFICATION_SUCCESS_TIMEOUT
import com.isupatches.wisefy.callbacks.DisableWifiCallbacks
import com.isupatches.wisefy.constants.MISSING_PARAMETER

import org.junit.Assert.assertEquals
import org.junit.Test

import org.mockito.Mockito.mock
import org.mockito.Mockito.timeout
import org.mockito.Mockito.verify

/**
 * Tests the ability to disable a device's wifi.
 *
 * @author Patches
 */
internal class DisableWifiTests : BaseAndroidJUnit4TestClass() {

    @Test fun sync_failure_prechecks() {
        mockWiseFyPrechecksUtil.disableWifi_failure()
        assertEquals(false, wiseFy.disableWifi())
        verificationUtil.didNotTryToDisableWifi()
    }

    @Test fun sync_failure() {
        mockWiseFyPrechecksUtil.disableWifi_success()
        mockNetworkUtil.disableWifi_failure()
        assertEquals(false, wiseFy.disableWifi())
        verificationUtil.triedToDisableWifi()
    }

    @Test fun sync_success() {
        mockWiseFyPrechecksUtil.disableWifi_success()
        mockNetworkUtil.disableWifi_success()
        assertEquals(true, wiseFy.disableWifi())
        verificationUtil.triedToDisableWifi()
    }

    @Test fun async_failure_prechecks() {
        mockWiseFyPrechecksUtil.disableWifi_failure()
        val mockCallbacks = mock(DisableWifiCallbacks::class.java)
        wiseFy.disableWifi(mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).wisefyFailure(MISSING_PARAMETER)
        verificationUtil.didNotTryToDisableWifi()
    }

    @Test fun async_failure_prechecks_nullCallback() {
        mockWiseFyPrechecksUtil.disableWifi_failure()
        nullCallbackUtil.callDisableWifi()
        verificationUtil.didNotTryToDisableWifi()
    }

    @Test fun async_failure() {
        mockWiseFyPrechecksUtil.disableWifi_success()
        mockNetworkUtil.disableWifi_failure()
        val mockCallbacks = mock(DisableWifiCallbacks::class.java)
        wiseFy.disableWifi(mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).failureDisablingWifi()
        verificationUtil.triedToDisableWifi()
    }

    @Test fun async_failure_nullCallback() {
        mockWiseFyPrechecksUtil.disableWifi_success()
        mockNetworkUtil.disableWifi_failure()
        nullCallbackUtil.callDisableWifi()
        verificationUtil.triedToDisableWifi()
    }

    @Test fun async_success() {
        mockWiseFyPrechecksUtil.disableWifi_success()
        mockNetworkUtil.disableWifi_success()
        val mockCallbacks = mock(DisableWifiCallbacks::class.java)
        wiseFy.disableWifi(mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).wifiDisabled()
        verificationUtil.triedToDisableWifi()
    }

    @Test fun async_success_nullCallback() {
        mockWiseFyPrechecksUtil.disableWifi_success()
        mockNetworkUtil.disableWifi_success()
        nullCallbackUtil.callDisableWifi()
        verificationUtil.triedToDisableWifi()
    }
}
