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

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.isupatches.android.wisefy.sample.ui.primitives.WisefySamplePrimaryButtonColors
import com.isupatches.android.wisefy.sample.ui.primitives.WisefySampleSizes
import com.isupatches.android.wisefy.sample.ui.primitives.WisefySampleTypography

@Composable
internal fun WisefyPrimaryButton(
    @StringRes stringResId: Int,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = WisefySampleSizes.Margin.Large, end = WisefySampleSizes.Margin.Large),
        content = {
            Text(
                text = LocalContext.current.getString(stringResId),
                style = WisefySampleTypography.body1,
                modifier = Modifier.padding(
                    top = WisefySampleSizes.Margin.Medium,
                    bottom = WisefySampleSizes.Margin.Medium,
                    start = WisefySampleSizes.Margin.Large,
                    end = WisefySampleSizes.Margin.Large,
                )
            )
        },
        colors = WisefySamplePrimaryButtonColors(),
        onClick = {
            onClick()
        }
    )
}
