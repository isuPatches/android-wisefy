package com.isupatches.android.wisefy.internal.preferences

import com.isupatches.android.wisefy.sample.internal.entities.SearchType
import com.isupatches.android.wisefy.sample.ui.search.SearchStore

internal class InMemorySearchStore : SearchStore {

    private var lastUsedRegex: String = ""
    private var searchType: SearchType = SearchType.ACCESS_POINT
    private var returnFullList: Boolean = true
    private var filterDuplicates: Boolean = true
    private var timeout: Int = 1

    override fun clear() {
        lastUsedRegex = ""
        searchType = SearchType.ACCESS_POINT
        returnFullList = true
        filterDuplicates = true
        timeout = 1
    }

    /*
     * Last Used Regex
     */

    override fun getLastUsedRegex() = lastUsedRegex
    override fun setLastUsedRegex(lastUsedRegex: String) {
        this.lastUsedRegex = lastUsedRegex
    }

    /*
     * Search Type
     */

    override fun getSearchType() = searchType
    override fun setSearchType(searchType: SearchType) {
        this.searchType = searchType
    }

    /*
     * Return Full List
     */

    override fun shouldReturnFullList() = returnFullList
    override fun setReturnFullList(returnFullList: Boolean) {
        this.returnFullList = returnFullList
    }

    /*
     * Filter Duplicates
     */

    override fun shouldFilterDuplicates() = filterDuplicates
    override fun setFilterDuplicates(filterDuplicates: Boolean) {
        this.filterDuplicates = filterDuplicates
    }

    /*
     * Timeout
     */

    override fun getTimeout() = timeout
    override fun setTimeout(timeout: Int) {
        this.timeout = timeout
    }
}
