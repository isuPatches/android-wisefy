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

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.CHANGE_WIFI_STATE
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import com.isupatches.android.wisefy.sample.logging.WisefySampleLogger
import com.isupatches.android.wisefy.sample.ui.components.WisefyPrimaryButton
import com.isupatches.android.wisefy.sample.ui.components.WisefySampleEditText
import com.isupatches.android.wisefy.sample.ui.components.WisefySampleEditTextError
import com.isupatches.android.wisefy.sample.ui.components.WisefySampleSSIDTypeSelectionRows
import com.isupatches.android.wisefy.sample.ui.primitives.WisefySampleSizes
import com.isupatches.android.wisefy.sample.ui.theme.WisefySampleTheme

private const val LOG_TAG = "RemoveNetworkScreenContent"

@Composable
internal fun RemoveNetworkScreenContent(viewModel: RemoveNetworkViewModel) {
    val removeNetworkPermissionsLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->
            if (result.all { it.value }) {
                @Suppress("MissingPermission")
                viewModel.removeNetwork()
            } else {
                WisefySampleLogger.w(LOG_TAG, "Permissions required to remove a network are denied")
                viewModel.onRemoveNetworkPermissionsError()
            }
        }

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
            RemoveNetworkInputRows(
                inputState = { viewModel.uiState.value.inputState },
                viewModel = viewModel
            )
            WisefySampleSSIDTypeSelectionRows(
                ssidType = { viewModel.uiState.value.ssidType },
                onSSIDTypeChanged = { ssidType -> viewModel.onSSIDTypeChanged(ssidType) }
            )
            Row(modifier = Modifier.padding(top = WisefySampleSizes.Large)) {
                WisefyPrimaryButton(stringResId = R.string.remove_network) {
                    removeNetworkPermissionsLauncher.launch(arrayOf(ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE))
                }
            }
        }
    }
}

@Composable
internal fun RemoveNetworkInputRows(
    inputState: () -> RemoveNetworkInputState,
    viewModel: RemoveNetworkViewModel
) {
    val text = remember { mutableStateOf("") }

    Row {
        WisefySampleEditText(
            text = text.value,
            onTextChange = { newText ->
                text.value = newText
                viewModel.onInputChanged(newText)
            },
            labelResId = R.string.regex_for_network,
            error = when (inputState()) {
                is RemoveNetworkInputState.SSID.Invalid.Empty -> {
                    WisefySampleEditTextError(R.string.network_input_empty)
                }
                is RemoveNetworkInputState.SSID.Invalid.TooShort -> {
                    WisefySampleEditTextError(R.string.network_input_too_short)
                }
                is RemoveNetworkInputState.SSID.Invalid.TooLong -> {
                    WisefySampleEditTextError(R.string.network_input_too_long)
                }
                is RemoveNetworkInputState.SSID.Invalid.InvalidCharacters -> {
                    WisefySampleEditTextError(R.string.network_input_invalid_characters)
                }
                is RemoveNetworkInputState.SSID.Invalid.InvalidStartCharacters -> {
                    WisefySampleEditTextError(R.string.network_input_invalid_start_characters)
                }
                is RemoveNetworkInputState.SSID.Invalid.LeadingOrTrailingSpaces -> {
                    WisefySampleEditTextError(R.string.network_input_leading_or_trailing_spaces)
                }
                is RemoveNetworkInputState.SSID.Invalid.InvalidUnicode -> {
                    WisefySampleEditTextError(R.string.network_input_not_valid_unicode)
                }
                is RemoveNetworkInputState.BSSID.Invalid.Empty -> {
                    WisefySampleEditTextError(R.string.bssid_input_empty)
                }
                is RemoveNetworkInputState.BSSID.Invalid.ImproperFormat -> {
                    WisefySampleEditTextError(R.string.bssid_input_improper_format)
                }
                is RemoveNetworkInputState.SSID.Valid -> null
                is RemoveNetworkInputState.BSSID.Valid -> null
            }
        )
    }
}
