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
package com.isupatches.android.wisefy.savednetworks.os.apis

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.ACCESS_WIFI_STATE
import androidx.annotation.RequiresPermission

/**
 * A default internal API for getting and searching for saved networks through the Android OS.
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
internal interface DefaultSavedNetworkApi {

    /**
     * A default internal API that is used to get all saved networks through the Android OS.
     *
     * @return List<WifiConfiguration> - The list of saved networks
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    @Suppress("Deprecation")
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    fun getSavedNetworks(): List<android.net.wifi.WifiConfiguration>

    /**
     * A default internal API that is used to search for saved networks by SSID through the Android OS.
     *
     * @param regexForSSID The regex to match for the saved network's SSID
     *
     * @return List<WifiConfiguration> - The list of matching saved networks
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    @Suppress("Deprecation")
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    fun searchForSavedNetworksBySSID(regexForSSID: String): List<android.net.wifi.WifiConfiguration>

    /**
     * A default internal API that is used to search for saved networks by BSSID through the Android OS.
     *
     * @param regexForBSSID The regex to match for the saved network's BSSID
     *
     * @return List<WifiConfiguration> - The list of matching saved networks
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    @Suppress("Deprecation")
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    fun searchForSavedNetworksByBSSID(regexForBSSID: String): List<android.net.wifi.WifiConfiguration>
}
