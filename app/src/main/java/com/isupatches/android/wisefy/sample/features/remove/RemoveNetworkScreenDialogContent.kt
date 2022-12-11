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

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.isupatches.android.wisefy.core.exceptions.WisefyException
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkResult
import com.isupatches.android.wisefy.sample.ComposablePreviewWisefy
import com.isupatches.android.wisefy.sample.R
import com.isupatches.android.wisefy.sample.ui.components.WisefySampleNoticeDialog

@Composable
internal fun RemoveNetworkScreenDialogContent(
    dialogState: () -> RemoveNetworkDialogState,
    viewModel: RemoveNetworkViewModel
) {
    when (val currentDialogState = dialogState()) {
        is RemoveNetworkDialogState.None -> {
            // No-op, no dialog
        }
        is RemoveNetworkDialogState.RemoveNetwork.Success -> {
            WisefySampleNoticeDialog(
                title = R.string.remove_network,
                body = R.string.succeeded_removing_network_args,
                currentDialogState.result,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is RemoveNetworkDialogState.RemoveNetwork.PermissionsError -> {
            WisefySampleNoticeDialog(
                title = R.string.permission_error,
                body = R.string.permission_error_remove_network,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is RemoveNetworkDialogState.RemoveNetwork.Failure -> {
            WisefySampleNoticeDialog(
                title = R.string.remove_network,
                body = R.string.failure_removing_network_args,
                currentDialogState.result,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is RemoveNetworkDialogState.Failure.WisefyAsync -> {
            WisefySampleNoticeDialog(
                title = R.string.wisefy_async_error,
                body = R.string.wisefy_async_error_descriptions_args,
                currentDialogState.exception.message ?: "",
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is RemoveNetworkDialogState.InputError -> {
            WisefySampleNoticeDialog(
                title = R.string.input_error,
                body = R.string.network_input_invalid,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RemoveNetworkScreenDialogContentLightPreview(
    @PreviewParameter(RemoveNetworkDialogStatePreviewParameterProvider::class) dialogState: RemoveNetworkDialogState
) {
    RemoveNetworkScreenDialogContent(
        viewModel = DefaultRemoveNetworkViewModel(
            context = LocalContext.current,
            wisefy = ComposablePreviewWisefy(),
        ),
        dialogState = { dialogState }
    )
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun RemoveNetworkScreenDialogContentDarkPreview(
    @PreviewParameter(RemoveNetworkDialogStatePreviewParameterProvider::class) dialogState: RemoveNetworkDialogState
) {
    RemoveNetworkScreenDialogContent(
        viewModel = DefaultRemoveNetworkViewModel(
            context = LocalContext.current,
            wisefy = ComposablePreviewWisefy(),
        ),
        dialogState = { dialogState }
    )
}

private class RemoveNetworkDialogStatePreviewParameterProvider : PreviewParameterProvider<RemoveNetworkDialogState> {
    override val values: Sequence<RemoveNetworkDialogState> = sequenceOf(
        RemoveNetworkDialogState.Failure.WisefyAsync(WisefyException("", null)),
        RemoveNetworkDialogState.InputError,
        RemoveNetworkDialogState.RemoveNetwork.Failure(RemoveNetworkResult.Failure.False),
        RemoveNetworkDialogState.RemoveNetwork.PermissionsError,
        RemoveNetworkDialogState.RemoveNetwork.Success(RemoveNetworkResult.Success.True)
    )
}
