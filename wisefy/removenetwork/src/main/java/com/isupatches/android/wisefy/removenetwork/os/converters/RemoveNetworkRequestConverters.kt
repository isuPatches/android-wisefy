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
package com.isupatches.android.wisefy.removenetwork.os.converters

import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkRequest
import com.isupatches.android.wisefy.savednetworks.entities.SearchForSavedNetworkRequest

/**
 * An internal helper to convert a remove network request to a search for saved network request.
 *
 * @see RemoveNetworkRequest
 * @see SearchForSavedNetworkRequest
 *
 * @return SearchForSavedNetworkRequest - The SearchForSavedNetworkRequest created from the RemoveNetworkRequest
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
internal fun RemoveNetworkRequest.toSearchForNetworkRequest(): SearchForSavedNetworkRequest {
    return when (this) {
        is RemoveNetworkRequest.SSID -> SearchForSavedNetworkRequest.SSID(regex)
        is RemoveNetworkRequest.BSSID -> SearchForSavedNetworkRequest.BSSID(regex)
    }
}
