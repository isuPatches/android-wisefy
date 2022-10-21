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
import com.isupatches.android.wisefy.accesspoints.callbacks.GetAccessPointsCallbacks
import com.isupatches.android.wisefy.accesspoints.entities.GetAccessPointsQuery

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
     * @param query The details of the request to get nearby access points
     * @param callbacks The optional callbacks for when nearby access points are returned
     *
     * @see GetAccessPointsQuery
     * @see GetAccessPointsCallbacks
     *
     * @author Patches Klinefelter
     * @since 08/2022, version 5.0.0
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun getAccessPoints(query: GetAccessPointsQuery, callbacks: GetAccessPointsCallbacks?)
}
