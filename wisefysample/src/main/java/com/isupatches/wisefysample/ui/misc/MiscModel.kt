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
import com.isupatches.wisefy.callbacks.DisableWifiCallbacks
import com.isupatches.wisefy.callbacks.EnableWifiCallbacks
import com.isupatches.wisefy.callbacks.GetCurrentNetworkCallbacks
import com.isupatches.wisefy.callbacks.GetCurrentNetworkInfoCallbacks
import com.isupatches.wisefy.callbacks.GetFrequencyCallbacks
import com.isupatches.wisefy.callbacks.GetIPCallbacks
import com.isupatches.wisefy.callbacks.GetNearbyAccessPointsCallbacks
import com.isupatches.wisefy.callbacks.GetSavedNetworksCallbacks

class MiscModel(
    private val presenter: MiscMvp.Presenter,
    private val wiseFy: WiseFy
): MiscMvp.Model {

    @RequiresPermission(allOf = [CHANGE_WIFI_STATE])
    override fun disableWifi() {
        wiseFy.disableWifi(object : DisableWifiCallbacks {
            override fun wifiDisabled() {
                presenter.displayWifiDisabled()
            }

            override fun failureDisablingWifi() {
                presenter.displayFailureDisablingWifi()
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
                presenter.displayWiseFyFailure(wisefyFailureCode)
            }
        })
    }

    @RequiresPermission(allOf = [CHANGE_WIFI_STATE])
    override fun enableWifi() {
        wiseFy.enableWifi(object : EnableWifiCallbacks {
            override fun wifiEnabled() {
                presenter.displayWifiEnabled()
            }

            override fun failureEnablingWifi() {
                presenter.displayFailureEnablingWifi()
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
                presenter.displayWiseFyFailure(wisefyFailureCode)
            }
        })
    }

    @RequiresPermission(allOf = [ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE])
    override fun getCurrentNetwork() {
        wiseFy.getCurrentNetwork(object : GetCurrentNetworkCallbacks {
            override fun retrievedCurrentNetwork(currentNetwork: WifiInfo) {
                presenter.displayCurrentNetwork(currentNetwork)
            }

            override fun noCurrentNetwork() {
                presenter.displayNoCurrentNetwork()
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
                presenter.displayWiseFyFailure(wisefyFailureCode)
            }
        })
    }

    @RequiresPermission(allOf = [ACCESS_NETWORK_STATE])
    override fun getCurrentNetworkInfo() {
        wiseFy.getCurrentNetworkInfo(object : GetCurrentNetworkInfoCallbacks {
            override fun retrievedCurrentNetworkInfo(currentNetworkDetails: NetworkInfo) {
                presenter.displayCurrentNetworkInfo(currentNetworkDetails)
            }

            override fun noCurrentNetworkInfo() {
                presenter.displayNoCurrentNetworkInfo()
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
                presenter.displayWiseFyFailure(wisefyFailureCode)
            }
        })
    }

    @RequiresPermission(allOf = [ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE])
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun getFrequency() {
        wiseFy.getFrequency(object : GetFrequencyCallbacks {
            override fun retrievedFrequency(frequency: Int) {
                presenter.displayFrequency(frequency)
            }

            override fun failureGettingFrequency() {
                presenter.displayFailureRetrievingFrequency()
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
                presenter.displayWiseFyFailure(wisefyFailureCode)
            }
        })
    }

    @RequiresPermission(allOf = [ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE])
    override fun getIP() {
        wiseFy.getIP(object : GetIPCallbacks {
            override fun retrievedIP(ip: String) {
                presenter.displayIP(ip)
            }

            override fun failureRetrievingIP() {
                presenter.displayFailureRetrievingIP()
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
                presenter.displayWiseFyFailure(wisefyFailureCode)
            }
        })
    }

    @RequiresPermission(allOf = [ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE])
    override fun getNearbyAccessPoints() {
        wiseFy.getNearbyAccessPoints(true, object : GetNearbyAccessPointsCallbacks {
            override fun retrievedNearbyAccessPoints(nearbyAccessPoints: List<ScanResult>) {
                presenter.displayNearbyAccessPoints(nearbyAccessPoints)
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
                presenter.displayWiseFyFailure(wisefyFailureCode)
            }
        })
    }

    @RequiresPermission(allOf = [ACCESS_WIFI_STATE])
    override fun getSavedNetworks() {
        wiseFy.getSavedNetworks(object : GetSavedNetworksCallbacks {
            override fun noSavedNetworksFound() {
                presenter.displayNoSavedNetworksFound()
            }

            override fun retrievedSavedNetworks(savedNetworks: List<WifiConfiguration>) {
                presenter.displaySavedNetworks(savedNetworks)
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
                presenter.displayWiseFyFailure(wisefyFailureCode)
            }
        })
    }
}
