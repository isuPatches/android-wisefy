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
package com.isupatches.android.wisefy.ktx

import android.Manifest.permission.CHANGE_NETWORK_STATE
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.WisefyApi
import com.isupatches.android.wisefy.core.exceptions.WisefyException
import com.isupatches.android.wisefy.networkconnection.callbacks.ConnectToNetworkCallbacks
import com.isupatches.android.wisefy.networkconnection.entities.ConnectToNetworkRequest
import com.isupatches.android.wisefy.networkconnection.entities.ConnectToNetworkResult
import kotlin.coroutines.suspendCoroutine

@Throws(WisefyException::class)
@RequiresPermission(CHANGE_NETWORK_STATE)
suspend fun WisefyApi.connectToNetworkAsync(request: ConnectToNetworkRequest): ConnectToNetworkResult =
    suspendCoroutine { continuation ->
        connectToNetwork(
            request = request,
            callbacks = object : ConnectToNetworkCallbacks {
                override fun onConnectedToNetwork() {
                    continuation.resumeWith(Result.success(ConnectToNetworkResult.Success.True))
                }

                override fun onConnectionRequestPlaced() {
                    continuation.resumeWith(Result.success(ConnectToNetworkResult.Success.ConnectionRequestSent))
                }

                override fun onFailureConnectingToNetwork() {
                    continuation.resumeWith(Result.success(ConnectToNetworkResult.Failure.False))
                }

                override fun onNetworkNotFoundToConnectTo() {
                    continuation.resumeWith(Result.success(ConnectToNetworkResult.Failure.NetworkNotFound))
                }

                override fun onWisefyAsyncFailure(exception: WisefyException) {
                    continuation.resumeWith(Result.failure(exception))
                }
            }
        )
    }
