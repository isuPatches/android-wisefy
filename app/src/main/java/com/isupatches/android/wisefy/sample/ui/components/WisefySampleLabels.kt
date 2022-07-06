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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.isupatches.android.wisefy.sample.R
import com.isupatches.android.wisefy.sample.ui.primitives.WisefySampleTypography

@Composable
internal fun WisefySampleDialogTitleLabel(@StringRes stringResId: Int, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(stringResId),
        style = WisefySampleTypography.h5,
        color = MaterialTheme.colors.primary,
        modifier = modifier
    )
}

@Composable
internal fun WisefySampleDialogBodyLabel(
    @StringRes stringResId: Int,
    modifier: Modifier = Modifier,
    vararg formatArgs: Any
) {
    val text = if (formatArgs.any()) {
        stringResource(stringResId, *formatArgs)
    } else {
        stringResource(stringResId)
    }
    Text(
        text = text,
        style = WisefySampleTypography.body1,
        color = MaterialTheme.colors.onSurface,
        modifier = modifier
    )
}

@Composable
internal fun WisefySampleBodyLabel(@StringRes stringResId: Int, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(stringResId),
        style = WisefySampleTypography.body1,
        color = MaterialTheme.colors.onBackground,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
internal fun WisefySampleBodyLabelLightPreview() {
    WisefySampleBodyLabel(R.string.wisefy)
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
internal fun WisefySampleBodyLabelDarkPreview() {
    WisefySampleBodyLabel(R.string.wisefy)
}

@Composable
internal fun WisefySampleSubHeaderLabel(
    @StringRes stringResId: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(stringResId),
        style = WisefySampleTypography.h6,
        color = MaterialTheme.colors.onBackground,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
internal fun WisefySampleSubHeaderLabelLightPreview() {
    WisefySampleSubHeaderLabel(R.string.wisefy)
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
internal fun WisefySampleSubHeaderLabelDarkPreview() {
    WisefySampleSubHeaderLabel(R.string.wisefy)
}

@Composable
internal fun WisefySampleCaptionLabel(
    @StringRes stringResId: Int,
    modifier: Modifier = Modifier,
    vararg formatArgs: Any
) {
    val text = if (formatArgs.any()) {
        stringResource(stringResId, *formatArgs)
    } else {
        stringResource(stringResId)
    }
    Text(
        text = text,
        style = WisefySampleTypography.caption,
        color = MaterialTheme.colors.onBackground,
        modifier = modifier
    )
}
