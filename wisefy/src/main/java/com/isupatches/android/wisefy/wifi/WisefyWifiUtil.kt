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
import com.isupatches.android.wisefy.callbacks.DisableWifiCallbacks
import com.isupatches.android.wisefy.callbacks.EnableWifiCallbacks
import com.isupatches.android.wisefy.constants.DeprecationMessages
import com.isupatches.android.wisefy.logging.WisefyLogger
import com.isupatches.android.wisefy.util.coroutines.CoroutineDispatcherProvider
import com.isupatches.android.wisefy.util.SdkUtil
import com.isupatches.android.wisefy.util.coroutines.createBaseCoroutineExceptionHandler
import com.isupatches.android.wisefy.wifi.delegates.Android29WifiDelegate
import com.isupatches.android.wisefy.wifi.delegates.LegacyWifiDelegate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal interface WifiUtil : WifiApi, WifiApiAsync

private const val LOG_TAG = "WisefyWifiUtil"

internal class WisefyWifiUtil(
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider,
    logger: WisefyLogger?,
    sdkUtil: SdkUtil,
    wifiManager: WifiManager
) : WifiUtil {

    private val delegate = when {
        sdkUtil.isAtLeastQ() -> Android29WifiDelegate(wifiManager)
        else -> LegacyWifiDelegate(wifiManager)
    }
    private val wifiScope = CoroutineScope(Job() + coroutineDispatcherProvider.io)

    init {
        logger?.d(LOG_TAG, "WisefyWifiUtil delegate is: ${delegate::class.java.simpleName}")
    }

    @Deprecated(DeprecationMessages.DISABLE_WIFI)
    override fun disableWifi(): Boolean {
        return delegate.disableWifi()
    }

    @Deprecated(DeprecationMessages.DISABLE_WIFI)
    override fun disableWifi(callbacks: DisableWifiCallbacks?) {
        wifiScope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val result = delegate.disableWifi()
            withContext(coroutineDispatcherProvider.main) {
                if (result) {
                    callbacks?.wifiDisabled()
                } else {
                    callbacks?.failureDisablingWifi()
                }
            }
        }
    }

    @Deprecated(DeprecationMessages.ENABLE_WIFI)
    override fun enableWifi(): Boolean {
        return delegate.enableWifi()
    }

    @Deprecated(DeprecationMessages.ENABLE_WIFI)
    override fun enableWifi(callbacks: EnableWifiCallbacks?) {
        wifiScope.launch(createBaseCoroutineExceptionHandler(callbacks)) {
            val result = delegate.enableWifi()
            withContext(coroutineDispatcherProvider.main) {
                if (result) {
                    callbacks?.wifiEnabled()
                } else {
                    callbacks?.failureEnablingWifi()
                }
            }
        }
    }

    override fun isWifiEnabled(): Boolean {
        return delegate.isWifiEnabled()
    }
}
