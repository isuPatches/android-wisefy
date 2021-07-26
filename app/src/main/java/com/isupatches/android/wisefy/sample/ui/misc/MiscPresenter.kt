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
package com.isupatches.android.wisefy.sample.ui.misc

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.net.NetworkInfo
import android.net.wifi.ScanResult
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiInfo
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.sample.internal.scaffolding.BasePresenter
import com.isupatches.wisefy.callbacks.DisableWifiCallbacks
import com.isupatches.wisefy.callbacks.EnableWifiCallbacks
import com.isupatches.wisefy.callbacks.GetCurrentNetworkCallbacks
import com.isupatches.wisefy.callbacks.GetCurrentNetworkInfoCallbacks
import com.isupatches.wisefy.callbacks.GetFrequencyCallbacks
import com.isupatches.wisefy.callbacks.GetIPCallbacks
import com.isupatches.wisefy.callbacks.GetNearbyAccessPointsCallbacks
import com.isupatches.wisefy.callbacks.GetSavedNetworksCallbacks
import com.isupatches.android.wisefy.sample.internal.scaffolding.Presenter
import com.isupatches.android.wisefy.sample.internal.util.RxSchedulersProvider
import javax.inject.Inject

internal interface MiscPresenter : Presenter<MiscFragment> {

    fun disableWifi()

    fun enableWifi()

    fun getCurrentNetwork()

    fun getCurrentNetworkInfo()

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun getFrequency()

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun getIP()

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun getNearbyAccessPoints()

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun getSavedNetworks()
}

@MiscScope
internal class DefaultMiscPresenter @Inject constructor(
    private val model: MiscModel,
    rxSchedulersProvider: RxSchedulersProvider
) : BasePresenter<MiscFragment>(rxSchedulersProvider), MiscPresenter {

    /*
     * Model call-throughs
     */

    override fun disableWifi() {
        model.disableWifi(object : DisableWifiCallbacks {
            override fun wifiDisabled() {
                doSafelyWithView { view -> view.displayWifiDisabled() }
            }

            override fun failureDisablingWifi() {
                doSafelyWithView { view -> view.displayFailureDisablingWifi() }
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
                displayWiseFyFailure(wisefyFailureCode)
            }
        })
    }

    override fun enableWifi() {
        model.enableWifi(object : EnableWifiCallbacks {
            override fun wifiEnabled() {
                doSafelyWithView { view -> view.displayWifiEnabled() }
            }

            override fun failureEnablingWifi() {
                doSafelyWithView { view -> view.displayFailureEnablingWifi() }
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
                displayWiseFyFailure(wisefyFailureCode)
            }
        })
    }

    override fun getCurrentNetwork() {
        model.getCurrentNetwork(object : GetCurrentNetworkCallbacks {
            override fun retrievedCurrentNetwork(currentNetwork: WifiInfo) {
                doSafelyWithView { view -> view.displayCurrentNetwork(currentNetwork) }
            }

            override fun noCurrentNetwork() {
                doSafelyWithView { view -> view.displayNoCurrentNetwork() }
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
                displayWiseFyFailure(wisefyFailureCode)
            }
        })
    }

    override fun getCurrentNetworkInfo() {
        model.getCurrentNetworkInfo(object : GetCurrentNetworkInfoCallbacks {
            override fun retrievedCurrentNetworkInfo(currentNetworkInfo: NetworkInfo) {
                doSafelyWithView { view -> view.displayCurrentNetworkInfo(currentNetworkInfo) }
            }

            override fun noCurrentNetworkInfo() {
                doSafelyWithView { view -> view.displayNoCurrentNetworkInfo() }
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
                displayWiseFyFailure(wisefyFailureCode)
            }
        })
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun getFrequency() {
        model.getFrequency(object : GetFrequencyCallbacks {
            override fun retrievedFrequency(frequency: Int) {
                doSafelyWithView { view -> view.displayFrequency(frequency) }
            }

            override fun failureGettingFrequency() {
                doSafelyWithView { view -> view.displayFailureRetrievingFrequency() }
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
                displayWiseFyFailure(wisefyFailureCode)
            }
        })
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getIP() {
        model.getIP(object : GetIPCallbacks {
            override fun retrievedIP(ip: String) {
                doSafelyWithView { view -> view.displayIP(ip) }
            }

            override fun failureRetrievingIP() {
                doSafelyWithView { view -> view.displayFailureRetrievingIP() }
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
                displayWiseFyFailure(wisefyFailureCode)
            }
        })
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getNearbyAccessPoints() {
        model.getNearbyAccessPoints(object : GetNearbyAccessPointsCallbacks {
            override fun noAccessPointsFound() {
                doSafelyWithView { view -> view.displayNoAccessPointsFound() }
            }

            override fun retrievedNearbyAccessPoints(nearbyAccessPoints: List<ScanResult>) {
                doSafelyWithView { view -> view.displayNearbyAccessPoints(nearbyAccessPoints) }
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
                displayWiseFyFailure(wisefyFailureCode)
            }
        })
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getSavedNetworks() {
        model.getSavedNetworks(object : GetSavedNetworksCallbacks {
            override fun noSavedNetworksFound() {
                doSafelyWithView { view -> view.displayNoSavedNetworksFound() }
            }

            override fun retrievedSavedNetworks(savedNetworks: List<WifiConfiguration>) {
                doSafelyWithView { view -> view.displaySavedNetworks(savedNetworks) }
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
                displayWiseFyFailure(wisefyFailureCode)
            }
        })
    }
}
