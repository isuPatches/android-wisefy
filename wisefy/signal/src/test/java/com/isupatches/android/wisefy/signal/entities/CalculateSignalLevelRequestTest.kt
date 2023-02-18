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

internal class CalculateSignalLevelRequestTest {

    @Test
    fun success() {
        // Given
        val result = CalculateSignalLevelRequest.BelowAndroid30(
            rssi = TEST_RSSI,
            desiredNumberOfBars = TEST_DESIRED_NUMBER_OF_BARS
        )

        // Expect
        assertEquals(TEST_RSSI, result.rssi)
        assertEquals(TEST_DESIRED_NUMBER_OF_BARS, result.desiredNumberOfBars)
    }

    @Test
    fun failure_assertion() {
        // Given
        val result = CalculateSignalLevelRequest.Android30AndAbove(rssi = TEST_RSSI)

        // Expect
        assertEquals(TEST_RSSI, result.rssi)
    }

    companion object {
        const val TEST_RSSI = -65
        const val TEST_DESIRED_NUMBER_OF_BARS = 4
    }
}
