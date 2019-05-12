package com.isupatches.wisefysample.internal.util

import android.widget.EditText

internal fun EditText.getTrimmedInput(): String = text.toString().trim()
