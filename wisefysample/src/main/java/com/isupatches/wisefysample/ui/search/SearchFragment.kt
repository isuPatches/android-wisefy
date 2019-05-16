package com.isupatches.wisefysample.ui.search

import android.Manifest
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.net.wifi.ScanResult
import android.net.wifi.WifiConfiguration
import android.os.Bundle
import android.view.View
import android.widget.SeekBar

import com.isupatches.wisefysample.R
import com.isupatches.wisefysample.internal.models.SearchType
import com.isupatches.wisefysample.internal.preferences.SearchStore
import com.isupatches.wisefysample.internal.base.BaseFragment
import com.isupatches.wisefysample.internal.util.asHtmlSpanned
import com.isupatches.wisefysample.internal.util.displayShortToast
import com.isupatches.wisefysample.internal.util.getTrimmedInput
import com.isupatches.wisefysample.internal.util.hideKeyboardFrom

import kotlinx.android.synthetic.main.fragment_search.filterDupesRdg
import kotlinx.android.synthetic.main.fragment_search.filterDupesTxt
import kotlinx.android.synthetic.main.fragment_search.returnFullListRdg
import kotlinx.android.synthetic.main.fragment_search.searchBtn
import kotlinx.android.synthetic.main.fragment_search.searchRegexEdt
import kotlinx.android.synthetic.main.fragment_search.searchTypeRdg
import kotlinx.android.synthetic.main.fragment_search.timeoutSeek
import kotlinx.android.synthetic.main.fragment_search.timeoutTxt

import javax.inject.Inject

internal class SearchFragment : BaseFragment(), SearchMvp.View {

    override val layoutRes = R.layout.fragment_search

    private val presenter by lazy { SearchPresenter(SearchModel(wiseFy)) }

    @Inject lateinit var searchStore: SearchStore

    companion object {
        val TAG: String = SearchFragment::class.java.simpleName

        fun newInstance() = SearchFragment()

        private const val TIMEOUT_MIN = 1
        private const val TIMEOUT_MAX = 60

        private const val WISEFY_SEARCH_FOR_SAVED_NETWORK_REQUEST_CODE = 1
        private const val WISEFY_SEARCH_FOR_SAVED_NETWORKS_REQUEST_CODE = 2
        private const val WISEFY_SEARCH_FOR_ACCESS_POINT_REQUEST_CODE = 3
        private const val WISEFY_SEARCH_FOR_ACCESS_POINTS_REQUEST_CODE = 4
        private const val WISEFY_SEARCH_FOR_SSID_REQUEST_CODE = 5
        private const val WISEFY_SEARCH_FOR_SSIDS_REQUEST_CODE = 6
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            restoreUI()
        }

