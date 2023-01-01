/*
 * Copyright 2022 Patches Barrett
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.isupatches.android.wisefy.accesspoints.entities

import android.net.wifi.ScanResult
import com.isupatches.android.wisefy.core.constants.MAX_FREQUENCY_2_4_GHZ
import com.isupatches.android.wisefy.core.constants.MAX_FREQUENCY_5_GHZ
import com.isupatches.android.wisefy.core.constants.MIN_FREQUENCY_2_4_GHZ
import com.isupatches.android.wisefy.core.constants.MIN_FREQUENCY_5_GHZ
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

internal class AccessPointDataTest {

    companion object {
        private const val TEST_SSID = "Test SSID"
        private const val TEST_BSSID = "Test:BSSID"
        private const val TEST_FREQUENCY = 24500
        private const val TEST_RSSI = -65
    }

    @Test
    fun assignsSSID_fromRawValue() {
        // Given
        val rawValue = ScanResult().apply {
            @Suppress("Deprecation")
            SSID = TEST_SSID
        }
        val accessPoint = AccessPointData(rawValue = rawValue)

        // Expect
        assertEquals(TEST_SSID, accessPoint.ssid)
    }

    @Test
    fun assignsBSSID_fromRawValue() {
        // Given
        val rawValue = ScanResult().apply {
            BSSID = TEST_BSSID
        }
        val accessPoint = AccessPointData(rawValue = rawValue)

        // Expect
        assertEquals(TEST_BSSID, accessPoint.bssid)
    }

    @Test
    fun assignsFrequency_fromRawValue() {
        // Given
        val rawValue = ScanResult().apply {
            frequency = TEST_FREQUENCY
        }
        val accessPoint = AccessPointData(rawValue = rawValue)

        // Expect
        assertEquals(TEST_FREQUENCY, accessPoint.frequency)
    }

    @Test
    fun assignsRSSI_fromRawValue() {
        // Given
        val rawValue = ScanResult().apply {
            level = TEST_RSSI
        }
        val accessPoint = AccessPointData(rawValue = rawValue)

        // Expect
        assertEquals(TEST_RSSI, accessPoint.rssi)
    }

    @Test
    fun assigns_is2gHz_fromRawValue_false_lower() {
        // Given
        val rawValue = ScanResult().apply {
            frequency = MIN_FREQUENCY_2_4_GHZ - 1
        }
        val accessPoint = AccessPointData(rawValue = rawValue)

        // Expect
        assertFalse(accessPoint.is2gHz)
    }

    @Test
    fun assigns_is2gHz_fromRawValue_false_higher() {
        // Given
        val rawValue = ScanResult().apply {
            frequency = MAX_FREQUENCY_2_4_GHZ + 1
        }
        val accessPoint = AccessPointData(rawValue = rawValue)

        // Expect
        assertFalse(accessPoint.is2gHz)
    }

    @Test
    fun assigns_is2gHz_fromRawValue_true() {
        // Given
        val rawValue = ScanResult().apply {
            frequency = MIN_FREQUENCY_2_4_GHZ + 1
        }
        val accessPoint = AccessPointData(rawValue = rawValue)

        // Expect
        assertTrue(accessPoint.is2gHz)
    }

    @Test
    fun assigns_is5gHz_fromRawValue_false_lower() {
        // Given
        val rawValue = ScanResult().apply {
            frequency = MIN_FREQUENCY_5_GHZ - 1
        }
        val accessPoint = AccessPointData(rawValue = rawValue)

        // Expect
        assertFalse(accessPoint.is5gHz)
    }

    @Test
    fun assigns_is5gHz_fromRawValue_false_higher() {
        // Given
        val rawValue = ScanResult().apply {
            frequency = MAX_FREQUENCY_5_GHZ + 1
        }
        val accessPoint = AccessPointData(rawValue = rawValue)

        // Expect
        assertFalse(accessPoint.is5gHz)
    }

    @Test
    fun assigns_is5gHz_fromRawValue_true() {
        // Given
        val rawValue = ScanResult().apply {
            frequency = MIN_FREQUENCY_5_GHZ + 1
        }
        val accessPoint = AccessPointData(rawValue = rawValue)

        // Expect
        assertTrue(accessPoint.is5gHz)
    }

    @Test
    fun assigns_isSecure_fromRawValue_false() {
        // Given
        val rawValue = ScanResult().apply {
            capabilities = ""
        }
        val accessPoint = AccessPointData(rawValue = rawValue)

        // Expect
        assertFalse(accessPoint.isSecure)
    }

    @Test
    fun assigns_isSecure_fromRawValue_true() {
        // Given
        val rawValue = ScanResult().apply {
            capabilities = "[WPA2-PSK-CCMP]"
        }
        val accessPoint = AccessPointData(rawValue = rawValue)

        // Expect
        assertTrue(accessPoint.isSecure)
    }
}
