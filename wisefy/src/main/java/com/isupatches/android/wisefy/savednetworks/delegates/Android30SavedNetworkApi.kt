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
import com.isupatches.android.wisefy.savednetworks.entities.SavedNetworkData
import com.isupatches.android.wisefy.savednetworks.entities.SearchForSavedNetworkRequest
import com.isupatches.android.wisefy.shared.entities.QUOTE

internal interface Android30SavedNetworkApi {
    @RequiresApi(Build.VERSION_CODES.R)
    @RequiresPermission(ACCESS_WIFI_STATE)
    fun getSavedNetworks(): List<SavedNetworkData>

    @RequiresApi(Build.VERSION_CODES.R)
    @RequiresPermission(ACCESS_WIFI_STATE)
    fun isNetworkSaved(request: SearchForSavedNetworkRequest): Boolean

    @RequiresApi(Build.VERSION_CODES.R)
    @RequiresPermission(ACCESS_WIFI_STATE)
    fun searchForSavedNetwork(request: SearchForSavedNetworkRequest): SavedNetworkData?

    @RequiresApi(Build.VERSION_CODES.R)
    @RequiresPermission(ACCESS_WIFI_STATE)
    fun searchForSavedNetworks(request: SearchForSavedNetworkRequest): List<SavedNetworkData>
}

internal class Android30SavedNetworkApiImpl(
    private val wifiManager: WifiManager
) : Android30SavedNetworkApi {

    @RequiresApi(Build.VERSION_CODES.R)
    @RequiresPermission(allOf = [ACCESS_WIFI_STATE])
    override fun getSavedNetworks(): List<SavedNetworkData> {
        return wifiManager.networkSuggestions.map {
            SavedNetworkData.Suggestion(value = it)
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    @RequiresPermission(allOf = [ACCESS_WIFI_STATE])
    override fun isNetworkSaved(request: SearchForSavedNetworkRequest): Boolean {
        return wifiManager.networkSuggestions.any {
            when (request) {
                is SearchForSavedNetworkRequest.SSID -> it.ssid.equals(request.regexForSSID)
                is SearchForSavedNetworkRequest.BSSID -> it.ssid.equals(request.regexForBSSID)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    @RequiresPermission(allOf = [ACCESS_WIFI_STATE])
    override fun searchForSavedNetwork(request: SearchForSavedNetworkRequest): SavedNetworkData? {
        val savedNetwork = wifiManager.networkSuggestions.firstOrNull {
            matchesRegexForSearch(it, request)
        }
        return if (savedNetwork != null) {
            SavedNetworkData.Suggestion(value = savedNetwork)
        } else {
            null
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    @RequiresPermission(allOf = [ACCESS_WIFI_STATE])
    override fun searchForSavedNetworks(request: SearchForSavedNetworkRequest): List<SavedNetworkData> {
        return wifiManager.networkSuggestions.filter {
            matchesRegexForSearch(it, request)
        }.map { savedNetwork ->
            SavedNetworkData.Suggestion(value = savedNetwork)
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun matchesRegexForSearch(
        suggestion: WifiNetworkSuggestion,
        request: SearchForSavedNetworkRequest
    ): Boolean {
        return when (request) {
            is SearchForSavedNetworkRequest.SSID -> {
                suggestion.ssid?.replace(QUOTE, "")?.matches(request.regexForSSID.toRegex()) == true ||
                    suggestion.ssid?.matches(request.regexForSSID.toRegex()) == true
            }
            is SearchForSavedNetworkRequest.BSSID -> {
                suggestion.bssid?.toString()?.replace(QUOTE, "")?.matches(request.regexForBSSID.toRegex()) == true ||
                    suggestion.bssid?.toString()?.matches(request.regexForBSSID.toRegex()) == true
            }
        }
    }
}
