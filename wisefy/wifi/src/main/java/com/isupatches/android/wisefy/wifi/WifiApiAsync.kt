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

import com.isupatches.android.wisefy.core.constants.DeprecationMessages
import com.isupatches.android.wisefy.wifi.callbacks.DisableWifiCallbacks
import com.isupatches.android.wisefy.wifi.callbacks.EnableWifiCallbacks
import com.isupatches.android.wisefy.wifi.callbacks.IsWifiEnabledCallbacks
import com.isupatches.android.wisefy.wifi.entities.DisableWifiRequest
import com.isupatches.android.wisefy.wifi.entities.EnableWifiRequest
import com.isupatches.android.wisefy.wifi.entities.IsWifiEnabledQuery

/**
 * A set of asynchronous APIs for enabling and disabling Wifi.
 *
 * @author Patches Klinefelter
 * @since 07/2022, version 5.0.0
 */
interface WifiApiAsync {

    /**
     * An asynchronous API to disable Wifi.
     *
     * *NOTES*
     *  - Internally locked by a mutex along with the async APIs to enable Wifi and check the device's current Wifi
     *    state
     *
     * @param request The details of the request to disable Wifi
     * @param callbacks The callbacks for results when disabling Wifi
     *
     * @see DisableWifiRequest
     * @see DisableWifiCallbacks
     *
     * @author Patches Klinefelter
     * @since 07/2022, version 5.0.0
     */
    @Deprecated(DeprecationMessages.Wifi.DISABLE)
    fun disableWifi(request: DisableWifiRequest = DisableWifiRequest(), callbacks: DisableWifiCallbacks?)

    /**
     * An asynchronous API to enable Wifi.
     *
     * *NOTES*
     *  - Internally locked by a mutex along with the async APIs to disable Wifi and check the device's current Wifi
     *    state
     *
     * @param request The details of the request to enable Wifi
     * @param callbacks The callbacks for results when enabling Wifi
     *
     * @see EnableWifiRequest
     * @see EnableWifiCallbacks
     *
     * @author Patches Klinefelter
     * @since 07/2022, version 5.0.0
     */
    @Deprecated(DeprecationMessages.Wifi.ENABLE)
    fun enableWifi(request: EnableWifiRequest = EnableWifiRequest(), callbacks: EnableWifiCallbacks?)

    /**
     * An asynchronous API to check the current state of Wifi (f.e. enabled or disabled).
     *
     * *NOTES*
     *  - Internally locked by a mutex along with the async APIs to enable and disable Wifi
     *
     * @param query The details of the query to check the current state of Wifi
     * @param callbacks The callbacks for results when checking the current state of Wifi
     *
     * @see IsWifiEnabledQuery
     * @see IsWifiEnabledCallbacks
     *
     * @author Patches Klinefelter
     * @since 07/2022, version 5.0.0
     */
    fun isWifiEnabled(query: IsWifiEnabledQuery = IsWifiEnabledQuery(), callbacks: IsWifiEnabledCallbacks?)
}
