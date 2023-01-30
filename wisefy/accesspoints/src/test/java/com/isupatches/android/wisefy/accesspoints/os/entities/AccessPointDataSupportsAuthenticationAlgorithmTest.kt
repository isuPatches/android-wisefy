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
import com.isupatches.android.wisefy.accesspoints.entities.supportsAuthenticationAlgorithm
import com.isupatches.android.wisefy.core.entities.AuthenticationAlgorithm
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

@RunWith(Parameterized::class)
internal class AccessPointDataSupportsAuthenticationAlgorithmTest(
    private val params: SupportsAuthenticationAlgorithmParams
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
        val result = accessPoint.supportsAuthenticationAlgorithm(params.authenticationAlgorithm)

        // When
        assertEquals(params.expectedResult, result)
    }

    companion object {
        @JvmStatic
        @Parameters(name = "{index}: {0}")
        fun paramValues(): List<SupportsAuthenticationAlgorithmParams> {
            return listOf(
                SupportsAuthenticationAlgorithmParams(
                    capabilities = "",
                    authenticationAlgorithm = AuthenticationAlgorithm.WEP,
                    expectedResult = false
                ),
                SupportsAuthenticationAlgorithmParams(
                    capabilities = "",
                    authenticationAlgorithm = AuthenticationAlgorithm.WPA,
                    expectedResult = false
                ),
                SupportsAuthenticationAlgorithmParams(
                    capabilities = "",
                    authenticationAlgorithm = AuthenticationAlgorithm.WPA2,
                    expectedResult = false
                ),
                SupportsAuthenticationAlgorithmParams(
                    capabilities = "",
                    authenticationAlgorithm = AuthenticationAlgorithm.WPA_EAP,
                    expectedResult = false
                ),
                SupportsAuthenticationAlgorithmParams(
                    capabilities = "",
                    authenticationAlgorithm = AuthenticationAlgorithm.IEEE8021X,
                    expectedResult = false
                ),
                SupportsAuthenticationAlgorithmParams(
                    capabilities = "WEP",
                    authenticationAlgorithm = AuthenticationAlgorithm.WEP,
                    expectedResult = true
                ),
                SupportsAuthenticationAlgorithmParams(
                    capabilities = "WEP",
                    authenticationAlgorithm = AuthenticationAlgorithm.WPA,
                    expectedResult = false
                ),
                SupportsAuthenticationAlgorithmParams(
                    capabilities = "WEP",
                    authenticationAlgorithm = AuthenticationAlgorithm.WPA2,
                    expectedResult = false
                ),
                SupportsAuthenticationAlgorithmParams(
                    capabilities = "WEP",
                    authenticationAlgorithm = AuthenticationAlgorithm.WPA_EAP,
                    expectedResult = false
                ),
                SupportsAuthenticationAlgorithmParams(
                    capabilities = "WEP",
                    authenticationAlgorithm = AuthenticationAlgorithm.IEEE8021X,
                    expectedResult = false
                ),
                SupportsAuthenticationAlgorithmParams(
                    capabilities = "WPA",
                    authenticationAlgorithm = AuthenticationAlgorithm.WEP,
                    expectedResult = false
                ),
                SupportsAuthenticationAlgorithmParams(
                    capabilities = "WPA",
                    authenticationAlgorithm = AuthenticationAlgorithm.WPA,
                    expectedResult = true
                ),
                SupportsAuthenticationAlgorithmParams(
                    capabilities = "WPA",
                    authenticationAlgorithm = AuthenticationAlgorithm.WPA2,
                    expectedResult = false
                ),
                SupportsAuthenticationAlgorithmParams(
                    capabilities = "WPA",
                    authenticationAlgorithm = AuthenticationAlgorithm.WPA_EAP,
                    expectedResult = false
                ),
                SupportsAuthenticationAlgorithmParams(
                    capabilities = "WPA",
                    authenticationAlgorithm = AuthenticationAlgorithm.IEEE8021X,
                    expectedResult = false
                ),
                SupportsAuthenticationAlgorithmParams(
                    capabilities = "WPA2",
                    authenticationAlgorithm = AuthenticationAlgorithm.WEP,
                    expectedResult = false
                ),
                SupportsAuthenticationAlgorithmParams(
                    capabilities = "WPA2",
                    authenticationAlgorithm = AuthenticationAlgorithm.WPA,
                    expectedResult = false
                ),
                SupportsAuthenticationAlgorithmParams(
                    capabilities = "WPA2",
                    authenticationAlgorithm = AuthenticationAlgorithm.WPA2,
                    expectedResult = true
                ),
                SupportsAuthenticationAlgorithmParams(
                    capabilities = "WPA2",
                    authenticationAlgorithm = AuthenticationAlgorithm.WPA_EAP,
                    expectedResult = false
                ),
                SupportsAuthenticationAlgorithmParams(
                    capabilities = "WPA2",
                    authenticationAlgorithm = AuthenticationAlgorithm.IEEE8021X,
                    expectedResult = false
                ),
                SupportsAuthenticationAlgorithmParams(
                    capabilities = "WPA-EAP",
                    authenticationAlgorithm = AuthenticationAlgorithm.WEP,
                    expectedResult = false
                ),
                SupportsAuthenticationAlgorithmParams(
                    capabilities = "WPA-EAP",
                    authenticationAlgorithm = AuthenticationAlgorithm.WPA,
                    expectedResult = false
                ),
                SupportsAuthenticationAlgorithmParams(
                    capabilities = "WPA-EAP",
                    authenticationAlgorithm = AuthenticationAlgorithm.WPA2,
                    expectedResult = false
                ),
                SupportsAuthenticationAlgorithmParams(
                    capabilities = "WPA-EAP",
                    authenticationAlgorithm = AuthenticationAlgorithm.WPA_EAP,
                    expectedResult = true
                ),
                SupportsAuthenticationAlgorithmParams(
                    capabilities = "WPA-EAP",
                    authenticationAlgorithm = AuthenticationAlgorithm.IEEE8021X,
                    expectedResult = false
                ),
                SupportsAuthenticationAlgorithmParams(
                    capabilities = "IEEE8021X",
                    authenticationAlgorithm = AuthenticationAlgorithm.WEP,
                    expectedResult = false
                ),
                SupportsAuthenticationAlgorithmParams(
                    capabilities = "IEEE8021X",
                    authenticationAlgorithm = AuthenticationAlgorithm.WPA,
                    expectedResult = false
                ),
                SupportsAuthenticationAlgorithmParams(
                    capabilities = "IEEE8021X",
                    authenticationAlgorithm = AuthenticationAlgorithm.WPA2,
                    expectedResult = false
                ),
                SupportsAuthenticationAlgorithmParams(
                    capabilities = "IEEE8021X",
                    authenticationAlgorithm = AuthenticationAlgorithm.WPA_EAP,
                    expectedResult = false
                ),
                SupportsAuthenticationAlgorithmParams(
                    capabilities = "IEEE8021X",
                    authenticationAlgorithm = AuthenticationAlgorithm.IEEE8021X,
                    expectedResult = true
                ),
                SupportsAuthenticationAlgorithmParams(
                    capabilities = "[WPA2-PSK-CCMP]",
                    authenticationAlgorithm = AuthenticationAlgorithm.WEP,
                    expectedResult = false
                ),
                SupportsAuthenticationAlgorithmParams(
                    capabilities = "[WPA2-PSK-CCMP]",
                    authenticationAlgorithm = AuthenticationAlgorithm.WPA,
                    expectedResult = false
                ),
                SupportsAuthenticationAlgorithmParams(
                    capabilities = "[WPA2-PSK-CCMP]",
                    authenticationAlgorithm = AuthenticationAlgorithm.WPA2,
                    expectedResult = true
                ),
                SupportsAuthenticationAlgorithmParams(
                    capabilities = "[WPA2-PSK-CCMP]",
                    authenticationAlgorithm = AuthenticationAlgorithm.WPA_EAP,
                    expectedResult = false
                ),
                SupportsAuthenticationAlgorithmParams(
                    capabilities = "[WPA2-PSK-CCMP]",
                    authenticationAlgorithm = AuthenticationAlgorithm.IEEE8021X,
                    expectedResult = false
                ),
                SupportsAuthenticationAlgorithmParams(
                    capabilities = "[WPA2-PSK-CCMP][WPA-PSK-CCMP]",
                    authenticationAlgorithm = AuthenticationAlgorithm.WEP,
                    expectedResult = false
                ),
                SupportsAuthenticationAlgorithmParams(
                    capabilities = "[WPA2-PSK-CCMP][WPA-PSK-CCMP]",
                    authenticationAlgorithm = AuthenticationAlgorithm.WPA,
                    expectedResult = true
                ),
                SupportsAuthenticationAlgorithmParams(
                    capabilities = "[WPA2-PSK-CCMP][WPA-PSK-CCMP]",
                    authenticationAlgorithm = AuthenticationAlgorithm.WPA2,
                    expectedResult = true
                ),
                SupportsAuthenticationAlgorithmParams(
                    capabilities = "[WPA2-PSK-CCMP][WPA-PSK-CCMP]",
                    authenticationAlgorithm = AuthenticationAlgorithm.WPA_EAP,
                    expectedResult = false
                ),
                SupportsAuthenticationAlgorithmParams(
                    capabilities = "[WPA2-PSK-CCMP][WPA-PSK-CCMP]",
                    authenticationAlgorithm = AuthenticationAlgorithm.IEEE8021X,
                    expectedResult = false
                )
            )
        }

        data class SupportsAuthenticationAlgorithmParams(
            val capabilities: String,
            val authenticationAlgorithm: AuthenticationAlgorithm,
            val expectedResult: Boolean
        )
    }
}
