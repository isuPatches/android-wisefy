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
import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.content.res.Configuration
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.isupatches.android.wisefy.sample.ComposablePreviewWisefy
import com.isupatches.android.wisefy.sample.R
import com.isupatches.android.wisefy.sample.logging.WisefySampleLogger
import com.isupatches.android.wisefy.sample.ui.components.WisefyPrimaryButton
import com.isupatches.android.wisefy.sample.ui.primitives.WisefySampleSizes
import com.isupatches.android.wisefy.sample.ui.theme.WisefySampleTheme
import com.isupatches.android.wisefy.sample.util.DefaultSdkUtil
import com.isupatches.android.wisefy.sample.util.SdkUtil
import kotlinx.coroutines.launch

private const val LOG_TAG = "MiscScreenContent"

@Composable
internal fun MiscScreenContent(
    viewModel: MiscViewModel,
    sdkUtil: SdkUtil,
    router: MiscScreenRouter
) {
    WisefySampleTheme {
        val scope = rememberCoroutineScope()
        val context = LocalContext.current
        val getCurrentNetworkPermissionsLauncher =
            rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                if (isGranted) {
                    scope.launch {
                        @Suppress("MissingPermission")
                        viewModel.getCurrentNetwork()
                    }
                } else {
                    WisefySampleLogger.w(LOG_TAG, "Permissions for getting current network info are denied")
                    viewModel.onGetCurrentNetworkPermissionsError()
                }
            }

        val getNearbyAccessPointsPermissionsLauncher =
            rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                if (isGranted) {
                    scope.launch {
                        @Suppress("MissingPermission")
                        viewModel.getNearbyAccessPoints()
                    }
                } else {
                    WisefySampleLogger.w(LOG_TAG, "Permissions for getting nearby access points are denied")
                    viewModel.onGetNearbyAccessPointsPermissionError()
                }
            }

        val getNetworkConnectionStatusPermissionsLauncher =
            rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                if (isGranted) {
                    scope.launch {
                        @Suppress("MissingPermission")
                        viewModel.getNetworkConnectionStatus()
                    }
                } else {
                    WisefySampleLogger.w(LOG_TAG, "Permissions for getting network connection status are denied")
                    viewModel.onGetNetworkConnectionStatusPermissionError()
                }
            }

        val getSavedNetworksPermissionsLauncher =
            rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->
                if (result.all { it.value }) {
                    scope.launch {
                        @Suppress("MissingPermission")
                        viewModel.getSavedNetworks()
                    }
                } else {
                    WisefySampleLogger.w(LOG_TAG, "Permissions for getting saved networks are denied")
                    viewModel.onGetSavedNetworksPermissionsError()
                }
            }

        val onMiscOptionClicked: (MiscScreenOption) -> Unit = { option ->
            when (option) {
                MiscScreenOption.DISABLE_WIFI -> {
                    scope.launch {
                        if (sdkUtil.isAtLeastQ()) {
                            viewModel.disableWifi(context)
                        } else {
                            viewModel.disableWifi()
                        }
                    }
                }
                MiscScreenOption.DISCONNECT_FROM_CURRENT_NETWORK -> viewModel.disconnectFromCurrentNetwork()
                MiscScreenOption.ENABLE_WIFI -> {
                    scope.launch {
                        if (sdkUtil.isAtLeastQ()) {
                            viewModel.enableWifi(context)
                        } else {
                            viewModel.enableWifi()
                        }
                    }
                }
                MiscScreenOption.GET_CURRENT_NETWORK -> {
                    getCurrentNetworkPermissionsLauncher.launch(ACCESS_NETWORK_STATE)
                }
                MiscScreenOption.GET_NEARBY_ACCESS_POINTS -> {
                    getNearbyAccessPointsPermissionsLauncher.launch(ACCESS_FINE_LOCATION)
                }
                MiscScreenOption.GET_NETWORK_CONNECTION_STATUS -> {
                    getNetworkConnectionStatusPermissionsLauncher.launch(ACCESS_NETWORK_STATE)
                }
                MiscScreenOption.GET_SAVED_NETWORKS -> {
                    getSavedNetworksPermissionsLauncher.launch(arrayOf(ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE))
                }
                MiscScreenOption.IS_WIFI_ENABLED -> {
                    scope.launch {
                        viewModel.isWifiEnabled()
                    }
                }
                MiscScreenOption.SIGNAL_FUNCTIONS -> router.openSignalScreen()
            }
        }

        val listState = rememberLazyListState()
        LazyColumn(
            state = listState,
            verticalArrangement = Arrangement.spacedBy(WisefySampleSizes.Large),
            contentPadding = PaddingValues(
                top = WisefySampleSizes.WisefySampleTopMargin,
                bottom = WisefySampleSizes.WisefySampleBottomMargin,
                start = WisefySampleSizes.WisefySampleHorizontalMargins,
                end = WisefySampleSizes.WisefySampleHorizontalMargins
            )
        ) {
            items(MiscScreenOption.values(), { it.id }) { option ->
                @OptIn(ExperimentalFoundationApi::class)
                Row(modifier = Modifier.animateItemPlacement()) {
                    MiscScreenOptionRow(option = option, onClick = onMiscOptionClicked)
                }
            }
        }
    }
}

internal enum class MiscScreenOption(val id: Long, @StringRes val stringResId: Int) {
    DISABLE_WIFI(R.id.disable_wifi.toLong(), R.string.disable_wifi),
    DISCONNECT_FROM_CURRENT_NETWORK(
        R.id.disconnect_from_current_network.toLong(),
        R.string.disconnect_from_current_network
    ),
    ENABLE_WIFI(R.id.enable_wifi.toLong(), R.string.enabled_wifi),
    GET_CURRENT_NETWORK(R.id.get_current_network.toLong(), R.string.get_current_network),
    GET_NEARBY_ACCESS_POINTS(R.id.get_nearby_access_points.toLong(), R.string.get_nearby_access_points),
    GET_NETWORK_CONNECTION_STATUS(R.id.get_network_connection_status.toLong(), R.string.get_network_connection_status),
    GET_SAVED_NETWORKS(R.id.get_saved_networks.toLong(), R.string.get_saved_networks),
    IS_WIFI_ENABLED(R.id.is_wifi_enabled.toLong(), R.string.is_wifi_enabled),
    SIGNAL_FUNCTIONS(R.id.signal_functions.toLong(), R.string.signal_functions)
}

@Composable
private fun MiscScreenOptionRow(option: MiscScreenOption, onClick: (MiscScreenOption) -> Unit) {
    WisefyPrimaryButton(
        stringResId = option.stringResId,
        onClick = {
            onClick(option)
        }
    )
}

@Preview(showBackground = true)
@Composable
@Suppress("UnusedPrivateMember")
private fun MiscScreenContentLightPreview() {
    MiscScreenContent(
        viewModel = DefaultMiscViewModel(
            wisefy = ComposablePreviewWisefy(),
            sdkUtil = DefaultSdkUtil()
        ),
        sdkUtil = DefaultSdkUtil(),
        router = DefaultMiscScreenRouter(
            navController = rememberNavController()
        )
    )
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
@Suppress("UnusedPrivateMember")
private fun MiscScreenContentDarkPreview() {
    MiscScreenContent(
        viewModel = DefaultMiscViewModel(
            wisefy = ComposablePreviewWisefy(),
            sdkUtil = DefaultSdkUtil()
        ),
        sdkUtil = DefaultSdkUtil(),
        router = DefaultMiscScreenRouter(
            navController = rememberNavController()
        )
    )
}
