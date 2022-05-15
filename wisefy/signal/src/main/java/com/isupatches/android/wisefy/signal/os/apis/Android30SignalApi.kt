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
package com.isupatches.android.wisefy.signal.os.apis

import android.os.Build
import androidx.annotation.RequiresApi
import com.isupatches.android.wisefy.core.entities.DeprecationMessages

/**
 * An Android 30 specific API for functions related to the signal strength of networks.
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
@RequiresApi(Build.VERSION_CODES.R)
internal interface Android30SignalApi {

    /**
     * An Android 30 specific API to calculate the bars of signal strength given the network's RSSI.
     *
     * @param rssiLevel The RSSI level of the network
     *
     * @return Int - The number of signal strength bars for the network given RSSI level.
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    fun calculateBars(rssiLevel: Int): Int

    /**
     * An Android 30 specific API to calculate the bars of signal strength given the network's RSSI.
     *
     * @param rssiLevel The RSSI level of the network
     * @param targetNumberOfBars The desired number of signal strength bars
     *
     * @return Int - The number of signal strength bars for the network given RSSI level and a desired number of bars.
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @Deprecated(DeprecationMessages.Signal.CALCULATE_BARS)
    fun calculateBars(rssiLevel: Int, targetNumberOfBars: Int): Int

    /**
     * An Android 30 specific API to compare the RSSI levels of two networks.
     *
     * @param rssi1 The RSSI level of the first network
     * @param rssi2 The RSSI level of the second network
     *
     * *Notes*
     * See https://developer.android.com/reference/android/net/wifi/WifiManager#compareSignalLevel(int,%20int)
     *
     * @return Int - The result of the comparison - less than 0 if first signal is weaker, 0 if the same, and greater
     * zero if the second signal is stronger
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    fun compareSignalLevel(rssi1: Int, rssi2: Int): Int
}
