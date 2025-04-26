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
@file:Suppress("DEPRECATION")

package com.isupatches.android.wisefy.wifi.os.impls

import android.net.wifi.WifiManager
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.mockito.quality.Strictness

internal class DefaultWifiApiImplTest {

    @get:Rule
    var mockitoRule: MockitoRule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS)

    @Mock
    private lateinit var mockWifiManager: WifiManager

    @Test
    fun disableWifi_success() {
        // Given
        given(mockWifiManager.setWifiEnabled(false)).willReturn(true)
        val impl = DefaultWifiApiImpl(wifiManager = mockWifiManager)

        // When
        val result = impl.disableWifi()

        // Then
        verify(mockWifiManager, times(1)).setWifiEnabled(false)
        assertTrue(result)
    }

    @Test
    fun disableWifi_unableToDisable() {
        // Given
        given(mockWifiManager.setWifiEnabled(false)).willReturn(false)
        val impl = DefaultWifiApiImpl(wifiManager = mockWifiManager)

        // When
        val result = impl.disableWifi()

        // Then
        verify(mockWifiManager, times(1)).setWifiEnabled(false)
        assertFalse(result)
    }

    @Test
    fun enableWifi_success() {
        // Given
        given(mockWifiManager.setWifiEnabled(true)).willReturn(true)
        val impl = DefaultWifiApiImpl(wifiManager = mockWifiManager)

        // When
        val result = impl.enableWifi()

        // Then
        verify(mockWifiManager, times(1)).setWifiEnabled(true)
        assertTrue(result)
    }

    @Test
    fun enableWifi_unableToEnable() {
        // Given
        given(mockWifiManager.setWifiEnabled(true)).willReturn(false)
        val impl = DefaultWifiApiImpl(wifiManager = mockWifiManager)

        // When
        val result = impl.enableWifi()

        // Then
        verify(mockWifiManager, times(1)).setWifiEnabled(true)
        assertFalse(result)
    }

    @Test
    fun isWifiEnabled_disabled() {
        // Given
        given(mockWifiManager.isWifiEnabled).willReturn(false)
        val impl = DefaultWifiApiImpl(wifiManager = mockWifiManager)

        // When
        val result = impl.isWifiEnabled()

        // Then
        verify(mockWifiManager, times(1)).isWifiEnabled
        assertFalse(result)
    }

    @Test
    fun isWifiEnabled_enabled() {
        // Given
        given(mockWifiManager.isWifiEnabled).willReturn(true)
        val impl = DefaultWifiApiImpl(wifiManager = mockWifiManager)

        // When
        val result = impl.isWifiEnabled()

        // Then
        verify(mockWifiManager, times(1)).isWifiEnabled
        assertTrue(result)
    }
}
