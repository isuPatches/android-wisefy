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
package com.isupatches.android.wisefy.signal

import android.net.wifi.WifiManager
import com.isupatches.android.wisefy.core.assertions.WisefyAssertions
import com.isupatches.android.wisefy.core.logging.DefaultWisefyLogger
import com.isupatches.android.wisefy.core.util.SdkUtilImpl
import com.isupatches.android.wisefy.signal.entities.CompareSignalLevelRequest
import com.isupatches.android.wisefy.signal.entities.CompareSignalLevelResult
import com.isupatches.android.wisefy.testsupport.anyNonNull
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@RunWith(Parameterized::class)
internal class WisefySignalDelegateCompareSignalLevelTest(
    private val params: CompareSignalLevelParams
) {

    @Mock
    private lateinit var mockWifiManager: WifiManager

    @Mock
    private lateinit var mockAdapter: SignalApi

    private lateinit var delegate: WisefySignalDelegate

    private var closable: AutoCloseable? = null

    @Before
    fun setUp() {
        closable = MockitoAnnotations.openMocks(this)
        delegate = WisefySignalDelegate(
            wifiManager = mockWifiManager,
            logger = DefaultWisefyLogger(),
            assertions = WisefyAssertions(false),
            sdkUtil = SdkUtilImpl(),
            adapter = mockAdapter
        )
    }

    @After
    fun tearDown() {
        closable?.close()
    }

    @Test
    fun test() {
        // Given
        given(mockAdapter.compareSignalLevel(params.request)).willReturn(params.result)

        // Then
        val result = delegate.compareSignalLevel(params.request)

        // When
        verify(mockAdapter, times(1)).compareSignalLevel(anyNonNull())
        assertEquals(params.result, result)
    }

    companion object {
        private const val TEST_RSSI1 = -65
        private const val TEST_RSSI2 = -65
        private const val TEST_VALUE = 0

        @JvmStatic
        @Parameterized.Parameters(name = "{index}: {0}")
        fun paramValues(): List<CompareSignalLevelParams> {
            return listOf(
                CompareSignalLevelParams(
                    request = CompareSignalLevelRequest(rssi1 = TEST_RSSI1, rssi2 = TEST_RSSI2),
                    result = CompareSignalLevelResult.Success.RSSIValuesAreEqual
                ),
                CompareSignalLevelParams(
                    request = CompareSignalLevelRequest(rssi1 = TEST_RSSI1, rssi2 = TEST_RSSI2),
                    result = CompareSignalLevelResult.Success.FirstRSSIValueIsWeaker(value = TEST_VALUE)

                ),
                CompareSignalLevelParams(
                    request = CompareSignalLevelRequest(rssi1 = TEST_RSSI1, rssi2 = TEST_RSSI2),
                    result = CompareSignalLevelResult.Success.FirstRSSIValueIsStronger(value = TEST_VALUE)
                )
            )
        }

        data class CompareSignalLevelParams(
            val request: CompareSignalLevelRequest,
            val result: CompareSignalLevelResult
        )
    }
}
