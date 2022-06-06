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
package com.isupatches.android.wisefy.sample.features.search

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.core.content.edit
import com.isupatches.android.wisefy.sample.R
import com.isupatches.android.wisefy.sample.entities.SearchType
import com.isupatches.android.wisefy.sample.scaffolding.BaseSharedPreferenceStore
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@VisibleForTesting
internal const val PREF_SEARCH_TYPE = "search_type"

@VisibleForTesting
internal const val PREF_RETURN_FULL_LIST = "return_full_list"

@VisibleForTesting
internal const val PREF_FILTER_DUPLICATES = "filter_duplicates"

@VisibleForTesting
internal const val PREF_TIMEOUT = "timeout"

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

internal class SharedPreferencesSearchStore @Inject constructor(
    @ApplicationContext context: Context
) : BaseSharedPreferenceStore(), SearchStore {

    private val sharedPreferences = getSharedPreferences(
        context,
        R.string.preferences_search_data
    )

    override fun clear() {
        sharedPreferences.edit { clear() }
    }

    /*
     * Last used Regex
     */

    override fun getLastUsedRegex() = sharedPreferences.getLastUsedRegex()

    override fun setLastUsedRegex(lastUsedRegex: String) {
        sharedPreferences.setLastUsedRegex(lastUsedRegex)
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
