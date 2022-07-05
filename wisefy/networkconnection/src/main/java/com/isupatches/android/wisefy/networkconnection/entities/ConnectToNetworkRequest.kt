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
package com.isupatches.android.wisefy.networkconnection.entities

/**
 * A set of classes that are used in requests to connect and disconnect from a network.
 *
 * @property timeoutInMillis The timeout in milliseconds to wait for a connection/disconnection for the network
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
sealed class ConnectToNetworkRequest(
    open val timeoutInMillis: Int
) {

    /**
     * A data representation of a request to connect to or disconnect from a network by SSID.
     *
     * @property ssid The SSID of the network to connect to or disconnect from
     * @property timeoutInMillis The timeout in milliseconds to wait for a connection/disconnection for the network
     *
     * @see ConnectToNetworkRequest
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    data class SSID(
        val ssid: String,
        override val timeoutInMillis: Int
    ) : ConnectToNetworkRequest(timeoutInMillis)

    /**
     * A data representation of a request to connect or disconnect from a network by BSSID.
     *
     * @property bssid The BSSID of the network to connect to or disconnect from
     * @property timeoutInMillis The timeout in milliseconds to wait for a connection/disconnection for the network
     *
     * @see ConnectToNetworkRequest
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    data class BSSID(
        val bssid: String,
        override val timeoutInMillis: Int
    ) : ConnectToNetworkRequest(timeoutInMillis)
}
