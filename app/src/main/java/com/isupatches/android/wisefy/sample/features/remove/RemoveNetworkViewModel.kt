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

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.Manifest.permission.CHANGE_WIFI_STATE
import android.content.Context
import androidx.annotation.RequiresPermission
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.isupatches.android.wisefy.WisefyApi
import com.isupatches.android.wisefy.core.exceptions.WisefyException
import com.isupatches.android.wisefy.ktx.removeNetworkAsync
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkRequest
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkResult
import com.isupatches.android.wisefy.sample.entities.SSIDType
import com.isupatches.android.wisefy.sample.scaffolding.BaseViewModel
import com.isupatches.android.wisefy.sample.scaffolding.BaseViewModelFactory
import com.isupatches.android.wisefy.sample.util.BSSIDInputError
import com.isupatches.android.wisefy.sample.util.SSIDInputError
import com.isupatches.android.wisefy.sample.util.validateBSSID
import com.isupatches.android.wisefy.sample.util.validateSSID
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

internal abstract class RemoveNetworkViewModel : BaseViewModel() {
    abstract val uiState: State<RemoveNetworkUIState>

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE, CHANGE_WIFI_STATE])
    abstract suspend fun removeNetwork()

    abstract fun onInputChanged(input: String)
    abstract fun onSSIDTypeChanged(ssidType: SSIDType)

    abstract fun onRemoveNetworkPermissionsError()

    abstract fun onDialogClosed()
}

