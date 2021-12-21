/*
 * Copyright 2021 Patches Klinefelter
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
import com.isupatches.android.wisefy.accesspoints.delegates.LegacyAccessPointsDelegate
import com.isupatches.android.wisefy.accesspoints.entities.AccessPointData
import com.isupatches.android.wisefy.accesspoints.entities.GetNearbyAccessPointsRequest
import com.isupatches.android.wisefy.accesspoints.entities.GetRSSIRequest
import com.isupatches.android.wisefy.accesspoints.entities.RSSIData
import com.isupatches.android.wisefy.accesspoints.entities.SSIDData
import com.isupatches.android.wisefy.accesspoints.entities.SearchForMultipleAccessPointsRequest
import com.isupatches.android.wisefy.accesspoints.entities.SearchForMultipleSSIDsRequest
import com.isupatches.android.wisefy.accesspoints.entities.SearchForSingleAccessPointRequest
import com.isupatches.android.wisefy.accesspoints.entities.SearchForSingleSSIDRequest
import com.isupatches.android.wisefy.callbacks.GetNearbyAccessPointCallbacks
import com.isupatches.android.wisefy.callbacks.GetRSSICallbacks
import com.isupatches.android.wisefy.callbacks.SearchForAccessPointCallbacks
import com.isupatches.android.wisefy.callbacks.SearchForAccessPointsCallbacks
import com.isupatches.android.wisefy.callbacks.SearchForSSIDCallbacks
import com.isupatches.android.wisefy.callbacks.SearchForSSIDsCallbacks
import com.isupatches.android.wisefy.logging.WisefyLogger
import com.isupatches.android.wisefy.util.coroutines.CoroutineDispatcherProvider
import com.isupatches.android.wisefy.util.coroutines.createBaseCoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal interface AccessPointsUtil : AccessPointsApi, AccessPointsApiAsync

private const val LOG_TAG = "WisefyAccessPointsUtil"

internal class WisefyAccessPointsUtil(
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider,
    logger: WisefyLogger?,
    wifiManager: WifiManager
) : AccessPointsUtil {

    private val delegate = LegacyAccessPointsDelegate(wifiManager, logger)
    private val accessPointScope = CoroutineScope(Job() + coroutineDispatcherProvider.io)

    init {
        logger?.d(LOG_TAG, "WisefyAccessPointsUtil delegate is: ${delegate::class.java.simpleName}")
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getNearbyAccessPoints(request: GetNearbyAccessPointsRequest): List<AccessPointData> {
        return delegate.getNearbyAccessPoints(request)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getNearbyAccessPoints(
        request: GetNearbyAccessPointsRequest,
        callbacks: GetNearbyAccessPointCallbacks?
    ) {
        accessPointScope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val accessPoints = delegate.getNearbyAccessPoints(request)
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
        return delegate.getRSSI(request)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getRSSI(request: GetRSSIRequest, callbacks: GetRSSICallbacks?) {
        accessPointScope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val rssi = delegate.getRSSI(request)
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
        return delegate.searchForAccessPoint(request)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForAccessPoint(
        request: SearchForSingleAccessPointRequest,
        callbacks: SearchForAccessPointCallbacks?
    ) {
        accessPointScope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val accessPoint = delegate.searchForAccessPoint(request)
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
        return delegate.searchForAccessPoints(request)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForAccessPoints(
        request: SearchForMultipleAccessPointsRequest,
        callbacks: SearchForAccessPointsCallbacks?
    ) {
        accessPointScope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val accessPoints = delegate.searchForAccessPoints(request)
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
        return delegate.searchForSSID(request)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForSSID(request: SearchForSingleSSIDRequest, callbacks: SearchForSSIDCallbacks?) {
        accessPointScope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val ssid = delegate.searchForSSID(request)
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
        return delegate.searchForSSIDs(request)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForSSIDs(request: SearchForMultipleSSIDsRequest, callbacks: SearchForSSIDsCallbacks?) {
        accessPointScope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val ssids = delegate.searchForSSIDs(request)
            withContext(coroutineDispatcherProvider.main) {
                when {
                    ssids.isNotEmpty() -> callbacks?.onSSIDsFound(ssids)
                    else -> callbacks?.onNoSSIDsFound()
                }
            }
        }
    }
}
