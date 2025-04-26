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

import android.net.wifi.WifiManager
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.mockito.quality.Strictness

internal class Android30SignalApiImplTest {

    @get:Rule
    var mockitoRule: MockitoRule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS)

    @Mock
    private lateinit var mockWifiManager: WifiManager

    @Test
    fun calculateSignalLevel() {
        // Given
        val impl = Android30SignalApiImpl(wifiManager = mockWifiManager)

        // When
        impl.calculateSignalLevel(80)

        // Then
        verify(mockWifiManager, times(1)).calculateSignalLevel(80)
    }

    @Test
    fun compareSignalLevel_firstValueIsWeaker() {
        // Given
        val impl = Android30SignalApiImpl(wifiManager = mockWifiManager)

        // When
        val result = impl.compareSignalLevel(40, 60)

        // Then
        assertEquals(-20, result)
    }

    @Test
    fun compareSignalLevel_valuesAreEqual() {
        // Given
        val impl = Android30SignalApiImpl(wifiManager = mockWifiManager)

        // When
        val result = impl.compareSignalLevel(50, 50)

        // Then
        assertEquals(0, result)
    }

    @Test
    fun compareSignalLevel_firstValueIsStronger() {
        // Given
        val impl = Android30SignalApiImpl(wifiManager = mockWifiManager)

        // When
        val result = impl.compareSignalLevel(60, 40)

        // Then
        assertEquals(20, result)
    }
}
