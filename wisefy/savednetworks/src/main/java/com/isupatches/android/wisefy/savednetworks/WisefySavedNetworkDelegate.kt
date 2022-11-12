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
package com.isupatches.android.wisefy.savednetworks

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.core.assertions.WisefyAssertions
import com.isupatches.android.wisefy.core.coroutines.CoroutineDispatcherProvider
import com.isupatches.android.wisefy.core.coroutines.createBaseCoroutineExceptionHandler
import com.isupatches.android.wisefy.core.logging.WisefyLogger
import com.isupatches.android.wisefy.core.util.SdkUtil
import com.isupatches.android.wisefy.savednetworks.callbacks.GetSavedNetworksCallbacks
import com.isupatches.android.wisefy.savednetworks.callbacks.IsNetworkSavedCallbacks
import com.isupatches.android.wisefy.savednetworks.entities.GetSavedNetworksQuery
import com.isupatches.android.wisefy.savednetworks.entities.GetSavedNetworksResult
import com.isupatches.android.wisefy.savednetworks.entities.IsNetworkSavedQuery
import com.isupatches.android.wisefy.savednetworks.entities.IsNetworkSavedResult
import com.isupatches.android.wisefy.savednetworks.os.adapters.Android29SavedNetworkAdapter
import com.isupatches.android.wisefy.savednetworks.os.adapters.Android30SavedNetworkAdapter
import com.isupatches.android.wisefy.savednetworks.os.adapters.DefaultSavedNetworkAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

/**
 * An internal Wisefy delegate for getting and searching for saved networks.
 *
 * *Notes*
 *  - These functions share a mutex with add/remove network
 *
 * @param coroutineDispatcherProvider The CoroutineDispatcherProvider instance to use
 * @param scope The CoroutineScope to use
 * @param savedNetworkMutex A mutex shared with add/remove network to ensure synchronization between saved network
 *  reads and writes
 * @param assertions The [WisefyAssertions] instance to use
 * @param logger The [WisefyLogger] instance to use
 * @param sdkUtil The [SdkUtil] instance to use
 * @param wifiManager The WifiManager instance to use
 *
 * @see CoroutineDispatcherProvider
 * @see WisefyAssertions
 * @see WisefyLogger
 * @see SdkUtil
 *
 * @author Patches Klinefelter
 * @since 07/2022, version 5.0.0
 */
class WisefySavedNetworkDelegate(
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider,
    private val scope: CoroutineScope,
    private val savedNetworkMutex: Mutex,
    assertions: WisefyAssertions,
    logger: WisefyLogger,
    sdkUtil: SdkUtil,
    wifiManager: WifiManager
) : SavedNetworkDelegate {

    companion object {
        private const val LOG_TAG = "WisefySavedNetworkDelegate"
    }

    private val adapter = when {
        sdkUtil.isAtLeastR() -> Android30SavedNetworkAdapter(wifiManager)
        sdkUtil.isAtLeastQ() -> Android29SavedNetworkAdapter(assertions)
        else -> DefaultSavedNetworkAdapter(wifiManager)
    }

    init {
        logger.d(LOG_TAG, "WisefySavedNetworkDelegate adapter is: ${adapter::class.java.simpleName}")
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun getSavedNetworks(query: GetSavedNetworksQuery): GetSavedNetworksResult {
        return adapter.getSavedNetworks(query)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun getSavedNetworks(query: GetSavedNetworksQuery, callbacks: GetSavedNetworksCallbacks?) {
        scope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            savedNetworkMutex.withLock {
                val result = adapter.getSavedNetworks(query)
                withContext(coroutineDispatcherProvider.main) {
                    when (result) {
                        is GetSavedNetworksResult.Empty -> callbacks?.onNoSavedNetworksFound()
                        is GetSavedNetworksResult.SavedNetworks -> {
                            callbacks?.onSavedNetworksRetrieved(result.value)
                        }
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
    override fun isNetworkSaved(query: IsNetworkSavedQuery, callbacks: IsNetworkSavedCallbacks?) {
        scope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            savedNetworkMutex.withLock {
                val result = adapter.isNetworkSaved(query)
                withContext(coroutineDispatcherProvider.main) {
                    when (result) {
                        is IsNetworkSavedResult.True -> callbacks?.onNetworkIsSaved()
                        is IsNetworkSavedResult.False -> callbacks?.onNetworkIsNotSaved()
                    }
                }
            }
        }
    }
}
