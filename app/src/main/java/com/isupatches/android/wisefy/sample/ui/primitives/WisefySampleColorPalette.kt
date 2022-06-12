package com.isupatches.android.wisefy.sample.ui.primitives/*
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
import androidx.compose.material.ButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.Color

internal object WisefySampleColorPalette {
    val Primary = Color(red = 0, green = 188, blue = 212, alpha = 255)
    val PrimaryDark = Color(red = 0, green = 151, blue = 167, alpha = 255)
    val Secondary = Color(red = 255, green = 64, blue = 129, alpha = 255)
    val Gray1 = Color(red = 255, green = 255, blue = 255, alpha = 255)
}

internal class WisefySamplePrimaryButtonColors : ButtonColors {

    @Composable
    override fun backgroundColor(enabled: Boolean): State<Color> {
        return object : State<Color> {
            override val value: Color = WisefySampleColorPalette.Primary
        }
    }

    @Composable
    override fun contentColor(enabled: Boolean): State<Color> {
        return object : State<Color> {
            override val value: Color = WisefySampleColorPalette.Gray1
        }
    }
}
