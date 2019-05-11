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
 * Tests the ability to determine if a device is connected to a mobile network.
 *
 * @author Patches
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.M])
internal class IsDeviceConnectedToMobileNetworkTests : BaseUnitTest() {

    @Test fun failure_prechecks() {
        mockWiseFyPrechecksUtil.isDeviceConnectedToMobileNetwork_failure()
        assertFalse(wisefy.isDeviceConnectedToMobileNetwork())
    }

    @Test fun failure() {
        mockWiseFyPrechecksUtil.isDeviceConnectedToMobileNetwork_success()
        mockWiseFyConnectionUtil.isDeviceConnectedToMobileNetwork(false)
        assertFalse(wisefy.isDeviceConnectedToMobileNetwork())
    }

    @Test fun success() {
        mockWiseFyPrechecksUtil.isDeviceConnectedToMobileNetwork_success()
        mockNetworkUtil.activeNetwork()
        mockWiseFyConnectionUtil.isDeviceConnectedToMobileNetwork(true)
        assertTrue(wisefy.isDeviceConnectedToMobileNetwork())
    }
}
