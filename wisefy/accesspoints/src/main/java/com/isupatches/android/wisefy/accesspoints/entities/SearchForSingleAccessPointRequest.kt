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
 * A set of classes and objects that are used to represent requests to search for a single access point.
 *
 * @property timeoutInMillis The timeout in milliseconds to wait for an access point to appear
 * @property filterDuplicates Whether nearby access points with the same SSID but lower RSSI levels should be excluded
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
sealed class SearchForSingleAccessPointRequest(
    open val timeoutInMillis: Int,
    open val filterDuplicates: Boolean
) {

    /**
     * A data representation of a request to search for a single access point by SSID.
     *
     * @property regexForSSID The regex to use when matching the SSID
     * @property timeoutInMillis The timeout in milliseconds to wait for an access point to appear
     * @property filterDuplicates Whether nearby access points with the same SSID but lower RSSI levels
     *  should be excluded
     *
     * @see SearchForSingleAccessPointRequest
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    data class SSID(
        val regexForSSID: String,
        override val timeoutInMillis: Int,
        override val filterDuplicates: Boolean = true
    ) : SearchForSingleAccessPointRequest(timeoutInMillis, filterDuplicates)

    /**
     * A data representation of a request to search for a single access point by BSSID.
     *
     * @property regexForBSSID The regex to use when matching the BSSID
     * @property timeoutInMillis The timeout in milliseconds to wait for an access point to appear
     * @property filterDuplicates Whether nearby access points with the same SSID but lower RSSI levels
     *  should be excluded
     *
     * @see SearchForSingleAccessPointRequest
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    data class BSSID(
        val regexForBSSID: String,
        override val timeoutInMillis: Int,
        override val filterDuplicates: Boolean = true
    ) : SearchForSingleAccessPointRequest(timeoutInMillis, filterDuplicates)
}
