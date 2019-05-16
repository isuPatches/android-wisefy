package com.isupatches.wisefysample.ui.search

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_WIFI_STATE
import androidx.annotation.RequiresPermission

import com.isupatches.wisefy.WiseFy
import com.isupatches.wisefy.callbacks.GetSavedNetworkCallbacks
import com.isupatches.wisefy.callbacks.GetSavedNetworksCallbacks
import com.isupatches.wisefy.callbacks.SearchForAccessPointCallbacks
import com.isupatches.wisefy.callbacks.SearchForAccessPointsCallbacks
import com.isupatches.wisefy.callbacks.SearchForSSIDCallbacks
import com.isupatches.wisefy.callbacks.SearchForSSIDsCallbacks

internal class SearchModel(
    private val wiseFy: WiseFy
): SearchMvp.Model {

    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun getSavedNetwork(regexForSSID: String, callbacks: GetSavedNetworkCallbacks) {
        wiseFy.getSavedNetwork(regexForSSID, callbacks)
    }

    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun getSavedNetworks(regexForSSID: String, callbacks: GetSavedNetworksCallbacks) {
        wiseFy.getSavedNetworks(regexForSSID, callbacks)
    }

    @RequiresPermission(allOf = [ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForAccessPoint(
        regexForSSID: String,
        timeout: Int,
        filterDuplicates: Boolean,
        callbacks: SearchForAccessPointCallbacks
    ) {
        wiseFy.searchForAccessPoint(regexForSSID, timeout, filterDuplicates, callbacks)
    }

    @RequiresPermission(allOf = [ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForAccessPoints(
        regexForSSID: String,
        filterDuplicates: Boolean,
        callbacks: SearchForAccessPointsCallbacks
    ) {
        wiseFy.searchForAccessPoints(regexForSSID, filterDuplicates, callbacks)
    }

    @RequiresPermission(allOf = [ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForSSID(
        regexForSSID: String,
        timeout: Int,
        callbacks: SearchForSSIDCallbacks
    ) {
        wiseFy.searchForSSID(regexForSSID, timeout, callbacks)
    }

    @RequiresPermission(allOf = [ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE])
    override fun searchForSSIDs(regexForSSID: String, callbacks: SearchForSSIDsCallbacks) {
        wiseFy.searchForSSIDs(regexForSSID, callbacks)
    }
}
