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
    dialogState: MiscScreenDialogState,
    viewModel: MiscViewModel
) {
    when (dialogState) {
        is MiscScreenDialogState.None -> {
            // No-op, no dialog
        }
        is MiscScreenDialogState.Success.DisableWifi -> {
            WisefySampleNoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.wifi_disabled,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.Success.EnableWifi -> {
            WisefySampleNoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.wifi_enabled,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.Success.GetCurrentNetwork -> {
            WisefySampleNoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.current_network_args,
                dialogState.network,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.Success.GetCurrentNetworkInfo -> {
            WisefySampleNoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.current_network_info_args,
                dialogState.networkInfo,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.Success.GetFrequency -> {
            WisefySampleNoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.frequency_args,
                dialogState.frequency.value,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.Success.GetIP -> {
            WisefySampleNoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.ip_args,
                dialogState.ip.value,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.Success.GetNearbyAccessPoints -> {
            WisefySampleNoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.access_point_args,
                dialogState.accessPoints,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.Success.GetSavedNetworks -> {
            WisefySampleNoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.saved_network_args,
                dialogState.savedNetworks,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.Failure.DisplayAndroidQMessage -> {
            WisefySampleNoticeDialog(
                title = R.string.android_q_notice,
                body = R.string.android_q_wifi_message,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.Failure.DisableWifi -> {
            WisefySampleNoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.failure_disabling_wifi,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.Failure.EnableWifi -> {
            WisefySampleNoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.failure_enabling_wifi,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.Failure.GetCurrentNetwork -> {
            WisefySampleNoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.no_current_network,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.Failure.GetCurrentNetworkInfo -> {
            WisefySampleNoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.no_current_network_info,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.Failure.GetFrequency -> {
            WisefySampleNoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.failure_retrieving_frequency,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.Failure.GetIP -> {
            WisefySampleNoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.failure_retrieving_ip,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.Failure.GetNearbyAccessPoints -> {
            WisefySampleNoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.no_access_points_found,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.Failure.GetSavedNetworks -> {
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
                dialogState.throwable.message ?: "",
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.PermissionsError.GetFrequency -> {
            WisefySampleNoticeDialog(
                title = R.string.permission_error,
                body = R.string.permission_error_get_frequency,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.PermissionsError.GetIP -> {
            WisefySampleNoticeDialog(
                title = R.string.permission_error,
                body = R.string.permission_error_get_ip,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.PermissionsError.GetNearbyAccessPoints -> {
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
    }
}
