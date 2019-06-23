package com.isupatches.wisefy

import com.isupatches.wisefy.internal.base.BaseInstrumentationTest

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Tests the ability to determine if a device is connected to a mobile or wifi network.
 *
 * @author Patches
 * @since 3.0
 */
internal class IsDeviceConnectedToMobileOrWifiNetworkTests : BaseInstrumentationTest() {

    @Test fun failure_missingPrerequisite() {
        mockWiseFyPrechecksUtil.isDeviceConnectedToMobileOrWifiNetwork_failure()
        assertFalse(wisefy.isDeviceConnectedToMobileOrWifiNetwork())
    }

    @Test fun failure() {
        mockWiseFyPrechecksUtil.isDeviceConnectedToMobileOrWifiNetwork_success()
        mockWiseFyConnectionUtil.isNetworkConnected(false)
        assertFalse(wisefy.isDeviceConnectedToMobileOrWifiNetwork())
    }

    @Test fun success() {
        mockWiseFyPrechecksUtil.isDeviceConnectedToMobileOrWifiNetwork_success()
        mockNetworkUtil.activeNetwork()
        mockWiseFyConnectionUtil.isNetworkConnected(true)
        assertTrue(wisefy.isDeviceConnectedToMobileOrWifiNetwork())
    }
}
