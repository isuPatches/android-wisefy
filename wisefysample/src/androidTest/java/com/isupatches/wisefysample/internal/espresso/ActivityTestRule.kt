package com.isupatches.wisefysample.internal.espresso

import androidx.annotation.StringRes
import androidx.test.rule.ActivityTestRule

fun ActivityTestRule<*>.getString(@StringRes stringRes: Int) = activity.getString(stringRes)

fun ActivityTestRule<*>.getString(@StringRes stringRes: Int, varargs: Any) =
        activity.getString(stringRes, varargs)