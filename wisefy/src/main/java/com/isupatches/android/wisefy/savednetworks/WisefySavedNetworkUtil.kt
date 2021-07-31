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

import android.Manifest
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.net.wifi.WifiManager
import android.os.Build
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.savednetworks.delegates.Android30SavedNetworkDelegate
import com.isupatches.android.wisefy.savednetworks.delegates.LegacySavedNetworkDelegate
import com.isupatches.android.wisefy.savednetworks.entities.SavedNetworkData

internal interface SavedNetworkUtil : SavedNetworkApi

internal class WisefySavedNetworkUtil(
    wifiManager: WifiManager
) : SavedNetworkUtil {

    private val delegate = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.R -> Android30SavedNetworkDelegate(wifiManager)
        else -> LegacySavedNetworkDelegate(wifiManager)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun getSavedNetworks(): List<SavedNetworkData> {
        return delegate.getSavedNetworks()
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
    override fun searchForSavedNetworks(regexForSSID: String): List<SavedNetworkData> {
        return delegate.searchForSavedNetworks(regexForSSID)
    }
}
