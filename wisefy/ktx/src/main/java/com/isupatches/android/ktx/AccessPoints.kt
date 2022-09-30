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
package com.isupatches.android.ktx

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.ACCESS_WIFI_STATE
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.WisefyApi
import com.isupatches.android.wisefy.accesspoints.callbacks.GetNearbyAccessPointCallbacks
import com.isupatches.android.wisefy.accesspoints.callbacks.GetRSSICallbacks
import com.isupatches.android.wisefy.accesspoints.callbacks.SearchForAccessPointsCallbacks
import com.isupatches.android.wisefy.accesspoints.entities.AccessPointData
import com.isupatches.android.wisefy.accesspoints.entities.GetNearbyAccessPointsRequest
import com.isupatches.android.wisefy.accesspoints.entities.GetNearbyAccessPointsResult
import com.isupatches.android.wisefy.accesspoints.entities.GetRSSIRequest
import com.isupatches.android.wisefy.accesspoints.entities.GetRSSIResult
import com.isupatches.android.wisefy.accesspoints.entities.RSSIData
import com.isupatches.android.wisefy.accesspoints.entities.SearchForAccessPointsRequest
import com.isupatches.android.wisefy.accesspoints.entities.SearchForAccessPointsResult
import com.isupatches.android.wisefy.core.exceptions.WisefyException
import kotlin.coroutines.suspendCoroutine
import kotlin.jvm.Throws

/**
 * A coroutine extension for getting all nearby access points.
 *
 * *NOTES*
 *  - Internally locked by a mutex for all access point related functionality (f.e. get, search, etc.)
 *
 * @param request The details of the request to get all nearby access points
 *
 * @see GetNearbyAccessPointsRequest
 * @see GetNearbyAccessPointsResult
 *
 * @return GetNearbyAccessPointsResult - The result of getting all nearby access points
 *
 * @throws WisefyException
 *
 * @author Patches Klinefelter
 * @since 08/2022, version 5.0.0
 */
@Throws(WisefyException::class)
@RequiresPermission(ACCESS_FINE_LOCATION)
suspend fun WisefyApi.getNearbyAccessPointsAsync(
    request: GetNearbyAccessPointsRequest = GetNearbyAccessPointsRequest()
): GetNearbyAccessPointsResult =
    suspendCoroutine { continuation ->
        getNearbyAccessPoints(request, object : GetNearbyAccessPointCallbacks {
            override fun onNoNearbyAccessPoints() {
                continuation.resumeWith(Result.success(GetNearbyAccessPointsResult.Empty))
            }

            override fun onNearbyAccessPointsRetrieved(accessPoints: List<AccessPointData>) {
                continuation.resumeWith(Result.success(GetNearbyAccessPointsResult.AccessPoints(accessPoints)))
            }

            override fun onWisefyAsyncFailure(exception: WisefyException) {
                continuation.resumeWith(Result.failure(exception))
            }
        })
    }

@Throws(WisefyException::class)
@RequiresPermission(ACCESS_FINE_LOCATION)
suspend fun WisefyApi.getRSSIAsync(request: GetRSSIRequest): GetRSSIResult = suspendCoroutine { continuation ->
    getRSSI(request, object : GetRSSICallbacks {
        override fun onRSSIRetrieved(rssi: RSSIData) {
            continuation.resumeWith(Result.success(GetRSSIResult.RSSI(rssi)))
        }

        override fun onNoNetworkToRetrieveRSSI() {
            continuation.resumeWith(Result.success(GetRSSIResult.Empty))
        }

        override fun onWisefyAsyncFailure(exception: WisefyException) {
            continuation.resumeWith(Result.failure(exception))
        }
    })
}

@Throws(WisefyException::class)
@RequiresPermission(ACCESS_FINE_LOCATION)
suspend fun WisefyApi.searchForAccessPointsAsync(request: SearchForAccessPointsRequest): SearchForAccessPointsResult =
    suspendCoroutine { continuation ->
        searchForAccessPoints(request, object : SearchForAccessPointsCallbacks {
            override fun onAccessPointsFound(accessPoints: List<AccessPointData>) {
                continuation.resumeWith(Result.success(SearchForAccessPointsResult.AccessPoints(accessPoints)))
            }

            override fun onNoAccessPointsFound() {
                continuation.resumeWith(Result.success(SearchForAccessPointsResult.Empty))
            }

            override fun onWisefyAsyncFailure(exception: WisefyException) {
                continuation.resumeWith(Result.failure(exception))
            }
        })
    }
