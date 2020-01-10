/*
 * Copyright 2019 Patches Klinefelter
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
package com.isupatches.wisefy.connection

import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.net.NetworkRequest
import android.net.wifi.WifiManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.VisibleForTesting
import com.isupatches.wisefy.logging.WiseFyLogger

/**
 * A class used internally to query and determine different parts of the connection state for a
 * device when WiseFy is on a device with at least SDK23 and is not configured to use the legacy
 * connection class.
 *
 * @see [ConnectivityManager]
 * @see [WifiManager]
 * @see [AbstractWiseFyConnection]
 *
 * Updates
 * - 01/07/2020: Added WiseFyLogger
 *
 * @author Patches
 * @since 4.0
 */
@RequiresApi(Build.VERSION_CODES.M)
internal class WiseFyConnectionSDK23 private constructor(
    private val connectivityManager: ConnectivityManager,
    wifiManager: WifiManager,
    private val logger: WiseFyLogger?
) : AbstractWiseFyConnection(wifiManager, logger) {

    internal companion object {
        // Internal to avoid SyntheticAccessor error within networkChangeCallback
        internal val TAG = WiseFyConnectionSDK23::class.java.simpleName

        /**
         * Used internally to create an instance of a WiseFyConnection for SDK 23.
         *
         * @param connectivityManager The instance of ConnectivityManager to use
         * @param wifiManager The instance of WifiManager to use
         *
         * @return WiseFyConnectionSDK23
         *
         * @see [WiseFyConnection]
         *
         * Updates
         * - 01/04/2020: Formatting update
         * - 01/07/2020: Added WiseFyLogger
         *
         * @author Patches
         * @since 4.0
         */
        fun create(
            connectivityManager: ConnectivityManager,
            wifiManager: WifiManager,
            logger: WiseFyLogger? = null
        ): WiseFyConnection {
            return WiseFyConnectionSDK23(connectivityManager, wifiManager, logger)
        }
    }

    // Internal to avoid SyntheticAccessor error within networkChangeCallback
    internal var connectionStatus: WiseFyConnectionStatus? = null

    @VisibleForTesting
    internal val networkChangeCallbacks by lazy {
        object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network?) {
                super.onAvailable(network)
                logger?.d(TAG, "onAvailable, $network")
                this@WiseFyConnectionSDK23.connectionStatus = WiseFyConnectionStatus.AVAILABLE
            }

            override fun onCapabilitiesChanged(network: Network?, networkCapabilities: NetworkCapabilities?) {
                super.onCapabilitiesChanged(network, networkCapabilities)
                logger?.d(
                    TAG,
                    "onCapabilitiesChanged, network: $network, networkCapabilities: $networkCapabilities"
                )
            }

            override fun onLinkPropertiesChanged(network: Network?, linkProperties: LinkProperties?) {
                super.onLinkPropertiesChanged(network, linkProperties)
                logger?.d(TAG, "onLinkPropertiesChanged, network: $network, linkProperties: $linkProperties")
            }

            override fun onLosing(network: Network?, maxMsToLive: Int) {
                super.onLosing(network, maxMsToLive)
                logger?.d(TAG, "onLosing, network: $network, maxMsToLive: $maxMsToLive")
                this@WiseFyConnectionSDK23.connectionStatus = WiseFyConnectionStatus.LOSING
            }

            override fun onLost(network: Network?) {
                super.onLost(network)
                logger?.d(TAG, "onLost, network: $network")
                this@WiseFyConnectionSDK23.connectionStatus = WiseFyConnectionStatus.LOST
            }

            override fun onUnavailable() {
                super.onUnavailable()
                logger?.d(TAG, "onUnavailable")
                this@WiseFyConnectionSDK23.connectionStatus = WiseFyConnectionStatus.UNAVAILABLE
            }
        }
    }

    /**
     * Used internally for any initialization of [WiseFyConnectionLegacy] class.
     *
     * @see [startListeningForNetworkChanges]
     *
     * @author Patches
     * @since 4.0
     */
    override fun init() {
        startListeningForNetworkChanges(connectivityManager)
    }

    /**
     * Used internally for any tear down of [WiseFyConnectionLegacy] class.
     *
     * @see [stopListeningForNetworkChanges]
     *
     * @author Patches
     * @since 4.0
     */
    override fun destroy() {
        stopListeningForNetworkChanges(connectivityManager)
    }

    /**
     * Used internally to check if a network is connected to a mobile network (f.e. non-Wifi)
     *
     * @return boolean - True if the device is using a mobile network, false otherwise
     *
     * @see [doesNetworkHaveTransportTypeAndInternetCapability]
     * @see [isNetworkConnected]
     * @see [NetworkCapabilities.TRANSPORT_CELLULAR]
     *
     * Updates
     * - 01/05/2020: Formatting update
     *
     * @author Patches
     * @since 4.0
     */
    override fun isDeviceConnectedToMobileNetwork(): Boolean {
        return doesNetworkHaveTransportTypeAndInternetCapability(
            transportType = NetworkCapabilities.TRANSPORT_CELLULAR
        ) && isNetworkConnected()
    }

    /**
     * Used internally to check if a network is connected to a wifi network (f.e. not using
     * mobile data)
     *
     * @return boolean - True if the device is using a wifi network, false otherwise
     *
     * @see [doesNetworkHaveTransportTypeAndInternetCapability]
     * @see [isNetworkConnected]
     * @see [NetworkCapabilities.TRANSPORT_WIFI]
     *
     * Updates
     * - 01/05/2020: Formatting update
     *
     * @author Patches
     * @since 4.0
     */
    override fun isDeviceConnectedToWifiNetwork(): Boolean {
        return doesNetworkHaveTransportTypeAndInternetCapability(
            transportType = NetworkCapabilities.TRANSPORT_WIFI
        ) && isNetworkConnected()
    }

    /**
     * Used internally to check if a network is in a roaming state.
     *
     * *NOTE* Determines roaming differently on P and above devices.
     *
     * @return boolean - True if the device is roaming, false otherwise
     *
     * @see [doesNetworkHaveCapability]
     * @see [ConnectivityManager.getActiveNetworkInfo]
     * @see [NetworkInfo.isRoaming]
     *
     * Updates
     * - 01/05/2020: Formatting update
     *
     * @author Patches
     * @since 4.0
     */
    override fun isDeviceRoaming(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            // NET_CAPABILITY_NOT_ROAMING only available for P and above devices :'(
            !doesNetworkHaveCapability(capability = NetworkCapabilities.NET_CAPABILITY_NOT_ROAMING)
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo
            @Suppress("deprecation")
            networkInfo != null && networkInfo.isRoaming
        }
    }

    /**
     * Used internally to check if a network is connected.
     *
     * @return boolean - True if the network is available
     *
     * @see [connectionStatus]
     * @see [networkChangeCallbacks]
     * @see [WiseFyConnectionStatus]
     *
     * @author Patches
     * @since 4.0
     */
    override fun isNetworkConnected(): Boolean = connectionStatus == WiseFyConnectionStatus.AVAILABLE

    /**
     * Used internally to check if the active network has a certain transport type as well as
     * internet capability.
     *
     * @param transportType The transport capability to check and see if the current network has
     *
     * @see [getActiveNetworkCapabilities]
     * @see [NetworkCapabilities.hasTransport]
     * @see [NetworkCapabilities.hasCapability]
     * @see [NetworkCapabilities.NET_CAPABILITY_INTERNET]
     *
     * Updates
     * - 01/05/2020: Formatting update
     *
     * @author Patches
     * @since 4.0
     */
    private fun doesNetworkHaveTransportTypeAndInternetCapability(transportType: Int): Boolean {
        return getActiveNetworkCapabilities()?.let {
            it.hasTransport(transportType) && it.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        } ?: false
    }

    /**
     * Used internally to check if the active network has a certain capability (f.e. to check if the
     * device has [NetworkCapabilities.NET_CAPABILITY_NOT_ROAMING] capability listed)
     *
     * @param capability The capability to check and see if the current network has
     *
     * @see [getActiveNetworkCapabilities]
     * @see [NetworkCapabilities.hasCapability]
     * @see NetworkCapabilities
     *
     * Updates
     * - 01/04/2020: Formatting update
     *
     * @author Patches
     * @since 4.0
     */
    private fun doesNetworkHaveCapability(capability: Int): Boolean {
        return getActiveNetworkCapabilities()?.hasCapability(capability) ?: false
    }

    /**
     * Used internally to return the capabilities of the active network.
     *
     * @see [NetworkCapabilities]
     * @see [ConnectivityManager.getNetworkCapabilities]
     * @see [ConnectivityManager.getActiveNetwork]
     *
     * Updates
     * - 01/04/2020: Formatting update
     *
     * @author Patches
     * @since 4.0
     */
    private fun getActiveNetworkCapabilities(): NetworkCapabilities? {
        return connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
    }

    /**
     * Used internally to start listening for network changes
     *
     * @param connectivityManager The connectivity manager instance to use to listen for network
     *  callbacks
     *
     * @see [ConnectivityManager.registerNetworkCallback]
     * @see [NetworkRequest.Builder]
     * @see [networkChangeCallbacks]
     *
     * @author Patches
     * @since 4.0
     */
    private fun startListeningForNetworkChanges(connectivityManager: ConnectivityManager) {
        val request = NetworkRequest.Builder().build()
        connectivityManager.registerNetworkCallback(request, networkChangeCallbacks)
    }

    /**
     * Used internally to stop listening for network changes
     *
     * @param connectivityManager The connectivity manager instance to use to stop
     *  listening  for networ callbacks
     *
     * @see [ConnectivityManager.unregisterNetworkCallback]
     * @see [networkChangeCallbacks]
     *
     * @author Patches
     * @since 4.0
     */
    private fun stopListeningForNetworkChanges(connectivityManager: ConnectivityManager) {
        connectivityManager.unregisterNetworkCallback(networkChangeCallbacks)
    }
}
