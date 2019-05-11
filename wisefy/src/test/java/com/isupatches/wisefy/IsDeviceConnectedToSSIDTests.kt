package com.isupatches.wisefy

import android.os.Build

import com.isupatches.wisefy.internal.base.BaseUnitTest

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Tests the ability to determine if a device is connected to an SSID.
 *
 * @author Patches
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.M])
internal class IsDeviceConnectedToSSIDTests : BaseUnitTest() {

    @Test fun failure_prechecks() {
        mockWiseFyPrechecksUtil.isDeviceConnectedToSSIDChecks_failure()
        assertFalse(wisefy.isDeviceConnectedToSSID(TEST_SSID))
    }

    @Test fun failure() {
        mockWiseFyPrechecksUtil.isDeviceConnectedToSSIDChecks_success()
        mockWiseFyConnectionUtil.isCurrentNetworkConnectedToSSID(false)
        assertFalse(wisefy.isDeviceConnectedToSSID(TEST_SSID))
    }

    @Test fun success() {
        mockWiseFyPrechecksUtil.isDeviceConnectedToSSIDChecks_success()
        mockWiseFyConnectionUtil.isCurrentNetworkConnectedToSSID(true)
        assertTrue(wisefy.isDeviceConnectedToSSID(TEST_SSID))
    }
}
