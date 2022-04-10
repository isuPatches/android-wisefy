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
package com.isupatches.android.wisefy.networkconnectionstatus.os.apis

import android.Manifest.permission.ACCESS_NETWORK_STATE
import androidx.annotation.RequiresPermission

/**
 * A default internal API for checking the device's connection status through the Android OS.
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
internal interface DefaultNetworkConnectionStatusApi {

    /**
     * An internal API that is used to add a network watcher.
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(ACCESS_NETWORK_STATE)
    fun attachNetworkWatcher()

    /**
     * An internal API that is used to remove a network watcher.
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    fun detachNetworkWatcher()

    /**
     * An internal API that is used to check if the device is currently connected to a given BSSID network.
     *
     * @param regexForBSSID The regex to match for the access point's BSSID
     *
     * @return Boolean - Whether the device is connected to a given BSSID. True if connected to the given BSSID,
     * otherwise false
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(ACCESS_NETWORK_STATE)
    fun isDeviceConnectedToBSSID(regexForBSSID: String): Boolean

    /**
     * An internal API that is used to check if the device is currently connected to a mobile network.
     *
     * @return Boolean - Whether the device is connected to a mobile network. True if connected to a mobile network,
     * otherwise false
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(ACCESS_NETWORK_STATE)
    fun isDeviceConnectedToMobileNetwork(): Boolean

    /**
     * An internal API that is used to check if the device is currently connected to a Wifi or mobile network.
     *
     * @return Boolean - Whether the device is connected to a Wifi or mobile network. True if connected to a Wifi or
     * mobile network, otherwise false
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    fun isDeviceConnectedToMobileOrWifiNetwork(): Boolean

    /**
     * An internal API that is used to check if the device is currently connected to a given SSID network.
     *
     * @param regexForSSID The regex to match for the access point's SSID
     *
     * @return Boolean - Whether the device is connected to a given SSID. True if connected to the given SSID,
     * otherwise false
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(ACCESS_NETWORK_STATE)
    fun isDeviceConnectedToSSID(regexForSSID: String): Boolean

    /**
     * An internal API that is used to check if the device is currently connected to a Wifi network.
     *
     * @return Boolean - Whether the device is connected to a Wifi network. True if connected to a Wifi network,
     * otherwise false
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(ACCESS_NETWORK_STATE)
    fun isDeviceConnectedToWifiNetwork(): Boolean

    /**
     * An internal API that is used to check if the device is currently roaming.
     *
     * @return Boolean - Whether the network is roaming. True if roaming, otherwise false
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(ACCESS_NETWORK_STATE)
    fun isDeviceRoaming(): Boolean
}
