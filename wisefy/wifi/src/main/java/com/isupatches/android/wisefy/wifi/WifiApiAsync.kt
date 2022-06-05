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
import com.isupatches.android.wisefy.wifi.callbacks.DisableWifiCallbacks
import com.isupatches.android.wisefy.wifi.callbacks.EnableWifiCallbacks
import com.isupatches.android.wisefy.wifi.entities.DisableWifiRequest
import com.isupatches.android.wisefy.wifi.entities.EnableWifiRequest

/**
 * A set of asynchronous APIs for enabling and disabling Wifi.
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
interface WifiApiAsync {

    /**
     * An asynchronous API to disable Wifi.
     *
     * @param request The details of the request to disable Wifi
     * @param callbacks The callbacks for results while disabling Wifi
     *
     * @see DisableWifiRequest
     * @see DisableWifiCallbacks
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @Deprecated(DeprecationMessages.Wifi.DISABLE)
    fun disableWifi(request: DisableWifiRequest = DisableWifiRequest(), callbacks: DisableWifiCallbacks?)

    /**
     * An asynchronous API to enable Wifi.
     *
     * @param request The details of the request to enable Wifi
     * @param callbacks The callbacks for results while enabling Wifi
     *
     * @see EnableWifiRequest
     * @see EnableWifiCallbacks
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @Deprecated(DeprecationMessages.Wifi.ENABLE)
    fun enableWifi(request: EnableWifiRequest = EnableWifiRequest(), callbacks: EnableWifiCallbacks?)
}
