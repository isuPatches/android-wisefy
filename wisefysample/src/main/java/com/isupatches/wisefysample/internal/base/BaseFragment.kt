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
import com.isupatches.wisefysample.ui.dialogs.ErrorDialogFragment

import dagger.android.support.AndroidSupportInjection

import javax.inject.Inject

internal abstract class BaseFragment : Fragment() {

    @get:LayoutRes abstract val layoutRes: Int

    @Inject lateinit var permissionUtil: PermissionUtil

    private fun isActivityInvalid(): Boolean = activity == null || activity!!.isFinishing

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
        showDialogNoDuplicates(
            tag = ErrorDialogFragment.TAG,
            dialog = ErrorDialogFragment.newInstance(
                title = getString(R.string.permission_error),
                message = getString(permissionErrorMessageResId)
            )
        )
    }

    protected fun displayPermissionErrorDialog(permissionErrorMessage: String) {
        showDialogNoDuplicates(
            tag = ErrorDialogFragment.TAG,
            dialog = ErrorDialogFragment.newInstance(
                title = getString(R.string.permission_error),
                message = permissionErrorMessage
            )
        )
    }

    private fun <T : BaseDialogFragment> showDialogNoDuplicates(tag: String, dialog: T) {
        if (isActivityInvalid()) return
        dialog.showNoDuplicates(childFragmentManager, tag)
    }

    protected fun displayWiseFyFailureWithCode(@WiseFyCode wiseFyFailureCode: Int) {
        showDialogNoDuplicates(
            tag = ErrorDialogFragment.TAG,
            dialog = ErrorDialogFragment.newInstance(
                title = getString(R.string.wisefy_error),
                message = getString(R.string.wisefy_error_descriptions_args, wiseFyFailureCode)
            )
        )
    }

    protected fun isPermissionGranted(permission: String, requestCode: Int): Boolean {
        return if (!permissionUtil.isPermissionGranted(activity!!, permission)) {
            if (permissionUtil.shouldShowRequestPermissionRationale(this, permission)) {
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
