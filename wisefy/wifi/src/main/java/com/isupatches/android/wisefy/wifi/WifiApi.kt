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

import com.isupatches.android.wisefy.core.entities.DeprecationMessages
import com.isupatches.android.wisefy.wifi.entities.DisableWifiRequest
import com.isupatches.android.wisefy.wifi.entities.DisableWifiResult
import com.isupatches.android.wisefy.wifi.entities.EnableWifiRequest
import com.isupatches.android.wisefy.wifi.entities.EnableWifiResult
import com.isupatches.android.wisefy.wifi.entities.IsWifiEnabledRequest
import com.isupatches.android.wisefy.wifi.entities.IsWifiEnabledResult

interface WifiApi {

    @Deprecated(DeprecationMessages.Wifi.DISABLE)
    fun disableWifi(request: DisableWifiRequest = DisableWifiRequest()): DisableWifiResult

    @Deprecated(DeprecationMessages.Wifi.ENABLE)
    fun enableWifi(request: EnableWifiRequest = EnableWifiRequest()): EnableWifiResult

    fun isWifiEnabled(request: IsWifiEnabledRequest = IsWifiEnabledRequest()): IsWifiEnabledResult
}