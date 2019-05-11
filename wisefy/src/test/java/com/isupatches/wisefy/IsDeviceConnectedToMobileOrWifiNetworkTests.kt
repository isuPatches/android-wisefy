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
 * Tests the ability to determine if a device is connected to a mobile or wifi network.
 *
 * @author Patches
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.M])
internal class IsDeviceConnectedToMobileOrWifiNetworkTests : BaseUnitTest() {

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
