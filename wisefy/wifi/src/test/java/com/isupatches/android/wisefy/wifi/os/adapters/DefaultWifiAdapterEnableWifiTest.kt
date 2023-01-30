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

import android.content.Context
import android.net.wifi.WifiManager
import com.isupatches.android.wisefy.core.assertions.WisefyAssertions
import com.isupatches.android.wisefy.core.constants.AssertionMessages
import com.isupatches.android.wisefy.core.logging.DefaultWisefyLogger
import com.isupatches.android.wisefy.wifi.entities.EnableWifiRequest
import com.isupatches.android.wisefy.wifi.entities.EnableWifiResult
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
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@RunWith(Parameterized::class)
internal class DefaultWifiAdapterEnableWifiTest(
    private val params: EnableWifiParams
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
        params.enableWifiResult?.let {
            @Suppress("Deprecation")
            given(mockWifiManager.setWifiEnabled(true)).willReturn(it)
        }

        // Then
        val result = adapter.enableWifi(params.request)

        // When
        assertEquals(params.expectedResult, result)
        params.enableWifiResult?.let {
            @Suppress("Deprecation", "UsePropertyAccessSyntax")
            verify(mockWifiManager, times(1)).setWifiEnabled(true)
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: {0}")
        fun paramValues(): List<EnableWifiParams> {
            return listOf(
                EnableWifiParams(
                    request = EnableWifiRequest.Default,
                    enableWifiResult = true,
                    expectedResult = EnableWifiResult.Success.Enabled
                ),
                EnableWifiParams(
                    request = EnableWifiRequest.Default,
                    enableWifiResult = false,
                    expectedResult = EnableWifiResult.Failure.UnableToEnable
                ),
                EnableWifiParams(
                    request = EnableWifiRequest.Android29OrAbove(Mockito.mock(Context::class.java)),
                    expectedResult = EnableWifiResult.Failure.Assertion(
                        AssertionMessages.Wifi.ANDROID_29_REQUEST_USED_ON_PRE_ANDROID_29
                    )
                )
            )
        }

        data class EnableWifiParams(
            val request: EnableWifiRequest,
            val enableWifiResult: Boolean? = null,
            val expectedResult: EnableWifiResult
        )
    }
}
