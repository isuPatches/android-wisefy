package com.isupatches.wisefy.main

import com.isupatches.wisefy.BaseAndroidJUnit4TestClass
import com.isupatches.wisefy.constants.WEP
import com.isupatches.wisefy.createMockAccessPointWithCapabilities

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Tests the ability to determine if a network has WEP security.
 *
 * @author Patches
 */
internal class IsNetworkWepTests : BaseAndroidJUnit4TestClass() {

    @Test fun failure_differentCapability() {
        val scanResult = createMockAccessPointWithCapabilities("Other")
        assertEquals(false, wisefy.isNetworkWEP(scanResult))
    }

    @Test fun failure_emptyCapabilities() {
        val scanResult = createMockAccessPointWithCapabilities("")
        assertEquals(false, wisefy.isNetworkWEP(scanResult))
    }

    @Test fun failure_nullCapabilities() {
        val scanResult = createMockAccessPointWithCapabilities(null)
        assertEquals(false, wisefy.isNetworkWEP(scanResult))
    }

    @Test fun failure_nullScanResult() {
        assertEquals(false, wisefy.isNetworkWEP(null))
    }

    @Test fun success() {
        val scanResult = createMockAccessPointWithCapabilities(WEP)
        assertEquals(true, wisefy.isNetworkWEP(scanResult))
    }
}
