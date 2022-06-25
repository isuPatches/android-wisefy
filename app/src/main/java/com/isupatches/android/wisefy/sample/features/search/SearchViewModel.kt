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
import com.isupatches.android.wisefy.sample.entities.SearchType
import com.isupatches.android.wisefy.sample.scaffolding.BaseViewModel
import com.isupatches.android.wisefy.sample.scaffolding.BaseViewModelFactory

internal abstract class SearchViewModel : BaseViewModel() {
    abstract val uiState: State<SearchUIState>
    abstract val searchType: State<SearchType>

    abstract fun onAccessPointSearchTypeSelected()
    abstract fun onSSIDSearchTypeSelected()
    abstract fun onSavedNetworkSearchTypeSelected()

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

    override fun onAccessPointSearchTypeSelected() {
        _searchType.value = SearchType.ACCESS_POINT
    }

    override fun onSSIDSearchTypeSelected() {
        _searchType.value = SearchType.SSID
    }

    override fun onSavedNetworkSearchTypeSelected() {
        _searchType.value = SearchType.SAVED_NETWORK
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
