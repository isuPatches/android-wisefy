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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.isupatches.android.wisefy.sample.R
import com.isupatches.android.wisefy.sample.entities.SearchType
import com.isupatches.android.wisefy.sample.ui.components.WisefyPrimaryButton
import com.isupatches.android.wisefy.sample.ui.components.WisefySampleBodyLabel
import com.isupatches.android.wisefy.sample.ui.components.WisefySampleCaptionLabel
import com.isupatches.android.wisefy.sample.ui.components.WisefySampleEditText
import com.isupatches.android.wisefy.sample.ui.components.WisefySampleLoadingIndicator
import com.isupatches.android.wisefy.sample.ui.components.WisefySampleRadioButton
import com.isupatches.android.wisefy.sample.ui.components.WisefySampleSlider
import com.isupatches.android.wisefy.sample.ui.components.WisefySampleSubHeaderLabel
import com.isupatches.android.wisefy.sample.ui.primitives.WisefySampleSizes
import com.isupatches.android.wisefy.sample.ui.theme.WisefySampleTheme
import kotlin.math.roundToInt

@Composable
internal fun SearchScreenContent(
    uiState: () -> SearchUIState,
    searchType: () -> SearchType,
    viewModel: SearchViewModel
) {
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
                WisefySampleEditText(text = "", onTextChange = { }, labelResId = R.string.regex_for_network)
            }
            Row {
                Box(Modifier.padding(top = WisefySampleSizes.XLarge)) {
                    WisefyPrimaryButton(stringResId = R.string.search, onClick = { })
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
                    onClick = { viewModel.onAccessPointSearchTypeSelected() }
                )
                WisefySampleBodyLabel(stringResId = R.string.access_point)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                WisefySampleRadioButton(
                    isSelected = searchType() == SearchType.SSID,
                    onClick = { viewModel.onSSIDSearchTypeSelected() }
                )
                WisefySampleBodyLabel(stringResId = R.string.ssid)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                WisefySampleRadioButton(
                    isSelected = searchType() == SearchType.SAVED_NETWORK,
                    onClick = { viewModel.onSavedNetworkSearchTypeSelected() }
                )
                WisefySampleBodyLabel(stringResId = R.string.saved_network)
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
                        viewModel.onReturnFullListSelected()
                    }
                )
                WisefySampleBodyLabel(stringResId = R.string.yes)
                WisefySampleRadioButton(
                    isSelected = !viewModel.returnFullList.value,
                    onClick = {
                        viewModel.onReturnFullListDeselected()
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
                        viewModel.onFilterDuplicatesSelected()
                    }
                )
                WisefySampleBodyLabel(stringResId = R.string.yes)
                WisefySampleRadioButton(
                    isSelected = !viewModel.filterDuplicates.value,
                    onClick = {
                        viewModel.onFilterDuplicatesDeselected()
                    }
                )
                WisefySampleBodyLabel(stringResId = R.string.no)
            }
            Row(modifier = Modifier.padding(top = WisefySampleSizes.Medium)) {
                WisefySampleSlider(
                    valueRange = object : ClosedFloatingPointRange<Float> {
                        override fun lessThanOrEquals(a: Float, b: Float): Boolean {
                            return a < b
                        }

                        override val endInclusive: Float = 60f
                        override val start: Float = 1f
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
