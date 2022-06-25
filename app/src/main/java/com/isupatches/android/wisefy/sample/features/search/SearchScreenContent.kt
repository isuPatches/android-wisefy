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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.isupatches.android.wisefy.sample.R
import com.isupatches.android.wisefy.sample.entities.SearchType
import com.isupatches.android.wisefy.sample.ui.primitives.WisefySampleSizes
import com.isupatches.android.wisefy.sample.ui.theme.WisefySampleTheme

@Composable
internal fun SearchScreenContent(
    uiState: () -> SearchUIState,
    searchType: () -> SearchType,
    viewModel: SearchViewModel
) {
    WisefySampleTheme {
        val currentUIState = uiState()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = WisefySampleSizes.XXLarge)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = searchType() == SearchType.ACCESS_POINT,
                    onClick = { viewModel.onAccessPointSearchTypeSelected() }
                )
                Text(LocalContext.current.getString(R.string.access_point))
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = searchType() == SearchType.SSID,
                    onClick = { viewModel.onSSIDSearchTypeSelected() }
                )
                Text(LocalContext.current.getString(R.string.ssid))
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = searchType() == SearchType.SAVED_NETWORK,
                    onClick = { viewModel.onSavedNetworkSearchTypeSelected() }
                )
                Text(LocalContext.current.getString(R.string.saved_network))
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = false,
                    onClick = { }
                )
                Text(LocalContext.current.getString(R.string.yes))
                RadioButton(
                    selected = false,
                    onClick = { }
                )
                Text(LocalContext.current.getString(R.string.no))
            }
        }

        SearchScreenDialogContent(dialogState = currentUIState.dialogState, viewModel = viewModel)
    }
}
