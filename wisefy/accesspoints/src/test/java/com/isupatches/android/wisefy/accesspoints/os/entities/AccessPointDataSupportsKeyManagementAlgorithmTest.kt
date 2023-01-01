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
package com.isupatches.android.wisefy.accesspoints.os.entities

import android.net.wifi.ScanResult
import com.isupatches.android.wisefy.accesspoints.entities.AccessPointData
import com.isupatches.android.wisefy.accesspoints.entities.supportsKeyManagementAlgorithm
import com.isupatches.android.wisefy.core.entities.KeyManagementAlgorithm
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

@RunWith(Parameterized::class)
internal class AccessPointDataSupportsKeyManagementAlgorithmTest(
    private val params: SupportsKeyManagementAlgorithmParams
) {

    @Test
    fun supportsKeyManagementAlgorithm() {
        // Given
        val accessPoint = AccessPointData(
            rawValue = ScanResult().apply {
                capabilities = params.capabilities
            }
        )

        // Then
        val result = accessPoint.supportsKeyManagementAlgorithm(params.keyManagementAlgorithm)

        // When
        assertEquals(params.expectedResult, result)
    }

    companion object {
        @JvmStatic
        @Parameters(name = "{index}: {0}")
        fun params(): List<SupportsKeyManagementAlgorithmParams> {
            return listOf(
                SupportsKeyManagementAlgorithmParams(
                    capabilities = "",
                    keyManagementAlgorithm = KeyManagementAlgorithm.EAP,
                    expectedResult = false
                ),
                SupportsKeyManagementAlgorithmParams(
                    capabilities = "",
                    keyManagementAlgorithm = KeyManagementAlgorithm.PSK,
                    expectedResult = false
                ),
                SupportsKeyManagementAlgorithmParams(
                    capabilities = "EAP",
                    keyManagementAlgorithm = KeyManagementAlgorithm.EAP,
                    expectedResult = false
                ),
                SupportsKeyManagementAlgorithmParams(
                    capabilities = "EAP",
                    keyManagementAlgorithm = KeyManagementAlgorithm.PSK,
                    expectedResult = false
                ),
                SupportsKeyManagementAlgorithmParams(
                    capabilities = "PSK",
                    keyManagementAlgorithm = KeyManagementAlgorithm.EAP,
                    expectedResult = false
                ),
                SupportsKeyManagementAlgorithmParams(
                    capabilities = "PSK",
                    keyManagementAlgorithm = KeyManagementAlgorithm.PSK,
                    expectedResult = false
                ),
                SupportsKeyManagementAlgorithmParams(
                    capabilities = "[WPA2-PSK-CCMP]",
                    keyManagementAlgorithm = KeyManagementAlgorithm.EAP,
                    expectedResult = false
                ),
                SupportsKeyManagementAlgorithmParams(
                    capabilities = "[WPA2-PSK-CCMP]",
                    keyManagementAlgorithm = KeyManagementAlgorithm.PSK,
                    expectedResult = true
                ),
                SupportsKeyManagementAlgorithmParams(
                    capabilities = "[WPA2-EAP-CCMP]",
                    keyManagementAlgorithm = KeyManagementAlgorithm.EAP,
                    expectedResult = true
                ),
                SupportsKeyManagementAlgorithmParams(
                    capabilities = "[WPA2-EAP-CCMP]",
                    keyManagementAlgorithm = KeyManagementAlgorithm.PSK,
                    expectedResult = false
                ),
                SupportsKeyManagementAlgorithmParams(
                    capabilities = "[WPA2-EAP-CCMP][WPA-PSK-CCMP]",
                    keyManagementAlgorithm = KeyManagementAlgorithm.EAP,
                    expectedResult = true
                ),
                SupportsKeyManagementAlgorithmParams(
                    capabilities = "[WPA2-EAP-CCMP][WPA-PSK-CCMP]",
                    keyManagementAlgorithm = KeyManagementAlgorithm.PSK,
                    expectedResult = true
                )
            )
        }

        data class SupportsKeyManagementAlgorithmParams(
            val capabilities: String,
            val keyManagementAlgorithm: KeyManagementAlgorithm,
            val expectedResult: Boolean
        )
    }
}
