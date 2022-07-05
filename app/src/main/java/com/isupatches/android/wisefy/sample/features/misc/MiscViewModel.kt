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

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.os.Build
import androidx.annotation.RequiresPermission
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.isupatches.android.wisefy.WisefyApi
import com.isupatches.android.wisefy.accesspoints.callbacks.GetNearbyAccessPointCallbacks
import com.isupatches.android.wisefy.accesspoints.entities.AccessPointData
import com.isupatches.android.wisefy.accesspoints.entities.GetNearbyAccessPointsRequest
import com.isupatches.android.wisefy.frequency.callbacks.GetFrequencyCallbacks
import com.isupatches.android.wisefy.frequency.entities.FrequencyData
import com.isupatches.android.wisefy.frequency.entities.GetFrequencyRequest
import com.isupatches.android.wisefy.networkconnection.callbacks.DisconnectFromCurrentNetworkCallbacks
import com.isupatches.android.wisefy.networkconnection.entities.DisconnectFromCurrentNetworkRequest
import com.isupatches.android.wisefy.networkinfo.callbacks.GetCurrentNetworkCallbacks
import com.isupatches.android.wisefy.networkinfo.callbacks.GetCurrentNetworkInfoCallbacks
import com.isupatches.android.wisefy.networkinfo.callbacks.GetIPCallbacks
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkInfoRequest
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkRequest
import com.isupatches.android.wisefy.networkinfo.entities.GetIPRequest
import com.isupatches.android.wisefy.networkinfo.entities.IPData
import com.isupatches.android.wisefy.networkinfo.entities.NetworkData
import com.isupatches.android.wisefy.networkinfo.entities.NetworkInfoData
import com.isupatches.android.wisefy.sample.scaffolding.BaseViewModel
import com.isupatches.android.wisefy.sample.scaffolding.BaseViewModelFactory
import com.isupatches.android.wisefy.savednetworks.callbacks.GetSavedNetworksCallbacks
import com.isupatches.android.wisefy.savednetworks.entities.GetSavedNetworksRequest
import com.isupatches.android.wisefy.savednetworks.entities.SavedNetworkData
import com.isupatches.android.wisefy.wifi.callbacks.DisableWifiCallbacks
import com.isupatches.android.wisefy.wifi.callbacks.EnableWifiCallbacks
import com.isupatches.android.wisefy.wifi.entities.DisableWifiRequest
import com.isupatches.android.wisefy.wifi.entities.EnableWifiRequest

internal abstract class MiscViewModel : BaseViewModel() {
    abstract val uiState: State<MiscScreenUIState>

    abstract fun onDialogClosed()

    abstract fun onDisconnectFromCurrentNetworkPermissionsError()
    abstract fun onGetFrequencyPermissionsError()
    abstract fun onGetIPPermissionsError()
    abstract fun onGetNearbyAccessPointsPermissionError()
    abstract fun onGetSavedNetworksPermissionsError()

    abstract fun disableWifi()
    abstract fun disconnectFromCurrentNetwork()
    abstract fun enableWifi()
    abstract fun getCurrentNetwork()
    abstract fun getCurrentNetworkInfo()
    abstract fun getFrequency()
    abstract fun getIP()
    abstract fun getNearbyAccessPoints()

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    abstract fun getSavedNetworks()
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

    override fun disableWifi() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            _uiState.value = MiscScreenUIState(
                loadingState = MiscScreenLoadingState(isLoading = true),
                dialogState = MiscScreenDialogState.None
            )
            wisefy.disableWifi(
                request = DisableWifiRequest(),
                callbacks = object : DisableWifiCallbacks {
                    override fun onWifiDisabled() {
                        _uiState.value = MiscScreenUIState(
                            loadingState = MiscScreenLoadingState(isLoading = false),
                            dialogState = MiscScreenDialogState.DisableWifi.Success
                        )
                    }

                    override fun onFailureDisablingWifi() {
                        _uiState.value = MiscScreenUIState(
                            loadingState = MiscScreenLoadingState(isLoading = false),
                            dialogState = MiscScreenDialogState.DisableWifi.Failure
                        )
                    }

                    override fun onWisefyAsyncFailure(throwable: Throwable) {
                        _uiState.value = MiscScreenUIState(
                            loadingState = MiscScreenLoadingState(isLoading = false),
                            dialogState = MiscScreenDialogState.Failure.WisefyAsync(throwable = throwable)
                        )
                    }
                }
            )
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

