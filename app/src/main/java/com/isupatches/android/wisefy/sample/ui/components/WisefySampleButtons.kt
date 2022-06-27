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
package com.isupatches.android.wisefy.sample.ui.components

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.isupatches.android.wisefy.sample.R
import com.isupatches.android.wisefy.sample.ui.primitives.WisefySamplePrimaryButtonColors
import com.isupatches.android.wisefy.sample.ui.primitives.WisefySampleSizes
import com.isupatches.android.wisefy.sample.ui.theme.WisefySampleTheme

@Composable
internal fun WisefyPrimaryButton(
    @StringRes stringResId: Int,
    onClick: () -> Unit
) {
    WisefySampleTheme {

        Button(
            modifier = Modifier.fillMaxWidth(),
            content = {
                Text(
                    text = LocalContext.current.getString(stringResId),
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.padding(
                        top = WisefySampleSizes.Medium,
                        bottom = WisefySampleSizes.Medium,
                        start = WisefySampleSizes.Large,
                        end = WisefySampleSizes.Large,
                    ),
                    color = MaterialTheme.colors.onPrimary
                )
            },
            colors = WisefySamplePrimaryButtonColors(),
            onClick = {
                onClick()
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
internal fun WisefyPrimaryButtonLightPreview() {
    WisefyPrimaryButton(R.string.wisefy) { }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
internal fun WisefyPrimaryButtonDarkPreview() {
    WisefyPrimaryButton(R.string.wisefy) { }
}
