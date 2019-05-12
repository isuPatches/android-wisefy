package com.isupatches.wisefysample.ui.add

import android.Manifest.permission.ACCESS_WIFI_STATE
import android.net.wifi.WifiConfiguration
import androidx.annotation.RequiresPermission

import com.isupatches.wisefy.WiseFy
import com.isupatches.wisefysample.internal.base.BasePresenter

internal class AddNetworkPresenter(
    wiseFy: WiseFy
) : BasePresenter<AddNetworkMvp.View>(), AddNetworkMvp.Presenter {

    private val model = AddNetworkModel(this, wiseFy)

    /*
     * Model call-throughs
     */

    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun addOpenNetwork(ssid: String) {
        model.addOpenNetwork(ssid)
    }

    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun addWEPNetwork(ssid: String, password: String) {
        model.addWEPNetwork(ssid, password)
    }

    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun addWPA2Network(ssid: String, password: String) {
        model.addWPA2Network(ssid, password)
    }

    /*
     * View callbacks
     */

    override fun displayFailureAddingNetwork(wifiManagerReturn: Int) {
        doSafelyWithView { view -> view.displayFailureAddingNetwork(wifiManagerReturn) }
    }

    override fun displayNetworkAdded(newNetworkId: Int, networkConfig: WifiConfiguration) {
        doSafelyWithView { view -> view.displayNetworkAdded(newNetworkId, networkConfig) }
    }
}
