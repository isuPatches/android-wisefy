/*
 * Copyright 2019 Patches Klinefelter
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
package com.isupatches.wisefysample.ui.add

import android.Manifest.permission.ACCESS_WIFI_STATE
import androidx.annotation.RequiresPermission
import com.isupatches.wisefy.WiseFyPublicApi
import com.isupatches.wisefy.callbacks.AddNetworkCallbacks
import javax.inject.Inject

internal class AddNetworkModel @Inject constructor(
    private val wiseFy: WiseFyPublicApi
) : AddNetworkMvp.Model {

    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun addOpenNetwork(ssid: String, callbacks: AddNetworkCallbacks) {
        wiseFy.addOpenNetwork(ssid, callbacks)
    }

    @RequiresPermission(ACCESS_WIFI_STATE)
    @Suppress("DEPRECATION")
    override fun addWEPNetwork(
        ssid: String,
        password: String,
        callbacks: AddNetworkCallbacks
    ) {
        wiseFy.addWEPNetwork(ssid, password, callbacks)
    }

    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun addWPA2Network(
        ssid: String,
        password: String,
        callbacks: AddNetworkCallbacks
    ) {
        wiseFy.addWPA2Network(ssid, password, callbacks)
    }
}
