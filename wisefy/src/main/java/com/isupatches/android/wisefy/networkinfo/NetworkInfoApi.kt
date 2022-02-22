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
package com.isupatches.android.wisefy.networkinfo

import android.Manifest.permission.ACCESS_NETWORK_STATE
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.callbacks.GetCurrentNetworkCallbacks
import com.isupatches.android.wisefy.callbacks.GetCurrentNetworkInfoCallbacks
import com.isupatches.android.wisefy.networkinfo.entities.CurrentNetworkData
import com.isupatches.android.wisefy.networkinfo.entities.CurrentNetworkInfoData
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkInfoRequest
import com.isupatches.android.wisefy.networkinfo.entities.IPData

interface NetworkInfoApi {

    fun getCurrentNetwork(): CurrentNetworkData?

    @RequiresPermission(ACCESS_NETWORK_STATE)
    fun getCurrentNetworkInfo(
        request: GetCurrentNetworkInfoRequest = GetCurrentNetworkInfoRequest()
    ): CurrentNetworkInfoData?

    @RequiresPermission(ACCESS_NETWORK_STATE)
    fun getIP(): IPData?
}

interface NetworkInfoApiAsync {

    fun getCurrentNetwork(callbacks: GetCurrentNetworkCallbacks?)

    @RequiresPermission(ACCESS_NETWORK_STATE)
    fun getCurrentNetworkInfo(
        callbacks: GetCurrentNetworkInfoCallbacks?,
        request: GetCurrentNetworkInfoRequest = GetCurrentNetworkInfoRequest()
    )

    @RequiresPermission(ACCESS_NETWORK_STATE)
    fun getIP(callbacks: GetIPCallbacks?)
}
