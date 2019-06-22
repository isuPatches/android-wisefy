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
package com.isupatches.wisefysample.ui.misc

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.Manifest.permission.CHANGE_WIFI_STATE
import android.content.pm.PackageManager
import android.net.NetworkInfo
import android.net.wifi.ScanResult
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiInfo
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.VisibleForTesting

import com.isupatches.wisefy.constants.WiseFyCode
import com.isupatches.wisefysample.R
import com.isupatches.wisefysample.internal.base.BaseFragment
import com.isupatches.wisefysample.internal.util.SdkUtil

import dagger.Binds
import dagger.Module

import kotlinx.android.synthetic.main.fragment_misc.enableWifiBtn
import kotlinx.android.synthetic.main.fragment_misc.disableWifiBtn
import kotlinx.android.synthetic.main.fragment_misc.getCurrentNetworkBtn
import kotlinx.android.synthetic.main.fragment_misc.getCurrentNetworkInfoBtn
import kotlinx.android.synthetic.main.fragment_misc.getFrequencyBtn
import kotlinx.android.synthetic.main.fragment_misc.getIPBtn
import kotlinx.android.synthetic.main.fragment_misc.getNearbyAccessPointsBtn
import kotlinx.android.synthetic.main.fragment_misc.getSavedNetworksBtn

import javax.inject.Inject

@Suppress("LargeClass")
internal class MiscFragment : BaseFragment(), MiscMvp.View {

    override val layoutRes = R.layout.fragment_misc

    @Inject lateinit var presenter: MiscMvp.Presenter
    @Inject lateinit var sdkUtil: SdkUtil

