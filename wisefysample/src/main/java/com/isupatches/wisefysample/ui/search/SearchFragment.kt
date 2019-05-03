package com.isupatches.wisefysample.ui.search

import android.Manifest
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.net.wifi.ScanResult
import android.net.wifi.WifiConfiguration
import android.os.Bundle
import android.view.View
import android.widget.SeekBar

import com.isupatches.wisefysample.R
import com.isupatches.wisefysample.ui.base.BaseFragment
import com.isupatches.wisefysample.util.asHtmlSpanned
import com.isupatches.wisefysample.util.displayShortToast
import com.isupatches.wisefysample.util.getTrimmedInput

import kotlinx.android.synthetic.main.fragment_search.filterDupesRdg
import kotlinx.android.synthetic.main.fragment_search.filterDupesTxt
import kotlinx.android.synthetic.main.fragment_search.returnFullListRdg
import kotlinx.android.synthetic.main.fragment_search.searchBtn
import kotlinx.android.synthetic.main.fragment_search.searchRegexEdt
import kotlinx.android.synthetic.main.fragment_search.searchTypeRdg
import kotlinx.android.synthetic.main.fragment_search.timeoutSeek
import kotlinx.android.synthetic.main.fragment_search.timeoutTxt

class SearchFragment : BaseFragment(), SearchMvp.View {

    override val layoutRes = R.layout.fragment_search

    private val presenter by lazy { SearchPresenter(wiseFy) }

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

        searchTypeRdg.setOnCheckedChangeListener { _, _ ->
            updateUI()
        }
        returnFullListRdg.setOnCheckedChangeListener { _, _ ->
            updateUI()
        }

        searchBtn.setOnClickListener {
            search()
        }

        with(timeoutSeek) {
            max = TIMEOUT_MAX
            setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    val progressToDisplay = Math.max(TIMEOUT_MIN, progress)
                    timeoutTxt.text = getString(R.string.timeout_after_x_seconds_args_html, progressToDisplay).asHtmlSpanned()
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
    }

    /*
     * View helpers
     */

    private fun getFilterDuplicates(): Boolean =
        filterDupesRdg.checkedRadioButtonId == R.id.yesFilterDupesRdb

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

    private fun updateUI() {
        when (searchTypeRdg.checkedRadioButtonId) {
            R.id.accessPointRdb -> {
                filterDupesTxt.visibility = View.VISIBLE
                filterDupesRdg.visibility = View.VISIBLE
                when (returnFullListRdg.checkedRadioButtonId) {
                    R.id.yesFullListRdb -> {
                        timeoutSeek.visibility = View.INVISIBLE
                    }
                    R.id.noReturnFullListRdb -> {
                        timeoutSeek.visibility = View.VISIBLE
                    }
                }
            }
            R.id.ssidRdb -> {
                filterDupesTxt.visibility = View.INVISIBLE
                filterDupesRdg.visibility = View.INVISIBLE
                when (returnFullListRdg.checkedRadioButtonId) {
                    R.id.yesFullListRdb -> {
                        timeoutSeek.visibility = View.INVISIBLE
                    }
                    R.id.noReturnFullListRdb -> {
                        timeoutSeek.visibility = View.VISIBLE
                    }
                }
            }
            R.id.savedNetworkRdb -> {
                filterDupesTxt.visibility = View.INVISIBLE
                filterDupesRdg.visibility = View.INVISIBLE
                timeoutSeek.visibility = View.INVISIBLE
            }
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
            presenter.searchForAccessPoint(searchRegexEdt.getTrimmedInput(), timeoutSeek.progress, getFilterDuplicates())
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
            presenter.searchForSSID(searchRegexEdt.getTrimmedInput(), timeoutSeek.progress)
        }
    }

    @Throws(SecurityException::class)
    private fun searchForSSIDs() {
        if (checkSearchForSSIDsPermissions()) {
            presenter.searchForSSIDs(searchRegexEdt.getTrimmedInput());
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
