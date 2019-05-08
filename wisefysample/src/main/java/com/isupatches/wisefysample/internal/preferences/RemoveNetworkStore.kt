package com.isupatches.wisefysample.internal.preferences

import android.content.SharedPreferences

import androidx.core.content.edit

internal interface RemoveNetworkStore {
    fun clear()

    fun getLastUsedRegex(): String

    fun setLastUsedRegex(lastUsedRegex: String)
}

internal class SharedPreferencesRemoveNetworkStore(
    private val sharedPreferences: SharedPreferences
) : RemoveNetworkStore {

    override fun clear() {
        sharedPreferences.edit { clear() }
    }

    /*
     * Last used Regex
     */

    override fun getLastUsedRegex() = sharedPreferences.getString(
        PREF_LAST_USED_REGEX,
        ""
    ) ?: ""

    override fun setLastUsedRegex(lastUsedRegex: String) {
        sharedPreferences.edit {
            putString(PREF_LAST_USED_REGEX, lastUsedRegex)
        }
    }
}
