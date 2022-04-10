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
import com.isupatches.android.wisefy.core.coroutines.CoroutineDispatcherProvider
import com.isupatches.android.wisefy.core.coroutines.createBaseCoroutineExceptionHandler
import com.isupatches.android.wisefy.core.logging.WisefyLogger
import com.isupatches.android.wisefy.core.util.SdkUtil
import com.isupatches.android.wisefy.savednetworks.callbacks.GetSavedNetworksCallbacks
import com.isupatches.android.wisefy.savednetworks.callbacks.SearchForSavedNetworkCallbacks
import com.isupatches.android.wisefy.savednetworks.callbacks.SearchForSavedNetworksCallbacks
import com.isupatches.android.wisefy.savednetworks.entities.GetSavedNetworksRequest
import com.isupatches.android.wisefy.savednetworks.entities.GetSavedNetworksResult
import com.isupatches.android.wisefy.savednetworks.entities.IsNetworkSavedRequest
import com.isupatches.android.wisefy.savednetworks.entities.IsNetworkSavedResult
import com.isupatches.android.wisefy.savednetworks.entities.SearchForSavedNetworkRequest
import com.isupatches.android.wisefy.savednetworks.entities.SearchForSavedNetworkResult
import com.isupatches.android.wisefy.savednetworks.entities.SearchForSavedNetworksResult
import com.isupatches.android.wisefy.savednetworks.os.adapters.Android29SavedNetworkAdapter
import com.isupatches.android.wisefy.savednetworks.os.adapters.Android30SavedNetworkAdapter
import com.isupatches.android.wisefy.savednetworks.os.adapters.DefaultSavedNetworkAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WisefySavedNetworkDelegate(
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider,
    private val scope: CoroutineScope,
    logger: WisefyLogger,
    sdkUtil: SdkUtil,
    wifiManager: WifiManager
) : SavedNetworkDelegate {

    companion object {
        private const val LOG_TAG = "WisefySavedNetworkDelegate"
    }

    private val adapter = when {
        sdkUtil.isAtLeastR() -> Android30SavedNetworkAdapter(wifiManager)
        sdkUtil.isAtLeastQ() -> Android29SavedNetworkAdapter()
        else -> DefaultSavedNetworkAdapter(wifiManager)
    }

    init {
        logger.d(LOG_TAG, "WisefySavedNetworkDelegate adapter is: ${adapter::class.java.simpleName}")
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun getSavedNetworks(request: GetSavedNetworksRequest): GetSavedNetworksResult {
        return adapter.getSavedNetworks(request)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun getSavedNetworks(request: GetSavedNetworksRequest, callbacks: GetSavedNetworksCallbacks?) {
        scope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val savedNetworks = adapter.getSavedNetworks(request)
            withContext(coroutineDispatcherProvider.main) {
                when (savedNetworks) {
                    is GetSavedNetworksResult.Empty -> callbacks?.onNoSavedNetworksFound()
                    is GetSavedNetworksResult.SavedNetworks -> {
                        callbacks?.onSavedNetworksRetrieved(savedNetworks.data)
                    }
                }
            }
        }
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun isNetworkSaved(request: IsNetworkSavedRequest): IsNetworkSavedResult {
        return adapter.isNetworkSaved(request)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForSavedNetwork(request: SearchForSavedNetworkRequest): SearchForSavedNetworkResult {
        return adapter.searchForSavedNetwork(request)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForSavedNetwork(
        request: SearchForSavedNetworkRequest,
        callbacks: SearchForSavedNetworkCallbacks?
    ) {
        scope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val result = adapter.searchForSavedNetwork(request)
            withContext(coroutineDispatcherProvider.main) {
                when (result) {
                    is SearchForSavedNetworkResult.Empty -> callbacks?.onSavedNetworkNotFound()
                    is SearchForSavedNetworkResult.SavedNetwork -> callbacks?.onSavedNetworkRetrieved(result.data)
                }
            }
        }
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForSavedNetworks(request: SearchForSavedNetworkRequest): SearchForSavedNetworksResult {
        return adapter.searchForSavedNetworks(request)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForSavedNetworks(
        request: SearchForSavedNetworkRequest,
        callbacks: SearchForSavedNetworksCallbacks?
    ) {
        scope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val result = adapter.searchForSavedNetworks(request)
            withContext(coroutineDispatcherProvider.main) {
                when (result) {
                    is SearchForSavedNetworksResult.Empty -> callbacks?.onNoSavedNetworksFound()
                    is SearchForSavedNetworksResult.SavedNetworks -> callbacks?.onSavedNetworksRetrieved(result.data)
                }
            }
        }
    }
}
