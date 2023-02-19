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
package com.isupatches.android.wisefy.signal.entities

import org.junit.Assert.assertEquals
import org.junit.Test

internal class CompareSignalLevelRequestTest {

    @Test
    fun assignValues() {
        // Given
        val result = CompareSignalLevelRequest(rssi1 = TEST_RSSI_1, rssi2 = TEST_RSSI_2)

        // Expect
        assertEquals(TEST_RSSI_1, result.rssi1)
        assertEquals(TEST_RSSI_2, result.rssi2)
    }

    companion object {
        const val TEST_RSSI_1 = -65
        const val TEST_RSSI_2 = -66
    }
}
