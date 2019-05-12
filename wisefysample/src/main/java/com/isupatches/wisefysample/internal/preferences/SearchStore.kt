package com.isupatches.wisefysample.internal.preferences

import android.content.SharedPreferences

import androidx.core.content.edit

import com.isupatches.wisefysample.internal.models.SearchType

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

    /*
     * Search type
     */

    override fun getSearchType(): SearchType = SearchType.of(
        sharedPreferences.getInt(PREF_SEARCH_TYPE, SearchType.ACCESS_POINT.intVal)
    )

    override fun setSearchType(searchType: SearchType) {
        sharedPreferences.edit {
            putInt(PREF_SEARCH_TYPE, searchType.intVal)
        }
    }

    /*
     * Return full list
     */

    override fun shouldReturnFullList() = sharedPreferences.getBoolean(
        PREF_RETURN_FULL_LIST,
        true
    )

    override fun setReturnFullList(returnFullList: Boolean) {
        sharedPreferences.edit {
            putBoolean(PREF_RETURN_FULL_LIST, returnFullList)
        }
    }

    /*
     * Filter duplicates
     */

    override fun shouldFilterDuplicates() = sharedPreferences.getBoolean(
        PREF_FILTER_DUPLICATES,
        true
    )

    override fun setFilterDuplicates(filterDuplicates: Boolean) {
        sharedPreferences.edit {
            putBoolean(PREF_FILTER_DUPLICATES, filterDuplicates)
        }
    }

    /*
     * Timeout
     */

    override fun getTimeout() = sharedPreferences.getInt(PREF_TIMEOUT, 1)

    override fun setTimeout(timeout: Int) {
        sharedPreferences.edit {
            putInt(PREF_TIMEOUT, timeout)
        }
    }
}
