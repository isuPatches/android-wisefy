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
package com.isupatches.android.wisefy.savednetworks.os.impls

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.net.wifi.WifiManager
import android.net.wifi.WifiNetworkSuggestion
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.core.entities.QUOTE
import com.isupatches.android.wisefy.savednetworks.os.apis.Android30SavedNetworkApi

@RequiresApi(Build.VERSION_CODES.R)
internal class Android30SavedNetworkApiImpl(
    private val wifiManager: WifiManager
) : Android30SavedNetworkApi {

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun getSavedNetworks(): List<WifiNetworkSuggestion> {
        return wifiManager.networkSuggestions
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun isNetworkSavedWithSSID(regexForSSID: String): Boolean {
        return searchForSavedNetworksBySSID(regexForSSID).any()
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun isNetworkSavedWithBSSID(regexForBSSID: String): Boolean {
        return searchForSavedNetworksByBSSID(regexForBSSID).any()
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForSavedNetworkBySSID(regexForSSID: String): WifiNetworkSuggestion? {
        return searchForSavedNetworksBySSID(regexForSSID).firstOrNull()
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForSavedNetworkByBSSID(regexForBSSID: String): WifiNetworkSuggestion? {
        return searchForSavedNetworksByBSSID(regexForBSSID).firstOrNull()
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForSavedNetworksBySSID(regexForSSID: String): List<WifiNetworkSuggestion> {
        return wifiManager.networkSuggestions.filter { configuration ->
            configuration.ssid?.replace(QUOTE, "")?.matches(regexForSSID.toRegex()) == true ||
                configuration.ssid?.matches(regexForSSID.toRegex()) == true
        }
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForSavedNetworksByBSSID(regexForBSSID: String): List<WifiNetworkSuggestion> {
        return wifiManager.networkSuggestions.filter { configuration ->
            configuration.bssid?.toString()?.replace(QUOTE, "")?.matches(regexForBSSID.toRegex()) == true ||
                configuration.bssid?.toString()?.matches(regexForBSSID.toRegex()) == true
        }
    }
}
