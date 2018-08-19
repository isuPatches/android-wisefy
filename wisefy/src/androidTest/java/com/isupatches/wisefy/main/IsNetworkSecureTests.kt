package com.isupatches.wisefy.main

import com.isupatches.wisefy.BaseAndroidJUnit4TestClass
import com.isupatches.wisefy.constants.EAP
import com.isupatches.wisefy.constants.PSK
import com.isupatches.wisefy.constants.WEP
import com.isupatches.wisefy.constants.WPA
import com.isupatches.wisefy.constants.WPA2
import com.isupatches.wisefy.createMockAccessPointWithCapabilities

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Tests the ability to determine if a network is secure.
 *
 * @author Patches
 */
internal class IsNetworkSecureTests : BaseAndroidJUnit4TestClass() {

    @Test fun failure_emptyCapabilities() {
        val scanResult = createMockAccessPointWithCapabilities("")
        assertEquals(false, wisefy.isNetworkSecure(scanResult))
    }

    @Test fun failure_nullCapabilities() {
        val scanResult = createMockAccessPointWithCapabilities(null)
        assertEquals(false, wisefy.isNetworkSecure(scanResult))
    }

    @Test fun failure_nullScanResult() {
        assertEquals(false, wisefy.isNetworkSecure(null))
    }

    @Test fun success_withEAP() {
        val scanResult = createMockAccessPointWithCapabilities(EAP)
        assertEquals(true, wisefy.isNetworkSecure(scanResult))
    }

    @Test fun success_withPSK() {
        val scanResult = createMockAccessPointWithCapabilities(PSK)
        assertEquals(true, wisefy.isNetworkSecure(scanResult))
    }

    @Test fun success_withWEP() {
        val scanResult = createMockAccessPointWithCapabilities(WEP)
        assertEquals(true, wisefy.isNetworkSecure(scanResult))
    }

    @Test fun success_withWPA() {
        val scanResult = createMockAccessPointWithCapabilities(WPA)
        assertEquals(true, wisefy.isNetworkSecure(scanResult))
    }

    @Test fun success_withWPA2() {
        val scanResult = createMockAccessPointWithCapabilities(WPA2)
        assertEquals(true, wisefy.isNetworkSecure(scanResult))
    }

    @Test fun failure_otherCapabilities() {
        val scanResult = createMockAccessPointWithCapabilities("Other")
        assertEquals(false, wisefy.isNetworkSecure(scanResult))
    }
}
