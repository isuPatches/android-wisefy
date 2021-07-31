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

import android.net.wifi.ScanResult
import com.isupatches.android.wisefy.security.entities.Capability
import com.isupatches.android.wisefy.security.entities.EAP
import com.isupatches.android.wisefy.security.entities.PSK
import com.isupatches.android.wisefy.security.entities.WEP
import com.isupatches.android.wisefy.security.entities.WPA
import com.isupatches.android.wisefy.security.entities.WPA2
import com.isupatches.android.wisefy.security.entities.WPA3

internal interface LegacySecurityApi {
    fun isNetworkEAP(scanResult: ScanResult): Boolean
    fun isNetworkPSK(scanResult: ScanResult): Boolean
    fun isNetworkSecure(scanResult: ScanResult): Boolean
    fun isNetworkWEP(scanResult: ScanResult): Boolean
    fun isNetworkWPA(scanResult: ScanResult): Boolean
    fun isNetworkWPA2(scanResult: ScanResult): Boolean
    fun isNetworkWPA3(scanResult: ScanResult): Boolean
}

internal class LegacySecurityApiImpl : LegacySecurityApi {

    override fun isNetworkEAP(scanResult: ScanResult): Boolean {
        return containsCapability(scanResult, EAP)
    }

    override fun isNetworkPSK(scanResult: ScanResult): Boolean {
        return containsCapability(scanResult, PSK)
    }

    override fun isNetworkSecure(scanResult: ScanResult): Boolean {
        val networkCapabilities: String = scanResult.capabilities
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

    override fun isNetworkWEP(scanResult: ScanResult): Boolean {
        return containsCapability(scanResult, WEP)
    }

    override fun isNetworkWPA(scanResult: ScanResult): Boolean {
        return containsCapability(scanResult, WPA)
    }

    override fun isNetworkWPA2(scanResult: ScanResult): Boolean {
        return containsCapability(scanResult, WPA2)
    }

    override fun isNetworkWPA3(scanResult: ScanResult): Boolean {
        return containsCapability(scanResult, WPA3)
    }

    private fun containsCapability(
        scanResult: ScanResult?,
        @Capability capability: String
    ): Boolean {
        return scanResult?.capabilities != null && scanResult.capabilities.contains(capability)
    }
}
