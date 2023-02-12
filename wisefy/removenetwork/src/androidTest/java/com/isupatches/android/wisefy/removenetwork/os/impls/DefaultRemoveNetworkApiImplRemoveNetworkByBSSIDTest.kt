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

import android.net.wifi.WifiManager
import com.isupatches.android.wisefy.core.logging.DefaultWisefyLogger
import com.isupatches.android.wisefy.removenetwork.os.apis.DefaultRemoveNetworkApi
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.mockito.BDDMockito.anyInt
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.times
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@RunWith(Parameterized::class)
internal class DefaultRemoveNetworkApiImplRemoveNetworkByBSSIDTest(
    private val params: RemoveNetworkParams
) {

    @Mock
    private lateinit var mockWifiManager: WifiManager

    private lateinit var api: DefaultRemoveNetworkApi

    private var closable: AutoCloseable? = null

    @Before
    fun setUp() {
        closable = MockitoAnnotations.openMocks(this)
        api = DefaultRemoveNetworkApiImpl(wifiManager = mockWifiManager, logger = DefaultWisefyLogger())
    }

    @After
    fun tearDown() {
        closable?.close()
    }

    @Test
    fun test() {
        // Given
        @Suppress("Deprecation")
        given(mockWifiManager.configuredNetworks).willReturn(params.networks)
        @Suppress("Deprecation")
        given(mockWifiManager.removeNetwork(anyInt())).willReturn(params.expectedResult)

        // Then
        val result = api.removeNetworkByBSSID(bssid = params.bssid)

        // When
        assertEquals(params.expectedResult, result)
        @Suppress("Deprecation")
        verify(mockWifiManager, times(1)).configuredNetworks
        @Suppress("Deprecation")
        verify(mockWifiManager, times(params.expectedRemoveRequest)).removeNetwork(anyInt())
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: {0}")
        fun paramValues(): List<RemoveNetworkParams> {
            return listOf(
                RemoveNetworkParams(
                    bssid = TEST_BSSID1,
                    networks = listOf(
                        @Suppress("Deprecation")
                        android.net.wifi.WifiConfiguration().apply {
                            SSID = TEST_SSID
                            BSSID = TEST_BSSID1
                        }
                    ),
                    expectedResult = true,
                    expectedRemoveRequest = 1
                ),
                RemoveNetworkParams(
                    bssid = TEST_BSSID1,
                    networks = listOf(
                        @Suppress("Deprecation")
                        android.net.wifi.WifiConfiguration().apply {
                            SSID = TEST_SSID
                            BSSID = TEST_BSSID1
                        }
                    ),
                    expectedResult = false,
                    expectedRemoveRequest = 1
                ),
                RemoveNetworkParams(
                    bssid = TEST_BSSID1,
                    networks = emptyList(),
                    expectedResult = false,
                    expectedRemoveRequest = 0
                ),
                RemoveNetworkParams(
                    bssid = TEST_BSSID1,
                    networks = listOf(
                        @Suppress("Deprecation")
                        android.net.wifi.WifiConfiguration().apply {
                            SSID = TEST_SSID
                            BSSID = TEST_BSSID2
                        }
                    ),
                    expectedResult = false,
                    expectedRemoveRequest = 0
                ),
                RemoveNetworkParams(
                    bssid = TEST_BSSID2,
                    networks = listOf(
                        @Suppress("Deprecation")
                        android.net.wifi.WifiConfiguration().apply {
                            SSID = TEST_SSID
                            BSSID = TEST_BSSID1
                        }
                    ),
                    expectedResult = false,
                    expectedRemoveRequest = 0
                ),
                RemoveNetworkParams(
                    bssid = TEST_BSSID2,
                    networks = listOf(
                        @Suppress("Deprecation")
                        android.net.wifi.WifiConfiguration().apply {
                            SSID = TEST_SSID
                            BSSID = TEST_BSSID1
                        },
                        @Suppress("Deprecation")
                        android.net.wifi.WifiConfiguration().apply {
                            SSID = TEST_SSID
                            BSSID = TEST_BSSID3
                        }
                    ),
                    expectedResult = false,
                    expectedRemoveRequest = 0
                ),
                RemoveNetworkParams(
                    bssid = TEST_BSSID1,
                    networks = listOf(
                        @Suppress("Deprecation")
                        android.net.wifi.WifiConfiguration().apply {
                            SSID = TEST_SSID
                            BSSID = TEST_BSSID2
                        },
                        @Suppress("Deprecation")
                        android.net.wifi.WifiConfiguration().apply {
                            SSID = TEST_SSID
                            BSSID = TEST_BSSID3
                        }
                    ),
                    expectedResult = false,
                    expectedRemoveRequest = 0
                ),
                RemoveNetworkParams(
                    bssid = TEST_BSSID1,
                    networks = listOf(
                        @Suppress("Deprecation")
                        android.net.wifi.WifiConfiguration().apply {
                            SSID = TEST_SSID
                            BSSID = TEST_BSSID1
                        },
                        @Suppress("Deprecation")
                        android.net.wifi.WifiConfiguration().apply {
                            SSID = TEST_SSID
                            BSSID = TEST_BSSID2
                        }
                    ),
                    expectedResult = true,
                    expectedRemoveRequest = 1
                )
            )
        }

        data class RemoveNetworkParams(
            val bssid: String,
            @Suppress("Deprecation")
            val networks: List<android.net.wifi.WifiConfiguration>,
            val expectedResult: Boolean,
            val expectedRemoveRequest: Int
        )

        private const val TEST_SSID: String = "Test SSID"
        private const val TEST_BSSID1: String = "01:23:45:67:89:AB"
        private const val TEST_BSSID2: String = "23:45:67:89:AB:CD"
        private const val TEST_BSSID3: String = "45:67:89:AB:CD:EF"
    }
}
