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
import com.isupatches.android.wisefy.sample.entities.SearchType
import com.isupatches.android.wisefy.sample.scaffolding.BaseViewModel
import com.isupatches.android.wisefy.sample.scaffolding.BaseViewModelFactory
import com.isupatches.android.wisefy.sample.util.SSIDInputError
import com.isupatches.android.wisefy.sample.util.validateSSID
import com.isupatches.android.wisefy.savednetworks.callbacks.SearchForSavedNetworkCallbacks
import com.isupatches.android.wisefy.savednetworks.callbacks.SearchForSavedNetworksCallbacks
import com.isupatches.android.wisefy.savednetworks.entities.SavedNetworkData
import com.isupatches.android.wisefy.savednetworks.entities.SearchForSavedNetworkRequest
import com.isupatches.android.wisefy.savednetworks.entities.SearchForSavedNetworksRequest

internal abstract class SearchViewModel : BaseViewModel() {
    abstract val uiState: State<SearchUIState>
    abstract val searchType: State<SearchType>
    abstract val returnFullList: State<Boolean>
    abstract val filterDuplicates: State<Boolean>
    abstract val searchTimeout: State<Int>
    abstract val inputState: State<SearchInputState>

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

    abstract fun onAccessPointSearchTypeSelected()
    abstract fun onSSIDSearchTypeSelected()
    abstract fun onSavedNetworkSearchTypeSelected()

    abstract fun onReturnFullListSelected()
    abstract fun onReturnFullListDeselected()

    abstract fun onFilterDuplicatesSelected()
    abstract fun onFilterDuplicatesDeselected()

    abstract fun onSearchTimeoutValueUpdated(timeout: Int)

    abstract fun onDialogClosed()
}

internal class DefaultSearchViewModel(val wisefy: WisefyApi) : SearchViewModel() {

    private val _uiState = mutableStateOf(
        SearchUIState(
            loadingState = SearchLoadingState(isLoading = false),
            dialogState = SearchDialogState.None
        )
    )
    override val uiState: State<SearchUIState>
        get() = _uiState

    private val _searchType = mutableStateOf(SearchType.ACCESS_POINT)
    override val searchType: State<SearchType>
        get() = _searchType

    private val _returnFullList = mutableStateOf(true)
    override val returnFullList: State<Boolean>
        get() = _returnFullList

    private val _filterDuplicates = mutableStateOf(true)
    override val filterDuplicates: State<Boolean>
        get() = _filterDuplicates

    private val _searchTimeout = mutableStateOf(1)
    override val searchTimeout: State<Int>
        get() = _searchTimeout

    private val _inputState = mutableStateOf<SearchInputState>(SearchInputState.Invalid.Empty)
    override val inputState: State<SearchInputState>
        get() = _inputState

    override fun searchForAccessPoint() {
        val networkInput = inputState.value
        if (networkInput !is SearchInputState.Valid) {
            _uiState.value = SearchUIState(
                loadingState = SearchLoadingState(isLoading = false),
                dialogState = SearchDialogState.InputError
            )
            return
        }
        _uiState.value = SearchUIState(
            loadingState = SearchLoadingState(isLoading = true),
            dialogState = SearchDialogState.None
        )
        wisefy.searchForAccessPoint(
            request = SearchForSingleAccessPointRequest.SSID(
                regexForSSID = networkInput.value,
                timeoutInMillis = searchTimeout.value,
                filterDuplicates = filterDuplicates.value
            ),
            callbacks = object : SearchForAccessPointCallbacks {
                override fun onAccessPointFound(accessPoint: AccessPointData) {
                    _uiState.value = SearchUIState(
                        loadingState = SearchLoadingState(isLoading = false),
                        dialogState = SearchDialogState.Success.AccessPoint(accessPoint)
                    )
                }

                override fun onNoAccessPointFound() {
                    _uiState.value = SearchUIState(
                        loadingState = SearchLoadingState(isLoading = false),
                        dialogState = SearchDialogState.Failure.NoAccessPointFound
                    )
                }

                override fun onWisefyAsyncFailure(throwable: Throwable) {
                    _uiState.value = SearchUIState(
                        loadingState = SearchLoadingState(isLoading = false),
                        dialogState = SearchDialogState.Failure.WisefyAsync(throwable)
                    )
                }
            }
        )
    }

    override fun searchForAccessPoints() {
        val networkInput = inputState.value
        if (networkInput !is SearchInputState.Valid) {
            _uiState.value = SearchUIState(
                loadingState = SearchLoadingState(isLoading = false),
                dialogState = SearchDialogState.InputError
            )
            return
        }
        _uiState.value = SearchUIState(
            loadingState = SearchLoadingState(isLoading = true),
            dialogState = SearchDialogState.None
        )
        wisefy.searchForAccessPoints(
            request = SearchForMultipleAccessPointsRequest.SSID(regexForSSID = networkInput.value),
            callbacks = object : SearchForAccessPointsCallbacks {
                override fun onAccessPointsFound(accessPoints: List<AccessPointData>) {
                    _uiState.value = SearchUIState(
                        loadingState = SearchLoadingState(isLoading = false),
                        dialogState = SearchDialogState.Success.AccessPoints(accessPoints)
                    )
                }

                override fun onNoAccessPointsFound() {
                    _uiState.value = SearchUIState(
                        loadingState = SearchLoadingState(isLoading = false),
                        dialogState = SearchDialogState.Failure.NoAccessPointsFound
                    )
                }

                override fun onWisefyAsyncFailure(throwable: Throwable) {
                    _uiState.value = SearchUIState(
                        loadingState = SearchLoadingState(isLoading = false),
                        dialogState = SearchDialogState.Failure.WisefyAsync(throwable)
                    )
                }
            }
        )
    }

