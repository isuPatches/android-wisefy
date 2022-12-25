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
package com.isupatches.android.wisefy.sample.ui.components

import android.content.res.Configuration
import androidx.compose.material.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.isupatches.android.wisefy.sample.ui.primitives.WisefySliderColors
import com.isupatches.android.wisefy.sample.ui.theme.WisefySampleTheme

@Composable
internal fun WisefySampleSlider(
    startPosition: () -> Float?,
    valueRange: ClosedFloatingPointRange<Float>,
    onValueChange: (Float) -> Unit,
    onValueChangeFinished: (Float) -> Unit
) {
    WisefySampleTheme {
        var sliderPosition by remember { mutableStateOf(startPosition() ?: 0f) }
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
}

private const val PREVIEW_START_VALUE = 0f
private const val PREVIEW_END_VALUE = 30f

@Preview(showBackground = true)
@Composable
@Suppress("UnusedPrivateMember")
private fun WisefySampleSliderLightPreview() {
    WisefySampleSlider(
        startPosition = { 0f },
        valueRange = object : ClosedFloatingPointRange<Float> {
            override val start: Float = PREVIEW_START_VALUE
            override val endInclusive: Float = PREVIEW_END_VALUE

            override fun lessThanOrEquals(a: Float, b: Float): Boolean {
                return a <= b
            }
        },
        onValueChange = { },
        onValueChangeFinished = { }
    )
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
@Suppress("UnusedPrivateMember")
private fun WisefySampleSliderDarkPreview() {
    WisefySampleSlider(
        startPosition = { 0f },
        valueRange = object : ClosedFloatingPointRange<Float> {
            override val start: Float = PREVIEW_START_VALUE
            override val endInclusive: Float = PREVIEW_END_VALUE

            override fun lessThanOrEquals(a: Float, b: Float): Boolean {
                return a <= b
            }
        },
        onValueChange = { },
        onValueChangeFinished = { }
    )
}
