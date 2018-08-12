package com.isupatches.wisefy.main

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue

import com.isupatches.wisefy.BaseAndroidJUnit4TestClass

import org.junit.Test

/**
 * Tests the ability to determine if a device is connected to a mobile or wifi network.
 *
 * @author Patches
 */
internal class IsDeviceConnectedToMobileOrWifiNetworkTests : BaseAndroidJUnit4TestClass() {

    @Test fun failure_missingPrerequisite() {
        mockWiseFyPrechecksUtil.isDeviceConnectedToMobileOrWifiNetwork_failure()
        assertFalse(wiseFy.isDeviceConnectedToMobileOrWifiNetwork())
    }

    @Test fun failure() {
        mockWiseFyPrechecksUtil.isDeviceConnectedToMobileOrWifiNetwork_success()
        mockWiseFyConnectionUtil.isNetworkConnected(false)
        assertFalse(wiseFy.isDeviceConnectedToMobileOrWifiNetwork())
    }

    @Test fun success() {
        mockWiseFyPrechecksUtil.isDeviceConnectedToMobileOrWifiNetwork_success()
        mockNetworkUtil.activeNetwork()
        mockWiseFyConnectionUtil.isNetworkConnected(true)
        assertTrue(wiseFy.isDeviceConnectedToMobileOrWifiNetwork())
    }
}
