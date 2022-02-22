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
package com.isupatches.android.wisefy.sample.ui.add

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.CHANGE_WIFI_STATE
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings.ADD_WIFI_RESULT_SUCCESS
import android.provider.Settings.EXTRA_WIFI_NETWORK_RESULT_LIST
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.VisibleForTesting
import com.isupatches.android.viewglu.paste
import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkResult
import com.isupatches.android.wisefy.sample.R
import com.isupatches.android.wisefy.sample.databinding.FragmentAddNetworkBinding
import com.isupatches.android.wisefy.sample.internal.base.BaseFragment
import com.isupatches.android.wisefy.sample.internal.entities.NetworkType
import com.isupatches.android.wisefy.sample.internal.logging.WisefySampleLogger
import com.isupatches.android.wisefy.sample.internal.util.SdkUtil
import com.isupatches.android.wisefy.sample.internal.util.getTrimmedInput
import com.isupatches.android.wisefy.sample.internal.util.hideKeyboardFrom
import javax.inject.Inject

@VisibleForTesting internal const val WISEFY_ADD_OPEN_NETWORK_REQUEST_CODE = 1
@VisibleForTesting internal const val WISEFY_ADD_WPA2_NETWORK_REQUEST_CODE = 2
@VisibleForTesting internal const val WISEFY_ADD_WPA3_NETWORK_REQUEST_CODE = 3

private const val LOG_TAG = "AddNetworkFragment"

internal interface AddNetworkView {
    fun displayFailureAddingNetwork(result: AddNetworkResult)
    fun displayNetworkAdded(result: AddNetworkResult)
}

internal class AddNetworkFragment : BaseFragment(), AddNetworkView {

    @AddNetworkScope @Inject lateinit var presenter: AddNetworkPresenter
    @AddNetworkScope @Inject lateinit var addNetworkStore: AddNetworkStore
    @AddNetworkScope @Inject lateinit var sdkUtil: SdkUtil

