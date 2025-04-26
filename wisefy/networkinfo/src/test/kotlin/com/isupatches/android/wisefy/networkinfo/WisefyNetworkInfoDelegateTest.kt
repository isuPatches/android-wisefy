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
package com.isupatches.android.wisefy.networkinfo

import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import com.isupatches.android.wisefy.core.entities.NetworkConnectionStatus
import com.isupatches.android.wisefy.networkinfo.callbacks.GetCurrentNetworkCallbacks
import com.isupatches.android.wisefy.networkinfo.callbacks.GetNetworkConnectionStatusCallbacks
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkQuery
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkResult
import com.isupatches.android.wisefy.networkinfo.entities.GetNetworkConnectionStatusQuery
import com.isupatches.android.wisefy.networkinfo.entities.GetNetworkConnectionStatusResult
import com.isupatches.android.wisefy.networkinfo.entities.NetworkConnectionStatusData
import com.isupatches.android.wisefy.networkinfo.entities.NetworkData
import com.isupatches.android.wisefy.testsupport.anyNonNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.verifyNoInteractions
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.mockito.quality.Strictness

@ExperimentalCoroutinesApi
internal class WisefyNetworkInfoDelegateTest {

    @get:Rule
    var mockitoRule: MockitoRule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS)

    @Mock
    private lateinit var mockConnectivityManager: ConnectivityManager

    @Mock
    private lateinit var mockWifiManager: WifiManager

    @Mock
    private lateinit var mockAdapter: NetworkInfoApi

    @Mock
    private lateinit var mockGetNetworkConnectionStatusCallbacks: GetNetworkConnectionStatusCallbacks

    @Mock
    private lateinit var mockGetCurrentNetworkCallbacks: GetCurrentNetworkCallbacks

    private val testDispatcher = UnconfinedTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    private val testException = IllegalStateException("test exception")

    @Test
    fun getCurrentNetwork_sync_success() {
        // Given
        val networkData = NetworkData(
            network = null,
            connectionInfo = null,
            capabilities = null,
            linkProperties = null,
        )
        given(mockAdapter.getCurrentNetwork(anyNonNull())).willReturn(
            GetCurrentNetworkResult(value = networkData)
        )
        val delegate = WisefyNetworkInfoDelegate(
            connectivityManager = mockConnectivityManager,
            wifiManager = mockWifiManager,
            networkConnectionStatusProvider = { NetworkConnectionStatus.UNAVAILABLE },
            scope = testScope,
            networkConnectionMutex = Mutex(),
            mainDispatcher = testDispatcher,
            adapter = mockAdapter
        )
        val query = GetCurrentNetworkQuery()

        // When
        val result = delegate.getCurrentNetwork(query)

        // Then
        assertEquals(networkData, result.value)
        verify(mockAdapter, times(1)).getCurrentNetwork(query)
    }

    @Test
    fun getCurrentNetwork_async_success() {
        // Given
        val networkData = NetworkData(
            network = null,
            connectionInfo = null,
            capabilities = null,
            linkProperties = null,
        )
        given(mockAdapter.getCurrentNetwork(anyNonNull())).willReturn(GetCurrentNetworkResult(value = networkData))
        val delegate = WisefyNetworkInfoDelegate(
            connectivityManager = mockConnectivityManager,
            wifiManager = mockWifiManager,
            networkConnectionStatusProvider = { NetworkConnectionStatus.UNAVAILABLE },
            scope = testScope,
            networkConnectionMutex = Mutex(),
            mainDispatcher = testDispatcher,
            adapter = mockAdapter
        )
        val query = GetCurrentNetworkQuery()

        // When
        delegate.getCurrentNetwork(query, callbacks = mockGetCurrentNetworkCallbacks)

        // Then
        verify(mockAdapter, times(1)).getCurrentNetwork(query)
        verify(
            mockGetCurrentNetworkCallbacks,
            times(1),
        ).onCurrentNetworkRetrieved(networkData)
    }

    @Test
    fun getCurrentNetwork_async_success_noCallbacks() {
        // Given
        val networkData = NetworkData(
            network = null,
            connectionInfo = null,
            capabilities = null,
            linkProperties = null,
        )
        given(mockAdapter.getCurrentNetwork(anyNonNull())).willReturn(GetCurrentNetworkResult(value = networkData))
        val delegate = WisefyNetworkInfoDelegate(
            connectivityManager = mockConnectivityManager,
            wifiManager = mockWifiManager,
            networkConnectionStatusProvider = { NetworkConnectionStatus.UNAVAILABLE },
            scope = testScope,
            networkConnectionMutex = Mutex(),
            mainDispatcher = testDispatcher,
            adapter = mockAdapter
        )
        val query = GetCurrentNetworkQuery()

        // When
        delegate.getCurrentNetwork(query, callbacks = null)

        // Then
        verify(mockAdapter, times(1)).getCurrentNetwork(query)
        verifyNoInteractions(mockGetNetworkConnectionStatusCallbacks)
    }

    @Test
    fun getCurrentNetwork_async_failure() {
        // Given
        given(mockAdapter.getCurrentNetwork(anyNonNull())).willThrow(testException)
        val delegate = WisefyNetworkInfoDelegate(
            connectivityManager = mockConnectivityManager,
            wifiManager = mockWifiManager,
            networkConnectionStatusProvider = { NetworkConnectionStatus.UNAVAILABLE },
            scope = testScope,
            networkConnectionMutex = Mutex(),
            mainDispatcher = testDispatcher,
            adapter = mockAdapter
        )
        val query = GetCurrentNetworkQuery()

        // When
        delegate.getCurrentNetwork(query, callbacks = mockGetCurrentNetworkCallbacks)

        // Then
        verify(mockAdapter, times(1)).getCurrentNetwork(query)
        verify(
            mockGetCurrentNetworkCallbacks,
            times(1),
        ).onWisefyAsyncFailure(anyNonNull())
    }

    @Test
    fun getCurrentNetwork_async_failure_noCallbacks() {
        // Given
        given(mockAdapter.getCurrentNetwork(anyNonNull())).willThrow(testException)
        val delegate = WisefyNetworkInfoDelegate(
            connectivityManager = mockConnectivityManager,
            wifiManager = mockWifiManager,
            networkConnectionStatusProvider = { NetworkConnectionStatus.UNAVAILABLE },
            scope = testScope,
            networkConnectionMutex = Mutex(),
            mainDispatcher = testDispatcher,
            adapter = mockAdapter
        )
        val query = GetCurrentNetworkQuery()

        // When
        delegate.getCurrentNetwork(query, callbacks = null)

        // Then
        verify(mockAdapter, times(1)).getCurrentNetwork(query)
        verifyNoInteractions(mockGetCurrentNetworkCallbacks)
    }

    @Test
    fun getNetworkConnectionStatus_sync_success() {
        // Given
        val networkStatusData = NetworkConnectionStatusData(
            isConnected = true,
            isConnectedToMobileNetwork = true,
            isConnectedToWifiNetwork = true,
            isRoaming = false,
            ssidOfNetworkConnectedTo = "Test SSID",
            bssidOfNetworkConnectedTo = null,
            ip = null
        )
        given(mockAdapter.getNetworkConnectionStatus(anyNonNull())).willReturn(
            GetNetworkConnectionStatusResult(value = networkStatusData)
        )
        val delegate = WisefyNetworkInfoDelegate(
            connectivityManager = mockConnectivityManager,
            wifiManager = mockWifiManager,
            networkConnectionStatusProvider = { NetworkConnectionStatus.UNAVAILABLE },
            scope = testScope,
            networkConnectionMutex = Mutex(),
            mainDispatcher = testDispatcher,
            adapter = mockAdapter
        )
        val query = GetNetworkConnectionStatusQuery()

        // When
        val result = delegate.getNetworkConnectionStatus(query)

        // Then
        assertEquals(networkStatusData, result.value)
        verify(mockAdapter, times(1)).getNetworkConnectionStatus(query)
    }

    @Test
    fun getNetworkConnectionStatus_async_success() {
        // Given
        val networkStatusData = NetworkConnectionStatusData(
            isConnected = true,
            isConnectedToMobileNetwork = true,
            isConnectedToWifiNetwork = true,
            isRoaming = false,
            ssidOfNetworkConnectedTo = "Test SSID",
            bssidOfNetworkConnectedTo = null,
            ip = null
        )
        given(mockAdapter.getNetworkConnectionStatus(anyNonNull())).willReturn(
            GetNetworkConnectionStatusResult(value = networkStatusData)
        )
        val delegate = WisefyNetworkInfoDelegate(
            connectivityManager = mockConnectivityManager,
            wifiManager = mockWifiManager,
            networkConnectionStatusProvider = { NetworkConnectionStatus.UNAVAILABLE },
            scope = testScope,
            networkConnectionMutex = Mutex(),
            mainDispatcher = testDispatcher,
            adapter = mockAdapter
        )
        val query = GetNetworkConnectionStatusQuery()

        // When
        delegate.getNetworkConnectionStatus(query, callbacks = mockGetNetworkConnectionStatusCallbacks)

        // Then
        verify(mockAdapter, times(1)).getNetworkConnectionStatus(query)
        verify(
            mockGetNetworkConnectionStatusCallbacks,
            times(1),
        ).onDeviceNetworkConnectionStatusRetrieved(networkStatusData)
    }

    @Test
    fun getNetworkConnectionStatus_async_success_noCallbacks() {
        // Given
        val networkStatusData = NetworkConnectionStatusData(
            isConnected = true,
            isConnectedToMobileNetwork = true,
            isConnectedToWifiNetwork = true,
            isRoaming = false,
            ssidOfNetworkConnectedTo = "Test SSID",
            bssidOfNetworkConnectedTo = null,
            ip = null
        )
        given(mockAdapter.getNetworkConnectionStatus(anyNonNull())).willReturn(
            GetNetworkConnectionStatusResult(value = networkStatusData)
        )
        val delegate = WisefyNetworkInfoDelegate(
            connectivityManager = mockConnectivityManager,
            wifiManager = mockWifiManager,
            networkConnectionStatusProvider = { NetworkConnectionStatus.UNAVAILABLE },
            scope = testScope,
            networkConnectionMutex = Mutex(),
            mainDispatcher = testDispatcher,
            adapter = mockAdapter
        )
        val query = GetNetworkConnectionStatusQuery()

        // When
        delegate.getNetworkConnectionStatus(query, callbacks = null)

        // Then
        verify(mockAdapter, times(1)).getNetworkConnectionStatus(query)
        verifyNoInteractions(mockGetNetworkConnectionStatusCallbacks)
    }

    @Test
    fun getNetworkConnectionStatus_async_failure() {
        // Given
        given(mockAdapter.getNetworkConnectionStatus(anyNonNull())).willThrow(testException)
        val delegate = WisefyNetworkInfoDelegate(
            connectivityManager = mockConnectivityManager,
            wifiManager = mockWifiManager,
            networkConnectionStatusProvider = { NetworkConnectionStatus.UNAVAILABLE },
            scope = testScope,
            networkConnectionMutex = Mutex(),
            mainDispatcher = testDispatcher,
            adapter = mockAdapter
        )
        val query = GetNetworkConnectionStatusQuery()

        // When
        delegate.getNetworkConnectionStatus(query, callbacks = mockGetNetworkConnectionStatusCallbacks)

        // Then
        verify(mockAdapter, times(1)).getNetworkConnectionStatus(query)
        verify(
            mockGetNetworkConnectionStatusCallbacks,
            times(1),
        ).onWisefyAsyncFailure(anyNonNull())
    }

    @Test
    fun getNetworkConnectionStatus_async_failure_noCallbacks() {
        // Given
        given(mockAdapter.getNetworkConnectionStatus(anyNonNull())).willThrow(testException)
        val delegate = WisefyNetworkInfoDelegate(
            connectivityManager = mockConnectivityManager,
            wifiManager = mockWifiManager,
            networkConnectionStatusProvider = { NetworkConnectionStatus.UNAVAILABLE },
            scope = testScope,
            networkConnectionMutex = Mutex(),
            mainDispatcher = testDispatcher,
            adapter = mockAdapter
        )
        val query = GetNetworkConnectionStatusQuery()

        // When
        delegate.getNetworkConnectionStatus(query, callbacks = null)

        // Then
        verify(mockAdapter, times(1)).getNetworkConnectionStatus(query)
        verifyNoInteractions(mockGetNetworkConnectionStatusCallbacks)
    }
}
