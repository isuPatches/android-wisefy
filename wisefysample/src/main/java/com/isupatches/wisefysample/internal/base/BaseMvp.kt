package com.isupatches.wisefysample.internal.base

import com.isupatches.wisefy.constants.WiseFyCode

internal interface BaseMvp {

    interface View {
        fun displayWiseFyFailure(@WiseFyCode wiseFyFailureCode: Int)
    }

    interface Presenter<in V : View> {
        fun attachView(view: V)
        fun detachView()

        fun displayWiseFyFailure(@WiseFyCode wiseFyFailureCode: Int)
    }
}
