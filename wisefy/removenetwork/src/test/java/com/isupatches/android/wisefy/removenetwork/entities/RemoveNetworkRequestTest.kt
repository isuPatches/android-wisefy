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
package com.isupatches.android.wisefy.removenetwork.entities

import org.junit.Assert.assertEquals
import org.junit.Test

internal class RemoveNetworkRequestTest {

    @Test
    fun assignsValues_ssid() {
        // Given
        val request = RemoveNetworkRequest.SSID(ssid = TEST_SSID)

        // Expect
        assertEquals(TEST_SSID, request.ssid)
    }

    @Test
    fun assignsValues_bssid() {
        // Given
        val request = RemoveNetworkRequest.BSSID(bssid = TEST_BSSID)

        // Expect
        assertEquals(TEST_BSSID, request.bssid)
    }

    companion object {
        private const val TEST_SSID = "Test SSID"
        private const val TEST_BSSID: String = "01:23:45:67:89:AB"
    }
}
