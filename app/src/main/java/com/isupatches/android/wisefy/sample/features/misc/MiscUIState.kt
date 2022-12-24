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
package com.isupatches.android.wisefy.sample.features.misc

import com.isupatches.android.wisefy.core.exceptions.WisefyException
import com.isupatches.android.wisefy.networkconnection.entities.ChangeNetworkResult
import com.isupatches.android.wisefy.networkinfo.entities.GetNetworkConnectionStatusResult
import com.isupatches.android.wisefy.networkinfo.entities.NetworkData
import com.isupatches.android.wisefy.savednetworks.entities.SavedNetworkData
import com.isupatches.android.wisefy.wifi.entities.DisableWifiResult
import com.isupatches.android.wisefy.wifi.entities.EnableWifiResult

internal data class MiscUIState(
    val loadingState: MiscLoadingState,
    val dialogState: MiscDialogState
)

internal data class MiscLoadingState(
    val isLoading: Boolean = false
)

internal sealed class MiscDialogState {

    object None : MiscDialogState()

    sealed class Failure : MiscDialogState() {
        data class WisefyAsync(val exception: WisefyException) : Failure()
    }

    sealed class ChangeNetwork : MiscDialogState() {
        data class Success(val result: ChangeNetworkResult.Success) : ChangeNetwork()
        data class Failure(val result: ChangeNetworkResult.Failure) : ChangeNetwork()
        object PreAndroidQ : ChangeNetwork()
    }

    sealed class DisableWifi : MiscDialogState() {
        data class Success(val result: DisableWifiResult.Success) : DisableWifi()
        data class Failure(val result: DisableWifiResult.Failure) : DisableWifi()
        object PermissionsError : DisableWifi()
    }

    sealed class EnableWifi : MiscDialogState() {
        data class Success(val result: EnableWifiResult.Success) : EnableWifi()
        data class Failure(val result: EnableWifiResult.Failure) : EnableWifi()
        object PermissionsError : EnableWifi()
    }

    sealed class GetCurrentNetwork : MiscDialogState() {
        data class Success(val network: NetworkData) : GetCurrentNetwork()
        object Failure : GetCurrentNetwork()
        object PermissionsError : GetCurrentNetwork()
    }

    sealed class GetNetworkConnectionStatus : MiscDialogState() {
        data class Success(val data: GetNetworkConnectionStatusResult) : GetNetworkConnectionStatus()
        object PermissionsError : GetNetworkConnectionStatus()
    }

    sealed class GetSavedNetworks : MiscDialogState() {
        data class Success(val savedNetworks: List<SavedNetworkData>) : GetSavedNetworks()
        object Failure : GetSavedNetworks()
        object PermissionsError : GetSavedNetworks()
    }

    sealed class IsWifiEnabled : MiscDialogState() {
        object True : IsWifiEnabled()
        object False : IsWifiEnabled()
        object PermissionsError : IsWifiEnabled()
    }
}
