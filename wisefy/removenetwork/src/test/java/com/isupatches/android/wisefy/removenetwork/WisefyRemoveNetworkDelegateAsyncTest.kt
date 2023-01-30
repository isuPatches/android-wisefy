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
package com.isupatches.android.wisefy.removenetwork

import android.net.wifi.WifiManager
import com.isupatches.android.wisefy.core.assertions.WisefyAssertions
import com.isupatches.android.wisefy.core.logging.DefaultWisefyLogger
import com.isupatches.android.wisefy.core.util.SdkUtilImpl
import com.isupatches.android.wisefy.removenetwork.callbacks.RemoveNetworkCallbacks
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkRequest
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkResult
import com.isupatches.android.wisefy.testsupport.TestCoroutineDispatchProvider
import com.isupatches.android.wisefy.testsupport.anyNonNull
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
import org.mockito.BDDMockito.never
import org.mockito.BDDMockito.times
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(Parameterized::class)
internal class WisefyRemoveNetworkDelegateAsyncTest(
    private val params: RemoveNetworkParams
) {

    @Mock
    private lateinit var mockWifiManager: WifiManager

    @Mock
    private lateinit var mockAdapter: RemoveNetworkApi

    private lateinit var delegate: WisefyRemoveNetworkDelegate

    private var closable: AutoCloseable? = null

    private lateinit var testScope: TestScope

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        closable = MockitoAnnotations.openMocks(this)
        testScope = TestScope()
        delegate = WisefyRemoveNetworkDelegate(
            wifiManager = mockWifiManager,
            logger = DefaultWisefyLogger(),
            assertions = WisefyAssertions(false),
            sdkUtil = SdkUtilImpl(),
            coroutineDispatcherProvider = TestCoroutineDispatchProvider(),
            scope = testScope,
            savedNetworkMutex = Mutex(),
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
        given(mockAdapter.removeNetwork(params.request)).willReturn(params.result)

        // Then
        delegate.removeNetwork(params.request, params.mockCallbacks)
        advanceUntilIdle()

        // When
        verify(mockAdapter, times(1)).removeNetwork(params.request)
        when {
            params.mockCallbacks != null && params.expectedFailureCallbacks > 0 -> {
                verify(params.mockCallbacks, times(params.expectedFailureCallbacks)).onFailureRemovingNetwork(
                    params.result as RemoveNetworkResult.Failure
                )
            }
            params.mockCallbacks != null -> {
                verify(params.mockCallbacks, never()).onFailureRemovingNetwork(anyNonNull())
            }
        }
        when {
            params.mockCallbacks != null && params.expectedSuccessCallbacks > 0 -> {
                verify(params.mockCallbacks, times(params.expectedSuccessCallbacks)).onSuccessRemovingNetwork(
                    params.result as RemoveNetworkResult.Success
                )
            }
            params.mockCallbacks != null -> {
                verify(params.mockCallbacks, never()).onSuccessRemovingNetwork(anyNonNull())
            }
        }
    }

    companion object {
        private const val TEST_SSID: String = "Test SSID"
        private const val TEST_BSSID: String = "Test:BSSID"

        private const val REMOVE_NETWORK_FAILURE_RESULT_CODE: Int = -1
        private const val REMOVE_NETWORK_SUCCESS_RESULT_CODE: Int = 1

        @JvmStatic
        @Parameterized.Parameters(name = "{index}: {0}")
        fun paramValues(): List<RemoveNetworkParams> {
            return listOf(
                RemoveNetworkParams(
                    request = RemoveNetworkRequest.SSID(ssid = TEST_SSID),
                    result = RemoveNetworkResult.Success.ResultCode(REMOVE_NETWORK_SUCCESS_RESULT_CODE),
                    mockCallbacks = mock(RemoveNetworkCallbacks::class.java),
                    expectedSuccessCallbacks = 1
                ),
                RemoveNetworkParams(
                    request = RemoveNetworkRequest.BSSID(bssid = TEST_BSSID),
                    result = RemoveNetworkResult.Success.ResultCode(REMOVE_NETWORK_SUCCESS_RESULT_CODE),
                    mockCallbacks = mock(RemoveNetworkCallbacks::class.java),
                    expectedSuccessCallbacks = 1
                ),
                RemoveNetworkParams(
                    request = RemoveNetworkRequest.SSID(ssid = TEST_SSID),
                    result = RemoveNetworkResult.Success.True,
                    mockCallbacks = mock(RemoveNetworkCallbacks::class.java),
                    expectedSuccessCallbacks = 1
                ),
                RemoveNetworkParams(
                    request = RemoveNetworkRequest.BSSID(bssid = TEST_BSSID),
                    result = RemoveNetworkResult.Success.True,
                    mockCallbacks = mock(RemoveNetworkCallbacks::class.java),
                    expectedSuccessCallbacks = 1
                ),
                RemoveNetworkParams(
                    request = RemoveNetworkRequest.SSID(ssid = TEST_SSID),
                    result = RemoveNetworkResult.Failure.ResultCode(REMOVE_NETWORK_FAILURE_RESULT_CODE),
                    mockCallbacks = mock(RemoveNetworkCallbacks::class.java),
                    expectedFailureCallbacks = 1
                ),
                RemoveNetworkParams(
                    request = RemoveNetworkRequest.BSSID(bssid = TEST_BSSID),
                    result = RemoveNetworkResult.Failure.ResultCode(REMOVE_NETWORK_FAILURE_RESULT_CODE),
                    mockCallbacks = mock(RemoveNetworkCallbacks::class.java),
                    expectedFailureCallbacks = 1
                ),
                RemoveNetworkParams(
                    request = RemoveNetworkRequest.SSID(ssid = TEST_SSID),
                    result = RemoveNetworkResult.Failure.False,
                    mockCallbacks = mock(RemoveNetworkCallbacks::class.java),
                    expectedFailureCallbacks = 1
                ),
                RemoveNetworkParams(
                    request = RemoveNetworkRequest.BSSID(bssid = TEST_BSSID),
                    result = RemoveNetworkResult.Failure.False,
                    mockCallbacks = mock(RemoveNetworkCallbacks::class.java),
                    expectedFailureCallbacks = 1
                ),
                RemoveNetworkParams(
                    request = RemoveNetworkRequest.SSID(ssid = TEST_SSID),
                    result = RemoveNetworkResult.Failure.Assertion(""),
                    mockCallbacks = mock(RemoveNetworkCallbacks::class.java),
                    expectedFailureCallbacks = 1
                ),
                RemoveNetworkParams(
                    request = RemoveNetworkRequest.BSSID(bssid = TEST_BSSID),
                    result = RemoveNetworkResult.Failure.Assertion(""),
                    mockCallbacks = mock(RemoveNetworkCallbacks::class.java),
                    expectedFailureCallbacks = 1
                ),
                RemoveNetworkParams(
                    request = RemoveNetworkRequest.SSID(ssid = TEST_SSID),
                    result = RemoveNetworkResult.Success.ResultCode(REMOVE_NETWORK_SUCCESS_RESULT_CODE),
                    mockCallbacks = null
                ),
                RemoveNetworkParams(
                    request = RemoveNetworkRequest.BSSID(bssid = TEST_BSSID),
                    result = RemoveNetworkResult.Success.ResultCode(REMOVE_NETWORK_SUCCESS_RESULT_CODE),
                    mockCallbacks = null
                ),
                RemoveNetworkParams(
                    request = RemoveNetworkRequest.SSID(ssid = TEST_SSID),
                    result = RemoveNetworkResult.Success.True,
                    mockCallbacks = null
                ),
                RemoveNetworkParams(
                    request = RemoveNetworkRequest.BSSID(bssid = TEST_BSSID),
                    result = RemoveNetworkResult.Success.True,
                    mockCallbacks = null
                ),
                RemoveNetworkParams(
                    request = RemoveNetworkRequest.SSID(ssid = TEST_SSID),
                    result = RemoveNetworkResult.Failure.ResultCode(REMOVE_NETWORK_FAILURE_RESULT_CODE),
                    mockCallbacks = null
                ),
                RemoveNetworkParams(
                    request = RemoveNetworkRequest.BSSID(bssid = TEST_BSSID),
                    result = RemoveNetworkResult.Failure.ResultCode(REMOVE_NETWORK_FAILURE_RESULT_CODE),
                    mockCallbacks = null
                ),
                RemoveNetworkParams(
                    request = RemoveNetworkRequest.SSID(ssid = TEST_SSID),
                    result = RemoveNetworkResult.Failure.False,
                    mockCallbacks = null
                ),
                RemoveNetworkParams(
                    request = RemoveNetworkRequest.BSSID(bssid = TEST_BSSID),
                    result = RemoveNetworkResult.Failure.False,
                    mockCallbacks = null
                ),
                RemoveNetworkParams(
                    request = RemoveNetworkRequest.SSID(ssid = TEST_SSID),
                    result = RemoveNetworkResult.Failure.Assertion(""),
                    mockCallbacks = null
                ),
                RemoveNetworkParams(
                    request = RemoveNetworkRequest.BSSID(bssid = TEST_BSSID),
                    result = RemoveNetworkResult.Failure.Assertion(""),
                    mockCallbacks = null
                )
            )
        }

        data class RemoveNetworkParams(
            val request: RemoveNetworkRequest,
            val result: RemoveNetworkResult,
            val mockCallbacks: RemoveNetworkCallbacks?,
            val expectedFailureCallbacks: Int = 0,
            val expectedSuccessCallbacks: Int = 0
        )
    }
}
