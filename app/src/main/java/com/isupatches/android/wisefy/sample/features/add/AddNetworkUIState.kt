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
package com.isupatches.android.wisefy.sample.features.add

import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkResult
import com.isupatches.android.wisefy.networkconnection.entities.ConnectToNetworkResult
import com.isupatches.android.wisefy.sample.entities.NetworkType

internal data class AddNetworkUIState(
    val loadingState: AddNetworkLoadingState,
    val dialogState: AddNetworkDialogState,
    val inputState: AddNetworkInputState,
    val networkType: NetworkType
)

internal data class AddNetworkLoadingState(val isLoading: Boolean)

internal sealed class AddNetworkDialogState {

    object None : AddNetworkDialogState()

    sealed class Failure : AddNetworkDialogState() {
        data class WisefyAsync(val throwable: Throwable) : Failure()
    }

    sealed class AddNetwork : AddNetworkDialogState() {
        data class Failure(val result: AddNetworkResult.Failure) : AddNetwork()

        data class Success(val result: AddNetworkResult.Success) : AddNetwork()

        sealed class PermissionsError : AddNetwork() {
            object AddOpenNetwork : PermissionsError()
            object AddWPA2Network : PermissionsError()
            object AddWPA3Network : PermissionsError()
        }
    }

    sealed class ConnectToNetwork : AddNetworkDialogState() {
        data class Failure(val result: ConnectToNetworkResult.Failure) : ConnectToNetwork()
        data class Success(val result: ConnectToNetworkResult.Success) : ConnectToNetwork()
        sealed class PermissionsError : ConnectToNetwork()
    }

    sealed class InputError : AddNetworkDialogState() {
        object SSID : InputError()
        object Passphrase : InputError()
        object BSSID : InputError()
    }
}

internal data class AddNetworkInputState(
    val ssidInput: String,
    val ssidState: AddNetworkInputSSIDState,
    val passphraseInput: String,
    val passphraseState: AddNetworkPassphraseState,
    val bssidInput: String?,
    val bssidState: AddNetworkBSSIDState
)

internal sealed class AddNetworkInputSSIDState {
    object Valid : AddNetworkInputSSIDState()

    sealed class Invalid : AddNetworkInputSSIDState() {
        object Empty : Invalid()
        object TooShort : Invalid()
        object TooLong : Invalid()
        object InvalidCharacters : Invalid()
        object InvalidStartCharacters : Invalid()
        object LeadingOrTrailingSpaces : Invalid()
        object InvalidUnicode : Invalid()
    }
}

internal sealed class AddNetworkPassphraseState {
    object Valid : AddNetworkPassphraseState()

    sealed class Invalid : AddNetworkPassphraseState() {
        object Empty : Invalid()
        object TooShort : Invalid()
        object TooLong : Invalid()
        object InvalidASCII : Invalid()
    }
}

internal sealed class AddNetworkBSSIDState {
    sealed class Valid : AddNetworkBSSIDState() {
        object Empty : Valid()
        object BSSID : Valid()
    }

    object Invalid : AddNetworkBSSIDState()
}
