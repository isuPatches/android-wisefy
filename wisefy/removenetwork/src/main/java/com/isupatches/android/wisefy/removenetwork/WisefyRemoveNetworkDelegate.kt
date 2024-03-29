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
package com.isupatches.android.wisefy.removenetwork

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.Manifest.permission.CHANGE_WIFI_STATE
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.core.assertions.WisefyAssertions
import com.isupatches.android.wisefy.core.coroutines.CoroutineDispatcherProvider
import com.isupatches.android.wisefy.core.coroutines.createBaseCoroutineExceptionHandler
import com.isupatches.android.wisefy.core.logging.WisefyLogger
import com.isupatches.android.wisefy.core.util.SdkUtil
import com.isupatches.android.wisefy.removenetwork.callbacks.RemoveNetworkCallbacks
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkRequest
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkResult
import com.isupatches.android.wisefy.removenetwork.os.adapters.Android29RemoveNetworkAdapter
import com.isupatches.android.wisefy.removenetwork.os.adapters.Android30RemoveNetworkAdapter
import com.isupatches.android.wisefy.removenetwork.os.adapters.DefaultRemoveNetworkAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

/**
 * An internal Wisefy delegate for removing a network through the Android OS.
 *
 * @param assertions The [WisefyAssertions] instance to use
 * @param logger The [WisefyLogger] instance to use
 * @param sdkUtil The [SdkUtil] instance to use
 * @param wifiManager The WifiManager instance to use
 * @property coroutineDispatcherProvider The instance of the coroutine dispatcher provider to use
 * @property scope The coroutine scope to use
 * @property savedNetworkMutex A mutex shared with add/remove network to ensure synchronization between saved network
 *  reads and writes
 * @property adapter The adapter instance to use for removing a network (determined based on the Android OS level)
 *
 * @see CoroutineDispatcherProvider
 * @see SdkUtil
 * @see WisefyAssertions
 * @see WisefyLogger
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
class WisefyRemoveNetworkDelegate(
    assertions: WisefyAssertions,
    logger: WisefyLogger,
    sdkUtil: SdkUtil,
    wifiManager: WifiManager,
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider,
    private val scope: CoroutineScope,
    private val savedNetworkMutex: Mutex,
    private val adapter: RemoveNetworkApi = when {
        sdkUtil.isAtLeastR() -> Android30RemoveNetworkAdapter(logger, wifiManager)
        sdkUtil.isAtLeastQ() -> Android29RemoveNetworkAdapter(assertions)
        else -> DefaultRemoveNetworkAdapter(logger, wifiManager)
    }
) : RemoveNetworkDelegate {

    init {
        logger.d(LOG_TAG, "WisefyRemoveNetworkDelegate adapter is: ${adapter::class.java.simpleName}")
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE, CHANGE_WIFI_STATE])
    override fun removeNetwork(request: RemoveNetworkRequest): RemoveNetworkResult {
        return adapter.removeNetwork(request)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE, CHANGE_WIFI_STATE])
    override fun removeNetwork(request: RemoveNetworkRequest, callbacks: RemoveNetworkCallbacks?) {
        scope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            savedNetworkMutex.withLock {
                val result = adapter.removeNetwork(request)
                withContext(coroutineDispatcherProvider.main) {
                    when (result) {
                        is RemoveNetworkResult.Success -> callbacks?.onSuccessRemovingNetwork(result)
                        is RemoveNetworkResult.Failure -> callbacks?.onFailureRemovingNetwork(result)
                    }
                }
            }
        }
    }

    companion object {
        private const val LOG_TAG = "WisefyRemoveNetworkDelegate"
    }
}
