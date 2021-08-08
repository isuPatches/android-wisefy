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
package com.isupatches.android.wisefy.networkinfo

import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.net.ConnectivityManager
import android.net.Network
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.callbacks.GetCurrentNetworkCallbacks
import com.isupatches.android.wisefy.callbacks.GetCurrentNetworkInfoCallbacks
import com.isupatches.android.wisefy.callbacks.GetIPCallbacks
import com.isupatches.android.wisefy.logging.WisefyLogger
import com.isupatches.android.wisefy.networkinfo.delegates.LegacyNetworkInfoDelegate
import com.isupatches.android.wisefy.networkinfo.entities.CurrentNetworkData
import com.isupatches.android.wisefy.networkinfo.entities.CurrentNetworkInfoData
import com.isupatches.android.wisefy.util.coroutines.CoroutineDispatcherProvider
import com.isupatches.android.wisefy.util.coroutines.createBaseCoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal interface NetworkInfoUtil : NetworkInfoApi, NetworkInfoApiAsync

private const val LOG_TAG = "WisefyNetworkInfoUtil"

internal class WisefyNetworkInfoUtil(
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider,
    connectivityManager: ConnectivityManager,
    logger: WisefyLogger?,
    wifiManager: WifiManager
) : NetworkInfoUtil {

    private val delegate = LegacyNetworkInfoDelegate(wifiManager, connectivityManager, logger)
    private val networkInfoScope = CoroutineScope(Job() + coroutineDispatcherProvider.io)

    init {
        logger?.d(LOG_TAG, "WisefyNetworkInfoUtil delegate is: ${delegate::class.java.simpleName}")
    }

    override fun getCurrentNetwork(): CurrentNetworkData? {
        return delegate.getCurrentNetwork()
    }

    override fun getCurrentNetwork(callbacks: GetCurrentNetworkCallbacks?) {
        networkInfoScope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val currentNetwork = delegate.getCurrentNetwork()
            withContext(coroutineDispatcherProvider.main) {
                if (currentNetwork != null) {
                    callbacks?.retrievedCurrentNetwork(currentNetwork)
                } else {
                    callbacks?.noCurrentNetwork()
                }
            }
        }
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getCurrentNetworkInfo(network: Network?): CurrentNetworkInfoData? {
        return delegate.getCurrentNetworkInfo(network)
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getCurrentNetworkInfo(
        network: Network?,
        callbacks: GetCurrentNetworkInfoCallbacks?
    ) {
        networkInfoScope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val currentNetworkInfo = delegate.getCurrentNetworkInfo(network)
            withContext(coroutineDispatcherProvider.main) {
                if (currentNetworkInfo != null) {
                    callbacks?.retrievedCurrentNetworkInfo(currentNetworkInfo)
                } else {
                    callbacks?.noCurrentNetworkInfo()
                }
            }
        }
    }

    override fun getIP(): String? {
        return delegate.getIP()
    }

    override fun getIP(callbacks: GetIPCallbacks?) {
        networkInfoScope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val ip = delegate.getIP()
            withContext(coroutineDispatcherProvider.main) {
                if (ip != null) {
                    callbacks?.retrievedIP(ip)
                } else {
                    callbacks?.failureRetrievingIP()
                }
            }
        }
    }
}
