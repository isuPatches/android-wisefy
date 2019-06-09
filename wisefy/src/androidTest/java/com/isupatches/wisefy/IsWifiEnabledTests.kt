package com.isupatches.wisefy

import com.isupatches.wisefy.internal.base.BaseInstrumentationTest

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Tests the ability to determine if wifi is enabled on a device.
 *
 * @author Patches
 * @since 3.0
 */
internal class IsWifiEnabledTests : BaseInstrumentationTest() {

    @Test fun failure_prechecks() {
        mockWiseFyPrechecksUtil.isWifiEnabled_failure()
        assertEquals(false, wisefy.isWifiEnabled())
    }

    @Test fun failure() {
        mockWiseFyPrechecksUtil.isWifiEnabled_success()
        mockNetworkUtil.isWifiEnabled(false)
        assertEquals(false, wisefy.isWifiEnabled())
    }

    @Test fun success() {
        mockWiseFyPrechecksUtil.isWifiEnabled_success()
        mockNetworkUtil.isWifiEnabled(true)
        assertEquals(true, wisefy.isWifiEnabled())
    }
}
