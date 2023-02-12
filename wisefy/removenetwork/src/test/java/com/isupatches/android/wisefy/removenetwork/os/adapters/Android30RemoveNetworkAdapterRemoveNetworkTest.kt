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
package com.isupatches.android.wisefy.removenetwork.os.adapters

import android.net.wifi.WifiManager
import com.isupatches.android.wisefy.core.logging.DefaultWisefyLogger
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkRequest
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkResult
import com.isupatches.android.wisefy.removenetwork.os.apis.Android30RemoveNetworkApi
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.mockito.BDDMockito.anyString
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(Parameterized::class)
internal class Android30RemoveNetworkAdapterRemoveNetworkTest(
    private val params: RemoveNetworkParams
) {

    @Mock
    private lateinit var mockWifiManager: WifiManager

    @Mock
    private lateinit var mockApi: Android30RemoveNetworkApi

    private lateinit var adapter: Android30RemoveNetworkAdapter

    private var closable: AutoCloseable? = null

    @Before
    fun setUp() {
        closable = MockitoAnnotations.openMocks(this)
        adapter = Android30RemoveNetworkAdapter(
            wifiManager = mockWifiManager,
            logger = DefaultWisefyLogger(),
            api = mockApi
        )
    }

    @After
    fun tearDown() {
        closable?.close()
    }

    @Test
    fun test() {
        // Given
        given(mockApi.removeNetworkBySSID(anyString())).willReturn(params.removeNetworkResult)
        given(mockApi.removeNetworkByBSSID(anyString())).willReturn(params.removeNetworkResult)

        // Then
        val result = adapter.removeNetwork(params.request)

        // When
        assertEquals(params.expectedResult, result)
    }

    companion object {
        private const val TEST_SSID: String = "Test SSID"
        private const val TEST_BSSID: String = "Test:BSSID"

        private const val ADD_NETWORK_FAILURE_RESULT_CODE: Int = -1
        private const val ADD_NETWORK_SUCCESS_RESULT_CODE: Int = WifiManager.STATUS_NETWORK_SUGGESTIONS_SUCCESS

        @JvmStatic
        @Parameterized.Parameters(name = "{index}: {0}")
        fun paramValues(): List<RemoveNetworkParams> {
            return listOf(
                RemoveNetworkParams(
                    request = RemoveNetworkRequest.SSID(ssid = TEST_SSID),
                    removeNetworkResult = ADD_NETWORK_SUCCESS_RESULT_CODE,
                    expectedResult = RemoveNetworkResult.Success.ResultCode(ADD_NETWORK_SUCCESS_RESULT_CODE)
                ),
                RemoveNetworkParams(
                    request = RemoveNetworkRequest.BSSID(bssid = TEST_BSSID),
                    removeNetworkResult = ADD_NETWORK_SUCCESS_RESULT_CODE,
                    expectedResult = RemoveNetworkResult.Success.ResultCode(ADD_NETWORK_SUCCESS_RESULT_CODE)
                ),
                RemoveNetworkParams(
                    request = RemoveNetworkRequest.SSID(ssid = TEST_SSID),
                    removeNetworkResult = ADD_NETWORK_FAILURE_RESULT_CODE,
                    expectedResult = RemoveNetworkResult.Failure.ResultCode(ADD_NETWORK_FAILURE_RESULT_CODE)
                ),
                RemoveNetworkParams(
                    request = RemoveNetworkRequest.BSSID(bssid = TEST_BSSID),
                    removeNetworkResult = ADD_NETWORK_FAILURE_RESULT_CODE,
                    expectedResult = RemoveNetworkResult.Failure.ResultCode(ADD_NETWORK_FAILURE_RESULT_CODE)
                )
            )
        }

        data class RemoveNetworkParams(
            val request: RemoveNetworkRequest,
            val removeNetworkResult: Int,
            val expectedResult: RemoveNetworkResult
        )
    }
}
