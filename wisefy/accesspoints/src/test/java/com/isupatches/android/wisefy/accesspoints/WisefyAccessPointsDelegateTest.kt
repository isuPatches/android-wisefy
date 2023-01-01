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
package com.isupatches.android.wisefy.accesspoints

import android.net.wifi.ScanResult
import android.net.wifi.WifiManager
import com.isupatches.android.wisefy.accesspoints.callbacks.GetAccessPointsCallbacks
import com.isupatches.android.wisefy.accesspoints.entities.AccessPointData
import com.isupatches.android.wisefy.accesspoints.entities.GetAccessPointsQuery
import com.isupatches.android.wisefy.accesspoints.entities.GetAccessPointsResult
import com.isupatches.android.wisefy.core.coroutines.createBaseCoroutineExceptionHandler
import com.isupatches.android.wisefy.core.exceptions.WisefyException
import com.isupatches.android.wisefy.core.logging.DefaultWisefyLogger
import com.isupatches.android.wisefy.testsupport.TestCoroutineDispatchProvider
import com.isupatches.android.wisefy.testsupport.anyNonNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.anyList
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.never
import org.mockito.BDDMockito.times
import org.mockito.BDDMockito.verify
import org.mockito.Mock
import org.mockito.Mockito.lenient
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
internal class WisefyAccessPointsDelegateTest {

    @Mock
    private lateinit var mockWifiManager: WifiManager

    @Mock
    private lateinit var adapter: AccessPointsApi

    private lateinit var delegate: WisefyAccessPointsDelegate

