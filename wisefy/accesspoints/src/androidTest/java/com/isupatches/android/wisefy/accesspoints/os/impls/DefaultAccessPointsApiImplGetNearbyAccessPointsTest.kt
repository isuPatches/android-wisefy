/*
 * Copyright 2022 Patches Barrett
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
package com.isupatches.android.wisefy.accesspoints.os.impls

import android.net.wifi.ScanResult
import android.net.wifi.WifiManager
import com.isupatches.android.wisefy.accesspoints.entities.AccessPointData
import com.isupatches.android.wisefy.core.logging.DefaultWisefyLogger
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(Parameterized::class)
internal class DefaultAccessPointsApiImplGetNearbyAccessPointsTest(
    private val params: GetNearbyAccessPointsParams
) {

    @Mock
    private lateinit var mockWifiManager: WifiManager

    private lateinit var apiImpl: DefaultAccessPointsApiImpl

    private var closable: AutoCloseable? = null

    @Before
    fun setUp() {
        closable = MockitoAnnotations.openMocks(this)
        apiImpl = DefaultAccessPointsApiImpl(wifiManager = mockWifiManager, logger = DefaultWisefyLogger())
    }

    @After
    fun tearDown() {
        closable?.close()
    }

    @Test
    fun getNearbyAccessPoints() {
        // Given
        given(mockWifiManager.scanResults).willReturn(params.foundAccessPoints)

        // Then
        val result = apiImpl.getNearbyAccessPoints(filterDuplicates = params.filterDuplicates)

        // When
        assertEquals(params.expectedResultSize, result.size)
        assertEquals(params.expectedResultList, result)
    }

    companion object {
        @JvmStatic
        @Parameters(name = "{index}: {0}")
        fun foundAccessPointsParamValues(): List<GetNearbyAccessPointsParams> {
            return listOf(
                GetNearbyAccessPointsParams(
                    foundAccessPoints = null,
                    filterDuplicates = true,
                    expectedResultSize = 0,
                    expectedResultList = emptyList()
                ),
                GetNearbyAccessPointsParams(
                    foundAccessPoints = null,
                    filterDuplicates = false,
                    expectedResultSize = 0,
                    expectedResultList = emptyList()
                ),
                GetNearbyAccessPointsParams(
                    foundAccessPoints = emptyList(),
                    filterDuplicates = true,
                    expectedResultSize = 0,
                    expectedResultList = emptyList()
                ),
                GetNearbyAccessPointsParams(
                    foundAccessPoints = emptyList(),
                    filterDuplicates = false,
                    expectedResultSize = 0,
                    expectedResultList = emptyList()
                ),
                GetNearbyAccessPointsParams(
                    foundAccessPoints = listOf(TEST_SCAN_RESULT_1),
                    filterDuplicates = true,
                    expectedResultSize = 1,
                    expectedResultList = listOf(AccessPointData(TEST_SCAN_RESULT_1))
                ),
                GetNearbyAccessPointsParams(
                    foundAccessPoints = listOf(TEST_SCAN_RESULT_1),
                    filterDuplicates = false,
                    expectedResultSize = 1,
                    expectedResultList = listOf(AccessPointData(TEST_SCAN_RESULT_1))
                ),
                GetNearbyAccessPointsParams(
                    foundAccessPoints = listOf(TEST_SCAN_RESULT_1, TEST_SCAN_RESULT_2),
                    filterDuplicates = true,
                    expectedResultSize = 2,
                    expectedResultList = listOf(
                        AccessPointData(TEST_SCAN_RESULT_1),
                        AccessPointData(TEST_SCAN_RESULT_2)
                    )
                ),
                GetNearbyAccessPointsParams(
                    foundAccessPoints = listOf(TEST_SCAN_RESULT_1, TEST_SCAN_RESULT_2),
                    filterDuplicates = false,
                    expectedResultSize = 2,
                    expectedResultList = listOf(
                        AccessPointData(TEST_SCAN_RESULT_1),
                        AccessPointData(TEST_SCAN_RESULT_2)
                    )
                ),
                GetNearbyAccessPointsParams(
                    foundAccessPoints = listOf(TEST_SCAN_RESULT_1, TEST_SCAN_RESULT_SAME_SSID_LOWER_RSSI),
                    filterDuplicates = true,
                    expectedResultSize = 1,
                    expectedResultList = listOf(AccessPointData(TEST_SCAN_RESULT_1))
                ),
                GetNearbyAccessPointsParams(
                    foundAccessPoints = listOf(TEST_SCAN_RESULT_1, TEST_SCAN_RESULT_SAME_SSID_LOWER_RSSI),
                    filterDuplicates = false,
                    expectedResultSize = 2,
                    expectedResultList = listOf(
                        AccessPointData(TEST_SCAN_RESULT_1),
                        AccessPointData(TEST_SCAN_RESULT_SAME_SSID_LOWER_RSSI)
                    )
                ),
                GetNearbyAccessPointsParams(
                    foundAccessPoints = listOf(TEST_SCAN_RESULT_1, TEST_SCAN_RESULT_SAME_SSID_SAME_RSSI),
                    filterDuplicates = true,
                    expectedResultSize = 1,
                    expectedResultList = listOf(AccessPointData(TEST_SCAN_RESULT_1))
                ),
                GetNearbyAccessPointsParams(
                    foundAccessPoints = listOf(TEST_SCAN_RESULT_1, TEST_SCAN_RESULT_SAME_SSID_SAME_RSSI),
                    filterDuplicates = false,
                    expectedResultSize = 2,
                    expectedResultList = listOf(
                        AccessPointData(TEST_SCAN_RESULT_1),
                        AccessPointData(TEST_SCAN_RESULT_SAME_SSID_SAME_RSSI)
                    )
                ),
                GetNearbyAccessPointsParams(
                    foundAccessPoints = listOf(TEST_SCAN_RESULT_1, TEST_SCAN_RESULT_SAME_SSID_HIGHER_RSSI),
                    filterDuplicates = true,
                    expectedResultSize = 1,
                    expectedResultList = listOf(AccessPointData(TEST_SCAN_RESULT_SAME_SSID_HIGHER_RSSI))
                ),
                GetNearbyAccessPointsParams(
                    foundAccessPoints = listOf(TEST_SCAN_RESULT_1, TEST_SCAN_RESULT_SAME_SSID_HIGHER_RSSI),
                    filterDuplicates = false,
                    expectedResultSize = 2,
                    expectedResultList = listOf(
                        AccessPointData(TEST_SCAN_RESULT_1),
                        AccessPointData(TEST_SCAN_RESULT_SAME_SSID_HIGHER_RSSI)
                    )
                )
            )
        }

        data class GetNearbyAccessPointsParams(
            val foundAccessPoints: List<ScanResult>?,
            val filterDuplicates: Boolean,
            val expectedResultSize: Int,
            val expectedResultList: List<AccessPointData>
        )

        private const val TEST_SSID_1: String = "Test SSID 1"
        private const val TEST_SSID_2: String = "Test SSID 2"
        private const val TEST_RSSI_1: Int = -5
        private const val TEST_RSSI_2: Int = -25
        private const val TEST_RSSI_LOWER: Int = -15
        private const val TEST_RSSI_HIGHER: Int = 15
        private val TEST_SCAN_RESULT_1: ScanResult = ScanResult().apply {
            @Suppress("Deprecation")
            SSID = TEST_SSID_1
            level = TEST_RSSI_1
        }
        private val TEST_SCAN_RESULT_2: ScanResult = ScanResult().apply {
            @Suppress("Deprecation")
            SSID = TEST_SSID_2
            level = TEST_RSSI_2
        }
        private val TEST_SCAN_RESULT_SAME_SSID_LOWER_RSSI: ScanResult = ScanResult().apply {
            @Suppress("Deprecation")
            SSID = TEST_SSID_1
            level = TEST_RSSI_LOWER
        }
        private val TEST_SCAN_RESULT_SAME_SSID_SAME_RSSI: ScanResult = ScanResult().apply {
            @Suppress("Deprecation")
            SSID = TEST_SSID_1
            level = TEST_RSSI_1
        }
        private val TEST_SCAN_RESULT_SAME_SSID_HIGHER_RSSI: ScanResult = ScanResult().apply {
            @Suppress("Deprecation")
            SSID = TEST_SSID_1
            level = TEST_RSSI_HIGHER
        }
    }
}
