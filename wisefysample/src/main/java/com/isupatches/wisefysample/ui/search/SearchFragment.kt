package com.isupatches.wisefysample.ui.search

import com.isupatches.wisefysample.R
import com.isupatches.wisefysample.ui.base.BaseFragment

class SearchFragment : BaseFragment() {

    override val layoutRes = R.layout.fragment_search

    companion object {
        val TAG = SearchFragment::class.java.simpleName

        fun newInstance() = SearchFragment()
    }
}
