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
package com.isupatches.android.wisefy.accesspoints.entities

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test

internal class GetAccessPointsQueryTest {

    @Test
    fun assignsValues_all() {
        // Given
        val query = GetAccessPointsQuery.All(filterDuplicates = false)

        // Expect
        assertFalse(query.filterDuplicates)
    }

    @Test
    fun assignsValues_bySSID() {
        // Given
        val query = GetAccessPointsQuery.BySSID(
            regex = TEST_SSID,
            timeoutInMillis = TEST_TIMEOUT,
            filterDuplicates = false
        )

        // Expect
        assertEquals(TEST_SSID, query.regex)
        assertEquals(TEST_TIMEOUT, query.timeoutInMillis)
        assertFalse(query.filterDuplicates)
    }

    @Test
    fun assignsValues_byBSSID() {
        // Given
        val query = GetAccessPointsQuery.ByBSSID(
            regex = TEST_BSSID,
            timeoutInMillis = TEST_TIMEOUT,
            filterDuplicates = false
        )

        // Expect
        assertEquals(TEST_BSSID, query.regex)
        assertEquals(TEST_TIMEOUT, query.timeoutInMillis)
        assertFalse(query.filterDuplicates)
    }

    companion object {
        private const val TEST_SSID = "Test SSID"
        private const val TEST_BSSID: String = "01:23:45:67:89:AB"

        private const val TEST_TIMEOUT = 3000
    }
}
