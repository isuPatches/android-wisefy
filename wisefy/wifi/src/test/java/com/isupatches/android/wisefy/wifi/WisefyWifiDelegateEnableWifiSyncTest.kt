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
package com.isupatches.android.wisefy.wifi

import android.content.Context
import android.net.wifi.WifiManager
import com.isupatches.android.wisefy.core.assertions.WisefyAssertions
import com.isupatches.android.wisefy.core.logging.DefaultWisefyLogger
import com.isupatches.android.wisefy.core.util.SdkUtilImpl
import com.isupatches.android.wisefy.testsupport.TestCoroutineDispatchProvider
import com.isupatches.android.wisefy.wifi.entities.EnableWifiRequest
import com.isupatches.android.wisefy.wifi.entities.EnableWifiResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.test.TestScope
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(Parameterized::class)
internal class WisefyWifiDelegateEnableWifiSyncTest(
    private val params: EnableWifiParams
) {

    @Mock
    private lateinit var mockWifiManager: WifiManager

    @Mock
    private lateinit var mockAdapter: WifiApi

    private lateinit var delegate: WisefyWifiDelegate

    private var closable: AutoCloseable? = null

    @Before
    fun setUp() {
        closable = MockitoAnnotations.openMocks(this)
        delegate = WisefyWifiDelegate(
            wifiManager = mockWifiManager,
            logger = DefaultWisefyLogger(),
            assertions = WisefyAssertions(false),
            sdkUtil = SdkUtilImpl(),
            coroutineDispatcherProvider = TestCoroutineDispatchProvider(),
            scope = TestScope(),
            wifiMutex = Mutex(),
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
        given(mockAdapter.enableWifi(params.request)).willReturn(params.result)

        // Then
        val result = delegate.enableWifi(params.request)

        // When
        verify(mockAdapter, times(1)).enableWifi(params.request)
        assertEquals(params.result, result)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: {0}")
        fun paramValues(): List<EnableWifiParams> {
            return listOf(
                EnableWifiParams(
                    request = EnableWifiRequest.Default,
                    result = EnableWifiResult.Success.Enabled
                ),
                EnableWifiParams(
                    request = EnableWifiRequest.Default,
                    result = EnableWifiResult.Success.WifiSettingScreenOpened
                ),
                EnableWifiParams(
                    request = EnableWifiRequest.Default,
                    result = EnableWifiResult.Failure.UnableToEnable
                ),
                EnableWifiParams(
                    request = EnableWifiRequest.Default,
                    result = EnableWifiResult.Failure.Assertion("Test")
                ),
                EnableWifiParams(
                    request = EnableWifiRequest.Android29OrAbove(Mockito.mock(Context::class.java)),
                    result = EnableWifiResult.Success.Enabled
                ),
                EnableWifiParams(
                    request = EnableWifiRequest.Android29OrAbove(Mockito.mock(Context::class.java)),
                    result = EnableWifiResult.Success.WifiSettingScreenOpened
                ),
                EnableWifiParams(
                    request = EnableWifiRequest.Android29OrAbove(Mockito.mock(Context::class.java)),
                    result = EnableWifiResult.Failure.UnableToEnable
                ),
                EnableWifiParams(
                    request = EnableWifiRequest.Android29OrAbove(Mockito.mock(Context::class.java)),
                    result = EnableWifiResult.Failure.Assertion("Test")
                )
            )
        }

        data class EnableWifiParams(
            val request: EnableWifiRequest,
            val result: EnableWifiResult
        )
    }
}
