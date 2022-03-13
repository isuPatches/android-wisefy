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
package com.isupatches.android.wisefy.removenetwork

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.CHANGE_WIFI_STATE
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.removenetwork.callbacks.RemoveNetworkCallbacks
import com.isupatches.android.wisefy.shared.logging.WisefyLogger
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkRequest
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkResult
import com.isupatches.android.wisefy.removenetwork.os.adapters.Android29RemoveNetworkAdapter
import com.isupatches.android.wisefy.removenetwork.os.adapters.DefaultRemoveNetworkAdapter
import com.isupatches.android.wisefy.savednetworks.SavedNetworkDelegate
import com.isupatches.android.wisefy.shared.coroutines.CoroutineDispatcherProvider
import com.isupatches.android.wisefy.shared.coroutines.createBaseCoroutineExceptionHandler
import com.isupatches.android.wisefy.shared.util.SdkUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WisefyRemoveNetworkDelegate(
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider,
    logger: WisefyLogger?,
    savedNetworkDelegate: SavedNetworkDelegate,
    sdkUtil: SdkUtil,
    wifiManager: WifiManager
) : RemoveNetworkDelegate {

    companion object {
        private const val LOG_TAG = "WisefyRemoveNetworkDelegate"
    }

    private val adapter: RemoveNetworkApi = when {
        sdkUtil.isAtLeastQ() -> Android29RemoveNetworkAdapter(wifiManager)
        else -> DefaultRemoveNetworkAdapter(wifiManager, savedNetworkDelegate)
    }
    private val removeNetworkScope = CoroutineScope(Job() + coroutineDispatcherProvider.io)

    init {
        logger?.d(LOG_TAG, "WisefyRemoveNetworkDelegate adapter is: ${adapter::class.java.simpleName}")
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun removeNetwork(request: RemoveNetworkRequest): RemoveNetworkResult {
        return adapter.removeNetwork(request)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun removeNetwork(request: RemoveNetworkRequest, callbacks: RemoveNetworkCallbacks?) {
        removeNetworkScope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val result = adapter.removeNetwork(request)
            withContext(coroutineDispatcherProvider.main) {
                when (result) {
                    is RemoveNetworkResult.ResultCode.Success -> callbacks?.onNetworkRemoved(result)
                    is RemoveNetworkResult.ResultCode.Failure -> callbacks?.onFailureRemovingNetwork(result)
                    is RemoveNetworkResult.BooleanResult.Success -> callbacks?.onNetworkRemoved(result)
                    is RemoveNetworkResult.BooleanResult.Failure -> callbacks?.onFailureRemovingNetwork(result)
                    is RemoveNetworkResult.NetworkNotFound -> {
                        callbacks?.onNetworkNotFoundToRemove()
                    }
                }
            }
        }
    }
}
