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

import androidx.compose.material.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.isupatches.android.wisefy.sample.ui.primitives.WisefySliderColors

@Composable
internal fun WisefySampleSlider(
    valueRange: ClosedFloatingPointRange<Float>,
    onValueChange: (Float) -> Unit,
    onValueChangeFinished: (Float) -> Unit
) {
    var sliderPosition by remember { mutableStateOf(0f) }
    Slider(
        value = sliderPosition,
        valueRange = valueRange,
        onValueChange = {
            sliderPosition = it
            onValueChange(it)
        },
        onValueChangeFinished = {
            onValueChangeFinished(sliderPosition)
        },
        colors = WisefySliderColors()
    )
}
