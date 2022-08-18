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
package com.isupatches.android.wisefy.security.os.adapters

import com.isupatches.android.wisefy.security.SecurityApi
import com.isupatches.android.wisefy.security.entities.ContainsSecurityCapabilityRequest
import com.isupatches.android.wisefy.security.entities.ContainsSecurityCapabilityResult
import com.isupatches.android.wisefy.security.entities.IsNetworkSecureRequest
import com.isupatches.android.wisefy.security.entities.IsNetworkSecureResult
import com.isupatches.android.wisefy.security.os.apis.DefaultSecurityApi
import com.isupatches.android.wisefy.security.os.impls.DefaultSecurityApiImpl

/**
 * A default adapter for checking the security details of a network.
 *
 * @param api The OS level API instance to use
 *
 * @see DefaultSecurityApi
 * @see DefaultSecurityApiImpl
 * @see SecurityApi
 *
 * @author Patches Klinefelter
 * @since 07/2022, version 5.0.0
 */
internal class DefaultSecurityAdapter(
    private val api: DefaultSecurityApi = DefaultSecurityApiImpl()
) : SecurityApi {

    override fun doesNetworkContainSecurityCapability(
        request: ContainsSecurityCapabilityRequest
    ): ContainsSecurityCapabilityResult {
        val result = api.doesNetworkContainSecurityCapability(
            network = request.network.value,
            securityCapability = request.securityCapability
        )
        return if (result) {
            ContainsSecurityCapabilityResult.True
        } else {
            ContainsSecurityCapabilityResult.False
        }
    }

    override fun isNetworkSecure(request: IsNetworkSecureRequest): IsNetworkSecureResult {
        val result = api.isNetworkSecure(network = request.network.value)
        return if (result) {
            IsNetworkSecureResult.True
        } else {
            IsNetworkSecureResult.False
        }
    }
}
