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

import android.net.wifi.ScanResult
import android.net.wifi.WifiConfiguration

/**
 * An interface that helps with searching.
 *
 * @see [WiseFySearchLegacy]
 * @see [WiseFySearchSDK23]
 *
 * Updates
 * - 05/12/2019
 *      * Made more generic for pre and post SDK 23 classes
 *      * Added getNearbyAccessPoints
 *      * Removed removeEntriesWithLowerSignalStrength
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

    fun getNearbyAccessPoints(filterDuplicates: Boolean): List<ScanResult>?

    fun isNetworkASavedConfiguration(ssid: String?): Boolean
}
