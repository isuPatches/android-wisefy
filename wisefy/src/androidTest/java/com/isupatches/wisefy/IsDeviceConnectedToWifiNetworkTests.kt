package com.isupatches.wisefy

import com.isupatches.wisefy.internal.base.BaseInstrumentationTest

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Tests the ability to determine if a device is connected to a wifi network.
 *
 * @author Patches
 * @since 3.0
 */
internal class IsDeviceConnectedToWifiNetworkTests : BaseInstrumentationTest() {

    @Test fun failure_missingPrerequisite() {
        mockWiseFyPrechecksUtil.isDeviceConnectedToWifiNetwork_failure()
        assertFalse(wisefy.isDeviceConnectedToWifiNetwork())
    }

    @Test fun failure() {
        mockWiseFyPrechecksUtil.isDeviceConnectedToWifiNetwork_success()
        mockWiseFyConnectionUtil.isDeviceConnectedToWifiNetwork(false)
        assertFalse(wisefy.isDeviceConnectedToWifiNetwork())
    }

    @Test fun success() {
        mockWiseFyPrechecksUtil.isDeviceConnectedToWifiNetwork_success()
        mockNetworkUtil.activeNetwork()
        mockWiseFyConnectionUtil.isDeviceConnectedToWifiNetwork(true)
        assertTrue(wisefy.isDeviceConnectedToWifiNetwork())
    }
}
