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
package com.isupatches.android.wisefy.sample.features.add

import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkResult
import com.isupatches.android.wisefy.core.exceptions.WisefyException
import com.isupatches.android.wisefy.sample.entities.NetworkType

internal data class AddNetworkUIState(
    val loadingState: AddNetworkLoadingState,
    val dialogState: AddNetworkDialogState,
    val inputState: AddNetworkInputState,
    val networkType: NetworkType,
)

internal data class AddNetworkLoadingState(
    val isLoading: Boolean,
)

internal sealed class AddNetworkDialogState {

    data object None : AddNetworkDialogState()

    sealed class Failure : AddNetworkDialogState() {
        data class WisefyAsync(
            val exception: WisefyException,
        ) : Failure()
    }

    sealed class AddNetwork : AddNetworkDialogState() {
        data class Failure(
            val result: AddNetworkResult.Failure,
        ) : AddNetwork()

        data class Success(
            val result: AddNetworkResult.Success,
        ) : AddNetwork()

        sealed class PermissionsError : AddNetwork() {
            data object AddOpenNetwork : PermissionsError()
            data object AddWPA2Network : PermissionsError()
            data object AddWPA3Network : PermissionsError()
        }
    }

    sealed class InputError : AddNetworkDialogState() {
        data object SSID : InputError()
        data object Passphrase : InputError()
        data object BSSID : InputError()
    }
}

internal data class AddNetworkInputState(
    val ssidInput: String,
    val ssidInputValidityState: AddNetworkSSIDInputValidityState,
    val passphraseInput: String,
    val passphraseInputValidityState: AddNetworkPassphraseInputValidityState,
    val bssidInput: String?,
    val bssidInputValidityState: AddNetworkBSSIDInputValidityState,
)

internal sealed class AddNetworkSSIDInputValidityState {
    data object Valid : AddNetworkSSIDInputValidityState()

    sealed class Invalid : AddNetworkSSIDInputValidityState() {
        data object Empty : Invalid()
        data object TooShort : Invalid()
        data object TooLong : Invalid()
        data object InvalidCharacters : Invalid()
        data object InvalidStartCharacters : Invalid()
        data object LeadingOrTrailingSpaces : Invalid()
        data object InvalidUnicode : Invalid()
    }
}

internal sealed class AddNetworkPassphraseInputValidityState {
    data object Valid : AddNetworkPassphraseInputValidityState()

    sealed class Invalid : AddNetworkPassphraseInputValidityState() {
        data object Empty : Invalid()
        data object TooShort : Invalid()
        data object TooLong : Invalid()
        data object InvalidASCII : Invalid()
    }
}

internal sealed class AddNetworkBSSIDInputValidityState {
    sealed class Valid : AddNetworkBSSIDInputValidityState() {
        data object Empty : Valid()
        data object BSSID : Valid()
    }

    data object Invalid : AddNetworkBSSIDInputValidityState()
}
