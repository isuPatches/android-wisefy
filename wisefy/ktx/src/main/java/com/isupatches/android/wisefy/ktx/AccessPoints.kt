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
package com.isupatches.android.wisefy.ktx

import android.Manifest.permission.ACCESS_FINE_LOCATION
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.WisefyApi
import com.isupatches.android.wisefy.accesspoints.callbacks.GetAccessPointsCallbacks
import com.isupatches.android.wisefy.accesspoints.entities.AccessPointData
import com.isupatches.android.wisefy.accesspoints.entities.GetAccessPointsQuery
import com.isupatches.android.wisefy.accesspoints.entities.GetAccessPointsResult
import com.isupatches.android.wisefy.core.exceptions.WisefyException
import kotlin.coroutines.suspendCoroutine

/**
 * A coroutine extension for getting all nearby access points.
 *
 * *NOTES*
 *  - Internally locked by a mutex for all access point related functionality (f.e. get, search, etc.)
 *
 * @param query The details of the query to get all nearby access points
 *
 * @see GetAccessPointsQuery
 * @see GetAccessPointsResult
 *
 * @return GetNearbyAccessPointsResult - The result of getting all nearby access points
 *
 * @throws WisefyException
 *
 * @author Patches Barrett
 * @since 08/2022, version 5.0.0
 */
@Throws(WisefyException::class)
@RequiresPermission(ACCESS_FINE_LOCATION)
suspend fun WisefyApi.getAccessPointsAsync(
    query: GetAccessPointsQuery = GetAccessPointsQuery.All()
): GetAccessPointsResult =
    suspendCoroutine { continuation ->
        getAccessPoints(
            query = query,
            callbacks = object : GetAccessPointsCallbacks {
                override fun onNoNearbyAccessPoints() {
                    continuation.resumeWith(Result.success(GetAccessPointsResult.Empty))
                }

                override fun onNearbyAccessPointsRetrieved(accessPoints: List<AccessPointData>) {
                    continuation.resumeWith(Result.success(GetAccessPointsResult.AccessPoints(accessPoints)))
                }

                override fun onWisefyAsyncFailure(exception: WisefyException) {
                    continuation.resumeWith(Result.failure(exception))
                }
            }
        )
    }
