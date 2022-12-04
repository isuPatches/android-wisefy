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
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.addnetwork.AddNetworkApi
import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkRequest
import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkResult
import com.isupatches.android.wisefy.addnetwork.os.apis.DefaultAddNetworkApi
import com.isupatches.android.wisefy.addnetwork.os.impls.DefaultAddNetworkApiImpl
import com.isupatches.android.wisefy.core.assertions.WisefyAssertions
import com.isupatches.android.wisefy.core.constants.AssertionMessages
import com.isupatches.android.wisefy.core.util.SdkUtil

/**
 * A default adapter for adding networks.
 *
 * @param wifiManager The WifiManager instance to use
 * @param assertions The [WisefyAssertions] instance to use
 * @param api The OS level API instance to use
 *
 * @see AddNetworkApi
 * @see DefaultAddNetworkApi
 * @see DefaultAddNetworkApiImpl
 * @see WisefyAssertions
 *
 * @author Patches Klinefelter
 * @since 11/2022, version 5.0.0
 */
internal class DefaultAddNetworkAdapter(
    wifiManager: WifiManager,
    private val assertions: WisefyAssertions,
    private val sdkUtil: SdkUtil,
    private val api: DefaultAddNetworkApi = DefaultAddNetworkApiImpl(wifiManager)
) : AddNetworkApi {

    companion object {
        private const val WIFI_MANAGER_ADD_NETWORK_FAILURE = -1
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addNetwork(request: AddNetworkRequest): AddNetworkResult {
        return when (request) {
            is AddNetworkRequest.Open.Default -> {
                val resultCode = api.addOpenNetwork(request.ssid, request.bssid)
                if (resultCode > WIFI_MANAGER_ADD_NETWORK_FAILURE) {
                    AddNetworkResult.Success.ResultCode(resultCode)
                } else {
                    if (sdkUtil.isAtLeastQ()) {
                        AddNetworkResult.Failure.AndroidQ
                    } else {
                        AddNetworkResult.Failure.ResultCode(resultCode)
                    }
                }
            }
            is AddNetworkRequest.WPA2.Default -> {
                val resultCode = api.addWPA2Network(request.ssid, request.passphrase, request.bssid)
                if (resultCode > WIFI_MANAGER_ADD_NETWORK_FAILURE) {
                    AddNetworkResult.Success.ResultCode(resultCode)
                } else {
                    if (sdkUtil.isAtLeastQ()) {
                        AddNetworkResult.Failure.AndroidQ
                    } else {
                        AddNetworkResult.Failure.ResultCode(resultCode)
                    }
                }
            }
            is AddNetworkRequest.Open.Android30OrAbove, is AddNetworkRequest.WPA2.Android30OrAbove -> {
                val message = AssertionMessages.AddNetwork.RequestWithContext.USED_PRE_ANDROID_30
                assertions.fail(message = message)
                AddNetworkResult.Failure.Assertion(message = message)
            }
            is AddNetworkRequest.WPA3.Default, is AddNetworkRequest.WPA3.Android30OrAbove -> {
                val message = AssertionMessages.AddNetwork.WPA3Network.USED_PRE_ANDROID_29
                assertions.fail(message = message)
                AddNetworkResult.Failure.Assertion(message = message)
            }
        }
    }
}
