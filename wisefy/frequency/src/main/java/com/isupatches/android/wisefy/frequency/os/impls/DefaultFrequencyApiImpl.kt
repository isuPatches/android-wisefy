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
package com.isupatches.android.wisefy.frequency.os.impls

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.net.ConnectivityManager
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.os.Build
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.core.util.getNetwork
import com.isupatches.android.wisefy.frequency.MAX_FREQUENCY_5GHZ
import com.isupatches.android.wisefy.frequency.MIN_FREQUENCY_5GHZ
import com.isupatches.android.wisefy.frequency.entities.FrequencyData
import com.isupatches.android.wisefy.frequency.os.apis.DefaultFrequencyApi

/**
 * A default internal implementation for getting the frequency of a network through the Android OS.
 *
 * @param wifiManager The WifiManager instance to use
 * @param connectivityManager The ConnectivityManager instance to use
 *
 * @see DefaultFrequencyApi
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
internal class DefaultFrequencyApiImpl(
    private val wifiManager: WifiManager,
    private val connectivityManager: ConnectivityManager
) : DefaultFrequencyApi {

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getFrequency(): FrequencyData? {
        val currentNetwork = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            connectivityManager.getNetwork()
        } else {
            @Suppress("Deprecation")
            wifiManager.connectionInfo
        }
        return currentNetwork?.frequency?.let {
            FrequencyData(it)
        }
    }

    override fun getFrequency(network: WifiInfo): FrequencyData {
        return FrequencyData(network.frequency)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun isNetwork5gHz(): Boolean {
        val frequency = getFrequency()
        return frequency != null && frequency.value > MIN_FREQUENCY_5GHZ && frequency.value < MAX_FREQUENCY_5GHZ
    }

    override fun isNetwork5gHz(network: WifiInfo): Boolean {
        val frequency = getFrequency(network)
        return frequency.value in (MIN_FREQUENCY_5GHZ + 1) until MAX_FREQUENCY_5GHZ
    }
}
