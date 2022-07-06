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

import androidx.compose.runtime.Composable
import com.isupatches.android.wisefy.sample.R
import com.isupatches.android.wisefy.sample.ui.components.WisefySampleNoticeDialog

@Composable
internal fun MiscScreenDialogContent(
    dialogState: () -> MiscScreenDialogState,
    viewModel: MiscViewModel
) {
    when (val currentDialogState = dialogState()) {
        is MiscScreenDialogState.None -> {
            // No-op, no dialog
        }
        is MiscScreenDialogState.DisableWifi.Success -> {
            WisefySampleNoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.wifi_disabled,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.EnableWifi.Success -> {
            WisefySampleNoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.wifi_enabled,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.GetCurrentNetwork.Success -> {
            WisefySampleNoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.current_network_args,
                currentDialogState.network,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.GetCurrentNetworkInfo.Success -> {
            WisefySampleNoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.current_network_info_args,
                currentDialogState.networkInfo,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.GetFrequency.Success -> {
            WisefySampleNoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.frequency_args,
                currentDialogState.frequency.value,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.GetIP.Success -> {
            WisefySampleNoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.ip_args,
                currentDialogState.ip.value,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.GetNearbyAccessPoints.Success -> {
            WisefySampleNoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.access_point_args,
                currentDialogState.accessPoints,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.GetSavedNetworks.Success -> {
            WisefySampleNoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.saved_network_args,
                currentDialogState.savedNetworks,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.EnableWifi.DisplayAndroidQMessage -> {
            WisefySampleNoticeDialog(
                title = R.string.android_q_notice,
                body = R.string.android_q_enable_wifi_message,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.DisableWifi.DisplayAndroidQMessage -> {
            WisefySampleNoticeDialog(
                title = R.string.android_q_notice,
                body = R.string.android_q_disable_wifi_message,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.DisconnectFromCurrentNetwork.DisplayAndroidQMessage -> {
            WisefySampleNoticeDialog(
                title = R.string.android_q_notice,
                body = R.string.android_q_disconnect_message,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.DisableWifi.Failure -> {
            WisefySampleNoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.failure_disabling_wifi,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.EnableWifi.Failure -> {
            WisefySampleNoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.failure_enabling_wifi,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.GetCurrentNetwork.Failure -> {
            WisefySampleNoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.no_current_network,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.GetCurrentNetworkInfo.Failure -> {
            WisefySampleNoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.no_current_network_info,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.GetFrequency.Failure -> {
            WisefySampleNoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.failure_retrieving_frequency,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.GetIP.Failure -> {
            WisefySampleNoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.failure_retrieving_ip,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.GetNearbyAccessPoints.Failure -> {
            WisefySampleNoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.no_access_points_found,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.GetSavedNetworks.Failure -> {
            WisefySampleNoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.no_saved_networks_found,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.Failure.WisefyAsync -> {
            WisefySampleNoticeDialog(
                title = R.string.wisefy_async_error,
                body = R.string.wisefy_async_error_descriptions_args,
                currentDialogState.throwable.message ?: "",
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.GetFrequency.PermissionsError -> {
            WisefySampleNoticeDialog(
                title = R.string.permission_error,
                body = R.string.permission_error_get_frequency,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.GetIP.PermissionsError -> {
            WisefySampleNoticeDialog(
                title = R.string.permission_error,
                body = R.string.permission_error_get_ip,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.GetNearbyAccessPoints.PermissionsError -> {
            WisefySampleNoticeDialog(
                title = R.string.permission_error,
                body = R.string.permission_error_get_nearby_access_points,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.PermissionsError.GetSavedNetworks -> {
            WisefySampleNoticeDialog(
                title = R.string.permission_error,
                body = R.string.permission_error_get_saved_networks,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.DisconnectFromCurrentNetwork.Success.RequestPlaced -> {
            WisefySampleNoticeDialog(
                title = R.string.disconnect_from_current_network,
                body = R.string.succeeded_placing_request_to_disconnect_from_current_network,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.DisconnectFromCurrentNetwork.Success.Disconnected -> {
            WisefySampleNoticeDialog(
                title = R.string.disconnect_from_current_network,
                body = R.string.succeeded_disconnecting_from_current_network,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.DisconnectFromCurrentNetwork.Failure.NetworkNotFound -> {
            WisefySampleNoticeDialog(
                title = R.string.disconnect_from_current_network,
                body = R.string.network_not_found_to_disconnect,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.DisconnectFromCurrentNetwork.Failure.UnableToDisconnect -> {
            WisefySampleNoticeDialog(
                title = R.string.disconnect_from_current_network,
                body = R.string.failed_disconnecting_from_current_network,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.DisconnectFromCurrentNetwork.PermissionsError -> {
            WisefySampleNoticeDialog(
                title = R.string.permission_error,
                body = R.string.permission_error_disconnecting_from_current_network,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
    }
}
