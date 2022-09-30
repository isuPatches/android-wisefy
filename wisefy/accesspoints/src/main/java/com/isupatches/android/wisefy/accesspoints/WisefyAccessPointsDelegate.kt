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
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.accesspoints.callbacks.GetNearbyAccessPointCallbacks
import com.isupatches.android.wisefy.accesspoints.callbacks.GetRSSICallbacks
import com.isupatches.android.wisefy.accesspoints.callbacks.SearchForAccessPointsCallbacks
import com.isupatches.android.wisefy.accesspoints.entities.GetNearbyAccessPointsRequest
import com.isupatches.android.wisefy.accesspoints.entities.GetNearbyAccessPointsResult
import com.isupatches.android.wisefy.accesspoints.entities.GetRSSIRequest
import com.isupatches.android.wisefy.accesspoints.entities.GetRSSIResult
import com.isupatches.android.wisefy.accesspoints.entities.SearchForAccessPointsRequest
import com.isupatches.android.wisefy.accesspoints.entities.SearchForAccessPointsResult
import com.isupatches.android.wisefy.accesspoints.os.adapters.DefaultAccessPointsAdapter
import com.isupatches.android.wisefy.core.coroutines.CoroutineDispatcherProvider
import com.isupatches.android.wisefy.core.coroutines.createBaseCoroutineExceptionHandler
import com.isupatches.android.wisefy.core.logging.WisefyLogger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * An internal Wisefy delegate for getting and searching for nearby access points.
 *
 * @param coroutineDispatcherProvider The [CoroutineDispatcherProvider] instance to use
 * @param scope The coroutine scope to use
 * @param logger The [WisefyLogger] instance to use
 * @param wifiManager The WifiManager instance to use
 *
 * @see AccessPointsDelegate
 * @see CoroutineDispatcherProvider
 * @see WisefyLogger
 *
 * @author Patches Klinefelter
 * @since 08/2022, version 5.0.0
 */
class WisefyAccessPointsDelegate(
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider,
    private val scope: CoroutineScope,
    logger: WisefyLogger,
    wifiManager: WifiManager
) : AccessPointsDelegate {

    companion object {
        private const val LOG_TAG = "WisefyAccessPointsDelegate"
    }

    private val adapter = DefaultAccessPointsAdapter(wifiManager, logger)

    init {
        logger.d(LOG_TAG, "WisefyAccessPointsDelegate adapter is: ${adapter::class.java.simpleName}")
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getNearbyAccessPoints(request: GetNearbyAccessPointsRequest): GetNearbyAccessPointsResult {
        return adapter.getNearbyAccessPoints(request)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getNearbyAccessPoints(
        request: GetNearbyAccessPointsRequest,
        callbacks: GetNearbyAccessPointCallbacks?
    ) {
        scope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val result = adapter.getNearbyAccessPoints(request)
            withContext(coroutineDispatcherProvider.main) {
                when (result) {
                    is GetNearbyAccessPointsResult.Empty -> callbacks?.onNoNearbyAccessPoints()
                    is GetNearbyAccessPointsResult.AccessPoints -> {
                        callbacks?.onNearbyAccessPointsRetrieved(result.data)
                    }
                }
            }
        }
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getRSSI(request: GetRSSIRequest): GetRSSIResult {
        return adapter.getRSSI(request)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getRSSI(request: GetRSSIRequest, callbacks: GetRSSICallbacks?) {
        scope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val result = adapter.getRSSI(request)
            withContext(coroutineDispatcherProvider.main) {
                when (result) {
                    is GetRSSIResult.RSSI -> callbacks?.onRSSIRetrieved(result.data)
                    is GetRSSIResult.Empty -> callbacks?.onNoNetworkToRetrieveRSSI()
                }
            }
        }
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForAccessPoints(request: SearchForAccessPointsRequest): SearchForAccessPointsResult {
        return adapter.searchForAccessPoints(request)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForAccessPoints(
        request: SearchForAccessPointsRequest,
        callbacks: SearchForAccessPointsCallbacks?
    ) {
        scope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val result = adapter.searchForAccessPoints(request)
            withContext(coroutineDispatcherProvider.main) {
                when (result) {
                    is SearchForAccessPointsResult.AccessPoints -> callbacks?.onAccessPointsFound(result.data)
                    is SearchForAccessPointsResult.Empty -> callbacks?.onNoAccessPointsFound()
                }
            }
        }
    }
}
