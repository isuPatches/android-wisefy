/*
 * Copyright (c) 2024. Patches Barrett
 *
 * Last modified: September 21, 2024
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
import org.junit.Test

internal class CompareSignalLevelConvertersTest {

    @Test
    fun rssiValueIsWeaker() {
        val result = (-1).toCompareSignalLevelResult()
        assertEquals(CompareSignalLevelResult.Success.FirstRSSIValueIsWeaker(-1), result)
    }

    @Test
    fun rssiValueIsEqual() {
        val result = 0.toCompareSignalLevelResult()
        assertEquals(CompareSignalLevelResult.Success.RSSIValuesAreEqual(0), result)
    }

    @Test
    fun rssiValueIsStronger() {
        val result = 1.toCompareSignalLevelResult()
        assertEquals(CompareSignalLevelResult.Success.FirstRSSIValueIsStronger(1), result)
    }
}
