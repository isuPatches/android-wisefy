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
import com.isupatches.android.wisefy.security.entities.SecurityDetailsRequest
import com.isupatches.android.wisefy.security.entities.SecurityDetailsResult
import com.isupatches.android.wisefy.security.os.impls.DefaultSecurityApiImpl
import com.isupatches.android.wisefy.security.os.apis.DefaultSecurityApi

internal class DefaultSecurityAdapter(
    private val api: DefaultSecurityApi = DefaultSecurityApiImpl()
) : SecurityApi {

    override fun isNetworkEAP(request: SecurityDetailsRequest): SecurityDetailsResult {
        return when (request) {
            is SecurityDetailsRequest.AccessPoint -> {
                val result = api.isNetworkEAP(request.data.value)
                if (result) {
                    SecurityDetailsResult.True
                } else {
                    SecurityDetailsResult.False
                }
            }
        }
    }

    override fun isNetworkPSK(request: SecurityDetailsRequest): SecurityDetailsResult {
        return when (request) {
            is SecurityDetailsRequest.AccessPoint -> {
                val result = api.isNetworkPSK(request.data.value)
                if (result) {
                    SecurityDetailsResult.True
                } else {
                    SecurityDetailsResult.False
                }
            }
        }
    }

    override fun isNetworkSecure(request: SecurityDetailsRequest): SecurityDetailsResult {
        return when (request) {
            is SecurityDetailsRequest.AccessPoint -> {
                val result = api.isNetworkSecure(request.data.value)
                if (result) {
                    SecurityDetailsResult.True
                } else {
                    SecurityDetailsResult.False
                }
            }
        }
    }

    override fun isNetworkWEP(request: SecurityDetailsRequest): SecurityDetailsResult {
        return when (request) {
            is SecurityDetailsRequest.AccessPoint -> {
                val result = api.isNetworkWEP(request.data.value)
                if (result) {
                    SecurityDetailsResult.True
                } else {
                    SecurityDetailsResult.False
                }
            }
        }
    }

    override fun isNetworkWPA(request: SecurityDetailsRequest): SecurityDetailsResult {
        return when (request) {
            is SecurityDetailsRequest.AccessPoint -> {
                val result = api.isNetworkWPA(request.data.value)
                if (result) {
                    SecurityDetailsResult.True
                } else {
                    SecurityDetailsResult.False
                }
            }
        }
    }

    override fun isNetworkWPA2(request: SecurityDetailsRequest): SecurityDetailsResult {
        return when (request) {
            is SecurityDetailsRequest.AccessPoint -> {
                val result = api.isNetworkWPA2(request.data.value)
                if (result) {
                    SecurityDetailsResult.True
                } else {
                    SecurityDetailsResult.False
                }
            }
        }
    }

    override fun isNetworkWPA3(request: SecurityDetailsRequest): SecurityDetailsResult {
        return when (request) {
            is SecurityDetailsRequest.AccessPoint -> {
                val result = api.isNetworkWPA3(request.data.value)
                if (result) {
                    SecurityDetailsResult.True
                } else {
                    SecurityDetailsResult.False
                }
            }
        }
    }
}
