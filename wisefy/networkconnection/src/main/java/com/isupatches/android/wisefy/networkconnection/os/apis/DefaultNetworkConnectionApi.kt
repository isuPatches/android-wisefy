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
package com.isupatches.android.wisefy.networkconnection.os.apis

/**
 * A default internal API for connecting to and disconnecting from a network through the Android OS.
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
internal interface DefaultNetworkConnectionApi {

    /**
     * A default internal API to connect to a network through the Android OS.
     *
     * @param ssid The SSID of the network to connect to
     * @param timeoutInMillis The timeout in milliseconds to wait for a connection/disconnection for the network
     *
     * @return Boolean or null - Whether or not connecting to the network was successful, null if no network found
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    fun connectToNetworkBySSID(ssid: String, timeoutInMillis: Int): Boolean?

    /**
     * A default internal API to connect to a network through the Android OS.
     *
     * @param bssid The BSSID of the network to connect to
     * @param timeoutInMillis The timeout in milliseconds to wait for a connection/disconnection for the network
     *
     * @return Boolean or null - Whether or not connecting to the network was successful, null if no network found
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    fun connectToNetworkByBSSID(bssid: String, timeoutInMillis: Int): Boolean?

    /**
     * A default internal API to disconnect from the current network through the Android OS.
     *
     * @return Boolean - Whether or not disconnecting from the current network was successful
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    fun disconnectFromCurrentNetwork(): Boolean
}
