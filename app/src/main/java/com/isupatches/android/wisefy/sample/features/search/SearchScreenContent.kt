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
package com.isupatches.android.wisefy.sample.features.search

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.ACCESS_WIFI_STATE
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.isupatches.android.wisefy.sample.R
import com.isupatches.android.wisefy.sample.entities.SSIDType
import com.isupatches.android.wisefy.sample.entities.SearchType
import com.isupatches.android.wisefy.sample.logging.WisefySampleLogger
import com.isupatches.android.wisefy.sample.ui.components.WisefyPrimaryButton
import com.isupatches.android.wisefy.sample.ui.components.WisefySampleBodyLabel
import com.isupatches.android.wisefy.sample.ui.components.WisefySampleCaptionLabel
import com.isupatches.android.wisefy.sample.ui.components.WisefySampleEditText
import com.isupatches.android.wisefy.sample.ui.components.WisefySampleEditTextError
import com.isupatches.android.wisefy.sample.ui.components.WisefySampleLoadingIndicator
import com.isupatches.android.wisefy.sample.ui.components.WisefySampleRadioButton
import com.isupatches.android.wisefy.sample.ui.components.WisefySampleSlider
import com.isupatches.android.wisefy.sample.ui.components.WisefySampleSubHeaderLabel
import com.isupatches.android.wisefy.sample.ui.primitives.WisefySampleSizes
import com.isupatches.android.wisefy.sample.ui.theme.WisefySampleTheme
import kotlin.math.roundToInt

private const val LOG_TAG = "SearchScreenContent"

private const val MIN_SEARCH_TIMEOUT = 1f
private const val MAX_SEARCH_TIMEOUT = 60f

