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

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.isupatches.android.wisefy.core.exceptions.WisefyException
import com.isupatches.android.wisefy.networkconnection.entities.DisconnectFromCurrentNetworkResult
import com.isupatches.android.wisefy.networkinfo.entities.GetNetworkConnectionStatusResult
import com.isupatches.android.wisefy.networkinfo.entities.NetworkConnectionStatusData
import com.isupatches.android.wisefy.networkinfo.entities.NetworkData
import com.isupatches.android.wisefy.sample.R
import com.isupatches.android.wisefy.sample.ui.ComposablePreviewWisefy
import com.isupatches.android.wisefy.sample.ui.components.WisefySampleNoticeDialog
import com.isupatches.android.wisefy.wifi.entities.DisableWifiResult
import com.isupatches.android.wisefy.wifi.entities.EnableWifiResult

@Composable
internal fun MiscScreenDialogContent(
    dialogState: () -> MiscDialogState,
    viewModel: MiscViewModel
) {
    when (val currentDialogState = dialogState()) {
        is MiscDialogState.None -> {
            // No-op, no dialog
        }
        is MiscDialogState.Failure.WisefyAsync -> {
            WisefySampleNoticeDialog(
                title = R.string.wisefy_async_error,
                body = R.string.wisefy_async_error_descriptions_args,
                currentDialogState.exception.message ?: "",
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscDialogState.DisableWifi.Failure -> {
            WisefySampleNoticeDialog(
                title = R.string.disable_wifi,
                body = R.string.failure_disabling_wifi_args,
                currentDialogState.result,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscDialogState.DisableWifi.PermissionsError -> {
            WisefySampleNoticeDialog(
                title = R.string.permission_error,
                body = R.string.permission_error_disable_wifi,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscDialogState.DisableWifi.Success -> {
            WisefySampleNoticeDialog(
                title = R.string.disable_wifi,
                body = R.string.success_disabling_wifi_args,
                currentDialogState.result,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscDialogState.DisconnectFromCurrentNetwork.Failure -> {
            WisefySampleNoticeDialog(
                title = R.string.disconnect_from_current_network,
                body = R.string.failure_disconnecting_from_current_network_args,
                currentDialogState.result,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscDialogState.DisconnectFromCurrentNetwork.Success -> {
            WisefySampleNoticeDialog(
                title = R.string.disconnect_from_current_network,
                body = R.string.success_disconnecting_from_current_network_args,
                currentDialogState.result,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscDialogState.EnableWifi.Failure -> {
            WisefySampleNoticeDialog(
                title = R.string.enabled_wifi,
                body = R.string.failure_enabling_wifi_args,
                currentDialogState.result,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscDialogState.EnableWifi.PermissionsError -> {
            WisefySampleNoticeDialog(
                title = R.string.permission_error,
                body = R.string.permission_error_enable_wifi,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscDialogState.EnableWifi.Success -> {
            WisefySampleNoticeDialog(
                title = R.string.enabled_wifi,
                body = R.string.success_enabling_wifi_args,
                currentDialogState.result,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscDialogState.GetCurrentNetwork.Failure -> {
            WisefySampleNoticeDialog(
                title = R.string.get_current_network,
                body = R.string.no_current_network,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscDialogState.GetCurrentNetwork.PermissionsError -> {
            WisefySampleNoticeDialog(
                title = R.string.permission_error,
                body = R.string.permission_error_get_current_network,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscDialogState.GetCurrentNetwork.Success -> {
            WisefySampleNoticeDialog(
                title = R.string.get_current_network,
                body = R.string.current_network_args,
                currentDialogState.network,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscDialogState.GetNetworkConnectionStatus.Success -> {
            WisefySampleNoticeDialog(
                title = R.string.get_network_connection_status,
                body = R.string.network_connection_status_args,
                currentDialogState.data,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscDialogState.GetNetworkConnectionStatus.PermissionsError -> {
            WisefySampleNoticeDialog(
                title = R.string.permission_error,
                body = R.string.permission_error_get_network_connection_status,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscDialogState.GetSavedNetworks.Failure -> {
            WisefySampleNoticeDialog(
                title = R.string.get_saved_networks,
                body = R.string.no_saved_networks_found,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscDialogState.GetSavedNetworks.PermissionsError -> {
            WisefySampleNoticeDialog(
                title = R.string.permission_error,
                body = R.string.permission_error_get_saved_networks,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscDialogState.GetSavedNetworks.Success -> {
            WisefySampleNoticeDialog(
                title = R.string.get_saved_networks,
                body = R.string.saved_network_args,
                currentDialogState.savedNetworks,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscDialogState.IsWifiEnabled.False -> {
            WisefySampleNoticeDialog(
                title = R.string.is_wifi_enabled,
                body = R.string.wifi_is_disabled,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscDialogState.IsWifiEnabled.PermissionsError -> {
            WisefySampleNoticeDialog(
                title = R.string.permission_error,
                body = R.string.permission_error_is_wifi_enabled,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscDialogState.IsWifiEnabled.True -> {
            WisefySampleNoticeDialog(
                title = R.string.is_wifi_enabled,
                body = R.string.wifi_is_enabled,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
@Suppress("UnusedPrivateMember")
private fun MiscScreenDialogContentLightPreview(
    @PreviewParameter(MiscScreenDialogStatePreviewParameterProvider::class) dialogState: MiscDialogState
) {
    MiscScreenDialogContent(
        viewModel = DefaultMiscViewModel(
            wisefy = ComposablePreviewWisefy()
        ),
        dialogState = { dialogState }
    )
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
@Suppress("UnusedPrivateMember")
private fun MiscScreenDialogContentDarkPreview(
    @PreviewParameter(MiscScreenDialogStatePreviewParameterProvider::class) dialogState: MiscDialogState
) {
    MiscScreenDialogContent(
        viewModel = DefaultMiscViewModel(
            wisefy = ComposablePreviewWisefy()
        ),
        dialogState = { dialogState }
    )
}

private class MiscScreenDialogStatePreviewParameterProvider : PreviewParameterProvider<MiscDialogState> {
    override val values: Sequence<MiscDialogState> = sequenceOf(
        MiscDialogState.Failure.WisefyAsync(WisefyException("", null)),
        MiscDialogState.DisableWifi.Success(DisableWifiResult.Success.Disabled),
        MiscDialogState.DisableWifi.Failure(DisableWifiResult.Failure.UnableToDisable),
        MiscDialogState.DisableWifi.PermissionsError,
        MiscDialogState.DisconnectFromCurrentNetwork.Success(DisconnectFromCurrentNetworkResult.Success.True),
        MiscDialogState.DisconnectFromCurrentNetwork.Failure(DisconnectFromCurrentNetworkResult.Failure.False),
        MiscDialogState.EnableWifi.Success(EnableWifiResult.Success.Enabled),
        MiscDialogState.EnableWifi.Failure(EnableWifiResult.Failure.UnableToEnable),
        MiscDialogState.EnableWifi.PermissionsError,
        MiscDialogState.GetCurrentNetwork.Failure,
        MiscDialogState.GetCurrentNetwork.PermissionsError,
        MiscDialogState.GetCurrentNetwork.Success(NetworkData(null, null, null, null)),
        MiscDialogState.GetNetworkConnectionStatus.Success(
            GetNetworkConnectionStatusResult(
                value = NetworkConnectionStatusData(
                    isConnected = false,
                    isConnectedToMobileNetwork = false,
                    isConnectedToWifiNetwork = false,
                    isRoaming = false,
                    ssidOfNetworkConnectedTo = null,
                    bssidOfNetworkConnectedTo = null,
                    ip = null
                )
            )
        ),
        MiscDialogState.GetNetworkConnectionStatus.PermissionsError,
        MiscDialogState.GetSavedNetworks.Failure,
        MiscDialogState.GetSavedNetworks.PermissionsError,
        MiscDialogState.GetSavedNetworks.Success(emptyList()),
        MiscDialogState.IsWifiEnabled.False,
        MiscDialogState.IsWifiEnabled.True,
        MiscDialogState.IsWifiEnabled.PermissionsError
    )
}
