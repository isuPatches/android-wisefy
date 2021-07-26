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
package com.isupatches.android.wisefy.sample.ui.search

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager
import android.net.wifi.ScanResult
import android.net.wifi.WifiConfiguration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.annotation.VisibleForTesting
import com.isupatches.android.viewglu.paste
import com.isupatches.android.wisefy.sample.R
import com.isupatches.android.wisefy.sample.databinding.FragmentSearchBinding
import com.isupatches.android.wisefy.sample.internal.base.BaseFragment
import com.isupatches.android.wisefy.sample.internal.entities.SearchType
import com.isupatches.android.wisefy.sample.internal.util.asHtmlSpanned
import com.isupatches.android.wisefy.sample.internal.util.getTrimmedInput
import com.isupatches.android.wisefy.sample.internal.util.hideKeyboardFrom
import javax.inject.Inject
import kotlin.math.max

@VisibleForTesting internal const val WISEFY_SEARCH_FOR_SAVED_NETWORK_REQUEST_CODE = 1
@VisibleForTesting internal const val WISEFY_SEARCH_FOR_SAVED_NETWORKS_REQUEST_CODE = 2
@VisibleForTesting internal const val WISEFY_SEARCH_FOR_ACCESS_POINT_REQUEST_CODE = 3
@VisibleForTesting internal const val WISEFY_SEARCH_FOR_ACCESS_POINTS_REQUEST_CODE = 4
@VisibleForTesting internal const val WISEFY_SEARCH_FOR_SSID_REQUEST_CODE = 5
@VisibleForTesting internal const val WISEFY_SEARCH_FOR_SSIDS_REQUEST_CODE = 6

private const val TIMEOUT_MIN = 1
private const val TIMEOUT_MAX = 60

private const val SEEK_MILLI_OFFSET = 1000

private const val LOG_TAG = "SearchFragment"

internal interface SearchView {
    fun displaySavedNetwork(savedNetwork: WifiConfiguration)
    fun displaySavedNetworkNotFound()
    fun displaySavedNetworks(savedNetworks: List<WifiConfiguration>)
    fun displayNoSavedNetworksFound()
    fun displayAccessPoint(accessPoint: ScanResult)
    fun displayAccessPointNotFound()
    fun displayAccessPoints(accessPoints: List<ScanResult>)
    fun displayNoAccessPointsFound()
    fun displaySSID(ssid: String)
    fun displaySSIDNotFound()
    fun displaySSIDs(ssids: List<String>)
    fun displayNoSSIDsFound()
}

@Suppress("LargeClass")
internal class SearchFragment : BaseFragment(), SearchView {

    @SearchScope @Inject lateinit var presenter: SearchPresenter
    @SearchScope @Inject lateinit var searchStore: SearchStore

