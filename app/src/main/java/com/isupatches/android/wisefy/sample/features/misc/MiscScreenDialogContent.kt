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
import com.isupatches.android.wisefy.sample.ui.components.NoticeDialog

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
            NoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.wifi_disabled,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.Success.EnableWifi -> {
            NoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.wifi_enabled,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.Success.GetCurrentNetwork -> {
            NoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.current_network_args,
                dialogState.network,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.Success.GetCurrentNetworkInfo -> {
            NoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.current_network_info_args,
                dialogState.networkInfo,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.Success.GetFrequency -> {
            NoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.frequency_args,
                dialogState.frequency.value,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.Success.GetIP -> {
            NoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.ip_args,
                dialogState.ip.value,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.Success.GetNearbyAccessPoints -> {
            NoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.access_point_args,
                dialogState.accessPoints,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.Success.GetSavedNetworks -> {
            NoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.saved_network_args,
                dialogState.savedNetworks,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.Failure.DisplayAndroidQMessage -> {
            NoticeDialog(
                title = R.string.android_q_notice,
                body = R.string.android_q_wifi_message,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.Failure.DisableWifi -> {
            NoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.failure_disabling_wifi,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.Failure.EnableWifi -> {
            NoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.failure_enabling_wifi,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.Failure.GetCurrentNetwork -> {
            NoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.no_current_network,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.Failure.GetCurrentNetworkInfo -> {
            NoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.no_current_network_info,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.Failure.GetFrequency -> {
            NoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.failure_retrieving_frequency,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.Failure.GetIP -> {
            NoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.failure_retrieving_ip,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.Failure.GetNearbyAccessPoints -> {
            NoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.no_access_points_found,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.Failure.GetSavedNetworks -> {
            NoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.no_saved_networks_found,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.Failure.WisefyAsync -> {
            NoticeDialog(
                title = R.string.wisefy_async_error,
                body = R.string.wisefy_async_error_descriptions_args,
                dialogState.throwable.message ?: "",
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.PermissionsError.GetFrequency -> {
            NoticeDialog(
                title = R.string.permission_error,
                body = R.string.permission_error_get_frequency,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.PermissionsError.GetIP -> {
            NoticeDialog(
                title = R.string.permission_error,
                body = R.string.permission_error_get_ip,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.PermissionsError.GetNearbyAccessPoints -> {
            NoticeDialog(
                title = R.string.permission_error,
                body = R.string.permission_error_get_nearby_access_points,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is MiscScreenDialogState.PermissionsError.GetSavedNetworks -> {
            NoticeDialog(
                title = R.string.permission_error,
                body = R.string.permission_error_get_saved_networks,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
    }
}
