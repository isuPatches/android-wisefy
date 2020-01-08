/*
 * Copyright 2019 Patches Klinefelter
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
package com.isupatches.wisefy.utils

import android.net.wifi.WifiConfiguration
import com.isupatches.wisefy.constants.QUOTE

/**
 * To generate a configuration for an open network.
 *
 * @param ssid The ssid for the open network's configuration
 *
 * @return WifiConfiguration - The open network configuration
 *
 * @see [com.isupatches.wisefy.WiseFy.addOpenNetwork]
 * @see [WifiConfiguration]
 *
 * Updates
 * - 05/12/2019: General cleanup and removing deprecated values
 *
 * @author Patches
 * @since 3.0
 */
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

/**
 * To generate a configuration for a WEP network.
 *
 * @param ssid The ssid for the WEP network's configuration
 * @param password The password for the WEP network's configuration
 *
 * @return WifiConfiguration - The WEP network configuration
 *
 * @see [com.isupatches.wisefy.WiseFy.addWEPNetwork]
 * @see [WifiConfiguration]
 *
 * Updates
 * - 05/12/2019: General cleanup and removing deprecated values
 *
 * @author Patches
 * @since 3.0
 */
@Deprecated("Due to security and performance limitations, WEP networks are discouraged")
@Suppress("deprecation")
internal fun generateWEPNetworkConfiguration(ssid: String, password: String): WifiConfiguration {
    val wifiConfiguration = WifiConfiguration()
    wifiConfiguration.SSID = convertSSIDForConfig(ssid)
    wifiConfiguration.wepKeys[0] = QUOTE + password + QUOTE
    wifiConfiguration.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE)

    // Deprecated due to security issues with WPA networks, should use WPA2 instead
    wifiConfiguration.allowedProtocols.set(WifiConfiguration.Protocol.WPA) // WPA network protocol

    /*
     * Allowed Auth Algorithms
     */

    wifiConfiguration.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN)
    // Deprecated due to shared key authentication requiring static WEP keys
    wifiConfiguration.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.SHARED)

    /*
     * Allowed Protocols
     */
    wifiConfiguration.allowedProtocols.set(WifiConfiguration.Protocol.RSN)

    /*
     * Allowed Group Ciphers
     */

    // Deprecated because of WEP
    wifiConfiguration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40)
    wifiConfiguration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104)

    /*
     * Allowed Pairwise Ciphers
     */

    wifiConfiguration.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP)
    // Deprecated WPA algorithm, RSN and WPA2 should be used instead
    wifiConfiguration.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP)

    return wifiConfiguration
}

/**
 * To generate a configuration for a WPA2 network.
 *
 * @param ssid The ssid for the WPA2 network's configuration
 * @param password The password for the WPA2 network's configuration
 *
 * @return WifiConfiguration - The WPA2 network configuration
 *
 * @see [com.isupatches.wisefy.WiseFy.addWPA2Network]
 * @see [WifiConfiguration]
 *
 * Updates
 * - 05/12/2019: General cleanup and removing deprecated values
 *
 * @author Patches
 * @since 3.0
 */
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
