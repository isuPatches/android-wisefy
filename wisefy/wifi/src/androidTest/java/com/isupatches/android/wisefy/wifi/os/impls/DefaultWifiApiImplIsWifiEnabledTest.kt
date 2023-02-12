/*
 * Copyright 2023 Patches Barrett
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
package com.isupatches.android.wisefy.wifi.os.impls

import android.net.wifi.WifiManager
import androidx.test.espresso.intent.Intents
import com.isupatches.android.wisefy.core.logging.DefaultWisefyLogger
import com.isupatches.android.wisefy.wifi.os.apis.DefaultWifiApi
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@RunWith(Parameterized::class)
internal class DefaultWifiApiImplIsWifiEnabledTest(
    private val params: IsWifiEnabledParams
) {

    @Mock
    private lateinit var mockWifiManager: WifiManager

    private lateinit var api: DefaultWifiApi

    private var closable: AutoCloseable? = null

    @Before
    fun setUp() {
        Intents.init()
        closable = MockitoAnnotations.openMocks(this)
        api = DefaultWifiApiImpl(wifiManager = mockWifiManager, logger = DefaultWisefyLogger())
    }

    @After
    fun tearDown() {
        Intents.release()
        closable?.close()
    }

    @Test
    fun test() {
        // Given
        given(mockWifiManager.isWifiEnabled).willReturn(params.isWifiEnabledResult)

        // When
        val result = api.isWifiEnabled()

        // Then
        verify(mockWifiManager, times(1)).isWifiEnabled
        assertEquals(params.isWifiEnabledResult, result)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: {0}")
        fun paramValues(): List<IsWifiEnabledParams> {
            return listOf(
                IsWifiEnabledParams(isWifiEnabledResult = true),
                IsWifiEnabledParams(isWifiEnabledResult = false)
            )
        }

        data class IsWifiEnabledParams(val isWifiEnabledResult: Boolean)
    }
}
