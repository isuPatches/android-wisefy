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

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.isupatches.android.ktx.searchForSavedNetworkAsync
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
import com.isupatches.android.wisefy.core.exceptions.WisefyException
import com.isupatches.android.wisefy.sample.entities.SSIDType
import com.isupatches.android.wisefy.sample.entities.SearchType
import com.isupatches.android.wisefy.sample.scaffolding.BaseViewModel
import com.isupatches.android.wisefy.sample.scaffolding.BaseViewModelFactory
import com.isupatches.android.wisefy.sample.util.BSSIDInputError
import com.isupatches.android.wisefy.sample.util.SSIDInputError
import com.isupatches.android.wisefy.sample.util.validateBSSID
import com.isupatches.android.wisefy.sample.util.validateSSID
import com.isupatches.android.wisefy.savednetworks.entities.SearchForSavedNetworkRequest
import com.isupatches.android.wisefy.savednetworks.entities.SearchForSavedNetworkResult
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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
    abstract suspend fun searchForSavedNetwork()
    abstract suspend fun searchForSavedNetworks()
    abstract fun searchForSSID()
    abstract fun searchForSSIDs()

    abstract fun onSearchNetworkInputChanged(input: String)

    abstract fun onSearchTypeSelected(searchType: SearchType)
    abstract fun onReturnFullListChanged(enabled: Boolean)
    abstract fun onFilterDuplicatesChanged(enabled: Boolean)
    abstract fun onSSIDTypeChanged(ssidType: SSIDType)

    abstract fun onSearchTimeoutValueChange(timeout: Int)
    abstract fun onSearchTimeoutValueChangeFinished(timeout: Int)

    abstract fun onDialogClosed()
}

