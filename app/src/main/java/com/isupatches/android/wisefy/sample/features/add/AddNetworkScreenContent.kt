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
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.isupatches.android.wisefy.sample.R
import com.isupatches.android.wisefy.sample.entities.NetworkType
import com.isupatches.android.wisefy.sample.ui.components.WisefyPrimaryButton
import com.isupatches.android.wisefy.sample.ui.components.WisefySampleEditText
import com.isupatches.android.wisefy.sample.ui.components.WisefySampleEditTextError
import com.isupatches.android.wisefy.sample.ui.primitives.WisefySampleSizes
import com.isupatches.android.wisefy.sample.ui.theme.WisefySampleTheme

@Composable
internal fun AddNetworkScreenContent(
    uiState: () -> AddNetworkUIState,
    inputState: () -> AddNetworkInputState,
    networkType: () -> NetworkType,
    viewModel: AddNetworkViewModel,
    isAtLeastAndroidQ: Boolean
) {
    WisefySampleTheme {
        val currentUIState = uiState()
        val ssid = remember { mutableStateOf("") }
        val passphrase = remember { mutableStateOf("") }
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
        val currentInputState = inputState()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = WisefySampleSizes.XXLarge)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(LocalContext.current.getString(R.string.open))
                Text(LocalContext.current.getString(R.string.wpa2))
                if (isAtLeastAndroidQ) {
                    Text(LocalContext.current.getString(R.string.wpa3))
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                RadioButton(
                    selected = networkType() == NetworkType.OPEN,
                    onClick = { viewModel.onOpenNetworkTypeSelected() }
                )
                RadioButton(
                    selected = networkType() == NetworkType.WPA2,
                    onClick = { viewModel.onWPA2NetworkTypeSelected() }
                )
                if (isAtLeastAndroidQ) {
                    RadioButton(
                        selected = networkType() == NetworkType.WPA3,
                        onClick = { viewModel.onWPA3NetworkTypeSelected() }
                    )
                }
            }
            Row(
                modifier = Modifier.padding(
                    top = WisefySampleSizes.XLarge,
                    bottom = WisefySampleSizes.Large,
                    start = WisefySampleSizes.Large,
                    end = WisefySampleSizes.Large
                )
            ) {
                WisefySampleEditText(
                    text = ssid.value,
                    onTextChange = { newSSID ->
                        ssid.value = newSSID
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
            if (networkType() == NetworkType.WPA2 || networkType() == NetworkType.WPA3) {
                Row(
                    modifier = Modifier.padding(
                        top = WisefySampleSizes.Large,
                        bottom = WisefySampleSizes.Large,
                        start = WisefySampleSizes.Large,
                        end = WisefySampleSizes.Large
                    )
                ) {
                    WisefySampleEditText(
                        text = passphrase.value,
                        onTextChange = { newPassphrase ->
                            passphrase.value = newPassphrase
                            viewModel.onPassphraseInputChanged(newPassphrase)
                        },
                        labelResId = R.string.network_name,
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
            Row(modifier = Modifier.padding(top = WisefySampleSizes.Large)) {
                WisefyPrimaryButton(stringResId = R.string.add_network) {
                    if (isAtLeastAndroidQ) {
                        viewModel.addNetwork(launcher = addNetworkRequestLauncher)
                    } else {
                        viewModel.addNetwork()
                    }
                }
            }
        }

        AddNetworkScreenDialogContent(dialogState = currentUIState.dialogState, viewModel = viewModel)
    }
}
