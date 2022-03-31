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

import android.net.wifi.WifiManager
import com.isupatches.android.wisefy.networkconnection.NetworkConnectionApi
import com.isupatches.android.wisefy.networkconnection.entities.NetworkConnectionRequest
import com.isupatches.android.wisefy.networkconnection.entities.NetworkConnectionResult
import com.isupatches.android.wisefy.networkconnection.os.apis.DefaultNetworkConnectionApi
import com.isupatches.android.wisefy.networkconnection.os.impls.DefaultNetworkConnectionApiImpl
import com.isupatches.android.wisefy.networkconnectionstatus.NetworkConnectionStatusDelegate
import com.isupatches.android.wisefy.savednetworks.SavedNetworkDelegate
import com.isupatches.android.wisefy.shared.logging.WisefyLogger

/**
 * An Android 29 specific adapter for connecting to or disconnecting from a network.
 *
 * @param wifiManager The WifiManager instance to use
 * @param networkConnectionStatusDelegate The NetworkConnectionStatusDelegate instance to use
 * @param savedNetworkDelegate The SavedNetworkDelegate instance to use
 * @param logger The logger instance to use
 * @param api The OS level API instance to use
 *
 * @see DefaultNetworkConnectionApi
 * @see DefaultNetworkConnectionApiImpl
 * @see NetworkConnectionApi
 * @see NetworkConnectionStatusDelegate
 * @see SavedNetworkDelegate
 * @see WisefyLogger
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
internal class DefaultNetworkConnectionAdapter(
    wifiManager: WifiManager,
    networkConnectionStatusDelegate: NetworkConnectionStatusDelegate,
    savedNetworkDelegate: SavedNetworkDelegate,
    logger: WisefyLogger,
    private val api: DefaultNetworkConnectionApi = DefaultNetworkConnectionApiImpl(
        wifiManager,
        networkConnectionStatusDelegate,
        savedNetworkDelegate,
        logger
    )
) : NetworkConnectionApi {

    override fun connectToNetwork(request: NetworkConnectionRequest): NetworkConnectionResult {
        return when (request) {
            is NetworkConnectionRequest.SSID -> {
                when (api.connectToNetworkBySSID(request.ssid, request.timeoutInMillis)) {
                    true -> NetworkConnectionResult.Boolean.True
                    false -> NetworkConnectionResult.Boolean.False
                    null -> NetworkConnectionResult.NetworkNotFound
                }
            }
            is NetworkConnectionRequest.BSSID -> {
                when (api.connectToNetworkBySSID(request.bssid, request.timeoutInMillis)) {
                    true -> NetworkConnectionResult.Boolean.True
                    false -> NetworkConnectionResult.Boolean.False
                    null -> NetworkConnectionResult.NetworkNotFound
                }
            }
        }
    }

    override fun disconnectFromCurrentNetwork(): NetworkConnectionResult {
        return if (api.disconnectFromCurrentNetwork()) {
            NetworkConnectionResult.Boolean.True
        } else {
            NetworkConnectionResult.Boolean.False
        }
    }
}
