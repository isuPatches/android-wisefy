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

import android.Manifest.permission.CHANGE_NETWORK_STATE
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.networkconnection.callbacks.ConnectToNetworkCallbacks
import com.isupatches.android.wisefy.networkconnection.callbacks.DisconnectFromCurrentNetworkCallbacks
import com.isupatches.android.wisefy.networkconnection.entities.ConnectToNetworkRequest
import com.isupatches.android.wisefy.networkconnection.entities.DisconnectFromCurrentNetworkRequest

/**
 * A set of asynchronous APIs for connecting to and disconnecting from a network.
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
interface NetworkConnectionApiAsync {

    /**
     * An asynchronous API to connect to a network.
     *
     * @param request The details of the request to connect to a network
     * @param callbacks The callbacks for connecting to a network
     *
     * @see ConnectToNetworkCallbacks
     * @see ConnectToNetworkRequest
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(CHANGE_NETWORK_STATE)
    fun connectToNetwork(request: ConnectToNetworkRequest, callbacks: ConnectToNetworkCallbacks?)

    /**
     * An asynchronous API to disconnect from the current network.
     *
     * @param request The details of the request to disconnect from the current network
     * @param callbacks The callbacks for disconnecting from a network
     *
     * @see DisconnectFromCurrentNetworkRequest
     * @see DisconnectFromCurrentNetworkCallbacks
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    fun disconnectFromCurrentNetwork(
        request: DisconnectFromCurrentNetworkRequest,
        callbacks: DisconnectFromCurrentNetworkCallbacks?
    )
}
