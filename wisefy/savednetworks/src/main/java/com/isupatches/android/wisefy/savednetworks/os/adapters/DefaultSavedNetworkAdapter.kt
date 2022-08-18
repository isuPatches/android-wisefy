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
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.savednetworks.SavedNetworkApi
import com.isupatches.android.wisefy.savednetworks.entities.GetSavedNetworksRequest
import com.isupatches.android.wisefy.savednetworks.entities.GetSavedNetworksResult
import com.isupatches.android.wisefy.savednetworks.entities.IsNetworkSavedRequest
import com.isupatches.android.wisefy.savednetworks.entities.IsNetworkSavedResult
import com.isupatches.android.wisefy.savednetworks.entities.SavedNetworkData
import com.isupatches.android.wisefy.savednetworks.entities.SearchForSavedNetworkRequest
import com.isupatches.android.wisefy.savednetworks.entities.SearchForSavedNetworkResult
import com.isupatches.android.wisefy.savednetworks.os.apis.DefaultSavedNetworkApi
import com.isupatches.android.wisefy.savednetworks.os.impls.DefaultSavedNetworkApiImpl

/**
 * A default adapter for adding networks.
 *
 * @param wifiManager The WifiManager instance to use
 * @param api The OS level API instance to use
 *
 * @see DefaultSavedNetworkApi
 * @see DefaultSavedNetworkApiImpl
 * @see SavedNetworkApi
 *
 * @author Patches Klinefelter
 * @since 07/2022, version 5.0.0
 */
internal class DefaultSavedNetworkAdapter(
    wifiManager: WifiManager,
    private val api: DefaultSavedNetworkApi = DefaultSavedNetworkApiImpl(wifiManager)
) : SavedNetworkApi {

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun getSavedNetworks(request: GetSavedNetworksRequest): GetSavedNetworksResult {
        val savedNetworkConfiguration = api.getSavedNetworks().map { networkSuggestion ->
            SavedNetworkData.Configuration(networkSuggestion)
        }
        return if (savedNetworkConfiguration.isNotEmpty()) {
            GetSavedNetworksResult.Success.SavedNetworks(data = savedNetworkConfiguration)
        } else {
            GetSavedNetworksResult.Success.Empty
        }
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun isNetworkSaved(request: IsNetworkSavedRequest): IsNetworkSavedResult {
        val isNetworkSavedAsConfiguration = when (request) {
            is IsNetworkSavedRequest.SSID -> api.searchForSavedNetworksBySSID(request.regex).isNotEmpty()
            is IsNetworkSavedRequest.BSSID -> api.searchForSavedNetworksByBSSID(request.regex).isNotEmpty()
        }
        return if (isNetworkSavedAsConfiguration) {
            IsNetworkSavedResult.True
        } else {
            IsNetworkSavedResult.False
        }
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForSavedNetwork(request: SearchForSavedNetworkRequest): SearchForSavedNetworkResult {
        val savedNetworkConfigurations = when (request) {
            is SearchForSavedNetworkRequest.SSID -> api.searchForSavedNetworksBySSID(request.regex)
            is SearchForSavedNetworkRequest.BSSID -> api.searchForSavedNetworksByBSSID(request.regex)
        }
        return if (savedNetworkConfigurations.isNotEmpty()) {
            SearchForSavedNetworkResult.Success.SavedNetworks(
                data = savedNetworkConfigurations.map { SavedNetworkData.Configuration(it) }
            )
        } else {
            SearchForSavedNetworkResult.Success.Empty
        }
    }
}
