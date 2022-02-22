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
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.frequency.entities.FrequencyData

const val MIN_FREQUENCY_5GHZ: Int = 4900
const val MAX_FREQUENCY_5GHZ: Int = 5900

interface FrequencyApi {

    @RequiresPermission(ACCESS_FINE_LOCATION)
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun getFrequency(): FrequencyData?

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun getFrequency(network: WifiInfo): FrequencyData

    @RequiresPermission(ACCESS_FINE_LOCATION)
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun isNetwork5gHz(): Boolean

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun isNetwork5gHz(network: WifiInfo): Boolean
}

interface FrequencyApiAsync {
    @RequiresPermission(ACCESS_FINE_LOCATION)
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun getFrequency(callbacks: GetFrequencyCallbacks?)
}
