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
import com.isupatches.android.wisefy.testsupport.anyNonNull
import com.isupatches.android.wisefy.wifi.callbacks.EnableWifiCallbacks
import com.isupatches.android.wisefy.wifi.entities.EnableWifiRequest
import com.isupatches.android.wisefy.wifi.entities.EnableWifiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.never
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(Parameterized::class)
internal class WisefyWifiDelegateEnableWifiAsyncTest(
    private val params: EnableWifiParams
) {

    @Mock
    private lateinit var mockWifiManager: WifiManager

    @Mock
    private lateinit var mockAdapter: WifiApi

    private lateinit var delegate: WisefyWifiDelegate

    private var closable: AutoCloseable? = null

    private lateinit var testScope: TestScope

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        closable = MockitoAnnotations.openMocks(this)
        testScope = TestScope()
        delegate = WisefyWifiDelegate(
            wifiManager = mockWifiManager,
            logger = DefaultWisefyLogger(),
            assertions = WisefyAssertions(false),
            sdkUtil = SdkUtilImpl(),
            coroutineDispatcherProvider = TestCoroutineDispatchProvider(),
            scope = testScope,
            wifiMutex = Mutex(),
            adapter = mockAdapter
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        closable?.close()
    }

    @Test
    fun test() = runTest {
        // Given
        given(mockAdapter.enableWifi(params.request)).willReturn(params.result)

        // Then
        delegate.enableWifi(params.request, params.mockCallbacks)
        advanceUntilIdle()

        // When
        verify(mockAdapter, times(1)).enableWifi(params.request)
        when {
            params.mockCallbacks != null && params.expectedFailureCallbacks > 0 -> {
                verify(params.mockCallbacks, times(params.expectedFailureCallbacks)).onFailureEnablingWifi(
                    params.result as EnableWifiResult.Failure
                )
            }
            params.mockCallbacks != null -> {
                verify(params.mockCallbacks, never()).onFailureEnablingWifi(anyNonNull())
            }
        }
        when {
            params.mockCallbacks != null && params.expectedSuccessCallbacks > 0 -> {
                verify(params.mockCallbacks, times(params.expectedSuccessCallbacks)).onSuccessEnablingWifi(
                    params.result as EnableWifiResult.Success
                )
            }
            params.mockCallbacks != null -> {
                verify(params.mockCallbacks, never()).onSuccessEnablingWifi(anyNonNull())
            }
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: {0}")
        fun paramValues(): List<EnableWifiParams> {
            return listOf(
                EnableWifiParams(
                    request = EnableWifiRequest.Default,
                    result = EnableWifiResult.Success.Enabled,
                    mockCallbacks = mock(EnableWifiCallbacks::class.java),
                    expectedSuccessCallbacks = 1
                ),
                EnableWifiParams(
                    request = EnableWifiRequest.Default,
                    result = EnableWifiResult.Success.WifiSettingScreenOpened,
                    mockCallbacks = mock(EnableWifiCallbacks::class.java),
                    expectedSuccessCallbacks = 1
                ),
                EnableWifiParams(
                    request = EnableWifiRequest.Default,
                    result = EnableWifiResult.Success.Enabled,
                    mockCallbacks = null
                ),
                EnableWifiParams(
                    request = EnableWifiRequest.Default,
                    result = EnableWifiResult.Success.WifiSettingScreenOpened,
                    mockCallbacks = null
                ),
                EnableWifiParams(
                    request = EnableWifiRequest.Default,
                    result = EnableWifiResult.Failure.UnableToEnable,
                    mockCallbacks = mock(EnableWifiCallbacks::class.java),
                    expectedFailureCallbacks = 1
                ),
                EnableWifiParams(
                    request = EnableWifiRequest.Default,
                    result = EnableWifiResult.Failure.Assertion("Test"),
                    mockCallbacks = mock(EnableWifiCallbacks::class.java),
                    expectedFailureCallbacks = 1
                ),
                EnableWifiParams(
                    request = EnableWifiRequest.Default,
                    result = EnableWifiResult.Failure.UnableToEnable,
                    mockCallbacks = null
                ),
                EnableWifiParams(
                    request = EnableWifiRequest.Default,
                    result = EnableWifiResult.Failure.Assertion("Test"),
                    mockCallbacks = null
                ),
                EnableWifiParams(
                    request = EnableWifiRequest.Android29OrAbove(mock(Context::class.java)),
                    result = EnableWifiResult.Success.Enabled,
                    mockCallbacks = mock(EnableWifiCallbacks::class.java),
                    expectedSuccessCallbacks = 1
                ),
                EnableWifiParams(
                    request = EnableWifiRequest.Android29OrAbove(mock(Context::class.java)),
                    result = EnableWifiResult.Success.WifiSettingScreenOpened,
                    mockCallbacks = mock(EnableWifiCallbacks::class.java),
                    expectedSuccessCallbacks = 1
                ),
                EnableWifiParams(
                    request = EnableWifiRequest.Android29OrAbove(mock(Context::class.java)),
                    result = EnableWifiResult.Success.Enabled,
                    mockCallbacks = null
                ),
                EnableWifiParams(
                    request = EnableWifiRequest.Android29OrAbove(mock(Context::class.java)),
                    result = EnableWifiResult.Success.WifiSettingScreenOpened,
                    mockCallbacks = null
                ),
                EnableWifiParams(
                    request = EnableWifiRequest.Android29OrAbove(mock(Context::class.java)),
                    result = EnableWifiResult.Failure.UnableToEnable,
                    mockCallbacks = mock(EnableWifiCallbacks::class.java),
                    expectedFailureCallbacks = 1
                ),
                EnableWifiParams(
                    request = EnableWifiRequest.Android29OrAbove(mock(Context::class.java)),
                    result = EnableWifiResult.Failure.Assertion("Test"),
                    mockCallbacks = mock(EnableWifiCallbacks::class.java),
                    expectedFailureCallbacks = 1
                ),
                EnableWifiParams(
                    request = EnableWifiRequest.Android29OrAbove(mock(Context::class.java)),
                    result = EnableWifiResult.Failure.UnableToEnable,
                    mockCallbacks = null
                ),
                EnableWifiParams(
                    request = EnableWifiRequest.Android29OrAbove(mock(Context::class.java)),
                    result = EnableWifiResult.Failure.Assertion("Test"),
                    mockCallbacks = null
                )
            )
        }

        data class EnableWifiParams(
            val request: EnableWifiRequest,
            val result: EnableWifiResult,
            val mockCallbacks: EnableWifiCallbacks?,
            val expectedFailureCallbacks: Int = 0,
            val expectedSuccessCallbacks: Int = 0
        )
    }
}
