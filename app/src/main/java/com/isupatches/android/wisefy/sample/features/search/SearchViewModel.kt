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
package com.isupatches.android.wisefy.sample.features.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.isupatches.android.wisefy.WisefyApi
import com.isupatches.android.wisefy.accesspoints.callbacks.SearchForAccessPointCallbacks
import com.isupatches.android.wisefy.accesspoints.callbacks.SearchForAccessPointsCallbacks
import com.isupatches.android.wisefy.accesspoints.callbacks.SearchForSSIDCallbacks
import com.isupatches.android.wisefy.accesspoints.callbacks.SearchForSSIDsCallbacks
import com.isupatches.android.wisefy.accesspoints.entities.AccessPointData
import com.isupatches.android.wisefy.accesspoints.entities.SSIDData
import com.isupatches.android.wisefy.accesspoints.entities.SearchForMultipleAccessPointsRequest
import com.isupatches.android.wisefy.accesspoints.entities.SearchForMultipleSSIDsRequest
import com.isupatches.android.wisefy.accesspoints.entities.SearchForSingleAccessPointRequest
import com.isupatches.android.wisefy.accesspoints.entities.SearchForSingleSSIDRequest
import com.isupatches.android.wisefy.sample.entities.SSIDType
import com.isupatches.android.wisefy.sample.entities.SearchType
import com.isupatches.android.wisefy.sample.scaffolding.BaseViewModel
import com.isupatches.android.wisefy.sample.scaffolding.BaseViewModelFactory
import com.isupatches.android.wisefy.sample.util.BSSIDInputError
import com.isupatches.android.wisefy.sample.util.SSIDInputError
import com.isupatches.android.wisefy.sample.util.validateBSSID
import com.isupatches.android.wisefy.sample.util.validateSSID
import com.isupatches.android.wisefy.savednetworks.callbacks.SearchForSavedNetworkCallbacks
import com.isupatches.android.wisefy.savednetworks.callbacks.SearchForSavedNetworksCallbacks
import com.isupatches.android.wisefy.savednetworks.entities.SavedNetworkData
import com.isupatches.android.wisefy.savednetworks.entities.SearchForSavedNetworkRequest
import com.isupatches.android.wisefy.savednetworks.entities.SearchForSavedNetworksRequest

internal abstract class SearchViewModel : BaseViewModel() {
    abstract val uiState: State<SearchUIState>

    abstract fun onSearchForAccessPointPermissionError()
    abstract fun onSearchForAccessPointsPermissionError()
    abstract fun onSearchForSavedNetworkPermissionError()
    abstract fun onSearchForSavedNetworksPermissionError()
    abstract fun onSearchForSSIDPermissionError()
    abstract fun onSearchForSSIDsPermissionError()

    abstract fun searchForAccessPoint()
    abstract fun searchForAccessPoints()
    abstract fun searchForSavedNetwork()
    abstract fun searchForSavedNetworks()
    abstract fun searchForSSID()
    abstract fun searchForSSIDs()

    abstract fun onSearchNetworkInputChanged(input: String)

    abstract fun onSearchTypeSelected(searchType: SearchType)
    abstract fun onReturnFullListChanged(enabled: Boolean)
    abstract fun onFilterDuplicatesChanged(enabled: Boolean)
    abstract fun onSSIDTypeChanged(ssidType: SSIDType)

    abstract fun onSearchTimeoutValueUpdated(timeout: Int)

    abstract fun onDialogClosed()
}

internal class DefaultSearchViewModel(val wisefy: WisefyApi) : SearchViewModel() {

    private val _uiState = mutableStateOf(
        SearchUIState(
            loadingState = SearchLoadingState(isLoading = false),
            dialogState = SearchDialogState.None,
            inputState = SearchInputState.SSID.Invalid.Empty,
            searchType = SearchType.ACCESS_POINT,
            ssidType = SSIDType.SSID,
            returnFullList = true,
            filterDuplicates = true,
            searchTimeout = 1
        )
    )
    override val uiState: State<SearchUIState>
        get() = _uiState

    private var searchNetworkInput: String? = null

