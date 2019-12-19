package com.isupatches.wisefy.connection

import com.isupatches.wisefy.TEST_SSID
import com.isupatches.wisefy.TEST_SSID2
import com.isupatches.wisefy.TEST_TIMEOUT
import com.isupatches.wisefy.constants.MOBILE
import com.isupatches.wisefy.constants.WIFI
import com.isupatches.wisefy.internal.base.BaseInstrumentationTest
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * Used to test the WiseFyConnectionLegacy class and functionality determining various connection states.
 *
 * @see WiseFyConnectionLegacy
 *
 * @author Patches
 * @since 4.0
 */
internal class WiseFyConnectionLegacyTests : BaseInstrumentationTest() {

    private val wisefyConnection = WiseFyConnectionLegacy.create(
        mockConnectivityManager,
        mockWifiManager
    )

    @Before fun setUp() {
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
        mockNetworkUtil.currentNetworkConnectionStatus(isAvailable = true, isConnected = true, type = null)
        assertFalse(wisefyConnection.isCurrentNetworkConnectedToSSID(TEST_SSID))
    }

    @Test fun isCurrentNetworkConnectedToSSID_failure_emptySSID() {
        mockNetworkUtil.currentNetwork("")
        mockNetworkUtil.currentNetworkConnectionStatus(isAvailable = true, isConnected = true, type = null)
        assertFalse(wisefyConnection.isCurrentNetworkConnectedToSSID(TEST_SSID))
    }

    @Test fun isCurrentNetworkConnectedToSSID_failure_differentSSID() {
        mockNetworkUtil.currentNetwork(TEST_SSID2)
        mockNetworkUtil.currentNetworkConnectionStatus(isAvailable = true, isConnected = true, type = null)
        assertFalse(wisefyConnection.isCurrentNetworkConnectedToSSID(TEST_SSID))
    }

    @Test fun isCurrentNetworkConnectedToSSID_failure_notAvailable() {
        mockNetworkUtil.currentNetwork(TEST_SSID)
        mockNetworkUtil.currentNetworkConnectionStatus(isAvailable = false, isConnected = true, type = null)
        assertFalse(wisefyConnection.isCurrentNetworkConnectedToSSID(TEST_SSID))
    }

    @Test fun isCurrentNetworkConnectedToSSID_failure_notAvailableOrConnected() {
        mockNetworkUtil.currentNetwork(TEST_SSID)
        mockNetworkUtil.currentNetworkConnectionStatus(isAvailable = false, isConnected = false, type = null)
        assertFalse(wisefyConnection.isCurrentNetworkConnectedToSSID(TEST_SSID))
    }

    @Test fun isCurrentNetworkConnectedToSSID_failure_notConnected() {
        mockNetworkUtil.currentNetwork(TEST_SSID)
        mockNetworkUtil.currentNetworkConnectionStatus(isAvailable = true, isConnected = false, type = null)
        assertFalse(wisefyConnection.isCurrentNetworkConnectedToSSID(TEST_SSID))
    }

    @Test fun isCurrentNetworkConnectedToSSID_success() {
        mockNetworkUtil.currentNetwork(TEST_SSID)
        mockNetworkUtil.currentNetworkConnectionStatus(isAvailable = true, isConnected = false, type = null)
        assertTrue(wisefyConnection.isCurrentNetworkConnectedToSSID(TEST_SSID))
    }

    /*
     * isNetworkConnected tests
     */

    @Test fun isNetworkConnected_failure_nullNetworkInfoParam() {
        assertFalse(wisefyConnection.isNetworkConnected())
    }

    @Test fun isNetworkConnected_failure_notAvailable() {
        mockNetworkUtil.currentNetworkConnectionStatus(isAvailable = false, isConnected = true, type = null)
        assertFalse(wisefyConnection.isNetworkConnected())
    }

