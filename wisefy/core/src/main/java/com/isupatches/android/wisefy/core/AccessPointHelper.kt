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
        wifiSsid?.toString()?.replace(QUOTE, "")
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
