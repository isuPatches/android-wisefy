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
import com.isupatches.android.wisefy.addnetwork.os.apis.Android30AddNetworkApi
import com.isupatches.android.wisefy.core.assertions.WisefyAssertions
import com.isupatches.android.wisefy.core.logging.DefaultWisefyLogger
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.mockito.BDDMockito.anyString
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.isNull
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(Parameterized::class)
internal class Android30AddNetworkAdapterTest(
    private val params: AddNetworkParams
) {

    @Mock
    private lateinit var mockWifiManager: WifiManager

    @Mock
    private lateinit var mockAndroid30AddNetworkApi: Android30AddNetworkApi

    private lateinit var adapter: Android30AddNetworkAdapter

    private var closable: AutoCloseable? = null

    @Before
    fun setUp() {
        closable = MockitoAnnotations.openMocks(this)
        adapter = Android30AddNetworkAdapter(
            wifiManager = mockWifiManager,
            logger = DefaultWisefyLogger(),
            assertions = WisefyAssertions(false),
            api = mockAndroid30AddNetworkApi
        )
    }

    @After
    fun tearDown() {
        closable?.close()
    }

    @Test
    fun test() {
        // Given
        given(
            mockAndroid30AddNetworkApi.addOpenNetwork(
                anyString(),
                isNull()
            )
        ).willReturn(params.addNetworkResultCode)
        given(
            mockAndroid30AddNetworkApi.addOpenNetwork(
                anyString(),
                anyString()
            )
        ).willReturn(params.addNetworkResultCode)
        given(
            mockAndroid30AddNetworkApi.addWPA2Network(
                anyString(),
                anyString(),
                isNull()
            )
        ).willReturn(params.addNetworkResultCode)
        given(
            mockAndroid30AddNetworkApi.addWPA2Network(
                anyString(),
                anyString(),
                anyString()
            )
        ).willReturn(params.addNetworkResultCode)
        given(
            mockAndroid30AddNetworkApi.addWPA3Network(
                anyString(),
                anyString(),
                isNull()
            )
        ).willReturn(params.addNetworkResultCode)
        given(
            mockAndroid30AddNetworkApi.addWPA3Network(
                anyString(),
                anyString(),
                anyString()
            )
        ).willReturn(params.addNetworkResultCode)

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
        private const val ADD_NETWORK_SUCCESS_RESULT_CODE: Int = WifiManager.STATUS_NETWORK_SUGGESTIONS_SUCCESS

        @JvmStatic
        @Parameterized.Parameters(name = "{index}: {0}")
        fun paramValues(): List<AddNetworkParams> {
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
                    addNetworkResultCode = ADD_NETWORK_SUCCESS_RESULT_CODE,
                    expectedResult = AddNetworkResult.Success.ResultCode(ADD_NETWORK_SUCCESS_RESULT_CODE)
                ),
                AddNetworkParams(
                    request = AddNetworkRequest.WPA3(
                        ssid = TEST_SSID,
                        passphrase = TEST_PASSPHRASE,
                        bssid = TEST_BSSID
                    ),
                    addNetworkResultCode = ADD_NETWORK_SUCCESS_RESULT_CODE,
                    expectedResult = AddNetworkResult.Success.ResultCode(ADD_NETWORK_SUCCESS_RESULT_CODE)
                ),
                AddNetworkParams(
                    request = AddNetworkRequest.WPA3(
                        ssid = TEST_SSID,
                        passphrase = TEST_PASSPHRASE,
                        bssid = null
                    ),
                    addNetworkResultCode = ADD_NETWORK_FAILURE_RESULT_CODE,
                    expectedResult = AddNetworkResult.Failure.ResultCode(ADD_NETWORK_FAILURE_RESULT_CODE)
                ),
                AddNetworkParams(
                    request = AddNetworkRequest.WPA3(
                        ssid = TEST_SSID,
                        passphrase = TEST_PASSPHRASE,
                        bssid = TEST_BSSID
                    ),
                    addNetworkResultCode = ADD_NETWORK_FAILURE_RESULT_CODE,
                    expectedResult = AddNetworkResult.Failure.ResultCode(ADD_NETWORK_FAILURE_RESULT_CODE)
                )
            )
        }

        data class AddNetworkParams(
            val request: AddNetworkRequest,
            val addNetworkResultCode: Int,
            val expectedResult: AddNetworkResult
        )
    }
}
