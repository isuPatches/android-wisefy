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
package com.isupatches.android.wisefy.sample.ui

import android.content.res.Configuration
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.isupatches.android.wisefy.sample.R
import com.isupatches.android.wisefy.sample.ui.primitives.WisefySampleColorPalette
import com.isupatches.android.wisefy.sample.ui.primitives.WisefySampleTypography
import com.isupatches.android.wisefy.sample.ui.theme.WisefySampleTheme

@Composable
internal fun WisefySampleToolbar() {
    WisefySampleTheme {
        TopAppBar(
            title = {
                Text(
                    text = LocalContext.current.getString(R.string.app_name),
                    color = WisefySampleColorPalette.Gray1,
                    style = WisefySampleTypography.h5
                )
            },
            backgroundColor = WisefySampleColorPalette.Primary,
            contentColor = WisefySampleColorPalette.Gray1
        )
    }
}

@Preview(showBackground = true)
@Composable
internal fun WisefySampleToolbarLightPreview() {
    WisefySampleToolbar()
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
internal fun WisefySampleToolbarDarkPreview() {
    WisefySampleToolbar()
}
