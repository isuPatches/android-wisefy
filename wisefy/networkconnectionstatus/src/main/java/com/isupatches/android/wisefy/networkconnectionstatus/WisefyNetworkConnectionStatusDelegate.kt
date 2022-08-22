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
package com.isupatches.android.wisefy.networkconnectionstatus

import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.core.coroutines.CoroutineDispatcherProvider
import com.isupatches.android.wisefy.core.coroutines.createBaseCoroutineExceptionHandler
import com.isupatches.android.wisefy.core.logging.WisefyLogger
import com.isupatches.android.wisefy.core.util.SdkUtil
import com.isupatches.android.wisefy.networkconnectionstatus.callbacks.GetNetworkConnectionStatusCallbacks
import com.isupatches.android.wisefy.networkconnectionstatus.entities.GetNetworkConnectionStatusRequest
import com.isupatches.android.wisefy.networkconnectionstatus.entities.GetNetworkConnectionStatusResult
import com.isupatches.android.wisefy.networkconnectionstatus.os.adapters.DefaultNetworkConnectionStatusAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * An internal Wisefy delegate for checking the device's connection status and if it meets certain criteria.
 *
 * @param connectivityManager The ConnectivityManager instance to use
 * @param logger The logger instance to use
 * @param sdkUtil The SdkUtil instance to use
 * @param wifiManager The WifiManager instance to use
 *
 * @see NetworkConnectionStatusDelegate
 * @see SdkUtil
 * @see WisefyLogger
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
class WisefyNetworkConnectionStatusDelegate(
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider,
    private val scope: CoroutineScope,
    connectivityManager: ConnectivityManager,
    logger: WisefyLogger,
    sdkUtil: SdkUtil,
    wifiManager: WifiManager
) : NetworkConnectionStatusDelegate {

    companion object {
        private const val LOG_TAG = "WisefyNetworkConnectionStatusDelegate"
    }

    private val adapter = DefaultNetworkConnectionStatusAdapter(
        connectivityManager = connectivityManager,
        wifiManager = wifiManager,
        sdkUtil = sdkUtil,
        logger = logger
    )

    init {
        logger.d(LOG_TAG, "WisefyNetworkConnectionStatusDelegate adapter is: ${adapter::class.java.simpleName}")
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun attachNetworkWatcher() {
        adapter.attachNetworkWatcher()
    }

    override fun detachNetworkWatcher() {
        adapter.detachNetworkWatcher()
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getNetworkConnectionStatus(request: GetNetworkConnectionStatusRequest): GetNetworkConnectionStatusResult {
        return adapter.getNetworkConnectionStatus(request)
    }

    @RequiresPermission(ACCESS_NETWORK_STATE)
    override fun getNetworkConnectionStatus(
        request: GetNetworkConnectionStatusRequest,
        callbacks: GetNetworkConnectionStatusCallbacks?
    ) {
        scope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val currentNetworkConnectionStatus = adapter.getNetworkConnectionStatus(request)
            withContext(coroutineDispatcherProvider.main) {
                callbacks?.onDeviceNetworkConnectionStatusRetrieved(currentNetworkConnectionStatus)
            }
        }
    }
}
