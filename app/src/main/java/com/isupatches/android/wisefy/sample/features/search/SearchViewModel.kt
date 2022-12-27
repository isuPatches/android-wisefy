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
package com.isupatches.android.wisefy.sample.features.search

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.content.Context
import androidx.annotation.RequiresPermission
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.isupatches.android.wisefy.WisefyApi
import com.isupatches.android.wisefy.accesspoints.entities.GetAccessPointsQuery
import com.isupatches.android.wisefy.accesspoints.entities.GetAccessPointsResult
import com.isupatches.android.wisefy.core.exceptions.WisefyException
import com.isupatches.android.wisefy.ktx.getAccessPointsAsync
import com.isupatches.android.wisefy.ktx.getSavedNetworksAsync
import com.isupatches.android.wisefy.sample.entities.SSIDType
import com.isupatches.android.wisefy.sample.entities.SearchType
import com.isupatches.android.wisefy.sample.scaffolding.BaseViewModel
import com.isupatches.android.wisefy.sample.scaffolding.BaseViewModelFactory
import com.isupatches.android.wisefy.sample.util.BSSIDInputError
import com.isupatches.android.wisefy.sample.util.SSIDInputError
import com.isupatches.android.wisefy.sample.util.validateBSSID
import com.isupatches.android.wisefy.sample.util.validateSSID
import com.isupatches.android.wisefy.savednetworks.entities.GetSavedNetworksQuery
import com.isupatches.android.wisefy.savednetworks.entities.GetSavedNetworksResult
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val SECONDS_TO_MILLIS = 1000

internal abstract class SearchViewModel : BaseViewModel() {
    abstract val uiState: State<SearchUIState>

    abstract fun onSearchForAccessPointPermissionError()
    abstract fun onSearchForAccessPointsPermissionError()
    abstract fun onSearchForSavedNetworkPermissionError()
    abstract fun onSearchForSavedNetworksPermissionError()
    abstract fun onSearchForSSIDPermissionError()
    abstract fun onSearchForSSIDsPermissionError()

    @RequiresPermission(ACCESS_FINE_LOCATION)
    abstract suspend fun searchForAccessPoint()

    @RequiresPermission(ACCESS_FINE_LOCATION)
    abstract suspend fun searchForAccessPoints()

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    abstract suspend fun searchForSavedNetwork()

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    abstract suspend fun searchForSavedNetworks()

    @RequiresPermission(ACCESS_FINE_LOCATION)
    abstract suspend fun searchForSSID()

    @RequiresPermission(ACCESS_FINE_LOCATION)
    abstract suspend fun searchForSSIDs()

    abstract fun onSearchNetworkInputChanged(input: String)

    abstract fun onSearchTypeSelected(searchType: SearchType)
    abstract fun onUseRegexForSearchChanged(useRegexForSearch: Boolean)
    abstract fun onReturnFullListChanged(enabled: Boolean)
    abstract fun onFilterDuplicatesChanged(enabled: Boolean)
    abstract fun onSSIDTypeChanged(ssidType: SSIDType)

