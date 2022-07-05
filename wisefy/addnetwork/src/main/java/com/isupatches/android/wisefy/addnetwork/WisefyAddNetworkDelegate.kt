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
import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkResult
import com.isupatches.android.wisefy.addnetwork.entities.AddOpenNetworkRequest
import com.isupatches.android.wisefy.addnetwork.entities.AddWPA2NetworkRequest
import com.isupatches.android.wisefy.addnetwork.entities.AddWPA3NetworkRequest
import com.isupatches.android.wisefy.addnetwork.os.adapters.Android29AddNetworkAdapter
import com.isupatches.android.wisefy.addnetwork.os.adapters.Android30AddNetworkAdapter
import com.isupatches.android.wisefy.addnetwork.os.adapters.DefaultAddNetworkAdapter
import com.isupatches.android.wisefy.core.coroutines.CoroutineDispatcherProvider
import com.isupatches.android.wisefy.core.coroutines.createBaseCoroutineExceptionHandler
import com.isupatches.android.wisefy.core.logging.WisefyLogger
import com.isupatches.android.wisefy.core.util.SdkUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * An internal Wisefy delegate for adding networks.
 *
 * @param coroutineDispatcherProvider The instance of the coroutine dispatcher provider to use
 * @param scope The coroutine scope to use
 * @param logger The logger instance to use
 * @param sdkUtil The SDKUtil instance to use
 * @param wifiManager The WifiManager instance to use
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
    logger: WisefyLogger,
    sdkUtil: SdkUtil,
    wifiManager: WifiManager
) : AddNetworkDelegate {

    companion object {
        private const val LOG_TAG = "WisefyAddNetworkDelegate"
    }

    private val adapter = when {
        sdkUtil.isAtLeastR() -> Android30AddNetworkAdapter(wifiManager)
        sdkUtil.isAtLeastQ() -> Android29AddNetworkAdapter(wifiManager)
        else -> DefaultAddNetworkAdapter(wifiManager)
    }

    init {
        logger.d(LOG_TAG, "WisefyAddNetworkDelegate adapter is: ${adapter::class.java.simpleName}")
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addOpenNetwork(request: AddOpenNetworkRequest): AddNetworkResult {
        return adapter.addOpenNetwork(request)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addOpenNetwork(request: AddOpenNetworkRequest, callbacks: AddNetworkCallbacks?) {
        scope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val result = addOpenNetwork(request)
            withContext(coroutineDispatcherProvider.main) {
                when (result) {
                    is AddNetworkResult.Success -> callbacks?.onNetworkAdded(result)
                    is AddNetworkResult.Failure -> callbacks?.onFailureAddingNetwork(result)
                }
            }
        }
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addWPA2Network(request: AddWPA2NetworkRequest): AddNetworkResult {
        return adapter.addWPA2Network(request)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addWPA2Network(request: AddWPA2NetworkRequest, callbacks: AddNetworkCallbacks?) {
        scope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val result = addWPA2Network(request)
            withContext(coroutineDispatcherProvider.main) {
                when (result) {
                    is AddNetworkResult.Success -> callbacks?.onNetworkAdded(result)
                    is AddNetworkResult.Failure -> callbacks?.onFailureAddingNetwork(result)
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addWPA3Network(request: AddWPA3NetworkRequest): AddNetworkResult {
        return adapter.addWPA3Network(request)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addWPA3Network(request: AddWPA3NetworkRequest, callbacks: AddNetworkCallbacks?) {
        scope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val result = addWPA3Network(request)
            withContext(coroutineDispatcherProvider.main) {
                when (result) {
                    is AddNetworkResult.Success -> callbacks?.onNetworkAdded(result)
                    is AddNetworkResult.Failure -> callbacks?.onFailureAddingNetwork(result)
                }
            }
        }
    }
}
