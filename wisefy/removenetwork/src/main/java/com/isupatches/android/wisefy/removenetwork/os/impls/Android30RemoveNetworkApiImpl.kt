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
package com.isupatches.android.wisefy.removenetwork.os.impls

import android.Manifest.permission.CHANGE_WIFI_STATE
import android.net.wifi.WifiManager
import android.net.wifi.WifiNetworkSuggestion
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.core.hasBSSIDMatchingRegex
import com.isupatches.android.wisefy.core.hasSSIDMatchingRegex
import com.isupatches.android.wisefy.core.logging.WisefyLogger
import com.isupatches.android.wisefy.removenetwork.os.apis.Android30RemoveNetworkApi

/**
 * An Android 29 specific implementation for removing a network.
 *
 * @param wifiManager The WifiManager instance to use
 *
 * @see Android30RemoveNetworkApi
 *
 * @author Patches Barrett
 * @since 03/2022
 */
@RequiresApi(Build.VERSION_CODES.R)
internal class Android30RemoveNetworkApiImpl(
    private val wifiManager: WifiManager,
    private val logger: WisefyLogger
) : Android30RemoveNetworkApi {

    @RequiresPermission(CHANGE_WIFI_STATE)
    override fun removeNetworkBySSID(regexForSSID: String): Int {
        val networkToRemove = wifiManager.networkSuggestions.firstOrNull { it.hasSSIDMatchingRegex(regexForSSID) }
        return removeNetworkSuggestion(networkSuggestion = networkToRemove)
    }

    @RequiresPermission(CHANGE_WIFI_STATE)
    override fun removeNetworkByBSSID(regexForBSSID: String): Int {
        val networkToRemove = wifiManager.networkSuggestions.firstOrNull { it.hasBSSIDMatchingRegex(regexForBSSID) }
        return removeNetworkSuggestion(networkSuggestion = networkToRemove)
    }

    private fun removeNetworkSuggestion(networkSuggestion: WifiNetworkSuggestion?): Int {
        val result = networkSuggestion?.let { wifiManager.removeNetworkSuggestions(listOf(it)) } ?: -1
        logger.d(LOG_TAG, "Removing network suggestion.  Result: $result, networkSuggestion: $networkSuggestion")
        return result
    }

    companion object {
        private const val LOG_TAG = "Android30RemoveNetworkApiImpl"
    }
}
