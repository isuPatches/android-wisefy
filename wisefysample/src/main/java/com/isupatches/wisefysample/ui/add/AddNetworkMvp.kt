package com.isupatches.wisefysample.ui.add

import android.net.wifi.WifiConfiguration

import com.isupatches.wisefysample.internal.base.BaseMvp

internal interface AddNetworkMvp {

    interface View : BaseMvp.View {
        fun displayFailureAddingNetwork(wifiManagerReturn: Int)
        fun displayNetworkAdded(newNetworkId: Int, networkConfig: WifiConfiguration)
    }

    interface Presenter : BaseMvp.Presenter<View> {

        /*
         * Model call-throughs
         */
        fun addOpenNetwork(ssid: String)
        fun addWEPNetwork(ssid: String, password: String)
        fun addWPA2Network(ssid: String, password: String)

        /*
         * View callbacks
         */
        fun displayFailureAddingNetwork(wifiManagerReturn: Int)
        fun displayNetworkAdded(newNetworkId: Int, networkConfig: WifiConfiguration)
    }

    interface Model {
        fun addOpenNetwork(ssid: String)
        fun addWEPNetwork(ssid: String, password: String)
        fun addWPA2Network(ssid: String, password: String)
    }
}
