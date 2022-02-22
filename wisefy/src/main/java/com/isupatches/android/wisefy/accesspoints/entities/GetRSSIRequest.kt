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
 * A set of data representations to retrieve the RRSI level of a nearby network/access point.
 *
 * @property takeHighest Whether the request returns the first RSSI value or the highest RSSI value for the network in
 *  the case of one that has duplicate access points
 * @property timeoutInMillis How long the request should wait to find the network
 *
 * @author Patches Klinefelter
 * @since 02/2022
 */
sealed class GetRSSIRequest(
    open val takeHighest: Boolean,
    open val timeoutInMillis: Int
) {

    /**
     * A data representation to retrieve the RRSI level of a nearby network/access point by SSID.
     *
     * @property regexForSSID The regex to match for the access point's SSID
     * @property takeHighest Whether the request returns the first RSSI value or the highest RSSI value for the network
     *  in the case of one that has duplicate access points
     * @property timeoutInMillis How long the request should wait to find the network
     *
     * @see GetRSSIRequest
     *
     * @author Patches Klinefelter
     * @since 02/2022
     */
    data class SSID(
        val regexForSSID: String,
        override val timeoutInMillis: Int,
        override val takeHighest: Boolean = true
    ) : GetRSSIRequest(takeHighest, timeoutInMillis)

    /**
     * A data representation to retrieve the RRSI level of a nearby network/access point by BSSID.
     *
     * @property regexForBSSID The regex to match for the access point's BSSID
     * @property takeHighest Whether the request returns the first RSSI value or the highest RSSI value for the network
     *  in the case of one that has duplicate access points
     * @property timeoutInMillis How long the request should wait to find the network
     *
     * @see GetRSSIRequest
     *
     * @author Patches Klinefelter
     * @since 02/2022
     */
    data class BSSID(
        val regexForBSSID: String,
        override val timeoutInMillis: Int,
        override val takeHighest: Boolean = true
    ) : GetRSSIRequest(takeHighest, timeoutInMillis)
}
