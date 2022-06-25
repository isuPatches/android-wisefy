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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.isupatches.android.wisefy.sample.R
import com.isupatches.android.wisefy.sample.ui.primitives.WisefySampleCornerRadii
import com.isupatches.android.wisefy.sample.ui.primitives.WisefySampleSizes
import com.isupatches.android.wisefy.sample.ui.theme.WisefySampleTheme

@Composable
internal fun NoticeDialog(
    @StringRes title: Int,
    @StringRes body: Int,
    vararg bodyFormatArgs: Any,
    onClose: () -> Unit
) {
    WisefySampleTheme {
        Dialog(onDismissRequest = onClose) {
            Surface(
                shape = RoundedCornerShape(WisefySampleCornerRadii.Default),
                color = MaterialTheme.colors.surface
            ) {
                Column {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                modifier = Modifier
                                    .align(Alignment.TopCenter)
                                    .padding(
                                        top = WisefySampleSizes.XLarge,
                                        bottom = WisefySampleSizes.Small,
                                        start = WisefySampleSizes.Large,
                                        end = WisefySampleSizes.Large
                                    ),
                                text = LocalContext.current.getString(title),
                                style = MaterialTheme.typography.h5,
                                color = MaterialTheme.colors.primary
                            )
                        }
                    }
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = LocalContext.current.getString(body, *bodyFormatArgs),
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier.padding(
                                top = WisefySampleSizes.Medium,
                                bottom = WisefySampleSizes.XLarge,
                                start = WisefySampleSizes.XLarge,
                                end = WisefySampleSizes.XLarge
                            ),
                            color = MaterialTheme.colors.onSurface
                        )
                    }
                    Row(modifier = Modifier.fillMaxWidth().padding(bottom = WisefySampleSizes.XLarge)) {
                        WisefyPrimaryButton(stringResId = R.string.ok, onClick = onClose)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun NoticeDialogLightPreview() {
    NoticeDialog(
        R.string.permission_error,
        R.string.permission_error_add_open_network,
        onClose = { }
    )
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
internal fun NoticeDialogDarkPreview() {
    NoticeDialog(
        R.string.permission_error,
        R.string.permission_error_add_open_network,
        onClose = { }
    )
}
