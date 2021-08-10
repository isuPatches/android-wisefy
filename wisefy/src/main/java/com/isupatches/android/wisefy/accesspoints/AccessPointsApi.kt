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
import com.isupatches.android.wisefy.callbacks.GetNearbyAccessPointCallbacks
import com.isupatches.android.wisefy.callbacks.GetRSSICallbacks
import com.isupatches.android.wisefy.callbacks.SearchForAccessPointCallbacks
import com.isupatches.android.wisefy.callbacks.SearchForAccessPointsCallbacks
import com.isupatches.android.wisefy.callbacks.SearchForSSIDCallbacks
import com.isupatches.android.wisefy.callbacks.SearchForSSIDsCallbacks

interface AccessPointsApi {

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun getNearbyAccessPoints(filterDuplicates: Boolean): List<AccessPointData>

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun getRSSI(regexForSSID: String, takeHighest: Boolean, timeoutInMillis: Int): Int?

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForAccessPoint(
        regexForSSID: String,
        timeoutInMillis: Int,
        filterDuplicates: Boolean
    ): AccessPointData?

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForAccessPoints(
        regexForSSID: String,
        filterDuplicates: Boolean
    ): List<AccessPointData>

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForSSID(regexForSSID: String, timeoutInMillis: Int): String?

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForSSIDs(regexForSSID: String): List<String>
}

interface AccessPointsApiAsync {

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun getNearbyAccessPoints(filterDuplicates: Boolean, callbacks: GetNearbyAccessPointCallbacks?)

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun getRSSI(regexForSSID: String, takeHighest: Boolean, timeoutInMillis: Int, callbacks: GetRSSICallbacks?)

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForAccessPoint(
        regexForSSID: String,
        timeoutInMillis: Int,
        filterDuplicates: Boolean,
        callbacks: SearchForAccessPointCallbacks?
    )

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForAccessPoints(
        regexForSSID: String,
        filterDuplicates: Boolean,
        callbacks: SearchForAccessPointsCallbacks?
    )

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForSSID(regexForSSID: String, timeoutInMillis: Int, callbacks: SearchForSSIDCallbacks?)

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForSSIDs(regexForSSID: String, callbacks: SearchForSSIDsCallbacks?)
}