/*
 * Copyright 2021 Patches Klinefelter
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
package com.isupatches.android.wisefy.wifi.impl

import android.net.wifi.WifiManager
import com.isupatches.android.wisefy.constants.DeprecationMessages
import com.isupatches.android.wisefy.util.fail

internal interface Android29WifiApi {

    fun disableWifi(): Boolean

    fun enableWifi(): Boolean

    fun isWifiEnabled(): Boolean
}

internal class Android29WifiApiImpl(
    private val wifiManager: WifiManager
) : Android29WifiApi {

    override fun disableWifi(): Boolean {
        fail(DeprecationMessages.DISABLE_WIFI)
        return false
    }

    override fun enableWifi(): Boolean {
        fail(DeprecationMessages.ENABLE_WIFI)
        return false
    }

    override fun isWifiEnabled(): Boolean {
        return wifiManager.isWifiEnabled
    }
}
