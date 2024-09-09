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
package com.isupatches.android.wisefy.sample.features.search

import com.isupatches.android.wisefy.accesspoints.entities.AccessPointData
import com.isupatches.android.wisefy.sample.entities.SSIDType
import com.isupatches.android.wisefy.sample.entities.SearchType
import com.isupatches.android.wisefy.savednetworks.entities.SavedNetworkData

internal data class SearchUIState(
    val loadingState: SearchLoadingState,
    val dialogState: SearchDialogState,
    val inputState: SearchInputState,
    val searchType: SearchType,
    val ssidType: SSIDType,
    val useRegexForSearch: Boolean,
    val returnFullList: Boolean,
    val filterDuplicates: Boolean,
    val timeoutInSeconds: Int?,
)

internal data class SearchLoadingState(
    val isLoading: Boolean,
)

internal sealed class SearchDialogState {
    data object None : SearchDialogState()

    sealed class InputError : SearchDialogState() {
        data object SSID : InputError()
        data object BSSID : InputError()
    }

    sealed class Failure : SearchDialogState() {
        data class WisefyAsync(
            val exception: Throwable,
        ) : Failure()
    }

    sealed class SearchForAccessPoint : SearchDialogState() {
        data class Success(
            val data: AccessPointData,
        ) : SearchForAccessPoint()

        data object NoAccessPointFound : SearchForAccessPoint()

        data object PermissionError : SearchForAccessPoint()
    }

    sealed class SearchForAccessPoints : SearchDialogState() {
        data class Success(
            val data: List<AccessPointData>,
        ) : SearchForAccessPoints()

        data object NoAccessPointsFound : SearchForAccessPoints()

        data object PermissionError : SearchForAccessPoints()
    }

    sealed class SearchForSSID : SearchDialogState() {
        data class Success(
            val data: String,
        ) : SearchForSSID()

        data object NoSSIDFound : SearchForSSID()

        data object PermissionError : SearchForSSID()
    }

    sealed class SearchForSSIDs : SearchDialogState() {
        data class Success(
            val data: List<String>,
        ) : SearchForSSIDs()

        data object NoSSIDsFound : SearchForSSIDs()

        data object PermissionError : SearchForSSIDs()
    }

    sealed class SearchForSavedNetwork : SearchDialogState() {
        data class Success(
            val data: SavedNetworkData,
        ) : SearchForSavedNetwork()

        data object NoSavedNetworkFound : SearchForSavedNetwork()

        data object PermissionError : SearchForSavedNetwork()
    }

    sealed class SearchForSavedNetworks : SearchDialogState() {
        data class Success(
            val data: List<SavedNetworkData>,
        ) : SearchForSavedNetworks()

        data object NoSavedNetworksFound : SearchForSavedNetworks()

        data object PermissionError : SearchForSavedNetworks()
    }
}

internal data class SearchInputState(
    val input: String,
    val inputValidityState: SearchInputValidityState,
)

internal sealed class SearchInputValidityState {
    sealed class SSID : SearchInputValidityState() {
        data object Valid : SearchInputValidityState()

        sealed class Invalid : SearchInputValidityState() {
            data object Empty : Invalid()
            data object TooShort : Invalid()
            data object TooLong : Invalid()
            data object InvalidCharacters : Invalid()
            data object InvalidStartCharacters : Invalid()
            data object LeadingOrTrailingSpaces : Invalid()
            data object InvalidUnicode : Invalid()
        }
    }

    sealed class BSSID : SearchInputValidityState() {
        data object Valid : BSSID()

        sealed class Invalid : BSSID() {
            data object Empty : Invalid()
            data object ImproperFormat : Invalid()
        }
    }
}