    override fun searchForAccessPoint() {
        if (!isInputValid()) {
            _uiState.value = uiState.value.copy(
                loadingState = SearchLoadingState(isLoading = false),
                dialogState = SearchDialogState.Failure.InputError
            )
            return
        }
        _uiState.value = uiState.value.copy(
            loadingState = SearchLoadingState(isLoading = true),
            dialogState = SearchDialogState.None
        )
        val networkInput = uiState.value.inputState
        wisefy.searchForAccessPoint(
            request = if (uiState.value.ssidType == SSIDType.SSID) {
                if (networkInput is SearchInputState.SSID.Valid) {
                    SearchForSingleAccessPointRequest.SSID(
                        regex = networkInput.value,
                        timeoutInMillis = uiState.value.searchTimeout,
                        filterDuplicates = uiState.value.filterDuplicates
                    )
                } else {
                    error("")
                }
            } else {
                if (networkInput is SearchInputState.BSSID.Valid) {
                    SearchForSingleAccessPointRequest.BSSID(
                        regex = networkInput.value,
                        timeoutInMillis = uiState.value.searchTimeout,
                        filterDuplicates = uiState.value.filterDuplicates
                    )
                } else {
                    error("")
                }
            },
            callbacks = object : SearchForAccessPointCallbacks {
                override fun onAccessPointFound(accessPoint: AccessPointData) {
                    _uiState.value = uiState.value.copy(
                        loadingState = SearchLoadingState(isLoading = false),
                        dialogState = SearchDialogState.SearchForAccessPoint.Success(accessPoint)
                    )
                }

                override fun onNoAccessPointFound() {
                    _uiState.value = uiState.value.copy(
                        loadingState = SearchLoadingState(isLoading = false),
                        dialogState = SearchDialogState.SearchForAccessPoint.NoAccessPointFound
                    )
                }

                override fun onWisefyAsyncFailure(throwable: Throwable) {
                    _uiState.value = uiState.value.copy(
                        loadingState = SearchLoadingState(isLoading = false),
                        dialogState = SearchDialogState.Failure.WisefyAsync(throwable)
                    )
                }
            }
        )
    }

    override fun searchForAccessPoints() {
        if (!isInputValid()) {
            _uiState.value = uiState.value.copy(
                loadingState = SearchLoadingState(isLoading = false),
                dialogState = SearchDialogState.Failure.InputError
            )
            return
        }
        _uiState.value = uiState.value.copy(
            loadingState = SearchLoadingState(isLoading = true),
            dialogState = SearchDialogState.None
        )
        val networkInput = uiState.value.inputState
        wisefy.searchForAccessPoints(
            request = when (uiState.value.ssidType) {
                SSIDType.SSID -> {
                    if (networkInput is SearchInputState.SSID.Valid) {
                        SearchForMultipleAccessPointsRequest.SSID(regex = networkInput.value)
                    } else {
                        error("")
                    }
                }
                SSIDType.BSSID -> {
                    if (networkInput is SearchInputState.SSID.Valid) {
                        SearchForMultipleAccessPointsRequest.BSSID(regex = networkInput.value)
                    } else {
                        error("")
                    }
                }
            },
            callbacks = object : SearchForAccessPointsCallbacks {
                override fun onAccessPointsFound(accessPoints: List<AccessPointData>) {
                    _uiState.value = uiState.value.copy(
                        loadingState = SearchLoadingState(isLoading = false),
                        dialogState = SearchDialogState.SearchForAccessPoints.Success(accessPoints)
                    )
                }

                override fun onNoAccessPointsFound() {
                    _uiState.value = uiState.value.copy(
                        loadingState = SearchLoadingState(isLoading = false),
                        dialogState = SearchDialogState.SearchForAccessPoints.NoAccessPointsFound
                    )
                }

                override fun onWisefyAsyncFailure(throwable: Throwable) {
                    _uiState.value = uiState.value.copy(
                        loadingState = SearchLoadingState(isLoading = false),
                        dialogState = SearchDialogState.Failure.WisefyAsync(throwable)
                    )
                }
            }
        )
    }

