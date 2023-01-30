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
import com.isupatches.android.wisefy.wifi.entities.DisableWifiRequest
import com.isupatches.android.wisefy.wifi.entities.DisableWifiResult
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
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@RunWith(Parameterized::class)
internal class DefaultWifiAdapterDisableWifiTest(
    private val params: DisableWifiParams
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
        params.disableWifiResult?.let {
            @Suppress("Deprecation")
            given(mockWifiManager.setWifiEnabled(false)).willReturn(it)
        }

        // Then
        val result = adapter.disableWifi(params.request)

        // When
        assertEquals(params.expectedResult, result)
        params.disableWifiResult?.let {
            @Suppress("Deprecation", "UsePropertyAccessSyntax")
            verify(mockWifiManager, times(1)).setWifiEnabled(false)
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: {0}")
        fun paramValues(): List<DisableWifiParams> {
            return listOf(
                DisableWifiParams(
                    request = DisableWifiRequest.Default,
                    disableWifiResult = true,
                    expectedResult = DisableWifiResult.Success.Disabled
                ),
                DisableWifiParams(
                    request = DisableWifiRequest.Default,
                    disableWifiResult = false,
                    expectedResult = DisableWifiResult.Failure.UnableToDisable
                ),
                DisableWifiParams(
                    request = DisableWifiRequest.Android29OrAbove(mock(Context::class.java)),
                    expectedResult = DisableWifiResult.Failure.Assertion(
                        AssertionMessages.Wifi.ANDROID_29_REQUEST_USED_ON_PRE_ANDROID_29
                    )
                )
            )
        }

        data class DisableWifiParams(
            val request: DisableWifiRequest,
            val disableWifiResult: Boolean? = null,
            val expectedResult: DisableWifiResult
        )
    }
}
