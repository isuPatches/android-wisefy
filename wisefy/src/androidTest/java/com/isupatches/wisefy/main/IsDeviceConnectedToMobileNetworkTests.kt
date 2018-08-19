package com.isupatches.wisefy.main

import com.isupatches.wisefy.BaseAndroidJUnit4TestClass
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Tests the ability to determine if a device is connected to a mobile network.
 *
 * @author Patches
 */
internal class IsDeviceConnectedToMobileNetworkTests : BaseAndroidJUnit4TestClass() {

    @Test fun failure_prechecks() {
        mockWiseFyPrechecksUtil.isDeviceConnectedToMobileNetwork_failure()
        assertFalse(wisefy.isDeviceConnectedToMobileNetwork())
    }

    @Test fun failure() {
        mockWiseFyPrechecksUtil.isDeviceConnectedToMobileNetwork_success()
        mockWiseFyConnectionUtil.isNetworkConnectedAndMatchesType(false)
        assertFalse(wisefy.isDeviceConnectedToMobileNetwork())
    }

    @Test fun success() {
        mockWiseFyPrechecksUtil.isDeviceConnectedToMobileNetwork_success()
        mockNetworkUtil.activeNetwork()
        mockWiseFyConnectionUtil.isNetworkConnectedAndMatchesType(true)
        assertTrue(wisefy.isDeviceConnectedToMobileNetwork())
    }
}
