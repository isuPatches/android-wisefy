package com.isupatches.wisefysample.ui.add

import android.Manifest.permission.ACCESS_WIFI_STATE
import android.content.pm.PackageManager
import android.net.wifi.WifiConfiguration
import android.os.Bundle
import android.util.Log
import android.view.View

import com.isupatches.wisefy.constants.WiseFyCode
import com.isupatches.wisefysample.R
import com.isupatches.wisefysample.internal.base.BaseFragment
import com.isupatches.wisefysample.internal.models.NetworkType
import com.isupatches.wisefysample.internal.preferences.AddNetworkStore
import com.isupatches.wisefysample.internal.util.displayShortToast
import com.isupatches.wisefysample.internal.util.getTrimmedInput
import com.isupatches.wisefysample.internal.util.hideKeyboardFrom

import kotlinx.android.synthetic.main.fragment_add.addNetworkBtn
import kotlinx.android.synthetic.main.fragment_add.addNetworkTypeRdg
import kotlinx.android.synthetic.main.fragment_add.networkNameEdt
import kotlinx.android.synthetic.main.fragment_add.networkPasswordTil
import kotlinx.android.synthetic.main.fragment_add.networkPasswordEdt

import javax.inject.Inject

internal class AddNetworkFragment : BaseFragment(), AddNetworkMvp.View {

    override val layoutRes = R.layout.fragment_add

    private val presenter by lazy { AddNetworkPresenter(wiseFy) }

    @Inject lateinit var addNetworkStore: AddNetworkStore

    companion object {
        val TAG: String = AddNetworkFragment::class.java.simpleName

        fun newInstance() = AddNetworkFragment()

        private const val WISEFY_ADD_OPEN_NETWORK_REQUEST_CODE = 1
        private const val WISEFY_ADD_WEP_NETWORK_REQUEST_CODE = 2
        private const val WISEFY_ADD_WPA2_NETWORK_REQUEST_CODE = 3
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        presenter.detachView()
        super.onStop()
        addNetworkStore.setLastUsedNetworkName(networkNameEdt.getTrimmedInput())
        addNetworkStore.setLastUsedNetworkPassword(networkPasswordEdt.getTrimmedInput())
        hideKeyboardFrom(addNetworkBtn)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            restoreUI()
        }

        addNetworkBtn.setOnClickListener {
            when (addNetworkTypeRdg.checkedRadioButtonId) {
                R.id.openNetworkRdb -> addOpenNetwork()
                R.id.wepNetworkRdb -> addWEPNetwork()
                R.id.wpa2NetworkRdb -> addWPA2Network()
            }
        }
        addNetworkTypeRdg.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.openNetworkRdb -> addNetworkStore.setNetworkType(NetworkType.OPEN)
                R.id.wepNetworkRdb -> addNetworkStore.setNetworkType(NetworkType.WEP)
                R.id.wpa2NetworkRdb -> addNetworkStore.setNetworkType(NetworkType.WPA2)
            }
            updateUI()
        }
    }

    /*
     * View helpers
     */

    private fun adjustNetworkPasswordVisibility(visibility: Int) {
        networkPasswordTil.visibility = visibility
        networkPasswordEdt.visibility = visibility
    }

    private fun restoreUI() {
        // Restore edit text values
        networkNameEdt.setText(addNetworkStore.getLastUsedNetworkName())
        networkPasswordEdt.setText(addNetworkStore.getLastUsedNetworkPassword())

        // Restore checked state
        val checkedId = when(addNetworkStore.getNetworkType()) {
            NetworkType.OPEN -> R.id.openNetworkRdb
            NetworkType.WEP -> R.id.wepNetworkRdb
            NetworkType.WPA2 -> R.id.wpa2NetworkRdb
        }
        addNetworkTypeRdg.check(checkedId)

        // Show/hide password edit
        when(addNetworkStore.getNetworkType()) {
            NetworkType.OPEN -> adjustNetworkPasswordVisibility(View.INVISIBLE)
            else -> adjustNetworkPasswordVisibility(View.VISIBLE)
        }
    }

    private fun updateUI() {
        when(addNetworkTypeRdg.checkedRadioButtonId) {
            R.id.openNetworkRdb -> adjustNetworkPasswordVisibility(View.INVISIBLE)
            else -> adjustNetworkPasswordVisibility(View.VISIBLE)
        }
    }

    /*
     * Presenter callback overrides
     */

    override fun displayFailureAddingNetwork(wifiManagerReturn: Int) {
        displayShortToast("Failure adding network. WifiManager return: $wifiManagerReturn")
    }

    override fun displayNetworkAdded(newNetworkId: Int, networkConfig: WifiConfiguration) {
        displayShortToast("Network added. Id: $newNetworkId, Config: $networkConfig")
    }

    override fun displayWiseFyFailure(@WiseFyCode wiseFyFailureCode: Int) {
        displayWiseFyFailureWithCode(wiseFyFailureCode)
    }

    /*
     * WiseFy helpers
     */

    private fun addOpenNetwork() {
        if (checkAddOpenNetworkPermissions()) {
            presenter.addOpenNetwork(networkNameEdt.getTrimmedInput())
        }
    }

    private fun addWEPNetwork() {
        if (checkAddWEPNetworkPermissions()) {
            presenter.addWEPNetwork(
                ssid = networkNameEdt.getTrimmedInput(),
                password = networkPasswordEdt.getTrimmedInput()
            )
        }
    }

    private fun addWPA2Network() {
        if (checkAddWPA2NetworkPermissions()) {
            presenter.addWPA2Network(
                ssid = networkNameEdt.getTrimmedInput(),
                password = networkPasswordEdt.getTrimmedInput()
            )
        }
    }

    /*
     * Permission helpers
     */

    private fun checkAddOpenNetworkPermissions(): Boolean {
        return isPermissionGranted(ACCESS_WIFI_STATE, WISEFY_ADD_OPEN_NETWORK_REQUEST_CODE)
    }

    private fun checkAddWEPNetworkPermissions(): Boolean {
        return isPermissionGranted(ACCESS_WIFI_STATE, WISEFY_ADD_WEP_NETWORK_REQUEST_CODE)
    }

    private fun checkAddWPA2NetworkPermissions(): Boolean {
        return isPermissionGranted(ACCESS_WIFI_STATE, WISEFY_ADD_WPA2_NETWORK_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            WISEFY_ADD_OPEN_NETWORK_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    addOpenNetwork()
                } else {
                    Log.w(TAG, "Permissions for adding an open network are denied")
                    // Display permission error here
                }
            }
            WISEFY_ADD_WEP_NETWORK_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    addWEPNetwork()
                } else {
                    Log.w(TAG, "Permissions for adding a WEP network are denied")
                    // Display permission error here
                }
            }
            WISEFY_ADD_WPA2_NETWORK_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    addWPA2Network()
                } else {
                    Log.w(TAG, "Permissions for adding a WPA2 network are denied")
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
