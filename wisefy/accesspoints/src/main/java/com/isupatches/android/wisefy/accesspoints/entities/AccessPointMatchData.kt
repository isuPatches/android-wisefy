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
 * A set of classes that are used in requests to check if an access point matches the given criteria.
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
internal sealed class AccessPointMatchData {

    /**
     * A data class representing an access point match request with SSID.
     *
     * @property regexForSSID The regex to use when matching the SSID
     *
     * @see AccessPointMatchData
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    data class SSID(
        val regexForSSID: String
    ) : AccessPointMatchData()

    /**
     * A data class representing an access point match request with BSSID.
     *
     * @property regexForBSSID The regex to use when matching the BSSID
     *
     * @see AccessPointMatchData
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    data class BSSID(
        val regexForBSSID: String
    ) : AccessPointMatchData()
}
