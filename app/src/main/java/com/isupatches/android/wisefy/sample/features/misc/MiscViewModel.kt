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
import android.os.Build
import androidx.annotation.RequiresPermission
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.isupatches.android.ktx.disableWifiAsync
import com.isupatches.android.ktx.enableWifiAsync
import com.isupatches.android.ktx.getCurrentNetworkAsync
import com.isupatches.android.ktx.getCurrentNetworkInfoAsync
import com.isupatches.android.ktx.getFrequencyAsync
import com.isupatches.android.ktx.getIPAsync
import com.isupatches.android.ktx.getNearbyAccessPointsAsync
import com.isupatches.android.ktx.getRSSIAsync
import com.isupatches.android.ktx.getSavedNetworksAsync
import com.isupatches.android.ktx.isNetwork5gHzAsync
import com.isupatches.android.ktx.isWifiEnabledAsync
import com.isupatches.android.wisefy.WisefyApi
import com.isupatches.android.wisefy.accesspoints.entities.GetNearbyAccessPointsResult
import com.isupatches.android.wisefy.accesspoints.entities.GetRSSIRequest
import com.isupatches.android.wisefy.accesspoints.entities.GetRSSIResult
import com.isupatches.android.wisefy.core.exceptions.WisefyException
import com.isupatches.android.wisefy.frequency.entities.GetFrequencyResult
import com.isupatches.android.wisefy.frequency.entities.IsNetwork5gHzResult
import com.isupatches.android.wisefy.networkconnection.callbacks.DisconnectFromCurrentNetworkCallbacks
import com.isupatches.android.wisefy.networkconnection.entities.DisconnectFromCurrentNetworkRequest
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkInfoResult
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkResult
import com.isupatches.android.wisefy.networkinfo.entities.GetIPResult
import com.isupatches.android.wisefy.sample.scaffolding.BaseViewModel
import com.isupatches.android.wisefy.sample.scaffolding.BaseViewModelFactory
import com.isupatches.android.wisefy.savednetworks.entities.GetSavedNetworksRequest
import com.isupatches.android.wisefy.savednetworks.entities.GetSavedNetworksResult
import com.isupatches.android.wisefy.wifi.entities.DisableWifiResult
import com.isupatches.android.wisefy.wifi.entities.EnableWifiResult
import com.isupatches.android.wisefy.wifi.entities.IsWifiEnabledResult

internal abstract class MiscViewModel : BaseViewModel() {
    abstract val uiState: State<MiscScreenUIState>

    abstract fun onDialogClosed()

    abstract fun onGetCurrentNetworkInfoPermissionsError()
    abstract fun onGetFrequencyPermissionsError()
    abstract fun onGetIPPermissionsError()
    abstract fun onGetNearbyAccessPointsPermissionError()
    abstract fun onGetRSSIPermissionsError()
    abstract fun onGetSavedNetworksPermissionsError()
    abstract fun isNetwork5gHzPermissionsError()

    abstract suspend fun disableWifi()

    abstract fun disconnectFromCurrentNetwork()

    abstract suspend fun enableWifi()

    abstract suspend fun getCurrentNetwork()

    @RequiresPermission(ACCESS_NETWORK_STATE)
    abstract suspend fun getCurrentNetworkInfo()

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_NETWORK_STATE])
    abstract suspend fun getFrequency()

    @RequiresPermission(ACCESS_NETWORK_STATE)
    abstract suspend fun getIP()

    @RequiresPermission(ACCESS_FINE_LOCATION)
    abstract suspend fun getNearbyAccessPoints()

    @RequiresPermission(ACCESS_FINE_LOCATION)
    abstract suspend fun getRSSI()

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    abstract suspend fun getSavedNetworks()

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_NETWORK_STATE])
    abstract suspend fun isNetwork5gHz()

    abstract suspend fun isWifiEnabled()
}