    @Test fun isNetworkConnected_failure_notAvailableOrConnected() {
        mockNetworkUtil.currentNetworkConnectionStatus(isAvailable = false, isConnected = false, type = null)
        assertFalse(wisefyConnection.isNetworkConnected())
    }

    @Test fun isNetworkConnected_failure_notConnected() {
        mockNetworkUtil.currentNetworkConnectionStatus(isAvailable = true, isConnected = false, type = null)
        assertFalse(wisefyConnection.isNetworkConnected())
    }

    @Test fun isNetworkConnected_success() {
        mockNetworkUtil.currentNetworkConnectionStatus(isAvailable = true, isConnected = true, type = null)
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
        mockNetworkUtil.currentNetworkConnectionStatus(isAvailable = false, isConnected = false, type = MOBILE)
        assertFalse(wisefyConnection.isDeviceConnectedToMobileNetwork())
    }

    @Test fun isDeviceConnectedToMobileNetwork_failure_notAvailable() {
        mockNetworkUtil.currentNetwork(TEST_SSID)
        mockNetworkUtil.currentNetworkConnectionStatus(isAvailable = false, isConnected = true, type = MOBILE)
        assertFalse(wisefyConnection.isDeviceConnectedToMobileNetwork())
    }

    @Test fun isDeviceConnectedToMobileNetwork_failure_notConnected() {
        mockNetworkUtil.currentNetwork(TEST_SSID)
        mockNetworkUtil.currentNetworkConnectionStatus(isAvailable = true, isConnected = false, type = MOBILE)
        assertFalse(wisefyConnection.isDeviceConnectedToMobileNetwork())
    }

    @Test fun isDeviceConnectedToMobileNetwork_failure_nullTypeName() {
        mockNetworkUtil.currentNetwork(TEST_SSID)
        mockNetworkUtil.currentNetworkConnectionStatus(isAvailable = true, isConnected = true, type = null)
        assertFalse(wisefyConnection.isDeviceConnectedToMobileNetwork())
    }

    @Test fun isDeviceConnectedToMobileNetwork_failure_differentTypeName() {
        mockNetworkUtil.currentNetwork(TEST_SSID)
        mockNetworkUtil.currentNetworkConnectionStatus(isAvailable = true, isConnected = true, type = WIFI)
        assertFalse(wisefyConnection.isDeviceConnectedToMobileNetwork())
    }

    @Test fun isDeviceConnectedToMobileNetwork_failure_differentTypeName_differentCase() {
        mockNetworkUtil.currentNetwork(TEST_SSID)
        mockNetworkUtil.currentNetworkConnectionStatus(isAvailable = true, isConnected = true, type = "wifi")
        assertFalse(wisefyConnection.isDeviceConnectedToMobileNetwork())
    }

    @Test fun isDeviceConnectedToMobileNetwork_success() {
        mockNetworkUtil.currentNetwork(TEST_SSID)
        mockNetworkUtil.currentNetworkConnectionStatus(isAvailable = true, isConnected = true, type = null)
        assertTrue(wisefyConnection.isDeviceConnectedToMobileNetwork())
    }

    @Test fun isDeviceConnectedToMobileNetwork_success_differentCase() {
        mockNetworkUtil.currentNetwork(TEST_SSID)
        mockNetworkUtil.currentNetworkConnectionStatus(isAvailable = true, isConnected = true, type = "mobile")
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
        mockNetworkUtil.currentNetworkConnectionStatus(isAvailable = false, isConnected = false, type = WIFI)
        assertFalse(wisefyConnection.isDeviceConnectedToWifiNetwork())
    }

    @Test fun isDeviceConnectedToWifiNetwork_failure_notAvailable() {
        mockNetworkUtil.currentNetwork(TEST_SSID)
        mockNetworkUtil.currentNetworkConnectionStatus(isAvailable = false, isConnected = true, type = WIFI)
        assertFalse(wisefyConnection.isDeviceConnectedToWifiNetwork())
    }

