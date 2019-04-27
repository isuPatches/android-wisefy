package com.isupatches.wisefysample.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment

import com.isupatches.wisefy.WiseFy

open class BaseFragment : Fragment() {

    protected var wiseFy: WiseFy? = null
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        wiseFy = WiseFy.Brains(activity!!).logging(true).getSmarts()
    }

    override fun onDestroy() {
        super.onDestroy()
        wiseFy?.dump()
    }
}
