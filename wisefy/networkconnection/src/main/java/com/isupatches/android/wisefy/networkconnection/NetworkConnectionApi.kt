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
package com.isupatches.android.wisefy.networkconnection

import com.isupatches.android.wisefy.core.entities.DeprecationMessages
import com.isupatches.android.wisefy.networkconnection.entities.ConnectToNetworkRequest
import com.isupatches.android.wisefy.networkconnection.entities.ConnectToNetworkResult
import com.isupatches.android.wisefy.networkconnection.entities.DisconnectFromCurrentNetworkRequest
import com.isupatches.android.wisefy.networkconnection.entities.DisconnectFromCurrentNetworkResult

/**
 * A set of synchronous APIs for connecting to and disconnecting from a network.
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
interface NetworkConnectionApi {

    /**
     * A synchronous API to connect to a network.
     *
     * @param request The details of the request to connect to a network
     *
     * @see ConnectToNetworkRequest
     * @see ConnectToNetworkResult
     *
     * @return ConnectToNetworkResult - The result of connecting to a network
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    fun connectToNetwork(request: ConnectToNetworkRequest): ConnectToNetworkResult

    /**
     * A synchronous API to disconnect from the current network.
     *
     * @see DisconnectFromCurrentNetworkRequest
     * @see DisconnectFromCurrentNetworkResult
     *
     * @return DisconnectFromCurrentNetworkResult - The result of disconnecting from the current network
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @Deprecated(DeprecationMessages.NetworkConnection.DISCONNECT_FROM_CURRENT_NETWORK)
    fun disconnectFromCurrentNetwork(
        request: DisconnectFromCurrentNetworkRequest = DisconnectFromCurrentNetworkRequest()
    ): DisconnectFromCurrentNetworkResult
}
