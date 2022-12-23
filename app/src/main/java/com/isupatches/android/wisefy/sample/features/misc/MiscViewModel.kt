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
package com.isupatches.android.wisefy.sample.features.misc

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.Manifest.permission.CHANGE_WIFI_STATE
import androidx.annotation.RequiresPermission
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.isupatches.android.wisefy.WisefyApi
import com.isupatches.android.wisefy.core.exceptions.WisefyException
import com.isupatches.android.wisefy.ktx.disableWifiAsync
import com.isupatches.android.wisefy.ktx.enableWifiAsync
import com.isupatches.android.wisefy.ktx.getCurrentNetworkAsync
import com.isupatches.android.wisefy.ktx.getNetworkConnectionStatusAsync
import com.isupatches.android.wisefy.ktx.getSavedNetworksAsync
import com.isupatches.android.wisefy.ktx.isWifiEnabledAsync
import com.isupatches.android.wisefy.networkconnection.callbacks.DisconnectFromCurrentNetworkCallbacks
import com.isupatches.android.wisefy.networkconnection.entities.DisconnectFromCurrentNetworkRequest
import com.isupatches.android.wisefy.networkconnection.entities.DisconnectFromCurrentNetworkResult
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkResult
import com.isupatches.android.wisefy.networkinfo.entities.GetNetworkConnectionStatusResult
import com.isupatches.android.wisefy.sample.scaffolding.BaseViewModel
import com.isupatches.android.wisefy.sample.scaffolding.BaseViewModelFactory
import com.isupatches.android.wisefy.savednetworks.entities.GetSavedNetworksResult
import com.isupatches.android.wisefy.wifi.entities.DisableWifiRequest
import com.isupatches.android.wisefy.wifi.entities.DisableWifiResult
import com.isupatches.android.wisefy.wifi.entities.EnableWifiRequest
import com.isupatches.android.wisefy.wifi.entities.EnableWifiResult
import com.isupatches.android.wisefy.wifi.entities.IsWifiEnabledResult

internal abstract class MiscViewModel : BaseViewModel() {
    abstract val uiState: State<MiscUIState>

    abstract fun onDialogClosed()

    abstract fun onDisableWifiPermissionsError()
    abstract fun onEnableWifiPermissionsError()
    abstract fun onGetCurrentNetworkPermissionsError()
    abstract fun onGetNetworkConnectionStatusPermissionError()
    abstract fun onGetSavedNetworksPermissionsError()
    abstract fun onIsWifiEnabledPermissionsError()

    @RequiresPermission(CHANGE_WIFI_STATE)
    abstract suspend fun disableWifi(request: DisableWifiRequest)

    abstract suspend fun disconnectFromCurrentNetwork(request: DisconnectFromCurrentNetworkRequest)

    @RequiresPermission(CHANGE_WIFI_STATE)
    abstract suspend fun enableWifi(request: EnableWifiRequest)

    @RequiresPermission(ACCESS_NETWORK_STATE)
    abstract suspend fun getCurrentNetwork()

    @RequiresPermission(ACCESS_NETWORK_STATE)
    abstract suspend fun getNetworkConnectionStatus()

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    abstract suspend fun getSavedNetworks()

    @RequiresPermission(ACCESS_WIFI_STATE)
    abstract suspend fun isWifiEnabled()
}

internal class DefaultMiscViewModel(private val wisefy: WisefyApi) : MiscViewModel() {

    private val _uiState = mutableStateOf(
        MiscUIState(
            loadingState = MiscLoadingState(isLoading = false),
            dialogState = MiscDialogState.None
        )
    )
    override val uiState: State<MiscUIState>
        get() = _uiState

    @RequiresPermission(CHANGE_WIFI_STATE)
    override suspend fun disableWifi(request: DisableWifiRequest) {
        _uiState.value = MiscUIState(
            loadingState = MiscLoadingState(isLoading = true),
            dialogState = MiscDialogState.None
        )

        val result = try {
            wisefy.disableWifiAsync(request = request)
        } catch (ex: WisefyException) {
            _uiState.value = MiscUIState(
                loadingState = MiscLoadingState(isLoading = false),
                dialogState = MiscDialogState.Failure.WisefyAsync(exception = ex)
            )
            null
        }

        when (result) {
            is DisableWifiResult.Success -> {
                _uiState.value = MiscUIState(
                    loadingState = MiscLoadingState(isLoading = false),
                    dialogState = MiscDialogState.DisableWifi.Success(result)
                )
            }
            is DisableWifiResult.Failure -> {
                _uiState.value = MiscUIState(
                    loadingState = MiscLoadingState(isLoading = false),
                    dialogState = MiscDialogState.DisableWifi.Failure(result)
                )
            }
            null -> {
                // Case handled above in the catch clause
            }
        }
    }

