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
package com.isupatches.android.wisefy.networkinfo

import android.Manifest.permission.ACCESS_NETWORK_STATE
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.networkinfo.callbacks.GetCurrentNetworkCallbacks
import com.isupatches.android.wisefy.networkinfo.callbacks.GetNetworkConnectionStatusCallbacks
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkQuery
import com.isupatches.android.wisefy.networkinfo.entities.GetNetworkConnectionStatusQuery

/**
 * A set of asynchronous APIs for getting information about the device's current network and connection status.
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
interface NetworkInfoApiAsync {

    /**
     * An asynchronous API to get the device's current network.
     *
     * *Notes*
     *  - Locked by the networkConnectionMutex along with functions for connecting, disconnecting, changing and getting
     *    the device's current network connection status
     *
     * @param query The details of the query to get the device's current network
     * @param callbacks The callbacks for retrieving the device's current network
     *
     * @see GetCurrentNetworkQuery
     * @see GetCurrentNetworkCallbacks
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    @RequiresPermission(ACCESS_NETWORK_STATE)
    fun getCurrentNetwork(
        query: GetCurrentNetworkQuery = GetCurrentNetworkQuery(),
        callbacks: GetCurrentNetworkCallbacks?
    )

    /**
     * An asynchronous API to get the device's current network connection status.
     *
     * *Notes*
     *  - Locked by the networkConnectionMutex along with functions for connecting, disconnecting, changing, and getting
     *    the device's current network
     *
     * @param query The details of the query to get the device's current network connection status
     * @param callbacks The callbacks for retrieving the device's current network connection status
     *
     * @see GetNetworkConnectionStatusQuery
     * @see GetNetworkConnectionStatusCallbacks
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    @RequiresPermission(ACCESS_NETWORK_STATE)
    fun getNetworkConnectionStatus(
        query: GetNetworkConnectionStatusQuery = GetNetworkConnectionStatusQuery(),
        callbacks: GetNetworkConnectionStatusCallbacks?
    )
}
