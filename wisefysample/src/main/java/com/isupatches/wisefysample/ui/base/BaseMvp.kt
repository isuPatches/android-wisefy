package com.isupatches.wisefysample.ui.base

import com.isupatches.wisefy.constants.WiseFyCode

interface BaseMvp {

    interface View {
        fun displayWiseFyFailure(@WiseFyCode wiseFyFailureCode: Int)
    }

    interface Presenter<in V : View> {
        fun attachView(view: V)
        fun detachView()

        fun displayWiseFyFailure(@WiseFyCode wiseFyFailureCode: Int)
    }
}
