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

/**
 * A function that will create the network configuration for a WPA2 network.
 *
 * @param ssid The SSID of the open network
 * @param bssid The optional BSSID for the open network
 *
 * @return WifiNetworkSuggestion - The network suggestion for the open network
 *
 * @author Patches Klinefelter
 * @since 11/2022, version 5.0.0
 */
@RequiresApi(Build.VERSION_CODES.Q)
fun createOpenNetworkSuggestion(
    ssid: String,
    bssid: String?
): WifiNetworkSuggestion {
    val builder = WifiNetworkSuggestion.Builder()
        .setSsid(ssid)
        .setIsAppInteractionRequired(true) // Optional (Needs location permission)
    if (!bssid.isNullOrBlank()) {
        builder.setBssid(MacAddress.fromString(bssid))
    }
    return builder.build()
}

/**
 * A function that will create the network configuration for a WPA2 network.
 *
 * @param ssid The SSID of the WPA2 network
 * @param passphrase The passphrase of the WPA2 network
 * @param bssid The optional BSSID for the WPA2 network
 *
 * @return WifiNetworkSuggestion - The network suggestion for the WPA2 network
 *
 * @author Patches Klinefelter
 * @since 11/2022, version 5.0.0
 */
@RequiresApi(Build.VERSION_CODES.Q)
fun createWPA2NetworkSuggestion(
    ssid: String,
    passphrase: String,
    bssid: String?
): WifiNetworkSuggestion {
    val builder = WifiNetworkSuggestion.Builder()
        .setSsid(ssid)
        .setWpa2Passphrase(passphrase)
        .setIsAppInteractionRequired(true) // Optional (Needs location permission)
    if (!bssid.isNullOrBlank()) {
        builder.setBssid(MacAddress.fromString(bssid))
    }
    return builder.build()
}

/**
 * A function that will create the network configuration for a WPA2 network.
 *
 * @param ssid The SSID of the WPA3 network
 * @param passphrase The passphrase of the WPA3 network
 * @param bssid The optional BSSID for the WPA3 network
 *
 * @return WifiNetworkSuggestion - The network suggestion for the WPA3 network
 *
 * @author Patches Klinefelter
 * @since 11/2022, version 5.0.0
 */
@RequiresApi(Build.VERSION_CODES.Q)
fun createWPA3NetworkSuggestion(
    ssid: String,
    passphrase: String,
    bssid: String?
): WifiNetworkSuggestion {
    val builder = WifiNetworkSuggestion.Builder()
        .setSsid(ssid)
        .setWpa3Passphrase(passphrase)
        .setIsAppInteractionRequired(true) // Optional (Needs location permission)
    if (!bssid.isNullOrBlank()) {
        builder.setBssid(MacAddress.fromString(bssid))
    }
    return builder.build()
}
