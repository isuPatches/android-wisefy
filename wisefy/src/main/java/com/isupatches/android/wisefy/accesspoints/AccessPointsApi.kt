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
package com.isupatches.android.wisefy.accesspoints

import android.Manifest.permission.ACCESS_FINE_LOCATION
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.accesspoints.callbacks.GetNearbyAccessPointCallbacks
import com.isupatches.android.wisefy.accesspoints.callbacks.GetRSSICallbacks
import com.isupatches.android.wisefy.accesspoints.callbacks.SearchForAccessPointCallbacks
import com.isupatches.android.wisefy.accesspoints.callbacks.SearchForAccessPointsCallbacks
import com.isupatches.android.wisefy.accesspoints.callbacks.SearchForSSIDCallbacks
import com.isupatches.android.wisefy.accesspoints.callbacks.SearchForSSIDsCallbacks
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
 * A set of synchronous APIs for getting and searching for nearby access points.
 *
 * @author Patches Klinefelter
 * @since 02/2022
 */
interface AccessPointsApi {

    /**
     * A synchronous API to get a list of all nearby access points.
     *
     * @param request The details of the request to get nearby access points
     *
     * @see GetNearbyAccessPointsRequest
     * @see AccessPointData
     *
     * @return List<AccessPointData> - List of access points or empty list
     *
     * @author Patches Klinefelter
     * @since 02/2022
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun getNearbyAccessPoints(request: GetNearbyAccessPointsRequest): List<AccessPointData>

    /**
     * A synchronous API to get a network's RSSI level.
     *
     * @param request The details of the request to get a networks RSSI level
     *
     * @see GetRSSIRequest
     * @see RSSIData
     *
     * @return RSSIData - RSSI data if network found, otherwise null
     *
     * @author Patches Klinefelter
     * @since 02/2022
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun getRSSI(request: GetRSSIRequest): RSSIData?

    /**
     * A synchronous API to search for a nearby access point.
     *
     * @param request The details of the request to search for a nearby access point.
     *
     * @see SearchForSingleAccessPointRequest
     * @see AccessPointData
     *
     * @return AccessPointData - Access point data if network found, otherwise null
     *
     * @author Patches Klinefelter
     * @since 02/2022
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForAccessPoint(request: SearchForSingleAccessPointRequest): AccessPointData?

    /**
     * A synchronous API to search for a list of nearby access points.
     *
     * @param request The details of the request to search for a list of nearby access points.
     *
     * @see SearchForMultipleAccessPointsRequest
     * @see AccessPointData
     *
     * @return List<AccessPointData> - List of access points or empty list
     *
     * @author Patches Klinefelter
     * @since 02/2022
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForAccessPoints(request: SearchForMultipleAccessPointsRequest): List<AccessPointData>

    /**
     * An synchronous API to search for a nearby SSID.
     *
     * @param request The details of the request to search for a list of a nearby SSID.
     *
     * @see SearchForSingleSSIDRequest
     * @see SSIDData
     *
     * @return SSIDData - SSID data if network found, otherwise null
     *
     * @author Patches Klinefelter
     * @since 02/2022
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForSSID(request: SearchForSingleSSIDRequest): SSIDData?

    /**
     * A synchronous API to search for a list of nearby SSIDs.
     *
     * @param request The details of the request to search for a list of nearby SSIDs.
     *
     * @see SearchForMultipleSSIDsRequest
     * @see SSIDData
     *
     * @return List<SSIDData> - List of SSID data or empty list
     *
     * @author Patches Klinefelter
     * @since 02/2022
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForSSIDs(request: SearchForMultipleSSIDsRequest): List<SSIDData>
}

/**
 * A set of asynchronous APIs for getting and searching for nearby access points.
 *
 * @author Patches Klinefelter
 * @since 02/2022
 */
interface AccessPointsApiAsync {

    /**
     * An asynchronous API to get a list of all nearby access points.
     *
     * @param request The details of the request to get nearby access points
     * @param callbacks The callback for when all  nearby access points are returned
     *
     * @see GetNearbyAccessPointsRequest
     * @see GetNearbyAccessPointCallbacks
     *
     * @author Patches Klinefelter
     * @since 02/2022
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun getNearbyAccessPoints(request: GetNearbyAccessPointsRequest, callbacks: GetNearbyAccessPointCallbacks?)

    /**
     * An asynchronous API to get a network's RSSI level.
     *
     * @param request The details of the request to get a networks RSSI level
     * @param callbacks The callback for when the RSSI data is returned
     *
     * @see GetRSSIRequest
     * @see GetRSSICallbacks
     *
     * @author Patches Klinefelter
     * @since 02/2022
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun getRSSI(request: GetRSSIRequest, callbacks: GetRSSICallbacks?)

    /**
     * An asynchronous API to search for a nearby access point.
     *
     * @param request The details of the request to search for a nearby access point
     * @param callbacks The callback for when the list of access points is returned
     *
     * @see SearchForSingleAccessPointRequest
     * @see SearchForAccessPointCallbacks
     *
     * @author Patches Klinefelter
     * @since 02/2022
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForAccessPoint(request: SearchForSingleAccessPointRequest, callbacks: SearchForAccessPointCallbacks?)

    /**
     * An asynchronous API to search for a list of nearby access points.
     *
     * @param request The details of the request to search for a list of nearby access points
     * @param callbacks The callback for when the list of access points is returned
     *
     * @see SearchForMultipleAccessPointsRequest
     * @see SearchForAccessPointsCallbacks
     *
     * @author Patches Klinefelter
     * @since 02/2022
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForAccessPoints(request: SearchForMultipleAccessPointsRequest, callbacks: SearchForAccessPointsCallbacks?)

    /**
     * An asynchronous API to search for a nearby SSID.
     *
     * @param request The details of the request to search for a list of nearby SSIDs
     * @param callbacks The callback for when the SSID is returned
     *
     * @see SearchForSingleSSIDRequest
     * @see SearchForSSIDCallbacks
     *
     * @return List<SSIDData> - List of SSID data or empty list
     *
     * @author Patches Klinefelter
     * @since 02/2022
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForSSID(request: SearchForSingleSSIDRequest, callbacks: SearchForSSIDCallbacks?)

    /**
     * An asynchronous API to search for a list of nearby SSIDs.
     *
     * @param request The details of the request to search for a list of nearby SSIDs
     * @param callbacks The callback for when the list of SSIDs is returned
     *
     * @see SearchForMultipleSSIDsRequest
     * @see SearchForSSIDsCallbacks
     *
     * @return List<SSIDData> - List of SSID data or empty list
     *
     * @author Patches Klinefelter
     * @since 02/2022
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForSSIDs(request: SearchForMultipleSSIDsRequest, callbacks: SearchForSSIDsCallbacks?)
}
