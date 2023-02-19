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

import android.content.Context
import android.net.wifi.WifiManager
import android.os.Build
import androidx.test.platform.app.InstrumentationRegistry
import com.isupatches.android.wisefy.core.logging.DefaultWisefyLogger
import com.isupatches.android.wisefy.signal.os.apis.Android30SignalApi
import org.junit.Assert.assertEquals
import org.junit.Assume.assumeTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class Android30SignalApiImplCalculateSignalLevelTest(
    private val params: CompareSignalLevelParams
) {

    private lateinit var api: Android30SignalApi

    @Before
    fun setUp() {
        assumeTrue(
            "Can only run on API Level ${Build.VERSION_CODES.R} or newer",
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.R
        )
        val wifiManager = InstrumentationRegistry.getInstrumentation()
            .targetContext
            .applicationContext
            .getSystemService(Context.WIFI_SERVICE) as WifiManager
        api = Android30SignalApiImpl(logger = DefaultWisefyLogger(), wifiManager = wifiManager)
    }

    @Test
    fun test() {
        // Given
        @Suppress("Deprecation")
        val result = api.calculateSignalLevel(rssi = params.rssi)

        // Expect
        assertEquals(params.expectedResult, result)
    }

    companion object {
        private const val TEST_RSSI_NEGATIVE_FULL = -100
        private const val TEST_RSSI_NEGATIVE_HALF = -50
        private const val TEST_RSSI_ZERO = 0
        private const val TEST_RSSI_POSITIVE_HALF = 50
        private const val TEST_RSSI_POSITIVE_FULL = 100

        @JvmStatic
        @Parameterized.Parameters(name = "{index}: {0}")
        fun paramValues(): List<CompareSignalLevelParams> {
            return listOf(
                CompareSignalLevelParams(
                    rssi = TEST_RSSI_NEGATIVE_FULL,
                    desiredNumberOfBars = -1,
                    expectedResult = 0
                ),
                CompareSignalLevelParams(
                    rssi = TEST_RSSI_NEGATIVE_FULL,
                    desiredNumberOfBars = 0,
                    expectedResult = 0
                ),
                CompareSignalLevelParams(
                    rssi = TEST_RSSI_NEGATIVE_FULL,
                    desiredNumberOfBars = 1,
                    expectedResult = 0
                ),
                CompareSignalLevelParams(
                    rssi = TEST_RSSI_NEGATIVE_FULL,
                    desiredNumberOfBars = 2,
                    expectedResult = 0
                ),
                CompareSignalLevelParams(
                    rssi = TEST_RSSI_NEGATIVE_FULL,
                    desiredNumberOfBars = 3,
                    expectedResult = 0
                ),
                CompareSignalLevelParams(
                    rssi = TEST_RSSI_NEGATIVE_FULL,
                    desiredNumberOfBars = 4,
                    expectedResult = 0
                ),
                CompareSignalLevelParams(
                    rssi = TEST_RSSI_NEGATIVE_FULL,
                    desiredNumberOfBars = 5,
                    expectedResult = 0
                ),
                CompareSignalLevelParams(
                    rssi = TEST_RSSI_NEGATIVE_HALF,
                    desiredNumberOfBars = -1,
                    expectedResult = 4
                ),
                CompareSignalLevelParams(
                    rssi = TEST_RSSI_NEGATIVE_HALF,
                    desiredNumberOfBars = 0,
                    expectedResult = 4
                ),
                CompareSignalLevelParams(
                    rssi = TEST_RSSI_NEGATIVE_HALF,
                    desiredNumberOfBars = 1,
                    expectedResult = 4
                ),
                CompareSignalLevelParams(
                    rssi = TEST_RSSI_NEGATIVE_HALF,
                    desiredNumberOfBars = 2,
                    expectedResult = 4
                ),
                CompareSignalLevelParams(
                    rssi = TEST_RSSI_NEGATIVE_HALF,
                    desiredNumberOfBars = 3,
                    expectedResult = 4
                ),
                CompareSignalLevelParams(
                    rssi = TEST_RSSI_NEGATIVE_HALF,
                    desiredNumberOfBars = 4,
                    expectedResult = 4
                ),
                CompareSignalLevelParams(
                    rssi = TEST_RSSI_NEGATIVE_HALF,
                    desiredNumberOfBars = 5,
                    expectedResult = 4
                ),
                CompareSignalLevelParams(
                    rssi = TEST_RSSI_ZERO,
                    desiredNumberOfBars = -1,
                    expectedResult = 4
                ),
                CompareSignalLevelParams(
                    rssi = TEST_RSSI_ZERO,
                    desiredNumberOfBars = 0,
                    expectedResult = 4
                ),
                CompareSignalLevelParams(
                    rssi = TEST_RSSI_ZERO,
                    desiredNumberOfBars = 1,
                    expectedResult = 4
                ),
                CompareSignalLevelParams(
                    rssi = TEST_RSSI_ZERO,
                    desiredNumberOfBars = 2,
                    expectedResult = 4
                ),
                CompareSignalLevelParams(
                    rssi = TEST_RSSI_ZERO,
                    desiredNumberOfBars = 3,
                    expectedResult = 4
                ),
                CompareSignalLevelParams(
                    rssi = TEST_RSSI_ZERO,
                    desiredNumberOfBars = 4,
                    expectedResult = 4
                ),
                CompareSignalLevelParams(
                    rssi = TEST_RSSI_ZERO,
                    desiredNumberOfBars = 5,
                    expectedResult = 4
                ),
                CompareSignalLevelParams(
                    rssi = TEST_RSSI_POSITIVE_HALF,
                    desiredNumberOfBars = -1,
                    expectedResult = 4
                ),
                CompareSignalLevelParams(
                    rssi = TEST_RSSI_POSITIVE_HALF,
                    desiredNumberOfBars = 0,
                    expectedResult = 4
                ),
                CompareSignalLevelParams(
                    rssi = TEST_RSSI_POSITIVE_HALF,
                    desiredNumberOfBars = 1,
                    expectedResult = 4
                ),
                CompareSignalLevelParams(
                    rssi = TEST_RSSI_POSITIVE_HALF,
                    desiredNumberOfBars = 2,
                    expectedResult = 4
                ),
                CompareSignalLevelParams(
                    rssi = TEST_RSSI_POSITIVE_HALF,
                    desiredNumberOfBars = 3,
                    expectedResult = 4
                ),
                CompareSignalLevelParams(
                    rssi = TEST_RSSI_POSITIVE_HALF,
                    desiredNumberOfBars = 4,
                    expectedResult = 4
                ),
                CompareSignalLevelParams(
                    rssi = TEST_RSSI_POSITIVE_HALF,
                    desiredNumberOfBars = 5,
                    expectedResult = 4
                ),
                CompareSignalLevelParams(
                    rssi = TEST_RSSI_POSITIVE_FULL,
                    desiredNumberOfBars = -1,
                    expectedResult = 4
                ),
                CompareSignalLevelParams(
                    rssi = TEST_RSSI_POSITIVE_HALF,
                    desiredNumberOfBars = 0,
                    expectedResult = 4
                ),
                CompareSignalLevelParams(
                    rssi = TEST_RSSI_POSITIVE_FULL,
                    desiredNumberOfBars = 1,
                    expectedResult = 4
                ),
                CompareSignalLevelParams(
                    rssi = TEST_RSSI_POSITIVE_FULL,
                    desiredNumberOfBars = 2,
                    expectedResult = 4
                ),
                CompareSignalLevelParams(
                    rssi = TEST_RSSI_POSITIVE_FULL,
                    desiredNumberOfBars = 3,
                    expectedResult = 4
                ),
                CompareSignalLevelParams(
                    rssi = TEST_RSSI_POSITIVE_FULL,
                    desiredNumberOfBars = 4,
                    expectedResult = 4
                ),
                CompareSignalLevelParams(
                    rssi = TEST_RSSI_POSITIVE_FULL,
                    desiredNumberOfBars = 5,
                    expectedResult = 4
                )
            )
        }

        data class CompareSignalLevelParams(
            val rssi: Int,
            val desiredNumberOfBars: Int,
            val expectedResult: Int
        )
    }
}