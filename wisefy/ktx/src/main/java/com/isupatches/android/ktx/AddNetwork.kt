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
import android.Manifest.permission.CHANGE_WIFI_STATE
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.WisefyApi
import com.isupatches.android.wisefy.addnetwork.callbacks.AddNetworkCallbacks
import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkRequest
import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkResult
import com.isupatches.android.wisefy.core.exceptions.WisefyException
import kotlin.coroutines.suspendCoroutine
import kotlin.jvm.Throws

/**
 * A coroutine extension for adding a network.
 *
 * @param request The details of the request to add a network
 *
 * @see AddNetworkRequest
 * @see AddNetworkResult
 *
 * @return AddNetworkResult - The result when adding a network
 *
 * @throws WisefyException
 *
 * @author Patches Klinefelter
 * @since 08/2022, version 5.0.0
 */
@Throws(WisefyException::class)
@RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
suspend fun WisefyApi.addNetworkAsync(request: AddNetworkRequest): AddNetworkResult =
    suspendCoroutine { continuation ->
        addNetwork(request, object : AddNetworkCallbacks {
            override fun onNetworkAdded(result: AddNetworkResult.Success) {
                continuation.resumeWith(Result.success(result))
            }

            override fun onFailureAddingNetwork(result: AddNetworkResult.Failure) {
                continuation.resumeWith(Result.success(result))
            }

            override fun onWisefyAsyncFailure(exception: WisefyException) {
                continuation.resumeWith(Result.failure(exception))
            }
        })
    }
