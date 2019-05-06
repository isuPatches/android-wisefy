package com.isupatches.wisefysample.ui.main

import com.isupatches.wisefysample.R
import com.isupatches.wisefysample.internal.base.BaseFragment

internal class MainFragment : BaseFragment() {

    override val layoutRes = R.layout.fragment_main

    companion object {
        val TAG: String = MainFragment::class.java.simpleName

        fun newInstance() = MainFragment()
    }
}
