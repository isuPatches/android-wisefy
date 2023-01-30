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

import android.net.wifi.WifiManager
import com.isupatches.android.wisefy.core.assertions.WisefyAssertions
import com.isupatches.android.wisefy.core.logging.DefaultWisefyLogger
import com.isupatches.android.wisefy.core.util.SdkUtilImpl
import com.isupatches.android.wisefy.testsupport.TestCoroutineDispatchProvider
import com.isupatches.android.wisefy.wifi.callbacks.IsWifiEnabledCallbacks
import com.isupatches.android.wisefy.wifi.entities.IsWifiEnabledQuery
import com.isupatches.android.wisefy.wifi.entities.IsWifiEnabledResult
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
internal class WisefyWifiDelegateIsWifiEnabledAsyncTest(
    private val params: IsWifiEnabledParams
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
        given(mockAdapter.isWifiEnabled(params.query)).willReturn(params.result)

        // Then
        delegate.isWifiEnabled(params.query, params.mockCallbacks)
        advanceUntilIdle()

        // When
        verify(mockAdapter, times(1)).isWifiEnabled(params.query)
        when {
            params.mockCallbacks != null && params.expectedWifiDisabledCallbacks > 0 -> {
                verify(params.mockCallbacks, times(params.expectedWifiDisabledCallbacks)).onWifiIsDisabled()
            }
            params.mockCallbacks != null -> {
                verify(params.mockCallbacks, never()).onWifiIsDisabled()
            }
        }
        when {
            params.mockCallbacks != null && params.expectedWifiEnabledCallbacks > 0 -> {
                verify(params.mockCallbacks, times(params.expectedWifiEnabledCallbacks)).onWifiIsEnabled()
            }
            params.mockCallbacks != null -> {
                verify(params.mockCallbacks, never()).onWifiIsEnabled()
            }
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: {0}")
        fun paramValues(): List<IsWifiEnabledParams> {
            return listOf(
                IsWifiEnabledParams(
                    query = IsWifiEnabledQuery(),
                    result = IsWifiEnabledResult.True,
                    mockCallbacks = mock(IsWifiEnabledCallbacks::class.java),
                    expectedWifiEnabledCallbacks = 1
                ),
                IsWifiEnabledParams(
                    query = IsWifiEnabledQuery(),
                    result = IsWifiEnabledResult.False,
                    mockCallbacks = mock(IsWifiEnabledCallbacks::class.java),
                    expectedWifiDisabledCallbacks = 1
                ),
                IsWifiEnabledParams(
                    query = IsWifiEnabledQuery(),
                    result = IsWifiEnabledResult.True,
                    mockCallbacks = null
                ),
                IsWifiEnabledParams(
                    query = IsWifiEnabledQuery(),
                    result = IsWifiEnabledResult.False,
                    mockCallbacks = null
                )
            )
        }

        data class IsWifiEnabledParams(
            val query: IsWifiEnabledQuery,
            val result: IsWifiEnabledResult,
            val mockCallbacks: IsWifiEnabledCallbacks?,
            val expectedWifiEnabledCallbacks: Int = 0,
            val expectedWifiDisabledCallbacks: Int = 0
        )
    }
}