internal class DefaultSearchViewModel(
    context: Context,
    private val wisefy: WisefyApi,
    private val searchStore: SearchStore = DefaultSearchStore(context = context)
) : SearchViewModel() {

    private val _uiState = mutableStateOf(
        SearchUIState(
            loadingState = SearchLoadingState(isLoading = false),
            dialogState = SearchDialogState.None,
            inputState = SearchInputState(
                input = "",
                inputValidityState = SearchInputValidityState.SSID.Invalid.Empty
            ),
            searchType = SearchType.ACCESS_POINT,
            ssidType = SSIDType.SSID,
            returnFullList = true,
            filterDuplicates = true,
            timeout = 1
        )
    )
    override val uiState: State<SearchUIState>
        get() = _uiState

    init {
        viewModelScope.launch {
            searchStore.getLastUsedNetworkInput()
                .collectLatest { input ->
                    validateInput(input, uiState.value.ssidType)
                }
        }

        viewModelScope.launch {
            searchStore.getSearchType()
                .collectLatest { searchType ->
                    _uiState.value = uiState.value.copy(searchType = searchType)
                }
        }

        viewModelScope.launch {
            searchStore.getSSIDType()
                .collectLatest { ssidType ->
                    _uiState.value = uiState.value.copy(ssidType = ssidType)
                    validateInput(uiState.value.inputState.input, ssidType)
                }
        }

        viewModelScope.launch {
            searchStore.shouldReturnFullList()
                .collectLatest { returnFullList ->
                    _uiState.value = uiState.value.copy(returnFullList = returnFullList)
                }
        }

        viewModelScope.launch {
            searchStore.shouldFilterDuplicates()
                .collectLatest { filterDuplicates ->
                    _uiState.value = uiState.value.copy(filterDuplicates = filterDuplicates)
                }
        }
    }

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
                if (networkInput.inputValidityState is SearchInputValidityState.SSID.Valid) {
                    SearchForSingleAccessPointRequest.SSID(
                        regex = networkInput.input,
                        timeoutInMillis = uiState.value.timeout,
                        filterDuplicates = uiState.value.filterDuplicates
                    )
                } else {
                    error("")
                }
            } else {
                if (networkInput.inputValidityState is SearchInputValidityState.BSSID.Valid) {
                    SearchForSingleAccessPointRequest.BSSID(
                        regex = networkInput.input,
                        timeoutInMillis = uiState.value.timeout,
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

                override fun onWisefyAsyncFailure(exception: WisefyException) {
                    _uiState.value = uiState.value.copy(
                        loadingState = SearchLoadingState(isLoading = false),
                        dialogState = SearchDialogState.Failure.WisefyAsync(throwable = exception)
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
                    if (networkInput.inputValidityState is SearchInputValidityState.SSID.Valid) {
                        SearchForMultipleAccessPointsRequest.SSID(regex = networkInput.input)
                    } else {
                        error("")
                    }
                }
                SSIDType.BSSID -> {
                    if (networkInput.inputValidityState is SearchInputValidityState.SSID.Valid) {
                        SearchForMultipleAccessPointsRequest.BSSID(regex = networkInput.input)
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

                override fun onWisefyAsyncFailure(exception: WisefyException) {
                    _uiState.value = uiState.value.copy(
                        loadingState = SearchLoadingState(isLoading = false),
                        dialogState = SearchDialogState.Failure.WisefyAsync(throwable = exception)
                    )
                }
            }
        )
    }

    override suspend fun searchForSavedNetwork() {
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
        val result = try {
            wisefy.searchForSavedNetworkAsync(
                request = when (uiState.value.ssidType) {
                    SSIDType.SSID -> {
                        if (networkInput.inputValidityState is SearchInputValidityState.SSID.Valid) {
                            SearchForSavedNetworkRequest.SSID(regex = networkInput.input)
                        } else {
                            error("")
                        }
                    }
                    SSIDType.BSSID -> {
                        if (networkInput.inputValidityState is SearchInputValidityState.SSID.Valid) {
                            SearchForSavedNetworkRequest.BSSID(regex = networkInput.input)
                        } else {
                            error("")
                        }
                    }
                }
            )
        } catch (ex: WisefyException) {
            _uiState.value = uiState.value.copy(
                loadingState = SearchLoadingState(isLoading = false),
                dialogState = SearchDialogState.Failure.WisefyAsync(throwable = ex)
            )
            null
        }

        when (result) {
            is SearchForSavedNetworkResult.Success.Empty -> {
                _uiState.value = uiState.value.copy(
                    loadingState = SearchLoadingState(isLoading = false),
                    dialogState = SearchDialogState.SearchForSavedNetwork.NoSavedNetworkFound
                )
            }
            is SearchForSavedNetworkResult.Success.SavedNetworks -> {
                _uiState.value = uiState.value.copy(
                    loadingState = SearchLoadingState(isLoading = false),
                    dialogState = SearchDialogState.SearchForSavedNetwork.Success(result.data.first())
                )
            }
            is SearchForSavedNetworkResult.Failure.Assertion -> TODO()
            null -> TODO()
        }
    }

    override suspend fun searchForSavedNetworks() {
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
        val result = try {
            wisefy.searchForSavedNetworkAsync(
                request = when (uiState.value.ssidType) {
                    SSIDType.SSID -> {
                        if (networkInput.inputValidityState is SearchInputValidityState.SSID.Valid) {
                            SearchForSavedNetworkRequest.SSID(regex = networkInput.input)
                        } else {
                            error("")
                        }
                    }
                    SSIDType.BSSID -> {
                        if (networkInput.inputValidityState is SearchInputValidityState.SSID.Valid) {
                            SearchForSavedNetworkRequest.BSSID(regex = networkInput.input)
                        } else {
                            error("")
                        }
                    }
                }
            )
        } catch (ex: WisefyException) {
            _uiState.value = uiState.value.copy(
                loadingState = SearchLoadingState(isLoading = false),
                dialogState = SearchDialogState.Failure.WisefyAsync(throwable = ex)
            )
            null
        }

        when (result) {
            is SearchForSavedNetworkResult.Success.Empty -> {
                _uiState.value = uiState.value.copy(
                    loadingState = SearchLoadingState(isLoading = false),
                    dialogState = SearchDialogState.SearchForSavedNetworks.NoSavedNetworksFound
                )
            }
            is SearchForSavedNetworkResult.Success.SavedNetworks -> {
                _uiState.value = uiState.value.copy(
                    loadingState = SearchLoadingState(isLoading = false),
                    dialogState = SearchDialogState.SearchForSavedNetworks.Success(result.data)
                )
            }
            is SearchForSavedNetworkResult.Failure.Assertion -> TODO()
            null -> TODO()
        }
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
                    if (networkInput.inputValidityState is SearchInputValidityState.SSID.Valid) {
                        SearchForSingleSSIDRequest.SSID(
                            regex = networkInput.input,
                            timeoutInMillis = uiState.value.timeout
                        )
                    } else {
                        error("")
                    }
                }
                SSIDType.BSSID -> {
                    if (networkInput.inputValidityState is SearchInputValidityState.BSSID.Valid) {
                        SearchForSingleSSIDRequest.BSSID(
                            regex = networkInput.input,
                            timeoutInMillis = uiState.value.timeout
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

                override fun onWisefyAsyncFailure(exception: WisefyException) {
                    _uiState.value = uiState.value.copy(
                        loadingState = SearchLoadingState(isLoading = false),
                        dialogState = SearchDialogState.Failure.WisefyAsync(throwable = exception)
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
                    if (networkInput.inputValidityState is SearchInputValidityState.SSID.Valid) {
                        SearchForMultipleSSIDsRequest.SSID(regex = networkInput.input)
                    } else {
                        error("")
                    }
                }
                SSIDType.BSSID -> {
                    if (networkInput.inputValidityState is SearchInputValidityState.BSSID.Valid) {
                        SearchForMultipleSSIDsRequest.BSSID(regex = networkInput.input)
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

                override fun onWisefyAsyncFailure(exception: WisefyException) {
                    _uiState.value = uiState.value.copy(
                        loadingState = SearchLoadingState(isLoading = false),
                        dialogState = SearchDialogState.Failure.WisefyAsync(throwable = exception)
                    )
                }
            }
        )
    }

    override fun onSearchNetworkInputChanged(input: String) {
        viewModelScope.launch {
            searchStore.setLastUsedNetworkInput(input)
        }
    }

    override fun onSearchTypeSelected(searchType: SearchType) {
        viewModelScope.launch {
            searchStore.setSearchType(searchType)
        }
    }

    override fun onSSIDTypeChanged(ssidType: SSIDType) {
        viewModelScope.launch {
            searchStore.setSSIDType(ssidType)
        }
    }

    override fun onReturnFullListChanged(enabled: Boolean) {
        viewModelScope.launch {
            searchStore.setReturnFullList(enabled)
        }
    }

    override fun onFilterDuplicatesChanged(enabled: Boolean) {
        viewModelScope.launch {
            searchStore.setFilterDuplicates(enabled)
        }
    }

    override fun onSearchTimeoutValueChange(timeout: Int) {
        _uiState.value = uiState.value.copy(timeout = timeout)
    }

    override fun onSearchTimeoutValueChangeFinished(timeout: Int) {
        viewModelScope.launch {
            searchStore.setTimeout(timeout)
        }
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
        val inputValidityState = uiState.value.inputState.inputValidityState
        return inputValidityState is SearchInputValidityState.SSID.Valid ||
            inputValidityState is SearchInputValidityState.BSSID.Valid
    }

    private fun validateInput(input: String, ssidType: SSIDType) {
        val validityState = when (ssidType) {
            SSIDType.SSID -> {
                when (input.validateSSID()) {
                    SSIDInputError.EMPTY -> SearchInputValidityState.SSID.Invalid.Empty
                    SSIDInputError.TOO_SHORT -> SearchInputValidityState.SSID.Invalid.TooShort
                    SSIDInputError.TOO_LONG -> SearchInputValidityState.SSID.Invalid.TooLong
                    SSIDInputError.INVALID_CHARACTERS -> {
                        SearchInputValidityState.SSID.Invalid.InvalidCharacters
                    }
                    SSIDInputError.INVALID_START_CHARACTERS -> {
                        SearchInputValidityState.SSID.Invalid.InvalidStartCharacters
                    }
                    SSIDInputError.LEADING_OR_TRAILING_SPACES -> {
                        SearchInputValidityState.SSID.Invalid.LeadingOrTrailingSpaces
                    }
                    SSIDInputError.NOT_VALID_UNICODE -> SearchInputValidityState.SSID.Invalid.InvalidUnicode
                    SSIDInputError.NONE -> SearchInputValidityState.SSID.Valid
                }
            }
            SSIDType.BSSID -> {
                when (input.validateBSSID()) {
                    BSSIDInputError.EMPTY -> SearchInputValidityState.BSSID.Invalid.Empty
                    BSSIDInputError.INVALID -> SearchInputValidityState.BSSID.Invalid.ImproperFormat
                    BSSIDInputError.NONE -> SearchInputValidityState.BSSID.Valid
                }
            }
        }
        _uiState.value = uiState.value.copy(
            inputState = SearchInputState(
                input = input,
                inputValidityState = validityState
            )
        )
    }
}

internal class SearchViewModelFactory(
    private val context: Context,
    private val wisefy: WisefyApi
) : BaseViewModelFactory<SearchViewModel>(
    supportedClass = SearchViewModel::class,
    vmProvider = { DefaultSearchViewModel(context, wisefy) }
)
