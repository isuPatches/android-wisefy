/*
 * Copyright 2022 Patches Klinefelter
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
package com.isupatches.android.wisefy.sample.features.remove

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.CHANGE_WIFI_STATE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.isupatches.android.viewglu.paste
import com.isupatches.android.wisefy.removenetwork.entities.RemoveNetworkResult
import com.isupatches.android.wisefy.sample.R
import com.isupatches.android.wisefy.sample.databinding.FragmentRemoveNetworkBinding
import com.isupatches.android.wisefy.sample.logging.WisefySampleLogger
import com.isupatches.android.wisefy.sample.scaffolding.BaseFragment
import com.isupatches.android.wisefy.sample.util.getTrimmedInput
import com.isupatches.android.wisefy.sample.util.hideKeyboardFrom
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val LOG_TAG = "RemoveNetworkFragment"

internal interface RemoveNetworkView {
    fun displayFailureRemovingNetwork(result: RemoveNetworkResult)
    fun displayNetworkNotFountToRemove()
    fun displayNetworkRemoved(result: RemoveNetworkResult)
}

@AndroidEntryPoint
internal class RemoveNetworkFragment : BaseFragment(), RemoveNetworkView {

    @Inject
    lateinit var presenter: RemoveNetworkPresenter

    @Inject
    lateinit var removeNetworkStore: RemoveNetworkStore

    private var binding: FragmentRemoveNetworkBinding by paste()

    private val removeNetworkPermissionsLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { results ->
            if (results.all { it.value }) {
                @Suppress("MissingPermission")
                presenter.removeNetwork(binding.networkNameEdt.getTrimmedInput())
            } else {
                WisefySampleLogger.w(LOG_TAG, "Permissions for remove saved network are denied")
                displayPermissionErrorDialog(R.string.permission_error_remove_network)
            }
        }

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
            removeNetworkPermissionsLauncher.launch(arrayOf(ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE))
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

    override fun displayNetworkNotFountToRemove() {
        displayInfo(getString(R.string.network_not_fount_to_remove), R.string.remove_network_result)
    }

    override fun displayFailureRemovingNetwork(result: RemoveNetworkResult) {
        displayInfo(getString(R.string.failed_removing_network_args, result), R.string.remove_network_result)
    }

    override fun displayWisefyAsyncError(throwable: Throwable) {
        displayWisefyAsyncErrorDialog(throwable)
    }
}
