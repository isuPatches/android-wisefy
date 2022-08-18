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

/**
 * An internal Android 30 specific implementation for getting and searching for saved networks through the Android OS.
 *
 * @param wifiManager The WifiManager instance to use
 *
 * @see Android30SavedNetworkApi
 *
 * @author Patches Klinefelter
 * @since 07/2022, version 5.0.0
 */
@RequiresApi(Build.VERSION_CODES.R)
internal class Android30SavedNetworkApiImpl(
    private val wifiManager: WifiManager
) : Android30SavedNetworkApi {

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun getSavedNetworks(): List<WifiNetworkSuggestion> {
        return wifiManager.networkSuggestions
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForSavedNetworksBySSID(regexForSSID: String): List<WifiNetworkSuggestion> {
        return getSavedNetworks().filter { suggestion ->
            suggestion.ssid?.replace(QUOTE, "")?.matches(regexForSSID.toRegex()) == true ||
                suggestion.ssid?.matches(regexForSSID.toRegex()) == true
        }
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForSavedNetworksByBSSID(regexForBSSID: String): List<WifiNetworkSuggestion> {
        return getSavedNetworks().filter { suggestion ->
            suggestion.bssid?.toString()?.replace(QUOTE, "")?.matches(regexForBSSID.toRegex()) == true ||
                suggestion.bssid?.toString()?.matches(regexForBSSID.toRegex()) == true
        }
    }
}
