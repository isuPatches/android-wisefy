/*
 * Copyright 2021 Patches Klinefelter
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
package com.isupatches.android.wisefy.networkconnection.delegates

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.net.wifi.WifiNetworkSpecifier
import android.os.Build
import androidx.annotation.RequiresApi
import com.isupatches.android.wisefy.logging.WisefyLogger
import com.isupatches.android.wisefy.networkconnection.entities.NetworkConnectionResult

internal interface Android29NetworkConnectionApi {
    @RequiresApi(Build.VERSION_CODES.Q)
    fun connectToNetwork(ssidToConnectTo: String, timeoutInMillis: Int = 0): NetworkConnectionResult

    fun disconnectFromCurrentNetwork(): NetworkConnectionResult
}

private const val LOG_TAG = "Android29NetworkConnectionApiImpl"

internal class Android29NetworkConnectionApiImpl(
    private val connectionManager: ConnectivityManager,
    private val logger: WisefyLogger?
) : Android29NetworkConnectionApi, ConnectivityManager.NetworkCallback() {

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun connectToNetwork(ssidToConnectTo: String, timeoutInMillis: Int): NetworkConnectionResult {
        val networkRequest = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .removeCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .setNetworkSpecifier(
                WifiNetworkSpecifier.Builder()
                    .setSsid(ssidToConnectTo)
                    .build()
            )
            .build()
        connectionManager.requestNetwork(
            networkRequest,
            object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    logger?.d(LOG_TAG, "Network available")
                }

                override fun onUnavailable() {
                    super.onUnavailable()
                    logger?.d(LOG_TAG, "Network unavailable")
                }
            },
            timeoutInMillis
        )
        return NetworkConnectionResult.RequestPlaced
    }

    override fun disconnectFromCurrentNetwork(): NetworkConnectionResult {
        error("Unsure how to disconnect from network on Android Q and higher")
    }
}
