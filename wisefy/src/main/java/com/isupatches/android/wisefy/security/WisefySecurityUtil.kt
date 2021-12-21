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
package com.isupatches.android.wisefy.security

import com.isupatches.android.wisefy.accesspoints.entities.AccessPointData
import com.isupatches.android.wisefy.logging.WisefyLogger
import com.isupatches.android.wisefy.security.delegates.LegacySecurityDelegate

internal interface SecurityUtil : SecurityApi

private const val LOG_TAG = "WisefySecurityUtil"

internal class WisefySecurityUtil(
    logger: WisefyLogger?
) : SecurityUtil {

    private val delegate = LegacySecurityDelegate()

    init {
        logger?.d(LOG_TAG, "WisefySecurityUtil delegate is: ${delegate::class.java.simpleName}")
    }

    override fun isNetworkEAP(network: AccessPointData): Boolean {
        return delegate.isNetworkEAP(network)
    }

    override fun isNetworkPSK(network: AccessPointData): Boolean {
        return delegate.isNetworkPSK(network)
    }

    override fun isNetworkSecure(network: AccessPointData): Boolean {
        return delegate.isNetworkSecure(network)
    }

    override fun isNetworkWEP(network: AccessPointData): Boolean {
        return delegate.isNetworkWEP(network)
    }

    override fun isNetworkWPA(network: AccessPointData): Boolean {
        return delegate.isNetworkWPA(network)
    }

    override fun isNetworkWPA2(network: AccessPointData): Boolean {
        return delegate.isNetworkWPA2(network)
    }

    override fun isNetworkWPA3(network: AccessPointData): Boolean {
        return delegate.isNetworkWPA3(network)
    }
}
