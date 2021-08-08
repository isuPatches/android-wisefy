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
package com.isupatches.android.wisefy.sample.ui.misc

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.WisefyApi
import com.isupatches.android.wisefy.callbacks.GetFrequencyCallbacks
import com.isupatches.android.wisefy.callbacks.GetNearbyAccessPointCallbacks
import com.isupatches.android.wisefy.networkinfo.entities.CurrentNetworkData
import com.isupatches.android.wisefy.networkinfo.entities.CurrentNetworkInfoData
import com.isupatches.android.wisefy.sample.internal.scaffolding.BaseModel
import com.isupatches.android.wisefy.savednetworks.entities.SavedNetworkData
import javax.inject.Inject

internal interface MiscModel {

    fun disableWifi()

    fun enableWifi()

    fun getCurrentNetwork(): CurrentNetworkData?

    fun getCurrentNetworkInfo(): CurrentNetworkInfoData?

    @RequiresPermission(ACCESS_FINE_LOCATION)
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun getFrequency(callbacks: GetFrequencyCallbacks?)

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun getIP(): String

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun getNearbyAccessPoints(callbacks: GetNearbyAccessPointCallbacks?)

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun getSavedNetworks(): List<SavedNetworkData>
}

@MiscScope
internal class DefaultMiscModel @Inject constructor(
    private val wiseFy: WisefyApi
) : BaseModel(), MiscModel {

    override fun disableWifi() {
        wiseFy.disableWifi()
    }

    override fun enableWifi() {
        wiseFy.enableWifi()
    }

    override fun getCurrentNetwork(): CurrentNetworkData? {
        return wiseFy.getCurrentNetwork()
    }

    override fun getCurrentNetworkInfo(): CurrentNetworkInfoData? {
        return wiseFy.getCurrentNetworkInfo()
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun getFrequency(callbacks: GetFrequencyCallbacks?) {
        wiseFy.getFrequency(callbacks)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getIP(): String {
        return wiseFy.getIP() ?: ""
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getNearbyAccessPoints(callbacks: GetNearbyAccessPointCallbacks?) {
        return wiseFy.getNearbyAccessPoints(true, callbacks)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun getSavedNetworks(): List<SavedNetworkData> {
        return wiseFy.getSavedNetworks()
    }
}
