package com.isupatches.wisefy.main

import com.isupatches.wisefy.BaseAndroidJUnit4TestClass
import com.isupatches.wisefy.constants.WPA
import com.isupatches.wisefy.createMockAccessPointWithCapabilities

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Tests the ability to determine if a network has WPA security.
 *
 * @author Patches
 */
internal class IsNetworkWPATests : BaseAndroidJUnit4TestClass() {

    @Test fun failure_differentCapability() {
        val scanResult = createMockAccessPointWithCapabilities("Other")
        assertEquals(false, wiseFy.isNetworkWPA(scanResult))
    }

    @Test fun failure_emptyCapabilities() {
        val scanResult = createMockAccessPointWithCapabilities("")
        assertEquals(false, wiseFy.isNetworkWPA(scanResult))
    }

    @Test fun failure_nullCapabilities() {
        val scanResult = createMockAccessPointWithCapabilities(null)
        assertEquals(false, wiseFy.isNetworkWPA(scanResult))
    }

    @Test fun failure_nullScanResult() {
        assertEquals(false, wiseFy.isNetworkWPA(null))
    }

    @Test fun success() {
        val scanResult = createMockAccessPointWithCapabilities(WPA)
        assertEquals(true, wiseFy.isNetworkWPA(scanResult))
    }
}
