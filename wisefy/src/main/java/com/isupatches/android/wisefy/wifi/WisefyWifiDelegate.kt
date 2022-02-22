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
package com.isupatches.android.wisefy.wifi

import android.net.wifi.WifiManager
import com.isupatches.android.wisefy.constants.DeprecationMessages
import com.isupatches.android.wisefy.logging.WisefyLogger
import com.isupatches.android.wisefy.util.SdkUtil
import com.isupatches.android.wisefy.util.coroutines.CoroutineDispatcherProvider
import com.isupatches.android.wisefy.util.coroutines.createBaseCoroutineExceptionHandler
import com.isupatches.android.wisefy.wifi.callbacks.DisableWifiCallbacks
import com.isupatches.android.wisefy.wifi.callbacks.EnableWifiCallbacks
import com.isupatches.android.wisefy.wifi.proxies.Android29WifiProxy
import com.isupatches.android.wisefy.wifi.proxies.DefaultWifiProxy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal interface WifiDelegate : WifiApi, WifiApiAsync

private const val LOG_TAG = "WisefyWifiDelegate"

internal class WisefyWifiDelegate(
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider,
    private val scope: CoroutineScope,
    logger: WisefyLogger?,
    sdkUtil: SdkUtil,
    wifiManager: WifiManager
) : WifiDelegate {

    private val proxy = when {
        sdkUtil.isAtLeastQ() -> Android29WifiProxy(wifiManager)
        else -> DefaultWifiProxy(wifiManager)
    }

    init {
        logger?.d(LOG_TAG, "WisefyWifiDelegate proxy is: ${proxy::class.java.simpleName}")
    }

    @Deprecated(DeprecationMessages.DISABLE_WIFI)
    override fun disableWifi(): Boolean {
        return proxy.disableWifi()
    }

    @Deprecated(DeprecationMessages.DISABLE_WIFI)
    override fun disableWifi(callbacks: DisableWifiCallbacks?) {
        scope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val result = proxy.disableWifi()
            withContext(coroutineDispatcherProvider.main) {
                if (result) {
                    callbacks?.onWifiDisabled()
                } else {
                    callbacks?.onFailureDisablingWifi()
                }
            }
        }
    }

    @Deprecated(DeprecationMessages.ENABLE_WIFI)
    override fun enableWifi(): Boolean {
        return proxy.enableWifi()
    }

    @Deprecated(DeprecationMessages.ENABLE_WIFI)
    override fun enableWifi(callbacks: EnableWifiCallbacks?) {
        scope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val result = proxy.enableWifi()
            withContext(coroutineDispatcherProvider.main) {
                if (result) {
                    callbacks?.onWifiEnabled()
                } else {
                    callbacks?.onFailureEnablingWifi()
                }
            }
        }
    }

    override fun isWifiEnabled(): Boolean {
        return proxy.isWifiEnabled()
    }
}
