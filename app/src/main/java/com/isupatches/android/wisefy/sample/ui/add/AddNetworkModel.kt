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
import com.isupatches.android.wisefy.WisefyApi
import com.isupatches.android.wisefy.addnetwork.entities.AddOpenNetworkRequest
import com.isupatches.android.wisefy.addnetwork.entities.AddWPA2NetworkRequest
import com.isupatches.android.wisefy.addnetwork.entities.AddWPA3NetworkRequest
import com.isupatches.android.wisefy.callbacks.AddNetworkCallbacks
import com.isupatches.android.wisefy.sample.internal.scaffolding.BaseModel
import javax.inject.Inject

internal interface AddNetworkModel {

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun addOpenNetwork(ssid: String, callbacks: AddNetworkCallbacks?)

    @RequiresApi(Build.VERSION_CODES.R)
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun addOpenNetwork(
        ssid: String,
        activityResultLauncher: ActivityResultLauncher<Intent>,
        callbacks: AddNetworkCallbacks?
    )

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun addWPA2Network(
        ssid: String,
        passphrase: String,
        callbacks: AddNetworkCallbacks?
    )

    @RequiresApi(Build.VERSION_CODES.R)
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun addWPA2Network(
        ssid: String,
        passphrase: String,
        activityResultLauncher: ActivityResultLauncher<Intent>,
        callbacks: AddNetworkCallbacks?
    )

    @RequiresApi(Build.VERSION_CODES.Q)
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun addWPA3Network(
        ssid: String,
        passphrase: String,
        callbacks: AddNetworkCallbacks?
    )

    @RequiresApi(Build.VERSION_CODES.R)
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun addWPA3Network(
        ssid: String,
        passphrase: String,
        activityResultLauncher: ActivityResultLauncher<Intent>,
        callbacks: AddNetworkCallbacks?
    )
}

@AddNetworkScope
internal class DefaultAddNetworkModel @Inject constructor(
    private val wisefy: WisefyApi
) : BaseModel(), AddNetworkModel {

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addOpenNetwork(
        ssid: String,
        callbacks: AddNetworkCallbacks?
    ) {
        wisefy.addOpenNetwork(
            request = AddOpenNetworkRequest.Ssid(ssid = ssid),
            callbacks = callbacks
        )
    }

    @RequiresApi(Build.VERSION_CODES.R)
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addOpenNetwork(
        ssid: String,
        activityResultLauncher: ActivityResultLauncher<Intent>,
        callbacks: AddNetworkCallbacks?
    ) {
        wisefy.addOpenNetwork(
            request = AddOpenNetworkRequest.SsidAndActivityResultLauncher(
                ssid = ssid,
                activityResultLauncher = activityResultLauncher
            ),
            callbacks = callbacks
        )
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addWPA2Network(
        ssid: String,
        passphrase: String,
        callbacks: AddNetworkCallbacks?
    ) {
        wisefy.addWPA2Network(
            request = AddWPA2NetworkRequest.SsidAndPassphrase(
                ssid = ssid,
                passphrase = passphrase
            ),
            callbacks = callbacks
        )
    }

    @RequiresApi(Build.VERSION_CODES.R)
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addWPA2Network(
        ssid: String,
        passphrase: String,
        activityResultLauncher: ActivityResultLauncher<Intent>,
        callbacks: AddNetworkCallbacks?
    ) {
        wisefy.addWPA2Network(
            request = AddWPA2NetworkRequest.SsidPassphraseAndActivityResultLauncher(
                ssid = ssid,
                passphrase = passphrase,
                activityResultLauncher = activityResultLauncher
            ),
            callbacks = callbacks
        )
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addWPA3Network(
        ssid: String,
        passphrase: String,
        callbacks: AddNetworkCallbacks?
    ) {
        wisefy.addWPA3Network(
            request = AddWPA3NetworkRequest.SsidAndPassphrase(
                ssid = ssid,
                passphrase = passphrase
            ),
            callbacks = callbacks
        )
    }

    @RequiresApi(Build.VERSION_CODES.R)
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addWPA3Network(
        ssid: String,
        passphrase: String,
        activityResultLauncher: ActivityResultLauncher<Intent>,
        callbacks: AddNetworkCallbacks?
    ) {
        wisefy.addWPA3Network(
            request = AddWPA3NetworkRequest.SsidPassphraseAndActivityResultLauncher(
                ssid = ssid,
                passphrase = passphrase,
                activityResultLauncher = activityResultLauncher
            ),
            callbacks = callbacks
        )
    }
}
