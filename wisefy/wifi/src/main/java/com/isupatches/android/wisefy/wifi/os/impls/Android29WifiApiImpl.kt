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
package com.isupatches.android.wisefy.wifi.os.impls

import android.net.wifi.WifiManager
import android.os.Build
import androidx.annotation.RequiresApi
import com.isupatches.android.wisefy.shared.assertions.fail
import com.isupatches.android.wisefy.shared.entities.DeprecationMessages
import com.isupatches.android.wisefy.wifi.os.apis.Android29WifiApi

@RequiresApi(Build.VERSION_CODES.Q)
internal class Android29WifiApiImpl(
    private val wifiManager: WifiManager
) : Android29WifiApi {

    override fun disableWifi(): Boolean {
        fail(DeprecationMessages.Wifi.DISABLE)
        return false
    }

    override fun enableWifi(): Boolean {
        fail(DeprecationMessages.Wifi.ENABLE)
        return false
    }

    override fun isWifiEnabled(): Boolean {
        return wifiManager.isWifiEnabled
    }
}
