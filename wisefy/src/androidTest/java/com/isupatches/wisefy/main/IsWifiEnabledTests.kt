package com.isupatches.wisefy.main

import org.junit.Assert.assertEquals

import com.isupatches.wisefy.BaseAndroidJUnit4TestClass

import org.junit.Test

/**
 * Tests the ability to determine if wifi is enabled on a device.
 *
 * @author Patches
 */
internal class IsWifiEnabledTests : BaseAndroidJUnit4TestClass() {

    @Test fun failure_prechecks() {
        mockWiseFyPrechecksUtil.isWifiEnabled_failure()
        assertEquals(false, wiseFy.isWifiEnabled())
    }

    @Test fun failure() {
        mockWiseFyPrechecksUtil.isWifiEnabled_success()
        mockNetworkUtil.isWifiEnabled(false)
        assertEquals(false, wiseFy.isWifiEnabled())
    }

    @Test fun success() {
        mockWiseFyPrechecksUtil.isWifiEnabled_success()
        mockNetworkUtil.isWifiEnabled(true)
        assertEquals(true, wiseFy.isWifiEnabled())
    }
}
