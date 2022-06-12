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
import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.isupatches.android.wisefy.Wisefy
import com.isupatches.android.wisefy.WisefyApi
import com.isupatches.android.wisefy.sample.R
import com.isupatches.android.wisefy.sample.dialogs.NoticeDialog
import com.isupatches.android.wisefy.sample.logging.WisefySampleLogger
import com.isupatches.android.wisefy.sample.ui.primitives.WisefySampleColorPalette
import com.isupatches.android.wisefy.sample.ui.primitives.WisefySamplePrimaryButtonColors
import com.isupatches.android.wisefy.sample.ui.primitives.WisefySampleSizes
import com.isupatches.android.wisefy.sample.ui.primitives.WisefySampleTypography
import com.isupatches.android.wisefy.sample.ui.theme.WisefySampleTheme

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

    WisefySampleTheme {
        MiscScreenContent(
            uiState = { viewModel.uiState.value },
            onMiscOptionClicked = onMiscOptionClicked,
            onMiscDialogClosed = { viewModel.onDialogClosed() }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun MiscScreenContent(
    uiState: () -> MiscScreenUIState,
    onMiscOptionClicked: (MiscScreenOption) -> Unit,
    onMiscDialogClosed: () -> Unit
) {
    val listState = rememberLazyListState()
    val currentUIState = uiState()
    LazyColumn(
        state = listState,
        verticalArrangement = Arrangement.spacedBy(WisefySampleSizes.Padding.Large),
        contentPadding = PaddingValues(
            top = WisefySampleSizes.Padding.XXLARGE,
            bottom = WisefySampleSizes.Padding.Large
        )
    ) {
        items(MiscScreenOption.values(), { it.id }) { option ->
            Row(modifier = Modifier.animateItemPlacement()) {
                MiscScreenOptionRow(option = option, onClick = onMiscOptionClicked)
            }
        }
    }
    if (currentUIState.loadingState.isLoading) {
        LinearProgressIndicator(color = WisefySampleColorPalette.Secondary, modifier = Modifier.fillMaxWidth())
    }

    when (currentUIState.dialogState) {
        is MiscScreenDialogState.None -> {
            // No-op, no dialog
        }
        is MiscScreenDialogState.Success.DisableWifi -> {
            NoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.wifi_disabled,
                onClose = {
                    onMiscDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.Success.EnableWifi -> {
            NoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.wifi_enabled,
                onClose = {
                    onMiscDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.Success.GetCurrentNetwork -> {
            NoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.current_network_args,
                currentUIState.dialogState.network,
                onClose = {
                    onMiscDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.Success.GetCurrentNetworkInfo -> {
            NoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.current_network_info_args,
                currentUIState.dialogState.networkInfo,
                onClose = {
                    onMiscDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.Success.GetFrequency -> {
            NoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.frequency_args,
                currentUIState.dialogState.frequency.value,
                onClose = {
                    onMiscDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.Success.GetIP -> {
            NoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.ip_args,
                currentUIState.dialogState.ip.value,
                onClose = {
                    onMiscDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.Success.GetNearbyAccessPoints -> {
            NoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.access_point_args,
                currentUIState.dialogState.accessPoints,
                onClose = {
                    onMiscDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.Success.GetSavedNetworks -> {
            NoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.saved_network_args,
                currentUIState.dialogState.savedNetworks,
                onClose = {
                    onMiscDialogClosed()
                }
            )
        }
        MiscScreenDialogState.Failure.DisplayAndroidQMessage -> {
            NoticeDialog(
                title = R.string.android_q_notice,
                body = R.string.android_q_wifi_message,
                onClose = {
                    onMiscDialogClosed()
                }
            )
        }
        MiscScreenDialogState.Failure.DisableWifi -> {
            NoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.failure_disabling_wifi,
                onClose = {
                    onMiscDialogClosed()
                }
            )
        }
        MiscScreenDialogState.Failure.EnableWifi -> {
            NoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.failure_enabling_wifi,
                onClose = {
                    onMiscDialogClosed()
                }
            )
        }
        MiscScreenDialogState.Failure.GetCurrentNetwork -> {
            NoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.no_current_network,
                onClose = {
                    onMiscDialogClosed()
                }
            )
        }
        MiscScreenDialogState.Failure.GetCurrentNetworkInfo -> {
            NoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.no_current_network_info,
                onClose = {
                    onMiscDialogClosed()
                }
            )
        }
        MiscScreenDialogState.Failure.GetFrequency -> {
            NoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.failure_retrieving_frequency,
                onClose = {
                    onMiscDialogClosed()
                }
            )
        }
        MiscScreenDialogState.Failure.GetIP -> {
            NoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.failure_retrieving_ip,
                onClose = {
                    onMiscDialogClosed()
                }
            )
        }
        MiscScreenDialogState.Failure.GetNearbyAccessPoints -> {
            NoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.no_access_points_found,
                onClose = {
                    onMiscDialogClosed()
                }
            )
        }
        MiscScreenDialogState.Failure.GetSavedNetworks -> {
            NoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.no_saved_networks_found,
                onClose = {
                    onMiscDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.Failure.WisefyAsync -> {
            NoticeDialog(
                title = R.string.wisefy_async_error,
                body = R.string.wisefy_async_error_descriptions_args,
                currentUIState.dialogState.throwable.message ?: "",
                onClose = {
                    onMiscDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.PermissionsError.GetFrequency -> {
            NoticeDialog(
                title = R.string.permission_error,
                body = R.string.permission_error_get_frequency,
                onClose = {
                    onMiscDialogClosed()
                }
            )
        }
        MiscScreenDialogState.PermissionsError.GetIP -> {
            NoticeDialog(
                title = R.string.permission_error,
                body = R.string.permission_error_get_ip,
                onClose = {
                    onMiscDialogClosed()
                }
            )
        }
        MiscScreenDialogState.PermissionsError.GetNearbyAccessPoints -> {
            NoticeDialog(
                title = R.string.permission_error,
                body = R.string.permission_error_get_nearby_access_points,
                onClose = {
                    onMiscDialogClosed()
                }
            )
        }
        MiscScreenDialogState.PermissionsError.GetSavedNetworks -> {
            NoticeDialog(
                title = R.string.permission_error,
                body = R.string.permission_error_get_saved_networks,
                onClose = {
                    onMiscDialogClosed()
                }
            )
        }
    }
}

private enum class MiscScreenOption(val id: Long, @StringRes val stringResId: Int) {
    DISABLE_WIFI(R.id.disable_wifi.toLong(), R.string.disable_wifi),
    ENABLE_WIFI(R.id.enable_wifi.toLong(), R.string.enabled_wifi),
    GET_CURRENT_NETWORK(R.id.get_current_network.toLong(), R.string.get_current_network),
    GET_CURRENT_NETWORK_INFO(R.id.get_current_network_info.toLong(), R.string.get_current_network_info),
    GET_FREQUENCY(R.id.get_frequency.toLong(), R.string.get_frequency),
    GET_IP(R.id.get_ip.toLong(), R.string.get_ip),
    GET_NEARBY_ACCESS_POINTS(R.id.get_nearby_access_points.toLong(), R.string.get_nearby_access_points),
    GET_SAVED_NETWORKS(R.id.get_saved_networks.toLong(), R.string.get_saved_networks)
}

@Composable
private fun MiscScreenOptionRow(
    option: MiscScreenOption,
    onClick: (MiscScreenOption) -> Unit
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = WisefySampleSizes.Margin.Large, end = WisefySampleSizes.Margin.Large),
        content = {
            Text(
                text = LocalContext.current.getString(option.stringResId),
                style = WisefySampleTypography.body1,
                modifier = Modifier.padding(
                    top = WisefySampleSizes.Margin.Medium,
                    bottom = WisefySampleSizes.Margin.Medium,
                    start = WisefySampleSizes.Margin.Large,
                    end = WisefySampleSizes.Margin.Large,
                )
            )
        },
        colors = WisefySamplePrimaryButtonColors(),
        onClick = {
            onClick(option)
        }
    )
}

@Preview(showBackground = true)
@Composable
internal fun MiscScreenLayoutLightPreview() {
    MiscScreen(Wisefy.Brains(LocalContext.current.applicationContext).getSmarts())
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
internal fun MiscScreenLayoutDarkPreview() {
    MiscScreen(Wisefy.Brains(LocalContext.current.applicationContext).getSmarts())
}
