/*
 * Copyright 2022 Patches Barrett
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
import android.net.wifi.WifiNetworkSuggestion
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.addnetwork.os.apis.Android30AddNetworkApi
import com.isupatches.android.wisefy.core.logging.WisefyLogger
import com.isupatches.android.wisefy.core.wifimanager.createOpenNetworkSuggestion
import com.isupatches.android.wisefy.core.wifimanager.createWPA2NetworkSuggestion
import com.isupatches.android.wisefy.core.wifimanager.createWPA3NetworkSuggestion

/**
 * An internal Android 30 or higher implementation for adding networks through the Android OS.
 *
 * @property wifiManager The WifiManager instance to use
 * @property logger The [WisefyLogger] instance to use
 *
 * @see Android30AddNetworkApi
 * @see WisefyLogger
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
@RequiresApi(Build.VERSION_CODES.R)
internal class Android30AddNetworkApiImpl(
    private val wifiManager: WifiManager,
    private val logger: WisefyLogger
) : Android30AddNetworkApi {

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addOpenNetwork(ssid: String, bssid: String?): Int {
        return addNetworkSuggestion(suggestion = createOpenNetworkSuggestion(ssid, bssid))
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addWPA2Network(ssid: String, passphrase: String, bssid: String?): Int {
        return addNetworkSuggestion(suggestion = createWPA2NetworkSuggestion(ssid, passphrase, bssid))
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addWPA3Network(ssid: String, passphrase: String, bssid: String?): Int {
        return addNetworkSuggestion(suggestion = createWPA3NetworkSuggestion(ssid, passphrase, bssid))
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    private fun addNetworkSuggestion(suggestion: WifiNetworkSuggestion): Int {
        val result = wifiManager.addNetworkSuggestions(listOf(suggestion))
        logger.d(LOG_TAG, "Add network suggestion.  Result: $result, suggestion: $suggestion")
        return result
    }

    companion object {
        private const val LOG_TAG = "Android30AddNetworkApiImpl"
    }
}
