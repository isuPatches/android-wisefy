/*
 * Copyright (c) 2024. Patches Barrett
 *
 * Last modified: September 22, 2024
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
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.mockito.quality.Strictness

internal class Android30RemoveNetworkApiImplTest {

    @get:Rule
    var mockitoRule: MockitoRule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS)

    @Mock
    private lateinit var mockWifiManager: WifiManager

    @Test
    fun removeNetwork_bySSID_singleSSID_success() {
        // Given
        val networkSuggestion1 = WifiNetworkSuggestion.Builder()
            .setSsid(TEST_SSID1)
            .setIsAppInteractionRequired(true)
            .build()
        val networkSuggestions = listOf(networkSuggestion1)
        given(mockWifiManager.removeNetworkSuggestions(listOf(networkSuggestion1))).willReturn(
            REMOVE_NETWORK_SUCCESS_RESULT_CODE,
        )
        given(mockWifiManager.networkSuggestions).willReturn(networkSuggestions)
        val impl = Android30RemoveNetworkApiImpl(wifiManager = mockWifiManager)

        // When
        val result = impl.removeNetworkBySSID(TEST_SSID1)

        // Then
        assertEquals(REMOVE_NETWORK_SUCCESS_RESULT_CODE, result)
        verify(mockWifiManager, times(1)).removeNetworkSuggestions(listOf(networkSuggestion1))
    }

    @Test
    fun removeNetwork_bySSID_singleSSID_failure() {
        // Given
        val networkSuggestion1 = WifiNetworkSuggestion.Builder()
            .setSsid(TEST_SSID1)
            .setIsAppInteractionRequired(true)
            .build()
        val networkSuggestions = listOf(networkSuggestion1)
        given(mockWifiManager.removeNetworkSuggestions(listOf(networkSuggestion1))).willReturn(
            REMOVE_NETWORK_FAILURE_RESULT_CODE,
        )
        given(mockWifiManager.networkSuggestions).willReturn(networkSuggestions)
        val impl = Android30RemoveNetworkApiImpl(wifiManager = mockWifiManager)

        // When
        val result = impl.removeNetworkBySSID(TEST_SSID1)

        // Then
        assertEquals(REMOVE_NETWORK_FAILURE_RESULT_CODE, result)
        verify(mockWifiManager, times(1)).removeNetworkSuggestions(listOf(networkSuggestion1))
    }

    @Test
    fun removeNetwork_bySSID_multipleSSIDs_success() {
        // Given
        val networkSuggestion1 = WifiNetworkSuggestion.Builder()
            .setSsid(TEST_SSID1)
            .setIsAppInteractionRequired(true)
            .build()
        val networkSuggestion2 = WifiNetworkSuggestion.Builder()
            .setSsid(TEST_SSID2)
            .setIsAppInteractionRequired(true)
            .build()
        val networkSuggestions = listOf(networkSuggestion1, networkSuggestion2)
        given(mockWifiManager.removeNetworkSuggestions(listOf(networkSuggestion2))).willReturn(
            REMOVE_NETWORK_SUCCESS_RESULT_CODE,
        )
        given(mockWifiManager.networkSuggestions).willReturn(networkSuggestions)
        val impl = Android30RemoveNetworkApiImpl(wifiManager = mockWifiManager)

        // When
        val result = impl.removeNetworkBySSID(TEST_SSID2)

        // Then
        assertEquals(REMOVE_NETWORK_SUCCESS_RESULT_CODE, result)
        verify(mockWifiManager, times(1)).removeNetworkSuggestions(listOf(networkSuggestion2))
    }

    @Test
    fun removeNetwork_bySSID_multipleSSIDs_failure() {
        // Given
        val networkSuggestion1 = WifiNetworkSuggestion.Builder()
            .setSsid(TEST_SSID1)
            .setIsAppInteractionRequired(true)
            .build()
        val networkSuggestion2 = WifiNetworkSuggestion.Builder()
            .setSsid(TEST_SSID2)
            .setIsAppInteractionRequired(true)
            .build()
        val networkSuggestions = listOf(networkSuggestion1, networkSuggestion2)
        given(mockWifiManager.removeNetworkSuggestions(listOf(networkSuggestion2))).willReturn(
            REMOVE_NETWORK_FAILURE_RESULT_CODE,
        )
        given(mockWifiManager.networkSuggestions).willReturn(networkSuggestions)
        val impl = Android30RemoveNetworkApiImpl(wifiManager = mockWifiManager)

        // When
        val result = impl.removeNetworkBySSID(TEST_SSID2)

        // Then
        assertEquals(REMOVE_NETWORK_FAILURE_RESULT_CODE, result)
        verify(mockWifiManager, times(1)).removeNetworkSuggestions(listOf(networkSuggestion2))
    }

    @Test
    fun removeNetwork_byBSSID_singleBSSID_success() {
        // Given
        val networkSuggestion1 = WifiNetworkSuggestion.Builder()
            .setSsid(TEST_SSID1)
            .setBssid(MacAddress.fromString(TEST_BSSID1))
            .setIsAppInteractionRequired(true)
            .build()
        val networkSuggestions = listOf(networkSuggestion1)
        given(mockWifiManager.removeNetworkSuggestions(listOf(networkSuggestion1))).willReturn(
            REMOVE_NETWORK_SUCCESS_RESULT_CODE,
        )
        given(mockWifiManager.networkSuggestions).willReturn(networkSuggestions)
        val impl = Android30RemoveNetworkApiImpl(wifiManager = mockWifiManager)

        // When
        val result = impl.removeNetworkByBSSID(TEST_BSSID1)

        // Then
        assertEquals(REMOVE_NETWORK_SUCCESS_RESULT_CODE, result)
        verify(mockWifiManager, times(1)).removeNetworkSuggestions(listOf(networkSuggestion1))
    }

    @Test
    fun removeNetwork_byBSSID_singleBSSID_failure() {
        // Given
        val networkSuggestion1 = WifiNetworkSuggestion.Builder()
            .setSsid(TEST_SSID1)
            .setBssid(MacAddress.fromString(TEST_BSSID1))
            .setIsAppInteractionRequired(true)
            .build()
        val networkSuggestions = listOf(networkSuggestion1)
        given(mockWifiManager.removeNetworkSuggestions(listOf(networkSuggestion1))).willReturn(
            REMOVE_NETWORK_FAILURE_RESULT_CODE,
        )
        given(mockWifiManager.networkSuggestions).willReturn(networkSuggestions)
        val impl = Android30RemoveNetworkApiImpl(wifiManager = mockWifiManager)

        // When
        val result = impl.removeNetworkByBSSID(TEST_BSSID1)

        // Then
        assertEquals(REMOVE_NETWORK_FAILURE_RESULT_CODE, result)
        verify(mockWifiManager, times(1)).removeNetworkSuggestions(listOf(networkSuggestion1))
    }

    @Test
    fun removeNetwork_byBSSID_multipleBSSIDs_success() {
        // Given
        val networkSuggestion1 = WifiNetworkSuggestion.Builder()
            .setSsid(TEST_SSID1)
            .setBssid(MacAddress.fromString(TEST_BSSID1))
            .setIsAppInteractionRequired(true)
            .build()
        val networkSuggestion2 = WifiNetworkSuggestion.Builder()
            .setSsid(TEST_SSID2)
            .setBssid(MacAddress.fromString(TEST_BSSID2))
            .setIsAppInteractionRequired(true)
            .build()
        val networkSuggestions = listOf(networkSuggestion1, networkSuggestion2)
        given(mockWifiManager.removeNetworkSuggestions(listOf(networkSuggestion2))).willReturn(
            REMOVE_NETWORK_SUCCESS_RESULT_CODE,
        )
        given(mockWifiManager.networkSuggestions).willReturn(networkSuggestions)
        val impl = Android30RemoveNetworkApiImpl(wifiManager = mockWifiManager)

        // When
        val result = impl.removeNetworkByBSSID(TEST_BSSID2)

        // Then
        assertEquals(REMOVE_NETWORK_SUCCESS_RESULT_CODE, result)
        verify(mockWifiManager, times(1)).removeNetworkSuggestions(listOf(networkSuggestion2))
    }

    @Test
    fun removeNetwork_byBSSID_multipleBSSIDs_failure() {
        // Given
        val networkSuggestion1 = WifiNetworkSuggestion.Builder()
            .setSsid(TEST_SSID1)
            .setBssid(MacAddress.fromString(TEST_BSSID1))
            .setIsAppInteractionRequired(true)
            .build()
        val networkSuggestion2 = WifiNetworkSuggestion.Builder()
            .setSsid(TEST_SSID2)
            .setBssid(MacAddress.fromString(TEST_BSSID2))
            .setIsAppInteractionRequired(true)
            .build()
        val networkSuggestions = listOf(networkSuggestion1, networkSuggestion2)
        given(mockWifiManager.removeNetworkSuggestions(listOf(networkSuggestion2))).willReturn(
            REMOVE_NETWORK_FAILURE_RESULT_CODE,
        )
        given(mockWifiManager.networkSuggestions).willReturn(networkSuggestions)
        val impl = Android30RemoveNetworkApiImpl(wifiManager = mockWifiManager)

        // When
        val result = impl.removeNetworkByBSSID(TEST_BSSID2)

        // Then
        assertEquals(REMOVE_NETWORK_FAILURE_RESULT_CODE, result)
        verify(mockWifiManager, times(1)).removeNetworkSuggestions(listOf(networkSuggestion2))
    }

    companion object {
        private const val TEST_SSID1: String = "Test SSID1"
        private const val TEST_SSID2: String = "Test SSID2"

        private const val TEST_BSSID1: String = "01:23:45:67:89:AB"
        private const val TEST_BSSID2: String = "23:45:67:89:AB:CD"

        private const val REMOVE_NETWORK_FAILURE_RESULT_CODE: Int = -1
        private const val REMOVE_NETWORK_SUCCESS_RESULT_CODE: Int = WifiManager.STATUS_NETWORK_SUGGESTIONS_SUCCESS
    }
}
