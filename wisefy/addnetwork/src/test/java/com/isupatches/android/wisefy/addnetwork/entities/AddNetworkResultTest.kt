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
package com.isupatches.android.wisefy.addnetwork.entities

import android.net.wifi.WifiManager
import com.isupatches.android.wisefy.core.constants.AssertionMessages
import org.junit.Assert.assertEquals
import org.junit.Test

internal class AddNetworkResultTest {

    @Test
    fun assignsValues_success_resultCode() {
        // Given
        val result = AddNetworkResult.Success.ResultCode(ADD_NETWORK_SUCCESS_RESULT_CODE)

        // Expect
        assertEquals(ADD_NETWORK_SUCCESS_RESULT_CODE, result.value)
    }

    @Test
    fun assignsValues_failure_resultCode() {
        // Given
        val result = AddNetworkResult.Failure.ResultCode(ADD_NETWORK_FAILURE_RESULT_CODE)

        // Expect
        assertEquals(ADD_NETWORK_FAILURE_RESULT_CODE, result.value)
    }

    @Test
    fun assignsValues_failure_assertion() {
        // Given
        val result = AddNetworkResult.Failure.Assertion(
            AssertionMessages.AddNetwork.WPA3Network.USED_PRE_ANDROID_29
        )

        // Expect
        assertEquals(AssertionMessages.AddNetwork.WPA3Network.USED_PRE_ANDROID_29, result.message)
    }

    companion object {
        private const val ADD_NETWORK_FAILURE_RESULT_CODE: Int = -1
        private const val ADD_NETWORK_SUCCESS_RESULT_CODE: Int = WifiManager.STATUS_NETWORK_SUGGESTIONS_SUCCESS
    }
}
