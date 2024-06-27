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
package com.isupatches.android.wisefy.wifi.os.apis

import android.Manifest.permission.ACCESS_WIFI_STATE
import android.Manifest.permission.CHANGE_WIFI_STATE
import androidx.annotation.RequiresPermission

/**
 * A default set of APIs for enabling, disabling, and checking the state of wifi through the Android OS.
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
internal interface DefaultWifiApi {

    /**
     * A default API to disable Wifi through the Android OS.
     *
     * @return Boolean - The result of disabling Wifi. True if successful, otherwise false.
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    @RequiresPermission(CHANGE_WIFI_STATE)
    fun disableWifi(): Boolean

    /**
     * A default API to enable Wifi through the Android OS.
     *
     * @return Boolean - The result of enabling Wifi. True if successful, otherwise false.
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    @RequiresPermission(CHANGE_WIFI_STATE)
    fun enableWifi(): Boolean

    /**
     * A default API to check if Wifi is enabled through the Android OS.
     *
     * @return Boolean - Whether Wifi is enabled on the device. True if enabled, otherwise false.
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    @RequiresPermission(ACCESS_WIFI_STATE)
    fun isWifiEnabled(): Boolean
}
