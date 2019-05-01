package com.isupatches.wisefysample.ui.search

import android.net.wifi.ScanResult
import android.net.wifi.WifiConfiguration
import com.isupatches.wisefysample.ui.base.BaseMvp

interface SearchMvp {

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

        /*
         * Model call-throughs
         */
        fun getSavedNetwork(regexForSSID: String)
        fun getSavedNetworks(regexForSSID: String)
        fun searchForAccessPoint(regexForSSID: String, timeout: Int, filterDuplicates: Boolean)
        fun searchForAccessPoints(regexForSSID: String, filterDuplicates: Boolean)
        fun searchForSSID(regexForSSID: String, timeout: Int)
        fun searchForSSIDs(regexForSSID: String)

        /*
         * View Callbacks
         */
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

    interface Model {

        fun getSavedNetwork(regexForSSID: String)
        fun getSavedNetworks(regexForSSID: String)
        fun searchForAccessPoint(regexForSSID: String, timeout: Int, filterDuplicates: Boolean)
        fun searchForAccessPoints(regexForSSID: String, filterDuplicates: Boolean)
        fun searchForSSID(regexForSSID: String, timeout: Int)
        fun searchForSSIDs(regexForSSID: String)
    }
}
