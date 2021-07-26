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
package com.isupatches.android.wisefy.sample.ui.add

import android.Manifest.permission.ACCESS_FINE_LOCATION
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.sample.internal.scaffolding.BaseModel
import com.isupatches.wisefy.WiseFyPublicApi
import com.isupatches.wisefy.callbacks.AddNetworkCallbacks
import javax.inject.Inject

interface AddNetworkModel {
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun addOpenNetwork(ssid: String, callbacks: AddNetworkCallbacks)

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun addWEPNetwork(ssid: String, password: String, callbacks: AddNetworkCallbacks)

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun addWPA2Network(ssid: String, password: String, callbacks: AddNetworkCallbacks)
}

@AddNetworkScope
internal class DefaultAddNetworkModel @Inject constructor(
    private val wiseFy: WiseFyPublicApi
) : BaseModel(), AddNetworkModel {

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun addOpenNetwork(ssid: String, callbacks: AddNetworkCallbacks) {
        wiseFy.addOpenNetwork(ssid, callbacks)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    @Suppress("deprecation")
    override fun addWEPNetwork(
        ssid: String,
        password: String,
        callbacks: AddNetworkCallbacks
    ) {
        wiseFy.addWEPNetwork(ssid, password, callbacks)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun addWPA2Network(
        ssid: String,
        password: String,
        callbacks: AddNetworkCallbacks
    ) {
        wiseFy.addWPA2Network(ssid, password, callbacks)
    }
}
