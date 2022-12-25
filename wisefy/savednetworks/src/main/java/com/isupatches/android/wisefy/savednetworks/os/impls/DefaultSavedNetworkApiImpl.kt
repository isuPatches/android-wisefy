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
package com.isupatches.android.wisefy.savednetworks.os.impls

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.core.hasBSSIDMatchingRegex
import com.isupatches.android.wisefy.core.hasSSIDMatchingRegex
import com.isupatches.android.wisefy.core.logging.WisefyLogger
import com.isupatches.android.wisefy.savednetworks.os.apis.DefaultSavedNetworkApi

/**
 * An internal default implementation for getting and searching for saved networks through the Android OS.
 *
 * @property wifiManager The WifiManager instance to use
 * @property logger The [WisefyLogger] instance to use
 *
 * @see DefaultSavedNetworkApi
 * @see WisefyLogger
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
internal class DefaultSavedNetworkApiImpl(
    private val wifiManager: WifiManager,
    private val logger: WisefyLogger
) : DefaultSavedNetworkApi {

    @Suppress("Deprecation")
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun getSavedNetworks(): List<android.net.wifi.WifiConfiguration> {
        val savedNetworkConfigurations = wifiManager.configuredNetworks
        logger.d(LOG_TAG, "Saved network configurations: $savedNetworkConfigurations")
        return savedNetworkConfigurations
    }

    @Suppress("Deprecation")
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForSavedNetworksBySSID(regexForSSID: String): List<android.net.wifi.WifiConfiguration> {
        return getSavedNetworks().filter { it.hasSSIDMatchingRegex(regexForSSID) }
    }

    @Suppress("Deprecation")
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForSavedNetworksByBSSID(regexForBSSID: String): List<android.net.wifi.WifiConfiguration> {
        return getSavedNetworks().filter { it.hasBSSIDMatchingRegex(regexForBSSID) }
    }

    companion object {
        private const val LOG_TAG = "DefaultSavedNetworkApiImpl"
    }
}