    private lateinit var testScope: TestScope

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        val logger = DefaultWisefyLogger()
        testScope = TestScope()
        delegate = WisefyAccessPointsDelegate(
            logger = logger,
            wifiManager = mockWifiManager,
            coroutineDispatcherProvider = TestCoroutineDispatchProvider(),
            scope = testScope,
            adapter = adapter
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun getAccessPoints_sync_emptyList_filterDuplicates_true() {
        // Given
        val request = GetAccessPointsQuery.All()
        given(adapter.getAccessPoints(request)).willReturn(GetAccessPointsResult.Empty)

        // When
        val result = delegate.getAccessPoints(request)

        // Then
        assertEquals(GetAccessPointsResult.Empty, result)
    }

    @Test
    fun getAccessPoints_sync_emptyList_filterDuplicates_false() {
        // Given
        val request = GetAccessPointsQuery.All(filterDuplicates = false)
        given(adapter.getAccessPoints(request)).willReturn(GetAccessPointsResult.Empty)

        // When
        val result = delegate.getAccessPoints(request)

        // Then
        assertEquals(GetAccessPointsResult.Empty, result)
    }

    @Test
    fun getAccessPoints_async_emptyList_filterDuplicates_true() = runTest {
        // Given
        val request = GetAccessPointsQuery.All()
        val mockCallbacks = mock(GetAccessPointsCallbacks::class.java)
        given(adapter.getAccessPoints(request)).willReturn(GetAccessPointsResult.Empty)

        // When
        delegate.getAccessPoints(request, mockCallbacks)
        advanceUntilIdle()

        // Then
        verify(mockCallbacks, times(1)).onNoNearbyAccessPoints()
        verify(mockCallbacks, never()).onNearbyAccessPointsRetrieved(anyList())
        verify(mockCallbacks, never()).onWisefyAsyncFailure(anyNonNull())
    }

    @Test
    fun getAccessPoints_async_emptyList_filterDuplicates_false() = runTest {
        // Given
        val request = GetAccessPointsQuery.All(filterDuplicates = false)
        val mockCallbacks = mock(GetAccessPointsCallbacks::class.java)
        given(adapter.getAccessPoints(request)).willReturn(GetAccessPointsResult.Empty)

        // When
        delegate.getAccessPoints(request, mockCallbacks)
        advanceUntilIdle()

        // Then
        verify(mockCallbacks, times(1)).onNoNearbyAccessPoints()
        verify(mockCallbacks, never()).onNearbyAccessPointsRetrieved(anyList())
        verify(mockCallbacks, never()).onWisefyAsyncFailure(anyNonNull())
    }

    @Test
    fun getAccessPoints_sync_withList_filterDuplicates_true() {
        // Given
        val request = GetAccessPointsQuery.All()
        given(adapter.getAccessPoints(request)).willReturn(
            GetAccessPointsResult.AccessPoints(listOf(TEST_ACCESS_POINT))
        )

        // When
        val result = delegate.getAccessPoints(request)

        // Then
        assertTrue(result is GetAccessPointsResult.AccessPoints)
        assertEquals(1, (result as GetAccessPointsResult.AccessPoints).value.size)
        assertEquals(listOf(TEST_ACCESS_POINT), result.value)
    }

    @Test
    fun getAccessPoints_sync_withList_filterDuplicates_false() {
        // Given
        val request = GetAccessPointsQuery.All(filterDuplicates = false)
        given(adapter.getAccessPoints(request)).willReturn(
            GetAccessPointsResult.AccessPoints(listOf(TEST_ACCESS_POINT))
        )

        // When
        val result = delegate.getAccessPoints(request)

        // Then
        assertTrue(result is GetAccessPointsResult.AccessPoints)
        assertEquals(1, (result as GetAccessPointsResult.AccessPoints).value.size)
        assertEquals(listOf(TEST_ACCESS_POINT), result.value)
    }

    @Test
    fun getAccessPoints_async_withList_filterDuplicates_true() = runTest {
        // Given
        val request = GetAccessPointsQuery.All()
        val mockCallbacks = mock(GetAccessPointsCallbacks::class.java)
        given(adapter.getAccessPoints(request)).willReturn(
            GetAccessPointsResult.AccessPoints(listOf(TEST_ACCESS_POINT))
        )

        // When
        delegate.getAccessPoints(request, mockCallbacks)
        advanceUntilIdle()

        // Then
        verify(mockCallbacks, never()).onNoNearbyAccessPoints()
        verify(mockCallbacks, times(1)).onNearbyAccessPointsRetrieved(listOf(TEST_ACCESS_POINT))
        verify(mockCallbacks, never()).onWisefyAsyncFailure(anyNonNull())
    }

    @Test
    fun getAccessPoints_async_withList_filterDuplicates_false() = runTest {
        // Given
        val request = GetAccessPointsQuery.All(filterDuplicates = false)
        val mockCallbacks = mock(GetAccessPointsCallbacks::class.java)
        given(adapter.getAccessPoints(request)).willReturn(
            GetAccessPointsResult.AccessPoints(listOf(TEST_ACCESS_POINT))
        )

        // When
        delegate.getAccessPoints(request, mockCallbacks)
        advanceUntilIdle()

        // Then
        verify(mockCallbacks, never()).onNoNearbyAccessPoints()
        verify(mockCallbacks, times(1)).onNearbyAccessPointsRetrieved(listOf(TEST_ACCESS_POINT))
        verify(mockCallbacks, never()).onWisefyAsyncFailure(anyNonNull())
    }

    @Test
    fun getAccessPoints_async_nullCallbacks() = runTest {
        // Given
        val request = GetAccessPointsQuery.All(filterDuplicates = false)

        // Expect
        try {
            delegate.getAccessPoints(request, null)
            advanceUntilIdle()
        } catch (ex: Exception) {
            fail("Should not hit exception for getAccessPoints with null callbacks")
        }
    }

    @Test
    fun getAccessPoints_async_exception() {
        // Given
        val request = GetAccessPointsQuery.All()
        val testException = WisefyException("Test exception", null)
        val mockCallbacks = mock(GetAccessPointsCallbacks::class.java)
        lenient().`when`(adapter.getAccessPoints(request)).thenAnswer { throw testException }

        testScope.launch(createBaseCoroutineExceptionHandler(mockCallbacks)) {
            delegate.getAccessPoints(request, mockCallbacks)

            // Then
            verify(mockCallbacks, never()).onNoNearbyAccessPoints()
            verify(mockCallbacks, never()).onNearbyAccessPointsRetrieved(anyList())
            verify(mockCallbacks, times(1)).onWisefyAsyncFailure(testException)
        }

        testScope.advanceUntilIdle()
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
