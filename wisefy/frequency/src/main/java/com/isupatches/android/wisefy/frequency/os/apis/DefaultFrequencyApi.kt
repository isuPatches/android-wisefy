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
package com.isupatches.android.wisefy.frequency.os.apis

import android.Manifest
import android.net.wifi.WifiInfo
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.frequency.entities.FrequencyData

/**
 * A default internal API for getting the frequency of a network through the Android OS.
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
internal interface DefaultFrequencyApi {

    /**
     * A default API to get the frequency of the current network from the Android OS.
     *
     * @see FrequencyData
     *
     * @return FrequencyData - The frequency data for the current network if there is one, otherwise null
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun getFrequency(): FrequencyData?

    /**
     * A default API to get the frequency of a given network from the Android OS.
     *
     * @param network The network to get the frequency of
     *
     * @see FrequencyData
     *
     * @return FrequencyData - The frequency data for the given network
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    fun getFrequency(network: WifiInfo): FrequencyData

    /**
     * A default API to check if the frequency of the current network is 5G from the Android OS.
     *
     * @return Boolean - Whether the network is 5G or not.  True if 5G, otherwise false
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    fun isNetwork5gHz(): Boolean

    /**
     * A default API to check if the frequency of a given network is 5G from the Android OS.
     *
     * @param network The network to determine if it's frequency is 5G
     *
     * @return Boolean - Whether the network is 5G or not. True if 5G, otherwise false
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    fun isNetwork5gHz(network: WifiInfo): Boolean
}
