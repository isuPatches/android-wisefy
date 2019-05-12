package com.isupatches.wisefy

import android.os.Build

import com.isupatches.wisefy.callbacks.DisableWifiCallbacks
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
 * Tests the ability to disable a device's wifi.
 *
 * @author Patches
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.M])
internal class DisableWifiTests : BaseUnitTest() {

    @Test fun sync_failure_prechecks() {
        mockWiseFyPrechecksUtil.disableWifi_failure()
        assertEquals(false, wisefy.disableWifi())
        verificationUtil.didNotTryToDisableWifi()
    }

    @Test fun sync_failure() {
        mockWiseFyPrechecksUtil.disableWifi_success()
        mockNetworkUtil.disableWifi_failure()
        assertEquals(false, wisefy.disableWifi())
        verificationUtil.triedToDisableWifi()
    }

    @Test fun sync_success() {
        mockWiseFyPrechecksUtil.disableWifi_success()
        mockNetworkUtil.disableWifi_success()
        assertEquals(true, wisefy.disableWifi())
        verificationUtil.triedToDisableWifi()
    }

    @Test fun async_failure_prechecks() {
        mockWiseFyPrechecksUtil.disableWifi_failure()
        val mockCallbacks = mock(DisableWifiCallbacks::class.java)
        wisefy.disableWifi(mockCallbacks)
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
        wisefy.disableWifi(mockCallbacks)
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
        wisefy.disableWifi(mockCallbacks)
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