    override suspend fun disconnectFromCurrentNetwork(request: DisconnectFromCurrentNetworkRequest) {
        _uiState.value = MiscUIState(
            loadingState = MiscLoadingState(isLoading = true),
            dialogState = MiscDialogState.None
        )
        wisefy.disconnectFromCurrentNetwork(
            request = request,
            callbacks = object : DisconnectFromCurrentNetworkCallbacks {
                override fun onNetworkScreenOpened() {
                    _uiState.value = MiscUIState(
                        loadingState = MiscLoadingState(isLoading = false),
                        dialogState = MiscDialogState.DisconnectFromCurrentNetwork.Success(
                            result = DisconnectFromCurrentNetworkResult.Success.NetworkScreenOpened
                        )
                    )
                }

                override fun onDisconnectRequestPlaced() {
                    _uiState.value = MiscUIState(
                        loadingState = MiscLoadingState(isLoading = false),
                        dialogState = MiscDialogState.DisconnectFromCurrentNetwork.Success(
                            result = DisconnectFromCurrentNetworkResult.Success.DisconnectRequestSent
                        )
                    )
                }

                override fun onDisconnectedFromCurrentNetwork() {
                    _uiState.value = MiscUIState(
                        loadingState = MiscLoadingState(isLoading = false),
                        dialogState = MiscDialogState.DisconnectFromCurrentNetwork.Success(
                            result = DisconnectFromCurrentNetworkResult.Success.True
                        )
                    )
                }

                override fun onNetworkNotFoundToDisconnectFrom() {
                    _uiState.value = MiscUIState(
                        loadingState = MiscLoadingState(isLoading = false),
                        dialogState = MiscDialogState.DisconnectFromCurrentNetwork.Failure(
                            result = DisconnectFromCurrentNetworkResult.Failure.NetworkNotFound
                        )
                    )
                }

                override fun onFailureDisconnectingFromCurrentNetwork() {
                    _uiState.value = MiscUIState(
                        loadingState = MiscLoadingState(isLoading = false),
                        dialogState = MiscDialogState.DisconnectFromCurrentNetwork.Failure(
                            result = DisconnectFromCurrentNetworkResult.Failure.False
                        )
                    )
                }

                override fun onWisefyAsyncFailure(exception: WisefyException) {
                    _uiState.value = MiscUIState(
                        loadingState = MiscLoadingState(isLoading = false),
                        dialogState = MiscDialogState.Failure.WisefyAsync(exception = exception)
                    )
                }
            }
        )
    }

