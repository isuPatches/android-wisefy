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

import com.isupatches.wisefy.WiseFy
import com.isupatches.wisefysample.internal.base.BasePresenter

internal class MiscPresenter(wiseFy: WiseFy) : BasePresenter<MiscMvp.View>(), MiscMvp.Presenter {

    private val model = MiscModel(this, wiseFy)

    /*
     * Model call-throughs
     */

    @RequiresPermission(allOf = [CHANGE_WIFI_STATE])
    override fun disableWifi() {
        model.disableWifi()
    }

    @RequiresPermission(allOf = [CHANGE_WIFI_STATE])
    override fun enableWifi() {
        model.enableWifi()
    }

    @RequiresPermission(allOf = [ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE])
    override fun getCurrentNetwork() {
        model.getCurrentNetwork()
    }

    @RequiresPermission(allOf = [ACCESS_NETWORK_STATE])
    override fun getCurrentNetworkInfo() {
        model.getCurrentNetworkInfo()
    }

    @RequiresPermission(allOf = [ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE])
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun getFrequency() {
        model.getFrequency()
    }

    @RequiresPermission(allOf = [ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE])
    override fun getIP() {
        model.getIP()
    }

    @RequiresPermission(allOf = [ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE])
    override fun getNearbyAccessPoints() {
        model.getNearbyAccessPoints()
    }

    @RequiresPermission(allOf = [ACCESS_WIFI_STATE])
    override fun getSavedNetworks() {
        model.getSavedNetworks()
    }

    /*
     * View callbacks
     */

    override fun displayWifiDisabled() {
        doSafelyWithView { view -> view.displayWifiDisabled() }
    }

    override fun displayFailureDisablingWifi() {
        doSafelyWithView { view -> view.displayFailureDisablingWifi() }
    }

    override fun displayWifiEnabled() {
        doSafelyWithView { view -> view.displayWifiEnabled() }
    }

    override fun displayFailureEnablingWifi() {
        doSafelyWithView { view -> view.displayFailureEnablingWifi() }
    }

    override fun displayCurrentNetwork(currentNetwork: WifiInfo) {
        doSafelyWithView { view -> view.displayCurrentNetwork(currentNetwork) }
    }

    override fun displayNoCurrentNetwork() {
        doSafelyWithView { view -> view.displayNoCurrentNetwork() }
    }

    override fun displayCurrentNetworkInfo(currentNetworkDetails: NetworkInfo) {
        doSafelyWithView { view -> view.displayCurrentNetworkInfo(currentNetworkDetails) }
    }

    override fun displayNoCurrentNetworkInfo() {
        doSafelyWithView { view -> view.displayNoCurrentNetworkInfo() }
    }

    override fun displayFrequency(frequency: Int) {
        doSafelyWithView { view -> view.displayFrequency(frequency) }
    }

    override fun displayFailureRetrievingFrequency() {
        doSafelyWithView { view -> view.displayFailureRetrievingFrequency() }
    }

    override fun displayIP(ip: String) {
        doSafelyWithView { view -> view.displayIP(ip) }
    }

    override fun displayFailureRetrievingIP() {
        doSafelyWithView { view -> view.displayFailureRetrievingIP() }
    }

    override fun displayNearbyAccessPoints(accessPoints: List<ScanResult>) {
        doSafelyWithView { view -> view.displayNearbyAccessPoints(accessPoints) }
    }

    override fun displayNoAccessPointsFound() {
        doSafelyWithView { view -> view.displayNoAccessPointsFound() }
    }

    override fun displayNoSavedNetworksFound() {
        doSafelyWithView { view -> view.displayNoSavedNetworksFound() }
    }

    override fun displaySavedNetworks(savedNetworks: List<WifiConfiguration>) {
        doSafelyWithView { view -> view.displaySavedNetworks(savedNetworks) }
    }
}
