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
import com.isupatches.android.wisefy.accesspoints.callbacks.SearchForAccessPointCallbacks
import com.isupatches.android.wisefy.accesspoints.callbacks.SearchForAccessPointsCallbacks
import com.isupatches.android.wisefy.accesspoints.callbacks.SearchForSSIDCallbacks
import com.isupatches.android.wisefy.accesspoints.callbacks.SearchForSSIDsCallbacks
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
import com.isupatches.android.wisefy.accesspoints.os.adapters.DefaultAccessPointsAdapter
import com.isupatches.android.wisefy.shared.coroutines.CoroutineDispatcherProvider
import com.isupatches.android.wisefy.shared.coroutines.createBaseCoroutineExceptionHandler
import com.isupatches.android.wisefy.shared.logging.WisefyLogger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A default Wisefy delegate for getting and searching for nearby access points.
 *
 * @param coroutineDispatcherProvider The instance of the coroutine dispatcher provider to use
 * @param scope The coroutine scope to use
 * @param logger The logger instance to use
 * @param wifiManager The WifiManager instance to use
 *
 * @see AccessPointsDelegate
 * @see CoroutineDispatcherProvider
 * @see WisefyLogger
 *
 * @author Patches Klinefelter
 * @since 03/2022
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
    override fun searchForAccessPoint(request: SearchForSingleAccessPointRequest): SearchForAccessPointResult {
        return adapter.searchForAccessPoint(request)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForAccessPoint(
        request: SearchForSingleAccessPointRequest,
        callbacks: SearchForAccessPointCallbacks?
    ) {
        scope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val result = adapter.searchForAccessPoint(request)
            withContext(coroutineDispatcherProvider.main) {
                when (result) {
                    is SearchForAccessPointResult.Empty -> callbacks?.onNoAccessPointFound()
                    is SearchForAccessPointResult.AccessPoint -> callbacks?.onAccessPointFound(result.data)
                }
            }
        }
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForAccessPoints(
        request: SearchForMultipleAccessPointsRequest
    ): SearchForAccessPointsResult {
        return adapter.searchForAccessPoints(request)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForAccessPoints(
        request: SearchForMultipleAccessPointsRequest,
        callbacks: SearchForAccessPointsCallbacks?
    ) {
        scope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val result = adapter.searchForAccessPoints(request)
            withContext(coroutineDispatcherProvider.main) {
                when (result) {
                    is SearchForAccessPointsResult.Empty -> callbacks?.onNoAccessPointsFound()
                    is SearchForAccessPointsResult.AccessPoints -> callbacks?.onAccessPointsFound(result.data)
                }
            }
        }
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForSSID(request: SearchForSingleSSIDRequest): SearchForSSIDResult {
        return adapter.searchForSSID(request)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForSSID(request: SearchForSingleSSIDRequest, callbacks: SearchForSSIDCallbacks?) {
        scope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val result = adapter.searchForSSID(request)
            withContext(coroutineDispatcherProvider.main) {
                when (result) {
                    is SearchForSSIDResult.Empty -> callbacks?.onSSIDNotFound()
                    is SearchForSSIDResult.SSID -> callbacks?.onSSIDFound(result.data)
                }
            }
        }
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForSSIDs(request: SearchForMultipleSSIDsRequest): SearchForSSIDsResult {
        return adapter.searchForSSIDs(request)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForSSIDs(request: SearchForMultipleSSIDsRequest, callbacks: SearchForSSIDsCallbacks?) {
        scope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val result = adapter.searchForSSIDs(request)
            withContext(coroutineDispatcherProvider.main) {
                when (result) {
                    is SearchForSSIDsResult.Empty -> callbacks?.onNoSSIDsFound()
                    is SearchForSSIDsResult.SSIDs -> callbacks?.onSSIDsFound(result.data)
                }
            }
        }
    }
}