    private var binding: FragmentAddNetworkBinding by paste()
    private lateinit var addNetworkResult: ActivityResultLauncher<Intent>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAddNetworkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addNetworkResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result: ActivityResult ->
            when (result.resultCode) {
                Activity.RESULT_OK -> {
                    val data = result.data ?: return@registerForActivityResult
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                        val networkResultList = data.getIntegerArrayListExtra(EXTRA_WIFI_NETWORK_RESULT_LIST)
                            ?: emptyList()
                        for (resultCode in networkResultList) {
                            if (resultCode == ADD_WIFI_RESULT_SUCCESS) {
                                displayNetworkAdded(AddNetworkResult.Success.ResultCode(resultCode))
                            } else {
                                displayFailureAddingNetwork(AddNetworkResult.Failure.ResultCode(resultCode))
                            }
                        }
                    }
                }
            }
        }
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
                R.id.wpa2NetworkRdb -> addWPA2Network()
                R.id.wpa3NetworkRdb -> addWPA3Network()
            }
        }
        binding.addNetworkTypeRdg.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.openNetworkRdb -> addNetworkStore.setNetworkType(NetworkType.OPEN)
                R.id.wpa2NetworkRdb -> addNetworkStore.setNetworkType(NetworkType.WPA2)
                R.id.wpa3NetworkRdb -> addNetworkStore.setNetworkType(NetworkType.WPA2)
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
            NetworkType.WPA2 -> R.id.wpa2NetworkRdb
            NetworkType.WPA3 -> R.id.wpa3NetworkRdb
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

    override fun displayFailureAddingNetwork(result: AddNetworkResult) {
        displayInfo(getString(R.string.failed_adding_network_args, result), R.string.add_network_result)
    }

    override fun displayNetworkAdded(result: AddNetworkResult) {
        displayInfoFullScreen(
            getString(R.string.succeeded_adding_network_args, result),
            R.string.add_network_result
        )
    }

    override fun displayWisefyAsyncError(throwable: Throwable) {
        displayWisefyAsyncErrorDialog(throwable)
    }

    /*
     * WiseFy helpers
     */

    @Throws(SecurityException::class)
    private fun addOpenNetwork() {
        if (checkAddOpenNetworkPermissions()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                presenter.addOpenNetwork(
                    ssid = binding.networkNameEdt.getTrimmedInput(),
                    activityResultLauncher = addNetworkResult
                )
            } else {
                presenter.addOpenNetwork(ssid = binding.networkNameEdt.getTrimmedInput())
            }
        }
    }

    @Throws(SecurityException::class)
    private fun addWPA2Network() {
        if (checkAddWPA2NetworkPermissions()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                presenter.addWPA2Network(
                    ssid = binding.networkNameEdt.getTrimmedInput(),
                    passphrase = binding.networkPasswordEdt.getTrimmedInput(),
                    activityResultLauncher = addNetworkResult
                )
            } else {
                presenter.addWPA2Network(
                    ssid = binding.networkNameEdt.getTrimmedInput(),
                    passphrase = binding.networkPasswordEdt.getTrimmedInput()
                )
            }
        }
    }

    @Throws(SecurityException::class)
    private fun addWPA3Network() {
        if (sdkUtil.isAtLeastQ()) {
            if (checkAddWPA3NetworkPermissions()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    presenter.addWPA3Network(
                        ssid = binding.networkNameEdt.getTrimmedInput(),
                        passphrase = binding.networkPasswordEdt.getTrimmedInput(),
                        activityResultLauncher = addNetworkResult
                    )
                } else {
                    presenter.addWPA3Network(
                        ssid = binding.networkNameEdt.getTrimmedInput(),
                        passphrase = binding.networkPasswordEdt.getTrimmedInput()
                    )
                }
            }
        } else {
            displayInfo(R.string.add_wpa3_android_q_notice)
        }
    }

    /*
     * Permission helpers
     */

    private fun checkAddOpenNetworkPermissions(): Boolean {
        return isPermissionGranted(ACCESS_FINE_LOCATION, WISEFY_ADD_OPEN_NETWORK_REQUEST_CODE) &&
            isPermissionGranted(CHANGE_WIFI_STATE, WISEFY_ADD_OPEN_NETWORK_REQUEST_CODE)
    }

    private fun checkAddWPA2NetworkPermissions(): Boolean {
        return isPermissionGranted(ACCESS_FINE_LOCATION, WISEFY_ADD_WPA2_NETWORK_REQUEST_CODE) &&
            isPermissionGranted(CHANGE_WIFI_STATE, WISEFY_ADD_WPA2_NETWORK_REQUEST_CODE)
    }

    private fun checkAddWPA3NetworkPermissions(): Boolean {
        return isPermissionGranted(ACCESS_FINE_LOCATION, WISEFY_ADD_WPA3_NETWORK_REQUEST_CODE) &&
            isPermissionGranted(CHANGE_WIFI_STATE, WISEFY_ADD_WPA3_NETWORK_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            WISEFY_ADD_OPEN_NETWORK_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    addOpenNetwork()
                } else {
                    WisefySampleLogger.w(LOG_TAG, "Permissions for adding an open network are denied")
                    displayPermissionErrorDialog(R.string.permission_error_add_open_network)
                }
            }
            WISEFY_ADD_WPA2_NETWORK_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    addWPA2Network()
                } else {
                    WisefySampleLogger.w(LOG_TAG, "Permissions for adding a WPA2 network are denied")
                    displayPermissionErrorDialog(R.string.permission_error_add_wpa2_network)
                }
            }
            WISEFY_ADD_WPA3_NETWORK_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    addWPA3Network()
                } else {
                    WisefySampleLogger.w(LOG_TAG, "Permissions for adding a WPA3 network are denied")
                    displayPermissionErrorDialog(R.string.permission_error_add_wpa3_network)
                }
            }
            else -> {
                WisefySampleLogger.wtf(LOG_TAG, "Weird permission requested, not handled")
                displayPermissionErrorDialog(
                    getString(R.string.permission_error_unhandled_request_code_args, requestCode)
                )
            }
        }
    }
}
