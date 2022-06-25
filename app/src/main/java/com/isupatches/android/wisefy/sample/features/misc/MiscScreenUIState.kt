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
        object DisableWifi : Failure()
        object DisplayAndroidQMessage : Failure()
        object EnableWifi : Failure()
        object GetCurrentNetwork : Failure()
        object GetCurrentNetworkInfo : Failure()
        object GetFrequency : Failure()
        object GetIP : Failure()
        data class WisefyAsync(val throwable: Throwable) : Failure()
        object GetNearbyAccessPoints : Failure()
        object GetSavedNetworks : Failure()
    }

    sealed class Success : MiscScreenDialogState() {
        object DisableWifi : Success()
        object EnableWifi : Success()
        data class GetCurrentNetwork(val network: NetworkData) : Success()
        data class GetCurrentNetworkInfo(val networkInfo: NetworkInfoData) : Success()
        data class GetFrequency(val frequency: FrequencyData) : Success()
        data class GetIP(val ip: IPData) : Success()
        data class GetNearbyAccessPoints(val accessPoints: List<AccessPointData>) : Success()
        data class GetSavedNetworks(val savedNetworks: List<SavedNetworkData>) : Success()
    }

    sealed class PermissionsError : MiscScreenDialogState() {
        object GetFrequency : PermissionsError()
        object GetIP : PermissionsError()
        object GetNearbyAccessPoints : PermissionsError()
        object GetSavedNetworks : PermissionsError()
    }
}
