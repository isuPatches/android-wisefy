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
import com.isupatches.android.wisefy.accesspoints.entities.SearchForAccessPointResult
import com.isupatches.android.wisefy.accesspoints.entities.SearchForAccessPointsResult
import com.isupatches.android.wisefy.accesspoints.entities.SearchForMultipleAccessPointsRequest
import com.isupatches.android.wisefy.accesspoints.entities.SearchForMultipleSSIDsRequest
import com.isupatches.android.wisefy.accesspoints.entities.SearchForSSIDResult
import com.isupatches.android.wisefy.accesspoints.entities.SearchForSSIDsResult
import com.isupatches.android.wisefy.accesspoints.entities.SearchForSingleAccessPointRequest
import com.isupatches.android.wisefy.accesspoints.entities.SearchForSingleSSIDRequest
import com.isupatches.android.wisefy.accesspoints.os.apis.DefaultAccessPointsApi
import com.isupatches.android.wisefy.accesspoints.os.impls.DefaultAccessPointsApiImpl
import com.isupatches.android.wisefy.shared.logging.WisefyLogger

/**
 * A default adapter for getting and searching for nearby access points.
 *
 * @see AccessPointsApi
 *
 * @author Patches Klinefelter
 * @since 03/2022
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
    override fun searchForAccessPoint(request: SearchForSingleAccessPointRequest): SearchForAccessPointResult {
        val accessPoint = when (request) {
            is SearchForSingleAccessPointRequest.SSID -> api.searchForAccessPointBySSID(
                regexForSSID = request.regexForSSID,
                timeoutInMillis = request.timeoutInMillis,
                filterDuplicates = request.filterDuplicates
            )
            is SearchForSingleAccessPointRequest.BSSID -> api.searchForAccessPointByBSSID(
                regexForBSSID = request.regexForBSSID,
                timeoutInMillis = request.timeoutInMillis,
                filterDuplicates = request.filterDuplicates
            )
        }
        return if (accessPoint != null) {
            SearchForAccessPointResult.AccessPoint(data = accessPoint)
        } else {
            SearchForAccessPointResult.Empty
        }
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForAccessPoints(request: SearchForMultipleAccessPointsRequest): SearchForAccessPointsResult {
        val accessPoints = when (request) {
            is SearchForMultipleAccessPointsRequest.SSID -> api.searchForAccessPointsBySSID(
                regexForSSID = request.regexForSSID,
                filterDuplicates = request.filterDuplicates
            )
            is SearchForMultipleAccessPointsRequest.BSSID -> api.searchForAccessPointsByBSSID(
                regexForBSSID = request.regexForBSSID,
                filterDuplicates = request.filterDuplicates
            )
        }
        return if (accessPoints.isNotEmpty()) {
            SearchForAccessPointsResult.AccessPoints(data = accessPoints)
        } else {
            SearchForAccessPointsResult.Empty
        }
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForSSID(request: SearchForSingleSSIDRequest): SearchForSSIDResult {
        val ssid = when (request) {
            is SearchForSingleSSIDRequest.SSID -> api.searchForSSIDByRegex(
                regexForSSID = request.regexForSSID,
                timeoutInMillis = request.timeoutInMillis
            )
            is SearchForSingleSSIDRequest.BSSID -> api.searchForBSSIDByRegex(
                regexForBSSID = request.regexForBSSID,
                timeoutInMillis = request.timeoutInMillis
            )
        }
        return if (ssid != null) {
            SearchForSSIDResult.SSID(data = ssid)
        } else {
            SearchForSSIDResult.Empty
        }
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForSSIDs(request: SearchForMultipleSSIDsRequest): SearchForSSIDsResult {
        val ssids = when (request) {
            is SearchForMultipleSSIDsRequest.SSID -> api.searchForSSIDsByRegex(regexForSSID = request.regexForSSID)
            is SearchForMultipleSSIDsRequest.BSSID -> api.searchForBSSIDsByRegex(regexForBSSID = request.regexForBSSID)
        }
        return if (ssids.isNotEmpty()) {
            SearchForSSIDsResult.SSIDs(data = ssids)
        } else {
            SearchForSSIDsResult.Empty
        }
    }
}
