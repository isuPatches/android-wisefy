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
package com.isupatches.android.wisefy.networkinfo.entities

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

internal class NetworkConnectionStatusDataTest {

    @Test
    fun assignsValueWithNulls() {
        // Given
        val value = NetworkConnectionStatusData(
            isConnected = false,
            isConnectedToMobileNetwork = false,
            isConnectedToWifiNetwork = false,
            isRoaming = false,
            ssidOfNetworkConnectedTo = null,
            bssidOfNetworkConnectedTo = null,
            ip = null
        )

        // Expect
        assertNotNull(value)
        assertFalse(value.isConnected)
        assertFalse(value.isConnectedToMobileNetwork)
        assertFalse(value.isConnectedToWifiNetwork)
        assertFalse(value.isRoaming)
        assertNull(value.ssidOfNetworkConnectedTo)
        assertNull(value.bssidOfNetworkConnectedTo)
        assertNull(value.ip)
    }

    @Test
    fun assignsValueWithNonNulls() {
        // Given
        val testSSID = "Test SSID"
        val testBSSID = "Test:BSSID"
        val testIP = "192.168.0.1"
        val value = NetworkConnectionStatusData(
            isConnected = true,
            isConnectedToMobileNetwork = true,
            isConnectedToWifiNetwork = true,
            isRoaming = true,
            ssidOfNetworkConnectedTo = testSSID,
            bssidOfNetworkConnectedTo = testBSSID,
            ip = testIP
        )

        // Expect
        assertNotNull(value)
        assertTrue(value.isConnected)
        assertTrue(value.isConnectedToMobileNetwork)
        assertTrue(value.isConnectedToWifiNetwork)
        assertTrue(value.isRoaming)
        assertEquals(testSSID, value.ssidOfNetworkConnectedTo)
        assertEquals(testBSSID, value.bssidOfNetworkConnectedTo)
        assertEquals(testIP, value.ip)
    }
}
