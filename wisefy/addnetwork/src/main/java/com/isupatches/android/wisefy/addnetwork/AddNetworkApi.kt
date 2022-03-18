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
package com.isupatches.android.wisefy.addnetwork

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.CHANGE_WIFI_STATE
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkResult
import com.isupatches.android.wisefy.addnetwork.entities.AddOpenNetworkRequest
import com.isupatches.android.wisefy.addnetwork.entities.AddWPA2NetworkRequest
import com.isupatches.android.wisefy.addnetwork.entities.AddWPA3NetworkRequest

/**
 * A set of synchronous APIs for adding a network.
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
interface AddNetworkApi {

    /**
     * A synchronous API to add an open network.
     *
     * @param request The details of the request to add an open network
     *
     * @see AddOpenNetworkRequest
     * @see AddNetworkResult
     *
     * @return AddNetworkResult - The result when adding the open network
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    fun addOpenNetwork(request: AddOpenNetworkRequest): AddNetworkResult

    /**
     * A synchronous API to add a WPA2 network.
     *
     * @param request The details of the request to add a WPA2 network
     *
     * @see AddWPA2NetworkRequest
     * @see AddNetworkResult
     *
     * @return AddNetworkResult - The result when adding the WPA2 network
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    fun addWPA2Network(request: AddWPA2NetworkRequest): AddNetworkResult

    /**
     * A synchronous API to add a WPA3 network.
     *
     * @param request The details of the request to add a WPA3 network
     *
     * @see AddWPA3NetworkRequest
     * @see AddNetworkResult
     *
     * @return AddNetworkResult - The result when adding the WPA3 network
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresApi(Build.VERSION_CODES.Q)
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    fun addWPA3Network(request: AddWPA3NetworkRequest): AddNetworkResult
}
