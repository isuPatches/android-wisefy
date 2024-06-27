/*
 * Copyright 2023 Patches Barrett
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
import com.isupatches.android.wisefy.core.constants.MIN_FREQUENCY_5_GHZ
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

internal class AccessPointDataTest {

    @Test
    fun assignsValues() {
        // Given
        val rawValue = ScanResult().apply {
            @Suppress("DEPRECATION")
            SSID = TEST_SSID
            BSSID = TEST_BSSID
            frequency = TEST_FREQUENCY
            level = TEST_RSSI
        }
        val accessPoint = AccessPointData(rawValue = rawValue)

        // Expect
        assertEquals(rawValue, accessPoint.rawValue)
        assertEquals(TEST_SSID, accessPoint.ssid)
        assertEquals(TEST_BSSID, accessPoint.bssid)
        assertEquals(TEST_FREQUENCY, accessPoint.frequency)
        assertEquals(TEST_RSSI, accessPoint.rssi)
        assertFalse(accessPoint.is2gHz)
        assertTrue(accessPoint.is5gHz)
        assertFalse(accessPoint.isSecure)
    }

    companion object {
        private const val TEST_SSID = "Test SSID"
        private const val TEST_BSSID: String = "01:23:45:67:89:AB"
        private const val TEST_FREQUENCY = MIN_FREQUENCY_5_GHZ
        private const val TEST_RSSI = -61
    }
}
