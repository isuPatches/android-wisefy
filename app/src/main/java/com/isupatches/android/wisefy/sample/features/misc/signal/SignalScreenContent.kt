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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.isupatches.android.wisefy.sample.ComposablePreviewWisefy
import com.isupatches.android.wisefy.sample.R
import com.isupatches.android.wisefy.sample.ui.components.WisefyPrimaryButton
import com.isupatches.android.wisefy.sample.ui.components.WisefySampleEditTextError
import com.isupatches.android.wisefy.sample.ui.components.WisefySampleNumericalEditText
import com.isupatches.android.wisefy.sample.ui.primitives.WisefySampleSizes
import com.isupatches.android.wisefy.sample.ui.theme.WisefySampleTheme
import com.isupatches.android.wisefy.sample.util.DefaultSdkUtil

@Composable
internal fun SignalScreenContent(
    viewModel: SignalViewModel
) {
    WisefySampleTheme {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(
                    top = WisefySampleSizes.WisefySampleTopMargin,
                    bottom = WisefySampleSizes.WisefySampleBottomMargin,
                    start = WisefySampleSizes.WisefySampleHorizontalMargins,
                    end = WisefySampleSizes.WisefySampleHorizontalMargins
                )
        ) {
            SignalScreenCalculateSignalLevelInputRows(
                inputState = { viewModel.uiState.value.inputState.calculateSignalLevelInputState },
                viewModel = viewModel
            )
            Row(modifier = Modifier.padding(top = WisefySampleSizes.Large, bottom = WisefySampleSizes.Large)) {
                WisefyPrimaryButton(stringResId = R.string.calculate_signal_level) {
                    viewModel.calculateSignalLevel()
                }
            }
            SignalScreenCompareSignalLevelInputRows(
                inputState = { viewModel.uiState.value.inputState.compareSignalLevelInputState },
                viewModel = viewModel
            )
            Row(modifier = Modifier.padding(top = WisefySampleSizes.Large)) {
                WisefyPrimaryButton(stringResId = R.string.compare_signal_level) {
                    viewModel.compareSignalLevel()
                }
            }
        }
    }
}

@Composable
private fun SignalScreenCalculateSignalLevelInputRows(
    inputState: () -> CalculateSignalLevelInputState,
    viewModel: SignalViewModel
) {
    val currentInputState = inputState()
    Row {
        WisefySampleNumericalEditText(
            text = currentInputState.rssiLevelInput,
            onTextChange = { newText ->
                viewModel.onCalculateSignalLevelInputChanged(newText)
            },
            labelResId = R.string.rssi_value,
            error = when (currentInputState.validityState) {
                is SignalInputValidityState.CalculateSignalLevel.Invalid.Empty -> {
                    WisefySampleEditTextError(R.string.rssi_input_empty)
                }
                is SignalInputValidityState.CalculateSignalLevel.Invalid.NotAnInt -> {
                    WisefySampleEditTextError(R.string.rssi_input_invalid_int)
                }
                is SignalInputValidityState.CalculateSignalLevel.Valid -> null
            }
        )
    }
}

@Composable
private fun SignalScreenCompareSignalLevelInputRows(
    inputState: () -> CompareSignalLevelInputState,
    viewModel: SignalViewModel
) {
    val currentInputState = inputState()
    Row {
        WisefySampleNumericalEditText(
            text = currentInputState.rssi1InputState.rssiLevelInput,
            onTextChange = { newText ->
                viewModel.onCompareSignalLevelRSSI1InputChanged(newText)
            },
            labelResId = R.string.rssi1_value,
            error = when (currentInputState.rssi1InputState.validityState) {
                is SignalInputValidityState.CompareSignalLevel.Invalid.Empty -> {
                    WisefySampleEditTextError(R.string.rssi_input_empty)
                }
                is SignalInputValidityState.CompareSignalLevel.Invalid.NotAnInt -> {
                    WisefySampleEditTextError(R.string.rssi_input_invalid_int)
                }
                is SignalInputValidityState.CompareSignalLevel.Valid -> null
            }
        )
    }
    Row {
        WisefySampleNumericalEditText(
            text = currentInputState.rssi2InputState.rssiLevelInput,
            onTextChange = { newText ->
                viewModel.onCompareSignalLevelRSSI2InputChanged(newText)
            },
            labelResId = R.string.rssi2_value,
            error = when (currentInputState.rssi2InputState.validityState) {
                is SignalInputValidityState.CompareSignalLevel.Invalid.Empty -> {
                    WisefySampleEditTextError(R.string.rssi_input_empty)
                }
                is SignalInputValidityState.CompareSignalLevel.Invalid.NotAnInt -> {
                    WisefySampleEditTextError(R.string.rssi_input_invalid_int)
                }
                is SignalInputValidityState.CompareSignalLevel.Valid -> null
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignalScreenContentLightPreview() {
    SignalScreenContent(
        viewModel = DefaultSignalViewModel(
            context = LocalContext.current,
            wisefy = ComposablePreviewWisefy(),
            sdkUtil = DefaultSdkUtil()
        )
    )
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SignalScreenContentDarkPreview() {
    SignalScreenContent(
        viewModel = DefaultSignalViewModel(
            context = LocalContext.current,
            wisefy = ComposablePreviewWisefy(),
            sdkUtil = DefaultSdkUtil()
        )
    )
}
