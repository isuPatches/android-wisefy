/*
 * Copyright 2025 Patches Barrett
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
import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkRequest
import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkResult
import com.isupatches.android.wisefy.core.logging.NoOpWisefyLogger
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.times
import org.mockito.BDDMockito.verify
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.mockito.quality.Strictness

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(Parameterized::class)
internal class WisefyAddNetworkDelegateAddNetworkSyncTest(
    private val params: AddNetworkParams,
) {

    @get:Rule
    var mockitoRule: MockitoRule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS)

    @Mock
    private lateinit var mockWifiManager: WifiManager

    @Mock
    private lateinit var mockAdapter: AddNetworkApi

    private lateinit var delegate: WisefyAddNetworkDelegate

    private val testDispatcher = UnconfinedTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @Before
    fun setUp() {
        delegate = WisefyAddNetworkDelegate(
            wifiManager = mockWifiManager,
            scope = testScope,
            mainDispatcher = testDispatcher,
            savedNetworkMutex = Mutex(),
            adapter = mockAdapter,
        )
    }

    @Test
    fun test() {
        // Given
        given(mockAdapter.addNetwork(params.request)).willReturn(params.result)

        // Then
        val result = delegate.addNetwork(params.request)

        // When
        assertEquals(params.result, result)
        verify(mockAdapter, times(1)).addNetwork(params.request)
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
                ),
                AddNetworkParams(
                    request = AddNetworkRequest.Open(ssid = TEST_SSID, bssid = TEST_BSSID),
                    result = AddNetworkResult.Success.ResultCode(ADD_NETWORK_SUCCESS_RESULT_CODE),
                ),
                AddNetworkParams(
                    request = AddNetworkRequest.Open(ssid = TEST_SSID, bssid = null),
                    result = AddNetworkResult.Failure.ResultCode(ADD_NETWORK_FAILURE_RESULT_CODE),
                ),
                AddNetworkParams(
                    request = AddNetworkRequest.Open(ssid = TEST_SSID, bssid = TEST_BSSID),
                    result = AddNetworkResult.Failure.ResultCode(ADD_NETWORK_FAILURE_RESULT_CODE),
                ),
                AddNetworkParams(
                    request = AddNetworkRequest.WPA2(ssid = TEST_SSID, passphrase = TEST_PASSPHRASE, bssid = null),
                    result = AddNetworkResult.Success.ResultCode(ADD_NETWORK_SUCCESS_RESULT_CODE),
                ),
                AddNetworkParams(
                    request = AddNetworkRequest.WPA2(
                        ssid = TEST_SSID,
                        passphrase = TEST_PASSPHRASE,
                        bssid = TEST_BSSID,
                    ),
                    result = AddNetworkResult.Success.ResultCode(ADD_NETWORK_SUCCESS_RESULT_CODE),
                ),
                AddNetworkParams(
                    request = AddNetworkRequest.WPA2(
                        ssid = TEST_SSID,
                        passphrase = TEST_PASSPHRASE,
                        bssid = null,
                    ),
                    result = AddNetworkResult.Failure.ResultCode(ADD_NETWORK_FAILURE_RESULT_CODE),
                ),
                AddNetworkParams(
                    request = AddNetworkRequest.WPA2(
                        ssid = TEST_SSID,
                        passphrase = TEST_PASSPHRASE,
                        bssid = TEST_BSSID,
                    ),
                    result = AddNetworkResult.Failure.ResultCode(ADD_NETWORK_FAILURE_RESULT_CODE),
                ),
                AddNetworkParams(
                    request = AddNetworkRequest.WPA3(ssid = TEST_SSID, passphrase = TEST_PASSPHRASE, bssid = null),
                    result = AddNetworkResult.Success.ResultCode(ADD_NETWORK_SUCCESS_RESULT_CODE),
                ),
                AddNetworkParams(
                    request = AddNetworkRequest.WPA3(
                        ssid = TEST_SSID,
                        passphrase = TEST_PASSPHRASE,
                        bssid = TEST_BSSID,
                    ),
                    result = AddNetworkResult.Success.ResultCode(ADD_NETWORK_SUCCESS_RESULT_CODE),
                ),
                AddNetworkParams(
                    request = AddNetworkRequest.WPA3(
                        ssid = TEST_SSID,
                        passphrase = TEST_PASSPHRASE,
                        bssid = null,
                    ),
                    result = AddNetworkResult.Failure.ResultCode(ADD_NETWORK_FAILURE_RESULT_CODE),
                ),
                AddNetworkParams(
                    request = AddNetworkRequest.WPA3(
                        ssid = TEST_SSID,
                        passphrase = TEST_PASSPHRASE,
                        bssid = TEST_BSSID,
                    ),
                    result = AddNetworkResult.Failure.ResultCode(ADD_NETWORK_FAILURE_RESULT_CODE),
                ),
            )
        }

        data class AddNetworkParams(
            val request: AddNetworkRequest,
            val result: AddNetworkResult,
        )
    }
}
