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

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.CHANGE_WIFI_STATE
import android.content.res.Configuration
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.isupatches.android.wisefy.sample.R
import com.isupatches.android.wisefy.sample.entities.NetworkType
import com.isupatches.android.wisefy.sample.logging.WisefySampleLogger
import com.isupatches.android.wisefy.sample.ui.ComposablePreviewWisefy
import com.isupatches.android.wisefy.sample.ui.components.WisefyPrimaryButton
import com.isupatches.android.wisefy.sample.ui.components.WisefySampleBodyLabel
import com.isupatches.android.wisefy.sample.ui.components.WisefySampleEditText
import com.isupatches.android.wisefy.sample.ui.components.WisefySampleEditTextError
import com.isupatches.android.wisefy.sample.ui.components.WisefySampleRadioButton
import com.isupatches.android.wisefy.sample.ui.primitives.WisefySampleSizes
import com.isupatches.android.wisefy.sample.ui.theme.WisefySampleTheme
import com.isupatches.android.wisefy.sample.util.DefaultSdkUtil
import com.isupatches.android.wisefy.sample.util.SdkUtil
import kotlinx.coroutines.launch

private const val LOG_TAG = "AddNetworkScreenContent"

@Composable
internal fun AddNetworkScreenContent(
    viewModel: AddNetworkViewModel,
    sdkUtil: SdkUtil
) {
    WisefySampleTheme {
        val scope = rememberCoroutineScope()

        val addNetworkPermissionLauncher =
            rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->
                if (result.all { it.value }) {
                    scope.launch {
                        @Suppress("MissingPermission")
                        viewModel.addNetwork()
                    }
                } else {
                    WisefySampleLogger.w(LOG_TAG, "Permissions required to add a network are denied")
                    viewModel.onAddNetworkPermissionsError()
                }
            }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(
                    top = WisefySampleSizes.WisefySampleTopMargin,
                    bottom = WisefySampleSizes.WisefySampleBottomMargin,
                    start = WisefySampleSizes.WisefySampleHorizontalMargins,
                    end = WisefySampleSizes.WisefySampleHorizontalMargins
                )
        ) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                WisefySampleBodyLabel(stringResId = R.string.open)
                WisefySampleBodyLabel(stringResId = R.string.wpa2)
                if (sdkUtil.isAtLeastQ()) {
                    WisefySampleBodyLabel(stringResId = R.string.wpa3)
                }
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                AddNetworkRadioButtonGroup(
                    networkType = { viewModel.uiState.value.networkType },
                    viewModel = viewModel,
                    isAtLeastAndroidQ = sdkUtil.isAtLeastQ()
                )
            }
            AddNetworkInputRows(
                networkType = { viewModel.uiState.value.networkType },
                inputState = { viewModel.uiState.value.inputState },
                viewModel = viewModel
            )
            Row(modifier = Modifier.padding(top = WisefySampleSizes.Large)) {
                WisefyPrimaryButton(stringResId = R.string.add_network) {
                    addNetworkPermissionLauncher.launch(arrayOf(ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE))
                }
            }
        }
    }
}

@Composable
private fun AddNetworkRadioButtonGroup(
    networkType: () -> NetworkType,
    viewModel: AddNetworkViewModel,
    isAtLeastAndroidQ: Boolean
) {
    val currentNetworkType = networkType()
    WisefySampleRadioButton(
        isSelected = currentNetworkType == NetworkType.OPEN,
        onClick = { viewModel.onNetworkTypeSelected(NetworkType.OPEN) }
    )
    WisefySampleRadioButton(
        isSelected = currentNetworkType == NetworkType.WPA2,
        onClick = { viewModel.onNetworkTypeSelected(NetworkType.WPA2) }
    )
    if (isAtLeastAndroidQ) {
        WisefySampleRadioButton(
            isSelected = currentNetworkType == NetworkType.WPA3,
            onClick = { viewModel.onNetworkTypeSelected(NetworkType.WPA3) }
        )
    }
}

