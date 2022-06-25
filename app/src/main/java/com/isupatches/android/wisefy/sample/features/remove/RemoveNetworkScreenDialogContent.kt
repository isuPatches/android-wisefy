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
package com.isupatches.android.wisefy.sample.features.remove

import androidx.compose.runtime.Composable
import com.isupatches.android.wisefy.sample.R
import com.isupatches.android.wisefy.sample.ui.components.NoticeDialog

@Composable
internal fun RemoveNetworkScreenDialogContent(
    dialogState: RemoveNetworkDialogState,
    viewModel: RemoveNetworkViewModel
) {
    when (dialogState) {
        is RemoveNetworkDialogState.None -> {
            // No-op, no dialog
        }
        is RemoveNetworkDialogState.Success -> {
            NoticeDialog(
                title = R.string.remove_network_result,
                body = R.string.succeeded_removing_network_args,
                dialogState.result,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is RemoveNetworkDialogState.PermissionsError -> {
            NoticeDialog(
                title = R.string.permission_error,
                body = R.string.permission_error_remove_network,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is RemoveNetworkDialogState.Failure.NotFoundToRemove -> {
            NoticeDialog(
                title = R.string.remove_network_result,
                body = R.string.network_not_found_to_remove,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is RemoveNetworkDialogState.Failure.UnableToRemove -> {
            NoticeDialog(
                title = R.string.remove_network_result,
                body = R.string.failed_removing_network_args,
                dialogState.result,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is RemoveNetworkDialogState.Failure.WisefyAsync -> {
            NoticeDialog(
                title = R.string.wisefy_async_error,
                body = R.string.wisefy_async_error_descriptions_args,
                dialogState.throwable.message ?: "",
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is RemoveNetworkDialogState.InputError -> {
            NoticeDialog(
                title = R.string.wisefy_action_result,
                body = R.string.network_input_invalid,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
    }
}
