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
package com.isupatches.android.wisefy.sample.features.misc.signal

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.isupatches.android.wisefy.core.exceptions.WisefyException
import com.isupatches.android.wisefy.sample.ComposablePreviewWisefy
import com.isupatches.android.wisefy.sample.R
import com.isupatches.android.wisefy.sample.ui.components.WisefySampleNoticeDialog
import com.isupatches.android.wisefy.sample.util.DefaultSdkUtil
import com.isupatches.android.wisefy.signal.entities.CalculateSignalLevelResult
import com.isupatches.android.wisefy.signal.entities.CompareSignalLevelResult

@Composable
internal fun SignalScreenDialogContent(
    dialogState: () -> SignalDialogState,
    viewModel: SignalViewModel
) {
    when (val currentDialogState = dialogState()) {
        is SignalDialogState.None -> {
            // No-op, no dialog
        }
        is SignalDialogState.CalculateSignalLevel.Failure -> {
            WisefySampleNoticeDialog(
                title = R.string.calculate_signal_level,
                body = R.string.failure_calculating_signal_level_args,
                currentDialogState.result,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is SignalDialogState.CalculateSignalLevel.Success -> {
            WisefySampleNoticeDialog(
                title = R.string.calculate_signal_level,
                body = R.string.succeeded_calculating_signal_level_args,
                currentDialogState.result,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is SignalDialogState.CompareSignalLevel.Success -> {
            WisefySampleNoticeDialog(
                title = R.string.compare_signal_level,
                body = R.string.succeeded_comparing_signal_level_args,
                currentDialogState.result,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is SignalDialogState.Failure.WisefyAsync -> {
            WisefySampleNoticeDialog(
                title = R.string.wisefy_async_error,
                body = R.string.wisefy_async_error_descriptions_args,
                currentDialogState.exception.message ?: "",
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is SignalDialogState.InputError.CalculateSignalLevel -> {
            WisefySampleNoticeDialog(
                title = R.string.input_error,
                body = R.string.rssi_input_invalid,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is SignalDialogState.InputError.CompareSignalLevel.RSSI1 -> {
            WisefySampleNoticeDialog(
                title = R.string.input_error,
                body = R.string.rssi_input_invalid,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is SignalDialogState.InputError.CompareSignalLevel.RSSI2 -> {
            WisefySampleNoticeDialog(
                title = R.string.input_error,
                body = R.string.rssi_input_invalid,
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
private fun SignalScreenDialogContentLightPreview(
    @PreviewParameter(SignalScreenDialogStatePreviewParameterProvider::class) dialogState: SignalDialogState
) {
    SignalScreenDialogContent(
        viewModel = DefaultSignalViewModel(
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
private fun SignalScreenDialogContentDarkPreview(
    @PreviewParameter(SignalScreenDialogStatePreviewParameterProvider::class) dialogState: SignalDialogState
) {
    SignalScreenDialogContent(
        viewModel = DefaultSignalViewModel(
            context = LocalContext.current,
            wisefy = ComposablePreviewWisefy(),
            sdkUtil = DefaultSdkUtil()
        ),
        dialogState = { dialogState }
    )
}

private class SignalScreenDialogStatePreviewParameterProvider : PreviewParameterProvider<SignalDialogState> {
    override val values: Sequence<SignalDialogState> = sequenceOf(
        SignalDialogState.Failure.WisefyAsync(WisefyException("", null)),
        SignalDialogState.CalculateSignalLevel.Success(CalculateSignalLevelResult.Success(0)),
        SignalDialogState.CalculateSignalLevel.Failure(CalculateSignalLevelResult.Failure.Assertion("")),
        SignalDialogState.CompareSignalLevel.Success(CompareSignalLevelResult.Success.RSSIValuesAreEqual(0)),
        SignalDialogState.InputError.CalculateSignalLevel,
        SignalDialogState.InputError.CompareSignalLevel.RSSI1,
        SignalDialogState.InputError.CompareSignalLevel.RSSI2
    )
}
