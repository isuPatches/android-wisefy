package com.isupatches.wisefysample.internal.preferences

import android.content.SharedPreferences

import androidx.core.content.edit

import com.isupatches.wisefysample.internal.models.SearchType

private const val PREF_LAST_USED_REGEX = "last used regex"
private const val PREF_SEARCH_TYPE = "search type"
private const val PREF_RETURN_FULL_LIST = "return full list"
private const val PREF_FILTER_DUPLICATES = "filter duplicates"
private const val PREF_TIMEOUT = "timeout"

internal interface SearchStore {
    fun clear()

    fun getLastUsedRegex(): String
    fun getSearchType(): SearchType
    fun shouldReturnFullList(): Boolean
    fun shouldFilterDuplicates(): Boolean
    fun getTimeout(): Int

    fun setLastUsedRegex(lastUsedRegex: String)
    fun setSearchType(searchType: SearchType)
    fun setReturnFullList(returnFullList: Boolean)
    fun setFilterDuplicates(filterDuplicates: Boolean)
    fun setTimeout(timeout: Int)
}

internal class SharedPreferencesSearchStore(
    private val sharedPreferences: SharedPreferences
) : SearchStore {

    override fun clear() {
        sharedPreferences.edit { clear() }
    }

    override fun getLastUsedRegex() = sharedPreferences.getString(
        PREF_LAST_USED_REGEX,
        ""
    ) ?: ""

    override fun getSearchType(): SearchType = SearchType.of(
        sharedPreferences.getInt(PREF_SEARCH_TYPE, SearchType.ACCESS_POINT.intVal)
    )

    override fun shouldReturnFullList() = sharedPreferences.getBoolean(
        PREF_RETURN_FULL_LIST,
        true
    )

    override fun shouldFilterDuplicates() = sharedPreferences.getBoolean(
        PREF_FILTER_DUPLICATES,
        true
    )

    override fun getTimeout() = sharedPreferences.getInt(PREF_TIMEOUT, 1)

    override fun setLastUsedRegex(lastUsedRegex: String) {
        sharedPreferences.edit {
            putString(PREF_LAST_USED_REGEX, lastUsedRegex)
        }
    }

    override fun setSearchType(searchType: SearchType) {
        sharedPreferences.edit {
            putInt(PREF_SEARCH_TYPE, searchType.intVal)
        }
    }

    override fun setReturnFullList(returnFullList: Boolean) {
        sharedPreferences.edit {
            putBoolean(PREF_RETURN_FULL_LIST, returnFullList)
        }
    }

    override fun setFilterDuplicates(filterDuplicates: Boolean) {
        sharedPreferences.edit {
            putBoolean(PREF_FILTER_DUPLICATES, filterDuplicates)
        }
    }

    override fun setTimeout(timeout: Int) {
        sharedPreferences.edit {
            putInt(PREF_TIMEOUT, timeout)
        }
    }
}
