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
package com.isupatches.android.wisefy.wifi.os.adapters

import android.Manifest.permission.ACCESS_WIFI_STATE
import android.Manifest.permission.CHANGE_WIFI_STATE
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.core.assertions.WisefyAssertions
import com.isupatches.android.wisefy.core.constants.AssertionMessages
import com.isupatches.android.wisefy.core.logging.WisefyLogger
import com.isupatches.android.wisefy.wifi.WifiApi
import com.isupatches.android.wisefy.wifi.entities.DisableWifiRequest
import com.isupatches.android.wisefy.wifi.entities.DisableWifiResult
import com.isupatches.android.wisefy.wifi.entities.EnableWifiRequest
import com.isupatches.android.wisefy.wifi.entities.EnableWifiResult
import com.isupatches.android.wisefy.wifi.entities.IsWifiEnabledQuery
import com.isupatches.android.wisefy.wifi.entities.IsWifiEnabledResult
import com.isupatches.android.wisefy.wifi.os.apis.DefaultWifiApi
import com.isupatches.android.wisefy.wifi.os.impls.DefaultWifiApiImpl

/**
 * A default adapter for enabling, disabling, and checking the state of Wifi.
 *
 * @param wifiManager The WifiManager instance to use
 * @param api The OS level API instance to use
 *
 * @see DefaultWifiApi
 * @see DefaultWifiApiImpl
 * @see WifiApi
 *
 * @author Patches Klinefelter
 * @since 07/2022, version 5.0.0
 */
internal class DefaultWifiAdapter(
    wifiManager: WifiManager,
    logger: WisefyLogger,
    private val assertions: WisefyAssertions,
    private val api: DefaultWifiApi = DefaultWifiApiImpl(wifiManager, logger)
) : WifiApi {

    @RequiresPermission(CHANGE_WIFI_STATE)
    override fun disableWifi(request: DisableWifiRequest): DisableWifiResult {
        return when (request) {
            is DisableWifiRequest.Default -> {
                val result = api.disableWifi()
                if (result) {
                    DisableWifiResult.Success.Disabled
                } else {
                    DisableWifiResult.Failure.UnableToDisable
                }
            }
            else -> {
                val message = AssertionMessages.Wifi.ANDROID_29_REQUEST_USED_ON_PRE_ANDROID_29
                assertions.fail(message = message)
                DisableWifiResult.Failure.Assertion(message = message)
            }
        }
    }

    @RequiresPermission(CHANGE_WIFI_STATE)
    override fun enableWifi(request: EnableWifiRequest): EnableWifiResult {
        return when (request) {
            is EnableWifiRequest.Default -> {
                val result = api.enableWifi()
                if (result) {
                    EnableWifiResult.Success.Enabled
                } else {
                    EnableWifiResult.Failure.UnableToEnable
                }
            }
            else -> {
                val message = AssertionMessages.Wifi.ANDROID_29_REQUEST_USED_ON_PRE_ANDROID_29
                assertions.fail(message = message)
                EnableWifiResult.Failure.Assertion(message = message)
            }
        }
    }

    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun isWifiEnabled(query: IsWifiEnabledQuery): IsWifiEnabledResult {
        val result = api.isWifiEnabled()
        return if (result) {
            IsWifiEnabledResult.True
        } else {
            IsWifiEnabledResult.False
        }
    }
}
