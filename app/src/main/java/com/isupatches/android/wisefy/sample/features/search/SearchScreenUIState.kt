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
import com.isupatches.android.wisefy.savednetworks.entities.SavedNetworkData

internal data class SearchUIState(
    val loadingState: SearchLoadingState,
    val dialogState: SearchDialogState
)

internal data class SearchLoadingState(val isLoading: Boolean)

internal sealed class SearchDialogState {
    object None : SearchDialogState()

    object InputError : SearchDialogState()

    sealed class Failure : SearchDialogState() {
        object NoAccessPointFound : Failure()
        object NoAccessPointsFound : Failure()
        object NoSavedNetworkFound : Failure()
        object NoSavedNetworksFound : Failure()
        object NoSSIDFound : Failure()
        object NoSSIDsFound : Failure()
        data class WisefyAsync(val throwable: Throwable) : Failure()
    }

    sealed class PermissionError : SearchDialogState() {
        object AccessPoint : PermissionError()
        object AccessPoints : PermissionError()
        object SavedNetwork : PermissionError()
        object SavedNetworks : PermissionError()
        object SSID : PermissionError()
        object SSIDs : PermissionError()
    }

    sealed class Success : SearchDialogState() {
        data class AccessPoint(val data: AccessPointData) : Success()
        data class AccessPoints(val data: List<AccessPointData>) : Success()
        data class SavedNetwork(val data: SavedNetworkData) : Success()
        data class SavedNetworks(val data: List<SavedNetworkData>) : Success()
        data class SSID(val data: SSIDData) : Success()
        data class SSIDs(val data: List<SSIDData>) : Success()
    }
}

internal sealed class SearchInputState {
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
