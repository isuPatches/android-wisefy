/*
 * Copyright 2022 Patches Barrett
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
package com.isupatches.android.wisefy.networkinfo.os.apis

import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import androidx.annotation.RequiresPermission

/**
 * A default internal API for getting information about a network, the device's current network, and the device's IP
 * through the Android OS.
 *
 * @author Patches Barrett
 * @since 03/2022
 */
internal interface DefaultNetworkInfoApi {

    /**
     * An internal API that is used to get the device's current network from the Android OS.
     *
     * @return WifiInfo or null - The current network or null if the device is not connected to one
     *
     * @author Patches Barrett
     * @since 03/2022
     */
    @RequiresPermission(ACCESS_NETWORK_STATE)
    fun getCurrentNetwork(): Network?

    /**
     * An internal API that is used to get the network capabilities of a network from the Android OS.
     *
     * @param network The network to get the capabilities of
     *
     * @return NetworkCapabilities or null - The network capabilities from the the Android OS (can be null)
     *
     * @author Patches Barrett
     * @since 03/2022
     */
    @RequiresPermission(ACCESS_NETWORK_STATE)
    fun getNetworkCapabilities(network: Network): NetworkCapabilities?

    /**
     * An internal API that is used to get the link properties of a network from the Android OS.
     *
     * @param network The network to get the link properties of
     *
     * @return NetworkCapabilities or null - The link properties from the the Android OS (can be null)
     *
     * @author Patches Barrett
     * @since 03/2022
     */
    @RequiresPermission(ACCESS_NETWORK_STATE)
    fun getLinkProperties(network: Network): LinkProperties?

    @RequiresPermission(ACCESS_NETWORK_STATE)
    fun getSSIDOfTheNetworkTheDeviceIsConnectedTo(): String?

    @RequiresPermission(ACCESS_NETWORK_STATE)
    fun getBSSIDOfTheNetworkTheDeviceIsConnectedTo(): String?

    @RequiresPermission(ACCESS_NETWORK_STATE)
    fun getIP(): String?

    /**
     * An internal API that is used to check if the device is currently connected to a Wifi or mobile network.
     *
     * @return Boolean - Whether the device is connected to a Wifi or mobile network. True if connected to a Wifi or
     * mobile network, otherwise false
     *
     * @author Patches Barrett
     * @since 03/2022
     */
    suspend fun isDeviceConnected(): Boolean

    /**
     * An internal API that is used to check if the device is currently roaming.
     *
     * @return Boolean - Whether the network is roaming. True if roaming, otherwise false
     *
     * @author Patches Barrett
     * @since 03/2022
     */
    @RequiresPermission(ACCESS_NETWORK_STATE)
    fun isDeviceRoaming(): Boolean

    @RequiresPermission(ACCESS_NETWORK_STATE)
    fun isTransportTypeMobile(): Boolean

    @RequiresPermission(ACCESS_NETWORK_STATE)
    fun isTransportTypeWifi(): Boolean
}
