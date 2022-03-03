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
package com.isupatches.android.wisefy.addnetwork

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.CHANGE_WIFI_STATE
import android.net.wifi.WifiManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.addnetwork.callbacks.AddNetworkCallbacks
import com.isupatches.android.wisefy.addnetwork.proxies.Android29AddNetworkProxy
import com.isupatches.android.wisefy.addnetwork.proxies.Android30AddNetworkProxy
import com.isupatches.android.wisefy.addnetwork.proxies.DefaultAddNetworkProxy
import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkResult
import com.isupatches.android.wisefy.addnetwork.entities.AddOpenNetworkRequest
import com.isupatches.android.wisefy.addnetwork.entities.AddWPA2NetworkRequest
import com.isupatches.android.wisefy.addnetwork.entities.AddWPA3NetworkRequest
import com.isupatches.android.wisefy.shared.coroutines.CoroutineDispatcherProvider
import com.isupatches.android.wisefy.shared.coroutines.createBaseCoroutineExceptionHandler
import com.isupatches.android.wisefy.shared.logging.WisefyLogger
import com.isupatches.android.wisefy.shared.util.SdkUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A delegate for synchronous and asynchronous APIs to add networks.
 *
 * @see AddNetworkApi
 * @see AddNetworkApiAsync
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
interface AddNetworkDelegate : AddNetworkApi, AddNetworkApiAsync

private const val LOG_TAG = "WisefyAddNetworkDelegate"

/**
 * A default delegate for adding networks.
 *
 * @see AddNetworkDelegate
 * @see CoroutineDispatcherProvider
 * @see WisefyLogger
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
class WisefyAddNetworkDelegate(
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider,
    private val scope: CoroutineScope,
    logger: WisefyLogger?,
    sdkUtil: SdkUtil,
    wifiManager: WifiManager
) : AddNetworkDelegate {

    private val proxy = when {
        sdkUtil.isAtLeastR() -> Android30AddNetworkProxy(wifiManager)
        sdkUtil.isAtLeastQ() -> Android29AddNetworkProxy(wifiManager, logger)
        else -> DefaultAddNetworkProxy(wifiManager)
    }

    init {
        logger?.d(LOG_TAG, "WisefyAddNetworkDelegate proxy is: ${proxy::class.java.simpleName}")
    }

    /*
     * Legacy API requires ACCESS_FINE_LOCATION while API 29+ requires CHANGE_WIFI_STATE
     */
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addOpenNetwork(request: AddOpenNetworkRequest): AddNetworkResult {
        return proxy.addOpenNetwork(request)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addOpenNetwork(request: AddOpenNetworkRequest, callbacks: AddNetworkCallbacks?) {
        scope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val addNetworkResult = addOpenNetwork(request)
            withContext(coroutineDispatcherProvider.main) {
                when (addNetworkResult) {
                    is AddNetworkResult.Success -> callbacks?.onNetworkAdded(addNetworkResult)
                    is AddNetworkResult.Failure -> callbacks?.onFailureAddingNetwork(addNetworkResult)
                }
            }
        }
    }

    /*
     * Legacy API requires ACCESS_FINE_LOCATION while API 29+ requires CHANGE_WIFI_STATE
     */
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addWPA2Network(request: AddWPA2NetworkRequest): AddNetworkResult {
        return proxy.addWPA2Network(request)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addWPA2Network(request: AddWPA2NetworkRequest, callbacks: AddNetworkCallbacks?) {
        scope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val addNetworkResult = addWPA2Network(request)
            withContext(coroutineDispatcherProvider.main) {
                when (addNetworkResult) {
                    is AddNetworkResult.Success -> callbacks?.onNetworkAdded(addNetworkResult)
                    is AddNetworkResult.Failure -> callbacks?.onFailureAddingNetwork(addNetworkResult)
                }
            }
        }
    }

    /*
     * Legacy API requires ACCESS_FINE_LOCATION while API 29+ requires CHANGE_WIFI_STATE
     */
    @RequiresApi(Build.VERSION_CODES.Q)
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addWPA3Network(request: AddWPA3NetworkRequest): AddNetworkResult {
        return proxy.addWPA3Network(request)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addWPA3Network(request: AddWPA3NetworkRequest, callbacks: AddNetworkCallbacks?) {
        scope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val addNetworkResult = addWPA3Network(request)
            withContext(coroutineDispatcherProvider.main) {
                when (addNetworkResult) {
                    is AddNetworkResult.Success -> callbacks?.onNetworkAdded(addNetworkResult)
                    is AddNetworkResult.Failure -> callbacks?.onFailureAddingNetwork(addNetworkResult)
                }
            }
        }
    }
}
