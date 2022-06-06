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
package com.isupatches.android.wisefy.sample.features.misc

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.isupatches.android.viewglu.paste
import com.isupatches.android.wisefy.accesspoints.entities.AccessPointData
import com.isupatches.android.wisefy.frequency.entities.FrequencyData
import com.isupatches.android.wisefy.networkinfo.entities.IPData
import com.isupatches.android.wisefy.networkinfo.entities.NetworkData
import com.isupatches.android.wisefy.networkinfo.entities.NetworkInfoData
import com.isupatches.android.wisefy.sample.R
import com.isupatches.android.wisefy.sample.databinding.FragmentMiscBinding
import com.isupatches.android.wisefy.sample.logging.WisefySampleLogger
import com.isupatches.android.wisefy.sample.scaffolding.BaseFragment
import com.isupatches.android.wisefy.savednetworks.entities.SavedNetworkData
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val LOG_TAG = "MiscFragment"

internal interface MiscView {
    fun displayAndroidQWifiMessage()
    fun displayWifiDisabled()
    fun displayFailureDisablingWifi()
    fun displayWifiEnabled()
    fun displayFailureEnablingWifi()
    fun displayCurrentNetwork(network: NetworkData)
    fun displayNoCurrentNetwork()
    fun displayCurrentNetworkInfo(networkInfo: NetworkInfoData)
    fun displayNoCurrentNetworkInfo()
    fun displayFrequency(frequency: FrequencyData)
    fun displayFailureRetrievingFrequency()
    fun displayIP(ip: IPData)
    fun displayFailureRetrievingIP()
    fun displayNearbyAccessPoints(accessPoints: List<AccessPointData>)
    fun displayNoAccessPointsFound()
    fun displayNoSavedNetworksFound()
    fun displaySavedNetworks(savedNetworks: List<SavedNetworkData>)
}

@Suppress("LargeClass")
@AndroidEntryPoint
internal class MiscFragment : BaseFragment(), MiscView {

    @Inject
    lateinit var presenter: MiscPresenter

    private var binding: FragmentMiscBinding by paste()

    private val getFrequencyPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                @Suppress("MissingPermission")
                presenter.getFrequency()
            } else {
                WisefySampleLogger.w(LOG_TAG, "Permissions for getting frequency are denied")
                displayPermissionErrorDialog(R.string.permission_error_get_frequency)
            }
        }

    private val getIPPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                @Suppress("MissingPermission")
                presenter.getIP()
            } else {
                WisefySampleLogger.w(LOG_TAG, "Permissions for getting ip are denied")
                displayPermissionErrorDialog(R.string.permission_error_get_ip)
            }
        }

    private val getNearbyAccessPointsPermissionsLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                @Suppress("MissingPermission")
                presenter.getNearbyAccessPoints()
            } else {
                WisefySampleLogger.w(LOG_TAG, "Permissions for getting nearby access points are denied")
                displayPermissionErrorDialog(R.string.permission_error_get_nearby_access_points)
            }
        }

    private val getSavedNetworksPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                @Suppress("MissingPermission")
                presenter.getSavedNetworks()
            } else {
                WisefySampleLogger.w(LOG_TAG, "Permissions for getting saved networks are denied")
                displayPermissionErrorDialog(R.string.permission_error_get_saved_networks)
            }
        }

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
            getFrequencyPermissionLauncher.launch(ACCESS_FINE_LOCATION)
        }
        binding.getIPBtn.setOnClickListener {
            getIPPermissionLauncher.launch(ACCESS_FINE_LOCATION)
        }
        binding.getNearbyAccessPointsBtn.setOnClickListener {
            getNearbyAccessPointsPermissionsLauncher.launch(ACCESS_FINE_LOCATION)
        }
        binding.getSavedNetworksBtn.setOnClickListener {
            getSavedNetworksPermissionLauncher.launch(ACCESS_FINE_LOCATION)
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

    override fun displayAndroidQWifiMessage() {
        displayInfo(R.string.android_q_wifi_message, R.string.android_q_notice)
    }

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

    override fun displayCurrentNetwork(network: NetworkData) {
        displayInfoFullScreen(
            getString(R.string.current_network_args, network),
            R.string.wisefy_action_result
        )
    }

    override fun displayNoCurrentNetwork() {
        displayInfo(R.string.no_current_network, R.string.wisefy_action_result)
    }

    override fun displayCurrentNetworkInfo(networkInfo: NetworkInfoData) {
        displayInfoFullScreen(
            getString(R.string.current_network_info_args, networkInfo),
            R.string.wisefy_action_result
        )
    }

    override fun displayNoCurrentNetworkInfo() {
        displayInfo(R.string.no_current_network_info, R.string.wisefy_action_result)
    }

    override fun displayFrequency(frequency: FrequencyData) {
        displayInfo(getString(R.string.frequency_args, frequency.value), R.string.wisefy_action_result)
    }

    override fun displayFailureRetrievingFrequency() {
        displayInfo(R.string.failure_retrieving_frequency, R.string.wisefy_action_result)
    }

    override fun displayIP(ip: IPData) {
        displayInfo(getString(R.string.ip_args, ip.value), R.string.wisefy_action_result)
    }

    override fun displayFailureRetrievingIP() {
        displayInfo(R.string.failure_retrieving_ip, R.string.wisefy_action_result)
    }

    override fun displayNearbyAccessPoints(accessPoints: List<AccessPointData>) {
        displayInfoFullScreen(
            getString(R.string.access_points_args, accessPoints),
            R.string.wisefy_action_result
        )
    }

    override fun displayNoAccessPointsFound() {
        displayInfo(R.string.no_access_points_found, R.string.wisefy_action_result)
    }

    override fun displaySavedNetworks(savedNetworks: List<SavedNetworkData>) {
        displayInfoFullScreen(
            getString(R.string.saved_networks_args, savedNetworks),
            R.string.wisefy_action_result
        )
    }

    override fun displayNoSavedNetworksFound() {
        displayInfo(R.string.no_saved_networks_found, R.string.wisefy_action_result)
    }

    override fun displayWisefyAsyncError(throwable: Throwable) {
        displayWisefyAsyncErrorDialog(throwable)
    }
}
