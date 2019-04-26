package com.isupatches.wisefy.search

import android.Manifest.permission.ACCESS_WIFI_STATE
import android.net.wifi.ScanResult
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission

import com.isupatches.wisefy.constants.QUOTE
import com.isupatches.wisefy.logging.WiseFyLogger
import com.isupatches.wisefy.utils.rest

import java.util.Locale

internal abstract class AbstractWiseFySearch(
    private val wifiManager: WifiManager
) : WiseFySearch {

    companion object {
        private val TAG = AbstractWiseFySearch::class.java.simpleName
    }

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
     * @author Patches
     * @since 3.0
     */
    @RequiresPermission(ACCESS_WIFI_STATE)
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
     * @author Patches
     * @since 3.0
     */
    @RequiresPermission(ACCESS_WIFI_STATE)
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
     * @author Patches
     * @since 3.0
     */
    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun isNetworkASavedConfiguration(ssid: String?): Boolean =
            !ssid.isNullOrEmpty() && findSavedNetworkByRegex(ssid) != null

    @RequiresPermission(ACCESS_WIFI_STATE)
    protected fun findAccessPointByRegex(
        accessPoints: List<ScanResult>,
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

            WiseFyLogger.debug(TAG, "Scanning SSIDs, pass %d", scanPass)
            if (accessPoints.isNotEmpty()) {
                var found = false
                for (accessPoint in accessPoints) {
                    if (takeHighest) {
                        if (accessPointMatchesRegex(accessPoint, regexForSSID) &&
                                hasHighestSignalStrength(accessPoints, accessPoint)
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

    @RequiresPermission(ACCESS_WIFI_STATE)
    protected fun findAccessPointsMatchingRegex(
        accessPoints: List<ScanResult>,
        regexForSSID: String,
        takeHighest: Boolean
    ): List<ScanResult>? {
        val matchingAccessPoints = ArrayList<ScanResult>()

        if (accessPoints.isEmpty()) {
            return null
        }

        for (accessPoint in accessPoints) {
            if (accessPointMatchesRegex(accessPoint, regexForSSID)) {
                if (takeHighest) {
                    if (hasHighestSignalStrength(accessPoints, accessPoint)) {
                        matchingAccessPoints.add(accessPoint)
                    }
                } else {
                    matchingAccessPoints.add(accessPoint)
                }
            }
        }

        return if (matchingAccessPoints.isNotEmpty()) matchingAccessPoints else null
    }

    @RequiresPermission(ACCESS_WIFI_STATE)
    protected fun findSSIDsMatchingRegex(
        accessPoints: List<ScanResult>,
        regexForSSID: String
    ): List<String>? {
        val matchingSSIDs = ArrayList<String>()
        if (accessPoints.isNotEmpty()) {
            for (accessPoint in accessPoints) {
                if (accessPointMatchesRegex(accessPoint, regexForSSID) && !matchingSSIDs.contains(accessPoint.SSID)) {
                    matchingSSIDs.add(accessPoint.SSID)
                }
            }
        }

        return if (matchingSSIDs.isNotEmpty()) matchingSSIDs else null
    }

    @RequiresPermission(ACCESS_WIFI_STATE)
    protected fun getNearbyAccessPoints(
        accessPoints: List<ScanResult>,
        filterDuplicates: Boolean
    ): List<ScanResult> {
        return if (filterDuplicates) {
            removeEntriesWithLowerSignalStrength(accessPoints)
        } else {
            accessPoints
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
     * @author Patches
     * @since 3.0
     */
    private fun savedNetworkMatchesRegex(savedNetwork: WifiConfiguration?, regexForSSID: String): Boolean {
        if (savedNetwork?.SSID != null) {
            val ssidInList = savedNetwork.SSID.replace(QUOTE, "")
            return ssidInList.matches(regexForSSID.toRegex())
        }
        return false
    }
}
