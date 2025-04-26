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
package com.isupatches.android.wisefy.signal

import android.net.wifi.WifiManager
import com.isupatches.android.wisefy.core.constants.AssertionMessages
import com.isupatches.android.wisefy.signal.entities.CalculateSignalLevelRequest
import com.isupatches.android.wisefy.signal.entities.CalculateSignalLevelResult
import com.isupatches.android.wisefy.signal.entities.CompareSignalLevelRequest
import com.isupatches.android.wisefy.signal.entities.CompareSignalLevelResult
import com.isupatches.android.wisefy.testsupport.anyNonNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.times
import org.mockito.BDDMockito.verify
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.mockito.quality.Strictness

internal class WisefySignalDelegateTest {

    @get:Rule
    var mockitoRule: MockitoRule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS)

    @Mock
    private lateinit var mockWifiManager: WifiManager

    @Mock
    private lateinit var mockAdapter: SignalApi

    private lateinit var delegate: WisefySignalDelegate

    @Before
    fun setUp() {
        delegate = WisefySignalDelegate(wifiManager = mockWifiManager, adapter = mockAdapter)
    }

    @Test
    fun calculateSignalLevel_belowAndroid30_success() {
        // Given
        given(mockAdapter.calculateSignalLevel(anyNonNull())).willReturn(CalculateSignalLevelResult.Success(1))
        val request = CalculateSignalLevelRequest.BelowAndroid30(rssiLevel = -1, numLevels = 4)

        // When
        val result = delegate.calculateSignalLevel(request = request)

        // Then
        assertTrue(result is CalculateSignalLevelResult.Success)
        verify(mockAdapter, times(1)).calculateSignalLevel(request)
    }

    @Test
    fun calculateSignalLevel_belowAndroid30_failure() {
        // Given
        given(mockAdapter.calculateSignalLevel(anyNonNull())).willReturn(
            CalculateSignalLevelResult.Failure.Assertion(
                message = AssertionMessages.Signal.INCORRECT_CALCULATE_BARS_USED_PRE_ANDROID_R,
            ),
        )
        val request = CalculateSignalLevelRequest.BelowAndroid30(rssiLevel = -1, numLevels = 4)

        // When
        val result = delegate.calculateSignalLevel(request = request)

        // Then
        assertTrue(result is CalculateSignalLevelResult.Failure)
        verify(mockAdapter, times(1)).calculateSignalLevel(request)
    }

    @Test
    fun calculateSignalLevel_aboveAndroid30_success() {
        // Given
        given(mockAdapter.calculateSignalLevel(anyNonNull())).willReturn(CalculateSignalLevelResult.Success(1))
        val request = CalculateSignalLevelRequest.Android30AndAbove(rssiLevel = -1)

        // When
        val result = delegate.calculateSignalLevel(request = request)

        // Then
        assertTrue(result is CalculateSignalLevelResult.Success)
        verify(mockAdapter, times(1)).calculateSignalLevel(request)
    }

    @Test
    fun calculateSignalLevel_aboveAndroid30_failure() {
        // Given
        given(mockAdapter.calculateSignalLevel(anyNonNull())).willReturn(
            CalculateSignalLevelResult.Failure.Assertion(
                message = AssertionMessages.Signal.INCORRECT_CALCULATE_BARS_USED_PRE_ANDROID_R,
            ),
        )
        val request = CalculateSignalLevelRequest.Android30AndAbove(rssiLevel = -1)

        // When
        val result = delegate.calculateSignalLevel(request = request)

        // Then
        assertTrue(result is CalculateSignalLevelResult.Failure)
        verify(mockAdapter, times(1)).calculateSignalLevel(request)
    }

    @Test
    fun compareSignalLevel_success_firstValueIsWeaker() {
        // Given
        given(mockAdapter.compareSignalLevel(anyNonNull())).willReturn(
            CompareSignalLevelResult.Success.FirstRSSIValueIsWeaker(-1),
        )
        val request = CompareSignalLevelRequest(rssi1 = 40, rssi2 = 60)

        // When
        val result = delegate.compareSignalLevel(request = request)

        // Then
        assertTrue(result is CompareSignalLevelResult.Success.FirstRSSIValueIsWeaker)
        verify(mockAdapter, times(1)).compareSignalLevel(request)
    }

    @Test
    fun compareSignalLevel_success_equal() {
        // Given
        given(mockAdapter.compareSignalLevel(anyNonNull())).willReturn(
            CompareSignalLevelResult.Success.RSSIValuesAreEqual(0),
        )
        val request = CompareSignalLevelRequest(rssi1 = 50, rssi2 = 50)

        // When
        val result = delegate.compareSignalLevel(request = request)

        // Then
        assertTrue(result is CompareSignalLevelResult.Success.RSSIValuesAreEqual)
        verify(mockAdapter, times(1)).compareSignalLevel(request)
    }

    @Test
    fun compareSignalLevel_success_firstValueIsStronger() {
        // Given
        given(mockAdapter.compareSignalLevel(anyNonNull())).willReturn(
            CompareSignalLevelResult.Success.FirstRSSIValueIsStronger(1),
        )
        val request = CompareSignalLevelRequest(rssi1 = 60, rssi2 = 40)

        // When
        val result = delegate.compareSignalLevel(request = request)

        // Then
        assertTrue(result is CompareSignalLevelResult.Success.FirstRSSIValueIsStronger)
        verify(mockAdapter, times(1)).compareSignalLevel(request)
    }
}
