package com.isupatches.wisefy

import com.isupatches.wisefy.constants.PSK
import com.isupatches.wisefy.internal.base.BaseInstrumentationTest
import com.isupatches.wisefy.internal.createMockAccessPointWithCapabilities
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Tests the ability to determine if a network has PSK security.
 *
 * @author Patches
 * @since 3.0
 */
internal class IsNetworkPSKTest : BaseInstrumentationTest() {

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