                    override fun onWisefyAsyncFailure(throwable: Throwable) {
                        _uiState.value = MiscScreenUIState(
                            loadingState = MiscScreenLoadingState(isLoading = false),
                            dialogState = MiscScreenDialogState.Failure.WisefyAsync(
                                throwable = throwable
                            )
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

    override fun enableWifi() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            _uiState.value = MiscScreenUIState(
                loadingState = MiscScreenLoadingState(isLoading = true),
                dialogState = MiscScreenDialogState.None
            )
            wisefy.enableWifi(
                request = EnableWifiRequest(),
                callbacks = object : EnableWifiCallbacks {
                    override fun onWifiEnabled() {
                        _uiState.value = MiscScreenUIState(
                            loadingState = MiscScreenLoadingState(isLoading = false),
                            dialogState = MiscScreenDialogState.EnableWifi.Success
                        )
                    }

                    override fun onFailureEnablingWifi() {
                        _uiState.value = MiscScreenUIState(
                            loadingState = MiscScreenLoadingState(isLoading = false),
                            dialogState = MiscScreenDialogState.EnableWifi.Failure
                        )
                    }

                    override fun onWisefyAsyncFailure(throwable: Throwable) {
                        _uiState.value = MiscScreenUIState(
                            loadingState = MiscScreenLoadingState(isLoading = false),
                            dialogState = MiscScreenDialogState.Failure.WisefyAsync(throwable = throwable)
                        )
                    }
                }
            )
        } else {
            _uiState.value = MiscScreenUIState(
                loadingState = MiscScreenLoadingState(isLoading = false),
                dialogState = MiscScreenDialogState.EnableWifi.DisplayAndroidQMessage
            )
        }
    }

    override fun getCurrentNetwork() {
        _uiState.value = MiscScreenUIState(
            loadingState = MiscScreenLoadingState(isLoading = true),
            dialogState = MiscScreenDialogState.None
        )
        wisefy.getCurrentNetwork(
            request = GetCurrentNetworkRequest(),
            callbacks = object : GetCurrentNetworkCallbacks {
                override fun onCurrentNetworkRetrieved(network: NetworkData) {
                    _uiState.value = MiscScreenUIState(
                        loadingState = MiscScreenLoadingState(isLoading = false),
                        dialogState = MiscScreenDialogState.GetCurrentNetwork.Success(network = network)
                    )
                }

                override fun onNoCurrentNetwork() {
                    _uiState.value = MiscScreenUIState(
                        loadingState = MiscScreenLoadingState(isLoading = false),
                        dialogState = MiscScreenDialogState.GetCurrentNetwork.Failure
                    )
                }

                override fun onWisefyAsyncFailure(throwable: Throwable) {
                    _uiState.value = MiscScreenUIState(
                        loadingState = MiscScreenLoadingState(isLoading = false),
                        dialogState = MiscScreenDialogState.Failure.WisefyAsync(throwable = throwable)
                    )
                }
            }
        )
    }

    override fun getCurrentNetworkInfo() {
        _uiState.value = MiscScreenUIState(
            loadingState = MiscScreenLoadingState(isLoading = true),
            dialogState = MiscScreenDialogState.None
        )
        wisefy.getCurrentNetworkInfo(
            request = GetCurrentNetworkInfoRequest(),
            callbacks = object : GetCurrentNetworkInfoCallbacks {
                override fun onCurrentNetworkInfoRetrieved(networkInfo: NetworkInfoData) {
                    _uiState.value = MiscScreenUIState(
                        loadingState = MiscScreenLoadingState(isLoading = false),
                        dialogState = MiscScreenDialogState.GetCurrentNetworkInfo.Success(networkInfo = networkInfo)
                    )
                }

                override fun onNoCurrentNetworkToRetrieveInfo() {
                    _uiState.value = MiscScreenUIState(
                        loadingState = MiscScreenLoadingState(isLoading = false),
                        dialogState = MiscScreenDialogState.GetCurrentNetworkInfo.Failure
                    )
                }

                override fun onWisefyAsyncFailure(throwable: Throwable) {
                    _uiState.value = MiscScreenUIState(
                        loadingState = MiscScreenLoadingState(isLoading = false),
                        dialogState = MiscScreenDialogState.Failure.WisefyAsync(throwable = throwable)
                    )
                }
            }
        )
    }

    override fun getFrequency() {
        _uiState.value = MiscScreenUIState(
            loadingState = MiscScreenLoadingState(isLoading = true),
            dialogState = MiscScreenDialogState.None
        )
        wisefy.getFrequency(
            request = GetFrequencyRequest.CurrentNetwork,
            callbacks = object : GetFrequencyCallbacks {
                override fun onFrequencyRetrieved(frequency: FrequencyData) {
                    _uiState.value = MiscScreenUIState(
                        loadingState = MiscScreenLoadingState(isLoading = false),
                        dialogState = MiscScreenDialogState.GetFrequency.Success(frequency = frequency)
                    )
                }

                override fun onFailureRetrievingFrequency() {
                    _uiState.value = MiscScreenUIState(
                        loadingState = MiscScreenLoadingState(isLoading = false),
                        dialogState = MiscScreenDialogState.GetFrequency.Failure
                    )
                }

                override fun onWisefyAsyncFailure(throwable: Throwable) {
                    _uiState.value = MiscScreenUIState(
                        loadingState = MiscScreenLoadingState(isLoading = false),
                        dialogState = MiscScreenDialogState.Failure.WisefyAsync(throwable = throwable)
                    )
                }
            }
        )
    }

