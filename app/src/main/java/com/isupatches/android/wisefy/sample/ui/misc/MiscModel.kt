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

import android.Manifest
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.net.NetworkInfo
import android.net.wifi.WifiInfo
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.WisefyApi
import com.isupatches.android.wisefy.accesspoints.entities.AccessPointData
import com.isupatches.android.wisefy.sample.internal.scaffolding.BaseModel
import com.isupatches.android.wisefy.savednetworks.entities.SavedNetworkData
import javax.inject.Inject

internal interface MiscModel {

    fun disableWifi()

    fun enableWifi()

    fun getCurrentNetwork(): WifiInfo?

    fun getCurrentNetworkInfo(): NetworkInfo?

    @RequiresPermission(ACCESS_FINE_LOCATION)
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun getFrequency(): Int

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun getIP(): String

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun getNearbyAccessPoints(): List<AccessPointData>

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

    override fun getCurrentNetwork(): WifiInfo? {
        return wiseFy.getCurrentNetwork()
    }

    override fun getCurrentNetworkInfo(): NetworkInfo? {
        return wiseFy.getCurrentNetworkInfo()
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun getFrequency(): Int {
        return wiseFy.getFrequency() ?: -1
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getIP(): String {
        return wiseFy.getIP() ?: ""
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getNearbyAccessPoints(): List<AccessPointData> {
        return wiseFy.getNearbyAccessPoints(true)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun getSavedNetworks(): List<SavedNetworkData> {
        return wiseFy.getSavedNetworks()
    }
}
