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
package com.isupatches.android.wisefy.frequency.proxies

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.net.ConnectivityManager
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.frequency.FrequencyApi
import com.isupatches.android.wisefy.frequency.impl.DefaultFrequencyApi
import com.isupatches.android.wisefy.frequency.impl.DefaultFrequencyApiImpl
import com.isupatches.android.wisefy.frequency.entities.FrequencyData

/**
 * A default proxy for getting the frequency of a network.
 *
 * @see FrequencyApi
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
internal class DefaultFrequencyProxy(
    wifiManager: WifiManager,
    connectivityManager: ConnectivityManager,
    private val impl: DefaultFrequencyApi = DefaultFrequencyApiImpl(
        wifiManager = wifiManager,
        connectivityManager = connectivityManager
    )
) : FrequencyApi {

    @RequiresPermission(ACCESS_FINE_LOCATION)
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun getFrequency(): FrequencyData? {
        return impl.getFrequency()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun getFrequency(network: WifiInfo): FrequencyData {
        return impl.getFrequency(network)
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun isNetwork5gHz(): Boolean {
        return impl.isNetwork5gHz()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun isNetwork5gHz(network: WifiInfo): Boolean {
        return impl.isNetwork5gHz(network)
    }
}