internal class DefaultRemoveNetworkViewModel(
    context: Context,
    private val wisefy: WisefyApi,
    private val removeNetworkStore: RemoveNetworkStore = DefaultRemoveNetworkStore(context = context)
) : RemoveNetworkViewModel() {

    private val _uiState = mutableStateOf(
        RemoveNetworkUIState(
            loadingState = RemoveNetworkLoadingState(isLoading = false),
            dialogState = RemoveNetworkDialogState.None,
            inputState = RemoveNetworkInputState(
                networkInput = "",
                networkInputValidityState = RemoveNetworkInputValidityState.SSID.Invalid.Empty
            ),
            ssidType = SSIDType.SSID
        )
    )
    override val uiState: State<RemoveNetworkUIState>
        get() = _uiState

    init {
        viewModelScope.launch {
            removeNetworkStore.getLastUsedNetworkInput()
                .collectLatest { input ->
                    validateInput(uiState.value.ssidType, input)
                }
        }

        viewModelScope.launch {
            removeNetworkStore.getSSIDType()
                .collectLatest { ssidType ->
                    _uiState.value = uiState.value.copy(ssidType = ssidType)
                    validateInput(ssidType, uiState.value.inputState.networkInput)
                }
        }
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE, CHANGE_WIFI_STATE])
    override suspend fun removeNetwork() {
        val currentInputState = uiState.value.inputState
        when (uiState.value.ssidType) {
            SSIDType.SSID -> {
                if (currentInputState.networkInputValidityState != RemoveNetworkInputValidityState.SSID.Valid) {
                    _uiState.value = uiState.value.copy(
                        loadingState = RemoveNetworkLoadingState(isLoading = false),
                        dialogState = RemoveNetworkDialogState.InputError.SSID
                    )
                    return
                }
            }
            SSIDType.BSSID -> {
                if (currentInputState.networkInputValidityState != RemoveNetworkInputValidityState.BSSID.Valid) {
                    _uiState.value = uiState.value.copy(
                        loadingState = RemoveNetworkLoadingState(isLoading = false),
                        dialogState = RemoveNetworkDialogState.InputError.BSSID
                    )
                    return
                }
            }
        }
        _uiState.value = uiState.value.copy(
            loadingState = RemoveNetworkLoadingState(isLoading = true),
            dialogState = RemoveNetworkDialogState.None
        )
        val request = when (uiState.value.ssidType) {
            SSIDType.SSID -> RemoveNetworkRequest.SSID(ssid = currentInputState.networkInput)
            SSIDType.BSSID -> RemoveNetworkRequest.BSSID(bssid = currentInputState.networkInput)
        }
        val result = try {
            wisefy.removeNetworkAsync(request = request)
        } catch (ex: WisefyException) {
            _uiState.value = uiState.value.copy(
                loadingState = RemoveNetworkLoadingState(isLoading = false),
                dialogState = RemoveNetworkDialogState.Failure.WisefyAsync(exception = ex)
            )
            null
        }

        when (result) {
            is RemoveNetworkResult.Success -> {
                _uiState.value = uiState.value.copy(
                    loadingState = RemoveNetworkLoadingState(isLoading = false),
                    dialogState = RemoveNetworkDialogState.RemoveNetwork.Success(result)
                )
            }
            is RemoveNetworkResult.Failure -> {
                _uiState.value = uiState.value.copy(
                    loadingState = RemoveNetworkLoadingState(isLoading = false),
                    dialogState = RemoveNetworkDialogState.RemoveNetwork.Failure(result)
                )
            }
            null -> {
                // Case handled above in the catch clause
            }
        }
    }

    override fun onInputChanged(input: String) {
        viewModelScope.launch {
            removeNetworkStore.setLastUsedNetworkInput(input)
        }
    }

    override fun onSSIDTypeChanged(ssidType: SSIDType) {
        viewModelScope.launch {
            removeNetworkStore.setSSIDType(ssidType)
        }
    }

    override fun onDialogClosed() {
        _uiState.value = uiState.value.copy(
            loadingState = RemoveNetworkLoadingState(isLoading = false),
            dialogState = RemoveNetworkDialogState.None
        )
    }

    override fun onRemoveNetworkPermissionsError() {
        _uiState.value = uiState.value.copy(
            loadingState = RemoveNetworkLoadingState(isLoading = false),
            dialogState = RemoveNetworkDialogState.RemoveNetwork.PermissionsError
        )
    }

    private fun validateInput(ssidType: SSIDType, input: String) {
        val validityState = when (ssidType) {
            SSIDType.SSID -> {
                when (input.validateSSID()) {
                    SSIDInputError.EMPTY -> RemoveNetworkInputValidityState.SSID.Invalid.Empty
                    SSIDInputError.TOO_SHORT -> RemoveNetworkInputValidityState.SSID.Invalid.TooShort
                    SSIDInputError.TOO_LONG -> RemoveNetworkInputValidityState.SSID.Invalid.TooLong
                    SSIDInputError.INVALID_CHARACTERS -> {
                        RemoveNetworkInputValidityState.SSID.Invalid.InvalidCharacters
                    }
                    SSIDInputError.INVALID_START_CHARACTERS -> {
                        RemoveNetworkInputValidityState.SSID.Invalid.InvalidStartCharacters
                    }
                    SSIDInputError.LEADING_OR_TRAILING_SPACES -> {
                        RemoveNetworkInputValidityState.SSID.Invalid.LeadingOrTrailingSpaces
                    }
                    SSIDInputError.NOT_VALID_UNICODE -> {
                        RemoveNetworkInputValidityState.SSID.Invalid.InvalidUnicode
                    }
                    SSIDInputError.NONE -> RemoveNetworkInputValidityState.SSID.Valid
                }
            }
            SSIDType.BSSID -> {
                when (input.validateBSSID()) {
                    BSSIDInputError.EMPTY -> RemoveNetworkInputValidityState.BSSID.Invalid.Empty
                    BSSIDInputError.INVALID -> RemoveNetworkInputValidityState.BSSID.Invalid.ImproperFormat
                    BSSIDInputError.NONE -> RemoveNetworkInputValidityState.BSSID.Valid
                }
            }
        }
        _uiState.value = uiState.value.copy(
            inputState = RemoveNetworkInputState(
                networkInput = input,
                networkInputValidityState = validityState
            )
        )
    }
}

internal class RemoveNetworkViewModelFactory(
    private val context: Context,
    private val wisefy: WisefyApi
) : BaseViewModelFactory<RemoveNetworkViewModel>(
    supportedClass = RemoveNetworkViewModel::class,
    vmProvider = { DefaultRemoveNetworkViewModel(context, wisefy) }
)
