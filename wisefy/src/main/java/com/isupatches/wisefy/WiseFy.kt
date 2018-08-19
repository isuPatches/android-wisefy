/*
 * Copyright 2018 Patches Klinefelter
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
package com.isupatches.wisefy

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.Manifest.permission.CHANGE_WIFI_STATE
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.wifi.ScanResult
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Handler
import android.support.annotation.RequiresApi
import android.support.annotation.RequiresPermission

import com.isupatches.wisefy.annotations.Async
import com.isupatches.wisefy.annotations.CallingThread
import com.isupatches.wisefy.annotations.PublicAPI
import com.isupatches.wisefy.annotations.Sync
import com.isupatches.wisefy.annotations.WaitsForTimeout
import com.isupatches.wisefy.annotations.WiseFyThread
import com.isupatches.wisefy.callbacks.AddNetworkCallbacks
import com.isupatches.wisefy.callbacks.ConnectToNetworkCallbacks
import com.isupatches.wisefy.callbacks.DisableWifiCallbacks
import com.isupatches.wisefy.callbacks.DisconnectFromCurrentNetworkCallbacks
import com.isupatches.wisefy.callbacks.EnableWifiCallbacks
import com.isupatches.wisefy.callbacks.GetCurrentNetworkCallbacks
import com.isupatches.wisefy.callbacks.GetCurrentNetworkInfoCallbacks
import com.isupatches.wisefy.callbacks.GetFrequencyCallbacks
import com.isupatches.wisefy.callbacks.GetIPCallbacks
import com.isupatches.wisefy.callbacks.GetNearbyAccessPointsCallbacks
import com.isupatches.wisefy.callbacks.GetRSSICallbacks
import com.isupatches.wisefy.callbacks.GetSavedNetworkCallbacks
import com.isupatches.wisefy.callbacks.GetSavedNetworksCallbacks
import com.isupatches.wisefy.callbacks.RemoveNetworkCallbacks
import com.isupatches.wisefy.callbacks.SearchForAccessPointCallbacks
import com.isupatches.wisefy.callbacks.SearchForAccessPointsCallbacks
import com.isupatches.wisefy.callbacks.SearchForSSIDCallbacks
import com.isupatches.wisefy.callbacks.SearchForSSIDsCallbacks
import com.isupatches.wisefy.constants.Capability
import com.isupatches.wisefy.constants.EAP
import com.isupatches.wisefy.constants.MISSING_PARAMETER
import com.isupatches.wisefy.constants.MOBILE
import com.isupatches.wisefy.constants.PSK
import com.isupatches.wisefy.constants.WEP
import com.isupatches.wisefy.constants.WIFI
import com.isupatches.wisefy.constants.WPA
import com.isupatches.wisefy.constants.WPA2
import com.isupatches.wisefy.logging.WiseFyLogger
import com.isupatches.wisefy.threads.WiseFyHandlerThread
import com.isupatches.wisefy.utils.generateOpenNetworkConfiguration
import com.isupatches.wisefy.utils.generateWEPNetworkConfiguration
import com.isupatches.wisefy.utils.generateWPA2NetworkConfiguration

import java.math.BigInteger
import java.net.InetAddress
import java.net.UnknownHostException

/**
 * Main class for WiseFy that provides a synchronous and asynchronous API to manipulate and query
 * for different parts of a device's wifi configuration and status.
 *
 * <p>Uses the builder pattern for creation - {@link [Brains]}</p>
 *
 * @author Patches
 * @since 3.0
 */
