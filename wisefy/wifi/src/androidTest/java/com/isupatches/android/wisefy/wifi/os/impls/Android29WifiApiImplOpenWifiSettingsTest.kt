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
import com.isupatches.android.wisefy.core.logging.DefaultWisefyLogger
import com.isupatches.android.wisefy.wifi.os.apis.Android29WifiApi
import org.hamcrest.core.AllOf.allOf
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class Android29WifiApiImplOpenWifiSettingsTest {

    @Mock
    private lateinit var mockWifiManager: WifiManager

    private lateinit var api: Android29WifiApi

    private var closable: AutoCloseable? = null

    @Before
    fun setUp() {
        Intents.init()
        closable = MockitoAnnotations.openMocks(this)
        api = Android29WifiApiImpl(wifiManager = mockWifiManager, logger = DefaultWisefyLogger())
    }

    @After
    fun tearDown() {
        Intents.release()
        closable?.close()
    }

    @Test
    fun test() {
        // Given
        val result = Instrumentation.ActivityResult(Activity.RESULT_OK, Intent())
        intending(hasAction(Settings.ACTION_WIFI_SETTINGS)).respondWith(result)

        // When
        api.openWifiSettings(InstrumentationRegistry.getInstrumentation().targetContext)

        // Then
        intended(
            allOf(
                hasAction(Settings.ACTION_WIFI_SETTINGS),
                hasFlag(Intent.FLAG_ACTIVITY_NEW_TASK)
            )
        )
    }
}
