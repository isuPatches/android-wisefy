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
package com.isupatches.android.wisefy.sample.features.misc.nearbyaccesspoints

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.ACCESS_WIFI_STATE
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.isupatches.android.wisefy.WisefyApi
import com.isupatches.android.wisefy.sample.logging.WisefySampleLogger
import com.isupatches.android.wisefy.sample.ui.components.WisefySampleLoadingIndicator
import kotlinx.coroutines.launch

private const val LOG_TAG = "NearbyAccessPointsScreen"

@Composable
internal fun NearbyAccessPointsScreen(
    wisefy: WisefyApi,
    viewModel: NearbyAccessPointsViewModel = viewModel(factory = NearbyAccessPointsViewModelFactory(wisefy))
) {
    val scope = rememberCoroutineScope()

    val getNearbyAccessPointsPermissionsLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->
            if (result.all { it.value }) {
                scope.launch {
                    viewModel.getNearbyAccessPoints()
                }
            } else {
                WisefySampleLogger.w(LOG_TAG, "Permissions for getting nearby access points are denied")
                viewModel.onGetNearbyAccessPointsPermissionError()
            }
        }

    SideEffect {
        getNearbyAccessPointsPermissionsLauncher.launch(arrayOf(ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE))
    }

    WisefySampleLoadingIndicator(isLoading = { viewModel.uiState.value.loadingState.isLoading })
    NearbyAccessPointsScreenDialogContent(dialogState = { viewModel.uiState.value.dialogState }, viewModel = viewModel)
    NearbyAccessPointsScreenContent(accessPoints = { viewModel.uiState.value.accessPointUIData })
}
