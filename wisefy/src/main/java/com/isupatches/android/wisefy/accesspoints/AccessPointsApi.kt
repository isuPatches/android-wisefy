/*
 * Copyright 2021 Patches Klinefelter
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
import com.isupatches.android.wisefy.accesspoints.entities.AccessPointData
import com.isupatches.android.wisefy.accesspoints.entities.GetNearbyAccessPointsRequest
import com.isupatches.android.wisefy.accesspoints.entities.GetRSSIRequest
import com.isupatches.android.wisefy.accesspoints.entities.RSSIData
import com.isupatches.android.wisefy.accesspoints.entities.SSIDData
import com.isupatches.android.wisefy.accesspoints.entities.SearchForMultipleAccessPointsRequest
import com.isupatches.android.wisefy.accesspoints.entities.SearchForMultipleSSIDsRequest
import com.isupatches.android.wisefy.accesspoints.entities.SearchForSingleAccessPointRequest
import com.isupatches.android.wisefy.accesspoints.entities.SearchForSingleSSIDRequest
import com.isupatches.android.wisefy.callbacks.GetNearbyAccessPointCallbacks
import com.isupatches.android.wisefy.callbacks.GetRSSICallbacks
import com.isupatches.android.wisefy.callbacks.SearchForAccessPointCallbacks
import com.isupatches.android.wisefy.callbacks.SearchForAccessPointsCallbacks
import com.isupatches.android.wisefy.callbacks.SearchForSSIDCallbacks
import com.isupatches.android.wisefy.callbacks.SearchForSSIDsCallbacks

/**
 *
 * @author Patches Klinefelter
 * @since 08/2021
 */
interface AccessPointsApi {

    /**
     *
     * @see GetNearbyAccessPointsRequest
     * @see AccessPointData
     *
     * @author Patches Klinefelter
     * @since 08/2021
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun getNearbyAccessPoints(request: GetNearbyAccessPointsRequest): List<AccessPointData>

    /**
     *
     * @see GetRSSIRequest
     * @see RSSIData
     *
     * @author Patches Klinefelter
     * @since 08/2021
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun getRSSI(request: GetRSSIRequest): RSSIData?

    /**
     *
     * @see SearchForSingleAccessPointRequest
     * @see AccessPointData
     *
     * @author Patches Klinefelter
     * @since 08/2021
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForAccessPoint(request: SearchForSingleAccessPointRequest): AccessPointData?

    /**
     *
     * @see SearchForMultipleAccessPointsRequest
     * @see AccessPointData
     *
     * @author Patches Klinefelter
     * @since 08/2021
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForAccessPoints(request: SearchForMultipleAccessPointsRequest): List<AccessPointData>

    /**
     *
     * @see SearchForSingleSSIDRequest
     * @see SSIDData
     *
     * @author Patches Klinefelter
     * @since 08/2021
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForSSID(request: SearchForSingleSSIDRequest): SSIDData?

    /**
     *
     * @see SearchForMultipleSSIDsRequest
     * @see SSIDData
     *
     * @author Patches Klinefelter
     * @since 08/2021
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForSSIDs(request: SearchForMultipleSSIDsRequest): List<SSIDData>
}

/**
 *
 * @author Patches Klinefelter
 * @since 08/2021
 */
interface AccessPointsApiAsync {

    /**
     *
     * @see GetNearbyAccessPointsRequest
     * @see GetNearbyAccessPointCallbacks
     *
     * @author Patches Klinefelter
     * @since 08/2021
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun getNearbyAccessPoints(request: GetNearbyAccessPointsRequest, callbacks: GetNearbyAccessPointCallbacks?)

    /**
     *
     * @see GetRSSIRequest
     * @see GetRSSICallbacks
     *
     * @author Patches Klinefelter
     * @since 08/2021
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun getRSSI(request: GetRSSIRequest, callbacks: GetRSSICallbacks?)

    /**
     *
     * @see SearchForSingleAccessPointRequest
     * @see SearchForAccessPointCallbacks
     *
     * @author Patches Klinefelter
     * @since 08/2021
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForAccessPoint(request: SearchForSingleAccessPointRequest, callbacks: SearchForAccessPointCallbacks?)

    /**
     *
     * @see SearchForMultipleAccessPointsRequest
     * @see SearchForAccessPointsCallbacks
     *
     * @author Patches Klinefelter
     * @since 08/2021
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForAccessPoints(request: SearchForMultipleAccessPointsRequest, callbacks: SearchForAccessPointsCallbacks?)

    /**
     *
     * @see SearchForSingleSSIDRequest
     * @see SearchForSSIDCallbacks
     *
     * @author Patches Klinefelter
     * @since 08/2021
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForSSID(request: SearchForSingleSSIDRequest, callbacks: SearchForSSIDCallbacks?)

    /**
     *
     * @see SearchForMultipleSSIDsRequest
     * @see SearchForSSIDsCallbacks
     *
     * @author Patches Klinefelter
     * @since 08/2021
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForSSIDs(request: SearchForMultipleSSIDsRequest, callbacks: SearchForSSIDsCallbacks?)
}
