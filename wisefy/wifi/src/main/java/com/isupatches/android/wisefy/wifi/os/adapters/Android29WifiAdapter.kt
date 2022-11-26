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

import android.net.wifi.WifiManager
import android.os.Build
import androidx.annotation.RequiresApi
import com.isupatches.android.wisefy.core.assertions.WisefyAssertions
import com.isupatches.android.wisefy.core.constants.AssertionMessages
import com.isupatches.android.wisefy.wifi.WifiApi
import com.isupatches.android.wisefy.wifi.entities.DisableWifiRequest
import com.isupatches.android.wisefy.wifi.entities.DisableWifiResult
import com.isupatches.android.wisefy.wifi.entities.EnableWifiRequest
import com.isupatches.android.wisefy.wifi.entities.EnableWifiResult
import com.isupatches.android.wisefy.wifi.entities.IsWifiEnabledQuery
import com.isupatches.android.wisefy.wifi.entities.IsWifiEnabledResult
import com.isupatches.android.wisefy.wifi.os.apis.Android29WifiApi
import com.isupatches.android.wisefy.wifi.os.apis.DefaultWifiApi
import com.isupatches.android.wisefy.wifi.os.impls.Android29WifiApiImpl
import com.isupatches.android.wisefy.wifi.os.impls.DefaultWifiApiImpl

/**
 * An Android 29 specific adapter for enabling, disabling, and checking the state of Wifi.
 *
 * @param assertions The [WisefyAssertions] instance to use
 * @param api The OS level API instance to use
 *
 * @see DefaultWifiApi
 * @see DefaultWifiApiImpl
 * @see WifiApi
 * @see WisefyAssertions
 *
 * @author Patches Klinefelter
 * @since 07/2022, version 5.0.0
 */
@RequiresApi(Build.VERSION_CODES.Q)
internal class Android29WifiAdapter(
    wifiManager: WifiManager,
    private val assertions: WisefyAssertions,
    private val api: Android29WifiApi = Android29WifiApiImpl(wifiManager)
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

    override fun isWifiEnabled(query: IsWifiEnabledQuery): IsWifiEnabledResult {
        val result = api.isWifiEnabled()
        return if (result) {
            IsWifiEnabledResult.True
        } else {
            IsWifiEnabledResult.False
        }
    }
}
