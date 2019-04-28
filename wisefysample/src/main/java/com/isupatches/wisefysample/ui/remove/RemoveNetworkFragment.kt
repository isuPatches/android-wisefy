package com.isupatches.wisefysample.ui.remove

import com.isupatches.wisefysample.R
import com.isupatches.wisefysample.ui.base.BaseFragment

class RemoveNetworkFragment : BaseFragment() {

    override val layoutRes = R.layout.fragment_remove

    companion object {
        val TAG = RemoveNetworkFragment::class.java.simpleName

        fun newInstance() = RemoveNetworkFragment()
    }
}
