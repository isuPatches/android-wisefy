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
package com.isupatches.android.wisefy.signal.os.converters

import com.isupatches.android.wisefy.signal.entities.CompareSignalLevelResult
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

internal class IntToCompareSignalLevelResultTest {

    @Test
    fun firstRSSValueIsStronger() {
        // Given
        val result = 1.toCompareSignalLevelResult()

        // Expect
        val resultCasted = result as CompareSignalLevelResult.Success.FirstRSSIValueIsStronger
        assertEquals(1, resultCasted.value)
    }

    @Test
    fun firstRSSIValueIsWeaker() {
        // Given
        val result = (-1).toCompareSignalLevelResult()

        // Expect
        val resultCasted = result as CompareSignalLevelResult.Success.FirstRSSIValueIsWeaker
        assertEquals(-1, resultCasted.value)
    }

    @Test
    fun rssiValuesAreEqual() {
        // Given
        val result = 0.toCompareSignalLevelResult()

        // Expect
        assertTrue(result is CompareSignalLevelResult.Success.RSSIValuesAreEqual)
    }
}
