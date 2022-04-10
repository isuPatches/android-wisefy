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
package com.isupatches.android.wisefy.removenetwork.os.adapters

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.core.assertions.fail
import com.isupatches.android.wisefy.removenetwork.RemoveNetworkApi
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkRequest
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkResult
import com.isupatches.android.wisefy.removenetwork.os.apis.DefaultRemoveNetworkApi
import com.isupatches.android.wisefy.removenetwork.os.converters.toSearchForNetworkRequest
import com.isupatches.android.wisefy.removenetwork.os.impls.DefaultRemoveNetworkApiImpl
import com.isupatches.android.wisefy.savednetworks.SavedNetworkDelegate
import com.isupatches.android.wisefy.savednetworks.entities.SavedNetworkData
import com.isupatches.android.wisefy.savednetworks.entities.SearchForSavedNetworkResult

internal class DefaultRemoveNetworkAdapter(
    private val wifiManager: WifiManager,
    private val savedNetworkDelegate: SavedNetworkDelegate,
    private val api: DefaultRemoveNetworkApi = DefaultRemoveNetworkApiImpl(wifiManager)
) : RemoveNetworkApi {

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun removeNetwork(request: RemoveNetworkRequest): RemoveNetworkResult {
        return when (
            val savedNetworkSearchResult = savedNetworkDelegate.searchForSavedNetwork(
                request = request.toSearchForNetworkRequest()
            )
        ) {
            is SearchForSavedNetworkResult.Empty -> RemoveNetworkResult.NetworkNotFound
            is SearchForSavedNetworkResult.SavedNetwork -> {
                when (val savedNetwork = savedNetworkSearchResult.data) {
                    is SavedNetworkData.Configuration -> {
                        val result = api.removeNetwork(savedNetwork.value.networkId)
                        if (result) {
                            RemoveNetworkResult.BooleanResult.Success
                        } else {
                            RemoveNetworkResult.BooleanResult.Failure
                        }
                    }
                    is SavedNetworkData.Suggestion -> {
                        fail("")
                        RemoveNetworkResult.BooleanResult.Failure
                    }
                }
            }
        }
    }
}
