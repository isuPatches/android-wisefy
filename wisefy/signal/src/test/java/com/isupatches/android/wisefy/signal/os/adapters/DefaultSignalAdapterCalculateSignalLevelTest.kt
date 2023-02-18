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

import com.isupatches.android.wisefy.core.assertions.WisefyAssertions
import com.isupatches.android.wisefy.core.constants.AssertionMessages
import com.isupatches.android.wisefy.core.logging.DefaultWisefyLogger
import com.isupatches.android.wisefy.signal.entities.CalculateSignalLevelRequest
import com.isupatches.android.wisefy.signal.entities.CalculateSignalLevelResult
import com.isupatches.android.wisefy.signal.os.apis.DefaultSignalApi
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
internal class DefaultSignalAdapterCalculateSignalLevelTest(
    private val params: CalculateSignalLevelParams
) {

    @Mock
    private lateinit var mockApi: DefaultSignalApi

    private lateinit var adapter: DefaultSignalAdapter

    private var closable: AutoCloseable? = null

    @Before
    fun setUp() {
        closable = MockitoAnnotations.openMocks(this)
        adapter = DefaultSignalAdapter(
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
            @Suppress("Deprecation")
            given(mockApi.calculateSignalLevel(anyInt(), anyInt())).willReturn(params.apiResult)
        }

        // Then
        val result = adapter.calculateSignalLevel(params.request)

        // When
        if (params.apiResult != null) {
            @Suppress("Deprecation")
            verify(mockApi, times(1)).calculateSignalLevel(anyInt(), anyInt())
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
                    apiResult = TEST_VALUE,
                    expectedResult = CalculateSignalLevelResult.Success(value = TEST_VALUE)
                ),
                CalculateSignalLevelParams(
                    request = CalculateSignalLevelRequest.Android30AndAbove(rssi = TEST_RSSI1),
                    expectedResult = CalculateSignalLevelResult.Failure.Assertion(
                        message = AssertionMessages.Signal.INCORRECT_CALCULATE_BARS_USED_PRE_ANDROID_R
                    )
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