    override fun searchForSavedNetwork() {
        if (!isInputValid()) {
            _uiState.value = uiState.value.copy(
                loadingState = SearchLoadingState(isLoading = false),
                dialogState = SearchDialogState.Failure.InputError
            )
            return
        }
        _uiState.value = uiState.value.copy(
            loadingState = SearchLoadingState(isLoading = true),
            dialogState = SearchDialogState.None
        )
        val networkInput = uiState.value.inputState
        wisefy.searchForSavedNetwork(
            request = when (uiState.value.ssidType) {
                SSIDType.SSID -> {
                    if (networkInput is SearchInputState.SSID.Valid) {
                        SearchForSavedNetworkRequest.SSID(regex = networkInput.value)
                    } else {
                        error("")
                    }
                }
                SSIDType.BSSID -> {
                    if (networkInput is SearchInputState.SSID.Valid) {
                        SearchForSavedNetworkRequest.BSSID(regex = networkInput.value)
                    } else {
                        error("")
                    }
                }
            },
            callbacks = object : SearchForSavedNetworkCallbacks {
                override fun onSavedNetworkRetrieved(savedNetwork: SavedNetworkData) {
                    _uiState.value = uiState.value.copy(
                        loadingState = SearchLoadingState(isLoading = false),
                        dialogState = SearchDialogState.SearchForSavedNetwork.Success(savedNetwork)
                    )
                }

                override fun onSavedNetworkNotFound() {
                    _uiState.value = uiState.value.copy(
                        loadingState = SearchLoadingState(isLoading = false),
                        dialogState = SearchDialogState.SearchForSavedNetwork.NoSavedNetworkFound
                    )
                }

                override fun onWisefyAsyncFailure(throwable: Throwable) {
                    _uiState.value = uiState.value.copy(
                        loadingState = SearchLoadingState(isLoading = false),
                        dialogState = SearchDialogState.Failure.WisefyAsync(throwable)
                    )
                }
            }
        )
    }

    override fun searchForSavedNetworks() {
        if (!isInputValid()) {
            _uiState.value = uiState.value.copy(
                loadingState = SearchLoadingState(isLoading = false),
                dialogState = SearchDialogState.Failure.InputError
            )
            return
        }
        _uiState.value = uiState.value.copy(
            loadingState = SearchLoadingState(isLoading = true),
            dialogState = SearchDialogState.None
        )
        val networkInput = uiState.value.inputState
        wisefy.searchForSavedNetworks(
            request = when (uiState.value.ssidType) {
                SSIDType.SSID -> {
                    if (networkInput is SearchInputState.SSID.Valid) {
                        SearchForSavedNetworksRequest.SSID(regex = networkInput.value)
                    } else {
                        error("")
                    }
                }
                SSIDType.BSSID -> {
                    if (networkInput is SearchInputState.SSID.Valid) {
                        SearchForSavedNetworksRequest.BSSID(regex = networkInput.value)
                    } else {
                        error("")
                    }
                }
            },
            callbacks = object : SearchForSavedNetworksCallbacks {
                override fun onSavedNetworksRetrieved(savedNetworks: List<SavedNetworkData>) {
                    _uiState.value = uiState.value.copy(
                        loadingState = SearchLoadingState(isLoading = false),
                        dialogState = SearchDialogState.SearchForSavedNetworks.Success(savedNetworks)
                    )
                }

                override fun onNoSavedNetworksFound() {
                    _uiState.value = uiState.value.copy(
                        loadingState = SearchLoadingState(isLoading = false),
                        dialogState = SearchDialogState.SearchForSavedNetworks.NoSavedNetworksFound
                    )
                }

                override fun onWisefyAsyncFailure(throwable: Throwable) {
                    _uiState.value = uiState.value.copy(
                        loadingState = SearchLoadingState(isLoading = false),
                        dialogState = SearchDialogState.Failure.WisefyAsync(throwable)
                    )
                }
            }
        )
    }

