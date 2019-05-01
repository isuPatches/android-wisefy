package com.isupatches.wisefysample.util

import android.widget.EditText

fun EditText.getTrimmedInput(): String = text.toString().trim()
