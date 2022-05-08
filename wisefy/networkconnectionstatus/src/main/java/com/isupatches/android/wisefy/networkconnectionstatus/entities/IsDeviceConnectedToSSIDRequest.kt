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
package com.isupatches.android.wisefy.networkconnectionstatus.entities

/**
 * A set of classes and objects that are used to represent requests checking if the device is connected to
 * a matching network.
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
sealed class IsDeviceConnectedToSSIDRequest {

    /**
     * A data representation to check if the device is connected to a network given an SSID.
     *
     * @param regex The regex to use when checking the SSID
     *
     * @see IsDeviceConnectedToSSIDRequest
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    data class SSID(val regex: String) : IsDeviceConnectedToSSIDRequest()

    /**
     * A data representation to check if the device is connected to a network given an BSSID.
     *
     * @param regex The regex to use when checking the BSSID
     *
     * @see IsDeviceConnectedToSSIDRequest
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    data class BSSID(val regex: String) : IsDeviceConnectedToSSIDRequest()
}