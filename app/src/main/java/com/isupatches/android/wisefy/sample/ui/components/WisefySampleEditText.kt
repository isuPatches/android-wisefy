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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.isupatches.android.wisefy.sample.R
import com.isupatches.android.wisefy.sample.ui.primitives.WisefySampleSizes
import com.isupatches.android.wisefy.sample.ui.primitives.WisefySampleTextFieldColors

@Composable
internal fun WisefySampleEditText(
    text: String,
    onTextChange: (String) -> Unit,
    @StringRes labelResId: Int,
    singleLine: Boolean = true,
    error: WisefySampleEditTextError? = null,
    isPasswordField: Boolean = false
) {
    val colors = WisefySampleTextFieldColors()
    val passwordVisible = rememberSaveable { mutableStateOf(false) }
    Column {
        Row {
            TextField(
                value = text,
                onValueChange = onTextChange,
                label = {
                    Text(
                        text = stringResource(labelResId),
                        style = MaterialTheme.typography.body1,
                        color = colors.placeholderColor(enabled = true).value
                    )
                },
                singleLine = singleLine,
                textStyle = MaterialTheme.typography.body1,
                colors = colors,
                modifier = Modifier.fillMaxWidth(),
                isError = error != null,
                visualTransformation = if (isPasswordField) {
                    if (passwordVisible.value) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    }
                } else {
                    VisualTransformation.None
                },
                trailingIcon = {
                    if (isPasswordField) {
                        val image = if (passwordVisible.value) {
                            Icons.Filled.Visibility
                        } else {
                            Icons.Filled.VisibilityOff
                        }

                        val description = if (passwordVisible.value) {
                            stringResource(R.string.hide_password)
                        } else {
                            stringResource(R.string.show_password)
                        }

                        IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                            Icon(imageVector = image, description)
                        }
                    }
                }
            )
        }
        WisefySampleEditTextErrorMessage(error)
    }
}

@Composable
internal fun WisefySampleNumericalEditText(
    text: String,
    onTextChange: (String) -> Unit,
    @StringRes labelResId: Int,
    error: WisefySampleEditTextError? = null
) {
    val colors = WisefySampleTextFieldColors()
    Column {
        Row {
            TextField(
                value = text,
                onValueChange = onTextChange,
                label = {
                    Text(
                        text = stringResource(labelResId),
                        style = MaterialTheme.typography.body1,
                        color = colors.placeholderColor(enabled = true).value
                    )
                },
                singleLine = true,
                textStyle = MaterialTheme.typography.body1,
                colors = colors,
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = VisualTransformation.None,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }
        WisefySampleEditTextErrorMessage(error)
    }
}

@Composable
internal fun WisefySampleEditTextErrorMessage(
    error: WisefySampleEditTextError? = null
) {
    error?.let {
        Row(modifier = Modifier.padding(top = WisefySampleSizes.Medium, bottom = WisefySampleSizes.Medium)) {
            Text(
                text = stringResource(it.errorMessageResId),
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = WisefySampleSizes.Large)
            )
        }
    }
}

internal data class WisefySampleEditTextError(
    @StringRes val errorMessageResId: Int
)

@Preview(showBackground = true)
@Composable
internal fun WisefySampleEditTextLightPreview() {
    WisefySampleEditText(
        text = "",
        onTextChange = { },
        labelResId = R.string.wisefy,
        singleLine = true
    )
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
internal fun WisefySampleEditTextDarkPreview() {
    WisefySampleEditText(
        text = "",
        onTextChange = { },
        labelResId = R.string.wisefy,
        singleLine = true
    )
}

@Preview(showBackground = true)
@Composable
private fun WisefySampleEditTextWithErrorLightPreview() {
    WisefySampleEditText(
        text = "",
        onTextChange = { },
        labelResId = R.string.wisefy,
        singleLine = true,
        error = WisefySampleEditTextError(R.string.permission_error)
    )
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun WisefySampleEditTextWithErrorDarkPreview() {
    WisefySampleEditText(
        text = "",
        onTextChange = { },
        labelResId = R.string.wisefy,
        singleLine = true,
        error = WisefySampleEditTextError(R.string.permission_error)
    )
}

@Preview(showBackground = true)
@Composable
private fun WisefySampleEditTextPasswordFieldLightPreview() {
    WisefySampleEditText(
        text = "password",
        onTextChange = { },
        labelResId = R.string.wisefy,
        isPasswordField = true
    )
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun WisefySampleEditTextPasswordFieldDarkPreview() {
    WisefySampleEditText(
        text = "password",
        onTextChange = { },
        labelResId = R.string.wisefy,
        isPasswordField = true
    )
}

@Preview(showBackground = true)
@Composable
private fun WisefySampleEditTextPasswordFieldWithErrorLightPreview() {
    WisefySampleEditText(
        text = "password",
        onTextChange = { },
        labelResId = R.string.wisefy,
        isPasswordField = true,
        error = WisefySampleEditTextError(R.string.permission_error)
    )
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun WisefySampleEditTextPasswordFieldWithErrorDarkPreview() {
    WisefySampleEditText(
        text = "password",
        onTextChange = { },
        labelResId = R.string.wisefy,
        isPasswordField = true,
        error = WisefySampleEditTextError(R.string.permission_error)
    )
}
