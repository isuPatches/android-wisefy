/*
 * Copyright 2022 Patches Barrett
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
package com.isupatches.android.wisefy.networkconnectionstatus

import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import com.isupatches.android.wisefy.core.entities.NetworkConnectionStatus
import com.isupatches.android.wisefy.core.logging.WisefyLogger

internal class WisefyNetworkCallbacks(
    private val logger: WisefyLogger,
    private val onNetworkConnectionStatusUpdated: (NetworkConnectionStatus) -> Unit
) : ConnectivityManager.NetworkCallback() {

    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        logger.d(LOG_TAG, "onAvailable, $network")
        onNetworkConnectionStatusUpdated(NetworkConnectionStatus.AVAILABLE)
    }

    override fun onCapabilitiesChanged(network: Network, networkCapabilities: NetworkCapabilities) {
        super.onCapabilitiesChanged(network, networkCapabilities)
        logger.d(
            LOG_TAG,
            "onCapabilitiesChanged, network: $network, networkCapabilities: $networkCapabilities"
        )
    }

    override fun onLinkPropertiesChanged(network: Network, linkProperties: LinkProperties) {
        super.onLinkPropertiesChanged(network, linkProperties)
        logger.d(LOG_TAG, "onLinkPropertiesChanged, network: $network, linkProperties: $linkProperties")
    }

    override fun onLosing(network: Network, maxMsToLive: Int) {
        super.onLosing(network, maxMsToLive)
        logger.d(LOG_TAG, "onLosing, network: $network, maxMsToLive: $maxMsToLive")
        onNetworkConnectionStatusUpdated(NetworkConnectionStatus.LOSING)
    }

    override fun onLost(network: Network) {
        super.onLost(network)
        logger.d(LOG_TAG, "onLost, network: $network")
        onNetworkConnectionStatusUpdated(NetworkConnectionStatus.LOST)
    }

    companion object {
        private const val LOG_TAG = "WisefyNetworkCallback"
    }
}
