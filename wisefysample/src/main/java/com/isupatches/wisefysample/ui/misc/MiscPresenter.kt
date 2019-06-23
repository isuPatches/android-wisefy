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
package com.isupatches.wisefysample.ui.misc

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.Manifest.permission.CHANGE_WIFI_STATE
import android.net.NetworkInfo
import android.net.wifi.ScanResult
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiInfo
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission

import com.isupatches.wisefy.callbacks.DisableWifiCallbacks
import com.isupatches.wisefy.callbacks.EnableWifiCallbacks
import com.isupatches.wisefy.callbacks.GetCurrentNetworkCallbacks
import com.isupatches.wisefy.callbacks.GetCurrentNetworkInfoCallbacks
import com.isupatches.wisefy.callbacks.GetFrequencyCallbacks
import com.isupatches.wisefy.callbacks.GetIPCallbacks
import com.isupatches.wisefy.callbacks.GetNearbyAccessPointsCallbacks
import com.isupatches.wisefy.callbacks.GetSavedNetworksCallbacks
import com.isupatches.wisefysample.internal.base.BasePresenter
import com.isupatches.wisefysample.internal.util.RxSchedulersProvider

import javax.inject.Inject

internal class MiscPresenter @Inject constructor(
    private val model: MiscMvp.Model,
    rxSchedulersProvider: RxSchedulersProvider
) : BasePresenter<MiscMvp.View>(rxSchedulersProvider), MiscMvp.Presenter {

    /*
     * Model call-throughs
     */

    @RequiresPermission(allOf = [CHANGE_WIFI_STATE])
    override fun disableWifi() {
        model.disableWifi(object : DisableWifiCallbacks {
            override fun wifiDisabled() {
                displayWifiDisabled()
            }

            override fun failureDisablingWifi() {
                displayFailureDisablingWifi()
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
                displayWiseFyFailure(wisefyFailureCode)
            }
        })
    }

    @RequiresPermission(allOf = [CHANGE_WIFI_STATE])
    override fun enableWifi() {
        model.enableWifi(object : EnableWifiCallbacks {
            override fun wifiEnabled() {
                displayWifiEnabled()
            }

            override fun failureEnablingWifi() {
                displayFailureEnablingWifi()
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
                displayWiseFyFailure(wisefyFailureCode)
            }
        })
    }

    @RequiresPermission(allOf = [ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE])
    override fun getCurrentNetwork() {
        model.getCurrentNetwork(object : GetCurrentNetworkCallbacks {
            override fun retrievedCurrentNetwork(currentNetwork: WifiInfo) {
                displayCurrentNetwork(currentNetwork)
            }

            override fun noCurrentNetwork() {
                displayNoCurrentNetwork()
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
                displayWiseFyFailure(wisefyFailureCode)
            }
        })
    }

    @RequiresPermission(allOf = [ACCESS_NETWORK_STATE])
    override fun getCurrentNetworkInfo() {
        model.getCurrentNetworkInfo(object : GetCurrentNetworkInfoCallbacks {
            override fun retrievedCurrentNetworkInfo(currentNetworkInfo: NetworkInfo) {
                displayCurrentNetworkInfo(currentNetworkInfo)
            }

            override fun noCurrentNetworkInfo() {
                displayNoCurrentNetworkInfo()
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
                displayWiseFyFailure(wisefyFailureCode)
            }
        })
    }

    @RequiresPermission(allOf = [ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE])
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun getFrequency() {
        model.getFrequency(object : GetFrequencyCallbacks {
            override fun retrievedFrequency(frequency: Int) {
                displayFrequency(frequency)
            }

            override fun failureGettingFrequency() {
                displayFailureRetrievingFrequency()
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
                displayWiseFyFailure(wisefyFailureCode)
            }
        })
    }

    @RequiresPermission(allOf = [ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE])
    override fun getIP() {
        model.getIP(object : GetIPCallbacks {
            override fun retrievedIP(ip: String) {
                displayIP(ip)
            }

            override fun failureRetrievingIP() {
                displayFailureRetrievingIP()
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
                displayWiseFyFailure(wisefyFailureCode)
            }
        })
    }

    @RequiresPermission(allOf = [ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE])
    override fun getNearbyAccessPoints() {
        model.getNearbyAccessPoints(object : GetNearbyAccessPointsCallbacks {
            override fun retrievedNearbyAccessPoints(nearbyAccessPoints: List<ScanResult>) {
                displayNearbyAccessPoints(nearbyAccessPoints)
            }

            override fun noAccessPointsFound() {
                displayNoAccessPointsFound()
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
                displayWiseFyFailure(wisefyFailureCode)
            }
        })
    }

    @RequiresPermission(allOf = [ACCESS_WIFI_STATE])
    override fun getSavedNetworks() {
        model.getSavedNetworks(object : GetSavedNetworksCallbacks {
            override fun noSavedNetworksFound() {
                displayNoSavedNetworksFound()
            }

            override fun retrievedSavedNetworks(savedNetworks: List<WifiConfiguration>) {
                displaySavedNetworks(savedNetworks)
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
                displayWiseFyFailure(wisefyFailureCode)
            }
        })
    }

    /*
     * View callbacks
     */

    private fun displayWifiDisabled() {
        doSafelyWithView { view -> view.displayWifiDisabled() }
    }

    private fun displayFailureDisablingWifi() {
        doSafelyWithView { view -> view.displayFailureDisablingWifi() }
    }

    private fun displayWifiEnabled() {
        doSafelyWithView { view -> view.displayWifiEnabled() }
    }

    private fun displayFailureEnablingWifi() {
        doSafelyWithView { view -> view.displayFailureEnablingWifi() }
    }

    private fun displayCurrentNetwork(currentNetwork: WifiInfo) {
        doSafelyWithView { view -> view.displayCurrentNetwork(currentNetwork) }
    }

    private fun displayNoCurrentNetwork() {
        doSafelyWithView { view -> view.displayNoCurrentNetwork() }
    }

    private fun displayCurrentNetworkInfo(currentNetworkDetails: NetworkInfo) {
        doSafelyWithView { view -> view.displayCurrentNetworkInfo(currentNetworkDetails) }
    }

    private fun displayNoCurrentNetworkInfo() {
        doSafelyWithView { view -> view.displayNoCurrentNetworkInfo() }
    }

    private fun displayFrequency(frequency: Int) {
        doSafelyWithView { view -> view.displayFrequency(frequency) }
    }

    private fun displayFailureRetrievingFrequency() {
        doSafelyWithView { view -> view.displayFailureRetrievingFrequency() }
    }

    private fun displayIP(ip: String) {
        doSafelyWithView { view -> view.displayIP(ip) }
    }

    private fun displayFailureRetrievingIP() {
        doSafelyWithView { view -> view.displayFailureRetrievingIP() }
    }

    private fun displayNearbyAccessPoints(accessPoints: List<ScanResult>) {
        doSafelyWithView { view -> view.displayNearbyAccessPoints(accessPoints) }
    }

    private fun displayNoAccessPointsFound() {
        doSafelyWithView { view -> view.displayNoAccessPointsFound() }
    }

    private fun displayNoSavedNetworksFound() {
        doSafelyWithView { view -> view.displayNoSavedNetworksFound() }
    }

    private fun displaySavedNetworks(savedNetworks: List<WifiConfiguration>) {
        doSafelyWithView { view -> view.displaySavedNetworks(savedNetworks) }
    }
}
