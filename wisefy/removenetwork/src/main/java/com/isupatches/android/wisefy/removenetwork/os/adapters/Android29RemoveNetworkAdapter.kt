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
import android.Manifest.permission.CHANGE_WIFI_STATE
import android.net.wifi.WifiManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.removenetwork.RemoveNetworkApi
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkRequest
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkResult
import com.isupatches.android.wisefy.removenetwork.os.apis.Android29RemoveNetworkApi
import com.isupatches.android.wisefy.removenetwork.os.impls.Android29RemoveNetworkApiImpl
import com.isupatches.android.wisefy.shared.wifimanager.createOpenNetworkSuggestionWithBSSID
import com.isupatches.android.wisefy.shared.wifimanager.createOpenNetworkSuggestionWithSSID

@RequiresApi(Build.VERSION_CODES.Q)
internal class Android29RemoveNetworkAdapter(
    private val wifiManager: WifiManager,
    private val api: Android29RemoveNetworkApi = Android29RemoveNetworkApiImpl(wifiManager)
) : RemoveNetworkApi {

    @RequiresApi(Build.VERSION_CODES.Q)
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun removeNetwork(request: RemoveNetworkRequest): RemoveNetworkResult {
        val suggestion = when (request) {
            is RemoveNetworkRequest.SSID -> createOpenNetworkSuggestionWithSSID(request.regex)
            is RemoveNetworkRequest.BSSID -> createOpenNetworkSuggestionWithBSSID(request.regex)
        }
        val resultCode = api.removeNetwork(suggestion)
        return if (resultCode == WifiManager.STATUS_NETWORK_SUGGESTIONS_SUCCESS) {
            RemoveNetworkResult.ResultCode.Success(value = resultCode)
        } else {
            RemoveNetworkResult.ResultCode.Failure(value = resultCode)
        }
    }
}
