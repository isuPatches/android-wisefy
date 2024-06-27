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
package com.isupatches.android.wisefy.networkconnection

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.core.constants.DeprecationMessages
import com.isupatches.android.wisefy.networkconnection.entities.ChangeNetworkRequest
import com.isupatches.android.wisefy.networkconnection.entities.ChangeNetworkResult
import com.isupatches.android.wisefy.networkconnection.entities.ConnectToNetworkRequest
import com.isupatches.android.wisefy.networkconnection.entities.ConnectToNetworkResult
import com.isupatches.android.wisefy.networkconnection.entities.DisconnectFromCurrentNetworkRequest
import com.isupatches.android.wisefy.networkconnection.entities.DisconnectFromCurrentNetworkResult

/**
 * A set of synchronous APIs for connecting to and disconnecting from a network.
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
interface NetworkConnectionApi {

    /**
     * An synchronous API to change the network the device is connected to.
     *
     * *Notes*
     *  - Will open up the internet connectivity panel
     *
     * @param request The details of the request to change the device's network
     *
     * @see ChangeNetworkRequest
     * @see ChangeNetworkResult
     *
     * @return ChangeNetworkResult - The result of changing the device's network
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    @RequiresApi(Build.VERSION_CODES.Q)
    fun changeNetwork(request: ChangeNetworkRequest): ChangeNetworkResult

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
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    @Deprecated(DeprecationMessages.NetworkConnection.CONNECT_TO_NETWORK)
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE, ACCESS_NETWORK_STATE])
    fun connectToNetwork(request: ConnectToNetworkRequest): ConnectToNetworkResult

    /**
     * A synchronous API to disconnect from the current network.
     *
     * @see DisconnectFromCurrentNetworkRequest
     * @see DisconnectFromCurrentNetworkResult
     *
     * @return DisconnectFromCurrentNetworkResult - The result of disconnecting from the current network
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    @Deprecated(DeprecationMessages.NetworkConnection.DISCONNECT_FROM_CURRENT_NETWORK)
    fun disconnectFromCurrentNetwork(request: DisconnectFromCurrentNetworkRequest): DisconnectFromCurrentNetworkResult
}
