package com.isupatches.wisefysample.ui.remove

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.isupatches.wisefysample.R
import com.isupatches.wisefysample.ui.base.BaseFragment

class RemoveNetworkFragment : BaseFragment() {

    companion object {
        val TAG = RemoveNetworkFragment::class.java.simpleName

        fun newInstance() = RemoveNetworkFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_remove, container, false)
    }
}
