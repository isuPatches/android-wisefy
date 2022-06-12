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
package com.isupatches.android.wisefy.sample.features.search

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.isupatches.android.viewglu.paste
import com.isupatches.android.wisefy.accesspoints.entities.AccessPointData
import com.isupatches.android.wisefy.accesspoints.entities.SSIDData
import com.isupatches.android.wisefy.sample.R
import com.isupatches.android.wisefy.sample.databinding.FragmentSearchBinding
import com.isupatches.android.wisefy.sample.entities.SearchType
import com.isupatches.android.wisefy.sample.logging.WisefySampleLogger
import com.isupatches.android.wisefy.sample.scaffolding.BaseFragment
import com.isupatches.android.wisefy.sample.ui.theme.WisefySampleTheme
import com.isupatches.android.wisefy.sample.util.asHtmlSpanned
import com.isupatches.android.wisefy.sample.util.getTrimmedInput
import com.isupatches.android.wisefy.sample.util.hideKeyboardFrom
import com.isupatches.android.wisefy.savednetworks.entities.SavedNetworkData
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.math.max

private const val TIMEOUT_MIN = 1
private const val TIMEOUT_MAX = 60

private const val SEEK_MILLI_OFFSET = 1000

private const val LOG_TAG = "SearchFragment"

internal interface SearchView {
    fun displaySavedNetwork(savedNetwork: SavedNetworkData?)
    fun displaySavedNetworkNotFound()
    fun displaySavedNetworks(savedNetworks: List<SavedNetworkData>)
    fun displayNoSavedNetworksFound()
    fun displayAccessPoint(accessPoint: AccessPointData?)
    fun displayAccessPointNotFound()
    fun displayAccessPoints(accessPoints: List<AccessPointData>)
    fun displayNoAccessPointsFound()
    fun displaySSID(ssid: SSIDData?)
    fun displaySSIDNotFound()
    fun displaySSIDs(ssids: List<SSIDData>)
    fun displayNoSSIDsFound()
}

@Suppress("LargeClass")
@AndroidEntryPoint
internal class SearchFragment : BaseFragment(), SearchView {

    @Inject
    lateinit var presenter: SearchPresenter

    @Inject
    lateinit var searchStore: SearchStore

    private var binding: FragmentSearchBinding by paste()

    private val searchForSavedNetworkPermissionsLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                @Suppress("MissingPermission")
                presenter.searchForSavedNetwork(binding.searchRegexEdt.getTrimmedInput())
            } else {
                WisefySampleLogger.w(LOG_TAG, "Permissions for getting a saved network are denied")
                displayPermissionErrorDialog(R.string.permission_error_search_for_saved_network)
            }
        }

    private val searchForSavedNetworksPermissionsLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                @Suppress("MissingPermission")
                presenter.searchForSavedNetworks(binding.searchRegexEdt.getTrimmedInput())
            } else {
                WisefySampleLogger.w(LOG_TAG, "Permissions for getting saved networks are denied")
                displayPermissionErrorDialog(R.string.permission_error_search_for_saved_networks)
            }
        }

    private val searchForAccessPointPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { results ->
            if (results.all { it.value }) {
                @Suppress("MissingPermission")
                presenter.searchForAccessPoint(
                    binding.searchRegexEdt.getTrimmedInput(),
                    getSelectedTimeout(),
                    getFilterDuplicates()
                )
            } else {
                WisefySampleLogger.w(LOG_TAG, "Permissions for searching for an access point are denied")
                displayPermissionErrorDialog(R.string.permission_error_search_for_access_point)
            }
        }

    private val searchForAccessPointsPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { results ->
            if (results.all { it.value }) {
                @Suppress("MissingPermission")
                presenter.searchForAccessPoints(binding.searchRegexEdt.getTrimmedInput(), getFilterDuplicates())
            } else {
                WisefySampleLogger.w(LOG_TAG, "Permissions for searching for access points are denied")
                displayPermissionErrorDialog(R.string.permission_error_search_for_access_points)
            }
        }

    private val searchForSSIDPermissionsLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                @Suppress("MissingPermission")
                presenter.searchForSSID(binding.searchRegexEdt.getTrimmedInput(), getSelectedTimeout())
            } else {
                WisefySampleLogger.w(LOG_TAG, "Permissions for searching for an SSID are denied")
                displayPermissionErrorDialog(R.string.permission_error_search_for_ssid)
            }
        }

    private val searchForSSIDsPermissionsLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                @Suppress("MissingPermission")
                presenter.searchForSSIDs(binding.searchRegexEdt.getTrimmedInput())
            } else {
                WisefySampleLogger.w(LOG_TAG, "Permissions for searching for SSIDs are denied")
                displayPermissionErrorDialog(R.string.permission_error_search_for_ssids)
            }
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            restoreUI()
        }

        binding.searchTypeRdg.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.accessPointRdb -> searchStore.setSearchType(SearchType.ACCESS_POINT)
                R.id.ssidRdb -> searchStore.setSearchType(SearchType.SSID)
                R.id.savedNetworkRdb -> searchStore.setSearchType(SearchType.SAVED_NETWORK)
            }
            updateUI()
        }
        binding.filterDupesRdg.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.yesFilterDupesRdb -> searchStore.setFilterDuplicates(true)
                else -> searchStore.setFilterDuplicates(false)
            }
        }
        binding.returnFullListRdg.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.yesFullListRdb -> searchStore.setReturnFullList(true)
                else -> searchStore.setReturnFullList(false)
            }
            updateUI()
        }
        binding.searchBtn.setOnClickListener { search() }

        with(binding.timeoutSeek) {
            max = TIMEOUT_MAX
            setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    val timeout = max(TIMEOUT_MIN, progress)
                    searchStore.setTimeout(timeout)
                    @Suppress("SyntheticAccessor")
                    binding.timeoutTxt.text = getString(
                        R.string.timeout_after_x_seconds_args_html,
                        timeout
                    ).asHtmlSpanned()
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                    // No-op
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    // No-op
                }
            })
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        presenter.detachView()
        super.onStop()
        searchStore.setLastUsedRegex(binding.searchRegexEdt.getTrimmedInput())
        hideKeyboardFrom(binding.searchBtn)
    }

    /*
     * View helpers
     */

    private fun adjustFilterDupesVisibility(visibility: Int) {
        binding.filterDupesTxt.visibility = visibility
        binding.filterDupesRdg.visibility = visibility
    }

    private fun adjustTimeoutVisibility(visibility: Int) {
        binding.timeoutSeek.visibility = visibility
        binding.timeoutTxt.visibility = visibility
    }

    private fun getFilterDuplicates(): Boolean {
        return binding.filterDupesRdg.checkedRadioButtonId == R.id.yesFilterDupesRdb
    }

    private fun getSelectedTimeout(): Int = binding.timeoutSeek.progress * SEEK_MILLI_OFFSET

    private fun restoreUI() {
        // Restore edit text value
        binding.searchRegexEdt.setText(searchStore.getLastUsedRegex())

        // Restore checked state
        val checkedId = when (searchStore.getSearchType()) {
            SearchType.ACCESS_POINT -> {
                showAccessPointUI()
                R.id.accessPointRdb
            }
            SearchType.SSID -> {
                showSSIDUI()
                R.id.ssidRdb
            }
            SearchType.SAVED_NETWORK -> {
                showSavedNetworkUI()
                R.id.savedNetworkRdb
            }
        }
        binding.searchTypeRdg.check(checkedId)

        // Restore return full list
        val fullListCheckedId = if (searchStore.shouldReturnFullList()) {
            R.id.yesFullListRdb
        } else {
            R.id.noFullListRdb
        }
        binding.returnFullListRdg.check(fullListCheckedId)

        // Restore filter duplicates
        val filterDupesCheckedId = if (searchStore.shouldFilterDuplicates()) {
            R.id.yesFilterDupesRdb
        } else {
            R.id.noFilterDupesRdb
        }
        binding.filterDupesRdg.check(filterDupesCheckedId)

        // Restore timeout
        val timeout = searchStore.getTimeout()
        binding.timeoutSeek.progress = timeout
        binding.timeoutTxt.text = getString(R.string.timeout_after_x_seconds_args_html, timeout).asHtmlSpanned()
        when (searchStore.getSearchType()) {
            SearchType.SAVED_NETWORK -> adjustTimeoutVisibility(View.INVISIBLE)
            else -> toggleSeekVisibility()
        }
    }

    private fun search() {
        when (binding.searchTypeRdg.checkedRadioButtonId) {
            R.id.accessPointRdb -> {
                when (binding.returnFullListRdg.checkedRadioButtonId) {
                    R.id.yesFullListRdb -> searchForAccessPointsPermissionLauncher.launch(
                        arrayOf(ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE)
                    )
                    R.id.noFullListRdb -> searchForAccessPointPermissionLauncher.launch(
                        arrayOf(ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE)
                    )
                }
            }
            R.id.savedNetworkRdb -> {
                when (binding.returnFullListRdg.checkedRadioButtonId) {
                    R.id.yesFullListRdb -> searchForSavedNetworksPermissionsLauncher.launch(ACCESS_FINE_LOCATION)
                    R.id.noFullListRdb -> searchForSavedNetworkPermissionsLauncher.launch(ACCESS_FINE_LOCATION)
                }
            }
            R.id.ssidRdb -> {
                when (binding.returnFullListRdg.checkedRadioButtonId) {
                    R.id.yesFullListRdb -> searchForSSIDsPermissionsLauncher.launch(ACCESS_FINE_LOCATION)
                    R.id.noFullListRdb -> searchForSSIDPermissionsLauncher.launch(ACCESS_FINE_LOCATION)
                }
            }
        }
    }

    private fun showAccessPointUI() {
        adjustFilterDupesVisibility(View.VISIBLE)
        toggleSeekVisibility()
    }

    private fun showSavedNetworkUI() {
        adjustFilterDupesVisibility(View.INVISIBLE)
        adjustTimeoutVisibility(View.INVISIBLE)
    }

    private fun showSSIDUI() {
        adjustFilterDupesVisibility(View.INVISIBLE)
        toggleSeekVisibility()
    }

    private fun toggleSeekVisibility() {
        when (binding.returnFullListRdg.checkedRadioButtonId) {
            R.id.yesFullListRdb -> adjustTimeoutVisibility(View.INVISIBLE)
            R.id.noFullListRdb -> adjustTimeoutVisibility(View.VISIBLE)
        }
    }

    private fun updateUI() {
        when (binding.searchTypeRdg.checkedRadioButtonId) {
            R.id.accessPointRdb -> showAccessPointUI()
            R.id.ssidRdb -> showSSIDUI()
            R.id.savedNetworkRdb -> showSavedNetworkUI()
        }
    }

    /*
     * Presenter callback overrides
     */

    override fun displaySavedNetwork(savedNetwork: SavedNetworkData?) {
        displayInfoFullScreen(getString(R.string.saved_network_args, savedNetwork), R.string.search_result)
    }

    override fun displaySavedNetworkNotFound() {
        displayInfo(R.string.saved_network_not_found, R.string.search_result)
    }

    override fun displaySavedNetworks(savedNetworks: List<SavedNetworkData>) {
        displayInfoFullScreen(getString(R.string.saved_networks_args, savedNetworks), R.string.search_result)
    }

    override fun displayNoSavedNetworksFound() {
        displayInfo(R.string.no_saved_networks_found, R.string.search_result)
    }

    override fun displayAccessPoint(accessPoint: AccessPointData?) {
        displayInfoFullScreen(getString(R.string.access_point_args, accessPoint), R.string.search_result)
    }

    override fun displayAccessPointNotFound() {
        displayInfo(R.string.access_point_not_found, R.string.search_result)
    }

    override fun displayAccessPoints(accessPoints: List<AccessPointData>) {
        displayInfoFullScreen(getString(R.string.access_points_args, accessPoints), R.string.search_result)
    }

    override fun displayNoAccessPointsFound() {
        displayInfo(R.string.no_access_points_found, R.string.search_result)
    }

    override fun displaySSID(ssid: SSIDData?) {
        displayInfoFullScreen(getString(R.string.ssid_args, ssid), R.string.search_result)
    }

    override fun displaySSIDNotFound() {
        displayInfo(R.string.ssid_not_found, R.string.search_result)
    }

    override fun displaySSIDs(ssids: List<SSIDData>) {
        displayInfoFullScreen(getString(R.string.ssids_args, ssids), R.string.search_result)
    }

    override fun displayNoSSIDsFound() {
        displayInfo(R.string.no_ssids_found, R.string.search_result)
    }

    override fun displayWisefyAsyncError(throwable: Throwable) {
        displayWisefyAsyncErrorDialog(throwable)
    }
}

@Composable
internal fun SearchScreen() {
    WisefySampleTheme {
        Text(text = "Search")
    }
}
