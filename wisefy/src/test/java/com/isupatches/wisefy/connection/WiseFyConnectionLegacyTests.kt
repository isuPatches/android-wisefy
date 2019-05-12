package com.isupatches.wisefy.connection

import android.os.Build

import com.isupatches.wisefy.TEST_SSID
import com.isupatches.wisefy.TEST_SSID2
import com.isupatches.wisefy.TEST_TIMEOUT
import com.isupatches.wisefy.TEST_TYPE1
import com.isupatches.wisefy.constants.MOBILE
import com.isupatches.wisefy.constants.WIFI
import com.isupatches.wisefy.internal.base.BaseUnitTest
import org.junit.After

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Used to test the WiseFyConnectionLegacy class and functionality determining various connection states.
 *
 * @author Patches
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.JELLY_BEAN])
internal class WiseFyConnectionLegacyTests : BaseUnitTest() {

    private val wisefyConnection = WiseFyConnectionLegacy.create(
        mockConnectivityManager,
        mockWifiManager
    )

    @Before fun setUp() {
        super.tearDown()
        wisefyConnection.init()
    }

    @After override fun tearDown() {
        super.tearDown()
        wisefyConnection.destroy()
    }

    /*
     * isCurrentNetworkConnectedToSSID tests
     */

    @Test fun isCurrentNetworkConnectedToSSID_failure_nullSSIDParam() {
        assertFalse(wisefyConnection.isCurrentNetworkConnectedToSSID(null))
    }

    @Test fun isCurrentNetworkConnectedToSSID_failure_emptySSIDParam() {
        assertFalse(wisefyConnection.isCurrentNetworkConnectedToSSID(""))
    }

    @Test fun isCurrentNetworkConnectedToSSID_failure_nullConnectionInfo() {
        mockNetworkUtil.currentNetwork_null()
        assertFalse(wisefyConnection.isCurrentNetworkConnectedToSSID(TEST_SSID))
    }

    @Test fun isCurrentNetworkConnectedToSSID_failure_nullSSID() {
        mockNetworkUtil.currentNetwork(null)
        mockNetworkUtil.currentNetworkConnectionStatus(true, true, null)
        assertFalse(wisefyConnection.isCurrentNetworkConnectedToSSID(TEST_SSID))
    }

    @Test fun isCurrentNetworkConnectedToSSID_failure_emptySSID() {
        mockNetworkUtil.currentNetwork("")
        mockNetworkUtil.currentNetworkConnectionStatus(true, true, null)
        assertFalse(wisefyConnection.isCurrentNetworkConnectedToSSID(TEST_SSID))
    }

    @Test fun isCurrentNetworkConnectedToSSID_failure_differentSSID() {
        mockNetworkUtil.currentNetwork(TEST_SSID2)
        mockNetworkUtil.currentNetworkConnectionStatus(true, true, null)
        assertFalse(wisefyConnection.isCurrentNetworkConnectedToSSID(TEST_SSID))
    }

    @Test fun isCurrentNetworkConnectedToSSID_failure_notAvailable() {
        mockNetworkUtil.currentNetwork(TEST_SSID)
        mockNetworkUtil.currentNetworkConnectionStatus(false, true, null)
        assertFalse(wisefyConnection.isCurrentNetworkConnectedToSSID(TEST_SSID))
    }

    @Test fun isCurrentNetworkConnectedToSSID_failure_notAvailableOrConnected() {
        mockNetworkUtil.currentNetwork(TEST_SSID)
        mockNetworkUtil.currentNetworkConnectionStatus(false, false, null)
        assertFalse(wisefyConnection.isCurrentNetworkConnectedToSSID(TEST_SSID))
    }

    @Test fun isCurrentNetworkConnectedToSSID_failure_notConnected() {
        mockNetworkUtil.currentNetwork(TEST_SSID)
        mockNetworkUtil.currentNetworkConnectionStatus(true, false, null)
        assertFalse(wisefyConnection.isCurrentNetworkConnectedToSSID(TEST_SSID))
    }

    @Test fun isCurrentNetworkConnectedToSSID_success() {
        mockNetworkUtil.currentNetwork(TEST_SSID)
        mockNetworkUtil.currentNetworkConnectionStatus(true, true, null)
        assertTrue(wisefyConnection.isCurrentNetworkConnectedToSSID(TEST_SSID))
    }

    /*
     * isNetworkConnected tests
     */

    @Test fun isNetworkConnected_failure_nullNetworkInfoParam() {
        assertFalse(wisefyConnection.isNetworkConnected())
    }

    @Test fun isNetworkConnected_failure_notAvailable() {
        mockNetworkUtil.currentNetworkConnectionStatus(false, true, null)
        assertFalse(wisefyConnection.isNetworkConnected())
    }

