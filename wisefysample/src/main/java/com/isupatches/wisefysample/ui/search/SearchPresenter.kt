package com.isupatches.wisefysample.ui.search

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.net.wifi.ScanResult
import android.net.wifi.WifiConfiguration
import androidx.annotation.RequiresPermission

import com.isupatches.wisefy.WiseFy
import com.isupatches.wisefysample.internal.base.BasePresenter

internal class SearchPresenter(
    wiseFy: WiseFy
) : BasePresenter<SearchMvp.View>(), SearchMvp.Presenter {

    private val model = SearchModel(this, wiseFy)

    /*
     * Model call-throughs
     */

    override fun getSavedNetwork(regexForSSID: String) {
        model.getSavedNetwork(regexForSSID)
    }

    override fun getSavedNetworks(regexForSSID: String) {
        model.getSavedNetworks(regexForSSID)
    }

    @RequiresPermission(allOf = [ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForAccessPoint(regexForSSID: String, timeout: Int, filterDuplicates: Boolean) {
        model.searchForAccessPoint(regexForSSID, timeout, filterDuplicates)
    }

    @RequiresPermission(allOf = [ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForAccessPoints(regexForSSID: String, filterDuplicates: Boolean) {
        model.searchForAccessPoints(regexForSSID, filterDuplicates)
    }

    @RequiresPermission(allOf = [ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForSSID(regexForSSID: String, timeout: Int) {
        model.searchForSSID(regexForSSID, timeout)
    }

    @RequiresPermission(allOf = [ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForSSIDs(regexForSSID: String) {
        model.searchForSSIDs(regexForSSID)
    }

    /*
     * View callbacks
     */

    override fun displaySavedNetwork(savedNetwork: WifiConfiguration) {
        doSafelyWithView { view -> view.displaySavedNetwork(savedNetwork) }
    }

    override fun displaySavedNetworkNotFound() {
        doSafelyWithView { view -> view.displaySavedNetworkNotFound() }
    }

    override fun displaySavedNetworks(savedNetworks: List<WifiConfiguration>) {
        doSafelyWithView { view -> view.displaySavedNetworks(savedNetworks) }
    }

    override fun displayNoSavedNetworksFound() {
        doSafelyWithView { view -> view.displayNoSavedNetworksFound() }
    }

    override fun displayAccessPoint(accessPoint: ScanResult) {
        doSafelyWithView { view -> view.displayAccessPoint(accessPoint) }
    }

    override fun displayAccessPointNotFound() {
        doSafelyWithView { view -> view.displayAccessPointNotFound() }
    }

    override fun displayAccessPoints(accessPoints: List<ScanResult>) {
        doSafelyWithView { view -> view.displayAccessPoints(accessPoints) }
    }

    override fun displayNoAccessPointsFound() {
        doSafelyWithView { view -> view.displayNoAccessPointsFound() }
    }

    override fun displaySSID(ssid: String) {
        doSafelyWithView { view -> view.displaySSID(ssid) }
    }

    override fun displaySSIDNotFound() {
        doSafelyWithView { view -> view.displaySSIDNotFound() }
    }

    override fun displaySSIDs(ssids: List<String>) {
        doSafelyWithView { view -> view.displaySSIDs(ssids) }
    }

    override fun displayNoSSIDsFound() {
        doSafelyWithView { view -> view.displayNoSSIDsFound() }
    }
}
