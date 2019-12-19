package com.isupatches.wisefy

import com.isupatches.wisefy.constants.WPA2
import com.isupatches.wisefy.internal.base.BaseInstrumentationTest
import com.isupatches.wisefy.internal.createMockAccessPointWithCapabilities
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Tests the ability to determine if a network has WPA2 security.
 *
 * @author Patches
 * @since 3.0
 */
internal class IsNetworkWPA2Test : BaseInstrumentationTest() {

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
