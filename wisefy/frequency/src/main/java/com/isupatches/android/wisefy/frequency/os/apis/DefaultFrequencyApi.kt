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

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.net.wifi.WifiInfo
import androidx.annotation.RequiresPermission

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
     * @return Int or null - The frequency of the current network if there is one, otherwise null
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_NETWORK_STATE])
    fun getFrequency(): Int?

    /**
     * A default API to get the frequency of a given network from the Android OS.
     *
     * @param network The network to get the frequency of
     *
     * @return Int - The frequency of the given network
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    fun getFrequency(network: WifiInfo): Int

    /**
     * A default API to check if the frequency of the current network is 5G from the Android OS.
     *
     * @return Boolean - Whether the network is 5G or not.  True if 5G, otherwise false
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_NETWORK_STATE])
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
