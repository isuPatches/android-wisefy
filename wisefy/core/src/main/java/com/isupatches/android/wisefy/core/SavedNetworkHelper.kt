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

import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiNetworkSuggestion
import android.os.Build
import androidx.annotation.RequiresApi
import com.isupatches.android.wisefy.core.constants.QUOTE

/**
 * A helper function to determine if the SSID for the [WifiConfiguration] matches a given regex.
 *
 * @param regex The regex to check if the SSID of the access point matches
 *
 * @return Boolean - True if the SSID of the [WifiConfiguration] matches, otherwise false
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
fun WifiConfiguration.hasSSIDMatchingRegex(regex: String): Boolean {
    return ssidWithoutQuotes.matches(regex.toRegex())
}

/**
 * A helper function to determine if the BSSID for the [WifiConfiguration] matches a given regex.
 *
 * @param regex The regex to check if the BSSID of the access point matches
 *
 * @return Boolean - True if the BSSID of the [WifiConfiguration] matches, otherwise false
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
fun WifiConfiguration.hasBSSIDMatchingRegex(regex: String): Boolean {
    return bssidWithoutQuotes.matches(regex.toRegex())
}

/**
 * A convenience property to expose the SSID of a [WifiConfiguration] taking into account any SDK level considerations
 * and with stripping unnecessary quotes.
 *
 * @return String - The value of the [WifiConfiguration]'s SSID with quotes removed
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
val WifiConfiguration.ssidWithoutQuotes: String
    get() = SSID?.replace(QUOTE, "") ?: ""

/**
 * A convenience property to expose the BSSID of a [WifiConfiguration] taking into account any SDK level considerations
 * and with stripping unnecessary quotes.
 *
 * @return String - The value of the [WifiConfiguration]'s BSSID with quotes removed
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
val WifiConfiguration.bssidWithoutQuotes: String
    get() = BSSID?.replace(QUOTE, "") ?: ""

/**
 * A helper function to determine if the SSID for the [WifiNetworkSuggestion] matches a given regex.
 *
 * @param regex The regex to check if the SSID of the access point matches
 *
 * @return Boolean - True if the SSID of the [WifiNetworkSuggestion] matches, otherwise false
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
@RequiresApi(Build.VERSION_CODES.R)
fun WifiNetworkSuggestion.hasSSIDMatchingRegex(regex: String): Boolean {
    return ssidWithoutQuotes.matches(regex.toRegex())
}

/**
 * A helper function to determine if the BSSID for the [WifiNetworkSuggestion] matches a given regex.
 *
 * @param regex The regex to check if the BSSID of the access point matches
 *
 * @return Boolean - True if the BSSID of the [WifiNetworkSuggestion] matches, otherwise false
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
@RequiresApi(Build.VERSION_CODES.R)
fun WifiNetworkSuggestion.hasBSSIDMatchingRegex(regex: String): Boolean {
    return bssidWithoutQuotes.matches(regex.toRegex())
}

/**
 * A convenience property to expose the SSID of a [WifiNetworkSuggestion] taking into account any SDK level
 * considerations and with stripping unnecessary quotes.
 *
 * @return String - The value of the [WifiNetworkSuggestion]'s SSID with quotes removed
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
private val WifiNetworkSuggestion.ssidWithoutQuotes: String
    @RequiresApi(Build.VERSION_CODES.R)
    get() = ssid?.replace(QUOTE, "") ?: ""

/**
 * A convenience property to expose the BSSID of a [WifiNetworkSuggestion] taking into account any SDK level
 * considerations and with stripping unnecessary quotes.
 *
 * @return String - The value of the [WifiNetworkSuggestion]'s BSSID with quotes removed
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
private val WifiNetworkSuggestion.bssidWithoutQuotes: String
    @RequiresApi(Build.VERSION_CODES.R)
    get() = bssid?.toString()?.replace(QUOTE, "") ?: ""