    @Test fun isDeviceConnectedToWifiNetwork_failure_notConnected() {
        mockNetworkUtil.currentNetwork(TEST_SSID)
        mockNetworkUtil.currentNetworkConnectionStatus(isAvailable = true, isConnected = false, type = WIFI)
        assertFalse(wisefyConnection.isDeviceConnectedToWifiNetwork())
    }

    @Test fun isDeviceConnectedToWifiNetwork_failure_nullTypeName() {
        mockNetworkUtil.currentNetwork(TEST_SSID)
        mockNetworkUtil.currentNetworkConnectionStatus(isAvailable = true, isConnected = true, type = null)
        assertFalse(wisefyConnection.isDeviceConnectedToWifiNetwork())
    }

    @Test fun isDeviceConnectedToWifiNetwork_failure_differentTypeName() {
        mockNetworkUtil.currentNetwork(TEST_SSID)
        mockNetworkUtil.currentNetworkConnectionStatus(isAvailable = true, isConnected = true, type = MOBILE)
        assertFalse(wisefyConnection.isDeviceConnectedToWifiNetwork())
    }

    @Test fun isDeviceConnectedToWifiNetwork_failure_differentTypeName_differentCase() {
        mockNetworkUtil.currentNetwork(TEST_SSID)
        mockNetworkUtil.currentNetworkConnectionStatus(isAvailable = true, isConnected = true, type = "mobile")
        assertFalse(wisefyConnection.isDeviceConnectedToWifiNetwork())
    }

    @Test fun isDeviceConnectedToWifiNetwork_success() {
        mockNetworkUtil.currentNetwork(TEST_SSID)
        mockNetworkUtil.currentNetworkConnectionStatus(isAvailable = true, isConnected = true, type = WIFI)
        assertTrue(wisefyConnection.isDeviceConnectedToWifiNetwork())
    }

    @Test fun isDeviceConnectedToWifiNetwork_success_differentCase() {
        mockNetworkUtil.currentNetwork(TEST_SSID)
        mockNetworkUtil.currentNetworkConnectionStatus(isAvailable = true, isConnected = true, type = "wifi")
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
        mockNetworkUtil.currentNetworkConnectionStatus(isAvailable = true, isConnected = true, type = WIFI)
        mockNetworkUtil.currentNetwork(TEST_SSID2)
        assertFalse(wisefyConnection.waitToConnectToSSID(TEST_SSID, TEST_TIMEOUT))
    }

    @Test fun waitToConnectToSSID_failure_notAvailable() {
        mockNetworkUtil.currentNetworkConnectionStatus(isAvailable = false, isConnected = true, type = WIFI)
        mockNetworkUtil.currentNetwork(TEST_SSID)
        assertFalse(wisefyConnection.waitToConnectToSSID(TEST_SSID, TEST_TIMEOUT))
    }

    @Test fun waitToConnectToSSID_failure_notAvailableOrConnected() {
        mockNetworkUtil.currentNetworkConnectionStatus(isAvailable = false, isConnected = false, type = WIFI)
        mockNetworkUtil.currentNetwork(TEST_SSID)
        assertFalse(wisefyConnection.waitToConnectToSSID(TEST_SSID, TEST_TIMEOUT))
    }

    @Test fun waitToConnectToSSID_failure_notConnected() {
        mockNetworkUtil.currentNetworkConnectionStatus(isAvailable = true, isConnected = false, type = WIFI)
        mockNetworkUtil.currentNetwork(TEST_SSID)
        assertFalse(wisefyConnection.waitToConnectToSSID(TEST_SSID, TEST_TIMEOUT))
    }

    @Test fun waitToConnectToSSID_success() {
        mockNetworkUtil.currentNetworkConnectionStatus(isAvailable = true, isConnected = true, type = WIFI)
        mockNetworkUtil.currentNetwork(TEST_SSID)
        assertTrue(wisefyConnection.waitToConnectToSSID(TEST_SSID, TEST_TIMEOUT))
    }
}
