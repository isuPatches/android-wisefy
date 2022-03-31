/*
 * Copyright 2022 Patches Klinefelter
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
package com.isupatches.android.wisefy.networkconnection.os.adapters

import android.net.ConnectivityManager
import android.net.MacAddress
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.net.wifi.WifiNetworkSpecifier
import android.os.Build
import androidx.annotation.RequiresApi
import com.isupatches.android.wisefy.networkconnection.NetworkConnectionApi
import com.isupatches.android.wisefy.networkconnection.entities.NetworkConnectionRequest
import com.isupatches.android.wisefy.networkconnection.entities.NetworkConnectionResult
import com.isupatches.android.wisefy.networkconnection.os.apis.Android29NetworkConnectionApi
import com.isupatches.android.wisefy.networkconnection.os.impls.Android29NetworkConnectionApiImpl
import com.isupatches.android.wisefy.shared.logging.WisefyLogger

/**
 * An Android 29 specific adapter for connecting to or disconnecting from a network.
 *
 * @param connectivityManager The ConnectivityManager instance to use
 * @param logger The logger instance to use
 * @param api The OS level API instance to use
 *
 * @see Android29NetworkConnectionApi
 * @see Android29NetworkConnectionApiImpl
 * @see NetworkConnectionApi
 * @see WisefyLogger
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
@RequiresApi(Build.VERSION_CODES.Q)
internal class Android29NetworkConnectionAdapter(
    connectivityManager: ConnectivityManager,
    logger: WisefyLogger,
    private val api: Android29NetworkConnectionApi = Android29NetworkConnectionApiImpl(connectivityManager, logger)
) : NetworkConnectionApi {

    override fun connectToNetwork(request: NetworkConnectionRequest): NetworkConnectionResult {
        val networkRequest = when (request) {
            is NetworkConnectionRequest.SSID -> {
                NetworkRequest.Builder()
                    .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                    .removeCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                    .setNetworkSpecifier(
                        WifiNetworkSpecifier.Builder()
                            .setSsid(request.ssid)
                            .build()
                    )
                    .build()
            }
            is NetworkConnectionRequest.BSSID -> {
                NetworkRequest.Builder()
                    .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                    .removeCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                    .setNetworkSpecifier(
                        WifiNetworkSpecifier.Builder()
                            .setBssid(MacAddress.fromString(request.bssid))
                            .build()
                    )
                    .build()
            }
        }
        api.connectToNetwork(networkRequest, request.timeoutInMillis)
        return NetworkConnectionResult.ConnectionRequestSent
    }

    override fun disconnectFromCurrentNetwork(): NetworkConnectionResult {
        api.disconnectFromCurrentNetwork()
        return NetworkConnectionResult.DisconnectRequestSent
    }
}
