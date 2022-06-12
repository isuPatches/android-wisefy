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

import android.os.Build
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.isupatches.android.wisefy.WisefyApi
import com.isupatches.android.wisefy.accesspoints.callbacks.GetNearbyAccessPointCallbacks
import com.isupatches.android.wisefy.accesspoints.entities.AccessPointData
import com.isupatches.android.wisefy.accesspoints.entities.GetNearbyAccessPointsRequest
import com.isupatches.android.wisefy.frequency.callbacks.GetFrequencyCallbacks
import com.isupatches.android.wisefy.frequency.entities.FrequencyData
import com.isupatches.android.wisefy.frequency.entities.GetFrequencyRequest
import com.isupatches.android.wisefy.networkinfo.callbacks.GetCurrentNetworkCallbacks
import com.isupatches.android.wisefy.networkinfo.callbacks.GetIPCallbacks
import com.isupatches.android.wisefy.networkinfo.callbacks.GetNetworkInfoCallbacks
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkRequest
import com.isupatches.android.wisefy.networkinfo.entities.GetIPRequest
import com.isupatches.android.wisefy.networkinfo.entities.GetNetworkInfoRequest
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

    abstract fun onGetFrequencyPermissionsError()
    abstract fun onGetIPPermissionsError()
    abstract fun onGetNearbyAccessPointsPermissionError()
    abstract fun onGetSavedNetworksPermissionsError()

    abstract fun disableWifi()
    abstract fun enableWifi()
    abstract fun getCurrentNetwork()
    abstract fun getCurrentNetworkInfo()
    abstract fun getFrequency()
    abstract fun getIP()
    abstract fun getNearbyAccessPoints()
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
                            dialogState = MiscScreenDialogState.Success.DisableWifi
                        )
                    }

                    override fun onFailureDisablingWifi() {
                        _uiState.value = MiscScreenUIState(
                            loadingState = MiscScreenLoadingState(isLoading = false),
                            dialogState = MiscScreenDialogState.Failure.DisableWifi
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
                dialogState = MiscScreenDialogState.Failure.DisplayAndroidQMessage
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
                            dialogState = MiscScreenDialogState.Success.EnableWifi
                        )
                    }

                    override fun onFailureEnablingWifi() {
                        _uiState.value = MiscScreenUIState(
                            loadingState = MiscScreenLoadingState(isLoading = false),
                            dialogState = MiscScreenDialogState.Failure.EnableWifi
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
                dialogState = MiscScreenDialogState.Failure.DisplayAndroidQMessage
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
                        dialogState = MiscScreenDialogState.Success.GetCurrentNetwork(network = network)
                    )
                }

                override fun onNoCurrentNetwork() {
                    _uiState.value = MiscScreenUIState(
                        loadingState = MiscScreenLoadingState(isLoading = false),
                        dialogState = MiscScreenDialogState.Failure.GetCurrentNetwork
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
        wisefy.getNetworkInfo(
            request = GetNetworkInfoRequest(),
            callbacks = object : GetNetworkInfoCallbacks {
                override fun onNetworkInfoRetrieved(networkInfo: NetworkInfoData) {
                    _uiState.value = MiscScreenUIState(
                        loadingState = MiscScreenLoadingState(isLoading = false),
                        dialogState = MiscScreenDialogState.Success.GetCurrentNetworkInfo(networkInfo = networkInfo)
                    )
                }

                override fun onNoNetworkToRetrieveInfo() {
                    _uiState.value = MiscScreenUIState(
                        loadingState = MiscScreenLoadingState(isLoading = false),
                        dialogState = MiscScreenDialogState.Failure.GetCurrentNetworkInfo
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
                        dialogState = MiscScreenDialogState.Success.GetFrequency(frequency = frequency)
                    )
                }

                override fun onFailureRetrievingFrequency() {
                    _uiState.value = MiscScreenUIState(
                        loadingState = MiscScreenLoadingState(isLoading = false),
                        dialogState = MiscScreenDialogState.Failure.GetFrequency
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
                        dialogState = MiscScreenDialogState.Success.GetIP(ip = ip)
                    )
                }

                override fun onFailureRetrievingIP() {
                    _uiState.value = MiscScreenUIState(
                        loadingState = MiscScreenLoadingState(isLoading = false),
                        dialogState = MiscScreenDialogState.Failure.GetIP
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
                        dialogState = MiscScreenDialogState.Success.GetNearbyAccessPoints(accessPoints = accessPoints)
                    )
                }

                override fun onNoNearbyAccessPoints() {
                    _uiState.value = MiscScreenUIState(
                        loadingState = MiscScreenLoadingState(isLoading = false),
                        dialogState = MiscScreenDialogState.Failure.GetNearbyAccessPoints
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
                        dialogState = MiscScreenDialogState.Success.GetSavedNetworks(savedNetworks = savedNetworks)
                    )
                }

                override fun onNoSavedNetworksFound() {
                    _uiState.value = MiscScreenUIState(
                        loadingState = MiscScreenLoadingState(isLoading = false),
                        dialogState = MiscScreenDialogState.Failure.GetSavedNetworks
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

    override fun onGetFrequencyPermissionsError() {
        _uiState.value = MiscScreenUIState(
            loadingState = MiscScreenLoadingState(isLoading = false),
            dialogState = MiscScreenDialogState.PermissionsError.GetFrequency
        )
    }

    override fun onGetIPPermissionsError() {
        _uiState.value = MiscScreenUIState(
            loadingState = MiscScreenLoadingState(isLoading = false),
            dialogState = MiscScreenDialogState.PermissionsError.GetIP
        )
    }

    override fun onGetNearbyAccessPointsPermissionError() {
        _uiState.value = MiscScreenUIState(
            loadingState = MiscScreenLoadingState(isLoading = false),
            dialogState = MiscScreenDialogState.PermissionsError.GetNearbyAccessPoints
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
