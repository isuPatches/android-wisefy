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
package com.isupatches.android.wisefy.wifi.entities

import com.isupatches.android.wisefy.core.constants.AssertionMessages
import org.junit.Assert
import org.junit.Test

internal class EnableWifiResultTest {

    @Test
    fun canInstantiate_success_enabled() {
        // Given
        val result = EnableWifiResult.Success.Enabled

        // Expect
        Assert.assertNotNull(result)
    }

    @Test
    fun canInstantiate_success_wifiSettingsScreenOpened() {
        // Given
        val result = EnableWifiResult.Success.WifiSettingScreenOpened

        // Expect
        Assert.assertNotNull(result)
    }

    @Test
    fun canInstantiate_failure_unableToEnable() {
        // Given
        val result = EnableWifiResult.Failure.UnableToEnable

        // Expect
        Assert.assertNotNull(result)
    }

    @Test
    fun assignsValues_failure_assertion() {
        // Given
        val result = EnableWifiResult.Failure.Assertion(
            AssertionMessages.Wifi.ANDROID_29_REQUEST_USED_ON_PRE_ANDROID_29
        )

        // Expect
        Assert.assertEquals(AssertionMessages.Wifi.ANDROID_29_REQUEST_USED_ON_PRE_ANDROID_29, result.message)
    }
}