    override fun searchForSavedNetwork() {
        val networkInput = inputState.value
        if (networkInput !is SearchInputState.Valid) {
            _uiState.value = SearchUIState(
                loadingState = SearchLoadingState(isLoading = false),
                dialogState = SearchDialogState.InputError
            )
            return
        }
        _uiState.value = SearchUIState(
            loadingState = SearchLoadingState(isLoading = true),
            dialogState = SearchDialogState.None
        )
        wisefy.searchForSavedNetwork(
            request = SearchForSavedNetworkRequest.SSID(regex = networkInput.value),
            callbacks = object : SearchForSavedNetworkCallbacks {
                override fun onSavedNetworkRetrieved(savedNetwork: SavedNetworkData) {
                    _uiState.value = SearchUIState(
                        loadingState = SearchLoadingState(isLoading = false),
                        dialogState = SearchDialogState.Success.SavedNetwork(savedNetwork)
                    )
                }

                override fun onSavedNetworkNotFound() {
                    _uiState.value = SearchUIState(
                        loadingState = SearchLoadingState(isLoading = false),
                        dialogState = SearchDialogState.Failure.NoSavedNetworkFound
                    )
                }

                override fun onWisefyAsyncFailure(throwable: Throwable) {
                    _uiState.value = SearchUIState(
                        loadingState = SearchLoadingState(isLoading = false),
                        dialogState = SearchDialogState.Failure.WisefyAsync(throwable)
                    )
                }
            }
        )
    }

    override fun searchForSavedNetworks() {
        val networkInput = inputState.value
        if (networkInput !is SearchInputState.Valid) {
            _uiState.value = SearchUIState(
                loadingState = SearchLoadingState(isLoading = false),
                dialogState = SearchDialogState.InputError
            )
            return
        }
        _uiState.value = SearchUIState(
            loadingState = SearchLoadingState(isLoading = true),
            dialogState = SearchDialogState.None
        )
        wisefy.searchForSavedNetworks(
            request = SearchForSavedNetworksRequest.SSID(regex = networkInput.value),
            callbacks = object : SearchForSavedNetworksCallbacks {
                override fun onSavedNetworksRetrieved(savedNetworks: List<SavedNetworkData>) {
                    _uiState.value = SearchUIState(
                        loadingState = SearchLoadingState(isLoading = false),
                        dialogState = SearchDialogState.Success.SavedNetworks(savedNetworks)
                    )
                }

                override fun onNoSavedNetworksFound() {
                    _uiState.value = SearchUIState(
                        loadingState = SearchLoadingState(isLoading = false),
                        dialogState = SearchDialogState.Failure.NoSavedNetworksFound
                    )
                }

                override fun onWisefyAsyncFailure(throwable: Throwable) {
                    _uiState.value = SearchUIState(
                        loadingState = SearchLoadingState(isLoading = false),
                        dialogState = SearchDialogState.Failure.WisefyAsync(throwable)
                    )
                }
            }
        )
    }

    override fun searchForSSID() {
        val networkInput = inputState.value
        if (networkInput !is SearchInputState.Valid) {
            _uiState.value = SearchUIState(
                loadingState = SearchLoadingState(isLoading = false),
                dialogState = SearchDialogState.InputError
            )
            return
        }
        _uiState.value = SearchUIState(
            loadingState = SearchLoadingState(isLoading = true),
            dialogState = SearchDialogState.None
        )
        wisefy.searchForSSID(
            request = SearchForSingleSSIDRequest.SSID(
                regexForSSID = networkInput.value,
                timeoutInMillis = searchTimeout.value
            ),
            callbacks = object : SearchForSSIDCallbacks {
                override fun onSSIDFound(ssid: SSIDData) {
                    _uiState.value = SearchUIState(
                        loadingState = SearchLoadingState(isLoading = false),
                        dialogState = SearchDialogState.Success.SSID(ssid)
                    )
                }

                override fun onSSIDNotFound() {
                    _uiState.value = SearchUIState(
                        loadingState = SearchLoadingState(isLoading = false),
                        dialogState = SearchDialogState.Failure.NoSSIDFound
                    )
                }

                override fun onWisefyAsyncFailure(throwable: Throwable) {
                    _uiState.value = SearchUIState(
                        loadingState = SearchLoadingState(isLoading = false),
                        dialogState = SearchDialogState.Failure.WisefyAsync(throwable)
                    )
                }
            }
        )
    }

