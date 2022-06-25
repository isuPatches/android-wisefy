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
package com.isupatches.android.wisefy.sample.ui.primitives

import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.material.ButtonColors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.Color

internal object WisefySampleColorPalette {
    val Primary = Color(red = 0, green = 188, blue = 212, alpha = 255)
    val PrimaryDark = Color(red = 0, green = 139, blue = 163, alpha = 255)
    val Secondary = Color(red = 255, green = 64, blue = 129, alpha = 255)
    val SecondaryLight = Color(red = 255, green = 100, blue = 152, alpha = 255)
    val Gray1 = Color(red = 250, green = 250, blue = 250, alpha = 255)
    val Gray2 = Color(red = 245, green = 245, blue = 245, alpha = 255)
    val Gray3 = Color(red = 238, green = 238, blue = 238, alpha = 255)
    val Gray4 = Color(red = 224, green = 224, blue = 224, alpha = 255)
    val Gray5 = Color(red = 189, green = 189, blue = 189, alpha = 255)
    val Gray6 = Color(red = 158, green = 158, blue = 158, alpha = 255)
    val Gray7 = Color(red = 117, green = 117, blue = 117, alpha = 255)
    val Gray8 = Color(red = 97, green = 97, blue = 97, alpha = 255)
    val Gray9 = Color(red = 66, green = 66, blue = 66, alpha = 255)
    val Gray10 = Color(red = 33, green = 33, blue = 33, alpha = 255)
    val Error = Color(red = 176, green = 0, blue = 32, alpha = 255)
    val ErrorDarkMode = Color(red = 207, green = 102, blue = 121, alpha = 255)
    val Success = Color(red = 102, green = 187, blue = 106, alpha = 255)
}

internal class WisefySamplePrimaryButtonColors : ButtonColors {

    @Composable
    override fun backgroundColor(enabled: Boolean): State<Color> {
        return object : State<Color> {
            override val value: Color = MaterialTheme.colors.primary
        }
    }

    @Composable
    override fun contentColor(enabled: Boolean): State<Color> {
        return object : State<Color> {
            override val value: Color = MaterialTheme.colors.onPrimary
        }
    }
}

internal class WisefySampleTextFieldColors : TextFieldColors {

    @Composable
    override fun backgroundColor(enabled: Boolean): State<Color> {
        return object : State<Color> {
            override val value: Color = MaterialTheme.colors.surface
        }
    }

    @Composable
    override fun cursorColor(isError: Boolean): State<Color> {
        return object : State<Color> {
            override val value: Color = if (isError) {
                MaterialTheme.colors.error
            } else {
                MaterialTheme.colors.primary
            }
        }
    }

    @Composable
    override fun indicatorColor(
        enabled: Boolean,
        isError: Boolean,
        interactionSource: InteractionSource
    ): State<Color> {
        return object : State<Color> {
            override val value: Color = if (isError) {
                MaterialTheme.colors.error
            } else {
                MaterialTheme.colors.primary
            }
        }
    }

    @Composable
    override fun labelColor(enabled: Boolean, error: Boolean, interactionSource: InteractionSource): State<Color> {
        return object : State<Color> {
            override val value: Color = MaterialTheme.colors.background
        }
    }

    @Composable
    override fun leadingIconColor(enabled: Boolean, isError: Boolean): State<Color> {
        return object : State<Color> {
            override val value: Color = MaterialTheme.colors.primary
        }
    }

    @Composable
    override fun placeholderColor(enabled: Boolean): State<Color> {
        return object : State<Color> {
            override val value: Color = WisefySampleColorPalette.Gray3
        }
    }

    @Composable
    override fun textColor(enabled: Boolean): State<Color> {
        return object : State<Color> {
            override val value: Color = MaterialTheme.colors.onSurface
        }
    }

    @Composable
    override fun trailingIconColor(enabled: Boolean, isError: Boolean): State<Color> {
        return object : State<Color> {
            override val value: Color = MaterialTheme.colors.primary
        }
    }
}
