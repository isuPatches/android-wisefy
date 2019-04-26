package com.isupatches.wisefy.search

import android.net.wifi.ScanResult
import android.net.wifi.WifiConfiguration

/**
 * An interface that helps with searching.
 *
 * @see [WiseFySearchLegacy]
 * @see [WiseFySearchSDK23]
 *
 * @author Patches
 * @since 3.0
 */
internal interface WiseFySearch {

    fun findAccessPointByRegex(regexForSSID: String, timeoutInMillis: Int, takeHighest: Boolean): ScanResult?

    fun findAccessPointsMatchingRegex(regexForSSID: String, takeHighest: Boolean): List<ScanResult>?

    fun findSavedNetworkByRegex(regexForSSID: String): WifiConfiguration?

    fun findSavedNetworksMatchingRegex(regexForSSID: String): List<WifiConfiguration>?

    fun findSSIDsMatchingRegex(regexForSSID: String): List<String>?

    fun getNearbyAccessPoints(filterDuplicates: Boolean): List<ScanResult>

    fun isNetworkASavedConfiguration(ssid: String?): Boolean
}
