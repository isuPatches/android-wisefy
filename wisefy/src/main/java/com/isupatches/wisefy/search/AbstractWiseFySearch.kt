/*
 * Copyright 2019 Patches Klinefelter
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

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.net.wifi.ScanResult
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.wisefy.constants.QUOTE
import com.isupatches.wisefy.logging.WiseFyLogger
import com.isupatches.wisefy.utils.rest
import java.util.Locale

/**
 * A class used internally to house shared search logic between all SDK versions of Android.
 *
 * @see [WifiManager]
 * @see [WiseFySearch]
 *
 * @author Patches
 * @since 4.0
 */
@Suppress("LargeClass")
internal abstract class AbstractWiseFySearch(
    private val wifiManager: WifiManager
) : WiseFySearch {

    companion object {
        private val TAG = AbstractWiseFySearch::class.java.simpleName
    }

    /**
     * An abstracted provider for various SDK level support the provides a way to retrieve a list
     * of nearby access points
     */
    abstract val scanResultsProvider: () -> List<ScanResult>?

    /**
     * Used internally to return the first configuration of s saved networks matching a given regex.
     *
     * @param regexForSSID The regex for the SSID to find in the configured network list
     *
     * @return WiFiConfiguration|null - The first saved configuration matching the given regex or null if none found
     *
     * @see [savedNetworkMatchesRegex]
     * @see [WifiConfiguration]
     * @see [WifiManager.getConfiguredNetworks]
     *
     * Updates
     * - 05/12/2019: Moved here from previous WiseFySearchImpl class
     * - 01/04/2020: Refined permissions
     *
     * @author Patches
     * @since 3.0
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun findSavedNetworkByRegex(regexForSSID: String): WifiConfiguration? {
        val savedNetworks = wifiManager.configuredNetworks
        if (savedNetworks == null || savedNetworks.isEmpty()) {
            return null
        }

        for (savedNetwork in savedNetworks) {
            if (savedNetworkMatchesRegex(savedNetwork, regexForSSID)) {
                return savedNetwork
            }
        }
        return null
    }

    /**
     * Used internally to return a list of saved networks matching a given regex.
     *
     * @param regexForSSID The regex for the SSIDs to find in the configured network list
     *
     * @return List of WifiConfigurations|null - Saved network configurations matching the given regex or null if '
     * none found
     *
     * @see [savedNetworkMatchesRegex]
     * @see [WifiConfiguration]
     * @see [WifiManager.getConfiguredNetworks]
     *
     * Updates
     * - 05/12/2019: Moved here from previous WiseFySearchImpl class
     * - 01/04/2020: Refined permissions
     *
     * @author Patches
     * @since 3.0
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun findSavedNetworksMatchingRegex(regexForSSID: String): List<WifiConfiguration>? {
        val savedNetworks = wifiManager.configuredNetworks
        val matchingSavedNetworks = ArrayList<WifiConfiguration>()

        if (savedNetworks == null || savedNetworks.isEmpty()) {
            return null
        }

        for (savedNetwork in savedNetworks) {
            if (savedNetworkMatchesRegex(savedNetwork, regexForSSID)) {
                matchingSavedNetworks.add(savedNetwork)
            }
        }

        return if (matchingSavedNetworks.isNotEmpty()) matchingSavedNetworks else null
    }

    /**
     * Used internally to determine if a network exists as a saved network configuration.
     *
     * @param ssid The ssid to check for in the configured network list
     *
     * @return boolean - True if the ssid was found in the configuration list
     *
     * @see [findSavedNetworkByRegex]
     *
     * Updates
     * - 05/12/2019: Moved here from previous WiseFySearchImpl class
     * - 01/04/2020: Refined permissions
     *
     * @author Patches
     * @since 3.0
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun isNetworkASavedConfiguration(ssid: String?): Boolean =
        !ssid.isNullOrEmpty() && findSavedNetworkByRegex(ssid) != null

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
     * @see [scanResultsProvider]
     *
     * Updates
     * - 05/12/2019
     *      * Moved here from previous WiseFySearchImpl class
     *      * Fixed bad behavior with empty access point list
     *      * Started using scanResultsProvider for various SDK level support
     * - 01/04/2020: Refined permissions
     *
     * @author Patches
     * @since 3.0
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun findAccessPointByRegex(
        regexForSSID: String,
        timeoutInMillis: Int,
        takeHighest: Boolean
    ): ScanResult? {
        var scanPass = 1
        var currentTime: Long
        val endTime = System.currentTimeMillis() + timeoutInMillis
        var accessPointToReturn: ScanResult? = null
        do {
            currentTime = System.currentTimeMillis()

            val accessPointsTemp = scanResultsProvider()

            WiseFyLogger.debug(TAG, "Scanning SSIDs, pass %d", scanPass)
            if (accessPointsTemp != null && accessPointsTemp.isNotEmpty()) {
                var found = false
                for (accessPoint in accessPointsTemp) {
                    if (takeHighest) {
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
                WiseFyLogger.warn(TAG, "Empty access point list")
            }
            WiseFyLogger.debug(TAG, "Current time: %d, end time: %d (findAccessPointByRegex)", currentTime, endTime)
            scanPass++
            rest()
        } while (currentTime < endTime)
        return accessPointToReturn
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
     * @see [scanResultsProvider]
     *
     * Updates
     * - 05/12/2019
     *      * Moved here from previous WiseFySearchImpl class
     *      * Started using scanResultsProvider for various SDK level support
     * - 01/04/2020: Refined permissions
     *
     * @author Patches
     * @since 3.0
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun findAccessPointsMatchingRegex(
        regexForSSID: String,
        takeHighest: Boolean
    ): List<ScanResult>? {
        val matchingAccessPoints = ArrayList<ScanResult>()

        val accessPointsTemp = scanResultsProvider()

        if (accessPointsTemp == null || accessPointsTemp.isEmpty()) {
            return null
        }

        for (accessPoint in accessPointsTemp) {
            if (accessPointMatchesRegex(accessPoint, regexForSSID)) {
                if (takeHighest) {
                    if (hasHighestSignalStrength(accessPointsTemp, accessPoint)) {
                        matchingAccessPoints.add(accessPoint)
                    }
                } else {
                    matchingAccessPoints.add(accessPoint)
                }
            }
        }

        return if (matchingAccessPoints.isNotEmpty()) matchingAccessPoints else null
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
     * @see [scanResultsProvider]
     *
     * Updates
     * - 05/12/2019
     *      * Moved here from previous WiseFySearchImpl class
     *      * Started using scanResultsProvider for various SDK level support
     * - 01/04/2020: Refined permissions
     *
     * @author Patches
     * @since 3.0
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun findSSIDsMatchingRegex(
        regexForSSID: String
    ): List<String>? {
        val matchingSSIDs = ArrayList<String>()
        val accessPointsTemp = scanResultsProvider()
        if (accessPointsTemp != null && accessPointsTemp.isNotEmpty()) {
            for (accessPoint in accessPointsTemp) {
                if (accessPointMatchesRegex(accessPoint, regexForSSID) && !matchingSSIDs.contains(accessPoint.SSID)) {
                    matchingSSIDs.add(accessPoint.SSID)
                }
            }
        }

        return if (matchingSSIDs.isNotEmpty()) matchingSSIDs else null
    }

    /**
     * Used internally to return a list of nearby access points.
     *
     * @param filterDuplicates If you want to exclude SSIDs with that same name that have a weaker signal strength
     *
     * @return List of ScanResult|null - A list of nearby access points or null if there are none
     *
     * @see [scanResultsProvider]
     * @see [ScanResult]
     * @see [removeEntriesWithLowerSignalStrength]
     *
     * Updates
     * - 01/04/2020: Refined permissions
     *
     * @author Patches
     * @since 4.0
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getNearbyAccessPoints(
        filterDuplicates: Boolean
    ): List<ScanResult>? {
        val accessPointsTemp = scanResultsProvider()

        if (accessPointsTemp == null || accessPointsTemp.isEmpty()) {
            return null
        }

        return if (filterDuplicates) {
            removeEntriesWithLowerSignalStrength(accessPointsTemp)
        } else {
            accessPointsTemp
        }
    }

    /*
     * Helpers
     */

    /**
     * Used internally to check if a an access point has an SSID that matches a given regex.
     *
     * @param accessPoint The access point for comparison
     * @param regexForSSID The regex for comparison
     *
     * @return boolean - True if the access point's SSID matches the given regex
     *
     * @see [ScanResult]
     *
     * Updates
     * - 05/12/2019: Moved here from previous WiseFySearchImpl class
     *
     * @author Patches
     * @since 3.0
     */
    private fun accessPointMatchesRegex(accessPoint: ScanResult?, regexForSSID: String): Boolean {
        WiseFyLogger.debug(
            TAG,
            "accessPoint. SSID: %s, regex for SSID: %s".format(Locale.US, accessPoint?.SSID, regexForSSID)
        )
        return accessPoint?.SSID?.matches(regexForSSID.toRegex()) ?: false
    }

    /**
     * Used internally to determine if the current access point has the highest signal strength
     * compared to others that have the same SSID.
     *
     * *NOTE* Case insensitive
     *
     * @param accessPoints A list of access points to compare the current access point to
     * @param currentAccessPoint The access point to see if it has the highest signal strength
     *
     * @return boolean - True if the current access point has the highest signal strength
     *
     * @see [ScanResult]
     * @see [WifiManager.compareSignalLevel]
     *
     * Updates
     * - 05/12/2019: Moved here from previous WiseFySearchImpl class
     *
     * @author Patches
     * @since 3.0
     */
    private fun hasHighestSignalStrength(accessPoints: List<ScanResult>, currentAccessPoint: ScanResult): Boolean {
        for (accessPoint in accessPoints) {
            if (accessPoint.SSID.equals(currentAccessPoint.SSID, ignoreCase = true)) {
                WiseFyLogger.debug(TAG, "RSSI level of current access point: %d", currentAccessPoint.level)
                WiseFyLogger.debug(TAG, "RSSI level of access point in list: %d", accessPoint.level)
                WiseFyLogger.debug(
                    TAG,
                    "comparison result: %d (hasHighestSignalStrength)",
                    WifiManager.compareSignalLevel(accessPoint.level, currentAccessPoint.level)
                )
                if (WifiManager.compareSignalLevel(accessPoint.level, currentAccessPoint.level) > 0) {
                    WiseFyLogger.debug(TAG, "Stronger signal strength found")
                    return false
                }
            }
        }
        return true
    }

    /**
     * Used internally to build a list of ScanResults (removes duplicates by taking access point with higher RSSI).
     *
     * *NOTE* Case insensitive
     *
     * @param accessPoints The list of access points to remove entries with lower signal strength from
     *
     * @return List of ScanResults - The filtered list of networks
     *
     * @see [ScanResult]
     * @see [WifiManager.compareSignalLevel]
     *
     * Updates
     * - 05/12/2019
     *      * Moved here from previous WiseFySearchImpl class
     *      * Made private
     *
     * @author Patches
     * @since 3.0
     */
    private fun removeEntriesWithLowerSignalStrength(accessPoints: List<ScanResult>): List<ScanResult> {
        val accessPointsToReturn = ArrayList<ScanResult>()

        for (accessPoint in accessPoints) {
            var found = false
            for (i in accessPointsToReturn.indices) {
                val scanResult = accessPointsToReturn[i]
                WiseFyLogger.debug(TAG, "SSID 1: %s, SSID 2: %s", accessPoint.SSID, scanResult.SSID)
                if (accessPoint.SSID.equals(scanResult.SSID, ignoreCase = true)) {
                    found = true
                    WiseFyLogger.debug(TAG, "RSSI level of access point 1: %d", scanResult.level)
                    WiseFyLogger.debug(TAG, "RSSI level of access point 2: %d", accessPoint.level)
                    WiseFyLogger.debug(
                        TAG,
                        "comparison result: %d (removeEntriesWithLowerSignalStrength)",
                        WifiManager.compareSignalLevel(accessPoint.level, scanResult.level)
                    )
                    if (WifiManager.compareSignalLevel(accessPoint.level, scanResult.level) > 0) {
                        WiseFyLogger.debug(TAG, "New result has a higher or same signal strength, swapping")
                        accessPointsToReturn[i] = accessPoint
                    }
                }
            }

            if (!found) {
                WiseFyLogger.debug(TAG, "Found new wifi network")
                accessPointsToReturn.add(accessPoint)
            }
        }
        return accessPointsToReturn
    }

    /**
     * Used internally to check if a saved network has an SSID that matches a given regex.
     *
     * @param savedNetwork The saved network for comparison
     * @param regexForSSID The regex for comparison
     *
     * @return boolean - True if the saved network's SSID matches the given regex
     *
     * @see [WifiConfiguration]
     *
     * Updates
     * - 05/12/2019
     *      * Moved here from previous WiseFySearchImpl class
     *      * Started to use ?.let for nullness check
     *
     * @author Patches
     * @since 3.0
     */
    private fun savedNetworkMatchesRegex(savedNetwork: WifiConfiguration?, regexForSSID: String): Boolean {
        return savedNetwork?.let {
            return if (it.SSID != null) {
                val ssidInList = it.SSID.replace(QUOTE, "")
                ssidInList.matches(regexForSSID.toRegex())
            } else {
                false
            }
        } ?: false
    }
}
