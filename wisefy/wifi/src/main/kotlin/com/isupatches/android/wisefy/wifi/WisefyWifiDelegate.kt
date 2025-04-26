/*
 * Copyright (c) 2024. Patches Barrett
 *
 * Last modified: September 22, 2024
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
import com.isupatches.android.wisefy.core.assertions.NoOpWisefyAssertions
import com.isupatches.android.wisefy.core.assertions.WisefyAssertions
import com.isupatches.android.wisefy.core.exceptions.WisefyException
import com.isupatches.android.wisefy.core.logging.NoOpWisefyLogger
import com.isupatches.android.wisefy.core.logging.WisefyLogger
import com.isupatches.android.wisefy.core.util.AndroidUtil
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
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlin.coroutines.cancellation.CancellationException

/**
 * An internal Wisefy delegate for enabling, disabling, and checking the state of wifi.
 *
 * @param wifiManager The WifiManager instance to use
 * @param scope The coroutine scope to use
 * @param wifiMutex The mutex for all read/write operations involving wifi
 * @param assertions The [WisefyAssertions] instance to use (defaults to no-op)
 * @param logger The [WisefyLogger] instance to use (defaults to no-op)
 * @param mainDispatcher The main thread dispatcher
 * @param adapter The adapter instance to use for wifi operations (determined based on the Android OS level)
 *
 * @see Android29WifiAdapter
 * @see DefaultWifiAdapter
 * @see WifiDelegate
 * @see WisefyAssertions
 * @see WisefyLogger
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
@Suppress("LongParameterList")
class WisefyWifiDelegate(
    wifiManager: WifiManager,
    private val scope: CoroutineScope,
    private val wifiMutex: Mutex,
    assertions: WisefyAssertions = NoOpWisefyAssertions(),
    logger: WisefyLogger = NoOpWisefyLogger(),
    private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main,
    private val adapter: WifiApi = if (AndroidUtil.isAtLeastQ()) {
        Android29WifiAdapter(wifiManager, logger, assertions)
    } else {
        DefaultWifiAdapter(wifiManager, logger, assertions)
    },
) : WifiDelegate {

    init {
        logger.d(LOG_TAG, "WisefyWifiDelegate adapter is: ${adapter::class.java.simpleName}")
    }

    @RequiresPermission(CHANGE_WIFI_STATE)
    override fun disableWifi(request: DisableWifiRequest): DisableWifiResult {
        return adapter.disableWifi(request)
    }

    @RequiresPermission(CHANGE_WIFI_STATE)
    @Suppress("TooGenericExceptionCaught")
    override fun disableWifi(
        request: DisableWifiRequest,
        callbacks: DisableWifiCallbacks?,
    ) {
        scope.launch {
            wifiMutex.withLock {
                try {
                    val result = adapter.disableWifi(request)
                    withContext(mainDispatcher) {
                        when (result) {
                            is DisableWifiResult.Success -> callbacks?.onSuccessDisablingWifi(result)
                            is DisableWifiResult.Failure -> callbacks?.onFailureDisablingWifi(result)
                        }
                    }
                } catch (ex: CancellationException) {
                    throw ex
                } catch (ex: Exception) {
                    withContext(mainDispatcher) {
                        callbacks?.onWisefyAsyncFailure(
                            WisefyException(message = "Internal Wisefy error with disableWifi", throwable = ex),
                        )
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
    @Suppress("TooGenericExceptionCaught")
    override fun enableWifi(
        request: EnableWifiRequest,
        callbacks: EnableWifiCallbacks?,
    ) {
        scope.launch {
            wifiMutex.withLock {
                try {
                    val result = adapter.enableWifi(request)
                    withContext(mainDispatcher) {
                        when (result) {
                            is EnableWifiResult.Success -> callbacks?.onSuccessEnablingWifi(result)
                            is EnableWifiResult.Failure -> callbacks?.onFailureEnablingWifi(result)
                        }
                    }
                } catch (ex: CancellationException) {
                    throw ex
                } catch (ex: Exception) {
                    withContext(mainDispatcher) {
                        callbacks?.onWisefyAsyncFailure(
                            WisefyException(message = "Internal Wisefy error with enableWifi", throwable = ex),
                        )
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
    @Suppress("TooGenericExceptionCaught")
    override fun isWifiEnabled(
        query: IsWifiEnabledQuery,
        callbacks: IsWifiEnabledCallbacks?,
    ) {
        scope.launch {
            wifiMutex.withLock {
                try {
                    val result = adapter.isWifiEnabled(query)
                    withContext(mainDispatcher) {
                        when (result) {
                            is IsWifiEnabledResult.True -> callbacks?.onWifiIsEnabled()
                            is IsWifiEnabledResult.False -> callbacks?.onWifiIsDisabled()
                        }
                    }
                } catch (ex: CancellationException) {
                    throw ex
                } catch (ex: Exception) {
                    withContext(mainDispatcher) {
                        callbacks?.onWisefyAsyncFailure(
                            WisefyException(message = "Internal Wisefy error with isWifiEnabled", throwable = ex),
                        )
                    }
                }
            }
        }
    }

    companion object {
        private const val LOG_TAG = "WisefyWifiDelegate"
    }
}
