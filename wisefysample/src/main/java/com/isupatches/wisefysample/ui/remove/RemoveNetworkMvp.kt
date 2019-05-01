package com.isupatches.wisefysample.ui.remove

import com.isupatches.wisefysample.ui.base.BaseMvp

interface RemoveNetworkMvp {

    interface View : BaseMvp.View {
        fun displayNetworkRemoved()
        fun displayNetworkNotFoundToRemove()
        fun displayFailureRemovingNetwork()
    }

    interface Presenter : BaseMvp.Presenter<View> {

        /*
         * Model call-throughs
         */
        fun removeNetwork(networkName: String)

        /*
         * View callbacks
         */
        fun displayNetworkRemoved()
        fun displayNetworkNotFoundToRemove()
        fun displayFailureRemovingNetwork()
    }

    interface Model {
        fun removeNetwork(networkName: String)
    }
}