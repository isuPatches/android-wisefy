/*
 * Copyright 2022 Patches Barrett
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
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.isupatches.android.wisefy.sample.entities.SSIDType
import com.isupatches.android.wisefy.sample.entities.SearchType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val PREF_LAST_USED_NETWORK_INPUT = "last_used_network_input"
private const val PREF_SEARCH_TYPE = "search_type"
private const val PREF_SSID_TYPE = "ssid_type"
private const val PREF_USE_REGEX_FOR_SEARCH = "use_regex_for_search"
private const val PREF_RETURN_FULL_LIST = "return_full_list"
private const val PREF_FILTER_DUPLICATES = "filter_duplicates"
private const val PREF_TIMEOUT = "timeout"

internal interface SearchStore {
    suspend fun clear()

    fun getLastUsedNetworkInput(): Flow<String>
    fun getSearchType(): Flow<SearchType>
    fun getSSIDType(): Flow<SSIDType>
    fun shouldUseRegexForSearch(): Flow<Boolean>
    fun shouldReturnFullList(): Flow<Boolean>
    fun shouldFilterDuplicates(): Flow<Boolean>
    fun getTimeout(): Flow<Int>

    suspend fun setLastUsedNetworkInput(lastUsedNetworkInput: String)
    suspend fun setSearchType(searchType: SearchType)
    suspend fun setSSIDType(ssidType: SSIDType)
    suspend fun setUseRegexForSearch(useRegexForSearch: Boolean)
    suspend fun setReturnFullList(returnFullList: Boolean)
    suspend fun setFilterDuplicates(filterDuplicates: Boolean)
    suspend fun setTimeout(timeout: Int)
}

private val Context.searchDataStore: DataStore<Preferences> by preferencesDataStore(name = "searchDataStore")

internal class DefaultSearchStore(
    private val context: Context
) : SearchStore {

    private val lastUsedNetworkInputKey = stringPreferencesKey(PREF_LAST_USED_NETWORK_INPUT)
    private val searchTypeKey = intPreferencesKey(PREF_SEARCH_TYPE)
    private val ssidTypeKey = intPreferencesKey(PREF_SSID_TYPE)
    private val useRegexForSearchKey = booleanPreferencesKey(PREF_USE_REGEX_FOR_SEARCH)
    private val returnFullListKey = booleanPreferencesKey(PREF_RETURN_FULL_LIST)
    private val filterDuplicatesKey = booleanPreferencesKey(PREF_FILTER_DUPLICATES)
    private val timeoutKey = intPreferencesKey(PREF_TIMEOUT)

    override suspend fun clear() {
        context.searchDataStore.edit {
            clear()
        }
    }

    /*
     * Last used network input
     */

    override fun getLastUsedNetworkInput(): Flow<String> {
        return context.searchDataStore.data.map { preferences ->
            preferences[lastUsedNetworkInputKey] ?: ""
        }
    }

    override suspend fun setLastUsedNetworkInput(lastUsedNetworkInput: String) {
        context.searchDataStore.edit { preferences ->
            preferences[lastUsedNetworkInputKey] = lastUsedNetworkInput
        }
    }

    /*
     * Search type
     */
    override fun getSearchType(): Flow<SearchType> {
        return context.searchDataStore.data.map { preferences ->
            SearchType.of(preferences[searchTypeKey] ?: SearchType.ACCESS_POINT.intVal)
        }
    }

    override suspend fun setSearchType(searchType: SearchType) {
        context.searchDataStore.edit { preferences ->
            preferences[searchTypeKey] = searchType.intVal
        }
    }

    /*
     * SSID Type
     */

    override fun getSSIDType(): Flow<SSIDType> {
        return context.searchDataStore.data.map { preferences ->
            SSIDType.of(preferences[ssidTypeKey] ?: SSIDType.SSID.intVal)
        }
    }

    override suspend fun setSSIDType(ssidType: SSIDType) {
        context.searchDataStore.edit { preferences ->
            preferences[ssidTypeKey] = ssidType.intVal
        }
    }

    /*
     * Use regex for search
     */

    override fun shouldUseRegexForSearch(): Flow<Boolean> {
        return context.searchDataStore.data.map { preferences ->
            preferences[useRegexForSearchKey] ?: false
        }
    }

    override suspend fun setUseRegexForSearch(useRegexForSearch: Boolean) {
        context.searchDataStore.edit { preferences ->
            preferences[useRegexForSearchKey] = useRegexForSearch
        }
    }

    /*
     * Return full list
     */

    override fun shouldReturnFullList(): Flow<Boolean> {
        return context.searchDataStore.data.map { preferences ->
            preferences[returnFullListKey] ?: true
        }
    }

    override suspend fun setReturnFullList(returnFullList: Boolean) {
        context.searchDataStore.edit { preferences ->
            preferences[returnFullListKey] = returnFullList
        }
    }

    /*
     * Filter duplicates
     */

    override fun shouldFilterDuplicates(): Flow<Boolean> {
        return context.searchDataStore.data.map { preferences ->
            preferences[filterDuplicatesKey] ?: true
        }
    }

    override suspend fun setFilterDuplicates(filterDuplicates: Boolean) {
        context.searchDataStore.edit { preferences ->
            preferences[filterDuplicatesKey] = filterDuplicates
        }
    }

    /*
     * Timeout
     */

    override fun getTimeout(): Flow<Int> {
        return context.searchDataStore.data.map { preferences ->
            preferences[timeoutKey] ?: 1
        }
    }

    override suspend fun setTimeout(timeout: Int) {
        context.searchDataStore.edit { preferences ->
            preferences[timeoutKey] = timeout
        }
    }
}
