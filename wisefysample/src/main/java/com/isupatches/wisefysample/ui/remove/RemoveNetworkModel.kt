package com.isupatches.wisefysample.ui.remove

import android.Manifest.permission.ACCESS_WIFI_STATE
import androidx.annotation.RequiresPermission

import com.isupatches.wisefy.WiseFy
import com.isupatches.wisefy.callbacks.RemoveNetworkCallbacks

import javax.inject.Inject

internal class RemoveNetworkModel @Inject constructor(
    private val wiseFy: WiseFy
): RemoveNetworkMvp.Model {

    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun removeNetwork(networkName: String, callbacks: RemoveNetworkCallbacks) {
        wiseFy.removeNetwork(networkName, callbacks)
    }
}