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
package com.isupatches.android.wisefy.core.wifimanager

import android.net.MacAddress
import android.net.wifi.WifiNetworkSuggestion
import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.Q)
fun createOpenNetworkSuggestionWithSSID(ssid: String): WifiNetworkSuggestion {
    return WifiNetworkSuggestion.Builder()
        .setSsid(ssid)
        .build()
}

@RequiresApi(Build.VERSION_CODES.Q)
fun createOpenNetworkSuggestionWithBSSID(bssid: String): WifiNetworkSuggestion {
    return WifiNetworkSuggestion.Builder()
        .setBssid(MacAddress.fromString(bssid))
        .build()
}

@RequiresApi(Build.VERSION_CODES.Q)
fun createWPA2NetworkSuggestionWithSSID(
    ssid: String,
    passphrase: String
): WifiNetworkSuggestion {
    return WifiNetworkSuggestion.Builder()
        .setSsid(ssid)
        .setWpa2Passphrase(passphrase)
        .build()
}

@RequiresApi(Build.VERSION_CODES.Q)
fun createWPA2NetworkSuggestionWithBSSID(
    bssid: String,
    passphrase: String
): WifiNetworkSuggestion {
    return WifiNetworkSuggestion.Builder()
        .setBssid(MacAddress.fromString(bssid))
        .setWpa2Passphrase(passphrase)
        .build()
}

@RequiresApi(Build.VERSION_CODES.Q)
fun createWPA3NetworkSuggestionWithSSID(
    ssid: String,
    passphrase: String
): WifiNetworkSuggestion {
    return WifiNetworkSuggestion.Builder()
        .setSsid(ssid)
        .setWpa3Passphrase(passphrase)
        .build()
}

@RequiresApi(Build.VERSION_CODES.Q)
fun createWPA3NetworkSuggestionWithBSSID(
    bssid: String,
    passphrase: String
): WifiNetworkSuggestion {
    return WifiNetworkSuggestion.Builder()
        .setBssid(MacAddress.fromString(bssid))
        .setWpa3Passphrase(passphrase)
        .build()
}
