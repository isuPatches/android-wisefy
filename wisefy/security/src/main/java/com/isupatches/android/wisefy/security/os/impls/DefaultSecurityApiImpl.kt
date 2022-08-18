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

/**
 * A default internal implementation for checking the security details of a network through the Android OS.
 *
 * @see DefaultSecurityApi
 *
 * @author Patches Klinefelter
 * @since 07/2022, version 5.0.0
 */
internal class DefaultSecurityApiImpl : DefaultSecurityApi {

    override fun doesNetworkContainSecurityCapability(
        network: ScanResult,
        securityCapability: SecurityCapability
    ): Boolean {
        return containsCapability(network, securityCapability)
    }

    override fun isNetworkSecure(network: ScanResult): Boolean {
        network.capabilities?.let { capabilities ->
            val securityCapabilities = SecurityCapability.ALL
            for (securityCapability in securityCapabilities) {
                if (capabilities.contains(securityCapability.stringValue)) {
                    return true
                }
            }
        }
        return false
    }

    private fun containsCapability(network: ScanResult, capability: SecurityCapability): Boolean {
        return network.capabilities != null && network.capabilities.contains(capability.stringValue)
    }
}
