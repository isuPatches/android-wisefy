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
package com.isupatches.android.wisefy.networkconnectionstatus

import com.isupatches.android.wisefy.networkconnectionstatus.entities.IsDeviceConnectedResult
import com.isupatches.android.wisefy.networkconnectionstatus.entities.IsDeviceConnectedToMobileNetworkRequest
import com.isupatches.android.wisefy.networkconnectionstatus.entities.IsDeviceConnectedToMobileOrWifiNetworkRequest
import com.isupatches.android.wisefy.networkconnectionstatus.entities.IsDeviceConnectedToSSIDRequest
import com.isupatches.android.wisefy.networkconnectionstatus.entities.IsDeviceConnectedToWifiNetworkRequest
import com.isupatches.android.wisefy.networkconnectionstatus.entities.IsDeviceRoamingRequest
import com.isupatches.android.wisefy.networkconnectionstatus.entities.IsDeviceRoamingResult

/**
 * A set of synchronous APIs for checking the device's connection status.
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
interface NetworkConnectionStatusApi {

    /**
     * An internal API that is used to check if the device is currently connected to a mobile network.
     *
     * @param request The details of the request to check if the device is connected to a mobile network
     *
     * @return IsDeviceConnectedResult - The result of if the device is currently connected to a mobile network
     *
     * @see IsDeviceConnectedToMobileNetworkRequest
     * @see IsDeviceConnectedResult
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    fun isDeviceConnectedToMobileNetwork(
        request: IsDeviceConnectedToMobileNetworkRequest = IsDeviceConnectedToMobileNetworkRequest()
    ): IsDeviceConnectedResult

    /**
     * A synchronous API that is used to check if the device is currently connected to a Wifi or mobile network.
     *
     * @param request The details of the request to check if the device is connected to a mobile or Wifi network
     *
     * @return IsDeviceConnectedResult - The result of if the device is currently connected to a Wifi or mobile network
     *
     * @see IsDeviceConnectedToMobileOrWifiNetworkRequest
     * @see IsDeviceConnectedResult
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    fun isDeviceConnectedToMobileOrWifiNetwork(
        request: IsDeviceConnectedToMobileOrWifiNetworkRequest = IsDeviceConnectedToMobileOrWifiNetworkRequest()
    ): IsDeviceConnectedResult

    /**
     * A synchronous API that is used to check if the device is currently connected to a given SSID network.
     *
     * @param request The details of the request to check if the device is currently connected to a given SSID
     *
     * @return IsDeviceConnectedResult - The result of if the device is currently connected to a given SSID
     *
     * @see IsDeviceConnectedToSSIDRequest
     * @see IsDeviceConnectedResult
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    fun isDeviceConnectedToSSID(request: IsDeviceConnectedToSSIDRequest): IsDeviceConnectedResult

    /**
     * A synchronous API that is used to check if the device is currently connected to a Wifi network.
     *
     * @param request The details of the request to check if the device is connected to a Wifi network
     *
     * @return IsDeviceConnectedResult - The result of if the device is currently connected to a Wifi network
     *
     * @see IsDeviceConnectedToWifiNetworkRequest
     * @see IsDeviceConnectedResult
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    fun isDeviceConnectedToWifiNetwork(
        request: IsDeviceConnectedToWifiNetworkRequest = IsDeviceConnectedToWifiNetworkRequest()
    ): IsDeviceConnectedResult

    /**
     * A synchronous API that is used to check if the device is currently roaming.
     *
     * @param request The details of the request to see if the device is roaming
     *
     * @return IsDeviceRoamingResult - The result of if the device is currently roaming
     *
     * @see IsDeviceRoamingRequest
     * @see IsDeviceRoamingResult
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    fun isDeviceRoaming(request: IsDeviceRoamingRequest = IsDeviceRoamingRequest()): IsDeviceRoamingResult
}
