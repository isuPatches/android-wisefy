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
import com.isupatches.android.wisefy.accesspoints.entities.GetNearbyAccessPointsRequest
import com.isupatches.android.wisefy.accesspoints.entities.GetRSSIRequest
import com.isupatches.android.wisefy.accesspoints.entities.RSSIData
import com.isupatches.android.wisefy.accesspoints.entities.SSIDData
import com.isupatches.android.wisefy.accesspoints.entities.SearchForMultipleAccessPointsRequest
import com.isupatches.android.wisefy.accesspoints.entities.SearchForMultipleSSIDsRequest
import com.isupatches.android.wisefy.accesspoints.entities.SearchForSingleAccessPointRequest
import com.isupatches.android.wisefy.accesspoints.entities.SearchForSingleSSIDRequest

/**
 * A default internal API for getting and searching for nearby access points through the Android OS.
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
internal interface DefaultAccessPointsApi {

    /**
     * An internal API to get a list of nearby access points.
     *
     * @param filterDuplicates Whether nearby access points with the same SSID but lower RSSI levels should be excluded
     *
     * @see GetNearbyAccessPointsRequest
     * @see AccessPointData
     *
     * @return List<AccessPointData> - List of access points or empty list
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun getNearbyAccessPoints(filterDuplicates: Boolean): List<AccessPointData>

    /**
     * An internal API to get a network's RSSI level.
     *
     * @param request The details of the request to get a networks RSSI level
     *
     * @see GetRSSIRequest
     * @see RSSIData
     *
     * @return RSSIData - RSSI data if network found, otherwise null
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun getRSSIBySSID(regexForSSID: String, takeHighest: Boolean, timeoutInMillis: Int): RSSIData?

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun getRSSIByBSSID(regexForBSSID: String, takeHighest: Boolean, timeoutInMillis: Int): RSSIData?

    /**
     * An internal API to search for a nearby access point.
     *
     * @param request The details of the request to search for a nearby access point
     *
     * @see SearchForSingleAccessPointRequest
     * @see AccessPointData
     *
     * @return AccessPointData - Access point data if network found, otherwise null
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForAccessPointBySSID(
        regexForSSID: String,
        timeoutInMillis: Int,
        filterDuplicates: Boolean = true
    ): AccessPointData?

    /**
     * An internal API to search for a list of nearby access points.
     *
     * @param request The details of the request to search for a list of nearby access points
     *
     * @see SearchForMultipleAccessPointsRequest
     * @see AccessPointData
     *
     * @return List<AccessPointData> - List of access points or empty list
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForAccessPointByBSSID(
        regexForBSSID: String,
        timeoutInMillis: Int,
        filterDuplicates: Boolean = true
    ): AccessPointData?

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForAccessPointsBySSID(
        regexForSSID: String,
        filterDuplicates: Boolean = true
    ): List<AccessPointData>

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForAccessPointsByBSSID(
        regexForBSSID: String,
        filterDuplicates: Boolean = true
    ): List<AccessPointData>

    /**
     * An internal API to search for a nearby SSID.
     *
     * @param request The details of the request to search for a list of a nearby SSID
     *
     * @see SearchForSingleSSIDRequest
     * @see SSIDData
     *
     * @return SSIDData - SSID data if network found, otherwise null
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForSSIDByRegex(regexForSSID: String, timeoutInMillis: Int): SSIDData?

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForBSSIDByRegex(regexForBSSID: String, timeoutInMillis: Int): SSIDData?

    /**
     * An internal API to search for a list of nearby SSIDs.
     *
     * @param request The details of the request to search for a list of nearby SSIDs
     *
     * @see SearchForMultipleSSIDsRequest
     * @see SSIDData
     *
     * @return List<SSIDData> - List of SSID data or empty list
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForSSIDsByRegex(regexForSSID: String): List<SSIDData>

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForBSSIDsByRegex(regexForBSSID: String): List<SSIDData>
}
