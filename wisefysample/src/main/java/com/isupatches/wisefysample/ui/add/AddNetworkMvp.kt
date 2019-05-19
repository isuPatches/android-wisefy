package com.isupatches.wisefysample.ui.add

import android.net.wifi.WifiConfiguration

import com.isupatches.wisefy.callbacks.AddNetworkCallbacks
import com.isupatches.wisefysample.internal.base.BaseMvp

@Suppress("UndocumentedPublicClass", "UndocumentedPublicFunction")
internal interface AddNetworkMvp {

    interface View : BaseMvp.View {
        fun displayFailureAddingNetwork(wifiManagerReturn: Int)
        fun displayNetworkAdded(newNetworkId: Int, networkConfig: WifiConfiguration)
    }

    interface Presenter : BaseMvp.Presenter<View> {
        fun addOpenNetwork(ssid: String)
        fun addWEPNetwork(ssid: String, password: String)
        fun addWPA2Network(ssid: String, password: String)
    }

    interface Model {
        fun addOpenNetwork(ssid: String, callbacks: AddNetworkCallbacks)
        fun addWEPNetwork(ssid: String, password: String, callbacks: AddNetworkCallbacks)
        fun addWPA2Network(ssid: String, password: String, callbacks: AddNetworkCallbacks)
    }
}
