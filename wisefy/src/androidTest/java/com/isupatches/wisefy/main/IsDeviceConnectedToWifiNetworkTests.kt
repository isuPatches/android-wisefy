package com.isupatches.wisefy.main

import com.isupatches.wisefy.BaseAndroidJUnit4TestClass
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Tests the ability to determine if a device is connected to a wifi network.
 *
 * @author Patches
 */
internal class IsDeviceConnectedToWifiNetworkTests : BaseAndroidJUnit4TestClass() {

    @Test fun failure_missingPrerequisite() {
        mockWiseFyPrechecksUtil.isDeviceConnectedToWifiNetwork_failure()
        assertFalse(wisefy.isDeviceConnectedToWifiNetwork())
    }

    @Test fun failure() {
        mockWiseFyPrechecksUtil.isDeviceConnectedToWifiNetwork_success()
        mockWiseFyConnectionUtil.isNetworkConnectedAndMatchesType(false)
        assertFalse(wisefy.isDeviceConnectedToWifiNetwork())
    }

    @Test fun success() {
        mockWiseFyPrechecksUtil.isDeviceConnectedToWifiNetwork_success()
        mockNetworkUtil.activeNetwork()
        mockWiseFyConnectionUtil.isNetworkConnectedAndMatchesType(true)
        assertTrue(wisefy.isDeviceConnectedToWifiNetwork())
    }
}
