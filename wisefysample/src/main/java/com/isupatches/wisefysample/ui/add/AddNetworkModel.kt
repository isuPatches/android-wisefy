package com.isupatches.wisefysample.ui.add

import android.Manifest.permission.ACCESS_WIFI_STATE
import android.net.wifi.WifiConfiguration
import androidx.annotation.RequiresPermission

import com.isupatches.wisefy.WiseFy
import com.isupatches.wisefy.callbacks.AddNetworkCallbacks

internal class AddNetworkModel(
    private val presenter: AddNetworkMvp.Presenter,
    private val wiseFy: WiseFy
): AddNetworkMvp.Model {

    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun addOpenNetwork(ssid: String) {
        wiseFy.addOpenNetwork(ssid, object : AddNetworkCallbacks {
            override fun networkAdded(newNetworkId: Int, networkConfig: WifiConfiguration) {
                presenter.displayNetworkAdded(newNetworkId, networkConfig)
            }

            override fun failureAddingNetwork(wifiManagerReturn: Int) {
                presenter.displayFailureAddingNetwork(wifiManagerReturn)
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
                presenter.displayWiseFyFailure(wisefyFailureCode)
            }
        })
    }

    @RequiresPermission(ACCESS_WIFI_STATE)
    @Suppress("DEPRECATION")
    override fun addWEPNetwork(ssid: String, password: String) {
        wiseFy.addWEPNetwork(ssid, password, object : AddNetworkCallbacks {
            override fun networkAdded(newNetworkId: Int, networkConfig: WifiConfiguration) {
                presenter.displayNetworkAdded(newNetworkId, networkConfig)
            }

            override fun failureAddingNetwork(wifiManagerReturn: Int) {
                presenter.displayFailureAddingNetwork(wifiManagerReturn)
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
                presenter.displayWiseFyFailure(wisefyFailureCode)
            }
        })
    }

    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun addWPA2Network(ssid: String, password: String) {
        wiseFy.addWPA2Network(ssid, password, object : AddNetworkCallbacks {
            override fun networkAdded(newNetworkId: Int, networkConfig: WifiConfiguration) {
                presenter.displayNetworkAdded(newNetworkId, networkConfig)
            }

            override fun failureAddingNetwork(wifiManagerReturn: Int) {
                presenter.displayFailureAddingNetwork(wifiManagerReturn)
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
                presenter.displayWiseFyFailure(wisefyFailureCode)
            }
        })
    }
}
