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
import android.net.Network
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.callbacks.GetCurrentNetworkCallbacks
import com.isupatches.android.wisefy.callbacks.GetCurrentNetworkInfoCallbacks
import com.isupatches.android.wisefy.callbacks.GetIPCallbacks
import com.isupatches.android.wisefy.networkinfo.entities.CurrentNetworkData
import com.isupatches.android.wisefy.networkinfo.entities.CurrentNetworkInfoData

interface NetworkInfoApi {

    fun getCurrentNetwork(): CurrentNetworkData?

    @RequiresPermission(ACCESS_NETWORK_STATE)
    fun getCurrentNetworkInfo(network: Network? = null): CurrentNetworkInfoData?

    fun getIP(): String?
}

interface NetworkInfoApiAsync {

    fun getCurrentNetwork(callbacks: GetCurrentNetworkCallbacks?)

    @RequiresPermission(ACCESS_NETWORK_STATE)
    fun getCurrentNetworkInfo(
        callbacks: GetCurrentNetworkInfoCallbacks?,
        network: Network? = null
    )

    fun getIP(callbacks: GetIPCallbacks?)
}