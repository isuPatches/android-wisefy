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
package com.isupatches.android.wisefy.security.os.impls

import android.net.wifi.ScanResult
import com.isupatches.android.wisefy.security.entities.SecurityCapability
import com.isupatches.android.wisefy.security.os.apis.DefaultSecurityApi

internal class DefaultSecurityApiImpl : DefaultSecurityApi {

    override fun isNetworkEAP(scanResult: ScanResult): Boolean {
        return containsCapability(scanResult, SecurityCapability.EAP)
    }

    override fun isNetworkPSK(scanResult: ScanResult): Boolean {
        return containsCapability(scanResult, SecurityCapability.PSK)
    }

    override fun isNetworkSecure(scanResult: ScanResult): Boolean {
        scanResult.capabilities?.let { capabilities ->
            val securityCapabilities = SecurityCapability.ALL
            for (securityCapability in securityCapabilities) {
                if (capabilities.contains(securityCapability.stringValue)) {
                    return true
                }
            }
        }
        return false
    }

    override fun isNetworkWEP(scanResult: ScanResult): Boolean {
        return containsCapability(scanResult, SecurityCapability.WEP)
    }

    override fun isNetworkWPA(scanResult: ScanResult): Boolean {
        return containsCapability(scanResult, SecurityCapability.WPA)
    }

    override fun isNetworkWPA2(scanResult: ScanResult): Boolean {
        return containsCapability(scanResult, SecurityCapability.WPA2)
    }

    override fun isNetworkWPA3(scanResult: ScanResult): Boolean {
        return containsCapability(scanResult, SecurityCapability.WPA3)
    }

    private fun containsCapability(scanResult: ScanResult, capability: SecurityCapability): Boolean {
        return scanResult.capabilities != null && scanResult.capabilities.contains(capability.stringValue)
    }
}
