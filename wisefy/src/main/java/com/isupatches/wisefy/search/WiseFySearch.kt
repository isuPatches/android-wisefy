package com.isupatches.wisefy.search

import android.net.wifi.ScanResult
import android.net.wifi.WifiConfiguration

/**
 * An interface that helps with searching.
 *
 * @see [WiseFySearchImpl]
 *
 * @author Patches
 * @since 3.0
 */
internal interface WiseFySearch {

    fun init()

    fun destroy()

    fun findAccessPointByRegex(regexForSSID: String, timeoutInMillis: Int, takeHighest: Boolean): ScanResult?

    fun findAccessPointsMatchingRegex(regexForSSID: String, takeHighest: Boolean): List<ScanResult>?

    fun findSavedNetworkByRegex(regexForSSID: String): WifiConfiguration?

    fun findSavedNetworksMatchingRegex(regexForSSID: String): List<WifiConfiguration>?

    fun findSSIDsMatchingRegex(regexForSSID: String): List<String>?

    fun isNetworkASavedConfiguration(ssid: String?): Boolean

    fun removeEntriesWithLowerSignalStrength(accessPoints: List<ScanResult>): List<ScanResult>
}
