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
package com.isupatches.android.wisefy.addnetwork

import android.net.wifi.WifiManager
import com.isupatches.android.wisefy.addnetwork.callbacks.AddNetworkCallbacks
import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkRequest
import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkResult
import com.isupatches.android.wisefy.core.assertions.WisefyAssertions
import com.isupatches.android.wisefy.core.logging.DefaultWisefyLogger
import com.isupatches.android.wisefy.core.util.SdkUtilImpl
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
internal class WisefyAddNetworkDelegateAddNetworkAsyncTest(
    private val params: AddNetworkParams
) {

    @Mock
    private lateinit var mockWifiManager: WifiManager

    @Mock
    private lateinit var mockAdapter: AddNetworkApi

    private lateinit var delegate: WisefyAddNetworkDelegate

    private var closable: AutoCloseable? = null

    private lateinit var testScope: TestScope

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        closable = MockitoAnnotations.openMocks(this)
        testScope = TestScope()
        delegate = WisefyAddNetworkDelegate(
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
        given(mockAdapter.addNetwork(params.request)).willReturn(params.result)

        // Then
        delegate.addNetwork(params.request, params.mockCallbacks)
        advanceUntilIdle()

        // When
        verify(mockAdapter, times(1)).addNetwork(params.request)
        when {
            params.mockCallbacks != null && params.expectedFailureCallbacks > 0 -> {
                verify(params.mockCallbacks, times(params.expectedFailureCallbacks)).onFailureAddingNetwork(
                    params.result as AddNetworkResult.Failure
                )
            }
            params.mockCallbacks != null -> {
                verify(params.mockCallbacks, never()).onFailureAddingNetwork(anyNonNull())
            }
        }
        when {
            params.mockCallbacks != null && params.expectedSuccessCallbacks > 0 -> {
                verify(params.mockCallbacks, times(params.expectedSuccessCallbacks)).onSuccessAddingNetwork(
                    params.result as AddNetworkResult.Success
                )
            }
            params.mockCallbacks != null -> {
                verify(params.mockCallbacks, never()).onSuccessAddingNetwork(anyNonNull())
            }
        }
    }

    companion object {
        private const val TEST_SSID: String = "Test SSID"
        private const val TEST_BSSID: String = "Test:BSSID"
        private const val TEST_PASSPHRASE: String = "immaPassPHRASE"

        private const val ADD_NETWORK_FAILURE_RESULT_CODE: Int = -1
        private const val ADD_NETWORK_SUCCESS_RESULT_CODE: Int = 1

        @JvmStatic
        @Parameterized.Parameters(name = "{index}: {0}")
        fun paramValues(): List<AddNetworkParams> {
            return listOf(
                AddNetworkParams(
                    request = AddNetworkRequest.Open(ssid = TEST_SSID, bssid = null),
                    result = AddNetworkResult.Success.ResultCode(ADD_NETWORK_SUCCESS_RESULT_CODE),
                    mockCallbacks = mock(AddNetworkCallbacks::class.java),
                    expectedSuccessCallbacks = 1
                ),
                AddNetworkParams(
                    request = AddNetworkRequest.Open(ssid = TEST_SSID, bssid = TEST_BSSID),
                    result = AddNetworkResult.Success.ResultCode(ADD_NETWORK_SUCCESS_RESULT_CODE),
                    mockCallbacks = mock(AddNetworkCallbacks::class.java),
                    expectedSuccessCallbacks = 1
                ),
                AddNetworkParams(
                    request = AddNetworkRequest.Open(ssid = TEST_SSID, bssid = TEST_BSSID),
                    result = AddNetworkResult.Success.ResultCode(ADD_NETWORK_SUCCESS_RESULT_CODE),
                    mockCallbacks = null
                ),
                AddNetworkParams(
                    request = AddNetworkRequest.Open(ssid = TEST_SSID, bssid = null),
                    result = AddNetworkResult.Failure.ResultCode(ADD_NETWORK_FAILURE_RESULT_CODE),
                    mockCallbacks = mock(AddNetworkCallbacks::class.java),
                    expectedFailureCallbacks = 1
                ),
                AddNetworkParams(
                    request = AddNetworkRequest.Open(ssid = TEST_SSID, bssid = TEST_BSSID),
                    result = AddNetworkResult.Failure.ResultCode(ADD_NETWORK_FAILURE_RESULT_CODE),
                    mockCallbacks = mock(AddNetworkCallbacks::class.java),
                    expectedFailureCallbacks = 1
                ),
                AddNetworkParams(
                    request = AddNetworkRequest.Open(ssid = TEST_SSID, bssid = TEST_BSSID),
                    result = AddNetworkResult.Failure.ResultCode(ADD_NETWORK_FAILURE_RESULT_CODE),
                    mockCallbacks = null
                ),
                AddNetworkParams(
                    request = AddNetworkRequest.WPA2(ssid = TEST_SSID, passphrase = TEST_PASSPHRASE, bssid = null),
                    result = AddNetworkResult.Success.ResultCode(ADD_NETWORK_SUCCESS_RESULT_CODE),
                    mockCallbacks = mock(AddNetworkCallbacks::class.java),
                    expectedSuccessCallbacks = 1
                ),
                AddNetworkParams(
                    request = AddNetworkRequest.WPA2(
                        ssid = TEST_SSID,
                        passphrase = TEST_PASSPHRASE,
                        bssid = TEST_BSSID
                    ),
                    result = AddNetworkResult.Success.ResultCode(ADD_NETWORK_SUCCESS_RESULT_CODE),
                    mockCallbacks = mock(AddNetworkCallbacks::class.java),
                    expectedSuccessCallbacks = 1
                ),
                AddNetworkParams(
                    request = AddNetworkRequest.WPA2(
                        ssid = TEST_SSID,
                        passphrase = TEST_PASSPHRASE,
                        bssid = TEST_BSSID
                    ),
                    result = AddNetworkResult.Success.ResultCode(ADD_NETWORK_SUCCESS_RESULT_CODE),
                    mockCallbacks = null
                ),
                AddNetworkParams(
                    request = AddNetworkRequest.WPA2(
                        ssid = TEST_SSID,
                        passphrase = TEST_PASSPHRASE,
                        bssid = null
                    ),
                    result = AddNetworkResult.Failure.ResultCode(ADD_NETWORK_FAILURE_RESULT_CODE),
                    mockCallbacks = mock(AddNetworkCallbacks::class.java),
                    expectedFailureCallbacks = 1
                ),
                AddNetworkParams(
                    request = AddNetworkRequest.WPA2(
                        ssid = TEST_SSID,
                        passphrase = TEST_PASSPHRASE,
                        bssid = TEST_BSSID
                    ),
                    result = AddNetworkResult.Failure.ResultCode(ADD_NETWORK_FAILURE_RESULT_CODE),
                    mockCallbacks = mock(AddNetworkCallbacks::class.java),
                    expectedFailureCallbacks = 1
                ),
                AddNetworkParams(
                    request = AddNetworkRequest.WPA2(
                        ssid = TEST_SSID,
                        passphrase = TEST_PASSPHRASE,
                        bssid = TEST_BSSID
                    ),
                    result = AddNetworkResult.Failure.ResultCode(ADD_NETWORK_FAILURE_RESULT_CODE),
                    mockCallbacks = null
                ),
                AddNetworkParams(
                    request = AddNetworkRequest.WPA3(ssid = TEST_SSID, passphrase = TEST_PASSPHRASE, bssid = null),
                    result = AddNetworkResult.Success.ResultCode(ADD_NETWORK_SUCCESS_RESULT_CODE),
                    mockCallbacks = mock(AddNetworkCallbacks::class.java),
                    expectedSuccessCallbacks = 1
                ),
                AddNetworkParams(
                    request = AddNetworkRequest.WPA3(
                        ssid = TEST_SSID,
                        passphrase = TEST_PASSPHRASE,
                        bssid = TEST_BSSID
                    ),
                    result = AddNetworkResult.Success.ResultCode(ADD_NETWORK_SUCCESS_RESULT_CODE),
                    mockCallbacks = mock(AddNetworkCallbacks::class.java),
                    expectedSuccessCallbacks = 1
                ),
                AddNetworkParams(
                    request = AddNetworkRequest.WPA3(
                        ssid = TEST_SSID,
                        passphrase = TEST_PASSPHRASE,
                        bssid = TEST_BSSID
                    ),
                    result = AddNetworkResult.Success.ResultCode(ADD_NETWORK_SUCCESS_RESULT_CODE),
                    mockCallbacks = null
                ),
                AddNetworkParams(
                    request = AddNetworkRequest.WPA3(
                        ssid = TEST_SSID,
                        passphrase = TEST_PASSPHRASE,
                        bssid = null
                    ),
                    result = AddNetworkResult.Failure.ResultCode(ADD_NETWORK_FAILURE_RESULT_CODE),
                    mockCallbacks = mock(AddNetworkCallbacks::class.java),
                    expectedFailureCallbacks = 1
                ),
                AddNetworkParams(
                    request = AddNetworkRequest.WPA3(
                        ssid = TEST_SSID,
                        passphrase = TEST_PASSPHRASE,
                        bssid = TEST_BSSID
                    ),
                    result = AddNetworkResult.Failure.ResultCode(ADD_NETWORK_FAILURE_RESULT_CODE),
                    mockCallbacks = mock(AddNetworkCallbacks::class.java),
                    expectedFailureCallbacks = 1
                ),
                AddNetworkParams(
                    request = AddNetworkRequest.WPA3(
                        ssid = TEST_SSID,
                        passphrase = TEST_PASSPHRASE,
                        bssid = TEST_BSSID
                    ),
                    result = AddNetworkResult.Failure.ResultCode(ADD_NETWORK_FAILURE_RESULT_CODE),
                    mockCallbacks = null
                )
            )
        }

        data class AddNetworkParams(
            val request: AddNetworkRequest,
            val result: AddNetworkResult,
            val mockCallbacks: AddNetworkCallbacks?,
            val expectedFailureCallbacks: Int = 0,
            val expectedSuccessCallbacks: Int = 0
        )
    }
}
