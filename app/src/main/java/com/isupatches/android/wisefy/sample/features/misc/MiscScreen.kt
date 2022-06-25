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
package com.isupatches.android.wisefy.sample.features.misc

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.res.Configuration
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.isupatches.android.wisefy.Wisefy
import com.isupatches.android.wisefy.WisefyApi
import com.isupatches.android.wisefy.sample.logging.WisefySampleLogger

private const val LOG_TAG = "MiscScreen"

@Composable
internal fun MiscScreen(
    wisefy: WisefyApi,
    viewModel: MiscViewModel = viewModel(factory = MiscViewModelFactory(wisefy))
) {
    val getFrequencyPermissionLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                @Suppress("MissingPermission")
                viewModel.getFrequency()
            } else {
                WisefySampleLogger.w(LOG_TAG, "Permissions for getting frequency are denied")
                viewModel.onGetFrequencyPermissionsError()
            }
        }

    val getIPPermissionLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                @Suppress("MissingPermission")
                viewModel.getIP()
            } else {
                WisefySampleLogger.w(LOG_TAG, "Permissions for getting ip are denied")
                viewModel.onGetIPPermissionsError()
            }
        }

    val getNearbyAccessPointsPermissionsLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                @Suppress("MissingPermission")
                viewModel.getNearbyAccessPoints()
            } else {
                WisefySampleLogger.w(LOG_TAG, "Permissions for getting nearby access points are denied")
                viewModel.onGetNearbyAccessPointsPermissionError()
            }
        }

    val getSavedNetworksPermissionLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                @Suppress("MissingPermission")
                viewModel.getSavedNetworks()
            } else {
                WisefySampleLogger.w(LOG_TAG, "Permissions for getting saved networks are denied")
                viewModel.onGetSavedNetworksPermissionsError()
            }
        }

    val onMiscOptionClicked: (MiscScreenOption) -> Unit = { option ->
        when (option) {
            MiscScreenOption.DISABLE_WIFI -> viewModel.disableWifi()
            MiscScreenOption.ENABLE_WIFI -> viewModel.enableWifi()
            MiscScreenOption.GET_CURRENT_NETWORK -> viewModel.getCurrentNetwork()
            MiscScreenOption.GET_CURRENT_NETWORK_INFO -> viewModel.getCurrentNetworkInfo()
            MiscScreenOption.GET_FREQUENCY -> getFrequencyPermissionLauncher.launch(ACCESS_FINE_LOCATION)
            MiscScreenOption.GET_IP -> getIPPermissionLauncher.launch(ACCESS_FINE_LOCATION)
            MiscScreenOption.GET_NEARBY_ACCESS_POINTS -> {
                getNearbyAccessPointsPermissionsLauncher.launch(ACCESS_FINE_LOCATION)
            }
            MiscScreenOption.GET_SAVED_NETWORKS -> getSavedNetworksPermissionLauncher.launch(ACCESS_FINE_LOCATION)
        }
    }

    MiscScreenContent(
        uiState = { viewModel.uiState.value },
        onMiscOptionClicked = onMiscOptionClicked,
        viewModel = viewModel
    )
}

@Preview(showBackground = true)
@Composable
internal fun MiscScreenLightPreview() {
    MiscScreen(Wisefy.Brains(LocalContext.current.applicationContext).getSmarts())
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
internal fun MiscScreenDarkPreview() {
    MiscScreen(Wisefy.Brains(LocalContext.current.applicationContext).getSmarts())
}
