/*
 * Copyright 2022 Patches Barrett
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
package com.isupatches.android.wisefy.savednetworks.entities

/**
 * A data representation of a request to get all of the saved networks on the device.
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
sealed class GetSavedNetworksQuery {

    /**
     * A data representation of a query to get all saved networks.
     *
     * @see GetSavedNetworksQuery
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    object All : GetSavedNetworksQuery()

    /**
     * A data representation of a query to get saved networks matching a given SSID.
     *
     * @property regex The regex to use when finding the network by SSID
     *
     * @see GetSavedNetworksQuery
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    data class BySSID(val regex: String) : GetSavedNetworksQuery()

    /**
     * A data representation of a query to get saved networks matching a given BSSID.
     *
     * @property regex The regex to use when finding the network by BSSID
     *
     * @see GetSavedNetworksQuery
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    data class ByBSSID(val regex: String) : GetSavedNetworksQuery()
}
