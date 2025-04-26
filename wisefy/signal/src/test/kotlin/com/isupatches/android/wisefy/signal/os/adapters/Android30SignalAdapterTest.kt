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
package com.isupatches.android.wisefy.signal.os.adapters

import android.net.wifi.WifiManager
import com.isupatches.android.wisefy.core.constants.AssertionMessages
import com.isupatches.android.wisefy.signal.entities.CalculateSignalLevelRequest
import com.isupatches.android.wisefy.signal.entities.CalculateSignalLevelResult
import com.isupatches.android.wisefy.signal.entities.CompareSignalLevelRequest
import com.isupatches.android.wisefy.signal.entities.CompareSignalLevelResult
import com.isupatches.android.wisefy.signal.os.apis.Android30SignalApi
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.never
import org.mockito.Mock
import org.mockito.Mockito.anyInt
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.mockito.quality.Strictness

internal class Android30SignalAdapterTest {

    @get:Rule
    var mockitoRule: MockitoRule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS)

    @Mock
    private lateinit var mockWifiManager: WifiManager

    @Mock
    private lateinit var mockAndroid30SignalApi: Android30SignalApi

    @Test
    fun calculateSignalLevel_success() {
        // Given
        val adapter = Android30SignalAdapter(wifiManager = mockWifiManager, api = mockAndroid30SignalApi)
        val testRssiLevel = 80
        val request = CalculateSignalLevelRequest.Android30AndAbove(rssiLevel = testRssiLevel)
        val signalLevel = 1
        given(mockAndroid30SignalApi.calculateSignalLevel(anyInt())).willReturn(signalLevel)

        // When
        val result = adapter.calculateSignalLevel(request)

        // Then
        verify(mockAndroid30SignalApi, times(1)).calculateSignalLevel(testRssiLevel)
        assertEquals(CalculateSignalLevelResult.Success(1), result)
    }

    @Test
    fun calculateSignalLevel_failure_assertionForBelowSdk30() {
        // Given
        val adapter = Android30SignalAdapter(wifiManager = mockWifiManager, api = mockAndroid30SignalApi)
        val testRssiLevel = 80
        val request = CalculateSignalLevelRequest.BelowAndroid30(rssiLevel = testRssiLevel, numLevels = 4)

        // When
        val result = adapter.calculateSignalLevel(request)

        // Then
        verify(mockAndroid30SignalApi, never()).calculateSignalLevel(testRssiLevel)
        assertEquals(
            CalculateSignalLevelResult.Failure.Assertion(
                AssertionMessages.Signal.INCORRECT_CALCULATE_BARS_USED_ANDROID_R_OR_HIGHER,
            ),
            result,
        )
    }

    @Test
    fun compareSignalLevel_success_firstValueIsWeaker() {
        // Given
        val adapter = Android30SignalAdapter(wifiManager = mockWifiManager, api = mockAndroid30SignalApi)
        val request = CompareSignalLevelRequest(rssi1 = 40, rssi2 = 60)
        given(mockAndroid30SignalApi.compareSignalLevel(rssi1 = 40, rssi2 = 60)).willReturn(-1)

        // When
        val result = adapter.compareSignalLevel(request)

        // Then
        verify(mockAndroid30SignalApi, times(1)).compareSignalLevel(rssi1 = 40, rssi2 = 60)
        assertEquals(CompareSignalLevelResult.Success.FirstRSSIValueIsWeaker(-1), result)
    }

    @Test
    fun compareSignalLevel_success_valuesAreEqual() {
        // Given
        val adapter = Android30SignalAdapter(wifiManager = mockWifiManager, api = mockAndroid30SignalApi)
        val request = CompareSignalLevelRequest(rssi1 = 50, rssi2 = 50)
        given(mockAndroid30SignalApi.compareSignalLevel(rssi1 = 50, rssi2 = 50)).willReturn(0)

        // When
        val result = adapter.compareSignalLevel(request)

        // Then
        verify(mockAndroid30SignalApi, times(1)).compareSignalLevel(rssi1 = 50, rssi2 = 50)
        assertEquals(CompareSignalLevelResult.Success.RSSIValuesAreEqual(0), result)
    }

    @Test
    fun compareSignalLevel_success_firstValueIsStronger() {
        // Given
        val adapter = Android30SignalAdapter(wifiManager = mockWifiManager, api = mockAndroid30SignalApi)
        val request = CompareSignalLevelRequest(rssi1 = 60, rssi2 = 40)
        given(mockAndroid30SignalApi.compareSignalLevel(rssi1 = 60, rssi2 = 40)).willReturn(1)

        // When
        val result = adapter.compareSignalLevel(request)

        // Then
        verify(mockAndroid30SignalApi, times(1)).compareSignalLevel(rssi1 = 60, rssi2 = 40)
        assertEquals(CompareSignalLevelResult.Success.FirstRSSIValueIsStronger(1), result)
    }
}
