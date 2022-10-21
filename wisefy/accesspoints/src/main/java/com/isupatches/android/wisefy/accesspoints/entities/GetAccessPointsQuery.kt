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
 * A data representation of a request to get all nearby access points.
 *
 * @property filterDuplicates Whether nearby access points with the same SSID but lower RSSI levels should be excluded
 *
 * @author Patches Klinefelter
 * @since 08/2022, version 5.0.0
 */
sealed class GetAccessPointsQuery(open val filterDuplicates: Boolean) {

    data class All(
        override val filterDuplicates: Boolean = true
    ) : GetAccessPointsQuery(filterDuplicates)

    data class BySSID(
        val regex: String,
        val timeoutInMillis: Int? = null,
        override val filterDuplicates: Boolean = true
    ) : GetAccessPointsQuery(filterDuplicates)

    data class ByBSSID(
        val regex: String,
        val timeoutInMillis: Int? = null,
        override val filterDuplicates: Boolean = true
    ) : GetAccessPointsQuery(filterDuplicates)
}
