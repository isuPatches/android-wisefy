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
package com.isupatches.android.wisefy.wifi

import android.net.wifi.WifiManager
import com.isupatches.android.wisefy.constants.DeprecationMessages
import com.isupatches.android.wisefy.wifi.delegates.LegacyWifiDelegate

internal interface WifiUtil : WifiApi

internal class WisefyWifiUtil(
    wifiManager: WifiManager
) : WifiUtil {

    private val delegate = LegacyWifiDelegate(wifiManager)

    @Deprecated(DeprecationMessages.DISABLE_WIFI)
    override fun disableWifi(): Boolean {
        return delegate.disableWifi()
    }

    @Deprecated(DeprecationMessages.ENABLE_WIFI)
    override fun enableWifi(): Boolean {
        return delegate.enableWifi()
    }

    override fun isWifiEnabled(): Boolean {
        return delegate.isWifiEnabled()
    }
}
