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
import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkResult
import com.isupatches.android.wisefy.util.generateOpenNetworkConfiguration
import com.isupatches.android.wisefy.util.generateWPA2NetworkConfiguration

internal interface LegacyAddNetworkApi {
    fun addOpenNetwork(ssid: String): AddNetworkResult
    fun addWPA2Network(ssid: String, passphrase: String): AddNetworkResult
}

internal class LegacyAddNetworkApiImpl(
    private val wifiManager: WifiManager
) : LegacyAddNetworkApi {

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun addOpenNetwork(ssid: String): AddNetworkResult {
        val networkConfiguration = generateOpenNetworkConfiguration(ssid)
        val resultCode = wifiManager.addNetwork(networkConfiguration)
        return AddNetworkResult.ResultCode(resultCode)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun addWPA2Network(ssid: String, passphrase: String): AddNetworkResult {
        val networkConfiguration = generateWPA2NetworkConfiguration(ssid, passphrase)
        val resultCode = wifiManager.addNetwork(networkConfiguration)
        return AddNetworkResult.ResultCode(resultCode)
    }
}
