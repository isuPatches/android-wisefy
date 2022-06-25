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

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.CHANGE_WIFI_STATE
import android.content.Intent
import android.os.Build
import androidx.activity.result.ActivityResultLauncher
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.isupatches.android.wisefy.WisefyApi
import com.isupatches.android.wisefy.addnetwork.callbacks.AddNetworkCallbacks
import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkResult
import com.isupatches.android.wisefy.addnetwork.entities.AddOpenNetworkRequest
import com.isupatches.android.wisefy.addnetwork.entities.AddWPA2NetworkRequest
import com.isupatches.android.wisefy.addnetwork.entities.AddWPA3NetworkRequest
import com.isupatches.android.wisefy.sample.entities.NetworkType
import com.isupatches.android.wisefy.sample.scaffolding.BaseViewModel
import com.isupatches.android.wisefy.sample.scaffolding.BaseViewModelFactory
import com.isupatches.android.wisefy.sample.util.PassphraseInputError
import com.isupatches.android.wisefy.sample.util.SSIDInputError
import com.isupatches.android.wisefy.sample.util.SdkUtil
import com.isupatches.android.wisefy.sample.util.validatePassphrase
import com.isupatches.android.wisefy.sample.util.validateSSID

internal abstract class AddNetworkViewModel : BaseViewModel() {
    abstract val uiState: State<AddNetworkUIState>
    abstract val networkType: State<NetworkType>
    abstract val inputState: State<AddNetworkInputState>

    abstract val isAtLeastAndroidQ: Boolean

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    abstract fun addNetwork()

    @RequiresApi(Build.VERSION_CODES.R)
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    abstract fun addNetwork(launcher: ActivityResultLauncher<Intent>)

    abstract fun onDialogClosed()

    abstract fun onSSIDInputChanged(input: String)
    abstract fun onPassphraseInputChanged(input: String)

    abstract fun onOpenNetworkTypeSelected()
    abstract fun onWPA2NetworkTypeSelected()
    abstract fun onWPA3NetworkTypeSelected()

    abstract fun onAddNetworkPermissionsError()

    abstract fun onAddNetworkFailure(resultCode: Int)
    abstract fun onAddNetworkSuccess(resultCode: Int)
}

