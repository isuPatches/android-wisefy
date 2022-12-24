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
import androidx.annotation.RequiresPermission
import com.isupatches.android.wisefy.addnetwork.callbacks.AddNetworkCallbacks
import com.isupatches.android.wisefy.addnetwork.entities.AddNetworkRequest

/**
 * A set of asynchronous APIs related to adding networks.
 *
 * @author Patches Klinefelter
 * @since 12/2022, version 5.0.0
 */
interface AddNetworkApiAsync {

    /**
     * An asynchronous API for adding a network.
     *
     * @param request The details of the request to add a network
     * @param callbacks The optional callbacks for when the result for adding a network is returned
     *
     * @see AddNetworkRequest
     * @see AddNetworkCallbacks
     *
     * @author Patches Klinefelter
     * @since 12/2022, version 5.0.0
     */
    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE])
    fun addNetwork(request: AddNetworkRequest, callbacks: AddNetworkCallbacks?)
}
