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

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.Manifest.permission.CHANGE_WIFI_STATE
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.WisefyApi
import com.isupatches.android.wisefy.core.exceptions.WisefyException
import com.isupatches.android.wisefy.removenetwork.callbacks.RemoveNetworkCallbacks
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkRequest
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkResult
import kotlin.coroutines.suspendCoroutine

/**
 * A coroutine extension for removing a network.
 *
 * *Notes*
 *  - Locked by the savedNetworkMutex along with functions for adding, querying, and checking if a network is saved
 *
 * @receiver [WisefyApi]
 *
 * @param request The details of the request to remove a network
 *
 * @see RemoveNetworkRequest
 * @see RemoveNetworkResult
 *
 * @return RemoveNetworkResult - The result of removing a network
 *
 * @throws WisefyException
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
@Throws(WisefyException::class)
@RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE, CHANGE_WIFI_STATE])
suspend fun WisefyApi.removeNetworkAsync(request: RemoveNetworkRequest): RemoveNetworkResult =
    suspendCoroutine { continuation ->
        removeNetwork(
            request = request,
            callbacks = object : RemoveNetworkCallbacks {
                override fun onSuccessRemovingNetwork(result: RemoveNetworkResult.Success) {
                    continuation.resumeWith(Result.success(result))
                }

                override fun onFailureRemovingNetwork(result: RemoveNetworkResult.Failure) {
                    continuation.resumeWith(Result.success(result))
                }

                override fun onWisefyAsyncFailure(exception: WisefyException) {
                    continuation.resumeWith(Result.failure(exception))
                }
            }
        )
    }
