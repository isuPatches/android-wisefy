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

sealed class SearchForSingleAccessPointRequest(
    open val timeoutInMillis: Int,
    open val filterDuplicates: Boolean
) {

    data class SSID(
        val regexForSSID: String,
        override val timeoutInMillis: Int,
        override val filterDuplicates: Boolean = true
    ) : SearchForSingleAccessPointRequest(timeoutInMillis, filterDuplicates)

    data class BSSID(
        val regexForBSSID: String,
        override val timeoutInMillis: Int,
        override val filterDuplicates: Boolean = true
    ) : SearchForSingleAccessPointRequest(timeoutInMillis, filterDuplicates)
}

sealed class SearchForMultipleAccessPointsRequest(open val filterDuplicates: Boolean) {

    data class SSID(
        val regexForSSID: String,
        override val filterDuplicates: Boolean = true
    ) : SearchForMultipleAccessPointsRequest(filterDuplicates)

    data class BSSID(
        val regexForBSSID: String,
        override val filterDuplicates: Boolean = true
    ) : SearchForMultipleAccessPointsRequest(filterDuplicates)
}

internal fun SearchForSingleAccessPointRequest.toAccessPointMatchData(): AccessPointMatchData {
    return when (this) {
        is SearchForSingleAccessPointRequest.SSID -> AccessPointMatchData.SSID(regexForSSID)
        is SearchForSingleAccessPointRequest.BSSID -> AccessPointMatchData.BSSID(regexForBSSID)
    }
}

internal fun SearchForMultipleAccessPointsRequest.toAccessPointMatchData(): AccessPointMatchData {
    return when (this) {
        is SearchForMultipleAccessPointsRequest.SSID -> AccessPointMatchData.SSID(regexForSSID)
        is SearchForMultipleAccessPointsRequest.BSSID -> AccessPointMatchData.BSSID(regexForBSSID)
    }
}
