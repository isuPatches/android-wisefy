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
package com.isupatches.android.wisefy.util.legacy

import android.net.wifi.WifiConfiguration
import com.isupatches.android.wisefy.constants.QUOTE
import java.util.Locale

internal fun generateOpenNetworkConfiguration(ssid: String): WifiConfiguration {
    return WifiConfiguration().apply {
        SSID = convertSSIDForConfig(ssid)
        allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE)

        // Allowed auth algorithms
        allowedAuthAlgorithms.clear()

        // Allowed protocols
        allowedProtocols.set(WifiConfiguration.Protocol.RSN)

        // Allowed Group Ciphers
        allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP)
        allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP)

        // Allowed Pairwise Ciphers
        allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP)
    }
}

internal fun generateWPA2NetworkConfiguration(ssid: String, password: String): WifiConfiguration {
    return WifiConfiguration().apply {
        SSID = convertSSIDForConfig(ssid)
        preSharedKey = QUOTE + password + QUOTE
        allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK)
        status = WifiConfiguration.Status.ENABLED

        // Allowed protocols
        allowedProtocols.set(WifiConfiguration.Protocol.RSN)

        // Allowed Group Ciphers
        allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP)
        allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP)

        // Allowed Pairwise Ciphers
        allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP)
    }
}

private fun convertSSIDForConfig(ssid: String): String = "%s%s%s".format(Locale.US, QUOTE, ssid, QUOTE)
