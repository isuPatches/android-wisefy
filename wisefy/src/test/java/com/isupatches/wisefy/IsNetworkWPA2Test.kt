package com.isupatches.wisefy

import android.os.Build

import com.isupatches.wisefy.constants.WPA2
import com.isupatches.wisefy.internal.base.BaseUnitTest
import com.isupatches.wisefy.internal.createMockAccessPointWithCapabilities

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Tests the ability to determine if a network has WPA2 security.
 *
 * @author Patches
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.M])
internal class IsNetworkWPA2Test : BaseUnitTest() {

    @Test fun failure_differentCapability() {
        val scanResult = createMockAccessPointWithCapabilities("Other")
        assertEquals(false, wisefy.isNetworkWPA2(scanResult))
    }

    @Test fun failure_emptyCapabilities() {
        val scanResult = createMockAccessPointWithCapabilities("")
        assertEquals(false, wisefy.isNetworkWPA2(scanResult))
    }

    @Test fun failure_nullCapabilities() {
        val scanResult = createMockAccessPointWithCapabilities(null)
        assertEquals(false, wisefy.isNetworkWPA2(scanResult))
    }

    @Test fun failure_nullScanResult() {
        assertEquals(false, wisefy.isNetworkWPA2(null))
    }

    @Test fun success() {
        val scanResult = createMockAccessPointWithCapabilities(WPA2)
        assertEquals(true, wisefy.isNetworkWPA2(scanResult))
    }
}
