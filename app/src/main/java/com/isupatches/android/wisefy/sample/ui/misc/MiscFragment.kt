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
package com.isupatches.android.wisefy.sample.ui.misc

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager
import android.net.NetworkInfo
import android.net.wifi.ScanResult
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiInfo
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import com.isupatches.android.viewglu.paste
import com.isupatches.wisefy.constants.WiseFyCode
import com.isupatches.android.wisefy.sample.R
import com.isupatches.android.wisefy.sample.databinding.FragmentMiscBinding
import com.isupatches.android.wisefy.sample.internal.base.BaseFragment
import com.isupatches.android.wisefy.sample.internal.util.SdkUtil
import javax.inject.Inject

@VisibleForTesting internal const val WISEFY_GET_FREQUENCY_REQUEST_CODE = 1
@VisibleForTesting internal const val WISEFY_GET_IP_REQUEST_CODE = 2
@VisibleForTesting internal const val WISEFY_GET_NEARBY_ACCESS_POINTS_REQUEST_CODE = 3
@VisibleForTesting internal const val WISEFY_GET_SAVED_NETWORKS_REQUEST_CODE = 4

private const val LOG_TAG = "MiscFragment"

internal interface MiscView {
    fun displayWifiDisabled()
    fun displayFailureDisablingWifi()
    fun displayWifiEnabled()
    fun displayFailureEnablingWifi()
    fun displayCurrentNetwork(currentNetwork: WifiInfo)
    fun displayNoCurrentNetwork()
    fun displayCurrentNetworkInfo(currentNetworkInfo: NetworkInfo)
    fun displayNoCurrentNetworkInfo()
    fun displayFrequency(frequency: Int)
    fun displayFailureRetrievingFrequency()
    fun displayIP(ip: String)
    fun displayFailureRetrievingIP()
    fun displayNearbyAccessPoints(accessPoints: List<ScanResult>)
    fun displayNoAccessPointsFound()
    fun displayNoSavedNetworksFound()
    fun displaySavedNetworks(savedNetworks: List<WifiConfiguration>)
}

@Suppress("LargeClass")
internal class MiscFragment : BaseFragment(), MiscView {

    @MiscScope @Inject lateinit var presenter: MiscPresenter
    @MiscScope @Inject lateinit var sdkUtil: SdkUtil

