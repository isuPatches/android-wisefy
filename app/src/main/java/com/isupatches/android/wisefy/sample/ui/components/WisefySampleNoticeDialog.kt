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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.isupatches.android.wisefy.sample.R
import com.isupatches.android.wisefy.sample.ui.primitives.WisefySampleCornerRadii
import com.isupatches.android.wisefy.sample.ui.primitives.WisefySampleSizes
import com.isupatches.android.wisefy.sample.ui.theme.WisefySampleTheme

@Composable
internal fun WisefySampleNoticeDialog(
    @StringRes title: Int,
    @StringRes body: Int,
    vararg bodyFormatArgs: Any,
    onClose: () -> Unit
) {
    WisefySampleTheme {
        Dialog(onDismissRequest = onClose) {
            Box(modifier = Modifier.padding(top = WisefySampleSizes.XXLarge, bottom = WisefySampleSizes.XXLarge)) {
                Surface(
                    shape = RoundedCornerShape(WisefySampleCornerRadii.Default),
                    color = MaterialTheme.colors.surface
                ) {
                    Column(
                        modifier = Modifier.padding(
                            top = WisefySampleSizes.Large,
                            bottom = WisefySampleSizes.XLarge,
                            start = WisefySampleSizes.WisefySampleHorizontalMargins,
                            end = WisefySampleSizes.WisefySampleHorizontalMargins
                        )
                    ) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                            WisefySampleDialogTitleLabel(stringResId = title)
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .verticalScroll(state = rememberScrollState())
                                .weight(weight = 1f, fill = false)
                        ) {
                            WisefySampleDialogBodyLabel(
                                stringResId = body,
                                modifier = Modifier.padding(top = WisefySampleSizes.Large),
                                formatArgs = bodyFormatArgs
                            )
                        }
                        Row(modifier = Modifier.fillMaxWidth().padding(top = WisefySampleSizes.XLarge)) {
                            WisefyPrimaryButton(stringResId = R.string.ok, onClick = onClose)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun WisefySampleNoticeDialogLightPreview() {
    WisefySampleNoticeDialog(
        R.string.permission_error,
        R.string.permission_error_add_open_network,
        onClose = { }
    )
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
internal fun WisefySampleNoticeDialogDarkPreview() {
    WisefySampleNoticeDialog(
        R.string.permission_error,
        R.string.permission_error_add_open_network,
        onClose = { }
    )
}
