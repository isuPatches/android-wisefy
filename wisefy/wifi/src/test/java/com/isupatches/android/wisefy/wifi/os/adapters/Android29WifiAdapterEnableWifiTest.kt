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
import com.isupatches.android.wisefy.testsupport.anyNonNull
import com.isupatches.android.wisefy.wifi.entities.EnableWifiRequest
import com.isupatches.android.wisefy.wifi.entities.EnableWifiResult
import com.isupatches.android.wisefy.wifi.os.apis.Android29WifiApi
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@RunWith(Parameterized::class)
internal class Android29WifiAdapterEnableWifiTest(
    private val params: EnableWifiParams
) {

    @Mock
    private lateinit var mockWifiManager: WifiManager

    @Mock
    private lateinit var mockApi: Android29WifiApi

    private lateinit var adapter: Android29WifiAdapter

    private var closable: AutoCloseable? = null

    @Before
    fun setUp() {
        closable = MockitoAnnotations.openMocks(this)
        adapter = Android29WifiAdapter(
            wifiManager = mockWifiManager,
            logger = DefaultWisefyLogger(),
            assertions = WisefyAssertions(throwOnAssertions = false),
            api = mockApi
        )
    }

    @After
    fun tearDown() {
        closable?.close()
    }

    @Test
    fun test() {
        // When
        val result = adapter.enableWifi(params.request)

        // Expect
        assertEquals(params.expectedResult, result)
        if (params.expectedNumberOfApiCalls > 0) {
            verify(mockApi, times(params.expectedNumberOfApiCalls)).openWifiSettings(anyNonNull())
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: {0}")
        fun paramValues(): List<EnableWifiParams> {
            return listOf(
                EnableWifiParams(
                    request = EnableWifiRequest.Android29OrAbove(mock(Context::class.java)),
                    expectedResult = EnableWifiResult.Success.WifiSettingScreenOpened,
                    expectedNumberOfApiCalls = 1
                ),
                EnableWifiParams(
                    request = EnableWifiRequest.Default,
                    expectedResult = EnableWifiResult.Failure.Assertion(
                        AssertionMessages.Wifi.DEFAULT_REQUEST_USED_ANDROID_29_OR_HIGHER
                    )
                )
            )
        }

        data class EnableWifiParams(
            val request: EnableWifiRequest,
            val expectedResult: EnableWifiResult,
            val expectedNumberOfApiCalls: Int = 0
        )
    }
}
