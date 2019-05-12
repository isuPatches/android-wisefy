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
 * Tests the ability to determine if a device is connected to a wifi network.
 *
 * @author Patches
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.M])
internal class IsDeviceConnectedToWifiNetworkTests : BaseUnitTest() {

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
