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
import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkRequest
import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkResult
import com.isupatches.android.wisefy.addnetwork.os.apis.Android30AddNetworkApi
import com.isupatches.android.wisefy.addnetwork.os.impls.Android30AddNetworkApiImpl
import com.isupatches.android.wisefy.core.assertions.WisefyAssertions
import com.isupatches.android.wisefy.core.entities.AssertionMessages

/**
 * An Android 30 specific adapter for adding networks.
 *
 * @param wifiManager The WifiManager instance to use
 * @param assertions The [WisefyAssertions] instance to use
 * @param api The OS level API instance to use
 *
 * @see AddNetworkApi
 * @see Android30AddNetworkApi
 * @see Android30AddNetworkApiImpl
 * @see WisefyAssertions
 *
 * @author Patches Klinefelter
 * @since 08/2022, version 5.0.0
 */
@RequiresApi(Build.VERSION_CODES.R)
internal class Android30AddNetworkAdapter(
    wifiManager: WifiManager,
    private val assertions: WisefyAssertions,
    private val api: Android30AddNetworkApi = Android30AddNetworkApiImpl(wifiManager)
) : AddNetworkApi {

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addNetwork(request: AddNetworkRequest): AddNetworkResult {
        return when (request) {
            is AddNetworkRequest.Open.Android30OrAbove -> {
                api.addOpenNetwork(request.ssid, request.launcher, request.bssid)
                AddNetworkResult.Success.IntentLaunched
            }
            is AddNetworkRequest.WPA2.Android30OrAbove -> {
                api.addWPA2Network(request.ssid, request.passphrase, request.launcher, request.bssid)
                AddNetworkResult.Success.IntentLaunched
            }
            is AddNetworkRequest.WPA3.Android30OrAbove -> {
                api.addWPA3Network(request.ssid, request.passphrase, request.launcher, request.bssid)
                AddNetworkResult.Success.IntentLaunched
            }
            is AddNetworkRequest.Open.Default, is AddNetworkRequest.WPA2.Default, is AddNetworkRequest.WPA3.Default -> {
                val message = AssertionMessages.AddNetwork.ActivityResultLauncher.NOT_USED_ANDROID_30
                assertions.fail(message = message)
                AddNetworkResult.Failure.Assertion(message = message)
            }
        }
    }
}
