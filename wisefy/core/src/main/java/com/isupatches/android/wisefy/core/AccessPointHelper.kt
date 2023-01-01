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
package com.isupatches.android.wisefy.core

import android.net.wifi.ScanResult
import android.os.Build
import com.isupatches.android.wisefy.core.constants.QUOTE
import com.isupatches.android.wisefy.core.entities.AuthenticationAlgorithm
import com.isupatches.android.wisefy.core.entities.KeyManagementAlgorithm
import com.isupatches.android.wisefy.core.entities.PairwiseCipher

/**
 * A helper function to determine if the SSID for the [ScanResult] matches a given regex.
 *
 * @param regex The regex to check if the SSID of the access point matches
 *
 * @return Boolean - True if the SSID of the [ScanResult] matches, otherwise false
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
fun ScanResult.hasSSIDMatchingRegex(regex: String): Boolean {
    return ssidWithoutQuotes.matches(regex.toRegex())
}

/**
 * A helper function to determine if the BSSID for the [ScanResult] matches a given regex.
 *
 * @param regex The regex to check if the BSSID of the access point matches
 *
 * @return Boolean - True if the BSSID of the [ScanResult] matches, otherwise false
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
fun ScanResult.hasBSSIDMatchingRegex(regex: String): Boolean {
    return bssidWithoutQuotes.matches(regex.toRegex())
}

/**
 * A convenience property to expose the SSID of a [ScanResult] taking into account any SDK level considerations and with
 * stripping unnecessary quotes.
 *
 * @return String - The value of the [ScanResult]'s SSID with quotes removed
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
val ScanResult.ssidWithoutQuotes: String
    get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        @Suppress("Deprecation")
        wifiSsid?.toString()?.replace(QUOTE, "") ?: SSID?.replace(QUOTE, "")
    } else {
        @Suppress("Deprecation")
        SSID?.replace(QUOTE, "")
    } ?: ""

/**
 * A convenience property to expose the BSSID of a [ScanResult] taking into account any SDK level considerations and
 * with stripping unnecessary quotes.
 *
 * @return String - The value of the [ScanResult]'s BSSID with quotes removed
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
val ScanResult.bssidWithoutQuotes: String
    get() = BSSID?.replace(QUOTE, "") ?: ""

private const val AUTHENTICATION_ALGORITHM_INDEX = 0
private const val KEY_MANAGEMENT_ALGORITHM_INDEX = 1
private const val PAIRWISE_CIPHER_INDEX = 2

/**
 * An extension function to check if the given access point supports a certain [AuthenticationAlgorithm] based on
 * its rawValue.
 *
 * @receiver [ScanResult]
 *
 * @param authenticationAlgorithm The given [AuthenticationAlgorithm] to check the access point for
 *
 * @see AuthenticationAlgorithm
 *
 * @return Boolean - True if the access point supports the [AuthenticationAlgorithm], otherwise false
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
fun ScanResult.supportsAuthenticationAlgorithm(authenticationAlgorithm: AuthenticationAlgorithm): Boolean {
    if (capabilities.isNullOrBlank()) {
        return false
    }
    return capabilities
        .split("[")
        .any {
            val wpaEnterpriseCapability = it.substringBefore("]")
                .startsWith(AuthenticationAlgorithm.WPA_EAP.stringValue)
            if (authenticationAlgorithm == AuthenticationAlgorithm.WPA_EAP) {
                it.substringBefore("]").startsWith(authenticationAlgorithm.stringValue)
            } else {
                !wpaEnterpriseCapability && it.substringBefore("]").split("-")
                    .getOrNull(AUTHENTICATION_ALGORITHM_INDEX) == authenticationAlgorithm.stringValue
            }
        }
}

/**
 * An extension function to check if the given access point supports a certain [KeyManagementAlgorithm] based on
 * its rawValue.
 *
 * @receiver [ScanResult]
 *
 * @param keyManagementAlgorithm The given [KeyManagementAlgorithm] to check the access point for
 *
 * @see KeyManagementAlgorithm
 *
 * @return Boolean - True if the access point supports the [KeyManagementAlgorithm], otherwise false
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
fun ScanResult.supportsKeyManagementAlgorithm(keyManagementAlgorithm: KeyManagementAlgorithm): Boolean {
    if (capabilities.isNullOrBlank()) {
        return false
    }
    return capabilities
        .split("[")
        .any {
            it.substringBefore("]")
                .split("-")
                .getOrNull(KEY_MANAGEMENT_ALGORITHM_INDEX) == keyManagementAlgorithm.stringValue
        }
}

/**
 * An extension function to check if the given access point supports a certain [PairwiseCipher] based on
 * its rawValue.
 *
 * @receiver [ScanResult]
 *
 * @param pairwiseCipher The given [PairwiseCipher] to check the access point for
 *
 * @see PairwiseCipher
 *
 * @return Boolean - True if the access point supports the [PairwiseCipher], otherwise false
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
fun ScanResult.supportsPairwiseCipher(pairwiseCipher: PairwiseCipher): Boolean {
    if (capabilities.isNullOrBlank()) {
        return false
    }
    return capabilities
        .split("[")
        .any {
            println(it.substringBefore("]").split("-").getOrNull(PAIRWISE_CIPHER_INDEX))
            it.substringBefore("]")
                .split("-")
                .getOrNull(PAIRWISE_CIPHER_INDEX) == pairwiseCipher.stringValue
        }
}
