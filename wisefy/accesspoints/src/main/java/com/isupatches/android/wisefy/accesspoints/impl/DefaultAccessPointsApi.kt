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
package com.isupatches.android.wisefy.accesspoints.impl

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.net.wifi.ScanResult
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.accesspoints.converters.toAccessPointMatchData
import com.isupatches.android.wisefy.accesspoints.converters.toSearchForSingleAccessPointRequest
import com.isupatches.android.wisefy.accesspoints.entities.AccessPointData
import com.isupatches.android.wisefy.accesspoints.entities.AccessPointMatchData
import com.isupatches.android.wisefy.accesspoints.entities.GetNearbyAccessPointsRequest
import com.isupatches.android.wisefy.accesspoints.entities.GetRSSIRequest
import com.isupatches.android.wisefy.accesspoints.entities.RSSIData
import com.isupatches.android.wisefy.accesspoints.entities.SSIDData
import com.isupatches.android.wisefy.accesspoints.entities.SearchForMultipleAccessPointsRequest
import com.isupatches.android.wisefy.accesspoints.entities.SearchForMultipleSSIDsRequest
import com.isupatches.android.wisefy.accesspoints.entities.SearchForSingleAccessPointRequest
import com.isupatches.android.wisefy.accesspoints.entities.SearchForSingleSSIDRequest
import com.isupatches.android.wisefy.shared.logging.WisefyLogger
import com.isupatches.android.wisefy.shared.util.rest
import java.util.Locale
import kotlin.collections.ArrayList