    private var binding: FragmentSearchBinding by paste()

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
                    binding.timeoutTxt.text = getString(R.string.timeout_after_x_seconds_args_html, timeout).asHtmlSpanned()
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
                    R.id.yesFullListRdb -> searchForAccessPoints()
                    R.id.noFullListRdb -> searchForAccessPoint()
                }
            }
            R.id.savedNetworkRdb -> {
                when (binding.returnFullListRdg.checkedRadioButtonId) {
                    R.id.yesFullListRdb -> searchForSavedNetworks()
                    R.id.noFullListRdb -> searchForSavedNetwork()
                }
            }
            R.id.ssidRdb -> {
                when (binding.returnFullListRdg.checkedRadioButtonId) {
                    R.id.yesFullListRdb -> searchForSSIDs()
                    R.id.noFullListRdb -> searchForSSID()
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
     * WiseFy helpers
     */

    @Throws(SecurityException::class)
    private fun searchForAccessPoint() {
        if (checkSearchForAccessPointPermissions()) {
            presenter.searchForAccessPoint(
                binding.searchRegexEdt.getTrimmedInput(),
                getSelectedTimeout(),
                getFilterDuplicates()
            )
        }
    }

    @Throws(SecurityException::class)
    private fun searchForAccessPoints() {
        if (checkSearchForAccessPointsPermissions()) {
            presenter.searchForAccessPoints(binding.searchRegexEdt.getTrimmedInput(), getFilterDuplicates())
        }
    }

    @Throws(SecurityException::class)
    private fun searchForSavedNetwork() {
        if (checkSearchForSavedNetworkPermissions()) {
            presenter.searchForSavedNetwork(binding.searchRegexEdt.getTrimmedInput())
        }
    }

    @Throws(SecurityException::class)
    private fun searchForSavedNetworks() {
        if (checkSearchForSavedNetworksPermissions()) {
            presenter.searchForSavedNetworks(binding.searchRegexEdt.getTrimmedInput())
        }
    }

    @Throws(SecurityException::class)
    private fun searchForSSID() {
        if (checkSearchForSSIDPermissions()) {
            presenter.searchForSSID(binding.searchRegexEdt.getTrimmedInput(), getSelectedTimeout())
        }
    }

    @Throws(SecurityException::class)
    private fun searchForSSIDs() {
        if (checkSearchForSSIDsPermissions()) {
            presenter.searchForSSIDs(binding.searchRegexEdt.getTrimmedInput())
        }
    }

    /*
     * Presenter callback overrides
     */

    override fun displaySavedNetwork(savedNetwork: WifiConfiguration) {
        displayInfoFullScreen(getString(R.string.saved_network_args, savedNetwork), R.string.search_result)
    }

    override fun displaySavedNetworkNotFound() {
        displayInfo(R.string.saved_network_not_found, R.string.search_result)
    }

    override fun displaySavedNetworks(savedNetworks: List<WifiConfiguration>) {
        displayInfoFullScreen(getString(R.string.saved_networks_args, savedNetworks), R.string.search_result)
    }

    override fun displayNoSavedNetworksFound() {
        displayInfo(R.string.no_saved_networks_found, R.string.search_result)
    }

    override fun displayAccessPoint(accessPoint: ScanResult) {
        displayInfoFullScreen(getString(R.string.access_point_args, accessPoint), R.string.search_result)
    }

    override fun displayAccessPointNotFound() {
        displayInfo(R.string.access_point_not_found, R.string.search_result)
    }

    override fun displayAccessPoints(accessPoints: List<ScanResult>) {
        displayInfoFullScreen(getString(R.string.access_points_args, accessPoints), R.string.search_result)
    }

    override fun displayNoAccessPointsFound() {
        displayInfo(R.string.no_access_points_found, R.string.search_result)
    }

    override fun displaySSID(ssid: String) {
        displayInfoFullScreen(getString(R.string.ssid_args, ssid), R.string.search_result)
    }

    override fun displaySSIDNotFound() {
        displayInfo(R.string.ssid_not_found, R.string.search_result)
    }

    override fun displaySSIDs(ssids: List<String>) {
        displayInfoFullScreen(getString(R.string.ssids_args, ssids), R.string.search_result)
    }

    override fun displayNoSSIDsFound() {
        displayInfo(R.string.no_ssids_found, R.string.search_result)
    }

    override fun displayWiseFyFailure(wiseFyFailureCode: Int) {
        displayWiseFyFailureWithCode(wiseFyFailureCode)
    }

    /*
     * Permission helpers
     */

    private fun checkSearchForSavedNetworkPermissions(): Boolean {
        return isPermissionGranted(ACCESS_FINE_LOCATION, WISEFY_SEARCH_FOR_SAVED_NETWORK_REQUEST_CODE)
    }

    private fun checkSearchForSavedNetworksPermissions(): Boolean {
        return isPermissionGranted(ACCESS_FINE_LOCATION, WISEFY_SEARCH_FOR_SAVED_NETWORKS_REQUEST_CODE)
    }

    private fun checkSearchForAccessPointPermissions(): Boolean {
        return isPermissionGranted(ACCESS_FINE_LOCATION, WISEFY_SEARCH_FOR_ACCESS_POINT_REQUEST_CODE)
    }

    private fun checkSearchForAccessPointsPermissions(): Boolean {
        return isPermissionGranted(ACCESS_FINE_LOCATION, WISEFY_SEARCH_FOR_ACCESS_POINTS_REQUEST_CODE)
    }

    private fun checkSearchForSSIDPermissions(): Boolean {
        return isPermissionGranted(ACCESS_FINE_LOCATION, WISEFY_SEARCH_FOR_SSID_REQUEST_CODE)
    }

    private fun checkSearchForSSIDsPermissions(): Boolean {
        return isPermissionGranted(ACCESS_FINE_LOCATION, WISEFY_SEARCH_FOR_SSIDS_REQUEST_CODE)
    }

    @Suppress("LongMethod", "ComplexMethod")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            WISEFY_SEARCH_FOR_SAVED_NETWORK_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    searchForSavedNetwork()
                } else {
                    Log.w(LOG_TAG, "Permissions for getting a saved network are denied")
                    displayPermissionErrorDialog(R.string.permission_error_search_for_saved_network)
                }
            }
            WISEFY_SEARCH_FOR_SAVED_NETWORKS_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    searchForSavedNetworks()
                } else {
                    Log.w(LOG_TAG, "Permissions for getting saved networks are denied")
                    displayPermissionErrorDialog(R.string.permission_error_search_for_saved_networks)
                }
            }
            WISEFY_SEARCH_FOR_ACCESS_POINT_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    searchForAccessPoint()
                } else {
                    Log.w(LOG_TAG, "Permissions for searching for an access point are denied")
                    displayPermissionErrorDialog(R.string.permission_error_search_for_access_point)
                }
            }
            WISEFY_SEARCH_FOR_ACCESS_POINTS_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    searchForAccessPoints()
                } else {
                    Log.w(LOG_TAG, "Permissions for searching for access points are denied")
                    displayPermissionErrorDialog(R.string.permission_error_search_for_access_points)
                }
            }
            WISEFY_SEARCH_FOR_SSID_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    searchForSSID()
                } else {
                    Log.w(LOG_TAG, "Permissions for searching for an SSID are denied")
                    displayPermissionErrorDialog(R.string.permission_error_search_for_ssid)
                }
            }
            WISEFY_SEARCH_FOR_SSIDS_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    searchForSSIDs()
                } else {
                    Log.w(LOG_TAG, "Permissions for searching for SSIDs are denied")
                    displayPermissionErrorDialog(R.string.permission_error_search_for_ssids)
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
