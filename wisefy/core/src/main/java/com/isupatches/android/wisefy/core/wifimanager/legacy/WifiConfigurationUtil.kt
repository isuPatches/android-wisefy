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
@file:Suppress("Deprecation")

package com.isupatches.android.wisefy.core.wifimanager.legacy

import android.net.wifi.WifiConfiguration
import com.isupatches.android.wisefy.core.constants.QUOTE
import java.util.Locale

/**
 * A function that will create the network configuration for an open network.
 *
 * @param ssid The SSID of the open network
 * @param bssid The optional BSSID for the open network being added
 *
 * @return WifiConfiguration - The network configuration for the open network
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
fun createOpenNetworkConfiguration(ssid: String, bssid: String?): WifiConfiguration {
    return WifiConfiguration().apply {
        SSID = convertSSIDForConfig(ssid)
        if (!bssid.isNullOrBlank()) {
            BSSID = convertSSIDForConfig(bssid)
        }
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

/**
 * A function that will create the network configuration for a WPA2 network.
 *
 * @param ssid The SSID of the WPA2 network
 * @param passphrase The passphrase of the WPA2 network
 * @param bssid The optional BSSID for the WPA2 network being added
 *
 * @return WifiConfiguration - The network configuration for the WPA2 network
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
fun createWPA2NetworkConfiguration(ssid: String, passphrase: String, bssid: String?): WifiConfiguration {
    return WifiConfiguration().apply {
        SSID = convertSSIDForConfig(ssid)
        if (!bssid.isNullOrBlank()) {
            BSSID = convertSSIDForConfig(bssid)
        }
        preSharedKey = QUOTE + passphrase + QUOTE
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

private fun convertSSIDForConfig(ssid: String): String = "%s%s%s".format(
    Locale.US,
    QUOTE,
    ssid,
    QUOTE
)
