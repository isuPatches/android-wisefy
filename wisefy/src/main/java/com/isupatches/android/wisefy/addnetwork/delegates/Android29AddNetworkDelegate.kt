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
import android.Manifest.permission.CHANGE_WIFI_STATE
import android.net.wifi.WifiManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.addnetwork.AddNetworkApi
import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkResult
import com.isupatches.android.wisefy.addnetwork.entities.OpenNetworkData
import com.isupatches.android.wisefy.addnetwork.entities.WPA2NetworkData
import com.isupatches.android.wisefy.addnetwork.entities.WPA3NetworkData
import com.isupatches.android.wisefy.constants.ErrorMessages
import com.isupatches.android.wisefy.logging.WisefyLogger

@RequiresApi(Build.VERSION_CODES.Q)
internal class Android29AddNetworkDelegate(
    wifiManager: WifiManager,
    logger: WisefyLogger?,
    private val impl: Android29AddNetworkApi = Android29AddNetworkApiImpl(wifiManager, logger)
) : AddNetworkApi {

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addOpenNetwork(data: OpenNetworkData): AddNetworkResult {
        return when (data) {
            is OpenNetworkData.Ssid -> {
                impl.addOpenNetwork(data.ssid)
            }
            is OpenNetworkData.SsidAndActivityResultLauncher -> {
                error(ErrorMessages.AddNetwork.ActivityResultLauncher.USED_PRE_ANDROID_30)
            }
        }
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addWPA2Network(data: WPA2NetworkData): AddNetworkResult {
        return when (data) {
            is WPA2NetworkData.SsidAndPassphrase -> {
                impl.addWPA2Network(data.ssid, data.passphrase)
            }
            is WPA2NetworkData.SsidPassphraseAndActivityResultLauncher -> {
                error(ErrorMessages.AddNetwork.ActivityResultLauncher.USED_PRE_ANDROID_30)
            }
        }
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addWPA3Network(data: WPA3NetworkData): AddNetworkResult {
        return when (data) {
            is WPA3NetworkData.SsidAndPassphrase -> {
                impl.addWPA3Network(data.ssid, data.passphrase)
            }
            is WPA3NetworkData.SsidPassphraseAndActivityResultLauncher -> {
                error(ErrorMessages.AddNetwork.ActivityResultLauncher.USED_PRE_ANDROID_30)
            }
        }
    }
}