    override fun searchForSSIDs() {
        val networkInput = inputState.value
        if (networkInput !is SearchInputState.Valid) {
            _uiState.value = SearchUIState(
                loadingState = SearchLoadingState(isLoading = false),
                dialogState = SearchDialogState.InputError
            )
            return
        }
        _uiState.value = SearchUIState(
            loadingState = SearchLoadingState(isLoading = true),
            dialogState = SearchDialogState.None
        )
        wisefy.searchForSSIDs(
            request = SearchForMultipleSSIDsRequest.SSID(regexForSSID = networkInput.value),
            callbacks = object : SearchForSSIDsCallbacks {
                override fun onSSIDsFound(ssids: List<SSIDData>) {
                    _uiState.value = SearchUIState(
                        loadingState = SearchLoadingState(isLoading = false),
                        dialogState = SearchDialogState.Success.SSIDs(ssids)
                    )
                }

                override fun onNoSSIDsFound() {
                    _uiState.value = SearchUIState(
                        loadingState = SearchLoadingState(isLoading = false),
                        dialogState = SearchDialogState.Failure.NoSSIDsFound
                    )
                }

                override fun onWisefyAsyncFailure(throwable: Throwable) {
                    _uiState.value = SearchUIState(
                        loadingState = SearchLoadingState(isLoading = false),
                        dialogState = SearchDialogState.Failure.WisefyAsync(throwable)
                    )
                }
            }
        )
    }

    override fun onSearchNetworkInputChanged(input: String) {
        _inputState.value = when (input.validateSSID()) {
            SSIDInputError.EMPTY -> SearchInputState.Invalid.Empty
            SSIDInputError.TOO_SHORT -> SearchInputState.Invalid.TooShort
            SSIDInputError.TOO_LONG -> SearchInputState.Invalid.TooLong
            SSIDInputError.INVALID_CHARACTERS -> SearchInputState.Invalid.InvalidCharacters
            SSIDInputError.INVALID_START_CHARACTERS -> SearchInputState.Invalid.InvalidStartCharacters
            SSIDInputError.LEADING_OR_TRAILING_SPACES -> SearchInputState.Invalid.LeadingOrTrailingSpaces
            SSIDInputError.NOT_VALID_UNICODE -> SearchInputState.Invalid.InvalidUnicode
            SSIDInputError.NONE -> SearchInputState.Valid(input)
        }
    }

    override fun onAccessPointSearchTypeSelected() {
        _searchType.value = SearchType.ACCESS_POINT
    }

    override fun onSSIDSearchTypeSelected() {
        _searchType.value = SearchType.SSID
    }

    override fun onSavedNetworkSearchTypeSelected() {
        _searchType.value = SearchType.SAVED_NETWORK
    }

    override fun onReturnFullListSelected() {
        _returnFullList.value = true
    }

    override fun onReturnFullListDeselected() {
        _returnFullList.value = false
    }

    override fun onFilterDuplicatesSelected() {
        _filterDuplicates.value = true
    }

    override fun onFilterDuplicatesDeselected() {
        _filterDuplicates.value = false
    }

    override fun onSearchTimeoutValueUpdated(timeout: Int) {
        _searchTimeout.value = timeout
    }

    override fun onSearchForAccessPointPermissionError() {
        _uiState.value = SearchUIState(
            loadingState = SearchLoadingState(isLoading = false),
            dialogState = SearchDialogState.PermissionError.AccessPoint
        )
    }

    override fun onSearchForAccessPointsPermissionError() {
        _uiState.value = SearchUIState(
            loadingState = SearchLoadingState(isLoading = false),
            dialogState = SearchDialogState.PermissionError.AccessPoints
        )
    }

    override fun onSearchForSavedNetworkPermissionError() {
        _uiState.value = SearchUIState(
            loadingState = SearchLoadingState(isLoading = false),
            dialogState = SearchDialogState.PermissionError.SavedNetwork
        )
    }

    override fun onSearchForSavedNetworksPermissionError() {
        _uiState.value = SearchUIState(
            loadingState = SearchLoadingState(isLoading = false),
            dialogState = SearchDialogState.PermissionError.SavedNetworks
        )
    }

    override fun onSearchForSSIDPermissionError() {
        _uiState.value = SearchUIState(
            loadingState = SearchLoadingState(isLoading = false),
            dialogState = SearchDialogState.PermissionError.SSID
        )
    }

    override fun onSearchForSSIDsPermissionError() {
        _uiState.value = SearchUIState(
            loadingState = SearchLoadingState(isLoading = false),
            dialogState = SearchDialogState.PermissionError.SSIDs
        )
    }

    override fun onDialogClosed() {
        _uiState.value = SearchUIState(
            loadingState = SearchLoadingState(isLoading = false),
            dialogState = SearchDialogState.None
        )
    }
}

internal class SearchViewModelFactory(
    private val wisefy: WisefyApi
) : BaseViewModelFactory<SearchViewModel>(
    supportedClass = SearchViewModel::class,
    vmProvider = { DefaultSearchViewModel(wisefy) }
)
