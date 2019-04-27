package com.isupatches.wisefysample.ui.main

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.content.pm.PackageManager
import android.net.wifi.ScanResult
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

import com.isupatches.wisefy.WiseFy
import com.isupatches.wisefy.callbacks.GetNearbyAccessPointsCallbacks
import com.isupatches.wisefysample.R
import com.isupatches.wisefysample.nav.openFragment
import com.isupatches.wisefysample.ui.add.AddNetworkFragment
import com.isupatches.wisefysample.ui.misc.MiscFragment
import com.isupatches.wisefysample.ui.remove.RemoveNetworkFragment
import com.isupatches.wisefysample.ui.search.SearchFragment
import com.isupatches.wisefysample.util.PermissionsUtil
import kotlinx.android.synthetic.main.activity_main.bottomNavigationView

const val WISEFY_PERMISSION_REQUEST_CODE = 1

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener  {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    private lateinit var wisefy: WiseFy

    private val permissionUtil = PermissionsUtil.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wisefy = WiseFy.Brains(this).getSmarts()

        if (checkForPermissions()) {
            getNearbyAccessPoints()
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(this)
        if (savedInstanceState == null) {
//            bottomNavigationView.selectItem(R.id.menu_home)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        wisefy.dump()
    }

    private fun checkForPermissions(): Boolean {
        return isPermissionGranted(ACCESS_COARSE_LOCATION, WISEFY_PERMISSION_REQUEST_CODE)
    }

    private fun isPermissionGranted(permission: String, requestCode: Int): Boolean {
        return if (permissionUtil.permissionNotGranted(this, permission)) {
            if (permissionUtil.shouldShowPermissionRationale(this, permission)) {
                // Display dialog or rationale for requesting permission here
            } else {
                permissionUtil.requestPermissions(this, arrayOf(permission), requestCode)
            }
            false
        } else {
            true
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            WISEFY_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "WiseFy permissions granted")
                    // Continue WiseFy logic here
                } else {
                    Log.e(TAG, "Access course location permission denied")
                    // Display permission error here
                }
            }
            else -> {
                // Display permission error here
                Log.wtf(TAG, "Weird permission requested, not handled")
            }
        }
    }

    @Throws(SecurityException::class)
    private fun getNearbyAccessPoints() {
        wisefy.getNearbyAccessPoints(true, object : GetNearbyAccessPointsCallbacks {
            override fun wisefyFailure(wisefyFailureCode: Int) {
                // failure logic goes here
            }

            override fun retrievedNearbyAccessPoints(nearbyAccessPoints: List<ScanResult>) {
                // You should see this populate with results after approving the
                // the ACCESS_COARSE_LOCATION permission
                Log.d(TAG, "List: $nearbyAccessPoints")
            }
        })
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_add -> {
                openFragment(this, AddNetworkFragment.newInstance(), AddNetworkFragment.TAG)
                return true
            }
            R.id.menu_remove -> {
                openFragment(this, RemoveNetworkFragment.newInstance(), RemoveNetworkFragment.TAG)
                return true
            }
            R.id.menu_home -> {
                openFragment(this, MainFragment.newInstance(), MainFragment.TAG)
                return true
            }
            R.id.menu_search -> {
                openFragment(this, SearchFragment.newInstance(), SearchFragment.TAG)
                return true
            }
            R.id.menu_misc -> {
                openFragment(this, MiscFragment.newInstance(), MiscFragment.TAG)
                return true
            }
        }
//        bottomNavigationView.selectItem(item.itemId)
        return super.onOptionsItemSelected(item)
    }
}
