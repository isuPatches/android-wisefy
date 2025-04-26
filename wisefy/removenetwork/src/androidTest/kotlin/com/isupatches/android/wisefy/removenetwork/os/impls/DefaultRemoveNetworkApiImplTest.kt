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
@file:Suppress("Deprecation")

package com.isupatches.android.wisefy.removenetwork.os.impls

import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiManager
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.mockito.quality.Strictness

internal class DefaultRemoveNetworkApiImplTest {

    @get:Rule
    var mockitoRule: MockitoRule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS)

    @Mock
    private lateinit var mockWifiManager: WifiManager

    @Test
    fun removeNetwork_bySSID_singleSSID_success() {
        // Given
        val networkConfiguration1 = WifiConfiguration().apply {
            networkId = TEST_NETWORK_ID1
            SSID = TEST_SSID1
        }
        val networkConfigurations = listOf(networkConfiguration1)
        given(mockWifiManager.removeNetwork(TEST_NETWORK_ID1)).willReturn(true)
        given(mockWifiManager.configuredNetworks).willReturn(networkConfigurations)
        val impl = DefaultRemoveNetworkApiImpl(wifiManager = mockWifiManager)

        // When
        val result = impl.removeNetworkBySSID(TEST_SSID1)

        // Then
        assertTrue(result)
        verify(mockWifiManager, times(1)).removeNetwork(TEST_NETWORK_ID1)
    }

    @Test
    fun removeNetwork_bySSID_singleSSID_failure() {
        // Given
        val networkConfiguration1 = WifiConfiguration().apply {
            networkId = TEST_NETWORK_ID1
            SSID = TEST_SSID1
        }
        val networkConfigurations = listOf(networkConfiguration1)
        given(mockWifiManager.removeNetwork(TEST_NETWORK_ID1)).willReturn(false)
        given(mockWifiManager.configuredNetworks).willReturn(networkConfigurations)
        val impl = DefaultRemoveNetworkApiImpl(wifiManager = mockWifiManager)

        // When
        val result = impl.removeNetworkBySSID(TEST_SSID1)

        // Then
        assertFalse(result)
        verify(mockWifiManager, times(1)).removeNetwork(TEST_NETWORK_ID1)
    }

    @Test
    fun removeNetwork_bySSID_multipleSSIDs_success() {
        // Given
        val networkConfiguration1 = WifiConfiguration().apply {
            networkId = TEST_NETWORK_ID1
            SSID = TEST_SSID1
        }
        val networkConfiguration2 = WifiConfiguration().apply {
            networkId = TEST_NETWORK_ID2
            SSID = TEST_SSID2
        }
        val networkConfigurations = listOf(networkConfiguration1, networkConfiguration2)
        given(mockWifiManager.removeNetwork(TEST_NETWORK_ID2)).willReturn(true)
        given(mockWifiManager.configuredNetworks).willReturn(networkConfigurations)
        val impl = DefaultRemoveNetworkApiImpl(wifiManager = mockWifiManager)

        // When
        val result = impl.removeNetworkBySSID(TEST_SSID2)

        // Then
        assertTrue(result)
        verify(mockWifiManager, times(1)).removeNetwork(TEST_NETWORK_ID2)
    }

    @Test
    fun removeNetwork_bySSID_multipleSSIDs_failure() {
        // Given
        val networkConfiguration1 = WifiConfiguration().apply {
            networkId = TEST_NETWORK_ID1
            SSID = TEST_SSID1
        }
        val networkConfiguration2 = WifiConfiguration().apply {
            networkId = TEST_NETWORK_ID2
            SSID = TEST_SSID2
        }
        val networkConfigurations = listOf(networkConfiguration1, networkConfiguration2)
        given(mockWifiManager.removeNetwork(TEST_NETWORK_ID2)).willReturn(false)
        given(mockWifiManager.configuredNetworks).willReturn(networkConfigurations)
        val impl = DefaultRemoveNetworkApiImpl(wifiManager = mockWifiManager)

        // When
        val result = impl.removeNetworkBySSID(TEST_SSID2)

        // Then
        assertFalse(result)
        verify(mockWifiManager, times(1)).removeNetwork(TEST_NETWORK_ID2)
    }

    @Test
    fun removeNetwork_byBSSID_singleBSSID_success() {
        // Given
        val networkConfiguration1 = WifiConfiguration().apply {
            networkId = TEST_NETWORK_ID1
            SSID = TEST_SSID1
            BSSID = TEST_BSSID1
        }
        val networkConfigurations = listOf(networkConfiguration1)
        given(mockWifiManager.removeNetwork(TEST_NETWORK_ID1)).willReturn(true)
        given(mockWifiManager.configuredNetworks).willReturn(networkConfigurations)
        val impl = DefaultRemoveNetworkApiImpl(wifiManager = mockWifiManager)

        // When
        val result = impl.removeNetworkByBSSID(TEST_BSSID1)

        // Then
        assertTrue(result)
        verify(mockWifiManager, times(1)).removeNetwork(TEST_NETWORK_ID1)
    }

    @Test
    fun removeNetwork_byBSSID_singleBSSID_failure() {
        // Given
        val networkConfiguration1 = WifiConfiguration().apply {
            networkId = TEST_NETWORK_ID1
            SSID = TEST_SSID1
            BSSID = TEST_BSSID1
        }
        val networkConfigurations = listOf(networkConfiguration1)
        given(mockWifiManager.removeNetwork(TEST_NETWORK_ID1)).willReturn(false)
        given(mockWifiManager.configuredNetworks).willReturn(networkConfigurations)
        val impl = DefaultRemoveNetworkApiImpl(wifiManager = mockWifiManager)

        // When
        val result = impl.removeNetworkByBSSID(TEST_BSSID1)

        // Then
        assertFalse(result)
        verify(mockWifiManager, times(1)).removeNetwork(TEST_NETWORK_ID1)
    }

    @Test
    fun removeNetwork_byBSSID_multipleBSSIDs_success() {
        // Given
        val networkConfiguration1 = WifiConfiguration().apply {
            networkId = TEST_NETWORK_ID1
            SSID = TEST_SSID1
            BSSID = TEST_BSSID1
        }
        val networkConfiguration2 = WifiConfiguration().apply {
            networkId = TEST_NETWORK_ID2
            SSID = TEST_SSID2
            BSSID = TEST_BSSID2
        }
        val networkConfigurations = listOf(networkConfiguration1, networkConfiguration2)
        given(mockWifiManager.removeNetwork(TEST_NETWORK_ID2)).willReturn(true)
        given(mockWifiManager.configuredNetworks).willReturn(networkConfigurations)
        val impl = DefaultRemoveNetworkApiImpl(wifiManager = mockWifiManager)

        // When
        val result = impl.removeNetworkByBSSID(TEST_BSSID2)

        // Then
        assertTrue(result)
        verify(mockWifiManager, times(1)).removeNetwork(TEST_NETWORK_ID2)
    }

    @Test
    fun removeNetwork_byBSSID_multipleBSSIDs_failure() {
        // Given
        val networkConfiguration1 = WifiConfiguration().apply {
            networkId = TEST_NETWORK_ID1
            SSID = TEST_SSID1
            BSSID = TEST_BSSID1
        }
        val networkConfiguration2 = WifiConfiguration().apply {
            networkId = TEST_NETWORK_ID2
            SSID = TEST_SSID2
            BSSID = TEST_BSSID2
        }
        val networkConfigurations = listOf(networkConfiguration1, networkConfiguration2)
        given(mockWifiManager.removeNetwork(TEST_NETWORK_ID2)).willReturn(false)
        given(mockWifiManager.configuredNetworks).willReturn(networkConfigurations)
        val impl = DefaultRemoveNetworkApiImpl(wifiManager = mockWifiManager)

        // When
        val result = impl.removeNetworkByBSSID(TEST_BSSID2)

        // Then
        assertFalse(result)
        verify(mockWifiManager, times(1)).removeNetwork(TEST_NETWORK_ID2)
    }

    companion object {
        private const val TEST_SSID1: String = "Test SSID1"
        private const val TEST_SSID2: String = "Test SSID2"

        private const val TEST_BSSID1: String = "01:23:45:67:89:AB"
        private const val TEST_BSSID2: String = "23:45:67:89:AB:CD"

        private const val TEST_NETWORK_ID1: Int = 1
        private const val TEST_NETWORK_ID2: Int = 2
    }
}
