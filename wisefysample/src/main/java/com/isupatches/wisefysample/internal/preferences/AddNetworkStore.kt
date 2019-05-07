package com.isupatches.wisefysample.internal.preferences

import android.content.SharedPreferences

import androidx.core.content.edit

internal interface AddNetworkStore {
    fun clear()

    fun getLastUsedRegex(): String

    fun setLastUsedRegex(lastUsedRegex: String)
}

internal class SharedPreferencesAddNetworkStore(
    private val sharedPreferences: SharedPreferences
) : AddNetworkStore {

    override fun clear() {
        sharedPreferences.edit { clear() }
    }

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
