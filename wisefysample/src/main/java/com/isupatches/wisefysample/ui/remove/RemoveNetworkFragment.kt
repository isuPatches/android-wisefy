package com.isupatches.wisefysample.ui.remove

import android.Manifest.permission.ACCESS_WIFI_STATE
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View

import com.isupatches.wisefy.constants.WiseFyCode
import com.isupatches.wisefysample.R
import com.isupatches.wisefysample.internal.base.BaseFragment
import com.isupatches.wisefysample.internal.util.displayShortToast
import com.isupatches.wisefysample.internal.util.getTrimmedInput
import com.isupatches.wisefysample.internal.util.hideKeyboardFrom

import kotlinx.android.synthetic.main.fragment_remove.removeNetworkBtn
import kotlinx.android.synthetic.main.fragment_remove.removeNetworkEdt

internal class RemoveNetworkFragment : BaseFragment(), RemoveNetworkMvp.View {

    override val layoutRes = R.layout.fragment_remove

    private val presenter by lazy { RemoveNetworkPresenter(wiseFy) }

    companion object {
        val TAG: String = RemoveNetworkFragment::class.java.simpleName

        fun newInstance() = RemoveNetworkFragment()

        private const val WISEFY_REMOVE_NETWORK_REQUEST_CODE = 1
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        removeNetworkBtn.setOnClickListener {
            hideKeyboardFrom(removeNetworkBtn)
            removeNetwork()
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        presenter.detachView()
        super.onStop()
    }

    /*
     * Presenter callback overrides
     */

    override fun displayNetworkRemoved() {
        displayShortToast("Network removed")
    }

    override fun displayNetworkNotFoundToRemove() {
        displayShortToast("Network not found to remove")
    }

    override fun displayFailureRemovingNetwork() {
        displayShortToast("Failure removing network!")
    }

    override fun displayWiseFyFailure(@WiseFyCode wiseFyFailureCode: Int) {
        displayWiseFyFailureWithCode(wiseFyFailureCode)
    }

    /*
     * WiseFy helpers
     */

    private fun removeNetwork() {
        if (checkRemoveNetworkPermissions()) {
            presenter.removeNetwork(removeNetworkEdt.getTrimmedInput())
        }
    }

    /*
     * Permission helpers
     */

    private fun checkRemoveNetworkPermissions(): Boolean {
        return isPermissionGranted(ACCESS_WIFI_STATE, WISEFY_REMOVE_NETWORK_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            WISEFY_REMOVE_NETWORK_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    removeNetwork()
                } else {
                    Log.w(TAG, "Permissions for remove saved network are denied")
                    // Display permission error here
                }
            }
            else -> {
                // Display permission error here
                Log.wtf(TAG, "Weird permission requested, not handled")
            }
        }
    }
}
