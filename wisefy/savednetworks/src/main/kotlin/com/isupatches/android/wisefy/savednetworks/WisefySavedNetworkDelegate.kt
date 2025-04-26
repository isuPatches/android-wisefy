/*
 * Copyright (c) 2024. Patches Barrett
 *
 * Last modified: September 22, 2024
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
package com.isupatches.android.wisefy.savednetworks

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.core.assertions.NoOpWisefyAssertions
import com.isupatches.android.wisefy.core.assertions.WisefyAssertions
import com.isupatches.android.wisefy.core.exceptions.WisefyException
import com.isupatches.android.wisefy.core.logging.NoOpWisefyLogger
import com.isupatches.android.wisefy.core.logging.WisefyLogger
import com.isupatches.android.wisefy.core.util.AndroidUtil
import com.isupatches.android.wisefy.savednetworks.callbacks.GetSavedNetworksCallbacks
import com.isupatches.android.wisefy.savednetworks.callbacks.IsNetworkSavedCallbacks
import com.isupatches.android.wisefy.savednetworks.entities.GetSavedNetworksQuery
import com.isupatches.android.wisefy.savednetworks.entities.GetSavedNetworksResult
import com.isupatches.android.wisefy.savednetworks.entities.IsNetworkSavedQuery
import com.isupatches.android.wisefy.savednetworks.entities.IsNetworkSavedResult
import com.isupatches.android.wisefy.savednetworks.os.adapters.Android29SavedNetworkAdapter
import com.isupatches.android.wisefy.savednetworks.os.adapters.Android30SavedNetworkAdapter
import com.isupatches.android.wisefy.savednetworks.os.adapters.DefaultSavedNetworkAdapter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlin.coroutines.cancellation.CancellationException

/**
 * An internal Wisefy delegate for getting and searching for saved networks.
 *
 * *Notes*
 *  - These functions share a mutex with add/remove network
 *
 * @param wifiManager The WifiManager instance to use
 * @param scope The CoroutineScope to use
 * @param savedNetworkMutex A mutex shared with add/remove network to ensure synchronization between saved network
 *  reads and writes
 * @param assertions The [WisefyAssertions] instance to use (defaults to no-op)
 * @param logger The [WisefyLogger] instance to use (defaults to no-op)
 * @param mainDispatcher The main thread dispatcher
 * @param adapter The adapter instance to use for querying for saved networks and checking if a network is saved
 * (determined based on the Android OS level)
 *
 * @see Android29SavedNetworkAdapter
 * @see Android30SavedNetworkAdapter
 * @see DefaultSavedNetworkAdapter
 * @see WisefyAssertions
 * @see WisefyLogger
 * @see SavedNetworkApi
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
class WisefySavedNetworkDelegate(
    wifiManager: WifiManager,
    private val scope: CoroutineScope,
    private val savedNetworkMutex: Mutex,
    assertions: WisefyAssertions = NoOpWisefyAssertions(),
    logger: WisefyLogger = NoOpWisefyLogger(),
    private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main,
    private val adapter: SavedNetworkApi = when {
        AndroidUtil.isAtLeastR() -> Android30SavedNetworkAdapter(wifiManager, logger)
        AndroidUtil.isAtLeastQ() -> Android29SavedNetworkAdapter(assertions)
        else -> DefaultSavedNetworkAdapter(wifiManager, logger)
    },
) : SavedNetworkDelegate {

    init {
        logger.d(LOG_TAG, "WisefySavedNetworkDelegate adapter is: ${adapter::class.java.simpleName}")
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun getSavedNetworks(query: GetSavedNetworksQuery): GetSavedNetworksResult {
        return adapter.getSavedNetworks(query)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    @Suppress("TooGenericExceptionCaught")
    override fun getSavedNetworks(
        query: GetSavedNetworksQuery,
        callbacks: GetSavedNetworksCallbacks?,
    ) {
        scope.launch {
            savedNetworkMutex.withLock {
                try {
                    val result = adapter.getSavedNetworks(query)
                    withContext(mainDispatcher) {
                        when (result) {
                            is GetSavedNetworksResult.Empty -> {
                                callbacks?.onNoSavedNetworksFound()
                            }

                            is GetSavedNetworksResult.SavedNetworks -> {
                                callbacks?.onSavedNetworksRetrieved(result.value)
                            }
                        }
                    }
                } catch (ex: CancellationException) {
                    throw ex
                } catch (ex: Exception) {
                    withContext(mainDispatcher) {
                        callbacks?.onWisefyAsyncFailure(
                            WisefyException(message = "Internal Wisefy error with getSavedNetworks", throwable = ex),
                        )
                    }
                }
            }
        }
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun isNetworkSaved(query: IsNetworkSavedQuery): IsNetworkSavedResult {
        return adapter.isNetworkSaved(query)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    @Suppress("TooGenericExceptionCaught")
    override fun isNetworkSaved(
        query: IsNetworkSavedQuery,
        callbacks: IsNetworkSavedCallbacks?,
    ) {
        scope.launch {
            savedNetworkMutex.withLock {
                try {
                    val result = adapter.isNetworkSaved(query)
                    withContext(mainDispatcher) {
                        when (result) {
                            is IsNetworkSavedResult.True -> callbacks?.onNetworkIsSaved()
                            is IsNetworkSavedResult.False -> callbacks?.onNetworkIsNotSaved()
                        }
                    }
                } catch (ex: CancellationException) {
                    throw ex
                } catch (ex: Exception) {
                    withContext(mainDispatcher) {
                        callbacks?.onWisefyAsyncFailure(
                            WisefyException(message = "Internal Wisefy error with isNetworkSaved", throwable = ex),
                        )
                    }
                }
            }
        }
    }

    companion object {
        private const val LOG_TAG = "WisefySavedNetworkDelegate"
    }
}
