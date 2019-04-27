package com.isupatches.wisefysample.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.isupatches.wisefysample.R
import com.isupatches.wisefysample.ui.base.BaseFragment

class SearchFragment : BaseFragment() {

    companion object {
        val TAG = SearchFragment::class.java.simpleName

        fun newInstance() = SearchFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_search, container, false)
    }
}
