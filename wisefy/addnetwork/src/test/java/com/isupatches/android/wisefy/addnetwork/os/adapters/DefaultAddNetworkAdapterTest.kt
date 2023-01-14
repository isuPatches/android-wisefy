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
package com.isupatches.android.wisefy.addnetwork.os.adapters

import android.net.wifi.WifiManager
import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkRequest
import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkResult
import com.isupatches.android.wisefy.addnetwork.os.apis.DefaultAddNetworkApi
import com.isupatches.android.wisefy.core.assertions.WisefyAssertions
import com.isupatches.android.wisefy.core.constants.AssertionMessages
import com.isupatches.android.wisefy.core.logging.DefaultWisefyLogger
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters
import org.mockito.BDDMockito.anyString
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.isNull
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(Parameterized::class)
internal class DefaultAddNetworkAdapterTest(
    private val params: AddNetworkParams
) {

    @Mock
    private lateinit var mockWifiManager: WifiManager

    @Mock
    private lateinit var mockDefaultAddNetworkApi: DefaultAddNetworkApi

    private lateinit var adapter: DefaultAddNetworkAdapter

    private var closable: AutoCloseable? = null

    @Before
    fun setUp() {
        closable = MockitoAnnotations.openMocks(this)
        adapter = DefaultAddNetworkAdapter(
            wifiManager = mockWifiManager,
            logger = DefaultWisefyLogger(),
            assertions = WisefyAssertions(false),
            api = mockDefaultAddNetworkApi
        )
    }

    @After
    fun tearDown() {
        closable?.close()
    }

    @Test
    fun addNetwork() {
        // Given
        params.addNetworkResultCode?.let { addNetworkResultCode ->
            given(
                mockDefaultAddNetworkApi.addOpenNetwork(
                    anyString(),
                    isNull()
                )
            ).willReturn(addNetworkResultCode)
            given(
                mockDefaultAddNetworkApi.addOpenNetwork(
                    anyString(),
                    anyString()
                )
            ).willReturn(addNetworkResultCode)
            given(
                mockDefaultAddNetworkApi.addWPA2Network(
                    anyString(),
                    anyString(),
                    isNull()
                )
            ).willReturn(addNetworkResultCode)
            given(
                mockDefaultAddNetworkApi.addWPA2Network(
                    anyString(),
                    anyString(),
                    anyString()
                )
            ).willReturn(addNetworkResultCode)
        }

        // Then
        val result = adapter.addNetwork(params.request)

        // When
        assertEquals(params.expectedResult, result)
    }

    companion object {
        private const val TEST_SSID: String = "Test SSID"
        private const val TEST_BSSID: String = "Test:BSSID"
        private const val TEST_PASSPHRASE: String = "immaPassPHRASE"

        private const val ADD_NETWORK_FAILURE_RESULT_CODE: Int = -1
        private const val ADD_NETWORK_SUCCESS_RESULT_CODE: Int = 1

        @JvmStatic
        @Parameters(name = "{index}: {0}")
        fun params(): List<AddNetworkParams> {
            return listOf(
                AddNetworkParams(
                    request = AddNetworkRequest.Open(ssid = TEST_SSID, bssid = null),
                    addNetworkResultCode = ADD_NETWORK_SUCCESS_RESULT_CODE,
                    expectedResult = AddNetworkResult.Success.ResultCode(ADD_NETWORK_SUCCESS_RESULT_CODE)
                ),
                AddNetworkParams(
                    request = AddNetworkRequest.Open(ssid = TEST_SSID, bssid = TEST_BSSID),
                    addNetworkResultCode = ADD_NETWORK_SUCCESS_RESULT_CODE,
                    expectedResult = AddNetworkResult.Success.ResultCode(ADD_NETWORK_SUCCESS_RESULT_CODE)
                ),
                AddNetworkParams(
                    request = AddNetworkRequest.Open(ssid = TEST_SSID, bssid = null),
                    addNetworkResultCode = ADD_NETWORK_FAILURE_RESULT_CODE,
                    expectedResult = AddNetworkResult.Failure.ResultCode(ADD_NETWORK_FAILURE_RESULT_CODE)
                ),
                AddNetworkParams(
                    request = AddNetworkRequest.Open(ssid = TEST_SSID, bssid = TEST_BSSID),
                    addNetworkResultCode = ADD_NETWORK_FAILURE_RESULT_CODE,
                    expectedResult = AddNetworkResult.Failure.ResultCode(ADD_NETWORK_FAILURE_RESULT_CODE)
                ),
                AddNetworkParams(
                    request = AddNetworkRequest.WPA2(ssid = TEST_SSID, passphrase = TEST_PASSPHRASE, bssid = null),
                    addNetworkResultCode = ADD_NETWORK_SUCCESS_RESULT_CODE,
                    expectedResult = AddNetworkResult.Success.ResultCode(ADD_NETWORK_SUCCESS_RESULT_CODE)
                ),
                AddNetworkParams(
                    request = AddNetworkRequest.WPA2(
                        ssid = TEST_SSID,
                        passphrase = TEST_PASSPHRASE,
                        bssid = TEST_BSSID
                    ),
                    addNetworkResultCode = ADD_NETWORK_SUCCESS_RESULT_CODE,
                    expectedResult = AddNetworkResult.Success.ResultCode(ADD_NETWORK_SUCCESS_RESULT_CODE)
                ),
                AddNetworkParams(
                    request = AddNetworkRequest.WPA2(
                        ssid = TEST_SSID,
                        passphrase = TEST_PASSPHRASE,
                        bssid = null
                    ),
                    addNetworkResultCode = ADD_NETWORK_FAILURE_RESULT_CODE,
                    expectedResult = AddNetworkResult.Failure.ResultCode(ADD_NETWORK_FAILURE_RESULT_CODE)
                ),
                AddNetworkParams(
                    request = AddNetworkRequest.WPA2(
                        ssid = TEST_SSID,
                        passphrase = TEST_PASSPHRASE,
                        bssid = TEST_BSSID
                    ),
                    addNetworkResultCode = ADD_NETWORK_FAILURE_RESULT_CODE,
                    expectedResult = AddNetworkResult.Failure.ResultCode(ADD_NETWORK_FAILURE_RESULT_CODE)
                ),
                AddNetworkParams(
                    request = AddNetworkRequest.WPA3(ssid = TEST_SSID, passphrase = TEST_PASSPHRASE, bssid = null),
                    expectedResult = AddNetworkResult.Failure.Assertion(
                        AssertionMessages.AddNetwork.WPA3Network.USED_PRE_ANDROID_29
                    )
                ),
                AddNetworkParams(
                    request = AddNetworkRequest.WPA3(
                        ssid = TEST_SSID,
                        passphrase = TEST_PASSPHRASE,
                        bssid = TEST_BSSID
                    ),
                    expectedResult = AddNetworkResult.Failure.Assertion(
                        AssertionMessages.AddNetwork.WPA3Network.USED_PRE_ANDROID_29
                    )
                ),
                AddNetworkParams(
                    request = AddNetworkRequest.WPA3(
                        ssid = TEST_SSID,
                        passphrase = TEST_PASSPHRASE,
                        bssid = null
                    ),
                    expectedResult = AddNetworkResult.Failure.Assertion(
                        AssertionMessages.AddNetwork.WPA3Network.USED_PRE_ANDROID_29
                    )
                ),
                AddNetworkParams(
                    request = AddNetworkRequest.WPA3(
                        ssid = TEST_SSID,
                        passphrase = TEST_PASSPHRASE,
                        bssid = TEST_BSSID
                    ),
                    expectedResult = AddNetworkResult.Failure.Assertion(
                        AssertionMessages.AddNetwork.WPA3Network.USED_PRE_ANDROID_29
                    )
                )
            )
        }

        data class AddNetworkParams(
            val request: AddNetworkRequest,
            val addNetworkResultCode: Int? = null,
            val expectedResult: AddNetworkResult
        )
    }
}
