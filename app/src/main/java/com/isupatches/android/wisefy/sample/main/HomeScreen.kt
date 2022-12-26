/*
 * Copyright 2022 Patches Barrett
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
package com.isupatches.android.wisefy.sample.main

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.isupatches.android.wisefy.sample.BuildConfig
import com.isupatches.android.wisefy.sample.R
import com.isupatches.android.wisefy.sample.ui.primitives.WisefySampleSizes
import com.isupatches.android.wisefy.sample.ui.theme.WisefySampleTheme

@Composable
internal fun HomeScreen() {
    WisefySampleTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(
                    top = WisefySampleSizes.WisefySampleTopMargin,
                    start = WisefySampleSizes.WisefySampleHorizontalMargins,
                    end = WisefySampleSizes.WisefySampleHorizontalMargins
                )
        ) {
            Row {
                Box {
                    Image(
                        painter = painterResource(id = R.drawable.ic_logo),
                        contentDescription = stringResource(R.string.content_description_logo),
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.primary)
                    )
                }
            }
            Row {
                Box(modifier = Modifier.padding(top = WisefySampleSizes.Medium)) {
                    Text(
                        text = stringResource(R.string.wisefy),
                        style = MaterialTheme.typography.h2,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.primary
                    )
                }
            }
            Row {
                Box(modifier = Modifier.padding(top = WisefySampleSizes.XLarge)) {
                    Text(
                        text = stringResource(R.string.wisefy_sample_description),
                        style = MaterialTheme.typography.subtitle1,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.onBackground
                    )
                }
            }
            Row(
                modifier =
                Modifier.padding(
                    top = WisefySampleSizes.XLarge,
                    start = WisefySampleSizes.Large,
                    end = WisefySampleSizes.Large
                )
            ) {
                Text(
                    text = stringResource(R.string.core_version_args, BuildConfig.WISEFY_CORE_VERSION),
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onBackground
                )
            }
            Row(
                Modifier
                    .padding(
                        top = WisefySampleSizes.Small,
                        start = WisefySampleSizes.Large,
                        end = WisefySampleSizes.Large
                    )
            ) {
                Text(
                    text = stringResource(R.string.accesspoints_version_args, BuildConfig.WISEFY_ACCESS_POINTS_VERSION),
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onBackground
                )
            }
            Row(
                Modifier
                    .padding(
                        top = WisefySampleSizes.Small,
                        start = WisefySampleSizes.Large,
                        end = WisefySampleSizes.Large
                    )
            ) {
                Text(
                    text = stringResource(R.string.addnetwork_version_args, BuildConfig.WISEFY_ADD_NETWORK_VERSION),
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onBackground
                )
            }
            Row(
                Modifier
                    .padding(
                        top = WisefySampleSizes.Small,
                        start = WisefySampleSizes.Large,
                        end = WisefySampleSizes.Large
                    )
            ) {
                Text(
                    text = stringResource(
                        R.string.networkconnection_version_args,
                        BuildConfig.WISEFY_NETWORK_CONNECTION_VERSION
                    ),
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onBackground
                )
            }
            Row(
                Modifier
                    .padding(
                        top = WisefySampleSizes.Small,
                        start = WisefySampleSizes.Large,
                        end = WisefySampleSizes.Large
                    )
            ) {
                Text(
                    text = stringResource(R.string.networkinfo_version_args, BuildConfig.WISEFY_NETWORK_INFO_VERSION),
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onBackground
                )
            }
            Row(
                Modifier
                    .padding(
                        top = WisefySampleSizes.Small,
                        start = WisefySampleSizes.Large,
                        end = WisefySampleSizes.Large
                    )
            ) {
                Text(
                    text = stringResource(
                        R.string.removenetwork_version_args,
                        BuildConfig.WISEFY_REMOVE_NETWORK_VERSION
                    ),
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onBackground
                )
            }
            Row(
                Modifier
                    .padding(
                        top = WisefySampleSizes.Small,
                        start = WisefySampleSizes.Large,
                        end = WisefySampleSizes.Large
                    )
            ) {
                Text(
                    text = stringResource(
                        R.string.savednetworks_version_args,
                        BuildConfig.WISEFY_SAVED_NETWORKS_VERSION
                    ),
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onBackground
                )
            }
            Row(
                Modifier
                    .padding(
                        top = WisefySampleSizes.Small,
                        start = WisefySampleSizes.Large,
                        end = WisefySampleSizes.Large
                    )
            ) {
                Text(
                    text = stringResource(R.string.signal_version_args, BuildConfig.WISEFY_SIGNAL_VERSION),
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onBackground
                )
            }
            Row(
                Modifier
                    .padding(
                        top = WisefySampleSizes.Small,
                        start = WisefySampleSizes.Large,
                        end = WisefySampleSizes.Large
                    )
            ) {
                Text(
                    text = stringResource(R.string.wifi_version_args, BuildConfig.WISEFY_WIFI_VERSION),
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onBackground
                )
            }
            Row(
                Modifier
                    .padding(
                        top = WisefySampleSizes.Small,
                        start = WisefySampleSizes.Large,
                        end = WisefySampleSizes.Large
                    )
            ) {
                Text(
                    text = stringResource(R.string.wisefy_version_args, BuildConfig.WISEFY_VERSION),
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onBackground
                )
            }
            Row(
                modifier = Modifier
                    .padding(
                        bottom = WisefySampleSizes.XLarge,
                        top = WisefySampleSizes.Small,
                        start = WisefySampleSizes.Large,
                        end = WisefySampleSizes.Large
                    )
            ) {
                Text(
                    text = stringResource(R.string.wisefy_ktx_version_args, BuildConfig.WISEFY_KTX_VERSION),
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onBackground
                )
            }
            Row(Modifier.weight(1f)) {
                Spacer(modifier = Modifier.fillMaxSize())
            }
            Row {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = WisefySampleSizes.Large,
                            start = WisefySampleSizes.Medium,
                            bottom = WisefySampleSizes.Large,
                            end = WisefySampleSizes.Medium
                        ),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Text(
                        text = stringResource(
                            R.string.author_and_version_args,
                            BuildConfig.VERSION_NAME,
                            BuildConfig.VERSION_CODE,
                            BuildConfig.GIT_HASH
                        ),
                        style = MaterialTheme.typography.caption,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.onBackground
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
@Suppress("UnusedPrivateMember")
private fun HomeScreenLayoutLightPreview() {
    HomeScreen()
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
@Suppress("UnusedPrivateMember")
private fun HomeScreenLayoutDarkPreview() {
    HomeScreen()
}
