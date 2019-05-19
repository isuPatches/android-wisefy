package com.isupatches.wisefysample.ui.misc

import android.net.NetworkInfo
import android.net.wifi.ScanResult
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiInfo

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
        fun displayCurrentNetworkInfo(currentNetworkDetails: NetworkInfo)
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
        fun getFrequency()
        fun getIP()
        fun getNearbyAccessPoints()
        fun getSavedNetworks()
    }

    interface Model {
        fun disableWifi(callbacks: DisableWifiCallbacks)
        fun enableWifi(callbacks: EnableWifiCallbacks)
        fun getCurrentNetwork(callbacks: GetCurrentNetworkCallbacks)
        fun getCurrentNetworkInfo(callbacks: GetCurrentNetworkInfoCallbacks)
        fun getFrequency(callbacks: GetFrequencyCallbacks)
        fun getIP(callbacks: GetIPCallbacks)
        fun getNearbyAccessPoints(callbacks: GetNearbyAccessPointsCallbacks)
        fun getSavedNetworks(callbacks: GetSavedNetworksCallbacks)
    }
}
