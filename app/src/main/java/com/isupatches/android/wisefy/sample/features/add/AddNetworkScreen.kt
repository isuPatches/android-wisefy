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
package com.isupatches.android.wisefy.sample.features.add

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.isupatches.android.wisefy.WisefyApi
import com.isupatches.android.wisefy.sample.ui.components.WisefySampleLoadingIndicator
import com.isupatches.android.wisefy.sample.util.SdkUtil

@Composable
internal fun AddNetworkScreen(
    wisefy: WisefyApi,
    sdkUtil: SdkUtil,
    viewModel: AddNetworkViewModel = viewModel(
        factory = AddNetworkViewModelFactory(
            context = LocalContext.current.applicationContext,
            wisefy = wisefy,
            sdkUtil = sdkUtil
        )
    )
) {
    WisefySampleLoadingIndicator(isLoading = { viewModel.uiState.value.loadingState.isLoading })
    AddNetworkScreenDialogContent(dialogState = { viewModel.uiState.value.dialogState }, viewModel = viewModel)
    AddNetworkScreenContent(viewModel = viewModel, sdkUtil = sdkUtil)
}