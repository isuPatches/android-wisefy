package com.isupatches.wisefy.main

import com.isupatches.wisefy.BaseAndroidJUnit4TestClass
import com.isupatches.wisefy.TEST_SSID
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Tests the ability to determine if a device is connected to an SSID.
 *
 * @author Patches
 */
internal class IsDeviceConnectedToSSIDTests : BaseAndroidJUnit4TestClass() {

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
