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
package com.isupatches.android.wisefy.wifi

import android.net.wifi.WifiManager
import com.isupatches.android.wisefy.testsupport.anyNonNull
import com.isupatches.android.wisefy.wifi.callbacks.DisableWifiCallbacks
import com.isupatches.android.wisefy.wifi.callbacks.EnableWifiCallbacks
import com.isupatches.android.wisefy.wifi.callbacks.IsWifiEnabledCallbacks
import com.isupatches.android.wisefy.wifi.entities.DisableWifiRequest
import com.isupatches.android.wisefy.wifi.entities.DisableWifiResult
import com.isupatches.android.wisefy.wifi.entities.EnableWifiRequest
import com.isupatches.android.wisefy.wifi.entities.EnableWifiResult
import com.isupatches.android.wisefy.wifi.entities.IsWifiEnabledQuery
import com.isupatches.android.wisefy.wifi.entities.IsWifiEnabledResult
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
internal class WisefyWifiDelegateDisableWifiTest {

    @get:Rule
    var mockitoRule: MockitoRule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS)

    @Mock
    private lateinit var mockWifiManager: WifiManager

    @Mock
    private lateinit var mockAdapter: WifiApi

    @Mock
    private lateinit var mockDisableWifiCallbacks: DisableWifiCallbacks

    private val testException = IllegalStateException("test exception")
    private val testAssertion = DisableWifiResult.Failure.Assertion("test assertion")

    private val testDispatcher = UnconfinedTestDispatcher()
    private val scope = TestScope(testDispatcher)

    @Test
    fun disableWifi_sync_success_disabled() {
        // Given
        given(mockAdapter.disableWifi(anyNonNull())).willReturn(DisableWifiResult.Success.Disabled)
        val delegate = WisefyWifiDelegate(
            wifiManager = mockWifiManager,
            scope = scope,
            wifiMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = DisableWifiRequest.Default

        // When
        val result = delegate.disableWifi(request)

        // Then
        verify(mockAdapter, times(1)).disableWifi(request)
        assertEquals(DisableWifiResult.Success.Disabled, result)
    }

    @Test
    fun disableWifi_sync_success_wifiSettingsScreenOpened() {
        // Given
        given(mockAdapter.disableWifi(anyNonNull())).willReturn(DisableWifiResult.Success.WifiSettingScreenOpened)
        val delegate = WisefyWifiDelegate(
            wifiManager = mockWifiManager,
            mainDispatcher = testDispatcher,
            scope = scope,
            wifiMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = DisableWifiRequest.Default

        // When
        val result = delegate.disableWifi(request)

        // Then
        verify(mockAdapter, times(1)).disableWifi(request)
        assertEquals(DisableWifiResult.Success.WifiSettingScreenOpened, result)
    }

    @Test
    fun disableWifi_sync_failure_unableToDisable() {
        // Given
        given(mockAdapter.disableWifi(anyNonNull())).willReturn(DisableWifiResult.Failure.UnableToDisable)
        val delegate = WisefyWifiDelegate(
            wifiManager = mockWifiManager,
            mainDispatcher = testDispatcher,
            scope = scope,
            wifiMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = DisableWifiRequest.Default

        // When
        val result = delegate.disableWifi(request)

        // Then
        verify(mockAdapter, times(1)).disableWifi(request)
        assertEquals(DisableWifiResult.Failure.UnableToDisable, result)
    }

    @Test
    fun disableWifi_sync_failure_assertion() {
        // Given
        given(mockAdapter.disableWifi(anyNonNull())).willReturn(testAssertion)
        val delegate = WisefyWifiDelegate(
            wifiManager = mockWifiManager,
            mainDispatcher = testDispatcher,
            scope = scope,
            wifiMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = DisableWifiRequest.Default

        // When
        val result = delegate.disableWifi(request)

        // Then
        verify(mockAdapter, times(1)).disableWifi(request)
        assertEquals(testAssertion, result)
    }

    @Test
    fun disableWifi_async_success_disabled() {
        // Given
        given(mockAdapter.disableWifi(anyNonNull())).willReturn(DisableWifiResult.Success.Disabled)
        val delegate = WisefyWifiDelegate(
            wifiManager = mockWifiManager,
            mainDispatcher = testDispatcher,
            scope = scope,
            wifiMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = DisableWifiRequest.Default

        // When
        delegate.disableWifi(request, mockDisableWifiCallbacks)

        // Then
        verify(mockAdapter, times(1)).disableWifi(request)
        verify(mockDisableWifiCallbacks, times(1)).onSuccessDisablingWifi(
            DisableWifiResult.Success.Disabled,
        )
    }

    @Test
    fun disableWifi_async_success_wifiSettingsScreenOpened() {
        // Given
        given(mockAdapter.disableWifi(anyNonNull())).willReturn(DisableWifiResult.Success.WifiSettingScreenOpened)
        val delegate = WisefyWifiDelegate(
            wifiManager = mockWifiManager,
            mainDispatcher = testDispatcher,
            scope = scope,
            wifiMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = DisableWifiRequest.Default

        // When
        delegate.disableWifi(request, mockDisableWifiCallbacks)

        // Then
        verify(mockAdapter, times(1)).disableWifi(request)
        verify(mockDisableWifiCallbacks, times(1)).onSuccessDisablingWifi(
            DisableWifiResult.Success.WifiSettingScreenOpened,
        )
    }

    @Test
    fun disableWifi_async_failure_assertion() {
        // Given
        given(mockAdapter.disableWifi(anyNonNull())).willReturn(testAssertion)
        val delegate = WisefyWifiDelegate(
            wifiManager = mockWifiManager,
            mainDispatcher = testDispatcher,
            scope = scope,
            wifiMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = DisableWifiRequest.Default

        // When
        delegate.disableWifi(request, mockDisableWifiCallbacks)

        // Then
        verify(mockAdapter, times(1)).disableWifi(request)
        verify(mockDisableWifiCallbacks, times(1)).onFailureDisablingWifi(testAssertion)
    }

    @Test
    fun disableWifi_async_failure_unableToDisable() {
        // Given
        given(mockAdapter.disableWifi(anyNonNull())).willReturn(DisableWifiResult.Failure.UnableToDisable)
        val delegate = WisefyWifiDelegate(
            wifiManager = mockWifiManager,
            mainDispatcher = testDispatcher,
            scope = scope,
            wifiMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = DisableWifiRequest.Default

        // When
        delegate.disableWifi(request, mockDisableWifiCallbacks)

        // Then
        verify(mockAdapter, times(1)).disableWifi(request)
        verify(mockDisableWifiCallbacks, times(1)).onFailureDisablingWifi(
            DisableWifiResult.Failure.UnableToDisable,
        )
    }

    @Test
    fun disableWifi_async_failure_wisefyException() {
        // Given
        given(mockAdapter.disableWifi(anyNonNull())).willThrow(testException)
        val delegate = WisefyWifiDelegate(
            wifiManager = mockWifiManager,
            mainDispatcher = testDispatcher,
            scope = scope,
            wifiMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = DisableWifiRequest.Default

        // When
        delegate.disableWifi(request, mockDisableWifiCallbacks)

        // Then
        verify(mockAdapter, times(1)).disableWifi(request)
        verify(mockDisableWifiCallbacks, times(1)).onWisefyAsyncFailure(anyNonNull())
    }

    @Test
    fun disableWifi_async_success_disabled_noCallbacks() {
        // Given
        given(mockAdapter.disableWifi(anyNonNull())).willReturn(DisableWifiResult.Success.Disabled)
        val delegate = WisefyWifiDelegate(
            wifiManager = mockWifiManager,
            mainDispatcher = testDispatcher,
            scope = scope,
            wifiMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = DisableWifiRequest.Default

        // When
        delegate.disableWifi(request, callbacks = null)

        // Then
        verify(mockAdapter, times(1)).disableWifi(request)
        verifyNoInteractions(mockDisableWifiCallbacks)
    }

    @Test
    fun disableWifi_async_success_wifiSettingsScreenOpened_noCallbacks() {
        // Given
        given(mockAdapter.disableWifi(anyNonNull())).willReturn(DisableWifiResult.Success.WifiSettingScreenOpened)
        val delegate = WisefyWifiDelegate(
            wifiManager = mockWifiManager,
            mainDispatcher = testDispatcher,
            scope = scope,
            wifiMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = DisableWifiRequest.Default

        // When
        delegate.disableWifi(request, callbacks = null)

        // Then
        verify(mockAdapter, times(1)).disableWifi(request)
        verifyNoInteractions(mockDisableWifiCallbacks)
    }

    @Test
    fun disableWifi_async_failure_assertion_noCallbacks() {
        // Given
        given(mockAdapter.disableWifi(anyNonNull())).willReturn(testAssertion)
        val delegate = WisefyWifiDelegate(
            wifiManager = mockWifiManager,
            mainDispatcher = testDispatcher,
            scope = scope,
            wifiMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = DisableWifiRequest.Default

        // When
        delegate.disableWifi(request, callbacks = null)

        // Then
        verify(mockAdapter, times(1)).disableWifi(request)
        verifyNoInteractions(mockDisableWifiCallbacks)
    }

    @Test
    fun disableWifi_async_failure_unableToDisable_noCallbacks() {
        // Given
        given(mockAdapter.disableWifi(anyNonNull())).willReturn(DisableWifiResult.Failure.UnableToDisable)
        val delegate = WisefyWifiDelegate(
            wifiManager = mockWifiManager,
            mainDispatcher = testDispatcher,
            scope = scope,
            wifiMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = DisableWifiRequest.Default

        // When
        delegate.disableWifi(request, callbacks = null)

        // Then
        verify(mockAdapter, times(1)).disableWifi(request)
        verifyNoInteractions(mockDisableWifiCallbacks)
    }

    @Test
    fun disableWifi_async_failure_wisefyException_noCallbacks() {
        // Given
        given(mockAdapter.disableWifi(anyNonNull())).willThrow(testException)
        val delegate = WisefyWifiDelegate(
            wifiManager = mockWifiManager,
            mainDispatcher = testDispatcher,
            scope = scope,
            wifiMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = DisableWifiRequest.Default

        // When
        delegate.disableWifi(request, callbacks = null)

        // Then
        verify(mockAdapter, times(1)).disableWifi(request)
        verifyNoInteractions(mockDisableWifiCallbacks)
    }
}

@ExperimentalCoroutinesApi
internal class WisefyWifiDelegateEnableWifiTest {

    @get:Rule
    var mockitoRule: MockitoRule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS)

    @Mock
    private lateinit var mockWifiManager: WifiManager

    @Mock
    private lateinit var mockAdapter: WifiApi

    @Mock
    private lateinit var mockEnableWifiCallbacks: EnableWifiCallbacks

    private val testException = IllegalStateException("test exception")
    private val testAssertion = EnableWifiResult.Failure.Assertion("test assertion")

    private val testDispatcher = UnconfinedTestDispatcher()
    private val scope = TestScope(testDispatcher)

    @Test
    fun enableWifi_sync_success_enabled() {
        // Given
        given(mockAdapter.enableWifi(anyNonNull())).willReturn(EnableWifiResult.Success.Enabled)
        val delegate = WisefyWifiDelegate(
            wifiManager = mockWifiManager,
            mainDispatcher = testDispatcher,
            scope = scope,
            wifiMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = EnableWifiRequest.Default

        // When
        val result = delegate.enableWifi(request)

        // Then
        verify(mockAdapter, times(1)).enableWifi(request)
        assertEquals(EnableWifiResult.Success.Enabled, result)
    }

    @Test
    fun enableWifi_sync_success_wifiSettingsScreenOpened() {
        // Given
        given(mockAdapter.enableWifi(anyNonNull())).willReturn(EnableWifiResult.Success.WifiSettingScreenOpened)
        val delegate = WisefyWifiDelegate(
            wifiManager = mockWifiManager,
            mainDispatcher = testDispatcher,
            scope = scope,
            wifiMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = EnableWifiRequest.Default

        // When
        val result = delegate.enableWifi(request)

        // Then
        verify(mockAdapter, times(1)).enableWifi(request)
        assertEquals(EnableWifiResult.Success.WifiSettingScreenOpened, result)
    }

    @Test
    fun enableWifi_sync_failure_unableToEnable() {
        // Given
        given(mockAdapter.enableWifi(anyNonNull())).willReturn(EnableWifiResult.Failure.UnableToEnable)
        val delegate = WisefyWifiDelegate(
            wifiManager = mockWifiManager,
            mainDispatcher = testDispatcher,
            scope = scope,
            wifiMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = EnableWifiRequest.Default

        // When
        val result = delegate.enableWifi(request)

        // Then
        verify(mockAdapter, times(1)).enableWifi(request)
        assertEquals(EnableWifiResult.Failure.UnableToEnable, result)
    }

    @Test
    fun enableWifi_sync_failure_assertion() {
        // Given
        given(mockAdapter.enableWifi(anyNonNull())).willReturn(testAssertion)
        val delegate = WisefyWifiDelegate(
            wifiManager = mockWifiManager,
            mainDispatcher = testDispatcher,
            scope = scope,
            wifiMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = EnableWifiRequest.Default

        // When
        val result = delegate.enableWifi(request)

        // Then
        verify(mockAdapter, times(1)).enableWifi(request)
        assertEquals(testAssertion, result)
    }

    @Test
    fun enableWifi_async_success_enabled() {
        // Given
        given(mockAdapter.enableWifi(anyNonNull())).willReturn(EnableWifiResult.Success.Enabled)
        val delegate = WisefyWifiDelegate(
            wifiManager = mockWifiManager,
            mainDispatcher = testDispatcher,
            scope = scope,
            wifiMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = EnableWifiRequest.Default

        // When
        delegate.enableWifi(request, mockEnableWifiCallbacks)

        // Then
        verify(mockAdapter, times(1)).enableWifi(request)
        verify(mockEnableWifiCallbacks, times(1)).onSuccessEnablingWifi(
            EnableWifiResult.Success.Enabled,
        )
    }

    @Test
    fun enableWifi_async_success_wifiSettingsScreenOpened() {
        // Given
        given(mockAdapter.enableWifi(anyNonNull())).willReturn(EnableWifiResult.Success.WifiSettingScreenOpened)
        val delegate = WisefyWifiDelegate(
            wifiManager = mockWifiManager,
            mainDispatcher = testDispatcher,
            scope = scope,
            wifiMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = EnableWifiRequest.Default

        // When
        delegate.enableWifi(request, mockEnableWifiCallbacks)

        // Then
        verify(mockAdapter, times(1)).enableWifi(request)
        verify(mockEnableWifiCallbacks, times(1)).onSuccessEnablingWifi(
            EnableWifiResult.Success.WifiSettingScreenOpened,
        )
    }

    @Test
    fun enableWifi_async_failure_assertion() {
        // Given
        given(mockAdapter.enableWifi(anyNonNull())).willReturn(testAssertion)
        val delegate = WisefyWifiDelegate(
            wifiManager = mockWifiManager,
            mainDispatcher = testDispatcher,
            scope = scope,
            wifiMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = EnableWifiRequest.Default

        // When
        delegate.enableWifi(request, mockEnableWifiCallbacks)

        // Then
        verify(mockAdapter, times(1)).enableWifi(request)
        verify(mockEnableWifiCallbacks, times(1)).onFailureEnablingWifi(testAssertion)
    }

    @Test
    fun enableWifi_async_failure_unableToEnable() {
        // Given
        given(mockAdapter.enableWifi(anyNonNull())).willReturn(EnableWifiResult.Failure.UnableToEnable)
        val delegate = WisefyWifiDelegate(
            wifiManager = mockWifiManager,
            mainDispatcher = testDispatcher,
            scope = scope,
            wifiMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = EnableWifiRequest.Default

        // When
        delegate.enableWifi(request, mockEnableWifiCallbacks)

        // Then
        verify(mockAdapter, times(1)).enableWifi(request)
        verify(mockEnableWifiCallbacks, times(1)).onFailureEnablingWifi(
            EnableWifiResult.Failure.UnableToEnable,
        )
    }

    @Test
    fun enableWifi_async_failure_wisefyException() {
        // Given
        given(mockAdapter.enableWifi(anyNonNull())).willThrow(testException)
        val delegate = WisefyWifiDelegate(
            wifiManager = mockWifiManager,
            mainDispatcher = testDispatcher,
            scope = scope,
            wifiMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = EnableWifiRequest.Default

        // When
        delegate.enableWifi(request, mockEnableWifiCallbacks)

        // Then
        verify(mockAdapter, times(1)).enableWifi(request)
        verify(mockEnableWifiCallbacks, times(1)).onWisefyAsyncFailure(anyNonNull())
    }

    @Test
    fun enableWifi_async_success_enabled_nullCallbacks() {
        // Given
        given(mockAdapter.enableWifi(anyNonNull())).willReturn(EnableWifiResult.Success.Enabled)
        val delegate = WisefyWifiDelegate(
            wifiManager = mockWifiManager,
            mainDispatcher = testDispatcher,
            scope = scope,
            wifiMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = EnableWifiRequest.Default

        // When
        delegate.enableWifi(request, callbacks = null)

        // Then
        verify(mockAdapter, times(1)).enableWifi(request)
        verifyNoInteractions(mockEnableWifiCallbacks)
    }

    @Test
    fun enableWifi_async_success_wifiSettingsScreenOpened_nullCallbacks() {
        // Given
        given(mockAdapter.enableWifi(anyNonNull())).willReturn(EnableWifiResult.Success.WifiSettingScreenOpened)
        val delegate = WisefyWifiDelegate(
            wifiManager = mockWifiManager,
            mainDispatcher = testDispatcher,
            scope = scope,
            wifiMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = EnableWifiRequest.Default

        // When
        delegate.enableWifi(request, callbacks = null)

        // Then
        verify(mockAdapter, times(1)).enableWifi(request)
        verifyNoInteractions(mockEnableWifiCallbacks)
    }

    @Test
    fun enableWifi_async_failure_assertion_nullCallbacks() {
        // Given
        given(mockAdapter.enableWifi(anyNonNull())).willReturn(testAssertion)
        val delegate = WisefyWifiDelegate(
            wifiManager = mockWifiManager,
            mainDispatcher = testDispatcher,
            scope = scope,
            wifiMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = EnableWifiRequest.Default

        // When
        delegate.enableWifi(request, callbacks = null)

        // Then
        verify(mockAdapter, times(1)).enableWifi(request)
        verifyNoInteractions(mockEnableWifiCallbacks)
    }

    @Test
    fun enableWifi_async_failure_unableToEnable_nullCallbacks() {
        // Given
        given(mockAdapter.enableWifi(anyNonNull())).willReturn(EnableWifiResult.Failure.UnableToEnable)
        val delegate = WisefyWifiDelegate(
            wifiManager = mockWifiManager,
            mainDispatcher = testDispatcher,
            scope = scope,
            wifiMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = EnableWifiRequest.Default

        // When
        delegate.enableWifi(request, callbacks = null)

        // Then
        verify(mockAdapter, times(1)).enableWifi(request)
        verifyNoInteractions(mockEnableWifiCallbacks)
    }

    @Test
    fun enableWifi_async_failure_wisefyException_nullCallbacks() {
        // Given
        given(mockAdapter.enableWifi(anyNonNull())).willThrow(testException)
        val delegate = WisefyWifiDelegate(
            wifiManager = mockWifiManager,
            mainDispatcher = testDispatcher,
            scope = scope,
            wifiMutex = Mutex(),
            adapter = mockAdapter,
        )
        val request = EnableWifiRequest.Default

        // When
        delegate.enableWifi(request, callbacks = null)

        // Then
        verify(mockAdapter, times(1)).enableWifi(request)
        verifyNoInteractions(mockEnableWifiCallbacks)
    }
}

@ExperimentalCoroutinesApi
internal class WisefyWifiDelegateIsWifiEnabledTest {

    @get:Rule
    var mockitoRule: MockitoRule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS)

    @Mock
    private lateinit var mockWifiManager: WifiManager

    @Mock
    private lateinit var mockAdapter: WifiApi

    @Mock
    private lateinit var mockIsWifiEnabledCallbacks: IsWifiEnabledCallbacks

    private val testDispatcher = UnconfinedTestDispatcher()
    private val scope = TestScope(testDispatcher)

    private val testException = IllegalStateException("test exception")

    @Test
    fun isWifiEnabled_sync_enabled() {
        // Given
        given(mockAdapter.isWifiEnabled(anyNonNull())).willReturn(IsWifiEnabledResult.True)
        val delegate = WisefyWifiDelegate(
            wifiManager = mockWifiManager,
            mainDispatcher = testDispatcher,
            scope = scope,
            wifiMutex = Mutex(),
            adapter = mockAdapter,
        )
        val query = IsWifiEnabledQuery()

        // When
        val result = delegate.isWifiEnabled(query)

        // Then
        verify(mockAdapter, times(1)).isWifiEnabled(query)
        assertEquals(IsWifiEnabledResult.True, result)
    }

    @Test
    fun isWifiEnabled_sync_disabled() {
        // Given
        given(mockAdapter.isWifiEnabled(anyNonNull())).willReturn(IsWifiEnabledResult.False)
        val delegate = WisefyWifiDelegate(
            wifiManager = mockWifiManager,
            mainDispatcher = testDispatcher,
            scope = scope,
            wifiMutex = Mutex(),
            adapter = mockAdapter,
        )
        val query = IsWifiEnabledQuery()

        // When
        val result = delegate.isWifiEnabled(query)

        // Then
        verify(mockAdapter, times(1)).isWifiEnabled(query)
        assertEquals(IsWifiEnabledResult.False, result)
    }

    @Test
    fun isWifiEnabled_async_enabled() {
        // Given
        given(mockAdapter.isWifiEnabled(anyNonNull())).willReturn(IsWifiEnabledResult.True)
        val delegate = WisefyWifiDelegate(
            wifiManager = mockWifiManager,
            mainDispatcher = testDispatcher,
            scope = scope,
            wifiMutex = Mutex(),
            adapter = mockAdapter,
        )
        val query = IsWifiEnabledQuery()

        // When
        delegate.isWifiEnabled(query, mockIsWifiEnabledCallbacks)

        // Then
        verify(mockAdapter, times(1)).isWifiEnabled(query)
        verify(mockIsWifiEnabledCallbacks, times(1)).onWifiIsEnabled()
    }

    @Test
    fun isWifiEnabled_async_disabled() {
        // Given
        given(mockAdapter.isWifiEnabled(anyNonNull())).willReturn(IsWifiEnabledResult.False)
        val delegate = WisefyWifiDelegate(
            wifiManager = mockWifiManager,
            mainDispatcher = testDispatcher,
            scope = scope,
            wifiMutex = Mutex(),
            adapter = mockAdapter,
        )
        val query = IsWifiEnabledQuery()

        // When
        delegate.isWifiEnabled(query, mockIsWifiEnabledCallbacks)

        // Then
        verify(mockAdapter, times(1)).isWifiEnabled(query)
        verify(mockIsWifiEnabledCallbacks, times(1)).onWifiIsDisabled()
    }

    @Test
    fun isWifiEnabled_async_failure_wisefyException() {
        // Given
        given(mockAdapter.isWifiEnabled(anyNonNull())).willThrow(testException)
        val delegate = WisefyWifiDelegate(
            wifiManager = mockWifiManager,
            mainDispatcher = testDispatcher,
            scope = scope,
            wifiMutex = Mutex(),
            adapter = mockAdapter,
        )
        val query = IsWifiEnabledQuery()

        // When
        delegate.isWifiEnabled(query, mockIsWifiEnabledCallbacks)

        // Then
        verify(mockAdapter, times(1)).isWifiEnabled(query)
        verify(mockIsWifiEnabledCallbacks, times(1)).onWisefyAsyncFailure(anyNonNull())
    }

    @Test
    fun isWifiEnabled_async_enabled_nullCallbacks() {
        // Given
        given(mockAdapter.isWifiEnabled(anyNonNull())).willReturn(IsWifiEnabledResult.True)
        val delegate = WisefyWifiDelegate(
            wifiManager = mockWifiManager,
            mainDispatcher = testDispatcher,
            scope = scope,
            wifiMutex = Mutex(),
            adapter = mockAdapter,
        )
        val query = IsWifiEnabledQuery()

        // When
        delegate.isWifiEnabled(query, callbacks = null)

        // Then
        verify(mockAdapter, times(1)).isWifiEnabled(query)
        verifyNoInteractions(mockIsWifiEnabledCallbacks)
    }

    @Test
    fun isWifiEnabled_async_disabled_nullCallbacks() {
        // Given
        given(mockAdapter.isWifiEnabled(anyNonNull())).willReturn(IsWifiEnabledResult.False)
        val delegate = WisefyWifiDelegate(
            wifiManager = mockWifiManager,
            mainDispatcher = testDispatcher,
            scope = scope,
            wifiMutex = Mutex(),
            adapter = mockAdapter,
        )
        val query = IsWifiEnabledQuery()

        // When
        delegate.isWifiEnabled(query, callbacks = null)

        // Then
        verify(mockAdapter, times(1)).isWifiEnabled(query)
        verifyNoInteractions(mockIsWifiEnabledCallbacks)
    }

    @Test
    fun isWifiEnabled_async_failure_wisefyException_nullCallbacks() {
        // Given
        given(mockAdapter.isWifiEnabled(anyNonNull())).willThrow(testException)
        val delegate = WisefyWifiDelegate(
            wifiManager = mockWifiManager,
            mainDispatcher = testDispatcher,
            scope = scope,
            wifiMutex = Mutex(),
            adapter = mockAdapter,
        )
        val query = IsWifiEnabledQuery()

        // When
        delegate.isWifiEnabled(query, callbacks = null)

        // Then
        verify(mockAdapter, times(1)).isWifiEnabled(query)
        verifyNoInteractions(mockIsWifiEnabledCallbacks)
    }
}
