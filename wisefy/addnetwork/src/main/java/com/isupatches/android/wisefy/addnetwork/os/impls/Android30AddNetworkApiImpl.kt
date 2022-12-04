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
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.net.wifi.WifiNetworkSuggestion
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.addnetwork.os.apis.Android30AddNetworkApi
import com.isupatches.android.wisefy.core.wifimanager.createOpenNetworkSuggestion
import com.isupatches.android.wisefy.core.wifimanager.createWPA2NetworkSuggestion
import com.isupatches.android.wisefy.core.wifimanager.createWPA3NetworkSuggestion

/**
 * An internal Android 30 specific implementation for adding networks through the Android OS.
 *
 * @param wifiManager The WifiManager instance to use
 *
 * @see Android30AddNetworkApi
 *
 * @author Patches Klinefelter
 * @since 11/2022, version 5.0.0
 */
@RequiresApi(Build.VERSION_CODES.R)
internal class Android30AddNetworkApiImpl(
    private val wifiManager: WifiManager
) : Android30AddNetworkApi {

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addOpenNetwork(
        context: Context,
        ssid: String,
        bssid: String?
    ): Int {
        return addNetworkSuggestion(
            context = context,
            suggestion = createOpenNetworkSuggestion(ssid, bssid)
        )
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addWPA2Network(
        context: Context,
        ssid: String,
        passphrase: String,
        bssid: String?
    ): Int {
        return addNetworkSuggestion(
            context = context,
            suggestion = createWPA2NetworkSuggestion(ssid, passphrase, bssid)
        )
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addWPA3Network(
        context: Context,
        ssid: String,
        passphrase: String,
        bssid: String?
    ): Int {
        return addNetworkSuggestion(
            context = context,
            suggestion = createWPA3NetworkSuggestion(ssid, passphrase, bssid)
        )
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    private fun addNetworkSuggestion(context: Context, suggestion: WifiNetworkSuggestion): Int {
        val result = wifiManager.addNetworkSuggestions(listOf(suggestion))
        val intentFilter = IntentFilter(WifiManager.ACTION_WIFI_NETWORK_SUGGESTION_POST_CONNECTION)
        val broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent) {
                if (intent.action != WifiManager.ACTION_WIFI_NETWORK_SUGGESTION_POST_CONNECTION) {
                    return
                } else {
                    // Do something with success
                }
            }
        }
        context.registerReceiver(broadcastReceiver, intentFilter)
        return result
    }
}
