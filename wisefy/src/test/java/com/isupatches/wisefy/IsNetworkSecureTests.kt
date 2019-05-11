package com.isupatches.wisefy

import android.os.Build

import com.isupatches.wisefy.constants.EAP
import com.isupatches.wisefy.constants.PSK
import com.isupatches.wisefy.constants.WEP
import com.isupatches.wisefy.constants.WPA
import com.isupatches.wisefy.constants.WPA2
import com.isupatches.wisefy.internal.base.BaseUnitTest
import com.isupatches.wisefy.internal.createMockAccessPointWithCapabilities

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Tests the ability to determine if a network is secure.
 *
 * @author Patches
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.M])
internal class IsNetworkSecureTests : BaseUnitTest() {

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
