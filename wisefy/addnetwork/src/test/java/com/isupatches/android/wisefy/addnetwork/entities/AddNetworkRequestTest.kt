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
package com.isupatches.android.wisefy.addnetwork.entities

import org.junit.Assert.assertEquals
import org.junit.Test

internal class AddNetworkRequestTest {

    @Test
    fun assignsValues_open() {
        // Given
        val request = AddNetworkRequest.Open(
            ssid = TEST_SSID,
            bssid = TEST_BSSID
        )

        // Expect
        assertEquals(TEST_SSID, request.ssid)
        assertEquals(TEST_BSSID, request.bssid)
    }

    @Test
    fun assignsValues_wpa2() {
        // Given
        val request = AddNetworkRequest.WPA2(
            ssid = TEST_SSID,
            bssid = TEST_BSSID,
            passphrase = TEST_PASSPHRASE
        )

        // Expect
        assertEquals(TEST_SSID, request.ssid)
        assertEquals(TEST_BSSID, request.bssid)
        assertEquals(TEST_PASSPHRASE, request.passphrase)
    }

    @Test
    fun assignsValues_wpa3() {
        // Given
        val request = AddNetworkRequest.WPA3(
            ssid = TEST_SSID,
            bssid = TEST_BSSID,
            passphrase = TEST_PASSPHRASE
        )

        // Expect
        assertEquals(TEST_SSID, request.ssid)
        assertEquals(TEST_BSSID, request.bssid)
        assertEquals(TEST_PASSPHRASE, request.passphrase)
    }

    companion object {
        private const val TEST_SSID = "Test SSID"
        private const val TEST_BSSID: String = "01:23:45:67:89:AB"
        private const val TEST_PASSPHRASE = "testPASS123!"
    }
}
