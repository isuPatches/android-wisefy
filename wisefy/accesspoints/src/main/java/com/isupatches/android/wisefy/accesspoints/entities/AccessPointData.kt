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
import com.isupatches.android.wisefy.core.constants.MAX_FREQUENCY_5GHZ
import com.isupatches.android.wisefy.core.constants.MIN_FREQUENCY_5GHZ

/**
 * A data representation of an Access Point.
 *
 * @property rawValue The direct Android OS information about the access point
 * @property ssid A convenience property to expose the SSID of the access point from the [rawValue]
 * @property bssid A convenience property to expose the BSSID of the access point from the [rawValue]
 * @property frequency A convenience property to expose the frequency of the access point from the [rawValue]
 * @property rssi A convenience property to expose the RSSI level of the access point from the [rawValue]
 * @property is5gHz A convenience property to check if the access point is 5gHz based on its [rawValue]
 * @property isSecure A convenience property to check if the access point has any of the [SecurityCapability] values
 * listed based on its [rawValue]
 *
 * @see ScanResult
 * @see SecurityCapability
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
data class AccessPointData(
    val rawValue: ScanResult,
    val ssid: String,
    val bssid: String,
    val frequency: Int = rawValue.frequency,
    val rssi: Int = rawValue.level,
    val is5gHz: Boolean = frequency in MIN_FREQUENCY_5GHZ + 1 until MAX_FREQUENCY_5GHZ,
    val isSecure: Boolean = SecurityCapability.ALL.any { rawValue.capabilities.contains(it.stringValue) }
) {

    /**
     * A function to check if the given access point has a certain [SecurityCapability] based on its [rawValue].
     *
     * @param securityCapability The given [SecurityCapability] to check the access point for
     *
     * @see SecurityCapability
     *
     * @return Boolean - True if the access point contains the [SecurityCapability], otherwise false
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    fun containSecurityCapability(securityCapability: SecurityCapability): Boolean {
        return rawValue.capabilities.contains(securityCapability.stringValue)
    }
}
