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
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.activity.result.ActivityResultLauncher
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.isupatches.android.ktx.addNetworkAsync
import com.isupatches.android.wisefy.WisefyApi
import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkRequest
import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkResult
import com.isupatches.android.wisefy.core.exceptions.WisefyException
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
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

internal abstract class AddNetworkViewModel : BaseViewModel() {
    abstract val uiState: State<AddNetworkUIState>

    abstract val isAtLeastAndroidQ: Boolean

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    abstract suspend fun addNetwork()

    @RequiresApi(Build.VERSION_CODES.R)
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    abstract suspend fun addNetwork(launcher: ActivityResultLauncher<Intent>)

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
    @ApplicationContext context: Context,
    private val wisefy: WisefyApi,
    sdkUtil: SdkUtil,
    private val addNetworkStore: AddNetworkStore = DefaultAddNetworkStore(context = context),
) : AddNetworkViewModel() {

    private val _uiState = mutableStateOf(
        AddNetworkUIState(
            loadingState = AddNetworkLoadingState(false),
            dialogState = AddNetworkDialogState.None,
            inputState = AddNetworkInputState(
                ssidInput = "",
                ssidInputValidityState = AddNetworkSSIDInputValidityState.Invalid.Empty,
                passphraseInput = "",
                passphraseInputValidityState = AddNetworkPassphraseInputValidityState.Invalid.Empty,
                bssidInput = "",
                bssidInputValidityState = AddNetworkBSSIDInputValidityState.Valid.Empty
            ),
            networkType = NetworkType.OPEN
        )
    )
    override val uiState: State<AddNetworkUIState>
        get() = _uiState

    override val isAtLeastAndroidQ: Boolean = sdkUtil.isAtLeastQ()

    init {
        viewModelScope.launch {
            addNetworkStore.getNetworkType()
                .collectLatest { networkType ->
                    _uiState.value = uiState.value.copy(
                        networkType = networkType
                    )
                }
        }

        viewModelScope.launch {
            addNetworkStore.getLastUsedNetworkInput()
                .collectLatest { input ->
                    val newSSIDInputValidityState = when (input.validateSSID()) {
                        SSIDInputError.EMPTY -> AddNetworkSSIDInputValidityState.Invalid.Empty
                        SSIDInputError.TOO_SHORT -> AddNetworkSSIDInputValidityState.Invalid.TooShort
                        SSIDInputError.TOO_LONG -> AddNetworkSSIDInputValidityState.Invalid.TooLong
                        SSIDInputError.INVALID_CHARACTERS -> AddNetworkSSIDInputValidityState.Invalid.InvalidCharacters
                        SSIDInputError.INVALID_START_CHARACTERS -> {
                            AddNetworkSSIDInputValidityState.Invalid.InvalidStartCharacters
                        }
                        SSIDInputError.LEADING_OR_TRAILING_SPACES -> {
                            AddNetworkSSIDInputValidityState.Invalid.LeadingOrTrailingSpaces
                        }
                        SSIDInputError.NOT_VALID_UNICODE -> AddNetworkSSIDInputValidityState.Invalid.InvalidUnicode
                        SSIDInputError.NONE -> AddNetworkSSIDInputValidityState.Valid
                    }
                    _uiState.value = uiState.value.copy(
                        inputState = uiState.value.inputState.copy(
                            ssidInput = input,
                            ssidInputValidityState = newSSIDInputValidityState
                        )
                    )
                }
        }

        viewModelScope.launch {
            addNetworkStore.getLastUsedNetworkPassphraseInput()
                .collectLatest { input ->
                    val newPassphraseInputValidityState = when (input.validatePassphrase()) {
                        PassphraseInputError.NONE -> AddNetworkPassphraseInputValidityState.Valid
                        PassphraseInputError.TOO_SHORT -> AddNetworkPassphraseInputValidityState.Invalid.TooShort
                        PassphraseInputError.TOO_LONG -> AddNetworkPassphraseInputValidityState.Invalid.TooLong
                        PassphraseInputError.NOT_VALID_ASCII -> {
                            AddNetworkPassphraseInputValidityState.Invalid.InvalidASCII
                        }
                    }
                    _uiState.value = uiState.value.copy(
                        inputState = uiState.value.inputState.copy(
                            passphraseInput = input,
                            passphraseInputValidityState = newPassphraseInputValidityState
                        )
                    )
                }
        }

        viewModelScope.launch {
            addNetworkStore.getLastUsedNetworkBSSIDInput()
                .collectLatest { input ->
                    val newBSSIDInputValidityState = when (input.validateBSSID()) {
                        BSSIDInputError.NONE -> AddNetworkBSSIDInputValidityState.Valid.BSSID
                        BSSIDInputError.EMPTY -> AddNetworkBSSIDInputValidityState.Valid.Empty
                        BSSIDInputError.INVALID -> AddNetworkBSSIDInputValidityState.Invalid
                    }
                    _uiState.value = uiState.value.copy(
                        inputState = uiState.value.inputState.copy(
                            bssidInput = input,
                            bssidInputValidityState = newBSSIDInputValidityState
                        )
                    )
                }
        }
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override suspend fun addNetwork() {
        if (!isInputValid()) {
            return
        }

        _uiState.value = uiState.value.copy(
            loadingState = AddNetworkLoadingState(isLoading = true),
            dialogState = AddNetworkDialogState.None
        )

        val result = try {
            when (uiState.value.networkType) {
                NetworkType.OPEN -> {
                    wisefy.addNetworkAsync(
                        request = AddNetworkRequest.Open.Default(
                            ssid = uiState.value.inputState.ssidInput,
                            bssid = uiState.value.inputState.bssidInput
                        )
                    )
                }
                NetworkType.WPA2 -> {
                    wisefy.addNetworkAsync(
                        request = AddNetworkRequest.WPA2.Default(
                            ssid = uiState.value.inputState.ssidInput,
                            passphrase = uiState.value.inputState.passphraseInput,
                            bssid = uiState.value.inputState.bssidInput
                        )
                    )
                }
                NetworkType.WPA3 -> {
                    wisefy.addNetworkAsync(
                        request = AddNetworkRequest.WPA3.Default(
                            ssid = uiState.value.inputState.ssidInput,
                            passphrase = uiState.value.inputState.passphraseInput,
                            bssid = uiState.value.inputState.bssidInput
                        )
                    )
                }
            }
        } catch (ex: WisefyException) {
            _uiState.value = uiState.value.copy(
                loadingState = AddNetworkLoadingState(false),
                dialogState = AddNetworkDialogState.Failure.WisefyAsync(exception = ex)
            )
            null
        }

        when (result) {
            is AddNetworkResult.Success -> {
                _uiState.value = uiState.value.copy(
                    loadingState = AddNetworkLoadingState(false),
                    dialogState = AddNetworkDialogState.AddNetwork.Success(result)
                )
            }
            is AddNetworkResult.Failure -> {
                _uiState.value = uiState.value.copy(
                    loadingState = AddNetworkLoadingState(false),
                    dialogState = AddNetworkDialogState.AddNetwork.Failure(result)
                )
            }
            null -> {
                // Case handled above in the catch clause
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override suspend fun addNetwork(launcher: ActivityResultLauncher<Intent>) {
        if (!isInputValid()) {
            return
        }

        _uiState.value = uiState.value.copy(
            loadingState = AddNetworkLoadingState(isLoading = true),
            dialogState = AddNetworkDialogState.None
        )

        val result = try {
            when (uiState.value.networkType) {
                NetworkType.OPEN -> {
                    wisefy.addNetworkAsync(
                        request = AddNetworkRequest.Open.Android30OrAbove(
                            ssid = uiState.value.inputState.ssidInput,
                            bssid = uiState.value.inputState.bssidInput,
                            launcher = launcher
                        )
                    )
                }
                NetworkType.WPA2 -> {
                    wisefy.addNetworkAsync(
                        request = AddNetworkRequest.WPA2.Android30OrAbove(
                            ssid = uiState.value.inputState.ssidInput,
                            passphrase = uiState.value.inputState.passphraseInput,
                            bssid = uiState.value.inputState.bssidInput,
                            launcher = launcher
                        )
                    )
                }
                NetworkType.WPA3 -> {
                    wisefy.addNetworkAsync(
                        request = AddNetworkRequest.WPA3.Android30OrAbove(
                            ssid = uiState.value.inputState.ssidInput,
                            passphrase = uiState.value.inputState.passphraseInput,
                            bssid = uiState.value.inputState.bssidInput,
                            launcher = launcher
                        )
                    )
                }
            }
        } catch (ex: WisefyException) {
            _uiState.value = uiState.value.copy(
                loadingState = AddNetworkLoadingState(false),
                dialogState = AddNetworkDialogState.Failure.WisefyAsync(exception = ex)
            )
            null
        }

        when (result) {
            is AddNetworkResult.Success -> {
                _uiState.value = uiState.value.copy(
                    loadingState = AddNetworkLoadingState(false),
                    dialogState = AddNetworkDialogState.AddNetwork.Success(result)
                )
            }
            is AddNetworkResult.Failure -> {
                _uiState.value = uiState.value.copy(
                    loadingState = AddNetworkLoadingState(false),
                    dialogState = AddNetworkDialogState.AddNetwork.Failure(result)
                )
            }
            null -> {
                // Case handled above in the catch clause
            }
        }
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun connectToNetwork() {
        if (!isInputValid()) {
            return
        }

        _uiState.value = uiState.value.copy(
            loadingState = AddNetworkLoadingState(isLoading = true),
            dialogState = AddNetworkDialogState.None
        )

        wisefy.connectToNetwork(
            request = ConnectToNetworkRequest.SSID(
                ssid = uiState.value.inputState.ssidInput,
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

                override fun onWisefyAsyncFailure(exception: WisefyException) {
                    TODO("Not yet implemented")
                }
            }
        )
    }

    @RequiresApi(Build.VERSION_CODES.R)
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun connectToNetwork(launcher: ActivityResultLauncher<Intent>) {
        // todo@patches
    }

    private fun isInputValid(): Boolean {
        if (uiState.value.inputState.ssidInputValidityState !is AddNetworkSSIDInputValidityState.Valid) {
            _uiState.value = uiState.value.copy(
                loadingState = AddNetworkLoadingState(isLoading = false),
                dialogState = AddNetworkDialogState.InputError.SSID
            )
            return false
        }
        if (uiState.value.inputState.bssidInputValidityState !is AddNetworkBSSIDInputValidityState.Valid) {
            _uiState.value = uiState.value.copy(
                loadingState = AddNetworkLoadingState(isLoading = false),
                dialogState = AddNetworkDialogState.InputError.BSSID
            )
            return false
        }
        return when (uiState.value.networkType) {
            NetworkType.WPA2, NetworkType.WPA3 -> {
                if (uiState.value.inputState.passphraseInputValidityState !is
                    AddNetworkPassphraseInputValidityState.Valid
                ) {
                    _uiState.value = uiState.value.copy(
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
        viewModelScope.launch {
            addNetworkStore.setLastUsedNetworkInput(input)
        }
    }

    override fun onPassphraseInputChanged(input: String) {
        viewModelScope.launch {
            addNetworkStore.setLastUsedNetworkPassphraseInput(input)
        }
    }

    override fun onBSSIDInputChanged(input: String) {
        viewModelScope.launch {
            addNetworkStore.setLastUsedNetworkBSSIDInput(input)
        }
    }

    override fun onNetworkTypeSelected(networkType: NetworkType) {
        viewModelScope.launch {
            addNetworkStore.setNetworkType(networkType = networkType)
            when (networkType) {
                NetworkType.OPEN -> addNetworkStore.setLastUsedNetworkPassphraseInput("")
                NetworkType.WPA2, NetworkType.WPA3 -> {
                    // No-op
                }
            }
        }
    }

    override fun onAddNetworkSuccess(resultCode: Int) {
        _uiState.value = uiState.value.copy(
            loadingState = AddNetworkLoadingState(isLoading = false),
            dialogState = AddNetworkDialogState.AddNetwork.Success(AddNetworkResult.Success.ResultCode(resultCode))
        )
    }

    override fun onAddNetworkFailure(resultCode: Int) {
        _uiState.value = uiState.value.copy(
            loadingState = AddNetworkLoadingState(isLoading = false),
            dialogState = AddNetworkDialogState.AddNetwork.Failure(
                AddNetworkResult.Failure.ResultCode(resultCode)
            )
        )
    }

    override fun onDialogClosed() {
        _uiState.value = uiState.value.copy(
            loadingState = AddNetworkLoadingState(isLoading = false),
            dialogState = AddNetworkDialogState.None
        )
    }

    override fun onAddNetworkPermissionsError() {
        _uiState.value = uiState.value.copy(
            loadingState = AddNetworkLoadingState(isLoading = false),
            dialogState = AddNetworkDialogState.None
        )
    }

    override fun onConnectToNetworkPermissionError() {
        TODO("Not yet implemented")
    }
}

internal class AddNetworkViewModelFactory(
    private val context: Context,
    private val wisefy: WisefyApi,
    private val sdkUtil: SdkUtil,
) : BaseViewModelFactory<AddNetworkViewModel>(
    supportedClass = AddNetworkViewModel::class,
    vmProvider = { DefaultAddNetworkViewModel(context, wisefy, sdkUtil) }
)
