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
package com.isupatches.android.wisefy.frequency

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.frequency.delegates.LegacyFrequencyDelegate

const val MIN_FREQUENCY_5GHZ: Int = 4900
const val MAX_FREQUENCY_5GHZ: Int = 5900

internal interface FrequencyUtil : FrequencyApi

internal class WisefyFrequencyUtil(
    wifiManager: WifiManager
) : FrequencyUtil {

    private val delegate = LegacyFrequencyDelegate(wifiManager)

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getFrequency(): Int? {
        return delegate.getFrequency()
    }

    override fun getFrequency(network: WifiInfo): Int {
        return delegate.getFrequency(network)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun isNetwork5gHz(): Boolean {
        return delegate.isNetwork5gHz()
    }

    override fun isNetwork5gHz(network: WifiInfo): Boolean {
        return delegate.isNetwork5gHz(network)
    }
}
