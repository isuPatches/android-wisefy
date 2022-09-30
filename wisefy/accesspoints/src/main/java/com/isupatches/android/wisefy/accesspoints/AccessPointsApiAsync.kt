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
import com.isupatches.android.wisefy.accesspoints.callbacks.SearchForAccessPointsCallbacks
import com.isupatches.android.wisefy.accesspoints.entities.GetNearbyAccessPointsRequest
import com.isupatches.android.wisefy.accesspoints.entities.GetRSSIRequest
import com.isupatches.android.wisefy.accesspoints.entities.SearchForAccessPointsRequest

/**
 * A set of asynchronous APIs for getting and searching for nearby access points.
 *
 * @author Patches Klinefelter
 * @since 08/2022, version 5.0.0
 */
interface AccessPointsApiAsync {

    /**
     * An asynchronous API to get a list of all nearby access points.
     *
     * @param request The details of the request to get nearby access points
     * @param callbacks The optional callbacks for when nearby access points are returned
     *
     * @see GetNearbyAccessPointsRequest
     * @see GetNearbyAccessPointCallbacks
     *
     * @author Patches Klinefelter
     * @since 08/2022, version 5.0.0
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun getNearbyAccessPoints(request: GetNearbyAccessPointsRequest, callbacks: GetNearbyAccessPointCallbacks?)

    /**
     * An asynchronous API to get a network's RSSI level.
     *
     * @param request The details of the request to get a networks RSSI level
     * @param callbacks The optional callbacks for when the RSSI data is returned
     *
     * @see GetRSSIRequest
     * @see GetRSSICallbacks
     *
     * @author Patches Klinefelter
     * @since 08/2022, version 5.0.0
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun getRSSI(request: GetRSSIRequest, callbacks: GetRSSICallbacks?)

    /**
     * An asynchronous API to search for a list of nearby access points.
     *
     * @param request The details of the request to search for a list of nearby access points
     * @param callbacks The optional callbacks for when the list of access points is returned
     *
     * @see SearchForAccessPointsRequest
     * @see SearchForAccessPointsCallbacks
     *
     * @author Patches Klinefelter
     * @since 08/2022, version 5.0.0
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForAccessPoints(request: SearchForAccessPointsRequest, callbacks: SearchForAccessPointsCallbacks?)
}