    override fun searchForSSID() {
        if (!isInputValid()) {
            _uiState.value = uiState.value.copy(
                loadingState = SearchLoadingState(isLoading = false),
                dialogState = SearchDialogState.Failure.InputError
            )
            return
        }
        _uiState.value = uiState.value.copy(
            loadingState = SearchLoadingState(isLoading = true),
            dialogState = SearchDialogState.None
        )
        val networkInput = uiState.value.inputState
        wisefy.searchForSSID(
            request = when (uiState.value.ssidType) {
                SSIDType.SSID -> {
                    if (networkInput is SearchInputState.SSID.Valid) {
                        SearchForSingleSSIDRequest.SSID(
                            regex = networkInput.value,
                            timeoutInMillis = uiState.value.searchTimeout
                        )
                    } else {
                        error("")
                    }
                }
                SSIDType.BSSID -> {
                    if (networkInput is SearchInputState.BSSID.Valid) {
                        SearchForSingleSSIDRequest.BSSID(
                            regex = networkInput.value,
                            timeoutInMillis = uiState.value.searchTimeout
                        )
                    } else {
                        error("")
                    }
                }
            },
            callbacks = object : SearchForSSIDCallbacks {
                override fun onSSIDFound(ssid: SSIDData) {
                    _uiState.value = uiState.value.copy(
                        loadingState = SearchLoadingState(isLoading = false),
                        dialogState = SearchDialogState.SearchForSSID.Success(ssid)
                    )
                }

                override fun onSSIDNotFound() {
                    _uiState.value = uiState.value.copy(
                        loadingState = SearchLoadingState(isLoading = false),
                        dialogState = SearchDialogState.SearchForSSID.NoSSIDFound
                    )
                }

                override fun onWisefyAsyncFailure(throwable: Throwable) {
                    _uiState.value = uiState.value.copy(
                        loadingState = SearchLoadingState(isLoading = false),
                        dialogState = SearchDialogState.Failure.WisefyAsync(throwable)
                    )
                }
            }
        )
    }

    override fun searchForSSIDs() {
        if (!isInputValid()) {
            _uiState.value = uiState.value.copy(
                loadingState = SearchLoadingState(isLoading = false),
                dialogState = SearchDialogState.Failure.InputError
            )
            return
        }
        _uiState.value = uiState.value.copy(
            loadingState = SearchLoadingState(isLoading = true),
            dialogState = SearchDialogState.None
        )
        val networkInput = uiState.value.inputState
        wisefy.searchForSSIDs(
            request = when (uiState.value.ssidType) {
                SSIDType.SSID -> {
                    if (networkInput is SearchInputState.SSID.Valid) {
                        SearchForMultipleSSIDsRequest.SSID(regex = networkInput.value)
                    } else {
                        error("")
                    }
                }
                SSIDType.BSSID -> {
                    if (networkInput is SearchInputState.BSSID.Valid) {
                        SearchForMultipleSSIDsRequest.BSSID(regex = networkInput.value)
                    } else {
                        error("")
                    }
                }
            },
            callbacks = object : SearchForSSIDsCallbacks {
                override fun onSSIDsFound(ssids: List<SSIDData>) {
                    _uiState.value = uiState.value.copy(
                        loadingState = SearchLoadingState(isLoading = false),
                        dialogState = SearchDialogState.SearchForSSIDs.Success(ssids)
                    )
                }

                override fun onNoSSIDsFound() {
                    _uiState.value = uiState.value.copy(
                        loadingState = SearchLoadingState(isLoading = false),
                        dialogState = SearchDialogState.SearchForSSIDs.NoSSIDsFound
                    )
                }

                override fun onWisefyAsyncFailure(throwable: Throwable) {
                    _uiState.value = uiState.value.copy(
                        loadingState = SearchLoadingState(isLoading = false),
                        dialogState = SearchDialogState.Failure.WisefyAsync(throwable)
                    )
                }
            }
        )
    }

