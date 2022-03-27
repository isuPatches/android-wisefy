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
import com.isupatches.android.wisefy.addnetwork.callbacks.AddNetworkCallbacks
import com.isupatches.android.wisefy.addnetwork.entities.AddOpenNetworkRequest
import com.isupatches.android.wisefy.addnetwork.entities.AddWPA2NetworkRequest
import com.isupatches.android.wisefy.addnetwork.entities.AddWPA3NetworkRequest

/**
 * A set of asynchronous APIs for getting and searching for nearby access points.
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
interface AddNetworkApiAsync {

    /**
     * An asynchronous API to add an open network.
     *
     * @param request The details of the request to add an open network
     * @param callbacks The callbacks for when the result for adding a network is returned
     *
     * @see AddOpenNetworkRequest
     * @see AddNetworkCallbacks
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    fun addOpenNetwork(request: AddOpenNetworkRequest, callbacks: AddNetworkCallbacks?)

    /**
     * An asynchronous API to add a WPA2 network.
     *
     * @param request The details of the request to add a WPA2 network
     * @param callbacks The callbacks for when the result for adding a network is returned
     *
     * @see AddWPA2NetworkRequest
     * @see AddNetworkCallbacks
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    fun addWPA2Network(request: AddWPA2NetworkRequest, callbacks: AddNetworkCallbacks?)

    /**
     * An asynchronous API to add a WPA3 network.
     *
     * @param request The details of the request to add a WPA3 network
     * @param callbacks The callbacks for when the result for adding a network is returned
     *
     * @see AddWPA3NetworkRequest
     * @see AddNetworkCallbacks
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    @RequiresApi(Build.VERSION_CODES.Q)
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    fun addWPA3Network(request: AddWPA3NetworkRequest, callbacks: AddNetworkCallbacks?)
}
