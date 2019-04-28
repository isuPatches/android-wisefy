package com.isupatches.wisefysample.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

import com.isupatches.wisefy.WiseFy
import com.isupatches.wisefy.constants.WiseFyCode
import com.isupatches.wisefysample.util.PermissionsUtil
import com.isupatches.wisefysample.util.displayShortToast

abstract class BaseFragment : Fragment() {

    @get:LayoutRes abstract val layoutRes: Int

    protected lateinit var wiseFy: WiseFy
    protected val permissionUtil = PermissionsUtil.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        wiseFy = WiseFy.Brains(activity!!).logging(true).getSmarts()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutRes, container, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        wiseFy.dump()
    }

    protected fun displayWiseFyFailureWithCode(@WiseFyCode wiseFyFailureCode: Int) {
        displayShortToast("WiseFy Failure. Code: $wiseFyFailureCode")
    }
}