internal class DefaultAddNetworkViewModel(
    private val wisefy: WisefyApi,
    private val sdkUtil: SdkUtil
) : AddNetworkViewModel() {

    private val _uiState = mutableStateOf(
        AddNetworkUIState(
            loadingState = AddNetworkLoadingState(isLoading = false),
            dialogState = AddNetworkDialogState.None,
        )
    )
    override val uiState: State<AddNetworkUIState>
        get() = _uiState

    private val _networkType = mutableStateOf(NetworkType.OPEN)
    override val networkType: State<NetworkType>
        get() = _networkType

    private val _inputState: MutableState<AddNetworkInputState> = mutableStateOf(
        AddNetworkInputState(
            ssidState = AddNetworkInputSSIDState.Invalid.Empty,
            passphraseState = AddNetworkPassphraseState.Invalid.Empty
        )
    )

    private val addNetworkCallbacks = object : AddNetworkCallbacks {
        override fun onNetworkAdded(result: AddNetworkResult.Success) {
            _uiState.value = AddNetworkUIState(
                loadingState = AddNetworkLoadingState(false),
                dialogState = AddNetworkDialogState.Success(result)
            )
        }

        override fun onFailureAddingNetwork(result: AddNetworkResult.Failure) {
            _uiState.value = AddNetworkUIState(
                loadingState = AddNetworkLoadingState(false),
                dialogState = AddNetworkDialogState.Failure.UnableToAdd(result)
            )
        }

        override fun onWisefyAsyncFailure(throwable: Throwable) {
            _uiState.value = AddNetworkUIState(
                loadingState = AddNetworkLoadingState(false),
                dialogState = AddNetworkDialogState.Failure.WisefyAsync(throwable)
            )
        }
    }

    override val inputState: State<AddNetworkInputState>
        get() = _inputState

    override val isAtLeastAndroidQ: Boolean
        get() = sdkUtil.isAtLeastQ()

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addNetwork() {
        val ssidInput = _inputState.value.ssidState
        if (ssidInput !is AddNetworkInputSSIDState.Valid) {
            _uiState.value = AddNetworkUIState(
                loadingState = AddNetworkLoadingState(isLoading = false),
                dialogState = AddNetworkDialogState.InputError.SSID
            )
            return
        }
        when (networkType.value) {
            NetworkType.OPEN -> {
                _uiState.value = AddNetworkUIState(
                    loadingState = AddNetworkLoadingState(isLoading = true),
                    dialogState = AddNetworkDialogState.None
                )
                wisefy.addOpenNetwork(
                    request = AddOpenNetworkRequest.Default(ssid = ssidInput.value),
                    callbacks = addNetworkCallbacks
                )
            }
            NetworkType.WPA2 -> {
                val passphraseInput = _inputState.value.passphraseState
                if (passphraseInput !is AddNetworkPassphraseState.Valid) {
                    _uiState.value = AddNetworkUIState(
                        loadingState = AddNetworkLoadingState(isLoading = false),
                        dialogState = AddNetworkDialogState.InputError.Passphrase
                    )
                    return
                }
                _uiState.value = AddNetworkUIState(
                    loadingState = AddNetworkLoadingState(isLoading = true),
                    dialogState = AddNetworkDialogState.None
                )
                wisefy.addWPA2Network(
                    request = AddWPA2NetworkRequest.Default(
                        ssid = ssidInput.value,
                        passphrase = passphraseInput.value
                    ),
                    callbacks = addNetworkCallbacks
                )
            }
            NetworkType.WPA3 -> {
                val passphraseInput = _inputState.value.passphraseState
                if (passphraseInput !is AddNetworkPassphraseState.Valid) {
                    _uiState.value = AddNetworkUIState(
                        loadingState = AddNetworkLoadingState(isLoading = false),
                        dialogState = AddNetworkDialogState.InputError.Passphrase
                    )
                    return
                }
                _uiState.value = AddNetworkUIState(
                    loadingState = AddNetworkLoadingState(isLoading = true),
                    dialogState = AddNetworkDialogState.None
                )
                wisefy.addWPA3Network(
                    request = AddWPA3NetworkRequest.Default(
                        ssid = ssidInput.value,
                        passphrase = passphraseInput.value
                    ),
                    callbacks = addNetworkCallbacks
                )
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addNetwork(launcher: ActivityResultLauncher<Intent>) {
        val ssidInput = _inputState.value.ssidState
        if (ssidInput !is AddNetworkInputSSIDState.Valid) {
            _uiState.value = AddNetworkUIState(
                loadingState = AddNetworkLoadingState(isLoading = false),
                dialogState = AddNetworkDialogState.InputError.SSID
            )
            return
        }
        when (networkType.value) {
            NetworkType.OPEN -> {
                _uiState.value = AddNetworkUIState(
                    loadingState = AddNetworkLoadingState(isLoading = true),
                    dialogState = AddNetworkDialogState.None
                )
                wisefy.addOpenNetwork(
                    request = AddOpenNetworkRequest.Android30OrAbove(
                        ssid = ssidInput.value,
                        launcher = launcher
                    ),
                    callbacks = addNetworkCallbacks
                )
            }
            NetworkType.WPA2 -> {
                val passphraseInput = _inputState.value.passphraseState
                if (passphraseInput !is AddNetworkPassphraseState.Valid) {
                    _uiState.value = AddNetworkUIState(
                        loadingState = AddNetworkLoadingState(isLoading = false),
                        dialogState = AddNetworkDialogState.InputError.Passphrase
                    )
                    return
                }
                _uiState.value = AddNetworkUIState(
                    loadingState = AddNetworkLoadingState(isLoading = true),
                    dialogState = AddNetworkDialogState.None
                )
                wisefy.addWPA2Network(
                    request = AddWPA2NetworkRequest.Android30OrAbove(
                        ssid = ssidInput.value,
                        passphrase = passphraseInput.value,
                        launcher = launcher
                    ),
                    callbacks = addNetworkCallbacks
                )
            }
            NetworkType.WPA3 -> {
                val passphraseInput = _inputState.value.passphraseState
                if (passphraseInput !is AddNetworkPassphraseState.Valid) {
                    _uiState.value = AddNetworkUIState(
                        loadingState = AddNetworkLoadingState(isLoading = false),
                        dialogState = AddNetworkDialogState.InputError.Passphrase
                    )
                    return
                }
                _uiState.value = AddNetworkUIState(
                    loadingState = AddNetworkLoadingState(isLoading = true),
                    dialogState = AddNetworkDialogState.None
                )
                wisefy.addWPA3Network(
                    request = AddWPA3NetworkRequest.Android30OrAbove(
                        ssid = ssidInput.value,
                        passphrase = passphraseInput.value,
                        launcher = launcher
                    ),
                    callbacks = addNetworkCallbacks,
                )
            }
        }
    }

    override fun onSSIDInputChanged(input: String) {
        val newSSIDInputState = when (input.validateSSID()) {
            SSIDInputError.EMPTY -> AddNetworkInputSSIDState.Invalid.Empty
            SSIDInputError.TOO_SHORT -> AddNetworkInputSSIDState.Invalid.TooShort
            SSIDInputError.TOO_LONG -> AddNetworkInputSSIDState.Invalid.TooLong
            SSIDInputError.INVALID_CHARACTERS -> AddNetworkInputSSIDState.Invalid.InvalidCharacters
            SSIDInputError.INVALID_START_CHARACTERS -> AddNetworkInputSSIDState.Invalid.InvalidStartCharacters
            SSIDInputError.LEADING_OR_TRAILING_SPACES -> AddNetworkInputSSIDState.Invalid.LeadingOrTrailingSpaces
            SSIDInputError.NOT_VALID_UNICODE -> AddNetworkInputSSIDState.Invalid.InvalidUnicode
            SSIDInputError.NONE -> AddNetworkInputSSIDState.Valid(input)
        }
        _inputState.value = _inputState.value.copy(ssidState = newSSIDInputState)
    }

    override fun onPassphraseInputChanged(input: String) {
        val newPassphraseInputState = when (input.validatePassphrase()) {
            PassphraseInputError.NONE -> AddNetworkPassphraseState.Valid(input)
            PassphraseInputError.TOO_SHORT -> AddNetworkPassphraseState.Invalid.TooShort
            PassphraseInputError.TOO_LONG -> AddNetworkPassphraseState.Invalid.TooLong
            PassphraseInputError.NOT_VALID_ASCII -> AddNetworkPassphraseState.Invalid.InvalidASCII
        }
        _inputState.value = _inputState.value.copy(passphraseState = newPassphraseInputState)
    }

    override fun onOpenNetworkTypeSelected() {
        _networkType.value = NetworkType.OPEN
    }

    override fun onWPA2NetworkTypeSelected() {
        _networkType.value = NetworkType.WPA2
    }

    override fun onWPA3NetworkTypeSelected() {
        _networkType.value = NetworkType.WPA3
    }

    override fun onAddNetworkSuccess(resultCode: Int) {
        _uiState.value = AddNetworkUIState(
            loadingState = AddNetworkLoadingState(isLoading = false),
            dialogState = AddNetworkDialogState.Success(AddNetworkResult.Success.ResultCode(resultCode))
        )
    }

    override fun onAddNetworkFailure(resultCode: Int) {
        _uiState.value = AddNetworkUIState(
            loadingState = AddNetworkLoadingState(isLoading = false),
            dialogState = AddNetworkDialogState.Failure.UnableToAdd(AddNetworkResult.Failure.ResultCode(resultCode))
        )
    }

    override fun onDialogClosed() {
        _uiState.value = AddNetworkUIState(
            loadingState = AddNetworkLoadingState(isLoading = false),
            dialogState = AddNetworkDialogState.None
        )
    }

    override fun onAddNetworkPermissionsError() {
        _uiState.value = AddNetworkUIState(
            loadingState = AddNetworkLoadingState(isLoading = false),
            dialogState = AddNetworkDialogState.None
        )
    }
}

internal class AddNetworkViewModelFactory(
    private val wisefy: WisefyApi,
    private val sdkUtil: SdkUtil
) : BaseViewModelFactory<AddNetworkViewModel>(
    supportedClass = AddNetworkViewModel::class,
    vmProvider = { DefaultAddNetworkViewModel(wisefy, sdkUtil) }
)
