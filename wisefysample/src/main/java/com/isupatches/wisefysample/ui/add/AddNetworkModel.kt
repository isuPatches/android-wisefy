package com.isupatches.wisefysample.ui.add

import android.Manifest.permission.ACCESS_WIFI_STATE
import androidx.annotation.RequiresPermission

import com.isupatches.wisefy.WiseFy
import com.isupatches.wisefy.callbacks.AddNetworkCallbacks

import javax.inject.Inject

internal class AddNetworkModel @Inject constructor(
    private val wiseFy: WiseFy
): AddNetworkMvp.Model {

    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun addOpenNetwork(ssid: String, callbacks: AddNetworkCallbacks) {
        wiseFy.addOpenNetwork(ssid, callbacks)
    }

    @RequiresPermission(ACCESS_WIFI_STATE)
    @Suppress("DEPRECATION")
    override fun addWEPNetwork(
        ssid: String,
        password: String,
        callbacks: AddNetworkCallbacks
    ) {
        wiseFy.addWEPNetwork(ssid, password, callbacks)
    }

    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun addWPA2Network(
        ssid: String,
        password: String,
        callbacks: AddNetworkCallbacks
    ) {
        wiseFy.addWPA2Network(ssid, password, callbacks)
    }
}
