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
package com.isupatches.android.wisefy.security.delegates

import com.isupatches.android.wisefy.accesspoints.entities.AccessPointData
import com.isupatches.android.wisefy.security.entities.Capability
import com.isupatches.android.wisefy.security.entities.EAP
import com.isupatches.android.wisefy.security.entities.PSK
import com.isupatches.android.wisefy.security.entities.WEP
import com.isupatches.android.wisefy.security.entities.WPA
import com.isupatches.android.wisefy.security.entities.WPA2
import com.isupatches.android.wisefy.security.entities.WPA3

internal interface LegacySecurityApi {
    fun isNetworkEAP(network: AccessPointData): Boolean
    fun isNetworkPSK(network: AccessPointData): Boolean
    fun isNetworkSecure(network: AccessPointData): Boolean
    fun isNetworkWEP(network: AccessPointData): Boolean
    fun isNetworkWPA(network: AccessPointData): Boolean
    fun isNetworkWPA2(network: AccessPointData): Boolean
    fun isNetworkWPA3(network: AccessPointData): Boolean
}

internal class LegacySecurityApiImpl : LegacySecurityApi {

    override fun isNetworkEAP(network: AccessPointData): Boolean {
        return containsCapability(network, EAP)
    }

    override fun isNetworkPSK(network: AccessPointData): Boolean {
        return containsCapability(network, PSK)
    }

    override fun isNetworkSecure(network: AccessPointData): Boolean {
        val networkCapabilities: String = (network as? AccessPointData)?.value?.capabilities ?: ""
        networkCapabilities.let { capabilities ->
            val securityModes = arrayOf(EAP, PSK, WEP, WPA, WPA2, WPA3)
            for (securityMode in securityModes) {
                if (capabilities.contains(securityMode)) {
                    return true
                }
            }
        }
        return false
    }

    override fun isNetworkWEP(network: AccessPointData): Boolean {
        return containsCapability(network, WEP)
    }

    override fun isNetworkWPA(network: AccessPointData): Boolean {
        return containsCapability(network, WPA)
    }

    override fun isNetworkWPA2(network: AccessPointData): Boolean {
        return containsCapability(network, WPA2)
    }

    override fun isNetworkWPA3(network: AccessPointData): Boolean {
        return containsCapability(network, WPA3)
    }

    private fun containsCapability(
        network: AccessPointData,
        @Capability capability: String
    ): Boolean {
        return network.value.capabilities != null && network.value.capabilities.contains(capability)
    }
}
