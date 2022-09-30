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
package com.isupatches.android.wisefy.accesspoints.os.apis

import android.Manifest.permission.ACCESS_FINE_LOCATION
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.accesspoints.entities.AccessPointData
import com.isupatches.android.wisefy.accesspoints.entities.RSSIData

/**
 * A default internal API for getting and searching for nearby access points through the Android OS.
 *
 * @author Patches Klinefelter
 * @since 08/2022, version 5.0.0
 */
internal interface DefaultAccessPointsApi {

    /**
     * An internal API to get a list of nearby access points.
     *
     * @param filterDuplicates Whether nearby access points with the same SSID but lower RSSI levels should be excluded
     *
     * @see AccessPointData
     *
     * @return List<AccessPointData> - List of access points or empty list
     *
     * @author Patches Klinefelter
     * @since 08/2022, version 5.0.0
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun getNearbyAccessPoints(filterDuplicates: Boolean): List<AccessPointData>

    /**
     * An internal API to get a network's RSSI level by SSID.
     *
     * @param regexForSSID The regex to use when matching the access point's SSID
     * @param takeHighest Whether the request returns the first RSSI value or the highest RSSI value for the network
     *  in the case of one that has duplicate access points
     * @param timeoutInMillis How long the request should wait to find a network with a matching SSID
     *
     * @see RSSIData
     *
     * @return RSSIData - RSSI data if a matching network is found, otherwise null
     *
     * @author Patches Klinefelter
     * @since 08/2022, version 5.0.0
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun getRSSIBySSID(regexForSSID: String, takeHighest: Boolean, timeoutInMillis: Int): RSSIData?

    /**
     * An internal API to get a network's RSSI level by BSSID.
     *
     * @param regexForBSSID The regex to use when matching the access point's BSSID
     * @param takeHighest Whether the request returns the first RSSI value or the highest RSSI value for the network
     *  in the case of one that has duplicate access points
     * @param timeoutInMillis How long the request should wait to find the network with a matching BSSID
     *
     * @see RSSIData
     *
     * @return RSSIData - RSSI data if a matching network is found, otherwise null
     *
     * @author Patches Klinefelter
     * @since 08/2022, version 5.0.0
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun getRSSIByBSSID(regexForBSSID: String, takeHighest: Boolean, timeoutInMillis: Int): RSSIData?

    /**
     * An internal API to search for nearby access points by SSID.
     *
     * @param regexForSSID The regex to use when matching the access point's SSID
     * @param filterDuplicates Whether the request returns the first RSSI value or the highest RSSI value for the
     * network in the case of one that has duplicate access points
     *
     * @see AccessPointData
     *
     * @return List<AccessPointData> - The list of matching access points or empty list if there are none
     * *
     * @author Patches Klinefelter
     * @since 08/2022, version 5.0.0
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForAccessPointsBySSID(
        regexForSSID: String,
        timeoutInMillis: Int?,
        filterDuplicates: Boolean = true
    ): List<AccessPointData>

    /**
     * An internal API to search for nearby access points by BSSID.
     *
     * @param regexForBSSID The regex to use when matching the access point's BSSID
     * @param filterDuplicates Whether the request returns the first RSSI value or the highest RSSI value for the
     * network in the case of one that has duplicate access points
     *
     * @see AccessPointData
     *
     * @return List<AccessPointData> - The list of matching access points or empty list if there are none
     * *
     * @author Patches Klinefelter
     * @since 08/2022, version 5.0.0
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForAccessPointsByBSSID(
        regexForBSSID: String,
        timeoutInMillis: Int?,
        filterDuplicates: Boolean = true
    ): List<AccessPointData>
}
