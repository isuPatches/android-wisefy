package com.isupatches.wisefysample.ui.misc

import android.net.NetworkInfo
import android.net.wifi.ScanResult
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiInfo
import com.isupatches.wisefysample.ui.base.BaseMvp

interface MiscMvp {

    interface View : BaseMvp.View {
        fun displayWifiDisabled()
        fun displayFailureDisablingWifi()
        fun displayWifiEnabled()
        fun displayFailureEnablingWifi()
        fun displayCurrentNetwork(currentNetwork: WifiInfo)
        fun displayNoCurrentNetwork()
        fun displayCurrentNetworkInfo(currentNetworkDetails: NetworkInfo)
        fun displayNoCurrentNetworkInfo()
        fun displayFrequency(frequency: Int)
        fun displayFailureRetrievingFrequency()
        fun displayIP(ip: String)
        fun displayFailureRetrievingIP()
        fun displayNearbyAccessPoints(accessPoints: List<ScanResult>)
        fun displayNoSavedNetworksFound()
        fun displaySavedNetworks(savedNetworks: List<WifiConfiguration>)
    }

    interface Presenter : BaseMvp.Presenter<View> {

        /*
         * Model call-throughs
         */
        fun disableWifi()
        fun enableWifi()
        fun getCurrentNetwork()
        fun getCurrentNetworkInfo()
        fun getFrequency()
        fun getIP()
        fun getNearbyAccessPoints()
        fun getSavedNetworks()

        /*
         * View callbacks
         */
        fun displayWifiDisabled()
        fun displayFailureDisablingWifi()
        fun displayWifiEnabled()
        fun displayFailureEnablingWifi()
        fun displayCurrentNetwork(currentNetwork: WifiInfo)
        fun displayNoCurrentNetwork()
        fun displayCurrentNetworkInfo(currentNetworkDetails: NetworkInfo)
        fun displayNoCurrentNetworkInfo()
        fun displayFrequency(frequency: Int)
        fun displayFailureRetrievingFrequency()
        fun displayIP(ip: String)
        fun displayFailureRetrievingIP()
        fun displayNearbyAccessPoints(accessPoints: List<ScanResult>)
        fun displayNoSavedNetworksFound()
        fun displaySavedNetworks(savedNetworks: List<WifiConfiguration>)
    }

    interface Model {
        fun disableWifi()
        fun enableWifi()
        fun getCurrentNetwork()
        fun getCurrentNetworkInfo()
        fun getFrequency()
        fun getIP()
        fun getNearbyAccessPoints()
        fun getSavedNetworks()
    }
}