        searchTypeRdg.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.accessPointRdb -> searchStore.setSearchType(SearchType.ACCESS_POINT)
                R.id.ssidRdb -> searchStore.setSearchType(SearchType.SSID)
                R.id.savedNetworkRdb -> searchStore.setSearchType(SearchType.SAVED_NETWORK)
            }
            updateUI()
        }
        filterDupesRdg.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.yesFilterDupesRdb -> searchStore.setFilterDuplicates(true)
                else -> searchStore.setFilterDuplicates(false)
            }
        }
        returnFullListRdg.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.yesFullListRdb -> searchStore.setReturnFullList(true)
                else -> searchStore.setReturnFullList(false)
            }
            updateUI()
        }
        searchBtn.setOnClickListener { search() }

        with(timeoutSeek) {
            max = TIMEOUT_MAX
            setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    val timeout = Math.max(TIMEOUT_MIN, progress)
                    searchStore.setTimeout(timeout)
                    timeoutTxt.text = getString(R.string.timeout_after_x_seconds_args_html, timeout).asHtmlSpanned()
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
        searchStore.setLastUsedRegex(searchRegexEdt.getTrimmedInput())
        hideKeyboardFrom(searchBtn)
    }

    /*
     * View helpers
     */

    private fun adjustFilterDupesVisibility(visibility: Int) {
        filterDupesTxt.visibility = visibility
        filterDupesRdg.visibility = visibility
    }

    private fun adjustTimeoutVisibility(visibility: Int) {
        timeoutSeek.visibility = visibility
        timeoutTxt.visibility = visibility
    }

    private fun getFilterDuplicates(): Boolean =
        filterDupesRdg.checkedRadioButtonId == R.id.yesFilterDupesRdb

    private fun getSelectedTimeout(): Int = timeoutSeek.progress * 1000

    private fun restoreUI() {
        // Restore edit text value
        searchRegexEdt.setText(searchStore.getLastUsedRegex())

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
        searchTypeRdg.check(checkedId)

        // Restore return full list
        val fullListCheckedId = if (searchStore.shouldReturnFullList()) {
            R.id.yesFullListRdb
        } else {
            R.id.noReturnFullListRdb
        }
        returnFullListRdg.check(fullListCheckedId)

        // Restore filter duplicates
        val filterDupesCheckedId = if (searchStore.shouldFilterDuplicates()) {
            R.id.yesFilterDupesRdb
        } else {
            R.id.noFilterDupesRdb
        }
        filterDupesRdg.check(filterDupesCheckedId)

        // Restore timeout
        val timeout = searchStore.getTimeout()
        timeoutSeek.progress = timeout
        timeoutTxt.text = getString(R.string.timeout_after_x_seconds_args_html, timeout).asHtmlSpanned()
        when(searchStore.getSearchType()) {
            SearchType.SAVED_NETWORK -> adjustTimeoutVisibility(View.INVISIBLE)
            else -> toggleSeekVisibility()
        }
    }

    private fun search() {
        when (searchTypeRdg.checkedRadioButtonId) {
            R.id.accessPointRdb -> {
                when (returnFullListRdg.checkedRadioButtonId) {
                    R.id.yesFullListRdb -> searchForAccessPoints()
                    R.id.noReturnFullListRdb -> searchForAccessPoint()
                }
            }
            R.id.ssidRdb -> {
                when (returnFullListRdg.checkedRadioButtonId) {
                    R.id.yesFullListRdb -> searchForSSIDs()
                    R.id.noReturnFullListRdb -> searchForSSID()
                }
            }
            R.id.savedNetworkRdb -> {
                when (returnFullListRdg.checkedRadioButtonId) {
                    R.id.yesFullListRdb -> getSavedNetworks()
                    R.id.noReturnFullListRdb -> getSavedNetwork()
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
        when (returnFullListRdg.checkedRadioButtonId) {
            R.id.yesFullListRdb -> adjustTimeoutVisibility(View.INVISIBLE)
            R.id.noReturnFullListRdb -> adjustTimeoutVisibility(View.VISIBLE)
        }
    }

    private fun updateUI() {
        when (searchTypeRdg.checkedRadioButtonId) {
            R.id.accessPointRdb -> showAccessPointUI()
            R.id.ssidRdb -> showSSIDUI()
            R.id.savedNetworkRdb -> showSavedNetworkUI()
        }
    }

    /*
     * WiseFy helpers
     */

    private fun getSavedNetwork() {
        if (checkSearchForSavedNetworkPermissions()) {
            presenter.getSavedNetwork(searchRegexEdt.getTrimmedInput())
        }
    }

    private fun getSavedNetworks() {
        if (checkSearchForSavedNetworksPermissions()) {
            presenter.getSavedNetworks(searchRegexEdt.getTrimmedInput())
        }
    }

    @Throws(SecurityException::class)
    private fun searchForAccessPoint() {
        if (checkSearchForAccessPointPermissions()) {
            presenter.searchForAccessPoint(searchRegexEdt.getTrimmedInput(), getSelectedTimeout(), getFilterDuplicates())
        }
    }

    @Throws(SecurityException::class)
    private fun searchForAccessPoints() {
        if (checkSearchForAccessPointsPermissions()) {
            presenter.searchForAccessPoints(searchRegexEdt.getTrimmedInput(), getFilterDuplicates())
        }
    }

    @Throws(SecurityException::class)
    private fun searchForSSID() {
        if (checkSearchForSSIDPermissions()) {
            presenter.searchForSSID(searchRegexEdt.getTrimmedInput(), getSelectedTimeout())
        }
    }

    @Throws(SecurityException::class)
    private fun searchForSSIDs() {
        if (checkSearchForSSIDsPermissions()) {
            presenter.searchForSSIDs(searchRegexEdt.getTrimmedInput())
        }
    }

    /*
     * Presenter callback overrides
     */

    override fun displaySavedNetwork(savedNetwork: WifiConfiguration) {
        displayShortToast("Saved network: $savedNetwork")
    }

    override fun displaySavedNetworkNotFound() {
        displayShortToast("Saved network not found")
    }

    override fun displaySavedNetworks(savedNetworks: List<WifiConfiguration>) {
        displayShortToast("Saved networks: $savedNetworks")
    }

    override fun displayNoSavedNetworksFound() {
        displayShortToast("No saved networks found")
    }

    override fun displayAccessPoint(accessPoint: ScanResult) {
        displayShortToast("Access point: $accessPoint")
    }

    override fun displayAccessPointNotFound() {
        displayShortToast("Access point not found")
    }

    override fun displayAccessPoints(accessPoints: List<ScanResult>) {
        displayShortToast("Access points: $accessPoints")
    }

    override fun displayNoAccessPointsFound() {
        displayShortToast("No access points found")
    }

    override fun displaySSID(ssid: String) {
        displayShortToast("SSID: $ssid")
    }

    override fun displaySSIDNotFound() {
        displayShortToast("SSID not found")
    }

    override fun displaySSIDs(ssids: List<String>) {
        displayShortToast("SSIDs: $ssids")
    }

    override fun displayNoSSIDsFound() {
        displayShortToast("No SSIDs found")
    }

    override fun displayWiseFyFailure(wiseFyFailureCode: Int) {
        displayWiseFyFailureWithCode(wiseFyFailureCode)
    }

    /*
     * Permission helpers
     */

    private fun checkSearchForSavedNetworkPermissions(): Boolean {
        return isPermissionGranted(ACCESS_WIFI_STATE, WISEFY_SEARCH_FOR_SAVED_NETWORK_REQUEST_CODE)
    }

    private fun checkSearchForSavedNetworksPermissions(): Boolean {
        return isPermissionGranted(ACCESS_WIFI_STATE, WISEFY_SEARCH_FOR_SAVED_NETWORKS_REQUEST_CODE)
    }

    private fun checkSearchForAccessPointPermissions(): Boolean {
        return isPermissionGranted(ACCESS_WIFI_STATE, WISEFY_SEARCH_FOR_ACCESS_POINT_REQUEST_CODE)
                && isPermissionGranted(Manifest.permission.ACCESS_COARSE_LOCATION, WISEFY_SEARCH_FOR_ACCESS_POINT_REQUEST_CODE)
    }

    private fun checkSearchForAccessPointsPermissions(): Boolean {
        return isPermissionGranted(ACCESS_WIFI_STATE, WISEFY_SEARCH_FOR_ACCESS_POINTS_REQUEST_CODE)
                && isPermissionGranted(Manifest.permission.ACCESS_COARSE_LOCATION, WISEFY_SEARCH_FOR_ACCESS_POINTS_REQUEST_CODE)
    }

    private fun checkSearchForSSIDPermissions(): Boolean {
        return isPermissionGranted(ACCESS_WIFI_STATE, WISEFY_SEARCH_FOR_SSID_REQUEST_CODE)
                && isPermissionGranted(Manifest.permission.ACCESS_COARSE_LOCATION, WISEFY_SEARCH_FOR_SSID_REQUEST_CODE)
    }

    private fun checkSearchForSSIDsPermissions(): Boolean {
        return isPermissionGranted(ACCESS_WIFI_STATE, WISEFY_SEARCH_FOR_SSIDS_REQUEST_CODE)
                && isPermissionGranted(Manifest.permission.ACCESS_COARSE_LOCATION, WISEFY_SEARCH_FOR_SSIDS_REQUEST_CODE)
    }
}
