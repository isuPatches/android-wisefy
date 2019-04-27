package com.isupatches.wisefysample.ui.misc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.isupatches.wisefysample.R
import com.isupatches.wisefysample.ui.base.BaseFragment

class MiscFragment : BaseFragment() {

    companion object {
        val TAG = MiscFragment::class.java.simpleName

        fun newInstance() = MiscFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_misc, container, false)
    }
}
