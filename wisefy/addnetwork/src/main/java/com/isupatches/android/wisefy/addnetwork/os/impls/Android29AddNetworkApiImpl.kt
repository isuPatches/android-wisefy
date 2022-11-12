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
import android.Manifest.permission.CHANGE_WIFI_STATE
import android.net.wifi.WifiManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.addnetwork.os.apis.Android29AddNetworkApi
import com.isupatches.android.wisefy.core.wifimanager.createOpenNetworkSuggestion
import com.isupatches.android.wisefy.core.wifimanager.createWPA2NetworkSuggestion
import com.isupatches.android.wisefy.core.wifimanager.createWPA3NetworkSuggestion

/**
 * An internal Android 29 specific implementation for adding networks through the Android OS.
 *
 * @param wifiManager The WifiManager instance to use
 *
 * @see Android29AddNetworkApi
 *
 * @author Patches Klinefelter
 * @since 11/2022, version 5.0.0
 */
@RequiresApi(Build.VERSION_CODES.Q)
internal class Android29AddNetworkApiImpl(
    private val wifiManager: WifiManager
) : Android29AddNetworkApi {

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addOpenNetwork(ssid: String, bssid: String?): Int {
        val suggestion = createOpenNetworkSuggestion(ssid, bssid)
        return wifiManager.addNetworkSuggestions(arrayListOf(suggestion))
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addWPA2Network(ssid: String, passphrase: String, bssid: String?): Int {
        val suggestion = createWPA2NetworkSuggestion(ssid, passphrase, bssid)
        return wifiManager.addNetworkSuggestions(listOf(suggestion))
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addWPA3Network(ssid: String, passphrase: String, bssid: String?): Int {
        val suggestion = createWPA3NetworkSuggestion(ssid, passphrase, bssid)
        return wifiManager.addNetworkSuggestions(arrayListOf(suggestion))
    }
}
