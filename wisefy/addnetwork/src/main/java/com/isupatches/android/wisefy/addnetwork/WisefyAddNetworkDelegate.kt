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
package com.isupatches.android.wisefy.addnetwork

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.CHANGE_WIFI_STATE
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.addnetwork.callbacks.AddNetworkCallbacks
import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkRequest
import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkResult
import com.isupatches.android.wisefy.addnetwork.os.adapters.Android29AddNetworkAdapter
import com.isupatches.android.wisefy.addnetwork.os.adapters.Android30AddNetworkAdapter
import com.isupatches.android.wisefy.addnetwork.os.adapters.DefaultAddNetworkAdapter
import com.isupatches.android.wisefy.core.assertions.WisefyAssertions
import com.isupatches.android.wisefy.core.coroutines.CoroutineDispatcherProvider
import com.isupatches.android.wisefy.core.coroutines.createBaseCoroutineExceptionHandler
import com.isupatches.android.wisefy.core.logging.WisefyLogger
import com.isupatches.android.wisefy.core.util.SdkUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

/**
 * An internal Wisefy delegate for adding networks.
 *
 * @param assertions The [WisefyAssertions] instance to use
 * @param logger The [WisefyLogger] instance to use
 * @param sdkUtil The [SdkUtil] instance to use
 * @param wifiManager The WifiManager instance to use
 * @property coroutineDispatcherProvider The [CoroutineDispatcherProvider] instance to use
 * @property scope The coroutine scope to use
 * @property savedNetworkMutex The mutex for all read/write operations involving saved networks
 * @property adapter The adapter instance to use for adding a network (determined based on the Android OS level)
 *
 * @see AddNetworkApi
 * @see AddNetworkDelegate
 * @see Android30AddNetworkAdapter
 * @see Android29AddNetworkAdapter
 * @see CoroutineDispatcherProvider
 * @see DefaultAddNetworkAdapter
 * @see SdkUtil
 * @see WisefyAssertions
 * @see WisefyLogger
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
class WisefyAddNetworkDelegate(
    assertions: WisefyAssertions,
    logger: WisefyLogger,
    sdkUtil: SdkUtil,
    wifiManager: WifiManager,
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider,
    private val scope: CoroutineScope,
    private val savedNetworkMutex: Mutex,
    private val adapter: AddNetworkApi = when {
        sdkUtil.isAtLeastR() -> Android30AddNetworkAdapter(wifiManager, logger, assertions)
        sdkUtil.isAtLeastQ() -> Android29AddNetworkAdapter(assertions)
        else -> DefaultAddNetworkAdapter(wifiManager, logger, assertions)
    }
) : AddNetworkDelegate {

    init {
        logger.d(LOG_TAG, "WisefyAddNetworkDelegate adapter is: ${adapter::class.java.simpleName}")
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addNetwork(request: AddNetworkRequest): AddNetworkResult {
        return adapter.addNetwork(request)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addNetwork(request: AddNetworkRequest, callbacks: AddNetworkCallbacks?) {
        scope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            savedNetworkMutex.withLock {
                val result = adapter.addNetwork(request)
                withContext(coroutineDispatcherProvider.main) {
                    when (result) {
                        is AddNetworkResult.Success -> callbacks?.onSuccessAddingNetwork(result)
                        is AddNetworkResult.Failure -> callbacks?.onFailureAddingNetwork(result)
                    }
                }
            }
        }
    }

    companion object {
        private const val LOG_TAG = "WisefyAddNetworkDelegate"
    }
}
