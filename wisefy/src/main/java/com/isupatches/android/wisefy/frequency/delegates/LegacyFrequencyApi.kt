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
package com.isupatches.android.wisefy.frequency.delegates

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.frequency.MAX_FREQUENCY_5GHZ
import com.isupatches.android.wisefy.frequency.MIN_FREQUENCY_5GHZ

internal interface LegacyFrequencyApi {
    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun getFrequency(): Int?

    fun getFrequency(network: WifiInfo): Int

    @RequiresPermission(ACCESS_FINE_LOCATION)
    fun isNetwork5gHz(): Boolean

    fun isNetwork5gHz(network: WifiInfo): Boolean
}

internal class LegacyFrequencyApiImpl(
    private val wifiManager: WifiManager
) : LegacyFrequencyApi {

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getFrequency(): Int? {
        val currentNetwork = wifiManager.connectionInfo
        return currentNetwork?.frequency
    }

    override fun getFrequency(network: WifiInfo): Int {
        return network.frequency
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun isNetwork5gHz(): Boolean {
        val frequency = getFrequency()
        return frequency != null && frequency > MIN_FREQUENCY_5GHZ && frequency < MAX_FREQUENCY_5GHZ
    }

    override fun isNetwork5gHz(network: WifiInfo): Boolean {
        val frequency = getFrequency(network)
        return frequency in (MIN_FREQUENCY_5GHZ + 1) until MAX_FREQUENCY_5GHZ
    }
}
