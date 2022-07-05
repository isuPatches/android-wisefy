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
package com.isupatches.android.wisefy.sample.features.search

import com.isupatches.android.wisefy.accesspoints.entities.AccessPointData
import com.isupatches.android.wisefy.accesspoints.entities.SSIDData
import com.isupatches.android.wisefy.sample.features.remove.RemoveNetworkInputState
import com.isupatches.android.wisefy.savednetworks.entities.SavedNetworkData

internal data class SearchUIState(
    val loadingState: SearchLoadingState,
    val dialogState: SearchDialogState
)

internal data class SearchLoadingState(val isLoading: Boolean)

internal sealed class SearchDialogState {
    object None : SearchDialogState()

    sealed class Failure : SearchDialogState() {
        data class WisefyAsync(val throwable: Throwable) : Failure()
        object InputError : Failure()
    }

    sealed class SearchForAccessPoint : SearchDialogState() {
        data class Success(val data: AccessPointData) : SearchForAccessPoint()
        object NoAccessPointFound : SearchForAccessPoint()
        object PermissionError : SearchForAccessPoint()
    }

    sealed class SearchForAccessPoints : SearchDialogState() {
        data class Success(val data: List<AccessPointData>) : SearchForAccessPoints()
        object NoAccessPointsFound : SearchForAccessPoints()
        object PermissionError : SearchForAccessPoints()
    }

    sealed class SearchForSSID : SearchDialogState() {
        data class Success(val data: SSIDData) : SearchForSSID()
        object NoSSIDFound : SearchForSSID()
        object PermissionError : SearchForSSID()
    }

    sealed class SearchForSSIDs : SearchDialogState() {
        data class Success(val data: List<SSIDData>) : SearchForSSIDs()
        object NoSSIDsFound : SearchForSSIDs()
        object PermissionError : SearchForSSIDs()
    }

    sealed class SearchForSavedNetwork : SearchDialogState() {
        data class Success(val data: SavedNetworkData) : SearchForSavedNetwork()
        object NoSavedNetworkFound : SearchForSavedNetwork()
        object PermissionError : SearchForSavedNetwork()
    }

    sealed class SearchForSavedNetworks : SearchDialogState() {
        data class Success(val data: List<SavedNetworkData>) : SearchForSavedNetworks()
        object NoSavedNetworksFound : SearchForSavedNetworks()
        object PermissionError : SearchForSavedNetworks()
    }
}

internal sealed class SearchInputState {
    sealed class SSID : SearchInputState() {
        data class Valid(val value: String) : SearchInputState()

        sealed class Invalid : SearchInputState() {
            object Empty : Invalid()
            object TooShort : Invalid()
            object TooLong : Invalid()
            object InvalidCharacters : Invalid()
            object InvalidStartCharacters : Invalid()
            object LeadingOrTrailingSpaces : Invalid()
            object InvalidUnicode : Invalid()
        }
    }

    sealed class BSSID : SearchInputState() {
        data class Valid(val value: String) : BSSID()

        sealed class Invalid : BSSID() {
            object Empty : Invalid()
            object ImproperFormat : Invalid()
        }
    }
}
