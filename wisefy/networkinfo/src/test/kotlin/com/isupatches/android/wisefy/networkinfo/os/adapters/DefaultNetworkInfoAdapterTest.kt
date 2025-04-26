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
package com.isupatches.android.wisefy.networkinfo.os.adapters

import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import com.isupatches.android.wisefy.core.entities.NetworkConnectionStatus
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkQuery
import com.isupatches.android.wisefy.networkinfo.os.apis.DefaultNetworkInfoApi
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.mockito.quality.Strictness

internal class DefaultNetworkInfoAdapterTest {

    @get:Rule
    var mockitoRule: MockitoRule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS)

    @Mock
    private lateinit var mockConnectivityManager: ConnectivityManager

    @Mock
    private lateinit var mockWifiManager: WifiManager

    @Mock
    private lateinit var mockNetworkInfoApi: DefaultNetworkInfoApi

    @Test
    fun getCurrentNetwork_noNetwork() {
        // Given
        val adapter = DefaultNetworkInfoAdapter(
            connectivityManager = mockConnectivityManager,
            wifiManager = mockWifiManager,
            networkConnectionStatusProvider = { NetworkConnectionStatus.AVAILABLE },
            isAtLeastAndroidP = true,
            isAtLeastAndroidS = true,
            api = mockNetworkInfoApi,
        )
        val query = GetCurrentNetworkQuery()

        // When
        adapter.getCurrentNetwork(query)
    }

    @Test
    fun getCurrentNetwork_network_withNetworkCapabilities_andNoLinkProperties() {
        // Given
        val adapter = DefaultNetworkInfoAdapter(
            connectivityManager = mockConnectivityManager,
            wifiManager = mockWifiManager,
            networkConnectionStatusProvider = { NetworkConnectionStatus.AVAILABLE },
            isAtLeastAndroidP = true,
            isAtLeastAndroidS = true,
            api = mockNetworkInfoApi,
        )
        val query = GetCurrentNetworkQuery()

        // When
        adapter.getCurrentNetwork(query)
    }

    @Test
    fun getCurrentNetwork_network_withNetworkCapabilities_butNoLinkProperties() {
        // Given
        val adapter = DefaultNetworkInfoAdapter(
            connectivityManager = mockConnectivityManager,
            wifiManager = mockWifiManager,
            networkConnectionStatusProvider = { NetworkConnectionStatus.AVAILABLE },
            isAtLeastAndroidP = true,
            isAtLeastAndroidS = true,
            api = mockNetworkInfoApi,
        )
        val query = GetCurrentNetworkQuery()

        // When
        adapter.getCurrentNetwork(query)
    }

    @Test
    fun getCurrentNetwork_network_withNoNetworkCapabilities_butWithLinkProperties() {
        // Given
        val adapter = DefaultNetworkInfoAdapter(
            connectivityManager = mockConnectivityManager,
            wifiManager = mockWifiManager,
            networkConnectionStatusProvider = { NetworkConnectionStatus.AVAILABLE },
            isAtLeastAndroidP = true,
            isAtLeastAndroidS = true,
            api = mockNetworkInfoApi,
        )
        val query = GetCurrentNetworkQuery()

        // When
        adapter.getCurrentNetwork(query)
    }

    @Test
    fun getCurrentNetwork_network_withNetworkCapabilities_andLinkProperties() {
        // Given
        val adapter = DefaultNetworkInfoAdapter(
            connectivityManager = mockConnectivityManager,
            wifiManager = mockWifiManager,
            networkConnectionStatusProvider = { NetworkConnectionStatus.AVAILABLE },
            isAtLeastAndroidP = true,
            isAtLeastAndroidS = true,
            api = mockNetworkInfoApi,
        )
        val query = GetCurrentNetworkQuery()

        // When
        adapter.getCurrentNetwork(query)
    }
}
