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
package com.isupatches.android.wisefy.savednetworks.delegates

import android.Manifest.permission.ACCESS_WIFI_STATE
import android.net.wifi.WifiManager
import android.net.wifi.WifiNetworkSuggestion
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.constants.QUOTE
import com.isupatches.android.wisefy.savednetworks.entities.SavedNetworkData

internal interface Android30SavedNetworkApi {
    @RequiresApi(Build.VERSION_CODES.R)
    @RequiresPermission(ACCESS_WIFI_STATE)
    fun getSavedNetworks(): List<SavedNetworkData>

    @RequiresApi(Build.VERSION_CODES.R)
    @RequiresPermission(ACCESS_WIFI_STATE)
    fun isNetworkSaved(ssid: String): Boolean

    @RequiresApi(Build.VERSION_CODES.R)
    @RequiresPermission(ACCESS_WIFI_STATE)
    fun searchForSavedNetwork(regexForSSID: String): SavedNetworkData

    @RequiresApi(Build.VERSION_CODES.R)
    @RequiresPermission(ACCESS_WIFI_STATE)
    fun searchForSavedNetworks(regexForSSID: String): List<SavedNetworkData>
}

internal class Android30SavedNetworkApiImpl(
    private val wifiManager: WifiManager
) : Android30SavedNetworkApi {

    @RequiresApi(Build.VERSION_CODES.R)
    @RequiresPermission(allOf = [ACCESS_WIFI_STATE])
    override fun getSavedNetworks(): List<SavedNetworkData> {
        return wifiManager.networkSuggestions.map {
            SavedNetworkData.Suggestion(data = it)
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    @RequiresPermission(allOf = [ACCESS_WIFI_STATE])
    override fun isNetworkSaved(ssid: String): Boolean {
        return wifiManager.networkSuggestions.any {
            it.ssid.equals(ssid)
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    @RequiresPermission(allOf = [ACCESS_WIFI_STATE])
    override fun searchForSavedNetwork(regexForSSID: String): SavedNetworkData {
        val savedNetwork = wifiManager.networkSuggestions.firstOrNull {
            matchesRegexForSSID(it, regexForSSID)
        }
        return SavedNetworkData.Suggestion(data = savedNetwork)
    }

    @RequiresApi(Build.VERSION_CODES.R)
    @RequiresPermission(allOf = [ACCESS_WIFI_STATE])
    override fun searchForSavedNetworks(regexForSSID: String): List<SavedNetworkData> {
        return wifiManager.networkSuggestions.filter {
            matchesRegexForSSID(it, regexForSSID)
        }.map { savedNetwork ->
            SavedNetworkData.Suggestion(data = savedNetwork)
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun matchesRegexForSSID(suggestion: WifiNetworkSuggestion, regexForSSID: String): Boolean {
        return suggestion.ssid?.replace(QUOTE, "")?.matches(regexForSSID.toRegex()) == true ||
            suggestion.ssid?.matches(regexForSSID.toRegex()) == true
    }
}
