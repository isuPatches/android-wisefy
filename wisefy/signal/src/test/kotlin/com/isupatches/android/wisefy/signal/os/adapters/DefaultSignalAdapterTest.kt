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

import com.isupatches.android.wisefy.core.constants.AssertionMessages
import com.isupatches.android.wisefy.signal.entities.CalculateSignalLevelRequest
import com.isupatches.android.wisefy.signal.entities.CalculateSignalLevelResult
import com.isupatches.android.wisefy.signal.entities.CompareSignalLevelRequest
import com.isupatches.android.wisefy.signal.entities.CompareSignalLevelResult
import com.isupatches.android.wisefy.signal.os.apis.DefaultSignalApi
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

@Suppress("DEPRECATION")
internal class DefaultSignalAdapterTest {

    @get:Rule
    var mockitoRule: MockitoRule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS)

    @Mock
    private lateinit var mockDefaultSignalApi: DefaultSignalApi

    @Test
    fun calculateSignalLevel_success() {
        // Given
        val adapter = DefaultSignalAdapter(api = mockDefaultSignalApi)
        val testRssiLevel = 80
        val request = CalculateSignalLevelRequest.BelowAndroid30(
            rssiLevel = testRssiLevel,
            numLevels = 4,
        )
        val signalLevel = 1
        given(mockDefaultSignalApi.calculateSignalLevel(anyInt(), anyInt())).willReturn(signalLevel)

        // When
        val result = adapter.calculateSignalLevel(request)

        // Then
        verify(mockDefaultSignalApi, times(1)).calculateSignalLevel(
            testRssiLevel,
            4,
        )
        assertEquals(CalculateSignalLevelResult.Success(1), result)
    }

    @Test
    fun calculateSignalLevel_failure_assertionForUsingOnSdk30OrAbove() {
        // Given
        val adapter = DefaultSignalAdapter(api = mockDefaultSignalApi)
        val testRssiLevel = 80
        val request = CalculateSignalLevelRequest.Android30AndAbove(rssiLevel = testRssiLevel)

        // When
        val result = adapter.calculateSignalLevel(request)

        // Then
        verify(mockDefaultSignalApi, never()).calculateSignalLevel(
            testRssiLevel,
            4,
        )
        assertEquals(
            CalculateSignalLevelResult.Failure.Assertion(
                AssertionMessages.Signal.INCORRECT_CALCULATE_BARS_USED_PRE_ANDROID_R,
            ),
            result,
        )
    }

    @Test
    fun compareSignalLevel_success_firstValueIsWeaker() {
        // Given
        val adapter = DefaultSignalAdapter(api = mockDefaultSignalApi)
        val request = CompareSignalLevelRequest(rssi1 = 40, rssi2 = 60)
        given(mockDefaultSignalApi.compareSignalLevel(rssi1 = 40, rssi2 = 60)).willReturn(-1)

        // When
        val result = adapter.compareSignalLevel(request)

        // Then
        verify(mockDefaultSignalApi, times(1)).compareSignalLevel(rssi1 = 40, rssi2 = 60)
        assertEquals(CompareSignalLevelResult.Success.FirstRSSIValueIsWeaker(-1), result)
    }

    @Test
    fun compareSignalLevel_success_valuesAreEqual() {
        // Given
        val adapter = DefaultSignalAdapter(api = mockDefaultSignalApi)
        val request = CompareSignalLevelRequest(rssi1 = 50, rssi2 = 50)
        given(mockDefaultSignalApi.compareSignalLevel(rssi1 = 50, rssi2 = 50)).willReturn(0)

        // When
        val result = adapter.compareSignalLevel(request)

        // Then
        verify(mockDefaultSignalApi, times(1)).compareSignalLevel(rssi1 = 50, rssi2 = 50)
        assertEquals(CompareSignalLevelResult.Success.RSSIValuesAreEqual(0), result)
    }

    @Test
    fun compareSignalLevel_success_firstValueIsStronger() {
        // Given
        val adapter = DefaultSignalAdapter(api = mockDefaultSignalApi)
        val request = CompareSignalLevelRequest(rssi1 = 60, rssi2 = 40)
        given(mockDefaultSignalApi.compareSignalLevel(rssi1 = 60, rssi2 = 40)).willReturn(1)

        // When
        val result = adapter.compareSignalLevel(request)

        // Then
        verify(mockDefaultSignalApi, times(1)).compareSignalLevel(rssi1 = 60, rssi2 = 40)
        assertEquals(CompareSignalLevelResult.Success.FirstRSSIValueIsStronger(1), result)
    }
}
