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
package com.isupatches.android.wisefy.addnetwork.os.impls

import android.net.wifi.WifiManager
import com.isupatches.android.wisefy.addnetwork.os.apis.DefaultAddNetworkApi
import com.isupatches.android.wisefy.core.logging.DefaultWisefyLogger
import com.isupatches.android.wisefy.testsupport.anyNonNull
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters
import org.mockito.BDDMockito.any
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.times
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@RunWith(Parameterized::class)
internal class DefaultAddNetworkApiImplTest(
    private val params: AddNetworkParams
) {

    @Mock
    private lateinit var mockWifiManager: WifiManager

    private lateinit var api: DefaultAddNetworkApi

    private var closable: AutoCloseable? = null

    @Before
    fun setUp() {
        closable = MockitoAnnotations.openMocks(this)
        api = DefaultAddNetworkApiImpl(wifiManager = mockWifiManager, logger = DefaultWisefyLogger())
    }

    @After
    fun tearDown() {
        closable?.close()
    }

    @Test
    fun test() {
        // Given
        @Suppress("Deprecation")
        given(mockWifiManager.addNetwork(any(android.net.wifi.WifiConfiguration::class.java))).willReturn(
            params.expectedResult
        )

        // Then
        val result = when (params) {
            is AddNetworkParams.Open -> api.addOpenNetwork(ssid = params.ssid, bssid = params.bssid)
            is AddNetworkParams.WPA2 -> {
                api.addWPA2Network(ssid = params.ssid, passphrase = params.passphrase, bssid = params.bssid)
            }
        }

        // When
        assertEquals(params.expectedResult, result)
        @Suppress("Deprecation")
        verify(mockWifiManager, times(1)).addNetwork(anyNonNull())
    }

    companion object {
        @JvmStatic
        @Parameters(name = "{index}: {0}")
        fun paramValues(): List<AddNetworkParams> {
            return listOf(
                AddNetworkParams.Open(
                    ssid = TEST_SSID,
                    expectedResult = ADD_NETWORK_SUCCESS_RESULT_CODE
                ),
                AddNetworkParams.Open(
                    ssid = TEST_SSID,
                    bssid = TEST_BSSID,
                    expectedResult = ADD_NETWORK_SUCCESS_RESULT_CODE
                ),
                AddNetworkParams.Open(
                    ssid = TEST_SSID,
                    expectedResult = ADD_NETWORK_FAILURE_RESULT_CODE
                ),
                AddNetworkParams.Open(
                    ssid = TEST_SSID,
                    bssid = TEST_BSSID,
                    expectedResult = ADD_NETWORK_FAILURE_RESULT_CODE
                ),
                AddNetworkParams.WPA2(
                    ssid = TEST_SSID,
                    passphrase = TEST_PASSPHRASE,
                    expectedResult = ADD_NETWORK_SUCCESS_RESULT_CODE
                ),
                AddNetworkParams.WPA2(
                    ssid = TEST_SSID,
                    passphrase = TEST_PASSPHRASE,
                    bssid = TEST_BSSID,
                    expectedResult = ADD_NETWORK_SUCCESS_RESULT_CODE
                ),
                AddNetworkParams.WPA2(
                    ssid = TEST_SSID,
                    passphrase = TEST_PASSPHRASE,
                    expectedResult = ADD_NETWORK_FAILURE_RESULT_CODE
                ),
                AddNetworkParams.WPA2(
                    ssid = TEST_SSID,
                    passphrase = TEST_PASSPHRASE,
                    bssid = TEST_BSSID,
                    expectedResult = ADD_NETWORK_FAILURE_RESULT_CODE
                )
            )
        }

        sealed class AddNetworkParams(
            open val expectedResult: Int
        ) {

            data class Open(
                val ssid: String,
                val bssid: String? = null,
                override val expectedResult: Int
            ) : AddNetworkParams(expectedResult)

            data class WPA2(
                val ssid: String,
                val passphrase: String,
                val bssid: String? = null,
                override val expectedResult: Int
            ) : AddNetworkParams(expectedResult)
        }

        private const val ADD_NETWORK_FAILURE_RESULT_CODE: Int = -1
        private const val ADD_NETWORK_SUCCESS_RESULT_CODE: Int = WifiManager.STATUS_NETWORK_SUGGESTIONS_SUCCESS

        private const val TEST_SSID: String = "Test SSID"
        private const val TEST_BSSID: String = "01:23:45:67:89:AB"

        private const val TEST_PASSPHRASE: String = "passPHRASE328!"
    }
}
