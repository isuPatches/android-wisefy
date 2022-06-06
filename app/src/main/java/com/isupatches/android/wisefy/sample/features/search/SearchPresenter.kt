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

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.ACCESS_WIFI_STATE
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.accesspoints.callbacks.SearchForAccessPointCallbacks
import com.isupatches.android.wisefy.accesspoints.callbacks.SearchForAccessPointsCallbacks
import com.isupatches.android.wisefy.accesspoints.callbacks.SearchForSSIDCallbacks
import com.isupatches.android.wisefy.accesspoints.callbacks.SearchForSSIDsCallbacks
import com.isupatches.android.wisefy.accesspoints.entities.AccessPointData
import com.isupatches.android.wisefy.accesspoints.entities.SSIDData
import com.isupatches.android.wisefy.sample.scaffolding.BasePresenter
import com.isupatches.android.wisefy.sample.scaffolding.Presenter
import com.isupatches.android.wisefy.savednetworks.callbacks.SearchForSavedNetworkCallbacks
import com.isupatches.android.wisefy.savednetworks.callbacks.SearchForSavedNetworksCallbacks
import com.isupatches.android.wisefy.savednetworks.entities.SavedNetworkData
import javax.inject.Inject

internal interface SearchPresenter : Presenter<SearchFragment> {
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForAccessPoint(regexForSSID: String, timeoutInMillis: Int, filterDuplicates: Boolean)

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForAccessPoints(regexForSSID: String, filterDuplicates: Boolean)

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    fun searchForSavedNetwork(regexForSSID: String)

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    fun searchForSavedNetworks(regexForSSID: String)

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForSSID(regexForSSID: String, timeoutInMillis: Int)

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun searchForSSIDs(regexForSSID: String)
}

internal class DefaultSearchPresenter @Inject constructor(
    private val model: SearchModel
) : BasePresenter<SearchFragment>(), SearchPresenter {

    /*
     * Model call-throughs
     */

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForAccessPoint(
        regexForSSID: String,
        timeoutInMillis: Int,
        filterDuplicates: Boolean
    ) {
        model.searchForAccessPoint(
            regexForSSID = regexForSSID,
            timeoutInMillis = timeoutInMillis,
            filterDuplicates = filterDuplicates,
            callbacks = object : SearchForAccessPointCallbacks {
                override fun onAccessPointFound(accessPoint: AccessPointData) {
                    doSafelyWithView { view ->
                        view.displayAccessPoint(accessPoint)
                    }
                }

                override fun onNoAccessPointFound() {
                    doSafelyWithView { view ->
                        view.displayAccessPointNotFound()
                    }
                }

                override fun onWisefyAsyncFailure(throwable: Throwable) {
                    doSafelyWithView { view ->
                        view.displayWisefyAsyncError(throwable)
                    }
                }
            }
        )
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForAccessPoints(regexForSSID: String, filterDuplicates: Boolean) {
        model.searchForAccessPoints(
            regexForSSID = regexForSSID,
            filterDuplicates = filterDuplicates,
            callbacks = object : SearchForAccessPointsCallbacks {
                override fun onAccessPointsFound(accessPoints: List<AccessPointData>) {
                    doSafelyWithView { view ->
                        view.displayAccessPoints(accessPoints)
                    }
                }

                override fun onNoAccessPointsFound() {
                    doSafelyWithView { view ->
                        view.displayNoAccessPointsFound()
                    }
                }

                override fun onWisefyAsyncFailure(throwable: Throwable) {
                    doSafelyWithView { view ->
                        view.displayWisefyAsyncError(throwable)
                    }
                }
            }
        )
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForSavedNetwork(regexForSSID: String) {
        model.searchForSavedNetwork(
            regexForSSID = regexForSSID,
            callbacks = object : SearchForSavedNetworkCallbacks {
                override fun onSavedNetworkNotFound() {
                    doSafelyWithView { view ->
                        view.displaySavedNetworkNotFound()
                    }
                }

                override fun onSavedNetworkRetrieved(savedNetwork: SavedNetworkData) {
                    doSafelyWithView { view ->
                        view.displaySavedNetwork(savedNetwork)
                    }
                }

                override fun onWisefyAsyncFailure(throwable: Throwable) {
                    doSafelyWithView { view ->
                        view.displayWisefyAsyncError(throwable)
                    }
                }
            }
        )
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForSavedNetworks(regexForSSID: String) {
        model.searchForSavedNetworks(
            regexForSSID = regexForSSID,
            callbacks = object : SearchForSavedNetworksCallbacks {
                override fun onNoSavedNetworksFound() {
                    doSafelyWithView { view ->
                        view.displayNoSavedNetworksFound()
                    }
                }

                override fun onSavedNetworksRetrieved(savedNetworks: List<SavedNetworkData>) {
                    doSafelyWithView { view ->
                        view.displaySavedNetworks(savedNetworks)
                    }
                }

                override fun onWisefyAsyncFailure(throwable: Throwable) {
                    doSafelyWithView { view ->
                        view.displayWisefyAsyncError(throwable)
                    }
                }
            }
        )
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForSSID(regexForSSID: String, timeoutInMillis: Int) {
        model.searchForSSID(
            regexForSSID = regexForSSID,
            timeoutInMillis = timeoutInMillis,
            callbacks = object : SearchForSSIDCallbacks {
                override fun onSSIDFound(ssid: SSIDData) {
                    doSafelyWithView { view ->
                        view.displaySSID(ssid)
                    }
                }

                override fun onSSIDNotFound() {
                    doSafelyWithView { view ->
                        view.displaySSIDNotFound()
                    }
                }

                override fun onWisefyAsyncFailure(throwable: Throwable) {
                    doSafelyWithView { view ->
                        view.displayWisefyAsyncError(throwable)
                    }
                }
            }
        )
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun searchForSSIDs(regexForSSID: String) {
        model.searchForSSIDs(
            regexForSSID = regexForSSID,
            callbacks = object : SearchForSSIDsCallbacks {
                override fun onSSIDsFound(ssids: List<SSIDData>) {
                    doSafelyWithView { view ->
                        view.displaySSIDs(ssids)
                    }
                }

                override fun onNoSSIDsFound() {
                    doSafelyWithView { view ->
                        view.displayNoSSIDsFound()
                    }
                }

                override fun onWisefyAsyncFailure(throwable: Throwable) {
                    doSafelyWithView { view ->
                        view.displayWisefyAsyncError(throwable)
                    }
                }
            }
        )
    }
}
