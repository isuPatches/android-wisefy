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
package com.isupatches.android.wisefy.addnetwork.os.adapters

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.addnetwork.AddNetworkApi
import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkResult
import com.isupatches.android.wisefy.addnetwork.entities.AddOpenNetworkRequest
import com.isupatches.android.wisefy.addnetwork.entities.AddWPA2NetworkRequest
import com.isupatches.android.wisefy.addnetwork.entities.AddWPA3NetworkRequest
import com.isupatches.android.wisefy.addnetwork.os.apis.DefaultAddNetworkApi
import com.isupatches.android.wisefy.addnetwork.os.impls.DefaultAddNetworkApiImpl
import com.isupatches.android.wisefy.shared.assertions.fail
import com.isupatches.android.wisefy.shared.entities.ErrorMessages

/**
 * A default proxy for adding networks.
 *
 * @see AddNetworkApi
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
internal class DefaultAddNetworkAdapter(
    wifiManager: WifiManager,
    private val api: DefaultAddNetworkApi = DefaultAddNetworkApiImpl(wifiManager)
) : AddNetworkApi {

    companion object {
        private const val WIFI_MANAGER_ADD_NETWORK_FAILURE = -1
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun addOpenNetwork(request: AddOpenNetworkRequest): AddNetworkResult {
        return when (request) {
            is AddOpenNetworkRequest.Default -> {
                val resultCode = api.addOpenNetwork(request.ssid)
                if (resultCode > WIFI_MANAGER_ADD_NETWORK_FAILURE) {
                    AddNetworkResult.Success.ResultCode(resultCode)
                } else {
                    AddNetworkResult.Failure.ResultCode(resultCode)
                }
            }
            is AddOpenNetworkRequest.Android30OrAbove -> {
                val message = ErrorMessages.AddNetwork.ActivityResultLauncher.USED_PRE_ANDROID_30
                fail(message)
                AddNetworkResult.Failure.WrongSDKLevel(message)
            }
        }
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun addWPA2Network(request: AddWPA2NetworkRequest): AddNetworkResult {
        return when (request) {
            is AddWPA2NetworkRequest.Default -> {
                val resultCode = api.addWPA2Network(request.ssid, request.passphrase)
                if (resultCode > WIFI_MANAGER_ADD_NETWORK_FAILURE) {
                    AddNetworkResult.Success.ResultCode(resultCode)
                } else {
                    AddNetworkResult.Failure.ResultCode(resultCode)
                }
            }
            is AddWPA2NetworkRequest.Android30OrAbove -> {
                val message = ErrorMessages.AddNetwork.ActivityResultLauncher.USED_PRE_ANDROID_30
                fail(message)
                AddNetworkResult.Failure.WrongSDKLevel(message)
            }
        }
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun addWPA3Network(request: AddWPA3NetworkRequest): AddNetworkResult {
        val message = ErrorMessages.AddNetwork.WPA3Network.PRE_ANDROID_29
        fail(message)
        return AddNetworkResult.Failure.WrongSDKLevel(message)
    }
}
