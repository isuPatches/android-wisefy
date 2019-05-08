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
package com.isupatches.wisefy.connection

import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.net.wifi.WifiManager
import android.os.Build
import androidx.annotation.RequiresApi

import com.isupatches.wisefy.logging.WiseFyLogger

/**
 * A class used internally to query and determine different parts of the connection state for a
 * device.
 *
 * @see [ConnectivityManager]
 * @see [WifiManager]
 * @see [WiseFyConnection]
 *
 * @author Patches
 * @since 4.0
 */
@RequiresApi(Build.VERSION_CODES.M)
internal class WiseFyConnectionSDK23 private constructor(
    private val connectivityManager: ConnectivityManager,
    wifiManager: WifiManager
) : AbstractWiseFyConnection(wifiManager) {

    internal companion object {
        // Internal to avoid SyntheticAccessor error within networkChangeCallback
        internal val TAG = WiseFyConnectionSDK23::class.java.simpleName

        fun create(connectivityManager: ConnectivityManager, wifiManager: WifiManager): WiseFyConnection =
                WiseFyConnectionSDK23(connectivityManager, wifiManager)
    }

    // Internal to avoid SyntheticAccessor error within networkChangeCallback
    internal var connectionStatus: WiseFyConnectionStatus? = null

    private val networkChangeCallback by lazy {
        object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network?) {
                super.onAvailable(network)
                WiseFyLogger.debug(TAG, "onAvailable, $network")
                this@WiseFyConnectionSDK23.connectionStatus = WiseFyConnectionStatus.AVAILABLE
            }

            override fun onCapabilitiesChanged(network: Network?, networkCapabilities: NetworkCapabilities?) {
                super.onCapabilitiesChanged(network, networkCapabilities)
                WiseFyLogger.debug(TAG, "onCapabilitiesChanged, network: $network, networkCapabilities: $networkCapabilities")
            }

            override fun onLinkPropertiesChanged(network: Network?, linkProperties: LinkProperties?) {
                super.onLinkPropertiesChanged(network, linkProperties)
                WiseFyLogger.debug(TAG, "onCapabilitiesChanged, network: $network, linkProperties: $linkProperties")
            }

            override fun onLosing(network: Network?, maxMsToLive: Int) {
                super.onLosing(network, maxMsToLive)
                WiseFyLogger.debug(TAG, "onLosing, network: $network, maxMsToLive: $maxMsToLive")
                this@WiseFyConnectionSDK23.connectionStatus = WiseFyConnectionStatus.LOSING
            }

            override fun onLost(network: Network?) {
                super.onLost(network)
                WiseFyLogger.debug(TAG, "onLost, network: $network")
                this@WiseFyConnectionSDK23.connectionStatus = WiseFyConnectionStatus.LOST
            }

            override fun onUnavailable() {
                super.onUnavailable()
                WiseFyLogger.debug(TAG, "onUnavailable")
                this@WiseFyConnectionSDK23.connectionStatus = WiseFyConnectionStatus.UNAVAILABLE
            }
        }
    }

    override fun init() {
        startListeningForNetworkChanges(connectivityManager)
    }

    override fun destroy() {
        stopListeningForNetworkChanges(connectivityManager)
    }

    override fun isDeviceConnectedToMobileNetwork(): Boolean = doesNetworkHaveTransportType(
        transportType = NetworkCapabilities.TRANSPORT_CELLULAR
    )

    override fun isDeviceConnectedToWifiNetwork(): Boolean = doesNetworkHaveTransportType(
        transportType = NetworkCapabilities.TRANSPORT_WIFI
    )

    override fun isDeviceRoaming(): Boolean =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            !doesNetworkHaveCapability(capability = NetworkCapabilities.NET_CAPABILITY_NOT_ROAMING)
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo
            @Suppress("DEPRECATION")
            networkInfo != null && networkInfo.isRoaming
        }

    override fun isNetworkConnected(): Boolean = connectionStatus == WiseFyConnectionStatus.AVAILABLE

    private fun doesNetworkHaveTransportType(transportType: Int): Boolean =
        getActiveNetworkCapabilities().hasTransport(transportType)

    private fun doesNetworkHaveCapability(capability: Int): Boolean =
        getActiveNetworkCapabilities().hasCapability(capability)

    private fun getActiveNetworkCapabilities(): NetworkCapabilities =
        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

    private fun startListeningForNetworkChanges(connectivityManager: ConnectivityManager) {
        val request = NetworkRequest.Builder().build()
        connectivityManager.registerNetworkCallback(request, networkChangeCallback)
    }

    private fun stopListeningForNetworkChanges(connectivityManager: ConnectivityManager) {
        connectivityManager.unregisterNetworkCallback(networkChangeCallback)
    }
}
