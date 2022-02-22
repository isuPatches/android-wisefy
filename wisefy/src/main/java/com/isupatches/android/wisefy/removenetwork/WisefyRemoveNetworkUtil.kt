/*
 * Copyright 2021 Patches Klinefelter
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
package com.isupatches.android.wisefy.removenetwork

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.CHANGE_WIFI_STATE
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.logging.WisefyLogger
import com.isupatches.android.wisefy.removenetwork.delegates.Android29RemoveNetworkDelegate
import com.isupatches.android.wisefy.removenetwork.delegates.LegacyRemoveNetworkDelegate
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkRequest
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkResult
import com.isupatches.android.wisefy.savednetworks.SavedNetworkDelegate
import com.isupatches.android.wisefy.util.SdkUtil
import com.isupatches.android.wisefy.util.coroutines.CoroutineDispatcherProvider
import com.isupatches.android.wisefy.util.coroutines.createBaseCoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal interface RemoveNetworkDelegate : RemoveNetworkApi, RemoveNetworkApiAsync

private const val LOG_TAG = "WisefyRemoveNetworkUtil"

internal class WisefyRemoveNetworkUtil(
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider,
    logger: WisefyLogger?,
    savedNetworkDelegate: SavedNetworkDelegate,
    sdkUtil: SdkUtil,
    wifiManager: WifiManager
) : RemoveNetworkDelegate {

    private val delegate: RemoveNetworkApi = when {
        sdkUtil.isAtLeastQ() -> Android29RemoveNetworkDelegate(wifiManager)
        else -> LegacyRemoveNetworkDelegate(wifiManager, savedNetworkDelegate)
    }
    private val removeNetworkScope = CoroutineScope(Job() + coroutineDispatcherProvider.io)

    init {
        logger?.d(LOG_TAG, "WisefyRemoveNetworkUtil delegate is: ${delegate::class.java.simpleName}")
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun removeNetwork(request: RemoveNetworkRequest): RemoveNetworkResult {
        return delegate.removeNetwork(request)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun removeNetwork(request: RemoveNetworkRequest, callbacks: RemoveNetworkCallbacks?) {
        removeNetworkScope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val result = delegate.removeNetwork(request)
            withContext(coroutineDispatcherProvider.main) {
                when (result) {
                    is RemoveNetworkResult.ResultCode -> {
                        if (result.value != -1) {
                            callbacks?.onNetworkRemoved(result)
                        } else {
                            callbacks?.onFailureRemovingNetwork(result)
                        }
                    }
                    is RemoveNetworkResult.Succeeded -> {
                        if (result.value) {
                            callbacks?.onNetworkRemoved(result)
                        } else {
                            callbacks?.onFailureRemovingNetwork(result)
                        }
                    }
                    is RemoveNetworkResult.NetworkNotFound -> {
                        callbacks?.onNetworkNotFoundToRemove()
                    }
                }
            }
        }
    }
}
