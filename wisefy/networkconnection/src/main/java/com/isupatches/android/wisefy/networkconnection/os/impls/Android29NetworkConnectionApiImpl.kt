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

import android.Manifest.permission.CHANGE_NETWORK_STATE
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.core.logging.WisefyLogger
import com.isupatches.android.wisefy.networkconnection.os.apis.Android29NetworkConnectionApi

/**
 * An Android 29 specific implementation for connecting to or disconnecting from a network through the Android OS.
 *
 * @param connectionManager The ConnectivityManager instance to use
 * @param logger The WisefyLogger instance to use
 *
 * @see Android29NetworkConnectionApi
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
@RequiresApi(Build.VERSION_CODES.Q)
internal class Android29NetworkConnectionApiImpl(
    private val connectionManager: ConnectivityManager,
    private val logger: WisefyLogger
) : Android29NetworkConnectionApi, ConnectivityManager.NetworkCallback() {

    companion object {
        private const val LOG_TAG = "Android29NetworkConnectionApiImpl"
    }

    private var networkCallback: ConnectivityManager.NetworkCallback? = null

    @RequiresPermission(CHANGE_NETWORK_STATE)
    override fun connectToNetwork(request: NetworkRequest, timeoutInMillis: Int) {
        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onLosing(network: Network, maxMsToLive: Int) {
                super.onLosing(network, maxMsToLive)
                logger.d(LOG_TAG, "onLosing")
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                logger.d(LOG_TAG, "onLost")
            }

            override fun onCapabilitiesChanged(network: Network, networkCapabilities: NetworkCapabilities) {
                super.onCapabilitiesChanged(network, networkCapabilities)
                logger.d(LOG_TAG, "onCapabilitiesChanged")
            }

            override fun onLinkPropertiesChanged(network: Network, linkProperties: LinkProperties) {
                super.onLinkPropertiesChanged(network, linkProperties)
                logger.d(LOG_TAG, "onLinkPropertiesChanged")
            }

            override fun onBlockedStatusChanged(network: Network, blocked: Boolean) {
                super.onBlockedStatusChanged(network, blocked)
                logger.d(LOG_TAG, "onBlockedStatusChanged")
            }

            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                logger.d(LOG_TAG, "onAvailable")
            }

            override fun onUnavailable() {
                super.onUnavailable()
                logger.d(LOG_TAG, "onUnavailable")
            }
        }
        this.networkCallback = networkCallback
        logger.d(LOG_TAG, "requestNetwork; timeoutInMillis: $timeoutInMillis, request: $request")
        connectionManager.requestNetwork(request, networkCallback)
    }

    override fun openNetworkScreen(context: Context) {
        context.startActivity(
            Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        )
    }
}
