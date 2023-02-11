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
package com.isupatches.android.wisefy.accesspoints.entities

import android.net.wifi.ScanResult
import com.isupatches.android.wisefy.core.entities.PairwiseCipher
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

@RunWith(Parameterized::class)
internal class AccessPointDataSupportsPairwiseCipherTest(
    private val params: SupportsPairwiseCipherParams
) {

    @Test
    fun test() {
        // Given
        val accessPoint = AccessPointData(
            rawValue = ScanResult().apply {
                capabilities = params.capabilities
            }
        )

        // Then
        val result = accessPoint.supportsPairwiseCipher(params.pairwiseCipher)

        // When
        assertEquals(params.expectedResult, result)
    }

    companion object {
        @JvmStatic
        @Parameters(name = "{index}: {0}")
        fun paramValues(): List<SupportsPairwiseCipherParams> {
            return listOf(
                SupportsPairwiseCipherParams(
                    capabilities = "",
                    pairwiseCipher = PairwiseCipher.CCMP,
                    expectedResult = false
                ),
                SupportsPairwiseCipherParams(
                    capabilities = "",
                    pairwiseCipher = PairwiseCipher.TKIP,
                    expectedResult = false
                ),
                SupportsPairwiseCipherParams(
                    capabilities = "CCMP",
                    pairwiseCipher = PairwiseCipher.CCMP,
                    expectedResult = false
                ),
                SupportsPairwiseCipherParams(
                    capabilities = "CCMP",
                    pairwiseCipher = PairwiseCipher.TKIP,
                    expectedResult = false
                ),
                SupportsPairwiseCipherParams(
                    capabilities = "TKIP",
                    pairwiseCipher = PairwiseCipher.CCMP,
                    expectedResult = false
                ),
                SupportsPairwiseCipherParams(
                    capabilities = "TKIP",
                    pairwiseCipher = PairwiseCipher.TKIP,
                    expectedResult = false
                ),
                SupportsPairwiseCipherParams(
                    capabilities = "[WPA2-PSK-CCMP]",
                    pairwiseCipher = PairwiseCipher.CCMP,
                    expectedResult = true
                ),
                SupportsPairwiseCipherParams(
                    capabilities = "[WPA2-PSK-CCMP]",
                    pairwiseCipher = PairwiseCipher.TKIP,
                    expectedResult = false
                ),
                SupportsPairwiseCipherParams(
                    capabilities = "[WPA2-PSK-TKIP]",
                    pairwiseCipher = PairwiseCipher.CCMP,
                    expectedResult = false
                ),
                SupportsPairwiseCipherParams(
                    capabilities = "[WPA2-PSK-TKIP]",
                    pairwiseCipher = PairwiseCipher.TKIP,
                    expectedResult = true
                ),
                SupportsPairwiseCipherParams(
                    capabilities = "[WPA2-EAP-CCMP][WPA-EAP-TKIP]",
                    pairwiseCipher = PairwiseCipher.CCMP,
                    expectedResult = true
                ),
                SupportsPairwiseCipherParams(
                    capabilities = "[WPA2-EAP-CCMP][WPA-EAP-TKIP]",
                    pairwiseCipher = PairwiseCipher.TKIP,
                    expectedResult = true
                )
            )
        }

        data class SupportsPairwiseCipherParams(
            val capabilities: String,
            val pairwiseCipher: PairwiseCipher,
            val expectedResult: Boolean
        )
    }
}
