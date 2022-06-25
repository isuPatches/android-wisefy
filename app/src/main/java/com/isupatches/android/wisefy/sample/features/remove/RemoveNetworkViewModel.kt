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
import android.util.Log
import androidx.annotation.RequiresPermission
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.isupatches.android.wisefy.WisefyApi
import com.isupatches.android.wisefy.removenetwork.callbacks.RemoveNetworkCallbacks
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkRequest
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkResult
import com.isupatches.android.wisefy.sample.scaffolding.BaseViewModel
import com.isupatches.android.wisefy.sample.scaffolding.BaseViewModelFactory
import com.isupatches.android.wisefy.sample.util.SSIDInputError
import com.isupatches.android.wisefy.sample.util.validateSSID

internal abstract class RemoveNetworkViewModel : BaseViewModel() {
    abstract val uiState: State<RemoveNetworkUIState>
    abstract val inputState: State<RemoveNetworkInputState>

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    abstract fun removeNetwork()

    abstract fun onInputChanged(networkToRemove: String)
    abstract fun onDialogClosed()
    abstract fun onRemoveNetworkPermissionsError()
}

internal class DefaultRemoveNetworkViewModel(
    private val wisefy: WisefyApi
) : RemoveNetworkViewModel() {

    private val _uiState = mutableStateOf(
        RemoveNetworkUIState(
            loadingState = RemoveNetworkLoadingState(isLoading = false),
            dialogState = RemoveNetworkDialogState.None
        )
    )
    override val uiState: State<RemoveNetworkUIState>
        get() = _uiState

    private val _inputState: MutableState<RemoveNetworkInputState> = mutableStateOf(
        RemoveNetworkInputState.Invalid.Empty
    )
    override val inputState: State<RemoveNetworkInputState>
        get() = _inputState

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun removeNetwork() {
        val currentInputState = inputState.value
        if (currentInputState !is RemoveNetworkInputState.Valid) {
            _uiState.value = RemoveNetworkUIState(
                loadingState = RemoveNetworkLoadingState(isLoading = false),
                dialogState = RemoveNetworkDialogState.InputError
            )
            return
        }
        _uiState.value = RemoveNetworkUIState(
            loadingState = RemoveNetworkLoadingState(isLoading = true),
            dialogState = RemoveNetworkDialogState.None
        )
        Log.d("TEST", "BOOM==  wisefy.removeNetwork")
        wisefy.removeNetwork(
            request = RemoveNetworkRequest.SSID(regex = currentInputState.value),
            callbacks = object : RemoveNetworkCallbacks {
                override fun onNetworkRemoved(result: RemoveNetworkResult.Success) {
                    Log.d("TEST", "BOOM==  onNetworkRemoved; result: $result")
                    _uiState.value = RemoveNetworkUIState(
                        loadingState = RemoveNetworkLoadingState(isLoading = false),
                        dialogState = RemoveNetworkDialogState.Success(result)
                    )
                }

                override fun onNetworkNotFoundToRemove() {
                    Log.d("TEST", "BOOM==  onNetworkNotFoundToRemove;")
                    _uiState.value = RemoveNetworkUIState(
                        loadingState = RemoveNetworkLoadingState(isLoading = false),
                        dialogState = RemoveNetworkDialogState.Failure.NotFoundToRemove
                    )
                }

                override fun onFailureRemovingNetwork(result: RemoveNetworkResult.Failure) {
                    Log.d("TEST", "BOOM==  onFailureRemovingNetwork; result: $result")
                    _uiState.value = RemoveNetworkUIState(
                        loadingState = RemoveNetworkLoadingState(isLoading = false),
                        dialogState = RemoveNetworkDialogState.Failure.UnableToRemove(result)
                    )
                }

                override fun onWisefyAsyncFailure(throwable: Throwable) {
                    Log.d("TEST", "BOOM==  onWisefyAsyncFailure; throwable: $throwable")
                    _uiState.value = RemoveNetworkUIState(
                        loadingState = RemoveNetworkLoadingState(isLoading = false),
                        dialogState = RemoveNetworkDialogState.Failure.WisefyAsync(throwable)
                    )
                }
            }
        )
    }

    override fun onInputChanged(networkToRemove: String) {
        _inputState.value = when (networkToRemove.validateSSID()) {
            SSIDInputError.EMPTY -> RemoveNetworkInputState.Invalid.Empty
            SSIDInputError.TOO_SHORT -> RemoveNetworkInputState.Invalid.TooShort
            SSIDInputError.TOO_LONG -> RemoveNetworkInputState.Invalid.TooLong
            SSIDInputError.INVALID_CHARACTERS -> RemoveNetworkInputState.Invalid.InvalidCharacters
            SSIDInputError.INVALID_START_CHARACTERS -> RemoveNetworkInputState.Invalid.InvalidStartCharacters
            SSIDInputError.LEADING_OR_TRAILING_SPACES -> RemoveNetworkInputState.Invalid.LeadingOrTrailingSpaces
            SSIDInputError.NOT_VALID_UNICODE -> RemoveNetworkInputState.Invalid.InvalidUnicode
            SSIDInputError.NONE -> RemoveNetworkInputState.Valid(networkToRemove)
        }
    }

    override fun onDialogClosed() {
        _uiState.value = RemoveNetworkUIState(
            loadingState = RemoveNetworkLoadingState(isLoading = false),
            dialogState = RemoveNetworkDialogState.None
        )
    }

    override fun onRemoveNetworkPermissionsError() {
        _uiState.value = RemoveNetworkUIState(
            loadingState = RemoveNetworkLoadingState(isLoading = false),
            dialogState = RemoveNetworkDialogState.PermissionsError
        )
    }
}

internal class RemoveNetworkViewModelFactory(
    private val wisefy: WisefyApi
) : BaseViewModelFactory<RemoveNetworkViewModel>(
    supportedClass = RemoveNetworkViewModel::class,
    vmProvider = { DefaultRemoveNetworkViewModel(wisefy) }
)