    override fun getIP() {
        _uiState.value = MiscScreenUIState(
            loadingState = MiscScreenLoadingState(isLoading = true),
            dialogState = MiscScreenDialogState.None
        )
        wisefy.getIP(
            request = GetIPRequest(),
            callbacks = object : GetIPCallbacks {
                override fun onIPRetrieved(ip: IPData) {
                    _uiState.value = MiscScreenUIState(
                        loadingState = MiscScreenLoadingState(isLoading = false),
                        dialogState = MiscScreenDialogState.GetIP.Success(ip = ip)
                    )
                }

                override fun onFailureRetrievingIP() {
                    _uiState.value = MiscScreenUIState(
                        loadingState = MiscScreenLoadingState(isLoading = false),
                        dialogState = MiscScreenDialogState.GetIP.Failure
                    )
                }

                override fun onWisefyAsyncFailure(throwable: Throwable) {
                    _uiState.value = MiscScreenUIState(
                        loadingState = MiscScreenLoadingState(isLoading = false),
                        dialogState = MiscScreenDialogState.Failure.WisefyAsync(throwable = throwable)
                    )
                }
            }
        )
    }

    override fun getNearbyAccessPoints() {
        _uiState.value = MiscScreenUIState(
            loadingState = MiscScreenLoadingState(isLoading = true),
            dialogState = MiscScreenDialogState.None
        )
        wisefy.getNearbyAccessPoints(
            request = GetNearbyAccessPointsRequest(),
            callbacks = object : GetNearbyAccessPointCallbacks {
                override fun onNearbyAccessPointsRetrieved(accessPoints: List<AccessPointData>) {
                    _uiState.value = MiscScreenUIState(
                        loadingState = MiscScreenLoadingState(isLoading = false),
                        dialogState = MiscScreenDialogState.GetNearbyAccessPoints.Success(accessPoints = accessPoints)
                    )
                }

                override fun onNoNearbyAccessPoints() {
                    _uiState.value = MiscScreenUIState(
                        loadingState = MiscScreenLoadingState(isLoading = false),
                        dialogState = MiscScreenDialogState.GetNearbyAccessPoints.Failure
                    )
                }

                override fun onWisefyAsyncFailure(throwable: Throwable) {
                    _uiState.value = MiscScreenUIState(
                        loadingState = MiscScreenLoadingState(isLoading = false),
                        dialogState = MiscScreenDialogState.Failure.WisefyAsync(throwable = throwable)
                    )
                }
            }
        )
    }

    @RequiresPermission(allOf = [ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun getSavedNetworks() {
        _uiState.value = MiscScreenUIState(
            loadingState = MiscScreenLoadingState(isLoading = true),
            dialogState = MiscScreenDialogState.None
        )
        wisefy.getSavedNetworks(
            request = GetSavedNetworksRequest(),
            callbacks = object : GetSavedNetworksCallbacks {
                override fun onSavedNetworksRetrieved(savedNetworks: List<SavedNetworkData>) {
                    _uiState.value = MiscScreenUIState(
                        loadingState = MiscScreenLoadingState(isLoading = false),
                        dialogState = MiscScreenDialogState.GetSavedNetworks.Success(savedNetworks = savedNetworks)
                    )
                }

                override fun onNoSavedNetworksFound() {
                    _uiState.value = MiscScreenUIState(
                        loadingState = MiscScreenLoadingState(isLoading = false),
                        dialogState = MiscScreenDialogState.GetSavedNetworks.Failure
                    )
                }

                override fun onWisefyAsyncFailure(throwable: Throwable) {
                    _uiState.value = MiscScreenUIState(
                        loadingState = MiscScreenLoadingState(isLoading = false),
                        dialogState = MiscScreenDialogState.Failure.WisefyAsync(throwable = throwable)
                    )
                }
            }
        )
    }

    override fun onDialogClosed() {
        _uiState.value = MiscScreenUIState(
            loadingState = MiscScreenLoadingState(isLoading = false),
            dialogState = MiscScreenDialogState.None
        )
    }

    override fun onDisconnectFromCurrentNetworkPermissionsError() {
        _uiState.value = MiscScreenUIState(
            loadingState = MiscScreenLoadingState(isLoading = false),
            dialogState = MiscScreenDialogState.DisconnectFromCurrentNetwork.PermissionsError
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

    override fun onGetSavedNetworksPermissionsError() {
        _uiState.value = MiscScreenUIState(
            loadingState = MiscScreenLoadingState(isLoading = false),
            dialogState = MiscScreenDialogState.PermissionsError.GetSavedNetworks
        )
    }
}

internal class MiscViewModelFactory(private val wisefy: WisefyApi) : BaseViewModelFactory<MiscViewModel>(
    supportedClass = MiscViewModel::class,
    vmProvider = { DefaultMiscViewModel(wisefy) }
)
