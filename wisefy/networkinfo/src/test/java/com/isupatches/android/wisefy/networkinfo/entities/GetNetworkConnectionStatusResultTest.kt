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

internal class GetNetworkConnectionStatusResultTest {

    @Test
    fun assignsValueWithNulls() {
        // Given
        val result = GetNetworkConnectionStatusResult(
            value = NetworkConnectionStatusData(
                isConnected = false,
                isConnectedToMobileNetwork = false,
                isConnectedToWifiNetwork = false,
                isRoaming = false,
                ssidOfNetworkConnectedTo = null,
                bssidOfNetworkConnectedTo = null,
                ip = null
            )
        )

        // Expect
        assertNotNull(result)
        assertNotNull(result.value)
        assertFalse(result.value.isConnected)
        assertFalse(result.value.isConnectedToMobileNetwork)
        assertFalse(result.value.isConnectedToWifiNetwork)
        assertFalse(result.value.isRoaming)
        assertNull(result.value.ssidOfNetworkConnectedTo)
        assertNull(result.value.bssidOfNetworkConnectedTo)
        assertNull(result.value.ip)
    }

    @Test
    fun assignsValueWithNonNulls() {
        // Given
        val testSSID = "Test SSID"
        val testBSSID = "Test:BSSID"
        val testIP = "192.168.0.1"
        val result = GetNetworkConnectionStatusResult(
            value = NetworkConnectionStatusData(
                isConnected = true,
                isConnectedToMobileNetwork = true,
                isConnectedToWifiNetwork = true,
                isRoaming = true,
                ssidOfNetworkConnectedTo = testSSID,
                bssidOfNetworkConnectedTo = testBSSID,
                ip = testIP
            )
        )

        // Expect
        assertNotNull(result)
        assertNotNull(result.value)
        assertTrue(result.value.isConnected)
        assertTrue(result.value.isConnectedToMobileNetwork)
        assertTrue(result.value.isConnectedToWifiNetwork)
        assertTrue(result.value.isRoaming)
        assertEquals(testSSID, result.value.ssidOfNetworkConnectedTo)
        assertEquals(testBSSID, result.value.bssidOfNetworkConnectedTo)
        assertEquals(testIP, result.value.ip)
    }
}
