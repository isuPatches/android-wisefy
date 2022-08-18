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

/**
 * An Android 30 specific API for functions related to the signal strength of networks.
 *
 * @author Patches Klinefelter
 * @since 07/2022, version 5.0.0
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
     * @since 07/2022, version 5.0.0
     */
    fun calculateBars(rssiLevel: Int): Int

    /**
     * An Android 30 specific API to compare the RSSI levels of two networks.
     *
     * @param rssi1 The RSSI level of the first network
     * @param rssi2 The RSSI level of the second network
     *
     * *Notes*
     * See https://developer.android.com/reference/android/net/wifi/WifiManager#compareSignalLevel(int,%20int)
     *
     * @return Int - The result of the comparison.  This will be less than 0 if first signal is weaker, 0 if the two
     * have the same strength, and greater than zero if the second signal is stronger
     *
     * @author Patches Klinefelter
     * @since 07/2022, version 5.0.0
     */
    fun compareSignalLevel(rssi1: Int, rssi2: Int): Int
}
