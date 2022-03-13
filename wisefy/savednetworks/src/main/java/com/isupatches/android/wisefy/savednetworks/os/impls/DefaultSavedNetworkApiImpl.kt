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
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.savednetworks.os.apis.DefaultSavedNetworkApi
import com.isupatches.android.wisefy.shared.entities.QUOTE

internal class DefaultSavedNetworkApiImpl(
    private val wifiManager: WifiManager
) : DefaultSavedNetworkApi {

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun getSavedNetworks(): List<WifiConfiguration> {
        return wifiManager.configuredNetworks ?: emptyList()
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun isNetworkSavedWithSSID(regexForSSID: String): Boolean {
        return searchForSavedNetworkBySSID(regexForSSID) != null
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun isNetworkSavedWithBSSID(regexForBSSID: String): Boolean {
        return searchForSavedNetworkByBSSID(regexForBSSID) != null
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForSavedNetworkBySSID(regexForSSID: String): WifiConfiguration? {
        return searchForSavedNetworksBySSID(regexForSSID).firstOrNull()
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForSavedNetworkByBSSID(regexForBSSID: String): WifiConfiguration? {
        return searchForSavedNetworksByBSSID(regexForBSSID).firstOrNull()
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForSavedNetworksBySSID(regexForSSID: String): List<WifiConfiguration> {
        return wifiManager.configuredNetworks?.filter { configuration ->
            configuration.SSID.replace(QUOTE, "").matches(regexForSSID.toRegex()) ||
                configuration.SSID.matches(regexForSSID.toRegex())
        } ?: emptyList()
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForSavedNetworksByBSSID(regexForBSSID: String): List<WifiConfiguration> {
        return wifiManager.configuredNetworks?.filter { configuration ->
            configuration.BSSID.replace(QUOTE, "").matches(regexForBSSID.toRegex()) ||
                configuration.BSSID.matches(regexForBSSID.toRegex())
        } ?: emptyList()
    }
}
