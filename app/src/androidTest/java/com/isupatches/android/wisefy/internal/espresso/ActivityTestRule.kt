package com.isupatches.android.wisefy.internal.espresso

import androidx.annotation.StringRes
import androidx.test.rule.ActivityTestRule

fun ActivityTestRule<*>.getString(@StringRes stringRes: Int): String = activity.getString(stringRes)

fun ActivityTestRule<*>.getString(
    @StringRes stringRes: Int,
    varargs: Any
): String = activity.getString(stringRes, varargs)
