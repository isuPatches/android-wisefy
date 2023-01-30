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
package com.isupatches.android.wisefy.wifi.os.adapters

import android.net.wifi.WifiManager
import com.isupatches.android.wisefy.core.assertions.WisefyAssertions
import com.isupatches.android.wisefy.core.logging.DefaultWisefyLogger
import com.isupatches.android.wisefy.wifi.entities.IsWifiEnabledQuery
import com.isupatches.android.wisefy.wifi.entities.IsWifiEnabledResult
import com.isupatches.android.wisefy.wifi.os.impls.DefaultWifiApiImpl
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.times
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@RunWith(Parameterized::class)
internal class DefaultWifiAdapterIsWifiEnabledTest(
    private val params: IsWifiEnabledParams
) {

    @Mock
    private lateinit var mockWifiManager: WifiManager

    private lateinit var adapter: DefaultWifiAdapter

    private var closable: AutoCloseable? = null

    @Before
    fun setUp() {
        closable = MockitoAnnotations.openMocks(this)
        val logger = DefaultWisefyLogger()
        adapter = DefaultWifiAdapter(
            wifiManager = mockWifiManager,
            logger = logger,
            assertions = WisefyAssertions(throwOnAssertions = false),
            api = DefaultWifiApiImpl(wifiManager = mockWifiManager, logger = logger)
        )
    }

    @After
    fun tearDown() {
        closable?.close()
    }

    @Test
    fun test() {
        // Given
        @Suppress("Deprecation")
        given(mockWifiManager.isWifiEnabled).willReturn(params.isWifiEnabledResult)

        // Then
        val result = adapter.isWifiEnabled()

        // When
        assertEquals(params.expectedResult, result)
        verify(mockWifiManager, times(1)).isWifiEnabled
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: {0}")
        fun paramValues(): List<IsWifiEnabledParams> {
            return listOf(
                IsWifiEnabledParams(
                    query = IsWifiEnabledQuery(),
                    isWifiEnabledResult = true,
                    expectedResult = IsWifiEnabledResult.True
                ),
                IsWifiEnabledParams(
                    query = IsWifiEnabledQuery(),
                    isWifiEnabledResult = false,
                    expectedResult = IsWifiEnabledResult.False
                )
            )
        }

        data class IsWifiEnabledParams(
            val query: IsWifiEnabledQuery,
            val isWifiEnabledResult: Boolean? = null,
            val expectedResult: IsWifiEnabledResult
        )
    }
}
