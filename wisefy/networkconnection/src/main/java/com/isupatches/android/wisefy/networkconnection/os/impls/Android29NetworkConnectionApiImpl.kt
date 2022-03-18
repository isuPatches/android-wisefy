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
package com.isupatches.android.wisefy.networkconnection.os.impls

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Build
import androidx.annotation.RequiresApi
import com.isupatches.android.wisefy.networkconnection.os.apis.Android29NetworkConnectionApi
import com.isupatches.android.wisefy.shared.logging.WisefyLogger

internal class Android29NetworkConnectionApiImpl(
    private val connectionManager: ConnectivityManager,
    private val logger: WisefyLogger
) : Android29NetworkConnectionApi, ConnectivityManager.NetworkCallback() {

    companion object {
        private const val LOG_TAG = "Android29NetworkConnectionApiImpl"
    }

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            logger.d(LOG_TAG, "Network available")
        }

        override fun onUnavailable() {
            super.onUnavailable()
            logger.d(LOG_TAG, "Network unavailable")
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun connectToNetwork(request: NetworkRequest, timeoutInMillis: Int) {
        connectionManager.requestNetwork(request, networkCallback, timeoutInMillis)
    }

    override fun disconnectFromCurrentNetwork() {
        connectionManager.unregisterNetworkCallback(networkCallback)
    }
}
