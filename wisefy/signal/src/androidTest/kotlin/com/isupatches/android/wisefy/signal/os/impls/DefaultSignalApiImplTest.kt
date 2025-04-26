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
package com.isupatches.android.wisefy.signal.os.impls

import org.junit.Assert.assertEquals
import org.junit.Test

internal class DefaultSignalApiImplTest {

    @Test
    fun calculateSignalLevel() {
        // Given
        val impl = DefaultSignalApiImpl()

        // When
        @Suppress("DEPRECATION")
        val result = impl.calculateSignalLevel(80, 4)

        // Then
        assertEquals(3, result)
    }

    @Test
    fun compareSignalLevel_firstValueIsWeaker() {
        // Given
        val impl = DefaultSignalApiImpl()

        // When
        val result = impl.compareSignalLevel(40, 60)

        // Then
        assertEquals(-20, result)
    }

    @Test
    fun compareSignalLevel_valuesAreEqual() {
        // Given
        val impl = DefaultSignalApiImpl()

        // When
        val result = impl.compareSignalLevel(50, 50)

        // Then
        assertEquals(0, result)
    }

    @Test
    fun compareSignalLevel_firstValueIsStronger() {
        // Given
        val impl = DefaultSignalApiImpl()

        // When
        val result = impl.compareSignalLevel(60, 40)

        // Then
        assertEquals(20, result)
    }
}
