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

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.ACCESS_NETWORK_STATE
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.WisefyApi
import com.isupatches.android.wisefy.core.exceptions.WisefyException
import com.isupatches.android.wisefy.frequency.callbacks.GetFrequencyCallbacks
import com.isupatches.android.wisefy.frequency.callbacks.IsNetwork5gHzCallbacks
import com.isupatches.android.wisefy.frequency.entities.FrequencyData
import com.isupatches.android.wisefy.frequency.entities.GetFrequencyRequest
import com.isupatches.android.wisefy.frequency.entities.GetFrequencyResult
import com.isupatches.android.wisefy.frequency.entities.IsNetwork5gHzRequest
import com.isupatches.android.wisefy.frequency.entities.IsNetwork5gHzResult
import kotlin.coroutines.suspendCoroutine
import kotlin.jvm.Throws

@Throws(WisefyException::class)
@RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_NETWORK_STATE])
suspend fun WisefyApi.getFrequencyAsync(
    request: GetFrequencyRequest = GetFrequencyRequest.CurrentNetwork
): GetFrequencyResult = suspendCoroutine { continuation ->
    getFrequency(
        request = request,
        callbacks = object : GetFrequencyCallbacks {
            override fun onFrequencyRetrieved(frequency: FrequencyData) {
                continuation.resumeWith(Result.success(GetFrequencyResult.WithFrequency(frequency)))
            }

            override fun onFailureRetrievingFrequency() {
                continuation.resumeWith(Result.success(GetFrequencyResult.Empty))
            }

            override fun onWisefyAsyncFailure(exception: WisefyException) {
                continuation.resumeWith(Result.failure(exception))
            }
        }
    )
}

@Throws(WisefyException::class)
@RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_NETWORK_STATE])
suspend fun WisefyApi.isNetwork5gHzAsync(
    request: IsNetwork5gHzRequest = IsNetwork5gHzRequest.CurrentNetwork
): IsNetwork5gHzResult = suspendCoroutine { continuation ->
    isNetwork5gHz(
        request = request,
        callbacks = object : IsNetwork5gHzCallbacks {
            override fun onNetworkIs5gHz() {
                continuation.resumeWith(Result.success(IsNetwork5gHzResult.True))
            }

            override fun onNetworkIsNot5gHz() {
                continuation.resumeWith(Result.success(IsNetwork5gHzResult.False))
            }

            override fun onWisefyAsyncFailure(exception: WisefyException) {
                continuation.resumeWith(Result.failure(exception))
            }
        }
    )
}
