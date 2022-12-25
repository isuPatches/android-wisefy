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

import android.Manifest.permission.CHANGE_NETWORK_STATE
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.core.constants.DeprecationMessages
import com.isupatches.android.wisefy.networkconnection.callbacks.ChangeNetworkCallbacks
import com.isupatches.android.wisefy.networkconnection.callbacks.ConnectToNetworkCallbacks
import com.isupatches.android.wisefy.networkconnection.callbacks.DisconnectFromCurrentNetworkCallbacks
import com.isupatches.android.wisefy.networkconnection.entities.ChangeNetworkRequest
import com.isupatches.android.wisefy.networkconnection.entities.ConnectToNetworkRequest
import com.isupatches.android.wisefy.networkconnection.entities.DisconnectFromCurrentNetworkRequest

/**
 * A set of asynchronous APIs for connecting to and disconnecting from a network.
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
interface NetworkConnectionApiAsync {

    /**
     * An asynchronous API to change the network the device is connected to.
     *
     * *Notes*
     *  - Locked by the networkConnectionMutex along with functions for connecting, disconnecting, getting the device's
     *    current network connection status, and getting the device's current network
     *  - Will open up the internet connectivity panel
     *
     * @param request The details of the request to change the device's network
     * @param callbacks The callbacks for changing the device's network
     *
     * @see ChangeNetworkCallbacks
     * @see ChangeNetworkRequest
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    @RequiresApi(Build.VERSION_CODES.Q)
    fun changeNetwork(request: ChangeNetworkRequest, callbacks: ChangeNetworkCallbacks?)

    /**
     * An asynchronous API to connect to a network.
     *
     * *Notes*
     *  - Locked by the networkConnectionMutex along with functions for disconnecting, changing, getting the device's
     *    current network connection status, and getting the device's current network
     *
     * @param request The details of the request to connect to a network
     * @param callbacks The callbacks for connecting to a network
     *
     * @see ConnectToNetworkCallbacks
     * @see ConnectToNetworkRequest
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    @Deprecated(DeprecationMessages.NetworkConnection.CONNECT_TO_NETWORK)
    @RequiresPermission(CHANGE_NETWORK_STATE)
    fun connectToNetwork(request: ConnectToNetworkRequest, callbacks: ConnectToNetworkCallbacks?)

    /**
     * An asynchronous API to disconnect from the current network.
     *
     * *Notes*
     *  - Locked by the networkConnectionMutex along with functions for connecting, changing, getting the device's
     *    current network connection status, and getting the device's current network
     *
     * @param request The details of the request to disconnect from the current network
     * @param callbacks The callbacks for disconnecting from a network
     *
     * @see DisconnectFromCurrentNetworkRequest
     * @see DisconnectFromCurrentNetworkCallbacks
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    @Deprecated(DeprecationMessages.NetworkConnection.DISCONNECT_FROM_CURRENT_NETWORK)
    fun disconnectFromCurrentNetwork(
        request: DisconnectFromCurrentNetworkRequest,
        callbacks: DisconnectFromCurrentNetworkCallbacks?
    )
}
