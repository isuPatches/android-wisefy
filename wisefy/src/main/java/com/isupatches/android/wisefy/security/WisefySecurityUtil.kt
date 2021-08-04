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

import android.net.wifi.ScanResult
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

    override fun isNetworkEAP(scanResult: ScanResult): Boolean {
        return delegate.isNetworkEAP(scanResult)
    }

    override fun isNetworkPSK(scanResult: ScanResult): Boolean {
        return delegate.isNetworkPSK(scanResult)
    }

    override fun isNetworkSecure(scanResult: ScanResult): Boolean {
        return delegate.isNetworkSecure(scanResult)
    }

    override fun isNetworkWEP(scanResult: ScanResult): Boolean {
        return delegate.isNetworkWEP(scanResult)
    }

    override fun isNetworkWPA(scanResult: ScanResult): Boolean {
        return delegate.isNetworkWPA(scanResult)
    }

    override fun isNetworkWPA2(scanResult: ScanResult): Boolean {
        return delegate.isNetworkWPA2(scanResult)
    }

    override fun isNetworkWPA3(scanResult: ScanResult): Boolean {
        return delegate.isNetworkWPA3(scanResult)
    }
}
