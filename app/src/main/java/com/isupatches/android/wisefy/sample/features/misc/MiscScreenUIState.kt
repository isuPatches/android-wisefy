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
package com.isupatches.android.wisefy.sample.features.misc

import com.isupatches.android.wisefy.accesspoints.entities.AccessPointData
import com.isupatches.android.wisefy.core.exceptions.WisefyException
import com.isupatches.android.wisefy.networkinfo.entities.GetNetworkConnectionStatusResult
import com.isupatches.android.wisefy.networkinfo.entities.NetworkData
import com.isupatches.android.wisefy.savednetworks.entities.SavedNetworkData
import com.isupatches.android.wisefy.wifi.entities.DisableWifiResult
import com.isupatches.android.wisefy.wifi.entities.EnableWifiResult

internal data class MiscScreenUIState(
    val loadingState: MiscScreenLoadingState,
    val dialogState: MiscScreenDialogState
)

internal data class MiscScreenLoadingState(
    val isLoading: Boolean = false
)

internal sealed class MiscScreenDialogState {

    object None : MiscScreenDialogState()

    sealed class Failure : MiscScreenDialogState() {
        data class WisefyAsync(val exception: WisefyException) : Failure()
    }

    sealed class DisableWifi : MiscScreenDialogState() {
        data class Success(val result: DisableWifiResult.Success) : DisableWifi()
        data class Failure(val result: DisableWifiResult.Failure) : DisableWifi()
    }

    sealed class DisconnectFromCurrentNetwork : MiscScreenDialogState() {
        sealed class Success : DisconnectFromCurrentNetwork() {
            object RequestPlaced : Success()
            object Disconnected : Success()
        }

        sealed class Failure : DisconnectFromCurrentNetwork() {
            object NetworkNotFound : Failure()
            object UnableToDisconnect : Failure()
        }

        object DisplayAndroidQMessage : DisconnectFromCurrentNetwork()
    }

    sealed class EnableWifi : MiscScreenDialogState() {
        data class Success(val result: EnableWifiResult.Success) : EnableWifi()
        data class Failure(val result: EnableWifiResult.Failure) : EnableWifi()
    }

    sealed class GetCurrentNetwork : MiscScreenDialogState() {
        data class Success(val network: NetworkData) : GetCurrentNetwork()
        object Failure : GetCurrentNetwork()
        object PermissionsError : GetCurrentNetwork()
    }

    sealed class GetNearbyAccessPoints : MiscScreenDialogState() {
        data class Success(val accessPoints: List<AccessPointData>) : GetNearbyAccessPoints()
        object Failure : GetNearbyAccessPoints()
        object PermissionsError : GetNearbyAccessPoints()
    }

    sealed class GetNetworkConnectionStatus : MiscScreenDialogState() {
        data class Success(val data: GetNetworkConnectionStatusResult) : GetNetworkConnectionStatus()
        object PermissionsError : GetNetworkConnectionStatus()
    }

    sealed class GetSavedNetworks : MiscScreenDialogState() {
        data class Success(val savedNetworks: List<SavedNetworkData>) : GetSavedNetworks()
        object Failure : GetSavedNetworks()
        object PermissionsError : GetSavedNetworks()
    }

    sealed class IsWifiEnabled : MiscScreenDialogState() {
        object True : IsWifiEnabled()
        object False : IsWifiEnabled()
    }
}
