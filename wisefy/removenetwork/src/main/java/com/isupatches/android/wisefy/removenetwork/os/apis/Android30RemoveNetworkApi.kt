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
package com.isupatches.android.wisefy.removenetwork.os.apis

import android.Manifest.permission.CHANGE_WIFI_STATE
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission

/**
 * An Android 30 or higher API for removing a network through the Android OS.
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
@RequiresApi(Build.VERSION_CODES.R)
internal interface Android30RemoveNetworkApi {

    /**
     * An Android 30 API for removing a network by SSID.
     *
     * *NOTE*
     * - Only removes the first network whose SSID matches
     * - Returns for this are the same as `removeNetworkSuggestion` found here:
     *  https://developer.android.com/reference/android/net/wifi/WifiManager#removeNetworkSuggestions(java.util.List%3Candroid.net.wifi.WifiNetworkSuggestion%3E,%20int)
     *
     * @param ssid The SSID of the network to remove
     *
     * @return Int - The result while removing the network a network
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    @RequiresPermission(CHANGE_WIFI_STATE)
    fun removeNetworkBySSID(ssid: String): Int

    /**
     * An Android 30 API for removing a network by BSSID.
     *
     * *NOTE*
     * - Only removes the first network whose BSSID matches
     * - Returns for this are the same as `removeNetworkSuggestion` found here:
     *  https://developer.android.com/reference/android/net/wifi/WifiManager#removeNetworkSuggestions(java.util.List%3Candroid.net.wifi.WifiNetworkSuggestion%3E,%20int)
     *
     * @param bssid The BSSID of the network to remove
     *
     * @return Int - The result while removing the network a network
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    @RequiresPermission(CHANGE_WIFI_STATE)
    fun removeNetworkByBSSID(bssid: String): Int
}
