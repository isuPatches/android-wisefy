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

import android.net.wifi.ScanResult
import android.net.wifi.WifiConfiguration
import com.isupatches.wisefy.callbacks.SearchForAccessPointCallbacks
import com.isupatches.wisefy.callbacks.SearchForAccessPointsCallbacks
import com.isupatches.wisefy.callbacks.SearchForSSIDCallbacks
import com.isupatches.wisefy.callbacks.SearchForSSIDsCallbacks
import com.isupatches.wisefy.callbacks.SearchForSavedNetworkCallbacks
import com.isupatches.wisefy.callbacks.SearchForSavedNetworksCallbacks
import com.isupatches.wisefysample.internal.base.BaseMvp

@Suppress("UndocumentedPublicClass", "UndocumentedPublicFunction")
internal interface SearchMvp {

    interface View : BaseMvp.View {
        fun displaySavedNetwork(savedNetwork: WifiConfiguration)
        fun displaySavedNetworkNotFound()
        fun displaySavedNetworks(savedNetworks: List<WifiConfiguration>)
        fun displayNoSavedNetworksFound()
        fun displayAccessPoint(accessPoint: ScanResult)
        fun displayAccessPointNotFound()
        fun displayAccessPoints(accessPoints: List<ScanResult>)
        fun displayNoAccessPointsFound()
        fun displaySSID(ssid: String)
        fun displaySSIDNotFound()
        fun displaySSIDs(ssids: List<String>)
        fun displayNoSSIDsFound()
    }

    interface Presenter : BaseMvp.Presenter<View> {
        fun searchForAccessPoint(regexForSSID: String, timeout: Int, filterDuplicates: Boolean)
        fun searchForAccessPoints(regexForSSID: String, filterDuplicates: Boolean)
        fun searchForSavedNetwork(regexForSSID: String)
        fun searchForSavedNetworks(regexForSSID: String)
        fun searchForSSID(regexForSSID: String, timeout: Int)
        fun searchForSSIDs(regexForSSID: String)
    }

    interface Model {
        fun searchForAccessPoint(
            regexForSSID: String,
            timeout: Int,
            filterDuplicates: Boolean,
            callbacks: SearchForAccessPointCallbacks
        )
        fun searchForAccessPoints(
            regexForSSID: String,
            filterDuplicates: Boolean,
            callbacks: SearchForAccessPointsCallbacks
        )
        fun searchForSavedNetwork(regexForSSID: String, callbacks: SearchForSavedNetworkCallbacks)
        fun searchForSavedNetworks(regexForSSID: String, callbacks: SearchForSavedNetworksCallbacks)
        fun searchForSSID(regexForSSID: String, timeout: Int, callbacks: SearchForSSIDCallbacks)
        fun searchForSSIDs(regexForSSID: String, callbacks: SearchForSSIDsCallbacks)
    }
}
