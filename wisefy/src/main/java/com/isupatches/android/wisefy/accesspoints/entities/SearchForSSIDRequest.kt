/*
 * Copyright 2021 Patches Klinefelter
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

sealed class SearchForSingleSSIDRequest(open val timeoutInMillis: Int) {

    data class SSID(
        val regexForSSID: String,
        override val timeoutInMillis: Int
    ) : SearchForSingleSSIDRequest(timeoutInMillis)

    data class BSSID(
        val regexForBSSID: String,
        override val timeoutInMillis: Int
    ) : SearchForSingleSSIDRequest(timeoutInMillis)
}

sealed class SearchForMultipleSSIDsRequest {

    data class SSID(
        val regexForSSID: String
    ) : SearchForMultipleSSIDsRequest()

    data class BSSID(
        val regexForBSSID: String
    ) : SearchForMultipleSSIDsRequest()
}

internal fun SearchForSingleSSIDRequest.toSearchForSingleAccessPointRequest(): SearchForSingleAccessPointRequest {
    return when (this) {
        is SearchForSingleSSIDRequest.SSID -> SearchForSingleAccessPointRequest.SSID(regexForSSID, timeoutInMillis)
        is SearchForSingleSSIDRequest.BSSID -> SearchForSingleAccessPointRequest.BSSID(regexForBSSID, timeoutInMillis)
    }
}

internal fun SearchForMultipleSSIDsRequest.toAccessPointMatchData(): AccessPointMatchData {
    return when (this) {
        is SearchForMultipleSSIDsRequest.SSID -> AccessPointMatchData.SSID(regexForSSID)
        is SearchForMultipleSSIDsRequest.BSSID -> AccessPointMatchData.BSSID(regexForBSSID)
    }
}
