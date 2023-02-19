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
package com.isupatches.android.wisefy.signal.os.impls

import android.net.wifi.WifiManager
import android.os.Build
import com.isupatches.android.wisefy.core.logging.DefaultWisefyLogger
import com.isupatches.android.wisefy.signal.os.apis.Android30SignalApi
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assume.assumeTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(Parameterized::class)
internal class Android30SignalApiImplCompareSignalLevelTest(
    private val params: CompareSignalLevelParams
) {

    @Mock
    private lateinit var mockWifiManager: WifiManager

    private var closable: AutoCloseable? = null

    private lateinit var api: Android30SignalApi

    @Before
    fun setUp() {
        assumeTrue(
            "Can only run on API Level ${Build.VERSION_CODES.R} or newer",
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.R
        )
        closable = MockitoAnnotations.openMocks(this)
        api = Android30SignalApiImpl(logger = DefaultWisefyLogger(), wifiManager = mockWifiManager)
    }

    @After
    fun tearDown() {
        closable?.close()
    }

    @Test
    fun test() {
        // Given
        val result = api.compareSignalLevel(rssi1 = params.rssi1, rssi2 = params.rssi2)

        // Expect
        assertEquals(params.expectedResult, result)
    }

    companion object {
        private const val TEST_RSSI1_NEGATIVE = -65
        private const val TEST_RSSI2_EQUAL_NEGATIVE = -65
        private const val TEST_RSSI2_LESSER_NEGATIVE = -66
        private const val TEST_RSSI2_GREATER_NEGATIVE = -64

        private const val TEST_RSSI1_POSITIVE = 65
        private const val TEST_RSSI2_EQUAL_POSITIVE = 65
        private const val TEST_RSSI2_LESSER_POSITIVE = 64
        private const val TEST_RSSI2_GREATER_POSITIVE = 66

        @JvmStatic
        @Parameterized.Parameters(name = "{index}: {0}")
        fun paramValues(): List<CompareSignalLevelParams> {
            return listOf(
                CompareSignalLevelParams(
                    rssi1 = TEST_RSSI1_NEGATIVE,
                    rssi2 = TEST_RSSI2_EQUAL_NEGATIVE,
                    expectedResult = 0
                ),
                CompareSignalLevelParams(
                    rssi1 = TEST_RSSI1_NEGATIVE,
                    rssi2 = TEST_RSSI2_LESSER_NEGATIVE,
                    expectedResult = 1
                ),
                CompareSignalLevelParams(
                    rssi1 = TEST_RSSI1_NEGATIVE,
                    rssi2 = TEST_RSSI2_GREATER_NEGATIVE,
                    expectedResult = -1
                ),
                CompareSignalLevelParams(
                    rssi1 = TEST_RSSI1_POSITIVE,
                    rssi2 = TEST_RSSI2_EQUAL_POSITIVE,
                    expectedResult = 0
                ),
                CompareSignalLevelParams(
                    rssi1 = TEST_RSSI1_POSITIVE,
                    rssi2 = TEST_RSSI2_LESSER_POSITIVE,
                    expectedResult = 1
                ),
                CompareSignalLevelParams(
                    rssi1 = TEST_RSSI1_POSITIVE,
                    rssi2 = TEST_RSSI2_GREATER_POSITIVE,
                    expectedResult = -1
                ),
                CompareSignalLevelParams(
                    rssi1 = TEST_RSSI2_EQUAL_NEGATIVE,
                    rssi2 = TEST_RSSI1_NEGATIVE,
                    expectedResult = 0
                ),
                CompareSignalLevelParams(
                    rssi1 = TEST_RSSI2_LESSER_NEGATIVE,
                    rssi2 = TEST_RSSI1_NEGATIVE,
                    expectedResult = -1
                ),
                CompareSignalLevelParams(
                    rssi1 = TEST_RSSI2_GREATER_NEGATIVE,
                    rssi2 = TEST_RSSI1_NEGATIVE,
                    expectedResult = 1
                ),
                CompareSignalLevelParams(
                    rssi1 = TEST_RSSI2_EQUAL_POSITIVE,
                    rssi2 = TEST_RSSI1_POSITIVE,
                    expectedResult = 0
                ),
                CompareSignalLevelParams(
                    rssi1 = TEST_RSSI2_LESSER_POSITIVE,
                    rssi2 = TEST_RSSI1_POSITIVE,
                    expectedResult = -1
                ),
                CompareSignalLevelParams(
                    rssi1 = TEST_RSSI2_GREATER_POSITIVE,
                    rssi2 = TEST_RSSI1_POSITIVE,
                    expectedResult = 1
                )
            )
        }

        data class CompareSignalLevelParams(
            val rssi1: Int,
            val rssi2: Int,
            val expectedResult: Int
        )
    }
}
