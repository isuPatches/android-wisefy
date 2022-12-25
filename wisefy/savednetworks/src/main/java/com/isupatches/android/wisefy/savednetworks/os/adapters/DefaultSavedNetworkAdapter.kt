/*
 * Copyright 2022 Patches Barrett
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
import com.isupatches.android.wisefy.core.logging.WisefyLogger
import com.isupatches.android.wisefy.savednetworks.SavedNetworkApi
import com.isupatches.android.wisefy.savednetworks.entities.GetSavedNetworksQuery
import com.isupatches.android.wisefy.savednetworks.entities.GetSavedNetworksResult
import com.isupatches.android.wisefy.savednetworks.entities.IsNetworkSavedQuery
import com.isupatches.android.wisefy.savednetworks.entities.IsNetworkSavedResult
import com.isupatches.android.wisefy.savednetworks.entities.SavedNetworkData
import com.isupatches.android.wisefy.savednetworks.os.apis.DefaultSavedNetworkApi
import com.isupatches.android.wisefy.savednetworks.os.impls.DefaultSavedNetworkApiImpl

/**
 * A default adapter for adding networks.
 *
 * @param wifiManager The WifiManager instance to use
 * @param logger The [WisefyLogger] instance to use
 * @property api The OS level API instance to use
 *
 * @see DefaultSavedNetworkApi
 * @see DefaultSavedNetworkApiImpl
 * @see SavedNetworkApi
 * @see WisefyLogger
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
internal class DefaultSavedNetworkAdapter(
    wifiManager: WifiManager,
    logger: WisefyLogger,
    private val api: DefaultSavedNetworkApi = DefaultSavedNetworkApiImpl(wifiManager, logger)
) : SavedNetworkApi {

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun getSavedNetworks(query: GetSavedNetworksQuery): GetSavedNetworksResult {
        val savedNetworkConfigurations = when (query) {
            is GetSavedNetworksQuery.All -> api.getSavedNetworks()
            is GetSavedNetworksQuery.BySSID -> api.searchForSavedNetworksBySSID(query.regex)
            is GetSavedNetworksQuery.ByBSSID -> api.searchForSavedNetworksByBSSID(query.regex)
        }
        return if (savedNetworkConfigurations.isNotEmpty()) {
            GetSavedNetworksResult.SavedNetworks(
                value = savedNetworkConfigurations.map { networkSuggestion ->
                    SavedNetworkData.Configuration(networkSuggestion)
                }
            )
        } else {
            GetSavedNetworksResult.Empty
        }
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun isNetworkSaved(query: IsNetworkSavedQuery): IsNetworkSavedResult {
        val isNetworkSavedAsConfiguration = when (query) {
            is IsNetworkSavedQuery.SSID -> api.searchForSavedNetworksBySSID(query.regex).isNotEmpty()
            is IsNetworkSavedQuery.BSSID -> api.searchForSavedNetworksByBSSID(query.regex).isNotEmpty()
        }
        return if (isNetworkSavedAsConfiguration) {
            IsNetworkSavedResult.True
        } else {
            IsNetworkSavedResult.False
        }
    }
}