@PublicAPI
@Suppress("LargeClass")
class WiseFy private constructor(
    private val connectivityManager: ConnectivityManager,
    private val wifiManager: WifiManager,
    private val wisefyConnection: WiseFyConnection,
    private val wisefyPrechecks: WiseFyPrechecks,
    private val wisefySearch: WiseFySearch
) : WiseFyPublicApi {

    companion object {
        private val TAG = WiseFy::class.java.simpleName

        /**
         * The default error return from [WifiManager]
         *
         * @see [WifiManager]
         *
         * @author Patches
         * @since 3.0
         */
        const val WIFI_MANAGER_FAILURE: Int = -1

        /**
         * The minimum value possible for a 5gHz network.
         *
         * @author Patches
         * @since 3.0
         */
        const val MIN_FREQUENCY_5GHZ: Int = 4900

        /**
         * The max value possible for a 5gHz network.
         *
         * @author Patches
         * @since 3.0
         */
        const val MAX_FREQUENCY_5GHZ: Int = 5900
    }

    private val wisefyLock = WiseFyLock()

    private var wisefyHandlerThread: WiseFyHandlerThread? = null
    private var wisefyHandler: Handler? = null

    /**
     * The Builder class for WiseFy.
     *
     * @author Patches
     * @since 3.0
     */
    class Brains(context: Context) {

        private var loggingEnabled: Boolean = false
        private var connectivityManager: ConnectivityManager
        private var wifiManager: WifiManager
        private var wisefyConnection: WiseFyConnection
        private var wisefyPrechecks: WiseFyPrechecks
        private var wisefySearch: WiseFySearch

        init {
            connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
            wisefyConnection = WiseFyConnectionImpl.create(connectivityManager, wifiManager)
            wisefySearch = WiseFySearchImpl.create(wifiManager)
            wisefyPrechecks = WiseFyPrechecksImpl.create(wisefySearch)
        }

        /**
         * Used to enable/or disable logging for a WiseFy instance.
         *
         * @param loggingEnabled If logging should be enabled for the WiseFy instance.
         *
         * @author Patches
         * @since 3.0
         */
        fun logging(loggingEnabled: Boolean): Brains = apply { this.loggingEnabled = loggingEnabled }

        /**
         * Used internally to set ConnectivityManager in tests.
         *
         * @param connectivityManager The custom ConnectivityManager to use (can be a mock)
         *
         * @see [ConnectivityManager]
         *
         * @author Patches
         * @since 3.0
         */
        internal fun customConnectivityManager(connectivityManager: ConnectivityManager): Brains = apply {
            this.connectivityManager = connectivityManager
        }

        /**
         * Used internally to set WifiManager in tests.
         *
         * @param wifiManager The custom WifiManager to use (can be a mock)
         *
         * @see [WifiManager]
         *
         * @author Patches
         * @since 3.0
         */
        internal fun customWifiManager(wifiManager: WifiManager): Brains = apply {
            this.wifiManager = wifiManager
        }

        /**
         * Used internally to set WiseFyConnection in tests.
         *
         * @param wisefyConnection The custom WiseFyConnection to use (can be a mock or custom interface implementation)
         *
         * @see [WiseFyConnection]
         *
         * @author Patches
         * @since 3.0
         */
        internal fun customWiseFyConnection(wisefyConnection: WiseFyConnection): Brains = apply {
            this.wisefyConnection = wisefyConnection
        }

        /**
         * Used internally to set WiseFyPrechecks in tests.
         *
         * @param wisefyPrechecks The custom WiseFyPrechecks to use (can be a mock or custom interface implementation)
         *
         * @see [WiseFyPrechecks]
         *
         * @author Patches
         * @since 3.0
         */
        internal fun customWiseFyPrechecks(wisefyPrechecks: WiseFyPrechecks): Brains = apply {
            this.wisefyPrechecks = wisefyPrechecks
        }

        /**
         * Used internally to set WiseFySearch in tests.
         *
         * @param wisefySearch The custom WisefySearch to use (can be a mock or custom interface implementation)
         *
         * @see [WiseFySearch]
         *
         * @author Patches
         * @since 3.0
         */
        internal fun customWiseFySearch(wisefySearch: WiseFySearch): Brains = apply {
            this.wisefySearch = wisefySearch
        }

        /**
         * Uses a private constructor and returns a WiseFy instance.
         *
         * @see [WiseFyLogger.configureWiseFyLoggerImplementation]
         *
         * @author Patches
         * @since 3.0
         */
        fun getSmarts(): WiseFy {
            WiseFyLogger.configureWiseFyLoggerImplementation(loggingEnabled)
            return WiseFy(
                connectivityManager = connectivityManager,
                wifiManager = wifiManager,
                wisefyConnection = wisefyConnection,
                wisefyPrechecks = wisefyPrechecks,
                wisefySearch = wisefySearch
            )
        }
    }

    /**
     * To add an open network to the user's configured network list.
     *
     * @param ssid The ssid of the open network you want to add
     *
     * @return int - The return code from WifiManager for network creation (-1 for failure)
     *
     * @see [addNetworkConfiguration]
     * @see [generateOpenNetworkConfiguration]
     * @see [WiseFyPrechecks.addNetworkPrechecks]
     *
     * @author Patches
     * @since 3.0
     */
    @Sync
    @CallingThread
    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun addOpenNetwork(ssid: String?): Int {
        val precheck = wisefyPrechecks.addNetworkPrechecks(ssid)
        return if (precheck.failed()) {
            precheck.code
        } else addNetworkConfiguration(generateOpenNetworkConfiguration(ssid!!))
    }

    /**
     * To add an open network to the user's configured network list.
     *
     * @param ssid The ssid of the open network you want to add
     * @param callbacks The listener to return results to
     *
     * @see [addNetworkConfiguration]
     * @see [AddNetworkCallbacks]
     * @see [generateOpenNetworkConfiguration]
     * @see [runOnWiseFyThread]
     * @see [WiseFyLock]
     * @see [WiseFyPrechecks.addNetworkPrechecks]
     *
     * @author Patches
     * @since 3.0
     */
    @Async
    @WiseFyThread
    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun addOpenNetwork(ssid: String?, callbacks: AddNetworkCallbacks?) {
        runOnWiseFyThread(Runnable {
            synchronized(wisefyLock) {
                val precheck = wisefyPrechecks.addNetworkPrechecks(ssid)
                if (precheck.failed()) {
                    callbacks?.wisefyFailure(precheck.code)
                    return@Runnable
                }

                val openNetworkConfiguration = generateOpenNetworkConfiguration(ssid!!)
                val result = addNetworkConfiguration(openNetworkConfiguration)
                if (result != WIFI_MANAGER_FAILURE) {
                    callbacks?.networkAdded(result, openNetworkConfiguration)
                } else {
                    callbacks?.failureAddingNetwork(result)
                }
            }
        })
    }

    /**
     * To add a WEP network to the user's configured network list.
     *
     * @param ssid The ssid of the WEP network you want to add
     * @param password The password for the WEP network being added
     *
     * @return int - The return code from WifiManager for network creation (-1 for failure)
     *
     * @see [addNetworkConfiguration]
     * @see [generateWEPNetworkConfiguration]
     * @see [WiseFyPrechecks.addNetworkPrechecks]
     *
     * @author Patches
     * @since 3.0
     */
    @Sync
    @CallingThread
    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun addWEPNetwork(ssid: String?, password: String?): Int {
        val precheck = wisefyPrechecks.addNetworkPrechecks(ssid, password)
        return if (precheck.failed()) {
            precheck.code
        } else addNetworkConfiguration(generateWEPNetworkConfiguration(ssid!!, password!!))
    }

    /**
     * To add a WEP network to the user's configured network list.
     *
     * @param ssid The ssid of the WEP network you want to add
     * @param password The password for the WEP network being added
     * @param callbacks The listener to return results to
     *
     * @see [addNetworkConfiguration]
     * @see [AddNetworkCallbacks]
     * @see [generateWEPNetworkConfiguration]
     * @see [runOnWiseFyThread]
     * @see [WiseFyLock]
     * @see [WiseFyPrechecks.addNetworkPrechecks]
     *
     * @author Patches
     * @since 3.0
     */
    @Async
    @WiseFyThread
    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun addWEPNetwork(ssid: String?, password: String?, callbacks: AddNetworkCallbacks?) {
        runOnWiseFyThread(Runnable {
            synchronized(wisefyLock) {
                val precheck = wisefyPrechecks.addNetworkPrechecks(ssid, password)
                if (precheck.failed()) {
                    callbacks?.wisefyFailure(precheck.code)
                    return@Runnable
                }

                val wepNetworkConfiguration = generateWEPNetworkConfiguration(ssid!!, password!!)
                val result = addNetworkConfiguration(wepNetworkConfiguration)
                if (result != WIFI_MANAGER_FAILURE) {
                    callbacks?.networkAdded(result, wepNetworkConfiguration)
                } else {
                    callbacks?.failureAddingNetwork(result)
                }
            }
        })
    }

    /**
     * To add a WPA2 network to the user's configured network list.
     *
     * @param ssid The ssid of the WPA2 network you want to add
     * @param password The password for the WPA2 network being added
     *
     * @return int - The return code from WifiManager for network creation (-1 for failure)
     *
     * @see [addNetworkConfiguration]
     * @see [generateWPA2NetworkConfiguration]
     * @see [WiseFyPrechecks.addNetworkPrechecks]
     *
     * @author Patches
     * @since 3.0
     */
    @Sync
    @CallingThread
    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun addWPA2Network(ssid: String?, password: String?): Int {
        val precheck = wisefyPrechecks.addNetworkPrechecks(ssid, password)
        return if (precheck.failed()) {
            precheck.code
        } else addNetworkConfiguration(generateWPA2NetworkConfiguration(ssid!!, password!!))
    }

    /**
     * To add a WPA2 network to the user's configured network list.
     *
     * @param ssid The ssid of the WPA2 network you want to add
     * @param password The password for the WPA2 network being added
     * @param callbacks The listener to return results to
     *
     * @see [addNetworkConfiguration]
     * @see [AddNetworkCallbacks]
     * @see [generateWPA2NetworkConfiguration]
     * @see [runOnWiseFyThread]
     * @see [WiseFyLock]
     * @see [WiseFyPrechecks.addNetworkPrechecks]
     *
     * @author Patches
     * @since 3.0
     */
    @Async
    @WiseFyThread
    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun addWPA2Network(ssid: String?, password: String?, callbacks: AddNetworkCallbacks?) {
        runOnWiseFyThread(Runnable {
            synchronized(wisefyLock) {
                val precheck = wisefyPrechecks.addNetworkPrechecks(ssid, password)
                if (precheck.failed()) {
                    callbacks?.wisefyFailure(precheck.code)
                    return@Runnable
                }

                val wpa2NetworkConfiguration = generateWPA2NetworkConfiguration(ssid!!, password!!)
                val result = addNetworkConfiguration(wpa2NetworkConfiguration)
                if (result != WIFI_MANAGER_FAILURE) {
                    callbacks?.networkAdded(result, wpa2NetworkConfiguration)
                } else {
                    callbacks?.failureAddingNetwork(result)
                }
            }
        })
    }

    /**
     * To convert an RSSI level for a network to a number of bars.
     *
     * @param rssiLevel The signal strength of the network
     * @param targetNumberOfBars How many bars or levels there will be total
     *
     * @return int - The number of bars for the given RSSI value
     *
     * @see [WifiManager.calculateSignalLevel]
     *
     * @author Patches
     * @since 3.0
     */
    @Sync
    @CallingThread
    override fun calculateBars(rssiLevel: Int, targetNumberOfBars: Int): Int =
        WifiManager.calculateSignalLevel(rssiLevel, targetNumberOfBars)

    /**
     * To compare the signal strength of two networks.
     *
     * This method will return:
     * - Negative value if the first signal is weaker than the second signal
     * - 0 if the two signals have the same strength
     * - Positive value if the first signal is stronger than the second signal
     *
     * @param rssi1 The signal strength of network 1
     * @param rssi2 The signal strength of network 2
     *
     * @return int - The result of the comparison
     *
     * @see [WifiManager.compareSignalLevel]
     *
     * @author Patches
     * @since 3.0
     */
    @Sync
    @CallingThread
    override fun compareSignalLevel(rssi1: Int, rssi2: Int): Int = WifiManager.compareSignalLevel(rssi1, rssi2)

    /**
     * Used to connect to a network.
     *
     *
     * *NOTE* Gets a list of saved networks, connects to the given ssid if found, and verifies connectivity.
     *
     * @param ssidToConnectTo The ssid to connect/reconnect to
     * @param timeoutInMillis The number of milliseconds to continue waiting for the device to connect to the given SSID
     *
     * @return boolean - If the network was successfully reconnected
     *
     * @see [connectToNetworkWithId]
     * @see [WiseFyConnection.waitToConnectToSSID]
     * @see [WiseFyPrechecks.connectToNetworkPrechecks]
     * @see [WiseFySearch.findSavedNetworkByRegex]
     *
     * @author Patches
     * @since 3.0
     */
    @Sync
    @CallingThread
    @WaitsForTimeout
    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun connectToNetwork(ssidToConnectTo: String?, timeoutInMillis: Int): Boolean {
        if (wisefyPrechecks.connectToNetworkPrechecks(ssidToConnectTo).failed()) {
            return false
        }

        val wifiConfiguration = wisefySearch.findSavedNetworkByRegex(ssidToConnectTo!!)
        if (wifiConfiguration != null) {
            connectToNetworkWithId(wifiConfiguration.networkId)
            return wisefyConnection.waitToConnectToSSID(ssidToConnectTo, timeoutInMillis)
        }

        return false
    }

    /**
     * Used to connect to a network.
     *
     * Gets a list of saved networks, connects to the given ssid if found, and verifies connectivity.
     *
     * @param ssidToConnectTo The ssid to connect/reconnect to
     * @param timeoutInMillis The number of milliseconds to continue waiting for the device to connect to the given SSID
     * @param callbacks The listener to return results to
     *
     * @see [connectToNetworkWithId]
     * @see [ConnectToNetworkCallbacks]
     * @see [runOnWiseFyThread]
     * @see [WiseFyConnection.waitToConnectToSSID]
     * @see [WiseFyLock]
     * @see [WiseFyPrechecks.connectToNetworkPrechecks]
     * @see [WiseFySearch.findSavedNetworkByRegex]
     *
     * @author Patches
     * @since 3.0
     */
    @Async
    @WiseFyThread
    @WaitsForTimeout
    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun connectToNetwork(
        ssidToConnectTo: String?,
        timeoutInMillis: Int,
        callbacks: ConnectToNetworkCallbacks?
    ) {
        runOnWiseFyThread(Runnable {
            synchronized(wisefyLock) {
                val precheck = wisefyPrechecks.connectToNetworkPrechecks(ssidToConnectTo)
                if (precheck.failed()) {
                    callbacks?.wisefyFailure(precheck.code)
                    return@Runnable
                }

                val wifiConfiguration = wisefySearch.findSavedNetworkByRegex(ssidToConnectTo!!)
                if (wifiConfiguration != null) {
                    connectToNetworkWithId(wifiConfiguration.networkId)
                    val connected = wisefyConnection.waitToConnectToSSID(ssidToConnectTo, timeoutInMillis)
                    if (connected) {
                        callbacks?.connectedToNetwork()
                    } else {
                        callbacks?.failureConnectingToNetwork()
                    }
                } else {
                    callbacks?.networkNotFoundToConnectTo()
                }
            }
        })
    }

    /**
     * To disable Wifi on a user's device.
     *
     * @return boolean - True if the command succeeded in disabling wifi
     *
     * @see [WifiManager.setWifiEnabled]
     * @see [WiseFyPrechecks.disableWifiChecks]
     *
     * @author Patches
     * @since 3.0
     */
    @Sync
    @CallingThread
    @RequiresPermission(CHANGE_WIFI_STATE)
    override fun disableWifi(): Boolean =
        wisefyPrechecks.disableWifiChecks().passed() && wifiManager.setWifiEnabled(false)

    /**
     * To disable Wifi on a user's device.
     *
     * @param callbacks The listener to return results to
     *
     * @see [DisableWifiCallbacks]
     * @see [runOnWiseFyThread]
     * @see [WifiManager.setWifiEnabled]
     * @see [WiseFyLock]
     * @see [WiseFyPrechecks.disableWifiChecks]
     *
     * @author Patches
     * @since 3.0
     */
    @Async
    @WiseFyThread
    @RequiresPermission(CHANGE_WIFI_STATE)
    override fun disableWifi(callbacks: DisableWifiCallbacks?) {
        runOnWiseFyThread(Runnable {
            synchronized(wisefyLock) {
                val precheck = wisefyPrechecks.disableWifiChecks()
                if (precheck.failed()) {
                    callbacks?.wisefyFailure(precheck.code)
                    return@Runnable
                }

                if (wifiManager.setWifiEnabled(false)) {
                    callbacks?.wifiDisabled()
                } else {
                    callbacks?.failureDisablingWifi()
                }
            }
        })
    }

    /**
     * To disconnect the user from their current network.
     *
     * @return boolean - If the command succeeded in disconnecting the device from the current network
     *
     * @see [WifiManager.disconnect]
     * @see [WiseFyPrechecks.disconnectFromCurrentNetworkChecks]
     *
     * @author Patches
     * @since 3.0
     */
    @Sync
    @CallingThread
    override fun disconnectFromCurrentNetwork(): Boolean =
        wisefyPrechecks.disconnectFromCurrentNetworkChecks().passed() && wifiManager.disconnect()

    /**
     * To disconnect the user from their current network.
     *
     * @param callbacks The listener to return results to
     *
     * @see [DisconnectFromCurrentNetworkCallbacks]
     * @see [runOnWiseFyThread]
     * @see [WifiManager.disconnect]
     * @see [WiseFyLock]
     * @see [WiseFyPrechecks.disconnectFromCurrentNetworkChecks
     *
     * @author Patches
     * @since 3.0
     */
    @Async
    @WiseFyThread
    override fun disconnectFromCurrentNetwork(callbacks: DisconnectFromCurrentNetworkCallbacks?) {
        runOnWiseFyThread(Runnable {
            synchronized(wisefyLock) {
                val precheck = wisefyPrechecks.disconnectFromCurrentNetworkChecks()
                if (precheck.failed()) {
                    callbacks?.wisefyFailure(precheck.code)
                    return@Runnable
                }

                if (wifiManager.disconnect()) {
                    callbacks?.disconnectedFromCurrentNetwork()
                } else {
                    callbacks?.failureDisconnectingFromCurrentNetwork()
                }
            }
        })
    }

    /**
     * Used to cleanup the thread started by WiseFy.
     *
     * @see WiseFyHandlerThread
     * @see WiseFyLock
     *
     * @author Patches
     * @since 3.0
     */
    override fun dump() {
        wisefyHandlerThread?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                it.quitSafely()
            } else {
                it.quit()
            }
            if (it.isAlive) {
                WiseFyLogger.warn(
                    TAG,
                    "WiseFy Thread is still alive.  Current status: isAlive(): %b, getState(): %s",
                    it.isAlive,
                    it.state
                )
                it.interrupt()
            }
            WiseFyLogger.debug(
                TAG,
                "WiseFy Thread isAlive(): %b, getState(): %s",
                it.isAlive,
                it.state
            )
            wisefyHandlerThread = null
        }
        wisefyHandler = null
        WiseFyLogger.debug(TAG, "Cleaned up WiseFy Thread")
    }

    /**
     * To enable Wifi on a user's device.
     *
     * @return boolean - If the command succeeded in enabling wifi
     *
     * @see [WifiManager.setWifiEnabled]
     * @see [WiseFyPrechecks.enableWifiChecks]
     *
     * @author Patches
     * @since 3.0
     */
    @Sync
    @CallingThread
    @RequiresPermission(CHANGE_WIFI_STATE)
    override fun enableWifi(): Boolean =
        wisefyPrechecks.enableWifiChecks().passed() && wifiManager.setWifiEnabled(true)

    /**
     * To enable Wifi on a user's device.
     *
     * @param callbacks The listener to return results to
     *
     * @see [EnableWifiCallbacks]
     * @see [runOnWiseFyThread]
     * @see [WifiManager.setWifiEnabled]
     * @see [WiseFyLock]
     * @see [WiseFyPrechecks.enableWifiChecks]
     *
     * @author Patches
     * @since 3.0
     */
    @Async
    @WiseFyThread
    @RequiresPermission(CHANGE_WIFI_STATE)
    override fun enableWifi(callbacks: EnableWifiCallbacks?) {
        runOnWiseFyThread(Runnable {
            synchronized(wisefyLock) {
                val precheck = wisefyPrechecks.enableWifiChecks()
                if (precheck.failed()) {
                    callbacks?.wisefyFailure(precheck.code)
                    return@Runnable
                }

                if (wifiManager.setWifiEnabled(true)) {
                    callbacks?.wifiEnabled()
                } else {
                    callbacks?.failureEnablingWifi()
                }
            }
        })
    }

    /**
     * To retrieve the user's current network.
     *
     * @return WifiInfo|null - The user's current network information
     *
     * @throws SecurityException Without necessary permissions granted
     *
     * @see [WifiInfo]
     * @see [WifiManager.getConnectionInfo]
     * @see [WiseFyPrechecks.getCurrentNetworkChecks]
     *
     * @author Patches
     * @since 3.0
     */
    @Sync
    @CallingThread
    @RequiresPermission(allOf = arrayOf(ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE))
    @Throws(SecurityException::class)
    override fun getCurrentNetwork(): WifiInfo? {
        return if (wisefyPrechecks.getCurrentNetworkChecks().passed()) {
            wifiManager.connectionInfo
        } else null
    }

    /**
     * To retrieve the user's current network.
     *
     * @param callbacks The listener to return results to
     *
     * @throws SecurityException Without necessary permissions granted
     *
     * @see [GetCurrentNetworkCallbacks]
     * @see [runOnWiseFyThread]
     * @see [WifiInfo]
     * @see [WifiManager.getConnectionInfo]
     * @see [WiseFyLock]
     * @see [WiseFyPrechecks.getCurrentNetworkChecks]
     *
     * @author Patches
     * @since 3.0
     */
    @Async
    @WiseFyThread
    @RequiresPermission(allOf = arrayOf(ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE))
    override fun getCurrentNetwork(callbacks: GetCurrentNetworkCallbacks?) {
        runOnWiseFyThread(Runnable {
            synchronized(wisefyLock) {
                val precheck = wisefyPrechecks.getCurrentNetworkChecks()
                if (precheck.failed()) {
                    callbacks?.wisefyFailure(precheck.code)
                    return@Runnable
                }

                callbacks?.retrievedCurrentNetwork(wifiManager.connectionInfo)
            }
        })
    }

    /**
     * To retrieve the details of the phones active network.
     *
     * @return NetworkInfo
     *
     * @see [ConnectivityManager.getActiveNetworkInfo]
     * @see [NetworkInfo]
     * @see [WiseFyPrechecks.getCurrentNetworkInfoChecks]
     *
     * @author Patches
     * @since 3.0
     */
    @Sync
    @CallingThread
    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getCurrentNetworkInfo(): NetworkInfo? {
        return if (wisefyPrechecks.getCurrentNetworkInfoChecks().passed()) {
            connectivityManager.activeNetworkInfo
        } else null
    }

    /**
     * To retrieve the details of the phones active network.
     *
     * @param callbacks The listener to return results to
     *
     * @see [ConnectivityManager.getActiveNetworkInfo]
     * @see [GetCurrentNetworkInfoCallbacks]
     * @see [NetworkInfo]
     * @see [runOnWiseFyThread]
     * @see [WiseFyLock]
     * @see [WiseFyPrechecks.getCurrentNetworkInfoChecks]
     *
     * @author Patches
     * @since 3.0
     */
    @Async
    @WiseFyThread
    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getCurrentNetworkInfo(callbacks: GetCurrentNetworkInfoCallbacks?) {
        runOnWiseFyThread(Runnable {
            synchronized(wisefyLock) {
                val precheck = wisefyPrechecks.getCurrentNetworkInfoChecks()
                if (precheck.failed()) {
                    callbacks?.wisefyFailure(precheck.code)
                    return@Runnable
                }

                callbacks?.retrievedCurrentNetworkInfo(connectivityManager.activeNetworkInfo)
            }
        })
    }

    /**
     * To retrieve the frequency of the device's current network.
     *
     * @return Integer - The frequency of the devices current network or null if no network
     *
     * @throws SecurityException Without necessary permissions granted
     *
     * @see [getCurrentNetwork]
     * @see [WifiInfo]
     * @see [WifiInfo.getFrequency]
     *
     * @author Patches
     * @since 3.0
     */
    @Sync
    @CallingThread
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresPermission(allOf = arrayOf(ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE))
    @Throws(SecurityException::class)
    override fun getFrequency(): Int? {
        val currentNetwork = getCurrentNetwork()
        return currentNetwork?.frequency
    }

    /**
     * To retrieve the frequency of the device's current network.
     *
     * @param callbacks The listener to return results to
     *
     * @throws SecurityException Without necessary permissions granted
     *
     * @see [getCurrentNetwork]
     * @see [GetFrequencyCallbacks]
     * @see [runOnWiseFyThread]
     * @see [WifiInfo]
     * @see [WifiInfo.getFrequency]
     * @see [WiseFyLock]
     *
     * @author Patches
     * @since 3.0
     */
    @Async
    @WiseFyThread
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresPermission(allOf = arrayOf(ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE))
    @Throws(SecurityException::class)
    override fun getFrequency(callbacks: GetFrequencyCallbacks?) {
        runOnWiseFyThread(Runnable {
            synchronized(wisefyLock) {
                val currentNetwork = getCurrentNetwork()
                if (currentNetwork != null) {
                    callbacks?.retrievedFrequency(currentNetwork.frequency)
                } else {
                    callbacks?.failureGettingFrequency()
                }
            }
        })
    }

    /**
     * To retrieve the frequency of a network.
     *
     * @param network The network to return the frequency of
     *
     * @return Integer - The frequency of the devices current network or null if no network
     *
     * @see [WifiInfo]
     * @see [WifiInfo.getFrequency]
     *
     * @author Patches
     * @since 3.0
     */
    @Sync
    @CallingThread
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun getFrequency(network: WifiInfo?): Int? = network?.frequency

    /**
     * To retrieve the frequency of a network.
     *
     * @param network The network to return the frequency of
     * @param callbacks The listener to return results to
     *
     * @see [GetFrequencyCallbacks]
     * @see [runOnWiseFyThread]
     * @see [WifiInfo.getFrequency]
     * @see [WiseFyLock]
     *
     * @author Patches
     * @since 3.0
     */
    @Async
    @WiseFyThread
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun getFrequency(network: WifiInfo?, callbacks: GetFrequencyCallbacks?) {
        runOnWiseFyThread(Runnable {
            synchronized(wisefyLock) {
                if (network != null) {
                    callbacks?.retrievedFrequency(network.frequency)
                } else {
                    callbacks?.wisefyFailure(MISSING_PARAMETER)
                }
            }
        })
    }

    /**
     * To retrieve the IPv4 or IPv6 of a device.
     *
     * @return String - The IPv4 or IPv6 address
     *
     * @see [InetAddress]
     * @see [InetAddress.getHostAddress]
     * @see [WifiInfo.getIpAddress]
     * @see [WifiManager.getConnectionInfo]
     * @see [WiseFyPrechecks.getIPChecks]
     *
     * @author Patches
     * @since 3.0
     */
    @Sync
    @CallingThread
    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun getIP(): String? {
        if (wisefyPrechecks.getIPChecks().failed()) {
            return null
        }

        val ipAddress = BigInteger.valueOf(wifiManager.connectionInfo.ipAddress.toLong()).toByteArray()
        try {
            return InetAddress.getByAddress(ipAddress).hostAddress
        } catch (uhe: UnknownHostException) {
            WiseFyLogger.error(TAG, uhe, "UnknownHostException while gathering IP (sync)")
        }
        return null
    }

    /**
     * To retrieve the IPv4 or IPv6 of a device.
     *
     * @param callbacks The listener to return results to
     *
     * @see [GetIPCallbacks]
     * @see [InetAddress]
     * @see [InetAddress.getHostAddress]
     * @see [runOnWiseFyThread]
     * @see [WifiInfo.getIpAddress]
     * @see [WifiManager.getConnectionInfo]
     * @see [WiseFyLock]
     * @see [WiseFyPrechecks.getIPChecks]
     *
     * @author Patches
     * @since 3.0
     */
    @Async
    @WiseFyThread
    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun getIP(callbacks: GetIPCallbacks?) {
        runOnWiseFyThread(Runnable {
            synchronized(wisefyLock) {
                val precheck = wisefyPrechecks.getIPChecks()
                if (precheck.failed()) {
                    callbacks?.wisefyFailure(precheck.code)
                    return@Runnable
                }

                val ipAddress = BigInteger.valueOf(wifiManager.connectionInfo.ipAddress.toLong()).toByteArray()
                try {
                    callbacks?.retrievedIP(InetAddress.getByAddress(ipAddress).hostAddress)
                } catch (uhe: UnknownHostException) {
                    WiseFyLogger.error(TAG, uhe, "UnknownHostException while gathering IP (async)")
                    callbacks?.failureRetrievingIP()
                }
            }
        })
    }

    /**
     * To retrieve a list of nearby access points.
     *
     * *NOTE* Setting filterDuplicates to true will exclude access points for an SSID that have a weaker RSSI
     * (will always take the highest signal strength).
     *
     * @param filterDuplicates If you want to exclude SSIDs with that same name that have a weaker signal strength
     *
     * @return List of ScanResults|null - List of nearby access points
     *
     * @throws SecurityException Without necessary permissions granted
     *
     * @see [ScanResult]
     * @see [WifiManager.getScanResults]
     * @see [WifiManager.startScan]
     * @see [WiseFyPrechecks.getNearbyAccessPointsChecks]
     * @see [WiseFySearch.removeEntriesWithLowerSignalStrength]
     *
     * @author Patches
     * @since 3.0
     */
    @Sync
    @CallingThread
    @RequiresPermission(allOf = arrayOf(ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE))
    @Throws(SecurityException::class)
    override fun getNearbyAccessPoints(filterDuplicates: Boolean): List<ScanResult>? {
        if (wisefyPrechecks.getNearbyAccessPointsChecks().failed()) {
            return null
        }

        wifiManager.startScan()
        return if (filterDuplicates) {
            wisefySearch.removeEntriesWithLowerSignalStrength(wifiManager.scanResults)
        } else {
            wifiManager.scanResults
        }
    }

    /**
     * To retrieve a list of nearby access points.
     *
     * *NOTE* Setting filterDuplicates to true will not return SSIDs with a weaker signal strength (will always take the highest).
     *
     * @param filterDuplicates If you want to exclude SSIDs with that same name that have a weaker signal strength
     * @param callbacks The listener to return results to
     *
     * @see [GetNearbyAccessPointsCallbacks]
     * @see [runOnWiseFyThread]
     * @see [ScanResult]
     * @see [WifiManager.getScanResults]
     * @see [WifiManager.startScan]
     * @see [WiseFyLock]
     * @see [WiseFyPrechecks.getNearbyAccessPointsChecks]
     * @see [WiseFySearch.removeEntriesWithLowerSignalStrength]
     *
     * @author Patches
     * @since 3.0
     */
    @Async
    @WiseFyThread
    @RequiresPermission(allOf = arrayOf(ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE))
    override fun getNearbyAccessPoints(filterDuplicates: Boolean, callbacks: GetNearbyAccessPointsCallbacks?) {
        runOnWiseFyThread(Runnable {
            synchronized(wisefyLock) {
                val precheck = wisefyPrechecks.getNearbyAccessPointsChecks()
                if (precheck.failed()) {
                    callbacks?.wisefyFailure(precheck.code)
                    return@Runnable
                }

                wifiManager.startScan()
                if (filterDuplicates) {
                    callbacks?.retrievedNearbyAccessPoints(
                        wisefySearch.removeEntriesWithLowerSignalStrength(wifiManager.scanResults)
                    )
                } else {
                    callbacks?.retrievedNearbyAccessPoints(wifiManager.scanResults)
                }
            }
        })
    }

    /**
     * To retrieve the RSSI of the first network matching a given regex.
     *
     * *NOTE* Setting takeHighest to true will return the access point with the highest RSSI for the given SSID.
     *
     * @param regexForSSID The regex to be used to search for the ssid
     * @param takeHighest Whether to return the access point with the highest RSSI for the given SSID
     * @param timeoutInMillis The amount of time to search for a matching SSID
     *
     * @return Integer - The RSSI value for the found SSID or null if no matching network found
     *
     * @see [ScanResult]
     * @see [ScanResult.level]
     * @see [WiseFyPrechecks.getRSSIChecks]
     * @see [WiseFySearch.findAccessPointByRegex]
     *
     * @author Patches
     * @since 3.0
     */
    @Sync
    @CallingThread
    @WaitsForTimeout
    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun getRSSI(regexForSSID: String?, takeHighest: Boolean, timeoutInMillis: Int): Int? {
        if (wisefyPrechecks.getRSSIChecks(regexForSSID).failed()) {
            return null
        }

        return wisefySearch.findAccessPointByRegex(regexForSSID!!, timeoutInMillis, takeHighest)?.level
    }

    /**
     * To retrieve the RSSI of the first network matching a given regex.
     *
     * *NOTE* Setting takeHighest to true will return the access point with the highest RSSI for the given SSID.
     *
     * @param regexForSSID The regex to be used to search for the ssid
     * @param takeHighest Whether to return the access point with the highest RSSI for the given SSID
     * @param timeoutInMillis The amount of time to search for a matching SSID
     * @param callbacks The listener to return results to
     *
     * @see [GetRSSICallbacks]
     * @see [runOnWiseFyThread]
     * @see [ScanResult]
     * @see [ScanResult.level]
     * @see [WiseFyLock]
     * @see [WiseFyPrechecks.getRSSIChecks]
     * @see [WiseFySearch.findAccessPointByRegex]
     *
     * @author Patches
     * @since 3.0
     */
    @Async
    @WiseFyThread
    @WaitsForTimeout
    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun getRSSI(
        regexForSSID: String?,
        takeHighest: Boolean,
        timeoutInMillis: Int,
        callbacks: GetRSSICallbacks?
    ) {
        runOnWiseFyThread(Runnable {
            synchronized(wisefyLock) {
                val precheck = wisefyPrechecks.getRSSIChecks(regexForSSID)
                if (precheck.failed()) {
                    callbacks?.wisefyFailure(precheck.code)
                    return@Runnable
                }

                val accessPoint = wisefySearch.findAccessPointByRegex(regexForSSID!!, timeoutInMillis, takeHighest)
                if (accessPoint != null) {
                    callbacks?.retrievedRSSI(accessPoint.level)
                } else {
                    callbacks?.networkNotFoundToRetrieveRSSI()
                }
            }
        })
    }

    /**
     * To search for and return a saved WiFiConfiguration given an SSID.
     *
     * @param regexForSSID The ssid to use while searching for saved configuration
     *
     * @return WifiConfiguration|null - Saved network that matches the ssid
     */
    @Sync
    @CallingThread
    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun getSavedNetwork(regexForSSID: String?): WifiConfiguration? {
        return if (wisefyPrechecks.getSavedNetworkChecks(regexForSSID).passed()) {
            wisefySearch.findSavedNetworkByRegex(regexForSSID!!)
        } else null
    }

    /**
     * To search for and return a saved WiFiConfiguration given an SSID.
     *
     * @param regexForSSID The ssid to use while searching for saved configuration
     * @param callbacks The listener to return results to
     */
    @Async
    @WiseFyThread
    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun getSavedNetwork(regexForSSID: String?, callbacks: GetSavedNetworkCallbacks?) {
        runOnWiseFyThread(Runnable {
            synchronized(wisefyLock) {
                val precheck = wisefyPrechecks.getSavedNetworkChecks(regexForSSID)
                if (precheck.failed()) {
                    callbacks?.wisefyFailure(precheck.code)
                    return@Runnable
                }

                val savedNetwork = wisefySearch.findSavedNetworkByRegex(regexForSSID!!)
                if (savedNetwork != null) {
                    callbacks?.retrievedSavedNetwork(savedNetwork)
                } else {
                    callbacks?.savedNetworkNotFound()
                }
            }
        })
    }

    /**
     * To retrieve a list of saved networks on a user's device.
     *
     * @return List of WifiConfiguration|null - List of saved networks on a users device
     */
    @Sync
    @CallingThread
    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun getSavedNetworks(): List<WifiConfiguration>? {
        return if (wisefyPrechecks.getSavedNetworksChecks().passed()) {
            wifiManager.configuredNetworks
        } else null
    }

    /**
     * To retrieve a list of saved networks on a user's device.
     *
     * @param callbacks The listener to return results to
     */
    @Async
    @WiseFyThread
    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun getSavedNetworks(callbacks: GetSavedNetworksCallbacks?) {
        runOnWiseFyThread(Runnable {
            synchronized(wisefyLock) {
                val precheck = wisefyPrechecks.getSavedNetworksChecks()
                if (precheck.failed()) {
                    callbacks?.wisefyFailure(precheck.code)
                    return@Runnable
                }

                val savedNetworks = wifiManager.configuredNetworks
                if (savedNetworks != null && !savedNetworks.isEmpty()) {
                    callbacks?.retrievedSavedNetworks(savedNetworks)
                } else {
                    callbacks?.noSavedNetworksFound()
                }
            }
        })
    }

    /**
     * To retrieve a list of saved networks on a user's device that match a given regex.
     *
     * @param regexForSSID The ssid to use while searching for saved configurations
     *
     * @return List of WifiConfigurations|null - The list of saved network configurations that match the given regex
     */
    @Sync
    @CallingThread
    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun getSavedNetworks(regexForSSID: String?): List<WifiConfiguration>? {
        return if (wisefyPrechecks.getSavedNetworksChecks(regexForSSID).passed()) {
            wisefySearch.findSavedNetworksMatchingRegex(regexForSSID!!)
        } else null
    }

    /**
     * To retrieve a list of saved networks on a user's device that match a given regex.
     *
     * @param regexForSSID The ssid to use while searching for saved configurations
     * @param callbacks The listener to return results to
     */
    @Async
    @WiseFyThread
    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun getSavedNetworks(regexForSSID: String?, callbacks: GetSavedNetworksCallbacks?) {
        runOnWiseFyThread(Runnable {
            synchronized(wisefyLock) {
                val precheck = wisefyPrechecks.getSavedNetworksChecks(regexForSSID)
                if (precheck.failed()) {
                    callbacks?.wisefyFailure(precheck.code)
                    return@Runnable
                }

                val savedNetworks = wisefySearch.findSavedNetworksMatchingRegex(regexForSSID!!)
                if (savedNetworks != null && !savedNetworks.isEmpty()) {
                    callbacks?.retrievedSavedNetworks(savedNetworks)
                } else {
                    callbacks?.noSavedNetworksFound()
                }
            }
        })
    }

    /**
     * To retrieve the lock in use by WiseFy for synchronization.
     *
     * @return WiseFyLock - The instance of the lock in use by WiseFy
     *
     * @see WiseFyLock
     */
    override fun getWiseFyLock(): WiseFyLock = wisefyLock

    /**
     * To check if the device is connected to a mobile network.
     *
     * @return bool - If the device is currently connected to a mobile network
     */
    @Sync
    @CallingThread
    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun isDeviceConnectedToMobileNetwork(): Boolean =
        wisefyPrechecks.isDeviceConnectedToMobileNetworkChecks().passed() &&
            wisefyConnection.isNetworkConnectedAndMatchesType(connectivityManager.activeNetworkInfo, MOBILE)

    /**
     * To check if the device is connected to a mobile or wifi network.
     *
     * @return bool - If the device is currently connected to a mobile or wifi network
     */
    @Sync
    @CallingThread
    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun isDeviceConnectedToMobileOrWifiNetwork(): Boolean =
        wisefyPrechecks.isDeviceConnectedToMobileOrWifiNetworkChecks().passed() &&
            wisefyConnection.isNetworkConnected(connectivityManager.activeNetworkInfo)

    /**
     * To check if the device is connected to a given SSID.
     *
     * @param ssid The SSID to check if the device is attached to
     *
     * @return bool - If the device is currently attached to the given SSID
     */
    @Sync
    @CallingThread
    @RequiresPermission(allOf = arrayOf(ACCESS_NETWORK_STATE, ACCESS_WIFI_STATE))
    override fun isDeviceConnectedToSSID(ssid: String?): Boolean =
        wisefyPrechecks.isDeviceConnectedToSSIDChecks(ssid).passed() &&
            wisefyConnection.isCurrentNetworkConnectedToSSID(ssid)

    /**
     * To check if the device is connected to a wifi network.
     *
     * @return bool - If the device is currently connected to a wifi network
     */
    @Sync
    @CallingThread
    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun isDeviceConnectedToWifiNetwork(): Boolean =
        wisefyPrechecks.isDeviceConnectedToWifiNetworkChecks().passed() &&
            wisefyConnection.isNetworkConnectedAndMatchesType(connectivityManager.activeNetworkInfo, WIFI)

    /**
     * To query if the device is roaming.
     *
     * @return boolean - If the current network is roaming
     */
    @Sync
    @CallingThread
    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun isDeviceRoaming(): Boolean {
        if (wisefyPrechecks.isDeviceRoamingChecks().failed()) {
            return false
        }

        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isRoaming
    }

    /**
     * To query if logging is enabled or disabled for a WiseFy instance.
     *
     * @return boolean - If logging is enabled for the WiseFy instance
     */
    @Sync
    @CallingThread
    override fun isLoggingEnabled(): Boolean = WiseFyLogger.isLoggingEnabled()

    /**
     * To check if the device's current network is 5gHz.
     *
     * @return boolean - If the network is 5gHz
     *
     * @throws SecurityException Without necessary permissions granted
     */
    @Sync
    @CallingThread
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresPermission(allOf = arrayOf(ACCESS_COARSE_LOCATION, ACCESS_WIFI_STATE))
    @Throws(SecurityException::class)
    override fun isNetwork5gHz(): Boolean {
        val frequency = getFrequency()
        return frequency != null && frequency > MIN_FREQUENCY_5GHZ && frequency < MAX_FREQUENCY_5GHZ
    }

    /**
     * To check if a given network is 5gHz.
     *
     * @param network The network to check if it's 5gHz
     *
     * @return boolean - If the network is 5gHz
     */
    @Sync
    @CallingThread
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun isNetwork5gHz(network: WifiInfo?): Boolean {
        val frequency = getFrequency(network)
        return frequency != null && frequency > MIN_FREQUENCY_5GHZ && frequency < MAX_FREQUENCY_5GHZ
    }

    /**
     * To check and return if a network is a EAP network.
     *
     * @param scanResult The network to check
     *
     * @return boolean - Whether the network has EAP capabilities listed
     */
    @Sync
    @CallingThread
    override fun isNetworkEAP(scanResult: ScanResult?): Boolean = containsCapability(scanResult, EAP)

    /**
     * To check and return if a network is a PSK network.
     *
     * @param scanResult The network to check
     *
     * @return boolean - Whether the network has PSK capabilities listed
     */
    @Sync
    @CallingThread
    override fun isNetworkPSK(scanResult: ScanResult?): Boolean = containsCapability(scanResult, PSK)

    /**
     * To check if an SSID is in the list of configured networks.
     *
     * @param ssid The SSID to check and see if it's in the list of configured networks
     *
     * @return boolean - If the SSID is in the list of configured networks
     */
    @Sync
    @CallingThread
    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun isNetworkSaved(ssid: String?): Boolean =
        wisefyPrechecks.isNetworkSavedChecks().passed() && wisefySearch.isNetworkASavedConfiguration(ssid)

    /**
     * To check and return if a network is secure (WEP/WPA/WPA2 capabilities).
     *
     * @param scanResult The network to see if it is secure
     *
     * @return boolean - Whether the network is secure
     */
    @Sync
    @CallingThread
    override fun isNetworkSecure(scanResult: ScanResult?): Boolean {
        if (scanResult?.capabilities != null) {
            val networkCapabilities = scanResult.capabilities
            val securityModes = arrayOf(EAP, PSK, WEP, WPA, WPA2)
            for (i in securityModes.indices.reversed()) {
                if (networkCapabilities.contains(securityModes[i])) {
                    return true
                }
            }
        }
        return false
    }

    /**
     * To check and return if a network is a WEP network.
     *
     * @param scanResult The network to check
     *
     * @return boolean - Whether the network has WEP capabilities listed
     */
    @Sync
    @CallingThread
    override fun isNetworkWEP(scanResult: ScanResult?): Boolean = containsCapability(scanResult, WEP)

    /**
     * To check and return if a network is a WPA network.
     *
     * @param scanResult The network to check
     *
     * @return boolean - Whether the network has WPA capabilities listed
     */
    @Sync
    @CallingThread
    override fun isNetworkWPA(scanResult: ScanResult?): Boolean = containsCapability(scanResult, WPA)

    /**
     * To check and return if a network is a WPA2 network.
     *
     * @param scanResult The network to check
     *
     * @return boolean - Whether the network has WPA2 capabilities listed
     */
    @Sync
    @CallingThread
    override fun isNetworkWPA2(scanResult: ScanResult?): Boolean = containsCapability(scanResult, WPA2)

    /**
     * To check if Wifi is enabled on the device or not.
     *
     * @return boolean - if Wifi is enabled on device
     */
    @Sync
    @CallingThread
    override fun isWifiEnabled(): Boolean = wisefyPrechecks.isWifiEnabledChecks().passed() && wifiManager.isWifiEnabled

    /**
     * To remove a configured network.
     *
     * @param ssidToRemove The ssid of the network you want to remove from the configured network list
     *
     * @return boolean - If the command succeeded in removing the network
     *
     * @see [WifiConfiguration]
     * @see [WiseFyPrechecks.removeNetworkCheck]
     * @sse [WiseFySearch.findSavedNetworkByRegex]
     *
     * @author Patches
     * @since 3.0
     */
    @Sync
    @CallingThread
    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun removeNetwork(ssidToRemove: String?): Boolean {
        if (wisefyPrechecks.removeNetworkCheck(ssidToRemove).failed()) {
            return false
        }

        val wifiConfiguration = wisefySearch.findSavedNetworkByRegex(ssidToRemove!!)
        if (wifiConfiguration != null) {
            return removeNetworkConfiguration(wifiConfiguration)
        } else {
            WiseFyLogger.warn(TAG, "SSID to remove: %s was not found in list to remove network", ssidToRemove)
        }
        return false
    }

    /**
     * To remove a configured network.
     *
     * @param ssidToRemove The ssid of the network you want to remove from the configured network list
     * @param callbacks The listener to return results to
     *
     * @see [RemoveNetworkCallbacks]
     * @see [runOnWiseFyThread]
     * @see [WifiConfiguration]
     * @see [WiseFyLock]
     * @see [WiseFyPrechecks.removeNetworkCheck]
     * @sse [WiseFySearch.findSavedNetworkByRegex]
     *
     * @author Patches
     * @since 3.0
     */
    @Async
    @WiseFyThread
    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun removeNetwork(ssidToRemove: String?, callbacks: RemoveNetworkCallbacks?) {
        runOnWiseFyThread(Runnable {
            synchronized(wisefyLock) {
                val precheck = wisefyPrechecks.removeNetworkCheck(ssidToRemove)
                if (precheck.failed()) {
                    callbacks?.wisefyFailure(precheck.code)
                    return@Runnable
                }

                val wifiConfiguration = wisefySearch.findSavedNetworkByRegex(ssidToRemove!!)
                if (wifiConfiguration != null) {
                    if (removeNetworkConfiguration(wifiConfiguration)) {
                        callbacks?.networkRemoved()
                    } else {
                        callbacks?.failureRemovingNetwork()
                    }
                } else {
                    callbacks?.networkNotFoundToRemove()
                }
            }
        })
    }

    /**
     * To return the first access point that matches a given regex.
     *
     *
     * *NOTE* Setting filterDuplicates to true will not return an access point with a weaker signal strength (will always take the highest).
     *
     * @param regexForSSID The regex to use when iterating through nearby access points
     * @param timeoutInMillis The amount of time (in milliseconds) to wait for a matching access point
     * @param filterDuplicates If you want to exclude access points with the same name that have a weaker signal strength
     *
     * @return ScanResult|null - The first access point or access point with the highest signal strength matching the regex
     *
     * @see [ScanResult]
     * @see [WiseFyPrechecks.searchForAccessPointChecks]
     * @see [WiseFySearch.findAccessPointByRegex]
     *
     * @author Patches
     * @since 3.0
     */
    @Sync
    @CallingThread
    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun searchForAccessPoint(
        regexForSSID: String?,
        timeoutInMillis: Int,
        filterDuplicates: Boolean
    ): ScanResult? {
        return if (wisefyPrechecks.searchForAccessPointChecks(regexForSSID).passed()) {
            wisefySearch.findAccessPointByRegex(regexForSSID!!, timeoutInMillis, filterDuplicates)
        } else null
    }

    /**
     * To return the first access point that matches a given regex.
     *
     *
     * *NOTE* Setting filterDuplicates to true will not return an access point with a weaker signal strength (will always take the highest).
     *
     * @param regexForSSID The regex to use when iterating through nearby access points
     * @param timeoutInMillis The amount of time (in milliseconds) to wait for a matching access point
     * @param filterDuplicates If you want to exclude access points with the same name that have a weaker signal strength
     * @param callbacks The listener to return results to
     *
     * @see [runOnWiseFyThread]
     * @see [ScanResult]
     * @see [SearchForAccessPointCallbacks]
     * @see [WiseFyLock]
     * @see [WiseFyPrechecks.searchForAccessPointChecks]
     * @see [WiseFySearch.findAccessPointByRegex]
     *
     * @author Patches
     * @since 3.0
     */
    @Async
    @WiseFyThread
    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun searchForAccessPoint(
        regexForSSID: String?,
        timeoutInMillis: Int,
        filterDuplicates: Boolean,
        callbacks: SearchForAccessPointCallbacks?
    ) {
        runOnWiseFyThread(Runnable {
            synchronized(wisefyLock) {
                val precheck = wisefyPrechecks.searchForAccessPointChecks(regexForSSID)
                if (precheck.failed()) {
                    callbacks?.wisefyFailure(precheck.code)
                    return@Runnable
                }

                val scanResult = wisefySearch.findAccessPointByRegex(regexForSSID!!, timeoutInMillis, filterDuplicates)
                if (scanResult != null) {
                    callbacks?.accessPointFound(scanResult)
                } else {
                    callbacks?.accessPointNotFound()
                }
            }
        })
    }

    /**
     * To return nearby access points that match a given regex.
     *
     *
     * *NOTE* Setting filterDuplicates to true will not return access points with a weaker signal strength (will always take the highest).
     *
     * @param regexForSSID The regex to use when iterating through nearby access points
     * @param filterDuplicates If you want to exclude access points with the same name that have a weaker signal strength
     *
     * @return List of ScanResult|null - The list of matching access points or null if none match the given regex
     *
     * @see [ScanResult]
     * @see [WiseFyPrechecks.searchForAccessPointsChecks]
     * @see [WiseFySearch.findAccessPointsMatchingRegex]
     *
     * @author Patches
     * @since 3.0
     */
    @Sync
    @CallingThread
    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun searchForAccessPoints(regexForSSID: String?, filterDuplicates: Boolean): List<ScanResult>? {
        return if (wisefyPrechecks.searchForAccessPointsChecks(regexForSSID).passed()) {
            wisefySearch.findAccessPointsMatchingRegex(regexForSSID!!, filterDuplicates)
        } else null
    }

    /**
     * To return nearby access points that match a given regex.
     *
     *
     * *NOTE* Setting filterDuplicates to true will not return access points with a weaker signal strength (will always take the highest).
     *
     * @param regexForSSID The regex to use when iterating through nearby access points
     * @param filterDuplicates If you want to exclude access points with the same name that have a weaker signal strength
     * @param callbacks The listener to return results to
     *
     * @see [runOnWiseFyThread]
     * @see [ScanResult]
     * @see [SearchForAccessPointsCallbacks]
     * @see [WiseFyLock]
     * @see [WiseFyPrechecks.searchForAccessPointsChecks]
     * @see [WiseFySearch.findAccessPointsMatchingRegex]
     *
     * @author Patches
     * @since 3.0
     */
    @Async
    @WiseFyThread
    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun searchForAccessPoints(
        regexForSSID: String?,
        filterDuplicates: Boolean,
        callbacks: SearchForAccessPointsCallbacks?
    ) {
        runOnWiseFyThread(Runnable {
            synchronized(wisefyLock) {
                val precheck = wisefyPrechecks.searchForAccessPointsChecks(regexForSSID)
                if (precheck.failed()) {
                    callbacks?.wisefyFailure(precheck.code)
                    return@Runnable
                }

                val networks = wisefySearch.findAccessPointsMatchingRegex(regexForSSID!!, filterDuplicates)
                if (networks != null) {
                    callbacks?.foundAccessPoints(networks)
                } else {
                    callbacks?.noAccessPointsFound()
                }
            }
        })
    }

    /**
     * To search local networks and return the first one that contains a given ssid.
     *
     * @param regexForSSID The regex to be used to search for the ssid
     * @param timeoutInMillis The number of milliseconds to keep searching for the SSID
     *
     * @return String|null - The first SSID that contains the search ssid (if any, else null)
     *
     * @see [ScanResult]
     * @see [ScanResult.SSID]
     * @see [WiseFyPrechecks.searchForSSIDChecks]
     * @see [WiseFySearch.findAccessPointByRegex]
     *
     * @author Patches
     * @since 3.0
     */
    @Sync
    @CallingThread
    @WaitsForTimeout
    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun searchForSSID(regexForSSID: String?, timeoutInMillis: Int): String? {
        if (wisefyPrechecks.searchForSSIDChecks(regexForSSID).failed()) {
            return null
        }

        val scanResult = wisefySearch.findAccessPointByRegex(regexForSSID!!, timeoutInMillis, false)
        return scanResult?.SSID
    }

    /**
     * To search local networks and return the first one that contains a given ssid.
     *
     * @param regexForSSID The regex to be used to search for the ssid
     * @param timeoutInMillis The number of milliseconds to keep searching for the SSID
     * @param callbacks The listener to return results to
     *
     * @see [runOnWiseFyThread]
     * @see [ScanResult]
     * @see [ScanResult.SSID]
     * @see [SearchForSSIDCallbacks]
     * @see [WiseFyLock]
     * @see [WiseFyPrechecks.searchForSSIDChecks]
     * @see [WiseFySearch.findAccessPointByRegex]
     *
     * @author Patches
     * @since 3.0
     */
    @Async
    @WiseFyThread
    @WaitsForTimeout
    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun searchForSSID(regexForSSID: String?, timeoutInMillis: Int, callbacks: SearchForSSIDCallbacks?) {
        runOnWiseFyThread(Runnable {
            synchronized(wisefyLock) {
                val precheck = wisefyPrechecks.searchForSSIDChecks(regexForSSID)
                if (precheck.failed()) {
                    callbacks?.wisefyFailure(precheck.code)
                    return@Runnable
                }

                val scanResult = wisefySearch.findAccessPointByRegex(regexForSSID!!, timeoutInMillis, false)
                if (scanResult != null) {
                    callbacks?.ssidFound(scanResult.SSID)
                } else {
                    callbacks?.ssidNotFound()
                }
            }
        })
    }

    /**
     * To search local networks and return the first one that contains a given ssid.
     *
     * @param regexForSSID The regex to be used to search for the ssid
     *
     * @return String|null - The first SSID that contains the search ssid (if any, else null)
     *
     * @see [WiseFyPrechecks.searchForSSIDsChecks]
     * @see [WiseFySearch.findSSIDsMatchingRegex]
     *
     * @author Patches
     * @since 3.0
     */
    @Sync
    @CallingThread
    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun searchForSSIDs(regexForSSID: String?): List<String>? {
        return if (wisefyPrechecks.searchForSSIDsChecks(regexForSSID).passed()) {
            wisefySearch.findSSIDsMatchingRegex(regexForSSID!!)
        } else null
    }

    /**
     * To search local networks and return the first one that contains a given ssid.
     *
     * @param regexForSSID The regex to be used to search for the ssid
     * @param callbacks The listener to return results to
     *
     * @see [runOnWiseFyThread]
     * @see [SearchForSSIDsCallbacks]
     * @see [WiseFyLock]
     * @see [WiseFyPrechecks.searchForSSIDsChecks]
     * @see [WiseFySearch.findSSIDsMatchingRegex]
     *
     * @author Patches
     * @since 3.0
     */
    @Async
    @WiseFyThread
    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun searchForSSIDs(regexForSSID: String?, callbacks: SearchForSSIDsCallbacks?) {
        runOnWiseFyThread(Runnable {
            synchronized(wisefyLock) {
                val precheck = wisefyPrechecks.searchForSSIDsChecks(regexForSSID)
                if (precheck.failed()) {
                    callbacks?.wisefyFailure(precheck.code)
                    return@Runnable
                }

                val ssids = wisefySearch.findSSIDsMatchingRegex(regexForSSID!!)
                if (ssids != null && ssids.isNotEmpty()) {
                    callbacks?.retrievedSSIDs(ssids)
                } else {
                    callbacks?.noSSIDsFound()
                }
            }
        })
    }

    /*
     * HELPERS
     */

    /**
     * Used internally to add and save a new wifi configuration.
     *
     * @param wifiConfiguration The network configuration to add
     *
     * @return boolean - If the network was successfully added
     *
     * @see [WifiConfiguration]
     * @see [WifiConfiguration.SSID]
     * @see [WifiManager.addNetwork]
     *
     * @author Patches
     * @since 3.0
     */
    private fun addNetworkConfiguration(wifiConfiguration: WifiConfiguration): Int {
        val result = wifiManager.addNetwork(wifiConfiguration)
        WiseFyLogger.debug(TAG, "Adding network with SSID: %s had result: %d", wifiConfiguration.SSID, result)
        return result
    }

    /**
     * Used internally to check if a network has a given capability.
     *
     * @param scanResult The network to check
     * @param capability The capability to check for
     *
     * @return boolean - True if the network contains the capability
     *
     * @see [Capability]
     * @see [ScanResult]
     * @see [ScanResult.capabilities]
     *
     * @author Patches
     * @since 3.0
     */
    private fun containsCapability(scanResult: ScanResult?, @Capability capability: String): Boolean =
        scanResult?.capabilities != null && scanResult.capabilities.contains(capability)

    /**
     * Used internally to connect to a network given it's id.
     *
     * @param networkId The network id to enable
     *
     * @see [WifiManager.disconnect]
     * @see [WifiManager.enableNetwork]
     * @see [WifiManager.reconnect]
     *
     * @author Patches
     * @since 3.0
     */
    private fun connectToNetworkWithId(networkId: Int) {
        wifiManager.disconnect()
        wifiManager.enableNetwork(networkId, true)
        wifiManager.reconnect()
    }

    /**
     * A method to execute logic on a background thread.
     *
     * @param runnable The block of code to execute in the background
     *
     * @see [setupWiseFyThread]
     *
     * @author Patches
     * @since 3.0
     */
    private fun runOnWiseFyThread(runnable: Runnable) {
        if (wisefyHandler == null) {
            setupWiseFyThread()
        }
        wisefyHandler?.post(runnable)
    }

    /**
     * Used internally to remove a network.
     *
     * @param wifiConfiguration The network to remove
     *
     * @return bool - true if the network was successfully removed
     *
     * @see [WifiConfiguration]
     * @see [WifiConfiguration.networkId]
     * @see [WifiConfiguration.SSID]
     * @see [WifiManager.disconnect]
     * @see [WifiManager.removeNetwork]
     * @see [WifiManager.reconnect]
     *
     * @author Patches
     * @since 3.0
     */
    private fun removeNetworkConfiguration(wifiConfiguration: WifiConfiguration): Boolean {
        wifiManager.disconnect()
        val result = wifiManager.removeNetwork(wifiConfiguration.networkId)
        WiseFyLogger.debug(TAG, "Removing network with SSID: %s had result: %b", wifiConfiguration.SSID, result)
        wifiManager.reconnect()
        return result
    }

    /**
     * Used internally to setup a WiseFyThread to run background operations.
     *
     * @see [runOnWiseFyThread]
     * @see [WiseFyHandlerThread]
     *
     * @author Patches
     * @since 3.0
     */
    private fun setupWiseFyThread() {
        wisefyHandlerThread = WiseFyHandlerThread(WiseFyHandlerThread.TAG)
        wisefyHandlerThread?.let {
            it.start()
            val looper = it.looper
            wisefyHandler = Handler(looper)
        }
    }
}