    private var binding: FragmentMiscBinding by paste()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMiscBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.disableWifiBtn.setOnClickListener {
            presenter.disableWifi()
        }
        binding.enableWifiBtn.setOnClickListener {
            presenter.enableWifi()
        }
        binding.getCurrentNetworkBtn.setOnClickListener {
            presenter.getCurrentNetwork()
        }
        binding.getCurrentNetworkInfoBtn.setOnClickListener {
            presenter.getCurrentNetworkInfo()
        }
        binding.getFrequencyBtn.setOnClickListener {
            getFrequency()
        }
        binding.getIPBtn.setOnClickListener {
            getIP()
        }
        binding.getNearbyAccessPointsBtn.setOnClickListener {
            getNearbyAccessPoints()
        }
        binding.getSavedNetworksBtn.setOnClickListener {
            getSavedNetworks()
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
     * Presenter overrides
     */

    override fun displayWifiDisabled() {
        displayInfo(R.string.wifi_disabled, R.string.wisefy_action_result)
    }

    override fun displayFailureDisablingWifi() {
        displayInfo(R.string.failure_disabling_wifi, R.string.wisefy_action_result)
    }

    override fun displayWifiEnabled() {
        displayInfo(R.string.wifi_enabled, R.string.wisefy_action_result)
    }

    override fun displayFailureEnablingWifi() {
        displayInfo(R.string.failure_enabling_wifi, R.string.wisefy_action_result)
    }

    override fun displayCurrentNetwork(currentNetwork: WifiInfo) {
        displayInfoFullScreen(
            getString(R.string.current_network_args, currentNetwork),
            R.string.wisefy_action_result
        )
    }

    override fun displayNoCurrentNetwork() {
        displayInfo(R.string.no_current_network, R.string.wisefy_action_result)
    }

    override fun displayCurrentNetworkInfo(currentNetworkInfo: NetworkInfo) {
        displayInfoFullScreen(
            getString(R.string.current_network_info_args, currentNetworkInfo),
            R.string.wisefy_action_result
        )
    }

    override fun displayNoCurrentNetworkInfo() {
        displayInfo(R.string.no_current_network_info, R.string.wisefy_action_result)
    }

    override fun displayFrequency(frequency: Int) {
        displayInfo(getString(R.string.frequency_args, frequency), R.string.wisefy_action_result)
    }

    override fun displayFailureRetrievingFrequency() {
        displayInfo(R.string.failure_retrieving_frequency, R.string.wisefy_action_result)
    }

    override fun displayIP(ip: String) {
        displayInfo(getString(R.string.ip_args, ip), R.string.wisefy_action_result)
    }

    override fun displayFailureRetrievingIP() {
        displayInfo(R.string.failure_retrieving_ip, R.string.wisefy_action_result)
    }

    override fun displayNearbyAccessPoints(accessPoints: List<ScanResult>) {
        displayInfoFullScreen(
            getString(R.string.access_points_args, accessPoints),
            R.string.wisefy_action_result
        )
    }

    override fun displayNoAccessPointsFound() {
        displayInfo(R.string.no_access_points_found, R.string.wisefy_action_result)
    }

    override fun displaySavedNetworks(savedNetworks: List<WifiConfiguration>) {
        displayInfoFullScreen(
            getString(R.string.saved_networks_args, savedNetworks),
            R.string.wisefy_action_result
        )
    }

    override fun displayNoSavedNetworksFound() {
        displayInfo(R.string.no_saved_networks_found, R.string.wisefy_action_result)
    }

    override fun displayWiseFyFailure(@WiseFyCode wiseFyFailureCode: Int) {
        displayWiseFyFailureWithCode(wiseFyFailureCode)
    }

    /*
     * WiseFy helpers
     */

    @Throws(SecurityException::class)
    private fun getFrequency() {
        if (sdkUtil.isAtLeastLollipop()) {
            if (checkGetFrequencyPermissions()) {
                presenter.getFrequency()
            }
        } else {
            displayInfo(R.string.frequency_lollipop_notice)
        }
    }

    @Throws(SecurityException::class)
    private fun getIP() {
        if (checkGetIPPermissions()) {
            presenter.getIP()
        }
    }

    @Throws(SecurityException::class)
    private fun getNearbyAccessPoints() {
        if (checkGetNearbyAccessPointsPermissions()) {
            presenter.getNearbyAccessPoints()
        }
    }

    @Throws(SecurityException::class)
    private fun getSavedNetworks() {
        if (checkGetSavedNetworksPermissions()) {
            presenter.getSavedNetworks()
        }
    }

    /*
     * Permission helpers
     */

    private fun checkGetFrequencyPermissions(): Boolean {
        return isPermissionGranted(ACCESS_FINE_LOCATION, WISEFY_GET_FREQUENCY_REQUEST_CODE)
    }

    private fun checkGetIPPermissions(): Boolean {
        return isPermissionGranted(ACCESS_FINE_LOCATION, WISEFY_GET_IP_REQUEST_CODE)
    }

    private fun checkGetNearbyAccessPointsPermissions(): Boolean {
        return isPermissionGranted(ACCESS_FINE_LOCATION, WISEFY_GET_NEARBY_ACCESS_POINTS_REQUEST_CODE)
    }

    private fun checkGetSavedNetworksPermissions(): Boolean {
        return isPermissionGranted(ACCESS_FINE_LOCATION, WISEFY_GET_SAVED_NETWORKS_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            WISEFY_GET_FREQUENCY_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getFrequency()
                } else {
                    Log.w(LOG_TAG, "Permissions for getting frequency are denied")
                    displayPermissionErrorDialog(R.string.permission_error_get_frequency)
                }
            }
            WISEFY_GET_IP_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getIP()
                } else {
                    Log.w(LOG_TAG, "Permissions for getting ip are denied")
                    displayPermissionErrorDialog(R.string.permission_error_get_ip)
                }
            }
            WISEFY_GET_NEARBY_ACCESS_POINTS_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getNearbyAccessPoints()
                } else {
                    Log.w(LOG_TAG, "Permissions for getting nearby access points are denied")
                    displayPermissionErrorDialog(R.string.permission_error_get_nearby_access_points)
                }
            }
            WISEFY_GET_SAVED_NETWORKS_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getSavedNetworks()
                } else {
                    Log.w(LOG_TAG, "Permissions for getting saved networks are denied")
                    displayPermissionErrorDialog(R.string.permission_error_get_saved_networks)
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
