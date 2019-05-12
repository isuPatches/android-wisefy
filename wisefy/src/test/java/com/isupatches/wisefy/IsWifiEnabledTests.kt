package com.isupatches.wisefy

import android.os.Build

import com.isupatches.wisefy.internal.base.BaseUnitTest

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Tests the ability to determine if wifi is enabled on a device.
 *
 * @author Patches
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.M])
internal class IsWifiEnabledTests : BaseUnitTest() {

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
