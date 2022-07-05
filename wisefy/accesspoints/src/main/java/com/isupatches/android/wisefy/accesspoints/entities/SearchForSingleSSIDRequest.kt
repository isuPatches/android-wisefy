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
 * A set of classes and objects that are used to represent requests to search for a single SSID.
 *
 * @property timeoutInMillis The timeout in milliseconds to wait for an SSID to appear
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
sealed class SearchForSingleSSIDRequest(open val timeoutInMillis: Int) {

    /**
     * A data representation of a request to search for a single SSID by regex.
     *
     * @property regex The regex to use when matching the SSID
     *
     * @see SearchForSingleSSIDRequest
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    data class SSID(
        val regex: String,
        override val timeoutInMillis: Int
    ) : SearchForSingleSSIDRequest(timeoutInMillis)

    /**
     * A data representation of a request to search for a single BSSID by regex.
     *
     * @property regex The regex to use when matching the BSSID
     *
     * @see SearchForSingleSSIDRequest
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    data class BSSID(
        val regex: String,
        override val timeoutInMillis: Int
    ) : SearchForSingleSSIDRequest(timeoutInMillis)
}
