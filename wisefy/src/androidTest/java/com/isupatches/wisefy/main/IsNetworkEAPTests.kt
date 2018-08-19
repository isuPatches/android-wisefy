package com.isupatches.wisefy.main

import com.isupatches.wisefy.BaseAndroidJUnit4TestClass
import com.isupatches.wisefy.constants.EAP
import com.isupatches.wisefy.createMockAccessPointWithCapabilities

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Tests the ability to determine if a network has EAP security.
 *
 * @author Patches
 */
internal class IsNetworkEAPTests : BaseAndroidJUnit4TestClass() {

    @Test fun failure_differentCapability() {
        val scanResult = createMockAccessPointWithCapabilities("Other")
        assertEquals(false, wisefy.isNetworkEAP(scanResult))
    }

    @Test fun failure_emptyCapabilities() {
        val scanResult = createMockAccessPointWithCapabilities("")
        assertEquals(false, wisefy.isNetworkEAP(scanResult))
    }

    @Test fun failure_nullCapabilities() {
        val scanResult = createMockAccessPointWithCapabilities(null)
        assertEquals(false, wisefy.isNetworkEAP(scanResult))
    }

    @Test fun failure_nullScanResult() {
        assertEquals(false, wisefy.isNetworkEAP(null))
    }

    @Test fun success() {
        val scanResult = createMockAccessPointWithCapabilities(EAP)
        assertEquals(true, wisefy.isNetworkEAP(scanResult))
    }
}
