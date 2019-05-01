package com.isupatches.wisefysample.ui.search

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.net.wifi.ScanResult
import android.net.wifi.WifiConfiguration
import androidx.annotation.RequiresPermission

import com.isupatches.wisefy.WiseFy
import com.isupatches.wisefy.callbacks.GetSavedNetworkCallbacks
import com.isupatches.wisefy.callbacks.GetSavedNetworksCallbacks
import com.isupatches.wisefy.callbacks.SearchForAccessPointCallbacks
import com.isupatches.wisefy.callbacks.SearchForAccessPointsCallbacks
import com.isupatches.wisefy.callbacks.SearchForSSIDCallbacks
import com.isupatches.wisefy.callbacks.SearchForSSIDsCallbacks
import com.isupatches.wisefy.constants.WiseFyCode

class SearchModel(
    private val presenter: SearchMvp.Presenter,
    private val wiseFy: WiseFy
): SearchMvp.Model {

    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun getSavedNetwork(regexForSSID: String) {
        wiseFy.getSavedNetwork(regexForSSID, object : GetSavedNetworkCallbacks {
            override fun retrievedSavedNetwork(savedNetwork: WifiConfiguration) {
                presenter.displaySavedNetwork(savedNetwork)
            }

            override fun savedNetworkNotFound() {
                presenter.displaySavedNetworkNotFound()
            }

            override fun wisefyFailure(@WiseFyCode wisefyFailureCode: Int) {
                presenter.displayWiseFyFailure(wisefyFailureCode)
            }
        })
    }

    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun getSavedNetworks(regexForSSID: String) {
        wiseFy.getSavedNetworks(regexForSSID, object : GetSavedNetworksCallbacks {
            override fun retrievedSavedNetworks(savedNetworks: List<WifiConfiguration>) {
                presenter.displaySavedNetworks(savedNetworks)
            }

            override fun noSavedNetworksFound() {
                presenter.displayNoSavedNetworksFound()
            }

            override fun wisefyFailure(@WiseFyCode wisefyFailureCode: Int) {
                presenter.displayWiseFyFailure(wisefyFailureCode)
            }
        })
    }

    @RequiresPermission(allOf = [ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForAccessPoint(regexForSSID: String, timeout: Int, filterDuplicates: Boolean) {
        wiseFy.searchForAccessPoint(regexForSSID, timeout, filterDuplicates, object : SearchForAccessPointCallbacks {
            override fun accessPointFound(accessPoint: ScanResult) {
                presenter.displayAccessPoint(accessPoint)
            }

            override fun accessPointNotFound() {
                presenter.displayAccessPointNotFound()
            }

            override fun wisefyFailure(@WiseFyCode wisefyFailureCode: Int) {
                presenter.displayWiseFyFailure(wisefyFailureCode)
            }
        })
    }

    @RequiresPermission(allOf = [ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForAccessPoints(regexForSSID: String, filterDuplicates: Boolean) {
        wiseFy.searchForAccessPoints(regexForSSID, filterDuplicates, object : SearchForAccessPointsCallbacks {
            override fun foundAccessPoints(accessPoints: List<ScanResult>) {
                presenter.displayAccessPoints(accessPoints)
            }

            override fun noAccessPointsFound() {
                presenter.displayNoAccessPointsFound()
            }

            override fun wisefyFailure(@WiseFyCode wisefyFailureCode: Int) {
                presenter.displayWiseFyFailure(wisefyFailureCode)
            }
        })
    }

    @RequiresPermission(allOf = [ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForSSID(regexForSSID: String, timeout: Int) {
        wiseFy.searchForSSID(regexForSSID, timeout, object : SearchForSSIDCallbacks {
            override fun ssidFound(ssid: String) {
                presenter.displaySSID(ssid)
            }

            override fun ssidNotFound() {
                presenter.displaySSIDNotFound()
            }

            override fun wisefyFailure(@WiseFyCode wisefyFailureCode: Int) {
                presenter.displayWiseFyFailure(wisefyFailureCode)
            }
        })
    }

    @RequiresPermission(allOf = [ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForSSIDs(regexForSSID: String) {
        wiseFy.searchForSSIDs(regexForSSID, object : SearchForSSIDsCallbacks {
            override fun retrievedSSIDs(ssids: List<String>) {
                presenter.displaySSIDs(ssids)
            }

            override fun noSSIDsFound() {
                presenter.displayNoSSIDsFound()
            }

            override fun wisefyFailure(@WiseFyCode wisefyFailureCode: Int) {
                presenter.displayWiseFyFailure(wisefyFailureCode)
            }
        })
    }
}
