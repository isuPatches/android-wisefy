package com.isupatches.wisefysample.ui.remove

import android.Manifest.permission.ACCESS_WIFI_STATE
import androidx.annotation.RequiresPermission

import com.isupatches.wisefy.WiseFy
import com.isupatches.wisefysample.internal.base.BasePresenter

internal class RemoveNetworkPresenter(
    wiseFy: WiseFy
) : BasePresenter<RemoveNetworkMvp.View>(), RemoveNetworkMvp.Presenter {

    private val model = RemoveNetworkModel(this, wiseFy)

    /*
     * Model call-throughs
     */

    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun removeNetwork(networkName: String) {
        model.removeNetwork(networkName)
    }

    /*
     * View callbacks
     */

    override fun displayNetworkRemoved() {
        doSafelyWithView { view -> view.displayNetworkRemoved() }
    }

    override fun displayNetworkNotFoundToRemove() {
        doSafelyWithView { view -> view.displayNetworkNotFoundToRemove() }
    }

    override fun displayFailureRemovingNetwork() {
        doSafelyWithView { view -> view.displayFailureRemovingNetwork() }
    }
}
