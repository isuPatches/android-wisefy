package com.isupatches.wisefysample.internal.preferences

import android.content.SharedPreferences
import androidx.core.content.edit

internal fun SharedPreferences.getLastUsedRegex() = getNonNullString(
        PREF_LAST_USED_REGEX
    )

internal fun SharedPreferences.setLastUsedRegex(lastUsedRegex: String) {
    edit {
        putString(PREF_LAST_USED_REGEX, lastUsedRegex)
    }
}

internal fun SharedPreferences.getNonNullString(key: String) =
    getString(key, "") ?: ""
