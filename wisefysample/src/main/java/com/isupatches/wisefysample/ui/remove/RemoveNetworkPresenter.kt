package com.isupatches.wisefysample.ui.remove

import android.Manifest.permission.ACCESS_WIFI_STATE
import androidx.annotation.RequiresPermission

import com.isupatches.wisefy.callbacks.RemoveNetworkCallbacks
import com.isupatches.wisefy.constants.WiseFyCode
import com.isupatches.wisefysample.internal.base.BasePresenter

internal class RemoveNetworkPresenter(
    private val model: RemoveNetworkMvp.Model
) : BasePresenter<RemoveNetworkMvp.View>(), RemoveNetworkMvp.Presenter {

    /*
     * Model call-throughs
     */

    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun removeNetwork(networkName: String) {
        model.removeNetwork(networkName, object : RemoveNetworkCallbacks {
            override fun networkRemoved() {
                displayNetworkRemoved()
            }

            override fun networkNotFoundToRemove() {
                displayNetworkNotFoundToRemove()
            }

            override fun failureRemovingNetwork() {
                displayFailureRemovingNetwork()
            }

            override fun wisefyFailure(@WiseFyCode wisefyFailureCode: Int) {
                displayWiseFyFailure(wisefyFailureCode)
            }
        })
    }

    /*
     * View callbacks
     */

    private fun displayNetworkRemoved() {
        doSafelyWithView { view -> view.displayNetworkRemoved() }
    }

    private fun displayNetworkNotFoundToRemove() {
        doSafelyWithView { view -> view.displayNetworkNotFoundToRemove() }
    }

    private fun displayFailureRemovingNetwork() {
        doSafelyWithView { view -> view.displayFailureRemovingNetwork() }
    }
}
