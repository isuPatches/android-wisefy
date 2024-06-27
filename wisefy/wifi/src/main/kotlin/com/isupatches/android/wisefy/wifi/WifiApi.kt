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
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.wifi.entities.DisableWifiRequest
import com.isupatches.android.wisefy.wifi.entities.DisableWifiResult
import com.isupatches.android.wisefy.wifi.entities.EnableWifiRequest
import com.isupatches.android.wisefy.wifi.entities.EnableWifiResult
import com.isupatches.android.wisefy.wifi.entities.IsWifiEnabledQuery
import com.isupatches.android.wisefy.wifi.entities.IsWifiEnabledResult

/**
 * A set of synchronous APIs for enabling, disabling, and checking the state of wifi.
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
interface WifiApi {

    /**
     * A synchronous API to disable wifi.
     *
     * @param request The details of the request to disable wifi
     *
     * *NOTES*
     *  - Will open the Wifi settings screen on Android Q / SDK 29 or higher
     *
     * @see DisableWifiRequest
     * @see DisableWifiResult
     *
     * @return DisableWifiResult - The result of disabling wifi
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    @RequiresPermission(CHANGE_WIFI_STATE)
    fun disableWifi(request: DisableWifiRequest): DisableWifiResult

    /**
     * A synchronous API to enable wifi.
     *
     * *NOTES*
     *  - Will open the Wifi settings screen on Android Q / SDK 29 or higher
     *
     * @param request The details of the request to enable wifi
     *
     * @see EnableWifiRequest
     * @see EnableWifiResult
     *
     * @return EnableWifiResult - The result of enabling wifi
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    @RequiresPermission(CHANGE_WIFI_STATE)
    fun enableWifi(request: EnableWifiRequest): EnableWifiResult

    /**
     * A synchronous API to check if wifi is enabled.
     *
     * @param query The details of the query to check if wifi is enabled
     *
     * @see IsWifiEnabledQuery
     * @see IsWifiEnabledResult
     *
     * @return IsWifiEnabledResult - The result of checking if wifi is enabled
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    @RequiresPermission(ACCESS_WIFI_STATE)
    fun isWifiEnabled(query: IsWifiEnabledQuery = IsWifiEnabledQuery()): IsWifiEnabledResult
}
