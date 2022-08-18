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

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.Manifest.permission.ACCESS_WIFI_STATE
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.isupatches.android.wisefy.sample.R
import com.isupatches.android.wisefy.sample.logging.WisefySampleLogger
import com.isupatches.android.wisefy.sample.ui.components.WisefyPrimaryButton
import com.isupatches.android.wisefy.sample.ui.primitives.WisefySampleSizes
import com.isupatches.android.wisefy.sample.ui.theme.WisefySampleTheme
import kotlinx.coroutines.launch

private const val LOG_TAG = "MiscScreenContent"

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun MiscScreenContent(viewModel: MiscViewModel) {
    WisefySampleTheme {

        val scope = rememberCoroutineScope()

        val getCurrentNetworkInfoPermissionsLauncher =
            rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                if (isGranted) {
                    scope.launch {
                        @Suppress("MissingPermission")
                        viewModel.getCurrentNetworkInfo()
                    }
                } else {
                    WisefySampleLogger.w(LOG_TAG, "Permissions for getting current network info are denied")
                    viewModel.onGetCurrentNetworkInfoPermissionsError()
                }
            }

        val getFrequencyPermissionsLauncher =
            rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->
                if (result.all { it.value }) {
                    scope.launch {
                        @Suppress("MissingPermission")
                        viewModel.getFrequency()
                    }
                } else {
                    WisefySampleLogger.w(LOG_TAG, "Permissions for getting frequency are denied")
                    viewModel.onGetFrequencyPermissionsError()
                }
            }

        val getIPPermissionsLauncher =
            rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                if (isGranted) {
                    scope.launch {
                        @Suppress("MissingPermission")
                        viewModel.getIP()
                    }
                } else {
                    WisefySampleLogger.w(LOG_TAG, "Permissions for getting ip are denied")
                    viewModel.onGetIPPermissionsError()
                }
            }

        val getNearbyAccessPointsPermissionsLauncher =
            rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                if (isGranted) {
                    scope.launch {
                        @Suppress("MissingPermission")
                        viewModel.getNearbyAccessPoints()
                    }
                } else {
                    WisefySampleLogger.w(LOG_TAG, "Permissions for getting nearby access points are denied")
                    viewModel.onGetNearbyAccessPointsPermissionError()
                }
            }

        val getSavedNetworksPermissionsLauncher =
            rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->
                if (result.all { it.value }) {
                    scope.launch {
                        @Suppress("MissingPermission")
                        viewModel.getSavedNetworks()
                    }
                } else {
                    WisefySampleLogger.w(LOG_TAG, "Permissions for getting saved networks are denied")
                    viewModel.onGetSavedNetworksPermissionsError()
                }
            }

        val isNetwork5gHzPermissionsLauncher =
            rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->
                if (result.all { it.value }) {
                    scope.launch {
                        @Suppress("MissingPermission")
                        viewModel.isNetwork5gHz()
                    }
                } else {
                    WisefySampleLogger.w(LOG_TAG, "Permissions for checking if the current network is 5gHz are denied")
                    viewModel.isNetwork5gHzPermissionsError()
                }
            }

        val onMiscOptionClicked: (MiscScreenOption) -> Unit = { option ->
            when (option) {
                MiscScreenOption.DISABLE_WIFI -> {
                    scope.launch {
                        viewModel.disableWifi()
                    }
                }
                MiscScreenOption.DISCONNECT_FROM_CURRENT_NETWORK -> viewModel.disconnectFromCurrentNetwork()
                MiscScreenOption.ENABLE_WIFI -> {
                    scope.launch {
                        viewModel.enableWifi()
                    }
                }
                MiscScreenOption.GET_CURRENT_NETWORK -> {
                    scope.launch {
                        viewModel.getCurrentNetwork()
                    }
                }
                MiscScreenOption.GET_CURRENT_NETWORK_INFO -> {
                    getCurrentNetworkInfoPermissionsLauncher.launch(ACCESS_NETWORK_STATE)
                }
                MiscScreenOption.GET_FREQUENCY -> {
                    getFrequencyPermissionsLauncher.launch(arrayOf(ACCESS_FINE_LOCATION, ACCESS_NETWORK_STATE))
                }
                MiscScreenOption.GET_IP -> getIPPermissionsLauncher.launch(ACCESS_FINE_LOCATION)
                MiscScreenOption.GET_NEARBY_ACCESS_POINTS -> {
                    getNearbyAccessPointsPermissionsLauncher.launch(ACCESS_FINE_LOCATION)
                }
                MiscScreenOption.GET_RSSI -> {  }
                MiscScreenOption.GET_SAVED_NETWORKS -> {
                    getSavedNetworksPermissionsLauncher.launch(arrayOf(ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE))
                }
                MiscScreenOption.IS_NETWORK_5GHZ -> {
                    isNetwork5gHzPermissionsLauncher.launch(arrayOf(ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE))
                }
                MiscScreenOption.IS_WIFI_ENABLED -> {
                    scope.launch {
                        viewModel.isWifiEnabled()
                    }
                }
            }
        }

        val listState = rememberLazyListState()
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
    GET_RSSI(R.id.get_rssi.toLong(), R.string.get_rssi),
    GET_SAVED_NETWORKS(R.id.get_saved_networks.toLong(), R.string.get_saved_networks),
    IS_NETWORK_5GHZ(R.id.is_network_5ghz.toLong(), R.string.is_network_5ghz),
    IS_WIFI_ENABLED(R.id.is_wifi_enabled.toLong(), R.string.is_wifi_enabled)
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
