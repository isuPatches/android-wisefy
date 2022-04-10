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
import com.isupatches.android.wisefy.addnetwork.os.apis.Android30AddNetworkApi
import com.isupatches.android.wisefy.addnetwork.os.impls.Android30AddNetworkApiImpl
import com.isupatches.android.wisefy.core.assertions.fail
import com.isupatches.android.wisefy.core.entities.ErrorMessages

/**
 * An Android 30 specific adapter for adding networks.
 *
 * @param wifiManager The WifiManager instance to use
 * @param api The OS level API instance to use
 *
 * @see AddNetworkApi
 * @see Android30AddNetworkApi
 * @see Android30AddNetworkApiImpl
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
@RequiresApi(Build.VERSION_CODES.R)
internal class Android30AddNetworkAdapter(
    wifiManager: WifiManager,
    private val api: Android30AddNetworkApi = Android30AddNetworkApiImpl(wifiManager)
) : AddNetworkApi {

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addOpenNetwork(request: AddOpenNetworkRequest): AddNetworkResult {
        return when (request) {
            is AddOpenNetworkRequest.Android30OrAbove -> {
                api.addOpenNetwork(request.ssid, request.launcher)
                AddNetworkResult.Success.IntentLaunched
            }
            is AddOpenNetworkRequest.Default -> {
                val message = ErrorMessages.AddNetwork.ActivityResultLauncher.NOT_USED_ANDROID_30
                fail(message)
                AddNetworkResult.Failure.WrongSDKLevel(message)
            }
        }
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addWPA2Network(request: AddWPA2NetworkRequest): AddNetworkResult {
        return when (request) {
            is AddWPA2NetworkRequest.Android30OrAbove -> {
                api.addWPA2Network(request.ssid, request.passphrase, request.launcher)
                AddNetworkResult.Success.IntentLaunched
            }
            is AddWPA2NetworkRequest.Default -> {
                val message = ErrorMessages.AddNetwork.ActivityResultLauncher.NOT_USED_ANDROID_30
                fail(message)
                AddNetworkResult.Failure.WrongSDKLevel(message)
            }
        }
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addWPA3Network(request: AddWPA3NetworkRequest): AddNetworkResult {
        return when (request) {
            is AddWPA3NetworkRequest.Android30OrAbove -> {
                api.addWPA3Network(request.ssid, request.passphrase, request.launcher)
                AddNetworkResult.Success.IntentLaunched
            }
            is AddWPA3NetworkRequest.Default -> {
                val message = ErrorMessages.AddNetwork.ActivityResultLauncher.NOT_USED_ANDROID_30
                fail(message)
                AddNetworkResult.Failure.WrongSDKLevel(message)
            }
        }
    }
}