    companion object {
        val TAG: String = MiscFragment::class.java.simpleName

        fun newInstance() = MiscFragment()

        @VisibleForTesting internal const val WISEFY_DISABLE_WIFI_REQUEST_CODE = 1
        @VisibleForTesting internal const val WISEFY_ENABLE_WIFI_REQUEST_CODE = 2
        @VisibleForTesting internal const val WISEFY_GET_CURRENT_NETWORK_REQUEST_CODE = 3
        @VisibleForTesting internal const val WISEFY_GET_CURRENT_NETWORK_INFO_REQUEST_CODE = 4
        @VisibleForTesting internal const val WISEFY_GET_FREQUENCY_REQUEST_CODE = 5
        @VisibleForTesting internal const val WISEFY_GET_IP_REQUEST_CODE = 6
        @VisibleForTesting internal const val WISEFY_GET_NEARBY_ACCESS_POINTS_REQUEST_CODE = 7
        @VisibleForTesting internal const val WISEFY_GET_SAVED_NETWORKS_REQUEST_CODE = 8
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        disableWifiBtn.setOnClickListener {
            disableWifi()
        }
        enableWifiBtn.setOnClickListener {
            enableWifi()
        }
        getCurrentNetworkBtn.setOnClickListener {
            getCurrentNetwork()
        }
        getCurrentNetworkInfoBtn.setOnClickListener {
            getCurrentNetworkInfo()
        }
        getFrequencyBtn.setOnClickListener {
            getFrequency()
        }
        getIPBtn.setOnClickListener {
            getIP()
        }
        getNearbyAccessPointsBtn.setOnClickListener {
            getNearbyAccessPoints()
        }
        getSavedNetworksBtn.setOnClickListener {
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

    private fun disableWifi() {
        if (checkDisableWifiPermissions()) {
            presenter.disableWifi()
        }
    }

    private fun enableWifi() {
        if (checkEnableWifiPermissions()) {
            presenter.enableWifi()
        }
    }

    @Throws(SecurityException::class)
    private fun getCurrentNetwork() {
        if (checkGetCurrentNetworkPermissions()) {
            presenter.getCurrentNetwork()
        }
    }

    private fun getCurrentNetworkInfo() {
        if (checkGetCurrentNetworkInfoPermissions()) {
            presenter.getCurrentNetworkInfo()
        }
    }

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

    private fun getSavedNetworks() {
        if (checkGetSavedNetworksPermissions()) {
            presenter.getSavedNetworks()
        }
    }

    /*
     * Permission helpers
     */

    private fun checkDisableWifiPermissions(): Boolean {
        return isPermissionGranted(CHANGE_WIFI_STATE, WISEFY_DISABLE_WIFI_REQUEST_CODE)
    }

    private fun checkEnableWifiPermissions(): Boolean {
        return isPermissionGranted(CHANGE_WIFI_STATE, WISEFY_ENABLE_WIFI_REQUEST_CODE)
    }

    private fun checkGetCurrentNetworkPermissions(): Boolean {
        return isPermissionGranted(ACCESS_WIFI_STATE, WISEFY_GET_CURRENT_NETWORK_REQUEST_CODE) &&
                isPermissionGranted(ACCESS_COARSE_LOCATION, WISEFY_GET_CURRENT_NETWORK_REQUEST_CODE)
    }

    private fun checkGetCurrentNetworkInfoPermissions(): Boolean {
        return isPermissionGranted(ACCESS_NETWORK_STATE, WISEFY_GET_CURRENT_NETWORK_INFO_REQUEST_CODE)
    }

    private fun checkGetFrequencyPermissions(): Boolean {
        return isPermissionGranted(ACCESS_WIFI_STATE, WISEFY_GET_FREQUENCY_REQUEST_CODE) &&
                isPermissionGranted(ACCESS_COARSE_LOCATION, WISEFY_GET_FREQUENCY_REQUEST_CODE)
    }

    private fun checkGetIPPermissions(): Boolean {
        return isPermissionGranted(ACCESS_WIFI_STATE, WISEFY_GET_IP_REQUEST_CODE) &&
                isPermissionGranted(ACCESS_COARSE_LOCATION, WISEFY_GET_IP_REQUEST_CODE)
    }

    private fun checkGetNearbyAccessPointsPermissions(): Boolean {
        return isPermissionGranted(ACCESS_WIFI_STATE, WISEFY_GET_NEARBY_ACCESS_POINTS_REQUEST_CODE) &&
                isPermissionGranted(ACCESS_COARSE_LOCATION, WISEFY_GET_NEARBY_ACCESS_POINTS_REQUEST_CODE)
    }

    private fun checkGetSavedNetworksPermissions(): Boolean {
        return isPermissionGranted(ACCESS_WIFI_STATE, WISEFY_GET_SAVED_NETWORKS_REQUEST_CODE)
    }

    @Suppress("LongMethod", "ComplexMethod")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            WISEFY_DISABLE_WIFI_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    disableWifi()
                } else {
                    Log.w(TAG, "Permissions for disabling wifi are denied")
                    displayPermissionErrorDialog(R.string.permission_error_disable_wifi)
                }
            }
            WISEFY_ENABLE_WIFI_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    enableWifi()
                } else {
                    Log.w(TAG, "Permissions for enabling wifi are denied")
                    displayPermissionErrorDialog(R.string.permission_error_enable_wifi)
                }
            }
            WISEFY_GET_CURRENT_NETWORK_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getCurrentNetwork()
                } else {
                    Log.w(TAG, "Permissions for getting current network are denied")
                    displayPermissionErrorDialog(R.string.permission_error_get_current_network)
                }
            }
            WISEFY_GET_CURRENT_NETWORK_INFO_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getCurrentNetworkInfo()
                } else {
                    Log.w(TAG, "Permissions for getting current network info are denied")
                    displayPermissionErrorDialog(R.string.permission_error_get_current_network_info)
                }
            }
            WISEFY_GET_FREQUENCY_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getFrequency()
                } else {
                    Log.w(TAG, "Permissions for getting frequency are denied")
                    displayPermissionErrorDialog(R.string.permission_error_get_frequency)
                }
            }
            WISEFY_GET_IP_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getIP()
                } else {
                    Log.w(TAG, "Permissions for getting ip are denied")
                    displayPermissionErrorDialog(R.string.permission_error_get_ip)
                }
            }
            WISEFY_GET_NEARBY_ACCESS_POINTS_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getNearbyAccessPoints()
                } else {
                    Log.w(TAG, "Permissions for getting nearby access points are denied")
                    displayPermissionErrorDialog(R.string.permission_error_get_nearby_access_points)
                }
            }
            WISEFY_GET_SAVED_NETWORKS_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getSavedNetworks()
                } else {
                    Log.w(TAG, "Permissions for getting saved networks are denied")
                    displayPermissionErrorDialog(R.string.permission_error_get_saved_networks)
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
    @Module internal interface MiscFragmentModule {
        @Binds fun bindMiscModel(impl: MiscModel): MiscMvp.Model
        @Binds fun bindMiscPresenter(impl: MiscPresenter): MiscMvp.Presenter
    }
}
