/*
 * Copyright 2021 Patches Klinefelter
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
package com.isupatches.android.wisefy.sample.ui.search

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.ACCESS_WIFI_STATE
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.sample.internal.scaffolding.BasePresenter
import com.isupatches.android.wisefy.sample.internal.scaffolding.Presenter
import javax.inject.Inject

internal interface SearchPresenter : Presenter<SearchFragment> {
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForAccessPoint(regexForSSID: String, timeout: Int, filterDuplicates: Boolean)

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForAccessPoints(regexForSSID: String, filterDuplicates: Boolean)

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    fun searchForSavedNetwork(regexForSSID: String)

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    fun searchForSavedNetworks(regexForSSID: String)

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForSSID(regexForSSID: String, timeout: Int)

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForSSIDs(regexForSSID: String)
}

@SearchScope
internal class DefaultSearchPresenter @Inject constructor(
    private val model: SearchModel
) : BasePresenter<SearchFragment>(), SearchPresenter {

    /*
     * Model call-throughs
     */

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForAccessPoint(
        regexForSSID: String,
        timeout: Int,
        filterDuplicates: Boolean
    ) {
        val result = model.searchForAccessPoint(regexForSSID, timeout, filterDuplicates)
        doSafelyWithView { view ->
            view.displayAccessPoint(result)
        }
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForAccessPoints(regexForSSID: String, filterDuplicates: Boolean) {
        val results = model.searchForAccessPoints(regexForSSID, filterDuplicates)
        doSafelyWithView { view ->
            view.displayAccessPoints(results)
        }
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForSavedNetwork(regexForSSID: String) {
        val result = model.searchForSavedNetwork(regexForSSID)
        doSafelyWithView { view ->
            view.displaySavedNetwork(result)
        }
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForSavedNetworks(regexForSSID: String) {
        val results = model.searchForSavedNetworks(regexForSSID)
        doSafelyWithView { view ->
            view.displaySavedNetworks(results)
        }
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForSSID(regexForSSID: String, timeout: Int) {
        val result = model.searchForSSID(regexForSSID, timeout)
        doSafelyWithView { view ->
            view.displaySSID(result)
        }
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForSSIDs(regexForSSID: String) {
        val results = model.searchForSSIDs(regexForSSID)
        doSafelyWithView { view ->
            view.displaySSIDs(results)
        }
    }
}
