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

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.isupatches.android.wisefy.WisefyApi
import com.isupatches.android.wisefy.core.exceptions.WisefyException
import com.isupatches.android.wisefy.sample.scaffolding.BaseViewModel
import com.isupatches.android.wisefy.sample.scaffolding.BaseViewModelFactory
import com.isupatches.android.wisefy.sample.util.SdkUtil
import com.isupatches.android.wisefy.signal.entities.CalculateSignalLevelRequest
import com.isupatches.android.wisefy.signal.entities.CalculateSignalLevelResult
import com.isupatches.android.wisefy.signal.entities.CompareSignalLevelRequest
import com.isupatches.android.wisefy.signal.entities.CompareSignalLevelResult
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

internal abstract class SignalViewModel : BaseViewModel() {
    abstract val uiState: State<SignalScreenUIState>

    abstract fun calculateSignalLevel()

    abstract fun compareSignalLevel()

    abstract fun onCalculateSignalLevelInputChanged(input: String)
    abstract fun onCompareSignalLevelRSSI1InputChanged(input: String)
    abstract fun onCompareSignalLevelRSSI2InputChanged(input: String)

    abstract fun onDialogClosed()
}

private const val TARGET_NUMBER_OF_SIGNAL_LEVELS = 4

internal class DefaultSignalViewModel(
    context: Context,
    private val wisefy: WisefyApi,
    private val sdkUtil: SdkUtil,
    private val signalStore: SignalStore = DefaultSignalStore(context = context)
) : SignalViewModel() {

    private val _uiState = mutableStateOf(
        SignalScreenUIState(
            loadingState = SignalScreenLoadingState(isLoading = false),
            dialogState = SignalScreenDialogState.None,
            inputState = SignalScreenInputState(
                calculateSignalLevelInputState = SignalScreenCalculateSignalLevelInputState(
                    rssiLevelInput = "",
                    validityState = SignalScreenInputValidityState.CalculateSignalLevel.Invalid.Empty
                ),
                compareSignalLevelInputState = SignalScreenCompareSignalLevelInputState(
                    rssi1InputState = SignalScreenCompareSignalLevelRSSIInputState(
                        rssiLevelInput = "",
                        validityState = SignalScreenInputValidityState.CompareSignalLevel.Invalid.Empty
                    ),
                    rssi2InputState = SignalScreenCompareSignalLevelRSSIInputState(
                        rssiLevelInput = "",
                        validityState = SignalScreenInputValidityState.CompareSignalLevel.Invalid.Empty
                    )
                )
            )
        )
    )
    override val uiState: State<SignalScreenUIState>
        get() = _uiState

    init {
        viewModelScope.launch {
            signalStore.getCalculateSignalLevelRSSI()
                .collectLatest { rssiLevel ->
                    validateCalculateSignalLevelInputValidity(rssiLevel)
                }
        }

        viewModelScope.launch {
            signalStore.getCompareSignalLevelRSSI1()
                .collectLatest { rssi1 ->
                    validateCompareSignalLevelRSSI1InputValidity(rssi1)
                }
        }

        viewModelScope.launch {
            signalStore.getCompareSignalLevelRSSI2()
                .collectLatest { rssi2 ->
                    validateCompareSignalLevelRSSI2InputValidity(rssi2)
                }
        }
    }

    override fun calculateSignalLevel() {
        _uiState.value = uiState.value.copy(
            loadingState = SignalScreenLoadingState(isLoading = true),
            dialogState = SignalScreenDialogState.None
        )

        if (!isCalculateSignalLevelInputValid()) {
            return
        }

        val rssiLevel = uiState.value.inputState.calculateSignalLevelInputState.rssiLevelInput.toInt()
        val request = if (sdkUtil.isAtLeastR()) {
            CalculateSignalLevelRequest.Android30AndAbove(rssiLevel = rssiLevel)
        } else {
            CalculateSignalLevelRequest.BelowAndroid30(
                rssiLevel = rssiLevel,
                numLevels = TARGET_NUMBER_OF_SIGNAL_LEVELS
            )
        }
        val result = try {
            wisefy.calculateSignalLevel(request)
        } catch (ex: WisefyException) {
            _uiState.value = uiState.value.copy(
                loadingState = SignalScreenLoadingState(isLoading = false),
                dialogState = SignalScreenDialogState.Failure.WisefyAsync(exception = ex)
            )
            null
        }

        when (result) {
            is CalculateSignalLevelResult.Failure -> {
                _uiState.value = uiState.value.copy(
                    loadingState = SignalScreenLoadingState(isLoading = false),
                    dialogState = SignalScreenDialogState.CalculateSignalLevel.Failure(result)
                )
            }
            is CalculateSignalLevelResult.Success -> {
                _uiState.value = uiState.value.copy(
                    loadingState = SignalScreenLoadingState(isLoading = false),
                    dialogState = SignalScreenDialogState.CalculateSignalLevel.Success(result)
                )
            }
            null -> {
                // Case handled above in the catch clause
            }
        }
    }

    override fun compareSignalLevel() {
        _uiState.value = uiState.value.copy(
            loadingState = SignalScreenLoadingState(isLoading = true),
            dialogState = SignalScreenDialogState.None
        )

        if (!isCompareSignalLevelInputValid()) {
            return
        }

        val rssi1 = uiState.value.inputState.compareSignalLevelInputState.rssi1InputState.rssiLevelInput.toInt()
        val rssi2 = uiState.value.inputState.compareSignalLevelInputState.rssi2InputState.rssiLevelInput.toInt()
        val request = CompareSignalLevelRequest(rssi1 = rssi1, rssi2 = rssi2)
        val result = try {
            wisefy.compareSignalLevel(request)
        } catch (ex: WisefyException) {
            _uiState.value = uiState.value.copy(
                loadingState = SignalScreenLoadingState(isLoading = false),
                dialogState = SignalScreenDialogState.Failure.WisefyAsync(exception = ex)
            )
            null
        }

        when (result) {
            is CompareSignalLevelResult.Success -> {
                _uiState.value = uiState.value.copy(
                    loadingState = SignalScreenLoadingState(isLoading = false),
                    dialogState = SignalScreenDialogState.CompareSignalLevel.Success(result)
                )
            }
            null -> {
                // Case handled above in the catch clause
            }
        }
    }

    override fun onCalculateSignalLevelInputChanged(input: String) {
        viewModelScope.launch {
            signalStore.setCalculateSignalLevelRSSI(input)
        }
    }

    override fun onCompareSignalLevelRSSI1InputChanged(input: String) {
        viewModelScope.launch {
            signalStore.setCompareSignalLevelRSSI1(input)
        }
    }

    override fun onCompareSignalLevelRSSI2InputChanged(input: String) {
        viewModelScope.launch {
            signalStore.setCompareSignalLevelRSSI2(input)
        }
    }

    override fun onDialogClosed() {
        _uiState.value = uiState.value.copy(
            loadingState = SignalScreenLoadingState(isLoading = false),
            dialogState = SignalScreenDialogState.None
        )
    }

    private fun isCalculateSignalLevelInputValid(): Boolean {
        if (uiState.value.inputState.calculateSignalLevelInputState.validityState !is
            SignalScreenInputValidityState.CalculateSignalLevel.Valid
        ) {
            _uiState.value = uiState.value.copy(
                loadingState = SignalScreenLoadingState(isLoading = false),
                dialogState = SignalScreenDialogState.InputError.CalculateSignalLevel
            )
            return false
        }
        return true
    }

    private fun isCompareSignalLevelInputValid(): Boolean {
        if (uiState.value.inputState.compareSignalLevelInputState.rssi1InputState.validityState !is
            SignalScreenInputValidityState.CompareSignalLevel.Valid
        ) {
            _uiState.value = uiState.value.copy(
                loadingState = SignalScreenLoadingState(isLoading = false),
                dialogState = SignalScreenDialogState.InputError.CompareSignalLevel.RSSI1
            )
            return false
        }
        if (uiState.value.inputState.compareSignalLevelInputState.rssi2InputState.validityState !is
            SignalScreenInputValidityState.CompareSignalLevel.Valid
        ) {
            _uiState.value = uiState.value.copy(
                loadingState = SignalScreenLoadingState(isLoading = false),
                dialogState = SignalScreenDialogState.InputError.CompareSignalLevel.RSSI2
            )
            return false
        }
        return true
    }

    private fun validateCalculateSignalLevelInputValidity(rssiLevel: String) {
        val validityState = when {
            rssiLevel.isBlank() -> SignalScreenInputValidityState.CalculateSignalLevel.Invalid.Empty
            rssiLevel.toIntOrNull() == null -> SignalScreenInputValidityState.CalculateSignalLevel.Invalid.NotAnInt
            else -> SignalScreenInputValidityState.CalculateSignalLevel.Valid
        }
        _uiState.value = uiState.value.copy(
            inputState = uiState.value.inputState.copy(
                calculateSignalLevelInputState = SignalScreenCalculateSignalLevelInputState(
                    rssiLevelInput = rssiLevel,
                    validityState = validityState
                )
            )
        )
    }

    private fun validateCompareSignalLevelRSSI1InputValidity(rssi1: String) {
        val validityState = when {
            rssi1.isBlank() -> SignalScreenInputValidityState.CompareSignalLevel.Invalid.Empty
            rssi1.toIntOrNull() == null -> SignalScreenInputValidityState.CompareSignalLevel.Invalid.NotAnInt
            else -> SignalScreenInputValidityState.CompareSignalLevel.Valid
        }
        _uiState.value = uiState.value.copy(
            inputState = uiState.value.inputState.copy(
                compareSignalLevelInputState = uiState.value.inputState.compareSignalLevelInputState.copy(
                    rssi1InputState = SignalScreenCompareSignalLevelRSSIInputState(
                        rssiLevelInput = rssi1,
                        validityState = validityState
                    )
                )
            )
        )
    }

    private fun validateCompareSignalLevelRSSI2InputValidity(rssi2: String) {
        val validityState = when {
            rssi2.isBlank() -> SignalScreenInputValidityState.CompareSignalLevel.Invalid.Empty
            rssi2.toIntOrNull() == null -> SignalScreenInputValidityState.CompareSignalLevel.Invalid.NotAnInt
            else -> SignalScreenInputValidityState.CompareSignalLevel.Valid
        }
        _uiState.value = uiState.value.copy(
            inputState = uiState.value.inputState.copy(
                compareSignalLevelInputState = uiState.value.inputState.compareSignalLevelInputState.copy(
                    rssi2InputState = SignalScreenCompareSignalLevelRSSIInputState(
                        rssiLevelInput = rssi2,
                        validityState = validityState
                    )
                )
            )
        )
    }
}

internal class SignalViewModelFactory(
    context: Context,
    sdkUtil: SdkUtil,
    private val wisefy: WisefyApi
) : BaseViewModelFactory<SignalViewModel>(
    supportedClass = SignalViewModel::class,
    vmProvider = { DefaultSignalViewModel(context, wisefy, sdkUtil) }
)
