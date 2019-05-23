package com.isupatches.wisefysample.internal.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment

import com.isupatches.wisefy.constants.WiseFyCode
import com.isupatches.wisefysample.R
import com.isupatches.wisefysample.internal.util.PermissionUtil
import com.isupatches.wisefysample.internal.util.displayShortToast

import dagger.android.support.AndroidSupportInjection

import javax.inject.Inject

internal abstract class BaseFragment : Fragment() {

    @get:LayoutRes abstract val layoutRes: Int

    @Inject lateinit var permissionUtil: PermissionUtil

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutRes, container, false)
    }

    protected fun displayInfo(@StringRes infoMessageResId: Int) {
        activity?.let {
            AlertDialog.Builder(it)
                .setTitle(R.string.info)
                .setMessage(infoMessageResId)
                .setPositiveButton(R.string.ok) { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }

    protected fun displayPermissionErrorDialog(@StringRes permissionErrorMessageResId: Int) {
        activity?.let {
            AlertDialog.Builder(it)
                .setTitle(R.string.permission_error)
                .setMessage(permissionErrorMessageResId)
                .setPositiveButton(R.string.ok) { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
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
