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

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.isupatches.android.wisefy.WisefyApi

@Composable
internal fun SearchScreen(
    wisefy: WisefyApi,
    viewModel: SearchViewModel = viewModel(factory = SearchViewModelFactory(wisefy))
) {
    SearchScreenContent(
        uiState = { viewModel.uiState.value },
        searchType = { viewModel.searchType.value },
        inputState = { viewModel.inputState.value },
        viewModel = viewModel
    )
}