    @Test fun isNetworkConnected_failure_notAvailableOrConnected() {
        mockNetworkUtil.currentNetworkConnectionStatus(false, false, null)
        assertFalse(wisefyConnection.isNetworkConnected())
    }

    @Test fun isNetworkConnected_failure_notConnected() {
        mockNetworkUtil.currentNetworkConnectionStatus(true, false, null)
        assertFalse(wisefyConnection.isNetworkConnected())
    }

    @Test fun isNetworkConnected_success() {
        mockNetworkUtil.currentNetworkConnectionStatus(true, true, null)
        assertTrue(wisefyConnection.isNetworkConnected())
    }

    /*
     * isDeviceConnectedToMobileNetwork tests
     */

    @Test fun isDeviceConnectedToMobileNetwork_failure_nullNetworkInfo() {
        assertFalse(wisefyConnection.isDeviceConnectedToMobileNetwork())
    }

    @Test fun isDeviceConnectedToMobileNetwork_failure_notAvailableOrConnected() {
        mockNetworkUtil.currentNetwork(TEST_SSID)
        mockNetworkUtil.currentNetworkConnectionStatus(false, false, MOBILE)
        assertFalse(wisefyConnection.isDeviceConnectedToMobileNetwork())
    }

    @Test fun isDeviceConnectedToMobileNetwork_failure_notAvailable() {
        mockNetworkUtil.currentNetwork(TEST_SSID)
        mockNetworkUtil.currentNetworkConnectionStatus(false, true, MOBILE)
        assertFalse(wisefyConnection.isDeviceConnectedToMobileNetwork())
    }

    @Test fun isDeviceConnectedToMobileNetwork_failure_notConnected() {
        mockNetworkUtil.currentNetwork(TEST_SSID)
        mockNetworkUtil.currentNetworkConnectionStatus(true, false, MOBILE)
        assertFalse(wisefyConnection.isDeviceConnectedToMobileNetwork())
    }

    @Test fun isDeviceConnectedToMobileNetwork_failure_nullTypeName() {
        mockNetworkUtil.currentNetwork(TEST_SSID)
        mockNetworkUtil.currentNetworkConnectionStatus(true, true, null)
        assertFalse(wisefyConnection.isDeviceConnectedToMobileNetwork())
    }

    @Test fun isDeviceConnectedToMobileNetwork_failure_differentTypeName() {
        mockNetworkUtil.currentNetwork(TEST_SSID)
        mockNetworkUtil.currentNetworkConnectionStatus(true, true, WIFI)
        assertFalse(wisefyConnection.isDeviceConnectedToMobileNetwork())
    }

    @Test fun isDeviceConnectedToMobileNetwork_failure_differentTypeName_differentCase() {
        mockNetworkUtil.currentNetwork(TEST_SSID)
        mockNetworkUtil.currentNetworkConnectionStatus(true, true, "wifi")
        assertFalse(wisefyConnection.isDeviceConnectedToMobileNetwork())
    }

    @Test fun isDeviceConnectedToMobileNetwork_success() {
        mockNetworkUtil.currentNetwork(TEST_SSID)
        mockNetworkUtil.currentNetworkConnectionStatus(true, true, MOBILE)
        assertTrue(wisefyConnection.isDeviceConnectedToMobileNetwork())
    }

    @Test fun isDeviceConnectedToMobileNetwork_success_differentCase() {
        mockNetworkUtil.currentNetwork(TEST_SSID)
        mockNetworkUtil.currentNetworkConnectionStatus(true, true, "mobile")
        assertTrue(wisefyConnection.isDeviceConnectedToMobileNetwork())
    }

    /*
     * isDeviceConnectedToWifiNetwork tests
     */

    @Test fun isDeviceConnectedToWifiNetwork_failure_nullNetworkInfo() {
        assertFalse(wisefyConnection.isDeviceConnectedToWifiNetwork())
    }

    @Test fun isDeviceConnectedToWifiNetwork_failure_notAvailableOrConnected() {
        mockNetworkUtil.currentNetwork(TEST_SSID)
        mockNetworkUtil.currentNetworkConnectionStatus(false, false, WIFI)
        assertFalse(wisefyConnection.isDeviceConnectedToWifiNetwork())
    }

    @Test fun isDeviceConnectedToWifiNetwork_failure_notAvailable() {
        mockNetworkUtil.currentNetwork(TEST_SSID)
        mockNetworkUtil.currentNetworkConnectionStatus(false, true, WIFI)
        assertFalse(wisefyConnection.isDeviceConnectedToWifiNetwork())
    }

    @Test fun isDeviceConnectedToWifiNetwork_failure_notConnected() {
        mockNetworkUtil.currentNetwork(TEST_SSID)
        mockNetworkUtil.currentNetworkConnectionStatus(true, false, WIFI)
        assertFalse(wisefyConnection.isDeviceConnectedToWifiNetwork())
    }

