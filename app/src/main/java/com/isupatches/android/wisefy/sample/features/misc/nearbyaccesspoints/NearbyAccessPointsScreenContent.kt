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
import android.net.wifi.ScanResult
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.isupatches.android.wisefy.accesspoints.entities.AccessPointData
import com.isupatches.android.wisefy.accesspoints.entities.SecurityCapability
import com.isupatches.android.wisefy.sample.R
import com.isupatches.android.wisefy.sample.ui.ComposablePreviewWisefy
import com.isupatches.android.wisefy.sample.ui.components.WisefySampleBodyLabel
import com.isupatches.android.wisefy.sample.ui.components.WisefySampleSubHeaderLabel
import com.isupatches.android.wisefy.sample.ui.primitives.WisefySampleSizes
import com.isupatches.android.wisefy.sample.ui.theme.WisefySampleTheme

@Composable
internal fun NearbyAccessPointsScreenContent(
    accessPoints: () -> List<AccessPointUIData>
) {
    WisefySampleTheme {
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
            items(accessPoints()) { accessPoint ->
                @OptIn(ExperimentalFoundationApi::class)
                Row(modifier = Modifier.animateItemPlacement()) {
                    AccessPointRow(accessPoint = accessPoint)
                }
            }
        }
    }
}

@Composable
private fun AccessPointRow(accessPoint: AccessPointUIData) {
    WisefySampleTheme {
        Column {
            Row {
                WisefySampleSubHeaderLabel(
                    stringResId = R.string.access_point_header_args,
                    modifier = Modifier,
                    accessPoint.accessPoint.ssid,
                    accessPoint.accessPoint.bssid
                )
            }
            Row {
                WisefySampleBodyLabel(
                    stringResId = R.string.access_point_is_saved_by_ssid_args,
                    modifier = Modifier.padding(top = WisefySampleSizes.Large),
                    accessPoint.isSavedBySSID
                )
            }
            Row {
                WisefySampleBodyLabel(
                    stringResId = R.string.access_point_is_saved_by_bssid_args,
                    modifier = Modifier.padding(top = WisefySampleSizes.Medium),
                    accessPoint.isSavedByBSSID
                )
            }
            Row {
                WisefySampleBodyLabel(
                    stringResId = R.string.access_point_security_capabilities_args,
                    modifier = Modifier.padding(top = WisefySampleSizes.Medium),
                    accessPoint.securityCapabilities
                )
            }
            Row {
                WisefySampleBodyLabel(
                    stringResId = R.string.access_point_raw_value_args,
                    modifier = Modifier.padding(top = WisefySampleSizes.Medium),
                    accessPoint.accessPoint
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.R)
@Preview(showBackground = true)
@Composable
@Suppress("UnusedPrivateMember")
private fun NearbyAccessPointsScreenContentLightPreview() {
    NearbyAccessPointsScreenContent(
        accessPoints = {
            listOf(
                AccessPointUIData(
                    accessPoint = AccessPointData(
                        rawValue = ScanResult().also {
                            it.capabilities = ""
                        },
                        ssid = "",
                        bssid = "",
                        frequency = 4900
                    ),
                    isSavedBySSID = false,
                    isSavedByBSSID = false,
                    securityCapabilities = SecurityCapability.ALL.associateWith {
                        false
                    }
                )
            )
        }
    )
}

@RequiresApi(Build.VERSION_CODES.R)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
@Suppress("UnusedPrivateMember")
private fun NearbyAccessPointsScreenContentDarkPreview() {
    NearbyAccessPointsScreenContent(
        accessPoints = {
            listOf(
                AccessPointUIData(
                    accessPoint = AccessPointData(
                        rawValue = ScanResult().also {
                            it.capabilities = ""
                        },
                        ssid = "",
                        bssid = "",
                        frequency = 4900
                    ),
                    isSavedBySSID = false,
                    isSavedByBSSID = false,
                    securityCapabilities = SecurityCapability.ALL.associateWith {
                        false
                    }
                )
            )
        }
    )
}
