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
import android.Manifest.permission.CHANGE_WIFI_STATE
import android.net.wifi.WifiManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.addnetwork.AddNetworkApi
import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkResult
import com.isupatches.android.wisefy.addnetwork.entities.AddOpenNetworkRequest
import com.isupatches.android.wisefy.addnetwork.entities.AddWPA2NetworkRequest
import com.isupatches.android.wisefy.addnetwork.entities.AddWPA3NetworkRequest
import com.isupatches.android.wisefy.addnetwork.os.apis.Android29AddNetworkApi
import com.isupatches.android.wisefy.addnetwork.os.impls.Android29AddNetworkApiImpl
import com.isupatches.android.wisefy.core.assertions.fail
import com.isupatches.android.wisefy.core.entities.ErrorMessages

/**
 * An Android 29 specific adapter for adding networks.
 *
 * @param wifiManager The WifiManager instance to use
 * @param api The OS level API instance to use
 *
 * @see AddNetworkApi
 * @see Android29AddNetworkApi
 * @see Android29AddNetworkApiImpl
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
@RequiresApi(Build.VERSION_CODES.Q)
internal class Android29AddNetworkAdapter(
    wifiManager: WifiManager,
    private val api: Android29AddNetworkApi = Android29AddNetworkApiImpl(wifiManager)
) : AddNetworkApi {

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addOpenNetwork(request: AddOpenNetworkRequest): AddNetworkResult {
        return when (request) {
            is AddOpenNetworkRequest.Default -> {
                val resultCode = api.addOpenNetwork(request.ssid)
                if (resultCode == WifiManager.STATUS_NETWORK_SUGGESTIONS_SUCCESS) {
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

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addWPA2Network(request: AddWPA2NetworkRequest): AddNetworkResult {
        return when (request) {
            is AddWPA2NetworkRequest.Default -> {
                val resultCode = api.addWPA2Network(request.ssid, request.passphrase)
                if (resultCode == WifiManager.STATUS_NETWORK_SUGGESTIONS_SUCCESS) {
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

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addWPA3Network(request: AddWPA3NetworkRequest): AddNetworkResult {
        return when (request) {
            is AddWPA3NetworkRequest.Default -> {
                val resultCode = api.addWPA3Network(request.ssid, request.passphrase)
                if (resultCode == WifiManager.STATUS_NETWORK_SUGGESTIONS_SUCCESS) {
                    AddNetworkResult.Success.ResultCode(resultCode)
                } else {
                    AddNetworkResult.Failure.ResultCode(resultCode)
                }
            }
            is AddWPA3NetworkRequest.Android30OrAbove -> {
                val message = ErrorMessages.AddNetwork.ActivityResultLauncher.USED_PRE_ANDROID_30
                fail(message)
                AddNetworkResult.Failure.WrongSDKLevel(message)
            }
        }
    }
}
