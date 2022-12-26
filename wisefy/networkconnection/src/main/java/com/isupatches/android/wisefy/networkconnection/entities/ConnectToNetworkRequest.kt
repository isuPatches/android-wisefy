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
package com.isupatches.android.wisefy.networkconnection.entities

/**
 * A set of classes and objects that are used in requests to connect to a network.
 *
 * @property timeoutInMillis The timeout in milliseconds to wait for a connection to the network
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
sealed class ConnectToNetworkRequest(
    open val timeoutInMillis: Int
) {

    /**
     * A representation of a request to connect to a network by SSID.
     *
     * @property ssid The SSID of the network to connect to or disconnect from
     * @property timeoutInMillis The timeout in milliseconds to wait for a connection to the network
     *
     * @see ConnectToNetworkRequest
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    data class SSID(
        val ssid: String,
        override val timeoutInMillis: Int
    ) : ConnectToNetworkRequest(timeoutInMillis)

    /**
     * A representation of a request to connect to a network by BSSID.
     *
     * @property bssid The BSSID of the network to connect to or disconnect from
     * @property timeoutInMillis The timeout in milliseconds to wait for a connection to the network
     *
     * @see ConnectToNetworkRequest
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    data class BSSID(
        val bssid: String,
        override val timeoutInMillis: Int
    ) : ConnectToNetworkRequest(timeoutInMillis)
}
