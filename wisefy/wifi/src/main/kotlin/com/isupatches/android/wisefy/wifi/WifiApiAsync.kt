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
import com.isupatches.android.wisefy.wifi.callbacks.DisableWifiCallbacks
import com.isupatches.android.wisefy.wifi.callbacks.EnableWifiCallbacks
import com.isupatches.android.wisefy.wifi.callbacks.IsWifiEnabledCallbacks
import com.isupatches.android.wisefy.wifi.entities.DisableWifiRequest
import com.isupatches.android.wisefy.wifi.entities.EnableWifiRequest
import com.isupatches.android.wisefy.wifi.entities.IsWifiEnabledQuery

/**
 * A set of asynchronous APIs for enabling and disabling wifi.
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
interface WifiApiAsync {

    /**
     * An asynchronous API to disable wifi.
     *
     * *NOTES*
     *  - Locked by the wifiMutex along with functions for enabling wifi and checking if wifi is enabled
     *  - Will open the Wifi settings screen on Android Q / SDK 29 or higher
     *
     * @param request The details of the request to disable wifi
     * @param callbacks The callbacks for results when disabling wifi
     *
     * @see DisableWifiRequest
     * @see DisableWifiCallbacks
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    @RequiresPermission(CHANGE_WIFI_STATE)
    fun disableWifi(request: DisableWifiRequest, callbacks: DisableWifiCallbacks?)

    /**
     * An asynchronous API to enable Wifi.
     *
     * *NOTES*
     *  - Locked by the wifiMutex along with functions for disabling wifi and checking if wifi is enabled
     *  - Will open the wifi settings screen on Android Q / SDK 29 or higher
     *
     * @param request The details of the request to enable wifi
     * @param callbacks The callbacks for results when enabling wifi
     *
     * @see EnableWifiRequest
     * @see EnableWifiCallbacks
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    @RequiresPermission(CHANGE_WIFI_STATE)
    fun enableWifi(request: EnableWifiRequest, callbacks: EnableWifiCallbacks?)

    /**
     * An asynchronous API to check the current state of wifi (f.e. enabled or disabled).
     *
     * *NOTES*
     *  - Locked by the wifiMutex along with functions for enabling and disabling wifi
     *
     * @param query The details of the query to check the current state of wifi
     * @param callbacks The callbacks for results when checking the current state of wifi
     *
     * @see IsWifiEnabledQuery
     * @see IsWifiEnabledCallbacks
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    @RequiresPermission(ACCESS_WIFI_STATE)
    fun isWifiEnabled(query: IsWifiEnabledQuery = IsWifiEnabledQuery(), callbacks: IsWifiEnabledCallbacks?)
}
