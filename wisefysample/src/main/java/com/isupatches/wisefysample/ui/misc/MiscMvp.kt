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

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.net.NetworkInfo
import android.net.wifi.ScanResult
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiInfo
import androidx.annotation.RequiresPermission
import com.isupatches.wisefy.callbacks.DisableWifiCallbacks
import com.isupatches.wisefy.callbacks.EnableWifiCallbacks
import com.isupatches.wisefy.callbacks.GetCurrentNetworkCallbacks
import com.isupatches.wisefy.callbacks.GetCurrentNetworkInfoCallbacks
import com.isupatches.wisefy.callbacks.GetFrequencyCallbacks
import com.isupatches.wisefy.callbacks.GetIPCallbacks
import com.isupatches.wisefy.callbacks.GetNearbyAccessPointsCallbacks
import com.isupatches.wisefy.callbacks.GetSavedNetworksCallbacks
import com.isupatches.wisefysample.internal.base.BaseMvp

@Suppress("UndocumentedPublicClass", "UndocumentedPublicFunction")
internal interface MiscMvp {

    interface View : BaseMvp.View {
        fun displayWifiDisabled()
        fun displayFailureDisablingWifi()
        fun displayWifiEnabled()
        fun displayFailureEnablingWifi()
        fun displayCurrentNetwork(currentNetwork: WifiInfo)
        fun displayNoCurrentNetwork()
        fun displayCurrentNetworkInfo(currentNetworkInfo: NetworkInfo)
        fun displayNoCurrentNetworkInfo()
        fun displayFrequency(frequency: Int)
        fun displayFailureRetrievingFrequency()
        fun displayIP(ip: String)
        fun displayFailureRetrievingIP()
        fun displayNearbyAccessPoints(accessPoints: List<ScanResult>)
        fun displayNoAccessPointsFound()
        fun displayNoSavedNetworksFound()
        fun displaySavedNetworks(savedNetworks: List<WifiConfiguration>)
    }

    interface Presenter : BaseMvp.Presenter<View> {

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

    interface Model {

        fun disableWifi(callbacks: DisableWifiCallbacks)

        fun enableWifi(callbacks: EnableWifiCallbacks)

        fun getCurrentNetwork(callbacks: GetCurrentNetworkCallbacks)

        fun getCurrentNetworkInfo(callbacks: GetCurrentNetworkInfoCallbacks)

        @RequiresPermission(ACCESS_FINE_LOCATION)
        fun getFrequency(callbacks: GetFrequencyCallbacks)

        @RequiresPermission(ACCESS_FINE_LOCATION)
        fun getIP(callbacks: GetIPCallbacks)

        @RequiresPermission(ACCESS_FINE_LOCATION)
        fun getNearbyAccessPoints(callbacks: GetNearbyAccessPointsCallbacks)

        @RequiresPermission(ACCESS_FINE_LOCATION)
        fun getSavedNetworks(callbacks: GetSavedNetworksCallbacks)
    }
}
