/*
 * Copyright 2021 Patches Klinefelter
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
package com.isupatches.android.wisefy.util

import android.net.wifi.WifiConfiguration
import com.isupatches.android.wisefy.constants.QUOTE

internal fun generateOpenNetworkConfiguration(ssid: String): WifiConfiguration {
    val wifiConfiguration = WifiConfiguration()
    wifiConfiguration.SSID = convertSSIDForConfig(ssid)
    wifiConfiguration.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE)

    // Allowed auth algorithms
    wifiConfiguration.allowedAuthAlgorithms.clear()

    // Allowed protocols
    wifiConfiguration.allowedProtocols.set(WifiConfiguration.Protocol.RSN)

    // Allowed Group Ciphers
    wifiConfiguration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP)
    wifiConfiguration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP)

    // Allowed Pairwise Ciphers
    wifiConfiguration.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP)
    return wifiConfiguration
}

internal fun generateWPA2NetworkConfiguration(ssid: String, password: String): WifiConfiguration {
    val wifiConfiguration = WifiConfiguration()
    wifiConfiguration.SSID = convertSSIDForConfig(ssid)
    wifiConfiguration.preSharedKey = QUOTE + password + QUOTE
    wifiConfiguration.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK)
    wifiConfiguration.status = WifiConfiguration.Status.ENABLED

    // Allowed protocols
    wifiConfiguration.allowedProtocols.set(WifiConfiguration.Protocol.RSN)

    // Allowed Group Ciphers
    wifiConfiguration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP)
    wifiConfiguration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP)

    // Allowed Pairwise Ciphers
    wifiConfiguration.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP)
    return wifiConfiguration
}
