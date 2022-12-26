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
package com.isupatches.android.wisefy.sample.features.add

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.CHANGE_WIFI_STATE
import android.content.Context
import androidx.annotation.RequiresPermission
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.isupatches.android.wisefy.WisefyApi
import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkRequest
import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkResult
import com.isupatches.android.wisefy.core.exceptions.WisefyException
import com.isupatches.android.wisefy.ktx.addNetworkAsync
import com.isupatches.android.wisefy.sample.entities.NetworkType
import com.isupatches.android.wisefy.sample.scaffolding.BaseViewModel
import com.isupatches.android.wisefy.sample.scaffolding.BaseViewModelFactory
import com.isupatches.android.wisefy.sample.util.BSSIDInputError
import com.isupatches.android.wisefy.sample.util.ErrorMessages
import com.isupatches.android.wisefy.sample.util.PassphraseInputError
import com.isupatches.android.wisefy.sample.util.SSIDInputError
import com.isupatches.android.wisefy.sample.util.SdkUtil
import com.isupatches.android.wisefy.sample.util.validateBSSID
import com.isupatches.android.wisefy.sample.util.validatePassphrase
import com.isupatches.android.wisefy.sample.util.validateSSID
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

internal abstract class AddNetworkViewModel : BaseViewModel() {
    abstract val uiState: State<AddNetworkUIState>

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    abstract suspend fun addNetwork()

    abstract fun onDialogClosed()

    abstract fun onSSIDInputChanged(input: String)
    abstract fun onBSSIDInputChanged(input: String)
    abstract fun onPassphraseInputChanged(input: String)

    abstract fun onNetworkTypeSelected(networkType: NetworkType)

    abstract fun onAddNetworkPermissionsError()
}

internal class DefaultAddNetworkViewModel(
    context: Context,
    private val wisefy: WisefyApi,
    private val sdkUtil: SdkUtil,
    private val addNetworkStore: AddNetworkStore = DefaultAddNetworkStore(context = context)
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
        if (isInputInvalid()) {
            return
        }
        _uiState.value = uiState.value.copy(
            loadingState = AddNetworkLoadingState(isLoading = true),
            dialogState = AddNetworkDialogState.None
        )
        val request = when (uiState.value.networkType) {
            NetworkType.OPEN -> {
                AddNetworkRequest.Open(
                    ssid = uiState.value.inputState.ssidInput,
                    bssid = uiState.value.inputState.bssidInput
                )
            }
            NetworkType.WPA2 -> {
                AddNetworkRequest.WPA2(
                    ssid = uiState.value.inputState.ssidInput,
                    passphrase = uiState.value.inputState.passphraseInput,
                    bssid = uiState.value.inputState.bssidInput
                )
            }
            NetworkType.WPA3 -> {
                if (sdkUtil.isAtLeastQ()) {
                    AddNetworkRequest.WPA3(
                        ssid = uiState.value.inputState.ssidInput,
                        passphrase = uiState.value.inputState.passphraseInput,
                        bssid = uiState.value.inputState.bssidInput
                    )
                } else {
                    /*
                     * The UI not displaying the WPA3 option on pre-Android Q / SDK 29 devices
                     * will prevent us from getting here.
                     */
                    error(ErrorMessages.AddNetwork.WPA3_NETWORK_ADD_ON_PRE_ANDROID_Q_DEVICE)
                }
            }
        }
        when (val result = getAddNetworkResult(request)) {
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

    private fun isInputInvalid(): Boolean {
        val currentInputState = uiState.value.inputState
        if (currentInputState.ssidInputValidityState !is AddNetworkSSIDInputValidityState.Valid) {
            _uiState.value = uiState.value.copy(
                loadingState = AddNetworkLoadingState(isLoading = false),
                dialogState = AddNetworkDialogState.InputError.SSID
            )
            return true
        }
        if (currentInputState.bssidInputValidityState !is AddNetworkBSSIDInputValidityState.Valid) {
            _uiState.value = uiState.value.copy(
                loadingState = AddNetworkLoadingState(isLoading = false),
                dialogState = AddNetworkDialogState.InputError.BSSID
            )
            return true
        }
        return when (uiState.value.networkType) {
            NetworkType.WPA2, NetworkType.WPA3 -> {
                if (currentInputState.passphraseInputValidityState !is AddNetworkPassphraseInputValidityState.Valid) {
                    _uiState.value = uiState.value.copy(
                        loadingState = AddNetworkLoadingState(isLoading = false),
                        dialogState = AddNetworkDialogState.InputError.Passphrase
                    )
                    true
                } else {
                    false
                }
            }
            NetworkType.OPEN -> false
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

    override fun onDialogClosed() {
        _uiState.value = uiState.value.copy(
            loadingState = AddNetworkLoadingState(isLoading = false),
            dialogState = AddNetworkDialogState.None
        )
    }

    override fun onAddNetworkPermissionsError() {
        _uiState.value = uiState.value.copy(
            loadingState = AddNetworkLoadingState(isLoading = false),
            dialogState = when (uiState.value.networkType) {
                NetworkType.OPEN -> AddNetworkDialogState.AddNetwork.PermissionsError.AddOpenNetwork
                NetworkType.WPA2 -> AddNetworkDialogState.AddNetwork.PermissionsError.AddWPA2Network
                NetworkType.WPA3 -> AddNetworkDialogState.AddNetwork.PermissionsError.AddWPA3Network
            }
        )
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    private suspend fun getAddNetworkResult(request: AddNetworkRequest): AddNetworkResult? {
        return try {
            wisefy.addNetworkAsync(request)
        } catch (ex: WisefyException) {
            _uiState.value = uiState.value.copy(
                loadingState = AddNetworkLoadingState(false),
                dialogState = AddNetworkDialogState.Failure.WisefyAsync(exception = ex)
            )
            null
        }
    }
}

internal class AddNetworkViewModelFactory(
    private val context: Context,
    private val wisefy: WisefyApi,
    private val sdkUtil: SdkUtil
) : BaseViewModelFactory<AddNetworkViewModel>(
    supportedClass = AddNetworkViewModel::class,
    vmProvider = { DefaultAddNetworkViewModel(context, wisefy, sdkUtil) }
)
