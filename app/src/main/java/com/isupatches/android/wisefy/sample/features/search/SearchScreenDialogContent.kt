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

import android.content.res.Configuration
import android.net.wifi.WifiConfiguration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.isupatches.android.wisefy.core.exceptions.WisefyException
import com.isupatches.android.wisefy.sample.R
import com.isupatches.android.wisefy.sample.ui.ComposablePreviewWisefy
import com.isupatches.android.wisefy.sample.ui.components.WisefySampleNoticeDialog
import com.isupatches.android.wisefy.savednetworks.entities.SavedNetworkData

@Composable
internal fun SearchScreenDialogContent(
    dialogState: () -> SearchDialogState,
    viewModel: SearchViewModel
) {
    when (val currentDialogState = dialogState()) {
        is SearchDialogState.None -> {
            // No-op, no dialog
        }
        is SearchDialogState.SearchForAccessPoint.NoAccessPointFound -> {
            WisefySampleNoticeDialog(
                title = R.string.search_result,
                body = R.string.access_point_not_found
            ) {
                viewModel.onDialogClosed()
            }
        }
        is SearchDialogState.SearchForAccessPoints.NoAccessPointsFound -> {
            WisefySampleNoticeDialog(
                title = R.string.search_result,
                body = R.string.no_access_points_found
            ) {
                viewModel.onDialogClosed()
            }
        }
        is SearchDialogState.SearchForSSID.NoSSIDFound -> {
            WisefySampleNoticeDialog(
                title = R.string.search_result,
                body = R.string.ssid_not_found
            ) {
                viewModel.onDialogClosed()
            }
        }
        is SearchDialogState.SearchForSSIDs.NoSSIDsFound -> {
            WisefySampleNoticeDialog(
                title = R.string.search_result,
                body = R.string.no_ssids_found
            ) {
                viewModel.onDialogClosed()
            }
        }
        is SearchDialogState.SearchForSavedNetwork.NoSavedNetworkFound -> {
            WisefySampleNoticeDialog(
                title = R.string.search_result,
                body = R.string.saved_network_not_found
            ) {
                viewModel.onDialogClosed()
            }
        }
        is SearchDialogState.SearchForSavedNetworks.NoSavedNetworksFound -> {
            WisefySampleNoticeDialog(
                title = R.string.search_result,
                body = R.string.no_saved_networks_found
            ) {
                viewModel.onDialogClosed()
            }
        }
        is SearchDialogState.Failure.WisefyAsync -> {
            WisefySampleNoticeDialog(
                title = R.string.wisefy_async_error,
                body = R.string.wisefy_async_error_descriptions_args,
                currentDialogState.throwable.message ?: "",
                onClose = {
                    viewModel.onDialogClosed()
                }
            )
        }
        is SearchDialogState.InputError -> {
            WisefySampleNoticeDialog(title = R.string.search_result, body = R.string.network_input_invalid) {
                viewModel.onDialogClosed()
            }
        }
        is SearchDialogState.SearchForAccessPoint.PermissionError -> {
            WisefySampleNoticeDialog(
                title = R.string.permission_error,
                body = R.string.permission_error_search_for_access_point
            ) {
                viewModel.onDialogClosed()
            }
        }
        is SearchDialogState.SearchForAccessPoints.PermissionError -> {
            WisefySampleNoticeDialog(
                title = R.string.permission_error,
                body = R.string.permission_error_search_for_access_points
            ) {
                viewModel.onDialogClosed()
            }
        }
        is SearchDialogState.SearchForSSID.PermissionError -> {
            WisefySampleNoticeDialog(
                title = R.string.permission_error,
                body = R.string.permission_error_search_for_ssid
            ) {
                viewModel.onDialogClosed()
            }
        }
        is SearchDialogState.SearchForSSIDs.PermissionError -> {
            WisefySampleNoticeDialog(
                title = R.string.permission_error,
                body = R.string.permission_error_search_for_ssids
            ) {
                viewModel.onDialogClosed()
            }
        }
        is SearchDialogState.SearchForSavedNetwork.PermissionError -> {
            WisefySampleNoticeDialog(
                title = R.string.permission_error,
                body = R.string.permission_error_search_for_saved_network
            ) {
                viewModel.onDialogClosed()
            }
        }
        is SearchDialogState.SearchForSavedNetworks.PermissionError -> {
            WisefySampleNoticeDialog(
                title = R.string.permission_error,
                body = R.string.permission_error_search_for_saved_networks
            ) {
                viewModel.onDialogClosed()
            }
        }
        is SearchDialogState.SearchForAccessPoint.Success -> {
            WisefySampleNoticeDialog(
                title = R.string.search_result,
                body = R.string.access_point_args,
                currentDialogState.data
            ) {
                viewModel.onDialogClosed()
            }
        }
        is SearchDialogState.SearchForAccessPoints.Success -> {
            WisefySampleNoticeDialog(
                title = R.string.search_result,
                body = R.string.access_points_args,
                currentDialogState.data
            ) {
                viewModel.onDialogClosed()
            }
        }
        is SearchDialogState.SearchForSSID.Success -> {
            WisefySampleNoticeDialog(
                title = R.string.search_result,
                body = R.string.ssid_args,
                currentDialogState.data
            ) {
                viewModel.onDialogClosed()
            }
        }
        is SearchDialogState.SearchForSSIDs.Success -> {
            WisefySampleNoticeDialog(
                title = R.string.search_result,
                body = R.string.ssids_args,
                currentDialogState.data
            ) {
                viewModel.onDialogClosed()
            }
        }
        is SearchDialogState.SearchForSavedNetwork.Success -> {
            WisefySampleNoticeDialog(
                title = R.string.search_result,
                body = R.string.saved_network_args,
                currentDialogState.data
            ) {
                viewModel.onDialogClosed()
            }
        }
        is SearchDialogState.SearchForSavedNetworks.Success -> {
            WisefySampleNoticeDialog(
                title = R.string.search_result,
                body = R.string.saved_networks_args,
                currentDialogState.data
            ) {
                viewModel.onDialogClosed()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
@Suppress("UnusedPrivateMember")
private fun SearchScreenDialogContentLightPreview(
    @PreviewParameter(SearchScreenDialogStatePreviewParameterProvider::class) dialogState: SearchDialogState
) {
    SearchScreenDialogContent(
        viewModel = DefaultSearchViewModel(
            context = LocalContext.current,
            wisefy = ComposablePreviewWisefy()
        ),
        dialogState = { dialogState }
    )
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
@Suppress("UnusedPrivateMember")
private fun RemoveNetworkScreenDialogContentDarkPreview(
    @PreviewParameter(SearchScreenDialogStatePreviewParameterProvider::class) dialogState: SearchDialogState
) {
    SearchScreenDialogContent(
        viewModel = DefaultSearchViewModel(
            context = LocalContext.current,
            wisefy = ComposablePreviewWisefy()
        ),
        dialogState = { dialogState }
    )
}

private class SearchScreenDialogStatePreviewParameterProvider : PreviewParameterProvider<SearchDialogState> {
    override val values: Sequence<SearchDialogState> = sequenceOf(
        SearchDialogState.Failure.WisefyAsync(WisefyException("", null)),
        SearchDialogState.InputError,
        SearchDialogState.SearchForSSID.Success(""),
        SearchDialogState.SearchForSSID.NoSSIDFound,
        SearchDialogState.SearchForSSID.PermissionError,
        SearchDialogState.SearchForAccessPoint.NoAccessPointFound,
        SearchDialogState.SearchForAccessPoint.PermissionError,
        SearchDialogState.SearchForSavedNetwork.Success(SavedNetworkData.Configuration(WifiConfiguration())),
        SearchDialogState.SearchForSavedNetwork.NoSavedNetworkFound,
        SearchDialogState.SearchForSavedNetwork.PermissionError,
        SearchDialogState.SearchForSSIDs.Success(emptyList()),
        SearchDialogState.SearchForSSIDs.NoSSIDsFound,
        SearchDialogState.SearchForSSIDs.PermissionError,
        SearchDialogState.SearchForAccessPoints.Success(emptyList()),
        SearchDialogState.SearchForAccessPoints.NoAccessPointsFound,
        SearchDialogState.SearchForAccessPoints.PermissionError,
        SearchDialogState.SearchForSavedNetworks.Success(emptyList()),
        SearchDialogState.SearchForSavedNetworks.NoSavedNetworksFound,
        SearchDialogState.SearchForSavedNetworks.PermissionError
    )
}
