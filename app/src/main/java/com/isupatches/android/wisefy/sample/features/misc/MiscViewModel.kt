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
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.isupatches.android.wisefy.WisefyApi
import com.isupatches.android.wisefy.accesspoints.entities.GetAccessPointsResult
import com.isupatches.android.wisefy.core.exceptions.WisefyException
import com.isupatches.android.wisefy.ktx.disableWifiAsync
import com.isupatches.android.wisefy.ktx.enableWifiAsync
import com.isupatches.android.wisefy.ktx.getAccessPointsAsync
import com.isupatches.android.wisefy.ktx.getCurrentNetworkAsync
import com.isupatches.android.wisefy.ktx.getNetworkConnectionStatusAsync
import com.isupatches.android.wisefy.ktx.getSavedNetworksAsync
import com.isupatches.android.wisefy.ktx.isWifiEnabledAsync
import com.isupatches.android.wisefy.networkconnection.callbacks.DisconnectFromCurrentNetworkCallbacks
import com.isupatches.android.wisefy.networkconnection.entities.DisconnectFromCurrentNetworkRequest
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkResult
import com.isupatches.android.wisefy.networkinfo.entities.GetNetworkConnectionStatusResult
import com.isupatches.android.wisefy.sample.scaffolding.BaseViewModel
import com.isupatches.android.wisefy.sample.scaffolding.BaseViewModelFactory
import com.isupatches.android.wisefy.sample.util.SdkUtil
import com.isupatches.android.wisefy.savednetworks.entities.GetSavedNetworksResult
import com.isupatches.android.wisefy.wifi.entities.DisableWifiRequest
import com.isupatches.android.wisefy.wifi.entities.DisableWifiResult
import com.isupatches.android.wisefy.wifi.entities.EnableWifiRequest
import com.isupatches.android.wisefy.wifi.entities.EnableWifiResult
import com.isupatches.android.wisefy.wifi.entities.IsWifiEnabledResult

internal abstract class MiscViewModel : BaseViewModel() {
    abstract val uiState: State<MiscScreenUIState>

    abstract fun onDialogClosed()

    abstract fun onGetCurrentNetworkPermissionsError()
    abstract fun onGetNearbyAccessPointsPermissionError()
    abstract fun onGetNetworkConnectionStatusPermissionError()
    abstract fun onGetSavedNetworksPermissionsError()

    abstract suspend fun disableWifi()

    @RequiresApi(Build.VERSION_CODES.Q)
    abstract suspend fun disableWifi(context: Context)

    abstract fun disconnectFromCurrentNetwork()

    abstract suspend fun enableWifi()

    @RequiresApi(Build.VERSION_CODES.Q)
    abstract suspend fun enableWifi(context: Context)

    @RequiresPermission(ACCESS_NETWORK_STATE)
    abstract suspend fun getCurrentNetwork()

    @RequiresPermission(ACCESS_FINE_LOCATION)
    abstract suspend fun getNearbyAccessPoints()

    @RequiresPermission(ACCESS_NETWORK_STATE)
    abstract suspend fun getNetworkConnectionStatus()

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    abstract suspend fun getSavedNetworks()

    abstract suspend fun isWifiEnabled()
}

