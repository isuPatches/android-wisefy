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
package com.isupatches.android.wisefy.sample.features.misc.signal

import com.isupatches.android.wisefy.core.exceptions.WisefyException
import com.isupatches.android.wisefy.signal.entities.CalculateSignalLevelResult
import com.isupatches.android.wisefy.signal.entities.CompareSignalLevelResult

internal data class SignalUIState(
    val loadingState: SignalLoadingState,
    val dialogState: SignalDialogState,
    val inputState: SignalInputState
)

internal data class SignalLoadingState(
    val isLoading: Boolean = false
)

internal sealed class SignalDialogState {
    object None : SignalDialogState()

    sealed class InputError : SignalDialogState() {

        object CalculateSignalLevel : InputError()

        sealed class CompareSignalLevel : InputError() {
            object RSSI1 : CompareSignalLevel()
            object RSSI2 : CompareSignalLevel()
        }
    }

    sealed class Failure : SignalDialogState() {
        data class WisefyAsync(val exception: WisefyException) : Failure()
    }

    sealed class CalculateSignalLevel : SignalDialogState() {
        data class Success(val result: CalculateSignalLevelResult.Success) : CalculateSignalLevel()
        data class Failure(val result: CalculateSignalLevelResult.Failure) : CalculateSignalLevel()
    }

    sealed class CompareSignalLevel : SignalDialogState() {
        data class Success(val result: CompareSignalLevelResult.Success) : CalculateSignalLevel()
    }
}

internal data class SignalInputState(
    val calculateSignalLevelInputState: CalculateSignalLevelInputState,
    val compareSignalLevelInputState: CompareSignalLevelInputState
)

internal data class CalculateSignalLevelInputState(
    val rssiLevelInput: String,
    val validityState: SignalInputValidityState.CalculateSignalLevel
)

internal data class CompareSignalLevelInputState(
    val rssi1InputState: CompareSignalLevelRSSIInputState,
    val rssi2InputState: CompareSignalLevelRSSIInputState
)

internal data class CompareSignalLevelRSSIInputState(
    val rssiLevelInput: String,
    val validityState: SignalInputValidityState.CompareSignalLevel
)

internal sealed class SignalInputValidityState {

    sealed class CalculateSignalLevel : SignalInputValidityState() {
        object Valid : CalculateSignalLevel()

        sealed class Invalid : CalculateSignalLevel() {
            object Empty : Invalid()
            object NotAnInt : Invalid()
        }
    }

    sealed class CompareSignalLevel : SignalInputValidityState() {
        object Valid : CompareSignalLevel()

        sealed class Invalid : CompareSignalLevel() {
            object Empty : Invalid()
            object NotAnInt : Invalid()
        }
    }
}
