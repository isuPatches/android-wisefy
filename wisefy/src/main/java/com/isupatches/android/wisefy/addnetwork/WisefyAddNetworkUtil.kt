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
package com.isupatches.android.wisefy.addnetwork

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.CHANGE_WIFI_STATE
import android.net.wifi.WifiManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.addnetwork.delegates.Android29AddNetworkDelegate
import com.isupatches.android.wisefy.addnetwork.delegates.Android30AddNetworkDelegate
import com.isupatches.android.wisefy.addnetwork.delegates.LegacyAddNetworkDelegate
import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkResult
import com.isupatches.android.wisefy.addnetwork.entities.OpenNetworkData
import com.isupatches.android.wisefy.addnetwork.entities.WPA2NetworkData
import com.isupatches.android.wisefy.addnetwork.entities.WPA3NetworkData

internal interface AddNetworkUtil : AddNetworkApi

internal class WisefyAddNetworkUtil(
    wifiManager: WifiManager
) : AddNetworkUtil {

    private val delegate = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.R -> Android30AddNetworkDelegate()
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> Android29AddNetworkDelegate(wifiManager)
        else -> LegacyAddNetworkDelegate(wifiManager)
    }

    /*
     * Legacy API requires ACCESS_FINE_LOCATION while API 29+ requires CHANGE_WIFI_STATE
     */
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addOpenNetwork(data: OpenNetworkData): AddNetworkResult {
        return delegate.addOpenNetwork(data)
    }

    /*
     * Legacy API requires ACCESS_FINE_LOCATION while API 29+ requires CHANGE_WIFI_STATE
     */
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addWPA2Network(data: WPA2NetworkData): AddNetworkResult {
        return delegate.addWPA2Network(data)
    }

    /*
     * Legacy API requires ACCESS_FINE_LOCATION while API 29+ requires CHANGE_WIFI_STATE
     */
    @RequiresApi(Build.VERSION_CODES.Q)
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addWPA3Network(data: WPA3NetworkData): AddNetworkResult {
        return delegate.addWPA3Network(data)
    }
}
