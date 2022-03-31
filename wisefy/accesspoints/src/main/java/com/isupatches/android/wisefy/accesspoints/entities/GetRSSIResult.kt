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
 * A set of classes and objects that are used to represent a result when getting the RSSI of a nearby access point.
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
sealed class GetRSSIResult {

    /**
     * A data representation for when there is no matching nearby access point to retrieve the RSSI.
     *
     * @see GetRSSIResult
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    object Empty : GetRSSIResult()

    /**
     * A data representation for when there is a matching nearby access point to retrieve the RSSI.
     *
     * @property data The RSSI value for the nearby access point
     *
     * @see GetRSSIResult
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    data class RSSI(val data: RSSIData) : GetRSSIResult()
}
