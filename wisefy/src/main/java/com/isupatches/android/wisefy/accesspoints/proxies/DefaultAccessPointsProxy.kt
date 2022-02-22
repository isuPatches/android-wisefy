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
package com.isupatches.android.wisefy.accesspoints.proxies

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.accesspoints.AccessPointsApi
import com.isupatches.android.wisefy.accesspoints.entities.AccessPointData
import com.isupatches.android.wisefy.accesspoints.entities.GetNearbyAccessPointsRequest
import com.isupatches.android.wisefy.accesspoints.entities.GetRSSIRequest
import com.isupatches.android.wisefy.accesspoints.entities.RSSIData
import com.isupatches.android.wisefy.accesspoints.entities.SSIDData
import com.isupatches.android.wisefy.accesspoints.entities.SearchForMultipleAccessPointsRequest
import com.isupatches.android.wisefy.accesspoints.entities.SearchForMultipleSSIDsRequest
import com.isupatches.android.wisefy.accesspoints.entities.SearchForSingleAccessPointRequest
import com.isupatches.android.wisefy.accesspoints.entities.SearchForSingleSSIDRequest
import com.isupatches.android.wisefy.accesspoints.impl.DefaultAccessPointsApi
import com.isupatches.android.wisefy.accesspoints.impl.DefaultAccessPointsApiImpl
import com.isupatches.android.wisefy.logging.WisefyLogger

/**
 * A default proxy for getting and searching for nearby access points.
 *
 * @see AccessPointsApi
 *
 * @author Patches Klinefelter
 * @since 02/2022
 */
internal class DefaultAccessPointsProxy(
    wifiManager: WifiManager,
    logger: WisefyLogger?,
    private val impl: DefaultAccessPointsApi = DefaultAccessPointsApiImpl(wifiManager, logger)
) : AccessPointsApi {

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getNearbyAccessPoints(request: GetNearbyAccessPointsRequest): List<AccessPointData> {
        return impl.getNearbyAccessPoints(request)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getRSSI(request: GetRSSIRequest): RSSIData? {
        return impl.getRSSI(request)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForAccessPoint(request: SearchForSingleAccessPointRequest): AccessPointData? {
        return impl.searchForAccessPoint(request)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForAccessPoints(request: SearchForMultipleAccessPointsRequest): List<AccessPointData> {
        return impl.searchForAccessPoints(request)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForSSID(request: SearchForSingleSSIDRequest): SSIDData? {
        return impl.searchForSSID(request)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForSSIDs(request: SearchForMultipleSSIDsRequest): List<SSIDData> {
        return impl.searchForSSIDs(request)
    }
}
