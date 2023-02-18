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
import com.isupatches.android.wisefy.core.logging.DefaultWisefyLogger
import com.isupatches.android.wisefy.signal.entities.CompareSignalLevelRequest
import com.isupatches.android.wisefy.signal.entities.CompareSignalLevelResult
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
internal class DefaultSignalAdapterCompareSignalLevelTest(
    private val params: CompareSignalLevelParams
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
        given(mockApi.compareSignalLevel(anyInt(), anyInt())).willReturn(params.apiResult)

        // Then
        val result = adapter.compareSignalLevel(params.request)

        // When
        verify(mockApi, times(1)).compareSignalLevel(anyInt(), anyInt())
        assertEquals(params.expectedResult, result)
    }

    companion object {
        private const val TEST_RSSI_1 = -65
        private const val TEST_RSSI_2 = -66

        @JvmStatic
        @Parameterized.Parameters(name = "{index}: {0}")
        fun paramValues(): List<CompareSignalLevelParams> {
            return listOf(
                CompareSignalLevelParams(
                    request = CompareSignalLevelRequest(TEST_RSSI_1, TEST_RSSI_2),
                    apiResult = 0,
                    expectedResult = CompareSignalLevelResult.Success.RSSIValuesAreEqual
                ),
                CompareSignalLevelParams(
                    request = CompareSignalLevelRequest(TEST_RSSI_1, TEST_RSSI_2),
                    apiResult = -1,
                    expectedResult = CompareSignalLevelResult.Success.FirstRSSIValueIsWeaker(-1)
                ),
                CompareSignalLevelParams(
                    request = CompareSignalLevelRequest(TEST_RSSI_1, TEST_RSSI_2),
                    apiResult = 1,
                    expectedResult = CompareSignalLevelResult.Success.FirstRSSIValueIsStronger(1)
                )
            )
        }

        data class CompareSignalLevelParams(
            val request: CompareSignalLevelRequest,
            val apiResult: Int,
            val expectedResult: CompareSignalLevelResult
        )
    }
}
