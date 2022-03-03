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
package com.isupatches.android.wisefy.frequency

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.net.wifi.WifiInfo
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.frequency.callbacks.GetFrequencyCallbacks
import com.isupatches.android.wisefy.frequency.entities.FrequencyData

/**
 * A constant for the minimum frequency for a 5G network.
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
const val MIN_FREQUENCY_5GHZ: Int = 4900

/**
 * A constant for the maximum frequency for a 5G network.
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
const val MAX_FREQUENCY_5GHZ: Int = 5900

/**
 * A set of synchronous APIs for getting the frequency of a network.
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
interface FrequencyApi {

    /**
     * An synchronous API to get the frequency of the current network.
     *
     * @see FrequencyData
     *
     * @return FrequencyData - The frequency data for the current network if there is one, otherwise null
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun getFrequency(): FrequencyData?

    /**
     * An synchronous API to get the frequency of a given network.
     *
     * @param network The network to get the frequency of
     *
     * @see FrequencyData
     *
     * @return FrequencyData - The frequency data of the given network
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun getFrequency(network: WifiInfo): FrequencyData

    /**
     * An synchronous API to check if the frequency of the current network is 5G.
     *
     * @return Boolean - Whether the current network is 5G or not. True if 5G, otherwise false
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun isNetwork5gHz(): Boolean

    /**
     * An synchronous API to check if the frequency of a given network is 5G.
     *
     * @param network The network to determine if it's frequency is 5G
     *
     * @return Boolean - Whether the given network is 5G or not. True if 5G, otherwise false
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun isNetwork5gHz(network: WifiInfo): Boolean
}

/**
 * A set of asynchronous APIs for getting the frequency of a network.
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
interface FrequencyApiAsync {

    /**
     * An asynchronous API to get the frequency of the current network.
     *
     * @param callbacks The callbacks for when the frequency is returned
     *
     * @see GetFrequencyCallbacks
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(ACCESS_FINE_LOCATION)
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun getFrequency(callbacks: GetFrequencyCallbacks?)
}
