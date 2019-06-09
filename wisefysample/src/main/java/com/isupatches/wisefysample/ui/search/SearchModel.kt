/*
 * Copyright 2019 Patches Klinefelter
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
package com.isupatches.wisefysample.ui.search

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_WIFI_STATE
import androidx.annotation.RequiresPermission

import com.isupatches.wisefy.WiseFyPublicApi
import com.isupatches.wisefy.callbacks.SearchForAccessPointCallbacks
import com.isupatches.wisefy.callbacks.SearchForAccessPointsCallbacks
import com.isupatches.wisefy.callbacks.SearchForSSIDCallbacks
import com.isupatches.wisefy.callbacks.SearchForSSIDsCallbacks
import com.isupatches.wisefy.callbacks.SearchForSavedNetworkCallbacks
import com.isupatches.wisefy.callbacks.SearchForSavedNetworksCallbacks

import javax.inject.Inject

internal class SearchModel @Inject constructor(
    private val wiseFy: WiseFyPublicApi
) : SearchMvp.Model {

    @RequiresPermission(allOf = [ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForAccessPoint(
        regexForSSID: String,
        timeout: Int,
        filterDuplicates: Boolean,
        callbacks: SearchForAccessPointCallbacks
    ) {
        wiseFy.searchForAccessPoint(regexForSSID, timeout, filterDuplicates, callbacks)
    }

    @RequiresPermission(allOf = [ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForAccessPoints(
        regexForSSID: String,
        filterDuplicates: Boolean,
        callbacks: SearchForAccessPointsCallbacks
    ) {
        wiseFy.searchForAccessPoints(regexForSSID, filterDuplicates, callbacks)
    }

    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun searchForSavedNetwork(
        regexForSSID: String,
        callbacks: SearchForSavedNetworkCallbacks
    ) {
        wiseFy.searchForSavedNetwork(regexForSSID, callbacks)
    }

    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun searchForSavedNetworks(
        regexForSSID: String,
        callbacks: SearchForSavedNetworksCallbacks
    ) {
        wiseFy.searchForSavedNetworks(regexForSSID, callbacks)
    }

    @RequiresPermission(allOf = [ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForSSID(
        regexForSSID: String,
        timeout: Int,
        callbacks: SearchForSSIDCallbacks
    ) {
        wiseFy.searchForSSID(regexForSSID, timeout, callbacks)
    }

    @RequiresPermission(allOf = [ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForSSIDs(regexForSSID: String, callbacks: SearchForSSIDsCallbacks) {
        wiseFy.searchForSSIDs(regexForSSID, callbacks)
    }
}
