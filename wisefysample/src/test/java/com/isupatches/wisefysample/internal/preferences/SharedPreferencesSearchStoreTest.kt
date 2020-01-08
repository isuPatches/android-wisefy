package com.isupatches.wisefysample.internal.preferences

import com.isupatches.wisefysample.TEST_SSID_1
import com.isupatches.wisefysample.TEST_TIMEOUT
import com.isupatches.wisefysample.internal.models.SearchType
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

internal class SharedPreferencesSearchStoreTest : BaseSharedPreferencesStoreTest() {

    companion object {
        private const val TIMEOUT = 10
    }

    private val store by lazy {
        SharedPreferencesSearchStore(sharedPreferences)
    }

    @Test
    fun clearsSearchStoreData() {
        store.clear()
        verify(editor).clear()
    }

    /*
     * Last Used Regex
     */

    @Test
    fun setLastUsedRegex_search() {
        store.setLastUsedRegex(TEST_SSID_1)
        verifyUpdatedLastUsedRegex()
    }

    @Test
    fun getLastUsedRegex_search() {
        store.getLastUsedRegex()
        verifyRetrievedLastUsedRegex()
    }

    /*
     * Search Type
     */

    @Test
    fun setSearchType() {
        store.setSearchType(SearchType.ACCESS_POINT)
        verify(editor).putInt(PREF_SEARCH_TYPE, SearchType.ACCESS_POINT.intVal)
    }

    @Test
    fun getSearchType() {
        store.getSearchType()
        verify(sharedPreferences).getInt(PREF_SEARCH_TYPE, SearchType.ACCESS_POINT.intVal)
    }

    /*
     * Filter Duplicates
     */

    @Test
    fun setFilterDuplicates() {
        store.setFilterDuplicates(true)
        verify(editor).putBoolean(PREF_FILTER_DUPLICATES, true)
    }

    @Test
    fun shouldFilterDuplicates() {
        store.shouldFilterDuplicates()
        verify(sharedPreferences).getBoolean(PREF_FILTER_DUPLICATES, true)
    }

    /*
     * Return Full List
     */

    @Test
    fun setReturnFullList() {
        store.setReturnFullList(true)
        verify(editor).putBoolean(PREF_RETURN_FULL_LIST, true)
    }

    @Test
    fun shouldReturnFullList() {
        store.shouldReturnFullList()
        verify(sharedPreferences).getBoolean(PREF_RETURN_FULL_LIST, true)
    }

    /*
     * Timeout
     */

    @Test
    fun setTimeout() {
        store.setTimeout(TIMEOUT)
        verify(editor).putInt(PREF_TIMEOUT, TIMEOUT)
    }

    @Test
    fun getTimeout() {
        store.getTimeout()
        verify(sharedPreferences).getInt(PREF_TIMEOUT, TEST_TIMEOUT)
    }
}
