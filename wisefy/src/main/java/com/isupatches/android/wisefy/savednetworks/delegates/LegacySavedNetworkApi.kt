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

internal interface LegacySavedNetworkApi {
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    fun getSavedNetworks(): List<SavedNetworkData>

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    fun isNetworkSaved(ssid: String): Boolean

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    fun searchForSavedNetwork(regexForSSID: String): SavedNetworkData?

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    fun searchForSavedNetworks(regexForSSID: String): List<SavedNetworkData>
}

internal class LegacySavedNetworkApiImpl(
    private val wifiManager: WifiManager
) : LegacySavedNetworkApi {

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun getSavedNetworks(): List<SavedNetworkData> {
        return wifiManager.configuredNetworks.map {
            SavedNetworkData.Configuration(data = it)
        }
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun isNetworkSaved(ssid: String): Boolean {
        return wifiManager.configuredNetworks.any {
            it.SSID.equals(ssid)
        }
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForSavedNetwork(regexForSSID: String): SavedNetworkData? {
        val savedNetwork = wifiManager.configuredNetworks.firstOrNull {
            matchesRegexForSSID(it, regexForSSID)
        }
        return if (savedNetwork != null) {
            SavedNetworkData.Configuration(data = savedNetwork)
        } else {
            null
        }
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForSavedNetworks(regexForSSID: String): List<SavedNetworkData> {
        return wifiManager.configuredNetworks.filter {
            matchesRegexForSSID(it, regexForSSID)
        }.map { savedNetwork ->
            SavedNetworkData.Configuration(data = savedNetwork)
        }
    }

    private fun matchesRegexForSSID(configuration: WifiConfiguration, regexForSSID: String): Boolean {
        return configuration.SSID.replace(QUOTE, "").matches(regexForSSID.toRegex()) ||
            configuration.SSID.matches(regexForSSID.toRegex())
    }
}
