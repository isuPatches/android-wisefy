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
package com.isupatches.android.wisefy.addnetwork

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.CHANGE_WIFI_STATE
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.addnetwork.callbacks.AddNetworkCallbacks
import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkResult
import com.isupatches.android.wisefy.addnetwork.entities.AddOpenNetworkRequest
import com.isupatches.android.wisefy.addnetwork.entities.AddWPA2NetworkRequest
import com.isupatches.android.wisefy.addnetwork.entities.AddWPA3NetworkRequest

interface AddNetworkApi {

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    fun addOpenNetwork(request: AddOpenNetworkRequest): AddNetworkResult

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    fun addWPA2Network(request: AddWPA2NetworkRequest): AddNetworkResult

    @RequiresApi(Build.VERSION_CODES.Q)
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    fun addWPA3Network(request: AddWPA3NetworkRequest): AddNetworkResult
}

interface AddNetworkApiAsync {

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    fun addOpenNetwork(request: AddOpenNetworkRequest, callbacks: AddNetworkCallbacks?)

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    fun addWPA2Network(request: AddWPA2NetworkRequest, callbacks: AddNetworkCallbacks?)

    @RequiresApi(Build.VERSION_CODES.Q)
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    fun addWPA3Network(request: AddWPA3NetworkRequest, callbacks: AddNetworkCallbacks?)
}
