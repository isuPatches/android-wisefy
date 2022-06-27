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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.isupatches.android.wisefy.sample.R
import com.isupatches.android.wisefy.sample.ui.components.WisefyPrimaryButton
import com.isupatches.android.wisefy.sample.ui.components.WisefySampleEditText
import com.isupatches.android.wisefy.sample.ui.components.WisefySampleEditTextError
import com.isupatches.android.wisefy.sample.ui.components.WisefySampleLoadingIndicator
import com.isupatches.android.wisefy.sample.ui.primitives.WisefySampleSizes
import com.isupatches.android.wisefy.sample.ui.theme.WisefySampleTheme

@Composable
internal fun RemoveNetworkScreenContent(
    uiState: () -> RemoveNetworkUIState,
    inputState: () -> RemoveNetworkInputState,
    viewModel: RemoveNetworkViewModel
) {
    WisefySampleTheme {
        val currentUIState = uiState()
        val text = remember { mutableStateOf("") }

        if (currentUIState.loadingState.isLoading) {
            WisefySampleLoadingIndicator()
        }

        RemoveNetworkScreenDialogContent(dialogState = currentUIState.dialogState, viewModel = viewModel)

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
            Row {
                WisefySampleEditText(
                    text = text.value,
                    onTextChange = { newText ->
                        text.value = newText
                        viewModel.onInputChanged(newText)
                    },
                    labelResId = R.string.network_name,
                    error = when (inputState()) {
                        is RemoveNetworkInputState.Invalid.Empty -> {
                            WisefySampleEditTextError(R.string.network_input_empty)
                        }
                        is RemoveNetworkInputState.Invalid.TooShort -> {
                            WisefySampleEditTextError(R.string.network_input_too_short)
                        }
                        is RemoveNetworkInputState.Invalid.TooLong -> {
                            WisefySampleEditTextError(R.string.network_input_too_long)
                        }
                        is RemoveNetworkInputState.Invalid.InvalidCharacters -> {
                            WisefySampleEditTextError(R.string.network_input_invalid_characters)
                        }
                        is RemoveNetworkInputState.Invalid.InvalidStartCharacters -> {
                            WisefySampleEditTextError(R.string.network_input_invalid_start_characters)
                        }
                        is RemoveNetworkInputState.Invalid.LeadingOrTrailingSpaces -> {
                            WisefySampleEditTextError(R.string.network_input_leading_or_trailing_spaces)
                        }
                        is RemoveNetworkInputState.Invalid.InvalidUnicode -> {
                            WisefySampleEditTextError(R.string.network_input_not_valid_unicode)
                        }
                        is RemoveNetworkInputState.Valid -> null
                    }
                )
            }
            Row(modifier = Modifier.padding(top = WisefySampleSizes.Large)) {
                WisefyPrimaryButton(stringResId = R.string.remove_network) {
                    viewModel.removeNetwork()
                }
            }
        }
    }
}