@Composable
private fun AddNetworkInputRows(
    networkType: () -> NetworkType,
    inputState: () -> AddNetworkInputState,
    viewModel: AddNetworkViewModel
) {
    val currentNetworkType = networkType()
    val currentInputState = inputState()

    Row(modifier = Modifier.padding(top = WisefySampleSizes.XLarge)) {
        WisefySampleEditText(
            text = currentInputState.ssidInput,
            onTextChange = { newSSID ->
                viewModel.onSSIDInputChanged(newSSID)
            },
            labelResId = R.string.network_name,
            error = when (currentInputState.ssidInputValidityState) {
                is AddNetworkSSIDInputValidityState.Invalid.Empty -> {
                    WisefySampleEditTextError(R.string.network_input_empty)
                }
                is AddNetworkSSIDInputValidityState.Invalid.TooShort -> {
                    WisefySampleEditTextError(R.string.network_input_too_short)
                }
                is AddNetworkSSIDInputValidityState.Invalid.TooLong -> {
                    WisefySampleEditTextError(R.string.network_input_too_long)
                }
                is AddNetworkSSIDInputValidityState.Invalid.InvalidCharacters -> {
                    WisefySampleEditTextError(R.string.network_input_invalid_characters)
                }
                is AddNetworkSSIDInputValidityState.Invalid.InvalidStartCharacters -> {
                    WisefySampleEditTextError(R.string.network_input_invalid_start_characters)
                }
                is AddNetworkSSIDInputValidityState.Invalid.LeadingOrTrailingSpaces -> {
                    WisefySampleEditTextError(R.string.network_input_leading_or_trailing_spaces)
                }
                is AddNetworkSSIDInputValidityState.Invalid.InvalidUnicode -> {
                    WisefySampleEditTextError(R.string.network_input_not_valid_unicode)
                }
                is AddNetworkSSIDInputValidityState.Valid -> null
            }
        )
    }
    Row(modifier = Modifier.padding(top = WisefySampleSizes.XLarge)) {
        WisefySampleEditText(
            text = currentInputState.bssidInput ?: "",
            onTextChange = { newBSSID ->
                viewModel.onBSSIDInputChanged(newBSSID)
            },
            labelResId = R.string.bssid,
            error = when (currentInputState.bssidInputValidityState) {
                is AddNetworkBSSIDInputValidityState.Invalid -> {
                    WisefySampleEditTextError(R.string.bssid_input_invalid)
                }
                is AddNetworkBSSIDInputValidityState.Valid.Empty, is AddNetworkBSSIDInputValidityState.Valid.BSSID -> {
                    null
                }
            }
        )
    }
    if (currentNetworkType == NetworkType.WPA2 || currentNetworkType == NetworkType.WPA3) {
        Row(modifier = Modifier.padding(top = WisefySampleSizes.Large)) {
            WisefySampleEditText(
                text = currentInputState.passphraseInput,
                onTextChange = { newPassphrase ->
                    viewModel.onPassphraseInputChanged(newPassphrase)
                },
                labelResId = R.string.network_passphrase,
                isPasswordField = true,
                error = when (currentInputState.passphraseInputValidityState) {
                    is AddNetworkPassphraseInputValidityState.Invalid.Empty -> {
                        WisefySampleEditTextError(R.string.passphrase_input_empty)
                    }
                    is AddNetworkPassphraseInputValidityState.Invalid.TooShort -> {
                        WisefySampleEditTextError(R.string.passphrase_input_too_short)
                    }
                    is AddNetworkPassphraseInputValidityState.Invalid.TooLong -> {
                        WisefySampleEditTextError(R.string.passphrase_input_too_long)
                    }
                    is AddNetworkPassphraseInputValidityState.Invalid.InvalidASCII -> {
                        WisefySampleEditTextError(R.string.passphrase_input_not_valid_ascii)
                    }
                    is AddNetworkPassphraseInputValidityState.Valid -> null
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
@Suppress("UnusedPrivateMember")
private fun AddNetworkScreenContentLightPreview() {
    AddNetworkScreenContent(
        viewModel = DefaultAddNetworkViewModel(
            context = LocalContext.current,
            wisefy = ComposablePreviewWisefy(),
            sdkUtil = DefaultSdkUtil()
        ),
        sdkUtil = DefaultSdkUtil()
    )
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
@Suppress("UnusedPrivateMember")
private fun AddNetworkScreenContentDarkPreview() {
    AddNetworkScreenContent(
        viewModel = DefaultAddNetworkViewModel(
            context = LocalContext.current,
            wisefy = ComposablePreviewWisefy(),
            sdkUtil = DefaultSdkUtil()
        ),
        sdkUtil = DefaultSdkUtil()
    )
}
