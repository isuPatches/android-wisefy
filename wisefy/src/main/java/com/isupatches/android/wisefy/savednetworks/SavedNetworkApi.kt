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
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.shared.base.GetSavedNetworksCallbacks
import com.isupatches.android.wisefy.shared.base.SearchForSavedNetworkCallbacks
import com.isupatches.android.wisefy.shared.base.SearchForSavedNetworksCallbacks
import com.isupatches.android.wisefy.savednetworks.entities.SavedNetworkData
import com.isupatches.android.wisefy.savednetworks.entities.SearchForSavedNetworkRequest

interface SavedNetworkApi {
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    fun getSavedNetworks(): List<SavedNetworkData>

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    fun isNetworkSaved(request: SearchForSavedNetworkRequest): Boolean

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    fun searchForSavedNetwork(request: SearchForSavedNetworkRequest): SavedNetworkData?

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    fun searchForSavedNetworks(request: SearchForSavedNetworkRequest): List<SavedNetworkData>
}

interface SavedNetworkApiAsync {
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    fun getSavedNetworks(callbacks: GetSavedNetworksCallbacks?)

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    fun searchForSavedNetwork(request: SearchForSavedNetworkRequest, callbacks: SearchForSavedNetworkCallbacks?)

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    fun searchForSavedNetworks(request: SearchForSavedNetworkRequest, callbacks: SearchForSavedNetworksCallbacks?)
}
