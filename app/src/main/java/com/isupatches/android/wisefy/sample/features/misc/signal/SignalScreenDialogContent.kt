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

import androidx.compose.runtime.Composable
import com.isupatches.android.wisefy.sample.R
import com.isupatches.android.wisefy.sample.ui.components.WisefySampleNoticeDialog

@Composable
internal fun SignalScreenDialogContent(
    dialogState: () -> SignalScreenDialogState,
    viewModel: SignalViewModel
) {
    when (val currentDialogState = dialogState()) {
        is SignalScreenDialogState.None -> {
            // No-op, no dialog
        }
        is SignalScreenDialogState.CalculateSignalLevel.Failure -> {
            WisefySampleNoticeDialog(
                title = R.string.calculate_signal_level,
                body = R.string.failure_calculating_signal_level_args,
                currentDialogState.result,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is SignalScreenDialogState.CalculateSignalLevel.Success -> {
            WisefySampleNoticeDialog(
                title = R.string.calculate_signal_level,
                body = R.string.succeeded_calculating_signal_level_args,
                currentDialogState.result,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is SignalScreenDialogState.CompareSignalLevel.Success -> {
            WisefySampleNoticeDialog(
                title = R.string.compare_signal_level,
                body = R.string.succeeded_comparing_signal_level_args,
                currentDialogState.result,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is SignalScreenDialogState.Failure.WisefyAsync -> {
            WisefySampleNoticeDialog(
                title = R.string.wisefy_async_error,
                body = R.string.wisefy_async_error_descriptions_args,
                currentDialogState.exception.message ?: "",
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        SignalScreenDialogState.InputError.CalculateSignalLevel -> {
            WisefySampleNoticeDialog(
                title = R.string.input_error,
                body = R.string.rssi_input_invalid,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        SignalScreenDialogState.InputError.CompareSignalLevel.RSSI1 -> {
            WisefySampleNoticeDialog(
                title = R.string.input_error,
                body = R.string.rssi_input_invalid,
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        SignalScreenDialogState.InputError.CompareSignalLevel.RSSI2 -> {
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
