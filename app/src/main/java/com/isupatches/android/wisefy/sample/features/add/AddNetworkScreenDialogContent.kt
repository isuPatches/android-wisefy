/*
 * Copyright 2022 Patches Barrett
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

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkResult
import com.isupatches.android.wisefy.core.exceptions.WisefyException
import com.isupatches.android.wisefy.sample.R
import com.isupatches.android.wisefy.sample.ui.ComposablePreviewWisefy
import com.isupatches.android.wisefy.sample.ui.components.WisefySampleNoticeDialog
import com.isupatches.android.wisefy.sample.util.DefaultSdkUtil

@Composable
internal fun AddNetworkScreenDialogContent(
    dialogState: () -> AddNetworkDialogState,
    viewModel: AddNetworkViewModel
) {
    when (val currentDialogState = dialogState()) {
        is AddNetworkDialogState.None -> {
            // No-op, no dialog
        }
        is AddNetworkDialogState.AddNetwork.Failure -> {
            WisefySampleNoticeDialog(
                title = R.string.add_network,
                body = R.string.failure_adding_network_args,
                currentDialogState.result,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is AddNetworkDialogState.AddNetwork.Success -> {
            WisefySampleNoticeDialog(
                title = R.string.add_network,
                body = R.string.success_adding_network_args,
                currentDialogState.result,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is AddNetworkDialogState.AddNetwork.PermissionsError.AddOpenNetwork -> {
            WisefySampleNoticeDialog(
                title = R.string.permission_error,
                body = R.string.permission_error_add_open_network,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is AddNetworkDialogState.AddNetwork.PermissionsError.AddWPA2Network -> {
            WisefySampleNoticeDialog(
                title = R.string.permission_error,
                body = R.string.permission_error_add_wpa2_network,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is AddNetworkDialogState.AddNetwork.PermissionsError.AddWPA3Network -> {
            WisefySampleNoticeDialog(
                title = R.string.permission_error,
                body = R.string.permission_error_add_wpa3_network,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is AddNetworkDialogState.Failure.WisefyAsync -> {
            WisefySampleNoticeDialog(
                title = R.string.wisefy_async_error,
                body = R.string.wisefy_async_error_descriptions_args,
                currentDialogState.exception.message ?: "",
                currentDialogState.exception.cause?.message ?: "",
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is AddNetworkDialogState.InputError.SSID -> {
            WisefySampleNoticeDialog(
                title = R.string.input_error,
                body = R.string.ssid_input_invalid,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is AddNetworkDialogState.InputError.Passphrase -> {
            WisefySampleNoticeDialog(
                title = R.string.input_error,
                body = R.string.passphrase_input_invalid,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is AddNetworkDialogState.InputError.BSSID -> {
            WisefySampleNoticeDialog(
                title = R.string.input_error,
                body = R.string.bssid_input_invalid,
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
private fun AddNetworkScreenDialogContentLightPreview(
    @PreviewParameter(AddNetworkDialogStatePreviewParameterProvider::class) dialogState: AddNetworkDialogState
) {
    AddNetworkScreenDialogContent(
        viewModel = DefaultAddNetworkViewModel(
            context = LocalContext.current,
            wisefy = ComposablePreviewWisefy(),
            sdkUtil = DefaultSdkUtil()
        ),
        dialogState = { dialogState }
    )
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
@Suppress("UnusedPrivateMember")
private fun AddNetworkScreenDialogContentDarkPreview(
    @PreviewParameter(AddNetworkDialogStatePreviewParameterProvider::class) dialogState: AddNetworkDialogState
) {
    AddNetworkScreenDialogContent(
        viewModel = DefaultAddNetworkViewModel(
            context = LocalContext.current,
            wisefy = ComposablePreviewWisefy(),
            sdkUtil = DefaultSdkUtil()
        ),
        dialogState = { dialogState }
    )
}

private class AddNetworkDialogStatePreviewParameterProvider : PreviewParameterProvider<AddNetworkDialogState> {
    override val values: Sequence<AddNetworkDialogState> = sequenceOf(
        AddNetworkDialogState.Failure.WisefyAsync(WisefyException("", null)),
        AddNetworkDialogState.AddNetwork.Success(AddNetworkResult.Success.ResultCode(0)),
        AddNetworkDialogState.AddNetwork.Failure(AddNetworkResult.Failure.ResultCode(-1)),
        AddNetworkDialogState.AddNetwork.PermissionsError.AddOpenNetwork,
        AddNetworkDialogState.AddNetwork.PermissionsError.AddWPA2Network,
        AddNetworkDialogState.AddNetwork.PermissionsError.AddWPA3Network,
        AddNetworkDialogState.InputError.SSID,
        AddNetworkDialogState.InputError.Passphrase,
        AddNetworkDialogState.InputError.BSSID
    )
}