    @Test fun isDeviceConnectedToWifiNetwork_failure_nullTypeName() {
        mockNetworkUtil.currentNetwork(TEST_SSID)
        mockNetworkUtil.currentNetworkConnectionStatus(true, true, null)
        assertFalse(wisefyConnection.isDeviceConnectedToWifiNetwork())
    }

    @Test fun isDeviceConnectedToWifiNetwork_failure_differentTypeName() {
        mockNetworkUtil.currentNetwork(TEST_SSID)
        mockNetworkUtil.currentNetworkConnectionStatus(true, true, MOBILE)
        assertFalse(wisefyConnection.isDeviceConnectedToWifiNetwork())
    }

    @Test fun isDeviceConnectedToWifiNetwork_failure_differentTypeName_differentCase() {
        mockNetworkUtil.currentNetwork(TEST_SSID)
        mockNetworkUtil.currentNetworkConnectionStatus(true, true, "mobile")
        assertFalse(wisefyConnection.isDeviceConnectedToWifiNetwork())
    }

    @Test fun isDeviceConnectedToWifiNetwork_success() {
        mockNetworkUtil.currentNetwork(TEST_SSID)
        mockNetworkUtil.currentNetworkConnectionStatus(true, true, WIFI)
        assertTrue(wisefyConnection.isDeviceConnectedToWifiNetwork())
    }

    @Test fun isDeviceConnectedToWifiNetwork_success_differentCase() {
        mockNetworkUtil.currentNetwork(TEST_SSID)
        mockNetworkUtil.currentNetworkConnectionStatus(true, true, "wifi")
        assertTrue(wisefyConnection.isDeviceConnectedToWifiNetwork())
    }

    /*
     * isDeviceRoaming tests
     */

    @Test fun isDeviceRoaming_failure_nullNetworkInfo() {
        assertFalse(wisefyConnection.isDeviceRoaming())
    }

    @Test fun isDeviceRoaming_failure() {
        mockNetworkUtil.currentNetwork(TEST_SSID)
        mockNetworkUtil.isDeviceRoaming(false)
        assertFalse(wisefyConnection.isDeviceRoaming())
    }

    @Test fun isDeviceRoaming_success() {
        mockNetworkUtil.currentNetwork(TEST_SSID)
        mockNetworkUtil.isDeviceRoaming(true)
        assertTrue(wisefyConnection.isDeviceRoaming())
    }

    /*
     * waitToConnectToSSID tests
     */

    @Test fun waitToConnectToSSID_failure_nullSSIDParam() {
        assertFalse(wisefyConnection.waitToConnectToSSID(null, TEST_TIMEOUT))
    }

    @Test fun waitToConnectToSSID_failure_nullConnectionInfo() {
        mockNetworkUtil.currentNetwork_null()
        assertFalse(wisefyConnection.waitToConnectToSSID(TEST_SSID, TEST_TIMEOUT))
    }

    @Test fun waitToConnectToSSID_failure_nullSSID() {
        mockNetworkUtil.currentNetwork(null)
        assertFalse(wisefyConnection.waitToConnectToSSID(null, TEST_TIMEOUT))
    }

    @Test fun waitToConnectToSSID_failure_differentSSID() {
        mockNetworkUtil.currentNetworkConnectionStatus(true, true, TEST_TYPE1)
        mockNetworkUtil.currentNetwork(TEST_SSID2)
        assertFalse(wisefyConnection.waitToConnectToSSID(TEST_SSID, TEST_TIMEOUT))
    }

    @Test fun waitToConnectToSSID_failure_notAvailable() {
        mockNetworkUtil.currentNetworkConnectionStatus(false, true, TEST_TYPE1)
        mockNetworkUtil.currentNetwork(TEST_SSID)
        assertFalse(wisefyConnection.waitToConnectToSSID(TEST_SSID, TEST_TIMEOUT))
    }

    @Test fun waitToConnectToSSID_failure_notAvailableOrConnected() {
        mockNetworkUtil.currentNetworkConnectionStatus(false, false, TEST_TYPE1)
        mockNetworkUtil.currentNetwork(TEST_SSID)
        assertFalse(wisefyConnection.waitToConnectToSSID(TEST_SSID, TEST_TIMEOUT))
    }

    @Test fun waitToConnectToSSID_failure_notConnected() {
        mockNetworkUtil.currentNetworkConnectionStatus(true, false, TEST_TYPE1)
        mockNetworkUtil.currentNetwork(TEST_SSID)
        assertFalse(wisefyConnection.waitToConnectToSSID(TEST_SSID, TEST_TIMEOUT))
    }

    @Test fun waitToConnectToSSID_success() {
        mockNetworkUtil.currentNetworkConnectionStatus(true, true, TEST_TYPE1)
        mockNetworkUtil.currentNetwork(TEST_SSID)
        assertTrue(wisefyConnection.waitToConnectToSSID(TEST_SSID, TEST_TIMEOUT))
    }
}
