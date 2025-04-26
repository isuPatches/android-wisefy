/*
 * Copyright 2025 Patches Barrett
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
import com.isupatches.android.wisefy.core.assertions.NoOpWisefyAssertions
import com.isupatches.android.wisefy.core.assertions.WisefyAssertions
import com.isupatches.android.wisefy.core.exceptions.WisefyException
import com.isupatches.android.wisefy.core.logging.NoOpWisefyLogger
import com.isupatches.android.wisefy.core.logging.WisefyLogger
import com.isupatches.android.wisefy.core.util.AndroidUtil
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlin.coroutines.cancellation.CancellationException

/**
 * An internal Wisefy delegate for adding networks.
 *
 * @param wifiManager The WifiManager instance to use
 * @param scope The coroutine scope to use
 * @param savedNetworkMutex The mutex for all read/write operations involving saved networks
 * @param assertions The [WisefyAssertions] instance to use (defaults to no-op)
 * @param logger The [WisefyLogger] instance to use (defaults to no-op)
 * @param mainDispatcher The main thread dispatcher
 * @param adapter The adapter instance to use for adding a network (determined based on the Android OS level)
 *
 * @see AddNetworkApi
 * @see AddNetworkDelegate
 * @see Android30AddNetworkAdapter
 * @see Android29AddNetworkAdapter
 * @see DefaultAddNetworkAdapter
 * @see WisefyAssertions
 * @see WisefyLogger
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
class WisefyAddNetworkDelegate(
    wifiManager: WifiManager,
    private val scope: CoroutineScope,
    private val savedNetworkMutex: Mutex,
    assertions: WisefyAssertions = NoOpWisefyAssertions(),
    logger: WisefyLogger = NoOpWisefyLogger(),
    private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main,
    private val adapter: AddNetworkApi = when {
        AndroidUtil.isAtLeastR() -> Android30AddNetworkAdapter(wifiManager, logger, assertions)
        AndroidUtil.isAtLeastQ() -> Android29AddNetworkAdapter(assertions)
        else -> DefaultAddNetworkAdapter(wifiManager, logger, assertions)
    },
) : AddNetworkDelegate {

    init {
        logger.d(LOG_TAG, "WisefyAddNetworkDelegate adapter is: ${adapter::class.java.simpleName}")
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addNetwork(request: AddNetworkRequest): AddNetworkResult {
        return adapter.addNetwork(request)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    @Suppress("TooGenericExceptionCaught")
    override fun addNetwork(
        request: AddNetworkRequest,
        callbacks: AddNetworkCallbacks?,
    ) {
        scope.launch {
            savedNetworkMutex.withLock {
                try {
                    val result = adapter.addNetwork(request)
                    withContext(mainDispatcher) {
                        when (result) {
                            is AddNetworkResult.Success -> callbacks?.onSuccessAddingNetwork(result)
                            is AddNetworkResult.Failure -> callbacks?.onFailureAddingNetwork(result)
                        }
                    }
                } catch (ex: CancellationException) {
                    throw ex
                } catch (ex: Exception) {
                    withContext(mainDispatcher) {
                        callbacks?.onWisefyAsyncFailure(
                            WisefyException(message = "Internal Wisefy error with addNetwork", throwable = ex),
                        )
                    }
                }
            }
        }
    }

    companion object {
        private const val LOG_TAG = "WisefyAddNetworkDelegate"
    }
}
