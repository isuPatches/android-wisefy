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
package com.isupatches.android.wisefy.accesspoints.os.impls

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.net.wifi.ScanResult
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.accesspoints.entities.AccessPointData
import com.isupatches.android.wisefy.accesspoints.os.apis.DefaultAccessPointsApi
import com.isupatches.android.wisefy.core.bssidWithoutQuotes
import com.isupatches.android.wisefy.core.hasBSSIDMatchingRegex
import com.isupatches.android.wisefy.core.hasSSIDMatchingRegex
import com.isupatches.android.wisefy.core.logging.WisefyLogger
import com.isupatches.android.wisefy.core.ssidWithoutQuotes
import com.isupatches.android.wisefy.core.util.withTimeout

/**
 * A default internal implementation for querying for access points through the Android OS.
 *
 * @param wifiManager The WifiManager instance to use
 * @param logger The [WisefyLogger] instance to use
 *
 * @see DefaultAccessPointsApi
 * @see WisefyLogger
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
internal class DefaultAccessPointsApiImpl(
    private val wifiManager: WifiManager,
    private val logger: WisefyLogger
) : DefaultAccessPointsApi {

    companion object {
        private const val LOG_TAG = "DefaultAccessPointsApiImpl"
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun getNearbyAccessPoints(filterDuplicates: Boolean): List<AccessPointData> {
        val allAccessPoints = getLastScanResults()
        if (allAccessPoints.isEmpty()) {
            return emptyList()
        }

        return if (filterDuplicates) {
            removeEntriesWithLowerSignalStrength(accessPoints = allAccessPoints)
        } else {
            allAccessPoints.map { scanResult ->
                AccessPointData(
                    rawValue = scanResult,
                    ssid = scanResult.ssidWithoutQuotes,
                    bssid = scanResult.bssidWithoutQuotes
                )
            }
        }
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForAccessPointsBySSID(
        regex: String,
        timeoutInMillis: Int?,
        filterDuplicates: Boolean
    ): List<AccessPointData> {
        return searchForMultipleAccessPoints(
            filterDuplicates = filterDuplicates,
            timeoutInMillis = timeoutInMillis
        ) { accessPoint ->
            accessPoint.hasSSIDMatchingRegex(regex)
        }
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForAccessPointsByBSSID(
        regex: String,
        timeoutInMillis: Int?,
        filterDuplicates: Boolean
    ): List<AccessPointData> {
        return searchForMultipleAccessPoints(
            filterDuplicates = filterDuplicates,
            timeoutInMillis = timeoutInMillis
        ) { accessPoint ->
            accessPoint.hasBSSIDMatchingRegex(regex)
        }
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    private fun filterAccessPoints(
        filterDuplicates: Boolean,
        matcher: (ScanResult) -> Boolean
    ): List<AccessPointData> {
        val allAccessPoints = getLastScanResults()
        if (allAccessPoints.isEmpty()) {
            return emptyList()
        }
        return if (filterDuplicates) {
            removeEntriesWithLowerSignalStrength(allAccessPoints.filter(matcher))
        } else {
            allAccessPoints.filter(matcher).map {
                AccessPointData(rawValue = it, ssid = it.ssidWithoutQuotes, bssid = it.bssidWithoutQuotes)
            }
        }
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    private fun getLastScanResults(): List<ScanResult> {
        // For SDK 23 and above, devices will be limited on ability to trigger scans and it's been
        // indicated by Android Google docs that eventually apps will no longer be able to trigger a
        // scan to prevent abusive apps, therefore for WiseFy we're going to just use the last
        // set of scan results...the downside is this may take some time to be updated.
        return wifiManager.scanResults ?: emptyList()
    }

    private fun removeEntriesWithLowerSignalStrength(accessPoints: List<ScanResult>): List<AccessPointData> {
        val accessPointsToReturn = mutableMapOf<String, AccessPointData>()
        accessPoints.forEach { accessPoint ->
            if (accessPointsToReturn.containsKey(accessPoint.ssidWithoutQuotes)) {
                val previousAccessPoint = requireNotNull(accessPointsToReturn[accessPoint.ssidWithoutQuotes])
                val comparisonResult = WifiManager.compareSignalLevel(accessPoint.level, previousAccessPoint.rssi)
                logger.d(
                    LOG_TAG,
                    "Comparing.  SSID 1: %s, RSSI 1: %d, SSID 2: %s, RSSI 2: %d, comparison result: %d",
                    accessPoint.ssidWithoutQuotes,
                    accessPoint.level,
                    previousAccessPoint.ssid,
                    previousAccessPoint.rssi,
                    comparisonResult
                )
                if (comparisonResult > 0) {
                    logger.d(LOG_TAG, "Found network with same SSID but higher RSSI, swapping")
                    accessPointsToReturn[accessPoint.ssidWithoutQuotes] = AccessPointData(
                        rawValue = accessPoint,
                        ssid = accessPoint.ssidWithoutQuotes,
                        bssid = accessPoint.bssidWithoutQuotes
                    )
                }
            } else {
                val accessPointData = AccessPointData(
                    rawValue = accessPoint,
                    ssid = accessPoint.ssidWithoutQuotes,
                    bssid = accessPoint.bssidWithoutQuotes
                )
                logger.d(LOG_TAG, "Found new network. $accessPointData")
                accessPointsToReturn[accessPoint.ssidWithoutQuotes] = accessPointData
            }
        }
        return accessPointsToReturn.values.toList()
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    private fun searchForMultipleAccessPoints(
        filterDuplicates: Boolean,
        timeoutInMillis: Int?,
        matcher: (ScanResult) -> Boolean
    ): List<AccessPointData> {
        var filteredAccessPoints: List<AccessPointData> = emptyList()
        if (timeoutInMillis != null) {
            withTimeout(timeoutInMillis) {
                filteredAccessPoints = filterAccessPoints(filterDuplicates, matcher)
                filteredAccessPoints.isNotEmpty()
            }
        } else {
            filteredAccessPoints = filterAccessPoints(filterDuplicates, matcher)
        }
        return filteredAccessPoints
    }
}