    override fun onSearchNetworkInputChanged(input: String) {
        this.searchNetworkInput = input
        val inputState = when (uiState.value.ssidType) {
            SSIDType.SSID -> {
                when (input.validateSSID()) {
                    SSIDInputError.EMPTY -> SearchInputState.SSID.Invalid.Empty
                    SSIDInputError.TOO_SHORT -> SearchInputState.SSID.Invalid.TooShort
                    SSIDInputError.TOO_LONG -> SearchInputState.SSID.Invalid.TooLong
                    SSIDInputError.INVALID_CHARACTERS -> SearchInputState.SSID.Invalid.InvalidCharacters
                    SSIDInputError.INVALID_START_CHARACTERS -> SearchInputState.SSID.Invalid.InvalidStartCharacters
                    SSIDInputError.LEADING_OR_TRAILING_SPACES -> SearchInputState.SSID.Invalid.LeadingOrTrailingSpaces
                    SSIDInputError.NOT_VALID_UNICODE -> SearchInputState.SSID.Invalid.InvalidUnicode
                    SSIDInputError.NONE -> SearchInputState.SSID.Valid(input)
                }
            }
            SSIDType.BSSID -> {
                when (input.validateBSSID()) {
                    BSSIDInputError.EMPTY -> SearchInputState.BSSID.Invalid.Empty
                    BSSIDInputError.INVALID -> SearchInputState.BSSID.Invalid.ImproperFormat
                    BSSIDInputError.NONE -> SearchInputState.BSSID.Valid(input)
                }
            }
        }
        _uiState.value = uiState.value.copy(inputState = inputState)
    }

    override fun onSearchTypeSelected(searchType: SearchType) {
        _uiState.value = uiState.value.copy(searchType = searchType)
    }

    override fun onReturnFullListChanged(enabled: Boolean) {
        _uiState.value = uiState.value.copy(returnFullList = enabled)
    }

    override fun onFilterDuplicatesChanged(enabled: Boolean) {
        _uiState.value = uiState.value.copy(filterDuplicates = enabled)
    }

    override fun onSSIDTypeChanged(ssidType: SSIDType) {
        _uiState.value = uiState.value.copy(ssidType = ssidType)
        searchNetworkInput?.let {
            onSearchNetworkInputChanged(it)
        }
    }

    override fun onSearchTimeoutValueUpdated(timeout: Int) {
        _uiState.value = uiState.value.copy(searchTimeout = timeout)
    }

    override fun onSearchForAccessPointPermissionError() {
        _uiState.value = uiState.value.copy(
            loadingState = SearchLoadingState(isLoading = false),
            dialogState = SearchDialogState.SearchForAccessPoint.PermissionError
        )
    }

    override fun onSearchForAccessPointsPermissionError() {
        _uiState.value = uiState.value.copy(
            loadingState = SearchLoadingState(isLoading = false),
            dialogState = SearchDialogState.SearchForAccessPoints.PermissionError
        )
    }

    override fun onSearchForSavedNetworkPermissionError() {
        _uiState.value = uiState.value.copy(
            loadingState = SearchLoadingState(isLoading = false),
            dialogState = SearchDialogState.SearchForSavedNetwork.PermissionError
        )
    }

    override fun onSearchForSavedNetworksPermissionError() {
        _uiState.value = uiState.value.copy(
            loadingState = SearchLoadingState(isLoading = false),
            dialogState = SearchDialogState.SearchForSavedNetworks.PermissionError
        )
    }

    override fun onSearchForSSIDPermissionError() {
        _uiState.value = uiState.value.copy(
            loadingState = SearchLoadingState(isLoading = false),
            dialogState = SearchDialogState.SearchForSSID.PermissionError
        )
    }

    override fun onSearchForSSIDsPermissionError() {
        _uiState.value = uiState.value.copy(
            loadingState = SearchLoadingState(isLoading = false),
            dialogState = SearchDialogState.SearchForSSIDs.PermissionError
        )
    }

    override fun onDialogClosed() {
        _uiState.value = uiState.value.copy(
            loadingState = SearchLoadingState(isLoading = false),
            dialogState = SearchDialogState.None
        )
    }

    private fun isInputValid(): Boolean {
        val networkInputState = uiState.value.inputState
        return networkInputState is SearchInputState.SSID.Valid || networkInputState is SearchInputState.BSSID.Valid
    }
}

internal class SearchViewModelFactory(
    private val wisefy: WisefyApi
) : BaseViewModelFactory<SearchViewModel>(
    supportedClass = SearchViewModel::class,
    vmProvider = { DefaultSearchViewModel(wisefy) }
)
