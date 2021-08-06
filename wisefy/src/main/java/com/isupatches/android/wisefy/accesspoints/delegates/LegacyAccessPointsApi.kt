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
package com.isupatches.android.wisefy.accesspoints.delegates

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.net.wifi.ScanResult
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.accesspoints.entities.AccessPointData
import com.isupatches.android.wisefy.logging.WisefyLogger
import com.isupatches.android.wisefy.util.rest
import java.util.Locale
import kotlin.collections.ArrayList

internal interface LegacyAccessPointsApi {

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun getNearbyAccessPoints(filterDuplicates: Boolean): List<AccessPointData>

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun getRSSI(regexForSSID: String, takeHighest: Boolean, timeoutInMillis: Int): Int?

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForAccessPoint(
        regexForSSID: String,
        timeoutInMillis: Int,
        filterDuplicates: Boolean
    ): AccessPointData

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForAccessPoints(
        regexForSSID: String,
        filterDuplicates: Boolean
    ): List<AccessPointData>

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForSSID(regexForSSID: String, timeoutInMillis: Int): String?

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForSSIDs(regexForSSID: String): List<String>
}

private const val LOG_TAG = "LegacyAccessPointsApiImpl"

internal class LegacyAccessPointsApiImpl(
    private val wifiManager: WifiManager,
    private val logger: WisefyLogger?
) : LegacyAccessPointsApi {

    // For SDK 23 and above, devices will be limited on ability to trigger scans and it's been
    // indicated by Android Google docs that eventually apps will no longer be able to trigger a
    // scan to prevent abusive apps, therefore for WiseFy we're going to just use the last
    // set of scan results...the downside is this may take some time to be updated.
    private val scanResultsProvider by lazy { { wifiManager.scanResults } }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getNearbyAccessPoints(filterDuplicates: Boolean): List<AccessPointData> {
        val accessPointsTemp = scanResultsProvider()

        if (accessPointsTemp == null || accessPointsTemp.isEmpty()) {
            return emptyList()
        }

        return if (filterDuplicates) {
            removeEntriesWithLowerSignalStrength(accessPointsTemp).map { scanResult ->
                AccessPointData.ScanData(data = scanResult)
            }
        } else {
            accessPointsTemp.map { scanResult -> AccessPointData.ScanData(data = scanResult) }
        }
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getRSSI(regexForSSID: String, takeHighest: Boolean, timeoutInMillis: Int): Int? {
        val scanData = searchForAccessPoint(regexForSSID, timeoutInMillis, takeHighest)
        return (scanData as? AccessPointData.ScanData)?.data?.level
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForAccessPoint(
        regexForSSID: String,
        timeoutInMillis: Int,
        filterDuplicates: Boolean
    ): AccessPointData {
        var scanPass = 1
        var currentTime: Long
        val endTime = System.currentTimeMillis() + timeoutInMillis
        var accessPointToReturn: ScanResult? = null
        do {
            currentTime = System.currentTimeMillis()

            val accessPointsTemp = scanResultsProvider()

            logger?.d(LOG_TAG, "Scanning SSIDs, pass %d", scanPass)
            if (accessPointsTemp != null && accessPointsTemp.isNotEmpty()) {
                var found = false
                for (accessPoint in accessPointsTemp) {
                    if (filterDuplicates) {
                        if (accessPointMatchesRegex(accessPoint, regexForSSID) &&
                            hasHighestSignalStrength(accessPointsTemp, accessPoint)
                        ) {
                            accessPointToReturn = accessPoint
                            // Need to continue through rest of the list since
                            // we don't know which one will have the highest
                            break
                        }
                    } else {
                        if (accessPointMatchesRegex(accessPoint, regexForSSID)) {
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
        return AccessPointData.ScanData(data = accessPointToReturn)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForAccessPoints(
        regexForSSID: String,
        filterDuplicates: Boolean
    ): List<AccessPointData> {
        val matchingAccessPoints = ArrayList<AccessPointData>()

        val accessPointsTemp = scanResultsProvider()

        if (accessPointsTemp == null || accessPointsTemp.isEmpty()) {
            return emptyList()
        }

        for (accessPoint in accessPointsTemp) {
            if (accessPointMatchesRegex(accessPoint, regexForSSID)) {
                if (filterDuplicates) {
                    if (hasHighestSignalStrength(accessPointsTemp, accessPoint)) {
                        matchingAccessPoints.add(AccessPointData.ScanData(data = accessPoint))
                    }
                } else {
                    matchingAccessPoints.add(AccessPointData.ScanData(data = accessPoint))
                }
            }
        }

        return if (matchingAccessPoints.isNotEmpty()) matchingAccessPoints else emptyList()
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForSSID(regexForSSID: String, timeoutInMillis: Int): String? {
        val scanData = searchForAccessPoint(regexForSSID, timeoutInMillis, false)
        return (scanData as? AccessPointData.ScanData)?.data?.SSID
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForSSIDs(regexForSSID: String): List<String> {
        val matchingSSIDs = ArrayList<String>()
        val accessPointsTemp = scanResultsProvider() ?: emptyList<ScanResult>()
        for (accessPoint in accessPointsTemp) {
            if (accessPointMatchesRegex(accessPoint, regexForSSID) &&
                !matchingSSIDs.contains(accessPoint.SSID)
            ) {
                matchingSSIDs.add(accessPoint.SSID)
            }
        }
        return if (matchingSSIDs.isNotEmpty()) matchingSSIDs else emptyList()
    }

    private fun accessPointMatchesRegex(accessPoint: ScanResult?, regexForSSID: String): Boolean {
        logger?.d(
            LOG_TAG,
            "accessPoint. SSID: %s, regex for SSID: %s".format(Locale.US, accessPoint?.SSID, regexForSSID)
        )
        return accessPoint?.SSID?.matches(regexForSSID.toRegex()) ?: false
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

    private fun removeEntriesWithLowerSignalStrength(accessPoints: List<ScanResult>): List<ScanResult> {
        val accessPointsToReturn = ArrayList<ScanResult>()

        for (accessPoint in accessPoints) {
            var found = false
            for (i in accessPointsToReturn.indices) {
                val scanResult = accessPointsToReturn[i]
                logger?.d(LOG_TAG, "SSID 1: %s, SSID 2: %s", accessPoint.SSID, scanResult.SSID)
                if (accessPoint.SSID.equals(scanResult.SSID, ignoreCase = true)) {
                    found = true
                    val comparisonResult = WifiManager.compareSignalLevel(accessPoint.level, scanResult.level)
                    logger?.d(
                        LOG_TAG,
                        "Access point 1 RSSI: %d\nAccess point 2 RSSI: %d\nComparison result: %d",
                        scanResult.level, accessPoint.level, comparisonResult
                    )
                    if (comparisonResult > 0) {
                        logger?.d(LOG_TAG, "New result has a higher or same signal strength, swapping")
                        accessPointsToReturn[i] = accessPoint
                    }
                }
            }

            if (!found) {
                logger?.d(LOG_TAG, "Found new wifi network")
                accessPointsToReturn.add(accessPoint)
            }
        }
        return accessPointsToReturn
    }
}
