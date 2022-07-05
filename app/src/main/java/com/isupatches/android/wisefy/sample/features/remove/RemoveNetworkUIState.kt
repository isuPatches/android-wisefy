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
package com.isupatches.android.wisefy.sample.features.remove

import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkResult

internal data class RemoveNetworkUIState(
    val loadingState: RemoveNetworkLoadingState,
    val dialogState: RemoveNetworkDialogState
)

internal data class RemoveNetworkLoadingState(val isLoading: Boolean)

internal sealed class RemoveNetworkDialogState {

    object None : RemoveNetworkDialogState()

    sealed class Failure : RemoveNetworkDialogState() {
        data class WisefyAsync(val throwable: Throwable) : Failure()
        object InputError : Failure()
    }

    sealed class RemoveNetwork : RemoveNetworkDialogState() {
        sealed class Failure : RemoveNetwork() {
            object NotFoundToRemove : Failure()
            data class UnableToRemove(val result: RemoveNetworkResult.Failure) : Failure()
        }

        data class Success(val result: RemoveNetworkResult.Success) : RemoveNetwork()

        object PermissionsError : RemoveNetwork()
    }
}

internal sealed class RemoveNetworkInputState {

    sealed class SSID : RemoveNetworkInputState() {
        data class Valid(val value: String) : SSID()

        sealed class Invalid : SSID() {
            object Empty : Invalid()
            object TooShort : Invalid()
            object TooLong : Invalid()
            object InvalidCharacters : Invalid()
            object InvalidStartCharacters : Invalid()
            object LeadingOrTrailingSpaces : Invalid()
            object InvalidUnicode : Invalid()
        }
    }

    sealed class BSSID : RemoveNetworkInputState() {
        data class Valid(val value: String) : BSSID()

        sealed class Invalid : BSSID() {
            object Empty : Invalid()
            object ImproperFormat : Invalid()
        }
    }
}
