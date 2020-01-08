/*
 * Copyright 2019 Patches Klinefelter
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
package com.isupatches.wisefysample.ui.remove

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.VisibleForTesting
import com.isupatches.wisefy.constants.WiseFyCode
import com.isupatches.wisefysample.R
import com.isupatches.wisefysample.internal.base.BaseFragment
import com.isupatches.wisefysample.internal.preferences.RemoveNetworkStore
import com.isupatches.wisefysample.internal.util.getTrimmedInput
import com.isupatches.wisefysample.internal.util.hideKeyboardFrom
import dagger.Binds
import dagger.Module
import javax.inject.Inject
import kotlinx.android.synthetic.main.fragment_remove.networkNameEdt
import kotlinx.android.synthetic.main.fragment_remove.removeNetworkBtn

internal class RemoveNetworkFragment : BaseFragment(), RemoveNetworkMvp.View {

    override val layoutRes = R.layout.fragment_remove

    @Inject lateinit var presenter: RemoveNetworkMvp.Presenter
    @Inject lateinit var removeNetworkStore: RemoveNetworkStore

    companion object {
        val TAG: String = RemoveNetworkFragment::class.java.simpleName

        fun newInstance() = RemoveNetworkFragment()

        @VisibleForTesting internal const val WISEFY_REMOVE_NETWORK_REQUEST_CODE = 1
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            restoreUI()
        }

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
        removeNetworkStore.setLastUsedRegex(networkNameEdt.getTrimmedInput())
        hideKeyboardFrom(removeNetworkBtn)
    }

    /*
     * View helpers
     */

    private fun restoreUI() {
        // Restore edit text value
        networkNameEdt.setText(removeNetworkStore.getLastUsedRegex())
    }

    /*
     * Presenter callback overrides
     */

    override fun displayNetworkRemoved() {
        displayInfo(R.string.network_removed, R.string.remover_network_result)
    }

    override fun displayNetworkNotFoundToRemove() {
        displayInfo(R.string.network_not_found_to_remove, R.string.remover_network_result)
    }

    override fun displayFailureRemovingNetwork() {
        displayInfo(R.string.failure_removing_network, R.string.remover_network_result)
    }

    override fun displayWiseFyFailure(@WiseFyCode wiseFyFailureCode: Int) {
        displayWiseFyFailureWithCode(wiseFyFailureCode)
    }

    /*
     * WiseFy helpers
     */

    @Throws(SecurityException::class)
    private fun removeNetwork() {
        if (checkRemoveNetworkPermissions()) {
            presenter.removeNetwork(networkNameEdt.getTrimmedInput())
        }
    }

    /*
     * Permission helpers
     */

    private fun checkRemoveNetworkPermissions(): Boolean {
        return isPermissionGranted(ACCESS_FINE_LOCATION, WISEFY_REMOVE_NETWORK_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            WISEFY_REMOVE_NETWORK_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    removeNetwork()
                } else {
                    Log.w(TAG, "Permissions for remove saved network are denied")
                    displayPermissionErrorDialog(R.string.permission_error_remove_network)
                }
            }
            else -> {
                Log.wtf(TAG, "Weird permission requested, not handled")
                displayPermissionErrorDialog(
                    getString(R.string.permission_error_unhandled_request_code_args, requestCode)
                )
            }
        }
    }

    /*
     * Dagger
     */

    @Suppress("unused")
    @Module internal interface RemoveNetworkFragmentModule {
        @Binds fun bindRemoveNetworkModel(impl: RemoveNetworkModel): RemoveNetworkMvp.Model
        @Binds fun bindRemoveNetworkPresenter(impl: RemoveNetworkPresenter): RemoveNetworkMvp.Presenter
    }
}
