package com.isupatches.wisefysample.ui.remove

import com.isupatches.wisefy.callbacks.RemoveNetworkCallbacks
import com.isupatches.wisefysample.internal.base.BaseMvp

internal interface RemoveNetworkMvp {

    interface View : BaseMvp.View {
        fun displayNetworkRemoved()
        fun displayNetworkNotFoundToRemove()
        fun displayFailureRemovingNetwork()
    }

    interface Presenter : BaseMvp.Presenter<View> {
        fun removeNetwork(networkName: String)
    }

    interface Model {
        fun removeNetwork(networkName: String, callbacks: RemoveNetworkCallbacks)
    }
}