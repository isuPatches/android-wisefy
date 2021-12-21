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
package com.isupatches.android.wisefy.addnetwork.delegates

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.addnetwork.AddNetworkApi
import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkResult
import com.isupatches.android.wisefy.addnetwork.entities.AddOpenNetworkRequest
import com.isupatches.android.wisefy.addnetwork.entities.AddWPA2NetworkRequest
import com.isupatches.android.wisefy.addnetwork.entities.AddWPA3NetworkRequest
import com.isupatches.android.wisefy.constants.ErrorMessages
import com.isupatches.android.wisefy.util.fail

internal class LegacyAddNetworkDelegate(
    wifiManager: WifiManager,
    private val impl: LegacyAddNetworkApi = LegacyAddNetworkApiImpl(wifiManager)
) : AddNetworkApi {

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun addOpenNetwork(request: AddOpenNetworkRequest): AddNetworkResult {
        return when (request) {
            is AddOpenNetworkRequest.Ssid -> {
                impl.addOpenNetwork(request.ssid)
            }
            is AddOpenNetworkRequest.SsidAndActivityResultLauncher -> {
                val message = ErrorMessages.AddNetwork.ActivityResultLauncher.USED_PRE_ANDROID_30
                fail(message)
                return AddNetworkResult.WrongSDKLevelError(message)
            }
        }
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun addWPA2Network(request: AddWPA2NetworkRequest): AddNetworkResult {
        return when (request) {
            is AddWPA2NetworkRequest.SsidAndPassphrase -> {
                impl.addWPA2Network(request.ssid, request.passphrase)
            }
            is AddWPA2NetworkRequest.SsidPassphraseAndActivityResultLauncher -> {
                val message = ErrorMessages.AddNetwork.ActivityResultLauncher.USED_PRE_ANDROID_30
                fail(message)
                return AddNetworkResult.WrongSDKLevelError(message)
            }
        }
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun addWPA3Network(request: AddWPA3NetworkRequest): AddNetworkResult {
        val message = ErrorMessages.AddNetwork.WPA3Network.PRE_ANDROID_29
        fail(message)
        return AddNetworkResult.WrongSDKLevelError(message)
    }
}
