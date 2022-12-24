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
package com.isupatches.android.wisefy.sample.features.misc.nearbyaccesspoints

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.isupatches.android.wisefy.core.exceptions.WisefyException
import com.isupatches.android.wisefy.sample.R
import com.isupatches.android.wisefy.sample.ui.ComposablePreviewWisefy
import com.isupatches.android.wisefy.sample.ui.components.WisefySampleNoticeDialog
import com.isupatches.android.wisefy.sample.ui.theme.WisefySampleTheme

@Composable
internal fun NearbyAccessPointsScreenDialogContent(
    dialogState: () -> NearbyAccessPointsDialogState,
    viewModel: NearbyAccessPointsViewModel
) {
    WisefySampleTheme {
        when (val currentDialogState = dialogState()) {
            is NearbyAccessPointsDialogState.None -> {
                // No-op, no dialog
            }
            is NearbyAccessPointsDialogState.GetNearbyAccessPoints.PermissionsError -> {
                WisefySampleNoticeDialog(
                    title = R.string.permission_error,
                    body = R.string.permission_error_get_nearby_access_points,
                    onClose = {
                        viewModel.onDialogClosed()
                    }
                )
            }
            is NearbyAccessPointsDialogState.Failure.WisefyAsync -> {
                WisefySampleNoticeDialog(
                    title = R.string.wisefy_async_error,
                    body = R.string.wisefy_async_error_descriptions_args,
                    currentDialogState.exception.message ?: "",
                    onClose = {
                        viewModel.onDialogClosed()
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
@Suppress("UnusedPrivateMember")
private fun AddNetworkScreenDialogContentLightPreview(
    @PreviewParameter(NearbyAccessPointsDialogStatePreviewParameterProvider::class)
    dialogState: NearbyAccessPointsDialogState
) {
    NearbyAccessPointsScreenDialogContent(
        viewModel = DefaultNearbyAccessPointsViewModel(
            wisefy = ComposablePreviewWisefy()
        ),
        dialogState = { dialogState }
    )
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
@Suppress("UnusedPrivateMember")
private fun AddNetworkScreenDialogContentDarkPreview(
    @PreviewParameter(NearbyAccessPointsDialogStatePreviewParameterProvider::class)
    dialogState: NearbyAccessPointsDialogState
) {
    NearbyAccessPointsScreenDialogContent(
        viewModel = DefaultNearbyAccessPointsViewModel(
            wisefy = ComposablePreviewWisefy()
        ),
        dialogState = { dialogState }
    )
}

private class NearbyAccessPointsDialogStatePreviewParameterProvider :
    PreviewParameterProvider<NearbyAccessPointsDialogState> {
    override val values: Sequence<NearbyAccessPointsDialogState> = sequenceOf(
        NearbyAccessPointsDialogState.Failure.WisefyAsync(WisefyException("", null)),
        NearbyAccessPointsDialogState.GetNearbyAccessPoints.PermissionsError
    )
}
