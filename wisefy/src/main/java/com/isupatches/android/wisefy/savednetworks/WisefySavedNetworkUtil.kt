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
package com.isupatches.android.wisefy.savednetworks

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.callbacks.GetSavedNetworksCallbacks
import com.isupatches.android.wisefy.callbacks.SearchForSavedNetworkCallbacks
import com.isupatches.android.wisefy.callbacks.SearchForSavedNetworksCallbacks
import com.isupatches.android.wisefy.logging.WisefyLogger
import com.isupatches.android.wisefy.savednetworks.delegates.Android29SavedNetworkDelegate
import com.isupatches.android.wisefy.savednetworks.delegates.Android30SavedNetworkDelegate
import com.isupatches.android.wisefy.savednetworks.delegates.LegacySavedNetworkDelegate
import com.isupatches.android.wisefy.savednetworks.entities.SavedNetworkData
import com.isupatches.android.wisefy.util.coroutines.CoroutineDispatcherProvider
import com.isupatches.android.wisefy.util.SdkUtil
import com.isupatches.android.wisefy.util.coroutines.createBaseCoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal interface SavedNetworkUtil : SavedNetworkApi, SavedNetworkApiAsync

private const val LOG_TAG = "WisefySavedNetworkUtil"

internal class WisefySavedNetworkUtil(
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider,
    logger: WisefyLogger?,
    sdkUtil: SdkUtil,
    wifiManager: WifiManager
) : SavedNetworkUtil {

    private val delegate = when {
        sdkUtil.isAtLeastR() -> Android30SavedNetworkDelegate(wifiManager)
        sdkUtil.isAtLeastQ() -> Android29SavedNetworkDelegate(logger)
        else -> LegacySavedNetworkDelegate(wifiManager)
    }
    private val savedNetworkScope = CoroutineScope(Job() + coroutineDispatcherProvider.io)

    init {
        logger?.d(LOG_TAG, "WisefySavedNetworkUtil delegate is: ${delegate::class.java.simpleName}")
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun getSavedNetworks(): List<SavedNetworkData> {
        return delegate.getSavedNetworks()
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun getSavedNetworks(callbacks: GetSavedNetworksCallbacks?) {
        savedNetworkScope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val savedNetworks = delegate.getSavedNetworks()
            withContext(coroutineDispatcherProvider.main) {
                if (savedNetworks.isNotEmpty()) {
                    callbacks?.retrievedSavedNetworks(savedNetworks)
                } else {
                    callbacks?.noSavedNetworksFound()
                }
            }
        }
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun isNetworkSaved(ssid: String): Boolean {
        return delegate.isNetworkSaved(ssid)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForSavedNetwork(regexForSSID: String): SavedNetworkData? {
        return delegate.searchForSavedNetwork(regexForSSID)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForSavedNetwork(regexForSSID: String, callbacks: SearchForSavedNetworkCallbacks?) {
        savedNetworkScope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val savedNetwork = delegate.searchForSavedNetwork(regexForSSID)
            withContext(coroutineDispatcherProvider.main) {
                if (savedNetwork != null) {
                    callbacks?.retrievedSavedNetwork(savedNetwork)
                } else {
                    callbacks?.savedNetworkNotFound()
                }
            }
        }
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForSavedNetworks(regexForSSID: String): List<SavedNetworkData> {
        return delegate.searchForSavedNetworks(regexForSSID)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForSavedNetworks(regexForSSID: String, callbacks: SearchForSavedNetworksCallbacks?) {
        savedNetworkScope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val savedNetworks = delegate.searchForSavedNetworks(regexForSSID)
            withContext(coroutineDispatcherProvider.main) {
                if (savedNetworks.isNotEmpty()) {
                    callbacks?.retrievedSavedNetworks(savedNetworks)
                } else {
                    callbacks?.noSavedNetworksFound()
                }
            }
        }
    }
}
