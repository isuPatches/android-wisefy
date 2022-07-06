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

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.CHANGE_WIFI_STATE
import androidx.annotation.RequiresPermission
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.isupatches.android.wisefy.WisefyApi
import com.isupatches.android.wisefy.removenetwork.callbacks.RemoveNetworkCallbacks
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkRequest
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkResult
import com.isupatches.android.wisefy.sample.entities.SSIDType
import com.isupatches.android.wisefy.sample.scaffolding.BaseViewModel
import com.isupatches.android.wisefy.sample.scaffolding.BaseViewModelFactory
import com.isupatches.android.wisefy.sample.util.BSSIDInputError
import com.isupatches.android.wisefy.sample.util.SSIDInputError
import com.isupatches.android.wisefy.sample.util.validateBSSID
import com.isupatches.android.wisefy.sample.util.validateSSID

internal abstract class RemoveNetworkViewModel : BaseViewModel() {
    abstract val uiState: State<RemoveNetworkUIState>

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    abstract fun removeNetwork()

    abstract fun onInputChanged(networkToRemove: String)
    abstract fun onSSIDTypeChanged(ssidType: SSIDType)

    abstract fun onRemoveNetworkPermissionsError()

    abstract fun onDialogClosed()
}

internal class DefaultRemoveNetworkViewModel(
    private val wisefy: WisefyApi
) : RemoveNetworkViewModel() {

    private val _uiState = mutableStateOf(
        RemoveNetworkUIState(
            loadingState = RemoveNetworkLoadingState(isLoading = false),
            dialogState = RemoveNetworkDialogState.None,
            inputState = RemoveNetworkInputState.SSID.Invalid.Empty,
            ssidType = SSIDType.SSID
        )
    )
    override val uiState: State<RemoveNetworkUIState>
        get() = _uiState

    private var networkToRemove: String? = null

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun removeNetwork() {
        val currentInputState = uiState.value.inputState
        if (!isInputValid()) {
            _uiState.value = uiState.value.copy(
                loadingState = RemoveNetworkLoadingState(isLoading = false),
                dialogState = RemoveNetworkDialogState.Failure.InputError
            )
            return
        }
        _uiState.value = uiState.value.copy(
            loadingState = RemoveNetworkLoadingState(isLoading = true),
            dialogState = RemoveNetworkDialogState.None
        )
        wisefy.removeNetwork(
            request = when (uiState.value.ssidType) {
                SSIDType.SSID -> {
                    if (currentInputState is RemoveNetworkInputState.SSID.Valid) {
                        RemoveNetworkRequest.SSID(regex = currentInputState.value)
                    } else {
                        error("")
                    }
                }
                SSIDType.BSSID -> {
                    if (currentInputState is RemoveNetworkInputState.BSSID.Valid) {
                        RemoveNetworkRequest.BSSID(regex = currentInputState.value)
                    } else {
                        error("")
                    }
                }
            },
            callbacks = object : RemoveNetworkCallbacks {
                override fun onNetworkRemoved(result: RemoveNetworkResult.Success) {
                    _uiState.value = uiState.value.copy(
                        loadingState = RemoveNetworkLoadingState(isLoading = false),
                        dialogState = RemoveNetworkDialogState.RemoveNetwork.Success(result)
                    )
                }

                override fun onNetworkNotFoundToRemove() {
                    _uiState.value = uiState.value.copy(
                        loadingState = RemoveNetworkLoadingState(isLoading = false),
                        dialogState = RemoveNetworkDialogState.RemoveNetwork.Failure.NotFoundToRemove
                    )
                }

                override fun onFailureRemovingNetwork(result: RemoveNetworkResult.Failure) {
                    _uiState.value = uiState.value.copy(
                        loadingState = RemoveNetworkLoadingState(isLoading = false),
                        dialogState = RemoveNetworkDialogState.RemoveNetwork.Failure.UnableToRemove(result)
                    )
                }

                override fun onWisefyAsyncFailure(throwable: Throwable) {
                    _uiState.value = uiState.value.copy(
                        loadingState = RemoveNetworkLoadingState(isLoading = false),
                        dialogState = RemoveNetworkDialogState.Failure.WisefyAsync(throwable)
                    )
                }
            }
        )
    }

    override fun onInputChanged(networkToRemove: String) {
        this.networkToRemove = networkToRemove
        val inputState = when (uiState.value.ssidType) {
            SSIDType.SSID -> {
                when (networkToRemove.validateSSID()) {
                    SSIDInputError.EMPTY -> RemoveNetworkInputState.SSID.Invalid.Empty
                    SSIDInputError.TOO_SHORT -> RemoveNetworkInputState.SSID.Invalid.TooShort
                    SSIDInputError.TOO_LONG -> RemoveNetworkInputState.SSID.Invalid.TooLong
                    SSIDInputError.INVALID_CHARACTERS -> RemoveNetworkInputState.SSID.Invalid.InvalidCharacters
                    SSIDInputError.INVALID_START_CHARACTERS -> {
                        RemoveNetworkInputState.SSID.Invalid.InvalidStartCharacters
                    }
                    SSIDInputError.LEADING_OR_TRAILING_SPACES -> {
                        RemoveNetworkInputState.SSID.Invalid.LeadingOrTrailingSpaces
                    }
                    SSIDInputError.NOT_VALID_UNICODE -> RemoveNetworkInputState.SSID.Invalid.InvalidUnicode
                    SSIDInputError.NONE -> RemoveNetworkInputState.SSID.Valid(networkToRemove)
                }
            }
            SSIDType.BSSID -> {
                when (networkToRemove.validateBSSID()) {
                    BSSIDInputError.EMPTY -> RemoveNetworkInputState.BSSID.Invalid.Empty
                    BSSIDInputError.INVALID -> RemoveNetworkInputState.BSSID.Invalid.ImproperFormat
                    BSSIDInputError.NONE -> RemoveNetworkInputState.BSSID.Valid(networkToRemove)
                }
            }
        }
        _uiState.value = uiState.value.copy(inputState = inputState)
    }

    override fun onSSIDTypeChanged(ssidType: SSIDType) {
        _uiState.value = uiState.value.copy(ssidType = ssidType)
        networkToRemove?.let {
            onInputChanged(it)
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

    private fun isInputValid(): Boolean {
        val currentInputState = uiState.value.inputState
        return currentInputState is RemoveNetworkInputState.SSID.Valid ||
            currentInputState is RemoveNetworkInputState.BSSID.Valid
    }
}

internal class RemoveNetworkViewModelFactory(
    private val wisefy: WisefyApi
) : BaseViewModelFactory<RemoveNetworkViewModel>(
    supportedClass = RemoveNetworkViewModel::class,
    vmProvider = { DefaultRemoveNetworkViewModel(wisefy) }
)
