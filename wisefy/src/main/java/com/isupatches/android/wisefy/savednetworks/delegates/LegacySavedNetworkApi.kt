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

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.constants.QUOTE
import com.isupatches.android.wisefy.savednetworks.entities.SavedNetworkData
import com.isupatches.android.wisefy.savednetworks.entities.SearchForSavedNetworkRequest

internal interface LegacySavedNetworkApi {
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    fun getSavedNetworks(): List<SavedNetworkData>

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    fun isNetworkSaved(request: SearchForSavedNetworkRequest): Boolean

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    fun searchForSavedNetwork(request: SearchForSavedNetworkRequest): SavedNetworkData?

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    fun searchForSavedNetworks(request: SearchForSavedNetworkRequest): List<SavedNetworkData>
}

internal class LegacySavedNetworkApiImpl(
    private val wifiManager: WifiManager
) : LegacySavedNetworkApi {

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun getSavedNetworks(): List<SavedNetworkData> {
        return wifiManager.configuredNetworks.map {
            SavedNetworkData.Configuration(value = it)
        }
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun isNetworkSaved(request: SearchForSavedNetworkRequest): Boolean {
        return wifiManager.configuredNetworks.any {
            it.SSID.equals(request)
        }
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForSavedNetwork(request: SearchForSavedNetworkRequest): SavedNetworkData? {
        val savedNetwork = wifiManager.configuredNetworks.firstOrNull {
            matchesRegexForSearch(it, request)
        }
        return if (savedNetwork != null) {
            SavedNetworkData.Configuration(value = savedNetwork)
        } else {
            null
        }
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForSavedNetworks(request: SearchForSavedNetworkRequest): List<SavedNetworkData> {
        return wifiManager.configuredNetworks.filter {
            matchesRegexForSearch(it, request)
        }.map { savedNetwork ->
            SavedNetworkData.Configuration(value = savedNetwork)
        }
    }

    private fun matchesRegexForSearch(
        configuration: WifiConfiguration,
        request: SearchForSavedNetworkRequest
    ): Boolean {
        return when (request) {
            is SearchForSavedNetworkRequest.SSID -> {
                configuration.SSID.replace(QUOTE, "").matches(request.regexForSSID.toRegex()) ||
                    configuration.SSID.matches(request.regexForSSID.toRegex())
            }
            is SearchForSavedNetworkRequest.BSSID -> {
                configuration.BSSID.replace(QUOTE, "").matches(request.regexForBSSID.toRegex()) ||
                    configuration.BSSID.matches(request.regexForBSSID.toRegex())
            }
        }
    }
}