@Composable
internal fun SearchScreenContent(
    uiState: () -> SearchUIState,
    searchType: () -> SearchType,
    inputState: () -> SearchInputState,
    viewModel: SearchViewModel
) {

    val searchForAccessPointPermissionsLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                @Suppress("MissingPermission")
                viewModel.searchForAccessPoint()
            } else {
                WisefySampleLogger.w(LOG_TAG, "Permissions required to search for an access point are denied")
                viewModel.onSearchForAccessPointPermissionError()
            }
        }

    val searchForAccessPointsPermissionsLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                @Suppress("MissingPermission")
                viewModel.searchForAccessPoints()
            } else {
                WisefySampleLogger.w(LOG_TAG, "Permissions required to search for access points are denied")
                viewModel.onSearchForAccessPointsPermissionError()
            }
        }

    val searchForSavedNetworkPermissionsLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->
            if (result.all { it.value }) {
                @Suppress("MissingPermission")
                viewModel.searchForSavedNetwork()
            } else {
                WisefySampleLogger.w(LOG_TAG, "Permissions required to search for a saved network are denied")
                viewModel.onSearchForSavedNetworkPermissionError()
            }
        }

    val searchForSavedNetworksPermissionsLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->
            if (result.all { it.value }) {
                @Suppress("MissingPermission")
                viewModel.searchForSavedNetworks()
            } else {
                WisefySampleLogger.w(LOG_TAG, "Permissions required to search for saved networks are denied")
                viewModel.onSearchForSavedNetworksPermissionError()
            }
        }

    val searchForSSIDPermissionsLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                @Suppress("MissingPermission")
                viewModel.searchForSSID()
            } else {
                WisefySampleLogger.w(LOG_TAG, "Permissions required to search for an SSID are denied")
                viewModel.onSearchForSSIDPermissionError()
            }
        }

    val searchForSSIDsPermissionsLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                @Suppress("MissingPermission")
                viewModel.searchForSSIDs()
            } else {
                WisefySampleLogger.w(LOG_TAG, "Permissions required to search for SSIDs are denied")
                viewModel.onSearchForSSIDsPermissionError()
            }
        }

    WisefySampleTheme {
        val currentUIState = uiState()

        if (currentUIState.loadingState.isLoading) {
            WisefySampleLoadingIndicator()
        }

        SearchScreenDialogContent(dialogState = currentUIState.dialogState, viewModel = viewModel)

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
            Row {
                val text = remember { mutableStateOf("") }

                WisefySampleEditText(
                    text = text.value,
                    onTextChange = { newText ->
                        text.value = newText
                        viewModel.onSearchNetworkInputChanged(newText)
                    },
                    labelResId = R.string.regex_for_network,
                    error = when (inputState()) {
                        is SearchInputState.SSID.Invalid.Empty -> {
                            WisefySampleEditTextError(R.string.network_input_empty)
                        }
                        is SearchInputState.SSID.Invalid.TooShort -> {
                            WisefySampleEditTextError(R.string.network_input_too_short)
                        }
                        is SearchInputState.SSID.Invalid.TooLong -> {
                            WisefySampleEditTextError(R.string.network_input_too_long)
                        }
                        is SearchInputState.SSID.Invalid.InvalidCharacters -> {
                            WisefySampleEditTextError(R.string.network_input_invalid_characters)
                        }
                        is SearchInputState.SSID.Invalid.InvalidStartCharacters -> {
                            WisefySampleEditTextError(R.string.network_input_invalid_start_characters)
                        }
                        is SearchInputState.SSID.Invalid.LeadingOrTrailingSpaces -> {
                            WisefySampleEditTextError(R.string.network_input_leading_or_trailing_spaces)
                        }
                        is SearchInputState.SSID.Invalid.InvalidUnicode -> {
                            WisefySampleEditTextError(R.string.network_input_not_valid_unicode)
                        }
                        is SearchInputState.SSID.Valid -> null
                        is SearchInputState.BSSID.Invalid.Empty -> {
                            WisefySampleEditTextError(R.string.bssid_input_empty)
                        }
                        is SearchInputState.BSSID.Invalid.ImproperFormat -> {
                            WisefySampleEditTextError(R.string.bssid_input_improper_format)
                        }
                        is SearchInputState.BSSID.Valid -> null
                    }
                )
            }
            Row {
                Box(Modifier.padding(top = WisefySampleSizes.XLarge)) {
                    WisefyPrimaryButton(stringResId = R.string.search) {
                        when (viewModel.searchType.value) {
                            SearchType.ACCESS_POINT -> {
                                if (viewModel.returnFullList.value) {
                                    searchForAccessPointsPermissionsLauncher.launch(ACCESS_FINE_LOCATION)
                                } else {
                                    searchForAccessPointPermissionsLauncher.launch(ACCESS_FINE_LOCATION)
                                }
                            }
                            SearchType.SAVED_NETWORK -> {
                                if (viewModel.returnFullList.value) {
                                    searchForSavedNetworksPermissionsLauncher.launch(
                                        arrayOf(ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE)
                                    )
                                } else {
                                    searchForSavedNetworkPermissionsLauncher.launch(
                                        arrayOf(ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE)
                                    )
                                }
                            }
                            SearchType.SSID -> {
                                if (viewModel.returnFullList.value) {
                                    searchForSSIDsPermissionsLauncher.launch(ACCESS_FINE_LOCATION)
                                } else {
                                    searchForSSIDPermissionsLauncher.launch(ACCESS_FINE_LOCATION)
                                }
                            }
                        }
                    }
                }
            }
            Row {
                WisefySampleSubHeaderLabel(
                    stringResId = R.string.search_for,
                    modifier = Modifier.padding(top = WisefySampleSizes.XLarge)
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                WisefySampleRadioButton(
                    isSelected = searchType() == SearchType.ACCESS_POINT,
                    onClick = { viewModel.onSearchTypeSelected(SearchType.ACCESS_POINT) }
                )
                WisefySampleBodyLabel(stringResId = R.string.access_point)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                WisefySampleRadioButton(
                    isSelected = searchType() == SearchType.SSID,
                    onClick = { viewModel.onSearchTypeSelected(SearchType.SSID) }
                )
                WisefySampleBodyLabel(stringResId = R.string.ssid)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                WisefySampleRadioButton(
                    isSelected = searchType() == SearchType.SAVED_NETWORK,
                    onClick = { viewModel.onSearchTypeSelected(SearchType.SAVED_NETWORK) }
                )
                WisefySampleBodyLabel(stringResId = R.string.saved_network)
            }
            Row {
                WisefySampleSubHeaderLabel(
                    modifier = Modifier.padding(top = WisefySampleSizes.Large),
                    stringResId = R.string.ssid_type
                )
            }
            Row(
                modifier = Modifier.padding(top = WisefySampleSizes.Medium),
                verticalAlignment = Alignment.CenterVertically
            ) {
                WisefySampleRadioButton(
                    isSelected = viewModel.ssidType.value == SSIDType.SSID,
                    onClick = {
                        viewModel.onSSIDTypeChanged(SSIDType.SSID)
                    }
                )
                WisefySampleBodyLabel(stringResId = R.string.ssid)
                WisefySampleRadioButton(
                    isSelected = viewModel.ssidType.value == SSIDType.BSSID,
                    onClick = {
                        viewModel.onSSIDTypeChanged(SSIDType.BSSID)
                    }
                )
                WisefySampleBodyLabel(stringResId = R.string.bssid)
            }
            Row {
                WisefySampleSubHeaderLabel(
                    modifier = Modifier.padding(top = WisefySampleSizes.Medium),
                    stringResId = R.string.return_full_list_label
                )
            }
            Row {
                WisefySampleBodyLabel(
                    modifier = Modifier.padding(top = WisefySampleSizes.Large),
                    stringResId = R.string.return_full_list_description
                )
            }
            Row(
                modifier = Modifier.padding(top = WisefySampleSizes.Medium),
                verticalAlignment = Alignment.CenterVertically
            ) {
                WisefySampleRadioButton(
                    isSelected = viewModel.returnFullList.value,
                    onClick = {
                        viewModel.onReturnFullListChanged(true)
                    }
                )
                WisefySampleBodyLabel(stringResId = R.string.yes)
                WisefySampleRadioButton(
                    isSelected = !viewModel.returnFullList.value,
                    onClick = {
                        viewModel.onReturnFullListChanged(false)
                    }
                )
                WisefySampleBodyLabel(stringResId = R.string.no)
            }
            Row {
                WisefySampleSubHeaderLabel(
                    modifier = Modifier.padding(top = WisefySampleSizes.Large),
                    stringResId = R.string.filter_duplicates
                )
            }
            Row(
                modifier = Modifier.padding(top = WisefySampleSizes.Medium),
                verticalAlignment = Alignment.CenterVertically
            ) {
                WisefySampleRadioButton(
                    isSelected = viewModel.filterDuplicates.value,
                    onClick = {
                        viewModel.onFilterDuplicatesChanged(true)
                    }
                )
                WisefySampleBodyLabel(stringResId = R.string.yes)
                WisefySampleRadioButton(
                    isSelected = !viewModel.filterDuplicates.value,
                    onClick = {
                        viewModel.onFilterDuplicatesChanged(false)
                    }
                )
                WisefySampleBodyLabel(stringResId = R.string.no)
            }
            if (!viewModel.returnFullList.value && viewModel.searchType.value != SearchType.SAVED_NETWORK) {
                Row(modifier = Modifier.padding(top = WisefySampleSizes.Medium)) {
                    WisefySampleSlider(
                        valueRange = object : ClosedFloatingPointRange<Float> {
                            override fun lessThanOrEquals(a: Float, b: Float): Boolean {
                                return a < b
                            }

                            override val start: Float = MIN_SEARCH_TIMEOUT
                            override val endInclusive: Float = MAX_SEARCH_TIMEOUT
                        },
                        onValueChanged = { newTimeoutValue ->
                            viewModel.onSearchTimeoutValueUpdated(newTimeoutValue.roundToInt())
                        }
                    )
                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    WisefySampleCaptionLabel(
                        stringResId = R.string.timeout_after_x_seconds_args_html,
                        modifier = Modifier,
                        viewModel.searchTimeout.value
                    )
                }
            }
        }
    }
}
