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
package com.isupatches.android.wisefy.removenetwork

import android.net.wifi.WifiManager
import com.isupatches.android.wisefy.removenetwork.callbacks.RemoveNetworkCallbacks
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkRequest
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkResult
import com.isupatches.android.wisefy.testsupport.anyNonNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.never
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.mockito.quality.Strictness

@ExperimentalCoroutinesApi
internal class WisefyRemoveNetworkDelegateTest {

    @get:Rule
    var mockitoRule: MockitoRule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS)

    @Mock
    private lateinit var mockWifiManager: WifiManager

    @Mock
    private lateinit var mockAdapter: RemoveNetworkApi

    @Mock
    private lateinit var mockRemoveNetworkCallbacks: RemoveNetworkCallbacks

    private val testDispatcher = UnconfinedTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @Test
    fun removeNetwork_bySSID_success_resultCode() {
        // Given
        given(mockAdapter.removeNetwork(anyNonNull())).willReturn(
            RemoveNetworkResult.Success.ResultCode(REMOVE_NETWORK_SUCCESS_RESULT_CODE),
        )
        val delegate = WisefyRemoveNetworkDelegate(
            wifiManager = mockWifiManager,
            scope = testScope,
            mainDispatcher = testDispatcher,
            savedNetworkMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = RemoveNetworkRequest.SSID(TEST_SSID)

        // When
        val result = delegate.removeNetwork(request)

        // Then
        verify(mockAdapter, times(1)).removeNetwork(request)
        assertEquals(
            RemoveNetworkResult.Success.ResultCode(REMOVE_NETWORK_SUCCESS_RESULT_CODE),
            result,
        )
    }

    @Test
    fun removeNetwork_bySSID_success_boolean() {
        // Given
        given(mockAdapter.removeNetwork(anyNonNull())).willReturn(RemoveNetworkResult.Success.True)
        val delegate = WisefyRemoveNetworkDelegate(
            wifiManager = mockWifiManager,
            scope = testScope,
            mainDispatcher = testDispatcher,
            savedNetworkMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = RemoveNetworkRequest.SSID(TEST_SSID)

        // When
        val result = delegate.removeNetwork(request)

        // Then
        verify(mockAdapter, times(1)).removeNetwork(request)
        assertEquals(RemoveNetworkResult.Success.True, result)
    }

    @Test
    fun removeNetwork_bySSID_failure_resultCode() {
        // Given
        given(mockAdapter.removeNetwork(anyNonNull())).willReturn(
            RemoveNetworkResult.Failure.ResultCode(REMOVE_NETWORK_FAILURE_RESULT_CODE),
        )
        val delegate = WisefyRemoveNetworkDelegate(
            wifiManager = mockWifiManager,
            scope = testScope,
            mainDispatcher = testDispatcher,
            savedNetworkMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = RemoveNetworkRequest.SSID(TEST_SSID)

        // When
        val result = delegate.removeNetwork(request)

        // Then
        verify(mockAdapter, times(1)).removeNetwork(request)
        assertEquals(
            RemoveNetworkResult.Failure.ResultCode(REMOVE_NETWORK_FAILURE_RESULT_CODE),
            result,
        )
    }

    @Test
    fun removeNetwork_bySSID_failure_boolean() {
        // Given
        given(mockAdapter.removeNetwork(anyNonNull())).willReturn(RemoveNetworkResult.Failure.False)
        val delegate = WisefyRemoveNetworkDelegate(
            wifiManager = mockWifiManager,
            scope = testScope,
            mainDispatcher = testDispatcher,
            savedNetworkMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = RemoveNetworkRequest.SSID(TEST_SSID)

        // When
        val result = delegate.removeNetwork(request)

        // Then
        verify(mockAdapter, times(1)).removeNetwork(request)
        assertEquals(RemoveNetworkResult.Failure.False, result)
    }

    @Test
    fun removeNetwork_byBSSID_success_resultCode() {
        // Given
        given(mockAdapter.removeNetwork(anyNonNull())).willReturn(
            RemoveNetworkResult.Success.ResultCode(REMOVE_NETWORK_SUCCESS_RESULT_CODE),
        )
        val delegate = WisefyRemoveNetworkDelegate(
            wifiManager = mockWifiManager,
            scope = testScope,
            mainDispatcher = testDispatcher,
            savedNetworkMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = RemoveNetworkRequest.BSSID(TEST_BSSID)

        // When
        val result = delegate.removeNetwork(request)

        // Then
        verify(mockAdapter, times(1)).removeNetwork(request)
        assertEquals(
            RemoveNetworkResult.Success.ResultCode(REMOVE_NETWORK_SUCCESS_RESULT_CODE),
            result,
        )
    }

    @Test
    fun removeNetwork_byBSSID_success_boolean() {
        // Given
        given(mockAdapter.removeNetwork(anyNonNull())).willReturn(RemoveNetworkResult.Success.True)
        val delegate = WisefyRemoveNetworkDelegate(
            wifiManager = mockWifiManager,
            scope = testScope,
            mainDispatcher = testDispatcher,
            savedNetworkMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = RemoveNetworkRequest.BSSID(TEST_BSSID)

        // When
        val result = delegate.removeNetwork(request)

        // Then
        verify(mockAdapter, times(1)).removeNetwork(request)
        assertEquals(RemoveNetworkResult.Success.True, result)
    }

    @Test
    fun removeNetwork_byBSSID_failure_resultCode() {
        // Given
        given(mockAdapter.removeNetwork(anyNonNull())).willReturn(
            RemoveNetworkResult.Failure.ResultCode(REMOVE_NETWORK_FAILURE_RESULT_CODE),
        )
        val delegate = WisefyRemoveNetworkDelegate(
            wifiManager = mockWifiManager,
            scope = testScope,
            mainDispatcher = testDispatcher,
            savedNetworkMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = RemoveNetworkRequest.BSSID(TEST_BSSID)

        // When
        val result = delegate.removeNetwork(request)

        // Then
        verify(mockAdapter, times(1)).removeNetwork(request)
        assertEquals(
            RemoveNetworkResult.Failure.ResultCode(REMOVE_NETWORK_FAILURE_RESULT_CODE),
            result,
        )
    }

    @Test
    fun removeNetwork_byBSSID_failure_boolean() {
        // Given
        given(mockAdapter.removeNetwork(anyNonNull())).willReturn(RemoveNetworkResult.Failure.False)
        val delegate = WisefyRemoveNetworkDelegate(
            wifiManager = mockWifiManager,
            scope = testScope,
            mainDispatcher = testDispatcher,
            savedNetworkMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = RemoveNetworkRequest.BSSID(TEST_BSSID)

        // When
        val result = delegate.removeNetwork(request)

        // Then
        verify(mockAdapter, times(1)).removeNetwork(request)
        assertEquals(RemoveNetworkResult.Failure.False, result)
    }

    @Test
    fun removeNetwork_bySSID_async_success_resultCode() {
        // Given
        given(mockAdapter.removeNetwork(anyNonNull())).willReturn(
            RemoveNetworkResult.Success.ResultCode(REMOVE_NETWORK_SUCCESS_RESULT_CODE),
        )
        val delegate = WisefyRemoveNetworkDelegate(
            wifiManager = mockWifiManager,
            scope = testScope,
            mainDispatcher = testDispatcher,
            savedNetworkMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = RemoveNetworkRequest.SSID(TEST_SSID)

        // When
        delegate.removeNetwork(request, mockRemoveNetworkCallbacks)

        // Then
        verify(mockAdapter, times(1)).removeNetwork(request)
        verify(mockRemoveNetworkCallbacks, times(1)).onSuccessRemovingNetwork(
            RemoveNetworkResult.Success.ResultCode(REMOVE_NETWORK_SUCCESS_RESULT_CODE),
        )
    }

    @Test
    fun removeNetwork_bySSID_async_success_boolean() {
        // Given
        given(mockAdapter.removeNetwork(anyNonNull())).willReturn(RemoveNetworkResult.Success.True)
        val delegate = WisefyRemoveNetworkDelegate(
            wifiManager = mockWifiManager,
            scope = testScope,
            mainDispatcher = testDispatcher,
            savedNetworkMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = RemoveNetworkRequest.SSID(TEST_SSID)

        // When
        delegate.removeNetwork(request, mockRemoveNetworkCallbacks)

        // Then
        verify(mockAdapter, times(1)).removeNetwork(request)
        verify(mockRemoveNetworkCallbacks, times(1)).onSuccessRemovingNetwork(
            RemoveNetworkResult.Success.True,
        )
    }

    @Test
    fun removeNetwork_bySSID_async_failure_resultCode() {
        // Given
        given(mockAdapter.removeNetwork(anyNonNull())).willReturn(
            RemoveNetworkResult.Failure.ResultCode(REMOVE_NETWORK_FAILURE_RESULT_CODE),
        )
        val delegate = WisefyRemoveNetworkDelegate(
            wifiManager = mockWifiManager,
            scope = testScope,
            mainDispatcher = testDispatcher,
            savedNetworkMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = RemoveNetworkRequest.SSID(TEST_SSID)

        // When
        delegate.removeNetwork(request, mockRemoveNetworkCallbacks)

        // Then
        verify(mockAdapter, times(1)).removeNetwork(request)
        verify(mockRemoveNetworkCallbacks, times(1)).onFailureRemovingNetwork(
            RemoveNetworkResult.Failure.ResultCode(REMOVE_NETWORK_FAILURE_RESULT_CODE),
        )
    }

    @Test
    fun removeNetwork_bySSID_async_failure_boolean() {
        // Given
        given(mockAdapter.removeNetwork(anyNonNull())).willReturn(RemoveNetworkResult.Failure.False)
        val delegate = WisefyRemoveNetworkDelegate(
            wifiManager = mockWifiManager,
            scope = testScope,
            mainDispatcher = testDispatcher,
            savedNetworkMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = RemoveNetworkRequest.SSID(TEST_SSID)

        // When
        delegate.removeNetwork(request, mockRemoveNetworkCallbacks)

        // Then
        verify(mockAdapter, times(1)).removeNetwork(request)
        verify(mockRemoveNetworkCallbacks, times(1)).onFailureRemovingNetwork(
            RemoveNetworkResult.Failure.False,
        )
    }

    @Test
    fun removeNetwork_bySSID_async_failure_wisefyException() {
        // Given
        given(mockAdapter.removeNetwork(anyNonNull())).willThrow(IllegalStateException("test exception"))
        val delegate = WisefyRemoveNetworkDelegate(
            wifiManager = mockWifiManager,
            scope = testScope,
            mainDispatcher = testDispatcher,
            savedNetworkMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = RemoveNetworkRequest.SSID(TEST_SSID)

        // When
        delegate.removeNetwork(request, mockRemoveNetworkCallbacks)

        // Then
        verify(mockAdapter, times(1)).removeNetwork(request)
        verify(mockRemoveNetworkCallbacks, times(1)).onWisefyAsyncFailure(anyNonNull())
    }

    @Test
    fun removeNetwork_bySSID_async_success_resultCode_noCallbacks() {
        // Given
        given(mockAdapter.removeNetwork(anyNonNull())).willReturn(
            RemoveNetworkResult.Success.ResultCode(REMOVE_NETWORK_SUCCESS_RESULT_CODE),
        )
        val delegate = WisefyRemoveNetworkDelegate(
            wifiManager = mockWifiManager,
            scope = testScope,
            mainDispatcher = testDispatcher,
            savedNetworkMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = RemoveNetworkRequest.SSID(TEST_SSID)

        // When
        delegate.removeNetwork(request, callbacks = null)

        // Then
        verify(mockAdapter, times(1)).removeNetwork(request)
        verify(mockRemoveNetworkCallbacks, never()).onWisefyAsyncFailure(anyNonNull())
    }

    @Test
    fun removeNetwork_bySSID_async_success_boolean_noCallbacks() {
        // Given
        given(mockAdapter.removeNetwork(anyNonNull())).willReturn(RemoveNetworkResult.Success.True)
        val delegate = WisefyRemoveNetworkDelegate(
            wifiManager = mockWifiManager,
            scope = testScope,
            mainDispatcher = testDispatcher,
            savedNetworkMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = RemoveNetworkRequest.SSID(TEST_SSID)

        // When
        delegate.removeNetwork(request, callbacks = null)

        // Then
        verify(mockAdapter, times(1)).removeNetwork(request)
        verify(mockRemoveNetworkCallbacks, never()).onWisefyAsyncFailure(anyNonNull())
    }

    @Test
    fun removeNetwork_bySSID_async_failure_resultCode_noCallbacks() {
        // Given
        given(mockAdapter.removeNetwork(anyNonNull())).willReturn(
            RemoveNetworkResult.Failure.ResultCode(REMOVE_NETWORK_FAILURE_RESULT_CODE),
        )
        val delegate = WisefyRemoveNetworkDelegate(
            wifiManager = mockWifiManager,
            scope = testScope,
            mainDispatcher = testDispatcher,
            savedNetworkMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = RemoveNetworkRequest.SSID(TEST_SSID)

        // When
        delegate.removeNetwork(request, callbacks = null)

        // Then
        verify(mockAdapter, times(1)).removeNetwork(request)
        verify(mockRemoveNetworkCallbacks, never()).onWisefyAsyncFailure(anyNonNull())
    }

    @Test
    fun removeNetwork_bySSID_async_failure_boolean_noCallbacks() {
        // Given
        given(mockAdapter.removeNetwork(anyNonNull())).willReturn(RemoveNetworkResult.Failure.False)
        val delegate = WisefyRemoveNetworkDelegate(
            wifiManager = mockWifiManager,
            scope = testScope,
            mainDispatcher = testDispatcher,
            savedNetworkMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = RemoveNetworkRequest.SSID(TEST_SSID)

        // When
        delegate.removeNetwork(request, callbacks = null)

        // Then
        verify(mockAdapter, times(1)).removeNetwork(request)
        verify(mockRemoveNetworkCallbacks, never()).onWisefyAsyncFailure(anyNonNull())
    }

    @Test
    fun removeNetwork_bySSID_async_failure_wisefyException_noCallbacks() {
        // Given
        given(mockAdapter.removeNetwork(anyNonNull())).willThrow(IllegalStateException("test exception"))
        val delegate = WisefyRemoveNetworkDelegate(
            wifiManager = mockWifiManager,
            scope = testScope,
            mainDispatcher = testDispatcher,
            savedNetworkMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = RemoveNetworkRequest.SSID(TEST_SSID)

        // When
        delegate.removeNetwork(request, callbacks = null)

        // Then
        verify(mockAdapter, times(1)).removeNetwork(request)
        verify(mockRemoveNetworkCallbacks, never()).onWisefyAsyncFailure(anyNonNull())
    }

    @Test
    fun removeNetwork_byBSSID_async_success_resultCode() {
        // Given
        given(mockAdapter.removeNetwork(anyNonNull())).willReturn(
            RemoveNetworkResult.Success.ResultCode(REMOVE_NETWORK_SUCCESS_RESULT_CODE),
        )
        val delegate = WisefyRemoveNetworkDelegate(
            wifiManager = mockWifiManager,
            scope = testScope,
            mainDispatcher = testDispatcher,
            savedNetworkMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = RemoveNetworkRequest.BSSID(TEST_BSSID)

        // When
        delegate.removeNetwork(request, mockRemoveNetworkCallbacks)

        // Then
        verify(mockAdapter, times(1)).removeNetwork(request)
        verify(mockRemoveNetworkCallbacks, times(1)).onSuccessRemovingNetwork(
            RemoveNetworkResult.Success.ResultCode(REMOVE_NETWORK_SUCCESS_RESULT_CODE),
        )
    }

    @Test
    fun removeNetwork_byBSSID_async_success_boolean() {
        // Given
        given(mockAdapter.removeNetwork(anyNonNull())).willReturn(RemoveNetworkResult.Success.True)
        val delegate = WisefyRemoveNetworkDelegate(
            wifiManager = mockWifiManager,
            scope = testScope,
            mainDispatcher = testDispatcher,
            savedNetworkMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = RemoveNetworkRequest.BSSID(TEST_BSSID)

        // When
        delegate.removeNetwork(request, mockRemoveNetworkCallbacks)

        // Then
        verify(mockAdapter, times(1)).removeNetwork(request)
        verify(mockRemoveNetworkCallbacks, times(1)).onSuccessRemovingNetwork(
            RemoveNetworkResult.Success.True,
        )
    }

    @Test
    fun removeNetwork_byBSSID_async_failure_resultCode() {
        // Given
        given(mockAdapter.removeNetwork(anyNonNull())).willReturn(
            RemoveNetworkResult.Failure.ResultCode(REMOVE_NETWORK_FAILURE_RESULT_CODE),
        )
        val delegate = WisefyRemoveNetworkDelegate(
            wifiManager = mockWifiManager,
            scope = testScope,
            mainDispatcher = testDispatcher,
            savedNetworkMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = RemoveNetworkRequest.BSSID(TEST_BSSID)

        // When
        delegate.removeNetwork(request, mockRemoveNetworkCallbacks)

        // Then
        verify(mockAdapter, times(1)).removeNetwork(request)
        verify(mockRemoveNetworkCallbacks, times(1)).onFailureRemovingNetwork(
            RemoveNetworkResult.Failure.ResultCode(REMOVE_NETWORK_FAILURE_RESULT_CODE),
        )
    }

    @Test
    fun removeNetwork_byBSSID_async_failure_boolean() {
        // Given
        given(mockAdapter.removeNetwork(anyNonNull())).willReturn(RemoveNetworkResult.Failure.False)
        val delegate = WisefyRemoveNetworkDelegate(
            wifiManager = mockWifiManager,
            scope = testScope,
            mainDispatcher = testDispatcher,
            savedNetworkMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = RemoveNetworkRequest.BSSID(TEST_BSSID)

        // When
        delegate.removeNetwork(request, mockRemoveNetworkCallbacks)

        // Then
        verify(mockAdapter, times(1)).removeNetwork(request)
        verify(mockRemoveNetworkCallbacks, times(1)).onFailureRemovingNetwork(
            RemoveNetworkResult.Failure.False,
        )
    }

    @Test
    fun removeNetwork_byBSSID_async_failure_wisefyException() {
        // Given
        given(mockAdapter.removeNetwork(anyNonNull())).willThrow(IllegalStateException("test exception"))
        val delegate = WisefyRemoveNetworkDelegate(
            wifiManager = mockWifiManager,
            scope = testScope,
            mainDispatcher = testDispatcher,
            savedNetworkMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = RemoveNetworkRequest.BSSID(TEST_BSSID)

        // When
        delegate.removeNetwork(request, mockRemoveNetworkCallbacks)

        // Then
        verify(mockAdapter, times(1)).removeNetwork(request)
        verify(mockRemoveNetworkCallbacks, times(1)).onWisefyAsyncFailure(anyNonNull())
    }

    @Test
    fun removeNetwork_byBSSID_async_success_resultCode_noCallbacks() {
        // Given
        given(mockAdapter.removeNetwork(anyNonNull())).willReturn(
            RemoveNetworkResult.Success.ResultCode(REMOVE_NETWORK_SUCCESS_RESULT_CODE),
        )
        val delegate = WisefyRemoveNetworkDelegate(
            wifiManager = mockWifiManager,
            scope = testScope,
            mainDispatcher = testDispatcher,
            savedNetworkMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = RemoveNetworkRequest.BSSID(TEST_BSSID)

        // When
        delegate.removeNetwork(request, callbacks = null)

        // Then
        verify(mockAdapter, times(1)).removeNetwork(request)
        verify(mockRemoveNetworkCallbacks, never()).onWisefyAsyncFailure(anyNonNull())
    }

    @Test
    fun removeNetwork_byBSSID_async_success_boolean_noCallbacks() {
        // Given
        given(mockAdapter.removeNetwork(anyNonNull())).willReturn(RemoveNetworkResult.Success.True)
        val delegate = WisefyRemoveNetworkDelegate(
            wifiManager = mockWifiManager,
            scope = testScope,
            mainDispatcher = testDispatcher,
            savedNetworkMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = RemoveNetworkRequest.BSSID(TEST_BSSID)

        // When
        delegate.removeNetwork(request, callbacks = null)

        // Then
        verify(mockAdapter, times(1)).removeNetwork(request)
        verify(mockRemoveNetworkCallbacks, never()).onWisefyAsyncFailure(anyNonNull())
    }

    @Test
    fun removeNetwork_byBSSID_async_failure_resultCode_noCallbacks() {
        // Given
        given(mockAdapter.removeNetwork(anyNonNull())).willReturn(
            RemoveNetworkResult.Failure.ResultCode(REMOVE_NETWORK_FAILURE_RESULT_CODE),
        )
        val delegate = WisefyRemoveNetworkDelegate(
            wifiManager = mockWifiManager,
            scope = testScope,
            mainDispatcher = testDispatcher,
            savedNetworkMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = RemoveNetworkRequest.BSSID(TEST_BSSID)

        // When
        delegate.removeNetwork(request, callbacks = null)

        // Then
        verify(mockAdapter, times(1)).removeNetwork(request)
        verify(mockRemoveNetworkCallbacks, never()).onWisefyAsyncFailure(anyNonNull())
    }

    @Test
    fun removeNetwork_byBSSID_async_failure_boolean_noCallbacks() {
        // Given
        given(mockAdapter.removeNetwork(anyNonNull())).willReturn(RemoveNetworkResult.Failure.False)
        val delegate = WisefyRemoveNetworkDelegate(
            wifiManager = mockWifiManager,
            scope = testScope,
            mainDispatcher = testDispatcher,
            savedNetworkMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = RemoveNetworkRequest.BSSID(TEST_BSSID)

        // When
        delegate.removeNetwork(request, callbacks = null)

        // Then
        verify(mockAdapter, times(1)).removeNetwork(request)
        verify(mockRemoveNetworkCallbacks, never()).onWisefyAsyncFailure(anyNonNull())
    }

    @Test
    fun removeNetwork_byBSSID_async_failure_wisefyException_noCallbacks() {
        // Given
        given(mockAdapter.removeNetwork(anyNonNull())).willThrow(IllegalStateException("test exception"))
        val delegate = WisefyRemoveNetworkDelegate(
            wifiManager = mockWifiManager,
            scope = testScope,
            mainDispatcher = testDispatcher,
            savedNetworkMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = RemoveNetworkRequest.BSSID(TEST_BSSID)

        // When
        delegate.removeNetwork(request, callbacks = null)

        // Then
        verify(mockAdapter, times(1)).removeNetwork(request)
        verify(mockRemoveNetworkCallbacks, never()).onWisefyAsyncFailure(anyNonNull())
    }

    companion object {
        private const val TEST_SSID: String = "Test SSID"
        private const val TEST_BSSID: String = "Test:BSSID"

        private const val REMOVE_NETWORK_FAILURE_RESULT_CODE: Int = -1
        private const val REMOVE_NETWORK_SUCCESS_RESULT_CODE: Int = WifiManager.STATUS_NETWORK_SUGGESTIONS_SUCCESS
    }
}
