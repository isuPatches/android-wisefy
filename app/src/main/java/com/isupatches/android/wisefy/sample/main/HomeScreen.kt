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
package com.isupatches.android.wisefy.sample.main

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.isupatches.android.wisefy.sample.R
import com.isupatches.android.wisefy.sample.ui.primitives.WisefySampleColorPalette
import com.isupatches.android.wisefy.sample.ui.primitives.WisefySampleSizes
import com.isupatches.android.wisefy.sample.ui.primitives.WisefySampleTypography
import com.isupatches.android.wisefy.sample.ui.theme.WisefySampleTheme

@Composable
internal fun HomeScreen() {
    WisefySampleTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row {
                    Box(
                        modifier = Modifier.padding(
                            top = 48.dp,
                            bottom = WisefySampleSizes.Margin.Small,
                            start = WisefySampleSizes.Margin.Large,
                            end = WisefySampleSizes.Margin.Large
                        )
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_logo),
                            contentDescription = LocalContext.current.getString(R.string.content_description_logo),
                            colorFilter = ColorFilter.tint(WisefySampleColorPalette.Primary)
                        )
                    }
                }
                Row(
                    modifier = Modifier.padding(
                        top = WisefySampleSizes.Margin.Small,
                        bottom = WisefySampleSizes.Margin.Large,
                        start = WisefySampleSizes.Margin.Large,
                        end = WisefySampleSizes.Margin.Large
                    )
                ) {
                    Text(
                        text = LocalContext.current.getString(R.string.wisefy),
                        style = WisefySampleTypography.h2,
                        color = WisefySampleColorPalette.Primary,
                        textAlign = TextAlign.Center
                    )
                }
                Row(
                    modifier = Modifier.padding(
                        top = WisefySampleSizes.Margin.Large,
                        bottom = WisefySampleSizes.Margin.Large,
                        start = WisefySampleSizes.Margin.Large,
                        end = WisefySampleSizes.Margin.Large
                    )
                ) {
                    Text(
                        text = LocalContext.current.getString(R.string.wisefy_sample_description),
                        style = WisefySampleTypography.subtitle1,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun HomeScreenLayoutLightPreview() {
    HomeScreen()
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
internal fun HomeScreenLayoutDarkPreview() {
    HomeScreen()
}
