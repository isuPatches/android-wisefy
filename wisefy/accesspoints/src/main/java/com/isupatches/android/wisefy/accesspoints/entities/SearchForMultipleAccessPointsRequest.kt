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
package com.isupatches.android.wisefy.accesspoints.entities

/**
 * A set of classes and objects that are used to represent requests to search for multiple access points.
 *
 * @property filterDuplicates Whether nearby access points with the same SSID but lower RSSI levels should be excluded
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
sealed class SearchForMultipleAccessPointsRequest(open val filterDuplicates: Boolean) {

    /**
     * A data representation of a request to search for multiple access points by SSID.
     *
     * @property regexForSSID The regex to use when matching the SSID
     * @property filterDuplicates Whether nearby access points with the same SSID but lower RSSI levels should be
     *  excluded
     *
     * @see SearchForMultipleAccessPointsRequest
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    data class SSID(
        val regexForSSID: String,
        override val filterDuplicates: Boolean = true
    ) : SearchForMultipleAccessPointsRequest(filterDuplicates)

    /**
     * A data representation of a request to search for multiple access points by BSSID.
     *
     * @property regexForBSSID The regex to use when matching the BSSID
     * @property filterDuplicates Whether nearby access points with the same SSID but lower RSSI levels should be
     *  excluded
     *
     * @see SearchForMultipleAccessPointsRequest
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    data class BSSID(
        val regexForBSSID: String,
        override val filterDuplicates: Boolean = true
    ) : SearchForMultipleAccessPointsRequest(filterDuplicates)
}