/*
 * Copyright 2022 Patches Barrett
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
package com.isupatches.android.wisefy.ktx

import android.Manifest.permission.ACCESS_NETWORK_STATE
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.WisefyApi
import com.isupatches.android.wisefy.core.exceptions.WisefyException
import com.isupatches.android.wisefy.networkinfo.callbacks.GetCurrentNetworkCallbacks
import com.isupatches.android.wisefy.networkinfo.callbacks.GetNetworkConnectionStatusCallbacks
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkQuery
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkResult
import com.isupatches.android.wisefy.networkinfo.entities.GetNetworkConnectionStatusQuery
import com.isupatches.android.wisefy.networkinfo.entities.GetNetworkConnectionStatusResult
import com.isupatches.android.wisefy.networkinfo.entities.NetworkData
import kotlin.coroutines.suspendCoroutine

@RequiresPermission(ACCESS_NETWORK_STATE)
@Throws(WisefyException::class)
suspend fun WisefyApi.getCurrentNetworkAsync(
    query: GetCurrentNetworkQuery = GetCurrentNetworkQuery()
): GetCurrentNetworkResult = suspendCoroutine { continuation ->
    getCurrentNetwork(
        query = query,
        callbacks = object : GetCurrentNetworkCallbacks {
            override fun onCurrentNetworkRetrieved(network: NetworkData) {
                continuation.resumeWith(Result.success(GetCurrentNetworkResult(network)))
            }

            override fun onWisefyAsyncFailure(exception: WisefyException) {
                continuation.resumeWith(Result.failure(exception))
            }
        }
    )
}

@Throws(WisefyException::class)
@RequiresPermission(ACCESS_NETWORK_STATE)
suspend fun WisefyApi.getNetworkConnectionStatusAsync(
    query: GetNetworkConnectionStatusQuery = GetNetworkConnectionStatusQuery()
): GetNetworkConnectionStatusResult = suspendCoroutine { continuation ->
    getNetworkConnectionStatus(
        query = query,
        callbacks = object : GetNetworkConnectionStatusCallbacks {
            override fun onDeviceNetworkConnectionStatusRetrieved(result: GetNetworkConnectionStatusResult) {
                continuation.resumeWith(Result.success(result))
            }

            override fun onWisefyAsyncFailure(exception: WisefyException) {
                continuation.resumeWith(Result.failure(exception))
            }
        }
    )
}
