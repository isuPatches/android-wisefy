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
package com.isupatches.android.wisefy.wifi

import android.Manifest.permission.ACCESS_WIFI_STATE
import android.Manifest.permission.CHANGE_WIFI_STATE
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.core.assertions.WisefyAssertions
import com.isupatches.android.wisefy.core.coroutines.CoroutineDispatcherProvider
import com.isupatches.android.wisefy.core.coroutines.createBaseCoroutineExceptionHandler
import com.isupatches.android.wisefy.core.logging.WisefyLogger
import com.isupatches.android.wisefy.core.util.SdkUtil
import com.isupatches.android.wisefy.wifi.callbacks.DisableWifiCallbacks
import com.isupatches.android.wisefy.wifi.callbacks.EnableWifiCallbacks
import com.isupatches.android.wisefy.wifi.callbacks.IsWifiEnabledCallbacks
import com.isupatches.android.wisefy.wifi.entities.DisableWifiRequest
import com.isupatches.android.wisefy.wifi.entities.DisableWifiResult
import com.isupatches.android.wisefy.wifi.entities.EnableWifiRequest
import com.isupatches.android.wisefy.wifi.entities.EnableWifiResult
import com.isupatches.android.wisefy.wifi.entities.IsWifiEnabledQuery
import com.isupatches.android.wisefy.wifi.entities.IsWifiEnabledResult
import com.isupatches.android.wisefy.wifi.os.adapters.Android29WifiAdapter
import com.isupatches.android.wisefy.wifi.os.adapters.DefaultWifiAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

/**
 * An internal Wisefy delegate for enabling, disabling, and checking the state of wifi.
 *
 * @param assertions The [WisefyAssertions] instance to use
 * @param logger The [WisefyLogger] instance to use
 * @param sdkUtil The [SdkUtil] instance to use
 * @param wifiManager The WifiManager instance to use
 * @property coroutineDispatcherProvider The [CoroutineDispatcherProvider] instance to use
 * @property scope The coroutine scope to use
 * @property wifiMutex The mutex for all read/write operations involving wifi
 * @property adapter The adapter instance to use for wifi operations (determined based on the Android OS level)
 *
 * @see Android29WifiAdapter
 * @see CoroutineDispatcherProvider
 * @see DefaultWifiAdapter
 * @see SdkUtil
 * @see WifiDelegate
 * @see WisefyAssertions
 * @see WisefyLogger
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
class WisefyWifiDelegate(
    assertions: WisefyAssertions,
    logger: WisefyLogger,
    sdkUtil: SdkUtil,
    wifiManager: WifiManager,
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider,
    private val scope: CoroutineScope,
    private val wifiMutex: Mutex,
    private val adapter: WifiApi = if (sdkUtil.isAtLeastQ()) {
        Android29WifiAdapter(wifiManager, logger, assertions)
    } else {
        DefaultWifiAdapter(wifiManager, logger, assertions)
    }
) : WifiDelegate {

    init {
        logger.d(LOG_TAG, "WisefyWifiDelegate adapter is: ${adapter::class.java.simpleName}")
    }

    @RequiresPermission(CHANGE_WIFI_STATE)
    override fun disableWifi(request: DisableWifiRequest): DisableWifiResult {
        return adapter.disableWifi(request)
    }

    @RequiresPermission(CHANGE_WIFI_STATE)
    override fun disableWifi(request: DisableWifiRequest, callbacks: DisableWifiCallbacks?) {
        scope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            wifiMutex.withLock {
                val result = adapter.disableWifi(request)
                withContext(coroutineDispatcherProvider.main) {
                    when (result) {
                        is DisableWifiResult.Success -> callbacks?.onSuccessDisablingWifi(result)
                        is DisableWifiResult.Failure -> callbacks?.onFailureDisablingWifi(result)
                    }
                }
            }
        }
    }

    @RequiresPermission(CHANGE_WIFI_STATE)
    override fun enableWifi(request: EnableWifiRequest): EnableWifiResult {
        return adapter.enableWifi(request)
    }

    @RequiresPermission(CHANGE_WIFI_STATE)
    override fun enableWifi(request: EnableWifiRequest, callbacks: EnableWifiCallbacks?) {
        scope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            wifiMutex.withLock {
                val result = adapter.enableWifi(request)
                withContext(coroutineDispatcherProvider.main) {
                    when (result) {
                        is EnableWifiResult.Success -> callbacks?.onSuccessEnablingWifi(result)
                        is EnableWifiResult.Failure -> callbacks?.onFailureEnablingWifi(result)
                    }
                }
            }
        }
    }

    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun isWifiEnabled(query: IsWifiEnabledQuery): IsWifiEnabledResult {
        return adapter.isWifiEnabled(query)
    }

    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun isWifiEnabled(query: IsWifiEnabledQuery, callbacks: IsWifiEnabledCallbacks?) {
        scope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            wifiMutex.withLock {
                val result = adapter.isWifiEnabled(query)
                withContext(coroutineDispatcherProvider.main) {
                    when (result) {
                        is IsWifiEnabledResult.True -> callbacks?.onWifiIsEnabled()
                        is IsWifiEnabledResult.False -> callbacks?.onWifiIsDisabled()
                    }
                }
            }
        }
    }

    companion object {
        private const val LOG_TAG = "WisefyWifiDelegate"
    }
}
