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
import com.isupatches.android.wisefy.networkconnection.callbacks.ConnectToNetworkCallbacks
import com.isupatches.android.wisefy.networkconnection.entities.ConnectToNetworkRequest
import com.isupatches.android.wisefy.sample.entities.NetworkType
import com.isupatches.android.wisefy.sample.scaffolding.BaseViewModel
import com.isupatches.android.wisefy.sample.scaffolding.BaseViewModelFactory
import com.isupatches.android.wisefy.sample.util.BSSIDInputError
import com.isupatches.android.wisefy.sample.util.PassphraseInputError
import com.isupatches.android.wisefy.sample.util.SSIDInputError
import com.isupatches.android.wisefy.sample.util.SdkUtil
import com.isupatches.android.wisefy.sample.util.validateBSSID
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

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    abstract fun connectToNetwork()

    @RequiresApi(Build.VERSION_CODES.R)
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    abstract fun connectToNetwork(launcher: ActivityResultLauncher<Intent>)

    abstract fun onDialogClosed()

    abstract fun onSSIDInputChanged(input: String)
    abstract fun onBSSIDInputChanged(input: String)
    abstract fun onPassphraseInputChanged(input: String)

    abstract fun onNetworkTypeSelected(networkType: NetworkType)

    abstract fun onAddNetworkPermissionsError()
    abstract fun onConnectToNetworkPermissionError()

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

    override val inputState: State<AddNetworkInputState>
        get() = _inputState

    override val isAtLeastAndroidQ: Boolean
        get() = sdkUtil.isAtLeastQ()

    private val _inputState: MutableState<AddNetworkInputState> = mutableStateOf(
        AddNetworkInputState(
            ssidState = AddNetworkInputSSIDState.Invalid.Empty,
            passphraseState = AddNetworkPassphraseState.Invalid.Empty,
            bssidState = AddNetworkBSSIDState.Valid.Empty
        )
    )

    private val addNetworkCallbacks = object : AddNetworkCallbacks {
        override fun onNetworkAdded(result: AddNetworkResult.Success) {
            _uiState.value = AddNetworkUIState(
                loadingState = AddNetworkLoadingState(false),
                dialogState = AddNetworkDialogState.AddNetwork.Success(result)
            )
        }

        override fun onFailureAddingNetwork(result: AddNetworkResult.Failure) {
            _uiState.value = AddNetworkUIState(
                loadingState = AddNetworkLoadingState(false),
                dialogState = AddNetworkDialogState.AddNetwork.Failure(result)
            )
        }

        override fun onWisefyAsyncFailure(throwable: Throwable) {
            _uiState.value = AddNetworkUIState(
                loadingState = AddNetworkLoadingState(false),
                dialogState = AddNetworkDialogState.Failure.WisefyAsync(throwable)
            )
        }
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addNetwork() {
        if (!isInputValid()) {
            return
        }

        _uiState.value = AddNetworkUIState(
            loadingState = AddNetworkLoadingState(isLoading = true),
            dialogState = AddNetworkDialogState.None
        )

        when (networkType.value) {
            NetworkType.OPEN -> {
                wisefy.addOpenNetwork(
                    request = AddOpenNetworkRequest.Default(
                        ssid = (_inputState.value.ssidState as AddNetworkInputSSIDState.Valid).value,
                    ),
                    callbacks = addNetworkCallbacks
                )
            }
            NetworkType.WPA2 -> {
                wisefy.addWPA2Network(
                    request = AddWPA2NetworkRequest.Default(
                        ssid = (_inputState.value.ssidState as AddNetworkInputSSIDState.Valid).value,
                        passphrase = (_inputState.value.passphraseState as AddNetworkPassphraseState.Valid).value
                    ),
                    callbacks = addNetworkCallbacks
                )
            }
            NetworkType.WPA3 -> {
                wisefy.addWPA3Network(
                    request = AddWPA3NetworkRequest.Default(
                        ssid = (_inputState.value.ssidState as AddNetworkInputSSIDState.Valid).value,
                        passphrase = (_inputState.value.passphraseState as AddNetworkPassphraseState.Valid).value
                    ),
                    callbacks = addNetworkCallbacks
                )
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addNetwork(launcher: ActivityResultLauncher<Intent>) {
        if (!isInputValid()) {
            return
        }

        _uiState.value = AddNetworkUIState(
            loadingState = AddNetworkLoadingState(isLoading = true),
            dialogState = AddNetworkDialogState.None
        )

        when (networkType.value) {
            NetworkType.OPEN -> {
                wisefy.addOpenNetwork(
                    request = AddOpenNetworkRequest.Android30OrAbove(
                        ssid = (_inputState.value.ssidState as AddNetworkInputSSIDState.Valid).value,
                        launcher = launcher
                    ),
                    callbacks = addNetworkCallbacks
                )
            }
            NetworkType.WPA2 -> {
                wisefy.addWPA2Network(
                    request = AddWPA2NetworkRequest.Android30OrAbove(
                        ssid = (_inputState.value.ssidState as AddNetworkInputSSIDState.Valid).value,
                        passphrase = (_inputState.value.passphraseState as AddNetworkPassphraseState.Valid).value,
                        launcher = launcher
                    ),
                    callbacks = addNetworkCallbacks
                )
            }
            NetworkType.WPA3 -> {
                wisefy.addWPA3Network(
                    request = AddWPA3NetworkRequest.Android30OrAbove(
                        ssid = (_inputState.value.ssidState as AddNetworkInputSSIDState.Valid).value,
                        passphrase = (_inputState.value.passphraseState as AddNetworkPassphraseState.Valid).value,
                        launcher = launcher
                    ),
                    callbacks = addNetworkCallbacks
                )
            }
        }
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun connectToNetwork() {
        if (!isInputValid()) {
            return
        }

        _uiState.value = AddNetworkUIState(
            loadingState = AddNetworkLoadingState(isLoading = true),
            dialogState = AddNetworkDialogState.None
        )

        wisefy.connectToNetwork(
            request = ConnectToNetworkRequest.SSID(
                ssid = (_inputState.value.ssidState as AddNetworkInputSSIDState.Valid).value,
                timeoutInMillis = 3000
            ),
            callbacks = object : ConnectToNetworkCallbacks {
                override fun onConnectionRequestPlaced() {
                    TODO("Not yet implemented")
                }

                override fun onConnectedToNetwork() {
                    TODO("Not yet implemented")
                }

                override fun onNetworkNotFoundToConnectTo() {
                    TODO("Not yet implemented")
                }

                override fun onFailureConnectingToNetwork() {
                    TODO("Not yet implemented")
                }

                override fun onWisefyAsyncFailure(throwable: Throwable) {
                    TODO("Not yet implemented")
                }
            }
        )
    }

    @RequiresApi(Build.VERSION_CODES.R)
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun connectToNetwork(launcher: ActivityResultLauncher<Intent>) {
    }

    private fun isInputValid(): Boolean {
        val ssidInput = _inputState.value.ssidState
        val bssidInput = _inputState.value.bssidState
        if (ssidInput !is AddNetworkInputSSIDState.Valid) {
            _uiState.value = AddNetworkUIState(
                loadingState = AddNetworkLoadingState(isLoading = false),
                dialogState = AddNetworkDialogState.InputError.SSID
            )
            return false
        }
        if (bssidInput !is AddNetworkBSSIDState.Valid) {
            _uiState.value = AddNetworkUIState(
                loadingState = AddNetworkLoadingState(isLoading = false),
                dialogState = AddNetworkDialogState.InputError.BSSID
            )
            return false
        }
        return when (networkType.value) {
            NetworkType.WPA2, NetworkType.WPA3 -> {
                val passphraseInput = _inputState.value.passphraseState
                if (passphraseInput !is AddNetworkPassphraseState.Valid) {
                    _uiState.value = AddNetworkUIState(
                        loadingState = AddNetworkLoadingState(isLoading = false),
                        dialogState = AddNetworkDialogState.InputError.Passphrase
                    )
                    false
                } else {
                    true
                }
            }
            NetworkType.OPEN -> true
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

    override fun onBSSIDInputChanged(input: String) {
        val newBSSIDInputState = when (input.validateBSSID()) {
            BSSIDInputError.NONE -> AddNetworkBSSIDState.Valid.BSSID(input)
            BSSIDInputError.EMPTY -> AddNetworkBSSIDState.Valid.Empty
            BSSIDInputError.INVALID -> AddNetworkBSSIDState.Invalid
        }
        _inputState.value = _inputState.value.copy(bssidState = newBSSIDInputState)
    }

    override fun onNetworkTypeSelected(networkType: NetworkType) {
        _networkType.value = networkType
    }

    override fun onAddNetworkSuccess(resultCode: Int) {
        _uiState.value = AddNetworkUIState(
            loadingState = AddNetworkLoadingState(isLoading = false),
            dialogState = AddNetworkDialogState.AddNetwork.Success(AddNetworkResult.Success.ResultCode(resultCode))
        )
    }

    override fun onAddNetworkFailure(resultCode: Int) {
        _uiState.value = AddNetworkUIState(
            loadingState = AddNetworkLoadingState(isLoading = false),
            dialogState = AddNetworkDialogState.AddNetwork.Failure(
                AddNetworkResult.Failure.ResultCode(resultCode)
            )
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

    override fun onConnectToNetworkPermissionError() {
        TODO("Not yet implemented")
    }
}

internal class AddNetworkViewModelFactory(
    private val wisefy: WisefyApi,
    private val sdkUtil: SdkUtil
) : BaseViewModelFactory<AddNetworkViewModel>(
    supportedClass = AddNetworkViewModel::class,
    vmProvider = { DefaultAddNetworkViewModel(wisefy, sdkUtil) }
)
