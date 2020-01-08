package com.isupatches.wisefy

import com.isupatches.wisefy.internal.base.BaseInstrumentationTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Tests the ability to determine if a device is connected to a mobile network.
 *
 * @author Patches
 * @since 3.0
 */
internal class IsDeviceConnectedToMobileNetworkTests : BaseInstrumentationTest() {

    @Test
    fun failure_prechecks() {
        mockWiseFyPrechecksUtil.isDeviceConnectedToMobileNetwork_failure()
        assertFalse(wisefy.isDeviceConnectedToMobileNetwork())
    }

    @Test
    fun failure() {
        mockWiseFyPrechecksUtil.isDeviceConnectedToMobileNetwork_success()
        mockWiseFyConnectionUtil.isDeviceConnectedToMobileNetwork(false)
        assertFalse(wisefy.isDeviceConnectedToMobileNetwork())
    }

    @Test
    fun success() {
        mockWiseFyPrechecksUtil.isDeviceConnectedToMobileNetwork_success()
        mockNetworkUtil.activeNetwork()
        mockWiseFyConnectionUtil.isDeviceConnectedToMobileNetwork(true)
        assertTrue(wisefy.isDeviceConnectedToMobileNetwork())
    }
}
