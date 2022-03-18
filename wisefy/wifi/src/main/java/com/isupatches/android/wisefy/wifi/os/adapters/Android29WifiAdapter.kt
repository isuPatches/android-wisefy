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
import com.isupatches.android.wisefy.shared.assertions.fail
import com.isupatches.android.wisefy.shared.entities.DeprecationMessages
import com.isupatches.android.wisefy.wifi.WifiApi
import com.isupatches.android.wisefy.wifi.entities.DisableWifiRequest
import com.isupatches.android.wisefy.wifi.entities.DisableWifiResult
import com.isupatches.android.wisefy.wifi.entities.EnableWifiRequest
import com.isupatches.android.wisefy.wifi.entities.EnableWifiResult
import com.isupatches.android.wisefy.wifi.entities.IsWifiEnabledRequest
import com.isupatches.android.wisefy.wifi.entities.IsWifiEnabledResult
import com.isupatches.android.wisefy.wifi.os.apis.DefaultWifiApi
import com.isupatches.android.wisefy.wifi.os.impls.DefaultWifiApiImpl

@RequiresApi(Build.VERSION_CODES.Q)
internal class Android29WifiAdapter(
    wifiManager: WifiManager,
    private val api: DefaultWifiApi = DefaultWifiApiImpl(wifiManager)
) : WifiApi {

    @Deprecated(DeprecationMessages.Wifi.DISABLE)
    override fun disableWifi(request: DisableWifiRequest): DisableWifiResult {
        fail(DeprecationMessages.Wifi.DISABLE)
        return DisableWifiResult.False
    }

    @Deprecated(DeprecationMessages.Wifi.ENABLE)
    override fun enableWifi(request: EnableWifiRequest): EnableWifiResult {
        fail(DeprecationMessages.Wifi.ENABLE)
        return EnableWifiResult.False
    }

    override fun isWifiEnabled(request: IsWifiEnabledRequest): IsWifiEnabledResult {
        val result = api.isWifiEnabled()
        return if (result) {
            IsWifiEnabledResult.True
        } else {
            IsWifiEnabledResult.False
        }
    }
}
