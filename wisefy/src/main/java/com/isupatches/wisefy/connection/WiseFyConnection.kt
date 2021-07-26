/*
 * Copyright 2019 Patches Klinefelter
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
package com.isupatches.wisefy.connection

/**
 * An interface with methods that relate to checking device connectivity.
 *
 * @see [DefaultWiseFyConnection]
 *
 * Updates
 * - 05/12/2019
 *      * Made more generic for pre and post SDK 23 classes
 *      * Added init and destroy signatures
 *      * Split isNetworkConnectedAndMatchesType into isDeviceConnectedToWifiNetwork
 *        and isDeviceConnectedToMobileNetwork
 *      * Added isDeviceRoaming
 * - 07/26/2021: Renamed from WiseFyConnectionSDK23 to DefaultWiseFyConnection
 *
 * @author Patches
 * @since 3.0
 */
internal interface WiseFyConnection {

    fun init()

    fun destroy()

    fun isCurrentNetworkConnectedToSSID(ssid: String?): Boolean

    fun isDeviceConnectedToWifiNetwork(): Boolean

    fun isDeviceConnectedToMobileNetwork(): Boolean

    fun isDeviceRoaming(): Boolean

    fun isNetworkConnected(): Boolean

    fun waitToConnectToSSID(ssid: String?, timeoutInMillis: Int): Boolean
}
