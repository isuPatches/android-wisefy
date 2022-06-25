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
package com.isupatches.android.wisefy.sample.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.isupatches.android.wisefy.sample.ui.primitives.WisefySampleColorPalette
import com.isupatches.android.wisefy.sample.ui.primitives.WisefySampleTypography

private val WisefySampleDarkColors = darkColors(
    primary = WisefySampleColorPalette.Primary,
    primaryVariant = WisefySampleColorPalette.PrimaryDark,
    onPrimary = WisefySampleColorPalette.Gray1,
    secondary = WisefySampleColorPalette.Secondary,
    secondaryVariant = WisefySampleColorPalette.SecondaryLight,
    onSecondary = WisefySampleColorPalette.Gray10,
    background = WisefySampleColorPalette.Gray10,
    onBackground = WisefySampleColorPalette.Gray1,
    surface = WisefySampleColorPalette.Gray9,
    onSurface = WisefySampleColorPalette.Gray2,
    error = WisefySampleColorPalette.ErrorDarkMode,
    onError = WisefySampleColorPalette.Gray1
)
private val WisefySampleLightColors = lightColors(
    primary = WisefySampleColorPalette.Primary,
    primaryVariant = WisefySampleColorPalette.PrimaryDark,
    onPrimary = WisefySampleColorPalette.Gray1,
    secondary = WisefySampleColorPalette.Secondary,
    secondaryVariant = WisefySampleColorPalette.SecondaryLight,
    onSecondary = WisefySampleColorPalette.Gray10,
    background = WisefySampleColorPalette.Gray1,
    onBackground = WisefySampleColorPalette.Gray10,
    surface = WisefySampleColorPalette.Gray3,
    onSurface = WisefySampleColorPalette.Gray9,
    error = WisefySampleColorPalette.Error,
    onError = WisefySampleColorPalette.Gray1,
)

@Composable
internal fun WisefySampleTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colors = if (darkTheme) WisefySampleDarkColors else WisefySampleLightColors,
        content = content,
        typography = WisefySampleTypography
    )
}
