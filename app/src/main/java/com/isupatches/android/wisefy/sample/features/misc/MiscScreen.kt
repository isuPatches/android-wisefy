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
package com.isupatches.android.wisefy.sample.features.misc

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.isupatches.android.wisefy.WisefyApi
import com.isupatches.android.wisefy.sample.ui.ComposablePreviewWisefy
import com.isupatches.android.wisefy.sample.ui.components.WisefySampleLoadingIndicator
import com.isupatches.android.wisefy.sample.util.DefaultSdkUtil
import com.isupatches.android.wisefy.sample.util.SdkUtil

@Composable
internal fun MiscScreen(
    wisefy: WisefyApi,
    sdkUtil: SdkUtil,
    navController: NavHostController,
    viewModel: MiscViewModel = viewModel(factory = MiscViewModelFactory(wisefy))
) {
    WisefySampleLoadingIndicator(isLoading = { viewModel.uiState.value.loadingState.isLoading })
    MiscScreenDialogContent(dialogState = { viewModel.uiState.value.dialogState }, viewModel = viewModel)
    MiscScreenContent(
        viewModel = viewModel,
        sdkUtil = sdkUtil,
        router = DefaultMiscScreenRouter(navController = navController)
    )
}

@Preview(showBackground = true)
@Composable
@Suppress("UnusedPrivateMember")
private fun MiscScreenLightPreview() {
    MiscScreen(
        wisefy = ComposablePreviewWisefy(),
        navController = rememberNavController(),
        sdkUtil = DefaultSdkUtil()
    )
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
@Suppress("UnusedPrivateMember")
private fun MiscScreenDarkPreview() {
    MiscScreen(
        wisefy = ComposablePreviewWisefy(),
        navController = rememberNavController(),
        sdkUtil = DefaultSdkUtil()
    )
}
