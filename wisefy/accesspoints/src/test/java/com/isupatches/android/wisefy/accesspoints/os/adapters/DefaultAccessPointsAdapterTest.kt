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
package com.isupatches.android.wisefy.accesspoints.os.adapters

import android.net.wifi.ScanResult
import android.net.wifi.WifiManager
import com.isupatches.android.wisefy.accesspoints.entities.AccessPointData
import com.isupatches.android.wisefy.accesspoints.entities.GetAccessPointsQuery
import com.isupatches.android.wisefy.accesspoints.entities.GetAccessPointsResult
import com.isupatches.android.wisefy.accesspoints.os.apis.DefaultAccessPointsApi
import com.isupatches.android.wisefy.core.logging.DefaultWisefyLogger
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class DefaultAccessPointsAdapterTest {

    @Mock
    private lateinit var mockWifiManager: WifiManager

    @Mock
    private lateinit var mockDefaultAccessPointsApi: DefaultAccessPointsApi

    private lateinit var adapter: DefaultAccessPointsAdapter

    @Before
    fun setUp() {
        adapter = DefaultAccessPointsAdapter(
            wifiManager = mockWifiManager,
            logger = DefaultWisefyLogger(),
            api = mockDefaultAccessPointsApi
        )
    }

    @Test
    fun getAccessPoints_emptyList_GetAccessPointsQuery_All_filterDuplicates_true() {
        // Given
        given(mockDefaultAccessPointsApi.getNearbyAccessPoints(true)).willReturn(emptyList())

        // When
        val result = adapter.getAccessPoints(GetAccessPointsQuery.All())

        // Then
        assertEquals(GetAccessPointsResult.Empty, result)
    }

    @Test
    fun getAccessPoints_emptyList_GetAccessPointsQuery_All_filterDuplicates_false() {
        // Given
        given(mockDefaultAccessPointsApi.getNearbyAccessPoints(false)).willReturn(emptyList())

        // When
        val result = adapter.getAccessPoints(GetAccessPointsQuery.All(filterDuplicates = false))

        // Then
        assertEquals(GetAccessPointsResult.Empty, result)
    }

    @Test
    fun getAccessPoints_emptyList_GetAccessPointsQuery_BySSID_withoutTimeout_filterDuplicates_true() {
        // Given
        given(mockDefaultAccessPointsApi.searchForAccessPointsBySSID(TEST_SSID, null, true)).willReturn(emptyList())

        // When
        val result = adapter.getAccessPoints(GetAccessPointsQuery.BySSID(TEST_SSID))

        // Then
        assertEquals(GetAccessPointsResult.Empty, result)
    }

    @Test
    fun getAccessPoints_emptyList_GetAccessPointsQuery_BySSID_withoutTimeout_filterDuplicates_false() {
        // Given
        given(mockDefaultAccessPointsApi.searchForAccessPointsBySSID(TEST_SSID, null, false)).willReturn(emptyList())

        // When
        val result = adapter.getAccessPoints(GetAccessPointsQuery.BySSID(TEST_SSID, null, false))

        // Then
        assertEquals(GetAccessPointsResult.Empty, result)
    }

    @Test
    fun getAccessPoints_emptyList_GetAccessPointsQuery_BySSID_withTimeout_filterDuplicates_true() {
        // Given
        given(mockDefaultAccessPointsApi.searchForAccessPointsBySSID(TEST_SSID, 5, true)).willReturn(emptyList())

        // When
        val result = adapter.getAccessPoints(GetAccessPointsQuery.BySSID(TEST_SSID, 5))

        // Then
        assertEquals(GetAccessPointsResult.Empty, result)
    }

    @Test
    fun getAccessPoints_emptyList_GetAccessPointsQuery_BySSID_withTimeout_filterDuplicates_false() {
        // Given
        given(mockDefaultAccessPointsApi.searchForAccessPointsByBSSID(TEST_BSSID, 5, false)).willReturn(emptyList())

        // When
        val result = adapter.getAccessPoints(GetAccessPointsQuery.ByBSSID(TEST_BSSID, 5, false))

        // Then
        assertEquals(GetAccessPointsResult.Empty, result)
    }

    @Test
    fun getAccessPoints_emptyList_GetAccessPointsQuery_ByBSSID_withoutTimeout_filterDuplicates_true() {
        // Given
        given(mockDefaultAccessPointsApi.searchForAccessPointsByBSSID(TEST_BSSID, null, true)).willReturn(emptyList())

        // When
        val result = adapter.getAccessPoints(GetAccessPointsQuery.ByBSSID(TEST_BSSID))

        // Then
        assertEquals(GetAccessPointsResult.Empty, result)
    }

    @Test
    fun getAccessPoints_emptyList_GetAccessPointsQuery_ByBSSID_withoutTimeout_filterDuplicates_false() {
        // Given
        given(mockDefaultAccessPointsApi.searchForAccessPointsByBSSID(TEST_BSSID, null, false)).willReturn(emptyList())

        // When
        val result = adapter.getAccessPoints(GetAccessPointsQuery.ByBSSID(TEST_BSSID, null, false))

        // Then
        assertEquals(GetAccessPointsResult.Empty, result)
    }

    @Test
    fun getAccessPoints_emptyList_GetAccessPointsQuery_ByBSSID_withTimeout_filterDuplicates_true() {
        // Given
        given(mockDefaultAccessPointsApi.searchForAccessPointsByBSSID(TEST_BSSID, 5, true)).willReturn(emptyList())

        // When
        val result = adapter.getAccessPoints(GetAccessPointsQuery.ByBSSID(TEST_BSSID, 5))

        // Then
        assertEquals(GetAccessPointsResult.Empty, result)
    }

    @Test
    fun getAccessPoints_emptyList_GetAccessPointsQuery_ByBSSID_withTimeout_filterDuplicates_false() {
        // Given
        given(mockDefaultAccessPointsApi.searchForAccessPointsByBSSID(TEST_BSSID, 5, false)).willReturn(emptyList())

        // When
        val result = adapter.getAccessPoints(GetAccessPointsQuery.ByBSSID(TEST_BSSID, 5, false))

        // Then
        assertEquals(GetAccessPointsResult.Empty, result)
    }

    @Test
    fun getAccessPoints_withList_GetAccessPointsQuery_All_filterDuplicates_true() {
        // Given
        given(mockDefaultAccessPointsApi.getNearbyAccessPoints(true)).willReturn(listOf(TEST_ACCESS_POINT))

        // When
        val result = adapter.getAccessPoints(GetAccessPointsQuery.All())

        // Then
        assertTrue(result is GetAccessPointsResult.AccessPoints)
        assertEquals(1, (result as GetAccessPointsResult.AccessPoints).value.size)
        assertEquals(listOf(TEST_ACCESS_POINT), result.value)
    }

    @Test
    fun getAccessPoints_withList_GetAccessPointsQuery_All_filterDuplicates_false() {
        // Given
        given(mockDefaultAccessPointsApi.getNearbyAccessPoints(false)).willReturn(listOf(TEST_ACCESS_POINT))

        // When
        val result = adapter.getAccessPoints(GetAccessPointsQuery.All(filterDuplicates = false))

        // Then
        assertTrue(result is GetAccessPointsResult.AccessPoints)
        assertEquals(1, (result as GetAccessPointsResult.AccessPoints).value.size)
        assertEquals(listOf(TEST_ACCESS_POINT), result.value)
    }

    @Test
    fun getAccessPoints_withList_GetAccessPointsQuery_BySSID_withoutTimeout_filterDuplicates_true() {
        // Given
        given(mockDefaultAccessPointsApi.searchForAccessPointsBySSID(TEST_SSID, null, true)).willReturn(
            listOf(TEST_ACCESS_POINT)
        )

        // When
        val result = adapter.getAccessPoints(GetAccessPointsQuery.BySSID(TEST_SSID))

        // Then
        assertTrue(result is GetAccessPointsResult.AccessPoints)
        assertEquals(1, (result as GetAccessPointsResult.AccessPoints).value.size)
        assertEquals(listOf(TEST_ACCESS_POINT), result.value)
    }

    @Test
    fun getAccessPoints_withList_GetAccessPointsQuery_BySSID_withoutTimeout_filterDuplicates_false() {
        // Given
        given(mockDefaultAccessPointsApi.searchForAccessPointsBySSID(TEST_SSID, null, false)).willReturn(
            listOf(TEST_ACCESS_POINT)
        )

        // When
        val result = adapter.getAccessPoints(GetAccessPointsQuery.BySSID(TEST_SSID, null, false))

        // Then
        assertTrue(result is GetAccessPointsResult.AccessPoints)
        assertEquals(1, (result as GetAccessPointsResult.AccessPoints).value.size)
        assertEquals(listOf(TEST_ACCESS_POINT), result.value)
    }

    @Test
    fun getAccessPoints_withList_GetAccessPointsQuery_BySSID_withTimeout_filterDuplicates_true() {
        // Given
        given(mockDefaultAccessPointsApi.searchForAccessPointsBySSID(TEST_SSID, 5, true)).willReturn(
            listOf(TEST_ACCESS_POINT)
        )

        // When
        val result = adapter.getAccessPoints(GetAccessPointsQuery.BySSID(TEST_SSID, 5))

        // Then
        assertTrue(result is GetAccessPointsResult.AccessPoints)
        assertEquals(1, (result as GetAccessPointsResult.AccessPoints).value.size)
        assertEquals(listOf(TEST_ACCESS_POINT), result.value)
    }

    @Test
    fun getAccessPoints_withList_GetAccessPointsQuery_BySSID_withTimeout_filterDuplicates_false() {
        // Given
        given(mockDefaultAccessPointsApi.searchForAccessPointsByBSSID(TEST_BSSID, 5, false)).willReturn(
            listOf(TEST_ACCESS_POINT)
        )

        // When
        val result = adapter.getAccessPoints(GetAccessPointsQuery.ByBSSID(TEST_BSSID, 5, false))

        // Then
        assertTrue(result is GetAccessPointsResult.AccessPoints)
        assertEquals(1, (result as GetAccessPointsResult.AccessPoints).value.size)
        assertEquals(listOf(TEST_ACCESS_POINT), result.value)
    }

    @Test
    fun getAccessPoints_withList_GetAccessPointsQuery_ByBSSID_withoutTimeout_filterDuplicates_true() {
        // Given
        given(mockDefaultAccessPointsApi.searchForAccessPointsByBSSID(TEST_BSSID, null, true)).willReturn(
            listOf(TEST_ACCESS_POINT)
        )

        // When
        val result = adapter.getAccessPoints(GetAccessPointsQuery.ByBSSID(TEST_BSSID))

        // Then
        assertTrue(result is GetAccessPointsResult.AccessPoints)
        assertEquals(1, (result as GetAccessPointsResult.AccessPoints).value.size)
        assertEquals(listOf(TEST_ACCESS_POINT), result.value)
    }

    @Test
    fun getAccessPoints_withList_GetAccessPointsQuery_ByBSSID_withoutTimeout_filterDuplicates_false() {
        // Given
        given(mockDefaultAccessPointsApi.searchForAccessPointsByBSSID(TEST_BSSID, null, false)).willReturn(
            listOf(TEST_ACCESS_POINT)
        )

        // When
        val result = adapter.getAccessPoints(GetAccessPointsQuery.ByBSSID(TEST_BSSID, null, false))

        // Then
        assertTrue(result is GetAccessPointsResult.AccessPoints)
        assertEquals(1, (result as GetAccessPointsResult.AccessPoints).value.size)
        assertEquals(listOf(TEST_ACCESS_POINT), result.value)
    }

    @Test
    fun getAccessPoints_withList_GetAccessPointsQuery_ByBSSID_withTimeout_filterDuplicates_true() {
        // Given
        given(mockDefaultAccessPointsApi.searchForAccessPointsByBSSID(TEST_BSSID, 5, true)).willReturn(
            listOf(TEST_ACCESS_POINT)
        )

        // When
        val result = adapter.getAccessPoints(GetAccessPointsQuery.ByBSSID(TEST_BSSID, 5))

        // Then
        assertTrue(result is GetAccessPointsResult.AccessPoints)
        assertEquals(1, (result as GetAccessPointsResult.AccessPoints).value.size)
        assertEquals(listOf(TEST_ACCESS_POINT), result.value)
    }

    @Test
    fun getAccessPoints_withList_GetAccessPointsQuery_ByBSSID_withTimeout_filterDuplicates_false() {
        // Given
        given(mockDefaultAccessPointsApi.searchForAccessPointsByBSSID(TEST_BSSID, 5, false)).willReturn(
            listOf(TEST_ACCESS_POINT)
        )

        // When
        val result = adapter.getAccessPoints(GetAccessPointsQuery.ByBSSID(TEST_BSSID, 5, false))

        // Then
        assertTrue(result is GetAccessPointsResult.AccessPoints)
        assertEquals(1, (result as GetAccessPointsResult.AccessPoints).value.size)
        assertEquals(listOf(TEST_ACCESS_POINT), result.value)
    }

    companion object {
        private const val TEST_SSID = "test SSID"
        private const val TEST_BSSID = "test:BSSID"
        private val TEST_ACCESS_POINT = AccessPointData(
            rawValue = ScanResult().apply {
                level = -66
                capabilities = ""
            },
            ssid = TEST_SSID,
            bssid = TEST_BSSID
        )
    }
}
