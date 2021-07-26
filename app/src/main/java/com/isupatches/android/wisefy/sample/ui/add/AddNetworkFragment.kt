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
package com.isupatches.android.wisefy.sample.ui.add

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager
import android.net.wifi.WifiConfiguration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import com.isupatches.android.viewglu.paste
import com.isupatches.android.wisefy.sample.databinding.FragmentAddNetworkBinding
import com.isupatches.wisefy.constants.WiseFyCode
import com.isupatches.android.wisefy.sample.R
import com.isupatches.android.wisefy.sample.internal.base.BaseFragment
import com.isupatches.android.wisefy.sample.internal.entities.NetworkType
import com.isupatches.android.wisefy.sample.internal.util.getTrimmedInput
import com.isupatches.android.wisefy.sample.internal.util.hideKeyboardFrom
import javax.inject.Inject

@VisibleForTesting internal const val WISEFY_ADD_OPEN_NETWORK_REQUEST_CODE = 1
@VisibleForTesting internal const val WISEFY_ADD_WEP_NETWORK_REQUEST_CODE = 2
@VisibleForTesting internal const val WISEFY_ADD_WPA2_NETWORK_REQUEST_CODE = 3

private const val LOG_TAG = "AddNetworkFragment"

internal interface AddNetworkView {
    fun displayFailureAddingNetwork(wifiManagerReturn: Int)
    fun displayNetworkAdded(newNetworkId: Int, networkConfig: WifiConfiguration)
}

internal class AddNetworkFragment : BaseFragment(), AddNetworkView {

    @AddNetworkScope @Inject lateinit var presenter: AddNetworkPresenter
    @AddNetworkScope @Inject lateinit var addNetworkStore: AddNetworkStore

    private var binding: FragmentAddNetworkBinding by paste()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAddNetworkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        presenter.detachView()
        super.onStop()
        addNetworkStore.setLastUsedNetworkName(binding.networkNameEdt.getTrimmedInput())
        addNetworkStore.setLastUsedNetworkPassword(binding.networkPasswordEdt.getTrimmedInput())
        hideKeyboardFrom(binding.addNetworkBtn)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            restoreUI()
        }

        binding.addNetworkBtn.setOnClickListener {
            when (binding.addNetworkTypeRdg.checkedRadioButtonId) {
                R.id.openNetworkRdb -> addOpenNetwork()
                R.id.wepNetworkRdb -> addWEPNetwork()
                R.id.wpa2NetworkRdb -> addWPA2Network()
            }
        }
        binding.addNetworkTypeRdg.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.openNetworkRdb -> addNetworkStore.setNetworkType(NetworkType.OPEN)
                R.id.wepNetworkRdb -> addNetworkStore.setNetworkType(NetworkType.WEP)
                R.id.wpa2NetworkRdb -> addNetworkStore.setNetworkType(NetworkType.WPA2)
            }
            updateUI()
        }
    }

    /*
     * View helpers
     */

    private fun adjustNetworkPasswordVisibility(visibility: Int) {
        binding.networkPasswordTil.visibility = visibility
        binding.networkPasswordEdt.visibility = visibility
    }

    private fun restoreUI() {
        // Restore edit text values
        binding.networkNameEdt.setText(addNetworkStore.getLastUsedNetworkName())
        binding.networkPasswordEdt.setText(addNetworkStore.getLastUsedNetworkPassword())

        // Restore checked state
        val checkedId = when (addNetworkStore.getNetworkType()) {
            NetworkType.OPEN -> R.id.openNetworkRdb
            NetworkType.WEP -> R.id.wepNetworkRdb
            NetworkType.WPA2 -> R.id.wpa2NetworkRdb
        }
        binding.addNetworkTypeRdg.check(checkedId)

        // Show/hide password edit
        when (addNetworkStore.getNetworkType()) {
            NetworkType.OPEN -> adjustNetworkPasswordVisibility(View.INVISIBLE)
            else -> adjustNetworkPasswordVisibility(View.VISIBLE)
        }
    }

    private fun updateUI() {
        when (binding.addNetworkTypeRdg.checkedRadioButtonId) {
            R.id.openNetworkRdb -> adjustNetworkPasswordVisibility(View.INVISIBLE)
            else -> adjustNetworkPasswordVisibility(View.VISIBLE)
        }
    }

    /*
     * Presenter callback overrides
     */

    override fun displayFailureAddingNetwork(wifiManagerReturn: Int) {
        displayInfo(getString(R.string.failure_adding_network, wifiManagerReturn), R.string.add_network_result)
    }

    override fun displayNetworkAdded(newNetworkId: Int, networkConfig: WifiConfiguration) {
        displayInfoFullScreen(
            getString(R.string.network_added, newNetworkId, networkConfig),
            R.string.add_network_result
        )
    }

    override fun displayWiseFyFailure(@WiseFyCode wiseFyFailureCode: Int) {
        displayWiseFyFailureWithCode(wiseFyFailureCode)
    }

    /*
     * WiseFy helpers
     */

    @Throws(SecurityException::class)
    private fun addOpenNetwork() {
        if (checkAddOpenNetworkPermissions()) {
            presenter.addOpenNetwork(binding.networkNameEdt.getTrimmedInput())
        }
    }

    @Throws(SecurityException::class)
    private fun addWEPNetwork() {
        if (checkAddWEPNetworkPermissions()) {
            presenter.addWEPNetwork(
                ssid = binding.networkNameEdt.getTrimmedInput(),
                password = binding.networkPasswordEdt.getTrimmedInput()
            )
        }
    }

    @Throws(SecurityException::class)
    private fun addWPA2Network() {
        if (checkAddWPA2NetworkPermissions()) {
            presenter.addWPA2Network(
                ssid = binding.networkNameEdt.getTrimmedInput(),
                password = binding.networkPasswordEdt.getTrimmedInput()
            )
        }
    }

    /*
     * Permission helpers
     */

    private fun checkAddOpenNetworkPermissions(): Boolean {
        return isPermissionGranted(ACCESS_FINE_LOCATION, WISEFY_ADD_OPEN_NETWORK_REQUEST_CODE)
    }

    private fun checkAddWEPNetworkPermissions(): Boolean {
        return isPermissionGranted(ACCESS_FINE_LOCATION, WISEFY_ADD_WEP_NETWORK_REQUEST_CODE)
    }

    private fun checkAddWPA2NetworkPermissions(): Boolean {
        return isPermissionGranted(ACCESS_FINE_LOCATION, WISEFY_ADD_WPA2_NETWORK_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            WISEFY_ADD_OPEN_NETWORK_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    addOpenNetwork()
                } else {
                    Log.w(LOG_TAG, "Permissions for adding an open network are denied")
                    displayPermissionErrorDialog(R.string.permission_error_add_open_network)
                }
            }
            WISEFY_ADD_WEP_NETWORK_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    addWEPNetwork()
                } else {
                    Log.w(LOG_TAG, "Permissions for adding a WEP network are denied")
                    displayPermissionErrorDialog(R.string.permission_error_add_wep_network)
                }
            }
            WISEFY_ADD_WPA2_NETWORK_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    addWPA2Network()
                } else {
                    Log.w(LOG_TAG, "Permissions for adding a WPA2 network are denied")
                    displayPermissionErrorDialog(R.string.permission_error_add_wpa2_network)
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
