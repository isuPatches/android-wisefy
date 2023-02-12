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
internal class DefaultRemoveNetworkApiImplRemoveNetworkBySSIDTest(
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
        val result = api.removeNetworkBySSID(ssid = params.ssid)

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
                    ssid = TEST_SSID1,
                    networks = listOf(
                        @Suppress("Deprecation")
                        android.net.wifi.WifiConfiguration().apply {
                            SSID = TEST_SSID1
                        }
                    ),
                    expectedResult = true,
                    expectedRemoveRequest = 1
                ),
                RemoveNetworkParams(
                    ssid = TEST_SSID1,
                    networks = listOf(
                        @Suppress("Deprecation")
                        android.net.wifi.WifiConfiguration().apply {
                            SSID = TEST_SSID1
                        }
                    ),
                    expectedResult = false,
                    expectedRemoveRequest = 1
                ),
                RemoveNetworkParams(
                    ssid = TEST_SSID1,
                    networks = emptyList(),
                    expectedResult = false,
                    expectedRemoveRequest = 0
                ),
                RemoveNetworkParams(
                    ssid = TEST_SSID1,
                    networks = listOf(
                        @Suppress("Deprecation")
                        android.net.wifi.WifiConfiguration().apply {
                            SSID = TEST_SSID2
                        }
                    ),
                    expectedResult = false,
                    expectedRemoveRequest = 0
                ),
                RemoveNetworkParams(
                    ssid = TEST_SSID2,
                    networks = listOf(
                        @Suppress("Deprecation")
                        android.net.wifi.WifiConfiguration().apply {
                            SSID = TEST_SSID1
                        }
                    ),
                    expectedResult = false,
                    expectedRemoveRequest = 0
                ),
                RemoveNetworkParams(
                    ssid = TEST_SSID2,
                    networks = listOf(
                        @Suppress("Deprecation")
                        android.net.wifi.WifiConfiguration().apply {
                            SSID = TEST_SSID1
                        },
                        @Suppress("Deprecation")
                        android.net.wifi.WifiConfiguration().apply {
                            SSID = TEST_SSID3
                        }
                    ),
                    expectedResult = false,
                    expectedRemoveRequest = 0
                ),
                RemoveNetworkParams(
                    ssid = TEST_SSID1,
                    networks = listOf(
                        @Suppress("Deprecation")
                        android.net.wifi.WifiConfiguration().apply {
                            SSID = TEST_SSID2
                        },
                        @Suppress("Deprecation")
                        android.net.wifi.WifiConfiguration().apply {
                            SSID = TEST_SSID3
                        }
                    ),
                    expectedResult = false,
                    expectedRemoveRequest = 0
                ),
                RemoveNetworkParams(
                    ssid = TEST_SSID1,
                    networks = listOf(
                        @Suppress("Deprecation")
                        android.net.wifi.WifiConfiguration().apply {
                            SSID = TEST_SSID1
                        },
                        @Suppress("Deprecation")
                        android.net.wifi.WifiConfiguration().apply {
                            SSID = TEST_SSID2
                        }
                    ),
                    expectedResult = true,
                    expectedRemoveRequest = 1
                )
            )
        }

        data class RemoveNetworkParams(
            val ssid: String,
            @Suppress("Deprecation")
            val networks: List<android.net.wifi.WifiConfiguration>,
            val expectedResult: Boolean,
            val expectedRemoveRequest: Int
        )

        private const val TEST_SSID1: String = "Test SSID1"
        private const val TEST_SSID2: String = "Test SSID2"
        private const val TEST_SSID3: String = "Test SSID3"
    }
}
