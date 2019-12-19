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
package com.isupatches.wisefysample.ui.misc

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.Manifest.permission.CHANGE_WIFI_STATE
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import com.isupatches.wisefy.WiseFyPublicApi
import com.isupatches.wisefy.callbacks.DisableWifiCallbacks
import com.isupatches.wisefy.callbacks.EnableWifiCallbacks
import com.isupatches.wisefy.callbacks.GetCurrentNetworkCallbacks
import com.isupatches.wisefy.callbacks.GetCurrentNetworkInfoCallbacks
import com.isupatches.wisefy.callbacks.GetFrequencyCallbacks
import com.isupatches.wisefy.callbacks.GetIPCallbacks
import com.isupatches.wisefy.callbacks.GetNearbyAccessPointsCallbacks
import com.isupatches.wisefy.callbacks.GetSavedNetworksCallbacks
import javax.inject.Inject

internal class MiscModel @Inject constructor(
    private val wiseFy: WiseFyPublicApi
) : MiscMvp.Model {

    @RequiresPermission(allOf = [CHANGE_WIFI_STATE])
    override fun disableWifi(callbacks: DisableWifiCallbacks) {
        wiseFy.disableWifi(callbacks)
    }

    @RequiresPermission(allOf = [CHANGE_WIFI_STATE])
    override fun enableWifi(callbacks: EnableWifiCallbacks) {
        wiseFy.enableWifi(callbacks)
    }

    @RequiresPermission(allOf = [ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE])
    override fun getCurrentNetwork(callbacks: GetCurrentNetworkCallbacks) {
        wiseFy.getCurrentNetwork(callbacks)
    }

    @RequiresPermission(allOf = [ACCESS_NETWORK_STATE])
    override fun getCurrentNetworkInfo(callbacks: GetCurrentNetworkInfoCallbacks) {
        wiseFy.getCurrentNetworkInfo(callbacks)
    }

    @RequiresPermission(allOf = [ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE])
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun getFrequency(callbacks: GetFrequencyCallbacks) {
        wiseFy.getFrequency(callbacks)
    }

    @RequiresPermission(allOf = [ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE])
    override fun getIP(callbacks: GetIPCallbacks) {
        wiseFy.getIP(callbacks)
    }

    @RequiresPermission(allOf = [ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE])
    override fun getNearbyAccessPoints(callbacks: GetNearbyAccessPointsCallbacks) {
        wiseFy.getNearbyAccessPoints(true, callbacks)
    }

    @RequiresPermission(allOf = [ACCESS_WIFI_STATE])
    override fun getSavedNetworks(callbacks: GetSavedNetworksCallbacks) {
        wiseFy.getSavedNetworks(callbacks)
    }
}
