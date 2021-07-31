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
package com.isupatches.android.wisefy.sample.ui.add

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.CHANGE_WIFI_STATE
import android.content.Intent
import android.os.Build
import androidx.activity.result.ActivityResultLauncher
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.sample.internal.scaffolding.BaseModel
import com.isupatches.android.wisefy.WisefyApi
import com.isupatches.android.wisefy.addnetwork.entities.OpenNetworkData
import com.isupatches.android.wisefy.addnetwork.entities.WPA2NetworkData
import com.isupatches.android.wisefy.addnetwork.entities.WPA3NetworkData
import javax.inject.Inject

internal interface AddNetworkModel {

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun addOpenNetwork(ssid: String)

    @RequiresApi(Build.VERSION_CODES.R)
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun addOpenNetwork(
        ssid: String,
        activityResultLauncher: ActivityResultLauncher<Intent>
    )

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun addWPA2Network(ssid: String, passphrase: String)

    @RequiresApi(Build.VERSION_CODES.R)
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun addWPA2Network(
        ssid: String,
        passphrase: String,
        activityResultLauncher: ActivityResultLauncher<Intent>
    )

    @RequiresApi(Build.VERSION_CODES.Q)
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun addWPA3Network(ssid: String, password: String)

    @RequiresApi(Build.VERSION_CODES.R)
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun addWPA3Network(
        ssid: String,
        passphrase: String,
        activityResultLauncher: ActivityResultLauncher<Intent>
    )
}

@AddNetworkScope
internal class DefaultAddNetworkModel @Inject constructor(
    private val wisefy: WisefyApi
) : BaseModel(), AddNetworkModel {

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addOpenNetwork(ssid: String) {
        wisefy.addOpenNetwork(OpenNetworkData.Ssid(ssid = ssid))
    }

    @RequiresApi(Build.VERSION_CODES.R)
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addOpenNetwork(
        ssid: String,
        activityResultLauncher: ActivityResultLauncher<Intent>
    ) {
        wisefy.addOpenNetwork(
            OpenNetworkData.SsidAndActivityResultLauncher(
                ssid = ssid,
                activityResultLauncher = activityResultLauncher
            )
        )
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addWPA2Network(ssid: String, passphrase: String) {
        wisefy.addWPA2Network(
            WPA2NetworkData.SsidAndPassphrase(
                ssid = ssid,
                passphrase = passphrase
            )
        )
    }

    @RequiresApi(Build.VERSION_CODES.R)
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addWPA2Network(
        ssid: String,
        passphrase: String,
        activityResultLauncher: ActivityResultLauncher<Intent>
    ) {
        wisefy.addWPA2Network(
            WPA2NetworkData.SsidPassphraseAndActivityResultLauncher(
                ssid = ssid,
                passphrase = passphrase,
                activityResultLauncher = activityResultLauncher
            )
        )
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addWPA3Network(ssid: String, passphrase: String) {
        wisefy.addWPA3Network(
            WPA3NetworkData.SsidAndPassphrase(
                ssid = ssid,
                passphrase = passphrase
            )
        )
    }

    @RequiresApi(Build.VERSION_CODES.R)
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addWPA3Network(
        ssid: String,
        passphrase: String,
        activityResultLauncher: ActivityResultLauncher<Intent>
    ) {
        wisefy.addWPA3Network(
            WPA3NetworkData.SsidPassphraseAndActivityResultLauncher(
                ssid = ssid,
                passphrase = passphrase,
                activityResultLauncher = activityResultLauncher
            )
        )
    }
}
