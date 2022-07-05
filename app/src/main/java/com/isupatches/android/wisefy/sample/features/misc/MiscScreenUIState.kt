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
import com.isupatches.android.wisefy.frequency.entities.FrequencyData
import com.isupatches.android.wisefy.networkinfo.entities.IPData
import com.isupatches.android.wisefy.networkinfo.entities.NetworkData
import com.isupatches.android.wisefy.networkinfo.entities.NetworkInfoData
import com.isupatches.android.wisefy.savednetworks.entities.SavedNetworkData

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
        data class WisefyAsync(val throwable: Throwable) : Failure()
    }

    sealed class PermissionsError : MiscScreenDialogState() {
        object GetSavedNetworks : PermissionsError()
    }

    sealed class DisableWifi : MiscScreenDialogState() {
        object Success : DisableWifi()
        object Failure : DisableWifi()
        object DisplayAndroidQMessage : DisableWifi()
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

        object PermissionsError : DisconnectFromCurrentNetwork()
    }

    sealed class EnableWifi : MiscScreenDialogState() {
        object Success : EnableWifi()
        object Failure : EnableWifi()
        object DisplayAndroidQMessage : EnableWifi()
    }

    sealed class GetCurrentNetwork : MiscScreenDialogState() {
        data class Success(val network: NetworkData) : GetCurrentNetwork()
        object Failure : GetCurrentNetwork()
    }

    sealed class GetCurrentNetworkInfo : MiscScreenDialogState() {
        data class Success(val networkInfo: NetworkInfoData) : GetCurrentNetworkInfo()
        object Failure : GetCurrentNetworkInfo()
    }

    sealed class GetFrequency : MiscScreenDialogState() {
        data class Success(val frequency: FrequencyData) : GetFrequency()
        object Failure : GetFrequency()
        object PermissionsError : GetFrequency()
    }

    sealed class GetIP : MiscScreenDialogState() {
        data class Success(val ip: IPData) : GetIP()
        object Failure : GetIP()
        object PermissionsError : GetIP()
    }

    sealed class GetNearbyAccessPoints : MiscScreenDialogState() {
        data class Success(val accessPoints: List<AccessPointData>) : GetNearbyAccessPoints()
        object Failure : GetNearbyAccessPoints()
        object PermissionsError : GetNearbyAccessPoints()
    }

    sealed class GetSavedNetworks : MiscScreenDialogState() {
        data class Success(val savedNetworks: List<SavedNetworkData>) : GetSavedNetworks()
        object Failure : GetSavedNetworks()
    }
}