internal class DefaultMiscViewModel(
    private val wisefy: WisefyApi,
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
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            _uiState.value = MiscScreenUIState(
                loadingState = MiscScreenLoadingState(isLoading = true),
                dialogState = MiscScreenDialogState.None
            )

            val result = try {
                wisefy.disableWifiAsync()
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
                        dialogState = MiscScreenDialogState.DisableWifi.Success
                    )
                }
                is DisableWifiResult.Failure -> {
                    _uiState.value = MiscScreenUIState(
                        loadingState = MiscScreenLoadingState(isLoading = false),
                        dialogState = MiscScreenDialogState.DisableWifi.Failure
                    )
                }
                null -> {
                    // Case handled above in the catch clause
                }
            }
        } else {
            _uiState.value = MiscScreenUIState(
                loadingState = MiscScreenLoadingState(isLoading = false),
                dialogState = MiscScreenDialogState.DisableWifi.DisplayAndroidQMessage
            )
        }
    }

    override fun disconnectFromCurrentNetwork() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
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
        } else {
            _uiState.value = MiscScreenUIState(
                loadingState = MiscScreenLoadingState(isLoading = false),
                dialogState = MiscScreenDialogState.DisconnectFromCurrentNetwork.DisplayAndroidQMessage
            )
        }
    }

    override suspend fun enableWifi() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            _uiState.value = MiscScreenUIState(
                loadingState = MiscScreenLoadingState(isLoading = true),
                dialogState = MiscScreenDialogState.None
            )
            val result = try {
                wisefy.enableWifiAsync()
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
                        dialogState = MiscScreenDialogState.EnableWifi.Success
                    )
                }
                is EnableWifiResult.Failure -> {
                    _uiState.value = MiscScreenUIState(
                        loadingState = MiscScreenLoadingState(isLoading = false),
                        dialogState = MiscScreenDialogState.EnableWifi.Failure
                    )
                }
                null -> {
                    // Case handled above in the catch clause
                }
            }
        } else {
            _uiState.value = MiscScreenUIState(
                loadingState = MiscScreenLoadingState(isLoading = false),
                dialogState = MiscScreenDialogState.EnableWifi.DisplayAndroidQMessage
            )
        }
    }

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
                    dialogState = MiscScreenDialogState.GetCurrentNetwork.Success(network = result.data)
                )
            }
            null -> {
                // Case handled above in the catch clause
            }
        }
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override suspend fun getCurrentNetworkInfo() {
        _uiState.value = MiscScreenUIState(
            loadingState = MiscScreenLoadingState(isLoading = true),
            dialogState = MiscScreenDialogState.None
        )
        val result = try {
            wisefy.getCurrentNetworkInfoAsync()
        } catch (ex: WisefyException) {
            _uiState.value = MiscScreenUIState(
                loadingState = MiscScreenLoadingState(isLoading = false),
                dialogState = MiscScreenDialogState.Failure.WisefyAsync(exception = ex)
            )
            null
        }

        when (result) {
            is GetCurrentNetworkInfoResult.Empty -> {
                _uiState.value = MiscScreenUIState(
                    loadingState = MiscScreenLoadingState(isLoading = false),
                    dialogState = MiscScreenDialogState.GetCurrentNetworkInfo.Failure
                )
            }
            is GetCurrentNetworkInfoResult.NetworkInfo -> {
                _uiState.value = MiscScreenUIState(
                    loadingState = MiscScreenLoadingState(isLoading = false),
                    dialogState = MiscScreenDialogState.GetCurrentNetworkInfo.Success(networkInfo = result.data)
                )
            }
            null -> {
                // Case handled above in the catch clause
            }
        }
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_NETWORK_STATE])
    override suspend fun getFrequency() {
        _uiState.value = MiscScreenUIState(
            loadingState = MiscScreenLoadingState(isLoading = true),
            dialogState = MiscScreenDialogState.None
        )

        val result = try {
            wisefy.getFrequencyAsync()
        } catch (ex: WisefyException) {
            _uiState.value = MiscScreenUIState(
                loadingState = MiscScreenLoadingState(isLoading = false),
                dialogState = MiscScreenDialogState.Failure.WisefyAsync(exception = ex)
            )
            null
        }

        when (result) {
            is GetFrequencyResult.Empty -> {
                _uiState.value = MiscScreenUIState(
                    loadingState = MiscScreenLoadingState(isLoading = false),
                    dialogState = MiscScreenDialogState.GetFrequency.Failure
                )
            }
            is GetFrequencyResult.WithFrequency -> {
                _uiState.value = MiscScreenUIState(
                    loadingState = MiscScreenLoadingState(isLoading = false),
                    dialogState = MiscScreenDialogState.GetFrequency.Success(frequency = result.data)
                )
            }
            null -> {
                // Case handled above in the catch clause
            }
        }
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override suspend fun getIP() {
        _uiState.value = MiscScreenUIState(
            loadingState = MiscScreenLoadingState(isLoading = true),
            dialogState = MiscScreenDialogState.None
        )
        val result = try {
            wisefy.getIPAsync()
        } catch (ex: WisefyException) {
            _uiState.value = MiscScreenUIState(
                loadingState = MiscScreenLoadingState(isLoading = false),
                dialogState = MiscScreenDialogState.Failure.WisefyAsync(exception = ex)
            )
            null
        }

        when (result) {
            is GetIPResult.Empty -> {
                _uiState.value = MiscScreenUIState(
                    loadingState = MiscScreenLoadingState(isLoading = false),
                    dialogState = MiscScreenDialogState.GetIP.Failure
                )
            }
            is GetIPResult.IPAddress -> {
                _uiState.value = MiscScreenUIState(
                    loadingState = MiscScreenLoadingState(isLoading = false),
                    dialogState = MiscScreenDialogState.GetIP.Success(ip = result.data)
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
            wisefy.getNearbyAccessPointsAsync()
        } catch (ex: WisefyException) {
            _uiState.value = MiscScreenUIState(
                loadingState = MiscScreenLoadingState(isLoading = false),
                dialogState = MiscScreenDialogState.Failure.WisefyAsync(exception = ex)
            )
            null
        }

        when (result) {
            is GetNearbyAccessPointsResult.Empty -> {
                _uiState.value = MiscScreenUIState(
                    loadingState = MiscScreenLoadingState(isLoading = false),
                    dialogState = MiscScreenDialogState.GetNearbyAccessPoints.Failure
                )
            }
            is GetNearbyAccessPointsResult.AccessPoints -> {
                _uiState.value = MiscScreenUIState(
                    loadingState = MiscScreenLoadingState(isLoading = false),
                    dialogState = MiscScreenDialogState.GetNearbyAccessPoints.Success(accessPoints = result.data)
                )
            }
            null -> {
                // Case handled above in the catch clause
            }
        }
    }

    override suspend fun getRSSI() {
        _uiState.value = MiscScreenUIState(
            loadingState = MiscScreenLoadingState(isLoading = true),
            dialogState = MiscScreenDialogState.None
        )

        val result = try {
            wisefy.getRSSIAsync(request = GetRSSIRequest.SSID("", 30))
        } catch (ex: WisefyException) {
            _uiState.value = MiscScreenUIState(
                loadingState = MiscScreenLoadingState(isLoading = false),
                dialogState = MiscScreenDialogState.Failure.WisefyAsync(exception = ex)
            )
            null
        }

        when (result) {
            is GetRSSIResult.Empty -> {
                _uiState.value = MiscScreenUIState(
                    loadingState = MiscScreenLoadingState(isLoading = false),
                    dialogState = MiscScreenDialogState.GetRSSI.Failure
                )
            }
            is GetRSSIResult.RSSI -> {
                _uiState.value = MiscScreenUIState(
                    loadingState = MiscScreenLoadingState(isLoading = false),
                    dialogState = MiscScreenDialogState.GetRSSI.Success(rssi = result.data)
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
            wisefy.getSavedNetworksAsync(
                request = GetSavedNetworksRequest(),
            )
        } catch (ex: WisefyException) {
            _uiState.value = MiscScreenUIState(
                loadingState = MiscScreenLoadingState(isLoading = false),
                dialogState = MiscScreenDialogState.Failure.WisefyAsync(exception = ex)
            )
            null
        }

        when (result) {
            is GetSavedNetworksResult.Success.SavedNetworks -> {
                _uiState.value = MiscScreenUIState(
                    loadingState = MiscScreenLoadingState(isLoading = false),
                    dialogState = MiscScreenDialogState.GetSavedNetworks.Success(savedNetworks = result.data)
                )
            }
            is GetSavedNetworksResult.Success.Empty -> {
                _uiState.value = MiscScreenUIState(
                    loadingState = MiscScreenLoadingState(isLoading = false),
                    dialogState = MiscScreenDialogState.GetSavedNetworks.Failure
                )
            }
            is GetSavedNetworksResult.Failure.Assertion -> {
                _uiState.value = MiscScreenUIState(
                    loadingState = MiscScreenLoadingState(isLoading = false),
                    dialogState = MiscScreenDialogState.Failure.WisefyAsync(
                        exception = WisefyException(message = result.message, throwable = null)
                    )
                )
            }
            null -> {
                // Case handled above in the catch clause
            }
        }
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_NETWORK_STATE])
    override suspend fun isNetwork5gHz() {
        _uiState.value = MiscScreenUIState(
            loadingState = MiscScreenLoadingState(isLoading = true),
            dialogState = MiscScreenDialogState.None
        )
        val result = try {
            wisefy.isNetwork5gHzAsync()
        } catch (ex: WisefyException) {
            _uiState.value = MiscScreenUIState(
                loadingState = MiscScreenLoadingState(isLoading = false),
                dialogState = MiscScreenDialogState.Failure.WisefyAsync(exception = ex)
            )
            null
        }

        when (result) {
            is IsNetwork5gHzResult.True -> {
                _uiState.value = MiscScreenUIState(
                    loadingState = MiscScreenLoadingState(isLoading = false),
                    dialogState = MiscScreenDialogState.IsNetwork5gHz.True
                )
            }
            is IsNetwork5gHzResult.False -> {
                _uiState.value = MiscScreenUIState(
                    loadingState = MiscScreenLoadingState(isLoading = false),
                    dialogState = MiscScreenDialogState.IsNetwork5gHz.False
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

    override fun onGetCurrentNetworkInfoPermissionsError() {
        _uiState.value = MiscScreenUIState(
            loadingState = MiscScreenLoadingState(isLoading = false),
            dialogState = MiscScreenDialogState.GetCurrentNetworkInfo.PermissionsError
        )
    }

    override fun onGetFrequencyPermissionsError() {
        _uiState.value = MiscScreenUIState(
            loadingState = MiscScreenLoadingState(isLoading = false),
            dialogState = MiscScreenDialogState.GetFrequency.PermissionsError
        )
    }

    override fun onGetIPPermissionsError() {
        _uiState.value = MiscScreenUIState(
            loadingState = MiscScreenLoadingState(isLoading = false),
            dialogState = MiscScreenDialogState.GetIP.PermissionsError
        )
    }

    override fun onGetNearbyAccessPointsPermissionError() {
        _uiState.value = MiscScreenUIState(
            loadingState = MiscScreenLoadingState(isLoading = false),
            dialogState = MiscScreenDialogState.GetNearbyAccessPoints.PermissionsError
        )
    }

    override fun onGetRSSIPermissionsError() {
        _uiState.value = MiscScreenUIState(
            loadingState = MiscScreenLoadingState(isLoading = false),
            dialogState = MiscScreenDialogState.GetRSSI.PermissionsError
        )
    }

    override fun onGetSavedNetworksPermissionsError() {
        _uiState.value = MiscScreenUIState(
            loadingState = MiscScreenLoadingState(isLoading = false),
            dialogState = MiscScreenDialogState.GetSavedNetworks.PermissionsError
        )
    }

    override fun isNetwork5gHzPermissionsError() {
        _uiState.value = MiscScreenUIState(
            loadingState = MiscScreenLoadingState(isLoading = false),
            dialogState = MiscScreenDialogState.IsNetwork5gHz.PermissionsError
        )
    }
}

internal class MiscViewModelFactory(private val wisefy: WisefyApi) : BaseViewModelFactory<MiscViewModel>(
    supportedClass = MiscViewModel::class,
    vmProvider = { DefaultMiscViewModel(wisefy) }
)