    @RequiresPermission(CHANGE_WIFI_STATE)
    override suspend fun enableWifi(request: EnableWifiRequest) {
        _uiState.value = MiscUIState(
            loadingState = MiscLoadingState(isLoading = true),
            dialogState = MiscDialogState.None
        )
        val result = try {
            wisefy.enableWifiAsync(request)
        } catch (ex: WisefyException) {
            _uiState.value = MiscUIState(
                loadingState = MiscLoadingState(isLoading = false),
                dialogState = MiscDialogState.Failure.WisefyAsync(exception = ex)
            )
            null
        }

        when (result) {
            is EnableWifiResult.Success -> {
                _uiState.value = MiscUIState(
                    loadingState = MiscLoadingState(isLoading = false),
                    dialogState = MiscDialogState.EnableWifi.Success(result)
                )
            }
            is EnableWifiResult.Failure -> {
                _uiState.value = MiscUIState(
                    loadingState = MiscLoadingState(isLoading = false),
                    dialogState = MiscDialogState.EnableWifi.Failure(result)
                )
            }
            null -> {
                // Case handled above in the catch clause
            }
        }
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override suspend fun getCurrentNetwork() {
        _uiState.value = MiscUIState(
            loadingState = MiscLoadingState(isLoading = true),
            dialogState = MiscDialogState.None
        )
        val result = try {
            wisefy.getCurrentNetworkAsync()
        } catch (ex: WisefyException) {
            _uiState.value = MiscUIState(
                loadingState = MiscLoadingState(isLoading = false),
                dialogState = MiscDialogState.Failure.WisefyAsync(exception = ex)
            )
            null
        }

        when (result) {
            is GetCurrentNetworkResult -> {
                _uiState.value = MiscUIState(
                    loadingState = MiscLoadingState(isLoading = false),
                    dialogState = MiscDialogState.GetCurrentNetwork.Success(network = result.value)
                )
            }
            null -> {
                // Case handled above in the catch clause
            }
        }
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override suspend fun getNetworkConnectionStatus() {
        _uiState.value = MiscUIState(
            loadingState = MiscLoadingState(isLoading = true),
            dialogState = MiscDialogState.None
        )

        val result = try {
            wisefy.getNetworkConnectionStatusAsync()
        } catch (ex: WisefyException) {
            _uiState.value = MiscUIState(
                loadingState = MiscLoadingState(isLoading = false),
                dialogState = MiscDialogState.Failure.WisefyAsync(exception = ex)
            )
            null
        }

        when (result) {
            is GetNetworkConnectionStatusResult -> {
                _uiState.value = MiscUIState(
                    loadingState = MiscLoadingState(isLoading = false),
                    dialogState = MiscDialogState.GetNetworkConnectionStatus.Success(data = result)
                )
            }
            null -> {
                // Case handled above in the catch clause
            }
        }
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override suspend fun getSavedNetworks() {
        _uiState.value = MiscUIState(
            loadingState = MiscLoadingState(isLoading = true),
            dialogState = MiscDialogState.None
        )
        val result = try {
            wisefy.getSavedNetworksAsync()
        } catch (ex: WisefyException) {
            _uiState.value = MiscUIState(
                loadingState = MiscLoadingState(isLoading = false),
                dialogState = MiscDialogState.Failure.WisefyAsync(exception = ex)
            )
            null
        }

        when (result) {
            is GetSavedNetworksResult.SavedNetworks -> {
                _uiState.value = MiscUIState(
                    loadingState = MiscLoadingState(isLoading = false),
                    dialogState = MiscDialogState.GetSavedNetworks.Success(savedNetworks = result.value)
                )
            }
            is GetSavedNetworksResult.Empty -> {
                _uiState.value = MiscUIState(
                    loadingState = MiscLoadingState(isLoading = false),
                    dialogState = MiscDialogState.GetSavedNetworks.Failure
                )
            }
            null -> {
                // Case handled above in the catch clause
            }
        }
    }

    @RequiresPermission(ACCESS_WIFI_STATE)
    override suspend fun isWifiEnabled() {
        _uiState.value = MiscUIState(
            loadingState = MiscLoadingState(isLoading = true),
            dialogState = MiscDialogState.None
        )
        val result = try {
            wisefy.isWifiEnabledAsync()
        } catch (ex: WisefyException) {
            _uiState.value = MiscUIState(
                loadingState = MiscLoadingState(isLoading = false),
                dialogState = MiscDialogState.Failure.WisefyAsync(exception = ex)
            )
            null
        }

        when (result) {
            is IsWifiEnabledResult.True -> {
                _uiState.value = MiscUIState(
                    loadingState = MiscLoadingState(isLoading = false),
                    dialogState = MiscDialogState.IsWifiEnabled.True
                )
            }
            is IsWifiEnabledResult.False -> {
                _uiState.value = MiscUIState(
                    loadingState = MiscLoadingState(isLoading = false),
                    dialogState = MiscDialogState.IsWifiEnabled.False
                )
            }
            null -> {
                // Case handled above in the catch clause
            }
        }
    }

    override fun onDialogClosed() {
        _uiState.value = MiscUIState(
            loadingState = MiscLoadingState(isLoading = false),
            dialogState = MiscDialogState.None
        )
    }

    override fun onDisableWifiPermissionsError() {
        _uiState.value = MiscUIState(
            loadingState = MiscLoadingState(isLoading = false),
            dialogState = MiscDialogState.DisableWifi.PermissionsError
        )
    }

    override fun onEnableWifiPermissionsError() {
        _uiState.value = MiscUIState(
            loadingState = MiscLoadingState(isLoading = false),
            dialogState = MiscDialogState.EnableWifi.PermissionsError
        )
    }

    override fun onGetCurrentNetworkPermissionsError() {
        _uiState.value = MiscUIState(
            loadingState = MiscLoadingState(isLoading = false),
            dialogState = MiscDialogState.GetCurrentNetwork.PermissionsError
        )
    }

    override fun onGetNetworkConnectionStatusPermissionError() {
        _uiState.value = MiscUIState(
            loadingState = MiscLoadingState(isLoading = false),
            dialogState = MiscDialogState.GetNetworkConnectionStatus.PermissionsError
        )
    }

    override fun onGetSavedNetworksPermissionsError() {
        _uiState.value = MiscUIState(
            loadingState = MiscLoadingState(isLoading = false),
            dialogState = MiscDialogState.GetSavedNetworks.PermissionsError
        )
    }

    override fun onIsWifiEnabledPermissionsError() {
        _uiState.value = MiscUIState(
            loadingState = MiscLoadingState(isLoading = false),
            dialogState = MiscDialogState.IsWifiEnabled.PermissionsError
        )
    }
}

internal class MiscViewModelFactory(
    private val wisefy: WisefyApi
) : BaseViewModelFactory<MiscViewModel>(
    supportedClass = MiscViewModel::class,
    vmProvider = { DefaultMiscViewModel(wisefy) }
)
