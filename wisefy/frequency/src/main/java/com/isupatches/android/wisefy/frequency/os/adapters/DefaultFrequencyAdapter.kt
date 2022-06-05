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
package com.isupatches.android.wisefy.frequency.os.adapters

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.frequency.FrequencyApi
import com.isupatches.android.wisefy.frequency.entities.FrequencyData
import com.isupatches.android.wisefy.frequency.entities.GetFrequencyRequest
import com.isupatches.android.wisefy.frequency.entities.GetFrequencyResult
import com.isupatches.android.wisefy.frequency.entities.IsNetwork5gHzRequest
import com.isupatches.android.wisefy.frequency.entities.IsNetwork5gHzResult
import com.isupatches.android.wisefy.frequency.os.apis.DefaultFrequencyApi
import com.isupatches.android.wisefy.frequency.os.impls.DefaultFrequencyApiImpl

/**
 * A default adapter for getting the frequency of a network.
 *
 * @param wifiManager The WifiManager instance to use
 * @param connectivityManager The ConnectivityManager instance to use
 * @param api The OS level API instance to use
 *
 * @see DefaultFrequencyApi
 * @see DefaultFrequencyApiImpl
 * @see FrequencyApi
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
internal class DefaultFrequencyAdapter(
    wifiManager: WifiManager,
    connectivityManager: ConnectivityManager,
    private val api: DefaultFrequencyApi = DefaultFrequencyApiImpl(
        wifiManager = wifiManager,
        connectivityManager = connectivityManager
    )
) : FrequencyApi {

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun getFrequency(request: GetFrequencyRequest): GetFrequencyResult {
        val frequency = when (request) {
            is GetFrequencyRequest.CurrentNetwork -> api.getFrequency()
            is GetFrequencyRequest.ForNetwork -> api.getFrequency(request.network)
        }
        return if (frequency != null) {
            GetFrequencyResult.WithFrequency(FrequencyData(frequency))
        } else {
            GetFrequencyResult.Empty
        }
    }

    @RequiresPermission(ACCESS_FINE_LOCATION)
    override fun isNetwork5gHz(request: IsNetwork5gHzRequest): IsNetwork5gHzResult {
        val isNetwork5gHz = when (request) {
            is IsNetwork5gHzRequest.CurrentNetwork -> api.isNetwork5gHz()
            is IsNetwork5gHzRequest.ForNetwork -> api.isNetwork5gHz(request.network)
        }
        return if (isNetwork5gHz) {
            IsNetwork5gHzResult.True
        } else {
            IsNetwork5gHzResult.False
        }
    }
}
