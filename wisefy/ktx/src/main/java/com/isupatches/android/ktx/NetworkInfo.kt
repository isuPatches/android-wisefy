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
package com.isupatches.android.ktx

import android.Manifest.permission.ACCESS_NETWORK_STATE
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.WisefyApi
import com.isupatches.android.wisefy.core.exceptions.WisefyException
import com.isupatches.android.wisefy.networkinfo.callbacks.GetCurrentNetworkCallbacks
import com.isupatches.android.wisefy.networkinfo.callbacks.GetCurrentNetworkInfoCallbacks
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkInfoRequest
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkInfoResult
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkRequest
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkResult
import com.isupatches.android.wisefy.networkinfo.entities.NetworkData
import com.isupatches.android.wisefy.networkinfo.entities.NetworkInfoData
import kotlin.coroutines.suspendCoroutine
import kotlin.jvm.Throws

@Throws(WisefyException::class)
suspend fun WisefyApi.getCurrentNetworkAsync(
    request: GetCurrentNetworkRequest = GetCurrentNetworkRequest()
): GetCurrentNetworkResult = suspendCoroutine { continuation ->
    getCurrentNetwork(
        request = request,
        callbacks = object : GetCurrentNetworkCallbacks {
            override fun onCurrentNetworkRetrieved(network: NetworkData) {
                continuation.resumeWith(Result.success(GetCurrentNetworkResult.Network(network)))
            }

            override fun onNoCurrentNetwork() {
                continuation.resumeWith(Result.success(GetCurrentNetworkResult.Empty))
            }

            override fun onWisefyAsyncFailure(exception: WisefyException) {
                continuation.resumeWith(Result.failure(exception))
            }
        }
    )
}

@Throws(WisefyException::class)
@RequiresPermission(ACCESS_NETWORK_STATE)
suspend fun WisefyApi.getCurrentNetworkInfoAsync(
    request: GetCurrentNetworkInfoRequest = GetCurrentNetworkInfoRequest()
): GetCurrentNetworkInfoResult = suspendCoroutine { continuation ->
    getCurrentNetworkInfo(
        request = request,
        callbacks = object : GetCurrentNetworkInfoCallbacks {
            override fun onCurrentNetworkInfoRetrieved(networkInfo: NetworkInfoData) {
                continuation.resumeWith(Result.success(GetCurrentNetworkInfoResult.NetworkInfo(networkInfo)))
            }

            override fun onNoCurrentNetworkToRetrieveInfo() {
                continuation.resumeWith(Result.success(GetCurrentNetworkInfoResult.Empty))
            }

            override fun onWisefyAsyncFailure(exception: WisefyException) {
                continuation.resumeWith(Result.failure(exception))
            }
        }
    )
}
