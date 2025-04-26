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
package com.isupatches.android.wisefy.wifi.os.impls

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.net.wifi.WifiManager
import android.provider.Settings
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasFlag
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.core.AllOf.allOf
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.mockito.quality.Strictness

internal class Android29WifiApiImplTest {

    @get:Rule
    var mockitoRule: MockitoRule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS)

    @Mock
    private lateinit var mockWifiManager: WifiManager

    @Before
    fun setUp() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun isWifiEnabled_disabled() {
        // Given
        given(mockWifiManager.isWifiEnabled).willReturn(false)
        val impl = Android29WifiApiImpl(wifiManager = mockWifiManager)

        // When
        val result = impl.isWifiEnabled()

        // Then
        verify(mockWifiManager, times(1)).isWifiEnabled
        assertFalse(result)
    }

    @Test
    fun isWifiEnabled_enabled() {
        // Given
        given(mockWifiManager.isWifiEnabled).willReturn(true)
        val impl = Android29WifiApiImpl(wifiManager = mockWifiManager)

        // When
        val result = impl.isWifiEnabled()

        // Then
        verify(mockWifiManager, times(1)).isWifiEnabled
        assertTrue(result)
    }

    @Test
    fun openWifiSettings() {
        // Given
        val result = Instrumentation.ActivityResult(Activity.RESULT_OK, Intent())
        intending(hasAction(Settings.ACTION_WIFI_SETTINGS)).respondWith(result)
        val impl = Android29WifiApiImpl(wifiManager = mockWifiManager)

        // When
        impl.openWifiSettings(InstrumentationRegistry.getInstrumentation().targetContext)

        // Then
        intended(
            allOf(
                hasAction(Settings.ACTION_WIFI_SETTINGS),
                hasFlag(Intent.FLAG_ACTIVITY_NEW_TASK),
            ),
        )
    }
}
