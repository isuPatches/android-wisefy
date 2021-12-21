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
package com.isupatches.android.wisefy.networkconnection.entities

import com.isupatches.android.wisefy.savednetworks.entities.SearchForSavedNetworkRequest

sealed class NetworkConnectionRequest(
    open val timeoutInMillis: Int
) {
    data class SSID(
        val ssid: String,
        override val timeoutInMillis: Int
    ) : NetworkConnectionRequest(timeoutInMillis)

    data class BSSID(
        val bssid: String,
        override val timeoutInMillis: Int
    ) : NetworkConnectionRequest(timeoutInMillis)
}

internal fun NetworkConnectionRequest.toSearchForNetworkRequest(): SearchForSavedNetworkRequest {
    return when (this) {
        is NetworkConnectionRequest.SSID -> SearchForSavedNetworkRequest.SSID(ssid)
        is NetworkConnectionRequest.BSSID -> SearchForSavedNetworkRequest.BSSID(bssid)
    }
}
