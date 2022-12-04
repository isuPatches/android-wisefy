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
package com.isupatches.android.wisefy.sample.features.misc.signal

import com.isupatches.android.wisefy.core.exceptions.WisefyException
import com.isupatches.android.wisefy.signal.entities.CalculateSignalLevelResult
import com.isupatches.android.wisefy.signal.entities.CompareSignalLevelResult

internal data class SignalScreenUIState(
    val loadingState: SignalScreenLoadingState,
    val dialogState: SignalScreenDialogState,
    val inputState: SignalScreenInputState
)

internal data class SignalScreenLoadingState(
    val isLoading: Boolean = false
)

internal sealed class SignalScreenDialogState {
    object None : SignalScreenDialogState()

    sealed class InputError : SignalScreenDialogState() {

        object CalculateSignalLevel : InputError()

        sealed class CompareSignalLevel : InputError() {
            object RSSI1 : CompareSignalLevel()
            object RSSI2 : CompareSignalLevel()
        }
    }

    sealed class Failure : SignalScreenDialogState() {
        data class WisefyAsync(val exception: WisefyException) : Failure()
    }

    sealed class CalculateSignalLevel : SignalScreenDialogState() {
        data class Success(val result: CalculateSignalLevelResult.Success) : CalculateSignalLevel()
        data class Failure(val result: CalculateSignalLevelResult.Failure) : CalculateSignalLevel()
    }

    sealed class CompareSignalLevel : SignalScreenDialogState() {
        data class Success(val result: CompareSignalLevelResult.Success) : CalculateSignalLevel()
    }
}

internal data class SignalScreenInputState(
    val calculateSignalLevelInputState: SignalScreenCalculateSignalLevelInputState,
    val compareSignalLevelInputState: SignalScreenCompareSignalLevelInputState
)

internal data class SignalScreenCalculateSignalLevelInputState(
    val rssiLevelInput: String,
    val validityState: SignalScreenInputValidityState.CalculateSignalLevel
)

internal data class SignalScreenCompareSignalLevelInputState(
    val rssi1InputState: SignalScreenCompareSignalLevelRSSIInputState,
    val rssi2InputState: SignalScreenCompareSignalLevelRSSIInputState
)

internal data class SignalScreenCompareSignalLevelRSSIInputState(
    val rssiLevelInput: String,
    val validityState: SignalScreenInputValidityState.CompareSignalLevel
)

internal sealed class SignalScreenInputValidityState {

    sealed class CalculateSignalLevel : SignalScreenInputValidityState() {
        object Valid : CalculateSignalLevel()

        sealed class Invalid : CalculateSignalLevel() {
            object Empty : Invalid()
            object NotAnInt : Invalid()
        }
    }

    sealed class CompareSignalLevel : SignalScreenInputValidityState() {
        object Valid : CompareSignalLevel()

        sealed class Invalid : CompareSignalLevel() {
            object Empty : Invalid()
            object NotAnInt : Invalid()
        }
    }
}
