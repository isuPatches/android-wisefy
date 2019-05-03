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
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View

import com.isupatches.wisefy.constants.WiseFyCode
import com.isupatches.wisefysample.R
import com.isupatches.wisefysample.ui.base.BaseFragment
import com.isupatches.wisefysample.util.displayShortToast

import kotlinx.android.synthetic.main.fragment_misc.enableWifiBtn
import kotlinx.android.synthetic.main.fragment_misc.disableWifiBtn
import kotlinx.android.synthetic.main.fragment_misc.getCurrentNetworkBtn
import kotlinx.android.synthetic.main.fragment_misc.getCurrentNetworkDetailsBtn
import kotlinx.android.synthetic.main.fragment_misc.getFrequencyBtn
import kotlinx.android.synthetic.main.fragment_misc.getIPBtn
import kotlinx.android.synthetic.main.fragment_misc.getNearbyAccessPointsBtn
import kotlinx.android.synthetic.main.fragment_misc.getSavedNetworksBtn

class MiscFragment : BaseFragment(), MiscMvp.View {

    override val layoutRes = R.layout.fragment_misc

    private val presenter by lazy { MiscPresenter(wiseFy) }

    companion object {
        val TAG: String = MiscFragment::class.java.simpleName

        fun newInstance() = MiscFragment()

        private const val WISEFY_DISABLE_WIFI_REQUEST_CODE = 1
        private const val WISEFY_ENABLE_WIFI_REQUEST_CODE = 2
        private const val WISEFY_GET_CURRENT_NETWORK_REQUEST_CODE = 3
        private const val WISEFY_GET_CURRENT_NETWORK_INFO_REQUEST_CODE = 4
        private const val WISEFY_GET_FREQUENCY_REQUEST_CODE = 5
        private const val WISEFY_GET_IP_REQUEST_CODE = 6
        private const val WISEFY_GET_NEARBY_ACCESS_POINTS_REQUEST_CODE = 7
        private const val WISEFY_GET_SAVED_NETWORKS_REQUEST_CODE = 8
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
        getCurrentNetworkDetailsBtn.setOnClickListener {
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
        displayShortToast("Wifi disabled!")
    }

    override fun displayFailureDisablingWifi() {
        displayShortToast("Failure disabling wifi")
    }

    override fun displayWifiEnabled() {
        displayShortToast("Wifi enabled!")
    }

    override fun displayFailureEnablingWifi() {
        displayShortToast("Failure enabling wifi")
    }

    override fun displayCurrentNetwork(currentNetwork: WifiInfo) {
        displayShortToast("Current network: $currentNetwork")
    }

    override fun displayNoCurrentNetwork() {
        displayShortToast("No current network")
    }

    override fun displayCurrentNetworkInfo(currentNetworkDetails: NetworkInfo) {
        displayShortToast("Current network info: $currentNetworkDetails")
    }

    override fun displayNoCurrentNetworkInfo() {
        displayShortToast("No current network info")
    }

    override fun displayFrequency(frequency: Int) {
        displayShortToast("Frequency: $frequency")
    }

    override fun displayFailureRetrievingFrequency() {
        displayShortToast("Failure retrieving frequency")
    }

    override fun displayIP(ip: String) {
        displayShortToast("IP: $ip")
    }

    override fun displayFailureRetrievingIP() {
        displayShortToast("Failure retrieving IP")
    }

    override fun displayNearbyAccessPoints(accessPoints: List<ScanResult>) {
        displayShortToast("Nearby access points: $accessPoints")
    }

    override fun displayNoAccessPointsFound() {
        displayShortToast("No access points found!")
    }

    override fun displaySavedNetworks(savedNetworks: List<WifiConfiguration>) {
        displayShortToast("Saved networks: $savedNetworks")
    }

    override fun displayNoSavedNetworksFound() {
        displayShortToast("No saved networks found!")
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (checkGetFrequencyPermissions()) {
                presenter.getFrequency()
            }
        } else {
            displayShortToast("getFrequency is a Lollipop and above feature")
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
        return isPermissionGranted(ACCESS_WIFI_STATE, WISEFY_GET_CURRENT_NETWORK_REQUEST_CODE)
                && isPermissionGranted(ACCESS_COARSE_LOCATION, WISEFY_GET_CURRENT_NETWORK_REQUEST_CODE)
    }

    private fun checkGetCurrentNetworkInfoPermissions(): Boolean {
        return isPermissionGranted(ACCESS_NETWORK_STATE, WISEFY_GET_CURRENT_NETWORK_INFO_REQUEST_CODE)
    }

    private fun checkGetFrequencyPermissions(): Boolean {
        return isPermissionGranted(ACCESS_WIFI_STATE, WISEFY_GET_FREQUENCY_REQUEST_CODE)
                && isPermissionGranted(ACCESS_COARSE_LOCATION, WISEFY_GET_FREQUENCY_REQUEST_CODE)
    }

    private fun  checkGetIPPermissions(): Boolean {
        return isPermissionGranted(ACCESS_WIFI_STATE, WISEFY_GET_IP_REQUEST_CODE)
                && isPermissionGranted(ACCESS_COARSE_LOCATION, WISEFY_GET_IP_REQUEST_CODE)
    }

    private fun checkGetNearbyAccessPointsPermissions(): Boolean {
        return isPermissionGranted(ACCESS_WIFI_STATE, WISEFY_GET_NEARBY_ACCESS_POINTS_REQUEST_CODE)
                && isPermissionGranted(ACCESS_COARSE_LOCATION, WISEFY_GET_NEARBY_ACCESS_POINTS_REQUEST_CODE)
    }

    private fun checkGetSavedNetworksPermissions(): Boolean {
        return isPermissionGranted(ACCESS_WIFI_STATE, WISEFY_GET_SAVED_NETWORKS_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            WISEFY_DISABLE_WIFI_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getIP()
                } else {
                    Log.w(TAG, "Permissions for disabling wifi are denied")
                    // Display permission error here
                }
            }
            WISEFY_ENABLE_WIFI_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getIP()
                } else {
                    Log.w(TAG, "Permissions for enabling wifi are denied")
                    // Display permission error here
                }
            }
            WISEFY_GET_CURRENT_NETWORK_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getCurrentNetwork()
                } else {
                    Log.w(TAG, "Permissions for getting current network are denied")
                    // Display permission error here
                }
            }
            WISEFY_GET_CURRENT_NETWORK_INFO_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getCurrentNetworkInfo()
                } else {
                    Log.w(TAG, "Permissions for getting current network info are denied")
                    // Display permission error here
                }
            }
            WISEFY_GET_FREQUENCY_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getFrequency()
                } else {
                    Log.w(TAG, "Permissions for getting frequency are denied")
                    // Display permission error here
                }
            }
            WISEFY_GET_IP_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getIP()
                } else {
                    Log.w(TAG, "Permissions for getting ip are denied")
                    // Display permission error here
                }
            }
            WISEFY_GET_NEARBY_ACCESS_POINTS_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getNearbyAccessPoints()
                } else {
                    Log.w(TAG, "Permissions for getting nearby access points are denied")
                    // Display permission error here
                }
            }
            WISEFY_GET_SAVED_NETWORKS_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getIP()
                } else {
                    Log.w(TAG, "Permissions for getting saved networks are denied")
                    // Display permission error here
                }
            }
            else -> {
                // Display permission error here
                Log.wtf(TAG, "Weird permission requested, not handled")
            }
        }
    }
}
