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
package com.isupatches.android.wisefy.networkconnection

import android.net.wifi.WifiManager
import com.isupatches.android.wisefy.logging.WisefyLogger
import com.isupatches.android.wisefy.networkconnection.delegates.LegacyNetworkConnectionDelegate
import com.isupatches.android.wisefy.networkconnectionstatus.NetworkConnectionStatusUtil
import com.isupatches.android.wisefy.savednetworks.SavedNetworkUtil

internal interface NetworkConnectionUtil : NetworkConnectionApi

private const val LOG_TAG = "WisefyNetworkConnectionUtil"

internal class WisefyNetworkConnectionUtil(
    wifiManager: WifiManager,
    networkConnectionStatusUtil: NetworkConnectionStatusUtil,
    savedNetworkUtil: SavedNetworkUtil,
    logger: WisefyLogger?
) : NetworkConnectionUtil {

    private val delegate = LegacyNetworkConnectionDelegate(
        wifiManager,
        networkConnectionStatusUtil,
        savedNetworkUtil,
        logger
    )

    init {
        logger?.d(LOG_TAG, "WisefyNetworkConnectionUtil delegate is: ${delegate::class.java.simpleName}")
    }

    override fun connectToNetwork(ssidToConnectTo: String, timeoutInMillis: Int): Boolean {
        return delegate.connectToNetwork(ssidToConnectTo, timeoutInMillis)
    }

    override fun disconnectFromCurrentNetwork(): Boolean {
        return disconnectFromCurrentNetwork()
    }
}
