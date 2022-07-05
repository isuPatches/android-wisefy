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
import com.isupatches.android.wisefy.networkinfo.callbacks.GetCurrentNetworkCallbacks
import com.isupatches.android.wisefy.networkinfo.callbacks.GetCurrentNetworkInfoCallbacks
import com.isupatches.android.wisefy.networkinfo.callbacks.GetIPCallbacks
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkInfoRequest
import com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkRequest
import com.isupatches.android.wisefy.networkinfo.entities.GetIPRequest

/**
 * A set of asynchronous APIs for getting information about a network, the device's current network,
 * and the device's IP.
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
interface NetworkInfoApiAsync {

    /**
     * An asynchronous API to get the device's current network.
     *
     * @param request The details of the request to get the device's current network
     * @param callbacks The callbacks for retrieving the device's current network
     *
     * @see GetCurrentNetworkRequest
     * @see GetCurrentNetworkCallbacks
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    fun getCurrentNetwork(
        request: GetCurrentNetworkRequest = GetCurrentNetworkRequest(),
        callbacks: GetCurrentNetworkCallbacks?
    )

    /**
     * An asynchronous API to get the device's IP.
     *
     * @param request The details of the request to get the device's IP
     * @param callbacks The callbacks for retrieving the device's IP
     *
     * @see GetIPRequest
     * @see GetIPCallbacks
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(ACCESS_NETWORK_STATE)
    fun getIP(request: GetIPRequest = GetIPRequest(), callbacks: GetIPCallbacks?)

    /**
     * An asynchronous API to get the information for a network.
     *
     * @param request The details of the request to get the information for a network
     * @param callbacks The callbacks for retrieving the information for a network
     *
     * @see GetCurrentNetworkInfoRequest
     * @see GetCurrentNetworkInfoCallbacks
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(ACCESS_NETWORK_STATE)
    fun getCurrentNetworkInfo(
        request: GetCurrentNetworkInfoRequest = GetCurrentNetworkInfoRequest(),
        callbacks: GetCurrentNetworkInfoCallbacks?
    )
}
