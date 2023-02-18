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
package com.isupatches.android.wisefy.signal.os.adapters

import android.net.wifi.WifiManager
import com.isupatches.android.wisefy.core.assertions.WisefyAssertions
import com.isupatches.android.wisefy.core.constants.AssertionMessages
import com.isupatches.android.wisefy.core.logging.DefaultWisefyLogger
import com.isupatches.android.wisefy.signal.entities.CalculateSignalLevelRequest
import com.isupatches.android.wisefy.signal.entities.CalculateSignalLevelResult
import com.isupatches.android.wisefy.signal.os.apis.Android30SignalApi
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.mockito.BDDMockito.anyInt
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@RunWith(Parameterized::class)
internal class Android30SignalAdapterCalculateSignalLevelTest(
    private val params: CalculateSignalLevelParams
) {

    @Mock
    private lateinit var mockWifiManager: WifiManager

    @Mock
    private lateinit var mockApi: Android30SignalApi

    private lateinit var adapter: Android30SignalAdapter

    private var closable: AutoCloseable? = null

    @Before
    fun setUp() {
        closable = MockitoAnnotations.openMocks(this)
        adapter = Android30SignalAdapter(
            wifiManager = mockWifiManager,
            logger = DefaultWisefyLogger(),
            assertions = WisefyAssertions(false),
            api = mockApi
        )
    }

    @After
    fun tearDown() {
        closable?.close()
    }

    @Test
    fun test() {
        // Given
        if (params.apiResult != null) {
            given(mockApi.calculateSignalLevel(anyInt())).willReturn(params.apiResult)
        }

        // Then
        val result = adapter.calculateSignalLevel(params.request)

        // When
        if (params.apiResult != null) {
            verify(mockApi, times(1)).calculateSignalLevel(anyInt())
        }
        assertEquals(params.expectedResult, result)
    }

    companion object {
        private const val TEST_RSSI1 = -65
        private const val TEST_DESIRED_NUMBER_OF_BARS = 4
        private const val TEST_VALUE = 0

        @JvmStatic
        @Parameterized.Parameters(name = "{index}: {0}")
        fun paramValues(): List<CalculateSignalLevelParams> {
            return listOf(
                CalculateSignalLevelParams(
                    request = CalculateSignalLevelRequest.BelowAndroid30(
                        rssi = TEST_RSSI1,
                        desiredNumberOfBars = TEST_DESIRED_NUMBER_OF_BARS,
                    ),
                    expectedResult = CalculateSignalLevelResult.Failure.Assertion(
                        message = AssertionMessages.Signal.INCORRECT_CALCULATE_BARS_USED_ANDROID_R_OR_HIGHER
                    )
                ),
                CalculateSignalLevelParams(
                    request = CalculateSignalLevelRequest.Android30AndAbove(rssi = TEST_RSSI1),
                    apiResult = TEST_VALUE,
                    expectedResult = CalculateSignalLevelResult.Success(value = TEST_VALUE)
                )
            )
        }

        data class CalculateSignalLevelParams(
            val request: CalculateSignalLevelRequest,
            val apiResult: Int? = null,
            val expectedResult: CalculateSignalLevelResult
        )
    }
}
