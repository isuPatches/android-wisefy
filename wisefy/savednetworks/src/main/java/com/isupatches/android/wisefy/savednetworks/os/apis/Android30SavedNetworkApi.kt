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
package com.isupatches.android.wisefy.savednetworks.os.apis

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.net.wifi.WifiNetworkSuggestion
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission

/**
 * An Android 30 specific internal API for getting and searching for saved networks through the Android OS.
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
@RequiresApi(Build.VERSION_CODES.R)
internal interface Android30SavedNetworkApi {

    /**
     * An Android 30 specific internal API that is used to get all saved networks through the Android OS.
     *
     * @return List<WifiNetworkSuggestion> - The list of saved networks
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    fun getSavedNetworks(): List<WifiNetworkSuggestion>

    /**
     * An Android 30 specific internal API that is used to check if there is a saved network by SSID through the
     * Android OS.
     *
     * @param regexForSSID The regex to match for the saved network's SSID
     *
     * @return Boolean - True if there is a matching saved network, otherwise false
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    fun isNetworkSavedWithSSID(regexForSSID: String): Boolean

    /**
     * An Android 30 specific internal API that is used to check if there is a saved network by BSSID through the
     * Android OS.
     *
     * @param regexForBSSID The regex to match for the saved network's BSSID
     *
     * @return Boolean - True if there is a matching saved network, otherwise false
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    fun isNetworkSavedWithBSSID(regexForBSSID: String): Boolean

    /**
     * An Android 30 specific internal API that is used to search for a saved network by SSID through the Android OS.
     *
     * @param regexForSSID The regex to match for the saved network's SSID
     *
     * @return WifiNetworkSuggestion or null - The matching saved network or null if there isn't one
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    fun searchForSavedNetworkBySSID(regexForSSID: String): WifiNetworkSuggestion?

    /**
     * An Android 30 specific internal API that is used to search for a saved network by BSSID through the Android OS.
     *
     * @param regexForBSSID The regex to match for the saved network's BSSID
     *
     * @return WifiNetworkSuggestion or null - The matching saved network or null if there isn't one
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    fun searchForSavedNetworkByBSSID(regexForBSSID: String): WifiNetworkSuggestion?

    /**
     * An Android 30 specific internal API that is used to search for saved networks by SSID through the Android OS.
     *
     * @param regexForSSID The regex to match for the saved network's SSID
     *
     * @return List<WifiNetworkSuggestion> - The list of matching saved networks
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    fun searchForSavedNetworksBySSID(regexForSSID: String): List<WifiNetworkSuggestion>

    /**
     * An Android 30 specific internal API that is used to search for saved networks by BSSID through the Android OS.
     *
     * @param regexForBSSID The regex to match for the saved network's BSSID
     *
     * @return List<WifiNetworkSuggestion> - The list of matching saved networks
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    fun searchForSavedNetworksByBSSID(regexForBSSID: String): List<WifiNetworkSuggestion>
}
