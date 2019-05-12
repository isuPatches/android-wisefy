package com.isupatches.wisefysample.ui.remove

import android.Manifest.permission.ACCESS_WIFI_STATE
import androidx.annotation.RequiresPermission

import com.isupatches.wisefy.WiseFy
import com.isupatches.wisefy.callbacks.RemoveNetworkCallbacks
import com.isupatches.wisefy.constants.WiseFyCode

internal class RemoveNetworkModel(
    private val presenter: RemoveNetworkMvp.Presenter,
    private val wiseFy: WiseFy
): RemoveNetworkMvp.Model {

    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun removeNetwork(networkName: String) {
        wiseFy.removeNetwork(networkName, object : RemoveNetworkCallbacks {
            override fun networkRemoved() {
                presenter.displayNetworkRemoved()
            }

            override fun networkNotFoundToRemove() {
                presenter.displayNetworkNotFoundToRemove()
            }

            override fun failureRemovingNetwork() {
                presenter.displayFailureRemovingNetwork()
            }

            override fun wisefyFailure(@WiseFyCode wisefyFailureCode: Int) {
                presenter.displayWiseFyFailure(wisefyFailureCode)
            }
        })
    }
}