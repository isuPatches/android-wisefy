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

import android.net.wifi.ScanResult
import android.os.Build
import com.isupatches.android.wisefy.core.constants.QUOTE

fun ScanResult.hasSSIDMatchingRegex(regex: String): Boolean {
    return ssidWithoutQuotes.matches(regex.toRegex())
}

fun ScanResult.hasBSSIDMatchingRegex(regex: String): Boolean {
    return bssidWithoutQuotes.matches(regex.toRegex())
}

val ScanResult.ssidWithoutQuotes: String
    get() = if (Build.VERSION.SDK_INT > Build.VERSION_CODES.TIRAMISU) {
        wifiSsid?.toString()?.replace(QUOTE, "")
    } else {
        @Suppress("Deprecation")
        SSID.replace(QUOTE, "")
    } ?: ""

val ScanResult.bssidWithoutQuotes: String
    get() = BSSID.replace(QUOTE, "")
