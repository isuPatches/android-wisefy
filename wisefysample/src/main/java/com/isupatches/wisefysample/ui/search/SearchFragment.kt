package com.isupatches.wisefysample.ui.search

import android.Manifest
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.net.wifi.ScanResult
import android.net.wifi.WifiConfiguration
import android.os.Bundle
import android.view.View
import com.isupatches.wisefysample.R
import com.isupatches.wisefysample.ui.base.BaseFragment
import com.isupatches.wisefysample.ui.misc.MiscFragment

class SearchFragment : BaseFragment(), SearchMvp.View {

    override val layoutRes = R.layout.fragment_search

    private val presenter by lazy { SearchPresenter(wiseFy) }

    companion object {
        val TAG: String = SearchFragment::class.java.simpleName

        fun newInstance() = SearchFragment()

        private const val WISEFY_SEARCH_FOR_SAVED_NETWORK_REQUEST_CODE = 1
        private const val WISEFY_SEARCH_FOR_SAVED_NETWORKS_REQUEST_CODE = 2
        private const val WISEFY_SEARCH_FOR_ACCESS_POINT_REQUEST_CODE = 3
        private const val WISEFY_SEARCH_FOR_ACCESS_POINTS_REQUEST_CODE = 4
        private const val WISEFY_SEARCH_FOR_SSID_REQUEST_CODE = 5
        private const val WISEFY_SEARCH_FOR_SSIDS_REQUEST_CODE = 6
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // todo@patches add UI click listeners here
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
     * WiseFy helpers
     */

    private fun getSavedNetwork() {
        if (checkSearchForSavedNetworkPermissions()) {

        }
    }

    private fun getSavedNetworks() {
        if (checkSearchForSavedNetworksPermissions()) {

        }
    }

    private fun searchForAccessPoint() {
        if (checkSearchForAccessPointPermissions()) {

        }
    }

    private fun searchForAccessPoints() {
        if (checkSearchForAccessPointsPermissions()) {

        }
    }

    private fun searchForSSID() {
        if (checkSearchForSSIDPermissions()) {

        }
    }

    private fun searchForSSIDs() {
        if (checkSearchForSSIDsPermissions()) {

        }
    }

    /*
     * Presenter callback overrides
     */

    override fun displaySavedNetwork(savedNetwork: WifiConfiguration) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun displaySavedNetworkNotFound() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun displaySavedNetworks(savedNetworks: List<WifiConfiguration>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun displayNoSavedNetworksFound() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun displayAccessPoint(accessPoint: ScanResult) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun displayAccessPointNotFound() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun displayAccessPoints(accessPoints: List<ScanResult>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun displayNoAccessPointsFound() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun displaySSID(ssid: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun displaySSIDNotFound() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun displaySSIDs(ssids: List<String>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun displayNoSSIDsFound() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun displayWiseFyFailure(wiseFyFailureCode: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
