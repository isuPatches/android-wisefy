package com.isupatches.wisefysample.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.isupatches.wisefysample.R
import com.isupatches.wisefysample.ui.base.BaseFragment

class AddNetworkFragment : BaseFragment() {

    companion object {
        val TAG = AddNetworkFragment::class.java.simpleName

        fun newInstance() = AddNetworkFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_add, container, false)
    }
}
