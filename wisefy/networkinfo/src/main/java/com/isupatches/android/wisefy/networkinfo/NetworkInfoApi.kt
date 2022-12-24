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
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkQuery
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkResult
import com.isupatches.android.wisefy.networkinfo.entities.GetNetworkConnectionStatusQuery
import com.isupatches.android.wisefy.networkinfo.entities.GetNetworkConnectionStatusResult

/**
 * A set of synchronous APIs for getting information about a network, the device's current network, and the device's IP.
 *
 * @author Patches Barrett
 * @since 03/2022
 */
interface NetworkInfoApi {

    /**
     * A synchronous API to get the device's current network.
     *
     * @param query The details of the query to get the device's current network
     *
     * @see GetCurrentNetworkQuery
     * @see GetCurrentNetworkResult
     *
     * @return GetCurrentNetworkResult - The result of getting the device's current network
     *
     * @author Patches Barrett
     * @since 03/2022
     */
    @RequiresPermission(ACCESS_NETWORK_STATE)
    fun getCurrentNetwork(
        query: GetCurrentNetworkQuery = GetCurrentNetworkQuery()
    ): GetCurrentNetworkResult

    @RequiresPermission(ACCESS_NETWORK_STATE)
    fun getNetworkConnectionStatus(
        query: GetNetworkConnectionStatusQuery = GetNetworkConnectionStatusQuery()
    ): GetNetworkConnectionStatusResult
}