internal class DefaultMiscViewModel(
    private val wisefy: WisefyApi,
    private val sdkUtil: SdkUtil
) : MiscViewModel() {

    private val _uiState = mutableStateOf(
        MiscScreenUIState(
            loadingState = MiscScreenLoadingState(isLoading = false),
            dialogState = MiscScreenDialogState.None
        )
    )
    override val uiState: State<MiscScreenUIState>
        get() = _uiState

    override suspend fun disableWifi() {
        disableWifi(request = DisableWifiRequest.Default)
    }

    override suspend fun disableWifi(context: Context) {
        disableWifi(request = DisableWifiRequest.Android29OrAbove(context))
    }

    override fun disconnectFromCurrentNetwork() {
        if (sdkUtil.isAtLeastQ()) {
            _uiState.value = MiscScreenUIState(
                loadingState = MiscScreenLoadingState(isLoading = false),
                dialogState = MiscScreenDialogState.DisconnectFromCurrentNetwork.DisplayAndroidQMessage
            )
            return
        }

        _uiState.value = MiscScreenUIState(
            loadingState = MiscScreenLoadingState(isLoading = true),
            dialogState = MiscScreenDialogState.None
        )
        wisefy.disconnectFromCurrentNetwork(
            request = DisconnectFromCurrentNetworkRequest(),
            callbacks = object : DisconnectFromCurrentNetworkCallbacks {
                override fun onDisconnectRequestPlaced() {
                    _uiState.value = MiscScreenUIState(
                        loadingState = MiscScreenLoadingState(isLoading = false),
                        dialogState = MiscScreenDialogState.DisconnectFromCurrentNetwork.Success.RequestPlaced
                    )
                }

                override fun onDisconnectedFromCurrentNetwork() {
                    _uiState.value = MiscScreenUIState(
                        loadingState = MiscScreenLoadingState(isLoading = false),
                        dialogState = MiscScreenDialogState.DisconnectFromCurrentNetwork.Success.Disconnected
                    )
                }

                override fun onNetworkNotFoundToDisconnectFrom() {
                    _uiState.value = MiscScreenUIState(
                        loadingState = MiscScreenLoadingState(isLoading = false),
                        dialogState = MiscScreenDialogState.DisconnectFromCurrentNetwork.Failure.NetworkNotFound
                    )
                }

                override fun onFailureDisconnectingFromCurrentNetwork() {
                    _uiState.value = MiscScreenUIState(
                        loadingState = MiscScreenLoadingState(isLoading = false),
                        dialogState = MiscScreenDialogState.DisconnectFromCurrentNetwork.Failure.UnableToDisconnect
                    )
                }

                override fun onWisefyAsyncFailure(exception: WisefyException) {
                    _uiState.value = MiscScreenUIState(
                        loadingState = MiscScreenLoadingState(isLoading = false),
                        dialogState = MiscScreenDialogState.Failure.WisefyAsync(exception = exception)
                    )
                }
            }
        )
    }

    override suspend fun enableWifi() {
        enableWifi(request = EnableWifiRequest.Default)
    }

    override suspend fun enableWifi(context: Context) {
        enableWifi(request = EnableWifiRequest.Android29OrAbove(context))
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override suspend fun getCurrentNetwork() {
        _uiState.value = MiscScreenUIState(
            loadingState = MiscScreenLoadingState(isLoading = true),
            dialogState = MiscScreenDialogState.None
        )
        val result = try {
            wisefy.getCurrentNetworkAsync()
        } catch (ex: WisefyException) {
            _uiState.value = MiscScreenUIState(
                loadingState = MiscScreenLoadingState(isLoading = false),
                dialogState = MiscScreenDialogState.Failure.WisefyAsync(exception = ex)
            )
            null
        }

        when (result) {
            is GetCurrentNetworkResult.Empty -> {
                _uiState.value = MiscScreenUIState(
                    loadingState = MiscScreenLoadingState(isLoading = false),
                    dialogState = MiscScreenDialogState.GetCurrentNetwork.Failure
                )
            }
            is GetCurrentNetworkResult.Network -> {
                _uiState.value = MiscScreenUIState(
                    loadingState = MiscScreenLoadingState(isLoading = false),
                    dialogState = MiscScreenDialogState.GetCurrentNetwork.Success(network = result.value)
                )
            }
            null -> {
                // Case handled above in the catch clause
            }
        }
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override suspend fun getNearbyAccessPoints() {
        _uiState.value = MiscScreenUIState(
            loadingState = MiscScreenLoadingState(isLoading = true),
            dialogState = MiscScreenDialogState.None
        )
        val result = try {
            wisefy.getAccessPointsAsync()
        } catch (ex: WisefyException) {
            _uiState.value = MiscScreenUIState(
                loadingState = MiscScreenLoadingState(isLoading = false),
                dialogState = MiscScreenDialogState.Failure.WisefyAsync(exception = ex)
            )
            null
        }

        when (result) {
            is GetAccessPointsResult.Empty -> {
                _uiState.value = MiscScreenUIState(
                    loadingState = MiscScreenLoadingState(isLoading = false),
                    dialogState = MiscScreenDialogState.GetNearbyAccessPoints.Failure
                )
            }
            is GetAccessPointsResult.AccessPoints -> {
                _uiState.value = MiscScreenUIState(
                    loadingState = MiscScreenLoadingState(isLoading = false),
                    dialogState = MiscScreenDialogState.GetNearbyAccessPoints.Success(accessPoints = result.value)
                )
            }
            null -> {
                // Case handled above in the catch clause
            }
        }
    }

    override suspend fun getNetworkConnectionStatus() {
        _uiState.value = MiscScreenUIState(
            loadingState = MiscScreenLoadingState(isLoading = true),
            dialogState = MiscScreenDialogState.None
        )

        val result = try {
            wisefy.getNetworkConnectionStatusAsync()
        } catch (ex: WisefyException) {
            _uiState.value = MiscScreenUIState(
                loadingState = MiscScreenLoadingState(isLoading = false),
                dialogState = MiscScreenDialogState.Failure.WisefyAsync(exception = ex)
            )
            null
        }

        when (result) {
            is GetNetworkConnectionStatusResult -> {
                _uiState.value = MiscScreenUIState(
                    loadingState = MiscScreenLoadingState(isLoading = false),
                    dialogState = MiscScreenDialogState.GetNetworkConnectionStatus.Success(data = result)
                )
            }
            null -> {
                // Case handled above in the catch clause
            }
        }
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override suspend fun getSavedNetworks() {
        _uiState.value = MiscScreenUIState(
            loadingState = MiscScreenLoadingState(isLoading = true),
            dialogState = MiscScreenDialogState.None
        )
        val result = try {
            wisefy.getSavedNetworksAsync()
        } catch (ex: WisefyException) {
            _uiState.value = MiscScreenUIState(
                loadingState = MiscScreenLoadingState(isLoading = false),
                dialogState = MiscScreenDialogState.Failure.WisefyAsync(exception = ex)
            )
            null
        }

        when (result) {
            is GetSavedNetworksResult.SavedNetworks -> {
                _uiState.value = MiscScreenUIState(
                    loadingState = MiscScreenLoadingState(isLoading = false),
                    dialogState = MiscScreenDialogState.GetSavedNetworks.Success(savedNetworks = result.value)
                )
            }
            is GetSavedNetworksResult.Empty -> {
                _uiState.value = MiscScreenUIState(
                    loadingState = MiscScreenLoadingState(isLoading = false),
                    dialogState = MiscScreenDialogState.GetSavedNetworks.Failure
                )
            }
            null -> {
                // Case handled above in the catch clause
            }
        }
    }

    override suspend fun isWifiEnabled() {
        _uiState.value = MiscScreenUIState(
            loadingState = MiscScreenLoadingState(isLoading = true),
            dialogState = MiscScreenDialogState.None
        )
        val result = try {
            wisefy.isWifiEnabledAsync()
        } catch (ex: WisefyException) {
            _uiState.value = MiscScreenUIState(
                loadingState = MiscScreenLoadingState(isLoading = false),
                dialogState = MiscScreenDialogState.Failure.WisefyAsync(exception = ex)
            )
            null
        }

        when (result) {
            is IsWifiEnabledResult.True -> {
                _uiState.value = MiscScreenUIState(
                    loadingState = MiscScreenLoadingState(isLoading = false),
                    dialogState = MiscScreenDialogState.IsWifiEnabled.True
                )
            }
            is IsWifiEnabledResult.False -> {
                _uiState.value = MiscScreenUIState(
                    loadingState = MiscScreenLoadingState(isLoading = false),
                    dialogState = MiscScreenDialogState.IsWifiEnabled.False
                )
            }
            null -> {
                // Case handled above in the catch clause
            }
        }
    }

    override fun onDialogClosed() {
        _uiState.value = MiscScreenUIState(
            loadingState = MiscScreenLoadingState(isLoading = false),
            dialogState = MiscScreenDialogState.None
        )
    }

    override fun onGetCurrentNetworkPermissionsError() {
        _uiState.value = MiscScreenUIState(
            loadingState = MiscScreenLoadingState(isLoading = false),
            dialogState = MiscScreenDialogState.GetCurrentNetwork.PermissionsError
        )
    }

    override fun onGetNearbyAccessPointsPermissionError() {
        _uiState.value = MiscScreenUIState(
            loadingState = MiscScreenLoadingState(isLoading = false),
            dialogState = MiscScreenDialogState.GetNearbyAccessPoints.PermissionsError
        )
    }

    override fun onGetNetworkConnectionStatusPermissionError() {
        _uiState.value = MiscScreenUIState(
            loadingState = MiscScreenLoadingState(isLoading = false),
            dialogState = MiscScreenDialogState.GetNetworkConnectionStatus.PermissionsError
        )
    }

    override fun onGetSavedNetworksPermissionsError() {
        _uiState.value = MiscScreenUIState(
            loadingState = MiscScreenLoadingState(isLoading = false),
            dialogState = MiscScreenDialogState.GetSavedNetworks.PermissionsError
        )
    }

    private suspend fun disableWifi(request: DisableWifiRequest) {
        _uiState.value = MiscScreenUIState(
            loadingState = MiscScreenLoadingState(isLoading = true),
            dialogState = MiscScreenDialogState.None
        )

        val result = try {
            wisefy.disableWifiAsync(request = request)
        } catch (ex: WisefyException) {
            _uiState.value = MiscScreenUIState(
                loadingState = MiscScreenLoadingState(isLoading = false),
                dialogState = MiscScreenDialogState.Failure.WisefyAsync(exception = ex)
            )
            null
        }

        when (result) {
            is DisableWifiResult.Success -> {
                _uiState.value = MiscScreenUIState(
                    loadingState = MiscScreenLoadingState(isLoading = false),
                    dialogState = MiscScreenDialogState.DisableWifi.Success(result)
                )
            }
            is DisableWifiResult.Failure -> {
                _uiState.value = MiscScreenUIState(
                    loadingState = MiscScreenLoadingState(isLoading = false),
                    dialogState = MiscScreenDialogState.DisableWifi.Failure(result)
                )
            }
            null -> {
                // Case handled above in the catch clause
            }
        }
    }

    private suspend fun enableWifi(request: EnableWifiRequest) {
        _uiState.value = MiscScreenUIState(
            loadingState = MiscScreenLoadingState(isLoading = true),
            dialogState = MiscScreenDialogState.None
        )
        val result = try {
            wisefy.enableWifiAsync(request)
        } catch (ex: WisefyException) {
            _uiState.value = MiscScreenUIState(
                loadingState = MiscScreenLoadingState(isLoading = false),
                dialogState = MiscScreenDialogState.Failure.WisefyAsync(exception = ex)
            )
            null
        }

        when (result) {
            is EnableWifiResult.Success -> {
                _uiState.value = MiscScreenUIState(
                    loadingState = MiscScreenLoadingState(isLoading = false),
                    dialogState = MiscScreenDialogState.EnableWifi.Success(result)
                )
            }
            is EnableWifiResult.Failure -> {
                _uiState.value = MiscScreenUIState(
                    loadingState = MiscScreenLoadingState(isLoading = false),
                    dialogState = MiscScreenDialogState.EnableWifi.Failure(result)
                )
            }
            null -> {
                // Case handled above in the catch clause
            }
        }
    }
}

internal class MiscViewModelFactory(
    private val wisefy: WisefyApi,
    private val sdkUtil: SdkUtil
) : BaseViewModelFactory<MiscViewModel>(
    supportedClass = MiscViewModel::class,
    vmProvider = { DefaultMiscViewModel(wisefy, sdkUtil) }
)
