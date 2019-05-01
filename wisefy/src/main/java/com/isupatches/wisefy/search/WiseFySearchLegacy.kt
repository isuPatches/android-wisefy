/*
 * Copyright 2018 Patches Klinefelter
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
package com.isupatches.wisefy.search

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.net.wifi.ScanResult
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission

import com.isupatches.wisefy.annotations.WaitsForTimeout
import com.isupatches.wisefy.utils.rest

/**
 * A class used internally for the purposes of shared query logic. This handles saved networks and
 * nearby access points. There is also filtering by regex functionality and some RSSI logic that
 * are tied into these queries.
 *
 * @see [WifiManager]
 * @see [WiseFySearch]
 *
 * @author Patches
 * @since 3.0
 */
@Suppress("deprecation")
internal class WiseFySearchLegacy private constructor(
    private val wifiManager: WifiManager
) : AbstractWiseFySearch(wifiManager) {

    internal companion object {
        fun create(wifiManager: WifiManager): WiseFySearch = WiseFySearchLegacy(wifiManager)
    }

    /**
     * Used internally to wait for a given time and return the first ScanResult whose SSID matches a given regex.
     *
     * Returns either:
     * - The first network whose SSID matches a given regex
     * - A network matching the given regex and has the highest RSSI
     *
     * @param regexForSSID The regex to check the SSID of the network against
     * @param timeoutInMillis The amount of time to wait for a match
     * @param takeHighest If the method should iterate through and return only the access point with the highest RSSI
     *
     * @return ScanResult|null - Matching network or null if none found
     *
     * @see [accessPointMatchesRegex]
     * @see [hasHighestSignalStrength]
     * @see [rest]
     * @see [ScanResult]
     * @see [WifiManager.startScan]
     * @see [WifiManager.getScanResults]
     *
     * @author Patches
     * @since 3.0
     */
    @WaitsForTimeout
    @RequiresPermission(allOf = [ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE])
    override fun findAccessPointByRegex(
        regexForSSID: String,
        timeoutInMillis: Int,
        takeHighest: Boolean
    ): ScanResult? {
        wifiManager.startScan()
        return findAccessPointByRegex(
            accessPoints = wifiManager.scanResults,
            regexForSSID = regexForSSID,
            timeoutInMillis = timeoutInMillis,
            takeHighest = takeHighest
        )
    }

    /**
     * Used internally to return a list of networks whose SSID match the given regex.
     *
     * @param regexForSSID The regex to check the SSID of the network against
     * @param takeHighest If the method should iterate through and return only the access point with the highest RSSI
     *
     * @return List of ScanResults|null - The list of networks that have an SSID that matches the given regex
     *
     * @see [accessPointMatchesRegex]
     * @see [hasHighestSignalStrength]
     * @see [ScanResult]
     * @see [WifiManager.startScan]
     * @see [WifiManager.getScanResults]
     *
     * @author Patches
     * @since 3.0
     */
    @RequiresPermission(allOf = [ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE])
    override fun findAccessPointsMatchingRegex(regexForSSID: String, takeHighest: Boolean): List<ScanResult>? {
        wifiManager.startScan()
        return findAccessPointsMatchingRegex(
            accessPoints = wifiManager.scanResults,
            regexForSSID = regexForSSID,
            takeHighest = takeHighest
        )
    }

    /**
     * Used internally to return a list of SSIDs from saved networks matching a given regex.
     *
     * @param regexForSSID The regex for the SSIDs to find in the configured network list
     *
     * @return List of Strings|null - SSIDs of saved network configurations matching the
     * given regex or null if none found
     *
     * @see [accessPointMatchesRegex]
     * @see [WifiManager.getScanResults]
     * @see [WifiManager.startScan]
     *
     * @author Patches
     * @since 3.0
     */
    @RequiresPermission(allOf = [ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE])
    override fun findSSIDsMatchingRegex(regexForSSID: String): List<String>? {
        wifiManager.startScan()
        return findSSIDsMatchingRegex(
            accessPoints = wifiManager.scanResults,
            regexForSSID = regexForSSID
        )
    }

    @RequiresPermission(allOf = [ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE])
    override fun getNearbyAccessPoints(filterDuplicates: Boolean): List<ScanResult> {
        wifiManager.startScan()
        return getNearbyAccessPoints(
            accessPoints = wifiManager.scanResults,
            filterDuplicates = filterDuplicates
        )
    }
}
