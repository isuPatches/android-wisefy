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

internal data class AddNetworkUIState(
    val loadingState: AddNetworkLoadingState,
    val dialogState: AddNetworkDialogState
)

internal data class AddNetworkLoadingState(val isLoading: Boolean)

internal sealed class AddNetworkDialogState {

    object None : AddNetworkDialogState()

    sealed class Failure : AddNetworkDialogState() {
        data class UnableToAdd(val result: AddNetworkResult.Failure) : Failure()
        data class WisefyAsync(val throwable: Throwable) : Failure()
    }

    data class Success(val result: AddNetworkResult.Success) : AddNetworkDialogState()

    sealed class PermissionsError : AddNetworkDialogState() {
        object AddOpenNetwork : PermissionsError()
        object AddWPA2Network : PermissionsError()
        object AddWPA3Network : PermissionsError()
    }

    sealed class InputError : AddNetworkDialogState() {
        object SSID : InputError()
        object Passphrase : InputError()
    }
}

internal data class AddNetworkInputState(
    val ssidState: AddNetworkInputSSIDState,
    val passphraseState: AddNetworkPassphraseState
)

internal sealed class AddNetworkInputSSIDState {
    data class Valid(val value: String) : AddNetworkInputSSIDState()

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
    data class Valid(val value: String) : AddNetworkPassphraseState()

    sealed class Invalid : AddNetworkPassphraseState() {
        object Empty : Invalid()
        object TooShort : Invalid()
        object TooLong : Invalid()
        object InvalidASCII : Invalid()
    }
}
