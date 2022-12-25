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

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.core.hasBSSIDMatchingRegex
import com.isupatches.android.wisefy.core.hasSSIDMatchingRegex
import com.isupatches.android.wisefy.core.logging.WisefyLogger
import com.isupatches.android.wisefy.removenetwork.os.apis.DefaultRemoveNetworkApi

/**
 * A default implementation for removing a network.
 *
 * @param wifiManager The WifiManager instance to use
 *
 * @see DefaultRemoveNetworkApi
 *
 * @author Patches Barrett
 * @since 03/2022
 */
internal class DefaultRemoveNetworkApiImpl(
    private val wifiManager: WifiManager,
    private val logger: WisefyLogger
) : DefaultRemoveNetworkApi {

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun removeNetworkBySSID(regexForSSID: String): Boolean {
        val networkToRemove = wifiManager.configuredNetworks.firstOrNull { it.hasSSIDMatchingRegex(regexForSSID) }
        return removeWifiConfiguration(wifiConfiguration = networkToRemove)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun removeNetworkByBSSID(regexForBSSID: String): Boolean {
        val networkToRemove = wifiManager.configuredNetworks.firstOrNull { it.hasBSSIDMatchingRegex(regexForBSSID) }
        return removeWifiConfiguration(wifiConfiguration = networkToRemove)
    }

    private fun removeWifiConfiguration(wifiConfiguration: WifiConfiguration?): Boolean {
        val result = wifiConfiguration?.let { wifiManager.removeNetwork(it.networkId) } ?: false
        logger.d(LOG_TAG, "Removing network suggestion.  Result: $result, wifiConfiguration: $wifiConfiguration")
        return result
    }

    companion object {
        private const val LOG_TAG = "DefaultRemoveNetworkApiImpl"
    }
}
