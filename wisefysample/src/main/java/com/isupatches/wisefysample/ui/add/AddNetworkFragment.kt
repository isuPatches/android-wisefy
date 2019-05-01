package com.isupatches.wisefysample.ui.add

import com.isupatches.wisefysample.R
import com.isupatches.wisefysample.ui.base.BaseFragment

class AddNetworkFragment : BaseFragment() {

    override val layoutRes = R.layout.fragment_add

    companion object {
        val TAG: String = AddNetworkFragment::class.java.simpleName

        fun newInstance() = AddNetworkFragment()
    }
}
