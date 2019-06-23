package com.isupatches.wisefy.internal

import android.net.ConnectivityManager
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiManager
import com.isupatches.wisefy.VERIFICATION_FAILURE_TIMEOUT
import com.isupatches.wisefy.VERIFICATION_SUCCESS_TIMEOUT

import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyBoolean
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mockito.after
import org.mockito.Mockito.atLeastOnce
import org.mockito.Mockito.timeout
import org.mockito.Mockito.verify

/**
 * A helper class with common logic to verify operations with WifiManager.
 *
 * @see WifiManager
 *
 * @author Patches
 * @since 3.0
 */
internal class VerificationUtil internal constructor(
    private val mockConnectivityManager: ConnectivityManager,
    private val mockWifiManager: WifiManager
) {

    /*
     * Anti-methods (checking for things TO NOT happen)
     */

    /**
     * To verify no attempt to add a network was made.
     *
     * @see WifiManager.addNetwork
     *
     * @author Patches
     * @since 3.0
     */
    fun didNoTryToAddNetwork() {
        verify(mockWifiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).addNetwork(any(WifiConfiguration::class.java))
    }

    /**
     * To verify no attempt attempt to connect to a network was made.
     *
     * @see WifiManager.enableNetwork
     * @see WifiManager.reconnect
     *
     * @author Patches
     * @since 3.0
     */
    fun didNotTryToConnectToNetwork() {
        verify(mockWifiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).enableNetwork(anyInt(), anyBoolean())
        verify(mockWifiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).reconnect()
    }

    /**
     * To verify no attempt to disable wifi was made.
     *
     * @see WifiManager.setWifiEnabled
     *
     * @author Patches
     * @since 3.0
     */
    fun didNotTryToDisableWifi() {
        verify(mockWifiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).isWifiEnabled = false
    }

    /**
     * To verify no attempt to disconnect the device from it's current network was made.
     *
     * @see WifiManager.disconnect
     *
     * @author Patches
     * @since 3.0
     */
    fun didNotTryToDisconnectFromCurrentNetwork() {
        verify(mockWifiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).disconnect()
    }

    /**
     * To verify no attempt to enable wifi was made.
     *
     * @see WifiManager.setWifiEnabled
     *
     * @author Patches
     * @since 3.0
     */
    fun didNotTryToEnableWifi() {
        verify(mockWifiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).isWifiEnabled = true
    }

    /**
     * To verify no attempt to get the device's current network was made.
     *
     * @see WifiManager.getConnectionInfo
     *
     * @author Patches
     * @since 3.0
     */
    fun didNotTryToGetCurrentNetwork() {
        verify(mockWifiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).connectionInfo
    }

    /**
     * To verify no attempt to get the device's current network info was made.
     *
     * @see ConnectivityManager.getActiveNetworkInfo
     *
     * @author Patches
     * @since 3.0
     */
    fun didNotTryToGetCurrentNetworkInfo() {
        verify(mockConnectivityManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).activeNetworkInfo
    }

    /**
     * To verify no attempt to get saved networks was made.
     *
     * @see WifiManager.getConfiguredNetworks
     *
     * @author Patches
     * @since 3.0
     */
    fun didNotTryToGetSavedNetworks() {
        verify(mockWifiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).configuredNetworks
    }

    /**
     * To verify no attempt to scan for nearby access points was made.
     *
     * @see WifiManager.startScan
     *
     * @author Patches
     * @since 3.0
     */
    @Suppress("DEPRECATION")
    fun didNotTryToScanForAccessPoints() {
        verify(mockWifiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).startScan()
    }

    /**
     * To verify no attempt to remove a network was made.
     *
     * @see WifiManager.removeNetwork
     *
     * @author Patches
     * @since 3.0
     */
    fun didNotTryToRemoveNetwork() {
        verify(mockWifiManager, after(VERIFICATION_FAILURE_TIMEOUT).times(0)).removeNetwork(anyInt())
    }

    /*
     * Positive-methods (checking for things TO happen)
     */

    /**
     * To verify no attempt to add a network was made.
     *
     * @see WifiManager.addNetwork
     *
     * @author Patches
     * @since 3.0
     */
    fun triedToAddNetwork() {
        verify(mockWifiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).addNetwork(any(WifiConfiguration::class.java))
    }

    /**
     * To verify an attempt to connect to a network was made.
     *
     * @see WifiManager.enableNetwork
     * @see WifiManager.reconnect
     *
     * @author Patches
     * @since 3.0
     */
    fun triedToConnectToNetwork() {
        verify(mockWifiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).enableNetwork(anyInt(), anyBoolean())
        verify(mockWifiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).reconnect()
    }

    /**
     * To verify an attempt to disable wifi was made.
     *
     * @see WifiManager.setWifiEnabled
     *
     * @author Patches
     * @since 3.0
     */
    fun triedToDisableWifi() {
        verify(mockWifiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).isWifiEnabled = false
    }

    /**
     * To verify an attempt to disconnect the device from it's current network was made.
     *
     * @see WifiManager.disconnect
     *
     * @author Patches
     * @since 3.0
     */
    fun triedToDisconnectFromCurrentNetwork() {
        verify(mockWifiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).disconnect()
    }

    /**
     * To verify an attempt to enable wifi was made.
     *
     * @see WifiManager.setWifiEnabled
     *
     * @author Patches
     * @since 3.0
     */
    fun triedToEnableWifi() {
        verify(mockWifiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).isWifiEnabled = true
    }

    /**
     * To verify an attempt to get the device's current network was made.
     *
     * @see WifiManager.getConnectionInfo
     *
     * @author Patches
     * @since 3.0
     */
    fun triedToGetCurrentNetwork() {
        verify(mockWifiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).connectionInfo
    }

    /**
     * To verify an attempt to get the device's current network info was made.
     *
     * @see ConnectivityManager.getActiveNetworkInfo
     *
     * @author Patches
     * @since 3.0
     */
    fun triedToGetCurrentNetworkInfo() {
        verify(mockConnectivityManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).activeNetworkInfo
    }

    /**
     * To verify an attempt to get nearby access points was made.
     *
     * @see WifiManager.getScanResults
     *
     * @author Patches
     * @since 3.0
     */
    fun triedToGetNearbyAccessPoints() {
        verify(mockWifiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).scanResults
    }

    /**
     * To verify an attempt to get saved networks was made.
     *
     * @see WifiManager.getConfiguredNetworks
     *
     * @author Patches
     * @since 3.0
     */
    fun triedToGetSavedNetworks() {
        verify(mockWifiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).configuredNetworks
    }

    /**
     * To verify an attempt to remove a network was made.
     *
     * @see WifiManager.removeNetwork
     *
     * @author Patches
     * @since 3.0
     */
    fun triedToRemoveNetwork() {
        verify(mockWifiManager, timeout(VERIFICATION_SUCCESS_TIMEOUT)).removeNetwork(anyInt())
    }

    /**
     * To verify an attempt to scan for nearby access points was made.
     *
     * @see WifiManager.removeNetwork
     *
     * @author Patches
     * @since 3.0
     */
    @Suppress("DEPRECATION")
    fun triedToScanForAccessPoints() {
        verify(mockWifiManager, atLeastOnce()).startScan()
    }
}
