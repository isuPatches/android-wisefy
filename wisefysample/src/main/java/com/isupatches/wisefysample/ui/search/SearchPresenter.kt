/*
 * Copyright 2019 Patches Klinefelter
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
package com.isupatches.wisefysample.ui.search

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.net.wifi.ScanResult
import android.net.wifi.WifiConfiguration
import androidx.annotation.RequiresPermission
import com.isupatches.wisefy.callbacks.SearchForAccessPointCallbacks
import com.isupatches.wisefy.callbacks.SearchForAccessPointsCallbacks
import com.isupatches.wisefy.callbacks.SearchForSSIDCallbacks
import com.isupatches.wisefy.callbacks.SearchForSSIDsCallbacks
import com.isupatches.wisefy.callbacks.SearchForSavedNetworkCallbacks
import com.isupatches.wisefy.callbacks.SearchForSavedNetworksCallbacks
import com.isupatches.wisefy.constants.WiseFyCode
import com.isupatches.wisefysample.internal.base.BasePresenter
import com.isupatches.wisefysample.internal.util.RxSchedulersProvider
import javax.inject.Inject

internal class SearchPresenter @Inject constructor(
    private val model: SearchMvp.Model,
    rxSchedulersProvider: RxSchedulersProvider
) : BasePresenter<SearchMvp.View>(rxSchedulersProvider), SearchMvp.Presenter {

    /*
     * Model call-throughs
     */

    @RequiresPermission(allOf = [ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForAccessPoint(
        regexForSSID: String,
        timeout: Int,
        filterDuplicates: Boolean
    ) {
        model.searchForAccessPoint(
            regexForSSID,
            timeout,
            filterDuplicates,
            object : SearchForAccessPointCallbacks {
                override fun accessPointFound(accessPoint: ScanResult) {
                    displayAccessPoint(accessPoint)
                }

                override fun accessPointNotFound() {
                    displayAccessPointNotFound()
                }

                override fun wisefyFailure(@WiseFyCode wisefyFailureCode: Int) {
                    displayWiseFyFailure(wisefyFailureCode)
                }
            })
    }

    @RequiresPermission(allOf = [ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForAccessPoints(regexForSSID: String, filterDuplicates: Boolean) {
        model.searchForAccessPoints(regexForSSID, filterDuplicates, object : SearchForAccessPointsCallbacks {
            override fun foundAccessPoints(accessPoints: List<ScanResult>) {
                displayAccessPoints(accessPoints)
            }

            override fun noAccessPointsFound() {
                displayNoAccessPointsFound()
            }

            override fun wisefyFailure(@WiseFyCode wisefyFailureCode: Int) {
                displayWiseFyFailure(wisefyFailureCode)
            }
        })
    }

    override fun searchForSavedNetwork(regexForSSID: String) {
        model.searchForSavedNetwork(regexForSSID, object : SearchForSavedNetworkCallbacks {
            override fun retrievedSavedNetwork(savedNetwork: WifiConfiguration) {
                displaySavedNetwork(savedNetwork)
            }

            override fun savedNetworkNotFound() {
                displaySavedNetworkNotFound()
            }

            override fun wisefyFailure(@WiseFyCode wisefyFailureCode: Int) {
                displayWiseFyFailure(wisefyFailureCode)
            }
        })
    }

    override fun searchForSavedNetworks(regexForSSID: String) {
        model.searchForSavedNetworks(regexForSSID, object : SearchForSavedNetworksCallbacks {
            override fun retrievedSavedNetworks(savedNetworks: List<WifiConfiguration>) {
                displaySavedNetworks(savedNetworks)
            }

            override fun noSavedNetworksFound() {
                displayNoSavedNetworksFound()
            }

            override fun wisefyFailure(@WiseFyCode wisefyFailureCode: Int) {
                displayWiseFyFailure(wisefyFailureCode)
            }
        })
    }

    @RequiresPermission(allOf = [ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForSSID(regexForSSID: String, timeout: Int) {
        model.searchForSSID(regexForSSID, timeout, object : SearchForSSIDCallbacks {
            override fun ssidFound(ssid: String) {
                displaySSID(ssid)
            }

            override fun ssidNotFound() {
                displaySSIDNotFound()
            }

            override fun wisefyFailure(@WiseFyCode wisefyFailureCode: Int) {
                displayWiseFyFailure(wisefyFailureCode)
            }
        })
    }

    @RequiresPermission(allOf = [ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForSSIDs(regexForSSID: String) {
        model.searchForSSIDs(regexForSSID, object : SearchForSSIDsCallbacks {
            override fun retrievedSSIDs(ssids: List<String>) {
                displaySSIDs(ssids)
            }

            override fun noSSIDsFound() {
                displayNoSSIDsFound()
            }

            override fun wisefyFailure(@WiseFyCode wisefyFailureCode: Int) {
                displayWiseFyFailure(wisefyFailureCode)
            }
        })
    }

    /*
     * View callbacks
     */

    private fun displaySavedNetwork(savedNetwork: WifiConfiguration) {
        doSafelyWithView { view -> view.displaySavedNetwork(savedNetwork) }
    }

    private fun displaySavedNetworkNotFound() {
        doSafelyWithView { view -> view.displaySavedNetworkNotFound() }
    }

    private fun displaySavedNetworks(savedNetworks: List<WifiConfiguration>) {
        doSafelyWithView { view -> view.displaySavedNetworks(savedNetworks) }
    }

    private fun displayNoSavedNetworksFound() {
        doSafelyWithView { view -> view.displayNoSavedNetworksFound() }
    }

    private fun displayAccessPoint(accessPoint: ScanResult) {
        doSafelyWithView { view -> view.displayAccessPoint(accessPoint) }
    }

    private fun displayAccessPointNotFound() {
        doSafelyWithView { view -> view.displayAccessPointNotFound() }
    }

    private fun displayAccessPoints(accessPoints: List<ScanResult>) {
        doSafelyWithView { view -> view.displayAccessPoints(accessPoints) }
    }

    private fun displayNoAccessPointsFound() {
        doSafelyWithView { view -> view.displayNoAccessPointsFound() }
    }

    private fun displaySSID(ssid: String) {
        doSafelyWithView { view -> view.displaySSID(ssid) }
    }

    private fun displaySSIDNotFound() {
        doSafelyWithView { view -> view.displaySSIDNotFound() }
    }

    private fun displaySSIDs(ssids: List<String>) {
        doSafelyWithView { view -> view.displaySSIDs(ssids) }
    }

    private fun displayNoSSIDsFound() {
        doSafelyWithView { view -> view.displayNoSSIDsFound() }
    }
}
