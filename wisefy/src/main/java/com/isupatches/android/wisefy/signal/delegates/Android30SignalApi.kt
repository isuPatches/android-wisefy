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
package com.isupatches.android.wisefy.signal.delegates

import android.net.wifi.WifiManager
import android.os.Build
import androidx.annotation.RequiresApi
import com.isupatches.android.wisefy.constants.DeprecationMessages
import com.isupatches.android.wisefy.constants.ErrorMessages

internal interface Android30SignalApi {
    @RequiresApi(Build.VERSION_CODES.R)
    fun calculateBars(rssiLevel: Int): Int

    @Deprecated(DeprecationMessages.CALCULATE_BARS)
    fun calculateBars(rssiLevel: Int, targetNumberOfBars: Int): Int

    fun compareSignalLevel(rssi1: Int, rssi2: Int): Int
}

internal class Android30SignalApiImpl(
    private val wifiManager: WifiManager
) : Android30SignalApi {

    @RequiresApi(Build.VERSION_CODES.R)
    override fun calculateBars(rssiLevel: Int): Int {
        return wifiManager.calculateSignalLevel(rssiLevel)
    }

    @Deprecated(
        message = DeprecationMessages.CALCULATE_BARS,
        replaceWith = ReplaceWith("this.calculateBars(rssiLevel)")
    )
    override fun calculateBars(rssiLevel: Int, targetNumberOfBars: Int): Int {
        error(ErrorMessages.CALCULATE_BARS_ANDROID_30)
    }

    override fun compareSignalLevel(rssi1: Int, rssi2: Int): Int {
        return WifiManager.compareSignalLevel(rssi1, rssi2)
    }
}
