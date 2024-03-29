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
package com.isupatches.android.wisefy.wifi.os.adapters

import android.Manifest.permission.ACCESS_WIFI_STATE
import android.net.wifi.WifiManager
import android.os.Build
import androidx.annotation.RequiresApi
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
import com.isupatches.android.wisefy.wifi.os.apis.Android29WifiApi
import com.isupatches.android.wisefy.wifi.os.impls.Android29WifiApiImpl

/**
 * An Android 29 or higher adapter for enabling, disabling, and checking the state of wifi.
 *
 * @param wifiManager The WifiManager instance to use
 * @param logger The [WisefyLogger] instance to use
 * @property assertions The [WisefyAssertions] instance to use
 * @property api The OS level API instance to use
 *
 * @see Android29WifiApi
 * @see Android29WifiApiImpl
 * @see WifiApi
 * @see WisefyAssertions
 * @see WisefyLogger
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
@RequiresApi(Build.VERSION_CODES.Q)
internal class Android29WifiAdapter(
    wifiManager: WifiManager,
    logger: WisefyLogger,
    private val assertions: WisefyAssertions,
    private val api: Android29WifiApi = Android29WifiApiImpl(wifiManager, logger)
) : WifiApi {

    override fun disableWifi(request: DisableWifiRequest): DisableWifiResult {
        return when (request) {
            is DisableWifiRequest.Android29OrAbove -> {
                api.openWifiSettings(request.context)
                return DisableWifiResult.Success.WifiSettingScreenOpened
            }
            is DisableWifiRequest.Default -> {
                val message = AssertionMessages.Wifi.DEFAULT_REQUEST_USED_ANDROID_29_OR_HIGHER
                assertions.fail(message = message)
                DisableWifiResult.Failure.Assertion(message = message)
            }
        }
    }

    override fun enableWifi(request: EnableWifiRequest): EnableWifiResult {
        return when (request) {
            is EnableWifiRequest.Android29OrAbove -> {
                api.openWifiSettings(request.context)
                return EnableWifiResult.Success.WifiSettingScreenOpened
            }
            is EnableWifiRequest.Default -> {
                val message = AssertionMessages.Wifi.DEFAULT_REQUEST_USED_ANDROID_29_OR_HIGHER
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
