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
package com.isupatches.android.wisefy.removenetwork.os.adapters

import android.net.wifi.WifiManager
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkRequest
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkResult
import com.isupatches.android.wisefy.removenetwork.os.apis.DefaultRemoveNetworkApi
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

internal class DefaultRemoveNetworkAdapterTest {

    @get:Rule
    var mockitoRule: MockitoRule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS)

    @Mock
    private lateinit var mockWifiManager: WifiManager

    @Mock
    private lateinit var mockApi: DefaultRemoveNetworkApi

    @Test
    fun removeNetwork_bySSID_success() {
        // Given
        given(mockApi.removeNetworkBySSID(TEST_SSID)).willReturn(true)
        val adapter = DefaultRemoveNetworkAdapter(wifiManager = mockWifiManager, api = mockApi)

        // When
        val result = adapter.removeNetwork(RemoveNetworkRequest.SSID(TEST_SSID))

        // Expect
        verify(mockApi, times(1)).removeNetworkBySSID(TEST_SSID)
        assertEquals(RemoveNetworkResult.Success.True, result)
    }

    @Test
    fun removeNetwork_byBSSID_success() {
        // Given
        given(mockApi.removeNetworkByBSSID(TEST_BSSID)).willReturn(true)
        val adapter = DefaultRemoveNetworkAdapter(wifiManager = mockWifiManager, api = mockApi)

        // When
        val result = adapter.removeNetwork(RemoveNetworkRequest.BSSID(TEST_BSSID))

        // Expect
        verify(mockApi, times(1)).removeNetworkByBSSID(TEST_BSSID)
        assertEquals(RemoveNetworkResult.Success.True, result)
    }

    @Test
    fun removeNetwork_bySSID_failure() {
        // Given
        given(mockApi.removeNetworkBySSID(TEST_SSID)).willReturn(false)
        val adapter = DefaultRemoveNetworkAdapter(wifiManager = mockWifiManager, api = mockApi)

        // When
        val result = adapter.removeNetwork(RemoveNetworkRequest.SSID(TEST_SSID))

        // Expect
        verify(mockApi, times(1)).removeNetworkBySSID(TEST_SSID)
        assertEquals(RemoveNetworkResult.Failure.False, result)
    }

    @Test
    fun removeNetwork_byBSSID_failure() {
        // Given
        given(mockApi.removeNetworkByBSSID(TEST_BSSID)).willReturn(false)
        val adapter = DefaultRemoveNetworkAdapter(wifiManager = mockWifiManager, api = mockApi)

        // When
        val result = adapter.removeNetwork(RemoveNetworkRequest.BSSID(TEST_BSSID))

        // Expect
        verify(mockApi, times(1)).removeNetworkByBSSID(TEST_BSSID)
        assertEquals(RemoveNetworkResult.Failure.False, result)
    }

    companion object {
        private const val TEST_SSID: String = "Test SSID"
        private const val TEST_BSSID: String = "Test:BSSID"
    }
}
