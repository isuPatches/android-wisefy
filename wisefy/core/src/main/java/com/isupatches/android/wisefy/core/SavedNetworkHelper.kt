/*
 * Copyright 2022 Patches Klinefelter
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

fun WifiConfiguration.hasSSIDMatchingRegex(regex: String): Boolean {
    return ssidWithoutQuotes.matches(regex.toRegex())
}

fun WifiConfiguration.hasBSSIDMatchingRegex(regex: String): Boolean {
    return bssidWithoutQuotes.matches(regex.toRegex())
}

val WifiConfiguration.ssidWithoutQuotes: String
    get() = SSID?.replace(QUOTE, "") ?: ""

val WifiConfiguration.bssidWithoutQuotes: String
    get() = BSSID?.replace(QUOTE, "") ?: ""

@RequiresApi(Build.VERSION_CODES.R)
fun WifiNetworkSuggestion.hasSSIDMatchingRegex(regex: String): Boolean {
    return ssidWithoutQuotes.matches(regex.toRegex())
}

@RequiresApi(Build.VERSION_CODES.R)
fun WifiNetworkSuggestion.hasBSSIDMatchingRegex(regex: String): Boolean {
    return bssidWithoutQuotes.matches(regex.toRegex())
}

private val WifiNetworkSuggestion.ssidWithoutQuotes: String
    @RequiresApi(Build.VERSION_CODES.R)
    get() = ssid?.replace(QUOTE, "") ?: ""

private val WifiNetworkSuggestion.bssidWithoutQuotes: String
    @RequiresApi(Build.VERSION_CODES.R)
    get() = bssid?.toString()?.replace(QUOTE, "") ?: ""
