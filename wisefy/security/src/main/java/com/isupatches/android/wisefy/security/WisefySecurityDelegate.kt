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
package com.isupatches.android.wisefy.security

import com.isupatches.android.wisefy.core.logging.WisefyLogger
import com.isupatches.android.wisefy.security.entities.SecurityDetailsRequest
import com.isupatches.android.wisefy.security.entities.SecurityDetailsResult
import com.isupatches.android.wisefy.security.os.adapters.DefaultSecurityAdapter

class WisefySecurityDelegate(
    logger: WisefyLogger
) : SecurityDelegate {

    companion object {
        private const val LOG_TAG = "WisefySecurityDelegate"
    }

    private val adapter = DefaultSecurityAdapter()

    init {
        logger.d(LOG_TAG, "WisefySecurityDelegate adapter is: ${adapter::class.java.simpleName}")
    }

    override fun isNetworkEAP(request: SecurityDetailsRequest): SecurityDetailsResult {
        return adapter.isNetworkEAP(request)
    }

    override fun isNetworkPSK(request: SecurityDetailsRequest): SecurityDetailsResult {
        return adapter.isNetworkPSK(request)
    }

    override fun isNetworkSecure(request: SecurityDetailsRequest): SecurityDetailsResult {
        return adapter.isNetworkSecure(request)
    }

    override fun isNetworkWEP(request: SecurityDetailsRequest): SecurityDetailsResult {
        return adapter.isNetworkWEP(request)
    }

    override fun isNetworkWPA(request: SecurityDetailsRequest): SecurityDetailsResult {
        return adapter.isNetworkWPA(request)
    }

    override fun isNetworkWPA2(request: SecurityDetailsRequest): SecurityDetailsResult {
        return adapter.isNetworkWPA2(request)
    }

    override fun isNetworkWPA3(request: SecurityDetailsRequest): SecurityDetailsResult {
        return adapter.isNetworkWPA3(request)
    }
}
