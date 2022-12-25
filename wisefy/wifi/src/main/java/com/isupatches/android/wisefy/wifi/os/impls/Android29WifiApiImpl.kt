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
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.provider.Settings
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.core.logging.WisefyLogger
import com.isupatches.android.wisefy.wifi.os.apis.Android29WifiApi

/**
 * An Android 29 or higher implementation for enabling, disabling, and checking the state of wifi through the
 * Android OS.
 *
 * @property wifiManager The WifiManager instance to use
 * @property logger The [WisefyLogger] instance to use
 *
 * @see Android29WifiApi
 * @see WisefyLogger
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
internal class Android29WifiApiImpl(
    private val wifiManager: WifiManager,
    private val logger: WisefyLogger
) : Android29WifiApi {

    override fun openWifiSettings(context: Context) {
        logger.d(LOG_TAG, "Opening Wifi settings screen")
        context.startActivity(Intent(Settings.ACTION_WIFI_SETTINGS).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }

    @RequiresPermission(ACCESS_WIFI_STATE)
    override fun isWifiEnabled(): Boolean {
        val isWifiEnabled = wifiManager.isWifiEnabled
        logger.d(LOG_TAG, "Result from isWifiEnabled: $isWifiEnabled")
        return isWifiEnabled
    }

    companion object {
        private const val LOG_TAG = "Android29WifiApiImpl"
    }
}
