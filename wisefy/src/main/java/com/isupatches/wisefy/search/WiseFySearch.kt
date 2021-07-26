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
import androidx.annotation.RequiresPermission

/**
 * An interface that helps with searching.
 *
 * @see [DefaultWiseFySearch]
 *
 * Updates
 * - 05/12/2019
 *      * Made more generic for pre and post SDK 23 classes
 *      * Added getNearbyAccessPoints
 *      * Removed removeEntriesWithLowerSignalStrength
 * - 01/04/2020: Refined permissions
 *
 * @author Patches
 * @since 3.0
 */
internal interface WiseFySearch {

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun findAccessPointByRegex(regexForSSID: String, timeoutInMillis: Int, takeHighest: Boolean): ScanResult?

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun findAccessPointsMatchingRegex(regexForSSID: String, takeHighest: Boolean): List<ScanResult>?

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun findSavedNetworkByRegex(regexForSSID: String): WifiConfiguration?

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun findSavedNetworksMatchingRegex(regexForSSID: String): List<WifiConfiguration>?

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun findSSIDsMatchingRegex(regexForSSID: String): List<String>?

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun getNearbyAccessPoints(filterDuplicates: Boolean): List<ScanResult>?

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun isNetworkASavedConfiguration(ssid: String?): Boolean
}
