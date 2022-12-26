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

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.ACCESS_WIFI_STATE
import androidx.annotation.RequiresPermission

/**
 * A default internal API for removing a network through the Android OS.
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
internal interface DefaultRemoveNetworkApi {

    /**
     * An Android 30 API for removing a network by SSID.
     *
     * *NOTE*
     * - Only removes the first network whose SSID matches
     * - Returns for this are the same as `removeNetwork` found here:
     * https://developer.android.com/reference/android/net/wifi/WifiManager#removeNetwork(int)
     *
     * @param ssid The SSID of the network to remove
     *
     * @return Int - The result while removing the network a network
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    fun removeNetworkBySSID(ssid: String): Boolean

    /**
     * An Android 30 API for removing a network by BSSID.
     *
     * *NOTE*
     * - Only removes the first network whose BSSID matches
     * - Returns for this are the same as `removeNetwork` found here:
     * https://developer.android.com/reference/android/net/wifi/WifiManager#removeNetwork(int)
     *
     * @param bssid The BSSID of the network to remove
     *
     * @return Int - The result while removing the network a network
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    fun removeNetworkByBSSID(bssid: String): Boolean
}
