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
package com.isupatches.android.wisefy.savednetworks.os.adapters

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.net.wifi.WifiManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.savednetworks.SavedNetworkApi
import com.isupatches.android.wisefy.savednetworks.entities.GetSavedNetworksQuery
import com.isupatches.android.wisefy.savednetworks.entities.GetSavedNetworksResult
import com.isupatches.android.wisefy.savednetworks.entities.IsNetworkSavedQuery
import com.isupatches.android.wisefy.savednetworks.entities.IsNetworkSavedResult
import com.isupatches.android.wisefy.savednetworks.entities.SavedNetworkData
import com.isupatches.android.wisefy.savednetworks.os.apis.Android30SavedNetworkApi
import com.isupatches.android.wisefy.savednetworks.os.impls.Android30SavedNetworkApiImpl

/**
 * An Android 30 specific adapter for adding networks.
 *
 * @param wifiManager The WifiManager instance to use
 * @param api The OS level API instance to use
 *
 * @see Android30SavedNetworkApi
 * @see Android30SavedNetworkApiImpl
 * @see SavedNetworkApi
 *
 * @author Patches Klinefelter
 * @since 07/2022, version 5.0.0
 */
@RequiresApi(Build.VERSION_CODES.R)
internal class Android30SavedNetworkAdapter(
    wifiManager: WifiManager,
    private val api: Android30SavedNetworkApi = Android30SavedNetworkApiImpl(wifiManager)
) : SavedNetworkApi {

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun getSavedNetworks(query: GetSavedNetworksQuery): GetSavedNetworksResult {
        val savedNetworkSuggestions = when (query) {
            is GetSavedNetworksQuery.All -> api.getSavedNetworks()
            is GetSavedNetworksQuery.BySSID -> api.searchForSavedNetworksBySSID(query.regex)
            is GetSavedNetworksQuery.ByBSSID -> api.searchForSavedNetworksByBSSID(query.regex)
        }
        return if (savedNetworkSuggestions.isNotEmpty()) {
            GetSavedNetworksResult.SavedNetworks(
                value = savedNetworkSuggestions.map { networkSuggestion ->
                    SavedNetworkData.Suggestion(networkSuggestion)
                }
            )
        } else {
            GetSavedNetworksResult.Empty
        }
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun isNetworkSaved(query: IsNetworkSavedQuery): IsNetworkSavedResult {
        val isNetworkSavedAsSuggestion = when (query) {
            is IsNetworkSavedQuery.SSID -> api.searchForSavedNetworksBySSID(query.regex).isNotEmpty()
            is IsNetworkSavedQuery.BSSID -> api.searchForSavedNetworksByBSSID(query.regex).isNotEmpty()
        }
        return if (isNetworkSavedAsSuggestion) {
            IsNetworkSavedResult.True
        } else {
            IsNetworkSavedResult.False
        }
    }
}
