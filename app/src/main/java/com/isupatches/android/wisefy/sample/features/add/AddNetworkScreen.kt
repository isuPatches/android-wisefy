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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.isupatches.android.wisefy.WisefyApi
import com.isupatches.android.wisefy.sample.util.SdkUtil

@Composable
internal fun AddNetworkScreen(
    wisefy: WisefyApi,
    sdkUtil: SdkUtil,
    viewModel: AddNetworkViewModel = viewModel(factory = AddNetworkViewModelFactory(wisefy, sdkUtil))
) {
    AddNetworkScreenContent(
        uiState = { viewModel.uiState.value },
        inputState = { viewModel.inputState.value },
        networkType = { viewModel.networkType.value },
        viewModel = viewModel,
        isAtLeastAndroidQ = viewModel.isAtLeastAndroidQ
    )
}
