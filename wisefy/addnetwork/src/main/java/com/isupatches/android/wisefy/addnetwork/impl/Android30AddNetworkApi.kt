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
package com.isupatches.android.wisefy.addnetwork.impl

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
import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkResult
import com.isupatches.android.wisefy.shared.wifi.createOpenNetworkSuggestionWithSSID
import com.isupatches.android.wisefy.shared.wifi.createWPA2NetworkSuggestionWithSSID
import com.isupatches.android.wisefy.shared.wifi.createWPA3NetworkSuggestionWithSSID

/**
 * An internal Android 30 specific API for adding networks through the Android OS.
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
internal interface Android30AddNetworkApi {

    /**
     * An API to add an open network for Android 30.
     *
     * @param ssid The SSID of the open network to add
     *
     * @see AddNetworkResult
     *
     * @return AddNetworkResult - The result of launching the intent to add an open network
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    fun addOpenNetwork(
        ssid: String,
        activityResultLauncher: ActivityResultLauncher<Intent>
    ): AddNetworkResult

    /**
     * An API to add a WPA2 network for Android 30.
     *
     * @param ssid The SSID of the WPA2 network to add
     * @param passphrase The passphrase to authenticate with the WPA2 network
     *
     * @see AddNetworkResult
     *
     * @return AddNetworkResult - The result of launching the intent to add a WPA2 network
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    fun addWPA2Network(
        ssid: String,
        passphrase: String,
        activityResultLauncher: ActivityResultLauncher<Intent>
    ): AddNetworkResult

    /**
     * An API to add a WPA3 network for Android 30.
     *
     * @param ssid The SSID of the WPA3 network to add
     * @param passphrase The passphrase to authenticate with the WPA3 network
     *
     * @see AddNetworkResult
     *
     * @return AddNetworkResult - The result of launching the intent to add a WPA3 network
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    fun addWPA3Network(
        ssid: String,
        passphrase: String,
        activityResultLauncher: ActivityResultLauncher<Intent>
    ): AddNetworkResult
}

/**
 * An internal Android 30 specific implementation for adding networks through the Android OS.
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
    ): AddNetworkResult {
        val suggestion = createOpenNetworkSuggestionWithSSID(ssid)
        return launchIntent(suggestion, activityResultLauncher)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addWPA2Network(
        ssid: String,
        passphrase: String,
        activityResultLauncher: ActivityResultLauncher<Intent>
    ): AddNetworkResult {
        val suggestion = createWPA2NetworkSuggestionWithSSID(ssid, passphrase)
        return launchIntent(suggestion, activityResultLauncher)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addWPA3Network(
        ssid: String,
        passphrase: String,
        activityResultLauncher: ActivityResultLauncher<Intent>
    ): AddNetworkResult {
        val suggestion = createWPA3NetworkSuggestionWithSSID(ssid, passphrase)
        return launchIntent(suggestion, activityResultLauncher)
    }

    @RequiresPermission(CHANGE_WIFI_STATE)
    private fun launchIntent(
        suggestion: WifiNetworkSuggestion,
        activityResultLauncher: ActivityResultLauncher<Intent>
    ): AddNetworkResult {
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
        return AddNetworkResult.Success.IntentLaunched
    }
}