    abstract fun onSearchTimeoutValueChangeFinished(timeoutInSeconds: Int)

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
            useRegexForSearch = false,
            returnFullList = true,
            filterDuplicates = true,
            timeoutInSeconds = null
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
            searchStore.shouldUseRegexForSearch()
                .collectLatest { useRegexForSearch ->
                    _uiState.value = uiState.value.copy(useRegexForSearch = useRegexForSearch)
                    validateInput(uiState.value.inputState.input, uiState.value.ssidType)
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

        viewModelScope.launch {
            searchStore.getTimeout()
                .collectLatest { timeout ->
                    _uiState.value = uiState.value.copy(timeoutInSeconds = timeout)
                }
        }
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override suspend fun searchForAccessPoint() {
        if (isInputInvalid()) {
            return
        }
        showProgress()
        when (val result = getAccessPoints()) {
            is GetAccessPointsResult.AccessPoints -> {
                _uiState.value = uiState.value.copy(
                    loadingState = SearchLoadingState(isLoading = false),
                    dialogState = SearchDialogState.SearchForAccessPoint.Success(result.value.first())
                )
            }
            is GetAccessPointsResult.Empty -> {
                _uiState.value = uiState.value.copy(
                    loadingState = SearchLoadingState(isLoading = false),
                    dialogState = SearchDialogState.SearchForAccessPoint.NoAccessPointFound
                )
            }
            null -> {
                // Case handled above in the catch clause
            }
        }
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override suspend fun searchForAccessPoints() {
        if (isInputInvalid()) {
            return
        }
        showProgress()
        when (val result = getAccessPoints()) {
            is GetAccessPointsResult.AccessPoints -> {
                _uiState.value = uiState.value.copy(
                    loadingState = SearchLoadingState(isLoading = false),
                    dialogState = SearchDialogState.SearchForAccessPoints.Success(result.value)
                )
            }
            is GetAccessPointsResult.Empty -> {
                _uiState.value = uiState.value.copy(
                    loadingState = SearchLoadingState(isLoading = false),
                    dialogState = SearchDialogState.SearchForAccessPoints.NoAccessPointsFound
                )
            }
            null -> {
                // Case handled above in the catch clause
            }
        }
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override suspend fun searchForSavedNetwork() {
        if (isInputInvalid()) {
            return
        }
        showProgress()
        when (val result = getSavedNetworks()) {
            is GetSavedNetworksResult.Empty -> {
                _uiState.value = uiState.value.copy(
                    loadingState = SearchLoadingState(isLoading = false),
                    dialogState = SearchDialogState.SearchForSavedNetwork.NoSavedNetworkFound
                )
            }
            is GetSavedNetworksResult.SavedNetworks -> {
                _uiState.value = uiState.value.copy(
                    loadingState = SearchLoadingState(isLoading = false),
                    dialogState = SearchDialogState.SearchForSavedNetwork.Success(result.value.first())
                )
            }
            null -> {
                // Case handled above in the catch clause
            }
        }
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override suspend fun searchForSavedNetworks() {
        if (isInputInvalid()) {
            return
        }
        showProgress()
        when (val result = getSavedNetworks()) {
            is GetSavedNetworksResult.Empty -> {
                _uiState.value = uiState.value.copy(
                    loadingState = SearchLoadingState(isLoading = false),
                    dialogState = SearchDialogState.SearchForSavedNetworks.NoSavedNetworksFound
                )
            }
            is GetSavedNetworksResult.SavedNetworks -> {
                _uiState.value = uiState.value.copy(
                    loadingState = SearchLoadingState(isLoading = false),
                    dialogState = SearchDialogState.SearchForSavedNetworks.Success(result.value)
                )
            }
            null -> {
                // Case handled above in the catch clause
            }
        }
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override suspend fun searchForSSID() {
        if (isInputInvalid()) {
            return
        }
        showProgress()
        when (val result = getAccessPoints()) {
            is GetAccessPointsResult.AccessPoints -> {
                val ssid = when (uiState.value.ssidType) {
                    SSIDType.SSID -> result.value.first().ssid
                    SSIDType.BSSID -> result.value.first().bssid
                }
                _uiState.value = uiState.value.copy(
                    loadingState = SearchLoadingState(isLoading = false),
                    dialogState = SearchDialogState.SearchForSSID.Success(ssid)
                )
            }
            is GetAccessPointsResult.Empty -> {
                _uiState.value = uiState.value.copy(
                    loadingState = SearchLoadingState(isLoading = false),
                    dialogState = SearchDialogState.SearchForSSID.NoSSIDFound
                )
            }
            null -> {
                // Case handled above in the catch clause
            }
        }
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override suspend fun searchForSSIDs() {
        if (isInputInvalid()) {
            return
        }
        showProgress()
        when (val result = getAccessPoints()) {
            is GetAccessPointsResult.AccessPoints -> {
                val ssids = when (uiState.value.ssidType) {
                    SSIDType.SSID -> result.value.map { it.ssid }
                    SSIDType.BSSID -> result.value.map { it.bssid }
                }
                _uiState.value = uiState.value.copy(
                    loadingState = SearchLoadingState(isLoading = false),
                    dialogState = SearchDialogState.SearchForSSIDs.Success(ssids)
                )
            }
            is GetAccessPointsResult.Empty -> {
                _uiState.value = uiState.value.copy(
                    loadingState = SearchLoadingState(isLoading = false),
                    dialogState = SearchDialogState.SearchForSSIDs.NoSSIDsFound
                )
            }
            null -> {
                // Case handled above in the catch clause
            }
        }
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

    override fun onUseRegexForSearchChanged(useRegexForSearch: Boolean) {
        viewModelScope.launch {
            searchStore.setUseRegexForSearch(useRegexForSearch)
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

    override fun onSearchTimeoutValueChangeFinished(timeoutInSeconds: Int) {
        viewModelScope.launch {
            _uiState.value = uiState.value.copy(timeoutInSeconds = timeoutInSeconds)
            searchStore.setTimeout(timeoutInSeconds)
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

    @RequiresPermission(ACCESS_FINE_LOCATION)
    private suspend fun getAccessPoints(): GetAccessPointsResult? {
        return try {
            wisefy.getAccessPointsAsync(query = getAccessPointsQuery())
        } catch (ex: WisefyException) {
            _uiState.value = uiState.value.copy(
                loadingState = SearchLoadingState(isLoading = false),
                dialogState = SearchDialogState.Failure.WisefyAsync(exception = ex)
            )
            null
        }
    }

    private fun getAccessPointsQuery(): GetAccessPointsQuery {
        val networkInput = uiState.value.inputState
        val timeoutInSeconds = if (uiState.value.searchType == SearchType.SAVED_NETWORK) {
            null
        } else {
            uiState.value.timeoutInSeconds
        }
        return when (uiState.value.ssidType) {
            SSIDType.SSID -> {
                GetAccessPointsQuery.BySSID(
                    regex = networkInput.input,
                    timeoutInMillis = timeoutInSeconds?.let { it * SECONDS_TO_MILLIS }
                )
            }
            SSIDType.BSSID -> {
                GetAccessPointsQuery.ByBSSID(
                    regex = networkInput.input,
                    timeoutInMillis = timeoutInSeconds?.let { it * SECONDS_TO_MILLIS }
                )
            }
        }
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    private suspend fun getSavedNetworks(): GetSavedNetworksResult? {
        return try {
            wisefy.getSavedNetworksAsync(query = getSavedNetworksQuery())
        } catch (ex: WisefyException) {
            _uiState.value = uiState.value.copy(
                loadingState = SearchLoadingState(isLoading = false),
                dialogState = SearchDialogState.Failure.WisefyAsync(exception = ex)
            )
            null
        }
    }

    private fun getSavedNetworksQuery(): GetSavedNetworksQuery {
        val networkInput = uiState.value.inputState
        return when (uiState.value.ssidType) {
            SSIDType.SSID -> GetSavedNetworksQuery.BySSID(regex = networkInput.input)
            SSIDType.BSSID -> GetSavedNetworksQuery.ByBSSID(regex = networkInput.input)
        }
    }

    private fun isInputInvalid(): Boolean {
        val currentInputState = uiState.value.inputState
        when (uiState.value.ssidType) {
            SSIDType.SSID -> {
                if (currentInputState.inputValidityState != SearchInputValidityState.SSID.Valid) {
                    _uiState.value = uiState.value.copy(
                        loadingState = SearchLoadingState(isLoading = false),
                        dialogState = SearchDialogState.InputError.SSID
                    )
                    return true
                }
            }
            SSIDType.BSSID -> {
                if (currentInputState.inputValidityState != SearchInputValidityState.BSSID.Valid) {
                    _uiState.value = uiState.value.copy(
                        loadingState = SearchLoadingState(isLoading = false),
                        dialogState = SearchDialogState.InputError.BSSID
                    )
                    return true
                }
            }
        }
        return false
    }

    private fun showProgress() {
        _uiState.value = uiState.value.copy(
            loadingState = SearchLoadingState(isLoading = true),
            dialogState = SearchDialogState.None
        )
    }

    private fun validateInput(input: String, ssidType: SSIDType) {
        val validityState = if (uiState.value.useRegexForSearch) {
            when (ssidType) {
                SSIDType.SSID -> {
                    SearchInputValidityState.SSID.Valid
                }
                SSIDType.BSSID -> {
                    SearchInputValidityState.BSSID.Valid
                }
            }
        } else {
            when (ssidType) {
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
