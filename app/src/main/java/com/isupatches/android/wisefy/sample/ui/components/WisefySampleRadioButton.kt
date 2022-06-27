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
import androidx.compose.material.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
internal fun WisefySampleRadioButton(
    isSelected: Boolean,
    onClick: () -> Unit
) {
    RadioButton(selected = isSelected, onClick = onClick)
}

@Preview(showBackground = true)
@Composable
internal fun WisefySampleRadioButtonLightPreview() {
    WisefySampleRadioButton(isSelected = true, onClick = { })
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
internal fun WisefySampleRadioButtonDarkPreview() {
    WisefySampleRadioButton(isSelected = true, onClick = { })
}
