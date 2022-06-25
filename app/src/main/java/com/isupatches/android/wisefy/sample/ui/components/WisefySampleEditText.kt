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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.isupatches.android.wisefy.sample.ui.primitives.WisefySampleSizes
import com.isupatches.android.wisefy.sample.ui.primitives.WisefySampleTextFieldColors

@Composable
internal fun WisefySampleEditText(
    text: String,
    onTextChange: (String) -> Unit,
    @StringRes labelResId: Int,
    singleLine: Boolean = true,
    error: WisefySampleEditTextError? = null
) {
    Column {
        Row {
            TextField(
                value = text,
                onValueChange = onTextChange,
                label = { LocalContext.current.getString(labelResId) },
                singleLine = singleLine,
                textStyle = MaterialTheme.typography.body1,
                colors = WisefySampleTextFieldColors(),
                modifier = Modifier.fillMaxWidth(),
                isError = error != null
            )
        }
        error?.let {
            Row(modifier = Modifier.padding(top = WisefySampleSizes.Medium, bottom = WisefySampleSizes.Medium)) {
                Text(
                    text = LocalContext.current.getString(it.errorMessageResId),
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = WisefySampleSizes.Large)
                )
            }
        }
    }
}

internal data class WisefySampleEditTextError(
    @StringRes val errorMessageResId: Int
)