/**
 * A default internal API for getting and searching for nearby access points through the Android OS.
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
internal interface DefaultAccessPointsApi {

    /**
     * An internal API to get a list of nearby access points.
     *
     * @param request The details of the request to get nearby access points
     *
     * @see GetNearbyAccessPointsRequest
     * @see AccessPointData
     *
     * @return List<AccessPointData> - List of access points or empty list
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun getNearbyAccessPoints(request: GetNearbyAccessPointsRequest): List<AccessPointData>

    /**
     * An internal API to get a network's RSSI level.
     *
     * @param request The details of the request to get a networks RSSI level
     *
     * @see GetRSSIRequest
     * @see RSSIData
     *
     * @return RSSIData - RSSI data if network found, otherwise null
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun getRSSI(request: GetRSSIRequest): RSSIData?

    /**
     * An internal API to search for a nearby access point.
     *
     * @param request The details of the request to search for a nearby access point
     *
     * @see SearchForSingleAccessPointRequest
     * @see AccessPointData
     *
     * @return AccessPointData - Access point data if network found, otherwise null
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForAccessPoint(request: SearchForSingleAccessPointRequest): AccessPointData?

    /**
     * An internal API to search for a list of nearby access points.
     *
     * @param request The details of the request to search for a list of nearby access points
     *
     * @see SearchForMultipleAccessPointsRequest
     * @see AccessPointData
     *
     * @return List<AccessPointData> - List of access points or empty list
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForAccessPoints(request: SearchForMultipleAccessPointsRequest): List<AccessPointData>

    /**
     * An internal API to search for a nearby SSID.
     *
     * @param request The details of the request to search for a list of a nearby SSID
     *
     * @see SearchForSingleSSIDRequest
     * @see SSIDData
     *
     * @return SSIDData - SSID data if network found, otherwise null
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForSSID(request: SearchForSingleSSIDRequest): SSIDData?

    /**
     * An internal API to search for a list of nearby SSIDs.
     *
     * @param request The details of the request to search for a list of nearby SSIDs
     *
     * @see SearchForMultipleSSIDsRequest
     * @see SSIDData
     *
     * @return List<SSIDData> - List of SSID data or empty list
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForSSIDs(request: SearchForMultipleSSIDsRequest): List<SSIDData>
}

private const val LOG_TAG = "DefaultAccessPointsApiImpl"

/**
 * A default internal implementation for getting and searching for nearby access points through the Android OS.
 *
 * @see DefaultAccessPointsApi
 * @see WisefyLogger
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
internal class DefaultAccessPointsApiImpl(
    private val wifiManager: WifiManager,
    private val logger: WisefyLogger?
) : DefaultAccessPointsApi {

    // For SDK 23 and above, devices will be limited on ability to trigger scans and it's been
    // indicated by Android Google docs that eventually apps will no longer be able to trigger a
    // scan to prevent abusive apps, therefore for WiseFy we're going to just use the last
    // set of scan results...the downside is this may take some time to be updated.
    private val scanResultsProvider by lazy { { wifiManager.scanResults } }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getNearbyAccessPoints(request: GetNearbyAccessPointsRequest): List<AccessPointData> {
        val accessPointsTemp = scanResultsProvider()
        if (accessPointsTemp == null || accessPointsTemp.isEmpty()) {
            return emptyList()
        }

        return if (request.filterDuplicates) {
            removeEntriesWithLowerSignalStrength(accessPointsTemp)
        } else {
            accessPointsTemp.map { scanResult -> AccessPointData(value = scanResult) }
        }
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getRSSI(request: GetRSSIRequest): RSSIData? {
        val scanData = searchForAccessPoint(request.toSearchForSingleAccessPointRequest())
        return scanData?.value?.level?.let {
            RSSIData(it)
        }
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForAccessPoint(request: SearchForSingleAccessPointRequest): AccessPointData? {
        var scanPass = 1
        var currentTime: Long
        val endTime = System.currentTimeMillis() + request.timeoutInMillis
        var accessPointToReturn: ScanResult? = null
        do {
            currentTime = System.currentTimeMillis()

            val accessPointsTemp = scanResultsProvider()

            logger?.d(LOG_TAG, "Scanning SSIDs, pass %d", scanPass)
            if (accessPointsTemp != null && accessPointsTemp.isNotEmpty()) {
                var found = false
                for (accessPoint in accessPointsTemp) {
                    if (request.filterDuplicates) {
                        if (accessPointMatchesRegex(accessPoint, request.toAccessPointMatchData()) &&
                            hasHighestSignalStrength(accessPointsTemp, accessPoint)
                        ) {
                            accessPointToReturn = accessPoint
                            // Need to continue through rest of the list since
                            // we don't know which one will have the highest
                            break
                        }
                    } else {
                        if (accessPointMatchesRegex(accessPoint, request.toAccessPointMatchData())) {
                            accessPointToReturn = accessPoint
                            found = true
                            break
                        }
                    }
                }

                if (found) {
                    break
                }
            } else {
                logger?.w(LOG_TAG, "Empty access point list")
            }
            logger?.d(LOG_TAG, "Current time: %d, end time: %d (findAccessPointByRegex)", currentTime, endTime)
            scanPass++
            rest()
        } while (currentTime < endTime)
        return accessPointToReturn?.let { AccessPointData(value = it) }
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForAccessPoints(request: SearchForMultipleAccessPointsRequest): List<AccessPointData> {
        val matchingAccessPoints = ArrayList<AccessPointData>()

        val accessPointsTemp = scanResultsProvider()

        if (accessPointsTemp == null || accessPointsTemp.isEmpty()) {
            return emptyList()
        }

        for (accessPoint in accessPointsTemp) {
            if (accessPointMatchesRegex(accessPoint, request.toAccessPointMatchData())) {
                if (request.filterDuplicates) {
                    if (hasHighestSignalStrength(accessPointsTemp, accessPoint)) {
                        matchingAccessPoints.add(AccessPointData(value = accessPoint))
                    }
                } else {
                    matchingAccessPoints.add(AccessPointData(value = accessPoint))
                }
            }
        }

        return matchingAccessPoints.ifEmpty { emptyList() }
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForSSID(request: SearchForSingleSSIDRequest): SSIDData? {
        val scanData = searchForAccessPoint(request.toSearchForSingleAccessPointRequest())
        return when (request) {
            is SearchForSingleSSIDRequest.SSID -> scanData?.value?.SSID?.let { ssid ->
                SSIDData.SSID(ssid)
            }
            is SearchForSingleSSIDRequest.BSSID -> scanData?.value?.BSSID?.let { bssid ->
                SSIDData.SSID(bssid)
            }
        }
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForSSIDs(request: SearchForMultipleSSIDsRequest): List<SSIDData> {
        val matchingSSIDs = ArrayList<SSIDData>()
        val accessPointsTemp = scanResultsProvider() ?: emptyList<ScanResult>()
        for (accessPoint in accessPointsTemp) {
            val potentialMatch = when (request) {
                is SearchForMultipleSSIDsRequest.SSID -> SSIDData.SSID(accessPoint.SSID)
                is SearchForMultipleSSIDsRequest.BSSID -> SSIDData.BSSID(accessPoint.BSSID)
            }
            if (accessPointMatchesRegex(accessPoint, request.toAccessPointMatchData()) &&
                !matchingSSIDs.contains(potentialMatch)
            ) {
                matchingSSIDs.add(potentialMatch)
            }
        }
        return matchingSSIDs.ifEmpty { emptyList() }
    }

    private fun accessPointMatchesRegex(accessPoint: ScanResult?, data: AccessPointMatchData): Boolean {
        logger?.d(
            LOG_TAG,
            "accessPoint. SSID: %s, BSSID: %s, match data: %s".format(
                Locale.US,
                accessPoint?.SSID,
                accessPoint?.BSSID,
                data
            )
        )
        return when (data) {
            is AccessPointMatchData.SSID -> accessPoint?.SSID?.matches(data.regexForSSID.toRegex()) ?: false
            is AccessPointMatchData.BSSID -> accessPoint?.BSSID?.matches(data.regexForBSSID.toRegex()) ?: false
        }
    }

    private fun hasHighestSignalStrength(
        accessPoints: List<ScanResult>,
        currentAccessPoint: ScanResult
    ): Boolean {
        for (accessPoint in accessPoints) {
            if (accessPoint.SSID.equals(currentAccessPoint.SSID, ignoreCase = true)) {
                val comparisonResult = WifiManager.compareSignalLevel(accessPoint.level, currentAccessPoint.level)
                logger?.d(
                    LOG_TAG,
                    "Current RSSI: %d\nAccess point RSSI: %d\nComparison result: %d",
                    currentAccessPoint.level, accessPoint.level, comparisonResult
                )
                if (comparisonResult > 0) {
                    logger?.d(LOG_TAG, "Stronger signal strength found")
                    return false
                }
            }
        }
        return true
    }

    private fun removeEntriesWithLowerSignalStrength(accessPoints: List<ScanResult>): List<AccessPointData> {
        val accessPointsToReturn = ArrayList<AccessPointData>()

        for (accessPoint in accessPoints) {
            var found = false
            for (i in accessPointsToReturn.indices) {
                val accessPointData = accessPointsToReturn[i]

                logger?.d(LOG_TAG, "SSID 1: %s, SSID 2: %s", accessPoint.SSID, accessPointData.value.SSID)
                if (accessPoint.SSID.equals(accessPointData.value.SSID, ignoreCase = true)) {
                    found = true
                    val comparisonResult = WifiManager.compareSignalLevel(
                        accessPoint.level,
                        accessPointData.value.level
                    )
                    logger?.d(
                        LOG_TAG,
                        "Access point 1 RSSI: %d\nAccess point 2 RSSI: %d\nComparison result: %d",
                        accessPointData.value.level, accessPoint.level, comparisonResult
                    )
                    if (comparisonResult > 0) {
                        logger?.d(LOG_TAG, "New result has a higher or same signal strength, swapping")
                        accessPointsToReturn[i] = AccessPointData(accessPoint)
                    }
                }
            }

            if (!found) {
                logger?.d(LOG_TAG, "Found new wifi network")
                accessPointsToReturn.add(AccessPointData(accessPoint))
            }
        }
        return accessPointsToReturn
    }
}
