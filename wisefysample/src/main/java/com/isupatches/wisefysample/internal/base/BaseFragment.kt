package com.isupatches.wisefysample.internal.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

import com.isupatches.wisefy.WiseFy
import com.isupatches.wisefy.constants.WiseFyCode
import com.isupatches.wisefysample.internal.util.PermissionUtil
import com.isupatches.wisefysample.internal.util.displayShortToast

import dagger.android.support.AndroidSupportInjection

import javax.inject.Inject

internal abstract class BaseFragment : Fragment() {

    @get:LayoutRes abstract val layoutRes: Int

    protected lateinit var wiseFy: WiseFy
    @Inject lateinit var permissionUtil: PermissionUtil

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

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

    protected fun isPermissionGranted(permission: String, requestCode: Int): Boolean {
        return if (permissionUtil.permissionNotGranted(activity!!, permission)) {
            if (shouldShowRequestPermissionRationale(permission)) {
                // Display dialog or rationale for requesting permission here
                requestPermissions(arrayOf(permission), requestCode)
            } else {
                requestPermissions(arrayOf(permission), requestCode)
            }
            false
        } else {
            true
        }
    }
}
