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
import android.Manifest.permission.CHANGE_WIFI_STATE
import android.net.wifi.WifiManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.core.assertions.WisefyAssertions
import com.isupatches.android.wisefy.core.constants.AssertionMessages
import com.isupatches.android.wisefy.removenetwork.RemoveNetworkApi
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkRequest
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkResult
import com.isupatches.android.wisefy.removenetwork.os.apis.Android30RemoveNetworkApi
import com.isupatches.android.wisefy.removenetwork.os.converters.toSearchForNetworkRequest
import com.isupatches.android.wisefy.removenetwork.os.impls.Android30RemoveNetworkApiImpl
import com.isupatches.android.wisefy.savednetworks.SavedNetworkDelegate
import com.isupatches.android.wisefy.savednetworks.entities.GetSavedNetworksResult
import com.isupatches.android.wisefy.savednetworks.entities.SavedNetworkData

/**
 * An Android 29 specific adapter for removing a network.
 *
 * @param wifiManager The WifiManager instance to use
 * @param api The OS level API instance to use
 *
 * @see Android30RemoveNetworkApi
 * @see Android30RemoveNetworkApiImpl
 * @see RemoveNetworkApi
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
@RequiresApi(Build.VERSION_CODES.R)
internal class Android30RemoveNetworkAdapter(
    private val wifiManager: WifiManager,
    private val savedNetworkDelegate: SavedNetworkDelegate,
    private val assertions: WisefyAssertions,
    private val api: Android30RemoveNetworkApi = Android30RemoveNetworkApiImpl(wifiManager)
) : RemoveNetworkApi {

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE, ACCESS_WIFI_STATE])
    override fun removeNetwork(request: RemoveNetworkRequest): RemoveNetworkResult {
        return when (
            val savedNetworkSearchResult = savedNetworkDelegate.getSavedNetworks(
                query = request.toSearchForNetworkRequest()
            )
        ) {
            is GetSavedNetworksResult.Empty -> RemoveNetworkResult.Failure.NetworkNotFound
            is GetSavedNetworksResult.SavedNetworks -> {
                when (val savedNetwork = savedNetworkSearchResult.value.first()) {
                    is SavedNetworkData.Suggestion -> {
                        val result = api.removeNetwork(savedNetwork.rawValue)
                        if (result == WifiManager.STATUS_NETWORK_SUGGESTIONS_SUCCESS) {
                            RemoveNetworkResult.Success.ResultCode(result)
                        } else {
                            RemoveNetworkResult.Failure.ResultCode(result)
                        }
                    }
                    is SavedNetworkData.Configuration -> {
                        val message = AssertionMessages.RemoveNetwork.CONFIGURATION_USED_ANDROID_Q
                        assertions.fail(message = message)
                        RemoveNetworkResult.Failure.Assertion(message = message)
                    }
                }
            }
        }
    }
}
