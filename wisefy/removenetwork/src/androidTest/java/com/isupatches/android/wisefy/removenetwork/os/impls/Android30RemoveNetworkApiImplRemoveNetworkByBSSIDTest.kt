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
package com.isupatches.android.wisefy.removenetwork.os.impls

import android.net.MacAddress
import android.net.wifi.WifiManager
import android.net.wifi.WifiNetworkSuggestion
import android.os.Build
import com.isupatches.android.wisefy.core.logging.DefaultWisefyLogger
import com.isupatches.android.wisefy.removenetwork.os.apis.Android30RemoveNetworkApi
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
internal class Android30RemoveNetworkApiImplRemoveNetworkByBSSIDTest(
    private val params: RemoveNetworkParams
) {

    @Mock
    private lateinit var mockWifiManager: WifiManager

    private lateinit var api: Android30RemoveNetworkApi

    private var closable: AutoCloseable? = null

    @Before
    fun setUp() {
        closable = MockitoAnnotations.openMocks(this)
        api = Android30RemoveNetworkApiImpl(wifiManager = mockWifiManager, logger = DefaultWisefyLogger())
    }

    @After
    fun tearDown() {
        closable?.close()
    }

    @Test
    fun test() {
        assumeTrue(
            "Can only run on API Level ${Build.VERSION_CODES.R} or newer",
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.R
        )

        // Given
        given(mockWifiManager.networkSuggestions).willReturn(params.networks)
        given(mockWifiManager.removeNetworkSuggestions(anyNonNull())).willReturn(params.expectedResult)

        // Then
        val result = api.removeNetworkByBSSID(bssid = params.bssid)

        // When
        assertEquals(params.expectedResult, result)
        verify(mockWifiManager, times(1)).networkSuggestions
        verify(mockWifiManager, times(params.expectedRemoveRequest)).removeNetworkSuggestions(anyNonNull())
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: {0}")
        fun paramValues(): List<RemoveNetworkParams> {
            return listOf(
                RemoveNetworkParams(
                    bssid = TEST_BSSID1,
                    networks = listOf(
                        WifiNetworkSuggestion.Builder()
                            .setSsid(TEST_SSID)
                            .setBssid(MacAddress.fromString(TEST_BSSID1))
                            .setIsAppInteractionRequired(true)
                            .build()
                    ),
                    expectedResult = REMOVE_NETWORK_SUCCESS_RESULT_CODE,
                    expectedRemoveRequest = 1
                ),
                RemoveNetworkParams(
                    bssid = TEST_BSSID1,
                    networks = listOf(
                        WifiNetworkSuggestion.Builder()
                            .setSsid(TEST_SSID)
                            .setBssid(MacAddress.fromString(TEST_BSSID1))
                            .setIsAppInteractionRequired(true)
                            .build()
                    ),
                    expectedResult = REMOVE_NETWORK_FAILURE_RESULT_CODE,
                    expectedRemoveRequest = 1
                ),
                RemoveNetworkParams(
                    bssid = TEST_BSSID1,
                    networks = emptyList(),
                    expectedResult = REMOVE_NETWORK_FAILURE_RESULT_CODE,
                    expectedRemoveRequest = 0
                ),
                RemoveNetworkParams(
                    bssid = TEST_BSSID1,
                    networks = listOf(
                        WifiNetworkSuggestion.Builder()
                            .setSsid(TEST_SSID)
                            .setBssid(MacAddress.fromString(TEST_BSSID2))
                            .setIsAppInteractionRequired(true)
                            .build()
                    ),
                    expectedResult = REMOVE_NETWORK_FAILURE_RESULT_CODE,
                    expectedRemoveRequest = 0
                ),
                RemoveNetworkParams(
                    bssid = TEST_BSSID2,
                    networks = listOf(
                        WifiNetworkSuggestion.Builder()
                            .setSsid(TEST_SSID)
                            .setBssid(MacAddress.fromString(TEST_BSSID1))
                            .setIsAppInteractionRequired(true)
                            .build()
                    ),
                    expectedResult = REMOVE_NETWORK_FAILURE_RESULT_CODE,
                    expectedRemoveRequest = 0
                ),
                RemoveNetworkParams(
                    bssid = TEST_BSSID2,
                    networks = listOf(
                        WifiNetworkSuggestion.Builder()
                            .setSsid(TEST_SSID)
                            .setBssid(MacAddress.fromString(TEST_BSSID1))
                            .setIsAppInteractionRequired(true)
                            .build(),
                        WifiNetworkSuggestion.Builder()
                            .setSsid(TEST_SSID)
                            .setBssid(MacAddress.fromString(TEST_BSSID3))
                            .setIsAppInteractionRequired(true)
                            .build()
                    ),
                    expectedResult = REMOVE_NETWORK_FAILURE_RESULT_CODE,
                    expectedRemoveRequest = 0
                ),
                RemoveNetworkParams(
                    bssid = TEST_BSSID1,
                    networks = listOf(
                        WifiNetworkSuggestion.Builder()
                            .setSsid(TEST_SSID)
                            .setBssid(MacAddress.fromString(TEST_BSSID2))
                            .setIsAppInteractionRequired(true)
                            .build(),
                        WifiNetworkSuggestion.Builder()
                            .setSsid(TEST_SSID)
                            .setBssid(MacAddress.fromString(TEST_BSSID3))
                            .setIsAppInteractionRequired(true)
                            .build()
                    ),
                    expectedResult = REMOVE_NETWORK_FAILURE_RESULT_CODE,
                    expectedRemoveRequest = 0
                ),
                RemoveNetworkParams(
                    bssid = TEST_BSSID1,
                    networks = listOf(
                        WifiNetworkSuggestion.Builder()
                            .setSsid(TEST_SSID)
                            .setBssid(MacAddress.fromString(TEST_BSSID1))
                            .setIsAppInteractionRequired(true)
                            .build(),
                        WifiNetworkSuggestion.Builder()
                            .setSsid(TEST_SSID)
                            .setBssid(MacAddress.fromString(TEST_BSSID2))
                            .setIsAppInteractionRequired(true)
                            .build()
                    ),
                    expectedResult = REMOVE_NETWORK_SUCCESS_RESULT_CODE,
                    expectedRemoveRequest = 1
                )
            )
        }

        data class RemoveNetworkParams(
            val bssid: String,
            val networks: List<WifiNetworkSuggestion>,
            val expectedResult: Int,
            val expectedRemoveRequest: Int
        )

        private const val REMOVE_NETWORK_FAILURE_RESULT_CODE: Int = -1
        private const val REMOVE_NETWORK_SUCCESS_RESULT_CODE: Int = WifiManager.STATUS_NETWORK_SUGGESTIONS_SUCCESS

        private const val TEST_SSID: String = "Test SSID"
        private const val TEST_BSSID1: String = "01:23:45:67:89:AB"
        private const val TEST_BSSID2: String = "23:45:67:89:AB:CD"
        private const val TEST_BSSID3: String = "45:67:89:AB:CD:EF"
    }
}
