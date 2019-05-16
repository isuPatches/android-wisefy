package com.isupatches.wisefysample.ui.misc

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.Manifest.permission.CHANGE_WIFI_STATE
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

internal class MiscModel(
    private val wiseFy: WiseFy
): MiscMvp.Model {

    @RequiresPermission(allOf = [CHANGE_WIFI_STATE])
    override fun disableWifi(callbacks: DisableWifiCallbacks) {
        wiseFy.disableWifi()
    }

    @RequiresPermission(allOf = [CHANGE_WIFI_STATE])
    override fun enableWifi(callbacks: EnableWifiCallbacks) {
        wiseFy.enableWifi(callbacks)
    }

    @RequiresPermission(allOf = [ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE])
    override fun getCurrentNetwork(callbacks: GetCurrentNetworkCallbacks) {
        wiseFy.getCurrentNetwork(callbacks)
    }

    @RequiresPermission(allOf = [ACCESS_NETWORK_STATE])
    override fun getCurrentNetworkInfo(callbacks: GetCurrentNetworkInfoCallbacks) {
        wiseFy.getCurrentNetworkInfo(callbacks)
    }

    @RequiresPermission(allOf = [ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE])
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun getFrequency(callbacks: GetFrequencyCallbacks) {
        wiseFy.getFrequency(callbacks)
    }

    @RequiresPermission(allOf = [ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE])
    override fun getIP(callbacks: GetIPCallbacks) {
        wiseFy.getIP(callbacks)
    }

    @RequiresPermission(allOf = [ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE])
    override fun getNearbyAccessPoints(callbacks: GetNearbyAccessPointsCallbacks) {
        wiseFy.getNearbyAccessPoints(true, callbacks)
    }

    @RequiresPermission(allOf = [ACCESS_WIFI_STATE])
    override fun getSavedNetworks(callbacks: GetSavedNetworksCallbacks) {
        wiseFy.getSavedNetworks(callbacks)
    }
}
