package com.isupatches.wisefy

import android.os.Build

import com.isupatches.wisefy.constants.PSK
import com.isupatches.wisefy.internal.base.BaseUnitTest
import com.isupatches.wisefy.internal.createMockAccessPointWithCapabilities

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Tests the ability to determine if a network has PSK security.
 *
 * @author Patches
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.M])
internal class IsNetworkPSKTest : BaseUnitTest() {

    @Test fun failure_differentCapability() {
        val scanResult = createMockAccessPointWithCapabilities("Other")
        assertEquals(false, wisefy.isNetworkPSK(scanResult))
    }

    @Test fun failure_emptyCapabilities() {
        val scanResult = createMockAccessPointWithCapabilities("")
        assertEquals(false, wisefy.isNetworkPSK(scanResult))
    }

    @Test fun failure_nullCapabilities() {
        val scanResult = createMockAccessPointWithCapabilities(null)
        assertEquals(false, wisefy.isNetworkPSK(scanResult))
    }

    @Test fun failure_nullScanResult() {
        assertEquals(false, wisefy.isNetworkPSK(null))
    }

    @Test fun success() {
        val scanResult = createMockAccessPointWithCapabilities(PSK)
        assertEquals(true, wisefy.isNetworkPSK(scanResult))
    }
}
