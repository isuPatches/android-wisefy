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

import android.content.Context
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.mockito.Mockito

internal class EnableWifiRequestTest {

    @Test
    fun canInstantiate_default() {
        // Given
        val result = EnableWifiRequest.Default

        // Expect
        assertNotNull(result)
    }

    @Test
    fun assignsValues_android29OrAbove() {
        // Given
        val mockContext = Mockito.mock(Context::class.java)
        val result = EnableWifiRequest.Android29OrAbove(context = mockContext)

        // Expect
        assertEquals(mockContext, result.context)
    }
}
