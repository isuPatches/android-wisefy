/*
 * Copyright 2021 Patches Klinefelter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.isupatches.android.wisefy.sample.ui.remove

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.CHANGE_WIFI_STATE
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import com.isupatches.android.viewglu.paste
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkResult
import com.isupatches.android.wisefy.sample.R
import com.isupatches.android.wisefy.sample.databinding.FragmentRemoveNetworkBinding
import com.isupatches.android.wisefy.sample.internal.base.BaseFragment
import com.isupatches.android.wisefy.sample.internal.util.getTrimmedInput
import com.isupatches.android.wisefy.sample.internal.util.hideKeyboardFrom
import javax.inject.Inject

@VisibleForTesting internal const val WISEFY_REMOVE_NETWORK_REQUEST_CODE = 1

private const val LOG_TAG = "RemoveNetworkFragment"

internal interface RemoveNetworkView {
    fun displayNetworkRemoved(result: RemoveNetworkResult)
    fun displayFailureRemovingNetwork(result: RemoveNetworkResult)
}

internal class RemoveNetworkFragment : BaseFragment(), RemoveNetworkView {

    @RemoveNetworkScope @Inject lateinit var presenter: RemoveNetworkPresenter
    @RemoveNetworkScope @Inject lateinit var removeNetworkStore: RemoveNetworkStore

    private var binding: FragmentRemoveNetworkBinding by paste()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentRemoveNetworkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            restoreUI()
        }

        binding.removeNetworkBtn.setOnClickListener {
            hideKeyboardFrom(binding.removeNetworkBtn)
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
        removeNetworkStore.setLastUsedRegex(binding.networkNameEdt.getTrimmedInput())
        hideKeyboardFrom(binding.removeNetworkBtn)
    }

    /*
     * View helpers
     */

    private fun restoreUI() {
        // Restore edit text value
        binding.networkNameEdt.setText(removeNetworkStore.getLastUsedRegex())
    }

    /*
     * Presenter callback overrides
     */

    override fun displayNetworkRemoved(result: RemoveNetworkResult) {
        displayInfo(getString(R.string.succeeded_removing_network_args, result), R.string.remove_network_result)
    }

    override fun displayFailureRemovingNetwork(result: RemoveNetworkResult) {
        displayInfo(getString(R.string.failed_removing_network_args, result), R.string.remove_network_result)
    }

    /*
     * WiseFy helpers
     */

    @Throws(SecurityException::class)
    private fun removeNetwork() {
        if (checkRemoveNetworkPermissions()) {
            presenter.removeNetwork(binding.networkNameEdt.getTrimmedInput())
        }
    }

    /*
     * Permission helpers
     */

    private fun checkRemoveNetworkPermissions(): Boolean {
        return isPermissionGranted(ACCESS_FINE_LOCATION, WISEFY_REMOVE_NETWORK_REQUEST_CODE)
            && isPermissionGranted(CHANGE_WIFI_STATE, WISEFY_REMOVE_NETWORK_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            WISEFY_REMOVE_NETWORK_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    removeNetwork()
                } else {
                    Log.w(LOG_TAG, "Permissions for remove saved network are denied")
                    displayPermissionErrorDialog(R.string.permission_error_remove_network)
                }
            }
            else -> {
                Log.wtf(LOG_TAG, "Weird permission requested, not handled")
                displayPermissionErrorDialog(
                    getString(R.string.permission_error_unhandled_request_code_args, requestCode)
                )
            }
        }
    }
}
