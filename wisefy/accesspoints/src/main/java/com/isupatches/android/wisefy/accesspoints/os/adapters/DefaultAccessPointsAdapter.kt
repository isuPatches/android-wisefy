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
package com.isupatches.android.wisefy.accesspoints.os.adapters

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.accesspoints.AccessPointsApi
import com.isupatches.android.wisefy.accesspoints.entities.GetNearbyAccessPointsRequest
import com.isupatches.android.wisefy.accesspoints.entities.GetNearbyAccessPointsResult
import com.isupatches.android.wisefy.accesspoints.entities.GetRSSIRequest
import com.isupatches.android.wisefy.accesspoints.entities.GetRSSIResult
import com.isupatches.android.wisefy.accesspoints.entities.SearchForAccessPointsRequest
import com.isupatches.android.wisefy.accesspoints.entities.SearchForAccessPointsResult
import com.isupatches.android.wisefy.accesspoints.os.apis.DefaultAccessPointsApi
import com.isupatches.android.wisefy.accesspoints.os.impls.DefaultAccessPointsApiImpl
import com.isupatches.android.wisefy.core.logging.WisefyLogger

/**
 * A default adapter for getting and searching for nearby access points.
 *
 * @param wifiManager The WifiManager instance to use
 * @param logger The [WisefyLogger] instance to use
 * @param api The OS level API instance to use
 *
 * @see AccessPointsApi
 * @see DefaultAccessPointsApi
 * @see DefaultAccessPointsApiImpl
 * @see WisefyLogger
 *
 * @author Patches Klinefelter
 * @since 08/2022, version 5.0.0
 */
internal class DefaultAccessPointsAdapter(
    wifiManager: WifiManager,
    logger: WisefyLogger,
    private val api: DefaultAccessPointsApi = DefaultAccessPointsApiImpl(wifiManager, logger)
) : AccessPointsApi {

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getNearbyAccessPoints(request: GetNearbyAccessPointsRequest): GetNearbyAccessPointsResult {
        val accessPoints = api.getNearbyAccessPoints(filterDuplicates = request.filterDuplicates)
        return if (accessPoints.isNotEmpty()) {
            GetNearbyAccessPointsResult.AccessPoints(data = accessPoints)
        } else {
            GetNearbyAccessPointsResult.Empty
        }
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getRSSI(request: GetRSSIRequest): GetRSSIResult {
        val rssi = when (request) {
            is GetRSSIRequest.SSID -> api.getRSSIBySSID(
                regexForSSID = request.regexForSSID,
                takeHighest = request.takeHighest,
                timeoutInMillis = request.timeoutInMillis
            )
            is GetRSSIRequest.BSSID -> api.getRSSIByBSSID(
                regexForBSSID = request.regexForBSSID,
                takeHighest = request.takeHighest,
                timeoutInMillis = request.timeoutInMillis
            )
        }
        return if (rssi != null) {
            GetRSSIResult.RSSI(data = rssi)
        } else {
            GetRSSIResult.Empty
        }
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForAccessPoints(request: SearchForAccessPointsRequest): SearchForAccessPointsResult {
        val accessPoints = when (request) {
            is SearchForAccessPointsRequest.SSID -> api.searchForAccessPointsBySSID(
                regexForSSID = request.regex,
                timeoutInMillis = request.timeoutInMillis,
                filterDuplicates = request.filterDuplicates
            )
            is SearchForAccessPointsRequest.BSSID -> api.searchForAccessPointsByBSSID(
                regexForBSSID = request.regex,
                timeoutInMillis = request.timeoutInMillis,
                filterDuplicates = request.filterDuplicates
            )
        }
        return if (accessPoints.isNotEmpty()) {
            SearchForAccessPointsResult.AccessPoints(data = accessPoints)
        } else {
            SearchForAccessPointsResult.Empty
        }
    }
}
