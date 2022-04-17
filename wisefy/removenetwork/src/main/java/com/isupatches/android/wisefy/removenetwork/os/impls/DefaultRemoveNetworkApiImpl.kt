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
package com.isupatches.android.wisefy.removenetwork.os.impls

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.removenetwork.os.apis.DefaultRemoveNetworkApi

/**
 * A default implementation for removing a network.
 *
 * @param wifiManager The WifiManager instance to use
 *
 * @see DefaultRemoveNetworkApi
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
internal class DefaultRemoveNetworkApiImpl(
    private val wifiManager: WifiManager
) : DefaultRemoveNetworkApi {

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun removeNetwork(networkId: Int): Boolean {
        return wifiManager.removeNetwork(networkId)
    }
}
