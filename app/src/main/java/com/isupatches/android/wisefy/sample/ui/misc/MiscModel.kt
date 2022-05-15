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
package com.isupatches.android.wisefy.sample.ui.misc

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.ACCESS_WIFI_STATE
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.WisefyApi
import com.isupatches.android.wisefy.accesspoints.callbacks.GetNearbyAccessPointCallbacks
import com.isupatches.android.wisefy.accesspoints.entities.GetNearbyAccessPointsRequest
import com.isupatches.android.wisefy.frequency.callbacks.GetFrequencyCallbacks
import com.isupatches.android.wisefy.networkinfo.callbacks.GetCurrentNetworkCallbacks
import com.isupatches.android.wisefy.networkinfo.callbacks.GetIPCallbacks
import com.isupatches.android.wisefy.networkinfo.callbacks.GetNetworkInfoCallbacks
import com.isupatches.android.wisefy.sample.internal.scaffolding.BaseModel
import com.isupatches.android.wisefy.savednetworks.callbacks.GetSavedNetworksCallbacks
import com.isupatches.android.wisefy.wifi.callbacks.DisableWifiCallbacks
import com.isupatches.android.wisefy.wifi.callbacks.EnableWifiCallbacks
import javax.inject.Inject

internal interface MiscModel {

    fun disableWifi(callbacks: DisableWifiCallbacks?)

    fun enableWifi(callbacks: EnableWifiCallbacks?)

    fun getCurrentNetwork(callbacks: GetCurrentNetworkCallbacks?)

    fun getCurrentNetworkInfo(callbacks: GetNetworkInfoCallbacks?)

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun getFrequency(callbacks: GetFrequencyCallbacks?)

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun getIP(callbacks: GetIPCallbacks?)

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun getNearbyAccessPoints(callbacks: GetNearbyAccessPointCallbacks?)

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun getSavedNetworks(callbacks: GetSavedNetworksCallbacks?)
}

@MiscScope
internal class DefaultMiscModel @Inject constructor(
    private val wiseFy: WisefyApi
) : BaseModel(), MiscModel {

    override fun disableWifi(callbacks: DisableWifiCallbacks?) {
        wiseFy.disableWifi(callbacks = callbacks)
    }

    override fun enableWifi(callbacks: EnableWifiCallbacks?) {
        wiseFy.enableWifi(callbacks = callbacks)
    }

    override fun getCurrentNetwork(callbacks: GetCurrentNetworkCallbacks?) {
        wiseFy.getCurrentNetwork(callbacks = callbacks)
    }

    override fun getCurrentNetworkInfo(callbacks: GetNetworkInfoCallbacks?) {
        wiseFy.getNetworkInfo(callbacks = callbacks)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getFrequency(callbacks: GetFrequencyCallbacks?) {
        wiseFy.getFrequency(callbacks = callbacks)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getIP(callbacks: GetIPCallbacks?) {
        wiseFy.getIP(callbacks = callbacks)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getNearbyAccessPoints(callbacks: GetNearbyAccessPointCallbacks?) {
        return wiseFy.getNearbyAccessPoints(
            request = GetNearbyAccessPointsRequest(),
            callbacks = callbacks
        )
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun getSavedNetworks(callbacks: GetSavedNetworksCallbacks?) {
        wiseFy.getSavedNetworks(callbacks = callbacks)
    }
}
