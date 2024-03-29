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
import com.isupatches.android.wisefy.wifi.callbacks.DisableWifiCallbacks
import com.isupatches.android.wisefy.wifi.entities.DisableWifiRequest
import com.isupatches.android.wisefy.wifi.entities.DisableWifiResult
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
internal class WisefyWifiDelegateDisableWifiAsyncTest(
    private val params: DisableWifiParams
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
        given(mockAdapter.disableWifi(params.request)).willReturn(params.result)

        // Then
        delegate.disableWifi(params.request, params.mockCallbacks)
        advanceUntilIdle()

        // When
        verify(mockAdapter, times(1)).disableWifi(params.request)
        when {
            params.mockCallbacks != null && params.expectedFailureCallbacks > 0 -> {
                verify(params.mockCallbacks, times(params.expectedFailureCallbacks)).onFailureDisablingWifi(
                    params.result as DisableWifiResult.Failure
                )
            }
            params.mockCallbacks != null -> {
                verify(params.mockCallbacks, never()).onFailureDisablingWifi(anyNonNull())
            }
        }
        when {
            params.mockCallbacks != null && params.expectedSuccessCallbacks > 0 -> {
                verify(params.mockCallbacks, times(params.expectedSuccessCallbacks)).onSuccessDisablingWifi(
                    params.result as DisableWifiResult.Success
                )
            }
            params.mockCallbacks != null -> {
                verify(params.mockCallbacks, never()).onSuccessDisablingWifi(anyNonNull())
            }
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: {0}")
        fun paramValues(): List<DisableWifiParams> {
            return listOf(
                DisableWifiParams(
                    request = DisableWifiRequest.Default,
                    result = DisableWifiResult.Success.Disabled,
                    mockCallbacks = mock(DisableWifiCallbacks::class.java),
                    expectedSuccessCallbacks = 1
                ),
                DisableWifiParams(
                    request = DisableWifiRequest.Default,
                    result = DisableWifiResult.Success.WifiSettingScreenOpened,
                    mockCallbacks = mock(DisableWifiCallbacks::class.java),
                    expectedSuccessCallbacks = 1
                ),
                DisableWifiParams(
                    request = DisableWifiRequest.Default,
                    result = DisableWifiResult.Success.Disabled,
                    mockCallbacks = null
                ),
                DisableWifiParams(
                    request = DisableWifiRequest.Default,
                    result = DisableWifiResult.Success.WifiSettingScreenOpened,
                    mockCallbacks = null
                ),
                DisableWifiParams(
                    request = DisableWifiRequest.Default,
                    result = DisableWifiResult.Failure.UnableToDisable,
                    mockCallbacks = mock(DisableWifiCallbacks::class.java),
                    expectedFailureCallbacks = 1
                ),
                DisableWifiParams(
                    request = DisableWifiRequest.Default,
                    result = DisableWifiResult.Failure.Assertion("Test"),
                    mockCallbacks = mock(DisableWifiCallbacks::class.java),
                    expectedFailureCallbacks = 1
                ),
                DisableWifiParams(
                    request = DisableWifiRequest.Default,
                    result = DisableWifiResult.Failure.UnableToDisable,
                    mockCallbacks = null
                ),
                DisableWifiParams(
                    request = DisableWifiRequest.Default,
                    result = DisableWifiResult.Failure.Assertion("Test"),
                    mockCallbacks = null
                ),
                DisableWifiParams(
                    request = DisableWifiRequest.Android29OrAbove(mock(Context::class.java)),
                    result = DisableWifiResult.Success.Disabled,
                    mockCallbacks = mock(DisableWifiCallbacks::class.java),
                    expectedSuccessCallbacks = 1
                ),
                DisableWifiParams(
                    request = DisableWifiRequest.Android29OrAbove(mock(Context::class.java)),
                    result = DisableWifiResult.Success.WifiSettingScreenOpened,
                    mockCallbacks = mock(DisableWifiCallbacks::class.java),
                    expectedSuccessCallbacks = 1
                ),
                DisableWifiParams(
                    request = DisableWifiRequest.Android29OrAbove(mock(Context::class.java)),
                    result = DisableWifiResult.Success.Disabled,
                    mockCallbacks = null
                ),
                DisableWifiParams(
                    request = DisableWifiRequest.Android29OrAbove(mock(Context::class.java)),
                    result = DisableWifiResult.Success.WifiSettingScreenOpened,
                    mockCallbacks = null
                ),
                DisableWifiParams(
                    request = DisableWifiRequest.Android29OrAbove(mock(Context::class.java)),
                    result = DisableWifiResult.Failure.UnableToDisable,
                    mockCallbacks = mock(DisableWifiCallbacks::class.java),
                    expectedFailureCallbacks = 1
                ),
                DisableWifiParams(
                    request = DisableWifiRequest.Android29OrAbove(mock(Context::class.java)),
                    result = DisableWifiResult.Failure.Assertion("Test"),
                    mockCallbacks = mock(DisableWifiCallbacks::class.java),
                    expectedFailureCallbacks = 1
                ),
                DisableWifiParams(
                    request = DisableWifiRequest.Android29OrAbove(mock(Context::class.java)),
                    result = DisableWifiResult.Failure.UnableToDisable,
                    mockCallbacks = null
                ),
                DisableWifiParams(
                    request = DisableWifiRequest.Android29OrAbove(mock(Context::class.java)),
                    result = DisableWifiResult.Failure.Assertion("Test"),
                    mockCallbacks = null
                )
            )
        }

        data class DisableWifiParams(
            val request: DisableWifiRequest,
            val result: DisableWifiResult,
            val mockCallbacks: DisableWifiCallbacks?,
            val expectedFailureCallbacks: Int = 0,
            val expectedSuccessCallbacks: Int = 0
        )
    }
}
