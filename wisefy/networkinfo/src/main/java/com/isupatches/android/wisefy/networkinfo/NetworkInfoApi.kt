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
package com.isupatches.android.wisefy.networkinfo

import android.Manifest.permission.ACCESS_NETWORK_STATE
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkInfoRequest
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkInfoResult
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkRequest
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkResult
import com.isupatches.android.wisefy.networkinfo.entities.GetIPRequest
import com.isupatches.android.wisefy.networkinfo.entities.GetIPResult

/**
 * A set of synchronous APIs for getting information about a network, the device's current network, and the device's IP.
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
interface NetworkInfoApi {

    /**
     * A synchronous API to get the device's current network.
     *
     * @param request The details of the request to get the device's current network
     *
     * @see GetCurrentNetworkRequest
     * @see GetCurrentNetworkResult
     *
     * @return GetCurrentNetworkResult - The result of getting the device's current network
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    fun getCurrentNetwork(
        request: GetCurrentNetworkRequest = GetCurrentNetworkRequest()
    ): GetCurrentNetworkResult

    /**
     * A synchronous API to get the device's IP.
     *
     * @param request The details of the request to get the device's IP
     *
     * @see GetIPRequest
     * @see GetIPResult
     *
     * @return GetIPResult - The result of getting the device's IP
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(ACCESS_NETWORK_STATE)
    fun getIP(request: GetIPRequest = GetIPRequest()): GetIPResult

    /**
     * A synchronous API to get the information for a network.
     *
     * @param request The details of the request to get the information for a network
     *
     * @see GetCurrentNetworkInfoRequest
     * @see GetCurrentNetworkInfoResult
     *
     * @return GetNetworkInfoResult - The result of getting the information for a network
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(ACCESS_NETWORK_STATE)
    fun getCurrentNetworkInfo(
        request: GetCurrentNetworkInfoRequest = GetCurrentNetworkInfoRequest()
    ): GetCurrentNetworkInfoResult
}
