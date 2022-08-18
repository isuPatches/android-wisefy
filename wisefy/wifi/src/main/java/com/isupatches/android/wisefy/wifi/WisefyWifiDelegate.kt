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
package com.isupatches.android.wisefy.wifi

import android.net.wifi.WifiManager
import com.isupatches.android.wisefy.core.assertions.WisefyAssertions
import com.isupatches.android.wisefy.core.coroutines.CoroutineDispatcherProvider
import com.isupatches.android.wisefy.core.coroutines.createBaseCoroutineExceptionHandler
import com.isupatches.android.wisefy.core.entities.DeprecationMessages
import com.isupatches.android.wisefy.core.logging.WisefyLogger
import com.isupatches.android.wisefy.core.util.SdkUtil
import com.isupatches.android.wisefy.wifi.callbacks.DisableWifiCallbacks
import com.isupatches.android.wisefy.wifi.callbacks.EnableWifiCallbacks
import com.isupatches.android.wisefy.wifi.callbacks.IsWifiEnabledCallbacks
import com.isupatches.android.wisefy.wifi.entities.DisableWifiRequest
import com.isupatches.android.wisefy.wifi.entities.DisableWifiResult
import com.isupatches.android.wisefy.wifi.entities.EnableWifiRequest
import com.isupatches.android.wisefy.wifi.entities.EnableWifiResult
import com.isupatches.android.wisefy.wifi.entities.IsWifiEnabledRequest
import com.isupatches.android.wisefy.wifi.entities.IsWifiEnabledResult
import com.isupatches.android.wisefy.wifi.os.adapters.Android29WifiAdapter
import com.isupatches.android.wisefy.wifi.os.adapters.DefaultWifiAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

/**
 * An internal Wisefy delegate for enabling, disabling, and checking the state of Wifi.
 *
 * @param coroutineDispatcherProvider The [CoroutineDispatcherProvider] instance to use
 * @param scope The coroutine scope to use
 * @param assertions The [WisefyAssertions] instance to use
 * @param logger The [WisefyLogger] instance to use
 * @param sdkUtil The [SdkUtil] instance to use
 * @param wifiManager The WifiManager instance to use
 *
 * @see CoroutineDispatcherProvider
 * @see SdkUtil
 * @see WifiDelegate
 * @see WisefyAssertions
 * @see WisefyLogger
 *
 * @author Patches Klinefelter
 * @since 07/2022, version 5.0.0
 */
class WisefyWifiDelegate(
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider,
    private val scope: CoroutineScope,
    assertions: WisefyAssertions,
    logger: WisefyLogger,
    sdkUtil: SdkUtil,
    wifiManager: WifiManager
) : WifiDelegate {

    companion object {
        private const val LOG_TAG = "WisefyWifiDelegate"
    }

    private val adapter = when {
        sdkUtil.isAtLeastQ() -> Android29WifiAdapter(wifiManager, assertions)
        else -> DefaultWifiAdapter(wifiManager)
    }

    // This will lock the async APIs so that
    private val wifiMutex = Mutex()

    init {
        logger.d(LOG_TAG, "WisefyWifiDelegate adapter is: ${adapter::class.java.simpleName}")
    }

    @Deprecated(DeprecationMessages.Wifi.DISABLE)
    override fun disableWifi(request: DisableWifiRequest): DisableWifiResult {
        return adapter.disableWifi(request)
    }

    @Deprecated(DeprecationMessages.Wifi.DISABLE)
    override fun disableWifi(request: DisableWifiRequest, callbacks: DisableWifiCallbacks?) {
        scope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            wifiMutex.withLock {
                val result = adapter.disableWifi(request)
                withContext(coroutineDispatcherProvider.main) {
                    when (result) {
                        is DisableWifiResult.Success -> callbacks?.onWifiDisabled()
                        is DisableWifiResult.Failure -> callbacks?.onFailureDisablingWifi()
                    }
                }
            }
        }
    }

    @Deprecated(DeprecationMessages.Wifi.ENABLE)
    override fun enableWifi(request: EnableWifiRequest): EnableWifiResult {
        return adapter.enableWifi(request)
    }

    @Deprecated(DeprecationMessages.Wifi.ENABLE)
    override fun enableWifi(request: EnableWifiRequest, callbacks: EnableWifiCallbacks?) {
        scope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            wifiMutex.withLock {
                val result = adapter.enableWifi(request)
                withContext(coroutineDispatcherProvider.main) {
                    when (result) {
                        is EnableWifiResult.Success -> callbacks?.onWifiEnabled()
                        is EnableWifiResult.Failure -> callbacks?.onFailureEnablingWifi()
                    }
                }
            }
        }
    }

    override fun isWifiEnabled(request: IsWifiEnabledRequest): IsWifiEnabledResult {
        return adapter.isWifiEnabled(request)
    }

    override fun isWifiEnabled(request: IsWifiEnabledRequest, callbacks: IsWifiEnabledCallbacks?) {
        scope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            wifiMutex.withLock {
                val result = adapter.isWifiEnabled(request)
                withContext(coroutineDispatcherProvider.main) {
                    when (result) {
                        is IsWifiEnabledResult.True -> callbacks?.onWifiIsEnabled()
                        is IsWifiEnabledResult.False -> callbacks?.onWifiIsDisabled()
                    }
                }
            }
        }
    }
}
