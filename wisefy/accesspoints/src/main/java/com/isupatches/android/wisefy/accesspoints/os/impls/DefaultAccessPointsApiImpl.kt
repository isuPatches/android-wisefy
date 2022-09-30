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
package com.isupatches.android.wisefy.accesspoints.os.impls

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.net.wifi.ScanResult
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.accesspoints.entities.AccessPointData
import com.isupatches.android.wisefy.accesspoints.entities.RSSIData
import com.isupatches.android.wisefy.accesspoints.os.apis.DefaultAccessPointsApi
import com.isupatches.android.wisefy.core.logging.WisefyLogger
import com.isupatches.android.wisefy.core.util.rest
import java.util.Locale

/**
 * A default internal implementation for getting and searching for nearby access points through the Android OS.
 *
 * @param wifiManager The WifiManager instance to use
 * @param logger The [WisefyLogger] instance to use
 *
 * @see DefaultAccessPointsApi
 * @see WisefyLogger
 *
 * @author Patches Klinefelter
 * @since 08/2022, version 5.0.0
 */
internal class DefaultAccessPointsApiImpl(
    private val wifiManager: WifiManager,
    private val logger: WisefyLogger
) : DefaultAccessPointsApi {

    companion object {
        private const val LOG_TAG = "DefaultAccessPointsApiImpl"
    }

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
            removeEntriesWithLowerSignalStrength(accessPoints = accessPointsTemp)
        } else {
            accessPointsTemp.map { scanResult -> AccessPointData(value = scanResult) }
        }
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getRSSIBySSID(regexForSSID: String, takeHighest: Boolean, timeoutInMillis: Int): RSSIData? {
        val scanData = searchForAccessPointsBySSID(
            regexForSSID = regexForSSID,
            timeoutInMillis = timeoutInMillis,
            filterDuplicates = takeHighest
        ).firstOrNull()
        return scanData?.value?.level?.let {
            RSSIData(value = it)
        }
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getRSSIByBSSID(regexForBSSID: String, takeHighest: Boolean, timeoutInMillis: Int): RSSIData? {
        val scanData = searchForAccessPointsByBSSID(
            regexForBSSID = regexForBSSID,
            timeoutInMillis = timeoutInMillis,
            filterDuplicates = takeHighest
        ).firstOrNull()
        return scanData?.value?.level?.let {
            RSSIData(value = it)
        }
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForAccessPointsBySSID(
        regexForSSID: String,
        timeoutInMillis: Int?,
        filterDuplicates: Boolean
    ): List<AccessPointData> {
        return searchForMultipleAccessPoints(
            filterDuplicates = filterDuplicates,
            timeoutInMillis = timeoutInMillis
        ) { scanResult ->
            accessPointSSIDMatchesRegex(accessPoint = scanResult, regexForSSID = regexForSSID)
        }
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForAccessPointsByBSSID(
        regexForBSSID: String,
        timeoutInMillis: Int?,
        filterDuplicates: Boolean
    ): List<AccessPointData> {
        return searchForMultipleAccessPoints(
            filterDuplicates = filterDuplicates,
            timeoutInMillis = timeoutInMillis
        ) { scanResult ->
            accessPointBSSIDMatchesRegex(accessPoint = scanResult, regexForBSSID = regexForBSSID)
        }
    }

    private fun accessPointSSIDMatchesRegex(accessPoint: ScanResult?, regexForSSID: String): Boolean {
        logger.d(
            LOG_TAG,
            "accessPoint. SSID: %s, regexForSSID: %s".format(Locale.US, accessPoint?.SSID, regexForSSID)
        )
        return accessPoint?.SSID?.matches(regexForSSID.toRegex()) ?: false
    }

    private fun accessPointBSSIDMatchesRegex(accessPoint: ScanResult?, regexForBSSID: String): Boolean {
        logger.d(
            LOG_TAG,
            "accessPoint. SSID: %s, regexForBSSID: %s".format(Locale.US, accessPoint?.BSSID, regexForBSSID)
        )
        return accessPoint?.BSSID?.matches(regexForBSSID.toRegex()) ?: false
    }

    private fun hasHighestSignalStrength(
        accessPoints: List<ScanResult>,
        currentAccessPoint: ScanResult
    ): Boolean {
        for (accessPoint in accessPoints) {
            if (accessPoint.SSID.equals(currentAccessPoint.SSID, ignoreCase = true)) {
                val comparisonResult = WifiManager.compareSignalLevel(accessPoint.level, currentAccessPoint.level)
                logger.d(
                    LOG_TAG,
                    "Current RSSI: %d\nAccess point RSSI: %d\nComparison result: %d",
                    currentAccessPoint.level, accessPoint.level, comparisonResult
                )
                if (comparisonResult > 0) {
                    logger.d(LOG_TAG, "Stronger signal strength found")
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

                logger.d(LOG_TAG, "SSID 1: %s, SSID 2: %s", accessPoint.SSID, accessPointData.value.SSID)
                if (accessPoint.SSID.equals(accessPointData.value.SSID, ignoreCase = true)) {
                    found = true
                    val comparisonResult = WifiManager.compareSignalLevel(
                        accessPoint.level,
                        accessPointData.value.level
                    )
                    logger.d(
                        LOG_TAG,
                        "Access point 1 RSSI: %d\nAccess point 2 RSSI: %d\nComparison result: %d",
                        accessPointData.value.level, accessPoint.level, comparisonResult
                    )
                    if (comparisonResult > 0) {
                        logger.d(LOG_TAG, "New result has a higher or same signal strength, swapping")
                        accessPointsToReturn[i] = AccessPointData(accessPoint)
                    }
                }
            }

            if (!found) {
                logger.d(LOG_TAG, "Found new wifi network")
                accessPointsToReturn.add(AccessPointData(accessPoint))
            }
        }
        return accessPointsToReturn
    }

    private fun scanForAccessPoints(
        filterDuplicates: Boolean,
        matcher: (ScanResult) -> Boolean
    ): List<AccessPointData> {
        val matchingAccessPoints = ArrayList<AccessPointData>()
        val accessPointsTemp = scanResultsProvider()

        if (accessPointsTemp == null || accessPointsTemp.isEmpty()) {
            return emptyList()
        }

        for (accessPoint in accessPointsTemp) {
            if (matcher(accessPoint)) {
                if (filterDuplicates) {
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

    private fun searchForMultipleAccessPoints(
        filterDuplicates: Boolean,
        timeoutInMillis: Int?,
        matcher: (ScanResult) -> Boolean
    ): List<AccessPointData> {
        var accessPointsTemp: List<AccessPointData>
        if (timeoutInMillis != null) {
            val currentTime = System.currentTimeMillis()
            val endTime = currentTime + timeoutInMillis
            do {
                accessPointsTemp = scanForAccessPoints(filterDuplicates, matcher)
                if (accessPointsTemp.isNotEmpty()) {
                    break
                }
                rest()
            } while (currentTime < endTime)
        } else {
            accessPointsTemp = scanForAccessPoints(filterDuplicates, matcher)
        }
        return accessPointsTemp
    }
}
