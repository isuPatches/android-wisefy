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
import android.content.Intent
import android.net.wifi.WifiManager
import android.net.wifi.WifiNetworkSuggestion
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.result.ActivityResultLauncher
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.addnetwork.os.apis.Android30AddNetworkApi
import com.isupatches.android.wisefy.shared.wifimanager.createOpenNetworkSuggestionWithSSID
import com.isupatches.android.wisefy.shared.wifimanager.createWPA2NetworkSuggestionWithSSID
import com.isupatches.android.wisefy.shared.wifimanager.createWPA3NetworkSuggestionWithSSID

/**
 * An internal Android 30 specific implementation for adding networks through the Android OS.
 *
 * @param wifiManager The WifiManager instance to use
 *
 * @see Android30AddNetworkApi
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
@RequiresApi(Build.VERSION_CODES.R)
internal class Android30AddNetworkApiImpl(
    private val wifiManager: WifiManager
) : Android30AddNetworkApi {

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addOpenNetwork(
        ssid: String,
        activityResultLauncher: ActivityResultLauncher<Intent>
    ) {
        val suggestion = createOpenNetworkSuggestionWithSSID(ssid)
        launchIntent(suggestion, activityResultLauncher)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addWPA2Network(
        ssid: String,
        passphrase: String,
        activityResultLauncher: ActivityResultLauncher<Intent>
    ) {
        val suggestion = createWPA2NetworkSuggestionWithSSID(ssid, passphrase)
        launchIntent(suggestion, activityResultLauncher)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addWPA3Network(
        ssid: String,
        passphrase: String,
        activityResultLauncher: ActivityResultLauncher<Intent>
    ) {
        val suggestion = createWPA3NetworkSuggestionWithSSID(ssid, passphrase)
        return launchIntent(suggestion, activityResultLauncher)
    }

    @RequiresPermission(CHANGE_WIFI_STATE)
    private fun launchIntent(
        suggestion: WifiNetworkSuggestion,
        activityResultLauncher: ActivityResultLauncher<Intent>
    ) {
        wifiManager.addNetworkSuggestions(listOf(suggestion))
        val bundle = Bundle().apply {
            putParcelableArrayList(
                Settings.EXTRA_WIFI_NETWORK_LIST,
                arrayListOf(suggestion)
            )
        }
        val intent = Intent(Settings.ACTION_WIFI_ADD_NETWORKS).apply {
            putExtras(bundle)
        }
        activityResultLauncher.launch(intent)
    }
}
