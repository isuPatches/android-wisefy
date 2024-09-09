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
package com.isupatches.android.wisefy.sample.features.remove

import com.isupatches.android.wisefy.core.exceptions.WisefyException
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkResult
import com.isupatches.android.wisefy.sample.entities.SSIDType

internal data class RemoveNetworkUIState(
    val loadingState: RemoveNetworkLoadingState,
    val dialogState: RemoveNetworkDialogState,
    val inputState: RemoveNetworkInputState,
    val ssidType: SSIDType,
)

internal data class RemoveNetworkLoadingState(
    val isLoading: Boolean,
)

internal sealed class RemoveNetworkDialogState {

    data object None : RemoveNetworkDialogState()

    sealed class Failure : RemoveNetworkDialogState() {
        data class WisefyAsync(
            val exception: WisefyException,
        ) : Failure()
    }

    sealed class RemoveNetwork : RemoveNetworkDialogState() {
        data class Failure(
            val result: RemoveNetworkResult.Failure,
        ) : RemoveNetwork()

        data class Success(
            val result: RemoveNetworkResult.Success,
        ) : RemoveNetwork()

        data object PermissionsError : RemoveNetwork()
    }

    sealed class InputError : RemoveNetworkDialogState() {
        data object SSID : InputError()
        data object BSSID : InputError()
    }
}

internal data class RemoveNetworkInputState(
    val networkInput: String,
    val networkInputValidityState: RemoveNetworkInputValidityState,
)

internal sealed class RemoveNetworkInputValidityState {

    sealed class SSID : RemoveNetworkInputValidityState() {
        data object Valid : SSID()

        sealed class Invalid : SSID() {
            data object Empty : Invalid()
            data object TooShort : Invalid()
            data object TooLong : Invalid()
            data object InvalidCharacters : Invalid()
            data object InvalidStartCharacters : Invalid()
            data object LeadingOrTrailingSpaces : Invalid()
            data object InvalidUnicode : Invalid()
        }
    }

    sealed class BSSID : RemoveNetworkInputValidityState() {
        data object Valid : BSSID()

        sealed class Invalid : BSSID() {
            data object Empty : Invalid()
            data object ImproperFormat : Invalid()
        }
    }
}
