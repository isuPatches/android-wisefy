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

import android.os.Build
import androidx.annotation.RequiresApi
import com.isupatches.android.wisefy.WisefyApi
import com.isupatches.android.wisefy.core.exceptions.WisefyException
import com.isupatches.android.wisefy.networkconnection.callbacks.ChangeNetworkCallbacks
import com.isupatches.android.wisefy.networkconnection.entities.ChangeNetworkRequest
import com.isupatches.android.wisefy.networkconnection.entities.ChangeNetworkResult
import kotlin.coroutines.suspendCoroutine

/**
 * A coroutine extension for changing the current network.
 *
 * *Notes*
 *  - Locked by the networkConnectionMutex along with functions for connecting, disconnecting, getting the device's
 *    current network connection status, and getting the device's current network
 *
 * @receiver [WisefyApi]
 *
 * @param request The details of the request to change the current network
 *
 * @see ChangeNetworkRequest
 * @see ChangeNetworkResult
 *
 * @return ChangeNetworkResult - The result when changing the current network
 *
 * @throws WisefyException
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
@RequiresApi(Build.VERSION_CODES.Q)
@Throws(WisefyException::class)
suspend fun WisefyApi.changeNetworkAsync(request: ChangeNetworkRequest): ChangeNetworkResult =
    suspendCoroutine { continuation ->
        changeNetwork(
            request = request,
            callbacks = object : ChangeNetworkCallbacks {
                override fun onFailureChangingNetworks(result: ChangeNetworkResult.Failure) {
                    continuation.resumeWith(Result.success(result))
                }

                override fun onSuccessChangingNetworks(result: ChangeNetworkResult.Success) {
                    continuation.resumeWith(Result.success(result))
                }

                override fun onWisefyAsyncFailure(exception: WisefyException) {
                    continuation.resumeWith(Result.failure(exception))
                }
            }
        )
    }
