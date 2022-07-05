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
package com.isupatches.android.wisefy.sample.features.misc

import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.isupatches.android.wisefy.sample.R
import com.isupatches.android.wisefy.sample.ui.components.WisefyPrimaryButton
import com.isupatches.android.wisefy.sample.ui.components.WisefySampleLoadingIndicator
import com.isupatches.android.wisefy.sample.ui.primitives.WisefySampleSizes
import com.isupatches.android.wisefy.sample.ui.theme.WisefySampleTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun MiscScreenContent(
    uiState: () -> MiscScreenUIState,
    onMiscOptionClicked: (MiscScreenOption) -> Unit,
    viewModel: MiscViewModel
) {
    WisefySampleTheme {
        val listState = rememberLazyListState()
        val currentUIState = uiState()

        if (currentUIState.loadingState.isLoading) {
            WisefySampleLoadingIndicator()
        }

        MiscScreenDialogContent(dialogState = currentUIState.dialogState, viewModel = viewModel)

        LazyColumn(
            state = listState,
            verticalArrangement = Arrangement.spacedBy(WisefySampleSizes.Large),
            contentPadding = PaddingValues(
                top = WisefySampleSizes.WisefySampleTopMargin,
                bottom = WisefySampleSizes.WisefySampleBottomMargin,
                start = WisefySampleSizes.WisefySampleHorizontalMargins,
                end = WisefySampleSizes.WisefySampleHorizontalMargins
            )
        ) {
            items(MiscScreenOption.values(), { it.id }) { option ->
                Row(modifier = Modifier.animateItemPlacement()) {
                    MiscScreenOptionRow(option = option, onClick = onMiscOptionClicked)
                }
            }
        }
    }
}

internal enum class MiscScreenOption(val id: Long, @StringRes val stringResId: Int) {
    DISABLE_WIFI(R.id.disable_wifi.toLong(), R.string.disable_wifi),
    DISCONNECT_FROM_CURRENT_NETWORK(
        R.id.disconnect_from_current_network.toLong(),
        R.string.disconnect_from_current_network
    ),
    ENABLE_WIFI(R.id.enable_wifi.toLong(), R.string.enabled_wifi),
    GET_CURRENT_NETWORK(R.id.get_current_network.toLong(), R.string.get_current_network),
    GET_CURRENT_NETWORK_INFO(R.id.get_current_network_info.toLong(), R.string.get_current_network_info),
    GET_FREQUENCY(R.id.get_frequency.toLong(), R.string.get_frequency),
    GET_IP(R.id.get_ip.toLong(), R.string.get_ip),
    GET_NEARBY_ACCESS_POINTS(R.id.get_nearby_access_points.toLong(), R.string.get_nearby_access_points),
    GET_SAVED_NETWORKS(R.id.get_saved_networks.toLong(), R.string.get_saved_networks)
}

@Composable
private fun MiscScreenOptionRow(option: MiscScreenOption, onClick: (MiscScreenOption) -> Unit) {
    WisefyPrimaryButton(
        stringResId = option.stringResId,
        onClick = {
            onClick(option)
        }
    )
}
