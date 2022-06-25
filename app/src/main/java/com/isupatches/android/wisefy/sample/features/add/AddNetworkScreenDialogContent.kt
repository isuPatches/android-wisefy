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
import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkResult
import com.isupatches.android.wisefy.sample.R
import com.isupatches.android.wisefy.sample.ui.components.NoticeDialog

@Composable
internal fun AddNetworkScreenDialogContent(
    dialogState: AddNetworkDialogState,
    viewModel: AddNetworkViewModel
) {
    when (dialogState) {
        is AddNetworkDialogState.None -> {
            // No-op, no dialog
        }
        is AddNetworkDialogState.Failure.UnableToAdd -> {
            NoticeDialog(
                title = R.string.add_network_result,
                body = R.string.failed_adding_network_args,
                dialogState.result,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is AddNetworkDialogState.Failure.WisefyAsync -> {
            NoticeDialog(
                title = R.string.wisefy_async_error,
                body = R.string.wisefy_async_error_descriptions_args,
                dialogState.throwable.message ?: "",
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is AddNetworkDialogState.PermissionsError.AddOpenNetwork -> {
            NoticeDialog(
                title = R.string.add_network_result,
                body = R.string.permission_error_add_open_network,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is AddNetworkDialogState.PermissionsError.AddWPA2Network -> {
            NoticeDialog(
                title = R.string.add_network_result,
                body = R.string.permission_error_add_wpa2_network,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is AddNetworkDialogState.PermissionsError.AddWPA3Network -> {
            NoticeDialog(
                title = R.string.add_network_result,
                body = R.string.permission_error_add_wpa3_network,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is AddNetworkDialogState.Success -> {
            if (dialogState.result !is AddNetworkResult.Success.IntentLaunched) {
                NoticeDialog(
                    title = R.string.add_network_result,
                    body = R.string.succeeded_adding_network_args,
                    dialogState.result,
                    onClose = {
                        viewModel.onDialogClosed()
                    }
                )
            }
        }
        AddNetworkDialogState.InputError.SSID -> {
            NoticeDialog(
                title = R.string.add_network_result,
                body = R.string.network_input_invalid,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        AddNetworkDialogState.InputError.Passphrase -> {
            NoticeDialog(
                title = R.string.add_network_result,
                body = R.string.passphrase_input_invalid,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
    }
}
