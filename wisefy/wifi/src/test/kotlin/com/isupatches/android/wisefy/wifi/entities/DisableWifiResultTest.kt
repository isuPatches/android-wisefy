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
package com.isupatches.android.wisefy.wifi.entities

import com.isupatches.android.wisefy.core.constants.AssertionMessages
import org.junit.Assert.assertEquals
import org.junit.Test

internal class DisableWifiResultTest {

    @Test
    fun assignsValues_failure_assertion() {
        // Given
        val result = DisableWifiResult.Failure.Assertion(
            AssertionMessages.Wifi.ANDROID_29_REQUEST_USED_ON_PRE_ANDROID_29,
        )

        // Expect
        assertEquals(AssertionMessages.Wifi.ANDROID_29_REQUEST_USED_ON_PRE_ANDROID_29, result.message)
    }
}
