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
package com.isupatches.android.wisefy.savednetworks.entities

/**
 * A set of classes and objects that are used to represent requests to search for saved networks.
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
sealed class SearchForSavedNetworksRequest {

    /**
     * A data representation of a request to search for saved networks by SSID.
     *
     * @property regex The regex to use when matching the SSID
     *
     * @see SearchForSavedNetworksRequest
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    data class SSID(val regex: String) : SearchForSavedNetworksRequest()

    /**
     * A data representation of a request to search for saved networks by BSSID.
     *
     * @property regex The regex to use when matching the BSSID
     *
     * @see SearchForSavedNetworksRequest
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    data class BSSID(val regex: String) : SearchForSavedNetworksRequest()
}
