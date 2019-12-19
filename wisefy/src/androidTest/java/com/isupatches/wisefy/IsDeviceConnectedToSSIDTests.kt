package com.isupatches.wisefy

import com.isupatches.wisefy.internal.base.BaseInstrumentationTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Tests the ability to determine if a device is connected to an SSID.
 *
 * @author Patches
 * @since 3.0
 */
internal class IsDeviceConnectedToSSIDTests : BaseInstrumentationTest() {

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
