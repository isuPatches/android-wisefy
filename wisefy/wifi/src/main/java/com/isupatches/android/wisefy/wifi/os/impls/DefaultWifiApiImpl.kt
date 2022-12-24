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
package com.isupatches.android.wisefy.wifi.os.impls

import android.Manifest.permission.ACCESS_WIFI_STATE
import android.Manifest.permission.CHANGE_WIFI_STATE
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.core.logging.WisefyLogger
import com.isupatches.android.wisefy.wifi.os.apis.DefaultWifiApi

/**
 * A default internal implementation for enabling, disabling, and checking the state of Wifi through the Android OS.
 *
 * @param wifiManager The WifiManager instance to use
 *
 * @see DefaultWifiApi
 *
 * @author Patches Barrett
 * @since 07/2022, version 5.0.0
 */
internal class DefaultWifiApiImpl(
    private val wifiManager: WifiManager,
    private val logger: WisefyLogger
) : DefaultWifiApi {

    companion object {
        private const val LOG_TAG = "DefaultWifiApiImpl"
    }

    @RequiresPermission(CHANGE_WIFI_STATE)
    override fun disableWifi(): Boolean {
        val result = wifiManager.setWifiEnabled(false)
        logger.d(LOG_TAG, "Result from disableWifi: $result")
        return result
    }

    @RequiresPermission(CHANGE_WIFI_STATE)
    override fun enableWifi(): Boolean {
        val result = wifiManager.setWifiEnabled(true)
        logger.d(LOG_TAG, "Result from enableWifi: $result")
        return result
    }

    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun isWifiEnabled(): Boolean {
        val isWifiEnabled = wifiManager.isWifiEnabled
        logger.d(LOG_TAG, "Result from isWifiEnabled: $isWifiEnabled")
        return isWifiEnabled
    }
}
