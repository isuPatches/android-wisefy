/*
 * Copyright (c) 2024. Patches Barrett
 *
 * Last modified: September 22, 2024
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
package com.isupatches.android.wisefy.removenetwork.os.adapters

import com.isupatches.android.wisefy.core.constants.AssertionMessages.AndroidQ.SAVED_NETWORK_FUNCTIONALITY_UNAVAILABLE_ANDROID_Q
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkRequest
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkResult
import org.junit.Assert.assertEquals
import org.junit.Test

internal class Android29RemoveNetworkAdapterTest {

    @Test
    fun removeNetwork_bySSID_assertion() {
        // Given
        val adapter = Android29RemoveNetworkAdapter()

        // When
        val result = adapter.removeNetwork(RemoveNetworkRequest.SSID(TEST_SSID))

        // Expect
        assertEquals(
            RemoveNetworkResult.Failure.Assertion(SAVED_NETWORK_FUNCTIONALITY_UNAVAILABLE_ANDROID_Q),
            result,
        )
    }

    @Test
    fun removeNetwork_byBSSID_assertion() {
        // Given
        val adapter = Android29RemoveNetworkAdapter()

        // When
        val result = adapter.removeNetwork(RemoveNetworkRequest.SSID(TEST_BSSID))

        // Expect
        assertEquals(
            RemoveNetworkResult.Failure.Assertion(SAVED_NETWORK_FUNCTIONALITY_UNAVAILABLE_ANDROID_Q),
            result,
        )
    }

    companion object {
        private const val TEST_SSID: String = "Test SSID"
        private const val TEST_BSSID: String = "Test:BSSID"
    }
}
