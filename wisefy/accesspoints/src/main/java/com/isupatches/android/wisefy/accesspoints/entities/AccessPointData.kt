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
import com.isupatches.android.wisefy.core.bssidWithoutQuotes
import com.isupatches.android.wisefy.core.constants.MAX_FREQUENCY_2_4_GHZ
import com.isupatches.android.wisefy.core.constants.MAX_FREQUENCY_5_GHZ
import com.isupatches.android.wisefy.core.constants.MIN_FREQUENCY_2_4_GHZ
import com.isupatches.android.wisefy.core.constants.MIN_FREQUENCY_5_GHZ
import com.isupatches.android.wisefy.core.entities.AuthenticationAlgorithm
import com.isupatches.android.wisefy.core.entities.KeyManagementAlgorithm
import com.isupatches.android.wisefy.core.entities.PairwiseCipher
import com.isupatches.android.wisefy.core.ssidWithoutQuotes
import com.isupatches.android.wisefy.core.supportsAuthenticationAlgorithm
import com.isupatches.android.wisefy.core.supportsKeyManagementAlgorithm
import com.isupatches.android.wisefy.core.supportsPairwiseCipher

/**
 * A data representation of an Access Point.
 *
 * @property rawValue The direct Android OS information about the access point
 * @property ssid A convenience property to expose the SSID of the access point from the [rawValue]
 * @property bssid A convenience property to expose the BSSID of the access point from the [rawValue]
 * @property frequency A convenience property to expose the frequency of the access point from the [rawValue]
 * @property rssi A convenience property to expose the RSSI level of the access point from the [rawValue]
 * @property is2gHz A convenience property to check if the access point is a 2.4gHz network based on its [rawValue]
 * @property is5gHz A convenience property to check if the access point is a 5gHz network based on its [rawValue]
 * @property isSecure A convenience property to check if the access point has any of the [AuthenticationAlgorithm]
 * values listed based on its [rawValue]
 *
 * @see AuthenticationAlgorithm
 * @see supportsAuthenticationAlgorithm
 * @see bssidWithoutQuotes
 * @see ssidWithoutQuotes
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
data class AccessPointData(
    val rawValue: ScanResult,
    val ssid: String = rawValue.ssidWithoutQuotes,
    val bssid: String = rawValue.bssidWithoutQuotes,
    val frequency: Int = rawValue.frequency,
    val rssi: Int = rawValue.level,
    val is2gHz: Boolean = frequency in MIN_FREQUENCY_2_4_GHZ until MAX_FREQUENCY_2_4_GHZ,
    val is5gHz: Boolean = frequency in MIN_FREQUENCY_5_GHZ until MAX_FREQUENCY_5_GHZ,
    val isSecure: Boolean = AuthenticationAlgorithm.ALL.any { rawValue.supportsAuthenticationAlgorithm(it) }
)

/**
 * An extension function to check if the given access point supports a certain [AuthenticationAlgorithm] based on
 * its rawValue.
 *
 * @receiver [AccessPointData]
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
fun AccessPointData.supportsAuthenticationAlgorithm(authenticationAlgorithm: AuthenticationAlgorithm): Boolean {
    return rawValue.supportsAuthenticationAlgorithm(authenticationAlgorithm)
}

/**
 * An extension function to check if the given access point supports a certain [KeyManagementAlgorithm] based on
 * its rawValue.
 *
 * @receiver [AccessPointData]
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
fun AccessPointData.supportsKeyManagementAlgorithm(keyManagementAlgorithm: KeyManagementAlgorithm): Boolean {
    return rawValue.supportsKeyManagementAlgorithm(keyManagementAlgorithm)
}

/**
 * An extension function to check if the given access point supports a certain [PairwiseCipher] based on
 * its rawValue.
 *
 * @receiver [AccessPointData]
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
fun AccessPointData.supportsPairwiseCipher(pairwiseCipher: PairwiseCipher): Boolean {
    return rawValue.supportsPairwiseCipher(pairwiseCipher)
}
