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
package com.isupatches.android.wisefy.addnetwork.os.apis

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.CHANGE_WIFI_STATE
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission

/**
 * An internal Android 30 or higher API for adding networks through the Android OS.
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
@RequiresApi(Build.VERSION_CODES.R)
internal interface Android30AddNetworkApi {

    /**
     * An API to add an open network for Android 30.
     *
     * @param ssid The SSID of the open network to add
     * @param bssid The optional BSSID for the open network being added
     *
     * @return Int - The result code for adding the open network suggestion
     * https://developer.android.com/reference/android/net/wifi/WifiManager#addNetworkSuggestions(java.util.List%3Candroid.net.wifi.WifiNetworkSuggestion%3E)
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    fun addOpenNetwork(ssid: String, bssid: String?): Int

    /**
     * An API to add a WPA2 network for Android 30.
     *
     * @param ssid The SSID of the WPA2 network to add
     * @param passphrase The passphrase to authenticate with the WPA2 network
     * @param bssid The optional BSSID for the WPA2 network being added
     *
     * @return Int - The result code for adding the WPA2 network suggestion
     * https://developer.android.com/reference/android/net/wifi/WifiManager#addNetworkSuggestions(java.util.List%3Candroid.net.wifi.WifiNetworkSuggestion%3E)
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    fun addWPA2Network(ssid: String, passphrase: String, bssid: String?): Int

    /**
     * An API to add a WPA3 network for Android 30.
     *
     * @param ssid The SSID of the WPA3 network to add
     * @param passphrase The passphrase to authenticate with the WPA3 network
     * @param bssid The optional BSSID for the WPA3 network being added
     *
     * @return Int - The result code for adding the WPA3 network suggestion
     * https://developer.android.com/reference/android/net/wifi/WifiManager#addNetworkSuggestions(java.util.List%3Candroid.net.wifi.WifiNetworkSuggestion%3E)
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    fun addWPA3Network(ssid: String, passphrase: String, bssid: String?): Int
}
