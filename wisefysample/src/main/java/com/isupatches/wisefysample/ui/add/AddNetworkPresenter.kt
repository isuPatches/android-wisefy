package com.isupatches.wisefysample.ui.add

import android.Manifest.permission.ACCESS_WIFI_STATE
import android.net.wifi.WifiConfiguration
import androidx.annotation.RequiresPermission

import com.isupatches.wisefy.callbacks.AddNetworkCallbacks
import com.isupatches.wisefysample.internal.base.BasePresenter
import com.isupatches.wisefysample.internal.util.RxSchedulersProvider
import javax.inject.Inject

internal class AddNetworkPresenter @Inject constructor(
    private val model: AddNetworkMvp.Model,
    rxSchedulersProvider: RxSchedulersProvider
) : BasePresenter<AddNetworkMvp.View>(rxSchedulersProvider), AddNetworkMvp.Presenter {

    /*
     * Model call-throughs
     */

    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun addOpenNetwork(ssid: String) {
        model.addOpenNetwork(ssid, object : AddNetworkCallbacks {
            override fun networkAdded(newNetworkId: Int, networkConfig: WifiConfiguration) {
                displayNetworkAdded(newNetworkId, networkConfig)
            }

            override fun failureAddingNetwork(wifiManagerReturn: Int) {
                displayFailureAddingNetwork(wifiManagerReturn)
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
                displayWiseFyFailure(wisefyFailureCode)
            }
        })
    }

    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun addWEPNetwork(ssid: String, password: String) {
        model.addWEPNetwork(ssid, password, object : AddNetworkCallbacks {
            override fun networkAdded(newNetworkId: Int, networkConfig: WifiConfiguration) {
                displayNetworkAdded(newNetworkId, networkConfig)
            }

            override fun failureAddingNetwork(wifiManagerReturn: Int) {
                displayFailureAddingNetwork(wifiManagerReturn)
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
                displayWiseFyFailure(wisefyFailureCode)
            }
        })
    }

    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun addWPA2Network(ssid: String, password: String) {
        model.addWPA2Network(ssid, password, object : AddNetworkCallbacks {
            override fun networkAdded(newNetworkId: Int, networkConfig: WifiConfiguration) {
                displayNetworkAdded(newNetworkId, networkConfig)
            }

            override fun failureAddingNetwork(wifiManagerReturn: Int) {
                displayFailureAddingNetwork(wifiManagerReturn)
            }

            override fun wisefyFailure(wisefyFailureCode: Int) {
                displayWiseFyFailure(wisefyFailureCode)
            }
        })
    }

    /*
     * View callbacks
     */

    private fun displayFailureAddingNetwork(wifiManagerReturn: Int) {
        doSafelyWithView { view -> view.displayFailureAddingNetwork(wifiManagerReturn) }
    }

    private fun displayNetworkAdded(newNetworkId: Int, networkConfig: WifiConfiguration) {
        doSafelyWithView { view -> view.displayNetworkAdded(newNetworkId, networkConfig) }
    }
}
