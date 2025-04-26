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
package com.isupatches.android.wisefy.wifi.os.adapters

import android.content.Context
import android.net.wifi.WifiManager
import com.isupatches.android.wisefy.core.constants.AssertionMessages
import com.isupatches.android.wisefy.wifi.entities.DisableWifiRequest
import com.isupatches.android.wisefy.wifi.entities.DisableWifiResult
import com.isupatches.android.wisefy.wifi.entities.EnableWifiRequest
import com.isupatches.android.wisefy.wifi.entities.EnableWifiResult
import com.isupatches.android.wisefy.wifi.entities.IsWifiEnabledQuery
import com.isupatches.android.wisefy.wifi.entities.IsWifiEnabledResult
import com.isupatches.android.wisefy.wifi.os.apis.Android29WifiApi
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.never
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.mockito.quality.Strictness

internal class Android29AdapterTest {

    @get:Rule
    var mockitoRule: MockitoRule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS)

    @Mock
    private lateinit var mockWifiManager: WifiManager

    @Mock
    private lateinit var mockApi: Android29WifiApi

    @Mock
    private lateinit var mockContext: Context

    @Test
    fun disableWifi() {
        // Given
        val adapter = Android29WifiAdapter(wifiManager = mockWifiManager, api = mockApi)

        // When
        val result = adapter.disableWifi(DisableWifiRequest.Android29OrAbove(context = mockContext))

        // Then
        verify(mockApi, times(1)).openWifiSettings(mockContext)
        assertEquals(DisableWifiResult.Success.WifiSettingScreenOpened, result)
    }

    @Test
    fun disableWifi_assertion_default() {
        // Given
        val adapter = Android29WifiAdapter(wifiManager = mockWifiManager, api = mockApi)

        // When
        val result = adapter.disableWifi(DisableWifiRequest.Default)

        // Then
        verify(mockApi, never()).openWifiSettings(mockContext)
        assertEquals(
            DisableWifiResult.Failure.Assertion(AssertionMessages.Wifi.DEFAULT_REQUEST_USED_ANDROID_29_OR_HIGHER),
            result,
        )
    }

    @Test
    fun enableWifi() {
        // Given
        val adapter = Android29WifiAdapter(wifiManager = mockWifiManager, api = mockApi)

        // When
        val result = adapter.enableWifi(EnableWifiRequest.Android29OrAbove(context = mockContext))

        // Then
        verify(mockApi, times(1)).openWifiSettings(mockContext)
        assertEquals(EnableWifiResult.Success.WifiSettingScreenOpened, result)
    }

    @Test
    fun enableWifi_assertion_default() {
        // Given
        val adapter = Android29WifiAdapter(wifiManager = mockWifiManager, api = mockApi)

        // When
        val result = adapter.enableWifi(EnableWifiRequest.Default)

        // Then
        verify(mockApi, never()).openWifiSettings(mockContext)
        assertEquals(
            EnableWifiResult.Failure.Assertion(AssertionMessages.Wifi.DEFAULT_REQUEST_USED_ANDROID_29_OR_HIGHER),
            result,
        )
    }

    @Test
    fun isWifiEnabled_disabled() {
        // Given
        given(mockApi.isWifiEnabled()).willReturn(false)
        val adapter = Android29WifiAdapter(wifiManager = mockWifiManager, api = mockApi)

        // When
        val result = adapter.isWifiEnabled(IsWifiEnabledQuery())

        // Then
        verify(mockApi, times(1)).isWifiEnabled()
        assertEquals(IsWifiEnabledResult.False, result)
    }

    @Test
    fun isWifiEnabled_enabled() {
        // Given
        given(mockApi.isWifiEnabled()).willReturn(true)
        val adapter = Android29WifiAdapter(wifiManager = mockWifiManager, api = mockApi)

        // When
        val result = adapter.isWifiEnabled(IsWifiEnabledQuery())

        // Then
        verify(mockApi, times(1)).isWifiEnabled()
        assertEquals(IsWifiEnabledResult.True, result)
    }
}
