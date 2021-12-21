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
package com.isupatches.android.wisefy.addnetwork.delegates

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.CHANGE_WIFI_STATE
import android.net.wifi.WifiManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkResult
import com.isupatches.android.wisefy.logging.WisefyLogger
import com.isupatches.android.wisefy.util.createOpenNetworkSuggestionWithSSID
import com.isupatches.android.wisefy.util.createWPA2NetworkSuggestionWithSSID
import com.isupatches.android.wisefy.util.createWPA3NetworkSuggestionWithSSID

@RequiresApi(Build.VERSION_CODES.Q)
internal interface Android29AddNetworkApi {
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    fun addOpenNetwork(ssid: String): AddNetworkResult

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    fun addWPA2Network(ssid: String, passphrase: String): AddNetworkResult

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    fun addWPA3Network(ssid: String, passphrase: String): AddNetworkResult
}

private const val LOG_TAG = "Android29AddNetworkApiImpl"
private const val ANDROID_Q_SAVE_NETWORK_WARNING =
    "There is no known way to save a network on Android Q similar to pre-Q or R+ behavior"

@RequiresApi(Build.VERSION_CODES.Q)
internal class Android29AddNetworkApiImpl(
    private val wifiManager: WifiManager,
    private val logger: WisefyLogger?
) : Android29AddNetworkApi {

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addOpenNetwork(ssid: String): AddNetworkResult {
        logger?.w(LOG_TAG, ANDROID_Q_SAVE_NETWORK_WARNING)
        val suggestion = createOpenNetworkSuggestionWithSSID(ssid)
        val resultCode = wifiManager.addNetworkSuggestions(arrayListOf(suggestion))
        return AddNetworkResult.ResultCode(resultCode)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addWPA2Network(ssid: String, passphrase: String): AddNetworkResult {
        logger?.w(LOG_TAG, ANDROID_Q_SAVE_NETWORK_WARNING)
        val suggestion = createWPA2NetworkSuggestionWithSSID(ssid, passphrase)
        val resultCode = wifiManager.addNetworkSuggestions(listOf(suggestion))
        return AddNetworkResult.ResultCode(resultCode)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addWPA3Network(ssid: String, passphrase: String): AddNetworkResult {
        logger?.w(LOG_TAG, ANDROID_Q_SAVE_NETWORK_WARNING)
        val suggestion = createWPA3NetworkSuggestionWithSSID(ssid, passphrase)
        val resultCode = wifiManager.addNetworkSuggestions(arrayListOf(suggestion))
        return AddNetworkResult.ResultCode(resultCode)
    }
}
