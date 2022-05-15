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
import com.isupatches.android.wisefy.security.entities.ContainsSecurityCapabilityRequest
import com.isupatches.android.wisefy.security.entities.ContainsSecurityCapabilityResult
import com.isupatches.android.wisefy.security.entities.IsNetworkSecureRequest
import com.isupatches.android.wisefy.security.entities.IsNetworkSecureResult
import com.isupatches.android.wisefy.security.os.adapters.DefaultSecurityAdapter

/**
 * An internal Wisefy delegate for checking a network's security capabilities.
 *
 * @param logger The logger instance to use
 *
 * @see SecurityDelegate
 * @see WisefyLogger
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
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

    override fun doesNetworkContainSecurityCapability(
        request: ContainsSecurityCapabilityRequest
    ): ContainsSecurityCapabilityResult {
        return adapter.doesNetworkContainSecurityCapability(request)
    }

    override fun isNetworkSecure(request: IsNetworkSecureRequest): IsNetworkSecureResult {
        return adapter.isNetworkSecure(request)
    }
}
