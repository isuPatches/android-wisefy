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
package com.isupatches.android.wisefy.addnetwork.os.impls

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.addnetwork.os.apis.DefaultAddNetworkApi
import com.isupatches.android.wisefy.core.wifimanager.legacy.createOpenNetworkConfiguration
import com.isupatches.android.wisefy.core.wifimanager.legacy.createWPA2NetworkConfiguration

/**
 * A default internal implementation for adding networks through the Android OS.
 *
 * @param wifiManager The WifiManager instance to use
 *
 * @see DefaultAddNetworkApi
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
internal class DefaultAddNetworkApiImpl(
    private val wifiManager: WifiManager
) : DefaultAddNetworkApi {

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun addOpenNetwork(ssid: String): Int {
        val networkConfiguration = createOpenNetworkConfiguration(ssid)
        return wifiManager.addNetwork(networkConfiguration)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun addWPA2Network(ssid: String, passphrase: String): Int {
        val networkConfiguration = createWPA2NetworkConfiguration(ssid, passphrase)
        return wifiManager.addNetwork(networkConfiguration)
    }
}
