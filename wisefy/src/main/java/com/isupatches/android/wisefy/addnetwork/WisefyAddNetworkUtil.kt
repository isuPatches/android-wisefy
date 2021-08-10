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
package com.isupatches.android.wisefy.addnetwork

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.CHANGE_WIFI_STATE
import android.net.wifi.WifiManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.addnetwork.delegates.Android29AddNetworkDelegate
import com.isupatches.android.wisefy.addnetwork.delegates.Android30AddNetworkDelegate
import com.isupatches.android.wisefy.addnetwork.delegates.LegacyAddNetworkDelegate
import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkResult
import com.isupatches.android.wisefy.addnetwork.entities.OpenNetworkData
import com.isupatches.android.wisefy.addnetwork.entities.WPA2NetworkData
import com.isupatches.android.wisefy.addnetwork.entities.WPA3NetworkData
import com.isupatches.android.wisefy.callbacks.AddNetworkCallbacks
import com.isupatches.android.wisefy.logging.WisefyLogger
import com.isupatches.android.wisefy.util.SdkUtil
import com.isupatches.android.wisefy.util.coroutines.CoroutineDispatcherProvider
import com.isupatches.android.wisefy.util.coroutines.createBaseCoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal interface AddNetworkUtil : AddNetworkApi, AddNetworkApiAsync

private const val LOG_TAG = "WisefyAddNetworkUtil"

internal class WisefyAddNetworkUtil(
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider,
    logger: WisefyLogger?,
    sdkUtil: SdkUtil,
    wifiManager: WifiManager
) : AddNetworkUtil {

    private val addNetworkScope = CoroutineScope(Job() + coroutineDispatcherProvider.io)

    private val delegate = when {
        sdkUtil.isAtLeastR() -> Android30AddNetworkDelegate(wifiManager)
        sdkUtil.isAtLeastQ() -> Android29AddNetworkDelegate(wifiManager, logger)
        else -> LegacyAddNetworkDelegate(wifiManager)
    }

    init {
        logger?.d(LOG_TAG, "WisefyAddNetworkUtil delegate is: ${delegate::class.java.simpleName}")
    }

    /*
     * Legacy API requires ACCESS_FINE_LOCATION while API 29+ requires CHANGE_WIFI_STATE
     */
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addOpenNetwork(data: OpenNetworkData): AddNetworkResult {
        return delegate.addOpenNetwork(data)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addOpenNetwork(data: OpenNetworkData, callbacks: AddNetworkCallbacks?) {
        addNetworkScope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val addNetworkResult = addOpenNetwork(data)
            withContext(coroutineDispatcherProvider.main) {
                when {
                    addNetworkResult is AddNetworkResult.ResultCode && addNetworkResult.data == -1 -> {
                        callbacks?.onFailureAddingNetwork(addNetworkResult)
                    }
                    else -> callbacks?.onNetworkAdded(addNetworkResult)
                }
            }
        }
    }

    /*
     * Legacy API requires ACCESS_FINE_LOCATION while API 29+ requires CHANGE_WIFI_STATE
     */
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addWPA2Network(data: WPA2NetworkData): AddNetworkResult {
        return delegate.addWPA2Network(data)
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addWPA2Network(data: WPA2NetworkData, callbacks: AddNetworkCallbacks?) {
        addNetworkScope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val addNetworkResult = addWPA2Network(data)
            withContext(coroutineDispatcherProvider.main) {
                when {
                    addNetworkResult is AddNetworkResult.ResultCode && addNetworkResult.data == -1 -> {
                        callbacks?.onFailureAddingNetwork(addNetworkResult)
                    }
                    else -> callbacks?.onNetworkAdded(addNetworkResult)
                }
            }
        }
    }

    /*
     * Legacy API requires ACCESS_FINE_LOCATION while API 29+ requires CHANGE_WIFI_STATE
     */
    @RequiresApi(Build.VERSION_CODES.Q)
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addWPA3Network(data: WPA3NetworkData): AddNetworkResult {
        return delegate.addWPA3Network(data)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    override fun addWPA3Network(data: WPA3NetworkData, callbacks: AddNetworkCallbacks?) {
        addNetworkScope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val addNetworkResult = addWPA3Network(data)
            withContext(coroutineDispatcherProvider.main) {
                when {
                    addNetworkResult is AddNetworkResult.ResultCode && addNetworkResult.data == -1 -> {
                        callbacks?.onFailureAddingNetwork(addNetworkResult)
                    }
                    else -> callbacks?.onNetworkAdded(addNetworkResult)
                }
            }
        }
    }
}
