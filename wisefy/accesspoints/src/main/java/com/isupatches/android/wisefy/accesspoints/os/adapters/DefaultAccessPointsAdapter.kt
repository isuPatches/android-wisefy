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
import android.util.Log
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.accesspoints.AccessPointsApi
import com.isupatches.android.wisefy.accesspoints.entities.GetAccessPointsQuery
import com.isupatches.android.wisefy.accesspoints.entities.GetAccessPointsResult
import com.isupatches.android.wisefy.accesspoints.os.apis.DefaultAccessPointsApi
import com.isupatches.android.wisefy.accesspoints.os.impls.DefaultAccessPointsApiImpl
import com.isupatches.android.wisefy.core.logging.WisefyLogger
import com.isupatches.android.wisefy.core.util.SdkUtil

/**
 * A default adapter for getting and searching for nearby access points.
 *
 * @param wifiManager The WifiManager instance to use
 * @param logger The [WisefyLogger] instance to use
 * @param sdkUtil The [SdkUtil] instance to use
 * @param api The OS level API instance to use
 *
 * @see AccessPointsApi
 * @see DefaultAccessPointsApi
 * @see DefaultAccessPointsApiImpl
 * @see SdkUtil
 * @see WisefyLogger
 *
 * @author Patches Klinefelter
 * @since 11/2022, version 5.0.0
 */
internal class DefaultAccessPointsAdapter(
    wifiManager: WifiManager,
    logger: WisefyLogger,
    sdkUtil: SdkUtil,
    private val api: DefaultAccessPointsApi = DefaultAccessPointsApiImpl(wifiManager, logger, sdkUtil)
) : AccessPointsApi {

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getAccessPoints(query: GetAccessPointsQuery): GetAccessPointsResult {
        Log.d("TEST", "BOOM== query: $query")
        val accessPoints = when (query) {
            is GetAccessPointsQuery.All -> api.getNearbyAccessPoints(filterDuplicates = query.filterDuplicates)
            is GetAccessPointsQuery.BySSID -> api.searchForAccessPointsBySSID(
                regex = query.regex,
                timeoutInMillis = query.timeoutInMillis,
                filterDuplicates = query.filterDuplicates
            )
            is GetAccessPointsQuery.ByBSSID -> api.searchForAccessPointsByBSSID(
                regex = query.regex,
                timeoutInMillis = query.timeoutInMillis,
                filterDuplicates = query.filterDuplicates
            )
        }
        return if (accessPoints.isNotEmpty()) {
            GetAccessPointsResult.AccessPoints(value = accessPoints)
        } else {
            GetAccessPointsResult.Empty
        }
    }
}
