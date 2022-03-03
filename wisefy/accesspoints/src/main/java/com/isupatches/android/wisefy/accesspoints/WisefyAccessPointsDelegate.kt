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
import com.isupatches.android.wisefy.accesspoints.proxies.DefaultAccessPointsProxy
import com.isupatches.android.wisefy.accesspoints.entities.AccessPointData
import com.isupatches.android.wisefy.accesspoints.entities.GetNearbyAccessPointsRequest
import com.isupatches.android.wisefy.accesspoints.entities.GetRSSIRequest
import com.isupatches.android.wisefy.accesspoints.entities.RSSIData
import com.isupatches.android.wisefy.accesspoints.entities.SSIDData
import com.isupatches.android.wisefy.accesspoints.entities.SearchForMultipleAccessPointsRequest
import com.isupatches.android.wisefy.accesspoints.entities.SearchForMultipleSSIDsRequest
import com.isupatches.android.wisefy.accesspoints.entities.SearchForSingleAccessPointRequest
import com.isupatches.android.wisefy.accesspoints.entities.SearchForSingleSSIDRequest
import com.isupatches.android.wisefy.shared.coroutines.CoroutineDispatcherProvider
import com.isupatches.android.wisefy.shared.coroutines.createBaseCoroutineExceptionHandler
import com.isupatches.android.wisefy.shared.logging.WisefyLogger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A delegate for synchronous and asynchronous access point APIs.
 *
 * @see AccessPointsApi
 * @see AccessPointsApiAsync
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
interface AccessPointsDelegate : AccessPointsApi, AccessPointsApiAsync

private const val LOG_TAG = "WisefyAccessPointsDelegate"

/**
 * A default delegate for getting and searching for nearby access points.
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
    logger: WisefyLogger?,
    wifiManager: WifiManager
) : AccessPointsDelegate {

    private val proxy = DefaultAccessPointsProxy(wifiManager, logger)

    init {
        logger?.d(LOG_TAG, "WisefyAccessPointsDelegate proxy is: ${proxy::class.java.simpleName}")
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getNearbyAccessPoints(request: GetNearbyAccessPointsRequest): List<AccessPointData> {
        return proxy.getNearbyAccessPoints(request)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getNearbyAccessPoints(
        request: GetNearbyAccessPointsRequest,
        callbacks: GetNearbyAccessPointCallbacks?
    ) {
        scope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val accessPoints = proxy.getNearbyAccessPoints(request)
            withContext(coroutineDispatcherProvider.main) {
                when {
                    accessPoints.isNotEmpty() -> callbacks?.onNearbyAccessPointsRetrieved(accessPoints)
                    else -> callbacks?.onNoNearbyAccessPoints()
                }
            }
        }
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getRSSI(request: GetRSSIRequest): RSSIData? {
        return proxy.getRSSI(request)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getRSSI(request: GetRSSIRequest, callbacks: GetRSSICallbacks?) {
        scope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val rssi = proxy.getRSSI(request)
            withContext(coroutineDispatcherProvider.main) {
                when {
                    rssi != null -> callbacks?.onRSSIRetrieved(rssi)
                    else -> callbacks?.onNoNetworkToRetrieveRSSI()
                }
            }
        }
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForAccessPoint(request: SearchForSingleAccessPointRequest): AccessPointData? {
        return proxy.searchForAccessPoint(request)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForAccessPoint(
        request: SearchForSingleAccessPointRequest,
        callbacks: SearchForAccessPointCallbacks?
    ) {
        scope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val accessPoint = proxy.searchForAccessPoint(request)
            withContext(coroutineDispatcherProvider.main) {
                when {
                    accessPoint != null -> callbacks?.onAccessPointFound(accessPoint)
                    else -> callbacks?.onNoAccessPointFound()
                }
            }
        }
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForAccessPoints(request: SearchForMultipleAccessPointsRequest): List<AccessPointData> {
        return proxy.searchForAccessPoints(request)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForAccessPoints(
        request: SearchForMultipleAccessPointsRequest,
        callbacks: SearchForAccessPointsCallbacks?
    ) {
        scope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val accessPoints = proxy.searchForAccessPoints(request)
            withContext(coroutineDispatcherProvider.main) {
                when {
                    accessPoints.isNotEmpty() -> callbacks?.onAccessPointsFound(accessPoints)
                    else -> callbacks?.onNoAccessPointsFound()
                }
            }
        }
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForSSID(request: SearchForSingleSSIDRequest): SSIDData? {
        return proxy.searchForSSID(request)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForSSID(request: SearchForSingleSSIDRequest, callbacks: SearchForSSIDCallbacks?) {
        scope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val ssid = proxy.searchForSSID(request)
            withContext(coroutineDispatcherProvider.main) {
                when {
                    ssid != null -> callbacks?.onSSIDFound(ssid)
                    else -> callbacks?.onSSIDNotFound()
                }
            }
        }
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForSSIDs(request: SearchForMultipleSSIDsRequest): List<SSIDData> {
        return proxy.searchForSSIDs(request)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForSSIDs(request: SearchForMultipleSSIDsRequest, callbacks: SearchForSSIDsCallbacks?) {
        scope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val ssids = proxy.searchForSSIDs(request)
            withContext(coroutineDispatcherProvider.main) {
                when {
                    ssids.isNotEmpty() -> callbacks?.onSSIDsFound(ssids)
                    else -> callbacks?.onNoSSIDsFound()
                }
            }
        }
    }
}
