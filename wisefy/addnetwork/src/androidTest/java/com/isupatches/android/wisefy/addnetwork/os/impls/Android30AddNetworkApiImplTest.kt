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
import android.os.Build
import com.isupatches.android.wisefy.core.logging.DefaultWisefyLogger
import com.isupatches.android.wisefy.testsupport.anyNonNull
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assume.assumeTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.times
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@RunWith(Parameterized::class)
internal class Android30AddNetworkApiImplTest(
    private val params: AddNetworkParams
) {

    @Mock
    private lateinit var mockWifiManager: WifiManager

    private lateinit var apiImpl: Android30AddNetworkApiImpl

    private var closable: AutoCloseable? = null

    @Before
    fun setUp() {
        closable = MockitoAnnotations.openMocks(this)
        apiImpl = Android30AddNetworkApiImpl(wifiManager = mockWifiManager, logger = DefaultWisefyLogger())
    }

    @After
    fun tearDown() {
        closable?.close()
    }

    @Test
    fun addNetwork() {
        assumeTrue(
            "Can only run on API Level ${Build.VERSION_CODES.Q} or newer",
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
        )

        // Given
        given(mockWifiManager.addNetworkSuggestions(anyNonNull())).willReturn(params.expectedResult)

        // Then
        val result = when (params) {
            is AddNetworkParams.Open -> apiImpl.addOpenNetwork(ssid = params.ssid, bssid = params.bssid)
            is AddNetworkParams.WPA2 -> {
                apiImpl.addWPA2Network(ssid = params.ssid, passphrase = params.passphrase, bssid = params.bssid)
            }
            is AddNetworkParams.WPA3 -> {
                apiImpl.addWPA3Network(ssid = params.ssid, passphrase = params.passphrase, bssid = params.bssid)
            }
        }

        // When
        assertEquals(params.expectedResult, result)
        verify(mockWifiManager, times(1)).addNetworkSuggestions(anyNonNull())
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: {0}")
        fun foundAddNetworkParamValues(): List<AddNetworkParams> {
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
                ),
                AddNetworkParams.WPA3(
                    ssid = TEST_SSID,
                    passphrase = TEST_PASSPHRASE,
                    expectedResult = ADD_NETWORK_SUCCESS_RESULT_CODE
                ),
                AddNetworkParams.WPA3(
                    ssid = TEST_SSID,
                    passphrase = TEST_PASSPHRASE,
                    bssid = TEST_BSSID,
                    expectedResult = ADD_NETWORK_SUCCESS_RESULT_CODE
                ),
                AddNetworkParams.WPA3(
                    ssid = TEST_SSID,
                    passphrase = TEST_PASSPHRASE,
                    expectedResult = ADD_NETWORK_FAILURE_RESULT_CODE
                ),
                AddNetworkParams.WPA3(
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

            data class WPA3(
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
