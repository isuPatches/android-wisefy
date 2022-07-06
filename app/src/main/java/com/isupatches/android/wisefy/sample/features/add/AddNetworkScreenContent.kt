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

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.CHANGE_WIFI_STATE
import android.app.Activity
import android.os.Build
import android.provider.Settings.ADD_WIFI_RESULT_SUCCESS
import android.provider.Settings.EXTRA_WIFI_NETWORK_RESULT_LIST
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.isupatches.android.wisefy.sample.R
import com.isupatches.android.wisefy.sample.entities.NetworkType
import com.isupatches.android.wisefy.sample.logging.WisefySampleLogger
import com.isupatches.android.wisefy.sample.ui.components.WisefyPrimaryButton
import com.isupatches.android.wisefy.sample.ui.components.WisefySampleBodyLabel
import com.isupatches.android.wisefy.sample.ui.components.WisefySampleEditText
import com.isupatches.android.wisefy.sample.ui.components.WisefySampleEditTextError
import com.isupatches.android.wisefy.sample.ui.components.WisefySampleRadioButton
import com.isupatches.android.wisefy.sample.ui.primitives.WisefySampleSizes
import com.isupatches.android.wisefy.sample.ui.theme.WisefySampleTheme

private const val LOG_TAG = "AddNetworkScreenContent"

@Composable
internal fun AddNetworkScreenContent(viewModel: AddNetworkViewModel, isAtLeastAndroidQ: Boolean) {
    WisefySampleTheme {
        val addNetworkRequestLauncher = rememberLauncherForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result: ActivityResult ->
            when (result.resultCode) {
                Activity.RESULT_OK -> {
                    val data = result.data ?: return@rememberLauncherForActivityResult
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                        val networkResultList = data.getIntegerArrayListExtra(EXTRA_WIFI_NETWORK_RESULT_LIST)
                            ?: emptyList()
                        for (resultCode in networkResultList) {
                            if (resultCode == ADD_WIFI_RESULT_SUCCESS) {
                                viewModel.onAddNetworkSuccess(resultCode)
                            } else {
                                viewModel.onAddNetworkFailure(resultCode)
                            }
                        }
                    }
                }
            }
        }

        val addNetworkPermissionLauncher =
            rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->
                if (result.all { it.value }) {
                    @Suppress("MissingPermission")
                    if (isAtLeastAndroidQ) {
                        viewModel.addNetwork(launcher = addNetworkRequestLauncher)
                    } else {
                        viewModel.addNetwork()
                    }
                } else {
                    WisefySampleLogger.w(LOG_TAG, "Permissions required to remove a network are denied")
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
                if (isAtLeastAndroidQ) {
                    WisefySampleBodyLabel(stringResId = R.string.wpa3)
                }
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                AddNetworkRadioButtonGroup(
                    networkType = { viewModel.uiState.value.networkType },
                    viewModel = viewModel,
                    isAtLeastAndroidQ = isAtLeastAndroidQ
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
            Row(modifier = Modifier.padding(top = WisefySampleSizes.Large)) {
                WisefyPrimaryButton(stringResId = R.string.connect_to_network) {
                    // todo@patches
//                    if (isAtLeastAndroidQ) {
//                        viewModel.addNetwork(launcher = addNetworkRequestLauncher)
//                    } else {
//                        viewModel.addNetwork()
//                    }
                }
            }
        }
    }
}

@Composable
internal fun AddNetworkRadioButtonGroup(
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
internal fun AddNetworkInputRows(
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
            error = when (currentInputState.ssidState) {
                is AddNetworkInputSSIDState.Invalid.Empty -> {
                    WisefySampleEditTextError(R.string.network_input_empty)
                }
                is AddNetworkInputSSIDState.Invalid.TooShort -> {
                    WisefySampleEditTextError(R.string.network_input_too_short)
                }
                is AddNetworkInputSSIDState.Invalid.TooLong -> {
                    WisefySampleEditTextError(R.string.network_input_too_long)
                }
                is AddNetworkInputSSIDState.Invalid.InvalidCharacters -> {
                    WisefySampleEditTextError(R.string.network_input_invalid_characters)
                }
                is AddNetworkInputSSIDState.Invalid.InvalidStartCharacters -> {
                    WisefySampleEditTextError(R.string.network_input_invalid_start_characters)
                }
                is AddNetworkInputSSIDState.Invalid.LeadingOrTrailingSpaces -> {
                    WisefySampleEditTextError(R.string.network_input_leading_or_trailing_spaces)
                }
                is AddNetworkInputSSIDState.Invalid.InvalidUnicode -> {
                    WisefySampleEditTextError(R.string.network_input_not_valid_unicode)
                }
                is AddNetworkInputSSIDState.Valid -> null
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
            error = when (currentInputState.bssidState) {
                is AddNetworkBSSIDState.Invalid -> {
                    WisefySampleEditTextError(R.string.bssid_input_invalid)
                }
                is AddNetworkBSSIDState.Valid.Empty, is AddNetworkBSSIDState.Valid.BSSID -> {
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
                error = when (currentInputState.passphraseState) {
                    is AddNetworkPassphraseState.Invalid.Empty -> {
                        WisefySampleEditTextError(R.string.passphrase_input_empty)
                    }
                    is AddNetworkPassphraseState.Invalid.TooShort -> {
                        WisefySampleEditTextError(R.string.passphrase_input_too_short)
                    }
                    is AddNetworkPassphraseState.Invalid.TooLong -> {
                        WisefySampleEditTextError(R.string.passphrase_input_too_long)
                    }
                    is AddNetworkPassphraseState.Invalid.InvalidASCII -> {
                        WisefySampleEditTextError(R.string.passphrase_input_not_valid_ascii)
                    }
                    is AddNetworkPassphraseState.Valid -> null
                }
            )
        }
    }
}
